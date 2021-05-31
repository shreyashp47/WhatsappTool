package com.taxivaxi.employee.models.taxidetailsmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 23/8/17.
 */

public class TaxiBookingDetail {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("reference_no")
    @Expose
    public String referenceNo;
    @SerializedName("external_reference_no")
    @Expose
    public String externalReferenceNo;
    @SerializedName("operator_id")
    @Expose
    public String operatorId;
    @SerializedName("operator_name")
    @Expose
    public String operatorName;
    @SerializedName("operator_email")
    @Expose
    public String operatorEmail;
    @SerializedName("operator_contact")
    @Expose
    public String operatorContact;
    @SerializedName("driver_name")
    @Expose
    public String driverName;
    @SerializedName("driver_contact")
    @Expose
    public String driverContact;
    @SerializedName("taxi_type_id")
    @Expose
    public String taxiTypeId;
    @SerializedName("taxi_type_name")
    @Expose
    public String taxiTypeName;
    @SerializedName("taxi_model_id")
    @Expose
    public String taxiModelId;
    @SerializedName("taxi_model_name")
    @Expose
    public String taxiModelName;
    @SerializedName("taxi_reg_no")
    @Expose
    public String taxiRegNo;
    @SerializedName("tour_type")
    @Expose
    public String tourType;
    @SerializedName("city_id")
    @Expose
    public String cityId;
    @SerializedName("drop_city_name")
    @Expose
    public String dropCityName;
    @SerializedName("drop_location")
    @Expose
    public String dropLocation;
    @SerializedName("pickup_location")
    @Expose
    public String pickupLocation;
    @SerializedName("booking_date")
    @Expose
    public String bookingDate;
    @SerializedName("pickup_datetime")
    @Expose
    public String pickupDatetime;
    @SerializedName("no_of_seats")
    @Expose
    public String noOfSeats;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("total_amount")
    @Expose
    public String totalAmount;
    @SerializedName("prepaid_amount")
    @Expose
    public String prepaidAmount;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("modified")
    @Expose
    public String modified;
    @SerializedName("days")
    @Expose
    public String days;
    @SerializedName("rate_id")
    @Expose
    public String rateId;
    @SerializedName("is_assign")
    @Expose
    public String isAssign;
    @SerializedName("is_invoice")
    @Expose
    public String isInvoice;
    @SerializedName("invoice_id")
    @Expose
    public String invoiceId;
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
    @SerializedName("group_id")
    @Expose
    public String groupId;
    @SerializedName("subgroup_id")
    @Expose
    public String subgroupId;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("ass_code")
    @Expose
    public String assCode;
    @SerializedName("reason_booking")
    @Expose
    public String reasonBooking;
    @SerializedName("duty_slip")
    @Expose
    public String dutySlip;
    @SerializedName("other1_slip")
    @Expose
    public String other1Slip;
    @SerializedName("other2_slip")
    @Expose
    public String other2Slip;
    @SerializedName("other3_slip")
    @Expose
    public String other3Slip;
    @SerializedName("other4_slip")
    @Expose
    public String other4Slip;
    @SerializedName("user_cancel_date")
    @Expose
    public String userCancelDate;
    @SerializedName("approved_date")
    @Expose
    public String approvedDate;
    @SerializedName("rejected_date")
    @Expose
    public String rejectedDate;
    @SerializedName("cancel_reason")
    @Expose
    public String cancelReason;
    @SerializedName("tv_accept_reject_date")
    @Expose
    public String tvAcceptRejectDate;
    @SerializedName("assign_date")
    @Expose
    public String assignDate;
    @SerializedName("taxivaxi_comment")
    @Expose
    public String taxivaxiComment;
    @SerializedName("has_operator_issue")
    @Expose
    public String hasOperatorIssue;
    @SerializedName("operator_issue_description")
    @Expose
    public String operatorIssueDescription;
    @SerializedName("operator_penalty")
    @Expose
    public String operatorPenalty;
    @SerializedName("is_op_invoice")
    @Expose
    public String isOpInvoice;
    @SerializedName("op_invoice_id")
    @Expose
    public String opInvoiceId;
    @SerializedName("op_invoice_amount")
    @Expose
    public String opInvoiceAmount;
    @SerializedName("operator_package")
    @Expose
    public String operatorPackage;
    @SerializedName("billed_by_operator")
    @Expose
    public String billedByOperator;
    @SerializedName("expected_hrs")
    @Expose
    public String expectedHrs;
    @SerializedName("expected_kms")
    @Expose
    public String expectedKms;
    @SerializedName("suggested_package")
    @Expose
    public String suggestedPackage;
    @SerializedName("garage_location")
    @Expose
    public String garageLocation;
    @SerializedName("garage_distance")
    @Expose
    public String garageDistance;
    @SerializedName("is_op_slip_provided")
    @Expose
    public String isOpSlipProvided;
    @SerializedName("is_op_bill_provided")
    @Expose
    public String isOpBillProvided;
    @SerializedName("assessment_city")
    @Expose
    public String assessmentCity;
    @SerializedName("is_op_payment_advance")
    @Expose
    public String isOpPaymentAdvance;
    @SerializedName("is_op_payment_partial")
    @Expose
    public String isOpPaymentPartial;
    @SerializedName("is_op_payment_complete")
    @Expose
    public String isOpPaymentComplete;
    @SerializedName("op_bill_number")
    @Expose
    public String opBillNumber;
    @SerializedName("billing_entity")
    @Expose
    public String billingEntity;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("rate_name")
    @Expose
    public String rateName;
    @SerializedName("kms")
    @Expose
    public String kms;
    @SerializedName("hours")
    @Expose
    public String hours;
    @SerializedName("km_rate")
    @Expose
    public String kmRate;
    @SerializedName("hour_rate")
    @Expose
    public String hourRate;
    @SerializedName("base_rate")
    @Expose
    public String baseRate;
    @SerializedName("night_rate")
    @Expose
    public String nightRate;
    @SerializedName("taxi_model_name_o")
    @Expose
    public String taxiModelNameO;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("user_email")
    @Expose
    public String userEmail;
    @SerializedName("user_contact")
    @Expose
    public String userContact;
    @SerializedName("pickup_date")
    @Expose
    public String pickupDate;
    @SerializedName("pickup_time")
    @Expose
    public String pickupTime;
    @SerializedName("drop_date")
    @Expose
    public String dropDate;
    @SerializedName("drop_time")
    @Expose
    public String dropTime;
    @SerializedName("kms_done")
    @Expose
    public String kmsDone;
    @SerializedName("extra_hours_charge")
    @Expose
    public String extraHoursCharge;
    @SerializedName("extra_kms_charge")
    @Expose
    public String extraKmsCharge;
    @SerializedName("driver")
    @Expose
    public String driver;
    @SerializedName("extras")
    @Expose
    public String extras;
    @SerializedName("tax")
    @Expose
    public String tax;
    @SerializedName("total")
    @Expose
    public String total;
    @SerializedName("People")
    @Expose
    public List<Person> people = null;

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

    public String getExternalReferenceNo() {
        return externalReferenceNo;
    }

    public void setExternalReferenceNo(String externalReferenceNo) {
        this.externalReferenceNo = externalReferenceNo;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
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

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }

    public String getTaxiTypeId() {
        return taxiTypeId;
    }

    public void setTaxiTypeId(String taxiTypeId) {
        this.taxiTypeId = taxiTypeId;
    }

    public String getTaxiTypeName() {
        return taxiTypeName;
    }

    public void setTaxiTypeName(String taxiTypeName) {
        this.taxiTypeName = taxiTypeName;
    }

    public String getTaxiModelId() {
        return taxiModelId;
    }

    public void setTaxiModelId(String taxiModelId) {
        this.taxiModelId = taxiModelId;
    }

    public String getTaxiModelName() {
        return taxiModelName;
    }

    public void setTaxiModelName(String taxiModelName) {
        this.taxiModelName = taxiModelName;
    }

    public String getTaxiRegNo() {
        return taxiRegNo;
    }

    public void setTaxiRegNo(String taxiRegNo) {
        this.taxiRegNo = taxiRegNo;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDropCityName() {
        return dropCityName;
    }

    public void setDropCityName(String dropCityName) {
        this.dropCityName = dropCityName;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getPickupDatetime() {
        return pickupDatetime;
    }

    public void setPickupDatetime(String pickupDatetime) {
        this.pickupDatetime = pickupDatetime;
    }

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPrepaidAmount() {
        return prepaidAmount;
    }

    public void setPrepaidAmount(String prepaidAmount) {
        this.prepaidAmount = prepaidAmount;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    public String getIsAssign() {
        return isAssign;
    }

    public void setIsAssign(String isAssign) {
        this.isAssign = isAssign;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSubgroupId() {
        return subgroupId;
    }

    public void setSubgroupId(String subgroupId) {
        this.subgroupId = subgroupId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAssCode() {
        return assCode;
    }

    public void setAssCode(String assCode) {
        this.assCode = assCode;
    }

    public String getReasonBooking() {
        return reasonBooking;
    }

    public void setReasonBooking(String reasonBooking) {
        this.reasonBooking = reasonBooking;
    }

    public String getDutySlip() {
        return dutySlip;
    }

    public void setDutySlip(String dutySlip) {
        this.dutySlip = dutySlip;
    }

    public String getOther1Slip() {
        return other1Slip;
    }

    public void setOther1Slip(String other1Slip) {
        this.other1Slip = other1Slip;
    }

    public String getOther2Slip() {
        return other2Slip;
    }

    public void setOther2Slip(String other2Slip) {
        this.other2Slip = other2Slip;
    }

    public String getOther3Slip() {
        return other3Slip;
    }

    public void setOther3Slip(String other3Slip) {
        this.other3Slip = other3Slip;
    }

    public String getOther4Slip() {
        return other4Slip;
    }

    public void setOther4Slip(String other4Slip) {
        this.other4Slip = other4Slip;
    }

    public String getUserCancelDate() {
        return userCancelDate;
    }

    public void setUserCancelDate(String userCancelDate) {
        this.userCancelDate = userCancelDate;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(String rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getTvAcceptRejectDate() {
        return tvAcceptRejectDate;
    }

    public void setTvAcceptRejectDate(String tvAcceptRejectDate) {
        this.tvAcceptRejectDate = tvAcceptRejectDate;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getTaxivaxiComment() {
        return taxivaxiComment;
    }

    public void setTaxivaxiComment(String taxivaxiComment) {
        this.taxivaxiComment = taxivaxiComment;
    }

    public String getHasOperatorIssue() {
        return hasOperatorIssue;
    }

    public void setHasOperatorIssue(String hasOperatorIssue) {
        this.hasOperatorIssue = hasOperatorIssue;
    }

    public String getOperatorIssueDescription() {
        return operatorIssueDescription;
    }

    public void setOperatorIssueDescription(String operatorIssueDescription) {
        this.operatorIssueDescription = operatorIssueDescription;
    }

    public String getOperatorPenalty() {
        return operatorPenalty;
    }

    public void setOperatorPenalty(String operatorPenalty) {
        this.operatorPenalty = operatorPenalty;
    }

    public String getIsOpInvoice() {
        return isOpInvoice;
    }

    public void setIsOpInvoice(String isOpInvoice) {
        this.isOpInvoice = isOpInvoice;
    }

    public String getOpInvoiceId() {
        return opInvoiceId;
    }

    public void setOpInvoiceId(String opInvoiceId) {
        this.opInvoiceId = opInvoiceId;
    }

    public String getOpInvoiceAmount() {
        return opInvoiceAmount;
    }

    public void setOpInvoiceAmount(String opInvoiceAmount) {
        this.opInvoiceAmount = opInvoiceAmount;
    }

    public String getOperatorPackage() {
        return operatorPackage;
    }

    public void setOperatorPackage(String operatorPackage) {
        this.operatorPackage = operatorPackage;
    }

    public String getBilledByOperator() {
        return billedByOperator;
    }

    public void setBilledByOperator(String billedByOperator) {
        this.billedByOperator = billedByOperator;
    }

    public String getExpectedHrs() {
        return expectedHrs;
    }

    public void setExpectedHrs(String expectedHrs) {
        this.expectedHrs = expectedHrs;
    }

    public String getExpectedKms() {
        return expectedKms;
    }

    public void setExpectedKms(String expectedKms) {
        this.expectedKms = expectedKms;
    }

    public String getSuggestedPackage() {
        return suggestedPackage;
    }

    public void setSuggestedPackage(String suggestedPackage) {
        this.suggestedPackage = suggestedPackage;
    }

    public String getGarageLocation() {
        return garageLocation;
    }

    public void setGarageLocation(String garageLocation) {
        this.garageLocation = garageLocation;
    }

    public String getGarageDistance() {
        return garageDistance;
    }

    public void setGarageDistance(String garageDistance) {
        this.garageDistance = garageDistance;
    }

    public String getIsOpSlipProvided() {
        return isOpSlipProvided;
    }

    public void setIsOpSlipProvided(String isOpSlipProvided) {
        this.isOpSlipProvided = isOpSlipProvided;
    }

    public String getIsOpBillProvided() {
        return isOpBillProvided;
    }

    public void setIsOpBillProvided(String isOpBillProvided) {
        this.isOpBillProvided = isOpBillProvided;
    }

    public String getAssessmentCity() {
        return assessmentCity;
    }

    public void setAssessmentCity(String assessmentCity) {
        this.assessmentCity = assessmentCity;
    }

    public String getIsOpPaymentAdvance() {
        return isOpPaymentAdvance;
    }

    public void setIsOpPaymentAdvance(String isOpPaymentAdvance) {
        this.isOpPaymentAdvance = isOpPaymentAdvance;
    }

    public String getIsOpPaymentPartial() {
        return isOpPaymentPartial;
    }

    public void setIsOpPaymentPartial(String isOpPaymentPartial) {
        this.isOpPaymentPartial = isOpPaymentPartial;
    }

    public String getIsOpPaymentComplete() {
        return isOpPaymentComplete;
    }

    public void setIsOpPaymentComplete(String isOpPaymentComplete) {
        this.isOpPaymentComplete = isOpPaymentComplete;
    }

    public String getOpBillNumber() {
        return opBillNumber;
    }

    public void setOpBillNumber(String opBillNumber) {
        this.opBillNumber = opBillNumber;
    }

    public String getBillingEntity() {
        return billingEntity;
    }

    public void setBillingEntity(String billingEntity) {
        this.billingEntity = billingEntity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    public String getKms() {
        return kms;
    }

    public void setKms(String kms) {
        this.kms = kms;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getKmRate() {
        return kmRate;
    }

    public void setKmRate(String kmRate) {
        this.kmRate = kmRate;
    }

    public String getHourRate() {
        return hourRate;
    }

    public void setHourRate(String hourRate) {
        this.hourRate = hourRate;
    }

    public String getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(String baseRate) {
        this.baseRate = baseRate;
    }

    public String getNightRate() {
        return nightRate;
    }

    public void setNightRate(String nightRate) {
        this.nightRate = nightRate;
    }

    public String getTaxiModelNameO() {
        return taxiModelNameO;
    }

    public void setTaxiModelNameO(String taxiModelNameO) {
        this.taxiModelNameO = taxiModelNameO;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getDropDate() {
        return dropDate;
    }

    public void setDropDate(String dropDate) {
        this.dropDate = dropDate;
    }

    public String getDropTime() {
        return dropTime;
    }

    public void setDropTime(String dropTime) {
        this.dropTime = dropTime;
    }

    public String getKmsDone() {
        return kmsDone;
    }

    public void setKmsDone(String kmsDone) {
        this.kmsDone = kmsDone;
    }

    public String getExtraHoursCharge() {
        return extraHoursCharge;
    }

    public void setExtraHoursCharge(String extraHoursCharge) {
        this.extraHoursCharge = extraHoursCharge;
    }

    public String getExtraKmsCharge() {
        return extraKmsCharge;
    }

    public void setExtraKmsCharge(String extraKmsCharge) {
        this.extraKmsCharge = extraKmsCharge;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
