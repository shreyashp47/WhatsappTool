package com.cotrav.printyou.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.cotrav.printyou.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class HomeActivity extends AppCompatActivity {

    TextView item, item2, item3, item4;
    ImageView img, img2, img3, img4;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_home);
        item = findViewById(R.id.item);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        img = findViewById(R.id.img);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);

        // Picasso.get().load("https://pngimg.com/uploads/tshirt/tshirt_PNG5449.png").into(img);
        Picasso.get().load("https://icon2.cleanpng.com/20180404/tiq/kisspng-magic-m" +
                "ug-personalization-printing-coffee-cup-coffee-mug-5ac483f4301df6.8166453115228282761971.jpg").into(img2);
        //Picasso.get().load("https://www.pngonly.com/wp-content/uploads/2017/06/Pen-PNG-Image.png").into(img3);
        //  Picasso.get().load("https://5.imimg.com/data5/KN/IJ/ZO/SELLER-10213454/wooden-photo-frame-500x500.jpg").into(img4);

        img.setImageDrawable(getDrawable(R.drawable.tshirt));
        // img2.setImageDrawable(getDrawable(R.drawable.whitecup));
        img3.setImageDrawable(getDrawable(R.drawable.pen));
        img4.setImageDrawable(getDrawable(R.drawable.frame));

        intent = new Intent(HomeActivity.this, MainActivity.class);

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap _bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap(); // your bitmap
                ByteArrayOutputStream _bs = new ByteArrayOutputStream();
                _bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
                intent.putExtra("image", _bs.toByteArray());
                intent.putExtra("ratio", "3:5");
                intent.putExtra("type", "tshirt");
                intent.putExtra("height", "800");
                intent.putExtra("width", "400");
                startActivity(intent);
            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap _bitmap = ((BitmapDrawable) img2.getDrawable()).getBitmap(); // your bitmap
                ByteArrayOutputStream _bs = new ByteArrayOutputStream();
                _bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
                intent.putExtra("image", _bs.toByteArray());
                intent.putExtra("ratio", "6:9");
                intent.putExtra("type", "cup");
                intent.putExtra("height", "100");
                intent.putExtra("width", "300");
                startActivity(intent);
            }
        });
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap _bitmap = ((BitmapDrawable) img3.getDrawable()).getBitmap(); // your bitmap
                ByteArrayOutputStream _bs = new ByteArrayOutputStream();
                _bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
                intent.putExtra("image", _bs.toByteArray());
                intent.putExtra("ratio", "10:2");
                intent.putExtra("type", "pen");

                intent.putExtra("height", "100");
                intent.putExtra("width", "300");
                startActivity(intent);
            }
        });
        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap _bitmap = ((BitmapDrawable) img4.getDrawable()).getBitmap(); // your bitmap
                ByteArrayOutputStream _bs = new ByteArrayOutputStream();
                _bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
                intent.putExtra("image", _bs.toByteArray());
                intent.putExtra("ratio", "4:3");
                intent.putExtra("type", "frame");
                intent.putExtra("height", "500");
                intent.putExtra("width", "700");
                startActivity(intent);
            }
        });
    }

    byte[] imageToBitmap(ImageView view) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), view.getId());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }
}