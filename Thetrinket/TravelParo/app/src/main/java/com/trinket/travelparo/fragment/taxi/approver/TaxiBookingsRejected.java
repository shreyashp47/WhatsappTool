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
import com.taxivaxi.approver.adapters.TaxiBookingsCompletedRejectedAdapter;
import com.taxivaxi.approver.models.bookingmodel.TaxiBooking;
import com.taxivaxi.approver.utility.GsonStringConvertor;
import com.taxivaxi.approver.viewmodel.ApproverInfoViewModel;
import com.taxivaxi.approver.viewmodel.TaxiBookingsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanu on 7/26/17.
 */

public class TaxiBookingsRejected extends Fragment implements SwipeRefreshLayout.OnRefreshListener, TaxiBookingsCompletedRejectedAdapter.OnLastLocationReachedListener {
    // Taxi bookings cancelled or rejected
    private static final String TAG = TaxiBookingsCompleted.class.getSimpleName();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ApproverInfoViewModel approverInfoViewModel;
    private TaxiBookingsViewModel taxiBookingsViewModel;
    private ArrayList<TaxiBooking> bookingList;
    private TaxiBookingsCompletedRejectedAdapter taxiBookingsCompletedRejectedAdapter;
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
        bookingList = new ArrayList<>();

        view = inflater.inflate(R.layout.cancled_recycler_view_layout, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        TaxiBookingsActivity.count = 0;
        swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh);
        taxiBookingsCompletedRejectedAdapter
                = new TaxiBookingsCompletedRejectedAdapter(getActivity(), bookingList, this);
        recyclerView = view.findViewById(R.id.bookings_recycler_view);
        connectionErrorLayout = (LinearLayout) view.findViewById(R.id.error_empty_layout);
        connectionErrorTextView = (TextView) view.findViewById(R.id.no_booking_connection_error);
        connectionErrorLayout.setVisibility(View.GONE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(taxiBookingsCompletedRejectedAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        taxiBookingsViewModel.getRejectedBookingList(approverInfoViewModel.getAccessToken().getValue(),
                approverInfoViewModel.getAuthType().getValue(), "rejected", String.valueOf(pageNo))
                .observe(this, new Observer<List<TaxiBooking>>() {
                    @Override
                    public void onChanged(@Nullable List<TaxiBooking> bookings) {
                        swipeRefreshLayout.setRefreshing(false);
                        progressBar.setVisibility(View.GONE);
                        connectionErrorLayout.setVisibility(View.GONE);
                        Log.i("Bookings", GsonStringConvertor.gsonToString(bookings));
                        if (bookings != null && pageNo == 1) {
                            bookingList.clear();
                            bookingList.addAll(bookings);
                            Log.i("CancelledBooking", GsonStringConvertor.gsonToString(bookings));
                        }
                        if (bookings != null && pageNo > 1) {
                            bookingList.addAll(bookings);
                        }
                        taxiBookingsCompletedRejectedAdapter.notifyItemRangeChanged(position, bookings.size());
                    }
                });

        taxiBookingsViewModel.getCancelledError().observe(this, new Observer<String>() {
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
        taxiBookingsViewModel.getHasMoreCancelledBookings().observe(this, new Observer<Boolean>() {
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
                approverInfoViewModel.getAuthType().getValue(), "rejected", String.valueOf(pageNo));

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
                    approverInfoViewModel.getAuthType().getValue(), "rejected", String.valueOf(pageNo));

        }
    }
}
