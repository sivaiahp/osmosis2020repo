package com.ownersite.rdr.entity;


import javax.persistence.*;

@Entity
@Cacheable(false)
@Table(name = "CustomerSubscription")
public class CustomerSubscription extends BaseEntity{
	private String subscriptionname;
	private String subscriptiondec;
	private java.util.Date customer_sub_startdate;
	private java.util.Date customer_sub_enddate;
	private String vin;
	private Double price;
	private Long customerId;
	private Long customerSubId;
	private Long primarySubId;
	private Long secondarySubId;
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCustomerSubId() {
		return customerSubId;
	}

	public void setCustomerSubId(Long customerSubId) {
		this.customerSubId = customerSubId;
	}

	public Long getPrimarySubId() {
		return primarySubId;
	}

	public void setPrimarySubId(Long primarySubId) {
		this.primarySubId = primarySubId;
	}

	public Long getSecondarySubId() {
		return secondarySubId;
	}

	public void setSecondarySubId(Long secondarySubId) {
		this.secondarySubId = secondarySubId;
	}

	@ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "subscription_id")
    Subscription subscription;

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public String getSubscriptionname(){
		return subscriptionname;
	}

	public void setSubscriptionname(String subscriptionname){
		this.subscriptionname=subscriptionname;
	}

	public String getSubscriptiondec(){
		return subscriptiondec;
	}

	public void setSubscriptiondec(String subscriptiondec){
		this.subscriptiondec=subscriptiondec;
	}

	public java.util.Date getCustomer_sub_startdate(){
		return customer_sub_startdate;
	}

	public void setCustomer_sub_startdate(java.util.Date customer_sub_startdate){
		this.customer_sub_startdate=customer_sub_startdate;
	}

	public java.util.Date getCustomer_sub_enddate(){
		return customer_sub_enddate;
	}

	public void setCustomer_sub_enddate(java.util.Date customer_sub_enddate){
		this.customer_sub_enddate=customer_sub_enddate;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CustomerSubscription [subscriptionname=" + subscriptionname + ", subscriptiondec=" + subscriptiondec
				+ ", customer_sub_startdate=" + customer_sub_startdate + ", customer_sub_enddate="
				+ customer_sub_enddate + ", vin=" + vin + ", price=" + price + ", customerId=" + customerId
				+ ", customerSubId=" + customerSubId + ", primarySubId=" + primarySubId + ", secondarySubId="
				+ secondarySubId + ", customer=" + customer + ", subscription=" + subscription + "]";
	}

	
}