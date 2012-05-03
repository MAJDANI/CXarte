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

import com.novedia.talentmap.web.ui.TabMain;
import com.vaadin.Application;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Configurable
public class MyVaadinApplication extends Application {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyVaadinApplication.class);

	/**
	 * Vaadin components
	 */
	private Window window;
	private HorizontalLayout hLayout;
	
	/**
	 * Vaadin components UI
	 */
	private TabMain tabMain;

	
	@Override
	public void init(){
		
		//setTheme("runo");
		
		setMainWindow(window);
		buildMainLayout();
	}
	
	/**
	 *  All Layout Build
	 */
	
	private void buildMainLayout(){
		hLayout.setSizeFull();
		hLayout.setMargin(true);
		hLayout.setStyle(Reindeer.LAYOUT_WHITE);
		
		hLayout.addComponent(tabMain);
		window.addComponent(hLayout);
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
	 * @param hLayout the hLayout to set
	 */
	public void sethLayout(HorizontalLayout hLayout) {
		this.hLayout = hLayout;
	}
	/**
	 * Set the tabProfileSheet value
	 * @param tabProfileSheet the tabProfileSheet to set
	 */
	public void setTabMain(TabMain tabMain) {
		this.tabMain = tabMain;
	}
	
}
