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

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.utils.Constants;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, 
locations = {"classpath:test-mockito-services-context.xml","classpath:test-api-rest-context.xml"})
public class CategoryControllerTest {
	
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Autowired
	private IAdminService adminService;
	
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void getAllTest() throws Exception {
		//GIVEN
		Category category = Category.builder().id(1).name("Java/JEE").build();
		List<Category> categoriesList = new ArrayList<Category>();
		categoriesList.add(category);
		
		//WHEN 
		Mockito.when(adminService.getAllCategories()).thenReturn(categoriesList);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.get("/categories/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void addCategoryTest() throws Exception {
		//GIVEN
		Integer resultExpected = 1;
		Category cat = Category.builder().name("test").build();
		
		//WHEN
		Mockito.when(adminService.addCategory(cat)).thenReturn(resultExpected);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.post("/category/{category_name}/", "test")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void saveCategoryTest() throws Exception {
		//GIVEN
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.MSG_ERROR, Constants.SUCCESSFUL_UPDATE);
		
		//WHEN
		Mockito.when(adminService.updateASkill(Mockito.any(Category.class), Mockito.any(Concept.class), Mockito.any(Tool.class))).thenReturn(map);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.put("/category/{categoryId}/{category_name}/",1,"test2")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void deleteCategoryTest() throws Exception{
		//GIVEN
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.MSG_ERROR, Constants.SUCCESSFUL_DELETE);
		
		//WHEN
		Mockito.when(adminService.deleteCategory(1)).thenReturn(map);
		
		//THEN
		mockMvc.perform(MockMvcRequestBuilders.delete("/category/{categoryId}/",1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
