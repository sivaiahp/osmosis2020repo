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

import com.ownersite.rdr.dto.ResponseDTO;
import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.service.manufacturer.ServicesService;

@RestController
@RequestMapping("/owner-site/manufacturer")
@CrossOrigin(origins = "*")
public class ServicesController {

	private static final HttpStatus OK = HttpStatus.OK;
	private static final HttpStatus ERROR = HttpStatus.ACCEPTED;

	private final ServicesService servicesService;

	@Autowired
	public ServicesController(ServicesService servicesService) {
		this.servicesService = servicesService;
	}

	@GetMapping(value = "/getAllServices", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ServiceDTO>> getAllServices() {
		List<ServiceDTO> services = null;
		HttpStatus httpStatus = OK;

		try {
			services = servicesService.getAllServices();
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
			servicesService.addService(service);
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
			if (servicesService.findServiceById(service.getServiceId()) != null) {
				servicesService.deleteService(service.getServiceId());
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
			if (servicesService.findServiceById(serviceToUpdate.getServiceId()) != null) {
				servicesService.updateService(serviceToUpdate);
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

}
