package com.cotrav.testalarm.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.cotrav.testalarm.repos.FetchPhoneRepository;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationJobService extends JobService {

    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";
    // Notification manager.
    NotificationManager mNotifyManager;
    SharedPreferences apicallPrf,loginPref;
    FetchPhoneRepository fetchPhoneRepository;

    /**
     * Called by the system once it determines it is time to run the job.
     *
     * @param jobParameters Contains the information about the job.
     * @return Boolean indicating whether or not the job was offloaded to a
     * separate thread.
     * In this case, it is false since the notification can be posted on
     * the main thread.
     */
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
/*
        // Create the notification channel.
        createNotificationChannel();

        // Set up the notification content intent to launch the app when
        // clicked.
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (this, 0, new Intent(this, MainActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (this, PRIMARY_CHANNEL_ID)
                .setContentTitle("getString(R.string.job_service)")
                .setContentText("getString(R.string.job_running)")
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.ic_upload)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        mNotifyManager.notify(0, builder.build());*/

        apicallPrf = getSharedPreferences("api_call", MODE_PRIVATE);
        loginPref = getSharedPreferences("login", MODE_PRIVATE);
      //  if (apicallPrf.getString("api_call", "n").equals("upload")) {

            Intent myService = new Intent(this, UploadAllLeadService.class);
            startService(myService);



     //   }if (apicallPrf.getString("api_call", "n").equals("fetch")) {


     /*       SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");
            String currentDateandTime = currentDate.format(new Date());
            final JsonObject json = new JsonObject();
            json.addProperty("userid", loginPref.getString("users_id", "n"));
            json.addProperty("date_created", currentDateandTime);
            fetchPhoneRepository = new FetchPhoneRepository(this.getApplication());
            fetchPhoneRepository.getPhoneNoList(json);
            */

       // }


        return false;
    }

    /**
     * Called by the system when the job is running but the conditions are no
     * longer met.
     * In this example it is never called since the job is not offloaded to a
     * different thread.
     *
     * @param jobParameters Contains the information about the job.
     * @return Boolean indicating whether the job needs rescheduling.
     */
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    /**
     * Creates a Notification channel, for OREO and higher.
     */
    public void createNotificationChannel() {

        // Create a notification manager object.
        mNotifyManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "getString(R.string.job_service_notification)",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("getString(R.string.notification_channel_description)");

            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    void uploadLead() {

    }
}
