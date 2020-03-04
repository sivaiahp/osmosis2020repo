package com.ownersite.rdr.service.manufacturer;

import java.util.List;

import com.ownersite.rdr.dto.ServiceDTO;

public interface ServicesService {

	List<ServiceDTO> getAllServices();

	void addService(ServiceDTO serviceDTO);

	void deleteService(long serviceId);

	void updateService(ServiceDTO serviceDTO);

	ServiceDTO findServiceById(long serviceId);

}
