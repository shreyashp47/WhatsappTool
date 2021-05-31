package com.taxivaxi.employee.models.allcitiesmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 17/7/17.
 */

public class AllCitiesApiResponse {
    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("error")
    @Expose
    public String error;
    @SerializedName("response")
    @Expose
    public CitiesResponse response;
}
