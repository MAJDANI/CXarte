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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.ISkillService;

/**
 * @author j.maquin
 * @author b.tiomofofou
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, 
locations = {"classpath:test-mockito-services-context.xml","classpath:test-api-rest-context.xml"})
public class SearchControllerTest {
	
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	IColleagueService colleagueService;
	
	@Autowired
	ISkillService skillService;
	
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	
	@Test
	public void testGetAllColleaguesByClient() throws Exception {
		
		//GIVEN
		Integer clientId = 1;
		List <Colleague> colleagueList = new ArrayList<Colleague>();
		Colleague colleague = Colleague.builder().id(1).firstName("test").build();
		colleagueList.add(colleague);
		
		//WHEN
		Mockito.when(colleagueService.getAllColleaguesByClient(Mockito.any(Client.class))).thenReturn(colleagueList);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/colleagues/byclient/{clientId}/",clientId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void testGetAllCmColleaguesByClient() throws Exception {
		
		//GIVEN
		Integer clientId = 1;
		Integer managerId = 1;
		List <Colleague> colleagueList = new ArrayList<Colleague>();
		Colleague colleague = Colleague.builder().id(1).firstName("test").build();
		colleagueList.add(colleague);
		
		//WHEN
		Mockito.when(colleagueService.getCmColleaguesByClient(Mockito.anyInt(),Mockito.anyInt())).thenReturn(colleagueList);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/colleagues/byclient/{clientId}/{managerId}/",clientId,managerId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void testGetAllColleaguesByName() throws Exception {
		
		//GIVEN
		String colleagueName = "test";
		List <Colleague> colleagueList = new ArrayList<Colleague>();
		Colleague colleague = Colleague.builder().id(1).firstName("test").build();
		colleagueList.add(colleague);
		
		//WHEN
		Mockito.when(colleagueService.getAllColleagues()).thenReturn(colleagueList);
		Mockito.when(colleagueService.getAllColleaguesByName(Mockito.anyString())).thenReturn(colleagueList);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/colleagues/byname/{name}/",colleagueName)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetAllCmColleaguesByName() throws Exception {
		
		//GIVEN
		String colleagueName = "test";
		Integer managerId = 1;
		List <Colleague> colleagueList = new ArrayList<Colleague>();
		Colleague colleague = Colleague.builder().id(1).firstName("test").build();
		colleagueList.add(colleague);
		
		//WHEN
		Mockito.when(colleagueService.getCmColleaguesByName(Mockito.anyString(),Mockito.anyInt())).thenReturn(colleagueList);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/colleagues/byname/{name}/{managerId}/",colleagueName,managerId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetAllColleaguesByTool() throws Exception {
		
		//GIVEN
		Integer toolId = 1;
		List<Colleague> colleagueList = new ArrayList<Colleague>();
		Colleague colleague = Colleague.builder().id(1).firstName("test").build();
		colleagueList.add(colleague);
		List<Integer> colleagueIds = new ArrayList<Integer>();
		colleagueIds.add(1);
		
		//WHEN
		Mockito.when(skillService.getAllColleagueIdByToolId(Mockito.anyInt())).thenReturn(colleagueIds);
		Mockito.when(colleagueService.getAllColleagueByColleagueIdList(Mockito.anyListOf(Integer.class))).thenReturn(colleagueList);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/colleagues/bytool/{toolId}/",toolId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetAllCmColleaguesByTool() throws Exception {
		//GIVEN
		Integer toolId = 1;
		Integer managerId = 1;
		List<Colleague> colleagueList = new ArrayList<Colleague>();
		Colleague colleague = Colleague.builder().id(1).firstName("test").build();
		colleagueList.add(colleague);
		List<Integer> colleagueIds = new ArrayList<Integer>();
		colleagueIds.add(1);
		
		//WHEN
		Mockito.when(skillService.getCmColleagueIdByToolId(Mockito.anyInt(),Mockito.anyInt())).thenReturn(colleagueIds);
		Mockito.when(colleagueService.getAllColleagueByColleagueIdList(Mockito.anyListOf(Integer.class))).thenReturn(colleagueList);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/colleagues/byconcept/{conceptId}/",toolId,managerId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void testCetAllColleaguesByConcept() throws Exception {
		//GIVEN
		Integer conceptId = 1;
		List<Colleague> colleagueList = new ArrayList<Colleague>();
		Colleague colleague = Colleague.builder().id(1).firstName("test").build();
		colleagueList.add(colleague);
		List<Integer> colleagueIds = new ArrayList<Integer>();
		colleagueIds.add(1);
		
		//WHEN
		Mockito.when(skillService.getAllColleagueIdByConceptId(Mockito.anyInt())).thenReturn(colleagueIds);
		Mockito.when(colleagueService.getAllColleagueByColleagueIdList(Mockito.anyListOf(Integer.class))).thenReturn(colleagueList);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/colleagues/bytool/{toolId}/",conceptId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	

	@Test
	public void testGetAllCmColleaguesByConcept() throws Exception {
		//GIVEN
		Integer conceptId = 1;
		Integer managerId = 1;
		List<Colleague> colleagueList = new ArrayList<Colleague>();
		Colleague colleague = Colleague.builder().id(1).firstName("test").build();
		colleagueList.add(colleague);
		List<Integer> colleagueIds = new ArrayList<Integer>();
		colleagueIds.add(1);
		
		//WHEN
		Mockito.when(skillService.getAllCmColleagueIdByConceptId(Mockito.anyInt(),Mockito.anyInt())).thenReturn(colleagueIds);
		Mockito.when(colleagueService.getAllColleagueByColleagueIdList(Mockito.anyListOf(Integer.class))).thenReturn(colleagueList);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/colleagues/byconcept/{conceptId}/{managerId}/",conceptId,managerId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
