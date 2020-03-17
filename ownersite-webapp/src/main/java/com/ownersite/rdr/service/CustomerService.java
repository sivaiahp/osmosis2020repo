/**
 * 
 */
package com.ownersite.rdr.service;

import com.ownersite.rdr.dto.*;
import com.ownersite.rdr.exception.OwnerSiteException;

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

    void confirmRDR(String customerId, String rdrCustConfirmedDate, String vin);

    void addCustomerEnquiry(String enquiry_created_date, String enquiry_resolved_date, String enquiry_question,
                            String enquiry_answer, String customerId, String dealerId);

    void addCustomerFeedback(String enquiry_created_date, String enquiry_resolved_date, String enquiry_question,
                             String enquiry_answer, String customerId, String dealerId);

    List<CustomerEnquiryDTO> getAllEnquiriesForCustomerId(String customerId);

    List<CustomerEnquiryDTO> getAllEnquiriesForDealerId(String  dealerId);

    List<CustomerFeedbackDTO> getAllFeedbackForCustomerId(String customerId);

    List<CustomerFeedbackDTO> getAllFeedbackForDealerId(String dealerId);

    void updateEnquiry(CustomerEnquiryDTO customerEnquiryDTO) throws OwnerSiteException;

    void updateCustomerService(String customerId, String serviceId, String vin, String dealerId, String complaints);

	List<CustomerServicesDTO> getServiceHistoryDealer(String dealerId, String subscriptionId);

}
