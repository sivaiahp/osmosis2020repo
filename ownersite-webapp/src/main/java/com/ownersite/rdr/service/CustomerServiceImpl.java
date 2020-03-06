/**
 * 
 */
package com.ownersite.rdr.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ownersite.rdr.dto.CustomerDTO;
import com.ownersite.rdr.dto.CustomerServicesDTO;
import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.VehiclesDTO;
import com.ownersite.rdr.entity.Customer;
import com.ownersite.rdr.entity.CustomerServices;
import com.ownersite.rdr.entity.CustomerSubscription;
import com.ownersite.rdr.entity.CustomerVechile;
import com.ownersite.rdr.entity.Vehicle;
import com.ownersite.rdr.repository.CustomerJpaRepository;
import com.ownersite.rdr.repository.CustomerServicesJpaRepository;
import com.ownersite.rdr.repository.CustomerSubscriptionJpaRepository;
import com.ownersite.rdr.repository.CustomerVehicleJpaRepository;
import com.ownersite.rdr.repository.VehicleJpaRepository;

/**
 * @author polamred
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

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
        List<CustomerSubscription> subscriptions = customerSubscriptionJpaRepository.findByCustomerId(Long.parseLong(customerId));
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
        List<CustomerServices> customerServices = customerServicesJpaRepository.findByCustomer(customer);
        List<CustomerServicesDTO> customerServicesDTOs = new ArrayList<>();
        for (CustomerServices customerService:customerServices) {
            CustomerServicesDTO customerServicesDTO = new CustomerServicesDTO();
        }
        return null;
    }

    @Override
    public List<VehiclesDTO> getMyVehicles(String customerId) {
        List<VehiclesDTO> customerServicesDTOs = new ArrayList<>();
        List<CustomerVechile> customerVechiles = customerVehicleJpaRepository.findByCustomerId(Long.parseLong(customerId));
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
	public CustomerDTO getCustomerById(long customerId) {
		Customer customer = customerJpaRepository.findByCustomerId(customerId);
		return buildCustomer(customer);
	}

	@Override
	public List<CustomerDTO> getAllCustomers(long dealerId) {
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		List<CustomerServices> customerServices = customerServicesJpaRepository.findByDealerId(dealerId);
		for (CustomerServices customerService : customerServices){
			Customer customer = customerJpaRepository.findByCustomerId(customerService.getCustomerId());
			customerDTOs.add(buildCustomer(customer));
		}
		return customerDTOs;
	}
	
	private CustomerDTO buildCustomer(Customer customer){
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(customer.getId());
		customerDTO.setCity(customer.getCity());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setAddress(customer.getAddress());
		customerDTO.setCountry(customer.getCountry());
		customerDTO.setFirstname(customer.getFirstname());
		customerDTO.setLastname(customer.getLastname());
		customerDTO.setMobile(customer.getMobile());
		customerDTO.setState(customer.getState());
		return customerDTO;
	}

	@Override
	public void cancelCustomerSubscription(long customerId) {
		if(customerSubscriptionJpaRepository.findByCustomerId(customerId).size() > 0){
			customerSubscriptionJpaRepository.cancelCustomerSubscriptionByCustomerId(customerId);
			logger.info("customer subscription canceld id:",customerId);
		} else {
			logger.info("No subscription for customer Id:", customerId);
		}
	}
}
