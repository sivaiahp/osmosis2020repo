/**
 * 
 */
package com.ownersite.rdr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ownersite.rdr.dto.ServiceStationDTO;
import com.ownersite.rdr.entity.ServiceStation;
import com.ownersite.rdr.repository.ServiceStationJpaRepository;

/**
 * @author polamred
 *
 */

@Service
public class ServiceStationServiceImpl implements ServiceStationService {

	private final ServiceStationJpaRepository serviceStationJpaRepository;

	@Autowired
	public ServiceStationServiceImpl(ServiceStationJpaRepository serviceStationJpaRepository) {
		this.serviceStationJpaRepository = serviceStationJpaRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownersite.rdr.service.ServiceStationService#getAllServiceStations()
	 */
	@Override
	public List<ServiceStationDTO> getAllServiceStations() {
		List<ServiceStation> serviceStationList = serviceStationJpaRepository.findAll();

		List<ServiceStationDTO> serviceStationDTOList = new ArrayList<>();
		for (ServiceStation serviceStation : serviceStationList) {
			serviceStationDTOList.add(makeServiceStationDTO(serviceStation));
		}
		return serviceStationDTOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ownersite.rdr.service.ServiceStationService#getServiceStationById(
	 * long)
	 */
	@Override
	public ServiceStationDTO getServiceStationById(long serviceStationId) {
		ServiceStation serviceStation = serviceStationJpaRepository.findByServiceStationId(serviceStationId);
		return makeServiceStationDTO(serviceStation);
	}

	private ServiceStationDTO makeServiceStationDTO(ServiceStation serviceSation) {
		ServiceStationDTO serviceStationDTO = new ServiceStationDTO();
		serviceStationDTO.setServiceStationId(serviceSation.getId());
		serviceStationDTO.setFirstname(serviceSation.getFirstname());
		serviceStationDTO.setLastname(serviceSation.getLastname());
		serviceStationDTO.setMobile(serviceSation.getMobile());
		serviceStationDTO.setState(serviceSation.getState());
		serviceStationDTO.setCity(serviceSation.getCity());
		serviceStationDTO.setCountry(serviceSation.getCountry());
		serviceStationDTO.setAddress(serviceSation.getAddress());
		serviceStationDTO.setEmail(serviceSation.getEmail());
		return serviceStationDTO;
	}

}
