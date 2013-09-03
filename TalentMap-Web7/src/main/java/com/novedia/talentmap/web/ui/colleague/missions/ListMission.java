package com.novedia.talentmap.web.ui.colleague.missions;

import java.util.Date;
import java.util.List;

import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.CUtils;
import com.novedia.talentmap.web.utils.MissionFieldLabel;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class ListMission extends Table {
	
	private MissionContainer missionContainer;
	
	
	 /**
     * Default constructor
     */
    public ListMission() {
		super();
		addColumns();
		setSelectable(true);
		setImmediate(true);
		setNullSelectionAllowed(true);
    }
    
    /**
     * Builds Headers of the Table ListMission
     */
    private void addColumns() {
		addContainerProperty(MissionFieldLabel.INTITULE, String.class, null);
		addContainerProperty(MissionFieldLabel.CLIENT, String.class, null);
		addContainerProperty(MissionFieldLabel.LIEU, String.class, null);
		addContainerProperty(MissionFieldLabel.DATE_DEBUT, String.class, null);
		addContainerProperty(MissionFieldLabel.DATE_FIN, String.class, null);
		addContainerProperty(MissionFieldLabel.COMMENTAIRE, String.class, null);
		addContainerProperty(MissionFieldLabel.OUTIL1, String.class, null);
		addContainerProperty(MissionFieldLabel.OUTIL2, String.class, null);
		addContainerProperty(MissionFieldLabel.OUTIL3, String.class, null);
    }
    
    /**
     * Fill all colleague mission in table
     * @return PagedTable
     */
    public Table fillAllColleagueMission(){
    	removeAllItems();
    	int colleagueId = TalentMapApplication.getCurrent().getAuthentication().getColleagueId();
    	missionContainer.fillContainer(colleagueId);
    	buildTable();
    	return this;
    }
    
    /**
     * Build mission table
     */
    private void buildTable(){
    	List<Mission> listMission = missionContainer.getItemIds();
    	
    	for (Mission mission : listMission) {
    		String dateDebut = formatterDate(mission.getStartDate());
    	    String dateFin = formatterDate(mission.getEndDate());
    	    String[] toolNames = { "", "", "" };
    	    int i = 0;
    	    if (mission.getTools() != null) {
	    		for (Tool t : mission.getTools()) {
	    		    toolNames[i] = t.getName();
	    		    i++;
	    		}
    	    }
    	    String notes = mission.getNotes();
    	    if (notes != null && notes.length() >= 25) {
    	    	notes = notes.substring(0, 25) + "[...]";
    	    }
    	    addItem(new Object[] { mission.getTitle(),
    		    mission.getClient().getName(), mission.getPlace(),
    		    dateDebut, dateFin, notes, toolNames[0], toolNames[1],
    		    toolNames[2] }, mission);
    	}
    	

    }
    
    
    /**
     * Format the date with the template dateFormat "dd/MM/yyyy"
     * 
     * @param date
     * @return
     */
    private String formatterDate(Date date) {
		if (date != null) {
		    return CUtils.DATE_FORMAT.format(date);
		} else {
		    return "";
		}
    }
    
    /**
     * Get the missionContainer
     * @return MissionContainer
     */
	public MissionContainer getMissionContainer() {
		return missionContainer;
	}

	/**
	 * Set the missionContainer 
	 * @param missionContainer missionContainer to set
	 */
	public void setMissionContainer(MissionContainer missionContainer) {
		this.missionContainer = missionContainer;
	}

    
}
