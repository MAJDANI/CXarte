package com.novedia.talentmap.web.ui.profile.mission;

import java.util.List;

import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.IColleagueService;
import com.vaadin.data.util.BeanItemContainer;

public class MissionContainer extends BeanItemContainer<Mission> {

	private IColleagueService collabService;
	
	/**
	 * Temporary parameter
	 */
	public static int COLLAB_ID = 2;

	public MissionContainer(IColleagueService collabService){
		super(Mission.class);
		this.collabService = collabService;
	}
	
	/**
	 * Calls the CollaboratorService to retrieve all missions in tha Data Base
	 * for the collaborator's id given
	 * @param collabId
	 * @return
	 */
	public MissionContainer fillContainer(int collabId){
		
		MissionContainer.COLLAB_ID = collabId;
		try {
			List<Mission> listMission = this.collabService.getAllMissions(MissionContainer.COLLAB_ID);
			System.out.println("MissionContainer.fillContainer() listMission = " + listMission);
			
			// TODO : Rustine pour éviter la NPE au démarrage de l'application
			if(listMission!=null){
				for(Mission m : listMission){
					addBean(m);
					System.out.println("MissionContainer.fillContainer() bean m = " + m);
				}
				
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
	public IColleagueService getCollabService() {
		return collabService;
	}

	/**
	 * Set the collabService value
	 * @param collabService the collabService to set
	 */
	public void setCollabService(IColleagueService collabService) {
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
