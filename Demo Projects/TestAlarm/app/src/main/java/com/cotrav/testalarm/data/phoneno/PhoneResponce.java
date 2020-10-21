package com.cotrav.testalarm.data.phoneno;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhoneResponce {

    @SerializedName("Response")
    @Expose
    private List<PhoneNumbers> response = null;

    public List<PhoneNumbers> getResponse() {
        return response;
    }

    public void setResponse(List<PhoneNumbers> response) {
        this.response = response;
    }


}
