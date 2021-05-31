package com.taxivaxi.spoc.model.assesmentcodemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 17/7/17.
 */

public class AssCode {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("assessment_code")
    @Expose
    public String assessmentCode;
    @SerializedName("code_desc")
    @Expose
    public String codeDesc;
    @SerializedName("from_date")
    @Expose
    public String fromDate;
    @SerializedName("to_date")
    @Expose
    public String toDate;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("modified")
    @Expose
    public String modified;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("is_active")
    @Expose
    public String isActive;

}
