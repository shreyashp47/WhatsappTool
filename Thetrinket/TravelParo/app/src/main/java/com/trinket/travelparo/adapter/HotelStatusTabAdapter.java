package com.taxivaxi.spoc.BookingStatus;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.taxivaxi.spoc.fragment.hotels.CancelledHotelBooking;
import com.taxivaxi.spoc.fragment.hotels.CompletedHotelBooking;
import com.taxivaxi.spoc.fragment.hotels.CurrentHotelBooking;
import com.taxivaxi.spoc.fragment.hotels.UpcomingHotelBooking;

/**
 * Created by linxterminal on 22/2/17.
 */

public class HotelStatusTabAdapter extends FragmentStatePagerAdapter {
    int tabcounts;
    HotelStatusTabAdapter(FragmentManager fm, int tabCount)
    {
        super(fm);
        tabcounts=tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        HotelStatusTab tab=new HotelStatusTab();
        Bundle bundle=new Bundle();
        switch (position)
        {
            case 0: return new CurrentHotelBooking();
            case 1: return new UpcomingHotelBooking();
            case 2: return new CompletedHotelBooking();
            case 3: return new CancelledHotelBooking();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabcounts;
    }
}

