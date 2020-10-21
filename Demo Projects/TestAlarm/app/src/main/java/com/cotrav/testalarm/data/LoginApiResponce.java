package com.cotrav.testalarm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginApiResponce {
    @SerializedName("userData")
    @Expose
    private UserDataResponce userData;

    public UserDataResponce getUserData() {
        return userData;
    }

    public void setUserData(UserDataResponce userData) {
        this.userData = userData;
    }

}
