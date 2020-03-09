/**
 * 
 */
package com.ownersite.rdr.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ownersite.rdr.dto.DealerDTO;
import com.ownersite.rdr.dto.ServiceStationDTO;
import com.ownersite.rdr.service.ServiceStationService;

/**
 * @author polamred
 *
 */

@RestController
@RequestMapping("/owner-site/servicestation")
@CrossOrigin(origins = "*")
public class ServiceStationController {

	private static final Logger logger = LoggerFactory.getLogger(ServiceStationController.class);

	private static final HttpStatus OK = HttpStatus.OK;
	private static final HttpStatus ERROR = HttpStatus.ACCEPTED;
	
	private final ServiceStationService serviceStationService;
	
	@Autowired
	public ServiceStationController(ServiceStationService serviceStationService){
		this.serviceStationService = serviceStationService;
	}
	
	
	@GetMapping(value = "/getAllServiceStations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ServiceStationDTO>> getAllDealers() {
		logger.info("loading available serviceStations");
		List<ServiceStationDTO> serviceStations = null;
		HttpStatus httpStatus = OK;

		try {
			serviceStations = serviceStationService.getAllServiceStations();
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(serviceStations, httpStatus);
	}
	
	@GetMapping(value = "/getServiceStation", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceStationDTO> getDealer(@RequestParam("serviceStationId") long serviceStationId) {
		logger.info("loading details for serviceStation:", serviceStationId);
		ServiceStationDTO serviceStationDTO = null;
		HttpStatus httpStatus = OK;

		try {
			serviceStationDTO = serviceStationService.getServiceStationById(serviceStationId);
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(serviceStationDTO, httpStatus);
	}
	
}
