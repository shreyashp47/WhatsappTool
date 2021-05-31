package com.trinket.travelparo.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.trinket.travelparo.R;
import com.trinket.travelparo.models.taxibookingmodel.Booking;

import java.util.List;


public class CompletedTaxiBookingAdapter extends RecyclerView.Adapter<CompletedTaxiBookingAdapter.ViewHolder> {

    List<Booking> bookingList;
    OnLastLocationReachedListener lastLocationReachedListener;
    OnDetailClickListener onDetailClickListener;

    public CompletedTaxiBookingAdapter(Fragment fragment, List<Booking> bookingList) {
        this.lastLocationReachedListener=(OnLastLocationReachedListener)fragment;
        this.onDetailClickListener=(OnDetailClickListener)fragment;
        this.bookingList = bookingList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new  ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.status_listview_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.id.setText(bookingList.get(position).getReferenceNo());
        holder.employeeName.setText(bookingList.get(position).getPeople().get(0).getPeopleName());
        holder.tourType.setText(bookingList.get(position).getTourType());
        holder.pickupLocation.setText(bookingList.get(position).getPickupLocation());
        holder.pickupTime.setText(bookingList.get(position).getPickupDatetime());
        holder.companyStatus.setText(bookingList.get(position).getStatusCompany());
        holder.taxivaxiStatus.setText(bookingList.get(position).getStatusTv());
        holder.companyStatus.setTextColor(Color.parseColor(bookingList.get(position).getStatusCompanyColor()));
        holder.taxivaxiStatus.setTextColor(Color.parseColor(bookingList.get(position).getStatusTaxivaxiColor()));
        if (position>=bookingList.size()-1){
            lastLocationReachedListener.onLastLocationReached(position);
        }
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView mTextView;
        TextView id;
        TextView pickupLocation;
        TextView pickupTime;
        TextView tourType;
        TextView employeeName;
        TextView companyStatus;
        TextView taxivaxiStatus;
        Button details;
        public ViewHolder(View v) {
            super(v);
            id = (TextView) v.findViewById(R.id.booking_id);
            pickupLocation = (TextView) v.findViewById(R.id.pick_up_location);
            pickupTime = (TextView) v.findViewById(R.id.pic_up_time);
            tourType =(TextView)v.findViewById(R.id.tour_type);
            employeeName =(TextView)v.findViewById(R.id.spoc_name);
            companyStatus =(TextView)v.findViewById(R.id.company_status);
            taxivaxiStatus =(TextView)v.findViewById(R.id.taxivaxi_status);
            details=(Button)v.findViewById(R.id.btn_detail);
            details.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int pos=getAdapterPosition();
            switch (view.getId()){
                case R.id.btn_detail: onDetailClickListener.onDetailClicked(bookingList.get(pos).getId());
                    break;
            }

        }
    }

    public interface OnLastLocationReachedListener {
        void onLastLocationReached(int position);
    }

    public interface OnDetailClickListener{
        void onDetailClicked(String bookingId);
    }

}
