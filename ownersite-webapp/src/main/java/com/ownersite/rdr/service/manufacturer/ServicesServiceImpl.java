/**
 * 
 */
package com.ownersite.rdr.service.manufacturer;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.repository.ServicesJpaRepository;

/**
 * @author polamred
 *
 */
@Service
public class ServicesServiceImpl implements ServicesService {
	private static final Logger logger = LoggerFactory.getLogger(ServicesServiceImpl.class);

	private final ServicesJpaRepository servicesJpaRepository;

	@Autowired
	public ServicesServiceImpl(ServicesJpaRepository servicesJpaRepository) {
		this.servicesJpaRepository = servicesJpaRepository;
	}

	@Override
	public List<ServiceDTO> getAllServices() {
		logger.info("loading all services=============");
		List<com.ownersite.rdr.entity.Service> listservices = servicesJpaRepository.findAll();

		return listservices.stream().map(ServiceDTO::new).collect(Collectors.toList());
	}

	@Override
	public void addService(ServiceDTO serviceDTO) {
		logger.info("creating service for:", serviceDTO);
		servicesJpaRepository.save(serviceDTO.convertToEntity(serviceDTO));
	}

	@Override
	public void deleteService(long serviceId) {
		logger.info("delete serviceId:", serviceId);
		servicesJpaRepository.deleteById(serviceId);
	}

	@Override
	public void updateService(ServiceDTO serviceDTO) {
		logger.info("updating service for:", serviceDTO);
		servicesJpaRepository.save(serviceDTO.convertToEntity(serviceDTO));
	}

	@Override
	public ServiceDTO findServiceById(long serviceId) {

		return new ServiceDTO(servicesJpaRepository.findByServiceId(serviceId));
	}

}
