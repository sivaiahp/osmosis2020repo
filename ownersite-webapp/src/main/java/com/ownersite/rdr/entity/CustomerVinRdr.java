package com.ownersite.rdr.entity;

import javax.persistence.*;

@Entity
@Cacheable(false)
@Table(name = "CustomerVinRdr")
public class CustomerVinRdr extends BaseEntity{
	private java.util.Date rdr_confirmed_date;
	
	private Long customer_Id;
	private Long vin;
	
	public Long getCustomerId() {
		return customer_Id;
	}

	public void setCustomerId(Long customerId) {
		this.customer_Id = customerId;
	}

	public Long getVin() {
		return vin;
	}

	public void setVin(Long vin) {
		this.vin = vin;
	}

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id", updatable = false, insertable = false)
	private Customer customer;

	public java.util.Date getRdr_confirmed_date(){
		return rdr_confirmed_date;
	}

	public void setRdr_confirmed_date(java.util.Date rdr_confirmed_date){
		this.rdr_confirmed_date=rdr_confirmed_date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CustomerVinRdr [rdr_confirmed_date=" + rdr_confirmed_date + ", customerId=" + customer_Id + ", vin="
				+ vin + ", customer=" + customer + "]";
	}

	
}