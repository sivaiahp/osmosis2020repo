package com.ownersite.rdr.controller.manufacturer;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ownersite.rdr.dto.ResponseDTO;
import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.service.manufacturer.ServicesService;
import com.ownersite.rdr.util.OwnerSiteUtility;

/**
 * The REST controller for managing the Services provided by the manufacturer
 * 
 * @author basridha
 *
 */
@RestController
@RequestMapping("/owner-site/manufacturer")
@CrossOrigin(origins = "*")
public class ServicesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServicesController.class);

	private static final HttpStatus OK = HttpStatus.OK;
	private static final HttpStatus ERROR = HttpStatus.ACCEPTED;

	private final ServicesService servicesService;

	@Autowired
	public ServicesController(ServicesService servicesService) {
		this.servicesService = servicesService;
	}

	/**
	 * Fetch all the services
	 * 
	 * @return the list of {@link ServiceDTO} as {@link ResponseEntity}
	 */
	@GetMapping(value = "/getAllServices", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ServiceDTO>> getAllServices() {
		LOGGER.info("Fetching all services");

		List<ServiceDTO> services = null;
		HttpStatus httpStatus = OK;

		try {
			services = servicesService.getAllServices();
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		LOGGER.info("Fetched all services");

		return new ResponseEntity<>(services, httpStatus);
	}

	/**
	 * Adds the new service
	 * 
	 * @param service the {@link ServiceDTO} to be added
	 * @return the status as {@link ResponseEntity} returns 0 if service is
	 *         successfully added, else 1
	 */
	@PostMapping(value = "/addNewService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> add(@RequestBody ServiceDTO service) {
		LOGGER.info("Adding service");

		String responseCode = "0";
		HttpStatus httpStatus = OK;
		Map<String, Object> errors = null;

		try {
			servicesService.addService(service);
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
			errors = OwnerSiteUtility.constructErrorResponse(exception);
		}

		LOGGER.info("Added service");

		return new ResponseEntity<>(new ResponseDTO(responseCode, errors), httpStatus);
	}

	/**
	 * Delete the service
	 * 
	 * @param service the {@link ServiceDTO} to be deleted
	 * @return the status as {@link ResponseEntity} returns 0 if service is
	 *         successfully deleted, else 1
	 */
	@DeleteMapping(value = "/deleteService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> delete(@RequestBody(required = true) ServiceDTO service) {
		LOGGER.info("Deleting service");

		String responseCode = "0";
		HttpStatus httpStatus = OK;
		Map<String, Object> errors = null;

		try {
			servicesService.deleteService(service.getServiceId());
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
			errors = OwnerSiteUtility.constructErrorResponse(exception);
		}

		LOGGER.info("Deleted service");

		return new ResponseEntity<>(new ResponseDTO(responseCode, errors), httpStatus);
	}

	/**
	 * Updated the existing service
	 * 
	 * @param serviceToUpdate the {@link ServiceDTO} to be updated
	 * @return the status as {@link ResponseEntity} returns 0 if service is
	 *         successfully updated, else 1
	 */
	@PutMapping(value = "/updateService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> update(@RequestBody(required = true) ServiceDTO serviceToUpdate) {
		LOGGER.info("Updating service");

		String responseCode = "0";
		HttpStatus httpStatus = OK;
		Map<String, Object> errors = null;

		try {
			servicesService.updateService(serviceToUpdate);
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
			errors = OwnerSiteUtility.constructErrorResponse(exception);
		}

		LOGGER.info("Updated service");

		return new ResponseEntity<>(new ResponseDTO(responseCode, errors), httpStatus);
	}

}
