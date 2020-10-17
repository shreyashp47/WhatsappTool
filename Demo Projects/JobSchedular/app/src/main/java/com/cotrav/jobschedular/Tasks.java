package com.cotrav.jobschedular;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class Tasks extends BroadcastReceiver {

    final public static String ONE_TIME = "onetime";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(ONE_TIME, "Executed Tasks.Java File"+ Calendar.getInstance().getTime());
        //Some task here for every morning
        Toast.makeText(context, "Start Displaying Pictures", Toast.LENGTH_LONG).show();



    }
}