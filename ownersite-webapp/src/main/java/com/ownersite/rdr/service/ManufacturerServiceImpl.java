/**
 * 
 */
package com.ownersite.rdr.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.entity.Subscription;
import com.ownersite.rdr.repository.CustomerSubscriptionJpaRepository;
import com.ownersite.rdr.repository.ServicesJpaRepository;
import com.ownersite.rdr.repository.SubscriptionJpaRepository;
import com.ownersite.rdr.repository.SubscriptionServiceJpaRepository;

/**
 * @author polamred
 *
 */
@Service
public class ManufacturerServiceImpl implements ManufacturerService {
	private static final Logger logger = LoggerFactory.getLogger(ManufacturerServiceImpl.class);

	private final ServicesJpaRepository servicesJpaRepository;

	private final SubscriptionJpaRepository subscriptionJpaRepository;

	private final SubscriptionServiceJpaRepository subscriptionServiceJpaRepository;

	private final CustomerSubscriptionJpaRepository customerSubscriptionJpaRepository;

	@Autowired
	public ManufacturerServiceImpl(ServicesJpaRepository servicesJpaRepository,
			SubscriptionJpaRepository subscriptionJpaRepository,
			SubscriptionServiceJpaRepository subscriptionServiceJpaRepository,
			CustomerSubscriptionJpaRepository customerSubscriptionJpaRepository) {
		this.servicesJpaRepository = servicesJpaRepository;
		this.subscriptionJpaRepository = subscriptionJpaRepository;
		this.subscriptionServiceJpaRepository = subscriptionServiceJpaRepository;
		this.customerSubscriptionJpaRepository = customerSubscriptionJpaRepository;
	}

	@Override
	public List<ServiceDTO> getAllServices() {
		logger.info("loading all services=============");
		List<com.ownersite.rdr.entity.Service> listservices = servicesJpaRepository.findAll();

		return listservices.stream().map(ServiceDTO::new).collect(Collectors.toList());
	}

	@Override
	public void addService(ServiceDTO serviceDTO) {
		logger.info("creating service for:", serviceDTO);
		servicesJpaRepository.save(serviceDTO.convertToEntity(serviceDTO));
	}

	@Override
	public void deleteService(long serviceId) {
		logger.info("delete serviceId:", serviceId);
		servicesJpaRepository.deleteById(serviceId);
	}

	@Override
	public void updateService(ServiceDTO serviceDTO) {
		logger.info("updating service for:", serviceDTO);
		servicesJpaRepository.save(serviceDTO.convertToEntity(serviceDTO));
	}

	@Override
	public ServiceDTO findServiceById(long serviceId) {

		return new ServiceDTO(servicesJpaRepository.findByServiceId(serviceId));
	}

	@Override
	public void createSubscription(CustomerSubscriptionDTO customerSubscriptionDTO) {
		logger.info("creating Subscription for:", customerSubscriptionDTO);
		subscriptionJpaRepository.save(customerSubscriptionDTO.convertToEntity());
	}

	@Override
	public void updateSubscription(CustomerSubscriptionDTO customerSubscriptionDTO) {
		logger.info("updating Subscription for:", customerSubscriptionDTO);
		try {
			Subscription subscription = customerSubscriptionDTO.convertToEntity();
			if (subscriptionJpaRepository.findBySubscriptionId(subscription.getId()) != null) {
				subscriptionJpaRepository.save(subscription);
			}
		} catch (Exception e) {
			logger.error("recieved error while updateing subscription........for:", customerSubscriptionDTO);
		}
	}

	@Override
	public void deleteSubscription(long subscriptionId) {
		logger.info("delete Subscription for:", subscriptionId);

		try {
			if (subscriptionJpaRepository.findBySubscriptionId(subscriptionId) != null) {
				if (customerSubscriptionJpaRepository.findBySubscriptionId(subscriptionId).isEmpty()) {
					if (!subscriptionServiceJpaRepository.findBySubscriptionId(subscriptionId).isEmpty()) {
						subscriptionServiceJpaRepository.deleteBySubscriptionId(subscriptionId);
					}
					subscriptionJpaRepository.deleteById(subscriptionId);
				} else {
					logger.info("subscription already tagged with customer, hence we can't delete:", subscriptionId);
				}
			}
		} catch (Exception e) {
			logger.error("recieved error while deleting subscription........id:", subscriptionId);
		}
	}

	@Override
	public List<CustomerSubscriptionDTO> getAllSubscriptions() {
		logger.info("loding Subscriptions==========");
		return subscriptionJpaRepository.findAll().stream().map(CustomerSubscriptionDTO::new)
				.collect(Collectors.toList());
	}

	@Override
	public CustomerSubscriptionDTO findBySubscriptionId(long id) {
		logger.info("find Subscription id:", id);
		return new CustomerSubscriptionDTO(subscriptionJpaRepository.findBySubscriptionId(id));
	}

}
