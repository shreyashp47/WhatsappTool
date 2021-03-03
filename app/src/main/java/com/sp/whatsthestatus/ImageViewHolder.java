package com.sp.whatsthestatus;


import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;


public class ImageViewHolder extends RecyclerView.ViewHolder {


    public ImageView imageView,imageViewCheck;


    public ImageViewHolder(View view) {
        super(view);


        this.imageView = (ImageView) view.findViewById(R.id.imageView_wa_image);
        this.imageViewCheck = (ImageView) view.findViewById(R.id.imageView_wa_checked);

    }
}