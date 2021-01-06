package com.taxivaxi.operator.model.archivedbooking;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Passanger {


    @SerializedName("admin_id")
    @Expose
    private String adminId;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("fcm_regid")
    @Expose
    private String fcmRegid;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("has_dummy_email")
    @Expose
    private String hasDummyEmail;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_proof_no")
    @Expose
    private String idProofNo;
    @SerializedName("id_proof_type")
    @Expose
    private String idProofType;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("people_cid")
    @Expose
    private String peopleCid;
    @SerializedName("people_contact")
    @Expose
    public String peopleContact;
    @SerializedName("people_email")
    @Expose
    private String peopleEmail;
    @SerializedName("people_name")
    @Expose
    public String peopleName;
    @SerializedName("subgroup_id")
    @Expose
    private String subgroupId;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getFcmRegid() {
        return fcmRegid;
    }

    public void setFcmRegid(String fcmRegid) {
        this.fcmRegid = fcmRegid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getHasDummyEmail() {
        return hasDummyEmail;
    }

    public void setHasDummyEmail(String hasDummyEmail) {
        this.hasDummyEmail = hasDummyEmail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdProofNo() {
        return idProofNo;
    }

    public void setIdProofNo(String idProofNo) {
        this.idProofNo = idProofNo;
    }

    public String getIdProofType() {
        return idProofType;
    }

    public void setIdProofType(String idProofType) {
        this.idProofType = idProofType;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
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

    public String getPeopleEmail() {
        return peopleEmail;
    }

    public void setPeopleEmail(String peopleEmail) {
        this.peopleEmail = peopleEmail;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getSubgroupId() {
        return subgroupId;
    }

    public void setSubgroupId(String subgroupId) {
        this.subgroupId = subgroupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
