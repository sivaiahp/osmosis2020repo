/**
 * 
 */
package com.ownersite.rdr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ownersite.rdr.repository.ServicesJpaRepository;
import com.ownersite.rdr.view.ServicesView;

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
	public List<ServicesView> getAllServices() {
		
		List<ServicesView> listserviceviews = new ArrayList<>();
		ServicesView serviceView = new ServicesView();
		serviceView.setServiceId(1L);
		serviceView.setServiceDec("hai");
		serviceView.setServiceName("sample");
		listserviceviews.add(serviceView);
		/*List<Services> listservices = servicesJpaRepository.findAll();
		for(Services service: listservices){
			if(service != null){
				serviceView = new ServicesView();
				serviceView.setServiceId(service.getId());
				serviceView.setServiceDec(service.getServicedec());
				serviceView.setServiceName(serviceView.getServiceName());
				listserviceviews.add(serviceView);
			}
		}*/
		return listserviceviews;
	}

}
