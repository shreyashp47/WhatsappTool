package com.taxivaxi.approver.adapters;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.taxivaxi.approver.R;
import com.taxivaxi.approver.activities.TaxiBookingsActivity;
import com.taxivaxi.approver.fragments.taxifragments.TaxiBookingDetails;
import com.taxivaxi.approver.models.bookingmodel.TaxiBooking;
import com.taxivaxi.approver.utility.GsonStringConvertor;

import java.util.List;


public class TaxiBookingsCompletedRejectedAdapter extends RecyclerView.Adapter<TaxiBookingsCompletedRejectedAdapter.MyViewHolder> {
    private FragmentActivity fragmentActivity;
    List<TaxiBooking> taxiBookingList;
OnLastLocationReachedListener lastLocationReachedListener;
    public TaxiBookingsCompletedRejectedAdapter(FragmentActivity fragmentActivity, List<TaxiBooking> taxiBookingList, Fragment fragment) {
        this.fragmentActivity = fragmentActivity;
        this.taxiBookingList=taxiBookingList;
        this.lastLocationReachedListener = (OnLastLocationReachedListener)fragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.taxi_bookings_completed_rejected_fragment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TaxiBooking taxiBooking=new TaxiBooking();
        taxiBooking=taxiBookingList.get(position);
        holder.bookingId.setText(taxiBooking.referenceNo);
        holder.spocName.setText(taxiBooking.userName);
        holder.tourType.setText(taxiBooking.tourType);
        holder.pickUpLocation.setText(taxiBooking.pickupLocation);
        holder.pickUpTime.setText(taxiBooking.pickupDatetime);
        holder.dropOffLocation.setText(taxiBooking.dropLocation);
        holder.clientApprovalStatus.setText(taxiBooking.statusCompany);
       holder.clientApprovalStatus.setTextColor(Color.parseColor(taxiBooking.statusCompanyColor));
        holder.taxiVaxiSatus.setText(taxiBooking.statusTaxivaxi);
        Log.d("colors",taxiBooking.statusTaxivaxiColor);
        if (!taxiBooking.statusTaxivaxiColor.equals("orange")) {
            holder.taxiVaxiSatus.setTextColor(Color.parseColor(taxiBooking.statusTaxivaxiColor));
        };

        holder.taxiVaxiSatus.setTextColor(Color.parseColor(taxiBooking.statusTaxivaxiColor));
        if (position>=taxiBookingList.size()-1){
            lastLocationReachedListener.onLastLocationReached(position);
        }
    }

    @Override
    public int getItemCount() {
        return taxiBookingList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView bookingId;
        private TextView spocName;
        private TextView tourType;
        private TextView pickUpLocation;
        private TextView pickUpTime;
        private TextView dropOffLocation;
        private TextView clientApprovalStatus;
        private TextView taxiVaxiSatus;
        private Button details;
        public MyViewHolder(View itemView) {
            super(itemView);
            bookingId = itemView.findViewById(R.id.booking_id);
            spocName = itemView.findViewById(R.id.spoc_name);
            tourType = itemView.findViewById(R.id.tour_type);
            pickUpLocation = itemView.findViewById(R.id.pick_up_location);
            pickUpTime = itemView.findViewById(R.id.pic_up_time);
            dropOffLocation = itemView.findViewById(R.id.drop_location);
            clientApprovalStatus = itemView.findViewById(R.id.client_company_approval_status); // Same as company status in reject booking
            taxiVaxiSatus = itemView.findViewById(R.id.taxivaxi_status);
            details = itemView.findViewById(R.id.details_button);
            details.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            switch (view.getId()) {
                case R.id.details_button:
                    TaxiBookingsActivity.count++;
                    TaxiBookingDetails taxiBookingDetails = new TaxiBookingDetails();
                    Bundle bundle=new Bundle();
                    bundle.putString("details", GsonStringConvertor.gsonToString(taxiBookingList.get(position)));
                    taxiBookingDetails.setArguments(bundle);
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
