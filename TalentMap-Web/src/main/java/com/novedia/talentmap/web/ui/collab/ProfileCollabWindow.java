package com.novedia.talentmap.web.ui.collab;

import com.novedia.talentmap.web.ui.profile.ProfileView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class ProfileCollabWindow extends Window {
	
	private ProfileView profileView;
	
	
	
	/**
	 * Build the class ProfileCollabWindow.java 
	 * @param profileView
	 */
	public ProfileCollabWindow(ProfileView profileView) {
		super();
		this.profileView = profileView;
		
		mainBuild();
	}
	
	public void mainBuild(){
		
		center();
		addComponent(this.profileView);
		
	}

	/**
	 * Set the profileView value
	 * @param profileView the profileView to set
	 */
	public void setProfileView(ProfileView profileView) {
		this.profileView = profileView;
	}
	
	
}
