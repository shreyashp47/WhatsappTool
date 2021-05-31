package com.taxivaxi.thetrinketadmin.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.taxivaxi.thetrinketadmin.R;
import com.taxivaxi.thetrinketadmin.viewmodel.LoginInfoViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button login;
    EditText email, passwd;
    TextView forgetPasswd;
    LoginInfoViewModel loginInfoViewModel;
    AlertDialog.Builder alertDialog;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Login. .  .");

        login = findViewById(R.id.login);
        email = findViewById(R.id.emailId);
        passwd = findViewById(R.id.passwd);
        forgetPasswd = findViewById(R.id.forgetpasswd);
        forgetPasswd.setOnClickListener(this);
        login.setOnClickListener(this);
        alertDialog=new AlertDialog.Builder(this);


        loginInfoViewModel = ViewModelProviders.of(this).get(LoginInfoViewModel.class);
        loginInfoViewModel.getLoginStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("success")) {
                    progressDialog.dismiss();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        loginInfoViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();
                alertDialog.setTitle("Error");
                alertDialog.setMessage(s);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();


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
                progressDialog.show();
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