package com.taxivaxi.employee.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.taxivaxi.employee.R;


public class UpdateApkActivity extends Activity implements View.OnClickListener{

    TextView updateApk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_apk);
        updateApk=(TextView)findViewById(R.id.update_apk);
        updateApk.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
            this.finishAffinity();
    }

    @Override
    public void onClick(View view) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.taxivaxi.employee")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.taxivaxi.employee")));
        }

    }
}
