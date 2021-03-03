package com.sp.whatsthestatus.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.sp.whatsthestatus.Rulers;
import com.sp.whatsthestatus.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


        /**
         ***
         //hide status bar
         View decorView = getWindow().getDecorView();
         int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
         decorView.setSystemUiVisibility(uiOptions);*/
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date strDate = null;
        try {
            strDate = sdf.parse(Rulers.valid_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (new Date().before(strDate)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkPerm();
                }
            }, 2000);

        } else {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setCancelable(false);

            dialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            dialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    String url_more_app = Rulers.link;
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse(url_more_app));
                    startActivity(viewIntent);
                    finish();
                }
            });

            dialog.setTitle("App is no more valid !");
            dialog.setMessage("Please update to latest version.");
            //dialog.show();

            AlertDialog alert = dialog.create();
            alert.show();

            Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
            nbutton.setTextColor(getColor(R.color.colorPrimary));
            Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
            pbutton.setTextColor(getColor(R.color.colorPrimary));
        }

    }


    public void checkPerm() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                startActivity(new Intent(SplashActivity.this, StatusActivity.class));
                finish();
            }
        } else {
            startActivity(new Intent(SplashActivity.this, StatusActivity.class));
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(SplashActivity.this, StatusActivity.class));
                    finish();
                } else {
                    checkPerm();
                }
                return;
            }

        }
    }
}