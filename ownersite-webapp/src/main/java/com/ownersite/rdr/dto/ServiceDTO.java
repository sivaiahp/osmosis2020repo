/**
 * 
 */
package com.ownersite.rdr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ownersite.rdr.entity.Service;

/**
 * @author polamred
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceDTO {

	private String servicename;
	private String servicedec;
	private Long serviceId;
	private String servicePrice;

	public ServiceDTO() {
		super();
	}

	public ServiceDTO(Service service) {
		super();
		this.setServiceId(service.getId());
		this.setServicename(service.getServicename());
		this.setServicedec(service.getServicedec());
	}

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

	public String getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}

	public Service convertToEntity(ServiceDTO serviceDTO) {
		Service service = new Service();
		service.setId(serviceDTO.getServiceId());
		service.setServicename(serviceDTO.getServicename());
		service.setServicedec(serviceDTO.getServicedec());

		return service;
	}

}
