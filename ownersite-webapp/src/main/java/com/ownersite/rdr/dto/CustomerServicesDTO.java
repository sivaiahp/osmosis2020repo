package com.ownersite.rdr.dto;

public class CustomerServicesDTO {

    private String serviceId;
    private String customerId;
    private String servicename;
    private String servicedec;
    private String servicePrice;
    private String vin;
    private String dealerId;
    private String serviceStationId;
    private String serviceRequestedDate;
    private String serviceStartedDate;
    private String serviceCompletedDate;
    private String serviceCustomerComplaints;
    private String servicecomplaintAnalysis;
    private String serviceRepairsDesc;
    private String serviceRepairsCost;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getServicedec() {
        return servicedec;
    }

    public void setServicedec(String servicedec) {
        this.servicedec = servicedec;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getServiceStationId() {
        return serviceStationId;
    }

    public void setServiceStationId(String serviceStationId) {
        this.serviceStationId = serviceStationId;
    }

    public String getServiceRequestedDate() {
        return serviceRequestedDate;
    }

    public void setServiceRequestedDate(String serviceRequestedDate) {
        this.serviceRequestedDate = serviceRequestedDate;
    }

    public String getServiceStartedDate() {
        return serviceStartedDate;
    }

    public void setServiceStartedDate(String serviceStartedDate) {
        this.serviceStartedDate = serviceStartedDate;
    }

    public String getServiceCompletedDate() {
        return serviceCompletedDate;
    }

    public void setServiceCompletedDate(String serviceCompletedDate) {
        this.serviceCompletedDate = serviceCompletedDate;
    }

    public String getServiceCustomerComplaints() {
        return serviceCustomerComplaints;
    }

    public void setServiceCustomerComplaints(String serviceCustomerComplaints) {
        this.serviceCustomerComplaints = serviceCustomerComplaints;
    }

    public String getServicecomplaintAnalysis() {
        return servicecomplaintAnalysis;
    }

    public void setServicecomplaintAnalysis(String servicecomplaintAnalysis) {
        this.servicecomplaintAnalysis = servicecomplaintAnalysis;
    }

    public String getServiceRepairsDesc() {
        return serviceRepairsDesc;
    }

    public void setServiceRepairsDesc(String serviceRepairsDesc) {
        this.serviceRepairsDesc = serviceRepairsDesc;
    }

    public String getServiceRepairsCost() {
        return serviceRepairsCost;
    }

    public void setServiceRepairsCost(String serviceRepairsCost) {
        this.serviceRepairsCost = serviceRepairsCost;
    }

    @Override
    public String toString() {
        return "CustomerServicesDTO{" +
                "serviceId='" + serviceId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", servicename='" + servicename + '\'' +
                ", servicedec='" + servicedec + '\'' +
                ", servicePrice='" + servicePrice + '\'' +
                ", vin='" + vin + '\'' +
                ", dealerId='" + dealerId + '\'' +
                ", serviceStationId='" + serviceStationId + '\'' +
                ", serviceRequestedDate='" + serviceRequestedDate + '\'' +
                ", serviceStartedDate='" + serviceStartedDate + '\'' +
                ", serviceCompletedDate='" + serviceCompletedDate + '\'' +
                ", serviceCustomerComplaints='" + serviceCustomerComplaints + '\'' +
                ", servicecomplaintAnalysis='" + servicecomplaintAnalysis + '\'' +
                ", serviceRepairsDesc='" + serviceRepairsDesc + '\'' +
                ", serviceRepairsCost='" + serviceRepairsCost + '\'' +
                '}';
    }
}
