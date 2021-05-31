package com.cotrav.printyou.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.cotrav.printyou.R;

public class PreviewActivity extends AppCompatActivity {
    ImageView edt_img,img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        edt_img=findViewById(R.id.edt_img);
        img=findViewById(R.id.image);

        ConstraintSet set = new ConstraintSet();
        ConstraintLayout constraintLayout=findViewById(R.id.frame);
        set.clone(constraintLayout);
        set.setDimensionRatio(edt_img.getId(), getIntent().getExtras().getString("ratio"));
        set.applyTo(constraintLayout);




        byte[] designbyteArray = getIntent().getByteArrayExtra("design");
       // byte[] imgbyteArray = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(designbyteArray, 0, designbyteArray.length);
    //   Bitmap imagebmp = BitmapFactory.decodeByteArray(imgbyteArray, 0, imgbyteArray.length);
        edt_img.setImageBitmap(bmp);
//      img.setImageBitmap(imagebmp);


        if(getIntent().hasExtra("image")) {
            ImageView _imv= new ImageView(this);
            Bitmap _bitmap = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("image"),0,
                    getIntent().getByteArrayExtra("image").length);
            img.setImageBitmap(_bitmap);
        }
    }
}