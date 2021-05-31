package com.taxivaxi.spoc.model.assesmentcitymodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssCityApiResponse
{

    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("error")
    @Expose
    public String error;
    @SerializedName("response")
    @Expose
    public AssCityResponse response;
}
