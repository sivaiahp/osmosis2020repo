/**
 * 
 */
package com.ownersite.rdr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author polamred
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceDTO {

	private String servicename;
	private String servicedec;
	private Long serviceId;
	
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getServicedec() {
		return servicedec;
	}
	public void setServicedec(String servicedec) {
		this.servicedec = servicedec;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
}
