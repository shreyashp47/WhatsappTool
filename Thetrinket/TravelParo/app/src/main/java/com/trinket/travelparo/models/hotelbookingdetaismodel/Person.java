package com.taxivaxi.spoc.model.hotelbookingdetaismodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sandeep on 23/8/17.
 */

public class Person {
    @SerializedName("people_name")
    @Expose
    public String peopleName;
    @SerializedName("people_email")
    @Expose
    public String peopleEmail;
    @SerializedName("people_cid")
    @Expose
    public String peopleCid;
    @SerializedName("people_contact")
    @Expose
    public String peopleContact;
    @SerializedName("age")
    @Expose
    public String age;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("id_proof_type")
    @Expose
    public String idProofType;
    @SerializedName("id_proof_no")
    @Expose
    public String idProofNo;
    @SerializedName("comment")
    @Expose
    public String comment;
    @SerializedName("comment_date")
    @Expose
    public String commentDate;

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
