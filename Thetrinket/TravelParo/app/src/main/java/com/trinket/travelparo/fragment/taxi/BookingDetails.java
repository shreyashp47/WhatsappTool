package com.taxivaxi.spoc.BookingStatus;


import android.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.taxivaxi.spoc.R;
import com.taxivaxi.spoc.model.FeedbackModel;
import com.taxivaxi.spoc.model.busbookingdetailsmodels.BusBookingDetail;
import com.taxivaxi.spoc.model.busbookingdetailsmodels.ViewBusBookingApiResponse;
import com.taxivaxi.spoc.model.taxidetailsmodels.TaxiBookingDetail;
import com.taxivaxi.spoc.model.taxidetailsmodels.TaxiBookingDetailsApiResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import es.dmoral.toasty.Toasty;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingDetails extends androidx.fragment.app.Fragment implements View.OnClickListener {
    JSONObject bookings;
    LinearLayout emp_lay1,emp_lay2,emp_lay3,emp_lay4,emp_lay5,emp_lay6,driver_lay,texi_lay,ticket_lay,assesmenlay,hotel_ticket,lay_ticketno,
            lay_operator_name,lay_bus_type,lay_operator_contact,lay10,lay11,lay5,lay12,lay13,lay14,feedbackLay1,feedbackLay2,feedbackLay3,
            feedbackLay4,feedbackLay5,feedbackLay6,feedbackLay,downloadTicketLay;
    TextView booking_ref_no,tour_type,pickup_location,booking_date,pickup_date_time,ass_code,reason_for_booking,room_no,hotel_address,
            hotel_contact,voucher_no,hotel_type,hotel_name,room_type,room_type_1,check_in,check_out,prefred_hotel,occupancy,downloadTicket,
            feedback1,feedback2,feedback3,feedback4,feedback5,feedback6,feedbackDate1,feedbackDate2,feedbackDate3,feedbackDate4,feedbackDate5,feedbackDate6;
    TextView empname1,empname2,empname3,empname4,empname5,empname6,empemail1,empemail2,empemail3,empemail4,empemail5,empemail6,empphno1;
    TextView  emphno1,emphno2,emphno3,emphno4,emphno5,emphno6;
    TextView driver_name,driver_contact_no;
    TextView taxi_model_name,taxi_reg_no;
    TextView ticket_no,pnr_no,bus_type,operator_name,operator_contact,seat_no;
    SharedPreferences loginpref;
    Gson gson;

    public BookingDetails() {
        // Required empty public constructor
    }

    String code;
    String type;
    TaxiBookingDetail taxiBookingDetail;
    BusBookingDetail busBookingDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=null;
        type=getArguments().getString("type","n");
        code=getArguments().getString("code","1");
        gson=new Gson();
        Log.d("JSON",getArguments().getString("json", "n"));
        if (type.equals("cab")) {
            TaxiBookingDetailsApiResponse taxiBookingDetailsApiResponse = gson.fromJson(getArguments().getString("json", "n"), TaxiBookingDetailsApiResponse.class);
            taxiBookingDetail = taxiBookingDetailsApiResponse.getResponse().getBookingDetail();
        }
        else if (type.equals("bus")){
            ViewBusBookingApiResponse viewBusBookingApiResponse=gson.fromJson(getArguments().getString("json","n"),ViewBusBookingApiResponse.class);
            busBookingDetail=viewBusBookingApiResponse.getResponse().getBookingDetail();
            Log.d("GsonConverted",gson.toJson(busBookingDetail));
        }

        loginpref=getActivity().getSharedPreferences("login_info",MODE_PRIVATE);
        Log.d("TypeCode",type+" "+code);

        if(type.equals("cab")||type.equals("bus")) {
            view = inflater.inflate(R.layout.fragment_booking_details, container, false);
        }else if(type.equals("hotel")){
            view = inflater.inflate(R.layout.fragment_detail_hotel,container,false);

        }else if(type.equals("train")){
            view = inflater.inflate(R.layout.fragment_bookingdetail_train, container, false);

        }
        empname1=(TextView) view.findViewById(R.id.employee_name1);
        empname2=(TextView) view.findViewById(R.id.employee_name2);
        empname3=(TextView) view.findViewById(R.id.employee_name3);
        empname4=(TextView) view.findViewById(R.id.employee_name4);
        empname5=(TextView) view.findViewById(R.id.employee_name5);
        empname6=(TextView) view.findViewById(R.id.employee_name6);

        empemail1=(TextView) view.findViewById(R.id.employee_Email1);
        empemail2=(TextView) view.findViewById(R.id.employee_Email2);
        empemail3=(TextView) view.findViewById(R.id.emplopyee_Email3);
        empemail4=(TextView) view.findViewById(R.id.employee_Email4);
        empemail5=(TextView) view.findViewById(R.id.employee_Email5);
        empemail6=(TextView) view.findViewById(R.id.employee_Email6);

        emphno1=(TextView) view.findViewById(R.id.employee_contact1);
        emphno2=(TextView) view.findViewById(R.id.employee_contact2);
        emphno3=(TextView) view.findViewById(R.id.employee_contact3);
        emphno4=(TextView) view.findViewById(R.id.employee_contact4);
        emphno5=(TextView) view.findViewById(R.id.employee_contact5);
        emphno6=(TextView) view.findViewById(R.id.employee_contact6);

        ticket_no=(TextView) view.findViewById(R.id.ticket_no);
        pnr_no=(TextView) view.findViewById(R.id.pnr_no);
        bus_type=(TextView)view.findViewById(R.id.bus_type);
        operator_name=(TextView)view.findViewById(R.id.operator_name);
        operator_contact=(TextView)view.findViewById(R.id.operator_contact);
        seat_no=(TextView)view.findViewById(R.id.seat_no);

        driver_name=(TextView) view.findViewById(R.id.driver_name);
        driver_contact_no=(TextView)view.findViewById(R.id.driver_contact);

        taxi_model_name=(TextView) view.findViewById(R.id.taxi_model_name);
        taxi_reg_no=(TextView) view.findViewById(R.id.taxi_reg_no);

        emp_lay1=(LinearLayout)view.findViewById(R.id.employee_lay1);
        emp_lay2=(LinearLayout)view.findViewById(R.id.employee_lay2);
        emp_lay3=(LinearLayout)view.findViewById(R.id.employee_lay3);
        emp_lay4=(LinearLayout)view.findViewById(R.id.employee_lay4);
        emp_lay5=(LinearLayout)view.findViewById(R.id.employee_lay5);
        emp_lay6=(LinearLayout)view.findViewById(R.id.employee_lay6);

        feedbackLay1=(LinearLayout)view.findViewById(R.id.feedback1_lay);
        feedbackLay2=(LinearLayout)view.findViewById(R.id.feedback2_lay);
        feedbackLay3=(LinearLayout)view.findViewById(R.id.feedback3_lay);
        feedbackLay4=(LinearLayout)view.findViewById(R.id.feedback4_lay);
        feedbackLay5=(LinearLayout)view.findViewById(R.id.feedback5_lay);
        feedbackLay6=(LinearLayout)view.findViewById(R.id.feedback6_lay);
        downloadTicketLay=(LinearLayout) view.findViewById(R.id.download_ticket_lay);

        feedback1=(TextView)view.findViewById(R.id.feedback1);
        feedback2=(TextView)view.findViewById(R.id.feedback2);
        feedback3=(TextView)view.findViewById(R.id.feedback3);
        feedback4=(TextView)view.findViewById(R.id.feedback4);
        feedback5=(TextView)view.findViewById(R.id.feedback5);
        feedback6=(TextView)view.findViewById(R.id.feedback6);

        feedbackDate1=(TextView)view.findViewById(R.id.feedback1_date);
        feedbackDate2=(TextView)view.findViewById(R.id.feedback2_date);
        feedbackDate3=(TextView)view.findViewById(R.id.feedback3_date);
        feedbackDate4=(TextView)view.findViewById(R.id.feedback4_date);
        feedbackDate5=(TextView)view.findViewById(R.id.feedback5_date);
        feedbackDate6=(TextView)view.findViewById(R.id.feedback6_date);

        feedbackLay1.setVisibility(View.GONE);
        feedbackLay2.setVisibility(View.GONE);
        feedbackLay3.setVisibility(View.GONE);
        feedbackLay4.setVisibility(View.GONE);
        feedbackLay5.setVisibility(View.GONE);
        feedbackLay6.setVisibility(View.GONE);

        feedbackLay=(LinearLayout) view.findViewById(R.id.feedback_lay);

        lay10 = (LinearLayout) view.findViewById(R.id.lay10);
        lay11  = (LinearLayout) view.findViewById(R.id.lay11);
        lay5 = (LinearLayout) view.findViewById(R.id.lay6);
        room_type_1 = (TextView) view.findViewById(R.id.room_type_1);
        check_in = (TextView) view.findViewById(R.id.check_in);
        downloadTicket=(TextView) view.findViewById(R.id.download_ticket);


        lay_operator_name= (LinearLayout) view.findViewById(R.id.lay_operator_name) ;
        lay_bus_type= (LinearLayout) view.findViewById(R.id.lay_bus_type) ;
        lay_ticketno= (LinearLayout) view.findViewById(R.id.lay_ticketno) ;
        lay_operator_contact =(LinearLayout)view.findViewById(R.id.lay_operator_contact);
        driver_lay=(LinearLayout)view.findViewById(R.id.driver_Details);
        texi_lay=(LinearLayout)view.findViewById(R.id.taxi_Details);
        ticket_lay=(LinearLayout) view.findViewById(R.id.ticket_lay);
        hotel_ticket=(LinearLayout) view.findViewById(R.id.hotel_ticket_lay);
        assesmenlay=(LinearLayout) view.findViewById(R.id.lay8);

        hotel_address= (TextView) view.findViewById(R.id.hotel_address) ;
        hotel_contact = (TextView) view.findViewById(R.id.hotel_contact);
        hotel_name=(TextView) view.findViewById(R.id.Hotel_name);
        room_no = (TextView) view.findViewById(R.id.room_no);
        room_type= (TextView) view.findViewById(R.id.room_type);
        voucher_no= (TextView) view.findViewById(R.id.voucher_no);


        booking_ref_no=(TextView) view.findViewById(R.id.booking_id);
        tour_type=(TextView) view.findViewById(R.id.b_tourType);
        pickup_location=(TextView) view.findViewById(R.id.Pickup_Loaction);
        booking_date=(TextView) view.findViewById(R.id.booking_date);

        pickup_date_time=(TextView) view.findViewById(R.id.pick_up_date_time);
        ass_code=(TextView) view.findViewById(R.id.accessment_code);
        reason_for_booking=(TextView) view.findViewById(R.id.booking_reason);

        emphno1.setOnClickListener(this);
        emphno2.setOnClickListener(this);
        emphno3.setOnClickListener(this);
        emphno4.setOnClickListener(this);
        emphno5.setOnClickListener(this);
        emphno6.setOnClickListener(this);
        driver_contact_no.setOnClickListener(this);
        operator_contact.setOnClickListener(this);
        downloadTicket.setOnClickListener(this);


        String json=getArguments().getString("json","info");
        String no_of_passenger=getArguments().getString("no_of_passenger","n");


        if (loginpref.getString("has_assessment_codes","n").equals("0")){
            assesmenlay.setVisibility(View.GONE);
        }

        if (type.equals("cab")){
            downloadTicketLay.setVisibility(View.GONE);
            downloadTicket.setText("Click to dowmload duty slip");
        }
        else if (type.equals("bus")){
            downloadTicket.setText("Click to download bus ticket");
        }


        switch (no_of_passenger){
            case "1" : emp_lay2.setVisibility(View.GONE);
                        emp_lay3.setVisibility(View.GONE);
                        emp_lay4.setVisibility(View.GONE);
                        emp_lay5.setVisibility(View.GONE);
                        emp_lay6.setVisibility(View.GONE);
                        break;
            case "2" :  emp_lay3.setVisibility(View.GONE);
                        emp_lay4.setVisibility(View.GONE);
                        emp_lay5.setVisibility(View.GONE);
                        emp_lay6.setVisibility(View.GONE);
                        break;
            case "3" :  emp_lay4.setVisibility(View.GONE);
                        emp_lay5.setVisibility(View.GONE);
                        emp_lay6.setVisibility(View.GONE);
                        break;
            case "4" :  emp_lay5.setVisibility(View.GONE);
                        emp_lay6.setVisibility(View.GONE);
                        break;
            case "5" :  emp_lay6.setVisibility(View.GONE);
                        break;

        }


        Log.e("json",json);
        Log.e("no",no_of_passenger);
        try{



        if(!json.equals("info")) {

            JSONObject jsonObject = (JSONObject) JSONValue.parse(json);
            JSONObject response = (JSONObject) jsonObject.get("response");
            bookings = (JSONObject) response.get("BookingDetail");
            Log.e("details", bookings.toString());
            if (type.equals("cab")) {
                booking_ref_no.setText(bookings.get("reference_no").toString());
                switch (bookings.get("tour_type").toString()) {
                    case "0":
                        tour_type.setText("Radio");
                        break;
                    case "1":
                        tour_type.setText("Local");
                        break;
                    case "2":
                        tour_type.setText("Outstation");
                        break;
                }

                pickup_location.setText(bookings.get("pickup_location").toString());
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                try {
                    // Date d1 = format1.parse(obj.getPickup_date_time());
                    // Log.d("simple date",d1.toString());

                    Date date = format1.parse(bookings.get("pickup_datetime").toString());
                    //         DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(activity);
                    //     Log.d("Time: ", dateFormat.format(date));
                    android.text.format.DateFormat df = new android.text.format.DateFormat();
                    String d = df.format("dd-MM-yyyy hh:mm:ss a", date).toString();
                    Log.d("Time1: ", d);
                    pickup_date_time.setText(d);


                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (bookings.get("ass_code").toString().equals("")) {
                    assesmenlay.setVisibility(View.GONE);
                } else {
                    ass_code.setText(bookings.get("ass_code").toString());
                }
                reason_for_booking.setText(bookings.get("reason_booking").toString());
                try {
                    // Date d1 = format1.parse(obj.getPickup_date_time());
                    // Log.d("simple date",d1.toString());

                    Date date = format1.parse(bookings.get("booking_date").toString());
                    //         DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(activity);
                    //     Log.d("Time: ", dateFormat.format(date));
                    android.text.format.DateFormat df = new android.text.format.DateFormat();
                    String d = df.format("dd-MM-yyyy hh:mm:ss a", date).toString();
                    Log.d("Time1: ", d);
                    booking_date.setText(d);


                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ticket_lay.setVisibility(View.GONE);
                hotel_ticket.setVisibility(View.GONE);
                if (bookings.get("driver_name").toString().length() > 1) {
                    driver_name.setText(bookings.get("driver_name").toString());

                } else {
                    driver_lay.setVisibility(View.GONE);
                }

                if (bookings.get("driver_contact").toString().length() > 1) {
                    driver_contact_no.setText(bookings.get("driver_contact").toString());
                } else {
                    driver_lay.setVisibility(View.GONE);
                }
                if (bookings.get("taxi_model_name") != null) {
                    taxi_model_name.setText(bookings.get("taxi_model_name").toString());
                } else {
                    texi_lay.setVisibility(View.GONE);
                }
                if (bookings.get("taxi_reg_no") != null) {
                    taxi_reg_no.setText(bookings.get("taxi_reg_no").toString());
                } else {
                    texi_lay.setVisibility(View.GONE);
                }
            }
            if (type.equals("bus")) {
                booking_ref_no.setText(bookings.get("reference_no").toString());
                tour_type.setText("Bus");
                pickup_location.setText(bookings.get("pickup_city").toString());
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                try {
                    // Date d1 = format1.parse(obj.getPickup_date_time());
                    // Log.d("simple date",d1.toString());

                    Date date = format1.parse(bookings.get("date_of_journey").toString());
                    Date booking  = format1.parse(bookings.get("booking_datetime").toString());
                    //         DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(activity);
                    //     Log.d("Time: ", dateFormat.format(date));
                    android.text.format.DateFormat df = new android.text.format.DateFormat();
                    String d = df.format("dd-MM-yyyy hh:mm:ss a", date).toString();
                    Log.d("Time1: ", d);
                    pickup_date_time.setText(d);
                    booking_date.setText(df.format("dd-MM-yyyy hh:mm:ss a", booking).toString());



                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ass_code.setText(bookings.get("assessment_code").toString());
                reason_for_booking.setText(bookings.get("reason_of_booking").toString());
                driver_lay.setVisibility(View.GONE);
                texi_lay.setVisibility(View.GONE);
                hotel_ticket.setVisibility(View.GONE);
                downloadTicketLay.setVisibility(View.GONE);

                if (bookings.get("ticket_number") == null && bookings.get("pnr_number") == null && bookings.get("bus_type_allocated") == null
                        && bookings.get("operator_name").toString().length() < 1 && bookings.get("operator_contact").toString().length() < 1 && bookings.get("seat_number") == null) {
                    ticket_lay.setVisibility(View.GONE);
                } else {
                    ticket_no.setText(bookings.get("ticket_number").toString());
                    pnr_no.setText(bookings.get("pnr_number").toString());
                    bus_type.setText(bookings.get("bus_type_allocated").toString());
                    operator_name.setText(bookings.get("operator_name").toString());
                    operator_contact.setText(bookings.get("operator_contact").toString());
                    seat_no.setText(bookings.get("seat_number").toString());
                    ticket_lay.setVisibility(View.VISIBLE);
                    downloadTicketLay.setVisibility(View.VISIBLE);

                }

            }
            if (type.equals("train")) {
                booking_ref_no.setText(bookings.get("reference_no").toString());
                tour_type.setText("Train");
                pickup_location.setText(bookings.get("from_city").toString());
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                try {
                    // Date d1 = format1.parse(obj.getPickup_date_time());
                    // Log.d("simple date",d1.toString());

                    Date date = format1.parse(bookings.get("date_of_journey").toString());
                    Date booking =format1.parse(bookings.get("booking_datetime").toString());

                    //         DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(activity);
                    //     Log.d("Time: ", dateFormat.format(date));
                    android.text.format.DateFormat df = new android.text.format.DateFormat();
                    String d = df.format("dd-MM-yyyy hh:mm:ss a", date).toString();
                    Log.d("Time1: ", d);
                    pickup_date_time.setText(d);
                    booking_date.setText(df.format("dd-MM-yyyy hh:mm:ss a", booking).toString());


                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ass_code.setText(bookings.get("assessment_code").toString());
                reason_for_booking.setText(bookings.get("reason_of_booking").toString());

                driver_lay.setVisibility(View.GONE);
                texi_lay.setVisibility(View.GONE);
                hotel_ticket.setVisibility(View.GONE);

                if (bookings.get("pnr_number") == null && bookings.get("coach_type_allocated") == null && bookings.get("seat_number") == null) {
                    ticket_lay.setVisibility(View.GONE);
                } else {
                    //ticket_no.setText(bookings.get("ticket_number").toString());
                    lay_ticketno.setVisibility(View.GONE);
                    lay_bus_type.setVisibility(View.GONE);
                    lay_operator_name.setVisibility(View.GONE);
                            lay_operator_contact.setVisibility(View.GONE);
                    pnr_no.setText(bookings.get("pnr_number").toString());
                    //bus_type.setText(bookings.get("bus_type_allocated").toString());
                    //operator_name.setText(bookings.get("operator_name").toString());
                    //operator_contact.setText(bookings.get("operator_contact").toString());
                    seat_no.setText(bookings.get("seat_number").toString());
                    downloadTicketLay.setVisibility(View.VISIBLE);

                }

            }
            if (type.equals("hotel")) {
                booking_ref_no.setText(bookings.get("reference_no").toString());
                tour_type.setText("Hotel");
                pickup_location.setText(bookings.get("city").toString());
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                try {
                    // Date d1 = format1.parse(obj.getPickup_date_time());
                    // Log.d("simple date",d1.toString());

                    Date date = format1.parse(bookings.get("arrival_datetime").toString());
                    //         DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(activity);
                    //     Log.d("Time: ", dateFormat.format(date));
                    android.text.format.DateFormat df = new android.text.format.DateFormat();
                    String d = df.format("dd-MM-yyyy hh:mm:ss a", date).toString();
                    Log.d("Time1: ", d);
                    pickup_date_time.setText(d);
                    pickup_date_time.setText(bookings.get("arrival_datetime").toString());
                    downloadTicketLay.setVisibility(View.VISIBLE);



                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ass_code.setText(bookings.get("ass_code").toString());
                reason_for_booking.setText(bookings.get("reason_booking").toString());
                booking_date.setText(bookings.get("booking_date").toString());
                lay10.setVisibility(View.VISIBLE);
                lay11.setVisibility(View.VISIBLE);
                lay5.setVisibility(View.GONE);
                room_type_1.setText(bookings.get("room_type").toString());
                check_in.setText(bookings.get("arrival_datetime").toString());

                driver_lay.setVisibility(View.GONE);
                texi_lay.setVisibility(View.GONE);
                ticket_lay.setVisibility(View.GONE);


                if (bookings.get("operator_name") == null && bookings.get("room_no").toString().length() < 1) {
                    if (bookings.get("assigned_room_type").toString().length() > 1 && bookings.get("assigned_hotel_address").toString().length() > 1
                            && bookings.get("voucher_number").toString().length() > 1&&bookings.get("assigned_room_type").toString().length()>1) {
                        hotel_address.setText(bookings.get("assigned_hotel_address").toString());
                        voucher_no.setText(bookings.get("voucher_number").toString());
                        hotel_contact.setText(bookings.get("hotel_contact").toString());
                        room_no.setText(bookings.get("room_no").toString());
                        room_type.setText(bookings.get("assigned_room_type").toString());
                        hotel_name.setVisibility(View.GONE);


                    } else {
                            hotel_ticket.setVisibility(View.GONE);

                    }
                } else {
                    hotel_name.setVisibility(View.GONE);

                    hotel_address.setText(bookings.get("assigned_hotel_address").toString());
                    voucher_no.setText(bookings.get("voucher_number").toString());
                    room_type.setText(bookings.get("assigned_room_type").toString());
                    hotel_name.setText(bookings.get("operator_name").toString());
                    hotel_contact.setText(bookings.get("hotel_contact").toString());
                    room_no.setText(bookings.get("room_no").toString());

                }

            }


        }
        }catch(NullPointerException e){
            e.printStackTrace();
            Toasty.error(getActivity(),"Something went wrong", Toast.LENGTH_SHORT).show();
            }


            ArrayList<String> namelist,emaillist,phnolist;
            ArrayList<FeedbackModel> feedbacksList=new ArrayList<>();
            namelist=new ArrayList<>();
            emaillist=new ArrayList<>();
            phnolist=new ArrayList<>();
            Map<String,String> commentEmployee=new HashMap<>();

            JSONArray peoplearray=(JSONArray) bookings.get("People");
            Iterator iterator=peoplearray.iterator();
            int c=0;
            while (iterator.hasNext()){
                JSONObject people=(JSONObject) iterator.next();
                namelist.add(new String(people.get("people_name").toString()));
                emaillist.add(new String(people.get("people_email").toString()));
                phnolist.add(new String(people.get("people_contact").toString()));
                if (code.equals("3") && people.get("comment")!=null && people.get("comment").toString().length()>9) {
                    commentEmployee.put(new String(people.get("comment").toString()),new String(people.get("people_name").toString()));
                    feedbacksList.add(new FeedbackModel(people.get("people_name").toString(),people.get("comment").toString(),people.get("comment_date").toString()));
                }
                c++;
            }

            switch (Integer.toString(c)){
                case "1" :  empname1.setText(namelist.get(0));
                            empemail1.setText(emaillist.get(0));
                            emphno1.setText(phnolist.get(0));
                            break;
                case "2" :  empname1.setText(namelist.get(0));
                            empemail1.setText(emaillist.get(0));
                            emphno1.setText(phnolist.get(0));
                            empname2.setText(namelist.get(1));
                            empemail2.setText(emaillist.get(1));
                            emphno2.setText(phnolist.get(1));
                            break;
                case "3" :  empname1.setText(namelist.get(0));
                            empemail1.setText(emaillist.get(0));
                            emphno1.setText(phnolist.get(0));
                            empname2.setText(namelist.get(1));
                            empemail2.setText(emaillist.get(1));
                            emphno2.setText(phnolist.get(1));
                            empname3.setText(namelist.get(2));
                            empemail3.setText(emaillist.get(2));
                            emphno3.setText(phnolist.get(2));
                            break;
                case "4" :  empname1.setText(namelist.get(0));
                            empemail1.setText(emaillist.get(0));
                            emphno1.setText(phnolist.get(0));
                            empname2.setText(namelist.get(1));
                            empemail2.setText(emaillist.get(1));
                            emphno2.setText(phnolist.get(1));
                            empname3.setText(namelist.get(2));
                            empemail3.setText(emaillist.get(2));
                            emphno3.setText(phnolist.get(2));
                            empname4.setText(namelist.get(3));
                            empemail4.setText(emaillist.get(3));
                            emphno4.setText(phnolist.get(3));
                            break;
                case "5" :  empname1.setText(namelist.get(0));
                            empemail1.setText(emaillist.get(0));
                            emphno1.setText(phnolist.get(0));
                            empname2.setText(namelist.get(1));
                            empemail2.setText(emaillist.get(1));
                            emphno2.setText(phnolist.get(1));
                            empname3.setText(namelist.get(2));
                            empemail3.setText(emaillist.get(2));
                            emphno3.setText(phnolist.get(2));
                            empname4.setText(namelist.get(3));
                            empemail4.setText(emaillist.get(3));
                            emphno4.setText(phnolist.get(3));
                            empname5.setText(namelist.get(4));
                            empemail5.setText(emaillist.get(4));
                            emphno5.setText(phnolist.get(4));
                            break;
                case "6" :  empname1.setText(namelist.get(0));
                            empemail1.setText(emaillist.get(0));
                            emphno1.setText(phnolist.get(0));
                            empname2.setText(namelist.get(1));
                            empemail2.setText(emaillist.get(1));
                            emphno2.setText(phnolist.get(1));
                            empname3.setText(namelist.get(2));
                            empemail3.setText(emaillist.get(2));
                            emphno3.setText(phnolist.get(2));
                            empname4.setText(namelist.get(3));
                            empemail4.setText(emaillist.get(3));
                            emphno4.setText(phnolist.get(3));
                            empname5.setText(namelist.get(4));
                            empemail5.setText(emaillist.get(4));
                            emphno5.setText(phnolist.get(4));
                            empname6.setText(namelist.get(5));
                            empemail6.setText(emaillist.get(5));
                            emphno6.setText(phnolist.get(5));
                            break;
            }

        if(code.equals("3") && type.equals("cab")){
            feedbackLay.setVisibility(View.VISIBLE);
            Log.d("condition","true");
        }else {
            feedbackLay.setVisibility(View.GONE);
            Log.d("condition","false");
        }


            switch (feedbacksList.size()){
                case 0:
                    feedback1.setText("\"No Feedbacks Yet\"");
                    feedbackLay1.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    feedback1.setText("\""+feedbacksList.get(0).getFeedback()+"\"");
                    feedbackDate1.setText(feedbacksList.get(0).getFeedbackDate()+"\n-"+feedbacksList.get(0).getEmployeeName());
                    feedbackLay1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    feedback1.setText("\""+feedbacksList.get(0).getFeedback()+"\"");
                    feedback2.setText("\""+feedbacksList.get(1).getFeedback()+"\"");
                    feedbackDate1.setText(feedbacksList.get(0).getFeedbackDate()+"\n-"+feedbacksList.get(0).getEmployeeName());
                    feedbackDate2.setText(feedbacksList.get(1).getFeedbackDate()+"\n-"+feedbacksList.get(1).getEmployeeName());
                    feedbackLay1.setVisibility(View.VISIBLE);
                    feedbackLay2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    feedback1.setText("\""+feedbacksList.get(0).getFeedback()+"\"");
                    feedback2.setText("\""+feedbacksList.get(1).getFeedback()+"\"");
                    feedback1.setText("\""+feedbacksList.get(2).getFeedback()+"\"");
                    feedbackDate1.setText(feedbacksList.get(0).getFeedbackDate()+"\n-"+feedbacksList.get(0).getEmployeeName());
                    feedbackDate1.setText(feedbacksList.get(1).getFeedbackDate()+"\n-"+feedbacksList.get(1).getEmployeeName());
                    feedbackDate1.setText(feedbacksList.get(2).getFeedbackDate()+"\n-"+feedbacksList.get(2).getEmployeeName());
                    feedbackLay1.setVisibility(View.VISIBLE);
                    feedbackLay2.setVisibility(View.VISIBLE);
                    feedbackLay3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    feedback1.setText("\""+feedbacksList.get(0).getFeedback()+"\"");
                    feedback2.setText("\""+feedbacksList.get(1).getFeedback()+"\"");
                    feedback3.setText("\""+feedbacksList.get(2).getFeedback()+"\"");
                    feedback4.setText("\""+feedbacksList.get(3).getFeedback()+"\"");
                    feedbackDate1.setText(feedbacksList.get(0).getFeedbackDate()+"\n-"+feedbacksList.get(0).getEmployeeName());
                    feedbackDate1.setText(feedbacksList.get(1).getFeedbackDate()+"\n-"+feedbacksList.get(1).getEmployeeName());
                    feedbackDate1.setText(feedbacksList.get(2).getFeedbackDate()+"\n-"+feedbacksList.get(2).getEmployeeName());
                    feedbackDate1.setText(feedbacksList.get(3).getFeedbackDate()+"\n-"+feedbacksList.get(3).getEmployeeName());
                    feedbackLay1.setVisibility(View.VISIBLE);
                    feedbackLay1.setVisibility(View.VISIBLE);
                    feedbackLay1.setVisibility(View.VISIBLE);
                    feedbackLay1.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    feedback1.setText("\""+feedbacksList.get(0).getFeedback()+"\"");
                    feedback1.setText("\""+feedbacksList.get(1).getFeedback()+"\"");
                    feedback1.setText("\""+feedbacksList.get(2).getFeedback()+"\"");
                    feedback1.setText("\""+feedbacksList.get(3).getFeedback()+"\"");
                    feedback1.setText("\""+feedbacksList.get(4).getFeedback()+"\"");
                    feedbackDate1.setText(feedbacksList.get(0).getFeedbackDate()+"\n-"+feedbacksList.get(0).getEmployeeName());
                    feedbackDate1.setText(feedbacksList.get(1).getFeedbackDate()+"\n-"+feedbacksList.get(1).getEmployeeName());
                    feedbackDate1.setText(feedbacksList.get(2).getFeedbackDate()+"\n-"+feedbacksList.get(2).getEmployeeName());
                    feedbackDate1.setText(feedbacksList.get(3).getFeedbackDate()+"\n-"+feedbacksList.get(3).getEmployeeName());
                    feedbackDate1.setText(feedbacksList.get(4).getFeedbackDate()+"\n-"+feedbacksList.get(4).getEmployeeName());
                    feedbackLay1.setVisibility(View.VISIBLE);
                    feedbackLay1.setVisibility(View.VISIBLE);
                    feedbackLay1.setVisibility(View.VISIBLE);
                    feedbackLay1.setVisibility(View.VISIBLE);
                    feedbackLay1.setVisibility(View.VISIBLE);
                    break;
                case 6:
                    feedback1.setText("\""+feedbacksList.get(0).getFeedback()+"\"");
                    feedback1.setText("\""+feedbacksList.get(1).getFeedback()+"\"");
                    feedback1.setText("\""+feedbacksList.get(2).getFeedback()+"\"");
                    feedback1.setText("\""+feedbacksList.get(3).getFeedback()+"\"");
                    feedback1.setText("\""+feedbacksList.get(4).getFeedback()+"\"");
                    feedback1.setText("\""+feedbacksList.get(5).getFeedback()+"\"");
                    feedbackDate1.setText(feedbacksList.get(0).getFeedbackDate()+"\n-"+feedbacksList.get(0).getEmployeeName());
                    feedbackDate1.setText(feedbacksList.get(1).getFeedbackDate()+"\n-"+feedbacksList.get(1).getEmployeeName());
                    feedbackDate1.setText(feedbacksList.get(2).getFeedbackDate()+"\n-"+feedbacksList.get(2).getEmployeeName());
                    feedbackDate1.setText(feedbacksList.get(3).getFeedbackDate()+"\n-"+feedbacksList.get(3).getEmployeeName());
                    feedbackDate1.setText(feedbacksList.get(4).getFeedbackDate()+"\n-"+feedbacksList.get(4).getEmployeeName());
                    feedbackDate1.setText(feedbacksList.get(5).getFeedbackDate()+"\n-"+feedbacksList.get(5).getEmployeeName());
                    feedbackLay1.setVisibility(View.VISIBLE);
                    feedbackLay1.setVisibility(View.VISIBLE);
                    feedbackLay1.setVisibility(View.VISIBLE);
                    feedbackLay1.setVisibility(View.VISIBLE);
                    feedbackLay1.setVisibility(View.VISIBLE);
                    feedbackLay1.setVisibility(View.VISIBLE);
            }

        return view;

        }


    @Override
    public void onClick(View view) {
        String phoneno=new String ();
        int id=view.getId();
        switch (id){
            case R.id.employee_contact1: phoneno=emphno1.getText().toString();
                                            break;
            case R.id.employee_contact2: phoneno=emphno2.getText().toString();
                                            break;
            case R.id.employee_contact3: phoneno=emphno3.getText().toString();
                                            break;
            case R.id.employee_contact4: phoneno=emphno4.getText().toString();
                                            break;
            case R.id.employee_contact5: phoneno=emphno5.getText().toString();
                                            break;
            case R.id.employee_contact6: phoneno=emphno6.getText().toString();
                                            break;
            case R.id.driver_contact: phoneno=driver_contact_no.getText().toString();
                                            break;
            case R.id.operator_contact: phoneno=operator_contact.getText().toString();
                                            break;
            case R.id.download_ticket: {
                if (type.equals("cab")) {
                    Log.d("DownLoadTicket", "Taxi");
                } else if (type.equals("bus")) {
                    if (busBookingDetail==null){
                        Log.d("Status","null");
                    }
                    else {
                        Log.d("DownLoadTicket", "Bus " +gson.toJson(busBookingDetail));
                    }

                    if (busBookingDetail.getTicket() != null && busBookingDetail.getTicket().length() > 1) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(busBookingDetail.getInvoiceDetail().get(0).getTicket()));
                        startActivity(browserIntent);
                    }
                }
                return;
            }

        }
        if (phoneno.startsWith("+91") || phoneno.startsWith("0")){
            Log.d("clicled phone no",phoneno);
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneno));
            startActivity(intent);
        }
        else {
            Log.d("clicled phone no","+91"+phoneno);
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+91"+phoneno));
            startActivity(intent);
        }
    }
}

