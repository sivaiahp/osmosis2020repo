package com.ownersite.rdr.service.manufacturer;

import java.util.List;

import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.SubscriptionServiceDTO;

public interface SubscriptionService {
	
	void createSubscription(CustomerSubscriptionDTO customerSubscriptionDTO);
	
	void updateSubscription(CustomerSubscriptionDTO customerSubscriptionDTO);
	
	void deleteSubscription(long subscriptionId);
	
	List<CustomerSubscriptionDTO> getAllSubscriptions();
	
	CustomerSubscriptionDTO findBySubscriptionId(long id);

	void updateSubscriptionServices(SubscriptionServiceDTO subscriptionServiceDTO);
	
}
