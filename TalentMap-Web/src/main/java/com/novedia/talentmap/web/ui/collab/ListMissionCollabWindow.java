package com.novedia.talentmap.web.ui.collab;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.ui.profile.mission.ListMission;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

/**
 * The {@link ListMissionCollabWindow} is the main window for render 
 *  missions of colleagues.
 *  
 * @author VGU
 *
 */
@SuppressWarnings("serial")
public class ListMissionCollabWindow extends Window {

	/**
	 * Vaadin Components
	 */
	private ListMission listMission;
	
	/**
	 * Colleague concerned
	 */
	private Colleague currentColleague;
	
	/**
	 * Default constructor
	 */
	public ListMissionCollabWindow(){
		super();
	}

	/**
	 * The main builder
	 * @class ProfileCollabWindow.java
	 */
	public void mainBuild() {
		setModal(true);
		center();
		this.setWidth("1135");
		removeAllComponents();
		this.setCaption(buildCaption());
		this.listMission.setColleagueId(currentColleague.getId());
		ListMission listMissionResult = this.listMission.buildAllColleagueMission();
		if(listMissionResult.size()>0) {
			this.addComponent(listMissionResult);
		} else {
			this.addComponent(new Label(ConstantsEnglish.LIST_MISSION_WINDOW_NO_MISSIONS));
		}
	}
	
	/**
	 * Builds the caption of the window with the Name of current colleague
	 * @return
	 */
	private String buildCaption() {
		String caption = ConstantsEnglish.LIST_MISSION_WINDOW_TITLE + currentColleague.getLastName() + " " + currentColleague.getFirstName() + " :";
		return caption;
	}
	
	public void setListMission(ListMission listMission) {
		this.listMission = listMission;
	}

	public Colleague getCurrentColleague() {
		return currentColleague;
	}

	public void setCurrentColleague(Colleague currentColleague) {
		this.currentColleague = currentColleague;
	}

	
}