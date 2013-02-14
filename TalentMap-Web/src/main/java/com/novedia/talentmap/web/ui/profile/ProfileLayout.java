package com.novedia.talentmap.web.ui.profile;

import com.novedia.talentmap.web.ui.ea.EaContentHistorique;
import com.novedia.talentmap.web.ui.profile.mission.MissionCollaboratorContent;
import com.novedia.talentmap.web.ui.profile.skill.SkillCollaboratorContent;
import com.novedia.talentmap.web.util.IProfileLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

public class ProfileLayout extends HorizontalLayout {

	/**
	 * Vaadin UI
	 */
	private ProfileCollaboratorContent profileCollabContent;
	private MissionCollaboratorContent missionCollabContent;
	private SkillCollaboratorContent skillCollabContent;
	private ProfileNavigation profileNavigation;
	private EaContentHistorique eaContentHistorique;

	/**
	 * Vaadin Components
	 */
	private HorizontalSplitPanel hSplitPanel;

	/**
	 * Build the class ProfileLayout.java
	 * 
	 * @param profileCollabContent
	 * @param profileNavigation
	 */
	public ProfileLayout(ProfileCollaboratorContent profileCollabContent,
			ProfileNavigation profileNavigation,
			HorizontalSplitPanel hSplitPanel,
			MissionCollaboratorContent missionCollabContent,
			SkillCollaboratorContent skillCollabContent,
			EaContentHistorique eaContentHistorique) {
		super();
		this.profileCollabContent = profileCollabContent;
		this.profileNavigation = profileNavigation;
		this.skillCollabContent = skillCollabContent;
		this.hSplitPanel = hSplitPanel;
		this.missionCollabContent = missionCollabContent;
		this.eaContentHistorique = eaContentHistorique;

		buildObservators();
		
		mainBuild();
	}
	
	public void buildObservators(){
		
		this.profileNavigation.addObservateur(new IProfileLayout() {
			
			@Override
			public void updateProfileLayout(Class<?> cl) {
				
				if(cl == ProfileCollaboratorContent.class){
					
					ProfileLayout.this.profileCollabContent.setVisible(true);
					ProfileLayout.this.missionCollabContent.setVisible(false);
					ProfileLayout.this.skillCollabContent.setVisible(false);
					ProfileLayout.this.eaContentHistorique.setVisible(false);
					
					ProfileLayout.this.hSplitPanel.setSecondComponent(ProfileLayout.this.profileCollabContent);
				}
				
				if(cl == MissionCollaboratorContent.class){
					
					ProfileLayout.this.profileCollabContent.setVisible(false);
					ProfileLayout.this.missionCollabContent.setVisible(true);
					ProfileLayout.this.skillCollabContent.setVisible(false);
					ProfileLayout.this.eaContentHistorique.setVisible(false);
					ProfileLayout.this.hSplitPanel.setSecondComponent(ProfileLayout.this.missionCollabContent);
				}
				
				if(cl == SkillCollaboratorContent.class){
					
					ProfileLayout.this.profileCollabContent.setVisible(false);
					ProfileLayout.this.missionCollabContent.setVisible(false);
					ProfileLayout.this.skillCollabContent.setVisible(true);
					ProfileLayout.this.eaContentHistorique.setVisible(false);
					ProfileLayout.this.hSplitPanel.setSecondComponent(ProfileLayout.this.skillCollabContent);
				}
				if(cl == EaContentHistorique.class){
					ProfileLayout.this.eaContentHistorique.setVisible(true);
					ProfileLayout.this.profileCollabContent.setVisible(false);
					ProfileLayout.this.missionCollabContent.setVisible(false);
					ProfileLayout.this.skillCollabContent.setVisible(false);
					ProfileLayout.this.hSplitPanel.setSecondComponent(ProfileLayout.this.eaContentHistorique);
					
				
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
		
		initView();
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.addComponent(this.profileNavigation);
		vLayout.setHeight(800);

		this.hSplitPanel.setFirstComponent(vLayout);
		this.hSplitPanel.setSecondComponent(this.profileCollabContent);
		this.hSplitPanel.setSplitPosition(20);
		this.hSplitPanel.setLocked(true);

		addComponent(this.hSplitPanel);
		setSizeFull();
		setExpandRatio(this.hSplitPanel, 1);
	}
	
	public void initView(){
		
		this.profileCollabContent.setVisible(true);
		this.missionCollabContent.setVisible(false);
		this.skillCollabContent.setVisible(false);
		this.eaContentHistorique.setVisible(false);
	}
	
	/**
	 * @return the eaContentHistorique
	 */
	public EaContentHistorique getEaContentHistorique() {
		return eaContentHistorique;
	}

	/**
	 * @param eaContentHistorique the eaContentHistorique to set
	 */
	public void setEaContentHistorique(EaContentHistorique eaContentHistorique) {
		this.eaContentHistorique = eaContentHistorique;
	}

	/**
	 * Get the profileCollabContent value
	 * 
	 * @return the profileCollabContent
	 */
	public ProfileCollaboratorContent getProfileCollabContent() {
		return profileCollabContent;
	}

	/**
	 * Set the profileCollabContent value
	 * 
	 * @param profileCollabContent
	 *            the profileCollabContent to set
	 */
	public void setProfileCollabContent(
			ProfileCollaboratorContent profileCollabContent) {
		this.profileCollabContent = profileCollabContent;
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
	public HorizontalSplitPanel gethSplitPanel() {
		return hSplitPanel;
	}

	/**
	 * Set the hSplitPanel value
	 * 
	 * @param hSplitPanel
	 *            the hSplitPanel to set
	 */
	public void sethSplitPanel(HorizontalSplitPanel hSplitPanel) {
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
