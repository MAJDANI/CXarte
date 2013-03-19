/*
 * Copyright 2009 IT Mill Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.novedia.talentmap.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.services.impl.AuthenticationService;
import com.novedia.talentmap.services.impl.BusinessEngineerService;
import com.novedia.talentmap.services.impl.RegistrationService;
import com.novedia.talentmap.web.ui.login.AuthenticatedScreen;
import com.novedia.talentmap.web.ui.login.RegistrationScreen;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.LabelConstants;
import com.novedia.talentmap.web.util.exceptions.TalentMapSecurityException;
import com.vaadin.Application;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.LoginForm.LoginListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Configurable

public class MyVaadinApplication extends Application implements LoginListener, ClickListener {


	/**
	 * The logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MyVaadinApplication.class);

	/**
	 * Vaadin components
	 */
	private Window window;


	/**
	 * The authentication service
	 */
	private AuthenticationService authenticationService;

	/**
	 * The registration service
	 */
	private RegistrationService registrationService;

	/**
	 * The business engineer service
	 */
	private BusinessEngineerService businessEngineerService;

	
	// me
	/**
	 * the authentication
	 */
	private Authentication authentication;
	
	/**
	 * the authenticatedScreen
	 */
	private AuthenticatedScreen authenticatedScreen;
	
	/**
	 * the loginView
	 */
	private VerticalLayout loginView;

	private Button signIn;
	private LoginForm loginForm;
	private Panel loginPanel;
	
	private String LABEL_BUTTON_SIGN_IN = "Sign in";
	
	/**
	 * The init
	 */
	@Override
	public void init() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Talent Map Front initialization");
		}
		this.setTheme("talentmap");
		this.setMainWindow(window);
		
		window.setContent(buildLoginView());
		
	}

	/**
	 * Build the login form
	 * @return a vertivalLayout with login form
	 */
	public VerticalLayout buildLoginView(){
		if(loginView != null){
			loginView.removeAllComponents();
		}
		loginView = new VerticalLayout();
		loginView.setSizeFull();
		loginView.setMargin(true);
		loginView.setStyleName(Reindeer.LAYOUT_WHITE);
		//Panel for login
		this.loginPanel = new Panel("Login");
		this.loginPanel.setWidth("400px");
		
		//The form
		loginForm = new LoginForm();
		loginForm.setUsernameCaption(LabelConstants.USER_LOGIN);
		loginForm.setPasswordCaption(LabelConstants.USER_PASSWORD);
		loginForm.setLoginButtonCaption(LabelConstants.LOGIN_BUTTON);
		loginForm.setHeight("150px");
		loginForm.addListener(this);
		loginPanel.addComponent(loginForm);
		
		this.signIn = new Button(LABEL_BUTTON_SIGN_IN);
		this.signIn.addListener(this);
		this.loginPanel.addComponent(signIn);
		loginView.addComponent(loginPanel);
		loginView.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		HorizontalLayout footer = new HorizontalLayout();
		footer.setHeight("50px");
		loginView.addComponent(footer);
		
		return loginView;
	}
	
	
	@Override
	public void buttonClick(ClickEvent event) {
		String button = event.getButton().getCaption();		
		if (button.equalsIgnoreCase("Sign In")){
			getMainWindow().setContent(new RegistrationScreen(this));
		}		
	}

	@Override
	public void onLogin(LoginEvent event) {
		//Credentials infos
		String username = event.getLoginParameter("username");
		String password = event.getLoginParameter("password");
		try {
			authentication = checkUserAuthentication(username, password);
			if (authentication != null) {
				authenticatedScreen.setAuthentication(authentication);
				authenticatedScreen.setApplication(this);
				getMainWindow().setContent(authenticatedScreen.selectedViewAccordingToUserRoles());
			}
		} catch (TalentMapSecurityException tmpex) {
			getMainWindow().showNotification("Bad user name/password");
		}
	}
	
	
	/**
	 * this method check the authentication user
	 * @param login login's user
	 * @param password password's user
	 */
	public Authentication checkUserAuthentication(final String login,final String password)
			throws TalentMapSecurityException {
		try {
			CredentialToken credential = new CredentialToken();
			credential.setLogin(login);
			credential.setPassword(CUtils.encodePassword(password));
			authentication = authenticationService.checkUser(credential);
			if (authentication == null
					|| (authentication != null && authentication.getAuthorization() == null)) {
				throw new TalentMapSecurityException("User unknown");
			}
		} catch (DataAccessException ex) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("Technical Exception : ", ex.getMessage());
			}
		}

		return authentication;
	}
	


	/**
	 * Register method
	 * 
	 * @param registration
	 */
	public Authentication register(Registration registration)
			throws TalentMapSecurityException {

		Authentication authenticate = null;

		registration.setLogin(getLogin(registration));
		try {

			// We check if the user is already existing in Database
			// (Collaborator table)
			if (registrationService.check(registration) == null) {
				registrationService.addColleagueFromRegistration(registration);
			} else {
				throw new TalentMapSecurityException("Email already used");
			}

			// Password encoding
			String encodedPassword = CUtils.encodePassword(registration.getPassword());
			registration.setPassword(encodedPassword);
			registrationService.addUserFromRegistration(registration);

			CredentialToken credential = new CredentialToken();
			credential.setLogin(registration.getLogin());
			credential.setPassword(registration.getPassword());
			authenticate = authenticationService.checkUser(credential);
		} catch (DataAccessException ex) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("Technical Exception : ", ex.getMessage());
			}
		}

		return authenticate;
	}

	/**
	 * 
	 * @param registration
	 * @return
	 */
	private String getLogin(Registration registration) {
		String firstName = registration.getFirstName();
		String lastName = registration.getLastName();
		String login = firstName.substring(0, 1) + "." + lastName;
		return login.toLowerCase();
	}

	
	
	//GETTER//SETTER
	
	/**
	 * Set the main window
	 * 
	 * @param window
	 */
	public void setWindow(Window window) {
		this.window = window;
	}
	
	
	/**
	 * Get the main window
	 * @return
	 */
	public Window getWindow() {
		return window;
	}

	/**
	 * Get the authenticationService
	 * @return
	 */
	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}
	
	/**
	 * set the authenticationService
	 * @param authenticationService the authenticationService to set
	 */
	public void setAuthenticationService(
			AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	/**
	 * Get the registrationService
	 * @return
	 */
	public RegistrationService getRegistrationService() {
		return registrationService;
	}

	
	/**
	 * Set the registrationService
	 * @param registrationService
	 */
	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	
	/**
	 * Get the businessEngineerService
	 * @return
	 */
	public BusinessEngineerService getBusinessEngineerService() {
		return businessEngineerService;
	}

	
	/**
	 * Set the businessEngineerService
	 * @param businessEngineerService
	 */
	public void setBusinessEngineerService(
			BusinessEngineerService businessEngineerService) {
		this.businessEngineerService = businessEngineerService;
	}

	/**
	 * Get the authentication
	 * @return
	 */
	public Authentication getAuthentication() {
		return authentication;
	}

	/**
	 * Set the authentication
	 * @param authentication
	 */
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	/**
	 * Get the authenticatedScreen
	 * @return
	 */
	public AuthenticatedScreen getAuthenticatedScreen() {
		return authenticatedScreen;
	}

	/**
	 * Set the authenticatedScreen
	 * @param authenticatedScreen
	 */
	public void setAuthenticatedScreen(AuthenticatedScreen authenticatedScreen) {
		this.authenticatedScreen = authenticatedScreen;
	}


	
	

}
