package com.taxivaxi.approver.models.BookingDetailsmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingDetailRespoce {


    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("response")
    @Expose
    public Response response;

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

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;

    }
}
