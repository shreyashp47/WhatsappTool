package com.trinket.travelparo.models.taxibookingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 25/9/17.
 */

public class    TaxiBookingApiResponse {
    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("error")
    @Expose
    public String error;
    @SerializedName("response")
    @Expose
    public TaxiBookingResponse response;

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

    public TaxiBookingResponse getResponse() {
        return response;
    }

    public void setResponse(TaxiBookingResponse response) {
        this.response = response;
    }
}
