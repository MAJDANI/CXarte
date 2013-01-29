package com.novedia.talentmap.web.ui.profile.mission;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.novedia.talentmap.model.entity.Mission;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Table;

public class ListMission extends Table implements ItemClickListener {

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
	 * the mission witch was clicked for modification 
	 */
	private Mission missionSelected;
	
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
		
		//int idResultsTable = 1;
		for (Mission mission : collectionMission) {
			String dateDebut = formatterDate(mission.getStartDate());
			String dateFin = formatterDate(mission.getEndDate());
			Integer idMission = mission.getId();
			System.out.println("idMission=" + idMission);
			addItem(new Object[] { 
					mission.getTitle(),
					mission.getClient(), mission.getPlace(),
					dateDebut, dateFin,
					mission.getNotes()}, mission.getId()); //idResultsTable);//test 2012/09/24
			//idResultsTable++;
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

	@Override
	public void itemClick(ItemClickEvent event) {
		// TODO Auto-generated method stub
		this.setValue(null);
		this.missionSelected = ((Mission) event.getSource());
	}
	
	public Mission getMissionSelected() {
		return missionSelected;
	}

}
