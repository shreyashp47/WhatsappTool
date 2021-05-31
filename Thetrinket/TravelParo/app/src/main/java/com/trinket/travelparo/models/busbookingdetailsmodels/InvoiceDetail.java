package com.taxivaxi.spoc.model.busbookingdetailsmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 24/8/17.
 */

public class InvoiceDetail {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("booking_id")
    @Expose
    public String bookingId;
    @SerializedName("ticket")
    @Expose
    public String ticket;
    @SerializedName("total")
    @Expose
    public String total;
    @SerializedName("taxivaxi_rate")
    @Expose
    public Object taxivaxiRate;
    @SerializedName("taxivaxi_charge")
    @Expose
    public String taxivaxiCharge;
    @SerializedName("taxivaxi_tax_rate")
    @Expose
    public String taxivaxiTaxRate;
    @SerializedName("taxivaxi_tax_charge")
    @Expose
    public String taxivaxiTaxCharge;
    @SerializedName("sub_total")
    @Expose
    public String subTotal;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("previous_status")
    @Expose
    public String previousStatus;
    @SerializedName("comment_client")
    @Expose
    public String commentClient;
    @SerializedName("comment_taxivaxi")
    @Expose
    public String commentTaxivaxi;
    @SerializedName("accepted_by")
    @Expose
    public String acceptedBy;
    @SerializedName("rejected_by")
    @Expose
    public String rejectedBy;
    @SerializedName("admin_accept_time")
    @Expose
    public Object adminAcceptTime;
    @SerializedName("admin_reject_time")
    @Expose
    public Object adminRejectTime;
    @SerializedName("spoc_accept_time")
    @Expose
    public Object spocAcceptTime;
    @SerializedName("spoc_reject_time")
    @Expose
    public Object spocRejectTime;
    @SerializedName("is_auto_cleared_by_spoc")
    @Expose
    public String isAutoClearedBySpoc;
    @SerializedName("is_billed")
    @Expose
    public String isBilled;
    @SerializedName("bill_id")
    @Expose
    public Object billId;
    @SerializedName("is_paid")
    @Expose
    public String isPaid;
    @SerializedName("is_cancelled")
    @Expose
    public String isCancelled;
    @SerializedName("cancelled_by")
    @Expose
    public String cancelledBy;
    @SerializedName("cancellation_charge")
    @Expose
    public String cancellationCharge;
    @SerializedName("cancellation_rate")
    @Expose
    public String cancellationRate;
    @SerializedName("tax_rate")
    @Expose
    public String taxRate;
    @SerializedName("tax")
    @Expose
    public String tax;
    @SerializedName("cancel_request_proof")
    @Expose
    public String cancelRequestProof;
    @SerializedName("operator_total")
    @Expose
    public String operatorTotal;
    @SerializedName("operator_commission")
    @Expose
    public String operatorCommission;
    @SerializedName("operator_commission_is_percent")
    @Expose
    public String operatorCommissionIsPercent;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("modified")
    @Expose
    public String modified;
    @SerializedName("mgmt_fee_igst_rate")
    @Expose
    public String mgmtFeeIgstRate;
    @SerializedName("mgmt_fee_cgst_rate")
    @Expose
    public String mgmtFeeCgstRate;
    @SerializedName("mgmt_fee_sgst_rate")
    @Expose
    public String mgmtFeeSgstRate;
    @SerializedName("mgmt_fee_igst")
    @Expose
    public String mgmtFeeIgst;
    @SerializedName("mgmt_fee_cgst")
    @Expose
    public String mgmtFeeCgst;
    @SerializedName("mgmt_fee_sgst")
    @Expose
    public String mgmtFeeSgst;
    @SerializedName("cancellation_fee_igst_rate")
    @Expose
    public String cancellationFeeIgstRate;
    @SerializedName("cancellation_fee_cgst_rate")
    @Expose
    public String cancellationFeeCgstRate;
    @SerializedName("cancellation_fee_sgst_rate")
    @Expose
    public String cancellationFeeSgstRate;
    @SerializedName("cancellation_fee_igst")
    @Expose
    public String cancellationFeeIgst;
    @SerializedName("cancellation_fee_cgst")
    @Expose
    public String cancellationFeeCgst;
    @SerializedName("cancellation_fee_sgst")
    @Expose
    public String cancellationFeeSgst;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Object getTaxivaxiRate() {
        return taxivaxiRate;
    }

    public void setTaxivaxiRate(Object taxivaxiRate) {
        this.taxivaxiRate = taxivaxiRate;
    }

    public String getTaxivaxiCharge() {
        return taxivaxiCharge;
    }

    public void setTaxivaxiCharge(String taxivaxiCharge) {
        this.taxivaxiCharge = taxivaxiCharge;
    }

    public String getTaxivaxiTaxRate() {
        return taxivaxiTaxRate;
    }

    public void setTaxivaxiTaxRate(String taxivaxiTaxRate) {
        this.taxivaxiTaxRate = taxivaxiTaxRate;
    }

    public String getTaxivaxiTaxCharge() {
        return taxivaxiTaxCharge;
    }

    public void setTaxivaxiTaxCharge(String taxivaxiTaxCharge) {
        this.taxivaxiTaxCharge = taxivaxiTaxCharge;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(String previousStatus) {
        this.previousStatus = previousStatus;
    }

    public String getCommentClient() {
        return commentClient;
    }

    public void setCommentClient(String commentClient) {
        this.commentClient = commentClient;
    }

    public String getCommentTaxivaxi() {
        return commentTaxivaxi;
    }

    public void setCommentTaxivaxi(String commentTaxivaxi) {
        this.commentTaxivaxi = commentTaxivaxi;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public String getRejectedBy() {
        return rejectedBy;
    }

    public void setRejectedBy(String rejectedBy) {
        this.rejectedBy = rejectedBy;
    }

    public Object getAdminAcceptTime() {
        return adminAcceptTime;
    }

    public void setAdminAcceptTime(Object adminAcceptTime) {
        this.adminAcceptTime = adminAcceptTime;
    }

    public Object getAdminRejectTime() {
        return adminRejectTime;
    }

    public void setAdminRejectTime(Object adminRejectTime) {
        this.adminRejectTime = adminRejectTime;
    }

    public Object getSpocAcceptTime() {
        return spocAcceptTime;
    }

    public void setSpocAcceptTime(Object spocAcceptTime) {
        this.spocAcceptTime = spocAcceptTime;
    }

    public Object getSpocRejectTime() {
        return spocRejectTime;
    }

    public void setSpocRejectTime(Object spocRejectTime) {
        this.spocRejectTime = spocRejectTime;
    }

    public String getIsAutoClearedBySpoc() {
        return isAutoClearedBySpoc;
    }

    public void setIsAutoClearedBySpoc(String isAutoClearedBySpoc) {
        this.isAutoClearedBySpoc = isAutoClearedBySpoc;
    }

    public String getIsBilled() {
        return isBilled;
    }

    public void setIsBilled(String isBilled) {
        this.isBilled = isBilled;
    }

    public Object getBillId() {
        return billId;
    }

    public void setBillId(Object billId) {
        this.billId = billId;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public String getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(String isCancelled) {
        this.isCancelled = isCancelled;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }

    public void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public String getCancellationCharge() {
        return cancellationCharge;
    }

    public void setCancellationCharge(String cancellationCharge) {
        this.cancellationCharge = cancellationCharge;
    }

    public String getCancellationRate() {
        return cancellationRate;
    }

    public void setCancellationRate(String cancellationRate) {
        this.cancellationRate = cancellationRate;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getCancelRequestProof() {
        return cancelRequestProof;
    }

    public void setCancelRequestProof(String cancelRequestProof) {
        this.cancelRequestProof = cancelRequestProof;
    }

    public String getOperatorTotal() {
        return operatorTotal;
    }

    public void setOperatorTotal(String operatorTotal) {
        this.operatorTotal = operatorTotal;
    }

    public String getOperatorCommission() {
        return operatorCommission;
    }

    public void setOperatorCommission(String operatorCommission) {
        this.operatorCommission = operatorCommission;
    }

    public String getOperatorCommissionIsPercent() {
        return operatorCommissionIsPercent;
    }

    public void setOperatorCommissionIsPercent(String operatorCommissionIsPercent) {
        this.operatorCommissionIsPercent = operatorCommissionIsPercent;
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

    public String getMgmtFeeIgstRate() {
        return mgmtFeeIgstRate;
    }

    public void setMgmtFeeIgstRate(String mgmtFeeIgstRate) {
        this.mgmtFeeIgstRate = mgmtFeeIgstRate;
    }

    public String getMgmtFeeCgstRate() {
        return mgmtFeeCgstRate;
    }

    public void setMgmtFeeCgstRate(String mgmtFeeCgstRate) {
        this.mgmtFeeCgstRate = mgmtFeeCgstRate;
    }

    public String getMgmtFeeSgstRate() {
        return mgmtFeeSgstRate;
    }

    public void setMgmtFeeSgstRate(String mgmtFeeSgstRate) {
        this.mgmtFeeSgstRate = mgmtFeeSgstRate;
    }

    public String getMgmtFeeIgst() {
        return mgmtFeeIgst;
    }

    public void setMgmtFeeIgst(String mgmtFeeIgst) {
        this.mgmtFeeIgst = mgmtFeeIgst;
    }

    public String getMgmtFeeCgst() {
        return mgmtFeeCgst;
    }

    public void setMgmtFeeCgst(String mgmtFeeCgst) {
        this.mgmtFeeCgst = mgmtFeeCgst;
    }

    public String getMgmtFeeSgst() {
        return mgmtFeeSgst;
    }

    public void setMgmtFeeSgst(String mgmtFeeSgst) {
        this.mgmtFeeSgst = mgmtFeeSgst;
    }

    public String getCancellationFeeIgstRate() {
        return cancellationFeeIgstRate;
    }

    public void setCancellationFeeIgstRate(String cancellationFeeIgstRate) {
        this.cancellationFeeIgstRate = cancellationFeeIgstRate;
    }

    public String getCancellationFeeCgstRate() {
        return cancellationFeeCgstRate;
    }

    public void setCancellationFeeCgstRate(String cancellationFeeCgstRate) {
        this.cancellationFeeCgstRate = cancellationFeeCgstRate;
    }

    public String getCancellationFeeSgstRate() {
        return cancellationFeeSgstRate;
    }

    public void setCancellationFeeSgstRate(String cancellationFeeSgstRate) {
        this.cancellationFeeSgstRate = cancellationFeeSgstRate;
    }

    public String getCancellationFeeIgst() {
        return cancellationFeeIgst;
    }

    public void setCancellationFeeIgst(String cancellationFeeIgst) {
        this.cancellationFeeIgst = cancellationFeeIgst;
    }

    public String getCancellationFeeCgst() {
        return cancellationFeeCgst;
    }

    public void setCancellationFeeCgst(String cancellationFeeCgst) {
        this.cancellationFeeCgst = cancellationFeeCgst;
    }

    public String getCancellationFeeSgst() {
        return cancellationFeeSgst;
    }

    public void setCancellationFeeSgst(String cancellationFeeSgst) {
        this.cancellationFeeSgst = cancellationFeeSgst;
    }
}
