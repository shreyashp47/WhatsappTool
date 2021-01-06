package com.taxivaxi.operator.fragment;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.taxivaxi.operator.R;
import com.taxivaxi.operator.activity.LoginActivity;
import com.taxivaxi.operator.viewmode.DriverInfoViewModel;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {


    ImageView profilePicture;
    ImageView editProfilePicture;
    View view;
    TextView driverName,account_name,account_no,account_ifsc_code;
    TextView driverContactNo;
    TextView driverEmailId;
    private static final int PICK_IMAGE = 100;
    DriverInfoViewModel driverInfoViewModel;
    TextView logout;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        profilePicture = view.findViewById(R.id.profile_pic);
        editProfilePicture = view.findViewById(R.id.edit_profile_pic);
        driverName = view.findViewById(R.id.driver_name);
        driverContactNo = view.findViewById(R.id.phone_no);
        driverEmailId = view.findViewById(R.id.email_id);
        logout = view.findViewById(R.id.logout);
        account_name = view.findViewById(R.id.account_name);
        account_no = view.findViewById(R.id.account_no);
        account_ifsc_code = view.findViewById(R.id.account_ifsc_code);
        logout.setOnClickListener(this);

        editProfilePicture.setOnClickListener(this);

        driverInfoViewModel = ViewModelProviders.of(getActivity()).get(DriverInfoViewModel.class);

        driverName.setText(driverInfoViewModel.getOperator().getValue().getOperatorName());
        if (driverInfoViewModel.getOperator().getValue().getContactNo() != null &&
                driverInfoViewModel.getOperator().getValue().getContactNo().length() > 0) {
            driverContactNo.setText(driverInfoViewModel.getOperator().getValue().getContactNo());
        } else {
            driverContactNo.setText("-Unavailable");
        }
        if (driverInfoViewModel.getOperator().getValue().getOperatorEmail() != null &&
                driverInfoViewModel.getOperator().getValue().getOperatorEmail().length() > 0) {
            driverEmailId.setText(driverInfoViewModel.getOperator().getValue().getOperatorEmail());
        } else {
            driverEmailId.setText("-Unavailable");
        }
        driverInfoViewModel.getLogout().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
        account_no.setText(driverInfoViewModel.getOperator().getValue().getBeneficiaryAccountNo());
        account_name.setText(driverInfoViewModel.getOperator().getValue().getBankName());
        account_ifsc_code.setText(driverInfoViewModel.getOperator().getValue().getIfscCode());

/*
        GlideApp
                .with(getActivity())
                .load("https://members.nationalgeographic.com/exposure/transcode/115/0/?url=https://amazonaws.com/avatar-file-5f1025020daa41339eda086f9bf992e6.jpg")
                .placeholder(R.drawable.ic_user)
                .error(R.drawable.action_button)
                .into(profilePicture);
*/

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_profile_pic:
                Log.d("Profile Pic", "Updated");
               /* Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);*/
                break;
            case R.id.logout:
                driverInfoViewModel.Logout();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_IMAGE:
                try {
                    InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    profilePicture.setImageBitmap(BitmapFactory.decodeStream(inputStream, new Rect(profilePicture.getMaxHeight(), profilePicture.getMaxWidth(), profilePicture.getMaxHeight(), profilePicture.getMaxWidth()), options));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
        }
    }
}
