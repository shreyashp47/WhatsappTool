package com.taxivaxi.spoc.model.hotelbookingdetaismodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 23/8/17.
 */

public class HotelBookingResponse {
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("BookingDetail")
    @Expose
    public HotelBookingDetail bookingDetail;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public HotelBookingDetail getBookingDetail() {
        return bookingDetail;
    }

    public void setBookingDetail(HotelBookingDetail bookingDetail) {
        this.bookingDetail = bookingDetail;
    }
}
