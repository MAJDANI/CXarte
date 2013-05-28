package com.novedia.talentmap.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.novedia.talentmap.model.entity.Authentication;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {
	
	
	RestTemplate restTemplate;
	
	public static final String URL = "http://localhost:8080/talentmap/api/v1";
	List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	MappingJacksonHttpMessageConverter json = new MappingJacksonHttpMessageConverter();
	
	public void setUp(){
		restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);
		converters.add(json);
		
	}
	
	
	@Test
	public void testCheckAuthenticationUserReturnAuthentication(){
		
		
		//GIVEN
		String ressource = URL+"/authentication/{login}/{password}/";
		
		
		//WHEN
//		Mockito.w
		Authentication authentication = restTemplate.postForObject(ressource, null, Authentication.class, "b.tiomo","test");
		
		
		//THEN
		
	}

}
