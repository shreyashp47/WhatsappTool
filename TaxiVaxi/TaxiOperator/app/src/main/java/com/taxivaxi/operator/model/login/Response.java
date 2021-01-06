package com.taxivaxi.operator.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Response {
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("operator")
    @Expose
    public Operator operator;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
