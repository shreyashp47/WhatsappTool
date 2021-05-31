package com.taxivaxi.thetrinketadmin.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginApiResponse {


    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("error")
    @Expose
    private List<String> error;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }
}
