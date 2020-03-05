package com.ownersite.rdr.controller;


import com.ownersite.rdr.dto.CustomerServicesDTO;
import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.ResponseDTO;
import com.ownersite.rdr.dto.VehiclesDTO;
import com.ownersite.rdr.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner-site/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    private static final HttpStatus OK = HttpStatus.OK;
    private static final HttpStatus ERROR = HttpStatus.ACCEPTED;

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/getAllSubscriptions")
    public ResponseEntity<List<CustomerSubscriptionDTO>> getAllSubscriptions(@RequestParam  String customerId) {
        List<CustomerSubscriptionDTO> subscriptions = null;
        HttpStatus httpStatus = OK;
        try {
            subscriptions = customerService.getAllSubscriptions(customerId);
        } catch (Exception exception) {
            httpStatus = ERROR;
        }

        return new ResponseEntity<>(subscriptions, httpStatus);
    }

    @GetMapping(value = "/viewCustomerServices")
    public ResponseEntity<List<CustomerServicesDTO>> getServiceHistory(@RequestParam  String customerId, @RequestParam  String subscriptionId) {
        List<CustomerServicesDTO> customerServices = null;
        HttpStatus httpStatus = OK;
        try {
            customerServices = customerService.getServiceHistory(customerId, subscriptionId);
        } catch (Exception exception) {
            httpStatus = ERROR;
        }

        return new ResponseEntity<>(customerServices, httpStatus);
    }

    @GetMapping(value = "/getMyVehicles")
    public ResponseEntity<List<VehiclesDTO>> getMyVehicles(@RequestParam  String customerId) {
        List<VehiclesDTO> customerServices = null;
        HttpStatus httpStatus = OK;
        try {
            customerServices = customerService.getMyVehicles(customerId);
        } catch (Exception exception) {
            httpStatus = ERROR;
        }
        return new ResponseEntity<>(customerServices, httpStatus);
    }

    @GetMapping(value = "/searchVIN")
    public ResponseEntity<VehiclesDTO> searchVIN(@RequestParam  String vin) {
        VehiclesDTO customerServices = null;
        HttpStatus httpStatus = OK;
        try {
            customerServices = customerService.getVehicle(vin);
        } catch (Exception exception) {
            httpStatus = ERROR;
        }
        return new ResponseEntity<>(customerServices, httpStatus);
    }

    @GetMapping(value = "/addVinForCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> addVinForCustomer(
            @RequestBody(required = true) VehiclesDTO vehiclesDTO) {
        String responseCode = "0";
        HttpStatus httpStatus = OK;
        try {
            customerService.addVinForCustomer(vehiclesDTO);
        } catch (Exception exception) {
            responseCode = "1";
            httpStatus = ERROR;
        }
        return new ResponseEntity<>(new ResponseDTO(responseCode), httpStatus);
    }

    @GetMapping(value = "/addCustomerSubscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> addCustomerSubscription(@RequestParam  String customerId,
                                                               @RequestParam  String subscriptionId,
                                                               @RequestParam  String vehicleId,
                                                               @RequestParam String subscriptionStartDate,
                                                               @RequestParam String subscriptionEndDate) {
        String responseCode = "0";
        HttpStatus httpStatus = OK;
        try {
            customerService.addCustomerSubscription(customerId,subscriptionId, vehicleId, subscriptionStartDate, subscriptionEndDate);
        } catch (Exception exception) {
            responseCode = "1";
            httpStatus = ERROR;
        }
        return new ResponseEntity<>(new ResponseDTO(responseCode), httpStatus);
    }

    @GetMapping(value = "/transferSubscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> transferSubscription(@RequestParam  String customerId,
                                                           @RequestParam  String subscriptionId,
                                                           @RequestParam   String vin) {
        String responseCode = "0";
        HttpStatus httpStatus = OK;
        try {
            customerService.transferSubscription(customerId,subscriptionId,vin);
        } catch (Exception exception) {
            responseCode = "1";
            httpStatus = ERROR;
        }
        return new ResponseEntity<>(new ResponseDTO(responseCode), httpStatus);
    }

    @GetMapping(value = "/cancelSubscription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> cancelSubscription(@RequestParam  String customerId,
                                                            @RequestParam  String subscriptionId,
                                                            @RequestParam   String vin) {
        String responseCode = "0";
        HttpStatus httpStatus = OK;
        try {
            customerService.cancelSubscription(customerId,subscriptionId,vin);
        } catch (Exception exception) {
            responseCode = "1";
            httpStatus = ERROR;
        }
        return new ResponseEntity<>(new ResponseDTO(responseCode), httpStatus);
    }
}
