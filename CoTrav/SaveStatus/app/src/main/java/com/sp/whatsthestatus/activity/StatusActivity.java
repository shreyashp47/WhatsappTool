package com.sp.whatsthestatus.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sp.whatsthestatus.R;
import com.sp.whatsthestatus.Rulers;
import com.sp.whatsthestatus.fragment.WAFragment;

import java.io.File;

public class StatusActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setTitle("WhatsApp Tool");
        setSupportActionBar(toolbar);

        ///check whatsapp
        if (checkInstallation("com.whatsapp")) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkPerm();
                }
            }, 2000);
        }
        // Show a dialog if meets conditions


      /*  try {
            if (!isMyServiceRunning(Class.forName("com.whatsappstatus.saver.service.NotificationService"))) {
                try {
                    startService(new Intent(this, Class.forName("com.whatsappstatus.saver.service.NotificationService")));
                } catch (Throwable e) {
                    throw new NoClassDefFoundError(e.getMessage());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/


//        setSupportActionBar(toolbar);
        stash();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            navigationView.getMenu().getItem(0).setChecked(true);
            Fragment fragment = new WAFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.framelayout, fragment).commit();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_whatsapp) {
            Fragment fragment = new WAFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.framelayout, fragment).commit();
        }
        if (id==R.id.share){

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", "Share WApp Status Saver App");
            intent.putExtra("android.intent.extra.TEXT", "Try this Awesome App 'WhatsApp Tool' which helps you in Saving all the WhatsApp Statuses ..! \n"+ Rulers.link);
            startActivity(Intent.createChooser(intent, "Share Via"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void stash() {
        File file = new File(new StringBuffer().append(new StringBuffer().append(Environment.getExternalStorageDirectory()).append(File.separator).toString()).append("WhatsApp/Media/.Statuses").toString());
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        file = new File(new StringBuffer().append(new StringBuffer().append(Environment.getExternalStorageDirectory()).append(File.separator).toString()).append("WhatsApp Business/Media/.Statuses").toString());
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        file = new File(new StringBuffer().append(new StringBuffer().append(Environment.getExternalStorageDirectory()).append(File.separator).toString()).append("GBWhatsApp/Media/.Statuses").toString());
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        new File(new StringBuffer().append(new StringBuffer().append(Environment.getExternalStorageDirectory()).append(File.separator).toString()).append("StorySaver/").toString()).mkdirs();
    }

    private boolean checkInstallation(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    private boolean isMyServiceRunning(Class<?> cls) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) getSystemService(ACTIVITY_SERVICE)).getRunningServices(Integer.MAX_VALUE)) {
            if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
           //         Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
            //        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                  //  checkPerm();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    public void checkPerm() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
             //   Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
        } else {
           // Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }
}