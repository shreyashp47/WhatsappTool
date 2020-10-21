package com.cotrav.testalarm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.cotrav.testalarm.Alarm;
import com.cotrav.testalarm.R;
import com.cotrav.testalarm.viewmodels.LoginViewModel;

import java.util.Random;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    EditText mobileNo;

    Button loginBtn;
    SharedPreferences loginPref;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPref = getSharedPreferences("login", MODE_PRIVATE);
        if (loginPref.getString("login_status", "n").equals("active")) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        mobileNo = findViewById(R.id.mobile_no);
        loginBtn = findViewById(R.id.login);
        loginBtn.setOnClickListener(this);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getLoginSuccess().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("success")) {
                    scheduleMorningJob();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    mobileNo.setError("Please Login through registered mobile");

                    Toast.makeText(LoginActivity.this, "Please Login through registered mobile", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    void scheduleMorningJob() {

        Alarm alarm = new Alarm(
                10,
                10,
                00,
                "Daily Check",
                System.currentTimeMillis(),
                true,
                false,
                true,
                true, true, true, true, true, true
        );
        //  createAlarmViewModel.insert(alarm);
        alarm.schedule(this);
        Alarm alarm2 = new Alarm(
                7,
                19,
                30,
                "Daily Check",
                System.currentTimeMillis(),
                true,
                false,
                true,
                true, true, true, true, true, true
        );
        //  createAlarmViewModel.insert(alarm);
        alarm2.schedule(this);
    }

    /*   public void scheduleJob( ) {
        ComponentName componentName = new ComponentName(this, DailyJobService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(1600000)
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }
    }*/

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.login:

                if (!mobileNo.getText().toString().equals(""))
                    loginViewModel.getLoginSuccess(mobileNo.getText().toString());
                else
                    mobileNo.setError("Mobile number required");

                break;
        }
    }
}