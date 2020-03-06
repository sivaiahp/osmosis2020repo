package com.ownersite.rdr.service.manufacturer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.dto.SubscriptionServiceDTO;
import com.ownersite.rdr.entity.Subscription;
import com.ownersite.rdr.exception.OwnerSiteException;
import com.ownersite.rdr.repository.CustomerSubscriptionJpaRepository;
import com.ownersite.rdr.repository.SubscriptionJpaRepository;
import com.ownersite.rdr.repository.SubscriptionServiceJpaRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

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
		LOGGER.info("creating Subscription for {}", customerSubscriptionDTO);

		subscriptionJpaRepository.save(customerSubscriptionDTO.convertToEntity());

		LOGGER.info("Subscription added successfully");
	}

	@Override
	public void updateSubscription(CustomerSubscriptionDTO customerSubscriptionDTO) throws OwnerSiteException {
		LOGGER.info("updating Subscription for {}", customerSubscriptionDTO);

		Subscription subscription = customerSubscriptionDTO.convertToEntity();
		if (subscriptionJpaRepository.findBySubscriptionId(subscription.getId()) == null) {
			throw new OwnerSiteException("Invalid subscription");
		} else {
			subscriptionJpaRepository.save(subscription);
		}

		LOGGER.info("Subscription updated successfully");
	}

	@Override
	public void deleteSubscription(long subscriptionId) throws OwnerSiteException {
		LOGGER.info("delete Subscription for {}", subscriptionId);

		if (subscriptionJpaRepository.findBySubscriptionId(subscriptionId) != null) {
			if (customerSubscriptionJpaRepository.findBySubscriptionId(subscriptionId).isEmpty()) {
				if (!subscriptionServiceJpaRepository.findBySubscriptionId(subscriptionId).isEmpty()) {
					subscriptionServiceJpaRepository.deleteBySubscriptionId(subscriptionId);
				}
				subscriptionJpaRepository.deleteById(subscriptionId);
			} else {
				LOGGER.error("subscription already tagged with customer, hence we can't delete {}", subscriptionId);

				throw new OwnerSiteException("Subscription cannot be deleted, as it is subscribed by customer");
			}
		}

		LOGGER.info("Subscription deleted successfully");
	}

	@Override
	public List<CustomerSubscriptionDTO> getAllSubscriptions() {
		LOGGER.info("Getting all subscriptions");

		return subscriptionJpaRepository.findAll().stream().map(CustomerSubscriptionDTO::new)
				.collect(Collectors.toList());
	}

	@Override
	public CustomerSubscriptionDTO findBySubscriptionId(long id) {
		LOGGER.info("Find Subscription by id {}", id);

		return new CustomerSubscriptionDTO(subscriptionJpaRepository.findBySubscriptionId(id));
	}

	@Override
	@Transactional
	public void updateSubscriptionServices(SubscriptionServiceDTO subscriptionServiceDTO) throws OwnerSiteException {
		LOGGER.info("Updating services tagged to subscription");

		List<Long> serviceIds = Arrays.asList(subscriptionServiceDTO.getServiceIds().split(",")).stream()
				.map(String::trim).map(Long::valueOf).collect(Collectors.toList());

		List<com.ownersite.rdr.entity.Service> updatedServices = servicesService.findServicesByIds(serviceIds);

		if (serviceIds.size() != updatedServices.size()) {
			throw new OwnerSiteException("Invalid service ids");
		}

		List<Long> updatedServiceIds = updatedServices.stream().map(com.ownersite.rdr.entity.Service::getId)
				.collect(Collectors.toList());

		Subscription subscription = subscriptionJpaRepository
				.findBySubscriptionId(Long.valueOf(subscriptionServiceDTO.getSubscriptionId()));

		if (subscription == null) {
			throw new OwnerSiteException("Invalid subscription id");
		}

		List<com.ownersite.rdr.entity.SubscriptionService> existingServices = subscription
				.getSubscriptionServicRegistrations();
		List<Long> existingServiceIds = existingServices.stream().map(service -> service.getService().getId())
				.collect(Collectors.toList());

		// Services to be un-tagged from subscription
		List<com.ownersite.rdr.entity.SubscriptionService> servicesToBeRemoved = existingServices.stream()
				.filter(subscriptionService -> !updatedServiceIds.contains(subscriptionService.getService().getId()))
				.collect(Collectors.toList());

		// New services to be tagged to subscription
		List<com.ownersite.rdr.entity.SubscriptionService> servicesToBeAdded = updatedServices.stream()
				.filter(service -> !existingServiceIds.contains(service.getId())).map(service -> {
					com.ownersite.rdr.entity.SubscriptionService subscriptionService = new com.ownersite.rdr.entity.SubscriptionService();
					subscriptionService.setService(service);
					subscriptionService.setSubscription(subscription);

					return subscriptionService;
				}).collect(Collectors.toList());

		if (!servicesToBeRemoved.isEmpty()) {
			existingServices.removeAll(servicesToBeRemoved);
		}
		if (!servicesToBeAdded.isEmpty()) {
			existingServices.addAll(servicesToBeAdded);
		}

		subscriptionJpaRepository.saveAndFlush(subscription);
		subscriptionJpaRepository.flush();

		LOGGER.info("Updated services tagged to subscription successfully");

	}

	@Override
	public List<ServiceDTO> getServicesBySubscription(CustomerSubscriptionDTO customerSubscriptionDTO) {
		LOGGER.info("Getting all the services tagged to a subscription");

		List<ServiceDTO> serviceDTOs = null;

		Subscription subscription = subscriptionJpaRepository
				.findBySubscriptionId(Long.valueOf(customerSubscriptionDTO.getSubscriptionId()));

		serviceDTOs = subscription.getSubscriptionServicRegistrations().stream()
				.map(com.ownersite.rdr.entity.SubscriptionService::getService).map(ServiceDTO::new)
				.collect(Collectors.toList());

		LOGGER.info("Got all the services tagged to a subscription successfully");

		return serviceDTOs;
	}

}
