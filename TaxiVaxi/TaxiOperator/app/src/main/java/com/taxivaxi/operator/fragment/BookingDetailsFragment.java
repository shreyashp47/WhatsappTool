package com.taxivaxi.operator.fragment;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.aditya.filebrowser.Constants;
import com.aditya.filebrowser.FileChooser;
import com.taxivaxi.operator.GsonStringConvertor;
import com.taxivaxi.operator.R;
import com.taxivaxi.operator.model.archivedbooking.Booking;
import com.taxivaxi.operator.model.archivedbooking.BookingApiResponse;
import com.taxivaxi.operator.model.archivedbooking.Passanger;
import com.taxivaxi.operator.retrofit.ConfigRetrofit;
import com.taxivaxi.operator.retrofit.UploadDutySlipAPI;
import com.taxivaxi.operator.viewmode.DriverInfoViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingDetailsFragment extends Fragment implements View.OnClickListener {
    DriverInfoViewModel driverInfoViewModel;
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
    TextView upload_documents, view_documents;
    TextView upload_documents1, view_documents1;
    TextView upload_documents2, view_documents2;
    TextView upload_documents3, view_documents3;
    TextView upload_documents4, view_documents4;

    Booking booking;
    Button upload;

    //FloatingActionButton floatingActionButton;

    View itemView;
    ProgressDialog progressDialog;
    AlertDialog.Builder builder;
    public static final int RequestPermissionCode = 1;

    public BookingDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        itemView = inflater.inflate(R.layout.fragment_details_booking, container, false);


        date = itemView.findViewById(R.id.travel_date);
        bookingId = itemView.findViewById(R.id.booking_id);
        pickupLocation = itemView.findViewById(R.id.pickup_location);
        dropLocation = itemView.findViewById(R.id.drop_location);
        noOfPassenger = itemView.findViewById(R.id.no_of_passenger);
        tripType = itemView.findViewById(R.id.trip_type);

        //  floatingActionButton=itemView.findViewById(R.id.start_trip_floating_button);
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
        upload_documents = itemView.findViewById(R.id.upload_documents);
        view_documents = itemView.findViewById(R.id.view_documents);
        upload_documents1 = itemView.findViewById(R.id.upload_documents1);
        view_documents1 = itemView.findViewById(R.id.view_documents1);
        upload_documents2 = itemView.findViewById(R.id.upload_documents2);
        view_documents2 = itemView.findViewById(R.id.view_documents2);
        upload_documents3 = itemView.findViewById(R.id.upload_documents3);
        view_documents3 = itemView.findViewById(R.id.view_documents3);
        upload_documents4 = itemView.findViewById(R.id.upload_documents4);
        view_documents4 = itemView.findViewById(R.id.view_documents4);
        upload = itemView.findViewById(R.id.upload_button);
        driverInfoViewModel = ViewModelProviders.of(this).get(DriverInfoViewModel.class);
        passengerLay1.setVisibility(View.GONE);
        passengerLay2.setVisibility(View.GONE);
        passengerLay3.setVisibility(View.GONE);
        passengerLay4.setVisibility(View.GONE);
        passengerLay5.setVisibility(View.GONE);
        passengerLay6.setVisibility(View.GONE);
        passengerDetails.setOnClickListener(this);
        upload_documents.setOnClickListener(this);
        view_documents.setOnClickListener(this);
        upload_documents1.setOnClickListener(this);
        view_documents1.setOnClickListener(this);
        upload_documents2.setOnClickListener(this);
        view_documents2.setOnClickListener(this);
        upload_documents3.setOnClickListener(this);
        view_documents3.setOnClickListener(this);
        upload_documents4.setOnClickListener(this);
        view_documents4.setOnClickListener(this);
        upload.setOnClickListener(this);
        //  floatingActionButton.setOnClickListener(this);

        booking = GsonStringConvertor.stringToGson(getArguments().getString("bookingInfo", "n"), Booking.class);
        setData();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);

        return itemView;
    }

    void setData() {
        date.setText(booking.getPickupDatetime());
        bookingId.setText(booking.getReferenceNo());
        pickupLocation.setText(booking.getPickupLocation());
        dropLocation.setText(booking.getDropLocation());
        tripType.setText(booking.getTourType());
        noOfPassenger.setText(booking.getPassangers().size() + "");

        if (booking.duty_slip != null && !booking.duty_slip.equals("")) {
            view_documents.setTextColor(Color.BLUE);
        } else {
            view_documents.setTextColor(Color.GRAY);
        }
        if (booking.duty_slip != null && !booking.duty_slip.equals("")) {
            view_documents1.setTextColor(Color.BLUE);
        } else {
            view_documents1.setTextColor(Color.GRAY);
        }
        if (booking.duty_slip != null && !booking.duty_slip.equals("")) {
            view_documents2.setTextColor(Color.BLUE);
        } else {
            view_documents2.setTextColor(Color.GRAY);
        }
        if (booking.duty_slip != null && !booking.duty_slip.equals("")) {
            view_documents3.setTextColor(Color.BLUE);
        } else {
            view_documents3.setTextColor(Color.GRAY);
        }
        if (booking.duty_slip != null && !booking.duty_slip.equals("")) {
            view_documents4.setTextColor(Color.BLUE);
        } else {
            view_documents4.setTextColor(Color.GRAY);
        }

    }

    Bitmap bitmap;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 2:
                    if (isInternetConnected(getActivity())) {
                      /*  Bitmap photo = (Bitmap) data.getExtras().get("data");
                        uploadDutySlip(photo);*/
                        try {

                            getRealPathFromURI(imageUri);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getActivity(), "NO INTERNET", Toast.LENGTH_LONG).show();
                    }
                    break;
                case 0:

                    Uri uri = data.getData();
                    getRealPathFromURI(uri);
                    break;
                case 3:
                    Uri file = data.getData();
                    path = file.getPath();
                    uploadDutySlip(getBase64FromPath(path));
                    break;
            }
        }
    }

    String path;

    public String getRealPathFromURI(Uri contentUri) {

        String[] proj = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, null, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
            uploadDutySlip(getBase64FromPath(path));
        }
        cursor.close();
        return path;
    }

    private boolean checkPermission(String permission) {
        int result = ContextCompat.checkSelfPermission(getActivity(), permission);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.upload_documents:
                selectFile();


                break;

            case R.id.view_documents:
                if (booking.duty_slip != null && !booking.duty_slip.equals("")) {

                    view_documents.setTextColor(Color.GREEN);
                    Uri webpage = Uri.parse(booking.duty_slip);
                    String string = booking.duty_slip;
                    if (!string.startsWith("http://") && !string.startsWith("https://")) {
                        webpage = Uri.parse(string);
                    }
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    getActivity().startActivity(browserIntent);
                } else {
                    Toast.makeText(getActivity(), "Duty Slip Not Uploaded", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.passenger_details:
                if (!isExpended) {
                    List<Passanger> passengers = new ArrayList<>();
                    passengers = booking.getPassangers();
                    isExpended = true;
                    switch (booking.getPassangers().size()) {
                        case 1:
                            passengerLay1.setVisibility(View.VISIBLE);
                            passengerName1.setText(passengers.get(0).getPeopleName());
                            passengerContact1.setText(passengers.get(0).getPeopleContact());
                            break;
                        case 2:
                            passengerLay1.setVisibility(View.VISIBLE);
                            passengerLay2.setVisibility(View.VISIBLE);
                            passengerName1.setText(passengers.get(0).getPeopleName());
                            passengerContact1.setText(passengers.get(0).peopleContact);
                            passengerName2.setText(passengers.get(1).peopleName);
                            passengerContact2.setText(passengers.get(1).peopleContact);
                            break;
                        case 3:
                            passengerLay1.setVisibility(View.VISIBLE);
                            passengerLay2.setVisibility(View.VISIBLE);
                            passengerLay3.setVisibility(View.VISIBLE);
                            passengerName1.setText(passengers.get(0).peopleName);
                            passengerContact1.setText(passengers.get(0).peopleContact);
                            passengerName2.setText(passengers.get(1).peopleName);
                            passengerContact2.setText(passengers.get(1).peopleContact);
                            passengerName3.setText(passengers.get(2).peopleName);
                            passengerContact3.setText(passengers.get(2).peopleContact);
                            break;
                        case 4:
                            passengerLay1.setVisibility(View.VISIBLE);
                            passengerLay2.setVisibility(View.VISIBLE);
                            passengerLay3.setVisibility(View.VISIBLE);
                            passengerLay4.setVisibility(View.VISIBLE);
                            passengerLay1.setVisibility(View.VISIBLE);
                            passengerLay2.setVisibility(View.VISIBLE);
                            passengerLay3.setVisibility(View.VISIBLE);
                            passengerName1.setText(passengers.get(0).peopleName);
                            passengerContact1.setText(passengers.get(0).peopleContact);
                            passengerName2.setText(passengers.get(1).peopleName);
                            passengerContact2.setText(passengers.get(1).peopleContact);
                            passengerName3.setText(passengers.get(2).peopleName);
                            passengerName4.setText(passengers.get(3).peopleName);
                            passengerContact4.setText(passengers.get(3).peopleContact);

                            break;
                        case 5:
                            passengerLay1.setVisibility(View.VISIBLE);
                            passengerLay2.setVisibility(View.VISIBLE);
                            passengerLay3.setVisibility(View.VISIBLE);
                            passengerLay4.setVisibility(View.VISIBLE);
                            passengerLay5.setVisibility(View.VISIBLE);

                            passengerName1.setText(passengers.get(0).peopleName);
                            passengerContact1.setText(passengers.get(0).peopleContact);
                            passengerName2.setText(passengers.get(1).peopleName);
                            passengerContact2.setText(passengers.get(1).peopleContact);
                            passengerName3.setText(passengers.get(2).peopleName);
                            passengerName4.setText(passengers.get(3).peopleName);
                            passengerContact4.setText(passengers.get(3).peopleContact);
                            passengerName5.setText(passengers.get(4).peopleName);
                            passengerContact5.setText(passengers.get(4).peopleContact);
                            break;
                        case 6:
                            passengerLay1.setVisibility(View.VISIBLE);
                            passengerLay2.setVisibility(View.VISIBLE);
                            passengerLay3.setVisibility(View.VISIBLE);
                            passengerLay4.setVisibility(View.VISIBLE);
                            passengerLay5.setVisibility(View.VISIBLE);
                            passengerLay6.setVisibility(View.VISIBLE);

                            passengerName1.setText(passengers.get(0).peopleName);
                            passengerContact1.setText(passengers.get(0).peopleContact);
                            passengerName2.setText(passengers.get(1).peopleName);
                            passengerContact2.setText(passengers.get(1).peopleContact);
                            passengerName3.setText(passengers.get(2).peopleName);
                            passengerName4.setText(passengers.get(3).peopleName);
                            passengerContact4.setText(passengers.get(3).peopleContact);
                            passengerName5.setText(passengers.get(4).peopleName);
                            passengerContact5.setText(passengers.get(4).peopleContact);
                            passengerName6.setText(passengers.get(5).peopleName);
                            passengerContact6.setText(passengers.get(5).peopleContact);
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

        }
    }

    Uri imageUri;

    void selectFile() {

        if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ) {
            final Dialog dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.option_selection_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


            Button imagebtn = dialog.findViewById(R.id.imagebtn);

            Button camera = dialog.findViewById(R.id.camera);
            Button other = dialog.findViewById(R.id.other);
            TextView cancelbtn = dialog.findViewById(R.id.cancelbtn);


            cancelbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (checkPermission(Manifest.permission.CAMERA)) {
                       /* checkFolder();
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, 2);*/
                        ContentValues values = new ContentValues();
                        values.put(MediaStore.Images.Media.TITLE, new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
                        imageUri = getActivity().getContentResolver().insert(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, 2);
                     /*   String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

                        File file = new File(Environment.getExternalStorageDirectory(), "/TaxiOperator/" + timeStamp + ".png");
                        imageUri = Uri.fromFile(file);

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, 2);*/
                        dialog.dismiss();

                    } else {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.CAMERA,},
                                1);
                        Toast.makeText(getActivity(), "Check Permission", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            other.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i2 = new Intent(getActivity(), FileChooser.class);
                    i2.putExtra(Constants.SELECTION_MODE, Constants.SELECTION_MODES.SINGLE_SELECTION.ordinal());
                    startActivityForResult(i2, 3);
                    dialog.dismiss();

                }
            });


            imagebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    //  startActivityForResult(intent, REQUEST_IMAGE);
                    try {
                        startActivityForResult(Intent.createChooser(intent, "Select picture"), 0);
                    } catch (android.content.ActivityNotFoundException ex) {
                        // Potentially direct the user to the Market with a Dialog
                        Toast.makeText(getActivity(), "Please install a File Manager.",
                                Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            });
            dialog.show();
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);

            Toast.makeText(getActivity(), "Check Permission", Toast.LENGTH_SHORT).show();
        }

    }

    public static boolean isInternetConnected(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo[] = cm.getAllNetworkInfo();

        int i;
        for (i = 0; i < networkInfo.length; ++i) {
            if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;

    }

    public void EnableRuntimePermissionToAccessCamera() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.CAMERA)) {

            // Printing toast message after enabling runtime permission.
            Toast.makeText(getActivity(), "CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {
        switch (RC) {
            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(),
                            "Permission Granted, Now your application can access CAMERA.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(),
                            "Permission Canceled, Now your application cannot access CAMERA.",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public static String getBase64FromPath(Bitmap bitmap) {
        String base64 = "";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

        return base64;
    }

    public static String getBase64FromPath(String path) {
        String base64 = "";
        try {/*from   w w w .  ja  va  2s  .  c om*/
            File file = new File(path);
            byte[] buffer = new byte[(int) file.length() + 100];
            @SuppressWarnings("resource")
            int length = new FileInputStream(file).read(buffer);
            base64 = Base64.encodeToString(buffer, 0, length,
                    Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }

    void uploadDutySlip(Bitmap bitmap) {

        progressDialog.setMessage("Uploading File");
        progressDialog.show();
        UploadDutySlipAPI uploadDutySlipAPI = ConfigRetrofit.configRetrofit(UploadDutySlipAPI.class);
        Call<BookingApiResponse> call = uploadDutySlipAPI.uploadDutySlip(driverInfoViewModel.getAccessToken().getValue(), booking.referenceNo,
                getBase64FromPath(bitmap), "jpg");
        call.enqueue(new Callback<BookingApiResponse>() {
            @Override
            public void onResponse(Call<BookingApiResponse> call, Response<BookingApiResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                    if (response.body() != null) {
                        getActivity().onBackPressed();
                    }
            }

            @Override
            public void onFailure(Call<BookingApiResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }

    void uploadDutySlip(String base46) {


        if (isValidExtension(path).equals("ok")) {
            progressDialog.setMessage("Uploading File");
            progressDialog.show();
            UploadDutySlipAPI uploadDutySlipAPI = ConfigRetrofit.configRetrofit(UploadDutySlipAPI.class);
            Call<BookingApiResponse> call = uploadDutySlipAPI.uploadDutySlip(driverInfoViewModel.getAccessToken().getValue(), booking.referenceNo,
                    base46, path.substring(path.lastIndexOf(".") + 1));
            call.enqueue(new Callback<BookingApiResponse>() {
                @Override
                public void onResponse(Call<BookingApiResponse> call, Response<BookingApiResponse> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful())
                        if (response.body() != null) {
                            getActivity().onBackPressed();
                        }
                }

                @Override
                public void onFailure(Call<BookingApiResponse> call, Throwable t) {
                    progressDialog.dismiss();

                }
            });
        } else Toast.makeText(getActivity(), isValidExtension(path), Toast.LENGTH_SHORT).show();

    }

    String isValidExtension(String string) {
        String[] extList = {"jpg","JPG", "png", "jpeg", "eps", "ico", "bmp",
                "pdf", "pptx", "ppt", "rtf",
                "doc", "docx", "txt",
                "xls", "ods", "xmls", "xlsx"};
        File file = new File(string);
        if (Arrays.asList(extList).contains(string.substring(string.lastIndexOf(".") + 1)))
            return "ok";
        else
            return "The uploaded file format is not allowed";

         /*       && Integer.parseInt(String.valueOf((file.length() / 1024) / 1024)) < 2) {
            return "ok";
        } else {
            if (!Arrays.asList(extList).contains(string.substring(string.lastIndexOf(".") + 1)))
                return "The uploaded file format is not allowed";
            if (Integer.parseInt(String.valueOf((file.length() / 1024) / 1024)) >= 2)
                return "File upto 2 MB is allowed";
        }

        return "";*/
    }

}
