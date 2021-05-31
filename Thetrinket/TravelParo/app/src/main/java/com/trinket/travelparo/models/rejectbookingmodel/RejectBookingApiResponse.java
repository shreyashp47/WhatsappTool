package com.trinket.travelparo.models.rejectbookingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RejectBookingApiResponse {
    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("error")
    @Expose
    public String error;
    @SerializedName("response")
    @Expose
    public RejectResponse response;

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

    public RejectResponse getResponse() {
        return response;
    }

    public void setResponse(RejectResponse response) {
        this.response = response;
    }
}
