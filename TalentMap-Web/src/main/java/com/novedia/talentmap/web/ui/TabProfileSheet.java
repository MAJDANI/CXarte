package com.novedia.talentmap.web.ui;

import com.novedia.talentmap.web.ui.admin.AdminContentLayout;
import com.novedia.talentmap.web.ui.admin.AdminView;
import com.novedia.talentmap.web.ui.collab.MonitoringCollabView;
import com.novedia.talentmap.web.ui.profile.ProfileView;
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
	private MonitoringCollabView monitoringCollabView;
	private AdminView adminView;
	
	/**
	 * Constants
	 */
	private final String TAB_PROFILE_NAME = "Fiche Profil";
	private final String TAB_COLLAB_NAME = "Suivi des Collaborateur";
	private final String TAB_ADMIN_NAME = "Administration";

	/**
	 * 
	 * Build the class TabProfileSheet.java 
	 * @param profileView
	 * @param collabView
	 */
	public TabProfileSheet(ProfileView profileView, MonitoringCollabView monitoringCollabView, AdminView adminView){
		super();
		this.profileView = profileView;
		this.monitoringCollabView = monitoringCollabView;
		this.adminView = adminView;
		
		setImmediate(true);
		
		addTab(this.profileView, TAB_PROFILE_NAME);
		addTab(this.monitoringCollabView, TAB_COLLAB_NAME);
		addTab(this.adminView, TAB_ADMIN_NAME);
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
	
	/**
	 * Set the adminView value
	 * @param adminView the adminView to set
	 */
	public void setAdminView(AdminView adminView) {
		this.adminView = adminView;
	}

}
