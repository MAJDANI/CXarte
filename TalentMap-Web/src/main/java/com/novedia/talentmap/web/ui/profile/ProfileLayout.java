package com.novedia.talentmap.web.ui.profile;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.web.ui.ea.EaContentHistorique;
import com.novedia.talentmap.web.ui.profile.mission.MissionCollaboratorContent;
import com.novedia.talentmap.web.ui.profile.mission.MissionColleagueContent;
import com.novedia.talentmap.web.ui.profile.skill.SkillCollaboratorContent;
import com.novedia.talentmap.web.util.IProfileLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ProfileLayout extends HorizontalLayout {

	/**
	 * Vaadin UI
	 */
	private ProfileCollaboratorContent profileColleagueContent;
	private ProfileNavigation profileNavigation;
	private MissionCollaboratorContent missionCollabContent;
	private SkillCollaboratorContent skillCollabContent;

	
	private Authentication authentication;

	/**
	 * Vaadin Components
	 */
	private HorizontalSplitPanel hSplitPanel;

	/**
	 * default constructor
	 */
	public ProfileLayout(){
		super();
	}
	
	
	
	/**
	 * Build the profil Layout of user
	 * @return
	 */
	public HorizontalLayout buildProfileLayout(){
		profileColleagueContent.setAuthentication(getAuthentication());
		skillCollabContent.setAuthentication(getAuthentication());
		missionCollabContent.setAuthentication(getAuthentication());
		removeAllComponents();
		hSplitPanel = new HorizontalSplitPanel();
		buildObservators();
		mainBuild();
		return this;
	}
	
	
	
	public void buildObservators(){
		
		this.profileNavigation.addObservateur(new IProfileLayout() {
			
			@Override
			public void updateProfileLayout(Class<?> cl) {
				
				if(cl == ProfileCollaboratorContent.class){
					ProfileLayout.this.hSplitPanel.setSecondComponent(ProfileLayout.this.profileColleagueContent.buildProfileColleagueContent());
				}
				
				if(cl == MissionCollaboratorContent.class){
					ProfileLayout.this.hSplitPanel.setSecondComponent(ProfileLayout.this.missionCollabContent.buildViewMissionColleagueContent());
				}
				
				if(cl == SkillCollaboratorContent.class){
					
					ProfileLayout.this.hSplitPanel.setSecondComponent(ProfileLayout.this.skillCollabContent.buildSkillCollaboratorContent());
				}
			}
		}, IProfileLayout.class);
	}

	/**
	 * The main builder
	 * 
	 * @class ProfileLayout.java
	 */
	public void mainBuild() {
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.addComponent(this.profileNavigation.mainBuild());
		vLayout.setHeight(800);

		this.hSplitPanel.setFirstComponent(vLayout);
		this.hSplitPanel.setSecondComponent(this.profileColleagueContent.buildProfileColleagueContent());
		this.hSplitPanel.setSplitPosition(20);
		this.hSplitPanel.setLocked(true);

		addComponent(this.hSplitPanel);
		setSizeFull();
		setExpandRatio(this.hSplitPanel, 1);
	}
	
	
	public Authentication getAuthentication() {
		return authentication;
	}



	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}


	/**
	 * Get the profileCollabContent value
	 * 
	 * @return the profileCollabContent
	 */
	public ProfileCollaboratorContent getProfileColleagueContent() {
		return profileColleagueContent;
	}

	/**
	 * Set the profileCollabContent value
	 * 
	 * @param profileCollabContent
	 *            the profileCollabContent to set
	 */
	public void setProfileColleagueContent(
			ProfileCollaboratorContent profileColleagueContent) {
		this.profileColleagueContent = profileColleagueContent;
	}

	/**
	 * Get the profileNavigation value
	 * 
	 * @return the profileNavigation
	 */
	public ProfileNavigation getProfileNavigation() {
		return profileNavigation;
	}

	/**
	 * Set the profileNavigation value
	 * 
	 * @param profileNavigation
	 *            the profileNavigation to set
	 */
	public void setProfileNavigation(ProfileNavigation profileNavigation) {
		this.profileNavigation = profileNavigation;
	}

	/**
	 * Get the hSplitPanel value
	 * 
	 * @return the hSplitPanel
	 */
	public HorizontalSplitPanel getHSplitPanel() {
		return hSplitPanel;
	}

	/**
	 * Set the hSplitPanel value
	 * 
	 * @param hSplitPanel
	 *            the hSplitPanel to set
	 */
	public void setHSplitPanel(HorizontalSplitPanel hSplitPanel) {
		this.hSplitPanel = hSplitPanel;
	}

	/**
	 * Get the missionCollabContent value
	 * 
	 * @return the missionCollabContent
	 */
	public MissionCollaboratorContent getMissionCollabContent() {
		return missionCollabContent;
	}

	/**
	 * Set the missionCollabContent value
	 * 
	 * @param missionCollabContent
	 *            the missionCollabContent to set
	 */
	public void setMissionCollabContent(
			MissionCollaboratorContent missionCollabContent) {
		this.missionCollabContent = missionCollabContent;
	}

	public SkillCollaboratorContent getSkillCollabContent() {
		return skillCollabContent;
	}

	public void setSkillCollabContent(SkillCollaboratorContent skillCollabContent) {
		this.skillCollabContent = skillCollabContent;
	}



		
}