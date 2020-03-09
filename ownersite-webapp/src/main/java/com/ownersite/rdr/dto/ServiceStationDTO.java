/**
 * 
 */
package com.ownersite.rdr.dto;

/**
 * @author polamred
 *
 */
public class ServiceStationDTO {
	
	private Long serviceStationId;
	private String firstname;
	private String lastname;
	private String mobile;
	private String email;
	private String address;
	private String city;
	private String state;
	private String country;
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getServiceStationId() {
		return serviceStationId;
	}
	public void setServiceStationId(Long serviceStationId) {
		this.serviceStationId = serviceStationId;
	}
	@Override
	public String toString() {
		return "ServiceStationDTO [serviceStationId=" + serviceStationId + ", firstname=" + firstname + ", lastname="
				+ lastname + ", mobile=" + mobile + ", email=" + email + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", country=" + country + "]";
	}
	
}
