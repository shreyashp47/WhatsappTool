package com.taxivaxi.approver.models.bookingmodel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

/**
 * Created by sandeep on 1/8/17.
 */

@Entity
public class TaxiBooking {


    @PrimaryKey
    @SerializedName("id")
    @Expose
    @NotNull
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
    @SerializedName("zone_name")
    @Expose
    public String zoneName;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("user_contact")
    @Expose
    public String userContact;
    @SerializedName( "user_email")
    @Expose
    public String userEmail;
    @SerializedName("invoice_created")
    @Expose
    public String invoiceCreated;
    @SerializedName("sub_total")
    @Expose
    public String subTotal;
    @SerializedName("spoc_accept_time")
    @Expose
    public String spocAcceptTime;
    @SerializedName("admin_accept_time")
    @Expose
    public String adminAcceptTime;
    @SerializedName("is_billed")
    @Expose
    public String isBilled;
    @SerializedName("invoice_status")
    @Expose
    public String invoiceStatus;
    @SerializedName("previous_status")
    @Expose
    public String previousStatus;
    @SerializedName("is_auto_cleared_by_spoc")
    @Expose
    public String isAutoClearedBySpoc;
    @SerializedName("comment_client")
    @Expose
    public String commentClient;
    @SerializedName("rejected_by")
    @Expose
    public String rejectedBy;
    @SerializedName("spoc_reject_time")
    @Expose
    public String spocRejectTime;
    @SerializedName("admin_reject_time")
    @Expose
    public String adminRejectTime;
    @SerializedName("bill_reference_no")
    @Expose
    public String billReferenceNo;
    @SerializedName("is_paid")
    @Expose
    public String isPaid;
    @SerializedName("bill_date")
    @Expose
    public String billDate;
    @SerializedName("payment_date")
    @Expose
    public String paymentDate;
    @SerializedName("booking_type")
    @Expose
    public String bookingType;
    @SerializedName("status_company")
    @Expose
    public String statusCompany;
    @SerializedName("status_company_color")
    @Expose
    public String statusCompanyColor;
    @SerializedName("status_taxivaxi")
    @Expose
    public String statusTaxivaxi;
    @SerializedName("status_taxivaxi_color")
    @Expose
    public String statusTaxivaxiColor;

}
