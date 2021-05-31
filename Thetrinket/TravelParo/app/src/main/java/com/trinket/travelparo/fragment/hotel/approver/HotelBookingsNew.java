package com.trinket.travelparo.fragment.hotel.approver;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.taxivaxi.approver.R;
import com.taxivaxi.approver.activities.HotelBookingsActivity;
import com.taxivaxi.approver.adapters.HotelBookingNewAdapter;
import com.taxivaxi.approver.models.hotelbookingmodel.HotelBooking;
import com.taxivaxi.approver.utility.GsonStringConvertor;
import com.taxivaxi.approver.viewmodel.ApproverInfoViewModel;
import com.taxivaxi.approver.viewmodel.HotelBookingViewModel;

import java.util.ArrayList;
import java.util.List;


public class HotelBookingsNew extends Fragment implements SwipeRefreshLayout.OnRefreshListener,HotelBookingNewAdapter.AcceptRejectHotelBookingListener,HotelBookingNewAdapter.OnLastLocationReachedListener {
    // Taxi bookings made but unapproved
    private static final String TAG = com.taxivaxi.approver.fragments.hotelfragments.HotelBookingsNew.class.getSimpleName();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ApproverInfoViewModel approverInfoViewModel;
    private HotelBookingViewModel hotelBookingViewModel;
    private ArrayList<HotelBooking> unAssignedTaxiBooking;
    private HotelBookingNewAdapter hotelBookingNewAdapter;
    private LinearLayout connectionErrorLayout;
    private TextView connectionErrorTextView;
    private View view;
    int pageNo = 1;
    int position = 0;
    int status = 0;
    boolean hasMoreBooking = true;
    ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        approverInfoViewModel= ViewModelProviders.of(getActivity()).get(ApproverInfoViewModel.class);
        hotelBookingViewModel = ViewModelProviders.of(getActivity()).get(HotelBookingViewModel.class);
        unAssignedTaxiBooking =new ArrayList<>();

        view = inflater.inflate(R.layout.new_recycler_view_layout , container, false);
        HotelBookingsActivity.count = 0;
        swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh);
        hotelBookingNewAdapter = new HotelBookingNewAdapter(getActivity(),unAssignedTaxiBooking,this);
        recyclerView = view.findViewById(R.id.bookings_recycler_view);
        connectionErrorLayout=(LinearLayout) view.findViewById(R.id.error_empty_layout);
        connectionErrorTextView=(TextView)view.findViewById(R.id.no_booking_connection_error);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(hotelBookingNewAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        hotelBookingViewModel.getUnAssignedBookingList(approverInfoViewModel.getAccessToken().getValue(),approverInfoViewModel.getAuthType().getValue(),"unapproved",String.valueOf(pageNo))
                .observe(this, new Observer<List<HotelBooking>>() {
                    @Override
                    public void onChanged(@Nullable List<HotelBooking> bookings) {
                        swipeRefreshLayout.setRefreshing(false);
                        progressBar.setVisibility(View.GONE);
                        connectionErrorLayout.setVisibility(View.GONE);
                        Log.i("Bookings", GsonStringConvertor.gsonToString(bookings));
                        if (bookings != null && pageNo == 1) {
                            unAssignedTaxiBooking.clear();
                            unAssignedTaxiBooking.addAll(bookings);
                            Log.i("CancelledBooking", GsonStringConvertor.gsonToString(bookings));
                        }
                        if (bookings != null && pageNo > 1) {
                            unAssignedTaxiBooking.addAll(bookings);
                        }
                        hotelBookingNewAdapter.notifyItemRangeChanged(position, bookings.size());
                    }
                });

        hotelBookingViewModel.getNewError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
                connectionErrorTextView.setText(s);
                connectionErrorLayout.setVisibility(View.VISIBLE);
                Snackbar.make(view,s, Snackbar.LENGTH_LONG).show();
            }
        });
        hotelBookingViewModel.getHasMoreNewBookings().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                Log.d("HasMore ", "" + aBoolean);
                hasMoreBooking = aBoolean;
            }
        });


        return view;
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        position = 0;
        connectionErrorLayout.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);
        hotelBookingViewModel.refeshData(approverInfoViewModel.getAccessToken().getValue(),
                approverInfoViewModel.getAuthType().getValue(),"unapproved",String.valueOf(pageNo));

    }

    @Override
    public void approveBooking(String bookingId, String bookingStatus,int position) {
        swipeRefreshLayout.setRefreshing(true);

        unAssignedTaxiBooking.remove(position);
        hotelBookingNewAdapter.notifyDataSetChanged();
        hotelBookingViewModel.approveBooking(approverInfoViewModel.getAccessToken().getValue(),approverInfoViewModel.getAuthType().getValue(),bookingId,bookingStatus)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Log.d("Status",s);
                        if (s!=null && s.length()>0){
                            Snackbar.make(view,s, Snackbar.LENGTH_LONG).show();
                            onRefresh();
                        }
                    }
                });
    }

    @Override
    public void rejectBooking(String bookingId,int position) {
        swipeRefreshLayout.setRefreshing(true);
        unAssignedTaxiBooking.remove(position);
        hotelBookingNewAdapter.notifyDataSetChanged();
        hotelBookingViewModel.rejectBooking(approverInfoViewModel.getAccessToken().getValue(),approverInfoViewModel.getAuthType().getValue(),bookingId)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Log.d("Status",s);
                        if (s!=null && s.length()>0){
                            Snackbar.make(view,s, Snackbar.LENGTH_LONG).show();
                            onRefresh();
                        }
                    }
                });
    }

    @Override
    public void onLastLocationReached(int position) {
        Log.d("LastLocationReached", position + " " + hasMoreBooking);
        if (hasMoreBooking) {
            pageNo++;
            this.position = position;
            connectionErrorLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            hotelBookingViewModel.refeshData(approverInfoViewModel.getAccessToken().getValue(),
                    approverInfoViewModel.getAuthType().getValue(),"unapproved",String.valueOf(pageNo));

        }
    }
}
