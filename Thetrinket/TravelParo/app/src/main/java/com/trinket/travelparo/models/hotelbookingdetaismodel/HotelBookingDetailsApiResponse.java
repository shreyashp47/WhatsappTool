package com.taxivaxi.spoc.model.hotelbookingdetaismodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 23/8/17.
 */

public class HotelBookingDetailsApiResponse {
    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("error")
    @Expose
    public String error;
    @SerializedName("response")
    @Expose
    public HotelBookingResponse response;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HotelBookingResponse getResponse() {
        return response;
    }

    public void setResponse(HotelBookingResponse response) {
        this.response = response;
    }
}
