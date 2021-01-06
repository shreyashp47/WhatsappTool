package com.taxivaxi.agent.fragment;


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

import com.taxivaxi.agent.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {


    ImageView profilePicture;
    ImageView editProfilePicture;
    View view;
     TextView driverContactNo;
    TextView driverEmailId;
    private static final int PICK_IMAGE = 100;

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

        driverContactNo = view.findViewById(R.id.phone_no);
        driverEmailId = view.findViewById(R.id.email_id);
        logout = view.findViewById(R.id.logout);


        logout.setOnClickListener(this);

        editProfilePicture.setOnClickListener(this);



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
