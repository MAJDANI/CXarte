package com.novedia.talentmap.web.ui;

import com.novedia.talentmap.web.ui.collab.MonitoringCollabView;
import com.novedia.talentmap.web.ui.profile.ProfileView;
import com.vaadin.ui.TabSheet;

/**
 * The Profile tab contains the profile view and also the collaborator view (CM) 
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */
public class TabProfileSheet extends TabSheet {
	
	/**
	 * Serial Version Number
	 */
	private static final long serialVersionUID = 2112120532020200846L;
	
	/**
	 * All views
	 */
	private ProfileView profileView;
	private MonitoringCollabView monitoringCollabView;
	
	/**
	 * Constants
	 */
	private final String TAB_PROFILE_NAME = "Fiche Profil";
	private final String TAB_COLLAB_NAME = "Suivi des Collaborateur";

	/**
	 * 
	 * Build the class TabProfileSheet.java 
	 * @param profileView
	 * @param collabView
	 */
	public TabProfileSheet(ProfileView profileView, MonitoringCollabView monitoringCollabView){
		super();
		this.profileView = profileView;
		this.monitoringCollabView = monitoringCollabView;
		
		setImmediate(true);
		
		addTab(this.profileView, TAB_PROFILE_NAME);
		addTab(this.monitoringCollabView, TAB_COLLAB_NAME);
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
	public void setMonitoringCollabView(MonitoringCollabView monitoringCollabView) {
		this.monitoringCollabView = monitoringCollabView;
	}

}
