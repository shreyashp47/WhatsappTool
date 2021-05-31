package com.trinket.travelparo.models.taxidetailsmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 23/8/17.
 */

public class ViewTaxiBookingDetailsResponse {
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("BookingDetail")
    @Expose
    public TaxiBookingDetail bookingDetail;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public TaxiBookingDetail getBookingDetail() {
        return bookingDetail;
    }

    public void setBookingDetail(TaxiBookingDetail bookingDetail) {
        this.bookingDetail = bookingDetail;
    }
}
