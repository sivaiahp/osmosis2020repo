/**
 * 
 */
package com.ownersite.rdr.service;

import com.ownersite.rdr.entity.CustomerServices;
import com.ownersite.rdr.entity.CustomerSubscription;
import com.ownersite.rdr.repository.CustomerServicesJpaRepository;
import com.ownersite.rdr.repository.CustomerSubscriptionJpaRepository;

import java.util.List;

/**
 * @author polamred
 *
 */
public class CustomerServiceImpl implements CustomerService {

    private CustomerSubscriptionJpaRepository customerSubscriptionJpaRepository;

    private CustomerServicesJpaRepository customerServicesJpaRepository;

    @Override
    public List<CustomerSubscription> getAllSubscriptions(String customerId) {
        List<CustomerSubscription> subscriptions = customerSubscriptionJpaRepository.findByCustomer();
        return subscriptions;
    }

    @Override
    public List<CustomerServices> getServiceHistory(String customerId) {
        List<CustomerService> customerServices = customerServicesJpaRepository.findByCustomer();
        return null;
    }
}
