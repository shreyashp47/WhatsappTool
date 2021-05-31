package com.taxivaxi.spoc.model;

/**
 * Created by sandeep on 25/8/17.
 */

public class FeedbackModel {

    String employeeName;
    String feedback;
    String feedbackDate;

    public FeedbackModel(String employeeName, String feedback, String feedbackDate) {
        this.employeeName = employeeName;
        this.feedback = feedback;
        this.feedbackDate = feedbackDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}
