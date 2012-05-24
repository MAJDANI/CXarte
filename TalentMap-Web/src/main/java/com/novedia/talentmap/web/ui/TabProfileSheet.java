package com.novedia.talentmap.web.ui;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * The Profile tab contains the profile view and also the collaborator view (CM) 
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */
public class TabProfileSheet extends TabSheet {
	
	private static final long serialVersionUID = 2112120532020200846L;
	
	/**
	 * All views
	 */
	private ProfileView profileView;
	private CollabView collabView;
	
	/**
	 * Constants
	 */
	private final String TAB_FIRST_NAME = "Fiche Profil";
	private final String TAB_SECOND_NAME = "Collaborateur";

	/**
	 * 
	 * Build the class TabProfileSheet.java 
	 * @param profileView
	 * @param collabView
	 */
	public TabProfileSheet(ProfileView profileView, CollabView collabView){
		super();
		this.profileView = profileView;
		this.collabView = collabView;
		
		setImmediate(true);
		
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
