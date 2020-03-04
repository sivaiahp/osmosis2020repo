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

import com.ownersite.rdr.entity.Service;
import com.ownersite.rdr.entity.Subscription;
import com.ownersite.rdr.service.ManufacturerService;

@RestController
@RequestMapping("/owner-site/manufacturer")
@CrossOrigin(origins = "*")
public class ManufacturerController {

	// TODO: Service autowiring

	private static List<Service> services;
	private static final HttpStatus OK = HttpStatus.OK;
	private static final HttpStatus ERROR = HttpStatus.ACCEPTED;
	private static Long id = 0L;

	private final ManufacturerService manufacturerService;

	@Autowired
	public ManufacturerController(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}

	@GetMapping(value = "/getAllServices", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Service>> getAllServices() {
		List<Service> services = null;
		HttpStatus httpStatus = OK;

		try {
			services = manufacturerService.getAllServices();
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(services, httpStatus);
	}

	@PostMapping(value = "/addNewService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Service service) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;

		try {
			// service.setId(++id);
			manufacturerService.addService(service);
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(responseCode, httpStatus);
	}

	@DeleteMapping(value = "/deleteService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@RequestBody(required = true) Service service) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;

		try {
			if (manufacturerService.findServiceById(service.getId()) != null) {
				manufacturerService.deleteService(service.getId());
			} else {
				responseCode = "1";
				httpStatus = ERROR;
			}
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(responseCode, httpStatus);
	}

	@PutMapping(value = "/updateService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody(required = true) Service serviceToUpdate) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;

		try {
			if (manufacturerService.findServiceById(serviceToUpdate.getId()) != null) {
				manufacturerService.updateService(serviceToUpdate);
			} else {
				responseCode = "1";
				httpStatus = ERROR;
			}
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(responseCode, httpStatus);
	}

	@GetMapping(value = "/getAllSubscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Subscription>> getAllSubscriptions() {
		List<Subscription> subscription = null;
		HttpStatus httpStatus = OK;

		try {
			subscription = manufacturerService.getAllSubscriptions();
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(subscription, httpStatus);
	}

	@PostMapping(value = "/addNewSubscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addSubscription(@RequestBody Subscription subscription) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;

		try {
			// service.setId(++id);
			manufacturerService.createSubscription(subscription);
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(responseCode, httpStatus);
	}

	@DeleteMapping(value = "/deleteSubscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteSubscription(@RequestBody(required = true) Subscription subscription) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;

		try {
			manufacturerService.deleteSubscription(subscription.getId());
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(responseCode, httpStatus);
	}

	@PutMapping(value = "/updateSubscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateSubscription(@RequestBody(required = true) Subscription subscription) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;

		try {
				manufacturerService.updateSubscription(subscription);
			
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(responseCode, httpStatus);
	}

}
