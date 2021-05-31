package com.taxivaxi.spoc.model.hotelbookingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 3/10/17.
 */

public class Person {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("people_name")
    @Expose
    public String peopleName;
    @SerializedName("people_contact")
    @Expose
    public String peopleContact;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getPeopleContact() {
        return peopleContact;
    }

    public void setPeopleContact(String peopleContact) {
        this.peopleContact = peopleContact;
    }
}
