package com.cotrav.testalarm.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.cotrav.testalarm.R;
import com.cotrav.testalarm.data.CallRoomDatabase;
import com.cotrav.testalarm.data.OfflineDatabase;
import com.cotrav.testalarm.other.App;
import com.cotrav.testalarm.repos.FetchPhoneRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UploadAllLeadService extends Service {

    SharedPreferences apicallPrf,loginPref;

    FetchPhoneRepository fetchPhoneRepository;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        notification();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                upload();
                fetch();

                //Do something after 100ms
            }
        }, 200);

        return START_NOT_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }




    void fetch(){
        loginPref = getSharedPreferences("login", MODE_PRIVATE);
        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = currentDate.format(new Date());
        final JsonObject json = new JsonObject();
        json.addProperty("userid", loginPref.getString("users_id", "n"));
        json.addProperty("date_created", currentDateandTime);
        fetchPhoneRepository = new FetchPhoneRepository(this.getApplication());
        fetchPhoneRepository.getPhoneNoList(json);
    }
    public void upload() {
        List<OfflineDatabase> offlineDatabase;
        CallRoomDatabase roomDatabase;
        offlineDatabase = new ArrayList<>();
        roomDatabase = CallRoomDatabase.getDatabase(this);

        FetchPhoneRepository fetchPhoneRepository;
        fetchPhoneRepository = new FetchPhoneRepository(this.getApplication());
        offlineDatabase = roomDatabase.daoRecord().getAllRec();
        if (offlineDatabase.size() > 0) {
            JsonArray mainjson = new JsonArray();

            for (int i = 0; i < offlineDatabase.size(); i++) {

                JsonObject json = new JsonObject();
                json.addProperty("phone", offlineDatabase.get(i).getPhoneNo());
                json.addProperty("call_duration", offlineDatabase.get(i).getCallDuration());
                json.addProperty("call_date", offlineDatabase.get(i).getDate());
                json.addProperty("call_details", "details");
                mainjson.add(json);
            }
            fetchPhoneRepository.getUploadLead(mainjson);
        }
        stopSelf();
    }

    public void notification() {
        //set a new Timer
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            Notification.Builder builder = new Notification.Builder(this, App.CHANNEL_1_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Call Uploading")
                    .setContentText("Recording is uploading")
                    .setAutoCancel(true);

            Notification notification = builder.build();
            startForeground(1, notification);

        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)

                    .setContentTitle("Call Uploading")
                    .setContentText("Recording is uploading")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);

            Notification notification = builder.build();

            startForeground(1, notification);

        }

    }

}