/**
 * 
 */
package com.ownersite.rdr.service;

import com.ownersite.rdr.dto.CustomerDTO;
import com.ownersite.rdr.dto.CustomerServicesDTO;
import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.VehiclesDTO;

import java.text.ParseException;
import java.util.List;

/**
 * @author polamred
 *
 */
public interface CustomerService {

    List<CustomerSubscriptionDTO> getAllSubscriptions(String customerId);

    List<CustomerServicesDTO> getServiceHistory(String customerId, String subscriptionId);

    List<VehiclesDTO> getMyVehicles(String customerId);
    
    CustomerDTO getCustomerById(long customerId);
    
    List<CustomerDTO> getAllCustomers(long dealerId);
    
    void cancelCustomerSubscription(long customerId);

    VehiclesDTO getVehicle(String vin);

    void addVinForCustomer(VehiclesDTO vehiclesDTO);

    void addCustomerSubscription(String customerId, String subscriptionId, String vehicleId,
                                 String subscriptionStartDate, String subscriptionEndDate) throws ParseException;

    void transferSubscription(String customerId, String subscriptionId, String vin);

    void cancelSubscription(String customerId, String subscriptionId, String vin);

	void addVinForSubscription(String subscriptionId, String vin);

}
