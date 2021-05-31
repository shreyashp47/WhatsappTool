package com.taxivaxi.approver.fragments.hotelfragments;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taxivaxi.approver.R;
import com.taxivaxi.approver.models.BookingDetailsmodel.Person;
import com.taxivaxi.approver.models.hotelbookingmodel.HotelBooking;
import com.taxivaxi.approver.utility.GsonStringConvertor;
import com.taxivaxi.approver.viewmodel.ApproverInfoViewModel;
import com.taxivaxi.approver.viewmodel.HotelBookingViewModel;


import java.util.List;


public class HotelBookingsDetails extends Fragment {
    private TextView bookingId;
    private TextView bookingDateTime;
    private TextView approverDateTime;
    private TextView hotelLocation;
    private TextView checkInDateTime;
    private TextView checkOutDateTime;
    private TextView roomNights;
    private TextView bookingStatus;
    private TextView assessmentCode;
    private TextView reasonForBooking;

    private TextView spocName;
    private TextView spocEmail;
    private TextView spocNumber;

    private TextView hotelName;
    private TextView roomNumber;
    private TextView voucherNumber;
    private TextView roomType;
    private TextView hotelContact;
    private TextView hotelAddress;
    private HotelBooking hotelBooking;

    HotelBookingViewModel bookingsViewModel;
    LinearLayout passengers_lay;
    LinearLayout passengers_lay2;
    LinearLayout passengers_lay3;
    private TextView empName;
    private TextView empEmail;
    private TextView empNumber;
    private ApproverInfoViewModel approverInfoViewModel;
    List<Person> person;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.hotel_bookings_details, container, false);
        bookingId = view.findViewById(R.id.booking_id);
        bookingDateTime = view.findViewById(R.id.booking_date);
        approverDateTime = view.findViewById(R.id.approval_date);
        hotelLocation = view.findViewById(R.id.hotel_location);
        checkInDateTime = view.findViewById(R.id.check_in);
        checkOutDateTime = view.findViewById(R.id.check_out);
        roomNights = view.findViewById(R.id.room_nights);
        bookingStatus = view.findViewById(R.id.booking_status);
        assessmentCode = view.findViewById(R.id.assessment_code);
        reasonForBooking = view.findViewById(R.id.booking_reason);

        spocName = view.findViewById(R.id.spoc_name);
        spocEmail = view.findViewById(R.id.spoc_Email);
        spocNumber = view.findViewById(R.id.spoc_contact);

        hotelName = view.findViewById(R.id.hotel_name);
        roomNumber = view.findViewById(R.id.room_no);
        voucherNumber = view.findViewById(R.id.voucher_no);
        roomType = view.findViewById(R.id.room_type);
        hotelContact = view.findViewById(R.id.hotel_contact);
        hotelAddress = view.findViewById(R.id.hotel_address);


        empName = view.findViewById(R.id.emp_name);
        empEmail = view.findViewById(R.id.emp_Email);
        empNumber = view.findViewById(R.id.emp_contact);
        bookingsViewModel = ViewModelProviders.of(this).get(HotelBookingViewModel.class);
        approverInfoViewModel= ViewModelProviders.of(getActivity()).get(ApproverInfoViewModel.class);
        passengers_lay = view.findViewById(R.id.Passengers_Details);
        passengers_lay2 = view.findViewById(R.id.passenger2_lay);
        passengers_lay3 = view.findViewById(R.id.passenger3_lay);
        if (!getArguments().getString("details","n").equals("n")){
            hotelBooking= GsonStringConvertor.stringToGson(getArguments().getString("details","n"),HotelBooking.class);
        }
        bookingsViewModel.Deatils(approverInfoViewModel.getAccessToken().getValue(),hotelBooking.id);
        bookingsViewModel.Deatils(approverInfoViewModel.getAccessToken().getValue()
                ,hotelBooking.id).observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable List<Person> people) {
                Log.d("TAG",people.toString());
                person=people;
                setData();
            }
        });
        //Sample Data
        bookingId.setText(hotelBooking.getReferenceNo());
        bookingDateTime.setText(hotelBooking.getBookingDate());
        approverDateTime.setText(hotelBooking.getAuthAcceptTime());
        hotelLocation.setText(hotelBooking.getCity());
        checkInDateTime.setText(hotelBooking.getArrivalDatetime());
        checkOutDateTime.setText(hotelBooking.getDepDatetime());
        roomNights.setText(hotelBooking.getDays());
        bookingStatus.setText(hotelBooking.getStatus());
        assessmentCode.setText(hotelBooking.getAssCode());
        reasonForBooking.setText(hotelBooking.getReasonBooking());

        spocName.setText(hotelBooking.getUserName());
        spocEmail.setText(hotelBooking.userEmail);
        spocNumber.setText(hotelBooking.getUserContact());

        hotelName.setText(hotelBooking.getAssignedHotel());
        roomNumber.setText(hotelBooking.getRoomNo());
        voucherNumber.setText(hotelBooking.getVoucherNumber());
        roomType.setText(hotelBooking.getRoomType());
        hotelContact.setText(hotelBooking.getHotelContact());
        hotelAddress.setText(hotelBooking.getAssignedHotelAddress());
        return view;
    }

    void setData(){
        passengers_lay.setVisibility(View.VISIBLE);
        empName.setText(person.get(0).getPeopleName().toString());
        empNumber.setText(person.get(0).getPeopleContact());
        empEmail.setText(person.get(0).getPeopleEmail());
       /* if (person.size()>1){
            passengers_lay2.setVisibility(View.VISIBLE);
        }
        if (person.size()>2){
            passengers_lay3.setVisibility(View.VISIBLE);
        }*/
    }
}
