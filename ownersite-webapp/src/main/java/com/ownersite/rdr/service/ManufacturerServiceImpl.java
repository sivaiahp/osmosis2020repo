/**
 * 
 */
package com.ownersite.rdr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ownersite.rdr.repository.ServicesJpaRepository;

/**
 * @author polamred
 *
 */
@Service
public class ManufacturerServiceImpl implements ManufacturerService {
	
	
	private final ServicesJpaRepository servicesJpaRepository;

	
	@Autowired
	public ManufacturerServiceImpl(ServicesJpaRepository servicesJpaRepository){
		this.servicesJpaRepository = servicesJpaRepository;
	}
	
	
	@Override
	public List<com.ownersite.rdr.entity.Service> getAllServices() {
		
		
		List<com.ownersite.rdr.entity.Service> listservices = servicesJpaRepository.findAll();
		
		return listservices;
	}


	@Override
	public void addService(com.ownersite.rdr.entity.Service service) {
		
		servicesJpaRepository.save(service);
	}


	@Override
	public void deleteService(Long serviceId) {
		
		servicesJpaRepository.deleteById(serviceId);
	}


	@Override
	public void updateService(com.ownersite.rdr.entity.Service service) {
		
		servicesJpaRepository.save(service);
	}


	@Override
	public com.ownersite.rdr.entity.Service findServiceById(Long serviceId) {
				
		return null;
	}

}
