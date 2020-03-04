/**
 * 
 */
package com.ownersite.rdr.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ownersite.rdr.entity.Subscription;
import com.ownersite.rdr.repository.ServicesJpaRepository;
import com.ownersite.rdr.repository.SubscriptionJpaRepository;
import com.ownersite.rdr.repository.SubscriptionServiceJpaRepository;

/**
 * @author polamred
 *
 */
@Service
public class ManufacturerServiceImpl implements ManufacturerService {
	private static final Logger logger = LoggerFactory.getLogger(ManufacturerServiceImpl.class);
	
	private final ServicesJpaRepository servicesJpaRepository;
	
	private final SubscriptionJpaRepository subscriptionJpaRepository;

	private final SubscriptionServiceJpaRepository subscriptionServiceJpaRepository;
	
	@Autowired
	public ManufacturerServiceImpl(ServicesJpaRepository servicesJpaRepository, SubscriptionJpaRepository subscriptionJpaRepository,
			SubscriptionServiceJpaRepository subscriptionServiceJpaRepository){
		this.servicesJpaRepository = servicesJpaRepository;
		this.subscriptionJpaRepository = subscriptionJpaRepository;
		this.subscriptionServiceJpaRepository = subscriptionServiceJpaRepository;
	}
	
	
	@Override
	public List<com.ownersite.rdr.entity.Service> getAllServices() {
		logger.info("loading all services=============");
		List<com.ownersite.rdr.entity.Service> listservices = servicesJpaRepository.findAll();
		
		return listservices;
	}


	@Override
	public void addService(com.ownersite.rdr.entity.Service service) {
		logger.info("creating service for:", service);
		servicesJpaRepository.save(service);
	}


	@Override
	public void deleteService(long serviceId) {
		logger.info("delete serviceId:", serviceId);
		servicesJpaRepository.deleteById(serviceId);
	}


	@Override
	public void updateService(com.ownersite.rdr.entity.Service service) {
		logger.info("updating service for:", service);
		servicesJpaRepository.save(service);
	}


	@Override
	public com.ownersite.rdr.entity.Service findServiceById(long serviceId) {
				
		return servicesJpaRepository.findByServiceId(serviceId);
	}


	@Override
	public void createSubscription(Subscription subscription) {
		logger.info("creating Subscription for:", subscription);
		subscriptionJpaRepository.save(subscription);
	}


	@Override
	public void updateSubscription(Subscription subscription) {
		logger.info("updating Subscription for:", subscription);
		try {
			if(subscriptionJpaRepository.findBySubscriptionId(subscription.getId()) != null){
				subscriptionJpaRepository.save(subscription);
			}
		} catch (Exception e){
			logger.error("recieved error while updateing subscription........for:", subscription);
		}
	}


	@Override
	public void deleteSubscription(long subscriptionId) {
		logger.info("delete Subscription for:", subscriptionId);
		
		try {
			if(subscriptionJpaRepository.findBySubscriptionId(subscriptionId) != null){
				if(subscriptionServiceJpaRepository.findBySubscriptionId(subscriptionId).size() > 0){
					subscriptionServiceJpaRepository.deleteBySubscriptionId(subscriptionId);
				}
				subscriptionJpaRepository.deleteById(subscriptionId);
			}
		} catch (Exception e){
			logger.error("recieved error while deleting subscription........id:", subscriptionId);
		}
	}


	@Override
	public List<Subscription> getAllSubscriptions() {
		logger.info("loding Subscriptions==========");
		return subscriptionJpaRepository.findAll();
	}


	@Override
	public Subscription findBySubscriptionId(long id) {
		logger.info("find Subscription id:", id);
		return subscriptionJpaRepository.findBySubscriptionId(id);
	}

}
