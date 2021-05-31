package com.taxivaxi.approver.models.acceptrejectbookingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 8/8/17.
 */

public class AcceptRejectBookingApiRespone {

    @SerializedName("success")
    @Expose
    public String success;

    @SerializedName("error")
    @Expose
    public String error;

    @SerializedName("response")
    @Expose
    public Response response;
}
