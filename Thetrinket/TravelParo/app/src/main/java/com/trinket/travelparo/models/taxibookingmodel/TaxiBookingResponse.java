package com.trinket.travelparo.models.taxibookingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 25/9/17.
 */

public class TaxiBookingResponse {
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("has_current_bookings")
    @Expose
    public String hasCurrentBookings;
    @SerializedName("has_upcoming_bookings")
    @Expose
    public String hasUpcomingBookings;
    @SerializedName("has_archived_bookings")
    @Expose
    public String hasArchivedBookings;
    @SerializedName("has_cancelled_bookings")
    @Expose
    public String hasCancelledBookings;

    public String getHasCurrentBookings() {
        return hasCurrentBookings;
    }

    public void setHasCurrentBookings(String hasCurrentBookings) {
        this.hasCurrentBookings = hasCurrentBookings;
    }

    public String getHasUpcomingBookings() {
        return hasUpcomingBookings;
    }

    public void setHasUpcomingBookings(String hasUpcomingBookings) {
        this.hasUpcomingBookings = hasUpcomingBookings;
    }

    public String getHasArchivedBookings() {
        return hasArchivedBookings;
    }

    public void setHasArchivedBookings(String hasArchivedBookings) {
        this.hasArchivedBookings = hasArchivedBookings;
    }

    public String getHasCancelledBookings() {
        return hasCancelledBookings;
    }

    public void setHasCancelledBookings(String hasCancelledBookings) {
        this.hasCancelledBookings = hasCancelledBookings;
    }

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
