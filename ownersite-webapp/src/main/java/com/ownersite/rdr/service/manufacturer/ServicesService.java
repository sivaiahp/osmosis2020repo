package com.ownersite.rdr.service.manufacturer;

import java.util.List;

import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.entity.Service;
import com.ownersite.rdr.exception.OwnerSiteException;

public interface ServicesService {

	List<ServiceDTO> getAllServices();

	void addService(ServiceDTO serviceDTO);

	void deleteService(long serviceId) throws OwnerSiteException;

	void updateService(ServiceDTO serviceDTO) throws OwnerSiteException;

	ServiceDTO findServiceById(long serviceId);

	List<Service> findServicesByIds(List<Long> serviceIds);

}
