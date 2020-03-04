package com.ownersite.rdr.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Cacheable(false)
@Table(name = "Vehicle")
public class Vehicle extends BaseEntity {

	private String make;
	private String model;
	private String submodel;
	private java.util.Date year;
	private Long customerId;
	private Long dealerId;
	private Long vin;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public Long getVin() {
		return vin;
	}

	public void setVin(Long vin) {
		this.vin = vin;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSubmodel() {
		return submodel;
	}

	public void setSubmodel(String submodel) {
		this.submodel = submodel;
	}

	public java.util.Date getYear() {
		return year;
	}

	public void setYear(java.util.Date year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Vehicle [make=" + make + ", model=" + model + ", submodel=" + submodel + ", year=" + year
				+ ", customerId=" + customerId + ", dealerId=" + dealerId + ", vin=" + vin + "]";
	}

	
}
