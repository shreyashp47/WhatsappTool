package com.taxivaxi.employee.models.taxibookingmodels;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 10/7/17.
 */
@Entity
public class TaxiBusBookings {

    @PrimaryKey
    @SerializedName("booking_id")
    @Expose
    @NonNull
    public String bookingId;
    @SerializedName("reference_no")
    @Expose
    public String referenceNo;
    @SerializedName("tour_type")
    @Expose
    public String tourType;
    @SerializedName("pickup_location")
    @Expose
    public String pickupLocation;
    @SerializedName("booking_date")
    @Expose
    public String bookingDate;
    @SerializedName("pickup_datetime")
    @Expose
    public String pickupDatetime;
    @SerializedName("p_date_for_sort")
    @Expose
    public String pDateForSort;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("booking_reason")
    @Expose
    public String bookingReason;
    @SerializedName("assessment_code")
    @Expose
    public String assessmentCode;

    @Embedded
    @SerializedName("spoc_details")
    @Expose
    public SpocDetails spocDetails;
    @SerializedName("is_assign")
    @Expose
    public String isAssign;

    @Embedded
    @SerializedName("driver_details")
    @Expose
    public DriverDetails driverDetails;

    @Embedded
    @SerializedName("taxi_details")
    @Expose
    public TaxiDetails taxiDetails;

    @SerializedName("status_company")
    @Expose
    public String statusCompany;

    @SerializedName("status_tv")
    @Expose
    public String statusTv;

    @SerializedName("is_cancel_allowed")
    @Expose
    public Integer isCancelAllowed;

    @SerializedName("drop_location")
    @Expose
    public String dropLocation;

    @SerializedName("start_otp")
    @Expose
    public String startOTP;

    @SerializedName("end_otp")
    @Expose
    public String endOTP;
}
