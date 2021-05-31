package com.taxivaxi.approver.adapters;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.taxivaxi.approver.R;
import com.taxivaxi.approver.activities.TaxiBookingsActivity;
import com.taxivaxi.approver.fragments.taxifragments.TaxiBookingDetails;
import com.taxivaxi.approver.models.bookingmodel.TaxiBooking;
import com.taxivaxi.approver.utility.GsonStringConvertor;

import java.util.ArrayList;

/**
 * Created by shanu on 7/26/17.
 */

public class TaxiBookingApprovedAdapter extends RecyclerView.Adapter<TaxiBookingApprovedAdapter.MyViewHolder> {
    private FragmentActivity fragmentActivity;
    private ArrayList<TaxiBooking> taxiBookingArrayList;
    OnLastLocationReachedListener lastLocationReachedListener;

    public TaxiBookingApprovedAdapter(FragmentActivity fragmentActivity, ArrayList<TaxiBooking> taxiBookingArrayList, Fragment fragment) {
        this.fragmentActivity = fragmentActivity;
        this.taxiBookingArrayList = taxiBookingArrayList;
        this.lastLocationReachedListener = (OnLastLocationReachedListener) fragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.taxi_bookings_approved_fragment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TaxiBooking taxiBooking = taxiBookingArrayList.get(position);
        holder.bookingId.setText(taxiBooking.referenceNo);
        holder.spocName.setText(taxiBooking.userName);
        holder.tourType.setText(taxiBooking.tourType);
        holder.pickUpLocation.setText(taxiBooking.pickupLocation);
        holder.pickUpTime.setText(taxiBooking.pickupDatetime);
        holder.dropOffLocation.setText(taxiBooking.dropLocation);


        if (position >= taxiBookingArrayList.size() - 1) {
            lastLocationReachedListener.onLastLocationReached(position);
        }
    }

    @Override
    public int getItemCount() {
        return taxiBookingArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView bookingId;
        private TextView spocName;
        private TextView tourType;
        private TextView pickUpLocation;
        private TextView pickUpTime;
        private TextView dropOffLocation;
        private Button details;

        private TextView taxiVaxiSatus;

        public MyViewHolder(View itemView) {
            super(itemView);
            bookingId = itemView.findViewById(R.id.booking_id);
            spocName = itemView.findViewById(R.id.spoc_name);
            tourType = itemView.findViewById(R.id.tour_type);
            pickUpLocation = itemView.findViewById(R.id.pick_up_location);
            pickUpTime = itemView.findViewById(R.id.pic_up_time);
            dropOffLocation = itemView.findViewById(R.id.drop_location);
            details = itemView.findViewById(R.id.details_button);
            details.setOnClickListener(this);
             taxiVaxiSatus = itemView.findViewById(R.id.taxivaxi_status);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();

            switch (view.getId()) {
                case R.id.details_button:
                    TaxiBookingsActivity.count++;
                    TaxiBookingDetails taxiBookingDetails = new TaxiBookingDetails();
                    Bundle args = new Bundle();
                    args.putString("details", GsonStringConvertor.gsonToString(taxiBookingArrayList.get(position)));
                    taxiBookingDetails.setArguments(args);
                    fragmentActivity.getSupportFragmentManager().beginTransaction()
                            .add(R.id.bookings_fragment_inner, taxiBookingDetails)
                            .addToBackStack(null).commit();
                    break;
                default:
                    break;
            }
        }
    }

    public interface OnLastLocationReachedListener {
        void onLastLocationReached(int position);
    }
}
