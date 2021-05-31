package com.taxivaxi.approver.fragments.hotelfragments;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.taxivaxi.approver.R;
import com.taxivaxi.approver.activities.HotelBookingsActivity;
import com.taxivaxi.approver.adapters.HotelBookingsCompletedRejectedAdapter;
import com.taxivaxi.approver.models.hotelbookingmodel.HotelBooking;
import com.taxivaxi.approver.utility.GsonStringConvertor;
import com.taxivaxi.approver.viewmodel.ApproverInfoViewModel;
import com.taxivaxi.approver.viewmodel.HotelBookingViewModel;

import java.util.ArrayList;
import java.util.List;

public class HotelBookingsCompleted extends Fragment implements SwipeRefreshLayout.OnRefreshListener,HotelBookingsCompletedRejectedAdapter.OnLastLocationReachedListener {
    private static final String TAG = HotelBookingsCompleted.class.getSimpleName();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ApproverInfoViewModel approverInfoViewModel;
    private HotelBookingViewModel hotelBookingViewModel;
    private ArrayList<HotelBooking> archivedHotelBookings;
    private HotelBookingsCompletedRejectedAdapter hotelBookingsCompletedRejectedAdapter;
    private LinearLayout connectionErrorLayout;
    private TextView connectionErrorTextView;
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
        hotelBookingViewModel =ViewModelProviders.of(getActivity()).get(HotelBookingViewModel.class);
        archivedHotelBookings =new ArrayList<>();

        final View view = inflater.inflate(R.layout.new_recycler_view_layout , container, false);
        HotelBookingsActivity.count = 0;
        swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh);
        connectionErrorLayout=(LinearLayout) view.findViewById(R.id.error_empty_layout);
        connectionErrorTextView=(TextView)view.findViewById(R.id.no_booking_connection_error);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        hotelBookingsCompletedRejectedAdapter
                = new HotelBookingsCompletedRejectedAdapter(getActivity(), archivedHotelBookings,this);
        recyclerView = view.findViewById(R.id.bookings_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(hotelBookingsCompletedRejectedAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doThis();
            }
        });
        swipeRefreshLayout.setRefreshing(true);

        hotelBookingViewModel.getArchivedBookingList(approverInfoViewModel.getAccessToken().getValue(),approverInfoViewModel.getAuthType().getValue(),"archived",String.valueOf(pageNo))
                .observe(this, new Observer<List<HotelBooking>>() {
                    @Override
                    public void onChanged(@Nullable List<HotelBooking> bookings) {
                        swipeRefreshLayout.setRefreshing(false);
                        progressBar.setVisibility(View.GONE);
                        connectionErrorLayout.setVisibility(View.GONE);
                        Log.i("Bookings", GsonStringConvertor.gsonToString(bookings));
                        if (bookings != null && pageNo == 1) {
                            archivedHotelBookings.clear();
                            archivedHotelBookings.addAll(bookings);
                            Log.i("CancelledBooking", GsonStringConvertor.gsonToString(bookings));
                        }
                        if (bookings != null && pageNo > 1) {
                            archivedHotelBookings.addAll(bookings);
                        }
                        hotelBookingsCompletedRejectedAdapter.notifyItemRangeChanged(position, bookings.size());
                    }
                });

        hotelBookingViewModel.getArchivedError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
                connectionErrorTextView.setText(s);
                connectionErrorLayout.setVisibility(View.VISIBLE);
                Snackbar.make(view,s,Snackbar.LENGTH_LONG).show();
            }
        });
        hotelBookingViewModel.getHasMoreArchivedBookings().observe(this, new Observer<Boolean>() {
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
        doThis();
    }

    public void doThis() {
        pageNo = 1;
        position = 0;
        connectionErrorLayout.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);
        hotelBookingViewModel.refeshData(approverInfoViewModel.getAccessToken().getValue(),
                approverInfoViewModel.getAuthType().getValue(),"archived",String.valueOf(pageNo));

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
                    approverInfoViewModel.getAuthType().getValue(),"archived",String.valueOf(pageNo));

        }
    }
}
