package com.taxivaxi.spoc.model.hotelbookingdetaismodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sandeep on 23/8/17.
 */

public class HotelBookingDetail {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("reference_no")
    @Expose
    public String referenceNo;
    @SerializedName("operator_name")
    @Expose
    public String operatorName;
    @SerializedName("room_type")
    @Expose
    public String roomType;
    @SerializedName("room_type_2")
    @Expose
    public String roomType2;
    @SerializedName("new_bucket_requested")
    @Expose
    public String newBucketRequested;
    @SerializedName("reason_bucket_approval")
    @Expose
    public String reasonBucketApproval;
    @SerializedName("request_bucket_time")
    @Expose
    public String requestBucketTime;
    @SerializedName("bucket_approved_time")
    @Expose
    public String bucketApprovedTime;
    @SerializedName("city_id")
    @Expose
    public String cityId;
    @SerializedName("from_city")
    @Expose
    public String fromCity;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("arrival_datetime")
    @Expose
    public String arrivalDatetime;
    @SerializedName("booking_date")
    @Expose
    public String bookingDate;
    @SerializedName("dep_datetime")
    @Expose
    public String depDatetime;
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
    @SerializedName("is_cancelled")
    @Expose
    public String isCancelled;
    @SerializedName("cancelled_by")
    @Expose
    public String cancelledBy;
    @SerializedName("cancel_date")
    @Expose
    public String cancelDate;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("modified")
    @Expose
    public String modified;
    @SerializedName("days")
    @Expose
    public String days;
    @SerializedName("is_assign")
    @Expose
    public String isAssign;
    @SerializedName("is_invoice")
    @Expose
    public String isInvoice;
    @SerializedName("invoice_id")
    @Expose
    public String invoiceId;
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
    @SerializedName("user_id")
    @Expose
    public String userId;
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
    @SerializedName("spoc_cancel_date")
    @Expose
    public String spocCancelDate;
    @SerializedName("spoc_cancel_reason")
    @Expose
    public String spocCancelReason;
    @SerializedName("auth_accept_time")
    @Expose
    public String authAcceptTime;
    @SerializedName("auth_reject_time")
    @Expose
    public String authRejectTime;
    @SerializedName("auth_reject_reason")
    @Expose
    public String authRejectReason;
    @SerializedName("taxivaxi_accept_time")
    @Expose
    public String taxivaxiAcceptTime;
    @SerializedName("taxivaxi_reject_time")
    @Expose
    public String taxivaxiRejectTime;
    @SerializedName("taxivaxi_assign_time")
    @Expose
    public String taxivaxiAssignTime;
    @SerializedName("taxivaxi_reject_reason")
    @Expose
    public String taxivaxiRejectReason;
    @SerializedName("taxivaxi_comment")
    @Expose
    public String taxivaxiComment;
    @SerializedName("arrival_details")
    @Expose
    public String arrivalDetails;
    @SerializedName("preferred_hotel")
    @Expose
    public String preferredHotel;
    @SerializedName("preferred_hotel_address")
    @Expose
    public String preferredHotelAddress;
    @SerializedName("assigned_hotel")
    @Expose
    public String assignedHotel;
    @SerializedName("name_mainoe")
    @Expose
    public String nameMainoe;
    @SerializedName("contact_1")
    @Expose
    public String contact1;
    @SerializedName("daily_breakfast")
    @Expose
    public String dailyBreakfast;
    @SerializedName("nearby_location")
    @Expose
    public String nearbyLocation;
    @SerializedName("assigned_room_type")
    @Expose
    public String assignedRoomType;
    @SerializedName("assigned_hotel_address")
    @Expose
    public String assignedHotelAddress;
    @SerializedName("hotel_contact")
    @Expose
    public String hotelContact;
    @SerializedName("room_no")
    @Expose
    public String roomNo;
    @SerializedName("is_ac_room")
    @Expose
    public String isAcRoom;
    @SerializedName("voucher_number")
    @Expose
    public String voucherNumber;
    @SerializedName("room_occupancy")
    @Expose
    public String roomOccupancy;
    @SerializedName("extra_bed")
    @Expose
    public String extraBed;
    @SerializedName("portal_used")
    @Expose
    public String portalUsed;
    @SerializedName("hotel_email")
    @Expose
    public String hotelEmail;
    @SerializedName("agent_id")
    @Expose
    public String agentId;
    @SerializedName("assessment_city")
    @Expose
    public String assessmentCity;
    @SerializedName("is_prepaid_booking")
    @Expose
    public String isPrepaidBooking;
    @SerializedName("is_prepaid_payment_verified")
    @Expose
    public String isPrepaidPaymentVerified;
    @SerializedName("is_payment_complete")
    @Expose
    public String isPaymentComplete;
    @SerializedName("room_type_name")
    @Expose
    public String roomTypeName;
    @SerializedName("room_type_2_name")
    @Expose
    public String roomType2Name;
    @SerializedName("price_1")
    @Expose
    public String price1;
    @SerializedName("price_2")
    @Expose
    public String price2;
    @SerializedName("assigned_bucket_price_1")
    @Expose
    public String assignedBucketPrice1;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("user_email")
    @Expose
    public String userEmail;
    @SerializedName("user_contact")
    @Expose
    public String userContact;
    @SerializedName("total")
    @Expose
    public String total;
    @SerializedName("service_tax_paid")
    @Expose
    public String serviceTaxPaid;
    @SerializedName("taxivaxi_charge")
    @Expose
    public String taxivaxiCharge;
    @SerializedName("taxivaxi_tax_charge")
    @Expose
    public String taxivaxiTaxCharge;
    @SerializedName("paid_by_taxivaxi")
    @Expose
    public String paidByTaxivaxi;
    @SerializedName("sub_total")
    @Expose
    public String subTotal;
    @SerializedName("voucher")
    @Expose
    public String voucher;
    @SerializedName("seller_voucher")
    @Expose
    public String sellerVoucher;
    @SerializedName("bucket_price")
    @Expose
    public String bucketPrice;
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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomType2() {
        return roomType2;
    }

    public void setRoomType2(String roomType2) {
        this.roomType2 = roomType2;
    }

    public String getNewBucketRequested() {
        return newBucketRequested;
    }

    public void setNewBucketRequested(String newBucketRequested) {
        this.newBucketRequested = newBucketRequested;
    }

    public String getReasonBucketApproval() {
        return reasonBucketApproval;
    }

    public void setReasonBucketApproval(String reasonBucketApproval) {
        this.reasonBucketApproval = reasonBucketApproval;
    }

    public String getRequestBucketTime() {
        return requestBucketTime;
    }

    public void setRequestBucketTime(String requestBucketTime) {
        this.requestBucketTime = requestBucketTime;
    }

    public String getBucketApprovedTime() {
        return bucketApprovedTime;
    }

    public void setBucketApprovedTime(String bucketApprovedTime) {
        this.bucketApprovedTime = bucketApprovedTime;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
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

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getDepDatetime() {
        return depDatetime;
    }

    public void setDepDatetime(String depDatetime) {
        this.depDatetime = depDatetime;
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

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getAuthRejectReason() {
        return authRejectReason;
    }

    public void setAuthRejectReason(String authRejectReason) {
        this.authRejectReason = authRejectReason;
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

    public String getTaxivaxiRejectReason() {
        return taxivaxiRejectReason;
    }

    public void setTaxivaxiRejectReason(String taxivaxiRejectReason) {
        this.taxivaxiRejectReason = taxivaxiRejectReason;
    }

    public String getTaxivaxiComment() {
        return taxivaxiComment;
    }

    public void setTaxivaxiComment(String taxivaxiComment) {
        this.taxivaxiComment = taxivaxiComment;
    }

    public String getArrivalDetails() {
        return arrivalDetails;
    }

    public void setArrivalDetails(String arrivalDetails) {
        this.arrivalDetails = arrivalDetails;
    }

    public String getPreferredHotel() {
        return preferredHotel;
    }

    public void setPreferredHotel(String preferredHotel) {
        this.preferredHotel = preferredHotel;
    }

    public String getPreferredHotelAddress() {
        return preferredHotelAddress;
    }

    public void setPreferredHotelAddress(String preferredHotelAddress) {
        this.preferredHotelAddress = preferredHotelAddress;
    }

    public String getAssignedHotel() {
        return assignedHotel;
    }

    public void setAssignedHotel(String assignedHotel) {
        this.assignedHotel = assignedHotel;
    }

    public String getNameMainoe() {
        return nameMainoe;
    }

    public void setNameMainoe(String nameMainoe) {
        this.nameMainoe = nameMainoe;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getDailyBreakfast() {
        return dailyBreakfast;
    }

    public void setDailyBreakfast(String dailyBreakfast) {
        this.dailyBreakfast = dailyBreakfast;
    }

    public String getNearbyLocation() {
        return nearbyLocation;
    }

    public void setNearbyLocation(String nearbyLocation) {
        this.nearbyLocation = nearbyLocation;
    }

    public String getAssignedRoomType() {
        return assignedRoomType;
    }

    public void setAssignedRoomType(String assignedRoomType) {
        this.assignedRoomType = assignedRoomType;
    }

    public String getAssignedHotelAddress() {
        return assignedHotelAddress;
    }

    public void setAssignedHotelAddress(String assignedHotelAddress) {
        this.assignedHotelAddress = assignedHotelAddress;
    }

    public String getHotelContact() {
        return hotelContact;
    }

    public void setHotelContact(String hotelContact) {
        this.hotelContact = hotelContact;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getIsAcRoom() {
        return isAcRoom;
    }

    public void setIsAcRoom(String isAcRoom) {
        this.isAcRoom = isAcRoom;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getRoomOccupancy() {
        return roomOccupancy;
    }

    public void setRoomOccupancy(String roomOccupancy) {
        this.roomOccupancy = roomOccupancy;
    }

    public String getExtraBed() {
        return extraBed;
    }

    public void setExtraBed(String extraBed) {
        this.extraBed = extraBed;
    }

    public String getPortalUsed() {
        return portalUsed;
    }

    public void setPortalUsed(String portalUsed) {
        this.portalUsed = portalUsed;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAssessmentCity() {
        return assessmentCity;
    }

    public void setAssessmentCity(String assessmentCity) {
        this.assessmentCity = assessmentCity;
    }

    public String getIsPrepaidBooking() {
        return isPrepaidBooking;
    }

    public void setIsPrepaidBooking(String isPrepaidBooking) {
        this.isPrepaidBooking = isPrepaidBooking;
    }

    public String getIsPrepaidPaymentVerified() {
        return isPrepaidPaymentVerified;
    }

    public void setIsPrepaidPaymentVerified(String isPrepaidPaymentVerified) {
        this.isPrepaidPaymentVerified = isPrepaidPaymentVerified;
    }

    public String getIsPaymentComplete() {
        return isPaymentComplete;
    }

    public void setIsPaymentComplete(String isPaymentComplete) {
        this.isPaymentComplete = isPaymentComplete;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getRoomType2Name() {
        return roomType2Name;
    }

    public void setRoomType2Name(String roomType2Name) {
        this.roomType2Name = roomType2Name;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public String getAssignedBucketPrice1() {
        return assignedBucketPrice1;
    }

    public void setAssignedBucketPrice1(String assignedBucketPrice1) {
        this.assignedBucketPrice1 = assignedBucketPrice1;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getServiceTaxPaid() {
        return serviceTaxPaid;
    }

    public void setServiceTaxPaid(String serviceTaxPaid) {
        this.serviceTaxPaid = serviceTaxPaid;
    }

    public String getTaxivaxiCharge() {
        return taxivaxiCharge;
    }

    public void setTaxivaxiCharge(String taxivaxiCharge) {
        this.taxivaxiCharge = taxivaxiCharge;
    }

    public String getTaxivaxiTaxCharge() {
        return taxivaxiTaxCharge;
    }

    public void setTaxivaxiTaxCharge(String taxivaxiTaxCharge) {
        this.taxivaxiTaxCharge = taxivaxiTaxCharge;
    }

    public String getPaidByTaxivaxi() {
        return paidByTaxivaxi;
    }

    public void setPaidByTaxivaxi(String paidByTaxivaxi) {
        this.paidByTaxivaxi = paidByTaxivaxi;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getSellerVoucher() {
        return sellerVoucher;
    }

    public void setSellerVoucher(String sellerVoucher) {
        this.sellerVoucher = sellerVoucher;
    }

    public String getBucketPrice() {
        return bucketPrice;
    }

    public void setBucketPrice(String bucketPrice) {
        this.bucketPrice = bucketPrice;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
