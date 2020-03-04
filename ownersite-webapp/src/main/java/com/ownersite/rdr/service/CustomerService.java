/**
 * 
 */
package com.ownersite.rdr.service;

import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.entity.CustomerServices;

import java.util.List;

/**
 * @author polamred
 *
 */
public interface CustomerService {

    List<CustomerSubscriptionDTO> getAllSubscriptions(String customerId);

    List<CustomerServicesDTO> getServiceHistory(String customerId);
}
