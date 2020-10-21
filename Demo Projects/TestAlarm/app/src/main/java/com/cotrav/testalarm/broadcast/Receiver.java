package com.cotrav.testalarm.broadcast;

import android.app.ActivityManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.cotrav.testalarm.Alarm;
import com.cotrav.testalarm.data.CallRoomDatabase;
import com.cotrav.testalarm.data.OfflineDatabase;
import com.cotrav.testalarm.data.phoneno.PhoneNumbers;
import com.cotrav.testalarm.service.LifeTimeService;
import com.cotrav.testalarm.service.NotificationJobService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.JOB_SCHEDULER_SERVICE;


public class Receiver extends BroadcastReceiver {

    String phoneNumber = "";
    String number = "";
    SharedPreferences dataSP;
    CallRoomDatabase roomDatabase;
    OfflineDatabase offlineDatabase;
    List<PhoneNumbers> phoneNumbers;
    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String SUNDAY = "SUNDAY";
    public static final String RECURRING = "RECURRING";
    public static final String TITLE = "TITLE";

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.PHONE_STATE".equals(intent.getAction())) {

            dataSP = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            Bundle extras = intent.getExtras();

            if (extras != null && extras
                    .getString(TelephonyManager.EXTRA_INCOMING_NUMBER) != null) {

                roomDatabase = CallRoomDatabase.getDatabase(context);
                offlineDatabase = new OfflineDatabase();
                phoneNumbers = new ArrayList<>();
                phoneNumbers = roomDatabase.phoneNumbersDao().getAllPhoneNo();

                String lgono = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                if (lgono != null)
                    Log.d("MyPhoneReceiver", "====== Phone " + lgono + " Log ======");

                for (int i = 0; i < phoneNumbers.size(); i++) {
                    Log.d("MyPhoneReceiver", "." + lgono + "=" + phoneNumbers.get(i).getPhone());
                    if (lgono.equals(phoneNumbers.get(i).getPhone())
                            || lgono.equals("+91" + phoneNumbers.get(i).getPhone())
                            || lgono.equals("0" + phoneNumbers.get(i).getPhone())) {
                        String state = extras.getString(TelephonyManager.EXTRA_STATE);
                        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                            String phoneNumber = extras
                                    .getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                            if (phoneNumber != null) {

                                Log.d("MyPhoneReceiver", phoneNumber + " Calling You");
                           /* String out = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(new Date());
                            SharedPreferences.Editor editor = dataSP.edit();
                            editor.putString("phoneNo", phoneNumber);
                            editor.putString("status", "incoming");
                            editor.putString("date", out);
                            editor.commit();
                            offlineDatabase.setPhoneNo(phoneNumber);
                            offlineDatabase.setDate(out);
                            offlineDatabase.setIncoming("yes");
                            roomDatabase.daoRecord().insert(offlineDatabase);*/
                            }
                        }

                        if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                            String phoneNumber = extras
                                    .getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                            if (phoneNumber != null) {
                                String out = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(new Date());
                         /*   if (!dataSP.getString("date", "n").equals("n")) {
                                offlineDatabase = roomDatabase.daoRecord().getRecord(dataSP.getString("date", "n"));
                                offlineDatabase.setOutgoing("yes");
                                offlineDatabase.setCallState("in");
                            } else {
                                offlineDatabase.setDate(out);
                                offlineDatabase.setCallState("out");
                            }
                            Log.d("MyPhoneReceiver", "On call " + phoneNumber + "");
                            SharedPreferences.Editor editor = dataSP.edit();
                            if (dataSP.getString("date", "n").equals("n"))
                                editor.putString("date", out);
                            editor.putString("status", "oncall");
                            editor.commit();
                            offlineDatabase.setPhoneNo(phoneNumber);
                            offlineDatabase.setCallPickUpTime(out);
                            roomDatabase.daoRecord().insert(offlineDatabase);*/
                            }
                        }

                        if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                            String phoneNumber = extras
                                    .getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                            if (phoneNumber != null) {
                                String out = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(new Date());
                          /*  if (!dataSP.getString("date", "n").equals("n")) {
                                offlineDatabase = roomDatabase.daoRecord().getRecord(dataSP.getString("date", "n"));
                            }
                            offlineDatabase.setPhoneNo(phoneNumber);
                            offlineDatabase.setCallHangTime(out);
                            roomDatabase.daoRecord().insert(offlineDatabase);
                            Log.d("MyPhoneReceiver", phoneNumber + " Call End ");
                            SharedPreferences.Editor editor = dataSP.edit();
                            editor.putString("phoneNo", "n");
                            editor.putString("status", "n");
                            editor.putString("date", "n");
                            editor.commit();*/
                                Intent myService = new Intent(context, LifeTimeService.class);
                                if (isMyServiceRunning(context.getApplicationContext(), LifeTimeService.class)) {
                                    context.stopService(myService);
                                }
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    context.startForegroundService(myService);
                                } else {
                                    context.startService(myService);
                                }

                           /* Intent in = new Intent(context, RecordService.class);
                            if (isMyServiceRunning(context.getApplicationContext(), RecordService.class)) {
                                context.stopService(in);
                            }*/
                            }
                        }
                        break;
                    }
                }
            }
        } else if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            String toastText = String.format("Alarm Reboot");
            //   Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
            startRescheduleAlarmsService(context);
        } else {
            String toastText = String.format("Alarm Received");
            //    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
            startAlarmService(context, intent);

        }
    }

    private static boolean isMyServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    private JobScheduler mScheduler;

    private void startAlarmService(Context context, Intent intent) {
        mScheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);

        int selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;


        ComponentName serviceName = new ComponentName(context.getPackageName(),
                NotificationJobService.class.getName());

        JobInfo.Builder builder = new JobInfo.Builder(10, serviceName)
                .setRequiredNetworkType(selectedNetworkOption)
                .setPersisted(true);


        JobInfo myJobInfo = builder.build();
        mScheduler.schedule(myJobInfo);
        //   Toast.makeText(context, "job_scheduled", Toast.LENGTH_SHORT).show();

    }
    private void startRescheduleAlarmsService(Context context) {

        Alarm alarm = new Alarm(
                10,
                13,
                44,
                "Daily Check",
                System.currentTimeMillis(),
                true,
                false,
                true,
                true, true, true, true, true, true
        );
        alarm.schedule(context);
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
        alarm2.schedule(context);
    }
}
