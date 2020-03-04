/**
 * 
 */
package com.ownersite.rdr.service;

import com.ownersite.rdr.entity.CustomerServices;
import com.ownersite.rdr.entity.CustomerSubscription;

import java.util.List;

/**
 * @author polamred
 *
 */
public interface CustomerService {

    List<CustomerSubscription> getAllSubscriptions(String customerId);

    List<CustomerServices> getServiceHistory(String customerId);
}
