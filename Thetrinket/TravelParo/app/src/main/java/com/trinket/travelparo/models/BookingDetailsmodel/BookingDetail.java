package com.taxivaxi.approver.models.BookingDetailsmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingDetail {
    @SerializedName("People")
    @Expose
    private List<Person> people = null;

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
