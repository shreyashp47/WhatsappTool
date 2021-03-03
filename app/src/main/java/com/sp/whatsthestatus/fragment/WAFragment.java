package com.sp.whatsthestatus.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sp.whatsthestatus.R;
import com.sp.whatsthestatus.adapter.ViewPagerWAAdapter;
import com.sp.whatsthestatus.fragment.WAImageFragment;
import com.sp.whatsthestatus.fragment.WAVideoFragment;


public class WAFragment extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;

    public WAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_wa, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.viewPager_wa);
        tabLayout =   v.findViewById(R.id.tab_layout_wa);
        viewPager.setOffscreenPageLimit(2);

        ViewPagerWAAdapter adapter = new ViewPagerWAAdapter(getChildFragmentManager());
        adapter.addTabs("Images",new WAImageFragment());
        adapter.addTabs("Videos",new WAVideoFragment());
        adapter.addTabs("Saved",new WASavedFragment());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }

}
