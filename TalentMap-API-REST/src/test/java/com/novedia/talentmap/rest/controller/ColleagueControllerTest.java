package com.novedia.talentmap.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IRegistrationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, 
locations = {"classpath:test-mockito-services-context.xml","classpath:test-api-rest-context.xml"})
public class ColleagueControllerTest {
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Autowired
	IColleagueService colleagueService;
	
	@Autowired
	IRegistrationService registrationService;
	
	@Autowired
	IAdminService adminService;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void getBusinessEngineersTest() throws Exception{
		//GIVEN
		List<Colleague> colleaguesList = new ArrayList<Colleague>();
		Colleague colleague = Colleague.builder().id(1).firstName("test").build();
		colleaguesList.add(colleague);
		Mockito.when(colleagueService.getAllBusinessEngineers()).thenReturn(colleaguesList);
		
		//WHEN and THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/businessengineers/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void getManagersTest() throws Exception{
		//GIVEN
		List<Colleague> colleaguesList = new ArrayList<Colleague>();
		Colleague colleague = Colleague.builder().id(1).firstName("test").build();
		colleaguesList.add(colleague);
		Mockito.when(colleagueService.getAllConsultantManager()).thenReturn(colleaguesList);
		
		//WHEN and THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/managers/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void getProfilesTest() throws Exception{
		//GIVEN
		List<Profile> profiles = new ArrayList<Profile>();
		Profile profile = Profile.builder().id(1).type("test").build();
		Profile profile2 = Profile.builder().id(2).type("test2").build();
		profiles.add(profile);
		profiles.add(profile2);
		Mockito.when(registrationService.getAllProfile()).thenReturn(profiles);
		
		//WHEN and THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/profiles/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void getColleagueTest() throws Exception{
		//GIVEN
		Colleague expectedColleague = Colleague.builder().id(1).firstName("test").build();
		Mockito.when(colleagueService.getColleague(1)).thenReturn(expectedColleague);
		
		//WHEN and THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/colleague/{colleagueId}/",1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("test"));
		
		
	}
	
	@Test
	public void deleteColleagueTest() throws Exception{
		//GIVEN
		Map<String, Object> map = new HashMap<String, Object>();
		Colleague colleague = Colleague.builder().id(1).build();
		Set<Colleague> colleagues = new HashSet<Colleague>();
		colleagues.add(colleague);
		Mockito.when(adminService.deleteColleague(colleagues)).thenReturn(map);
				
		//WHEN and THEN
		mockMvc.perform(MockMvcRequestBuilders.delete("/colleague/{colleagueId}/",1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}

}
