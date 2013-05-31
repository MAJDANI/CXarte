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
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.request.MockMvcRequestBuilders;
import org.springframework.test.web.server.result.MockMvcResultHandlers;
import org.springframework.test.web.server.result.MockMvcResultMatchers;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.services.IAdminService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, 
locations = {"classpath:test-mockito-services-context.xml","classpath:test-api-rest-context.xml"})
public class ConceptControllerTest {
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Autowired
	private IAdminService adminService;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webApplicationContextSetup(this.wac).build();
	}
	
	@Test
	public void getAllTest() throws Exception {
		
		//GIVEN
		Category category = Category.builder().id(1).build();
		Concept concept = Concept.builder().id(1).category(category).name("test").build();
		Concept concept2 = Concept.builder().id(2).category(category).name("test2").build();
		List<Concept> concepts = new ArrayList<Concept>();
		concepts.add(concept);
		concepts.add(concept2);
		Mockito.when(adminService.getAllConceptByCategory(category)).thenReturn(concepts);
		
		//WHEN and THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/concepts/{categoryId}/",1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				/*.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"))*/;
	}
	
	@Test
	public void addConceptTest() throws Exception {
		//GIVEN
		Integer expectedResult = 1;
		Category category = Category.builder().id(1).build();
		Concept concept = Concept.builder().category(category).name("test").build();
		Mockito.when(adminService.addConcept(concept)).thenReturn(expectedResult);
		
		//WHEN and THEN
		mockMvc.perform(MockMvcRequestBuilders.post("/concept/{categoryId}/{concept_name}/",1,"test")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedResult))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"));
	}

}
