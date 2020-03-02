package com.ownersite.rdr.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Cacheable(false)
@Table(name = "ServiceStation")
public class ServiceStation extends BaseEntity{
	private String firstname;
	private String lastname;
	private String mobile;
	private String email;
	private String address;
	private String city;
	private String state;
	private String country;

	public String getFirstname(){
		return firstname;
	}

	public void setFirstname(String firstname){
		this.firstname=firstname;
	}

	public String getLastname(){
		return lastname;
	}

	public void setLastname(String lastname){
		this.lastname=lastname;
	}

	public String getMobile(){
		return mobile;
	}

	public void setMobile(String mobile){
		this.mobile=mobile;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getCity(){
		return city;
	}

	public void setCity(String city){
		this.city=city;
	}

	public String getState(){
		return state;
	}

	public void setState(String state){
		this.state=state;
	}

	public String getCountry(){
		return country;
	}

	public void setCountry(String country){
		this.country=country;
	}
}