package com.trinket.travelparo.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.trinket.travelparo.models.user.UserData;

public class UserResponse {

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("user")
    @Expose
    private UserData user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

}
