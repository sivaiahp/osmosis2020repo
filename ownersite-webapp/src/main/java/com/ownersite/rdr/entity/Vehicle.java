package com.ownersite.rdr.entity;

public class Vehicle {

	private String make;
	private String model;
	private String submodel;
	private java.util.Date year;

	public String getMake(){
		return make;
	}

	public void setMake(String make){
		this.make=make;
	}

	public String getModel(){
		return model;
	}

	public void setModel(String model){
		this.model=model;
	}

	public String getSubmodel(){
		return submodel;
	}

	public void setSubmodel(String submodel){
		this.submodel=submodel;
	}

	public java.util.Date getYear(){
		return year;
	}

	public void setYear(java.util.Date year){
		this.year=year;
	}
}
