package com.ownersite.rdr.service.manufacturer;

import java.util.List;

import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.ReportDTO;
import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.dto.SubscriptionServiceDTO;
import com.ownersite.rdr.dto.SubscriptionVehicleDTO;
import com.ownersite.rdr.dto.VehiclesDTO;
import com.ownersite.rdr.exception.OwnerSiteException;

/**
 * The service interface for managing the Subcsriptions provided by manufacturer
 * and also generating data for analytics report
 * 
 * @author basridha
 *
 */
public interface SubscriptionService {

	/**
	 * Adds a new subscription
	 * 
	 * @param customerSubscriptionDTO the subscription to be created
	 * @throws OwnerSiteException if any error while adding the subscription
	 */
	void createSubscription(CustomerSubscriptionDTO customerSubscriptionDTO) throws OwnerSiteException;

	/**
	 * Updates the existing subscription
	 * 
	 * @param customerSubscriptionDTO the subscription to be updated
	 * @throws OwnerSiteException if any error while updating the subscription
	 */
	void updateSubscription(CustomerSubscriptionDTO customerSubscriptionDTO) throws OwnerSiteException;

	/**
	 * Deletes the subscription based on the given id
	 * 
	 * @param subscriptionId the id of the subscription to be deleted
	 * @throws OwnerSiteException if any error while deleting the subscription
	 */
	void deleteSubscription(long subscriptionId) throws OwnerSiteException;

	/**
	 * Fetches all the subscriptions
	 * 
	 * @return all the subscriptions
	 * @throws OwnerSiteException if any error while fetching the subcriptions
	 */
	List<CustomerSubscriptionDTO> getAllSubscriptions() throws OwnerSiteException;

	/**
	 * Fetch the subscription based on the given id
	 * 
	 * @param id the id of the subscription to be fetched
	 * @return the subscription
	 * @throws OwnerSiteException if any error while fetching the subscription
	 */
	CustomerSubscriptionDTO findBySubscriptionId(long id) throws OwnerSiteException;

	/**
	 * Updates the services associated with a subscription
	 * 
	 * @param subscriptionServiceDTO contains the subscriptions and the id to
	 *                               services to be associated to the subsciption
	 * @throws OwnerSiteException if any exception while updating the service
	 *                            against subscription
	 */
	void updateSubscriptionServices(SubscriptionServiceDTO subscriptionServiceDTO) throws OwnerSiteException;

	/**
	 * Fetches the services associated with the subscription
	 * 
	 * @param customerSubscriptionDTO contains the id of the subscription
	 * @return the services associated with the given subscription
	 * @throws OwnerSiteException if any exception while fetching the services
	 *                            associated with the given subscription
	 */
	List<ServiceDTO> getServicesBySubscription(CustomerSubscriptionDTO customerSubscriptionDTO)
			throws OwnerSiteException;

	/**
	 * Fetches all the vehicles
	 * 
	 * @return all the vehicles
	 */
	List<VehiclesDTO> getAllVehicles();

	/**
	 * Updates the vehicles associated with the subscription
	 * 
	 * @param subscriptionVehicleDTO contains the subscription and the id of the
	 *                               services to be associated with that
	 *                               subscription
	 * @throws OwnerSiteException if any exception while updating the vehicles
	 *                            against the given subscription
	 */
	void updateSubcriptionVehicles(SubscriptionVehicleDTO subscriptionVehicleDTO) throws OwnerSiteException;

	/**
	 * Generates the report data of subscriptions purchased monthly
	 * 
	 * @return the report data
	 */
	ReportDTO generateSubscriptionsPerMonthReport();

	/**
	 * Generates the report data of the subscriptions purchased monthly based on
	 * subscriptions
	 * 
	 * @return the report data
	 */
	ReportDTO generateMonthlySubscriptionsPerSubcriptionReport();

	/**
	 * Fetches the vehicles associated with the subscription
	 * 
	 * @param customerSubscriptionDTO contains the id of the subscription for which
	 *                                its associated vehicles to be fetched
	 * @return the vehicles associated to the given subscription
	 */
	List<VehiclesDTO> getVehiclesBySubscription(CustomerSubscriptionDTO customerSubscriptionDTO);

	/**
	 * Generates the report data for the number subscriptions availed monthly for
	 * all the subscriptions
	 * 
	 * @return the report data
	 */
	ReportDTO generateSubscriptions();

}
