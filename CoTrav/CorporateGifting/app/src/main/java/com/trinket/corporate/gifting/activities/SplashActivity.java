package com.trinket.corporate.gifting.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProviders;

import com.trinket.corporate.gifting.R;
import com.trinket.corporate.gifting.viewmodel.LoginInfoViewModel;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private static final int SPLASH_TIME_OUT = 2000;
    LoginInfoViewModel loginInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        loginInfoViewModel = ViewModelProviders.of(this).get(LoginInfoViewModel.class);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loginInfoViewModel.getIsLogin()) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);


    }
}