package com.taxivaxi.thetrinketadmin.fragment;

import android.app.FragmentManager;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taxivaxi.thetrinketadmin.R;

public class ProductDetailFragment extends DialogFragment {


    public ProductDetailFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    public void show(FragmentManager fragmentManager, String product_details) {
    }
}