package com.taxivaxi.approver.adapters;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.taxivaxi.approver.R;
import com.taxivaxi.approver.activities.HotelBookingsActivity;
import com.taxivaxi.approver.fragments.hotelfragments.HotelBookingsDetails;
import com.taxivaxi.approver.models.hotelbookingmodel.HotelBooking;
import com.taxivaxi.approver.utility.GsonStringConvertor;

import java.util.List;

public class HotelBookingNewAdapter extends RecyclerView.Adapter<HotelBookingNewAdapter.MyViewHolder> {
    private FragmentActivity fragmentActivity;
    private List<HotelBooking> hotelBookingList;
    private AcceptRejectHotelBookingListener acceptRejectHotelBookingListener;
    OnLastLocationReachedListener lastLocationReachedListener;

    public HotelBookingNewAdapter(FragmentActivity fragmentActivity, List<HotelBooking> hotelBookingList, AcceptRejectHotelBookingListener acceptRejectHotelBookingListener) {
        this.fragmentActivity = fragmentActivity;
        this.hotelBookingList = hotelBookingList;
        this.acceptRejectHotelBookingListener = acceptRejectHotelBookingListener;
        this.lastLocationReachedListener = (OnLastLocationReachedListener) acceptRejectHotelBookingListener;
    }

    public interface OnLastLocationReachedListener {
        void onLastLocationReached(int position);
    }

    public interface AcceptRejectHotelBookingListener {
        void approveBooking(String bookingId, String bookingStatus,int pos);

        void rejectBooking(String bookingId,int pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView bookingId;
        private TextView spocName;
        private TextView hotelLocation;
        private TextView checkInDateTime;
        private TextView checkOutDateTime;
        private TextView clientApprovalStatus;
        private TextView taxiVaxiSatus;
        private Button details;
        private Button approve;
        private Button reject;

        public MyViewHolder(View itemView) {
            super(itemView);
            bookingId = itemView.findViewById(R.id.booking_id);
            spocName = itemView.findViewById(R.id.spoc_name);
            hotelLocation = itemView.findViewById(R.id.hotel_location);
            checkInDateTime = itemView.findViewById(R.id.check_in);
            checkOutDateTime = itemView.findViewById(R.id.hotel_check_out_text_view_holder);
            clientApprovalStatus = itemView.findViewById(R.id.client_approval_status);
            taxiVaxiSatus = itemView.findViewById(R.id.taxivaxi_status);
            details = itemView.findViewById(R.id.details_button);
            approve = itemView.findViewById(R.id.approve_button);
            reject = itemView.findViewById(R.id.reject_button);
            details.setOnClickListener(this);
            approve.setOnClickListener(this);
            reject.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            switch (view.getId()) {
                case R.id.details_button:
                    HotelBookingsActivity.count++;
                    HotelBookingsDetails hotelBookingsDetails = new HotelBookingsDetails();
                    Bundle bundle = new Bundle();
                    bundle.putString("details", GsonStringConvertor.gsonToString(hotelBookingList.get(position)));
                    hotelBookingsDetails.setArguments(bundle);
                    fragmentActivity.getSupportFragmentManager().beginTransaction()
                            .add(R.id.bookings_fragment_inner, hotelBookingsDetails)
                            .addToBackStack(null).commit();
                    break;
                case R.id.approve_button:
                    acceptRejectHotelBookingListener.approveBooking(hotelBookingList.get(position).id, hotelBookingList.get(position).statusAuthTaxivaxi,position);
                    break;
                case R.id.reject_button:
                    acceptRejectHotelBookingListener.rejectBooking(hotelBookingList.get(position).id,position);
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_bookings_new_fragment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Sample Data
        HotelBooking hotelBooking = hotelBookingList.get(position);
        holder.bookingId.setText(hotelBooking.getReferenceNo());
        holder.spocName.setText(hotelBooking.userName);
        holder.hotelLocation.setText(hotelBooking.getCity());
        holder.checkInDateTime.setText(hotelBooking.arrivalDatetime);
        holder.checkOutDateTime.setText(hotelBooking.depDatetime);
        holder.clientApprovalStatus.setText(hotelBooking.statusCompany);
        holder.taxiVaxiSatus.setText(hotelBooking.statusTaxivaxi);

        holder.clientApprovalStatus.setTextColor(Color.parseColor(hotelBooking.statusCompanyColor));

        holder.taxiVaxiSatus.setTextColor(Color.parseColor(hotelBooking.statusTaxivaxiColor));
        if (position >= hotelBookingList.size() - 1) {
            lastLocationReachedListener.onLastLocationReached(position);
        }
    }

    @Override
    public int getItemCount() {
        return hotelBookingList.size();
    }

}
