package com.ownersite.rdr.entity;


import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Cacheable(false)
@Table(name = "CustomerServices")
public class CustomerServices extends BaseEntity{
	private String service_cust_complaints;
	private String service_analysis_desc;
	private String service_repairs_desc;
	private double service_cost;
	private Long customer_Id;
	private Long customerSubId;
	private Long companyServiceId;
	private Long dealer_Id;
	private Long serviceStationId;
	private Long vin;
	private String serviceRequestedDate;
	private Date serviceStartDate;
	private Date serviceCompletedDate;
	
    public Long getCustomerId() {
		return customer_Id;
	}

	public void setCustomerId(Long customerId) {
		this.customer_Id = customerId;
	}

	public Long getCustomerSubId() {
		return customerSubId;
	}

	public void setCustomerSubId(Long customerSubId) {
		this.customerSubId = customerSubId;
	}

	public Long getCompanyServiceId() {
		return companyServiceId;
	}

	public void setCompanyServiceId(Long companyServiceId) {
		this.companyServiceId = companyServiceId;
	}

	public Long getDealerId() {
		return dealer_Id;
	}

	public void setDealerId(Long dealerId) {
		this.dealer_Id = dealerId;
	}

	public Long getServiceStationId() {
		return serviceStationId;
	}

	public void setServiceStationId(Long serviceStationId) {
		this.serviceStationId = serviceStationId;
	}

	public Long getVin() {
		return vin;
	}

	public void setVin(Long vin) {
		this.vin = vin;
	}

	public String getServiceRequestedDate() {
		return serviceRequestedDate;
	}

	public void setServiceRequestedDate(String serviceRequestedDate) {
		this.serviceRequestedDate = serviceRequestedDate;
	}

	public Date getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public Date getServiceCompletedDate() {
		return serviceCompletedDate;
	}

	public void setServiceCompletedDate(Date serviceCompletedDate) {
		this.serviceCompletedDate = serviceCompletedDate;
	}

	@ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", updatable = false, insertable = false)
	private Customer customer;

    @ManyToOne
    @JoinColumn(name = "dealer_id", referencedColumnName = "id", updatable = false, insertable = false)
	private Dealer dealer;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
	private Service service;

	public String getService_cust_complaints(){
		return service_cust_complaints;
	}

	public void setService_cust_complaints(String service_cust_complaints){
		this.service_cust_complaints=service_cust_complaints;
	}

	public String getService_analysis_desc(){
		return service_analysis_desc;
	}

	public void setService_analysis_desc(String service_analysis_desc){
		this.service_analysis_desc=service_analysis_desc;
	}

	public String getService_repairs_desc(){
		return service_repairs_desc;
	}

	public void setService_repairs_desc(String service_repairs_desc){
		this.service_repairs_desc=service_repairs_desc;
	}

	public double getService_cost(){
		return service_cost;
	}

	public void setService_cost(double service_cost){
		this.service_cost=service_cost;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "CustomerServices [service_cust_complaints=" + service_cust_complaints + ", service_analysis_desc="
				+ service_analysis_desc + ", service_repairs_desc=" + service_repairs_desc + ", service_cost="
				+ service_cost + ", customerId=" + customer_Id + ", customerSubId=" + customerSubId
				+ ", companyServiceId=" + companyServiceId + ", dealerId=" + dealer_Id + ", serviceStationId="
				+ serviceStationId + ", vin=" + vin + ", serviceRequestedDate=" + serviceRequestedDate
				+ ", serviceStartDate=" + serviceStartDate + ", serviceCompletedDate=" + serviceCompletedDate
				+ ", customer=" + customer + ", dealer=" + dealer + ", service=" + service + "]";
	}

	
}