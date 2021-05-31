package com.trinket.travelparo.models.rejectbookingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 6/10/17.
 */

public class RejectResponse {
    @SerializedName("booking_id")
    @Expose
    public String bookingId;
    @SerializedName("message")
    @Expose
    public String message;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
