package com.cotrav.videorecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener {
    private static final String TAG = "RecordActivity";
    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    MediaRecorder recorder = new MediaRecorder();
    private Camera mCamera;
    Button recordingBtn;
    Boolean isRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        recordingBtn = (Button) findViewById(R.id.buttonstart);
        mCamera = Camera.open();
        surfaceView = (SurfaceView) findViewById(R.id.surface_camera);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        recordingBtn.setOnClickListener(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mCamera != null) {
            Camera.Parameters params = mCamera.getParameters();
            mCamera.setParameters(params);
        } else {
            Toast.makeText(getApplicationContext(), "Camera not available!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCamera.stopPreview();
        mCamera.release();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonstart:
                if (!isRecording) {
                    try {
                        startRecording();
                        isRecording = true;
                    } catch (Exception e) {
                        String message = e.getMessage();
                        Log.i(TAG, "Problem Start" + message);
                        recorder.release();
                    }
                } else {
                    recorder.stop();
                    recorder.release();
                    recorder = null;
                    isRecording = false;
                }
                break;
        }
    }

    protected void startRecording() throws IOException {
        recorder = new MediaRecorder();  // Works well
        mCamera.unlock();

        recorder.setCamera(mCamera);
        //recorder.setOrientationHint(90);

        recorder.setPreviewDisplay(surfaceHolder.getSurface());
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        recorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
        recorder.setPreviewDisplay(surfaceHolder.getSurface());

        ///file name in date
        String out = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(new Date());
        String fileName = out + "record.3gp";
        recorder.setOutputFile("/sdcard/" + fileName);

        recorder.prepare();
        recorder.start();
    }


}