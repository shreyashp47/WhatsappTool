package com.taxivaxi.employee.models.apkversionmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 31/8/17.
 */

public class ApkVersionApiResponse {
    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("error")
    @Expose
    public String error;
    @SerializedName("response")
    @Expose
    public ApkVersionResponse response;

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

    public ApkVersionResponse getResponse() {
        return response;
    }

    public void setResponse(ApkVersionResponse response) {
        this.response = response;
    }
}
