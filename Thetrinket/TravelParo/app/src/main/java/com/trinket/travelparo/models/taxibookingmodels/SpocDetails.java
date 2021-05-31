package com.taxivaxi.employee.models.taxibookingmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 10/7/17.
 */

public class SpocDetails {
    @SerializedName("spoc_name")
    @Expose
    public String spocName;
    @SerializedName("spoc_contact_email")
    @Expose
    public String spocContactEmail;
    @SerializedName("spoc_contact_no")
    @Expose
    public String spocContactNo;
}
