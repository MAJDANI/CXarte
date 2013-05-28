package com.novedia.talentmap.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.novedia.talentmap.model.entity.Authentication;

//@RequestMapping(value = "/test")
@Controller
public class Test {

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value = "/test/{login}/{password}/",method = RequestMethod.GET )
	@ResponseBody
	public Authentication get(@PathVariable String login, @PathVariable String password){
		System.out.println("tessssssss");
		Authentication authentication = null;
		authentication = restTemplate.postForObject("http://localhost:8080/talentmap/api/v1/authentication/{login}/{password}/", 
				null,Authentication.class, login,password);
		 System.out.println("oooooooooooo");
		//System.out.println(authentication.getToken().getLogin());
		return authentication;
	}
	
	
}
