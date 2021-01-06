package com.taxivaxi.operator.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.taxivaxi.operator.GsonStringConvertor;
import com.taxivaxi.operator.PendingDutySlipAdapter;
import com.taxivaxi.operator.R;
import com.taxivaxi.operator.activity.DetailsActivity;
import com.taxivaxi.operator.model.archivedbooking.Booking;
import com.taxivaxi.operator.viewmode.DriverInfoViewModel;
import com.taxivaxi.operator.viewmode.DutySlipViewModel;

import java.util.ArrayList;
import java.util.List;

public class PendingDuitySlipFragment extends Fragment implements PendingDutySlipAdapter.RecyclerViewEventListener, SwipeRefreshLayout.OnRefreshListener {
    DutySlipViewModel dutySlipViewModel;
    DriverInfoViewModel driverInfoViewModel;
    ProgressBar progressBar;
    View view;
    List<Booking> bookingList;
    List<String> bookingId;
    PendingDutySlipAdapter pendingDutySlipAdapter;
    ArrayAdapter<String> bookingIdAdapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    //SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pending_duity_slip, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Bookings");
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            setHasOptionsMenu(true);
        }
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.duty_recycleview);
        swipeRefreshLayout = view.findViewById(R.id.duty_swipe_refresh_layout);
     //   searchView = view.findViewById(R.id.search);


        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        dutySlipViewModel = ViewModelProviders.of(this).get(DutySlipViewModel.class);
        driverInfoViewModel = ViewModelProviders.of(this).get(DriverInfoViewModel.class);

        bookingList = new ArrayList<>();
        bookingId = new ArrayList<>();
        pendingDutySlipAdapter = new PendingDutySlipAdapter(this, bookingList);
        bookingIdAdapter = new ArrayAdapter<>(getContext(), R.layout.city_spinner_layout, bookingId);

        progressBar.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pendingDutySlipAdapter);

        dutySlipViewModel.getDutySlipList().observe(this, new Observer<List<Booking>>() {
            @Override
            public void onChanged(@Nullable List<Booking> bookings) {
                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
                Log.d("Booking", GsonStringConvertor.gsonToString(bookings));
                if (bookings.size() > 0) {
                    bookingList.clear();
                    bookingList.addAll(bookings);
                    pendingDutySlipAdapter.notifyDataSetChanged();
                    bookingId.clear();
                    for (int i = 0; i < bookings.size(); i++) {
                        bookingId.add(bookings.get(i).referenceNo);
                    }
                    bookingIdAdapter.notifyDataSetChanged();
                }
            }
        });

        dutySlipViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
                Log.e("Error", s);
            }
        });

        dutySlipViewModel.getDutySlips(driverInfoViewModel.getAccessToken().getValue());

/*
         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //filter(s);
                pendingDutySlipAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                pendingDutySlipAdapter.getFilter().filter(s);
                return false;
            }
        });
*/

        return view;
    }

    void filter(String text) {
        List<Booking> temp = new ArrayList();
        for (Booking d : bookingList) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.getReferenceNo().contains(text)) {
                temp.add(d);
            }
        }
        //update recyclerview
       // pendingDutySlipAdapter.updateList(temp);
    }

    @Override
    public void onRefresh() {
        dutySlipViewModel.getDutySlips(driverInfoViewModel.getAccessToken().getValue());
    }

    @Override
    public void onResume() {
        super.onResume();
        dutySlipViewModel.getDutySlips(driverInfoViewModel.getAccessToken().getValue());

    }

    SearchView searchView;
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        ((AppCompatActivity)getActivity()).getMenuInflater().inflate(R.menu.search, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) ((AppCompatActivity)getActivity()).getSystemService(Context.SEARCH_SERVICE);
     /*   searchView = (SearchView) menu.findItem(R.id.actionSearch)
                .getActionView();
*//*
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(((AppCompatActivity)getActivity()).getComponentName()));
*//*
        searchView.setMaxWidth(Integer.MAX_VALUE);*/

        // listening to search query text change
/*
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                pendingDutySlipAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                pendingDutySlipAdapter.getFilter().filter(query);
                return false;
            }
        });
*/
    }

    @Override
    public void onDetailsClicked(Booking booking) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("bookingInfo", GsonStringConvertor.gsonToString(booking));
        intent.putExtra("bookingType", "pending");
        startActivity(intent);

    }


}