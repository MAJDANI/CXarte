package com.novedia.talentmap.web.ui.collab;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.ui.profile.mission.ListMission;
import com.vaadin.ui.Button;
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
	private Button btnEditMission;

	/**
	 * Colleague concerned
	 */
	private Colleague currentColleague;
	
	private Integer roleId;
	
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
			if(Authorization.Role.CM.getId().equals(roleId)) {
				this.addComponent(new Label("Afficher la mission"));
				//this.addComponent(btnEditMission);
				
			}
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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRole(Integer roleId) {
		this.roleId = roleId;
	}

	
}