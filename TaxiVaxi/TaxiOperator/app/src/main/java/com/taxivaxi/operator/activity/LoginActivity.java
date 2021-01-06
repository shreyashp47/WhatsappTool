package com.taxivaxi.operator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.taxivaxi.operator.R;
import com.taxivaxi.operator.fragment.EnterLoginDetailsFragment;
import com.taxivaxi.operator.viewmode.DriverInfoViewModel;

public class LoginActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        frameLayout=(FrameLayout)findViewById(R.id.container_login_details);
        Fragment enterLoginDetailsFragment=new EnterLoginDetailsFragment();
        DriverInfoViewModel employeeViewModel= ViewModelProviders.of(this).get(DriverInfoViewModel.class);
        if (employeeViewModel.getIsLogin()) {
            Log.d("Info","access_token "+employeeViewModel.getAccessToken().getValue()+"  "+Boolean.toString(employeeViewModel.getIsLogin()));
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_login_details,enterLoginDetailsFragment)
                    .commit();

        }



    }
}
