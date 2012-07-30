package com.novedia.talentmap.web.ui.profile.mission;

import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class MissionCollaboratorContent extends VerticalLayout implements
		ClickListener {

	/**
	 * Vaadin UI
	 */
	private AddMissionPanel addMissionPanel;
	private ListMission listMission;

	/***
	 * Vaadin Components
	 */
	private Panel listPanel;
	private Button addMissionButton;
	private Label pageTitle;

	/**
	 * Constants
	 */
	public static final String PAGE_TITLE = "Liste des missions";
	public static final String ADD_MISSION_BUTTON_NAME = "Ajouter une mission";

	/**
	 * Build the class MissionCollaboratorContent.java
	 * 
	 * @param missionForm
	 */
	public MissionCollaboratorContent(ListMission listMission,
			Button addMissionButton, AddMissionPanel addMissionPanel,
			Panel listPanel, Label pageTitle) {
		super();
		this.addMissionPanel = addMissionPanel;
		this.listMission = listMission;
		this.addMissionButton = addMissionButton;
		this.listPanel = listPanel;
		this.pageTitle = pageTitle;

		buildMain();
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
	 * The builder of the global content
	 * 
	 * @class MissionCollaboratorContent.java
	 */
	public void buildContent() {

		addComponent(this.addMissionButton);
		addComponent(this.addMissionPanel);

		this.listPanel.addComponent(this.listMission);
		addComponent(this.listPanel);

		if (this.listMission.getItemIds().isEmpty()) {

			this.addMissionPanel.setVisible(true);
			this.addMissionButton.setEnabled(false);
			this.listPanel.setVisible(false);
		} else {

			this.addMissionPanel.setVisible(false);
			this.listPanel.setVisible(true);
		}
	}

	/**
	 * The button builder
	 * 
	 * @class MissionCollaboratorContent.java
	 */
	public void buildButton() {
		
		
		this.addMissionButton.setCaption(ADD_MISSION_BUTTON_NAME);
		this.addMissionButton.addListener(this);
	}

	@Override
	public void buttonClick(ClickEvent event) {

		Button button = event.getButton();

		if (button == this.addMissionButton) {

			this.addMissionPanel.setVisible(true);
		}
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
		this.addMissionButton = addMissionButton;
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
		return addMissionButton;
	}

}
