package com.novedia.talentmap.web.ui;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class TabProfileSheet extends TabSheet {
	
	private static final long serialVersionUID = 2112120532020200846L;
	private ProfileView profileView;
	private CollabView collabView;
	private final String TAB_FIRST_NAME = "Fiche Profil";
	private final String TAB_SECOND_NAME = "Collaborateur";

	public TabProfileSheet(ProfileView profileView, CollabView collabView){
		super();
		this.profileView = profileView;
		this.collabView = collabView;
		
		
		
		addTab(profileView, TAB_FIRST_NAME);
		//addTab(cmView, TAB_SECOND_NAME);
	}
	
	/**
	 * Set the profileView value
	 * @param profileView the profileView to set
	 */
	public void setProfileView(ProfileView profileView) {
		this.profileView = profileView;
	}

	/**
	 * Set the cmView value
	 * @param cmView the cmView to set
	 */
	public void setCmView(CollabView collabView) {
		this.collabView = collabView;
	}
	
}
