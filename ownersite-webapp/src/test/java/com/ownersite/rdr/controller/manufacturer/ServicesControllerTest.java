package com.ownersite.rdr.controller.manufacturer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ownersite.rdr.dto.ServiceDTO;
import com.ownersite.rdr.exception.OwnerSiteException;
import com.ownersite.rdr.service.manufacturer.ServicesService;

@RunWith(MockitoJUnitRunner.class)
public class ServicesControllerTest {

	@InjectMocks
	private ServicesController controller;
	@Mock
	private ServicesService service;

	private MockMvc mockMvc;
	private static String url = "/owner-site/manufacturer/";

	@Before
	public void initialize() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void getAllServicesTest() throws Exception {

		List<ServiceDTO> serviceDTOs = new ArrayList<>();
		ServiceDTO serviceDTO = new ServiceDTO();
		serviceDTO.setServiceId(1L);
		serviceDTO.setServicename("S1");
		serviceDTO.setServicedec("Service 1");
		serviceDTOs.add(serviceDTO);
		serviceDTO = new ServiceDTO();
		serviceDTO.setServiceId(2L);
		serviceDTO.setServicename("S2");
		serviceDTO.setServicedec("Service 2");
		serviceDTOs.add(serviceDTO);

		doReturn(serviceDTOs).when(service).getAllServices();

		mockMvc.perform(MockMvcRequestBuilders.get(url.concat("getAllServices")))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		doThrow(RuntimeException.class).when(service).getAllServices();

		mockMvc.perform(MockMvcRequestBuilders.get(url.concat("getAllServices"))).andExpect(status().isAccepted())
				.andReturn();

	}

	@Test
	public void addServiceTest() throws Exception {

		ServiceDTO serviceDTO = new ServiceDTO();
		serviceDTO.setServiceId(1L);
		serviceDTO.setServicename("S1");
		serviceDTO.setServicedec("Service 1");

		doNothing().when(service).addService(any(ServiceDTO.class));

		mockMvc.perform(MockMvcRequestBuilders.post(url.concat("addNewService")).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(serviceDTO)))
				.andExpect(status().isOk()).andReturn();

		doThrow(RuntimeException.class).when(service).addService(any(ServiceDTO.class));

		mockMvc.perform(MockMvcRequestBuilders.post(url.concat("addNewService")).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(serviceDTO)))
				.andExpect(status().isAccepted()).andReturn();

	}

	@Test
	public void deleteServiceTest() throws Exception {

		ServiceDTO serviceDTO = new ServiceDTO();
		serviceDTO.setServiceId(1L);
		serviceDTO.setServicename("S1");
		serviceDTO.setServicedec("Service 1");

		doNothing().when(service).deleteService(1L);

		mockMvc.perform(
				MockMvcRequestBuilders.delete(url.concat("deleteService")).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(serviceDTO)))
				.andExpect(status().isOk()).andReturn();

		doThrow(OwnerSiteException.class).when(service).deleteService(1L);

		mockMvc.perform(
				MockMvcRequestBuilders.delete(url.concat("deleteService")).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(serviceDTO)))
				.andExpect(status().isAccepted()).andReturn();

	}

	@Test
	public void updateServiceTest() throws Exception {

		ServiceDTO serviceDTO = new ServiceDTO();
		serviceDTO.setServiceId(1L);
		serviceDTO.setServicename("S1");
		serviceDTO.setServicedec("Service 1");

		doNothing().when(service).updateService(any(ServiceDTO.class));

		mockMvc.perform(MockMvcRequestBuilders.put(url.concat("updateService")).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(serviceDTO)))
				.andExpect(status().isOk()).andReturn();

		doThrow(OwnerSiteException.class).when(service).updateService(any(ServiceDTO.class));

		mockMvc.perform(MockMvcRequestBuilders.put(url.concat("updateService")).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(serviceDTO)))
				.andExpect(status().isAccepted()).andReturn();

	}

}
