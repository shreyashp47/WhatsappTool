package com.taxivaxi.spoc.model.hotelbookingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 3/10/17.
 */

public class Booking {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("reference_no")
    @Expose
    public String referenceNo;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("arrival_datetime")
    @Expose
    public String arrivalDatetime;
    @SerializedName("dep_datetime")
    @Expose
    public String depDatetime;
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
    @SerializedName("is_invoice")
    @Expose
    public String isInvoice;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("user_contact")
    @Expose
    public String userContact;
    @SerializedName("invoice_created")
    @Expose
    public String invoiceCreated;
    @SerializedName("is_cancel_allowed")
    @Expose
    public String isCancelAllowed;
    @SerializedName("People")
    @Expose
    public List<Person> people = null;
    @SerializedName("taxivaxi_contact")
    @Expose
    public String taxivaxiContact;
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

    public void setId(String id) {
        this.id = id;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArrivalDatetime() {
        return arrivalDatetime;
    }

    public void setArrivalDatetime(String arrivalDatetime) {
        this.arrivalDatetime = arrivalDatetime;
    }

    public String getDepDatetime() {
        return depDatetime;
    }

    public void setDepDatetime(String depDatetime) {
        this.depDatetime = depDatetime;
    }

    public String getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }

    public String getStatusAuth1() {
        return statusAuth1;
    }

    public void setStatusAuth1(String statusAuth1) {
        this.statusAuth1 = statusAuth1;
    }

    public String getStatusAuth2() {
        return statusAuth2;
    }

    public void setStatusAuth2(String statusAuth2) {
        this.statusAuth2 = statusAuth2;
    }

    public String getStatusAuthTaxivaxi() {
        return statusAuthTaxivaxi;
    }

    public void setStatusAuthTaxivaxi(String statusAuthTaxivaxi) {
        this.statusAuthTaxivaxi = statusAuthTaxivaxi;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getInvoiceCreated() {
        return invoiceCreated;
    }

    public void setInvoiceCreated(String invoiceCreated) {
        this.invoiceCreated = invoiceCreated;
    }

    public String getIsCancelAllowed() {
        return isCancelAllowed;
    }

    public void setIsCancelAllowed(String isCancelAllowed) {
        this.isCancelAllowed = isCancelAllowed;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public String getTaxivaxiContact() {
        return taxivaxiContact;
    }

    public void setTaxivaxiContact(String taxivaxiContact) {
        this.taxivaxiContact = taxivaxiContact;
    }

    public String getStatusCompany() {
        return statusCompany;
    }

    public void setStatusCompany(String statusCompany) {
        this.statusCompany = statusCompany;
    }

    public String getStatusCompanyColor() {
        return statusCompanyColor;
    }

    public void setStatusCompanyColor(String statusCompanyColor) {
        this.statusCompanyColor = statusCompanyColor;
    }

    public String getStatusTv() {
        return statusTv;
    }

    public void setStatusTv(String statusTv) {
        this.statusTv = statusTv;
    }

    public String getStatusTaxivaxiColor() {
        return statusTaxivaxiColor;
    }

    public void setStatusTaxivaxiColor(String statusTaxivaxiColor) {
        this.statusTaxivaxiColor = statusTaxivaxiColor;
    }
}
