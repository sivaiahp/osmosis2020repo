/**
 * 
 */
package com.ownersite.rdr.service;

import com.ownersite.rdr.dto.CustomerServicesDTO;
import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.VehiclesDTO;
import com.ownersite.rdr.entity.*;
import com.ownersite.rdr.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private SubscriptionJpaRepository subscriptionJpaRepository;

    @Autowired
    public CustomerServiceImpl(CustomerSubscriptionJpaRepository customerSubscriptionJpaRepository,
                               CustomerServicesJpaRepository customerServicesJpaRepository,
                               CustomerJpaRepository customerJpaRepository,
                               VehicleJpaRepository vehicleJpaRepository,
                               CustomerVehicleJpaRepository customerVehicleJpaRepository,
                               SubscriptionJpaRepository subscriptionJpaRepository) {
        this.customerSubscriptionJpaRepository = customerSubscriptionJpaRepository;
        this.customerServicesJpaRepository = customerServicesJpaRepository;
        this.customerJpaRepository = customerJpaRepository;
        this.vehicleJpaRepository = vehicleJpaRepository;
        this.customerVehicleJpaRepository = customerVehicleJpaRepository;
        this.subscriptionJpaRepository = subscriptionJpaRepository;
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
    public List<CustomerServicesDTO> getServiceHistory(String customerId, String subscriptionId) {
        Customer customer = customerJpaRepository.getOne(Long.parseLong(customerId));
        List<CustomerServices> customerServices = customerServicesJpaRepository.findByCustomer(customer);
        List<CustomerServicesDTO> customerServicesDTOs = new ArrayList<>();
        for (CustomerServices customerService:customerServices) {
            CustomerServicesDTO customerServicesDTO = new CustomerServicesDTO();
            customerServicesDTO.setCustomerId(customerId);
            customerServicesDTO.setDealerId(customerService.getDealerId().toString());
            customerServicesDTO.setServicecomplaintAnalysis(customerService.getService_analysis_desc());
            customerServicesDTO.setServiceCompletedDate(customerService.getServiceCompletedDate().toString());
            customerServicesDTO.setServiceCustomerComplaints(customerService.getService_cust_complaints());
            customerServicesDTO.setServicedec(customerService.getService().getServicedec());
            customerServicesDTO.setServiceId(customerService.getId().toString());
            customerServicesDTO.setServicename(customerService.getService().getServicename());
            customerServicesDTO.setServicePrice(Double.toString(customerService.getService_cost()));
            customerServicesDTO.setServiceRepairsCost(Double.toString(customerService.getService_cost()));
            customerServicesDTO.setServiceRepairsDesc(customerService.getService_repairs_desc());
            customerServicesDTO.setServiceRequestedDate(customerService.getServiceStartDate().toString());
            customerServicesDTO.setVin(customerService.getVin().toString());
            customerServicesDTO.setServiceStartedDate(customerService.getServiceStartDate().toString());
            customerServicesDTO.setServiceStationId(customerService.getServiceStationId().toString());
            customerServicesDTOs.add(customerServicesDTO);
        }
        return customerServicesDTOs;
    }

    @Override
    public List<VehiclesDTO> getMyVehicles(String customerId) {
        List<VehiclesDTO> customerServicesDTOs = new ArrayList<>();
        List<CustomerVechile> customerVechiles = customerVehicleJpaRepository.findByCustomerId(customerId);
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

    @Override
    public VehiclesDTO getVehicle(String vin) {
        CustomerVechile customerVechile = customerVehicleJpaRepository.findByVin(vin);
        Vehicle vehicle = vehicleJpaRepository.getOne(customerVechile.getVechileId());
        VehiclesDTO vehiclesDTO = new VehiclesDTO();
        vehiclesDTO.setVehicleId(customerVechile.getVechileId().toString());
        vehiclesDTO.setYear(vehicle.getYear().toString());
        vehiclesDTO.setRegisteredNumber(customerVechile.getRegisteredNumber());
        vehiclesDTO.setModel(vehicle.getModel());
        vehiclesDTO.setMake(vehicle.getMake());
        vehiclesDTO.setVin(vin);
        return vehiclesDTO;
    }

    @Override
    public void addVinForCustomer(VehiclesDTO vehiclesDTO) {
        CustomerVechile customerVechile = customerVehicleJpaRepository.findByVin(vehiclesDTO.getVin());
        customerVechile.setCustomerId(Long.parseLong(vehiclesDTO.getCustomerId()));
        customerVehicleJpaRepository.save(customerVechile);
    }

    @Override
    public void addCustomerSubscription(String customerId, String subscriptionId, String vehicleId,
                                        String subscriptionStartDate, String subscriptionEndDate) throws ParseException {
        Customer customer = customerJpaRepository.getOne(Long.parseLong(customerId));
        Subscription subscription = subscriptionJpaRepository.getOne(Long.parseLong(subscriptionId));
        CustomerVechile customerVechile = customerVehicleJpaRepository.getOne(Long.parseLong(vehicleId));
        CustomerSubscription customerSubscription = new CustomerSubscription();
        customerSubscription.setCustomer(customer);
        customerSubscription.setSubscription(subscription);
        customerSubscription.setCustomer_sub_startdate(new SimpleDateFormat("dd/MM/yyyy").parse(subscriptionStartDate));
        customerSubscription.setCustomer_sub_enddate(new SimpleDateFormat("dd/MM/yyyy").parse(subscriptionEndDate));
        customerSubscription.setCustomerId(Long.parseLong(customerId));
        customerSubscription.setSubscriptionname(subscription.getSubscriptionname());
        customerSubscription.setSubscriptiondec(subscription.getSubscriptiondec());
        customerSubscription.setPrice(new Double(subscription.getPrice()));
        customerSubscription.setVin(customerVechile.getVin());
        customerSubscriptionJpaRepository.save(customerSubscription);
    }

    @Override
    public void transferSubscription(String customerId, String subscriptionId, String vin) {
        List<CustomerSubscription> customerSubscriptions = customerSubscriptionJpaRepository
                .findBySubscriptionIdAndCustomerId(subscriptionId, customerId);
        CustomerSubscription customerSubscription = customerSubscriptions.get(0);
        customerSubscription.setVin(vin);
        customerSubscriptionJpaRepository.save(customerSubscription);
    }

    @Override
    public void cancelSubscription(String customerId, String subscriptionId, String vin) {
        List<CustomerSubscription> customerSubscriptions = customerSubscriptionJpaRepository
                .findBySubscriptionIdAndCustomerIdAndVin(subscriptionId, customerId, vin);
        CustomerSubscription customerSubscription = customerSubscriptions.get(0);
        customerSubscriptionJpaRepository.delete(customerSubscription);
    }
}
