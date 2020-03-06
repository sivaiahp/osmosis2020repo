/**
 * 
 */
package com.ownersite.rdr.service;

import com.ownersite.rdr.dto.CustomerDTO;
import com.ownersite.rdr.dto.CustomerServicesDTO;
import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.VehiclesDTO;

import java.util.List;

/**
 * @author polamred
 *
 */
public interface CustomerService {

    List<CustomerSubscriptionDTO> getAllSubscriptions(String customerId);

    List<CustomerServicesDTO> getServiceHistory(String customerId);

    List<VehiclesDTO> getMyVehicles(String customerId);
    
    CustomerDTO getCustomerById(long customerId);
    
    List<CustomerDTO> getAllCustomers(long dealerId);
    
    void cancelCustomerSubscription(long customerId);
}
