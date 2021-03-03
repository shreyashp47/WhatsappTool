package com.sp.whatsthestatus;

import android.annotation.SuppressLint;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;


import java.io.File;

public class ImageViewer extends AppCompatActivity {
    HelperMethods helperMethods;

    FloatingActionMenu floatingMenu;
    int position = 0;
    File f;

    class SomeClass implements View.OnClickListener {
        private final ImageViewer imageViewer;
        private final File file;

        class SomeOtherClass implements Runnable {
            private final SomeClass context;
            private final File file;

            SomeOtherClass(SomeClass someClass, File file) {
                context = someClass;
                this.file = file;
            }

            @Override
            public void run() {
                try {
                    HelperMethods.transfer(this.file);
                    Toast.makeText(getApplicationContext(), "Image Saved to Gallery :)", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("GridView", new StringBuffer().append("onClick: Error: ").append(e.getMessage()).toString());
                }
            }
        }

        SomeClass(ImageViewer imageViewer, File file) {
            this.imageViewer = imageViewer;
            this.file = file;

        }

        @Override
        public void onClick(View view) {
            new SomeOtherClass(this, this.file).run();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        helperMethods = new HelperMethods(this);
        // setSupportActionBar( findViewById(R.id.toolbar));
//        getSupportActionBar().setIcon(R.drawable.business_notif);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().hide();
        final Intent intent = getIntent();
        String string = intent.getExtras().getString("pos");
        position = intent.getExtras().getInt("position");


        f = new File(string);
        PhotoView photoView = (PhotoView) findViewById(R.id.photo);
        floatingMenu = findViewById(R.id.menu);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.save);
        FloatingActionButton floatingActionButton2 = (FloatingActionButton) findViewById(R.id.wall);
        FloatingActionButton floatingActionButton3 = (FloatingActionButton) findViewById(R.id.rep);
        FloatingActionButton floatingActionButton4 = (FloatingActionButton) findViewById(R.id.dlt);
        FloatingActionButton floatingActionButton5 = (FloatingActionButton) findViewById(R.id.other);
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("deleteFab", true)) {
            floatingActionButton4.setVisibility(View.VISIBLE);
        } else {
            floatingActionButton4.setVisibility(View.GONE);
        }
        floatingActionButton.setOnClickListener(downloadMediaItem(f));
        Glide.with(this).load(this.f).into(photoView);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                Intent intent;
                Uri uriForFile;
                if (Build.VERSION.SDK_INT >= 24) {
                    uriForFile = FileProvider.getUriForFile(getApplicationContext(), new StringBuffer().append(getApplicationContext().getPackageName()).append(".provider").toString(), f);
                    intent = new Intent(Intent.ACTION_ATTACH_DATA);
                    intent.setDataAndType(uriForFile, "image/*");
                    intent.putExtra("mimeType", "image/*");
                    intent.addFlags(1);
                    startActivity(Intent.createChooser(intent, "Set as: "));
                    return;
                }
                uriForFile = Uri.parse(new StringBuffer().append("file://").append(f.getAbsolutePath()).toString());
                intent = new Intent(Intent.ACTION_ATTACH_DATA);
                intent.setDataAndType(uriForFile, "image/*");
                intent.putExtra("mimeType", "image/*");
                intent.addFlags(Intent.EXTRA_DOCK_STATE_DESK);
                startActivity(Intent.createChooser(intent, "Set as: "));
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                Intent intent;
                Parcelable uriForFile;
                if (Build.VERSION.SDK_INT >= 24) {
                    uriForFile = FileProvider.getUriForFile(getApplicationContext(), new StringBuffer().append(getPackageName()).append(".provider").toString(), f);
                    try {
                        intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("image/*");
                        intent.setPackage("com.whatsapp");
                        intent.putExtra("android.intent.extra.STREAM", uriForFile);
                        intent.addFlags(Intent.EXTRA_DOCK_STATE_DESK);
                        startActivity(intent);

                        return;
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "WhatsApp Not Found on this Phone :(", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                uriForFile = Uri.parse(new StringBuffer().append("file://").append(f.getAbsolutePath()).toString());
                try {
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("image/*");
                    intent.setPackage("com.whatsapp");
                    intent.putExtra("android.intent.extra.STREAM", uriForFile);
                    startActivity(intent);
                } catch (ActivityNotFoundException e2) {
                    Toast.makeText(getApplicationContext(), "WhatsApp Not Found on this Phone :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ImageViewer.this);
                builder.setMessage("Sure to Delete this Image?").
                        setNegativeButton("Nope", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        digStash();
                        Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                //builder.create().show();
                AlertDialog alert = builder.create();
                alert.show();

                Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                nbutton.setTextColor(getColor(R.color.colorPrimary));
                Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                pbutton.setTextColor(getColor(R.color.colorPrimary));
                //digStash();
            }
        });
        floatingActionButton5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                Intent intent;
                Parcelable uriForFile;
                if (Build.VERSION.SDK_INT >= 24) {
                    uriForFile = FileProvider.getUriForFile(getApplicationContext(), new StringBuffer().append(getPackageName()).append(".provider").toString(), f);
                    try {
                        intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("image/*");
                       // intent.setPackage("com.whatsapp");
                        intent.putExtra(Intent.EXTRA_STREAM, uriForFile);
                        intent.addFlags(Intent.EXTRA_DOCK_STATE_DESK);
                        startActivity(Intent.createChooser(intent, "share"));

                        return;
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "WhatsApp Not Found on this Phone :(", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                uriForFile = Uri.parse(new StringBuffer().append("file://").append(f.getAbsolutePath()).toString());
                try {
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("image/*");
                    //intent.setPackage("com.whatsapp");
                    intent.putExtra(Intent.EXTRA_STREAM, uriForFile);
                    startActivity(intent);
                } catch (ActivityNotFoundException e2) {
                    Toast.makeText(getApplicationContext(), "WhatsApp Not Found on this Phone :(", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void digStash() {
        if (this.f.exists()) {
            this.f.delete();
            Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent();
        intent.putExtra("pos", this.position);
        setResult(-1, intent);
        finish();
    }

    public View.OnClickListener downloadMediaItem(File file) {
        return new SomeClass(this, file);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }

}
