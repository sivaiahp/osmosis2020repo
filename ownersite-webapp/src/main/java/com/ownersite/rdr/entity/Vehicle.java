package com.ownersite.rdr.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Cacheable(false)
@Table(name = "vehicle")
public class Vehicle extends BaseEntity{

	private String make;
	private String model;
	private String submodel;
	private String year;

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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Vehicle{" +
				"make='" + make + '\'' +
				", model='" + model + '\'' +
				", submodel='" + submodel + '\'' +
				", year=" + year +
				'}';
	}
}
