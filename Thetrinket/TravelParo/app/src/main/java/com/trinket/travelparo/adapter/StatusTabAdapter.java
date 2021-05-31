package com.trinket.travelparo.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class StationsAdapter extends FragmentStatePagerAdapter {

    int tabcounts;

    StationsAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        tabcounts = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CurrentTaxiBooking();
            case 1:
                return new UpcomingTaxiBooking();
            case 2:
                return new CompletedTaxiBooking();
            case 3:
                return new CancelledTaxiBooking();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabcounts;
    }
}
