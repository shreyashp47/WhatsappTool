package com.trinket.travelparo.models.approver.hotelbookingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HotelBookingApiResponse {
    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("error")
    @Expose
    public String error;
    @SerializedName("response")
    @Expose
    public HotelResponse response;

    public String getSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public HotelResponse getResponse() {
        return response;
    }
}
