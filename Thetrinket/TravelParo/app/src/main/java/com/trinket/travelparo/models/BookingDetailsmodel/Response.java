package com.taxivaxi.approver.models.BookingDetailsmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("BookingDetail")
    @Expose
    private BookingDetail bookingDetail;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public BookingDetail getBookingDetail() {
        return bookingDetail;
    }

    public void setBookingDetail(BookingDetail bookingDetail) {
        this.bookingDetail = bookingDetail;
    }
}
