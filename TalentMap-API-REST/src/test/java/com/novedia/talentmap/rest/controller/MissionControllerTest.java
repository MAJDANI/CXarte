package com.novedia.talentmap.rest.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.novedia.talentmap.model.dto.Response;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.IColleagueService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, 
locations = {"classpath:test-mockito-services-context.xml","classpath:test-api-rest-context.xml"})
public class MissionControllerTest {
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Autowired
	IColleagueService colleagueService;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void getAllMissionByColleagueTest() throws Exception {
		//GIVEN
		List<Mission> missions = new ArrayList<Mission>();
		Mission mission = Mission.builder().id(1).title("mission1").build();
		Mission mission2 = Mission.builder().id(2).title("mission2").build();
		missions.add(mission);
		missions.add(mission2);
		Mockito.when(colleagueService.getAllMissions(1)).thenReturn(missions);
		
		//WHEN and THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/missions/{colleagueId}/",1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deleteMissionTest() throws Exception{
		//GIVEN
		Integer expectedResult = 1;
		Response response = new Response();
		response.setStatus("nok");
		Mission missionToDelete = Mission.builder().id(1).build();
		Mockito.when(colleagueService.deleteMission(missionToDelete)).thenReturn(expectedResult);
		
		//WHEN and THEN
		mockMvc.perform(MockMvcRequestBuilders.delete("/mission/{missionId}/",1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("ok"));
	}

}
