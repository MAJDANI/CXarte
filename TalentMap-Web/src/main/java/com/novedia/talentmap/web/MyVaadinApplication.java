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
import com.novedia.talentmap.services.impl.AuthenticationService;
import com.novedia.talentmap.web.ui.TabMain;
import com.novedia.talentmap.web.ui.login.LoginScreen;
import com.novedia.talentmap.web.util.exceptions.TalentMapSecurityException;
import com.vaadin.Application;
import com.vaadin.service.ApplicationContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Configurable
public class MyVaadinApplication extends Application implements
		ApplicationContext.TransactionListener {

	/**
	 * The logger
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MyVaadinApplication.class);

	/**
	 * Vaadin components
	 */
	private Window window;

	/**
	 * Horizontal layout
	 */
	private HorizontalLayout mainLayout;

	/**
	 * Vertical Layout
	 */
	private VerticalLayout mainVerticalLayout;

	/**
	 * Vaadin components UI
	 */
	private TabMain mainTab;

	/**
	 * The athentication service
	 */
	private AuthenticationService authenticationService;

	/**
	 * The button close
	 */
	private Button closeButton;

	// /**
	// * The login screen
	// */
	// private LoginScreen loginScreen;

	/**
	 * The init
	 */
	@Override
	public void init() {

		// Set the main window
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Init application");
		}
		this.setTheme("talentmap");
		this.setMainWindow(window);

		// Add close button
		this.closeButton.setCaption("LogOut");
		this.closeButton.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				logout();
			}
		});

		//See internet: allows to fix bug : show twice window component.
		window.setContent(new LoginScreen(this));
	}

	/**
	 * Build Horizontal layout
	 */
	public HorizontalLayout buildMainLayout() {
		
		//TODO: Not use, because component extends layout
		
		// Set the main window
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("build the main component container");
		}
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		mainLayout.setStyleName(Reindeer.LAYOUT_WHITE);
		mainLayout.addComponent(new LoginScreen(this));

		return mainLayout;
	}

	/**
	 * Build vertical layout
	 * 
	 * @return
	 */
	public VerticalLayout buildMainVerticalLayout() {

		//TODO: Not use, because component extends layout
		
		// Set the main window
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("build the vertical layout");
		}
		mainVerticalLayout.setSizeFull();
		mainVerticalLayout.setMargin(true);
		mainVerticalLayout.setStyleName(Reindeer.LAYOUT_WHITE);
		mainVerticalLayout.addComponent(new LoginScreen(this));

		return mainVerticalLayout;
	}

	/**
	 * Login method
	 * 
	 * @param login
	 * @param password
	 */
	public Authentication login(String login, String password)
			throws TalentMapSecurityException {

		Authentication authenticate = null;
		try {
			CredentialToken credential = new CredentialToken();
			credential.setLogin(login);
			credential.setPassword(password);
			authenticate = authenticationService.checkUser(credential);

			if (authenticate == null
					|| (authenticate != null && authenticate.getAuthorization() == null)) {
				throw new TalentMapSecurityException("User unknown");
			}
		} catch (DataAccessException ex) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("Technical Exception : ", ex.getMessage());
			}
		}

		return authenticate;
	}

	/**
	 * Log out method
	 */
	public void logout() {

		// Fix bug: show twice a window component -> firefox
		this.getMainVerticalLayout().removeAllComponents();
		this.window.removeAllComponents();
		close();

		// Subject currentUser = SecurityUtils.getSubject();
		// if (currentUser.isAuthenticated()) {
		// currentUser.logout();
		// }

		// After closing, redirect user back to login
		//Set null, redirect to login page
		setLogoutURL(null);
	}

	/**
	 * Set the main window
	 * 
	 * @param window
	 */
	public void setWindow(Window window) {
		this.window = window;
	}

	/**
	 * Set the hLayout value
	 * 
	 * @param hLayout
	 *            the hLayout to set
	 */
	public void sethLayout(HorizontalLayout hLayout) {
		this.mainLayout = hLayout;
	}

	/**
	 * Set the tabProfileSheet value
	 * 
	 * @param tabProfileSheet
	 *            the tabProfileSheet to set
	 */
	public void setTabMain(TabMain tabMain) {
		this.mainTab = tabMain;
	}

	@Override
	public void transactionStart(Application application, Object transactionData) {

	}

	@Override
	public void transactionEnd(Application application, Object transactionData) {
	}

	/**
	 * @param authenticationService
	 *            the authenticationService to set
	 */
	public void setAuthenticationService(
			AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	/**
	 * @return the hLayout
	 */
	public HorizontalLayout gethLayout() {
		return mainLayout;
	}

	/**
	 * @return the mainTab
	 */
	public TabMain getMainTab() {
		return mainTab;
	}

	/**
	 * @param mainTab
	 *            the mainTab to set
	 */
	public void setMainTab(TabMain mainTab) {
		this.mainTab = mainTab;
	}

	/**
	 * @param mainLayout
	 *            the mainLayout to set
	 */
	public void setMainLayout(HorizontalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	/**
	 * @param closeButton
	 *            the closeButton to set
	 */
	public void setCloseButton(Button closeButton) {
		this.closeButton = closeButton;
	}

	/**
	 * @return the closeButton
	 */
	public Button getCloseButton() {
		return closeButton;
	}

	/**
	 * @return the mainVerticalLayout
	 */
	public VerticalLayout getMainVerticalLayout() {
		return mainVerticalLayout;
	}

	/**
	 * @param mainVerticalLayout the mainVerticalLayout to set
	 */
	public void setMainVerticalLayout(VerticalLayout mainVerticalLayout) {
		this.mainVerticalLayout = mainVerticalLayout;
	}

}