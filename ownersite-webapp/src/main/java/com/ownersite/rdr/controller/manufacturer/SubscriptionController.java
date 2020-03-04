package com.ownersite.rdr.controller.manufacturer;

import java.util.List;

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
import com.ownersite.rdr.dto.ResponseDTO;
import com.ownersite.rdr.service.manufacturer.SubscriptionService;

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

	@PostMapping(value = "/addNewSubscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> addSubscription(
			@RequestBody(required = true) CustomerSubscriptionDTO customerSubscriptionDTO) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;
		try {
			subscriptionService.createSubscription(customerSubscriptionDTO);
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}
		return new ResponseEntity<>(new ResponseDTO(responseCode), httpStatus);
	}

	@DeleteMapping(value = "/deleteSubscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> deleteSubscription(
			@RequestBody(required = true) CustomerSubscriptionDTO customerSubscriptionDTO) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;
		try {
			subscriptionService.deleteSubscription(Long.valueOf(customerSubscriptionDTO.getSubscriptionId()));
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}
		return new ResponseEntity<>(new ResponseDTO(responseCode), httpStatus);
	}

	@PutMapping(value = "/updateSubscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> updateSubscription(
			@RequestBody(required = true) CustomerSubscriptionDTO customerSubscriptionDTO) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;
		try {
			subscriptionService.updateSubscription(customerSubscriptionDTO);
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(new ResponseDTO(responseCode), httpStatus);
	}

}
