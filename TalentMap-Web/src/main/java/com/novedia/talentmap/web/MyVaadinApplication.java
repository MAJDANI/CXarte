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

import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.web.ui.CollaboratorForm;
import com.vaadin.Application;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

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
	private TabSheet homeTab;
	private TabSheet subTab;

	private String TAB_FIRST_NAME = "Profil";
	private String TAB_SECOND_NAME = "Recherche";
	private String SUBTAB_FIRST_NAME = "Fiche Profil";
	
	/**
	 * Vaadin components UI
	 */
	private CollaboratorForm collabForm;
	
	/**
	 * Vaadin buttons
	 */
	//private Button button;
	
	
	/**
	 * The talent map business service
	 */
	//private ICollaboratorService collaboratorService;
	
	@Override
	public void init(){
		
		//Set Theme to "Runo"
		setTheme("runo");
		
		//Set Components
		setMainWindow(window);
		hLayout.setSizeFull();
		hLayout.setMargin(true);
		initHomeTab(collabForm, new HorizontalLayout());
		
		
		//Add Components
		hLayout.addComponent(homeTab);
		window.addComponent(hLayout);
		
		
		
	}
	
	private void initHomeTab(Component ficheProfil, Component search){
		homeTab.setSizeFull();
		
		VerticalLayout vLayout1 = new VerticalLayout();
		vLayout1.addComponent(ficheProfil);
		subTab.addTab(vLayout1, SUBTAB_FIRST_NAME);
		homeTab.addTab(subTab,TAB_FIRST_NAME);
		
		VerticalLayout vLayout2 = new VerticalLayout();
		vLayout2.addComponent(search);
		homeTab.addTab(vLayout2, TAB_SECOND_NAME);
	}
	
	//ProfilService functions
	


	// SETTERS
	
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
	 * Set the homeTab value
	 * @param homeTab the homeTab to set
	 */
	public void setHomeTab(TabSheet homeTab) {
		this.homeTab = homeTab;
	}

	/**
	 * Set the collabForm value
	 * @param collabForm the collabForm to set
	 */
	public void setCollabForm(CollaboratorForm collabForm) {
		this.collabForm = collabForm;
	}
	
	/**
	 * Set the subTab value
	 * @param subTab the subTab to set
	 */
	public void setSubTab(TabSheet subTab) {
		this.subTab = subTab;
	}
	
}
