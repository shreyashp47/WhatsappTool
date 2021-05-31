package com.taxivaxi.employee.models.apkversionmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 31/8/17.
 */

public class ApkVersionResponse {

    @SerializedName("Version")
    @Expose
    public String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
