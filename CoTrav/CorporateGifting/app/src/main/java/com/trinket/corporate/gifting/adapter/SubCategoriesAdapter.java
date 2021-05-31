package com.trinket.corporate.gifting.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.trinket.corporate.gifting.R;
import com.trinket.corporate.gifting.activities.ProductActivity;
import com.trinket.corporate.gifting.model.home.Child;

import java.util.List;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.ViewHolder> {
    List<Child> childList;
    Activity activity;


    public SubCategoriesAdapter(List<Child> childList, Activity activity) {
        this.childList = childList;
        this.activity = activity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcatageory_item, parent, false);
        return new SubCategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Child list = childList.get(position);
        holder.subTitle.setText(list.getCategoriesName());


    }

    @Override
    public int getItemCount() {
        return childList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView subTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            subTitle = itemView.findViewById(R.id.subtitle);
            subTitle.setOnClickListener(this::onClick);

        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            switch (view.getId()) {

                case R.id.subtitle:
                    Intent intent =new Intent(activity, ProductActivity.class);
                    intent.putExtra("category_id",childList.get(pos).getCategoriesId().toString());
                    activity.startActivity(intent);
                    break;


            }

        }
    }


}
