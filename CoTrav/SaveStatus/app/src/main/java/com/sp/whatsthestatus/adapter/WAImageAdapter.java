package com.sp.whatsthestatus.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.sp.whatsthestatus.ImageViewHolder;
import com.sp.whatsthestatus.R;
import com.sp.whatsthestatus.WAImageModel;

import java.util.ArrayList;

public class WAImageAdapter extends
        RecyclerView.Adapter<ImageViewHolder> {
    private ArrayList<WAImageModel> arrayList;
    private Context context;
    private SparseBooleanArray mSelectedItemsIds;

    public WAImageAdapter(Context context,
                          ArrayList<WAImageModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        mSelectedItemsIds = new SparseBooleanArray();

    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);

    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder,
                                 int position) {

        //Setting text over text view
        RequestOptions centerCrop = new RequestOptions().override(holder.imageView.getWidth(), holder.imageView.getHeight()).centerCrop();
        Glide.with(context).asBitmap().apply(centerCrop).load((String) arrayList.get(position).getPath()).transition(BitmapTransitionOptions.withCrossFade()).into(holder.imageView);

        if (mSelectedItemsIds.get(position))
            holder.imageViewCheck.setVisibility(View.VISIBLE);
        else
            holder.imageViewCheck.setVisibility(View.GONE);


    }

    @Override
    public ImageViewHolder onCreateViewHolder(
            ViewGroup viewGroup, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.wa_image_list_item, viewGroup, false);
        return new ImageViewHolder(mainGroup);

    }


    /***
     * Methods required for do selections, remove selections, etc.
     */

    //Toggle selection methods
    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position),arrayList.get(position).getPath());
    }


    //Remove selected selections
    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }


    //Put or delete selected position into SparseBooleanArray
    public void selectView(int position, boolean value,String path) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();
    }

    //Get total selected count
    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    //Return all selected ids
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

    public WAImageModel getItem(int i) {
        return (WAImageModel) arrayList.get(i);
    }

    public void updateData(ArrayList<WAImageModel> viewModels) {
        arrayList.clear();
        arrayList.addAll(viewModels);
        notifyDataSetChanged();
    }

}