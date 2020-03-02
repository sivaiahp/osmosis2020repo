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
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CustomerSubscription{" +
				"subscriptionname='" + subscriptionname + '\'' +
				", subscriptiondec='" + subscriptiondec + '\'' +
				", customer_sub_startdate=" + customer_sub_startdate +
				", customer_sub_enddate=" + customer_sub_enddate +
				'}';
	}
}