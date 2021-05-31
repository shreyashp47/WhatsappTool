package com.trinket.corporate.gifting.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.trinket.corporate.gifting.R;
import com.trinket.corporate.gifting.viewmodel.LoginInfoViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button login;
    EditText email, passwd;
    TextView forgetPasswd;
    LoginInfoViewModel loginInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        login = findViewById(R.id.login);
        email = findViewById(R.id.emailId);
        passwd = findViewById(R.id.passwd);
        forgetPasswd = findViewById(R.id.forgetpasswd);
        forgetPasswd.setOnClickListener(this);
        login.setOnClickListener(this);


        loginInfoViewModel = ViewModelProviders.of(this).get(LoginInfoViewModel.class);
        loginInfoViewModel.getLoginStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("success")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.login) {
            if (email.getText().toString().equals("") && email.getText().toString().length() < 3 &&
                    passwd.getText().toString().equals("") && passwd.getText().toString().length() < 3) {
                if (email.getText().toString().equals("") && email.getText().toString().length() < 3)
                    email.setError("Enter Correct Email-Id");
                if (passwd.getText().toString().equals("") && passwd.getText().toString().length() < 3)
                    passwd.setError("Enter Correct Password");
            } else {
                loginInfoViewModel.performLogin(email.getText().toString(), passwd.getText().toString());
            }
        }
        if (id == R.id.forgetpasswd) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Forget Password");
            dialog.setMessage("Do you want to reset your password");
            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(LoginActivity.this, "Check Your Mail Box", Toast.LENGTH_LONG).show();
                }
            });
            dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.show();
        }
    }
}