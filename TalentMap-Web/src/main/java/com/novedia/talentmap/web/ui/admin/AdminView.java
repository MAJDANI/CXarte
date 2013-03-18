package com.novedia.talentmap.web.ui.admin;

import com.vaadin.ui.VerticalLayout;

public class AdminView extends VerticalLayout {
	
	private AdminContentLayout adminContentLayout;

	/**
	 * Deafault constructor
	 */
	public AdminView(){
		super();
	}
	
	
	/**
	 * Build the view's admin
	 * @return
	 */
	public AdminView buildAdminView(){
		removeAllComponents();
		adminContentLayout = adminContentLayout.buildViewAdminContentLayout();
		addComponent(adminContentLayout);
		return this;
	}
	
	
	/**
	 * 
	 * Build the class AdminView.java 
	 * @param adminContentLayout
	 * @param loginLayout
	 */
//	public AdminView(AdminContentLayout adminContentLayout) {
//		super();
//		this.adminContentLayout = adminContentLayout;
//		mainBuild();
//	}
	
	/**
	 * The main builder
	 * @class AdminView.java
	 */
//	public void mainBuild(){
//		removeAllComponents();
//		addComponent(this.adminContentLayout);
//
//	}
	

	
	/**
	 * Set the adminContentLayout value
	 * @param adminContentLayout the adminContentLayout to set
	 */
	public void setAdminContentLayout(AdminContentLayout adminContentLayout) {
		this.adminContentLayout = adminContentLayout;
	}	


}
