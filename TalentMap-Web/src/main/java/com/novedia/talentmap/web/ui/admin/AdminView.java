package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.web.util.IAdminView;
import com.vaadin.ui.VerticalLayout;

public class AdminView extends VerticalLayout {
	
	private AdminContentLayout adminContentLayout;
	private LoginLayout loginLayout;
	
	/**
	 * Flag
	 */
	public boolean isLogged;

	/**
	 * 
	 * Build the class AdminView.java 
	 * @param adminContentLayout
	 * @param loginLayout
	 */
	public AdminView(AdminContentLayout adminContentLayout, LoginLayout loginLayout) {
		super();
		this.adminContentLayout = adminContentLayout;
		this.loginLayout = loginLayout;
		this.isLogged = false; 
				
		buildObservators();
		
		mainBuild();
	}
	
	/**
	 * The main builder
	 * @class AdminView.java
	 */
	public void mainBuild(){
	
		removeAllComponents();
		
		if(isLogged){
			
			addComponent(this.adminContentLayout);
		}else{
			
			addComponent(this.loginLayout);
		}
	}
	
	/**
	 * The builder for all observator
	 * @class AdminView.java
	 */
	public void buildObservators(){
		
		this.loginLayout.addObservateur(new IAdminView() {
			
			@Override
			public void updateAdminContent(boolean isLogged) {
				
				AdminView.this.isLogged = isLogged;
				mainBuild();
			}
		}, IAdminView.class);
		
		this.adminContentLayout.addObservateur(new IAdminView() {
			
			@Override
			public void updateAdminContent(boolean isLogged) {
				
				AdminView.this.isLogged = isLogged;
				AdminView.this.loginLayout.clearField();
				mainBuild();
			}
		}, IAdminView.class);
	}
	

	/**
	 * Set the loginLayout value
	 * @param loginLayout the loginLayout to set
	 */
	public void setLoginLayout(LoginLayout loginLayout) {
		this.loginLayout = loginLayout;
	}
	
	/**
	 * Set the adminContentLayout value
	 * @param adminContentLayout the adminContentLayout to set
	 */
	public void setAdminContentLayout(AdminContentLayout adminContentLayout) {
		this.adminContentLayout = adminContentLayout;
	}	
	
	/**
	 * Get the isLogged value
	 * @return the isLogged
	 */
	public boolean isLogged() {
		return isLogged;
	}

	/**
	 * Set the isLogged value
	 * @param isLogged the isLogged to set
	 */
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
}
