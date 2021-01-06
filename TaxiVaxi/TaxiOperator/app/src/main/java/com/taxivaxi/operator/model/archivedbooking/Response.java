package com.taxivaxi.operator.model.archivedbooking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 6/2/18.
 */

public class Response {
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("Bookings")
    @Expose
    public List<Booking> bookings = null;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
