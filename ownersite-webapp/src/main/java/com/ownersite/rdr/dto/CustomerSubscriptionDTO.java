package com.ownersite.rdr.dto;

import com.ownersite.rdr.entity.CustomerSubscription;

public class CustomerSubscriptionDTO {

    private String subscriptionId;
    private String subscriptionName;
    private String subscriptionDesc;
    private String subscriptionPrice;
    private String vin;
    private String subscriptionStartDate;
    private String subscriptionEndDate;

    public CustomerSubscriptionDTO() {
    }



    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public String getSubscriptionDesc() {
        return subscriptionDesc;
    }

    public void setSubscriptionDesc(String subscriptionDesc) {
        this.subscriptionDesc = subscriptionDesc;
    }

    public String getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public void setSubscriptionPrice(String subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(String subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public String getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(String subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    @Override
    public String toString() {
        return "CustomerSubscriptionDTO{" +
                "subscriptionId='" + subscriptionId + '\'' +
                ", subscriptionName='" + subscriptionName + '\'' +
                ", subscriptionDesc='" + subscriptionDesc + '\'' +
                ", subscriptionPrice='" + subscriptionPrice + '\'' +
                ", vin='" + vin + '\'' +
                ", subscriptionStartDate='" + subscriptionStartDate + '\'' +
                ", subscriptionEndDate='" + subscriptionEndDate + '\'' +
                '}';
    }
}
