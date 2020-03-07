package com.ownersite.rdr.service.manufacturer;

import java.util.List;

import com.ownersite.rdr.dto.*;
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

	void updateSubcriptionVehicles(SubscriptionVehicleDTO subscriptionVehicleDTO) throws OwnerSiteException;

	List<ServiceDTO> getVehiclesBySubscription(CustomerSubscriptionDTO customerSubscriptionDTO);
}
