package com.taxivaxi.operator.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.taxivaxi.operator.activity.HomeActivity;
import com.taxivaxi.operator.R;
import com.taxivaxi.operator.viewmode.DriverInfoViewModel;


public class EnterLoginDetailsFragment extends Fragment implements View.OnClickListener {

    EditText loginNo, passwd;
    Button login;
    DriverInfoViewModel driverInfoViewModel;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_login_details, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        driverInfoViewModel = ViewModelProviders.of(getActivity()).get(DriverInfoViewModel.class);

        driverInfoViewModel.getLoginStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s.equals("successful")) {
                    progressBar.setVisibility(View.GONE);

                    startActivity(new Intent(getActivity(), HomeActivity.class));
                    getActivity().finish();
                }
            }
        });

        driverInfoViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
            }
        });
        loginNo = view.findViewById(R.id.username);
        passwd = view.findViewById(R.id.passwd);
        login = view.findViewById(R.id.login_btn);
        login.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.login_btn:
                if (loginNo.getText().toString().equals("")) {
                    loginNo.setError("Please Enter Contact No");
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    driverInfoViewModel.performLogin(loginNo.getText().toString(),
                            passwd.getText().toString());
                }
                break;
        }
    }

}
