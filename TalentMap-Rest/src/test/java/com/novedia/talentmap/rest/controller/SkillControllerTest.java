package com.novedia.talentmap.rest.controller;

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

import com.novedia.talentmap.model.dto.CategoryMapDTO;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.services.impl.SkillService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, 
locations = {"classpath:test-mockito-services-context.xml","classpath:test-api-rest-context.xml"})
public class SkillControllerTest {
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Autowired
	SkillService skillservice;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	/**
     * Test add skill
     */
	@Test
	public void addSkillTest() throws Exception {
		//GIVEN
		Integer expectedResult = 1;
		double averageScore = skillservice.computeToolAverage(4,1,1);
		Skill skill = Skill.builder().colleagueId(1).tool_id(1)
				.score(4).use_frequency(1)
				.no_using_time(1).averageScore((int) averageScore)
				.build();
		
		//WHEN 
		Mockito.when(skillservice.addSkill(skill)).thenReturn(expectedResult);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.post("/skill/{colleagueId}/{toolId}/{score}/{use_frequency}/{no_using_time}/",1,1,4,1,1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	/**
     * Test update skill
     */
	@Test
	public void saveSkillTest() throws Exception{
		//GIVEN
		Integer expectedResult = 1;
		double averageScore = skillservice.computeToolAverage(4,1,1);
		Skill skill = Skill.builder().colleagueId(1).tool_id(1)
				.score(4).use_frequency(1)
				.no_using_time(1).averageScore((int) averageScore)
				.build();
		
		//WHEN 
		Mockito.when(skillservice.saveSkill(skill)).thenReturn(expectedResult);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.put("/skill/{colleagueId}/{toolId}/{score}/{use_frequency}/{no_using_time}/",1,1,5,1,1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	/**
     * Test get skills of a colleague
     */
	@Test
	public void testGetSkillCollaborator() throws Exception {
		
		//GIVEN
		Integer colleagueId = 1;
		CategoryMapDTO categoryMapDto = null;
		
		
		//WHEN
		Mockito.when(skillservice.getAllCollaboratorSkill(Mockito.anyInt())).thenReturn(categoryMapDto);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/skills/{colleagueId}/",colleagueId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}

}
