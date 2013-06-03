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

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.services.IAdminService;


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
	public void addCategoryTest() throws Exception {
		Integer resultExpected = 1;
		Category cat = Category.builder().name("test").build();
		Mockito.when(adminService.addCategory(cat)).thenReturn(resultExpected);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/category/{category_name}/", "test")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(resultExpected))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"));
	}
	
	@Test
	public void saveCategoryTest() throws Exception {
		Integer idExpected =1;
		Category category = Category.builder().id(idExpected).name("test").build();
		
		
	}

}
