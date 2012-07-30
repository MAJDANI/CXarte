package com.novedia.talentmap.web.ui.profile;

import com.vaadin.ui.VerticalLayout;

public class ProfileView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * TalentMap Services
	 */
	private ProfileLayout profileLayout;

	/**
	 * Build the class ProfileView.java 
	 * @param profileLayout
	 */
	public ProfileView(ProfileLayout profileLayout) {
		super();
		this.profileLayout = profileLayout;
		
		mainBuild();
	}

	/**
	 * The main builder
	 * @class ProfileView.java
	 */
	public void mainBuild(){
		
		addComponent(this.profileLayout);
	}
	/**
	 * Get the profileLayout value
	 * @return the profileLayout
	 */
	public ProfileLayout getProfileLayout() {
		return profileLayout;
	}

	/**
	 * Set the profileLayout value
	 * @param profileLayout the profileLayout to set
	 */
	public void setProfileLayout(ProfileLayout profileLayout) {
		this.profileLayout = profileLayout;
	}
	
	
}
