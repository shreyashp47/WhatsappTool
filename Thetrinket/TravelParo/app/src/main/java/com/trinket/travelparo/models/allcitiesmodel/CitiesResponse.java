package com.taxivaxi.employee.models.allcitiesmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 17/7/17.
 */

public class CitiesResponse {

    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("Cities")
    @Expose
    public List<City> cities = null;
}
