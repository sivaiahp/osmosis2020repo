package com.ownersite.rdr.service.manufacturer;

import java.util.List;

import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.entity.Service;

public interface ServicesService {

	List<ServiceDTO> getAllServices();

	void addService(ServiceDTO serviceDTO);

	void deleteService(long serviceId);

	void updateService(ServiceDTO serviceDTO);

	ServiceDTO findServiceById(long serviceId);

	List<Service> findServicesByIds(List<Long> serviceIds);

}
