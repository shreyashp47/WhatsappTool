package com.taxivaxi.spoc.model.busbookingdetailsmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 24/8/17.
 */

public class Person {
    @SerializedName("people_name")
    @Expose
    public String peopleName;
    @SerializedName("people_email")
    @Expose
    public String peopleEmail;
    @SerializedName("people_cid")
    @Expose
    public String peopleCid;
    @SerializedName("people_contact")
    @Expose
    public String peopleContact;

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getPeopleEmail() {
        return peopleEmail;
    }

    public void setPeopleEmail(String peopleEmail) {
        this.peopleEmail = peopleEmail;
    }

    public String getPeopleCid() {
        return peopleCid;
    }

    public void setPeopleCid(String peopleCid) {
        this.peopleCid = peopleCid;
    }

    public String getPeopleContact() {
        return peopleContact;
    }

    public void setPeopleContact(String peopleContact) {
        this.peopleContact = peopleContact;
    }
}
