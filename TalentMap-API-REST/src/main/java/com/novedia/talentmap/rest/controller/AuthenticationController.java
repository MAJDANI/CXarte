package com.novedia.talentmap.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.services.impl.AuthenticationService;
import com.novedia.talentmap.services.impl.ChangePasswordService;

/**
 * 
 * @author b.tiomofofou
 * @author j.maquin
 *
 */

@Controller
@RequestMapping(value = "/authentication")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private ChangePasswordService changePasswordService;
	
	
	
	/**
	 * 
	 * @param login
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/{login}/{password}/" , method = RequestMethod.POST )
	@ResponseBody
	public Authentication checkAuthenticationUser(@PathVariable String login, @PathVariable String password){
		Authentication authentication = null;
		CredentialToken token = new CredentialToken();
		Md5PasswordEncoder md5encoder = new Md5PasswordEncoder();
		String encodePassword = md5encoder.encodePassword(password, null);
		token.setLogin(login);
		token.setPassword(encodePassword);
		authentication = authenticationService.checkUser(token);
		return authentication;
		
	}

	
	
	/**
	 * 
	 * @param login
	 * @param old_password
	 * @param new_password
	 * @return
	 */
	@RequestMapping(value = "/{login}/{old_password}/{new_password}/" , method = RequestMethod.PUT )
	@ResponseBody
	public Authentication changePassword(@PathVariable String login, @PathVariable String old_password,
			@PathVariable String new_password){
		Authentication authentication = null;
		CredentialToken token = new CredentialToken();
		Md5PasswordEncoder md5encoder = new Md5PasswordEncoder();
		String encodePassword = md5encoder.encodePassword(old_password, null);
		token.setLogin(login);
		token.setPassword(encodePassword);
		authentication = authenticationService.checkUser(token);
		if(authentication != null){
			encodePassword = md5encoder.encodePassword(new_password, null);
			token.setPassword(encodePassword);
			authentication.setToken(token);
			changePasswordService.savePassword(authentication);
		}
		return authentication;
	}
	
	
	
	
}
