package com.trinket.travelparo.models.taxibookingmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 10/7/17.
 */

public class TaxiBusBookingsResponse {

    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("Bookings")
    @Expose
    public List<TaxiBusBookings> bookings = null;

}
