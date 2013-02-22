package com.novedia.talentmap.web.ui;

import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.commons.ConstantsForMenuEnglish;
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
public class TabProfileSheet extends TabSheet{
	
	/**
	 * Serial Version Number
	 */
	private static final long serialVersionUID = 2112120532020200846L;
	
	/**
	 * All views
	 */
	private ProfileView profileView;
	private MonitoringCollabView monitoringCollabView;
	private TabProfileSkills tabProfileSkills;
	private TabProfileAdminstrativeData tabProfileAdminstratveData;
	private TabProfileHistoricMission tabProfileHistoricMission;
	
	/**
	 * 
	 * Build the class TabProfileSheet.java 
	 * @param profileView
	 * @param collabView
	 */
	public TabProfileSheet(ProfileView profileView,
			MonitoringCollabView monitoringCollabView,
			TabProfileSkills tabProfileSkills,
			TabProfileAdminstrativeData tabProfileAdminstratveData,
			TabProfileHistoricMission tabProfileHistoricMission){
		super();
		this.profileView = profileView;
		this.monitoringCollabView = monitoringCollabView;
		this.tabProfileSkills = tabProfileSkills;
		this.tabProfileAdminstratveData = tabProfileAdminstratveData;
		this.tabProfileHistoricMission = tabProfileHistoricMission;
	
		setImmediate(true);
		addTab(this.profileView, ConstantsEnglish.TAB_PROFILE_NAME);
		//addTab(this.monitoringCollabView, ConstantsEnglish.TAB_COLLAB_NAME);
		addTab(this.tabProfileSkills, ConstantsForMenuEnglish.VISUALIZE_SKILLS_NAME);
		addTab(this.tabProfileAdminstratveData, ConstantsForMenuEnglish.VISUALIZE_ADMINISTRATIVE_DATA);
		addTab(this.tabProfileHistoricMission, ConstantsForMenuEnglish.VISUALIZE_MISSIONS_NAME);	
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
	 * @return the tabProfileSkills
	 */
	public TabProfileSkills getTabProfileSkills() {
		return tabProfileSkills;
	}

	/**
	 * @param tabProfileSkills the tabProfileSkills to set
	 */
	public void setTabProfileSkills(TabProfileSkills tabProfileSkills) {
		this.tabProfileSkills = tabProfileSkills;
	}

	/**
	 * @param tabProfileAdminstratveData the tabProfileAdminstratveData to set
	 */
	public void setTabProfileAdminstratveData(
			TabProfileAdminstrativeData tabProfileAdminstratveData) {
		this.tabProfileAdminstratveData = tabProfileAdminstratveData;
	}

	/**
	 * @param tabProfileHistoricMission the tabProfileHistoricMission to set
	 */
	public void setTabProfileHistoricMission(
			TabProfileHistoricMission tabProfileHistoricMission) {
		this.tabProfileHistoricMission = tabProfileHistoricMission;
	}
}