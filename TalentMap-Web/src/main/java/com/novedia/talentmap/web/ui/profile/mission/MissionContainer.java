package com.novedia.talentmap.web.ui.profile.mission;

import java.util.List;

import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.ICollaboratorService;
import com.vaadin.data.util.BeanItemContainer;

public class MissionContainer extends BeanItemContainer<Mission> {

	private ICollaboratorService collabService;
	
	public static int COLLAB_ID;

	public MissionContainer(ICollaboratorService collabService){
		super(Mission.class);
		this.collabService = collabService;
	}
	
	public MissionContainer fillContainer(int collabId){
		
		this.COLLAB_ID = collabId;
		
		try {
			
			List<Mission> listMission = this.collabService.getAllMission(this.COLLAB_ID);
			
			for(Mission m : listMission){
				
				addItem(m);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return this;
	}
	
	/**
	 * Get the collabService value
	 * @return the collabService
	 */
	public ICollaboratorService getCollabService() {
		return collabService;
	}

	/**
	 * Set the collabService value
	 * @param collabService the collabService to set
	 */
	public void setCollabService(ICollaboratorService collabService) {
		this.collabService = collabService;
	}

	/**
	 * Get the cOLLAB_ID value
	 * @return the cOLLAB_ID
	 */
	public static int getCOLLAB_ID() {
		return COLLAB_ID;
	}

	/**
	 * Set the cOLLAB_ID value
	 * @param cOLLAB_ID the cOLLAB_ID to set
	 */
	public static void setCOLLAB_ID(int cOLLAB_ID) {
		COLLAB_ID = cOLLAB_ID;
	}
}
