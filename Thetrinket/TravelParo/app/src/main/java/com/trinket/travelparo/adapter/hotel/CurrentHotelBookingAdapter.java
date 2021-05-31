package com.taxivaxi.spoc.adapter.hotel;

import android.graphics.Color;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
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

public class CurrentHotelBookingAdapter extends RecyclerView.Adapter<CurrentHotelBookingAdapter.ViewHolder> {

    RecyclerviewEventListener recyclerviewEventListener;
    List<Booking> bookingList;

    public CurrentHotelBookingAdapter(Fragment fragment, List<Booking> bookingList) {
        this.recyclerviewEventListener = (RecyclerviewEventListener)fragment;
        this.bookingList = bookingList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CurrentHotelBookingAdapter.ViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.status_listview_layout_hotel, parent, false)));

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
            recyclerviewEventListener.onLastLocationReached(position);
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
        TextView city;
        TextView checkInDate;
        TextView tourType;
        TextView employeeName;
        TextView companyStatus;
        TextView taxivaxiStatus;
        Button details;
        Button sendTrack;
        Button call;
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
            sendTrack=v.findViewById(R.id.btn_sendtrack);
            call=v.findViewById(R.id.btn_call);
            details.setOnClickListener(this);
            call.setOnClickListener(this);
            call.setVisibility(View.VISIBLE);
        }

        @Override
        public void onClick(View view) {
            int pos=getAdapterPosition();
            switch (view.getId()){
                case R.id.btn_detail: recyclerviewEventListener.onDetailsClicked(bookingList.get(pos).getId());
                    break;
                case R.id.btn_call:
                    String phoneno=bookingList.get(pos).getTaxivaxiContact();
                    if (phoneno!=null && phoneno.length()>0){
                        if (phoneno.startsWith("+91") || phoneno.startsWith("0")){
                            Log.d("clicled phone no",phoneno);
                            recyclerviewEventListener.onCallClicked(phoneno);

                        }
                        else {
                            Log.d("clicled phone no","+91"+phoneno);
                            recyclerviewEventListener.onCallClicked("+91"+phoneno);
                        }
                    }else
                    {
                        Snackbar.make(view,"No Not Available",Snackbar.LENGTH_LONG).show();
                    }
                    break;
            }

        }
    }

    public interface RecyclerviewEventListener {
        void onLastLocationReached(int position);
        void onDetailsClicked(String  bookingId);
        void onCallClicked(String mobileNo);
    }

}
