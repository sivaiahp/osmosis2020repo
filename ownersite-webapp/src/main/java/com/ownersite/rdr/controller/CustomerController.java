package com.ownersite.rdr.controller;


import com.ownersite.rdr.dto.CustomerServicesDTO;
import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.entity.CustomerServices;
import com.ownersite.rdr.entity.CustomerSubscription;
import com.ownersite.rdr.entity.Service;
import com.ownersite.rdr.entity.Subscription;
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

    @GetMapping(value = "/getServiceHistory")
    public ResponseEntity<List<CustomerServicesDTO>> getServiceHistory(@RequestParam  String customerId) {
        List<CustomerServicesDTO> customerServices = null;
        HttpStatus httpStatus = OK;
        try {
            customerServices = customerService.getServiceHistory(customerId);
        } catch (Exception exception) {
            httpStatus = ERROR;
        }

        return new ResponseEntity<>(customerServices, httpStatus);
    }
}
