package com.taxivaxi.approver.adapters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.taxivaxi.approver.R;
import com.taxivaxi.approver.activities.HotelBookingsActivity;
import com.taxivaxi.approver.fragments.hotelfragments.HotelBookingsDetails;
import com.taxivaxi.approver.models.hotelbookingmodel.HotelBooking;
import com.taxivaxi.approver.utility.GsonStringConvertor;

import java.util.List;


public class HotelBookingsApprovedAdapter extends RecyclerView.Adapter<HotelBookingsApprovedAdapter.MyViewHolder> {
    private FragmentActivity fragmentActivity;
    List<HotelBooking> approvedHotelBooking;
OnLastLocationReachedListener lastLocationReachedListener;
    public HotelBookingsApprovedAdapter(FragmentActivity fragmentActivity, List<HotelBooking> approvedHotelBooking, Fragment fragment) {
        this.fragmentActivity = fragmentActivity;
        this.approvedHotelBooking=approvedHotelBooking;
        this.lastLocationReachedListener = (OnLastLocationReachedListener) fragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.hotel_bookings_approved_fragment, parent, false);
        return new MyViewHolder(view);
    }
    public interface OnLastLocationReachedListener {
        void onLastLocationReached(int position);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HotelBooking hotelBooking=approvedHotelBooking.get(position);
        holder.bookingId.setText(hotelBooking.getReferenceNo());
        holder.spocName.setText(hotelBooking.userName);
        holder.hotelLocation.setText(hotelBooking.city);
        holder.checkInDateTime.setText(hotelBooking.arrivalDatetime);
        holder.checkOutDateTime.setText(hotelBooking.depDatetime);
        if (position >= approvedHotelBooking.size() - 1) {
            lastLocationReachedListener.onLastLocationReached(position);
        }
    }

    @Override
    public int getItemCount() {
        return approvedHotelBooking.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView bookingId;
        private TextView spocName;
        private TextView hotelLocation;
        private TextView checkInDateTime;
        private TextView checkOutDateTime;
        private Button details;
        public MyViewHolder(View itemView) {
            super(itemView);
            bookingId = itemView.findViewById(R.id.booking_id);
            spocName = itemView.findViewById(R.id.spoc_name);
            hotelLocation = itemView.findViewById(R.id.hotel_location);
            checkInDateTime = itemView.findViewById(R.id.check_in);
            checkOutDateTime = itemView.findViewById(R.id.hotel_check_out_text_view_holder);
            details = itemView.findViewById(R.id.details_button);
            details.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();

            switch (view.getId()) {
                case R.id.details_button:
                    HotelBookingsActivity.count++;
                    HotelBookingsDetails hotelBookingsDetails = new HotelBookingsDetails();
                    Bundle args=new Bundle();
                    args.putString("details", GsonStringConvertor.gsonToString(approvedHotelBooking.get(position)));
                    hotelBookingsDetails.setArguments(args);
                    fragmentActivity.getSupportFragmentManager().beginTransaction()
                            .add(R.id.bookings_fragment_inner, hotelBookingsDetails)
                            .addToBackStack(null).commit();
                    break;
                default:
                    break;
            }
        }
    }

}
