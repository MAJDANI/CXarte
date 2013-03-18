package com.novedia.talentmap.web.ui.profile.mission;

import com.vaadin.ui.Panel;

@SuppressWarnings("serial")
public class AddMissionPanel extends Panel {
	
	
	private MissionForm missionForm;

	/**
	 * Default constructor
	 */
	public AddMissionPanel(){
		super();
	}
	
	/**
	 * Build the AddMissionPanel
	 * @return
	 */
	public AddMissionPanel buildAddMissionPanel(){
		mainBuild();
		return this;
	}

	/**
	 * Build the class AddMissionPanel.java 
	 * @param missionForm
	 */
//	public AddMissionPanel(MissionForm missionForm) {
//		super();
//		this.missionForm = missionForm;
//		
//		mainBuild();
//	}
	
	/**
	 * The main builder
	 * @class AddMissionPanel.java
	 */
	public void mainBuild(){
		
		addComponent(this.missionForm);
	}

	/**
	 * Get the missionForm value
	 * @return the missionForm
	 */
	public MissionForm getMissionForm() {
		return missionForm;
	}

	/**
	 * Set the missionForm value
	 * @param missionForm the missionForm to set
	 */
	public void setMissionForm(MissionForm missionForm) {
		this.missionForm = missionForm;
	}

	

}
