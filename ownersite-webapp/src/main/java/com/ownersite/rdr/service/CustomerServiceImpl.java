/**
 * 
 */
package com.ownersite.rdr.service;

import com.ownersite.rdr.dto.CustomerServicesDTO;
import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.VehiclesDTO;
import com.ownersite.rdr.entity.*;
import com.ownersite.rdr.repository.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author polamred
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerSubscriptionJpaRepository customerSubscriptionJpaRepository;

    private CustomerServicesJpaRepository customerServicesJpaRepository;

    private CustomerJpaRepository customerJpaRepository;

    private VehicleJpaRepository vehicleJpaRepository;
    private CustomerVehicleJpaRepository customerVehicleJpaRepository;

    @Autowired
    public CustomerServiceImpl(CustomerSubscriptionJpaRepository customerSubscriptionJpaRepository,
                               CustomerServicesJpaRepository customerServicesJpaRepository,
                               CustomerJpaRepository customerJpaRepository,
                               VehicleJpaRepository vehicleJpaRepository,
                               CustomerVehicleJpaRepository customerVehicleJpaRepository) {
        this.customerSubscriptionJpaRepository = customerSubscriptionJpaRepository;
        this.customerServicesJpaRepository = customerServicesJpaRepository;
        this.customerJpaRepository = customerJpaRepository;
        this.vehicleJpaRepository = vehicleJpaRepository;
        this.customerVehicleJpaRepository = customerVehicleJpaRepository;
    }

    @Override
    public List<CustomerSubscriptionDTO> getAllSubscriptions(String customerId) {
        List<CustomerSubscriptionDTO> customerSubscriptionDTOs = new ArrayList<>();
        List<CustomerSubscription> subscriptions = customerSubscriptionJpaRepository.findByCustomer(null);
        for (CustomerSubscription subscription: subscriptions) {
            customerSubscriptionDTOs.add(buildCustomerSubscriptionDTO(subscription));
        }
        return customerSubscriptionDTOs;
    }

    private CustomerSubscriptionDTO buildCustomerSubscriptionDTO(CustomerSubscription subscription) {
        CustomerSubscriptionDTO subscriptionDTO = new CustomerSubscriptionDTO();
        subscriptionDTO.setSubscriptionId(subscription.getId().toString());
        subscriptionDTO.setSubscriptionDesc(subscription.getSubscriptiondec());
        subscriptionDTO.setSubscriptionEndDate(subscription.getCustomer_sub_enddate().toString());
        subscriptionDTO.setSubscriptionStartDate(subscription.getCustomer_sub_startdate().toString());
        subscriptionDTO.setSubscriptionPrice(String.valueOf(subscription.getSubscription().getPrice()));
        subscriptionDTO.setVin(subscription.getVin());
        return subscriptionDTO;
    }

    @Override
    public List<CustomerServicesDTO> getServiceHistory(String customerId) {
        Customer customer = customerJpaRepository.getOne(Long.parseLong(customerId));
        List<CustomerService> customerServices = customerServicesJpaRepository.findByCustomer(customer);
        List<CustomerServicesDTO> customerServicesDTOs = new ArrayList<>();
        for (CustomerService customerService:customerServices) {
            CustomerServicesDTO customerServicesDTO = new CustomerServicesDTO();
        }
        return null;
    }

    @Override
    public List<VehiclesDTO> getMyVehicles(String customerId) {
        List<VehiclesDTO> customerServicesDTOs = new ArrayList<>();
        List<CustomerVechile> customerVechiles = customerVehicleJpaRepository.findByCustomer(customerId);
        for (CustomerVechile customerVechile :customerVechiles) {
            VehiclesDTO vehiclesDTO = new VehiclesDTO();
            vehiclesDTO.setVehicleId(customerVechile.getId().toString());
            Vehicle vehicle = vehicleJpaRepository.getOne(customerVechile.getVechileId());
            vehiclesDTO.setMake(vehicle.getMake());
            vehiclesDTO.setModel(vehicle.getModel());
            vehiclesDTO.setVin(customerVechile.getVin());
            vehiclesDTO.setRegisteredNumber(customerVechile.getRegisteredNumber());
            vehiclesDTO.setYear(vehicle.getYear().toString());
            customerServicesDTOs.add(vehiclesDTO);
        }
        return customerServicesDTOs;
    }
}
