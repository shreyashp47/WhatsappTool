package com.taxivaxi.approver.models.bookingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 1/8/17.
 */

public class Response {
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("has_more_bookings")
    @Expose
    public String has_more_bookings;
    @SerializedName("Bookings")
    @Expose
    public List<TaxiBooking> bookings = null;
}
