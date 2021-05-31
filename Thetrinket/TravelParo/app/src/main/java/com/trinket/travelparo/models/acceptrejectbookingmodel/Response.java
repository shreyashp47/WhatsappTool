package com.taxivaxi.approver.models.acceptrejectbookingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 8/8/17.
 */

public class Response {
    @SerializedName("booking_id")
    @Expose
    public String bookingId;
    @SerializedName("message")
    @Expose
    public String message;

}
