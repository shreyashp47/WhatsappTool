package com.taxivaxi.employee.models.allcitiesmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 17/7/17.
 */

public class City {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("state_id")
    @Expose
    public String stateId;
    @SerializedName("city_code")
    @Expose
    public String cityCode;
    @SerializedName("std_code")
    @Expose
    public String stdCode;
    @SerializedName("latitude")
    @Expose
    public String latitude;
    @SerializedName("longitude")
    @Expose
    public String longitude;
    @SerializedName("is_meal")
    @Expose
    public String isMeal;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("modified")
    @Expose
    public String modified;
    @SerializedName("admin_id")
    @Expose
    public String adminId;

}
