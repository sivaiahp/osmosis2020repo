/**
 * 
 */
package com.ownersite.rdr.controller.dealer;

/**
 * @author polamred
 *
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ownersite.rdr.dto.CustomerDTO;
import com.ownersite.rdr.dto.CustomerServicesDTO;
import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.DealerDTO;
import com.ownersite.rdr.dto.ResponseDTO;
import com.ownersite.rdr.service.CustomerService;
import com.ownersite.rdr.service.DealerService;

@RestController
@RequestMapping("/owner-site/dealer")
@CrossOrigin(origins = "*")
public class DealerController {
	private static final Logger logger = LoggerFactory.getLogger(DealerController.class);

	private static final HttpStatus OK = HttpStatus.OK;
	private static final HttpStatus ERROR = HttpStatus.ACCEPTED;

	//private final ServicesService servicesService;
	
	private final CustomerService customerService;
	
	private final DealerService dealerService;

	@Autowired
	public DealerController(CustomerService customerService,DealerService dealerService) {
		this.customerService = customerService;
		this.dealerService = dealerService;
	}

	@GetMapping(value = "/searchAllCustomers/{dealerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerDTO>> getAllCustomers(@PathVariable long dealerId) {
		logger.info("looking available customers for dealer:", dealerId);
		List<CustomerDTO> customers = null;
		HttpStatus httpStatus = OK;

		try {
			customers = customerService.getAllCustomers(dealerId);
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(customers, httpStatus);
	}
	
	@GetMapping(value = "/searchCustomer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDTO> serachCustomer(@PathVariable long customerId) {
		logger.info("loading selected customer details for Id:", customerId);
		CustomerDTO cust = null;
		HttpStatus httpStatus = OK;

		try {
			cust = customerService.getCustomerById(customerId);
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(cust, httpStatus);
	}

	@GetMapping(value = "/viewCustomerSubscriptions/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerSubscriptionDTO>> viewCustomerSubscriptions(@PathVariable long customerId) {
		HttpStatus httpStatus = OK;
		List<CustomerSubscriptionDTO> customerSubscriptionDTOs = null;
		try {
			customerSubscriptionDTOs= customerService.getAllSubscriptions(String.valueOf(customerId));
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(customerSubscriptionDTOs, httpStatus);
	}

	@PostMapping(value = "/cancelCustomerSubscriptions")
	public ResponseEntity<ResponseDTO> cancelCustomerSubscriptions(@RequestParam String customerId, @RequestParam String subscriptionId, @RequestParam String vin) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;
		try {
			customerService.cancelSubscription(customerId, subscriptionId, vin);
		} catch (Exception exception) {
			responseCode = "1";
			httpStatus = ERROR;
		}
		return new ResponseEntity<>(new ResponseDTO(responseCode), httpStatus);
	}

	@GetMapping(value = "/getServiceHistory/{customerId}/{subscriptionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerServicesDTO>> getServiceHistory(@PathVariable  String customerId, @PathVariable  String subscriptionId) {
        List<CustomerServicesDTO> customerServices = null;
        HttpStatus httpStatus = OK;
        try {
            customerServices = customerService.getServiceHistory(customerId, subscriptionId);
        } catch (Exception exception) {
            httpStatus = ERROR;
        }

        return new ResponseEntity<>(customerServices, httpStatus);
    }
	
	@GetMapping(value = "/getServiceDetails/{customerId}/{subscriptionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerServicesDTO>> getServiceDetails(@PathVariable  String customerId, @PathVariable  String subscriptionId) {
        List<CustomerServicesDTO> customerServices = null;
        HttpStatus httpStatus = OK;
        try {
            customerServices = customerService.getServiceHistory(customerId, subscriptionId);
        } catch (Exception exception) {
            httpStatus = ERROR;
        }

        return new ResponseEntity<>(customerServices, httpStatus);
    }

	@PostMapping(value = "/transferSubscriptions")
    public ResponseEntity<ResponseDTO> getServiceDetails(@RequestParam  String customerId, @RequestParam  String subscriptionId, @RequestParam  String vin) {
		String responseCode = "0";
		HttpStatus httpStatus = OK;
        try {
            customerService.transferSubscription(customerId, subscriptionId, vin);
        } catch (Exception exception) {
        	responseCode = "1";
            httpStatus = ERROR;
        }

        return new ResponseEntity<>(new ResponseDTO(responseCode), httpStatus);
    }
	
	@GetMapping(value = "/getAllDealers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DealerDTO>> getAllDealers() {
		logger.info("loading available dealers");
		List<DealerDTO> dealers = null;
		HttpStatus httpStatus = OK;

		try {
			dealers = dealerService.getAllDealers();
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(dealers, httpStatus);
	}
	
	@GetMapping(value = "/getDealer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DealerDTO> getDealer(@RequestParam("dealerId") long dealerId) {
		logger.info("loading details for dealer:", dealerId);
		DealerDTO dealer = null;
		HttpStatus httpStatus = OK;

		try {
			dealer = dealerService.getDealerById(dealerId);
		} catch (Exception exception) {
			httpStatus = ERROR;
		}

		return new ResponseEntity<>(dealer, httpStatus);
	}
}
