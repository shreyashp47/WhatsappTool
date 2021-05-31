package com.trinket.travelparo.models.taxibookingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 25/9/17.
 */

public class Booking {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("reference_no")
    @Expose
    public String referenceNo;
    @SerializedName("tour_type")
    @Expose
    public String tourType;
    @SerializedName("pickup_location")
    @Expose
    public String pickupLocation;
    @SerializedName("drop_location")
    @Expose
    public String dropLocation;
    @SerializedName("pickup_datetime")
    @Expose
    public String pickupDatetime;
    @SerializedName("driver_name")
    @Expose
    public String driverName;
    @SerializedName("driver_contact")
    @Expose
    public String driverContact;
    @SerializedName("taxi_reg_no")
    @Expose
    public String taxiRegNo;
    @SerializedName("status_user")
    @Expose
    public String statusUser;
    @SerializedName("status_auth1")
    @Expose
    public String statusAuth1;
    @SerializedName("status_auth2")
    @Expose
    public String statusAuth2;
    @SerializedName("status_auth_taxivaxi")
    @Expose
    public String statusAuthTaxivaxi;
    @SerializedName("taxi_model_name")
    @Expose
    public String taxiModelName;
    @SerializedName("booking_date")
    @Expose
    public String bookingDate;
    @SerializedName("days")
    @Expose
    public String days;
    @SerializedName("is_invoice")
    @Expose
    public String isInvoice;
    @SerializedName("taxi_type_name")
    @Expose
    public Object taxiTypeName;
    @SerializedName("zone_name")
    @Expose
    public Object zoneName;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("user_contact")
    @Expose
    public String userContact;
    @SerializedName("invoice_created")
    @Expose
    public Object invoiceCreated;
    @SerializedName("is_cancel_allowed")
    @Expose
    public String isCancelAllowed;
    @SerializedName("People")
    @Expose
    public List<Person> people = null;
    @SerializedName("taxivaxi_contact")
    @Expose
    public String taxivaxiContact;
    @SerializedName("booking_type")
    @Expose
    public String bookingType;
    @SerializedName("status_company")
    @Expose
    public String statusCompany;
    @SerializedName("status_company_color")
    @Expose
    public String statusCompanyColor;
    @SerializedName("status_tv")
    @Expose
    public String statusTv;
    @SerializedName("status_taxivaxi_color")
    @Expose
    public String statusTaxivaxiColor;

    public String getId() {
        return id;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public String getTourType() {
        return tourType;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public String getPickupDatetime() {
        return pickupDatetime;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public String getTaxiRegNo() {
        return taxiRegNo;
    }

    public String getStatusUser() {
        return statusUser;
    }

    public String getStatusAuth1() {
        return statusAuth1;
    }

    public String getStatusAuth2() {
        return statusAuth2;
    }

    public String getStatusAuthTaxivaxi() {
        return statusAuthTaxivaxi;
    }

    public String getTaxiModelName() {
        return taxiModelName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getDays() {
        return days;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public Object getTaxiTypeName() {
        return taxiTypeName;
    }

    public Object getZoneName() {
        return zoneName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserContact() {
        return userContact;
    }

    public Object getInvoiceCreated() {
        return invoiceCreated;
    }

    public String getIsCancelAllowed() {
        return isCancelAllowed;
    }

    public List<Person> getPeople() {
        return people;
    }

    public String getTaxivaxiContact() {
        return taxivaxiContact;
    }

    public String getBookingType() {
        return bookingType;
    }

    public String getStatusCompany() {
        return statusCompany;
    }

    public String getStatusCompanyColor() {
        return statusCompanyColor;
    }

    public String getStatusTv() {
        return statusTv;
    }

    public String getStatusTaxivaxiColor() {
        return statusTaxivaxiColor;
    }
}
