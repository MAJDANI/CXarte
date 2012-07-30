package com.novedia.talentmap.web.ui.profile.mission;

import java.util.List;

import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.ICollaboratorService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;

public class ListMission extends Table{

	/**
	 * Vaadin UI
	 */
	private MissionContainer missionContainer;

	/**
	 * Constants 
	 */
	public static final int COLLAB_ID = 2;
	
	/**
	 * Build the class ListMission.java 
	 * @param profileService
	 */
	public ListMission(MissionContainer missionContainer) {
		super();
		this.missionContainer = missionContainer;
	
		buildMain();
	}
	
	public void buildMain(){
		
		buildContainer();
	}
	
	public void buildContainer(){
		
		setContainerDataSource(this.missionContainer.fillContainer(this.COLLAB_ID));
	}
	
	/**
	 * Set the missionContainer value
	 * @param missionContainer the missionContainer to set
	 */
	public void setMissionContainer(MissionContainer missionContainer) {
		this.missionContainer = missionContainer;
	}

	/**
	 * Get the missionContainer value
	 * @return the missionContainer
	 */
	public MissionContainer getMissionContainer() {
		return missionContainer;
	}
}
