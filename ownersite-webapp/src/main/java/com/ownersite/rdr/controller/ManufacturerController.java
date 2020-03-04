package com.ownersite.rdr.controller;

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
import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.service.ManufacturerService;

@RestController
@RequestMapping("/owner-site/manufacturer")
@CrossOrigin(origins = "*")
public class ManufacturerController {

	private static final HttpStatus OK = HttpStatus.OK;
	private static final HttpStatus ERROR = HttpStatus.ACCEPTED;

	private final ManufacturerService manufacturerService;

	@Autowired
	public ManufacturerController(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}

	@GetMapping(value = "/getAllServices", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ServiceDTO>> getAllServices() {
		List<ServiceDTO> services = null;
		HttpStatus httpStatus = OK;

		try {
			services = manufacturerService.getAllServices();
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(services, httpStatus);
	}

	@PostMapping(value = "/addNewService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> add(@RequestBody ServiceDTO service) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;

		try {
			manufacturerService.addService(service);
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(new ResponseDTO(responseCode), httpStatus);
	}

	@DeleteMapping(value = "/deleteService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> delete(@RequestBody(required = true) ServiceDTO service) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;
		try {
			if (manufacturerService.findServiceById(service.getServiceId()) != null) {
				manufacturerService.deleteService(service.getServiceId());
			} else {
				responseCode = "1";
				httpStatus = ERROR;
			}
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}
		return new ResponseEntity<>(new ResponseDTO(responseCode), httpStatus);
	}

	@PutMapping(value = "/updateService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> update(@RequestBody(required = true) ServiceDTO serviceToUpdate) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;
		try {
			if (manufacturerService.findServiceById(serviceToUpdate.getServiceId()) != null) {
				manufacturerService.updateService(serviceToUpdate);
			} else {
				responseCode = "1";
				httpStatus = ERROR;
			}
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}
		return new ResponseEntity<>(new ResponseDTO(responseCode), httpStatus);
	}

	@GetMapping(value = "/getAllSubscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerSubscriptionDTO>> getAllSubscriptions() {
		List<CustomerSubscriptionDTO> subscription = null;
		HttpStatus httpStatus = OK;
		try {
			subscription = manufacturerService.getAllSubscriptions();
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
			manufacturerService.createSubscription(customerSubscriptionDTO);
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
			manufacturerService.deleteSubscription(Long.valueOf(customerSubscriptionDTO.getSubscriptionId()));
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
			manufacturerService.updateSubscription(customerSubscriptionDTO);
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(new ResponseDTO(responseCode), httpStatus);
	}

}
