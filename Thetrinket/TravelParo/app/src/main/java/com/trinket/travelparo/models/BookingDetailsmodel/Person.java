package com.taxivaxi.approver.models.BookingDetailsmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {
    @SerializedName("people_name")
    @Expose
    private String peopleName;
    @SerializedName("people_email")
    @Expose
    private String peopleEmail;
    @SerializedName("people_cid")
    @Expose
    private String peopleCid;
    @SerializedName("people_contact")
    @Expose
    private String peopleContact;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("id_proof_type")
    @Expose
    private String idProofType;
    @SerializedName("id_proof_no")
    @Expose
    private String idProofNo;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("comment_date")
    @Expose
    private String commentDate;

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdProofType() {
        return idProofType;
    }

    public void setIdProofType(String idProofType) {
        this.idProofType = idProofType;
    }

    public String getIdProofNo() {
        return idProofNo;
    }

    public void setIdProofNo(String idProofNo) {
        this.idProofNo = idProofNo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }
}
