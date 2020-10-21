package com.cotrav.testalarm.broadcast;

import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


import com.cotrav.testalarm.Alarm;

import java.util.Calendar;

import static android.content.Context.JOB_SCHEDULER_SERVICE;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String SUNDAY = "SUNDAY";
    public static final String RECURRING = "RECURRING";
    public static final String TITLE = "TITLE";
    NotificationManager mNotifyManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            String toastText = String.format("Alarm Reboot");
            //   Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
            startRescheduleAlarmsService(context);
        } else {
            String toastText = String.format("Alarm Received");
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
            if (!intent.getBooleanExtra(RECURRING, false)) {
                //      startAlarmService(context, intent);
            }
            if (alarmIsToday(intent)) {
                //    startAlarmService(context, intent);
            }
        }
    }

    private boolean alarmIsToday(Intent intent) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int today = calendar.get(Calendar.DAY_OF_WEEK);

        switch (today) {
            case Calendar.MONDAY:
                if (intent.getBooleanExtra(MONDAY, false))
                    return true;
                return false;
            case Calendar.TUESDAY:
                if (intent.getBooleanExtra(TUESDAY, false))
                    return true;
                return false;
            case Calendar.WEDNESDAY:
                if (intent.getBooleanExtra(WEDNESDAY, false))
                    return true;
                return false;
            case Calendar.THURSDAY:
                if (intent.getBooleanExtra(THURSDAY, false))
                    return true;
                return false;
            case Calendar.FRIDAY:
                if (intent.getBooleanExtra(FRIDAY, false))
                    return true;
                return false;
            case Calendar.SATURDAY:
                if (intent.getBooleanExtra(SATURDAY, false))
                    return true;
                return false;
            case Calendar.SUNDAY:
                if (intent.getBooleanExtra(SUNDAY, false))
                    return true;
                return false;
        }
        return false;
    }

    private JobScheduler mScheduler;

    private void startAlarmService(Context context, Intent intent) {
        mScheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);

    /*    int selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;


        ComponentName serviceName = new ComponentName(context.getPackageName(),
                NotificationJobService.class.getName());

        JobInfo.Builder builder = new JobInfo.Builder(10, serviceName)
                .setRequiredNetworkType(selectedNetworkOption)
                .setPersisted(true);


        JobInfo myJobInfo = builder.build();
        mScheduler.schedule(myJobInfo);*/
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
