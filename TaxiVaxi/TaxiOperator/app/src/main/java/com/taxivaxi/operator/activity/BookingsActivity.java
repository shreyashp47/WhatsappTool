package com.taxivaxi.operator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.taxivaxi.operator.GsonStringConvertor;
import com.taxivaxi.operator.PendingDutySlipAdapter;
import com.taxivaxi.operator.R;
import com.taxivaxi.operator.model.archivedbooking.Booking;
import com.taxivaxi.operator.viewmode.DriverInfoViewModel;
import com.taxivaxi.operator.viewmode.DutySlipViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookingsActivity extends AppCompatActivity
        implements
        PendingDutySlipAdapter.RecyclerViewEventListener,
        SwipeRefreshLayout.OnRefreshListener {
    DutySlipViewModel dutySlipViewModel;
    DriverInfoViewModel driverInfoViewModel;
    ProgressBar progressBar;

    List<Booking> bookingList;
    List<String> bookingId;
    PendingDutySlipAdapter pendingDutySlipAdapter;
    ArrayAdapter<String> bookingIdAdapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    String bookingType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);


       /*  if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new CurrentBookingFragment()).commit();
        }*/
       /* Intent in = getIntent();

        Bundle b = in.getExtras();
        bookingType = b.getString("bookingType");


        if (bookingType.equals("cancelled")) {
*/
        /*
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new CancelledBookingFragment()).commit();
*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Bookings");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        }
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.duty_recycleview);
        swipeRefreshLayout = findViewById(R.id.duty_swipe_refresh_layout);
        //   searchView = view.findViewById(R.id.search);


        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        dutySlipViewModel = ViewModelProviders.of(this).get(DutySlipViewModel.class);
        driverInfoViewModel = ViewModelProviders.of(this).get(DriverInfoViewModel.class);

        bookingList = new ArrayList<>();
        bookingId = new ArrayList<>();
        pendingDutySlipAdapter = new PendingDutySlipAdapter(this, bookingList);
        bookingIdAdapter = new ArrayAdapter<>(this
                , R.layout.city_spinner_layout, bookingId);

        progressBar.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
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

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
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
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
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

    @Override
    public void onDetailsClicked(Booking booking) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("bookingInfo", GsonStringConvertor.gsonToString(booking));
        intent.putExtra("bookingType", "pending");
        startActivity(intent);
    }
}