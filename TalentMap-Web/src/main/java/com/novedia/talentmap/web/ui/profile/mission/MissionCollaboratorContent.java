package com.novedia.talentmap.web.ui.profile.mission;

import com.novedia.talentmap.web.util.IMissionCollaboratorContent;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

public class MissionCollaboratorContent extends VerticalLayout implements
		ClickListener {

	/**
	 * Vaadin UI
	 */
	private AddMissionPanel addMissionPanel;
	private ListMission listMission;

	/**
	* We add missionForm in MissionCollaboratorContent's attributes in order to
	* be able to refresh the list listMission when a new mission is
	* created or when a mission is modified in missionForm
	 */
	private MissionForm missionForm ;

	/***
	 * Vaadin Components
	 */
	private Panel listPanel;
	private Label pageTitle;

	private HorizontalLayout hLayListMissionButtons;
	private Button btnAddMission;
	private Button btnModifyMission;
	private Button btnDeleteMission;

	/**
	 * Constants
	 */
	public static final String PAGE_TITLE = "Liste des missions";
	public static final String ADD_MISSION_BUTTON_NAME = "Ajouter une mission";
	public static final String MODIFY_MISSION_BUTTON_NAME = "Modifier la mission";
	public static final String DELETE_MISSION_BUTTON_NAME = "Supprimer la mission";

	/**
	 * Build the class MissionCollaboratorContent.java
	 * 
	 * @param missionForm
	 */
	
	public MissionCollaboratorContent(ListMission listMission, MissionForm missionForm,
			Button btnAddMission, AddMissionPanel addMissionPanel,
			Panel listPanel, Label pageTitle
			, Button btnModifyMission, Button btnDeleteMission
			) {
		super();
		this.listMission = listMission;
		this.missionForm = missionForm;
		this.btnAddMission = btnAddMission;
		this.addMissionPanel = addMissionPanel;
		this.listPanel = listPanel;
		this.pageTitle = pageTitle;
		this.btnModifyMission = btnModifyMission;
		this.btnDeleteMission = btnDeleteMission;

		buildMain();
		buildObersvators();
	}

	/**
	 * The main builder
	 * 
	 * @class MissionCollaboratorContent.java
	 */
	public void buildMain() {

		setMargin(true);
		setSpacing(true);

		buildTitle();

		buildButton();

		buildContent();
		// VGU
		// Set the form to act immediately on user input. This is
		// necessary for the validation of the fields to occur immediately
		// when the input focus changes and not just on commit.	
		this.missionForm.setImmediate(true);


	}

	/**
	 * The title builder
	 * 
	 * @class MissionCollaboratorContent.java
	 */
	public void buildTitle() {

		this.pageTitle.setCaption(PAGE_TITLE);
		this.pageTitle.setStyle(TalentMapCSS.H2);
		addComponent(this.pageTitle);
	}

	/**
	 * The button builder
	 * 
	 * @class MissionCollaboratorContent.java
	 */
	public void buildButton() {
		this.btnAddMission.setCaption(ADD_MISSION_BUTTON_NAME);
		this.btnAddMission.addListener(this);
		
		this.btnModifyMission.setCaption(MODIFY_MISSION_BUTTON_NAME);
		this.btnModifyMission.addListener(this);
	
		this.btnDeleteMission.setCaption(DELETE_MISSION_BUTTON_NAME);
		this.btnDeleteMission.addListener(this);
		
	}

	/**
	 * The builder of the global content
	 * 
	 * @class MissionCollaboratorContent.java
	 */
	public void buildContent() {

		addComponent(this.btnAddMission);
		addComponent(this.addMissionPanel);

		buildListPanelMission();

		if (this.listMission.getItemIds().isEmpty()) {

			showAddMissionPanel();
			this.btnAddMission.setEnabled(false);
			this.listPanel.setVisible(false);
		} else {

			hideAddMissionPanel();
			this.listPanel.setVisible(true);
		}
	}
	
	public void buildListPanelMission() {
		this.listPanel.addComponent(this.listMission);
		
		this.hLayListMissionButtons = new HorizontalLayout();
		this.hLayListMissionButtons.addComponent(btnModifyMission);
		this.hLayListMissionButtons.addComponent(btnDeleteMission);
		this.hLayListMissionButtons.setSpacing(true);
		
		this.listPanel.addComponent(this.hLayListMissionButtons);
		addComponent(this.listPanel);
	}


	//TODO VGU : utile?
	private void showAddMissionPanel() {
		this.addMissionPanel.setVisible(true);
	}

	//TODO VGU : utile?
	private void hideAddMissionPanel() {
		this.addMissionPanel.setVisible(false);
	}

	/**
	 * Builder for all observers
	 * @class IMissionView.java
	 */
	private void buildObersvators() {
		/*
		 * Observable : AddMissionPanel
		 */
		this.missionForm.addObservateur(new IMissionCollaboratorContent() {
			@Override
			public void updateListMission(ListMission listMission) {
				//MissionCollaboratorContent "receives" the new list updated
				MissionCollaboratorContent.this.listMission = listMission;
				//We remove the old listMission from the list panel
				MissionCollaboratorContent.this.listPanel.removeAllComponents();
				//We fill the listPanel with the new list
				buildListPanelMission();
				disableAddMissionPanel();
			}
			@Override
			public void cancelAddMission() {
				MissionCollaboratorContent.this.missionForm.emptyMissionForm();
				disableAddMissionPanel();
			}

		}, IMissionCollaboratorContent.class);
	}

	/**
	 * Shows the form to add missions and disables the button "add"
	 */
	private void enableAddMissionPanel(){
		btnAddMission.setEnabled(false);
		addMissionPanel.setVisible(true);
	}
	
	/**
	 * Hides the form to add missions and enables the button "add"
	 */
	private void disableAddMissionPanel(){
		btnAddMission.setEnabled(true);
		addMissionPanel.setVisible(false);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		System.out.println("MissionCollaboratorContent.buttonClick()");
		Button button = event.getButton();
		// ADD NEW MISSION
		if (button == this.btnAddMission) { 
			enableAddMissionPanel();
			//On indique que l'action courante est une crÃƒÂ©ation (pas une modification de mission)
			this.missionForm.setCurrentSaveMode(this.missionForm.SAVE_MODE_INSERT);
		}
		// MODIFY OR DELETE EXISTING MISSION
		else {
			Object missionIdSelected = this.listMission.getValue();
			System.out.println("missionId=" + missionIdSelected);
			// CHECK USER SELECTED A MISSION IN THE TABLE
			if(null == missionIdSelected) {
				getWindow().showNotification("Veuillez sÃƒÂ©lectionner une mission", Notification.TYPE_WARNING_MESSAGE);
			} 
			else {
				// MODIFY MISSION
				if (button == this.btnModifyMission) {
					fillAddMissionPanelWithMission((Integer)missionIdSelected);
					//On indique que l'action courante est une modification de mission (pas une crÃƒÂ©ation)
					this.missionForm.setCurrentSaveMode(this.missionForm.SAVE_MODE_UPDATE);
				} 
				// DELETE MISSION
				if (button == this.btnDeleteMission) {

					//TODO
					message("buttonClick : delete");
					deleteMission((Integer)missionIdSelected);
				}
			}
		}
	}
	
	
	/**
	 * Shows the form and fills it with the properties of the mission the user wants to update
	 * @param missionId : the id of the mission selected in the table
	 */
	private void fillAddMissionPanelWithMission(Integer missionId) {
		
		System.out.println("fillAddMissionPanelWithMission  missionId=" + missionId);
		enableAddMissionPanel();
		Item itemMissionSelected = this.listMission.getItem(missionId);
		System.out.println("itemMissionSelected=" + itemMissionSelected);

		this.missionForm.fillMissionFormWithMission(itemMissionSelected, missionId);
		
	}
	

	/**
	 * Asks the user if he really wants to delete the mission, then if ok the mission is deleted
	 * @param missionId : id of the mission to delete
	 */
	private void deleteMission(Integer missionId) {
		
		System.out.println("deleteMission  missionId=" + missionId);
		//TODO
		this.missionForm.deleteMission(missionId);
		
	}

	/**
	 * Temporary test method
	 * @deprecated
	 * @param message
	 */
	private void message(String message) {
		 getWindow().showNotification(message, Notification.TYPE_TRAY_NOTIFICATION);		
	}
	
	/**
	 * Get the listMission value
	 * 
	 * @return the listMission
	 */
	public ListMission getListMission() {
		return listMission;
	}

	/**
	 * Set the listMission value
	 * 
	 * @param listMission
	 *            the listMission to set
	 */
	public void setListMission(ListMission listMission) {
		this.listMission = listMission;
	}

	/**
	 * Set the addMissionButton value
	 * 
	 * @param addMissionButton
	 *            the addMissionButton to set
	 */
	public void setAddMissionButton(Button addMissionButton) {
		this.btnAddMission = addMissionButton;
	}

	/**
	 * Set the listPanel value
	 * 
	 * @param listPanel
	 *            the listPanel to set
	 */
	public void setListPanel(Panel listPanel) {
		this.listPanel = listPanel;
	}

	/**
	 * Get the pageTitle value
	 * 
	 * @return the pageTitle
	 */
	public Label getPageTitle() {
		return pageTitle;
	}

	/**
	 * Set the pageTitle value
	 * 
	 * @param pageTitle
	 *            the pageTitle to set
	 */
	public void setPageTitle(Label pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	/**
	 * Get the addMissionPanel value
	 * @return the addMissionPanel
	 */
	public AddMissionPanel getAddMissionPanel() {
		return addMissionPanel;
	}

	/**
	 * Set the addMissionPanel value
	 * @param addMissionPanel the addMissionPanel to set
	 */
	public void setAddMissionPanel(AddMissionPanel addMissionPanel) {
		this.addMissionPanel = addMissionPanel;
	}

	/**
	 * Get the listPanel value
	 * @return the listPanel
	 */
	public Panel getListPanel() {
		return listPanel;
	}

	/**
	 * Get the addMissionButton value
	 * @return the addMissionButton
	 */
	public Button getAddMissionButton() {
		return btnAddMission;
	}

}
