package com.taxivaxi.spoc.BookingForm;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LifecycleRegistryOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.taxivaxi.spoc.Constant;
import com.taxivaxi.spoc.PlacesFieldSelector;
import com.taxivaxi.spoc.R;
import com.taxivaxi.spoc.model.allcitiesmodel.City;
import com.taxivaxi.spoc.model.assesmentcitymodel.AssCity;
import com.taxivaxi.spoc.model.assesmentcodemodel.AssCode;
import com.taxivaxi.spoc.model.taxitypemodel.TaxiType;
import com.taxivaxi.spoc.retrofit.ConfigRetrofit;
import com.taxivaxi.spoc.utility.GsonStringConvertor;
import com.taxivaxi.spoc.viewmodels.AllCitiesViewModel;
import com.taxivaxi.spoc.viewmodels.AssessmentCitiesViewModel;
import com.taxivaxi.spoc.viewmodels.AssessmentCodeViewModel;
import com.taxivaxi.spoc.viewmodels.TaxiTypeViewModel;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import es.dmoral.toasty.Toasty;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class OutstationFragment extends Fragment  {

    SharedPreferences placepref,loginpref,assesmentpref,taxitypepref;
    ArrayList<String> placelist,pacakagelist,places_idlist,pacakage_idlist,taxi_type_list,taxi_type_id_list,assesmentlist,employee_idlist,employee_id_value,getEmployee_idphno;
    ArrayAdapter<String> placeadapter,assesmentadapter,taxiadapter;
    private static final int PICKUP_CODE_AUTOCOMPLETE = 1,EMPLOYEE_INFO=100;
    private static final int DROP_CODE_AUTOCOMPLETE = 0;
    private TextView pickup;
    public static final String TAG = "outstation";
    private TextView drop;
    static TextView clock,date;
    static TextView passenger,packages,days;
    SearchableSpinner assesmentspinner,pacakagespinner,taxitype,placespinner,assessmentCitiesSpinner;
    ArrayList<String> assessmentCityList,assessmentCityIdList;
    ArrayAdapter<String> assessmentCityAdapter;
    EditText reason_for_booking;
    static int total =1;
    static int nDays=1;
    double lat;
    double lng;
    String city;
    static int max =4;
    static String date_send;
    AssessmentCitiesViewModel assessmentCitiesViewModel;
    AssessmentCodeViewModel assessmentCodeViewModel;
    AllCitiesViewModel allCitiesViewModel;
    TaxiTypeViewModel taxiTypeViewModel;




    String pacakageid=new String();
    View view;
    String outstation;
    String hasSingleEmployee;

    static String assesmentvalue,selected_assementcode;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPageNo;
    RelativeLayout assessmentLayout;
    RectangularBounds bounds = RectangularBounds.newInstance(
            new LatLng(-40,-168),new LatLng(71,136));

    Context context;

    public static OutstationFragment newInstance(int pageNo) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        OutstationFragment fragment = new OutstationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceStat) {
        getActivity().setTitle("Taxi");

        context = getContext();
        if (!Places.isInitialized()) {
            Places.initialize(context, getResources().getString(R.string.places_api_key_part1)+
                    getResources().getString(R.string.places_api_key_part2)+
                    getResources().getString(R.string.places_api_key_part3)
            );

        }
        final PlacesFieldSelector fieldSelector = new PlacesFieldSelector();

        loginpref = getActivity().getSharedPreferences("login_info", MODE_PRIVATE);
        assessmentCitiesViewModel= ViewModelProviders.of(getActivity()).get(AssessmentCitiesViewModel.class);
        assessmentCodeViewModel=ViewModelProviders.of(getActivity()).get(AssessmentCodeViewModel.class);
        allCitiesViewModel=ViewModelProviders.of(getActivity()).get(AllCitiesViewModel.class);
        taxiTypeViewModel=ViewModelProviders.of(getActivity()).get(TaxiTypeViewModel.class);


        outstation = loginpref.getString("is_radio", null);
        hasSingleEmployee=loginpref.getString("has_single_employee",null);

        if (outstation != null&&outstation.equals("1")) {
            view = inflater.inflate(R.layout.content_outstation, container, false);
            assessmentLayout=(RelativeLayout)view.findViewById(R.id.assessment_layout);
            placespinner = (SearchableSpinner) view.findViewById(R.id.radio_pickup_city);
            assessmentCitiesSpinner=(SearchableSpinner)view.findViewById(R.id.assessment_city);
            assesmentspinner = (SearchableSpinner) view.findViewById(R.id.assessnment);
            taxitype=(SearchableSpinner)view.findViewById(R.id.taxitype);

            placelist=new ArrayList<>();
            places_idlist=new ArrayList<>();
            taxi_type_list=new ArrayList<>();
            taxi_type_id_list=new ArrayList<>();
            employee_idlist = new ArrayList<String>();
            assesmentlist = new ArrayList<String>();
            assessmentCityList=new ArrayList<>();
            assessmentCityIdList=new ArrayList<>();
            placelist=new ArrayList<>();
            places_idlist=new ArrayList<>();
            assesmentlist=new ArrayList<>();

            assessmentCityAdapter=new ArrayAdapter<>(getActivity(),R.layout.no_of_passenger_spinner_layout,assessmentCityList);
            assesmentadapter = new ArrayAdapter<String>(getActivity(), R.layout.no_of_passenger_spinner_layout, assesmentlist);
            placeadapter=new ArrayAdapter<String>(getActivity(),R.layout.no_of_passenger_spinner_layout,placelist);
            taxiadapter=new ArrayAdapter<String>(getActivity(),R.layout.no_of_passenger_spinner_layout,taxi_type_list);

            allCitiesViewModel.initAllCitiesViewModel(loginpref.getString("token", "n"), loginpref.getString("admin_id", "n"));
            taxiTypeViewModel.initViewModel(loginpref.getString("token", "n"), loginpref.getString("admin_id", "n"));

            placepref=getActivity().getSharedPreferences("placesinfo",getActivity().MODE_PRIVATE);
            taxitypepref=getActivity().getSharedPreferences("taxitypeinfo", Context.MODE_PRIVATE);
            loginpref=getActivity().getSharedPreferences("login_info",getActivity().MODE_PRIVATE);
            taxitypepref=getActivity().getSharedPreferences("taxitypeinfo", Context.MODE_PRIVATE);



            if (loginpref.getString("has_assessment_codes","n").equals("0")){
                assessmentLayout.setVisibility(View.GONE);
            }
            else {
                assessmentCitiesViewModel.initViewModel(loginpref.getString("token", "n"),
                        loginpref.getString("admin_id", "n"));
                assessmentCodeViewModel.initAssessmentCode(loginpref.getString("token", "n"), loginpref.getString("admin_id", "n"));
                assessmentCitiesSpinner.setAdapter(assessmentCityAdapter);
                assessmentCitiesViewModel.getLiveAssessmentCityList(loginpref.getString("token", "n"), loginpref.getString("admin_id", "n"))
                        .observe(this, new Observer<List<AssCity>>() {

                            @Override
                            public void onChanged(@Nullable List<AssCity> cities) {
                                Log.d("ass_cities", GsonStringConvertor.gsonToString(cities));
                                assessmentCityIdList.clear();
                                assessmentCityList.clear();
                                assessmentCityIdList.add("Assessment City Id");
                                assessmentCityList.add("As.City");
                                if (cities != null) {
                                    for (int i = 0; i < cities.size(); i++) {
                                        assessmentCityList.add(cities.get(i).name);
                                        assessmentCityIdList.add(cities.get(i).id);
                                    }
                                }
                                assessmentCityAdapter.notifyDataSetChanged();
                            }
                        });

                assesmentspinner.setAdapter(assesmentadapter);
                assessmentCodeViewModel.getAssessmentCodeInfo(loginpref.getString("token", "n"), loginpref.getString("admin_id", "n"))
                        .observe(this, new Observer<List<AssCode>>() {
                            @Override
                            public void onChanged(@Nullable List<AssCode> assCodes) {
                                assesmentlist.clear();
                                assesmentlist.add("Assessment Code");
                                if (assCodes != null && assCodes.size() > 0) {
                                    for (int i = 0; i < assCodes.size(); i++) {
                                        assesmentlist.add(assCodes.get(i).assessmentCode);
                                    }
                                }
                                assesmentadapter.notifyDataSetChanged();
                            }
                        });
            }

            assesmentspinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.d("outstaion_Activity",assesmentlist.size()+"");

                    if(assesmentlist.size()==0){

                        Toast.makeText(getActivity(),"Assessment Code list is empty ",Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            });

            placespinner.setAdapter(placeadapter);
            allCitiesViewModel.getCitiesLiveData(loginpref.getString("token", "n"), loginpref.getString("admin_id", "n"))
                    .observe(this, new Observer<List<City>>() {
                        @Override
                        public void onChanged(@Nullable List<City> cities) {
                            placelist.clear();
                            places_idlist.clear();
                            placelist.add("Select City");
                            places_idlist.add("Select City");
                            if (cities!=null && cities.size()>0){
                                for (int i=0;i<cities.size();i++){
                                    placelist.add(cities.get(i).name);
                                    places_idlist.add(cities.get(i).id);
                                }
                            }
                            placeadapter.notifyDataSetChanged();

                        }
                    });



            placespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(placespinner.getSelectedItem().toString().equals("Select City")){
                        TextView errorText = (TextView)placespinner.getSelectedView();
                        // errorText.setError(null);
                    }else{
                        city=placespinner.getSelectedItem().toString();
                        gettingLatLng(city);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        /*    placepref=getActivity().getSharedPreferences("placesinfo",getActivity().MODE_PRIVATE);
            if(placepref.getString("placeinfo","n").equals("n")){
                placelist.add(new String("Select City"));
                places_idlist.add(new String("Select City"));
                placeadapter=new ArrayAdapter<String>(getActivity(),R.layout.no_of_passenger_spinner_layout,placelist);
                placespinner.setAdapter(placeadapter);
                new getCities().execute(loginpref.getString("token","n"),loginpref.getString("admin_id","n"));
               // Toast.makeText(getActivity(),"Please Refresh",Toast.LENGTH_SHORT).show();
            }
            else {
                JSONObject jsonObject = (JSONObject) JSONValue.parse(placepref.getString("placeinfo", "n"));
                JSONObject response = (JSONObject) jsonObject.get("response");
                Log.e("response", response.toString());
                JSONArray array = (JSONArray) response.get("Cities");
                Iterator iterator = array.iterator();
                placelist.add(new String("Select City"));
                places_idlist.add(new String("Select City"));
                while (iterator.hasNext()) {
                    JSONObject object = (JSONObject) iterator.next();
                    placelist.add(new String(object.get("name").toString()));
                    places_idlist.add(new String(object.get("id").toString()));

                }
                placeadapter = new ArrayAdapter<String>(getActivity(), R.layout.no_of_passenger_spinner_layout, placelist);
                placespinner.setAdapter(placeadapter);
            }*/

            taxitype.setAdapter(taxiadapter);
            taxiTypeViewModel.getTaxiTypes().observe(this, new Observer<List<TaxiType>>() {
                @Override
                public void onChanged(@Nullable List<TaxiType> taxiTypes) {
                    taxi_type_list.clear();
                    taxi_type_id_list.clear();
                    taxi_type_list.add(new String("Taxi Type"));
                    taxi_type_id_list.add(new String("Taxi Type"));
                    if (taxiTypes!=null && taxiTypes.size()>0){
                        for (int i=0;i<taxiTypes.size();i++){
                            taxi_type_list.add(taxiTypes.get(i).getName());
                            taxi_type_id_list.add(taxiTypes.get(i).getId());
                        }
                    }
                    taxiadapter.notifyDataSetChanged();
                }
            });

           /* if(taxitypepref.getString("taxitypeinfo","n").equals("n")){
                taxi_type_list.add(new String("Taxi Type"));
                taxi_type_id_list.add(new String("Taxi Type"));
                taxiadapter=new ArrayAdapter<String>(getActivity(),R.layout.no_of_passenger_spinner_layout,taxi_type_list);
                taxitype.setAdapter(taxiadapter);
                new getTaxiTypes().execute(loginpref.getString("token","n"),loginpref.getString("admin_id","n"));
            }
            else {
                JSONObject jsonObject = (JSONObject) JSONValue.parse(taxitypepref.getString("taxitypeinfo", "n"));
                JSONObject response = (JSONObject) jsonObject.get("response");
                Log.e("response", response.toString());
                JSONArray array = (JSONArray) response.get("TaxiTypes");
                Iterator iterator = array.iterator();
                taxi_type_list.add(new String("Taxi Type"));
                taxi_type_id_list.add(new String("Taxi Type"));
                while (iterator.hasNext()) {
                    JSONObject object = (JSONObject) iterator.next();
                    taxi_type_list.add(new String(object.get("name").toString()));
                    taxi_type_id_list.add(new String(object.get("id").toString()));

                }
                taxiadapter = new ArrayAdapter(getActivity(), R.layout.no_of_passenger_spinner_layout, taxi_type_list);
                taxitype.setAdapter(taxiadapter);

            }*/

            taxitype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(taxitype.getSelectedItem().toString().equals("Taxi Type")){
                        Log.e("click","SUV");
                        TextView errorText = (TextView)taxitype.getSelectedView();
                        // errorText.setError(null);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


          /*assesmentspinner.setTitle("Assessment Code");
            assesmentpref=getActivity().getSharedPreferences("assesmentinfo", Context.MODE_PRIVATE);
            assesmentlist=new ArrayList<>();
            if(assesmentpref.getString("assesmentinfo","n").equals("n")){
                if(isInternetConnected(getActivity())) {
                    //assessmentlist.add("Assessment Code");
                    assesmentadapter = new ArrayAdapter<String>(getActivity(), R.layout.no_of_passenger_spinner_layout, assesmentlist);
                    //assessmentspinner.setAdapter(assesmentadapter);
                    new getAssesmentCode().execute(loginpref.getString("token", "n"), loginpref.getString("admin_id", "n"));
                }
                else {

                    dialog=new AlertDialog.Builder(getActivity());
                    dialog.setMessage("INTERNET CONNECTION UNAVAILABLE \n try again later");
                    dialog.show();
                }
            }
            else {
                JSONObject jsonObject = (JSONObject) JSONValue.parse(assesmentpref.getString("assesmentinfo", "n"));
                JSONObject response = (JSONObject) jsonObject.get("response");
                Log.e("response", response.toString());
                JSONArray array = (JSONArray) response.get("AssCodes");
                Iterator iterator = array.iterator();
                assesmentlist.add("Assessment Code");

                while (iterator.hasNext()) {
                    JSONObject object = (JSONObject) iterator.next();
                    Log.d("assementList",new String(object.get("assessment_code").toString()));
                    assesmentlist.add(new String(object.get("assessment_code").toString()));
                }
                assesmentadapter = new ArrayAdapter<String>(getActivity(), R.layout.no_of_passenger_spinner_layout, assesmentlist);
                assesmentspinner.setAdapter(assesmentadapter);

            }
            */

            pickup = (TextView) view.findViewById(R.id.outstation_pickup);
            pickup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent autocompleteIntent = new Autocomplete.
                            IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldSelector.getAllFields())
                            .setLocationBias(bounds)
                            .setCountry("IN")
                            .build(context);
                    startActivityForResult(autocompleteIntent, PICKUP_CODE_AUTOCOMPLETE);
                }
            });
            drop = (TextView) view.findViewById(R.id.outstation_drop);

            drop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent autocompleteIntent = new Autocomplete.
                            IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldSelector.getAllFields())
                            .setLocationBias(bounds)
                            .setCountry("IN")
                            .build(context);
                    startActivityForResult(autocompleteIntent, DROP_CODE_AUTOCOMPLETE);
                }
            });
            clock=(TextView) view.findViewById(R.id.outstaion_time);
            clock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment timePickerFragment = new TimePickerFragment();
                    timePickerFragment.show(getChildFragmentManager(), "timePicker");
                }
            });

            date=(TextView)view.findViewById(R.id.outstaion_date);
            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getChildFragmentManager(), "datePicker");
                }
            });

            passenger = (TextView) view.findViewById(R.id.passengers);
            if (hasSingleEmployee.equals("1")){
                passenger.setText("Self");
                passenger.setTextColor(ContextCompat.getColor(getContext(),R.color.taxi_vaxi_color_light_gray));
                passenger.setEnabled(false);
            }
            passenger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(taxitype.getSelectedItem().toString().equals("SUV")) {
                        Log.e("click", "SUV");
                        // noOfPassenger.add(new String("5"));
                        //noOfPassenger.add(new String("6"));
                        max = 6;
                    }else{
                        max=4;
                    }
                    DialogFragment passengerPicker = new NoOfPassengers();
                    passengerPicker.show(getChildFragmentManager(),"no of radio_passenger");

                }
            });
            days= (TextView)view.findViewById(R.id.days);
            days.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment passengerPicker = new NofDays();
                    passengerPicker.show(getChildFragmentManager(),"no of days");

                }
            });

            reason_for_booking = (EditText) view.findViewById(R.id.outstaion_reason_for_booking);
            Button bookbutton=(Button) view.findViewById(R.id.outstation_submit_button);
            bookbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isInternetConnected(getActivity())) {
                        if (pickup.getText().toString().length()<1 ||
                                date.getText().toString().length()<1
                                || clock.getText().toString().length()<1 ||
                                (hasSingleEmployee.equals("0")&&employee_idlist.size()==0 )||
                                reason_for_booking.getText().toString().length() < 1
                                || placespinner.getSelectedItem().toString().equals("select_city") ||
                                taxitype.getSelectedItem().toString().equals("Taxi Type") ||
                                days.getText().toString().length() < 1 ||
                                drop.getText().length()<1
                                ||(!loginpref.getString("has_assessment_codes","n").equals("0") && assesmentspinner.getSelectedItemPosition()==0)
                                ||(!loginpref.getString("has_assessment_codes","n").equals("0") && assessmentCitiesSpinner.getSelectedItemPosition()==0)) {
                            Toast.makeText(getActivity(), "Please Fill All  Fields", Toast.LENGTH_SHORT).show();
                            if (pickup.getText().toString().length()<1){
                                pickup.setError("field reqoired");
                            }
                            if (date.getText().toString().length()<1){
                                date.setError("field required");
                            }
                            if (clock.getText().toString().length()<1){
                                clock.setError("field required");
                            }
                            if (reason_for_booking.getText().toString().length()<1){
                                reason_for_booking.setError("field required");
                            }
                            if (hasSingleEmployee.equals("0") && employee_idlist.size()==0){
                                passenger.setError("field required");
                            }
                            if (placespinner.getSelectedItem().toString().equals("Select City")){
                                TextView errorText = (TextView)placespinner.getSelectedView();
                                errorText.setError("field required");
                                errorText.setTextColor(Color.RED);
                                errorText.setError(null);
                            }
                            if (taxitype.getSelectedItem().toString().equals("Taxi Type")){


                                TextView errorText = (TextView)taxitype.getSelectedView();
                                errorText.setError("field required");
                                errorText.setTextColor(Color.RED);
                                errorText.setError(null);
                            }
                            if (drop.getText().length()<1){
                                drop.setError("field required");
                            }
                            if (days.getText().toString().length() < 1){
                                days.setError("field required");
                            }
                            if (!loginpref.getString("has_assessment_codes","n").equals("0")){
                                if (assesmentspinner.getSelectedItemPosition()==0){
                                    ((android.widget.TextView)assesmentspinner.getSelectedView()).setError("field required");
                                }
                                if (assessmentCitiesSpinner.getSelectedItemPosition()==0){
                                    ((android.widget.TextView)assessmentCitiesSpinner.getSelectedView()).setError("field required");
                                }
                            }
                        }
                        else {
                            Log.d("PackageSentInfo",loginpref.getString("token", "n")+" "+ places_idlist.get(placespinner.getSelectedItemPosition())+" " +taxi_type_id_list.get(taxitype.getSelectedItemPosition())+" "
                                    +placelist.get(placespinner.getSelectedItemPosition())+" "+taxi_type_list.get(taxitype.getSelectedItemPosition()));
                            new getPacakages().execute(loginpref.getString("token", "n"), places_idlist.get(placespinner.getSelectedItemPosition()), taxi_type_id_list.get(taxitype.getSelectedItemPosition()), "2", loginpref.getString("admin_id", "n"));

                        }
                    }else {
                        dialog=new AlertDialog.Builder(getActivity());
                        dialog.setMessage("Internet Connection Unavailable");
                        dialog.setTitle("Connection Error");
                        dialog.show();
                    }
                }
            });
            return  view;
        }

        view = inflater.inflate(R.layout.not_avialble, container, false);
        com.rey.material.widget.TextView textView = (com.rey.material.widget.TextView) view.findViewById(R.id.text_preview);
        textView.setText("Outstation Cab Service not available for your account");

        return view;
    }
    void completeBooking(){
        if (loginpref.getString("has_assessment_codes", "n").equals("0")){
            new doOutstationBooking().execute(
                    loginpref.getString("token", "n"),
                    loginpref.getString("admin_id", "n"),
                    loginpref.getString("group_id", "n"),
                    "2",
                    " ",
                    reason_for_booking.getText().toString(),
                    total+"",
                    pickup.getText().toString(),
                    drop.getText().toString(),
                    date_send,
                    clock.getText().toString(),
                    loginpref.getString("subgroup_id", "n"),
                    places_idlist.get(placespinner.getSelectedItemPosition()),
                    pacakageid,
                    taxi_type_id_list.get(taxitype.getSelectedItemPosition()),
                    days.getText().toString(),
                    " ",
                    hasSingleEmployee);

        }
        else {
            new doOutstationBooking().execute(
                    loginpref.getString("token", "n"),
                    loginpref.getString("admin_id", "n"),
                    loginpref.getString("group_id", "n"),
                    "2",
                    assesmentspinner.getSelectedItem().toString(),
                    reason_for_booking.getText().toString(),
                    total+"",
                    pickup.getText().toString(),
                    drop.getText().toString(),
                    date_send,
                    clock.getText().toString(),
                    loginpref.getString("subgroup_id", "n"),
                    places_idlist.get(placespinner.getSelectedItemPosition()),
                    pacakageid,
                    taxi_type_id_list.get(taxitype.getSelectedItemPosition()),
                    days.getText().toString(),
                    assessmentCityList.get(assessmentCitiesSpinner.getSelectedItemPosition()),
                    hasSingleEmployee);
        }
    }
    static AlertDialog.Builder dialog;
    public  static boolean isInternetConnected(Context context){
        ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo[]=cm.getAllNetworkInfo();

        int i;
        for(i=0;i<networkInfo.length;++i){
            if(networkInfo[i].getState()== NetworkInfo.State.CONNECTED){
                return true;
            }
        }
        return false;

    }


    protected  class doOutstationBooking extends AsyncTask<String,Integer,String>{
        String apiURL = Constant.createbooking;
        Response response;
        OkHttpClient client = ConfigRetrofit.configOkhttp();
        RequestBody formBody;
        String assesmentvalue=new String();


        @Override
        protected String doInBackground(String... params) {


            switch(employee_idlist.size()) {
                case 0:
                    formBody = new FormBody.Builder()
                            .add("access_token", params[0])
                            .add("admin_id", params[1])
                            .add("group_id", params[2])
                            .add("tour_type", params[3])
                            .add("ass_code", params[4])
                            .add("reason_booking", params[5])
                            .add("no_of_seats", params[6])
                            .add("employee_id_1", " ")
                            .add("employee_contact_1", "")
                            .add("pickup_location", params[7])
                            .add("drop_location",params[8])
                            .add("days",params[15])
                            .add("pickup_datetime", params[9] + " , " + params[10])
                            .add("subgroup_id", params[11])
                            .add("city_id",params[12])
                            .add("rate_id",params[13])
                            .add("taxi_type_id",params[14])
                            .add("assessment_city",params[16])
                            .add("has_single_employee",params[17])
                            .build();
                    break;
                case 1:        formBody = new FormBody.Builder()
                        .add("access_token", params[0])
                        .add("admin_id", params[1])
                        .add("group_id", params[2])
                        .add("tour_type", params[3])
                        .add("ass_code", params[4])
                        .add("reason_booking", params[5])
                        .add("no_of_seats", params[6])
                        .add("employee_id_1", employee_id_value.get(0))
                        .add("employee_contact_1", getEmployee_idphno.get(0))
                        .add("pickup_location", params[7])
                        .add("drop_location",params[8])
                        .add("days",params[15])
                        .add("pickup_datetime", params[9] + " , " + params[10])
                        .add("subgroup_id", params[11])
                        .add("city_id",params[12])
                        .add("rate_id",params[13])
                        .add("taxi_type_id",params[14])
                        .add("assessment_city",params[16])
                        .add("has_single_employee",params[17])
                        .build();
                    break;
                case 2:        formBody = new FormBody.Builder()
                        .add("access_token", params[0])
                        .add("admin_id", params[1])
                        .add("group_id", params[2])
                        .add("tour_type", params[3])
                        .add("ass_code", params[4])
                        .add("reason_booking", params[5])
                        .add("no_of_seats", params[6])
                        .add("employee_id_1", employee_id_value.get(0))
                        .add("employee_contact_1", getEmployee_idphno.get(0))
                        .add("employee_id_2", employee_id_value.get(1))
                        .add("employee_contact_2", getEmployee_idphno.get(1))
                        .add("pickup_location", params[7])
                        .add("drop_location",params[8])
                        .add("days",params[15])
                        .add("pickup_datetime", params[9] + " , " + params[10])
                        .add("subgroup_id", params[11])
                        .add("city_id",params[12])
                        .add("rate_id",params[13])
                        .add("taxi_type_id",params[14])
                        .add("assessment_city",params[16])
                        .add("has_single_employee",params[17])
                        .build();
                    break;
                case 3:        formBody = new FormBody.Builder()
                        .add("access_token", params[0])
                        .add("admin_id", params[1])
                        .add("group_id", params[2])
                        .add("tour_type", params[3])
                        .add("ass_code", params[4])
                        .add("reason_booking", params[5])
                        .add("no_of_seats", params[6])
                        .add("employee_id_1", employee_id_value.get(0))
                        .add("employee_contact_1", getEmployee_idphno.get(0))
                        .add("employee_id_2", employee_id_value.get(1))
                        .add("employee_contact_2", getEmployee_idphno.get(1))
                        .add("employee_id_3", employee_id_value.get(2))
                        .add("employee_contact_3", getEmployee_idphno.get(2))
                        .add("pickup_location", params[7])
                        .add("drop_location",params[8])
                        .add("days",params[15])
                        .add("pickup_datetime", params[9] + " , " + params[10])
                        .add("subgroup_id", params[11])
                        .add("city_id",params[12])
                        .add("rate_id",params[13])
                        .add("taxi_type_id",params[14])
                        .add("assessment_city",params[16])
                        .add("has_single_employee",params[17])
                        .build();
                    break;
                case 4:        formBody = new FormBody.Builder()
                        .add("access_token", params[0])
                        .add("admin_id", params[1])
                        .add("group_id", params[2])
                        .add("tour_type", params[3])
                        .add("ass_code", params[4])
                        .add("reason_booking", params[5])
                        .add("no_of_seats", params[6])
                        .add("employee_id_1", employee_id_value.get(0))
                        .add("employee_contact_1", getEmployee_idphno.get(0))
                        .add("employee_id_2", employee_id_value.get(1))
                        .add("employee_contact_2", getEmployee_idphno.get(1))
                        .add("employee_id_3", employee_id_value.get(2))
                        .add("employee_contact_3", getEmployee_idphno.get(2))
                        .add("employee_id_4", employee_id_value.get(3))
                        .add("employee_contact_4", getEmployee_idphno.get(3))
                        .add("pickup_location", params[7])
                        .add("drop_location",params[8])
                        .add("days",params[15])
                        .add("pickup_datetime", params[9] + " , " + params[10])
                        .add("subgroup_id", params[11])
                        .add("city_id",params[12])
                        .add("rate_id",params[13])
                        .add("taxi_type_id",params[14])
                        .add("assessment_city",params[16])
                        .add("has_single_employee",params[17])
                        .build();
                    break;
                case 5:        formBody = new FormBody.Builder()
                        .add("access_token", params[0])
                        .add("admin_id", params[1])
                        .add("group_id", params[2])
                        .add("tour_type", params[3])
                        .add("ass_code", params[4])
                        .add("reason_booking", params[5])
                        .add("no_of_seats", params[6])
                        .add("employee_id_1", employee_id_value.get(0))
                        .add("employee_contact_1", getEmployee_idphno.get(0))
                        .add("employee_id_2", employee_id_value.get(1))
                        .add("employee_contact_2", getEmployee_idphno.get(1))
                        .add("employee_id_3", employee_id_value.get(2))
                        .add("employee_contact_3", getEmployee_idphno.get(2))
                        .add("employee_id_4", employee_id_value.get(3))
                        .add("employee_contact_4", getEmployee_idphno.get(3))
                        .add("employee_id_5", employee_id_value.get(4))
                        .add("employee_contact_5", getEmployee_idphno.get(4))
                        .add("pickup_location", params[7])
                        .add("drop_location",params[8])
                        .add("days",params[15])
                        .add("pickup_datetime", params[9] + " , " + params[10])
                        .add("subgroup_id", params[11])
                        .add("city_id",params[12])
                        .add("rate_id",params[13])
                        .add("taxi_type_id",params[14])
                        .add("assessment_city",params[16])
                        .add("has_single_employee",params[17])
                        .build();
                    break;
                case 6:        formBody = new FormBody.Builder()
                        .add("access_token", params[0])
                        .add("admin_id", params[1])
                        .add("group_id", params[2])
                        .add("tour_type", params[3])
                        .add("ass_code", params[4])
                        .add("reason_booking", params[5])
                        .add("no_of_seats", params[6])
                        .add("employee_id_1", employee_id_value.get(0))
                        .add("employee_contact_1", getEmployee_idphno.get(0))
                        .add("employee_id_2", employee_id_value.get(1))
                        .add("employee_contact_2", getEmployee_idphno.get(1))
                        .add("employee_id_3", employee_id_value.get(2))
                        .add("employee_contact_3", getEmployee_idphno.get(2))
                        .add("employee_id_4", employee_id_value.get(3))
                        .add("employee_contact_4", getEmployee_idphno.get(3))
                        .add("employee_id_5", employee_id_value.get(4))
                        .add("employee_contact_5", getEmployee_idphno.get(4))
                        .add("employee_id_6", employee_id_value.get(5))
                        .add("employee_contact_6", getEmployee_idphno.get(5))
                        .add("pickup_location", params[7])
                        .add("drop_location",params[8])
                        .add("days",params[15])
                        .add("pickup_datetime", params[9] + " , " + params[10])
                        .add("subgroup_id", params[11])
                        .add("city_id",params[12])
                        .add("rate_id",params[13])
                        .add("taxi_type_id",params[14])
                        .add("assessment_city",params[16])
                        .add("has_single_employee",params[17])
                        .build();
                    break;

            }


            Request.Builder request = new Request.Builder();
            request.url(apiURL)
                    .post(formBody)
                    .build();
            try {
                response = client.newCall(request.build()).execute();
                if(response.code()==200) {
                    assesmentvalue = response.body().string();
                    return assesmentvalue;
                }else {
                    Log.e("code",Integer.toString(response.code()));
                    return "Network Error";
                }

            } catch (IOException e) {
                e.getMessage();
                Log.e("error", e.getMessage());
            }
            return "Network Error";

        }

        ProgressDialog pd;
        AlertDialog.Builder d;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("completing booking");
            pd.setCancelable(false);
            pd.show();
            d=new AlertDialog.Builder(getActivity());

        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            if(response!=null){
                response.close();
            }
            pd.dismiss();
            if(s.equals("Network Error"))
            {
                pd.dismiss();
                d.setTitle("CONNECTION ERROR");
                d.setMessage("Unable To Complete Booking");
                d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                d.show();
            }
            else
            {Log.e("bookingResponse",s);
                JSONObject json=(JSONObject)JSONValue.parse(s);
                if(json.get("success").equals("1")){
                    JSONObject response=(JSONObject) json.get("response");
                    d.setTitle("BOOKING SUCCESSFUL");
                    d.setCancelable(false);
                    d.setMessage("Your Booking ID is : "+response.get("BookingId").toString());
                    Toasty.success(getActivity(),"Success",Toast.LENGTH_SHORT).show();

                    d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                       /*     pickuplocation.setText("");
                            date.setText("");
                            time.setText("");
                            dropcity.setText("");
                            noofdays.setText("");
                            reasonforbooking.setText("");
                            employeename.setText("");
                            placespinner.setSelection(0);
                            taxitype.setSelection(0);
                            assessmentspinner.setSelection(0);
                            noofemployeespinner.setSelection(0);
                            */
                            Intent intent=new Intent(getActivity(),CarMain.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });
                }
                else {
                    d.setTitle("BOOKING ERROR");
                    d.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    d.setMessage(json.get("error").toString());

                }
                d.show();
            }
        }
    }
    protected class getTaxiTypes extends AsyncTask<String,Integer,String> {

        String apiURL = Constant.getalltaxitype;
        Response response;
        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {

            RequestBody formBody = new FormBody.Builder()
                    .add("access_token", params[0])
                    .add("admin_id",params[1])
                    .build();

            Request.Builder request = new Request.Builder();
            request.url(apiURL)
                    .post(formBody)
                    .build();
            try {
                response = client.newCall(request.build()).execute();
                if(response.code()==200) {
                    pacakagevalue = response.body().string();
                    return pacakagevalue;
                }else {
                    return "Network Error";
                }

            } catch (IOException e) {
                e.getMessage();
                Log.d("error", e.getMessage());
            }
            return "Network Error";

        }

        ProgressDialog pd;
        AlertDialog.Builder d;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            d=new AlertDialog.Builder(getContext());
            d.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(response!=null) {
                response.body().close();
            }
            if (s.equals("Network Error")) {
                //pd.dismiss();
                d.setTitle("CONNRCTION ERROR");
                d.setMessage("Unable To get Taxi Type");
                //d.show();

            } else {

                Log.e("taxitypes", s);

                JSONObject json = (JSONObject) JSONValue.parse(s);
                if (json.get("success").toString().equals("1")) {
                    SharedPreferences.Editor taxitypeeditor=taxitypepref.edit();
                    taxitypeeditor.putString("taxitypeinfo", s)
                            .commit();
                    JSONObject response = (JSONObject) json.get("response");
                    JSONArray pacakages = (JSONArray) response.get("TaxiTypes");
                    Iterator i = pacakages.iterator();
                    while (i.hasNext()) {
                        JSONObject obj = (JSONObject) i.next();
                        taxi_type_list.add(new String(obj.get("name").toString()));
                        taxi_type_id_list.add(new String(obj.get("id").toString()));

                    }
                    if (taxi_type_list != null) {

                        taxiadapter.setNotifyOnChange(true);
                    }
                }

            }


        }

    }

    String placevalue;
    protected class getCities extends AsyncTask<String,Integer,String> {

        String apiURL = Constant.getcitylist;
        Response response;
        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {
            Log.e("city_admin",params[1]);
            RequestBody formBody = new FormBody.Builder()
                    .add("access_token", params[0])
                    .add("admin_id",params[1])
                    .build();

            Request.Builder request = new Request.Builder();
            request.url(apiURL)
                    .post(formBody)
                    .build();
            try {
                response = client.newCall(request.build()).execute();
                if(response.code()==200) {
                    placevalue = response.body().string();
                    return placevalue;
                }
                else{
                    return "Network Error";
                }

            } catch (IOException e) {
                e.getMessage();
                Log.d("error", e.getMessage());
            }
            return "Network Error";

        }

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(response!=null) {
                response.body().close();
            }
            if (s.equals("Network Error")) {

            }
            else {
                Log.e("places", s);

                JSONObject json = (JSONObject) JSONValue.parse(s);
                if(json.get("success").toString().equals("1")) {
                    SharedPreferences.Editor placeeditor=placepref.edit();
                    placeeditor.putString("placeinfo", s)
                            .commit();
                    JSONObject response = (JSONObject) json.get("response");
                    JSONArray places = (JSONArray) response.get("Cities");
                    Iterator i = places.iterator();
                    while (i.hasNext()) {
                        JSONObject obj = (JSONObject) i.next();
                        placelist.add(new String(obj.get("name").toString()));
                        places_idlist.add(new String(obj.get("id").toString()));

                    }
                    if (placelist != null) {

                        placeadapter.setNotifyOnChange(true);
                    }
                }

            }


        }


    }

    protected  void gettingLatLng(String city){
        if(Geocoder.isPresent() && isInternetConnected(getActivity())){
            try {
                Geocoder gc = new Geocoder(getActivity());
                List<Address> addresses= gc.getFromLocationName(city, 5); // get the found Address Objects

                List<LatLng> latlng = new ArrayList<LatLng>(addresses.size()); // A list to save the coordinates if they are available
                for(Address a : addresses){
                    if(a.hasLatitude() && a.hasLongitude()){
                        latlng.add(new LatLng(a.getLatitude(), a.getLongitude()));
                        lat =a.getLatitude();
                        lng = a.getLongitude();
                        Log.d("lnag",lng+"");
                    }
                }
                Log.d("Local lat and lng", Arrays.toString(latlng.toArray()));
            } catch (IOException e) {
                // handle the exception
            }
        }

    }

    protected class getAssesmentCode extends AsyncTask<String,Integer,String> {

        String apiURL = Constant.getassesmentcodes;
        Response response;
        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {

            RequestBody formBody = new FormBody.Builder()
                    .add("access_token", params[0])
                    .add("admin_id",params[1])
                    .build();

            Request.Builder request = new Request.Builder();
            request.url(apiURL)
                    .post(formBody)
                    .build();
            try {
                response = client.newCall(request.build()).execute();
                if(response.code()==200) {
                    assesmentvalue = response.body().string();
                    return assesmentvalue;
                }else {
                    return "Network Error";
                }
            } catch (IOException e) {
                e.getMessage();
                Log.d("error", e.getMessage());
            }
            return "Network Error";

        }

        ProgressDialog pd;
        AlertDialog.Builder d;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("loading");
            pd.show();
            // assesmentadapter.setNotifyOnChange(true);

            d=new AlertDialog.Builder(getActivity());
            d.setTitle("CONNECTION ERROR");
            d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(response!=null){
                pd.dismiss();
                response.close();
            }

            if(s.equals("Network Error"))
            {   d.setTitle("CONNECTION ERROR");
                d.setMessage("Unable To Obtain Assesment Codes");
                d.show();
            } else {
                Log.d("Assessment", s);

                JSONObject json = (JSONObject) JSONValue.parse(s);
                if(json.get("success").toString().equals("1")) {
                    SharedPreferences.Editor assesmenteditor=assesmentpref.edit();
                    assesmenteditor.putString("assesmentinfo", s)
                            .commit();
                    JSONObject response = (JSONObject) json.get("response");
                    JSONArray assesment = (JSONArray) response.get("AssCodes");
                    Iterator i = assesment.iterator();
                    assesmentlist.add("Assessment Code");

                    while (i.hasNext()) {
                        JSONObject obj = (JSONObject) i.next();
                        assesmentlist.add(new String(obj.get("assessment_code").toString()));
                        //assesmentadapter.setNotifyOnChange(true);

                    }
                    if (assesmentlist != null) {
                        // assessmentlist.add("abra ka dabra");
                        Log.d("set notify data set",assesmentlist.toString());
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.no_of_passenger_spinner_layout, assesmentlist);

                        assesmentspinner.setAdapter(adapter);

                        assesmentadapter.setNotifyOnChange(true);
                    }
                }

            }

            pd.dismiss();
        }


    }
    String pacakagevalue;
    protected class getPacakages extends AsyncTask<String,Integer,String> {

        String apiURL =Constant.getratesbycity;
        Response response;
        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {

            RequestBody formBody = new FormBody.Builder()
                    .add("access_token", params[0])
                    .add("city_id", params[1])
                    .add("taxi_type_id",params[2])
                    .add("tour_type",params[3])
                    .add("admin_id",params[4])
                    .build();

            Request.Builder request = new Request.Builder();
            request.url(apiURL)
                    .post(formBody)
                    .build();
            try {
                response = client.newCall(request.build()).execute();
                if (response.code()==200) {
                    pacakagevalue = response.body().string();
                    return pacakagevalue;
                }
                else {
                    return "Network Error";
                }

            } catch (IOException e) {
                e.getMessage();
                Log.d("error", e.getMessage());
            }
            return "Network Error";

        }

        ProgressDialog pd;
        AlertDialog.Builder ad,d;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("getting packages");
            pd.setCancelable(false);
            pd.show();
            d=new AlertDialog.Builder(getActivity());
            ad=new AlertDialog.Builder(getActivity());
            ad.setTitle("COMPLETE OUTSTATION BOOKING");
            ad.setMessage("Proceed To Complete Booking");
            ad.setCancelable(false);
            ad.setPositiveButton("proceed", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    completeBooking();

                }
            });
            ad.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(response!=null) {
                response.body().close();
            }
            if (s.equals("Network Error")) {
                pd.dismiss();
                d.setTitle("CONNECTION ERROR");
                d.setMessage("Unable To Load Package");
                d.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                d.show();

            } else {
                Log.e("pacakages", s);

                JSONObject json = (JSONObject) JSONValue.parse(s);
                if (json.get("success").toString().equals("1")) {
                    pd.dismiss();
                    JSONObject response = (JSONObject) json.get("response");
                    JSONArray pacakages = (JSONArray) response.get("Pkages");
                    Iterator i = pacakages.iterator();
                    if(i.hasNext()) {
                        JSONObject obj = (JSONObject) i.next();
                        pacakageid=obj.get("id").toString();
                        ad.show();

                    }
                }else{
                    pd.dismiss();
                    d.setTitle("Package");
                    d.setTitle("No Package Found");
                    d.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
                        @Override
                        public  void onClick(DialogInterface dialogInterface, int which){

                        }
                    });
                    d.show();



                }

            }


        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("request code",requestCode+"");
        // Log.d("employeee", Arrays.toString(employeeIdList.toArray()));

        // Check that the result was from the autocomplete widget.
        if (requestCode == PICKUP_CODE_AUTOCOMPLETE) {
            if (resultCode == RESULT_OK) {
                // Get the user's selected place from the Intent.
                String location=data.getStringExtra("LOCATION");
                //Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i(TAG, "Place Selected: " + location);
                Place place = Autocomplete.getPlaceFromIntent(data);

                // Format the place's details and display them in the TextView.
                pickup.setError(null);
                pickup.setText(place.getName());
                pickup.append(", "+place.getAddress());

            } else if (resultCode == RESULT_CANCELED) {
                // Indicates that the activity closed before a selection was made. For example if
                // the user pressed the back button.
            }
        } else if (requestCode == DROP_CODE_AUTOCOMPLETE) {
            if (resultCode == RESULT_OK) {
                // Get the user's selected place from the Intent.
                String location=data.getStringExtra("LOCATION");
                //Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i(TAG, "Place Selected: " + location);
                Place place = Autocomplete.getPlaceFromIntent(data);

                // Format the place's details and display them in the TextView.
                drop.setError(null);
                drop.setText(place.getName());
                drop.append(", "+place.getAddress());

            } else if (resultCode == RESULT_CANCELED) {
                // Indicates that the activity closed before a selection was made. For example if
                // the user pressed the back button.
            }
        }else if (requestCode ==EMPLOYEE_INFO){
            Log.d("employeee", Arrays.toString(employee_idlist.toArray()));

            if(resultCode == RESULT_OK){
                //  passenger.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.checkbox_on_background , 0);
                employee_idlist=data.getStringArrayListExtra("employee_id");
                getEmployee_idphno=data.getStringArrayListExtra("employee_phno");
                employee_id_value=data.getStringArrayListExtra("employeeIdValue");
                Log.d("employeee", Arrays.toString(employee_idlist.toArray()));
                Log.d("phone", Arrays.toString(getEmployee_idphno.toArray()));
                Log.d("employeee", Arrays.toString(employee_id_value.toArray()));

            }

        }
    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month , day);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            return datePickerDialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            final Calendar c = Calendar.getInstance();
            c.set(year,month,day);
            if(c.compareTo(Calendar.getInstance())>=0) {
                month = month + 1;
                date_send=year + "-" + month + "-" + day;

                if(day<10){
                    String d= Integer.toString(day);
                    d= 0+""+d;
                    date.setText(d + "-" + month + "-" + year );
                }else {
                    date.setText(day + "-" + month + "-" + year);
                }
                date.setError(null);

            }
            else{
                Toasty.error(getActivity(),"past dates are not allowed",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            if(minute<10 && hourOfDay<10) {
                clock.setText("0"+Integer.toString(hourOfDay) + ":" +"0"+Integer.toString(minute)+":00");
            }
            else
            if(minute<10) {
                clock.setText(Integer.toString(hourOfDay) + ":" +"0"+Integer.toString(minute)+":00");
            }
            else if(hourOfDay<10){
                clock.setText("0"+Integer.toString(hourOfDay) + ":"+Integer.toString(minute)+":00");
            }
            else {
                clock.setText(hourOfDay+":"+minute+":00");
            }
            clock.setError(null);
        }
    }

    public static class NoOfPassengers extends  DialogFragment{
        private ImageView plus,minus;
        private ImageView passenger1,passenger2,passenger3,passenger4,passenger5,passenger6;
        private TextView total_text;
        Button ok;
        RelativeLayout relativeLayout;
        public NoOfPassengers(){

        }

        public static NoOfPassengers newInstance(int no){
            NoOfPassengers frag = new NoOfPassengers();
            Bundle args = new Bundle();
            args.putInt("details",no);
            frag.setArguments(args);
            return  frag;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
            return inflater.inflate(R.layout.passenger_dialog_fragment,container);
        }

        @Override

        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

            super.onViewCreated(view, savedInstanceState);
            plus = (ImageView) view.findViewById(R.id.plus_one);
            minus = (ImageView) view.findViewById(R.id.negative_one);
            relativeLayout =(RelativeLayout) view.findViewById(R.id.text_relative);
            passenger1 = (ImageView) view.findViewById(R.id.passenger_1);
            passenger2 = (ImageView) view.findViewById(R.id.passenger_2);
            passenger2.setVisibility(View.GONE);
            passenger3 = (ImageView) view.findViewById(R.id.passenger_3);
            passenger3.setVisibility(View.GONE);
            passenger4 = (ImageView) view.findViewById(R.id.passenger_4);
            passenger4.setVisibility(View.GONE);
            passenger5 = (ImageView) view.findViewById(R.id.passenger_5);
            passenger5.setVisibility(View.GONE);
            passenger6 = (ImageView) view.findViewById(R.id.passenger_6);
            passenger6.setVisibility(View.GONE);
            total_text = (TextView) view.findViewById(R.id.total_text);
            if(total==2){
                passenger2.setVisibility(View.VISIBLE);
            }else if(total ==3){
                passenger2.setVisibility(View.VISIBLE);
                passenger3.setVisibility(View.VISIBLE);
            }else if(total ==4){
                passenger2.setVisibility(View.VISIBLE);
                passenger3.setVisibility(View.VISIBLE);
                passenger4.setVisibility(View.VISIBLE);
            }else if(total == 5){
                passenger2.setVisibility(View.VISIBLE);
                passenger3.setVisibility(View.VISIBLE);
                passenger4.setVisibility(View.VISIBLE);
                passenger5.setVisibility(View.VISIBLE);
            }else if(total ==6){
                passenger2.setVisibility(View.VISIBLE);
                passenger3.setVisibility(View.VISIBLE);
                passenger4.setVisibility(View.VISIBLE);
                passenger5.setVisibility(View.VISIBLE);
                passenger6.setVisibility(View.VISIBLE);
            }

            total_text.setText(total+"");
            ok = (Button) view.findViewById(R.id.ok);
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (total==max){
                        Toasty.error(getActivity(),"Number of Outstation Taxi should be less then "+max,Toast.LENGTH_SHORT).show();
                    }else {
                        total++;
                        total_text.setText(total + "");
                        if(total==2) {
                            passenger2.setVisibility(View.VISIBLE);
                        }if(total==3){
                            passenger3.setVisibility(View.VISIBLE);
                        }if (total==4){
                            passenger4.setVisibility(View.VISIBLE);
                        }if(total==5){
                            passenger5.setVisibility(View.VISIBLE);
                        }if(total==6){
                            passenger6.setVisibility(View.VISIBLE);
                        }
                    }

                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (total==1){
                        Toasty.error(getActivity(),"Number of Outstation Taxi should be more then "+ 0,Toast.LENGTH_SHORT).show();
                    }else {
                        total--;
                        total_text.setText(total + "");
                        if(total==2) {
                            passenger3.setVisibility(View.GONE);
                        }if(total==3){
                            passenger4.setVisibility(View.GONE);
                        }if (total==1){
                            passenger2.setVisibility(View.GONE);
                        }if(total==4){
                            passenger5.setVisibility(View.GONE);
                        }if(total == 5){
                            passenger6.setVisibility(View.GONE);
                        }
                    }

                }
            });

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    passenger.setError(null);
                    passenger.setText(total+"");
                    getDialog().dismiss();
                    Intent intent=new Intent(getActivity(),EmployeeInfo.class);
                    intent.putExtra("no_of_employee",total+"");
                    getActivity().startActivityForResult(intent,EMPLOYEE_INFO);

                }
            });




        }
    }

    public static class NofDays extends  DialogFragment{
        private ImageView plus,minus;
        private ImageView days_icon,passenger2,passenger3,passenger4,passenger5,passenger6;
        private TextView total_text;
        Button ok;
        RelativeLayout relativeLayout;
        public NofDays(){

        }

        public static NofDays newInstance(int no){
            NofDays frag = new NofDays();
            Bundle args = new Bundle();
            args.putInt("details",no);
            frag.setArguments(args);
            return  frag;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
            return inflater.inflate(R.layout.no_of_days,container);
        }

        @Override

        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

            super.onViewCreated(view, savedInstanceState);
            plus = (ImageView) view.findViewById(R.id.plus_one);
            minus = (ImageView) view.findViewById(R.id.negative_one);
            relativeLayout =(RelativeLayout) view.findViewById(R.id.text_relative);
            days_icon = (ImageView) view.findViewById(R.id.days_icon);

            total_text = (TextView) view.findViewById(R.id.total_text);


            total_text.setText(nDays+"");
            ok = (Button) view.findViewById(R.id.ok);
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    nDays++;
                    total_text.setText(nDays + "");

                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(nDays==1){
                        Toasty.error(getActivity(),"Days Should be more then 0",Toast.LENGTH_SHORT).show();
                    }else {
                        nDays--;
                        total_text.setText(nDays + "");
                    }

                }
            });

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    days.setError(null);
                    days.setText(nDays+"");
                    getDialog().dismiss();

                }
            });




        }
    }






}
