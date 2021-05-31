package com.taxivaxi.approver.models.hotelbookingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 4/8/17.
 */

public class HotelResponse {
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("Bookings")
    @Expose
    public List<HotelBooking> bookings = null;
    @SerializedName("has_more_bookings")
    @Expose
    public String has_more_bookings;

}
