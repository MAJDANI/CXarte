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

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.services.impl.AuthenticationService;
import com.novedia.talentmap.services.impl.ChangePasswordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, 
locations = {"classpath:test-mockito-services-context.xml","classpath:test-api-rest-context.xml"})
public class AuthenticationControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private ChangePasswordService changePasswordService;
	
	Authentication authentication;
	CredentialToken token;
	
	@Before
	public void setUp(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		authentication = new Authentication();
		token = new CredentialToken(); 
		token.setLogin("b.tiomo");
		token.setPassword("test");
		authentication.setToken(token);
		authentication.setColleagueId(59);
		
		Mockito.when(authenticationService.checkUser(Mockito.any(CredentialToken.class))).thenReturn(authentication);
		
	}
	
	/**
     * Test check a user's authentification
     */
	@Test
	public void testCheckAuthenticationUserReturnAuthentication() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.post("/authentication/{login}/{password}/", "b.tiomo","test")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	/**
     * Test update user's password
     */
	@Test
	public void testChangePassword() throws Exception {
		
		Mockito.when(changePasswordService.savePassword(Mockito.any(Authentication.class))).thenReturn(1);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/authentication/{login}/{old_password}/{new_password}/", "b.tiomo","test","test1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}

}
