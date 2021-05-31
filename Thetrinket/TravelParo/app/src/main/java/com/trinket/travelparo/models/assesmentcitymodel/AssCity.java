package com.taxivaxi.spoc.model.assesmentcitymodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssCity {


    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("modified")
    @Expose
    public String modified;

}
