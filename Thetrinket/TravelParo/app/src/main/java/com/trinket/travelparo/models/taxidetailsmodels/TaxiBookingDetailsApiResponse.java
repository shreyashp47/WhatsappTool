package com.trinket.travelparo.models.taxidetailsmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 23/8/17.
 */

public class TaxiBookingDetailsApiResponse {

    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("error")
    @Expose
    public String error;
    @SerializedName("response")
    @Expose
    public ViewTaxiBookingDetailsResponse response;

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

    public ViewTaxiBookingDetailsResponse getResponse() {
        return response;
    }

    public void setResponse(ViewTaxiBookingDetailsResponse response) {
        this.response = response;
    }
}
