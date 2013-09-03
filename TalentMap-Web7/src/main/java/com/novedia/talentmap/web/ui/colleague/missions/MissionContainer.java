package com.novedia.talentmap.web.ui.colleague.missions;

import java.util.List;

import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.IColleagueService;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class MissionContainer extends BeanItemContainer<Mission> {

	/**
	 * The colleagueService
	 */
	private IColleagueService colleagueService;

    /**
	 * Default constructor
	 */
    public MissionContainer() {
    	super(Mission.class);
    }

    /**
     * Calls the CollaboratorService to retrieve all missions in the Data Base
     * for the collaborator's id given
     * 
     * @param collabId
     * @return
     */
    public MissionContainer fillContainer(int collabId) {
		try {
			    List<Mission> listMission = this.colleagueService.getAllMissions(collabId);
			    removeAllItems();
			    if (listMission != null && !listMission.isEmpty()) {
					for (Mission m : listMission) {
					    addBean(m);
					}
			    }
			} catch (Exception e) {
				e.printStackTrace();
			}
		return this;
    }

    /**
     * Get the colleagueService
     * 
     * @return the colleagueService
     */
    public IColleagueService getColleagueService() {
	return colleagueService;
    }

    /**
     * Set the colleagueService 
     * 
     * @param collabService colleagueService to set
     */
    public void setColleagueService(IColleagueService collabService) {
	this.colleagueService = collabService;
    }

}
