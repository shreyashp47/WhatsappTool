package com.taxivaxi.thetrinketadmin.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toolbar;

import com.taxivaxi.thetrinketadmin.R;

public class OrderActivity extends AppCompatActivity {



    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().setTitle("Orders");
    }
}