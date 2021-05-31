package com.taxivaxi.spoc.model.taxitypemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 9/10/17.
 */

public class Response {
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("TaxiTypes")
    @Expose
    public List<TaxiType> taxiTypes = null;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<TaxiType> getTaxiTypes() {
        return taxiTypes;
    }

    public void setTaxiTypes(List<TaxiType> taxiTypes) {
        this.taxiTypes = taxiTypes;
    }
}
