package com.taxivaxi.spoc.model.busbookingdetailsmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 24/8/17.
 */

public class Response {
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("BookingDetail")
    @Expose
    public BusBookingDetail bookingDetail;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public BusBookingDetail getBookingDetail() {
        return bookingDetail;
    }

    public void setBookingDetail(BusBookingDetail bookingDetail) {
        this.bookingDetail = bookingDetail;
    }
}
