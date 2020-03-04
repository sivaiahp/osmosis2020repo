/**
 * 
 */
package com.ownersite.rdr.service;

import com.ownersite.rdr.dto.CustomerServicesDTO;
import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.entity.CustomerServices;
import com.ownersite.rdr.entity.CustomerSubscription;
import com.ownersite.rdr.repository.CustomerServicesJpaRepository;
import com.ownersite.rdr.repository.CustomerSubscriptionJpaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author polamred
 *
 */
public class CustomerServiceImpl implements CustomerService {

    private CustomerSubscriptionJpaRepository customerSubscriptionJpaRepository;

    private CustomerServicesJpaRepository customerServicesJpaRepository;

    public CustomerServiceImpl(CustomerSubscriptionJpaRepository customerSubscriptionJpaRepository, CustomerServicesJpaRepository customerServicesJpaRepository) {
        this.customerSubscriptionJpaRepository = customerSubscriptionJpaRepository;
        this.customerServicesJpaRepository = customerServicesJpaRepository;
    }

    @Override
    public List<CustomerSubscriptionDTO> getAllSubscriptions(String customerId) {
        List<CustomerSubscriptionDTO> customerSubscriptionDTOs = new ArrayList<>();
        List<CustomerSubscription> subscriptions = customerSubscriptionJpaRepository.findByCustomer();
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
        List<CustomerService> customerServices = customerServicesJpaRepository.findByCustomer();
        return null;
    }
}
