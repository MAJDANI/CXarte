package com.novedia.talentmap.web.ui.admin;

import com.vaadin.ui.VerticalLayout;

public class AdminView extends VerticalLayout {
	
	private AdminContentLayout adminContentLayout;

	/**
	 * 
	 * Build the class AdminView.java 
	 * @param adminContentLayout
	 * @param loginLayout
	 */
	public AdminView(AdminContentLayout adminContentLayout) {
		super();
		this.adminContentLayout = adminContentLayout;
		mainBuild();
	}
	
	/**
	 * The main builder
	 * @class AdminView.java
	 */
	public void mainBuild(){
	
		removeAllComponents();
		addComponent(this.adminContentLayout);

	}
	

	
	/**
	 * Set the adminContentLayout value
	 * @param adminContentLayout the adminContentLayout to set
	 */
	public void setAdminContentLayout(AdminContentLayout adminContentLayout) {
		this.adminContentLayout = adminContentLayout;
	}	


}
