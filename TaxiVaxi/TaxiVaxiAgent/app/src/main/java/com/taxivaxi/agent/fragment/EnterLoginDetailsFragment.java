package com.taxivaxi.agent.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.taxivaxi.agent.R;
import com.taxivaxi.agent.activity.HomeActivity;


public class EnterLoginDetailsFragment extends Fragment implements View.OnClickListener {

    EditText loginNo, passwd;
    Button login;

    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_login_details, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


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

                getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
                getActivity().finish();
                /* if (loginNo.getText().toString().equals("")) {
                    loginNo.setError("Please Enter Contact No");
                } else {
                    if (passwd.getText().toString().equals("")) {
                        passwd.setError("Please Enter Password");
                    } else {
                        progressBar.setVisibility(View.VISIBLE);
                        driverInfoViewModel.performLogin(loginNo.getText().toString(),
                                passwd.getText().toString());
                    }
                }*/
                break;
        }
    }

}
