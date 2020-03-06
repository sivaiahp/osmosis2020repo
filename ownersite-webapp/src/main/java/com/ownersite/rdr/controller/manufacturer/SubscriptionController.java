package com.ownersite.rdr.controller.manufacturer;

import java.util.List;
import java.util.Map;

import com.ownersite.rdr.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ownersite.rdr.service.manufacturer.SubscriptionService;
import com.ownersite.rdr.util.OwnerSiteUtility;

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

}
