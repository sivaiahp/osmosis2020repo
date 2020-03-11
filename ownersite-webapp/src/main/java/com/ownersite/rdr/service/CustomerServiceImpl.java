/**
 * 
 */
package com.ownersite.rdr.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ownersite.rdr.dto.*;
import com.ownersite.rdr.entity.*;
import com.ownersite.rdr.exception.OwnerSiteException;
import com.ownersite.rdr.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author balamurugan Jothilingam
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
    private SubscriptionJpaRepository subscriptionJpaRepository;
    private ServicesJpaRepository servicesJpaRepository;
    private DealerJpaRepository dealerJpaRepository;
    private CustomerEnquiryJpaRepository customerEnquiryJpaRepository;
    private CustomerFeedbackJpaRepository customerFeedbackJpaRepository;

    /**
     *
     * @param customerSubscriptionJpaRepository
     * @param customerServicesJpaRepository
     * @param customerJpaRepository
     * @param vehicleJpaRepository
     * @param customerVehicleJpaRepository
     * @param subscriptionJpaRepository
     * @param servicesJpaRepository
     * @param dealerJpaRepository
     * @param customerEnquiryJpaRepository
     * @param customerFeedbackJpaRepository
     */
    @Autowired
    public CustomerServiceImpl(CustomerSubscriptionJpaRepository customerSubscriptionJpaRepository,
                               CustomerServicesJpaRepository customerServicesJpaRepository,
                               CustomerJpaRepository customerJpaRepository,
                               VehicleJpaRepository vehicleJpaRepository,
                               CustomerVehicleJpaRepository customerVehicleJpaRepository,
                               SubscriptionJpaRepository subscriptionJpaRepository,
                               ServicesJpaRepository servicesJpaRepository,
                               DealerJpaRepository dealerJpaRepository,
                               CustomerEnquiryJpaRepository customerEnquiryJpaRepository,
                               CustomerFeedbackJpaRepository customerFeedbackJpaRepository) {
        this.customerSubscriptionJpaRepository = customerSubscriptionJpaRepository;
        this.customerServicesJpaRepository = customerServicesJpaRepository;
        this.customerJpaRepository = customerJpaRepository;
        this.vehicleJpaRepository = vehicleJpaRepository;
        this.customerVehicleJpaRepository = customerVehicleJpaRepository;
        this.subscriptionJpaRepository = subscriptionJpaRepository;
        this.servicesJpaRepository = servicesJpaRepository;
        this.dealerJpaRepository = dealerJpaRepository;
        this.customerEnquiryJpaRepository = customerEnquiryJpaRepository;
        this.customerFeedbackJpaRepository =  customerFeedbackJpaRepository;
    }


    /**
     *
     * @param customerId
     * @return
     */
    @Override
    public List<CustomerSubscriptionDTO> getAllSubscriptions(String customerId) {
        List<CustomerSubscriptionDTO> customerSubscriptionDTOs = new ArrayList<>();
        List<CustomerSubscription> subscriptions = customerSubscriptionJpaRepository.findByCustomerId(Long.parseLong(customerId));
        for (CustomerSubscription subscription: subscriptions) {
            customerSubscriptionDTOs.add(buildCustomerSubscriptionDTO(subscription));
        }
        return customerSubscriptionDTOs;
    }

    /**
     *
     * @param subscription
     * @return
     */
    private CustomerSubscriptionDTO buildCustomerSubscriptionDTO(CustomerSubscription subscription) {
        CustomerSubscriptionDTO subscriptionDTO = new CustomerSubscriptionDTO();
        subscriptionDTO.setSubscriptionId(subscription.getId().toString());
        subscriptionDTO.setSubscriptionDesc(subscription.getSubscriptiondec());
        subscriptionDTO.setSubscriptionName(subscription.getSubscriptionname());
        subscriptionDTO.setSubscriptionEndDate(subscription.getCustomer_sub_enddate().toString());
        subscriptionDTO.setSubscriptionStartDate(subscription.getCustomer_sub_startdate().toString());
        subscriptionDTO.setSubscriptionPrice(String.valueOf(subscription.getSubscription().getPrice()));
        subscriptionDTO.setVin(subscription.getVin());
        return subscriptionDTO;
    }

    /**
     *
     * @param customerId
     * @param subscriptionId
     * @return
     */
    @Override
    public List<CustomerServicesDTO> getServiceHistory(String customerId, String subscriptionId) {
        Customer customer = customerJpaRepository.getOne(Long.parseLong(customerId));
        List<com.ownersite.rdr.entity.Service> listservices = servicesJpaRepository.findAll();
        Map<Long,String> serviceNameHm = new HashMap<Long,String>();
        Map<Long,String> serviceDescHm = new HashMap<Long,String>();
        
        
        for(com.ownersite.rdr.entity.Service service: listservices) {
        	serviceNameHm.put(service.getId(), service.getServicename());
        	serviceDescHm.put(service.getId(), service.getServicedec());
        	
        }
        
        if(customer == null) {
        	System.out.println("--------No customer found----");
        }
        List<CustomerServices> customerServices = customerServicesJpaRepository.findByCustomer(customer);
        if(customerServices == null || customerServices.size() ==0) {
        	System.out.println("--------No customer found----");
        }
        List<CustomerServicesDTO> customerServicesDTOs = new ArrayList<>();
        try {
        for (CustomerServices customerService:customerServices) {
            CustomerServicesDTO customerServicesDTO = new CustomerServicesDTO();
            customerServicesDTO.setCustomerId(customerId);
            if(customerService.getDealerId() != null) {
            	 customerServicesDTO.setDealerId(customerService.getDealerId().toString());
            }
           
            customerServicesDTO.setServicecomplaintAnalysis(customerService.getService_analysis_desc());
            customerServicesDTO.setServiceCompletedDate(String.valueOf(customerService.getServiceCompletedDate()));
            customerServicesDTO.setServiceCustomerComplaints(customerService.getService_cust_complaints());
            
            customerServicesDTO.setServicedec(serviceDescHm.get(customerService.getId()));
            customerServicesDTO.setServicename(serviceNameHm.get(customerService.getId()));
            
            customerServicesDTO.setServiceId(customerService.getId().toString());
            
            if(new Double(customerService.getService_cost()) != null) {
            	  customerServicesDTO.setServicePrice(Double.toString(customerService.getService_cost()));
            }
          
            if(new Double(customerService.getService_cost()) != null) {
            customerServicesDTO.setServiceRepairsCost(Double.toString(customerService.getService_cost()));
            }
           
            customerServicesDTO.setServiceRepairsDesc(customerService.getService_repairs_desc());
            
          
            customerServicesDTO.setServiceRequestedDate(String.valueOf(customerService.getServiceStartDate()));
            customerServicesDTO.setVin(String.valueOf(customerService.getVin()));
            customerServicesDTO.setServiceStartedDate(String.valueOf(customerService.getServiceStartDate()));
            customerServicesDTO.setServiceStationId(String.valueOf(customerService.getServiceStationId()));
            
            customerServicesDTOs.add(customerServicesDTO);
        }}
        catch(Exception ex) {
        	ex.printStackTrace();
        }
        return customerServicesDTOs;
    }

    /**
     *
     * @param customerId
     * @return
     */
    @Override
    public List<VehiclesDTO> getMyVehicles(String customerId) {
        List<VehiclesDTO> customerServicesDTOs = new ArrayList<>();

        List<CustomerVechile> customerVechiles = customerVehicleJpaRepository.findByCustomerId(Long.parseLong(customerId));

        //List<CustomerVechile> customerVechiles = customerVehicleJpaRepository.findByCustomerId(customerId);

        for (CustomerVechile customerVechile :customerVechiles) {
            VehiclesDTO vehiclesDTO = new VehiclesDTO();
            vehiclesDTO.setVehicleId(customerVechile.getId().toString());
            Vehicle vehicle = vehicleJpaRepository.getOne(customerVechile.getVechileId());
            vehiclesDTO.setMake(vehicle.getMake());
            vehiclesDTO.setModel(vehicle.getModel());
            vehiclesDTO.setVin(customerVechile.getVin());
            vehiclesDTO.setRegisteredNumber(customerVechile.getRegisteredNumber());
            vehiclesDTO.setYear(vehicle.getYear().toString());
            vehiclesDTO.setCustomerId(customerId);
            vehiclesDTO.setRdrRegisteredDate(customerVechile.getRdrRegisteredDate());
            customerServicesDTOs.add(vehiclesDTO);
        }
        return customerServicesDTOs;
    }

    /**
     *
     * @param customerId
     * @return
     */
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

    /**
     *
     * @param customer
     * @return
     */
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

    /**
     *
     * @param customerId
     */
	@Override
	public void cancelCustomerSubscription(long customerId) {
		if(customerSubscriptionJpaRepository.findByCustomerId(customerId).size() > 0){
			customerSubscriptionJpaRepository.cancelCustomerSubscriptionByCustomerId(customerId);
			logger.info("customer subscription canceld id:",customerId);
		} else {
			logger.info("No subscription for customer Id:", customerId);
		}
	}

    /**
     *
     * @param vin
     * @return
     */
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

    /**
     *
     * @param vehiclesDTO
     */
    @Override
    public void addVinForCustomer(VehiclesDTO vehiclesDTO) {
        CustomerVechile customerVechile = customerVehicleJpaRepository.findByVin(vehiclesDTO.getVin());
        customerVechile.setCustomerId(Long.parseLong(vehiclesDTO.getCustomerId()));
        customerVehicleJpaRepository.save(customerVechile);
    }

    /**
     *
     * @param subscriptionId
     * @param vin
     */
    @Override
    public void addVinForSubscription(String subscriptionId, String vin) {
    	CustomerSubscription customerSubscription = customerSubscriptionJpaRepository.getOne(Long.parseLong(subscriptionId));
    	if(vin.equals("-1")) {
    		customerSubscription.setVin(null);
    	}else {
    		customerSubscription.setVin(vin);
    	}
    	
    	customerSubscriptionJpaRepository.save(customerSubscription);
    }

    /**
     *
     * @param customerId
     * @param subscriptionId
     * @param vehicleId
     * @param subscriptionStartDate
     * @param subscriptionEndDate
     * @throws ParseException
     */
    @Override
    public void addCustomerSubscription(String customerId, String subscriptionId, String vehicleId,
                                        String subscriptionStartDate, String subscriptionEndDate) throws ParseException {
        Customer customer = customerJpaRepository.getOne(Long.parseLong(customerId));
        Subscription subscription = subscriptionJpaRepository.getOne(Long.parseLong(subscriptionId));
        CustomerVechile customerVechile = null;
        if(!vehicleId.equals("-1")) {
        	 customerVechile = customerVehicleJpaRepository.getOne(Long.parseLong(vehicleId));
        }

        CustomerSubscription customerSubscription = buildCustomerSubscription(customerId, vehicleId, subscriptionStartDate, subscriptionEndDate, customer, subscription, customerVechile);
        CustomerSubscription savedCustomerSub = customerSubscriptionJpaRepository.save(customerSubscription);
        
		List<ServiceDTO> serviceDTOs = null;

		serviceDTOs = subscription.getSubscriptionServicRegistrations().stream()
				.map(com.ownersite.rdr.entity.SubscriptionService::getService).map(ServiceDTO::new)
				.collect(Collectors.toList());
		
		for(ServiceDTO serviceDTO:serviceDTOs) {
			CustomerServices customerServicesNew = new CustomerServices();
			customerServicesNew.setCustomer(customer);
			customerServicesNew.setCompanyServiceId(serviceDTO.getServiceId());
			customerServicesNew.setCustomerSubId(savedCustomerSub.getId());
			customerServicesJpaRepository.save(customerServicesNew);
		}
		
        
    }

    /**
     *
     * @param customerId
     * @param vehicleId
     * @param subscriptionStartDate
     * @param subscriptionEndDate
     * @param customer
     * @param subscription
     * @param customerVechile
     * @return
     * @throws ParseException
     */
    private CustomerSubscription buildCustomerSubscription(String customerId, String vehicleId, String subscriptionStartDate, String subscriptionEndDate,
                                                           Customer customer, Subscription subscription, CustomerVechile customerVechile) throws ParseException {
        CustomerSubscription customerSubscription = new CustomerSubscription();
        customerSubscription.setCustomer(customer);
        customerSubscription.setSubscription(subscription);
        customerSubscription.setCustomer_sub_startdate(new SimpleDateFormat("dd/MM/yyyy").parse(subscriptionStartDate));
        customerSubscription.setCustomer_sub_enddate(new SimpleDateFormat("dd/MM/yyyy").parse(subscriptionEndDate));
        customerSubscription.setCustomerId(Long.parseLong(customerId));
        customerSubscription.setSubscriptionname(subscription.getSubscriptionname());
        customerSubscription.setSubscriptiondec(subscription.getSubscriptiondec());
        customerSubscription.setPrice(new Double(subscription.getPrice()));
        if(!vehicleId.equals("-1")) {
        customerSubscription.setVin(customerVechile.getVin());
        }
        return customerSubscription;
    }

    /**
     *
     * @param customerId
     * @param subscriptionId
     * @param vin
     */
    @Override
    public void transferSubscription(String customerId, String subscriptionId, String vin) {
        List<CustomerSubscription> customerSubscriptions = customerSubscriptionJpaRepository
                .findBySubscriptionIdAndCustomerId(Long.valueOf(subscriptionId), Long.valueOf(customerId));
        CustomerSubscription customerSubscription = customerSubscriptions.get(0);
        customerSubscription.setVin(vin);
        customerSubscriptionJpaRepository.save(customerSubscription);
    }

    /**
     *
     * @param customerId
     * @param subscriptionId
     * @param vin
     */
    @Override
    public void cancelSubscription(String customerId, String subscriptionId, String vin) {
    	try {
    		
        customerSubscriptionJpaRepository.deleteById(Long.valueOf(subscriptionId));
        }
    	catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }


    /**
     *
     * @param customerId
     * @param rdrCustConfirmedDate
     * @param vin
     */
    @Override
    public void confirmRDR(String customerId, String rdrCustConfirmedDate, String vin) {
        CustomerVechile customerVechile = customerVehicleJpaRepository.findByVin(vin);
        System.out.println(customerVechile.getCustomerId());
        if(customerVechile.getCustomerId().toString().equals(customerId)){
            try {
                customerVechile.setRdrRegisteredDate(new SimpleDateFormat("dd/MM/yyyy").parse(rdrCustConfirmedDate));
                System.out.println(customerVechile.getRdrRegisteredDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            customerVehicleJpaRepository.save(customerVechile);
        }
    }

    /**
     *
     * @param customerId
     * @param serviceId
     * @param vin
     * @param dealerId
     */
    @Override
    public void addCustomerService(String customerId, String serviceId, String vin, String dealerId) {
        Customer customer = customerJpaRepository.getOne(Long.parseLong(customerId));
        com.ownersite.rdr.entity.Service service = servicesJpaRepository.getOne(Long.parseLong(serviceId));
        Dealer dealer = dealerJpaRepository.getOne(Long.parseLong(dealerId));
        CustomerServices customerServices = new CustomerServices();
        customerServices.setCustomer(customer);
        customerServices.setService(service);
        customerServices.setServiceRequestedDate(new SimpleDateFormat("DD/MM/yyyy").format( new java.util.Date()));
        customerServices.setDealer(dealer);
        customerServices.setVin(vin);
        customerServicesJpaRepository.save(customerServices);
    }

    /**
     *
     * @param enquiry_created_date
     * @param enquiry_resolved_date
     * @param enquiry_question
     * @param enquiry_answer
     * @param customerId
     * @param dealerId
     */
    @Override
    public void addCustomerEnquiry(String enquiry_created_date, String enquiry_resolved_date,
                                   String enquiry_question, String enquiry_answer, String customerId, String dealerId) {
        try {
            Customer customer = customerJpaRepository.getOne(Long.parseLong(customerId));
            CustomerEnquiry customerEnquiry = new CustomerEnquiry();
            
            customerEnquiry.setCustomer(customer);
            if(!dealerId.equals("-1")) {
            	 Dealer dealer = dealerJpaRepository.getOne(Long.parseLong(dealerId));
                 customerEnquiry.setDealer(dealer);
            }
           
            customerEnquiry.setEnquiry_question(enquiry_question);
            //customerEnquiry.setEnquiry_answer(enquiry_answer);
            customerEnquiry.setEnquiry_created_date(new SimpleDateFormat("dd/MM/yyyy").parse(enquiry_created_date));
            //customerEnquiry.setEnquiry_resolved_date(new SimpleDateFormat("dd/MM/yyyy").parse(enquiry_resolved_date));
            customerEnquiryJpaRepository.save(customerEnquiry);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param enquiry_created_date
     * @param enquiry_resolved_date
     * @param enquiry_question
     * @param enquiry_answer
     * @param customerId
     * @param dealerId
     */
    @Override
    public void addCustomerFeedback(String enquiry_created_date, String enquiry_resolved_date, String enquiry_question,
                                    String enquiry_answer, String customerId, String dealerId) {
        try {
            Customer customer = customerJpaRepository.getOne(Long.parseLong(customerId));
            CustomerFeedback customerFeedback = new CustomerFeedback();
            Dealer dealer = dealerJpaRepository.getOne(Long.parseLong(dealerId));
            customerFeedback.setCustomer(customer);
            customerFeedback.setDealer(dealer);
            customerFeedback.setEnquiry_question(enquiry_question);
            customerFeedback.setEnquiry_answer(enquiry_answer);
            customerFeedback.setEnquiry_created_date(new SimpleDateFormat("dd/MM/yyyy").parse(enquiry_created_date));
            customerFeedback.setEnquiry_resolved_date(new SimpleDateFormat("dd/MM/yyyy").parse(enquiry_resolved_date));
            customerFeedbackJpaRepository.save(customerFeedback);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param customerId
     * @return
     */
    @Override
    public List<CustomerEnquiryDTO> getAllEnquiriesForCustomerId(String customerId) {
        List<CustomerEnquiryDTO> customerEnquiryDTOList = new ArrayList<>();
        List<CustomerEnquiry> customerEnquiries = customerEnquiryJpaRepository.findByCustomerId(Long.parseLong(customerId));
        buildCustomerEnquiry(customerEnquiryDTOList, customerEnquiries);
        return customerEnquiryDTOList;
    }

    /**
     *
     * @param customerEnquiryDTOList
     * @param customerEnquiries
     */
    private void buildCustomerEnquiry(List<CustomerEnquiryDTO> customerEnquiryDTOList, List<CustomerEnquiry> customerEnquiries) {
        for (CustomerEnquiry customerEnquiry : customerEnquiries) {
            CustomerEnquiryDTO customerEnquiryDTO = new CustomerEnquiryDTO();
            customerEnquiryDTO.setEnquiryId(customerEnquiry.getId().toString());
            customerEnquiryDTO.setCustomerId(customerEnquiry.getCustomer().getId().toString());
            
            if(customerEnquiry.getDealer()!=null)
            customerEnquiryDTO.setDealerId(customerEnquiry.getDealer().getId().toString());
            
            customerEnquiryDTO.setEnquiryQuestion(customerEnquiry.getEnquiry_question());
            customerEnquiryDTO.setEnquiryAnswer(customerEnquiry.getEnquiry_answer());
            
            if(customerEnquiry.getEnquiry_created_date()!=null)
            customerEnquiryDTO.setEnquiryCreatedDate(new SimpleDateFormat("DD/MM/yyyy").format(customerEnquiry.getEnquiry_created_date()));
            
            if(customerEnquiry.getEnquiry_resolved_date()!=null)
            customerEnquiryDTO.setEnquiryResolvedDate(new SimpleDateFormat("DD/MM/yyyy").format(customerEnquiry.getEnquiry_resolved_date()));
            customerEnquiryDTOList.add(customerEnquiryDTO);
        }
    }

    /**
     *
     * @param dealerId
     * @return
     */
    @Override
    public List<CustomerEnquiryDTO> getAllEnquiriesForDealerId(String dealerId) {
        List<CustomerEnquiryDTO> customerEnquiryDTOList = new ArrayList<>();
        List<CustomerEnquiry> customerEnquiries = customerEnquiryJpaRepository.findByDealerId(Long.parseLong(dealerId));
        buildCustomerEnquiry(customerEnquiryDTOList, customerEnquiries);
        return customerEnquiryDTOList;
    }

    /**
     * gets all feedback for customer
     * @param customerId - customerId
     * @return customerFeedbackDTOList
     */
    @Override
    public List<CustomerFeedbackDTO> getAllFeedbackForCustomerId(String customerId) {
        logger.info("getting feedback for customer: {}", customerId);
        List<CustomerFeedbackDTO> customerFeedbackDTOList = new ArrayList<>();
        List<CustomerFeedback> customerEnquiries = customerFeedbackJpaRepository.findByCustomerId(customerId);
        buildCustomerFeedback(customerFeedbackDTOList, customerEnquiries);
        return customerFeedbackDTOList;
    }

    /**
     * Builds customer feedback entity
     * @param customerFeedbackDTOList - customerFeedbackDTOList
     * @param customerEnquiries - customerEnquiries
     */
    private void buildCustomerFeedback(List<CustomerFeedbackDTO> customerFeedbackDTOList, List<CustomerFeedback> customerEnquiries) {
        for (CustomerFeedback customerFeedback : customerEnquiries) {
            CustomerFeedbackDTO customerFeedbackDTO = new CustomerFeedbackDTO();
            customerFeedbackDTO.setCustomerFeedbackId(customerFeedback.getId().toString());
            customerFeedbackDTO.setCustomerId(customerFeedback.getCustomer().getId().toString());
            customerFeedbackDTO.setDealerId(customerFeedback.getDealer().getId().toString());
            customerFeedbackDTO.setEnquiryQuestion(customerFeedback.getEnquiry_question());
            customerFeedbackDTO.setEnquiryAnswer(customerFeedback.getEnquiry_answer());
            customerFeedbackDTO.setEnquiryCreatedDate(new SimpleDateFormat("DD/MM/yyyy").format(customerFeedback.getEnquiry_created_date()));
            customerFeedbackDTO.setEnquiryResolvedDate(new SimpleDateFormat("DD/MM/yyyy").format(customerFeedback.getEnquiry_resolved_date()));
            customerFeedbackDTOList.add(customerFeedbackDTO);
        }
    }

    /**
     * gets all the feedback for the dealer
     * @param dealerId - dealer id
     * @return list of feedback
     */
    @Override
    public List<CustomerFeedbackDTO> getAllFeedbackForDealerId(String dealerId) {
        logger.info("getting feedback for dealer: {}", dealerId);
        List<CustomerFeedbackDTO> customerFeedbackDTOList = new ArrayList<>();
        List<CustomerFeedback> customerFeedback = customerFeedbackJpaRepository.findByDealerId(dealerId);
        buildCustomerFeedback(customerFeedbackDTOList, customerFeedback);
        logger.info("customerFeedback retrieved successfully");
        return customerFeedbackDTOList;
    }

    /**
     * Updates enquiry
     * @param customerEnquiryDTO - customerEnquiryDTO
     * @throws OwnerSiteException when error occurs
     */
    @Override
    public void updateEnquiry(CustomerEnquiryDTO customerEnquiryDTO) throws OwnerSiteException {
        logger.info("updating customerEnquiryDTO for {}", customerEnquiryDTO);

        CustomerEnquiry customerEnquiry = customerEnquiryDTO.convertToEntity();
        if (subscriptionJpaRepository.findBySubscriptionId(customerEnquiry.getId()) == null) {
            logger.error("Invalid CustomerEnquiry");
            throw new OwnerSiteException("Invalid CustomerEnquiry");
        } else {
            customerEnquiryJpaRepository.save(customerEnquiry);
        }

        logger.info("customerEnquiry updated successfully");
    }
}
