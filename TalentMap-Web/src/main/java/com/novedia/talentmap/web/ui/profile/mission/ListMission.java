package com.novedia.talentmap.web.ui.profile.mission;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.novedia.talentmap.model.entity.Mission;
import com.vaadin.ui.Table;

public class ListMission extends Table{

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
		addColumns();
		this.missionContainer.fillContainer(this.COLLAB_ID);
		fillResultsTable(this.missionContainer);
	}
	
	/**
	 * Builds Headers of the Table ListMission
	 */
	public void addColumns() {
		addContainerProperty("Intitulé", String.class, null);
		addContainerProperty("Client", String.class, null);
		addContainerProperty("Lieu", String.class, null);
		addContainerProperty("Date début", String.class, null);
		addContainerProperty("Date fin", String.class, null);
		addContainerProperty("Commentaire", String.class, null);
	}

	/**
	 * Gets each item mission in the MissionContainer given in parameter.
	 * With each item mission we fill the actual Table ListMission using addItem() method.  
	 * @param missionContainer
	 */
	public void fillResultsTable(MissionContainer missionContainer) {
		Collection<Mission> collectionMission = missionContainer.getItemIds();
		
		int idResultsTable = 1;
		for (Mission mission : collectionMission) {
			String dateDebut = formatterDate(mission.getStart_date());
			String dateFin = formatterDate(mission.getEnd_date());
			
			addItem(new Object[] { mission.getName(),
					mission.getClient(), mission.getPlace(),
					dateDebut, dateFin,
					mission.getNotes()}, idResultsTable);
			idResultsTable++;
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
