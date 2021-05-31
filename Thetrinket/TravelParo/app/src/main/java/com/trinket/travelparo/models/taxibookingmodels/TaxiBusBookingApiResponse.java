package com.taxivaxi.employee.models.taxibookingmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 10/7/17.
 */

public class TaxiBusBookingApiResponse {
    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("error")
    @Expose
    public String error;
    @SerializedName("response")
    @Expose
    public TaxiBusBookingsResponse response;
}
