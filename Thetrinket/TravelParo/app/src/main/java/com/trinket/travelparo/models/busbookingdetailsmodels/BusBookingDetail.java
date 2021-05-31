package com.taxivaxi.spoc.model.busbookingdetailsmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 24/8/17.
 */

public class BusBookingDetail {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("reference_no")
    @Expose
    public String referenceNo;
    @SerializedName("portal_used")
    @Expose
    public String portalUsed;
    @SerializedName("operator_name")
    @Expose
    public String operatorName;
    @SerializedName("operator_contact")
    @Expose
    public String operatorContact;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("group_id")
    @Expose
    public String groupId;
    @SerializedName("subgroup_id")
    @Expose
    public String subgroupId;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("people_id")
    @Expose
    public String peopleId;
    @SerializedName("pickup_city")
    @Expose
    public String pickupCity;
    @SerializedName("drop_city")
    @Expose
    public String dropCity;
    @SerializedName("date_of_journey")
    @Expose
    public String dateOfJourney;
    @SerializedName("time_range")
    @Expose
    public String timeRange;
    @SerializedName("age")
    @Expose
    public String age;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("id_proof_type")
    @Expose
    public String idProofType;
    @SerializedName("id_proof_no")
    @Expose
    public String idProofNo;
    @SerializedName("time_of_journey")
    @Expose
    public Object timeOfJourney;
    @SerializedName("bus_type_priority_1")
    @Expose
    public String busTypePriority1;
    @SerializedName("bus_type_priority_2")
    @Expose
    public String busTypePriority2;
    @SerializedName("bus_type_priority_3")
    @Expose
    public String busTypePriority3;
    @SerializedName("boarding_point")
    @Expose
    public String boardingPoint;
    @SerializedName("drop_point")
    @Expose
    public Object dropPoint;
    @SerializedName("no_of_seats")
    @Expose
    public String noOfSeats;
    @SerializedName("assessment_code")
    @Expose
    public String assessmentCode;
    @SerializedName("reason_of_booking")
    @Expose
    public String reasonOfBooking;
    @SerializedName("is_invoice")
    @Expose
    public String isInvoice;
    @SerializedName("invoice_id")
    @Expose
    public String invoiceId;
    @SerializedName("booking_datetime")
    @Expose
    public String bookingDatetime;
    @SerializedName("status_spoc")
    @Expose
    public String statusSpoc;
    @SerializedName("status_auth1")
    @Expose
    public String statusAuth1;
    @SerializedName("status_auth2")
    @Expose
    public String statusAuth2;
    @SerializedName("status_auth_taxivaxi")
    @Expose
    public String statusAuthTaxivaxi;
    @SerializedName("auth_reject_reason")
    @Expose
    public String authRejectReason;
    @SerializedName("auth_accept_time")
    @Expose
    public String authAcceptTime;
    @SerializedName("auth_reject_time")
    @Expose
    public String authRejectTime;
    @SerializedName("spoc_cancel_date")
    @Expose
    public String spocCancelDate;
    @SerializedName("spoc_cancel_reason")
    @Expose
    public String spocCancelReason;
    @SerializedName("taxivaxi_accept_time")
    @Expose
    public String taxivaxiAcceptTime;
    @SerializedName("taxivaxi_reject_time")
    @Expose
    public String taxivaxiRejectTime;
    @SerializedName("taxivaxi_assign_time")
    @Expose
    public String taxivaxiAssignTime;
    @SerializedName("taxivaxi_comment")
    @Expose
    public String taxivaxiComment;
    @SerializedName("boarding_point_taxivaxi")
    @Expose
    public String boardingPointTaxivaxi;
    @SerializedName("pickup_datetime_taxivaxi")
    @Expose
    public String pickupDatetimeTaxivaxi;
    @SerializedName("ticket_number")
    @Expose
    public String ticketNumber;
    @SerializedName("pnr_number")
    @Expose
    public String pnrNumber;
    @SerializedName("bus_type_allocated")
    @Expose
    public String busTypeAllocated;
    @SerializedName("seat_number")
    @Expose
    public String seatNumber;
    @SerializedName("is_assign")
    @Expose
    public String isAssign;
    @SerializedName("is_cancelled")
    @Expose
    public String isCancelled;
    @SerializedName("cancelled_by")
    @Expose
    public String cancelledBy;
    @SerializedName("cancellation_charge")
    @Expose
    public String cancellationCharge;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("modified")
    @Expose
    public String modified;
    @SerializedName("assessment_city")
    @Expose
    public String assessmentCity;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("user_email")
    @Expose
    public String userEmail;
    @SerializedName("ticket")
    @Expose
    public String ticket;

    @SerializedName("user_contact")
    @Expose
    public String userContact;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("People")
    @Expose
    public List<Person> people = null;
    @SerializedName("InvoiceDetail")
    @Expose
    public List<InvoiceDetail> invoiceDetail = null;

    public String getId() {
        return id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
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

    public String getPortalUsed() {
        return portalUsed;
    }

    public void setPortalUsed(String portalUsed) {
        this.portalUsed = portalUsed;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorContact() {
        return operatorContact;
    }

    public void setOperatorContact(String operatorContact) {
        this.operatorContact = operatorContact;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getPickupCity() {
        return pickupCity;
    }

    public void setPickupCity(String pickupCity) {
        this.pickupCity = pickupCity;
    }

    public String getDropCity() {
        return dropCity;
    }

    public void setDropCity(String dropCity) {
        this.dropCity = dropCity;
    }

    public String getDateOfJourney() {
        return dateOfJourney;
    }

    public void setDateOfJourney(String dateOfJourney) {
        this.dateOfJourney = dateOfJourney;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdProofType() {
        return idProofType;
    }

    public void setIdProofType(String idProofType) {
        this.idProofType = idProofType;
    }

    public String getIdProofNo() {
        return idProofNo;
    }

    public void setIdProofNo(String idProofNo) {
        this.idProofNo = idProofNo;
    }

    public Object getTimeOfJourney() {
        return timeOfJourney;
    }

    public void setTimeOfJourney(Object timeOfJourney) {
        this.timeOfJourney = timeOfJourney;
    }

    public String getBusTypePriority1() {
        return busTypePriority1;
    }

    public void setBusTypePriority1(String busTypePriority1) {
        this.busTypePriority1 = busTypePriority1;
    }

    public String getBusTypePriority2() {
        return busTypePriority2;
    }

    public void setBusTypePriority2(String busTypePriority2) {
        this.busTypePriority2 = busTypePriority2;
    }

    public String getBusTypePriority3() {
        return busTypePriority3;
    }

    public void setBusTypePriority3(String busTypePriority3) {
        this.busTypePriority3 = busTypePriority3;
    }

    public String getBoardingPoint() {
        return boardingPoint;
    }

    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public Object getDropPoint() {
        return dropPoint;
    }

    public void setDropPoint(Object dropPoint) {
        this.dropPoint = dropPoint;
    }

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getAssessmentCode() {
        return assessmentCode;
    }

    public void setAssessmentCode(String assessmentCode) {
        this.assessmentCode = assessmentCode;
    }

    public String getReasonOfBooking() {
        return reasonOfBooking;
    }

    public void setReasonOfBooking(String reasonOfBooking) {
        this.reasonOfBooking = reasonOfBooking;
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

    public String getBookingDatetime() {
        return bookingDatetime;
    }

    public void setBookingDatetime(String bookingDatetime) {
        this.bookingDatetime = bookingDatetime;
    }

    public String getStatusSpoc() {
        return statusSpoc;
    }

    public void setStatusSpoc(String statusSpoc) {
        this.statusSpoc = statusSpoc;
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

    public String getAuthRejectReason() {
        return authRejectReason;
    }

    public void setAuthRejectReason(String authRejectReason) {
        this.authRejectReason = authRejectReason;
    }

    public String getAuthAcceptTime() {
        return authAcceptTime;
    }

    public void setAuthAcceptTime(String authAcceptTime) {
        this.authAcceptTime = authAcceptTime;
    }

    public String getAuthRejectTime() {
        return authRejectTime;
    }

    public void setAuthRejectTime(String authRejectTime) {
        this.authRejectTime = authRejectTime;
    }

    public String getSpocCancelDate() {
        return spocCancelDate;
    }

    public void setSpocCancelDate(String spocCancelDate) {
        this.spocCancelDate = spocCancelDate;
    }

    public String getSpocCancelReason() {
        return spocCancelReason;
    }

    public void setSpocCancelReason(String spocCancelReason) {
        this.spocCancelReason = spocCancelReason;
    }

    public String getTaxivaxiAcceptTime() {
        return taxivaxiAcceptTime;
    }

    public void setTaxivaxiAcceptTime(String taxivaxiAcceptTime) {
        this.taxivaxiAcceptTime = taxivaxiAcceptTime;
    }

    public String getTaxivaxiRejectTime() {
        return taxivaxiRejectTime;
    }

    public void setTaxivaxiRejectTime(String taxivaxiRejectTime) {
        this.taxivaxiRejectTime = taxivaxiRejectTime;
    }

    public String getTaxivaxiAssignTime() {
        return taxivaxiAssignTime;
    }

    public void setTaxivaxiAssignTime(String taxivaxiAssignTime) {
        this.taxivaxiAssignTime = taxivaxiAssignTime;
    }

    public String getTaxivaxiComment() {
        return taxivaxiComment;
    }

    public void setTaxivaxiComment(String taxivaxiComment) {
        this.taxivaxiComment = taxivaxiComment;
    }

    public String getBoardingPointTaxivaxi() {
        return boardingPointTaxivaxi;
    }

    public void setBoardingPointTaxivaxi(String boardingPointTaxivaxi) {
        this.boardingPointTaxivaxi = boardingPointTaxivaxi;
    }

    public String getPickupDatetimeTaxivaxi() {
        return pickupDatetimeTaxivaxi;
    }

    public void setPickupDatetimeTaxivaxi(String pickupDatetimeTaxivaxi) {
        this.pickupDatetimeTaxivaxi = pickupDatetimeTaxivaxi;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getPnrNumber() {
        return pnrNumber;
    }

    public void setPnrNumber(String pnrNumber) {
        this.pnrNumber = pnrNumber;
    }

    public String getBusTypeAllocated() {
        return busTypeAllocated;
    }

    public void setBusTypeAllocated(String busTypeAllocated) {
        this.busTypeAllocated = busTypeAllocated;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getIsAssign() {
        return isAssign;
    }

    public void setIsAssign(String isAssign) {
        this.isAssign = isAssign;
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

    public String getAssessmentCity() {
        return assessmentCity;
    }

    public void setAssessmentCity(String assessmentCity) {
        this.assessmentCity = assessmentCity;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<InvoiceDetail> getInvoiceDetail() {
        return invoiceDetail;
    }

    public void setInvoiceDetail(List<InvoiceDetail> invoiceDetail) {
        this.invoiceDetail = invoiceDetail;
    }
}
