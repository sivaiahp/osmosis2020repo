package com.ownersite.rdr.service.manufacturer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.ReportDTO;
import com.ownersite.rdr.dto.ReportDataSeriesDTO;
import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.dto.SubscriptionServiceDTO;
import com.ownersite.rdr.dto.SubscriptionVehicleDTO;
import com.ownersite.rdr.dto.VehiclesDTO;
import com.ownersite.rdr.entity.MonthlySubscribers;
import com.ownersite.rdr.entity.Subscription;
import com.ownersite.rdr.entity.Vehicle;
import com.ownersite.rdr.exception.OwnerSiteException;
import com.ownersite.rdr.repository.CustomerSubscriptionJpaRepository;
import com.ownersite.rdr.repository.CustomerVehicleJpaRepository;
import com.ownersite.rdr.repository.SubscriptionJpaRepository;
import com.ownersite.rdr.repository.SubscriptionServiceJpaRepository;
import com.ownersite.rdr.repository.VehicleJpaRepository;
import com.ownersite.rdr.util.OwnerSiteUtility;

/**
 * The implementation class for {@link SubscriptionService}
 * 
 * @author basridha
 *
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

	private final SubscriptionJpaRepository subscriptionJpaRepository;

	private final SubscriptionServiceJpaRepository subscriptionServiceJpaRepository;

	private final CustomerSubscriptionJpaRepository customerSubscriptionJpaRepository;

	private final ServicesService servicesService;

	private final VehicleJpaRepository vehicleJpaRepository;
	
	private final CustomerVehicleJpaRepository customerVehicleJpaRepository;

	@Autowired
	public SubscriptionServiceImpl(SubscriptionJpaRepository subscriptionJpaRepository,
			SubscriptionServiceJpaRepository subscriptionServiceJpaRepository,
			CustomerSubscriptionJpaRepository customerSubscriptionJpaRepository, ServicesService servicesService,
			VehicleJpaRepository vehicleJpaRepository, CustomerVehicleJpaRepository customerVehicleJpaRepository) {
		this.subscriptionJpaRepository = subscriptionJpaRepository;
		this.subscriptionServiceJpaRepository = subscriptionServiceJpaRepository;
		this.customerSubscriptionJpaRepository = customerSubscriptionJpaRepository;
		this.servicesService = servicesService;
		this.vehicleJpaRepository = vehicleJpaRepository;
		this.customerVehicleJpaRepository = customerVehicleJpaRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownersite.rdr.service.manufacturer.SubscriptionService#createSubscription
	 * (com.ownersite.rdr.dto.CustomerSubscriptionDTO)
	 */
	@Override
	public void createSubscription(CustomerSubscriptionDTO customerSubscriptionDTO) {
		LOGGER.info("creating Subscription for {}", customerSubscriptionDTO);

		subscriptionJpaRepository.save(customerSubscriptionDTO.convertToEntity());

		LOGGER.info("Subscription added successfully");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownersite.rdr.service.manufacturer.SubscriptionService#updateSubscription
	 * (com.ownersite.rdr.dto.CustomerSubscriptionDTO)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownersite.rdr.service.manufacturer.SubscriptionService#deleteSubscription
	 * (long)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownersite.rdr.service.manufacturer.SubscriptionService#
	 * getAllSubscriptions()
	 */
	@Override
	public List<CustomerSubscriptionDTO> getAllSubscriptions() {
		LOGGER.info("Getting all subscriptions");

		return subscriptionJpaRepository.findAll().stream().map(CustomerSubscriptionDTO::new)
				.collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownersite.rdr.service.manufacturer.SubscriptionService#
	 * findBySubscriptionId(long)
	 */
	@Override
	public CustomerSubscriptionDTO findBySubscriptionId(long id) {
		LOGGER.info("Find Subscription by id {}", id);

		return new CustomerSubscriptionDTO(subscriptionJpaRepository.findBySubscriptionId(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownersite.rdr.service.manufacturer.SubscriptionService#
	 * updateSubscriptionServices(com.ownersite.rdr.dto.SubscriptionServiceDTO)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownersite.rdr.service.manufacturer.SubscriptionService#
	 * getServicesBySubscription(com.ownersite.rdr.dto.CustomerSubscriptionDTO)
	 */
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownersite.rdr.service.manufacturer.SubscriptionService#
	 * getVehiclesBySubscription(com.ownersite.rdr.dto.CustomerSubscriptionDTO)
	 */
	@Override
	public List<VehiclesDTO> getVehiclesBySubscription(CustomerSubscriptionDTO customerSubscriptionDTO) {
		LOGGER.info("Getting all the services tagged to a subscription");

		List<VehiclesDTO> vehiclesDTOs = null;

		Subscription subscription = subscriptionJpaRepository
				.findBySubscriptionId(Long.valueOf(customerSubscriptionDTO.getSubscriptionId()));

		vehiclesDTOs = subscription.getSubscriptionVehicles().stream()
				.map(com.ownersite.rdr.entity.SubscriptionVehicle::getVehicle).map(VehiclesDTO::new)
				.collect(Collectors.toList());

		LOGGER.info("Got all the services tagged to a subscription successfully");

		return vehiclesDTOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownersite.rdr.service.manufacturer.SubscriptionService#getAllVehicles()
	 */
	@Override
	public List<VehiclesDTO> getAllVehicles() {
		List<VehiclesDTO> customerServicesDTOs = new ArrayList<>();

		List<Vehicle> vehicles = vehicleJpaRepository.findAll();

		for (Vehicle vehicle : vehicles) {
			VehiclesDTO vehiclesDTO = new VehiclesDTO();
			vehiclesDTO.setVehicleId(vehicle.getId().toString());
			vehiclesDTO.setMake(vehicle.getMake());
			vehiclesDTO.setModel(vehicle.getModel());
			vehiclesDTO.setYear(vehicle.getYear());
			customerServicesDTOs.add(vehiclesDTO);
		}
		return customerServicesDTOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownersite.rdr.service.manufacturer.SubscriptionService#
	 * updateSubcriptionVehicles(com.ownersite.rdr.dto.SubscriptionVehicleDTO)
	 */
	@Override
	@Transactional
	public void updateSubcriptionVehicles(SubscriptionVehicleDTO subscriptionVehicleDTO) throws OwnerSiteException {
		LOGGER.info("Updating services tagged to subscription");

		List<Long> vehicleIds = Arrays.asList(subscriptionVehicleDTO.getVehicleIds().split(",")).stream()
				.map(String::trim).map(Long::valueOf).collect(Collectors.toList());

		List<com.ownersite.rdr.entity.Vehicle> updatedVechicles = vehicleJpaRepository.findAllById(vehicleIds);

		if (vehicleIds.size() != updatedVechicles.size()) {
			throw new OwnerSiteException("Invalid service ids");
		}

		List<Long> updatedVehicleIds = updatedVechicles.stream().map(com.ownersite.rdr.entity.Vehicle::getId)
				.collect(Collectors.toList());

		Subscription subscription = subscriptionJpaRepository
				.findBySubscriptionId(Long.valueOf(subscriptionVehicleDTO.getSubscriptionId()));

		if (subscription == null) {
			throw new OwnerSiteException("Invalid subscription id");
		}

		List<com.ownersite.rdr.entity.SubscriptionVehicle> existingVehicles = subscription.getSubscriptionVehicles();
		List<Long> existingVehicleIds = existingVehicles.stream().map(vehicle -> vehicle.getVehicle().getId())
				.collect(Collectors.toList());

		// Services to be un-tagged from subscription
		List<com.ownersite.rdr.entity.SubscriptionVehicle> servicesToBeRemoved = existingVehicles.stream()
				.filter(subscriptionService -> !updatedVehicleIds.contains(subscriptionService.getVehicle().getId()))
				.collect(Collectors.toList());

		// New services to be tagged to subscription
		List<com.ownersite.rdr.entity.SubscriptionVehicle> servicesToBeAdded = updatedVechicles.stream()
				.filter(vehicle -> !existingVehicleIds.contains(vehicle.getId())).map(vehicle -> {
					com.ownersite.rdr.entity.SubscriptionVehicle subscriptionVehicle = new com.ownersite.rdr.entity.SubscriptionVehicle();
					subscriptionVehicle.setVehicle(vehicle);
					subscriptionVehicle.setSubscription(subscription);
					return subscriptionVehicle;
				}).collect(Collectors.toList());

		if (!servicesToBeRemoved.isEmpty()) {
			existingVehicles.removeAll(servicesToBeRemoved);
		}
		if (!servicesToBeAdded.isEmpty()) {
			existingVehicles.addAll(servicesToBeAdded);
		}

		subscriptionJpaRepository.saveAndFlush(subscription);
		subscriptionJpaRepository.flush();

		LOGGER.info("Updated services tagged to subscription successfully");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownersite.rdr.service.manufacturer.SubscriptionService#
	 * generateSubscriptionsPerMonthReport()
	 */
	@Override
	public ReportDTO generateSubscriptionsPerMonthReport() {
		LOGGER.info("Generating monthly subscriptions report");

		Map<String, String> subscribers = new LinkedHashMap<>();
		customerSubscriptionJpaRepository.generateMonthlySubscriptionsReport().stream()
				.forEach(subscriber -> subscribers.put(subscriber.getPeriod(), subscriber.getSubscribers()));

		List<String> months = OwnerSiteUtility.getTwelveMonthsFromToday();

		LOGGER.info("Generated monthly subscriptions report successfully");

		return new ReportDTO(months,
				Arrays.asList(new ReportDataSeriesDTO("Subscriptions", months.stream()
						.map(month -> Integer.parseInt(subscribers.containsKey(month) ? subscribers.get(month) : "0"))
						.collect(Collectors.toList()))));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownersite.rdr.service.manufacturer.SubscriptionService#
	 * generateMonthlySubscriptionsPerSubcriptionReport()
	 */
	@Override
	public ReportDTO generateMonthlySubscriptionsPerSubcriptionReport() {
		LOGGER.info("Generating monthly subscriptions per subscription report");

		List<String> months = OwnerSiteUtility.getTwelveMonthsFromToday();

		Map<String, List<MonthlySubscribers>> subscribers = customerSubscriptionJpaRepository
				.generateMonthlySubscriptionsPerSubscriptionReport().stream()
				.collect(Collectors.groupingBy(MonthlySubscribers::getSubscriptionname));

		List<ReportDataSeriesDTO> reportDataSeries = new LinkedList<>();

		subscribers.forEach((subscriptionName, subscription) -> {
			Map<String, String> subscribersPerMonth = new LinkedHashMap<>();
			subscription.stream().forEach(
					subscriber -> subscribersPerMonth.put(subscriber.getPeriod(), subscriber.getSubscribers()));

			reportDataSeries
					.add(new ReportDataSeriesDTO(subscriptionName,
							months.stream().map(month -> Integer.parseInt(
									subscribersPerMonth.containsKey(month) ? subscribersPerMonth.get(month) : "0"))
									.collect(Collectors.toList())));
		});

		LOGGER.info("Generated monthly subscriptions per subscription report successfully");

		return new ReportDTO(months, reportDataSeries);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownersite.rdr.service.manufacturer.SubscriptionService#
	 * generateSubscriptions()
	 */
	@Override
	public ReportDTO generateSubscriptions() {
		LOGGER.info("Generating subscriptions report");

		List<String> categories = new ArrayList<>();
		List<Number> data = new ArrayList<>();

		subscriptionJpaRepository.countSubscriptions().forEach(subscription -> {
			categories.add(subscription.getSubscriptionname());
			data.add(Integer.valueOf(subscription.getSubscribers()));
		});

		LOGGER.info("Generated subscriptions report successfully");
		return new ReportDTO(categories, Arrays.asList(new ReportDataSeriesDTO("Subscriptions", data)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ownersite.rdr.service.manufacturer.SubscriptionService#
	 * generateRDRReport()
	 */
	@Override
	public ReportDTO generateRDRReport() {
		LOGGER.info("Generating RDR report");

		List<String> months = OwnerSiteUtility.getTwelveMonthsFromToday();

		Map<String, List<CustomerVehicleJpaRepository.RDRReport>> averageDays = customerVehicleJpaRepository
				.generateRDRReport().stream()
				.collect(Collectors.groupingBy(CustomerVehicleJpaRepository.RDRReport::getName));

		List<ReportDataSeriesDTO> reportDataSeries = new LinkedList<>();

		averageDays.forEach((dealerName, averageDay) -> {
			Map<String, String> subscribersPerMonth = new LinkedHashMap<>();
			averageDay.stream().forEach(
					subscriber -> subscribersPerMonth.put(subscriber.getPeriod(), subscriber.getDays()));

			reportDataSeries
					.add(new ReportDataSeriesDTO(dealerName,
							months.stream().map(month -> Integer.parseInt(
									subscribersPerMonth.containsKey(month) ? subscribersPerMonth.get(month) : "0"))
									.collect(Collectors.toList())));
		});

		LOGGER.info("Generated RDR report successfully");

		return new ReportDTO(months, reportDataSeries);
	}

}
