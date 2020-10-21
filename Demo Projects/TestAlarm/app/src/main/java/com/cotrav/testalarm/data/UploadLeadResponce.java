package com.cotrav.testalarm.data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadLeadResponce {
    @SerializedName("success")
    @Expose
    private SuccessResponce success;

    public SuccessResponce getSuccess() {
        return success;
    }

    public void setSuccess(SuccessResponce success) {
        this.success = success;
    }

}
