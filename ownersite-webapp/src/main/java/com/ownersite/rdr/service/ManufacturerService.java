package com.ownersite.rdr.service;

import java.util.List;

import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.entity.Subscription;

public interface ManufacturerService {
	
	public List<ServiceDTO> getAllServices();
	
	public void addService(ServiceDTO serviceDTO);
	
	public void deleteService(long serviceId);

	public void updateService(ServiceDTO serviceDTO);
	
	ServiceDTO findServiceById(long serviceId);
	
	void createSubscription(Subscription subscription);
	
	void updateSubscription(Subscription subscription);
	
	void deleteSubscription(long subscriptionId);
	
	List<Subscription> getAllSubscriptions();
	
	Subscription findBySubscriptionId(long id);
}
