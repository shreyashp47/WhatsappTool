package com.taxivaxi.operator.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.taxivaxi.operator.R;
import com.taxivaxi.operator.activity.BookingsActivity;
import com.taxivaxi.operator.viewmode.DriverInfoViewModel;

import java.util.List;


/**
 * Fragment to shoe home/landing screen of the screen.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    View view;
    //layout represents archived bookings.
    LinearLayout pendingDutySlipLay; //layout represents archived bookings.
    TextView driverName; //TextView driver name.
      ProgressBar progressBar;
    DriverInfoViewModel driverInfoViewModel;  //driver info ViewModel to get driver information.

    public HomeFragment() {
        // Required empty public constructor
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_landing, container, false);
        pendingDutySlipLay = view.findViewById(R.id.pending_booking_lay);
        driverInfoViewModel = ViewModelProviders.of(getActivity()).get(DriverInfoViewModel.class);
        driverName = view.findViewById(R.id.driver_name);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        driverName.setText(driverInfoViewModel.getOperator().getValue().getOperatorName());
        pendingDutySlipLay.setOnClickListener(this);






        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.backgroungBlue));
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        Bundle bundle;
        switch (view.getId()) {

            case R.id.pending_booking_lay:
               /* getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container,new ArchivedBookingFragment())
                        .addToBackStack(null)
                        .commit();*/
                intent = new Intent(getActivity(), BookingsActivity.class);
                bundle = new Bundle();
                bundle.putString("bookingType", "dutyslip");
                intent.putExtras(bundle);
                startActivity(intent);

                break;
        }

    }
}
