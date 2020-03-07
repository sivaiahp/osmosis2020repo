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
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ownersite.rdr.dto.CustomerDTO;
import com.ownersite.rdr.dto.CustomerServicesDTO;
import com.ownersite.rdr.dto.CustomerSubscriptionDTO;
import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.dto.VehiclesDTO;
import com.ownersite.rdr.entity.Customer;
import com.ownersite.rdr.entity.CustomerServices;
import com.ownersite.rdr.entity.CustomerSubscription;
import com.ownersite.rdr.entity.CustomerVechile;
import com.ownersite.rdr.entity.Subscription;
import com.ownersite.rdr.entity.Vehicle;
import com.ownersite.rdr.repository.CustomerJpaRepository;
import com.ownersite.rdr.repository.CustomerServicesJpaRepository;
import com.ownersite.rdr.repository.CustomerSubscriptionJpaRepository;
import com.ownersite.rdr.repository.CustomerVehicleJpaRepository;
import com.ownersite.rdr.repository.ServicesJpaRepository;
import com.ownersite.rdr.repository.SubscriptionJpaRepository;
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
    private SubscriptionJpaRepository subscriptionJpaRepository;
    private ServicesJpaRepository servicesJpaRepository;
  

    @Autowired
    public CustomerServiceImpl(CustomerSubscriptionJpaRepository customerSubscriptionJpaRepository,
                               CustomerServicesJpaRepository customerServicesJpaRepository,
                               CustomerJpaRepository customerJpaRepository,
                               VehicleJpaRepository vehicleJpaRepository,
                               CustomerVehicleJpaRepository customerVehicleJpaRepository,
                               SubscriptionJpaRepository subscriptionJpaRepository,
                               ServicesJpaRepository servicesJpaRepository) {
        this.customerSubscriptionJpaRepository = customerSubscriptionJpaRepository;
        this.customerServicesJpaRepository = customerServicesJpaRepository;
        this.customerJpaRepository = customerJpaRepository;
        this.vehicleJpaRepository = vehicleJpaRepository;
        this.customerVehicleJpaRepository = customerVehicleJpaRepository;
        this.subscriptionJpaRepository = subscriptionJpaRepository;
        this.servicesJpaRepository = servicesJpaRepository;
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
        subscriptionDTO.setSubscriptionName(subscription.getSubscriptionname());
        subscriptionDTO.setSubscriptionEndDate(subscription.getCustomer_sub_enddate().toString());
        subscriptionDTO.setSubscriptionStartDate(subscription.getCustomer_sub_startdate().toString());
        subscriptionDTO.setSubscriptionPrice(String.valueOf(subscription.getSubscription().getPrice()));
        subscriptionDTO.setVin(subscription.getVin());
        return subscriptionDTO;
    }

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
    public void addVinForSubscription(String subscriptionId, String vin) {
    	CustomerSubscription customerSubscription = customerSubscriptionJpaRepository.getOne(Long.parseLong(subscriptionId));
    	if(vin.equals("-1")) {
    		customerSubscription.setVin(null);
    	}else {
    		customerSubscription.setVin(vin);
    	}
    	
    	customerSubscriptionJpaRepository.save(customerSubscription);
    }

    @Override
    public void addCustomerSubscription(String customerId, String subscriptionId, String vehicleId,
                                        String subscriptionStartDate, String subscriptionEndDate) throws ParseException {
        Customer customer = customerJpaRepository.getOne(Long.parseLong(customerId));
        Subscription subscription = subscriptionJpaRepository.getOne(Long.parseLong(subscriptionId));
        CustomerVechile customerVechile = null;
        if(!vehicleId.equals("-1")) {
        	 customerVechile = customerVehicleJpaRepository.getOne(Long.parseLong(vehicleId));
        }
       
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

    @Override
    public void transferSubscription(String customerId, String subscriptionId, String vin) {
        List<CustomerSubscription> customerSubscriptions = customerSubscriptionJpaRepository
                .findBySubscriptionIdAndCustomerId(Long.valueOf(subscriptionId), Long.valueOf(customerId));
        CustomerSubscription customerSubscription = customerSubscriptions.get(0);
        customerSubscription.setVin(vin);
        customerSubscriptionJpaRepository.save(customerSubscription);
    }

    @Override
    public void cancelSubscription(String customerId, String subscriptionId, String vin) {
    	try {
    		
        customerSubscriptionJpaRepository.deleteById(Long.valueOf(subscriptionId));
        }
    	catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }

}
