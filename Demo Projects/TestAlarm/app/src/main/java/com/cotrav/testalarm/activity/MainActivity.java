package com.cotrav.testalarm.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cotrav.testalarm.Alarm;
import com.cotrav.testalarm.CallHistoryAdapter;
import com.cotrav.testalarm.R;
import com.cotrav.testalarm.data.CallRoomDatabase;
import com.cotrav.testalarm.data.OfflineDatabase;
import com.cotrav.testalarm.viewmodels.AllPhoneNoViewModel;
import com.cotrav.testalarm.viewmodels.FetchDataViewModel;
import com.cotrav.testalarm.viewmodels.LoginViewModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission_group.CAMERA;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    CallRoomDatabase roomDatabase;
    TextView norecord;
    List<OfflineDatabase> offlineDatabase;
    CallHistoryAdapter callHistoryAdapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    FetchDataViewModel fetchDataViewModel;
    AllPhoneNoViewModel allPhoneNoViewModel;
    LoginViewModel loginViewModel;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    ProgressDialog uploadprogressDialog;
    AlertDialog.Builder dialog;
    Button checkin, checkout;
    SharedPreferences loginPref;
    TextView intime, outtime;
    Button uploadBtn, shedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent serviceIntent = new Intent(getApplicationContext(), LifeTimeService.class);
//        if (isMyServiceRunning(getApplicationContext(), LifeTimeService.class)) {
//            stopService(serviceIntent);
//        }
        // startService(serviceIntent);


        //////
        // FOR TESTING////
        /*{
            Alarm alarm = new Alarm(
                    10,
                    12,
                    12,
                    "Daily Check",
                    System.currentTimeMillis(),
                    true,
                    false,
                    true,
                    true, true, true, true, true, true
            );
            alarm.schedule(MainActivity.this);

        }*/
        // ////////////////


        loginPref = getSharedPreferences("login", MODE_PRIVATE);

        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = currentDate.format(new Date());
        final JsonObject json = new JsonObject();
        json.addProperty("userid", loginPref.getString("users_id", "n"));
        //    json.addProperty("userid", "2");
        json.addProperty("date_created", currentDateandTime);
        //     json.addProperty("date_created", "2020-10-11");
        dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Something Went Wrong");
        dialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                allPhoneNoViewModel.getPhoneNoList(json);
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        progressDialog = new ProgressDialog(this);
        uploadprogressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting Phone Numbers");
        progressDialog.setCancelable(false);
        progressDialog.show();
        roomDatabase = CallRoomDatabase.getDatabase(this);
        fetchDataViewModel = ViewModelProviders.of(this).get(FetchDataViewModel.class);
        allPhoneNoViewModel = ViewModelProviders.of(this).get(AllPhoneNoViewModel.class);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        norecord = findViewById(R.id.noRecord);
        swipeRefreshLayout = findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        progressBar = findViewById(R.id.progressbar);
        intime = findViewById(R.id.intime);
        outtime = findViewById(R.id.outtime);
        checkin = findViewById(R.id.checkin);
        checkout = findViewById(R.id.checkout);
        uploadBtn = findViewById(R.id.uploadbtn);
        shedule = findViewById(R.id.shedule);
        offlineDatabase = new ArrayList<>();

        callHistoryAdapter = new CallHistoryAdapter(this, offlineDatabase);
        recyclerView = (RecyclerView) findViewById(R.id.onoff);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        //Log.d("Archived", "Running archived fragment");
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(callHistoryAdapter);


        fetchDataViewModel.getOfflineDataList().observe(this, new Observer<List<OfflineDatabase>>() {
            @Override
            public void onChanged(List<OfflineDatabase> offlineDatabases) {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                if (offlineDatabases != null) {
                    offlineDatabase.clear();
                    offlineDatabase.addAll(offlineDatabases);
                }
                if (offlineDatabases.size() == 0) {
                    norecord.setVisibility(View.VISIBLE);
                } else norecord.setVisibility(View.GONE);

                callHistoryAdapter.notifyDataSetChanged();
            }
        });

        progressBar.setVisibility(View.VISIBLE);

        allPhoneNoViewModel.getPhoneNoList(json).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("OK")) {
                    progressBar.setVisibility(View.GONE);
                    progressDialog.dismiss();
                }
                if (s.equals("ERROR")) {
                    progressBar.setVisibility(View.GONE);
                    progressDialog.dismiss();
                    dialog.show();
                }
            }
        });

        allPhoneNoViewModel.getUploadCall().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("success")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Success");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                    uploadprogressDialog.dismiss();

                }
            }
        });
        if (!checkPermission()) {
            ActivityCompat.requestPermissions(this, new String[]{READ_CALL_LOG, READ_PHONE_STATE}, 1);

        }

        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String out = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
                intime.setText(out);
            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String out = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
                outtime.setText(out);
            }
        });
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
        shedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alarm alarm = new Alarm(
                        10,
                        10,
                        43,
                        "Daily Check",
                        System.currentTimeMillis(),
                        true,
                        false,
                        true,
                        true, true, true, true, true, true
                );
                alarm.schedule(MainActivity.this);

            }
        });

        onstartapp();
    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), READ_CALL_LOG);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRefresh() {
        if (swipeRefreshLayout.isRefreshing()) {

            swipeRefreshLayout.setRefreshing(false);
            fetchDataViewModel.getOfflineDataList();

        }
        // fetchDataViewModel.getOfflineDataList();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean smsAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted && smsAccepted)
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    else {
                        if (ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION) != 1)
                            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                        if (ContextCompat.checkSelfPermission(getApplicationContext(), SEND_SMS) != 1)
                            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                requestPermissions(new String[]{ACCESS_FINE_LOCATION, CAMERA},
                                        1);
                            }

                            return;
                        }

                    }
                }
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            loginViewModel.loguot();
            // cancelJob();

            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();

        }


        return super.onOptionsItemSelected(item);


    }

    void onstartapp() {

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
        alarm.cancelAlarm(this);
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
        alarm2.cancelAlarm(this);
        alarm2.schedule(this);
    }

    void upload() {
        if (offlineDatabase.size() > 0) {
            uploadprogressDialog.setMessage("Uploading...");
            uploadprogressDialog.show();

            JsonArray mainjson = new JsonArray();

            for (int i = 0; i < offlineDatabase.size(); i++) {

                JsonObject json = new JsonObject();
                json.addProperty("phone", offlineDatabase.get(i).getPhoneNo());
                json.addProperty("call_duration", offlineDatabase.get(i).getCallDuration());
                json.addProperty("call_date", offlineDatabase.get(i).getDate());
                json.addProperty("call_details", "details");
                mainjson.add(json);
            }
            allPhoneNoViewModel.getUploadCall(mainjson);

        }

    }

}