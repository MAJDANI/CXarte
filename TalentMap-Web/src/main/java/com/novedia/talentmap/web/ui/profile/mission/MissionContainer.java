package com.novedia.talentmap.web.ui.profile.mission;

import java.util.List;

import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IColleagueService;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class MissionContainer extends BeanItemContainer<Mission> {

	private IColleagueService collabService;
	
	/**
	 * 
	 */
	public MissionContainer(){
		super(Mission.class);
	}

//	public MissionContainer(IColleagueService collabService){
//		super(Mission.class);
//		this.collabService = collabService;
//	}
	
	/**
	 * Calls the CollaboratorService to retrieve all missions in tha Data Base
	 * for the collaborator's id given
	 * @param collabId
	 * @return
	 */
	public MissionContainer fillContainer(int collabId){
		
		try {
			List<Mission> listMission = this.collabService.getAllMissions(collabId);
			removeAllItems();
			// TODO : Rustine pour éviter la NPE au démarrage de l'application
			if(listMission!=null && !listMission.isEmpty()){
				for(Mission m : listMission){
					addBean(m);
					//System.out.println("MissionContainer.fillContainer() bean m = " + m);
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

}
