package com.novedia.talentmap.web.ui.profile.mission;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.novedia.talentmap.model.entity.Mission;

import com.vaadin.ui.Table;

public class ListMission extends Table {

	protected static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Vaadin UI
	 */
	private MissionContainer missionContainer;

	/**
	 * Temporary Constant 
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
		//calling setwith(850) allows vaadin display automatically an horizontal 
		//scrollBar if line width is too large
		setWidth(850);
		buildContainer();
	}

	/**
	 * Principal function filling ListMission Table
	 */
	public void buildContainer(){
		System.out.println("ListMission.buildContainer()");
		addColumns();
		setSelectable(true);
		setNullSelectionAllowed(true);
		fillMissionContainer();
		fillResultsTable();
	}
	
	public void fillMissionContainer() {
		this.missionContainer.fillContainer(this.COLLAB_ID);
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
		addContainerProperty(MissionForm.COMMENTAIRE, String.class, null);	}

	/**
	 * Gets each item mission in the MissionContainer given in parameter.
	 * With each item mission we fill the actual Table ListMission using addItem() method.  
	 * @param missionContainer
	 */
	public void fillResultsTable() {
		Collection<Mission> collectionMission = this.missionContainer.getItemIds();
		
		for (Mission mission : collectionMission) {
			String dateDebut = formatterDate(mission.getStartDate());
			String dateFin = formatterDate(mission.getEndDate());
			
			addItem(new Object[] { 
					mission.getTitle(),
					mission.getClient().getName(), mission.getPlace(),
					dateDebut, dateFin,
					mission.getNotes()}, mission);
		}
	}

	/**
	 * Format the date with the template dateFormat "dd/MM/yyyy"
	 * @param date
	 * @return
	 */
	public static String formatterDate(Date date) {
		return dateFormat.format(date);
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
