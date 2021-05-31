package com.cotrav.printyou.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;


import com.cotrav.printyou.R;
import com.cotrav.printyou.TextEditorDialogFragment;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import ja.burhanrashid52.photoeditor.OnPhotoEditorListener;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;
import ja.burhanrashid52.photoeditor.SaveSettings;
import ja.burhanrashid52.photoeditor.TextStyleBuilder;
import ja.burhanrashid52.photoeditor.ViewType;

public class MainActivity extends BaseActivity implements OnPhotoEditorListener {

    private static final int REQUEST_IMAGE = 101;
    private String filename;
    Button preview;
    ImageView addtext;

    PhotoEditor mPhotoEditor;
    private PhotoEditorView mPhotoEditorView;
    String ratio = "1:1";
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView btn_add = findViewById(R.id.addimg);

        preview = findViewById(R.id.btn_preview);


        mPhotoEditorView = findViewById(R.id.photoEditorView);
        ViewGroup.LayoutParams params = mPhotoEditorView.getLayoutParams();
        ratio = getIntent().getExtras().getString("ratio");
       /* ConstraintLayout linearLayout = findViewById(R.id.liner_lay);
        ConstraintSet set = new ConstraintSet();
        ConstraintLayout constraintLayout = findViewById(R.id.frame);
        set.clone(constraintLayout);
        set.setDimensionRatio(linearLayout.getId(), ratio);
        set.applyTo(constraintLayout);*/
        //params.height= Integer.parseInt(getIntent().getStringExtra("height"));
        // params.width= Integer.parseInt(getIntent().getStringExtra("width"));


        mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
                .setPinchTextScalable(true) // set flag to make text scalable when pinch
                //.setDefaultTextTypeface(mTextRobotoTf)
                //.setDefaultEmojiTypeface(mEmojiTypeFace)
                .build(); // build photo editor sdk

        mPhotoEditor.setOnPhotoEditorListener(this);


        ///set image in view
        ImageView imageView = findViewById(R.id.image);
        imageView.getLayoutParams().height = 1000;
        imageView.getLayoutParams().width = 1000;

        mPhotoEditorView.getLayoutParams().height= Integer.parseInt(getIntent().getExtras().getString("height"));;
        mPhotoEditorView.getLayoutParams().width= Integer.parseInt(getIntent().getExtras().getString("width"));;

        byte[] designbyteArray = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(designbyteArray, 0, designbyteArray.length);
        // mPhotoEditorView.getSource().setImageBitmap(bmp);
        imageView.setImageBitmap(bmp);

        addtext = findViewById(R.id.addtext);
        addtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextEditorDialogFragment textEditorDialogFragment = TextEditorDialogFragment.show(MainActivity.this);
                textEditorDialogFragment.setOnTextEditorListener(new TextEditorDialogFragment.TextEditor() {
                    @Override
                    public void onDone(String inputText, int colorCode, Typeface typeface) {
                        final TextStyleBuilder styleBuilder = new TextStyleBuilder();
                        styleBuilder.withTextColor(colorCode);
                        styleBuilder.withTextFont(typeface);

                        mPhotoEditor.addText(inputText, styleBuilder);

                    }
                });

            }
        });


     /*   ImageView img = findViewById(R.id.img);
        byte[] imageByte = getIntent().getByteArrayExtra("image");
        Bitmap imagebmp = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
        img.setImageBitmap(imagebmp);*/
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {

                    }
                });*/
                saveAndNext();
/*
                mPhotoEditor.saveAsBitmap(new OnSaveBitmap() {
                    @Override
                    public void onBitmapReady(Bitmap saveBitmap) {


                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                //TODO your background code
                                saveAndNext();
                                bitmap=saveBitmap;
                              */
/*  ByteArrayOutputStream _bs = new ByteArrayOutputStream();
                                saveBitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
                                Intent in1 = new Intent(MainActivity.this, PreviewActivity.class);
                                in1.putExtra("design", _bs.toByteArray());

                                byte[] imageByte = getIntent().getByteArrayExtra("image");
                                in1.putExtra("image", imageByte);
                                in1.putExtra("ratio", ratio);

                                startActivity(in1);*//*


                            }
                        });


                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
*/
               /* ByteArrayOutputStream stream = new ByteArrayOutputStream();

                byte[] designByte = stream.toByteArray();
                FrameLayout frameLayout=findViewById(R.id.frame);
                Bitmap bitmap = Bitmap.createBitmap(frameLayout.getWidth(), frameLayout.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                frameLayout.draw(canvas);
                ByteArrayOutputStream _bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);


                byte[] imageByte = getIntent().getByteArrayExtra("image");
                Intent in1 = new Intent(MainActivity.this, PreviewActivity.class);
                in1.putExtra("design",  _bs.toByteArray());
                in1.putExtra("image", imageByte);*/

                //startActivity(in1);

            }
        });


    }

    @Nullable
    @VisibleForTesting
    Uri mSaveImageUri;

    void saveAndNext() {

        if (requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            showLoading("Saving...");
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + ""
                    + System.currentTimeMillis() + ".png");
            try {
                file.createNewFile();

                SaveSettings saveSettings = new SaveSettings.Builder()
                        .setClearViewsEnabled(true)
                        .setTransparencyEnabled(true)
                        .build();

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                mPhotoEditor.saveAsFile(file.getAbsolutePath(), saveSettings, new PhotoEditor.OnSaveListener() {
                    @Override
                    public void onSuccess(@NonNull String imagePath) {
                        hideLoading();
                        showSnackbar("Image Saved Successfully");
                        mSaveImageUri = Uri.fromFile(new File(imagePath));
                        mPhotoEditorView.getSource().setImageURI(mSaveImageUri);
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {

                                Bitmap _bitmap = ((BitmapDrawable) mPhotoEditorView.getSource().getDrawable()).getBitmap(); // your bitmap
                                ByteArrayOutputStream _bs = new ByteArrayOutputStream();
                                _bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);

                                Intent nextIntent = new Intent(MainActivity.this, PreviewActivity.class);
                                nextIntent.putExtra("design", _bs.toByteArray());

                                byte[] imageByte = getIntent().getByteArrayExtra("image");
                                nextIntent.putExtra("image", imageByte);
                                nextIntent.putExtra("ratio", ratio);

                                nextIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                nextIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(nextIntent);


                            }
                        });
                    }

                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        hideLoading();
                        showSnackbar("Failed to save Image");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                hideLoading();
                showSnackbar(e.getMessage());
            }
        }
    }

    @Override
    public void onEditTextChangeListener(final View rootView, String text, int colorCode) {
        TextEditorDialogFragment textEditorDialogFragment =
                TextEditorDialogFragment.show(this, text, colorCode);
        textEditorDialogFragment.setOnTextEditorListener(new TextEditorDialogFragment.TextEditor() {
            @Override
            public void onDone(String inputText, int colorCode, Typeface typeface) {
                final TextStyleBuilder styleBuilder = new TextStyleBuilder();
                styleBuilder.withTextColor(colorCode);
                styleBuilder.withTextFont(typeface);

                mPhotoEditor.editText(rootView,inputText, styleBuilder);

            }
        });

    }

    @Override
    public void onAddViewListener(ViewType viewType, int numberOfAddedViews) {

    }

    @Override
    public void onRemoveViewListener(ViewType viewType, int numberOfAddedViews) {

    }

    @Override
    public void onStartViewChangeListener(ViewType viewType) {

    }

    @Override
    public void onStopViewChangeListener(ViewType viewType) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE: {
                    if (data != null && data.getData() != null) {
                        startCrop(data.getData());
                    }
                    break;
                }
                case UCrop.REQUEST_CROP: {
                    createImageView();
                    break;
                }
            }
        }
    }

    private void createImageView() {
        File file = new File(getCacheDir(), filename);
        Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
        ImageView imageView = new ImageView(MainActivity.this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(250, 250);
        params.leftMargin = 0;
        params.topMargin = 0;
        imageView.setLayoutParams(params);
        imageView.setImageBitmap(bmp);

        mPhotoEditor.addImage(bmp);
        file.delete();
    }

    private void startCrop(Uri data) {
        String name = getFileName(data);
        File file = new File(getCacheDir(), name);
        filename = name;
        UCrop uCrop = UCrop.of(data, Uri.fromFile(file));
        uCrop.start(this);
    }


    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    public Bitmap viewToBitmap(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}