package com.ownersite.rdr.service.manufacturer;

import java.util.List;

import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.entity.Service;
import com.ownersite.rdr.exception.OwnerSiteException;

/**
 * The Service interface for managing the services provided by manufacturer
 * 
 * @author basridha
 *
 */
public interface ServicesService {

	/**
	 * Fetches the services
	 * 
	 * @return the list of services
	 */
	List<ServiceDTO> getAllServices();

	/**
	 * Adds the service
	 * 
	 * @param serviceDTO the service to be added
	 */
	void addService(ServiceDTO serviceDTO);

	/**
	 * Deletes the service
	 * 
	 * @param serviceId the id of the service which has to be deleted
	 * @throws OwnerSiteException if service is not found
	 */
	void deleteService(long serviceId) throws OwnerSiteException;

	/**
	 * Update the service
	 * 
	 * @param serviceDTO the service to be updated
	 * @throws OwnerSiteException if service is not available
	 */
	void updateService(ServiceDTO serviceDTO) throws OwnerSiteException;

	/**
	 * Fetch the service based on the input service id
	 * 
	 * @param serviceId the id of the service to be fetched
	 * @return the service associated with given id
	 */
	ServiceDTO findServiceById(long serviceId);

	/**
	 * Fetches the services based on the given list of ids
	 * 
	 * @param serviceIds the ids of the services to be fetched
	 * @return the services associated with the given id's
	 */
	List<Service> findServicesByIds(List<Long> serviceIds);

}
