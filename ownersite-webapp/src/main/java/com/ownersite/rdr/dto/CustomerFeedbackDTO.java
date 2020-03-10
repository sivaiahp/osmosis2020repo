package com.ownersite.rdr.dto;


public class CustomerFeedbackDTO {

    private String enquiryCreatedDate;
    private String enquiryResolvedDate;
    private String enquiryQuestion;
    private String enquiryAnswer;
    private String customerId;
    private String dealerId;
    private String customerFeedbackId;

    public String getEnquiryCreatedDate() {
        return enquiryCreatedDate;
    }

    public void setEnquiryCreatedDate(String enquiryCreatedDate) {
        this.enquiryCreatedDate = enquiryCreatedDate;
    }

    public String getEnquiryResolvedDate() {
        return enquiryResolvedDate;
    }

    public void setEnquiryResolvedDate(String enquiryResolvedDate) {
        this.enquiryResolvedDate = enquiryResolvedDate;
    }

    public String getEnquiryQuestion() {
        return enquiryQuestion;
    }

    public void setEnquiryQuestion(String enquiryQuestion) {
        this.enquiryQuestion = enquiryQuestion;
    }

    public String getEnquiryAnswer() {
        return enquiryAnswer;
    }

    public void setEnquiryAnswer(String enquiryAnswer) {
        this.enquiryAnswer = enquiryAnswer;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getCustomerFeedbackId() {
        return customerFeedbackId;
    }

    public void setCustomerFeedbackId(String customerFeedbackId) {
        this.customerFeedbackId = customerFeedbackId;
    }

    @Override
    public String toString() {
        return "CustomerEnquiryDTO{" +
                "enquiryCreatedDate='" + enquiryCreatedDate + '\'' +
                ", enquiryResolvedDate='" + enquiryResolvedDate + '\'' +
                ", enquiryQuestion='" + enquiryQuestion + '\'' +
                ", enquiryAnswer='" + enquiryAnswer + '\'' +
                ", customerId='" + customerId + '\'' +
                ", dealerId='" + dealerId + '\'' +
                '}';
    }
}
