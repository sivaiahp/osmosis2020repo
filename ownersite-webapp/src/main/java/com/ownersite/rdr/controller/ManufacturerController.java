package com.ownersite.rdr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

@RestController
@RequestMapping("/owner-site/manufacturer")
@CrossOrigin(origins = "*")
public class ManufacturerController {

	// TODO: Service autowiring

	private static List<Service> services;
	private static final HttpStatus OK = HttpStatus.OK;
	private static final HttpStatus ERROR = HttpStatus.ACCEPTED;
	private static Long id = 0L;

	static {
		services = new ArrayList<>();
		Service service = new Service();
		service.setId(++id);
		service.setServicedec("Connecting all your safety");
		service.setServicename("Safety connect");
		services.add(service);
		service = new Service();
		service.setId(++id);
		service.setServicedec("Connecting all your services");
		service.setServicename("Service connect");
		services.add(service);
	}

	@GetMapping(value = "/getAllServices", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Service>> getAllServices() {
		List<Service> mockedServices = null;
		HttpStatus httpStatus = OK;

		try {
			mockedServices = ManufacturerController.services;
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(mockedServices, httpStatus);
	}

	@PostMapping(value = "/addNewService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Service service) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;

		try {
			service.setId(++id);
			ManufacturerController.services.add(service);
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(responseCode, httpStatus);
	}

	@DeleteMapping(value = "/deleteService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@RequestBody(required = true) Long serviceId) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;

		try {
			if (ManufacturerController.services.stream().anyMatch(service -> service.getId().equals(serviceId))) {
				ManufacturerController.services.removeIf(service -> service.getId().equals(serviceId));
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
			Optional<Service> servicesToUpdate = ManufacturerController.services.stream()
					.filter(service -> service.getId().equals(serviceToUpdate.getId())).findFirst();

			if (servicesToUpdate.isPresent()) {
				responseCode = "0";
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

}
