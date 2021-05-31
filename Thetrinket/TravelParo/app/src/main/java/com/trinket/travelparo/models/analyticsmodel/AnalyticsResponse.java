package com.taxivaxi.approver.models.analyticsmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 21/7/17.
 */

public class AnalyticsResponse {
    @SerializedName("access_token")
    @Expose
    public Object accessToken;
    @SerializedName("TotalBookings")
    @Expose
    public TotalBookings totalBookings;
}
