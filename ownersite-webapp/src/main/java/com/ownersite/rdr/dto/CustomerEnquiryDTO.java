package com.ownersite.rdr.dto;

import com.ownersite.rdr.entity.Customer;
import com.ownersite.rdr.entity.CustomerEnquiry;
import com.ownersite.rdr.entity.Dealer;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomerEnquiryDTO {

    private String enquiryCreatedDate;
    private String enquiryResolvedDate;
    private String enquiryQuestion;
    private String enquiryAnswer;
    private String customerId;
    private String dealerId;
    private String enquiryId;

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

    public String getEnquiryId() {
        return enquiryId;
    }

    public void setEnquiryId(String enquiryId) {
        this.enquiryId = enquiryId;
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

    public CustomerEnquiry convertToEntity() {
        CustomerEnquiry customerEnquiry = new CustomerEnquiry();
        try {
            customerEnquiry.setId(Long.parseLong(this.enquiryId));
            customerEnquiry.setEnquiry_created_date((new SimpleDateFormat("dd/MM/yyyy").parse(this.enquiryCreatedDate)));
            customerEnquiry.setEnquiry_question(this.enquiryQuestion);
            customerEnquiry.setEnquiry_answer(this.enquiryAnswer);
            customerEnquiry.setEnquiry_resolved_date(new SimpleDateFormat("dd/MM/yyyy").parse(this.enquiryResolvedDate));
            Dealer dealer = new Dealer();
            dealer.setId(Long.parseLong(this.dealerId));
            customerEnquiry.setDealer(dealer);
            Customer customer = new Customer();
            customer.setId(Long.parseLong(this.customerId));
            customerEnquiry.setCustomer(customer);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return customerEnquiry;
    }
}
