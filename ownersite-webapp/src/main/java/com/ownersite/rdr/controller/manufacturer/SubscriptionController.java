package com.ownersite.rdr.controller.manufacturer;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.ReportDTO;
import com.ownersite.rdr.dto.ResponseDTO;
import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.dto.SubscriptionServiceDTO;
import com.ownersite.rdr.dto.SubscriptionVehicleDTO;
import com.ownersite.rdr.dto.VehiclesDTO;
import com.ownersite.rdr.service.manufacturer.SubscriptionService;
import com.ownersite.rdr.util.OwnerSiteUtility;

/**
 * The REST controller class for managaing the Subscriptions provided by the
 * manufacturer and also for generating reports for analytics
 * 
 * @author basridha
 *
 */
@RestController
@RequestMapping("/owner-site/manufacturer")
@CrossOrigin(origins = "*")
public class SubscriptionController {

	private final SubscriptionService subscriptionService;
	private static final HttpStatus OK = HttpStatus.OK;
	private static final HttpStatus ERROR = HttpStatus.ACCEPTED;

	@Autowired
	public SubscriptionController(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

	/**
	 * Fetches all the subscriptions
	 * 
	 * @return the {@link List} of {@link CustomerSubscriptionDTO} as
	 *         {@link ResponseEntity} object
	 */
	@GetMapping(value = "/getAllSubscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerSubscriptionDTO>> getAllSubscriptions() {
		List<CustomerSubscriptionDTO> subscription = null;
		HttpStatus httpStatus = OK;

		try {
			subscription = subscriptionService.getAllSubscriptions();
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(subscription, httpStatus);
	}

	/**
	 * Fetch all the vehicles
	 * 
	 * @return the {@link List} of {@link VehiclesDTO} as {@link ResponseEntity}
	 *         object
	 */
	@GetMapping(value = "/getAllVehicles")
	public ResponseEntity<List<VehiclesDTO>> getAllVehicles() {
		List<VehiclesDTO> customerServices = null;
		HttpStatus httpStatus = OK;
		try {
			customerServices = subscriptionService.getAllVehicles();
		} catch (Exception exception) {
			httpStatus = ERROR;
		}
		return new ResponseEntity<>(customerServices, httpStatus);
	}

	/**
	 * Adds the new subscription
	 * 
	 * @param customerSubscriptionDTO the subscription to be added
	 * @return the status as {@link ResponseEntity} returns 0 if subscription is
	 *         successfully added, else 1
	 */
	@PostMapping(value = "/addNewSubscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> addSubscription(
			@RequestBody(required = true) CustomerSubscriptionDTO customerSubscriptionDTO) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;
		Map<String, Object> errors = null;

		try {
			subscriptionService.createSubscription(customerSubscriptionDTO);
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
			errors = OwnerSiteUtility.constructErrorResponse(exception);
		}

		return new ResponseEntity<>(new ResponseDTO(responseCode, errors), httpStatus);
	}

	/**
	 * 
	 * Deletes the existing subscription
	 * 
	 * @param customerSubscriptionDTO the subscription to be deleted
	 * @return the status as {@link ResponseEntity} returns 0 if subscription is
	 *         successfully deleted, else 1
	 */
	@DeleteMapping(value = "/deleteSubscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> deleteSubscription(
			@RequestBody(required = true) CustomerSubscriptionDTO customerSubscriptionDTO) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;
		Map<String, Object> errors = null;

		try {
			subscriptionService.deleteSubscription(Long.valueOf(customerSubscriptionDTO.getSubscriptionId()));
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
			errors = OwnerSiteUtility.constructErrorResponse(exception);
		}

		return new ResponseEntity<>(new ResponseDTO(responseCode, errors), httpStatus);
	}

	/**
	 * Updates the existing subscription
	 * 
	 * @param customerSubscriptionDTO the subscription to be updated
	 * @return the status as {@link ResponseEntity} returns 0 if subscription is
	 *         successfully updated, else 1
	 */
	@PutMapping(value = "/updateSubscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> updateSubscription(
			@RequestBody(required = true) CustomerSubscriptionDTO customerSubscriptionDTO) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;
		Map<String, Object> errors = null;

		try {
			subscriptionService.updateSubscription(customerSubscriptionDTO);
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
			errors = OwnerSiteUtility.constructErrorResponse(exception);
		}

		return new ResponseEntity<>(new ResponseDTO(responseCode, errors), httpStatus);
	}

	/**
	 * Updates (adding and deleting of services) the services associated to a
	 * subscription
	 * 
	 * @param subscriptionServiceDTO the subscription for which services to be
	 *                               updated. The services to be updated will be
	 *                               available as part of this object
	 * @return the status as {@link ResponseEntity} returns 0 if subscription's
	 *         services are successfully updated, else 1
	 */
	@PostMapping(value = "/updateSubcriptionServices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> updateSubscriptionServices(
			@RequestBody(required = true) SubscriptionServiceDTO subscriptionServiceDTO) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;
		Map<String, Object> errors = null;

		try {
			subscriptionService.updateSubscriptionServices(subscriptionServiceDTO);
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
			errors = OwnerSiteUtility.constructErrorResponse(exception);
		}

		return new ResponseEntity<>(new ResponseDTO(responseCode, errors), httpStatus);
	}

	/**
	 * Fetches the list of services associated to the given subscription
	 * 
	 * @param customerSubscriptionDTO the subscription for which services has to be
	 *                                retrieved
	 * @return all the services associated to the given subscription
	 */
	@PostMapping(value = "/getServicesBySubscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ServiceDTO>> getServicesBySubscription(
			@RequestBody(required = true) CustomerSubscriptionDTO customerSubscriptionDTO) {
		List<ServiceDTO> serviceDTOs = null;
		HttpStatus httpStatus = OK;

		try {
			serviceDTOs = subscriptionService.getServicesBySubscription(customerSubscriptionDTO);
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(serviceDTOs, httpStatus);
	}

	/**
	 * Fetches the vehicles associated to a subscription
	 * 
	 * @param customerSubscriptionDTO the subscription for which vehicles are
	 *                                associated
	 * @return all the vehicles associated to the given subscription
	 */
	@PostMapping(value = "/getVehiclesBySubscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VehiclesDTO>> getVehiclesBySubscription(
			@RequestBody(required = true) CustomerSubscriptionDTO customerSubscriptionDTO) {
		List<VehiclesDTO> vehiclesDTOs = null;
		HttpStatus httpStatus = OK;

		try {
			vehiclesDTOs = subscriptionService.getVehiclesBySubscription(customerSubscriptionDTO);
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(vehiclesDTOs, httpStatus);
	}

	/**
	 * Updates the vehicles associated to the subscription
	 * 
	 * @param subscriptionVehicleDTO the vehicle to be updated against the
	 *                               subscription
	 * @return the status as {@link ResponseEntity} returns 0 if subscription's
	 *         vehicle is successfully updated, else 1
	 */
	@PostMapping(value = "/updateSubcriptionVehicles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> updateSubcriptionVehicles(
			@RequestBody(required = true) SubscriptionVehicleDTO subscriptionVehicleDTO) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;
		Map<String, Object> errors = null;

		try {
			subscriptionService.updateSubcriptionVehicles(subscriptionVehicleDTO);
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
			errors = OwnerSiteUtility.constructErrorResponse(exception);
		}

		return new ResponseEntity<>(new ResponseDTO(responseCode, errors), httpStatus);
	}

	/**
	 * Generates the report data for the number subscriptions availed monthly
	 * 
	 * @return the data for constructing the report
	 */
	@GetMapping(value = "/monthlySubscriptionsReport", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReportDTO> generateMontlyScubscriptionsReport() {
		ReportDTO report = null;
		HttpStatus httpStatus = OK;

		try {
			report = subscriptionService.generateSubscriptionsPerMonthReport();
		} catch (Exception e) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(report, httpStatus);
	}

	/**
	 * Generates the report data for the number subscriptions availed monthly per
	 * subscription basis
	 * 
	 * @return the data for constructing the report
	 */
	@GetMapping(value = "/monthlySubscriptionsPerSubscriptionReport", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReportDTO> generateMontlyScubscriptionsPerSubscriptionReport() {
		ReportDTO report = null;
		HttpStatus httpStatus = OK;

		try {
			report = subscriptionService.generateMonthlySubscriptionsPerSubcriptionReport();
		} catch (Exception e) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(report, httpStatus);
	}

	/**
	 * Generates the report data for the number subscriptions availed monthly for
	 * all the subscriptions
	 * 
	 * @return the data for constructing the report
	 */
	@GetMapping(value = "/subscriptionsReport", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReportDTO> generateSubscriptionsReport() {
		ReportDTO report = null;
		HttpStatus httpStatus = OK;

		try {
			report = subscriptionService.generateSubscriptions();
		} catch (Exception e) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(report, httpStatus);
	}

}
