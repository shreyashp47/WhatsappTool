package com.taxivaxi.thetrinketadmin.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.taxivaxi.thetrinketadmin.R;
import com.taxivaxi.thetrinketadmin.activities.ProductActivity;
import com.taxivaxi.thetrinketadmin.model.home.CategoriesApiResponse;
import com.taxivaxi.thetrinketadmin.model.home.Child;
import com.taxivaxi.thetrinketadmin.retrofit.ApiUrl;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    List<CategoriesApiResponse> categoryList;
    Activity activity;
    SubCategoriesAdapter subCategoriesAdapter;

    public CategoriesAdapter(List<CategoriesApiResponse> categoryList, Activity activity) {
        this.categoryList = categoryList;
        this.activity = activity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catageories_item, parent, false);
        return new CategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CategoriesApiResponse list = categoryList.get(position);
        holder.title.setText(list.getCategoriesName());
        Picasso.get().load(ApiUrl.url + list.getImgpath()).into(holder.baner);
        Picasso.get().load(ApiUrl.url + list.getImgpath()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                //  holder.mainView.setBackground(new BitmapDrawable(activity.getResources(), bitmap));
                Palette.from(bitmap).maximumColorCount(18).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        // Get the "vibrant" color swatch based on the bitmap
                        Palette.Swatch vibrant = palette.getVibrantSwatch();
                        if (vibrant != null) {
                            // Set the background color of a layout based on the vibrant color
                            holder.mainView.setBackgroundColor(vibrant.getRgb());
                            // Update the title TextView with the proper text color
                            holder.title.setTextColor(vibrant.getTitleTextColor());
                            holder.description.setTextColor(vibrant.getBodyTextColor());
                        }
                    }
                });

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });

        if (list.getChilds() != null && list.getChilds().size() > 0) {
            subCategoriesAdapter = new SubCategoriesAdapter(list.getChilds(), activity);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity,
                    LinearLayoutManager.VERTICAL, false);
            holder.submain.setLayoutManager(layoutManager);
            holder.submain.setAdapter(subCategoriesAdapter);

        } else {
            holder.submain.setOnClickListener(null);
        }

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ConstraintLayout mainView;
        ImageView baner;
        TextView title, description;
        RecyclerView submain;
        List<Child> childList;
        SubCategoriesAdapter subCategoriesAdapter;


        public ViewHolder(View itemView) {
            super(itemView);
            mainView = itemView.findViewById(R.id.main);
            submain = itemView.findViewById(R.id.submain);
            mainView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
            baner = itemView.findViewById(R.id.baner);
            description = itemView.findViewById(R.id.description);


        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            switch (view.getId()) {
                case R.id.main:

                    if (categoryList.get(pos).getChilds() != null) {
                        if (categoryList.get(pos).getChilds().size() > 0)
                            if (submain.getVisibility() == View.GONE) {
                                submain.setVisibility(View.VISIBLE);
                            } else {
                                submain.setVisibility(View.GONE);
                            }
                        } else {
                            Intent intent =new Intent(activity, ProductActivity.class);
                            intent.putExtra("category_id",categoryList.get(pos).getCategoriesId().toString());
                            activity.startActivity(intent);
                        }


                    break;

            }

        }
    }


}
