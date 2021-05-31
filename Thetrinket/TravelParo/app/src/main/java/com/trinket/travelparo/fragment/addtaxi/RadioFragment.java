package com.taxivaxi.spoc.BookingForm;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.rey.material.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.rey.material.widget.Button;

import com.taxivaxi.spoc.Constant;
import com.taxivaxi.spoc.PlacesFieldSelector;
import com.taxivaxi.spoc.R;
import com.taxivaxi.spoc.model.allcitiesmodel.City;
import com.taxivaxi.spoc.model.assesmentcitymodel.AssCity;
import com.taxivaxi.spoc.model.assesmentcodemodel.AssCode;
import com.taxivaxi.spoc.retrofit.ConfigRetrofit;
import com.taxivaxi.spoc.utility.GsonStringConvertor;
import com.taxivaxi.spoc.viewmodels.AllCitiesViewModel;
import com.taxivaxi.spoc.viewmodels.AssessmentCitiesViewModel;
import com.taxivaxi.spoc.viewmodels.AssessmentCodeViewModel;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


public class RadioFragment extends Fragment implements  GoogleApiClient.OnConnectionFailedListener
        ,LocationListener,GoogleApiClient.ConnectionCallbacks{
    private static final int PICKUP_CODE_AUTOCOMPLETE = 11,EMPLOYEE_INFO=1100;
    private static final int DROP_CODE_AUTOCOMPLETE = 10;
    static ArrayAdapter<String> arrayAdapter;
    private TextView pickup;
    public static final String TAG = "RadioFragment";
    private TextView drop;
    static TextView clock,date;
    Toolbar toolbar;
    static TextView radio_passenger;
    SearchableSpinner assesmentspinner;
    EditText reason_for_booking;
    static SharedPreferences employeepref,assesmentpref,loginpref;
    static ArrayList<String> employee_idlist,getEmployee_idphno,employee_id_value;
    static String assesmentvalue,selected_assementcode;
    static int total =1;
    protected GoogleApiClient mlocationApiClient;
    protected Location mLastLocation;
    double lat=0.1;
    double lng;
    String city;
    String finalAddress;
    static String date_send;
    public static final String ARG_PAGE = "ARG_PAGE";
    SearchableSpinner assessmentCitySpinner,pickupCitySpinner;
    ArrayAdapter<String> pickUpCityAdapter,assesmentadapter,assessmentCitiesAdapter;
    String radio;
    View view;
    AllCitiesViewModel allCitiesViewModel;
    AssessmentCodeViewModel assessmentCodeViewModel;
    AssessmentCitiesViewModel assessmentCitiesViewModel;
    ArrayList<String> allCitiesNames,allCitiesId,assesmentlist,assessmentCityList,assessmentCityIdList;
    RelativeLayout assessmentLayout;
    String hasSingleEmployee;
    RectangularBounds bounds = RectangularBounds.newInstance(
            new LatLng(-40,-168),new LatLng(71,136));

    Context context;
    public static RadioFragment newInstance(int pageNo) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        RadioFragment fragment = new RadioFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceStat) {
        setRetainInstance(true);
        loginpref = getActivity().getSharedPreferences("login_info", MODE_PRIVATE);

        radio = loginpref.getString("is_radio", null);
        hasSingleEmployee=loginpref.getString("has_single_employee",null);
        getActivity().setTitle("Taxi");
        buildGoogleApiClient();

        assessmentCodeViewModel= ViewModelProviders.of(getActivity()).get(AssessmentCodeViewModel.class);

        context=getContext();
        if (!Places.isInitialized()) {
            Places.initialize(context, getResources().getString(R.string.places_api_key_part1)+
                    getResources().getString(R.string.places_api_key_part2)+
                    getResources().getString(R.string.places_api_key_part3)
            );

        }
        final PlacesFieldSelector fieldSelector = new PlacesFieldSelector();

        allCitiesViewModel=ViewModelProviders.of(getActivity()).get(AllCitiesViewModel.class);
        allCitiesViewModel.initAllCitiesViewModel(loginpref.getString("token","n"),loginpref.getString("admin_id","n"));



        assessmentCitiesViewModel=ViewModelProviders.of(getActivity()).get(AssessmentCitiesViewModel.class);

        if(radio != null&&radio.equals("1")) {
   // SharedPreferences loginpref=getActivity().getSharedPreferences("login_info",Context.MODE_PRIVATE);

            if (view==null) {
                view = inflater.inflate(R.layout.content_radio, container, false);
                assesmentspinner = (SearchableSpinner) view.findViewById(R.id.assessnment);
                assessmentLayout=(RelativeLayout)view.findViewById(R.id.assessment_layout);
                pickupCitySpinner=(SearchableSpinner) view.findViewById(R.id.radio_pickup_city);
                assessmentCitySpinner=(SearchableSpinner)view.findViewById(R.id.assessment_city);

                assesmentlist = new ArrayList<>();
                allCitiesNames=new ArrayList<>();
                allCitiesId=new ArrayList<>();
                assessmentCityIdList=new ArrayList<>();
                assessmentCityList=new ArrayList<>();

                assesmentadapter = new ArrayAdapter<>(getActivity(), R.layout.no_of_passenger_spinner_layout, assesmentlist);
                pickUpCityAdapter=new ArrayAdapter<>(getActivity(), R.layout.no_of_passenger_spinner_layout,allCitiesNames);
                assessmentCitiesAdapter=new ArrayAdapter<>(getActivity(), R.layout.no_of_passenger_spinner_layout,assessmentCityList);

                if (loginpref.getString("has_assessment_codes","n").equals("0")){
                    assessmentLayout.setVisibility(View.GONE);
                }
                else {
                    assessmentCodeViewModel.initAssessmentCode(loginpref.getString("token", "n"), loginpref.getString("admin_id", "n"));
                    assessmentCitiesViewModel.initViewModel(loginpref.getString("token", "n"), loginpref.getString("admin_id", "n"));
                    assesmentspinner.setAdapter(assesmentadapter);
                    assesmentspinner.setTitle("Assessment Code");
                    assessmentCodeViewModel.getAssessmentCodeInfo(loginpref.getString("token", "n"), loginpref.getString("admin_id", "n")).observe(this, new Observer<List<AssCode>>() {
                        @Override
                        public void onChanged(@Nullable List<AssCode> assCodes) {
                            Log.d("Assessment_code", GsonStringConvertor.gsonToString(assCodes));
                            assesmentlist.clear();
                            assesmentlist.add("As.Code");
                            if (assCodes != null) {
                                for (int i = 0; i < assCodes.size(); i++) {
                                    assesmentlist.add(assCodes.get(i).assessmentCode);
                                }
                            }
                            assesmentadapter.notifyDataSetChanged();
                        }
                    });

                    assesmentspinner.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.d("radio_Activity", assesmentadapter.getCount() + "");
                            if (assesmentadapter.getCount() == 0) {
                                Toast.makeText(getActivity(), "Assessment Code list is empty ", Toast.LENGTH_SHORT).show();
                            }
                            return false;
                        }
                    });

                    assessmentCitySpinner.setAdapter(assessmentCitiesAdapter);
                    assessmentCitiesViewModel.getLiveAssessmentCityList(loginpref.getString("token", "n"), loginpref.getString("admin_id", "n"))
                            .observe(this, new Observer<List<AssCity>>() {
                        @Override
                        public void onChanged(@Nullable List<AssCity> cities) {
                            Log.d("assessment cities", GsonStringConvertor.gsonToString(cities));
                            assessmentCityList.clear();
                            assessmentCityIdList.clear();
                            assessmentCityIdList.add("Assessment City Id");
                            assessmentCityList.add("As.City");
                            if (cities != null) {
                                for (int i = 0; i < cities.size(); i++) {
                                    assessmentCityList.add(cities.get(i).name);
                                    assessmentCityIdList.add(cities.get(i).id);
                                }
                            }

                            assessmentCitiesAdapter.notifyDataSetChanged();
                        }
                    });
                }



                pickupCitySpinner.setTitle("Select City");
                pickupCitySpinner.setAdapter(pickUpCityAdapter);
                allCitiesViewModel.getHotelCitiesLiveData(loginpref.getString("token","n"),loginpref.getString("admin_id","n")).observe(this, new Observer<List<City>>() {
                    @Override
                    public void onChanged(@Nullable List<City> cities) {

                        Log.d("All Cities",GsonStringConvertor.gsonToString(cities));
                        allCitiesId.clear();
                        allCitiesNames.clear();
                        allCitiesNames.add("Select City");
                        allCitiesId.add("Select City Id");
                        if (cities!=null) {
                            for (int i = 0; i < cities.size(); i++) {
                                allCitiesNames.add(cities.get(i).name);
                                allCitiesId.add(cities.get(i).id);
                            }
                        }
                        pickUpCityAdapter.notifyDataSetChanged();
                    }
                });




                pickup = (TextView) view.findViewById(R.id.radio_pickup);
                drop = (TextView) view.findViewById(R.id.radio_drop);

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
                clock = (TextView) view.findViewById(R.id.radio_time);
                clock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogFragment timePickerFragment = new TimePickerFragment();
                        timePickerFragment.show(getChildFragmentManager(), "timePicker");
                    }
                });

                date = (TextView) view.findViewById(R.id.radio_date);
                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogFragment datePickerFragment = new DatePickerFragment();
                        datePickerFragment.show(getChildFragmentManager(), "datePicker");
                    }
                });

                radio_passenger = (TextView) view.findViewById(R.id.passengers);
                if (hasSingleEmployee.equals("1")){
                    radio_passenger.setText("Self");
                    radio_passenger.setTextColor(ContextCompat.getColor(getContext(),R.color.taxi_vaxi_color_light_gray));
                    radio_passenger.setEnabled(false);
                }
                radio_passenger.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogFragment passengerPicker = new NoOfPassengers();
                        passengerPicker.show(getChildFragmentManager(), "no of radio_passenger");

                    }
                });
    /*    assement = (TextView) findViewById(R.id.assement);
        assement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/


                employee_idlist = new ArrayList<String>();
                reason_for_booking = (EditText) view.findViewById(R.id.radio_reason_for_booking);
                android.widget.Button bookbutton= (android.widget.Button) view.findViewById(R.id.radio_submit_button);
                bookbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isInternetConnected(getActivity())) {


                            if (pickup.getText().toString().length() < 1
                                    || drop.getText().toString().length() < 1
                                    || date.getText().toString().length() < 1
                                    || clock.getText().toString().length() < 1
                                    || (hasSingleEmployee.equals("0")&&employee_idlist.size() == 0)
                                    || reason_for_booking.getText().toString().length() < 1
                                    ||(!loginpref.getString("has_assessment_codes","n").equals("0") && assesmentspinner.getSelectedItemPosition()==0)
                                    ||((!loginpref.getString("has_assessment_codes","n").equals("0") && assessmentCitySpinner.getSelectedItemPosition()==0))) {
                                Toasty.error(getActivity(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                                if (pickup.getText().toString().length() < 1) {
                                    pickup.setError("field required");
                                }
                                if (drop.getText().toString().length() < 1) {
                                    drop.setError("field required");
                                }
                                if (date.getText().toString().length() < 1) {
                                    date.setError("field required");
                                }
                                if (clock.getText().toString().length() < 1) {
                                    clock.setError("field required");
                                }
                                if (reason_for_booking.getText().toString().length() < 1) {
                                    reason_for_booking.setError("field required");
                                }
                                if (hasSingleEmployee.equalsIgnoreCase("0") && employee_idlist.size() == 0) {
                                    radio_passenger.setError("field required");
                                    //Toast.makeText(getActivity(), "please choose Employee names", Toast.LENGTH_SHORT).show();
                                }
                                if (!loginpref.getString("has_assessment_codes","n").equals("0")){
                                    Log.d("Assessment Code In",loginpref.getString("has_assessment_codes","n"));
                                    if (assesmentspinner.getSelectedItemPosition()==0){
                                        ((android.widget.TextView)assesmentspinner.getSelectedView()).setError("field required");
                                    }
                                    if (assessmentCitySpinner.getSelectedItemPosition()==0){
                                        ((android.widget.TextView)assessmentCitySpinner.getSelectedView()).setError("field required");
                                    }
                                }
                                else {
                                    Log.d("Assessment Code Out",loginpref.getString("has_assessment_codes","n"));
                                }


                            } else {

                                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                                ad.setTitle("COMPLETE RADIO BOOKING");
                                ad.setMessage("Proceed To Complete Booking");
                                ad.setCancelable(false);
                                ad.setPositiveButton("proceed", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.d("Booking Access_token",loginpref.getString("token", "n"));


                                        if (loginpref.getString("has_assessment_codes", "n").equals("0")) {
                                            new doRadioBooking().execute(loginpref.getString("token", "n"),
                                                    loginpref.getString("admin_id", "n"),
                                                    loginpref.getString("group_id", "n"),
                                                    "0",
                                                    " ",
                                                    reason_for_booking.getText().toString(),
                                                    total + "", pickup.getText().toString(),
                                                    drop.getText().toString(),
                                                    date_send,
                                                    clock.getText().toString(),
                                                    loginpref.getString("subgroup_id", "n"),
                                                    " ",
                                                    " ",
                                                    hasSingleEmployee);

                                        } else {
                                            Log.d("selected_assementcode", assesmentspinner.getSelectedItem().toString());
                                            new doRadioBooking().execute(loginpref.getString("token", "n"),
                                                    loginpref.getString("admin_id", "n"),
                                                    loginpref.getString("group_id", "n"),
                                                    "0",
                                                    assesmentspinner.getSelectedItem().toString(),
                                                    reason_for_booking.getText().toString(),
                                                    total + "",
                                                    pickup.getText().toString(),
                                                    drop.getText().toString(),
                                                    date_send,
                                                    clock.getText().toString(),
                                                    loginpref.getString("subgroup_id", "n"),
                                                    allCitiesId.get(pickupCitySpinner.getSelectedItemPosition()),
                                                    assessmentCitySpinner.getSelectedItem().toString(),
                                                    hasSingleEmployee);
                                        }
                                    }
                                });
                                ad.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                ad.show();
                            }
                        } else {
                            dialog = new AlertDialog.Builder(getActivity());
                            dialog.setMessage("Internet Connection Unavailable");
                            dialog.setTitle("Connection Error");
                            dialog.show();
                        }
                    }
                });
            }
            return view;
}
        view = inflater.inflate(R.layout.not_avialble, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text_preview);
        textView.setText("Radio Cab Service not available for your account");

        return view;



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
    @Override
    public void onPause() {
        Log.d("onPause","zdfsa");

        super.onPause();
    }

    @Override
    public void onResume() {
        Log.d("onResume","zdfsa");

        super.onResume();
    }
    /*private void openAutocompleteActivity(int which) {
        try {
            // The autocomplete activity requires Google Play Services to be available. The intent
            // builder checks this and throws an exception if it is not the case.
            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setCountry("IN")
                    .build();
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .setFilter(typeFilter)
                    .build(this);
            startActivityForResult(intent, which);
        } catch (GooglePlayServicesRepairableException e) {
            // Indicates that Google Play Services is either not installed or not up to date. Prompt
            // the user to correct the issue.
            GoogleApiAvailability.getInstance().getErrorDialog(this, e.getConnectionStatusCode(),
                    0 ).show();
        } catch (GooglePlayServicesNotAvailableException e) {
            // Indicates that Google Play Services is not available and the problem is not easily
            // resolvable.
            String message = "Google Play Services is not available: " +
                    GoogleApiAvailability.getInstance().getErrorString(e.errorCode);

            Log.e(TAG, message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }*/

    protected synchronized void buildGoogleApiClient() {
        mlocationApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Do your stuff
    }
    @Override
    public void onConnected(Bundle connectionHint) {
        Log.d("Status","Inside onConnected");
        if(checkLocationPermission())
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mlocationApiClient);
        Log.d("Status","Inside onPermissionGranted");
        if (mLastLocation != null && isInternetConnected(getActivity())) {
            Log.d(mLastLocation.getLatitude()+"",mLastLocation.getLongitude()+"");
            //Log.d("current city",GlobalData.getInstance().getCurrentCity());
           // Toast.makeText(getActivity(),mLastLocation.getLatitude()+""+ mLastLocation.getLongitude(), Toast.LENGTH_LONG).show();
            lat= mLastLocation.getLatitude();
            lng=mLastLocation.getLongitude();
            Log.d("Location",lat+" "+lng);
            Geocoder geoCoder = new Geocoder(getActivity(), Locale.getDefault());
            try{
            List<Address> addresses = geoCoder.getFromLocation(lat, lng, 1);
            if (addresses != null && addresses.size() > 0) {
                Log.d("city",addresses.get(0).getLocality());
                city=addresses.get(0).getLocality();
            }}catch (IOException e){
                e.printStackTrace();
            }
            StringBuilder builder = new StringBuilder();
            try {
                List<Address> address = geoCoder.getFromLocation(lat, lng, 1);
                int maxLines = address.get(0).getMaxAddressLineIndex();
                for (int i=0; i<maxLines; i++) {
                    String addressStr = address.get(0).getAddressLine(i);
                    builder.append(addressStr);
                    builder.append(" ");
                }

                finalAddress = builder.toString(); //This is the complete address.
                //This will display the final address.
                Log.d("address",finalAddress);
                //currentLocation.setText(finalAddress);
               /* if(!currentLocation.getText().equals("Not available")){
                    currentLocation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(LocationSetter.this,GetLocation.class);
                            //intent.putExtra("LOCATION",currentLocation.getText());
                            //setResult(RESULT_OK,intent);
                            //finish();
                            startActivity(intent);
                        }
                    });
                }*/
            } catch (IOException e) {
                Log.d("Status","Inside LocationUnavailable");
                city="Unavailable";
                finalAddress="Unavailable";
                Log.e("Geocoder","Error"+e.getMessage());
            } catch (NullPointerException e) {
                Log.e("Geocoder","E"+ e.getMessage());
                // Handle NullPointerException
                city="Unavailable";
                finalAddress="Current Location Unavailable";
            }
        } else {
            city="Unavailable";
            finalAddress="Unavailable";
            lat=73.00;
           // Toast.makeText(getActivity(), "There is Problem in detecting your Location", Toast.LENGTH_LONG).show();
        }
    }
    private String getCityNameByCoordinates(double lat, double lon) throws IOException {


        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        //You had this as int. It is advised to have Lat/Loing as double.



    }
    @Override
    public void onProviderEnabled(String provider) {
       /* Toast.makeText(getActivity(), "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();
*/
    }

    @Override
    public void onProviderDisabled(String provider) {
        /*Toast.makeText(getActivity(), "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {


    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i("location", "Connection suspended");
        mlocationApiClient.connect();
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.e("location_activity", "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());

        // TODO(Developer): Check error code and notify the user of error state and resolution.
        Toast.makeText(getActivity(),
                "Network Error Try Restarting  App  " + connectionResult.getErrorCode(),
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onStart() {
        super.onStart();
        mlocationApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mlocationApiClient.isConnected()) {
            mlocationApiClient.disconnect();
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

            }/*else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                //Status status = PlaceAutocomplete.getStatus(this, data);
                //Log.e(TAG, "Error: Status = " + status.toString());
            }*/ else if (resultCode == RESULT_CANCELED) {
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

            }/* else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                Log.e(TAG, "Error: Status = " + status.toString());
            }*/ else if (resultCode == RESULT_CANCELED) {
                // Indicates that the activity closed before a selection was made. For example if
                // the user pressed the back button.
            }
        }else if (requestCode ==EMPLOYEE_INFO){
           // Log.d("employeee", Arrays.toString(employeeIdList.toArray()));

            if(resultCode == RESULT_OK){
               // radio_passenger.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.checkbox_on_background , 0);
                employee_idlist=data.getStringArrayListExtra("employee_id");
                getEmployee_idphno=data.getStringArrayListExtra("employee_phno");
                employee_id_value=data.getStringArrayListExtra("employeeIdValue");
                Log.d("employeee", Arrays.toString(employee_idlist.toArray()));
                Log.d("phone", Arrays.toString(getEmployee_idphno.toArray()));
                Log.d("employeee", Arrays.toString(employee_id_value.toArray()));

            }

        }
    }


    protected  class doRadioBooking extends AsyncTask<String,Integer,String>{
        String apiURL = Constant.createbooking;
        Response response;
        OkHttpClient client = ConfigRetrofit.configOkhttp();
        RequestBody formBody;


        @Override
        protected String doInBackground(String... params) {

            Log.d("radio_access_toke ",params[0]);
            Log.d("pick_Up_city",params[12]);
            Log.d("assessment_cit",params[13]);
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
                            .add("employee_contact_1", " ")
                            .add("pickup_location", params[7])
                            .add("drop_location", params[8])
                            .add("pickup_datetime", params[9] + " , " + params[10])
                            .add("subgroup_id", params[11])
                            .add("city_id",params[12])
                            .add("assessment_city",params[13])
                            .add("has_single_employee",params[14])
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
                        .add("drop_location", params[8])
                        .add("pickup_datetime", params[9] + " , " + params[10])
                        .add("subgroup_id", params[11])
                        .add("city_id",params[12])
                        .add("assessment_city",params[13])
                        .add("has_single_employee",params[14])
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
                        .add("drop_location", params[8])
                        .add("pickup_datetime", params[9] + " , " + params[10])
                        .add("subgroup_id", params[11])
                        .add("city_id",params[12])
                        .add("assessment_city",params[13])
                        .add("has_single_employee",params[14])
                        .build();
                    Log.e("id1",employee_id_value.get(0));
                    Log.e("id2",employee_id_value.get(1));
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
                        .add("drop_location", params[8])
                        .add("pickup_datetime", params[9] + " , " + params[10])
                        .add("subgroup_id", params[11])
                        .add("city_id",params[12])
                        .add("assessment_city",params[13])
                        .add("has_single_employee",params[14])
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
                        .add("drop_location", params[8])
                        .add("pickup_datetime", params[9] + " , " + params[10])
                        .add("subgroup_id", params[11])
                        .add("city_id",params[12])
                        .add("assessment_city",params[13])
                        .add("has_single_employee",params[14])
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
                        .add("drop_location", params[8])
                        .add("pickup_datetime", params[9] + " , " + params[10])
                        .add("subgroup_id", params[11])
                        .add("city_id",params[12])
                        .add("assessment_city",params[13])
                        .add("has_single_employee",params[14])
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
                        .add("drop_location", params[8])
                        .add("pickup_datetime", params[9] + " , " + params[10])
                        .add("subgroup_id", params[11])
                        .add("city_id",params[12])
                        .add("assessment_city",params[13])
                        .add("has_single_employee",params[14])
                        .build();
                    break;

            }
            if(formBody==null){
                Log.e("enull","enull");
            }

            Request.Builder request = new Request.Builder();
            request.url(apiURL)
                    .post(formBody)
                    .build();
            try {
                response = client.newCall(request.build()).execute();
                assesmentvalue = response.body().string();
                Log.e("bkr",assesmentvalue);
                return assesmentvalue;

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
            d=new AlertDialog.Builder(getActivity());
            pd = new ProgressDialog(getActivity());
            pd.setMessage("completing booking");
            total=1;
            pd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            if(response!=null){
                pd.dismiss();
                response.close();
            }

            if(s.equals("Network Error"))
            {   d.setTitle("CONNECTION ERROR");
                d.setMessage("Unable To Complete Booking");
                d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                d.show();
            }
            else {
                if(s.length()==0){
                    Log.e("bookingResponse", "nul");
                }

                JSONObject json = (JSONObject) JSONValue.parse(s);
                Log.e("bookingResponse", s);
                if (json.get("success").equals("1")) {
                    JSONObject response = (JSONObject) json.get("response");
                    d.setMessage("Your Booking ID is : "+response.get("BookingId").toString());
                    Toasty.success(getActivity(),"Success",Toast.LENGTH_SHORT).show();
                    d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        /*    pickuplocation.setText("");
                            droplocation.setText("");
                            clock.setText("");
                            date.setText("");
                            noofemployeespinner.setSelection(0);
                            employeename.setText("");
                            assessmentspinner.setSelection(0);
                            reasonforbooking.setText("");*/

                            Intent intent=new Intent(getActivity(),getActivity().getClass());
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                        }
                    });

                    d.setTitle("BOOKING SUCCESSFUL");
                    d.show();
                }
                else {
                    d.setTitle("BOOKING ERROR");
                    d.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    d.setMessage(json.get("error").toString());

                    d.show();
                }
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
            }            passenger5 = (ImageView) view.findViewById(R.id.passenger_5);
            passenger5.setVisibility(View.GONE);
            passenger6 = (ImageView) view.findViewById(R.id.passenger_6);
            passenger6.setVisibility(View.GONE);
            total_text.setText(total+"");
            ok = (Button) view.findViewById(R.id.ok);
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (total==4){
                        Toasty.error(getActivity(),"Number of Radio Passenger should be less then four",Toast.LENGTH_SHORT).show();
                    }else {
                        total++;
                        total_text.setText(total + "");
                        if(total==2) {
                            passenger2.setVisibility(View.VISIBLE);
                        }if(total==3){
                            passenger3.setVisibility(View.VISIBLE);
                        }if (total==4){
                            passenger4.setVisibility(View.VISIBLE);
                        }

                    }
                    //Todo

                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (total==1){
                        Toasty.error(getActivity(),"Number of Radio Passenger should be more then Zero",Toast.LENGTH_SHORT).show();
                    }else {
                        total--;
                        total_text.setText(total + "");
                        if(total==2) {
                            passenger3.setVisibility(View.GONE);
                        }if(total==3){
                            passenger4.setVisibility(View.GONE);
                        }if (total==1){
                            passenger2.setVisibility(View.GONE);
                        }
                    }

                }
            });

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radio_passenger.setError(null);
                    radio_passenger.setText(total+"");
                    getDialog().dismiss();
                    Intent intent=new Intent(getActivity(),EmployeeInfo.class);
                    intent.putExtra("no_of_employee",total+"");
                    getActivity().startActivityForResult(intent,EMPLOYEE_INFO);

                }
            });




        }
    }


}
