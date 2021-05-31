package com.trinket.corporate.gifting.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.trinket.corporate.gifting.R;

public class CartFragment extends Fragment {

    View view;
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cart, container, false);
        toolbar = view.findViewById(R.id.topAppBar);
        setHasOptionsMenu(false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        return view;
    }
}
