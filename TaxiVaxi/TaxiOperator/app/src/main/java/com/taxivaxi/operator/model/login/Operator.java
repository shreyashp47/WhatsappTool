package com.taxivaxi.operator.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Operator {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("old_password")
    @Expose
    private String oldPassword;
    @SerializedName("operator_name")
    @Expose
    private String operatorName;
    @SerializedName("operator_email")
    @Expose
    private String operatorEmail;
    @SerializedName("operator_contact")
    @Expose
    private String operatorContact;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("contact_name")
    @Expose
    private String contactName;
    @SerializedName("contact_email")
    @Expose
    private String contactEmail;
    @SerializedName("contact_no")
    @Expose
    private String contactNo;
    @SerializedName("operator_address")
    @Expose
    private String operatorAddress;
    @SerializedName("beneficiary_name")
    @Expose
    private String beneficiaryName;
    @SerializedName("beneficiary_account_no")
    @Expose
    private String beneficiaryAccountNo;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("ifsc_code")
    @Expose
    private String ifscCode;
    @SerializedName("is_service_tax_applicable")
    @Expose
    private String isServiceTaxApplicable;
    @SerializedName("service_tax_number")
    @Expose
    private String serviceTaxNumber;
    @SerializedName("night_start_time")
    @Expose
    private String nightStartTime;
    @SerializedName("night_end_time")
    @Expose
    private String nightEndTime;
    @SerializedName("tds_rate")
    @Expose
    private String tdsRate;
    @SerializedName("gst_id")
    @Expose
    private String gstId;
    @SerializedName("pan_no")
    @Expose
    private String panNo;
    @SerializedName("added")
    @Expose
    private String added;
    @SerializedName("modified")
    @Expose
    private String modified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorEmail() {
        return operatorEmail;
    }

    public void setOperatorEmail(String operatorEmail) {
        this.operatorEmail = operatorEmail;
    }

    public String getOperatorContact() {
        return operatorContact;
    }

    public void setOperatorContact(String operatorContact) {
        this.operatorContact = operatorContact;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getOperatorAddress() {
        return operatorAddress;
    }

    public void setOperatorAddress(String operatorAddress) {
        this.operatorAddress = operatorAddress;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryAccountNo() {
        return beneficiaryAccountNo;
    }

    public void setBeneficiaryAccountNo(String beneficiaryAccountNo) {
        this.beneficiaryAccountNo = beneficiaryAccountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getIsServiceTaxApplicable() {
        return isServiceTaxApplicable;
    }

    public void setIsServiceTaxApplicable(String isServiceTaxApplicable) {
        this.isServiceTaxApplicable = isServiceTaxApplicable;
    }

    public String getServiceTaxNumber() {
        return serviceTaxNumber;
    }

    public void setServiceTaxNumber(String serviceTaxNumber) {
        this.serviceTaxNumber = serviceTaxNumber;
    }

    public String getNightStartTime() {
        return nightStartTime;
    }

    public void setNightStartTime(String nightStartTime) {
        this.nightStartTime = nightStartTime;
    }

    public String getNightEndTime() {
        return nightEndTime;
    }

    public void setNightEndTime(String nightEndTime) {
        this.nightEndTime = nightEndTime;
    }

    public String getTdsRate() {
        return tdsRate;
    }

    public void setTdsRate(String tdsRate) {
        this.tdsRate = tdsRate;
    }

    public String getGstId() {
        return gstId;
    }

    public void setGstId(String gstId) {
        this.gstId = gstId;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}
