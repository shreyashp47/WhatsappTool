package com.trinket.corporate.gifting.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.trinket.corporate.gifting.R;
import com.trinket.corporate.gifting.activities.LoginActivity;
import com.trinket.corporate.gifting.viewmodel.LoginInfoViewModel;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    View view;
    TextView logout;
    LoginInfoViewModel loginInfoViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getContext().getColor(R.color.darkGrey));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Profile");
            //((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }
        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(this);


        loginInfoViewModel = ViewModelProviders.of(this).get(LoginInfoViewModel.class);
        loginInfoViewModel.getLogout().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Logout");
                builder.setMessage("Do you want to logout?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loginInfoViewModel.Logout();

                        dialogInterface.dismiss();
                    }
                });
                builder.show();

                break;
        }
    }
}