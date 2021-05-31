package com.taxivaxi.spoc.adapter.hotel;

import android.graphics.Color;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.taxivaxi.spoc.R;
import com.taxivaxi.spoc.model.hotelbookingmodel.Booking;

import java.util.List;

/**
 * Created by sandeep on 3/10/17.
 */

public class CancelledHotelBookingAdapter extends RecyclerView.Adapter<CancelledHotelBookingAdapter.ViewHolder>{

    OnLastLocationReachedListener onLastLocationReachedListener;
    OnDetailsClickedListener onDetailsClickedListener;
    List<Booking> bookingList;

    public CancelledHotelBookingAdapter(Fragment fragment, List<Booking> bookingList) {
        this.onLastLocationReachedListener = (OnLastLocationReachedListener)fragment;
        this.onDetailsClickedListener=(OnDetailsClickedListener) fragment;
        this.bookingList = bookingList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CancelledHotelBookingAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.status_listview_layout_hotel,parent,false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.id.setText(bookingList.get(position).getReferenceNo());
        holder.employeeName.setText(bookingList.get(position).getPeople().get(0).getPeopleName());
        holder.tourType.setText("Hotel");
        holder.city.setText(bookingList.get(position).getCity());
        holder.checkInDate.setText(bookingList.get(position).getArrivalDatetime());
        holder.companyStatus.setText(bookingList.get(position).getStatusCompany());
        holder.taxivaxiStatus.setText(bookingList.get(position).getStatusTv());
        holder.companyStatus.setTextColor(Color.parseColor(bookingList.get(position).getStatusCompanyColor()));
        holder.taxivaxiStatus.setTextColor(Color.parseColor(bookingList.get(position).getStatusTaxivaxiColor()));
        if (position>=bookingList.size()-1){
            onLastLocationReachedListener.onLastLocationReached(position);
        }

    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        // each data item is just a string in this case
        public TextView mTextView;
        TextView id;
        TextView city;
        TextView checkInDate;
        TextView tourType;
        TextView employeeName;
        TextView companyStatus;
        TextView taxivaxiStatus;
        Button details;
        public ViewHolder(View v) {
            super(v);
            id = (TextView) v.findViewById(R.id.booking_id);
            city = (TextView) v.findViewById(R.id.pick_up_location);
            checkInDate = (TextView) v.findViewById(R.id.pic_up_time);
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
                case R.id.btn_detail:
                    onDetailsClickedListener.onDetailsClicked(bookingList.get(pos).getId());
                    break;
            }

        }
    }

    public interface OnLastLocationReachedListener {
        void onLastLocationReached(int position);
    }

    public interface OnDetailsClickedListener{
        void onDetailsClicked(String bookingId);
    }
}
