package com.trinket.travelparo.adapter.hotel;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.taxivaxi.spoc.R;
import com.taxivaxi.spoc.model.hotelbookingmodel.Booking;

import java.util.List;

/**
 * Created by sandeep on 3/10/17.
 */

public class UpcomingHotelBookingAdapter extends RecyclerView.Adapter<UpcomingHotelBookingAdapter.ViewHolder> {

    final static String MESSAGE="The Booking cannot be cancelled online as it is accepted by taxivaxi.To cancel  the booking please contact us or send a mail to corporate@taxivaxi.com";

    OnLastLocationReachedListener onLastLocationReachedListener;
    List<Booking> bookingList;
    OnDetailsClickedListener onDetailsClickedListener;
    OnRejectClickedListener onRejectClickedListener;

    public UpcomingHotelBookingAdapter(Fragment fragment, List<Booking> bookingList){
        this.onLastLocationReachedListener=(OnLastLocationReachedListener)fragment;
        this.onDetailsClickedListener=(OnDetailsClickedListener)fragment;
        this.onRejectClickedListener=(OnRejectClickedListener)fragment;
        this.bookingList=bookingList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.active_hotel_adpter, parent, false)));

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

        if (bookingList.get(position).getIsCancelAllowed().equals("1")){
            holder.cancelLayout.setVisibility(View.VISIBLE);
            holder.supCancelLayout.setVisibility(View.GONE);
        }
        else {
            holder.cancelLayout.setVisibility(View.GONE);
            holder.supCancelLayout.setVisibility(View.VISIBLE);
        }

        if (position>=bookingList.size()-1){
            onLastLocationReachedListener.onLastLocationReached(position);
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
        LinearLayout cancelLayout;
        LinearLayout supCancelLayout;

        Button details;
        Button cancel;
        Button supReject;
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
            cancel=v.findViewById(R.id.btn_reject);
            cancelLayout=(LinearLayout)v.findViewById(R.id.lay_cancle);
            supCancelLayout=v.findViewById(R.id.lay_supressed_reject);
            supReject=(Button)v.findViewById(R.id.btn_supressed_reject);
            details.setOnClickListener(this);
            cancel.setOnClickListener(this);
            supReject.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int pos=getAdapterPosition();
            switch (view.getId()){
                case R.id.btn_detail: onDetailsClickedListener.onDetailsClicked(bookingList.get(pos).getId());
                    break;
                case R.id.btn_reject: onRejectClickedListener.onRejectClicked(bookingList.get(pos).getId());
                    break;
                case R.id.btn_supressed_reject:
                    Snackbar.make(view,MESSAGE, Snackbar.LENGTH_LONG).show();
            }

        }
    }

    public interface OnLastLocationReachedListener {
        void onLastLocationReached(int position);
    }

    public interface OnDetailsClickedListener{
        void onDetailsClicked(String bookingId);
    }

    public interface OnRejectClickedListener{
        void onRejectClicked(String bookingId);
    }

}
