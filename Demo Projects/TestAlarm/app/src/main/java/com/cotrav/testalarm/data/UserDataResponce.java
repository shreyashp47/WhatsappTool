package com.cotrav.testalarm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDataResponce {

    @SerializedName("users_id")
    @Expose
    private String usersId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("var_key")
    @Expose
    private Object varKey;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("is_deleted")
    @Expose
    private String isDeleted;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("user_type")
    @Expose
    private String userType;

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getVarKey() {
        return varKey;
    }

    public void setVarKey(Object varKey) {
        this.varKey = varKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
