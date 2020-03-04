package com.ownersite.rdr.service;

import java.util.List;

import com.ownersite.rdr.entity.Service;
import com.ownersite.rdr.entity.Subscription;

public interface ManufacturerService {
	
	public List<Service> getAllServices();
	
	public void addService(Service service);
	
	public void deleteService(long serviceId);

	public void updateService(Service service);
	
	Service findServiceById(long serviceId);
	
	void createSubscription(Subscription subscription);
	
	void updateSubscription(Subscription subscription);
	
	void deleteSubscription(long subscriptionId);
	
	List<Subscription> getAllSubscriptions();
	
	Subscription findBySubscriptionId(long id);
}
