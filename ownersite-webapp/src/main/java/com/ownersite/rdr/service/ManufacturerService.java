package com.ownersite.rdr.service;

import java.util.List;

import com.ownersite.rdr.entity.Service;

public interface ManufacturerService {
	
	public List<Service> getAllServices();
	
	public void addService(Service service);
	
	public void deleteService(Long serviceId);

	public void updateService(Service service);
	
	Service findServiceById(Long serviceId);
}
