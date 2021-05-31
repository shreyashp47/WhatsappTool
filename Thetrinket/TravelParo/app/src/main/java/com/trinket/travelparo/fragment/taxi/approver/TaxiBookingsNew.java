package com.taxivaxi.approver.fragments.taxifragments;


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
import com.taxivaxi.approver.activities.TaxiBookingsActivity;
import com.taxivaxi.approver.adapters.TaxiBookingNewAdapter;
import com.taxivaxi.approver.models.bookingmodel.TaxiBooking;
import com.taxivaxi.approver.utility.GsonStringConvertor;
import com.taxivaxi.approver.viewmodel.ApproverInfoViewModel;
import com.taxivaxi.approver.viewmodel.TaxiBookingsViewModel;

import java.util.ArrayList;
import java.util.List;


public class TaxiBookingsNew extends Fragment implements SwipeRefreshLayout.OnRefreshListener, TaxiBookingNewAdapter.AcceptRejectTaxiBookingListener
        , TaxiBookingNewAdapter.OnLastLocationReachedListener {
    // Taxi bookings made but unapproved
    private static final String TAG = TaxiBookingsNew.class.getSimpleName();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ApproverInfoViewModel approverInfoViewModel;
    private TaxiBookingsViewModel taxiBookingsViewModel;
    private ArrayList<TaxiBooking> unAssignedTaxiBooking;
    private TaxiBookingNewAdapter taxiBookingNewAdapter;
    private LinearLayout connectionErrorLayout;
    private TextView connectionErrorTextView;
    View view;
    int pageNo = 1;
    int position = 0;
    int status = 0;
    boolean hasMoreBooking = true;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        approverInfoViewModel = ViewModelProviders.of(getActivity()).get(ApproverInfoViewModel.class);
        taxiBookingsViewModel = ViewModelProviders.of(getActivity()).get(TaxiBookingsViewModel.class);
        unAssignedTaxiBooking = new ArrayList<>();

        view = inflater.inflate(R.layout.new_recycler_view_layout, container, false);
        TaxiBookingsActivity.count = 0;
        swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh);
        taxiBookingNewAdapter = new TaxiBookingNewAdapter(getActivity(), unAssignedTaxiBooking, this);
        recyclerView = view.findViewById(R.id.bookings_recycler_view);
        connectionErrorLayout = (LinearLayout) view.findViewById(R.id.error_empty_layout);
        connectionErrorTextView = (TextView) view.findViewById(R.id.no_booking_connection_error);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(taxiBookingNewAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        taxiBookingsViewModel.getUnAssignedBookingList(approverInfoViewModel.getAccessToken().getValue(),
                approverInfoViewModel.getAuthType().getValue(), "unapproved", String.valueOf(pageNo))
                .observe(this, new Observer<List<TaxiBooking>>() {
                    @Override
                    public void onChanged(@Nullable List<TaxiBooking> bookings) {
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
                        taxiBookingNewAdapter.notifyItemRangeChanged(position, bookings.size());
                    }
                });

        taxiBookingsViewModel.getNewError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                connectionErrorTextView.setText(s);
                connectionErrorLayout.setVisibility(View.VISIBLE);
                Snackbar.make(view, s, Snackbar.LENGTH_LONG).setAction("Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onRefresh();
                    }
                }).show();
            }
        });

        taxiBookingsViewModel.getHasMoreNewBookings().observe(this, new Observer<Boolean>() {
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
        taxiBookingsViewModel.refreshTaxiList(approverInfoViewModel.getAccessToken().getValue(),
                approverInfoViewModel.getAuthType().getValue(), "unapproved", String.valueOf(pageNo));

    }


    @Override
    public void approveBooking(String bookingId, int position) {
        swipeRefreshLayout.setRefreshing(true);

        unAssignedTaxiBooking.remove(position);
        taxiBookingNewAdapter.notifyDataSetChanged();
        taxiBookingsViewModel.approveBooking(approverInfoViewModel.getAccessToken().getValue(), approverInfoViewModel.getAuthType().getValue(), bookingId)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Log.d("Status", s);
                        if (s != null && s.length() > 0) {
                            Snackbar.make(view, s, Snackbar.LENGTH_LONG).show();
                            onRefresh();
                        }
                    }
                });
    }

    @Override
    public void rejectBooking(String bookingId, int position) {
        swipeRefreshLayout.setRefreshing(true);
        unAssignedTaxiBooking.remove(position);
        taxiBookingNewAdapter.notifyDataSetChanged();
        taxiBookingsViewModel.rejectBooking(approverInfoViewModel.getAccessToken().getValue(), approverInfoViewModel.getAuthType().getValue(), bookingId)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Log.d("Status", s);
                        if (s != null && s.length() > 0) {
                            Snackbar.make(view, s, Snackbar.LENGTH_LONG).show();
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
            taxiBookingsViewModel.refreshTaxiList(approverInfoViewModel.getAccessToken().getValue(),
                    approverInfoViewModel.getAuthType().getValue(), "unapproved", String.valueOf(pageNo));

        }
    }
}
