package com.ownersite.rdr.service.manufacturer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.entity.Service;
import com.ownersite.rdr.exception.OwnerSiteException;
import com.ownersite.rdr.repository.ServicesJpaRepository;

@RunWith(MockitoJUnitRunner.class)
public class ServicesServiceTest {

	@Mock
	private ServicesJpaRepository servicesJpaRepository;
	@InjectMocks
	private ServicesServiceImpl service;

	@Test
	public void getAllServicesTest() {
		List<Service> services = new ArrayList<>();
		Service service = new Service();
		service.setId(1L);
		service.setServicename("S1");
		service.setServicedec("Service 1");
		services.add(service);
		service = new Service();
		service.setId(2L);
		service.setServicename("S2");
		service.setServicedec("Service 2");
		services.add(service);

		doReturn(services).when(servicesJpaRepository).findAll();

		List<ServiceDTO> actualResponse = this.service.getAllServices();

		assertEquals(services.size(), actualResponse.size());
		assertEquals(services.get(0).getId(), actualResponse.get(0).getServiceId());
		assertEquals(services.get(0).getServicename(), actualResponse.get(0).getServicename());
		assertEquals(services.get(0).getServicedec(), actualResponse.get(0).getServicedec());
		assertEquals(services.get(1).getId(), actualResponse.get(1).getServiceId());
		assertEquals(services.get(1).getServicename(), actualResponse.get(1).getServicename());
		assertEquals(services.get(1).getServicedec(), actualResponse.get(1).getServicedec());

	}

	@Test
	public void addServiceTest() {
		ServiceDTO dto = new ServiceDTO();
		dto.setServiceId(3L);
		dto.setServicename("Service 3");
		dto.setServicedec("S3");

		doReturn(new Service()).when(servicesJpaRepository).save(any(Service.class));

		this.service.addService(dto);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void deleteServiceTest() {
		doReturn(null).when(servicesJpaRepository).findByServiceId(3L);

		try {
			service.deleteService(3L);
		} catch (OwnerSiteException exception) {
			assertTrue("Invalid service id".equals(exception.getErrorMessage()));
		}

		Service mockedService = mock(Service.class);
		List<SubscriptionService> subscriptionServices = mock(List.class);

		doReturn(mockedService).when(servicesJpaRepository).findByServiceId(1L);
		doReturn(subscriptionServices).when(mockedService).getSubscriptionServicRegistrations();
		doReturn(false).when(subscriptionServices).isEmpty();

		try {
			service.deleteService(1L);
		} catch (OwnerSiteException ownerSiteException) {
			assertTrue("Service cannot be deleted as it is tagged to one or more subscription(s)"
					.equals(ownerSiteException.getErrorMessage()));
		}

		doReturn(true).when(subscriptionServices).isEmpty();

		try {
			service.deleteService(1L);
		} catch (OwnerSiteException ownerSiteException) {
			assertFalse(true);
		}

	}

	@Test
	public void updateServiceTest() {
		doReturn(null).when(servicesJpaRepository).findByServiceId(3L);

		try {
			ServiceDTO serviceDTO = new ServiceDTO();
			serviceDTO.setServiceId(3L);
			serviceDTO.setServicedec("servicedec");
			serviceDTO.setServicename("servicename");

			service.updateService(serviceDTO);
		} catch (OwnerSiteException exception) {
			assertTrue("Invalid service id".equals(exception.getErrorMessage()));
		}

		Service mockedService = mock(Service.class);

		doReturn(mockedService).when(servicesJpaRepository).findByServiceId(1L);

		try {
			ServiceDTO serviceDTO = new ServiceDTO();
			serviceDTO.setServiceId(1L);
			serviceDTO.setServicedec("servicedec");
			serviceDTO.setServicename("servicename");

			service.updateService(serviceDTO);
		} catch (OwnerSiteException exception) {
			assertFalse(true);
		}

	}

	@Test
	public void findServcieByIdTest() {

		Service expectedResponse = new Service();
		expectedResponse.setId(1L);
		expectedResponse.setServicename("S1");
		expectedResponse.setServicedec("Service 1");

		doReturn(expectedResponse).when(servicesJpaRepository).findByServiceId(1L);

		ServiceDTO actualResponse = service.findServiceById(1L);

		assertEquals(expectedResponse.getId(), actualResponse.getServiceId());
		assertEquals(expectedResponse.getServicename(), actualResponse.getServicename());
		assertEquals(expectedResponse.getServicedec(), actualResponse.getServicedec());

	}

	@Test
	public void findServicesByIdsTest() {
		List<Long> serviceIds = Arrays.asList(1L, 2L);

		List<Service> services = new ArrayList<>();
		Service service = new Service();
		service.setId(1L);
		service.setServicename("S1");
		service.setServicedec("Service 1");
		services.add(service);
		service = new Service();
		service.setId(2L);
		service.setServicename("S2");
		service.setServicedec("Service 2");
		services.add(service);

		doReturn(services).when(servicesJpaRepository).findAllById(serviceIds);

		List<Service> actualResponse = this.service.findServicesByIds(serviceIds);

		assertEquals(services.size(), actualResponse.size());
		assertEquals(services.get(0).getId(), actualResponse.get(0).getId());
		assertEquals(services.get(0).getServicename(), actualResponse.get(0).getServicename());
		assertEquals(services.get(0).getServicedec(), actualResponse.get(0).getServicedec());
		assertEquals(services.get(1).getId(), actualResponse.get(1).getId());
		assertEquals(services.get(1).getServicename(), actualResponse.get(1).getServicename());
		assertEquals(services.get(1).getServicedec(), actualResponse.get(1).getServicedec());

	}

}
