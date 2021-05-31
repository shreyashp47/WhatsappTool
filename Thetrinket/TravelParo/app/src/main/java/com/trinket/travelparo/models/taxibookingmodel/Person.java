package com.trinket.travelparo.models.taxibookingmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 25/9/17.
 */

public class Person {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("people_name")
    @Expose
    public String peopleName;

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
}
