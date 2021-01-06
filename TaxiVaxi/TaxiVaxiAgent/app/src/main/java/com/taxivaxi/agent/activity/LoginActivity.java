package com.taxivaxi.agent.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.taxivaxi.agent.R;
import com.taxivaxi.agent.fragment.EnterLoginDetailsFragment;

public class LoginActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        frameLayout=(FrameLayout)findViewById(R.id.container_login_details);
        Fragment enterLoginDetailsFragment=new EnterLoginDetailsFragment();


            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_login_details,enterLoginDetailsFragment)
                    .commit();





    }
}