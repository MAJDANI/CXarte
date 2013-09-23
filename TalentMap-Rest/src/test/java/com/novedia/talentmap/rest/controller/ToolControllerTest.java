package com.novedia.talentmap.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.utils.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, 
locations = {"classpath:test-mockito-services-context.xml","classpath:test-api-rest-context.xml"})
public class ToolControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Autowired
	private IAdminService adminService;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	/**
     * Test get all tools
     */
	@Test
	public void getAllTest() throws Exception {
		//GIVEN
		Concept concept = Concept.builder().id(1).name("ORM").build();
		Concept concept2 = Concept.builder().id(2).name("TEST").build();
		Tool tool = Tool.builder().id(1).concept(concept).name("test").build();
		Tool tool2 = Tool.builder().id(2).concept(concept2).name("test2").build();
		List<Tool> toolsList = new ArrayList<Tool>();
		toolsList.add(tool);
		toolsList.add(tool2);
		
		//WHEN
		Mockito.when(adminService.getAllTools()).thenReturn(toolsList);
		
        //THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/tools/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				/*.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"))*/;
	}
	
	/**
     * Test get all tools on a concept
     */
	@Test
	public void getAllByConceptTest() throws Exception {
		//GIVEN
		Concept concept = Concept.builder().id(1).build();
		Tool tool = Tool.builder().id(1).concept(concept).name("test").build();
		Tool tool2 = Tool.builder().id(2).concept(concept).name("test2").build();
		List<Tool> tools = new ArrayList<Tool>();
		tools.add(tool);
		tools.add(tool2);
		
		//WHEN
		Mockito.when(adminService.getToolsByConcept(concept.getId())).thenReturn(tools);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/tools/{conceptId}/",1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				/*.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"))*/;
	}
	
	/**
     * Test add a tool
     */
	@Test
	public void addToolTest() throws Exception {
		//GIVEN
		Integer expectedResult = 1;
		
		//WHEN
		Mockito.when(adminService.addTool(Mockito.any(Tool.class))).thenReturn(expectedResult);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.post("/tool/{conceptId}/{tool_name}/",1,"test")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	/**
     * Test update a tool
     */
	@Test
	public void saveToolTest() throws Exception{
		//GIVEN
		Integer expectedResult = 1;
		Concept concept = Concept.builder().id(1).build();
		Tool tool = Tool.builder().concept(concept).id(expectedResult).name("test").build();
		
		//WHEN
		Mockito.when(adminService.updateTool(tool)).thenReturn(expectedResult);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.put("/tool/{conceptId}/{toolId}/{tool_name}/",1,1,"test2")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	/**
     * Test delete a tool
     */
	@Test
	public void deleteToolTest() throws Exception{
		//GIVEN
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.MSG_ERROR, Constants.SUCCESSFUL_DELETE);
		
		//WHEN
		Mockito.when(adminService.deleteTool(1)).thenReturn(map);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.delete("/tool/{toolId}/",1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
