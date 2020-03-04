package com.ownersite.rdr.service.manufacturer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.dto.SubscriptionServiceDTO;
import com.ownersite.rdr.entity.Subscription;
import com.ownersite.rdr.repository.CustomerSubscriptionJpaRepository;
import com.ownersite.rdr.repository.SubscriptionJpaRepository;
import com.ownersite.rdr.repository.SubscriptionServiceJpaRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	private static final Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

	private final SubscriptionJpaRepository subscriptionJpaRepository;

	private final SubscriptionServiceJpaRepository subscriptionServiceJpaRepository;

	private final CustomerSubscriptionJpaRepository customerSubscriptionJpaRepository;

	private final ServicesService servicesService;

	@Autowired
	public SubscriptionServiceImpl(SubscriptionJpaRepository subscriptionJpaRepository,
			SubscriptionServiceJpaRepository subscriptionServiceJpaRepository,
			CustomerSubscriptionJpaRepository customerSubscriptionJpaRepository, ServicesService servicesService) {
		this.subscriptionJpaRepository = subscriptionJpaRepository;
		this.subscriptionServiceJpaRepository = subscriptionServiceJpaRepository;
		this.customerSubscriptionJpaRepository = customerSubscriptionJpaRepository;
		this.servicesService = servicesService;
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

	@Override
	public void updateSubscriptionServices(SubscriptionServiceDTO subscriptionServiceDTO) {
		try {
			List<Long> serviceIds = Arrays.asList(subscriptionServiceDTO.getServiceIds().split(",")).stream()
					.map(String::trim).map(Long::valueOf).collect(Collectors.toList());
			List<com.ownersite.rdr.entity.Service> services = servicesService.findServicesByIds(serviceIds);

			Subscription subscription = subscriptionJpaRepository
					.findBySubscriptionId(Long.valueOf(subscriptionServiceDTO.getSubscriptionId()));

			if (serviceIds.size() != services.size() || subscription == null) {
				throw new Exception("Invalid input");
			}

			// FIXME: adds multiple services
			List<com.ownersite.rdr.entity.SubscriptionService> subscriptionServicRegistrations = services.stream()
					.map(service -> {
						com.ownersite.rdr.entity.SubscriptionService subscriptionService = new com.ownersite.rdr.entity.SubscriptionService();
						subscriptionService.setService(service);
						subscriptionService.setSubscription(subscription);

						return subscriptionService;
					}).collect(Collectors.toList());

			subscription.setSubscriptionServicRegistrations(subscriptionServicRegistrations);

			subscriptionJpaRepository.save(subscription);
		} catch (Exception exception) {
			throw new RuntimeException();
		}
	}

	@Override
	public List<ServiceDTO> getServicesBySubscription(CustomerSubscriptionDTO customerSubscriptionDTO) {
		List<ServiceDTO> serviceDTOs = null;
		
		try {
			Subscription subscription = subscriptionJpaRepository
					.findBySubscriptionId(Long.valueOf(customerSubscriptionDTO.getSubscriptionId()));
			
			serviceDTOs = subscription.getSubscriptionServicRegistrations().stream()
					.map(com.ownersite.rdr.entity.SubscriptionService::getService).map(ServiceDTO::new)
					.collect(Collectors.toList());
		} catch (Exception exception) {
			// TODO
		}

		return serviceDTOs;
	}

}
