package com.trinket.travelparo.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.trinket.travelparo.R;
import com.trinket.travelparo.activities.Bookings.HotelBookingActivity;
import com.trinket.travelparo.activities.Bookings.TaxiBookingActivity;
import com.trinket.travelparo.models.analyticsmodel.TotalBookings;
import com.trinket.travelparo.viewmodels.AnalyticsViewModel;
import com.trinket.travelparo.viewmodels.UserViewModel;

import static android.content.Context.MODE_PRIVATE;

public class ApproverHomeFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    View view;
    TextView showTaxi, showHotel;
    SharedPreferences sharedPreferences;
    TextView unapproved_taxi, approved_taxi, ua_invoice_taxi, unapproved_hotel, approved_hotel, ua_invoice_hotel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private UserViewModel approverInfoViewModel;
    private AnalyticsViewModel analyticsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_approver_home, container, false);
        showTaxi = view.findViewById(R.id.view_taxi);
        showTaxi.setOnClickListener(this);
        showHotel = view.findViewById(R.id.view_hotel);
        showHotel.setOnClickListener(this);
        sharedPreferences = getActivity().getSharedPreferences("user_info", MODE_PRIVATE);
        swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        unapproved_taxi = view.findViewById(R.id.unapproved_taxi);
        approved_taxi = view.findViewById(R.id.approved_taxi);
        ua_invoice_taxi = view.findViewById(R.id.ua_invoice_taxi);
        unapproved_hotel = view.findViewById(R.id.unapproved_hotel);
        approved_hotel = view.findViewById(R.id.approved_hotel);
        ua_invoice_hotel = view.findViewById(R.id.ua_invoice_hotel);
        approverInfoViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        analyticsViewModel = ViewModelProviders.of(getActivity()).get(AnalyticsViewModel.class);

        analyticsViewModel.getAuthLiveAnalyticsData(approverInfoViewModel.getAccessToken(), approverInfoViewModel.user())
                .observe(this, new Observer<TotalBookings>() {
                    @Override
                    public void onChanged(@Nullable TotalBookings totalBookings) {
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                        if (totalBookings != null) {
                            //    totalBookingsCount.setText(totalBookings.totalBookingsCount);
                            //    tripsTomorrowCount.setText(totalBookings.tomTotalBookingsCount);

                            unapproved_taxi.setText(totalBookings.paTaxiBookingsCount);
                            unapproved_hotel.setText(totalBookings.paHotelBookingsCount);


                            approved_taxi.setText(totalBookings.ucTaxiBookingsCount);

                            approved_hotel.setText(totalBookings.ucHotelBookingsCount);
                        }
                    }
                });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_taxi:
                Intent intent = new Intent(getActivity(), TaxiBookingActivity.class);
                getActivity().startActivity(intent);

                break;
            case R.id.view_hotel:
                Intent intent2 = new Intent(getActivity(), HotelBookingActivity.class);
                getActivity().startActivity(intent2);
                break;
        }

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        analyticsViewModel.refreshAnalyticsData(approverInfoViewModel.getAccessToken(), approverInfoViewModel.user());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        approverInfoViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        analyticsViewModel = ViewModelProviders.of(getActivity()).get(AnalyticsViewModel.class);
        analyticsViewModel.refreshAnalyticsData(approverInfoViewModel.getAccessToken(), approverInfoViewModel.user());

    }
}