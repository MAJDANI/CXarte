package com.novedia.talentmap.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.request.MockMvcRequestBuilders;
import org.springframework.test.web.server.result.MockMvcResultHandlers;
import org.springframework.test.web.server.result.MockMvcResultMatchers;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.services.impl.ClientService;


@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, 
locations = {"classpath:test-mockito-services-context.xml","classpath:test-api-rest-context.xml"})
public class ClientControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Autowired
	private ClientService clientService;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webApplicationContextSetup(this.wac).build();
	}
	
	@Test
	public void testGetAll() throws Exception {
		
		//GIVEN
		Client client = Client.builder().id(1).name("PSA").build();
		List<Client> clientsList = new ArrayList<Client>();
		clientsList.add(client);
		Mockito.when(clientService.getAllClients()).thenReturn(clientsList);
		
		//WHEN and THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/clients/")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				/*.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))*/;
		
	}
}
