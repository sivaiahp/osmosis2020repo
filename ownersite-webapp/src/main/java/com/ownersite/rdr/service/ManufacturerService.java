package com.ownersite.rdr.service;

import java.util.List;

import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.ServiceDTO;

public interface ManufacturerService {
	
	List<ServiceDTO> getAllServices();
	
	void addService(ServiceDTO serviceDTO);
	
	void deleteService(long serviceId);

	void updateService(ServiceDTO serviceDTO);
	
	ServiceDTO findServiceById(long serviceId);
	
	void createSubscription(CustomerSubscriptionDTO customerSubscriptionDTO);
	
	void updateSubscription(CustomerSubscriptionDTO customerSubscriptionDTO);
	
	void deleteSubscription(long subscriptionId);
	
	List<CustomerSubscriptionDTO> getAllSubscriptions();
	
	CustomerSubscriptionDTO findBySubscriptionId(long id);
}
