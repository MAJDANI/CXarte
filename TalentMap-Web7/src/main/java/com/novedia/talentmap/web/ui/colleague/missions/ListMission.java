package com.novedia.talentmap.web.ui.colleague.missions;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.CUtils;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class ListMission extends Table {
	
	private MissionContainer missionContainer;
	
	private ResourceBundle resourceBundle;
	
	
	 /**
     * Default constructor
     */
    public ListMission() {
		super();
		setSelectable(true);
		setImmediate(true);
		setNullSelectionAllowed(true);
		setWidth("100%");
    }
    
    
    /**
     * Fill all colleague mission in table
     * @return PagedTable
     */
    public Table fillAllColleagueMission(){
    	Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES , locale);
    	removeAllItems();
    	int colleagueId = TalentMapApplication.getCurrent().getAuthentication().getColleagueId();
    	missionContainer.fillContainer(colleagueId);
    	buildTable();
    	return this;
    }
    
    /**
     * Builds Headers of the Table ListMission
     */
    private void addColumns() {
		addContainerProperty(resourceBundle.getString("table.header.title"), String.class, null);
		addContainerProperty(resourceBundle.getString("table.header.client"), String.class, null);
		addContainerProperty(resourceBundle.getString("table.header.place"), String.class, null);
		addContainerProperty(resourceBundle.getString("table.header.date.debut"), String.class, null);
		addContainerProperty(resourceBundle.getString("table.header.date.fin"), String.class, null);
		addContainerProperty(resourceBundle.getString("table.header.comment"), String.class, null);
		addContainerProperty(resourceBundle.getString("table.header.tool1"), String.class, null);
		addContainerProperty(resourceBundle.getString("table.header.tool2"), String.class, null);
		addContainerProperty(resourceBundle.getString("table.header.tool3"), String.class, null);
    }
    
    
    /**
     * Build mission table
     */
    private void buildTable(){
    	addColumns();
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
