package com.taxivaxi.employee.models.taxibookingmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 10/7/17.
 */

public class DriverDetails {
    @SerializedName("driver_name")
    @Expose
    public String driverName;
    @SerializedName("driver_contact")
    @Expose
    public String driverContact;
}
