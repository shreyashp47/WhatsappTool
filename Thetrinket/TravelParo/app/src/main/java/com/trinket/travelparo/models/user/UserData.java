package com.trinket.travelparo.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("access_token")
    @Expose
    private Object accessToken;
    @SerializedName("admin_id")
    @Expose
    private String adminId;
    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("subgroup_id")
    @Expose
    private String subgroupId;
    @SerializedName("user_cid")
    @Expose
    private String userCid;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_contact")
    @Expose
    private String userContact;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("old_password")
    @Expose
    private Object oldPassword;
    @SerializedName("profile_image")
    @Expose
    private Object profileImage;
    @SerializedName("budget")
    @Expose
    private String budget;
    @SerializedName("expense")
    @Expose
    private String expense;
    @SerializedName("is_radio")
    @Expose
    private String isRadio;
    @SerializedName("is_local")
    @Expose
    private String isLocal;
    @SerializedName("is_outstation")
    @Expose
    private String isOutstation;
    @SerializedName("is_bus")
    @Expose
    private String isBus;
    @SerializedName("is_train")
    @Expose
    private String isTrain;
    @SerializedName("is_hotel")
    @Expose
    private String isHotel;
    @SerializedName("is_meal")
    @Expose
    private String isMeal;
    @SerializedName("is_flight")
    @Expose
    private String isFlight;
    @SerializedName("is_frro")
    @Expose
    private String isFrro;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("has_single_employee")
    @Expose
    private String hasSingleEmployee;
    @SerializedName("fcm_regid")
    @Expose
    private String fcmRegid;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("has_auth_level")
    @Expose
    private String hasAuthLevel;
    @SerializedName("has_assessment_codes")
    @Expose
    private String hasAssessmentCodes;
    @SerializedName("has_billing_spoc_auth_level")
    @Expose
    private String hasBillingSpocAuthLevel;
    @SerializedName("has_billing_admin_auth_level")
    @Expose
    private String hasBillingAdminAuthLevel;
    @SerializedName("logo")
    @Expose
    private String logo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(Object accessToken) {
        this.accessToken = accessToken;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSubgroupId() {
        return subgroupId;
    }

    public void setSubgroupId(String subgroupId) {
        this.subgroupId = subgroupId;
    }

    public String getUserCid() {
        return userCid;
    }

    public void setUserCid(String userCid) {
        this.userCid = userCid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(Object oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Object getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Object profileImage) {
        this.profileImage = profileImage;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getIsRadio() {
        return isRadio;
    }

    public void setIsRadio(String isRadio) {
        this.isRadio = isRadio;
    }

    public String getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(String isLocal) {
        this.isLocal = isLocal;
    }

    public String getIsOutstation() {
        return isOutstation;
    }

    public void setIsOutstation(String isOutstation) {
        this.isOutstation = isOutstation;
    }

    public String getIsBus() {
        return isBus;
    }

    public void setIsBus(String isBus) {
        this.isBus = isBus;
    }

    public String getIsTrain() {
        return isTrain;
    }

    public void setIsTrain(String isTrain) {
        this.isTrain = isTrain;
    }

    public String getIsHotel() {
        return isHotel;
    }

    public void setIsHotel(String isHotel) {
        this.isHotel = isHotel;
    }

    public String getIsMeal() {
        return isMeal;
    }

    public void setIsMeal(String isMeal) {
        this.isMeal = isMeal;
    }

    public String getIsFlight() {
        return isFlight;
    }

    public void setIsFlight(String isFlight) {
        this.isFlight = isFlight;
    }

    public String getIsFrro() {
        return isFrro;
    }

    public void setIsFrro(String isFrro) {
        this.isFrro = isFrro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHasSingleEmployee() {
        return hasSingleEmployee;
    }

    public void setHasSingleEmployee(String hasSingleEmployee) {
        this.hasSingleEmployee = hasSingleEmployee;
    }

    public String getFcmRegid() {
        return fcmRegid;
    }

    public void setFcmRegid(String fcmRegid) {
        this.fcmRegid = fcmRegid;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getHasAuthLevel() {
        return hasAuthLevel;
    }

    public void setHasAuthLevel(String hasAuthLevel) {
        this.hasAuthLevel = hasAuthLevel;
    }

    public String getHasAssessmentCodes() {
        return hasAssessmentCodes;
    }

    public void setHasAssessmentCodes(String hasAssessmentCodes) {
        this.hasAssessmentCodes = hasAssessmentCodes;
    }

    public String getHasBillingSpocAuthLevel() {
        return hasBillingSpocAuthLevel;
    }

    public void setHasBillingSpocAuthLevel(String hasBillingSpocAuthLevel) {
        this.hasBillingSpocAuthLevel = hasBillingSpocAuthLevel;
    }

    public String getHasBillingAdminAuthLevel() {
        return hasBillingAdminAuthLevel;
    }

    public void setHasBillingAdminAuthLevel(String hasBillingAdminAuthLevel) {
        this.hasBillingAdminAuthLevel = hasBillingAdminAuthLevel;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
