package com.novedia.talentmap.rest.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.request.MockMvcRequestBuilders;
import org.springframework.test.web.server.result.MockMvcResultHandlers;
import org.springframework.test.web.server.result.MockMvcResultMatchers;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.services.impl.AuthenticationService;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, 
locations = {"classpath:test-mockito-services-context.xml","classpath:test-api-rest-context.xml"})
public class AuthenticationControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	
	@Before
	public void setUp(){
		this.mockMvc = MockMvcBuilders.webApplicationContextSetup(this.wac).build();
	}
	
	
	
	@Test
	public void testCheckAuthenticationUserReturnAuthentication() throws Exception{
		
		Authentication authentication = new Authentication();
		CredentialToken token = new CredentialToken(); 
		Md5PasswordEncoder md5encoder = new Md5PasswordEncoder();
		String encodePassword = md5encoder.encodePassword("test", null);
		token.setLogin("b.tiomo");
		token.setPassword(encodePassword);
		
		authentication.setToken(token);
		authentication.setColleagueId(59);
		//authentication.setAuthorization(null);
		
		
//		authenticationService = mock(AuthenticationService.class);
		
		
		Mockito.when(authenticationService.checkUser(token)).thenReturn(authentication);
		System.out.println(authentication.getToken());
		
		mockMvc.perform(MockMvcRequestBuilders.post("/authentication/{login}/{password}/", "b.tiomo","test")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				/*.andExpect(MockMvcResultMatchers.jsonPath("$.colleagueId").value(59))*/;
		
	}

}
