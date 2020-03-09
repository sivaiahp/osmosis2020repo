/**
 * 
 */
package com.ownersite.rdr.service;

import java.util.List;

import com.ownersite.rdr.dto.ServiceStationDTO;

/**
 * @author polamred
 *
 */
public interface ServiceStationService {

	public List<ServiceStationDTO> getAllServiceStations();
	
	public ServiceStationDTO getServiceStationById(long serviceStationId);
}
