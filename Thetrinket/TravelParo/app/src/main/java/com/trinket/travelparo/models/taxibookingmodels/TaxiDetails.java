package com.taxivaxi.employee.models.taxibookingmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 10/7/17.
 */

public class TaxiDetails {
    @SerializedName("taxi_model_name")
    @Expose
    public String taxiModelName;
    @SerializedName("taxi_reg_no")
    @Expose
    public String taxiRegNo;
}
