package com.novedia.talentmap.web.ui.profile.mission;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;

import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class ListMission extends Table {

	protected static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy");

	/**
	 * Vaadin UI
	 */
	private MissionContainer missionContainer;

	private Authentication authentication;
	

	
	/**
	 * Default constructor
	 */
	public ListMission(){
		super();
		addColumns();
		setSelectable(true);
		setNullSelectionAllowed(true);
	}
	
	/**
	 * Build colleague's mission
	 * @return
	 */
	public ListMission buildAllColleagueMission(){
		removeAllItems();
		buildMain();
		return this;
	}
	
	/**
	 * Build the class ListMission.java 
	 * @param profileService
	 */
//	public ListMission(MissionContainer missionContainer) {
//		super();
//		this.missionContainer = missionContainer;
//		buildMain();
//	}
	
	public void buildMain(){
		//calling setwith(900) allows vaadin display automatically an horizontal 
		//scrollBar if line width is too large
		setWidth(900);
		buildContainer();
	}

	/**
	 * Principal function filling ListMission Table
	 */
	public void buildContainer(){
		fillMissionContainer();
		fillResultsTable();
	}
	
	public void fillMissionContainer() {
		this.missionContainer.fillContainer(authentication.getColleagueId());
	}
	/**
	 * Builds Headers of the Table ListMission
	 */
	public void addColumns() {
		addContainerProperty(MissionForm.INTITULE, String.class, null);
		addContainerProperty(MissionForm.CLIENT, String.class, null);
		addContainerProperty(MissionForm.LIEU, String.class, null);
		addContainerProperty(MissionForm.DATE_DEBUT, String.class, null);
		addContainerProperty(MissionForm.DATE_FIN, String.class, null);
		addContainerProperty(MissionForm.COMMENTAIRE, String.class, null);
		addContainerProperty(MissionForm.OUTILS1, String.class, null);
		addContainerProperty(MissionForm.OUTILS2, String.class, null);
		addContainerProperty(MissionForm.OUTILS3, String.class, null);
	}

	/**
	 * Gets each item mission in the MissionContainer given in parameter.
	 * With each item mission we fill the actual Table ListMission using addItem() method.  
	 * @param missionContainer
	 */
	public void fillResultsTable() {
		Collection<Mission> collectionMission = this.missionContainer
				.getItemIds();


		for (Mission mission : collectionMission) {
			String dateDebut = formatterDate(mission.getStartDate());
			String dateFin = formatterDate(mission.getEndDate());
			String[] toolNames =  {"","",""};
			int i = 0;
			if (mission.getTools() != null) {
				
				for (Tool t : mission.getTools()) {
					toolNames[i] = t.getName();
					i++;
					
				}

			}
				addItem(new Object[] { mission.getTitle(),
						mission.getClient().getName(), mission.getPlace(),
						dateDebut, dateFin, mission.getNotes(), toolNames[0],
						toolNames[1], toolNames[2] }, mission);
		}
	}

	/**
	 * Format the date with the template dateFormat "dd/MM/yyyy"
	 * @param date
	 * @return
	 */
	public static String formatterDate(Date date) {
		if (date != null) {
			return dateFormat.format(date);
		}
		else {
			return "";
		}
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


	public Authentication getAuthentication() {
		return authentication;
	}


	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	
	


}
