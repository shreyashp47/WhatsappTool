package com.taxivaxi.thetrinketadmin.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.taxivaxi.thetrinketadmin.R;
import com.taxivaxi.thetrinketadmin.viewmodel.LoginInfoViewModel;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private static final int SPLASH_TIME_OUT = 2000;
    LoginInfoViewModel loginInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        loginInfoViewModel = ViewModelProviders.of(this).get(LoginInfoViewModel.class);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loginInfoViewModel.getIsLogin()) {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);


    }
}