package com.cotrav.videorecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.IOException;

public class PortraitRecordActivity extends AppCompatActivity {
    private Camera myCamera;
    private MyCameraSurfaceView myCameraSurfaceView;
    private MediaRecorder mediaRecorder;
    public static int orientation;
    Button myButton;
    SurfaceHolder surfaceHolder;
    boolean recording;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement_video_stack);
        Log.i(null, "Video starting");
        recording = false;

        setContentView(R.layout.activity_enregistrement_video_stack);

        //Get Camera for preview
        myCamera = getCameraInstance();

        //myCamera.setDisplayOrientation(90); //Doesn't error here, but doesn't affect video.

        if (myCamera == null) {
            Toast.makeText(this,
                    "Fail to get Camera",
                    Toast.LENGTH_LONG).show();
        }

        myCameraSurfaceView = new MyCameraSurfaceView(this, myCamera, this);
        FrameLayout myCameraPreview = (FrameLayout) findViewById(R.id.surface_camera);
        myCameraPreview.addView(myCameraSurfaceView);

        myButton = (Button) findViewById(R.id.buttonstart);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                try {
                    if (recording) {
                        // stop recording and release camera
                        mediaRecorder.stop();  // stop the recording
                        releaseMediaRecorder(); // release the MediaRecorder object

                        //Exit after saved
                        //finish();
                        myButton.setText("REC");
                        recording = false;
                    } else {

                        //Release Camera before MediaRecorder start
                        releaseCamera();

                        if (!prepareMediaRecorder()) {
                            Toast.makeText(PortraitRecordActivity.this,
                                    "Fail in prepareMediaRecorder()!\n - Ended -",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }
                        mediaRecorder.start();
                        recording = true;
                        myButton.setText("STOP");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private Camera getCameraInstance(){
        // TODO Auto-generated method stub
        Camera c = null;
        try {
            c = Camera.open(0); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }


    private boolean prepareMediaRecorder(){
        myCamera = getCameraInstance();

        // set the orientation here to enable portrait recording.
        setCameraDisplayOrientation(this,0,myCamera);

        mediaRecorder = new MediaRecorder();

        myCamera.unlock();

        mediaRecorder.setCamera(myCamera);

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));

        mediaRecorder.setOutputFile("/sdcard/myvideo1.mp4");
        mediaRecorder.setMaxDuration(60000); // Set max duration 60 sec.
        mediaRecorder.setMaxFileSize(50000000); // Set max file size 50Mb

        mediaRecorder.setPreviewDisplay(myCameraSurfaceView.getHolder().getSurface());
        mediaRecorder.setOrientationHint(PortraitRecordActivity.orientation);
        try {
            mediaRecorder.prepare();
        } catch (IllegalStateException e) {
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            releaseMediaRecorder();
            return false;
        }
        return true;

    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaRecorder();       // if you are using MediaRecorder, release it first
        releaseCamera();              // release the camera immediately on pause event
    }

    private void releaseMediaRecorder(){
        if (mediaRecorder != null) {
            mediaRecorder.reset();   // clear recorder configuration
            mediaRecorder.release(); // release the recorder object
            mediaRecorder = new MediaRecorder();
            myCamera.lock();           // lock camera for later use
        }
    }

    private void releaseCamera(){
        if (myCamera != null){
            myCamera.release();        // release the camera for other applications
            myCamera = null;
        }
    }

    public class MyCameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

        private SurfaceHolder mHolder;
        private Camera mCamera;
        private Activity mActivity;

        public MyCameraSurfaceView(Context context, Camera camera, Activity activity) {
            super(context);
            mCamera = camera;
            mActivity=activity;
            // Install a SurfaceHolder.Callback so we get notified when the
            // underlying surface is created and destroyed.
            mHolder = getHolder();
            mHolder.addCallback(this);
            // deprecated setting, but required on Android versions prior to 3.0
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
        {
            try {
                setCameraDisplayOrientation(mActivity,0,mCamera);
                previewCamera();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void previewCamera()
        {
            try
            {
                mCamera.setPreviewDisplay(mHolder);
                mCamera.startPreview();
            }
            catch(Exception e)
            {
                //Log.d(APP_CLASS, "Cannot start preview", e);
            }
        }


        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // TODO Auto-generated method stub
            // The Surface has been created, now tell the camera where to draw the preview.
            try {
                mCamera.setPreviewDisplay(holder);
                mCamera.startPreview();
            } catch (IOException e) {
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // TODO Auto-generated method stub

        }


    }
    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, android.hardware.Camera camera) {

        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();

        android.hardware.Camera.getCameraInfo(cameraId, info);

        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;

        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        PortraitRecordActivity.orientation=result;
        camera.setDisplayOrientation(result);
    }
}