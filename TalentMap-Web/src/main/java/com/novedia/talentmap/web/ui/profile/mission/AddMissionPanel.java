package com.novedia.talentmap.web.ui.profile.mission;

import com.novedia.talentmap.web.util.IMissionCollaboratorContent;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.IProfileView;
import com.vaadin.ui.Panel;

public class AddMissionPanel extends Panel {
	
	private MissionForm missionForm;


	/**
	 * Build the class AddMissionPanel.java 
	 * @param missionForm
	 */
	public AddMissionPanel(MissionForm missionForm) {
		super();
		this.missionForm = missionForm;
		
		mainBuild();
	}
	
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
