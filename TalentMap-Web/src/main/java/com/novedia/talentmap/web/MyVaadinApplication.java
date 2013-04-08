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

import com.novedia.talentmap.web.ui.login.LoginScreen;
import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Configurable
public class MyVaadinApplication extends Application  {


	/**
	 * The logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MyVaadinApplication.class);

	/**
	 * Vaadin components
	 */
	private Window window;
	
	/**
	 * The loginScreen
	 */
	private LoginScreen loginScreen;
	
	
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
		
		loginScreen.setMyVaadinApplication(this);
		window.setContent(loginScreen.buildLoginScreenView());
		
	}

	
	
	/**
	 * Set the main window
	 * @param window window to set
	 */
	public void setWindow(Window window) {
		this.window = window;
	}
	
	
	/**
	 * Get the main window
	 * @return window
	 */
	public Window getWindow() {
		return window;
	}
	
	/**
	 * Get the LoginScreen 
	 * @return loginScreen
	 */
	public LoginScreen getLoginScreen() {
		return loginScreen;
	}

	/**
	 * Set the loginScreen
	 * @param loginScreen loginScreen to set
	 */
	public void setLoginScreen(LoginScreen loginScreen) {
		this.loginScreen = loginScreen;
	}


}
