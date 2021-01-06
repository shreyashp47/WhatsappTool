package com.taxivaxi.operator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.taxivaxi.operator.model.archivedbooking.Booking;
import com.taxivaxi.operator.model.archivedbooking.Passanger;

import java.util.ArrayList;
import java.util.List;

public class PendingDutySlipAdapter extends RecyclerView.Adapter<PendingDutySlipAdapter.ViewHolder> implements Filterable {
    List<Booking> bookingList;
    List<Booking> bookingListFiltered;
    RecyclerViewEventListener recyclerViewEventListener;

    public PendingDutySlipAdapter(RecyclerViewEventListener recyclerViewEventListener, List<Booking> bookingList) {
        this.bookingList = bookingList;
        this.recyclerViewEventListener = recyclerViewEventListener;
        this.bookingListFiltered = bookingList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_dutyslip_list, parent, false);
        return new PendingDutySlipAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Booking booking = bookingListFiltered.get(position);
        holder.date.setText(booking.getPickupDatetime());
        holder.bookingId.setText(booking.getReferenceNo());
        holder.pickupLocation.setText(booking.getPickupLocation());
        holder.noOfPassenger.setText(booking.getPassangers().size() + "");
        holder.dropLocation.setText(booking.getDropLocation());
        holder.tripType.setText(booking.getTourType() + " " + booking. getPackageName());
        List<Passanger> passengers = booking.getPassangers();
        switch (booking.getPassangers().size()) {
            case 1:
                holder.passengerName1.setText(passengers.get(0).getPeopleName());
                holder.passengerContact1.setText(passengers.get(0).getPeopleContact());
                break;
            case 2:
                holder.passengerName1.setText(passengers.get(0).getPeopleName());
                holder.passengerContact1.setText(passengers.get(0).getPeopleContact());
                holder.passengerName2.setText(passengers.get(1).getPeopleName());
                holder.passengerContact2.setText(passengers.get(1).getPeopleContact());
                break;
            case 3:
                holder.passengerName1.setText(passengers.get(0).getPeopleName());
                holder.passengerContact1.setText(passengers.get(0).getPeopleContact());
                holder.passengerName2.setText(passengers.get(1).getPeopleName());
                holder.passengerContact2.setText(passengers.get(1).getPeopleContact());
                holder.passengerName3.setText(passengers.get(2).getPeopleName());
                holder.passengerContact3.setText(passengers.get(2).getPeopleContact());
                break;
            case 4:
                holder.passengerName1.setText(passengers.get(0).getPeopleName());
                holder.passengerContact1.setText(passengers.get(0).getPeopleContact());
                holder.passengerName2.setText(passengers.get(1).getPeopleName());
                holder.passengerContact2.setText(passengers.get(1).getPeopleContact());
                holder.passengerName3.setText(passengers.get(2).getPeopleName());
                holder.passengerContact3.setText(passengers.get(2).getPeopleContact());
                holder.passengerName4.setText(passengers.get(3).getPeopleName());
                holder.passengerContact4.setText(passengers.get(3).getPeopleContact());
                break;
            case 5:
                holder.passengerName1.setText(passengers.get(0).getPeopleName());
                holder.passengerContact1.setText(passengers.get(0).getPeopleContact());
                holder.passengerName2.setText(passengers.get(1).getPeopleName());
                holder.passengerContact2.setText(passengers.get(1).getPeopleContact());
                holder.passengerName3.setText(passengers.get(2).getPeopleName());
                holder.passengerContact3.setText(passengers.get(2).getPeopleContact());
                holder.passengerName4.setText(passengers.get(3).getPeopleName());
                holder.passengerContact4.setText(passengers.get(3).getPeopleContact());
                holder.passengerName5.setText(passengers.get(4).getPeopleName());
                holder.passengerContact5.setText(passengers.get(4).getPeopleContact());
                break;
            case 6:
                holder.passengerName1.setText(passengers.get(0).getPeopleName());
                holder.passengerContact1.setText(passengers.get(0).getPeopleContact());
                holder.passengerName2.setText(passengers.get(1).getPeopleName());
                holder.passengerContact2.setText(passengers.get(1).getPeopleContact());
                holder.passengerName3.setText(passengers.get(2).getPeopleName());
                holder.passengerContact3.setText(passengers.get(2).getPeopleContact());
                holder.passengerName4.setText(passengers.get(3).getPeopleName());
                holder.passengerContact4.setText(passengers.get(3).getPeopleContact());
                holder.passengerName5.setText(passengers.get(4).getPeopleName());
                holder.passengerContact5.setText(passengers.get(4).getPeopleContact());
                holder.passengerName6.setText(passengers.get(5).getPeopleName());
                holder.passengerContact6.setText(passengers.get(5).getPeopleContact());
                break;

            default:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return bookingListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    bookingListFiltered = bookingList;
                } else {
                    List<Booking> filteredList = new ArrayList<>();
                    for (Booking row : bookingList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getReferenceNo().toLowerCase().contains(charString.toLowerCase()) || row.getReferenceNo().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    bookingListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = bookingListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                bookingListFiltered = (ArrayList<Booking>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView date;
        TextView time;
        TextView bookingId;
        TextView pickupLocation;
        TextView dropLocation;
        TextView noOfPassenger;
        TextView tripType;

        TextView passengerDetails;
        TextView passengerName1;
        TextView passengerContact1;
        TextView passengerName2;
        TextView passengerContact2;
        TextView passengerName3;
        TextView passengerContact3;
        TextView passengerName4;
        TextView passengerContact4;
        TextView passengerName5;
        TextView passengerContact5;
        TextView passengerName6;
        TextView passengerContact6;
        LinearLayout passengerLay1;
        LinearLayout passengerLay2;
        LinearLayout passengerLay3;
        LinearLayout passengerLay4;
        LinearLayout passengerLay5;
        LinearLayout passengerLay6;
        boolean isExpended = false;

        FloatingActionButton floatingActionButton;

        public ViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.travel_date);
            bookingId = itemView.findViewById(R.id.booking_id);
            pickupLocation = itemView.findViewById(R.id.pickup_location);
            dropLocation = itemView.findViewById(R.id.drop_location);
            noOfPassenger = itemView.findViewById(R.id.no_of_passenger);
            tripType = itemView.findViewById(R.id.trip_type);

            floatingActionButton = itemView.findViewById(R.id.floating_button);
            passengerDetails = itemView.findViewById(R.id.passenger_details);
            passengerName1 = itemView.findViewById(R.id.passenger_name_1);
            passengerContact1 = itemView.findViewById(R.id.passenger_contact_1);
            passengerName2 = itemView.findViewById(R.id.passenger_name_2);
            passengerContact2 = itemView.findViewById(R.id.passenger_contact_2);
            passengerName3 = itemView.findViewById(R.id.passenger_name_3);
            passengerContact3 = itemView.findViewById(R.id.passenger_contact_3);
            passengerName4 = itemView.findViewById(R.id.passenger_name_4);
            passengerContact4 = itemView.findViewById(R.id.passenger_contact_4);
            passengerName5 = itemView.findViewById(R.id.passenger_name_5);
            passengerContact5 = itemView.findViewById(R.id.passenger_contact_5);
            passengerName6 = itemView.findViewById(R.id.passenger_name_6);
            passengerContact6 = itemView.findViewById(R.id.passenger_contact_6);
            passengerLay1 = itemView.findViewById(R.id.passenger_lay_1);
            passengerLay2 = itemView.findViewById(R.id.passenger_lay_2);
            passengerLay3 = itemView.findViewById(R.id.passenger_lay_3);
            passengerLay4 = itemView.findViewById(R.id.passenger_lay_4);
            passengerLay5 = itemView.findViewById(R.id.passenger_lay_5);
            passengerLay6 = itemView.findViewById(R.id.passenger_lay_6);

            passengerLay1.setVisibility(View.GONE);
            passengerLay2.setVisibility(View.GONE);
            passengerLay3.setVisibility(View.GONE);
            passengerLay4.setVisibility(View.GONE);
            passengerLay5.setVisibility(View.GONE);
            passengerLay6.setVisibility(View.GONE);


            passengerDetails.setOnClickListener(this);
            floatingActionButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            switch (view.getId()) {
                case R.id.passenger_details:
                    if (!isExpended) {
                        isExpended = true;
                        Booking booking = bookingListFiltered.get(pos);
                        switch (booking.getPassangers().size()) {
                            case 1:
                                passengerLay1.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                passengerLay1.setVisibility(View.VISIBLE);
                                passengerLay2.setVisibility(View.VISIBLE);
                                break;
                            case 3:
                                passengerLay1.setVisibility(View.VISIBLE);
                                passengerLay2.setVisibility(View.VISIBLE);
                                passengerLay3.setVisibility(View.VISIBLE);
                                break;
                            case 4:
                                passengerLay1.setVisibility(View.VISIBLE);
                                passengerLay2.setVisibility(View.VISIBLE);
                                passengerLay3.setVisibility(View.VISIBLE);
                                passengerLay4.setVisibility(View.VISIBLE);
                                break;
                            case 5:
                                passengerLay1.setVisibility(View.VISIBLE);
                                passengerLay2.setVisibility(View.VISIBLE);
                                passengerLay3.setVisibility(View.VISIBLE);
                                passengerLay4.setVisibility(View.VISIBLE);
                                passengerLay5.setVisibility(View.VISIBLE);
                                break;
                            case 6:
                                passengerLay1.setVisibility(View.VISIBLE);
                                passengerLay2.setVisibility(View.VISIBLE);
                                passengerLay3.setVisibility(View.VISIBLE);
                                passengerLay4.setVisibility(View.VISIBLE);
                                passengerLay5.setVisibility(View.VISIBLE);
                                passengerLay6.setVisibility(View.VISIBLE);
                                break;
                            default:
                                break;
                        }
                        passengerDetails.setText("Collapse");
                        passengerDetails.setCompoundDrawablesWithIntrinsicBounds(null, view.getContext().getResources().getDrawable(R.drawable.ic_keyboard_arrow_up_black_24px), null, null);
                    } else {
                        isExpended = false;
                        passengerDetails.setText("Passenger Details");
                        passengerDetails.setCompoundDrawablesWithIntrinsicBounds(null, null, null, view.getContext().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24px));
                        passengerLay1.setVisibility(View.GONE);
                        passengerLay2.setVisibility(View.GONE);
                        passengerLay3.setVisibility(View.GONE);
                        passengerLay4.setVisibility(View.GONE);
                        passengerLay5.setVisibility(View.GONE);
                        passengerLay6.setVisibility(View.GONE);
                    }
                    break;
                case R.id.floating_button:
                    recyclerViewEventListener.onDetailsClicked(bookingListFiltered.get(pos));
                     break;
            }

        }
    }

    public interface RecyclerViewEventListener {
        void onDetailsClicked(Booking booking);
    }


}
