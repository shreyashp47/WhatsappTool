package com.taxivaxi.spoc.model.hotelbookingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 3/10/17.
 */

public class HotelBookingResponse {

    @SerializedName("access_token")
    @Expose
    public String accessToken;

    @SerializedName("has_more_bookings")
    @Expose
    public String hasMoreBookings;

    @SerializedName("Bookings")
    @Expose
    public List<Booking> bookings = null;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getHasMoreBookings() {
        return hasMoreBookings;
    }

    public void setHasMoreBookings(String hasMoreBookings) {
        this.hasMoreBookings = hasMoreBookings;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
