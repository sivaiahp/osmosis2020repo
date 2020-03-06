package com.ownersite.rdr.service.manufacturer;

import java.util.List;

import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.dto.SubscriptionServiceDTO;
import com.ownersite.rdr.dto.VehiclesDTO;
import com.ownersite.rdr.exception.OwnerSiteException;

public interface SubscriptionService {

	void createSubscription(CustomerSubscriptionDTO customerSubscriptionDTO) throws OwnerSiteException;

	void updateSubscription(CustomerSubscriptionDTO customerSubscriptionDTO) throws OwnerSiteException;

	void deleteSubscription(long subscriptionId) throws OwnerSiteException;

	List<CustomerSubscriptionDTO> getAllSubscriptions() throws OwnerSiteException;

	CustomerSubscriptionDTO findBySubscriptionId(long id) throws OwnerSiteException;

	void updateSubscriptionServices(SubscriptionServiceDTO subscriptionServiceDTO) throws OwnerSiteException;

	List<ServiceDTO> getServicesBySubscription(CustomerSubscriptionDTO customerSubscriptionDTO) throws OwnerSiteException;

    List<VehiclesDTO> getAllVehicles();

}
