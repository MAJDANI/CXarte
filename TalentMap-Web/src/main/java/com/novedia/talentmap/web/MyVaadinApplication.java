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

import com.novedia.talentmap.services.ITalentMapService;
import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Configurable
public class MyVaadinApplication extends Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyVaadinApplication.class);

	/**
	 * The vaadin window
	 */
	private Window window;
	
	/**
	 * Tthe vaadin button
	 */
	private Button button;
	
	/**
	 * The talent map business service
	 */
	private ITalentMapService tmService;


	@Override
	public void init() {

		setMainWindow(window);
		
		button.setCaption("Show TalentMap Skills");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				window.addComponent(new Label(tmService.displaySkills()));
			}
		});

		window.addComponent(button);
	}


	// Setters
	
	/**
	 * Set the main window
	 * 
	 * @param window
	 */
	public void setWindow(Window window) {
		this.window = window;
	}
	
	
	/**
	 * Set the vaadin button component
	 */
	public void setButton(Button button){
		this.button = button;
	}
	
	/**
	 * Set the service bean tmService
	 * @param tmService
	 */
	public void setTmService(ITalentMapService tmService) {
		this.tmService = tmService;
	}


}
