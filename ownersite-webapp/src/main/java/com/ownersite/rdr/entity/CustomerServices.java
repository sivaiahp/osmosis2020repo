package com.ownersite.rdr.entity;


import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Cacheable(false)
@Table(name = "CustomerServices")
public class CustomerServices extends BaseEntity{
	private String service_cust_complaints;
	private String service_analysis_desc;
	private String service_repairs_desc;
	private double service_cost;

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
}