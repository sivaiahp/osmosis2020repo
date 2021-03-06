/**
 * 
 */
package com.ownersite.rdr.service.manufacturer;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.exception.OwnerSiteException;
import com.ownersite.rdr.repository.ServicesJpaRepository;

/**
 * The implementation class of {@link ServicesService}
 * 
 * @author polamred
 * @author basridha
 *
 */
@Service
public class ServicesServiceImpl implements ServicesService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServicesServiceImpl.class);

	private final ServicesJpaRepository servicesJpaRepository;

	@Autowired
	public ServicesServiceImpl(ServicesJpaRepository servicesJpaRepository) {
		this.servicesJpaRepository = servicesJpaRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownersite.rdr.service.manufacturer.ServicesService#getAllServices()
	 */
	@Override
	public List<ServiceDTO> getAllServices() {
		LOGGER.info("Fetching all servcies");

		List<com.ownersite.rdr.entity.Service> listservices = servicesJpaRepository.findAll();

		LOGGER.info("Fetched all services successfully");

		return listservices.stream().map(ServiceDTO::new).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownersite.rdr.service.manufacturer.ServicesService#addService(com.
	 * ownersite.rdr.dto.ServiceDTO)
	 */
	@Override
	public void addService(ServiceDTO serviceDTO) {
		LOGGER.info("Creating service for {}", serviceDTO);

		servicesJpaRepository.save(serviceDTO.convertToEntity(serviceDTO));

		LOGGER.info("Created service {} successfully", serviceDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownersite.rdr.service.manufacturer.ServicesService#deleteService(long)
	 */
	@Override
	public void deleteService(long serviceId) throws OwnerSiteException {
		LOGGER.info("Deleting service by id {}", serviceId);

		com.ownersite.rdr.entity.Service service = servicesJpaRepository.findByServiceId(serviceId);

		if (service == null) {
			throw new OwnerSiteException("Invalid service id");
		}

		if (service.getSubscriptionServicRegistrations().isEmpty()) {
			servicesJpaRepository.deleteById(serviceId);
		} else {
			throw new OwnerSiteException("Service cannot be deleted as it is tagged to one or more subscription(s)");
		}

		LOGGER.info("Deleted servcie by id {} successfully", serviceId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownersite.rdr.service.manufacturer.ServicesService#updateService(com.
	 * ownersite.rdr.dto.ServiceDTO)
	 */
	@Override
	public void updateService(ServiceDTO serviceDTO) throws OwnerSiteException {
		LOGGER.info("Updating service {}", serviceDTO);

		if (this.servicesJpaRepository.findByServiceId(serviceDTO.getServiceId()) == null) {
			throw new OwnerSiteException("Invalid service id");
		}

		servicesJpaRepository.save(serviceDTO.convertToEntity(serviceDTO));

		LOGGER.info("Updated servcie {} successfully", serviceDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownersite.rdr.service.manufacturer.ServicesService#findServiceById(long)
	 */
	@Override
	public ServiceDTO findServiceById(long serviceId) {
		LOGGER.info("Fetch service by id {}", serviceId);

		return new ServiceDTO(servicesJpaRepository.findByServiceId(serviceId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownersite.rdr.service.manufacturer.ServicesService#findServicesByIds(java
	 * .util.List)
	 */
	@Override
	public List<com.ownersite.rdr.entity.Service> findServicesByIds(List<Long> serviceIds) {
		LOGGER.info("Fetch services by ids {}", serviceIds);

		return servicesJpaRepository.findAllById(serviceIds);
	}

}
