package com.novedia.talentmap.web.ui.profile.mission;

import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.IMissionCollaboratorContent;
import com.novedia.talentmap.web.util.Message;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

public class MissionCollaboratorContent extends VerticalLayout implements
		ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8997879738001974821L;

	/**
	 * Talent Map Service
	 */
	private IColleagueService collaboratorService;

	/**
	 * Vaadin UI
	 */
	private AddMissionPanel addMissionPanel;
	private ListMission listMission;

	/**
	 * We add missionForm in MissionCollaboratorContent's attributes in order to
	 * be able to refresh the list listMission when a new mission is created or
	 * when a mission is modified in missionForm
	 */
	private MissionForm missionForm;

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
	public static final String LABEL_BUTTON_ADD_MISSION = "Ajouter une mission";
	public static final String LABEL_BUTTON_MODIFY_MISSION = "Modifier la mission";
	public static final String LABEL_BUTTON_DELETE_MISSION = "Supprimer la mission";

	/**
	 * SubWindow to confirm or cancel a delete mission action
	 */
	private Window windowConfirmDelete;

	/**
	 * The mission selected by the user, in order to modify or delete it.
	 */
	private Mission selectedMission;
	
	/**
	 * Labels form confirmation delete window
	 */
	private static final String LABEL_WINDOW_CONFIRM_DELETE = "Confirmation de Suppression de mission";
	private static final String LABEL_BUTTON_CONFIRM_DELETE_MISSION = "Supprimer la mission";
	private static final String LABEL_BUTTON_CANCEL_DELETE_MISSION = "NE PAS Supprimer la mission";

	/**
	 * Build the class MissionCollaboratorContent.java
	 * 
	 * @param missionForm
	 */

	public MissionCollaboratorContent(IColleagueService collaboratorService,
			ListMission listMission, MissionForm missionForm,
			Button btnAddMission, AddMissionPanel addMissionPanel,
			Panel listPanel, Label pageTitle, Button btnModifyMission,
			Button btnDeleteMission) {
		super();
		this.collaboratorService = collaboratorService;
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
		this.btnAddMission.setCaption(LABEL_BUTTON_ADD_MISSION);
		this.btnAddMission.addListener(this);

		this.btnModifyMission.setCaption(LABEL_BUTTON_MODIFY_MISSION);
		this.btnModifyMission.addListener(this);

		this.btnDeleteMission.setCaption(LABEL_BUTTON_DELETE_MISSION);
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

			this.addMissionPanel.setVisible(true);
			this.btnAddMission.setEnabled(false);
			this.listPanel.setVisible(false);
			this.missionForm.setCurrentSaveMode(MissionForm.SAVE_MODE_INSERT);
		} else {

			this.addMissionPanel.setVisible(false);
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


	/**
	 * Builder for all observers
	 * 
	 * @class IMissionView.java
	 */
	private void buildObersvators() {
		/*
		 * Observable : AddMissionPanel
		 */
		this.missionForm.addObservateur(new IMissionCollaboratorContent() {
			@Override
			public void updateListMission(ListMission listMission) {
				// MissionCollaboratorContent "receives" the new list updated
				MissionCollaboratorContent.this.listMission = listMission;
				// We remove the old listMission from the list panel
				MissionCollaboratorContent.this.listPanel.removeAllComponents();
				// We fill the listPanel with the new list
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
	 * Shows the form to add missions and disables the button "add".
	 * The form must be emptied.
	 */
	private void enableAddMissionPanel() {
		btnAddMission.setEnabled(false);
		addMissionPanel.setVisible(true);
	}

	/**
	 * Hides the form to add missions and enables the button "add"
	 */
	private void disableAddMissionPanel() {
		btnAddMission.setEnabled(true);
		addMissionPanel.setVisible(false);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		System.out.println("MissionCollaboratorContent.buttonClick()");
		Button button = event.getButton();
		// ADD NEW MISSION
		if (button == this.btnAddMission) {
			//On vide le contenu de MissionForm
			MissionCollaboratorContent.this.missionForm.emptyMissionForm();
			//On affiche le panel de saisie d'une nouvelle mission
			enableAddMissionPanel();
			// On indique que l'action courante est une création (pas une
			// modification de mission)
			this.missionForm.setCurrentSaveMode(MissionForm.SAVE_MODE_INSERT);
		}
		// MODIFY OR DELETE EXISTING MISSION
		else {
			selectedMission = (Mission) this.listMission.getValue();
			// CHECK USER SELECTED A MISSION IN THE TABLE
			if (null == selectedMission) {
				getWindow().showNotification(
						"Veuillez sélectionner une mission",
						Notification.TYPE_WARNING_MESSAGE);
			} else {
				// MODIFY MISSION
				if (button == this.btnModifyMission) {
					fillAddMissionPanelWithMission(selectedMission);
					// On indique que l'action courante est une modification de
					// mission (pas une création)
					this.missionForm
							.setCurrentSaveMode(MissionForm.SAVE_MODE_UPDATE);
				}
				// DELETE MISSION
				if (button == this.btnDeleteMission) {
					openConfirmWindow();
				}
			}
		}
	}

	/**
	 * Calls the CollaboratorService to delete the mission in Data Base. After
	 * the insert the list of missions in the table is updated with fresh data.
	 * 
	 * @param missionToDelete
	 */
	public void deleteMission(int idMissionToDelete) {
		try {
			Mission missionToDelete = Mission.builder().id(idMissionToDelete).build();
			int result = this.collaboratorService.deleteMission(missionToDelete);

			if (result != 0) {
				this.missionForm.setCurrentAction(MissionForm.ACTION_DELETE);

				// TODO centralize messages
				CUtils.showMessage("La mission a bien été supprimée", Message.INFO, getWindow());

				// creates a new list
				missionForm.refreshListMission();

			} else {
				// TODO : what to do?
				CUtils.showMessage("La mission N'A PAS été supprimée", Message.INFO, getWindow());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a subWindow to ask the user to confirm or cancel delete the
	 * selected mission
	 */
	private void openConfirmWindow() {
		windowConfirmDelete = new Window(LABEL_WINDOW_CONFIRM_DELETE);
		windowConfirmDelete.center();

		// Debut : Au lien de ces 2 lignes, les lignes suivantes
		// Button confirmDeleteButton = new
		// Button(LABEL_BUTTON_CONFIRM_DELETE_MISSION, this,
		// "confirmDeleteButtonClick");
		// Button cancelDeleteButton = new
		// Button(LABEL_BUTTON_CANCEL_DELETE_MISSION, this,
		// "cancelDeleteButtonClick");
		Button confirmDeleteButton = new Button(
				LABEL_BUTTON_CONFIRM_DELETE_MISSION);
		Button cancelDeleteButton = new Button(
				LABEL_BUTTON_CANCEL_DELETE_MISSION);
		confirmDeleteButton.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 8938851452280879463L;

			public void buttonClick(ClickEvent event) {
				confirmDeleteButtonClick(event);
			}
		});
		cancelDeleteButton.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 8312230721392985816L;

			public void buttonClick(ClickEvent event) {
				cancelDeleteButtonClick(event);
			}
		});
		// Fin : Au lien de ces 2 lignes, les lignes suivantes

		confirmDeleteButton.addListener(missionForm);
		cancelDeleteButton.addListener(missionForm);

		windowConfirmDelete.addComponent(confirmDeleteButton);
		windowConfirmDelete.addComponent(cancelDeleteButton);
		windowConfirmDelete.setReadOnly(true);
		windowConfirmDelete.setModal(true);

		getWindow().addWindow(windowConfirmDelete);
	}

	/**
	 * Handles user confirmation to delete the selected mission
	 * 
	 * @param event
	 */
	public void confirmDeleteButtonClick(Button.ClickEvent event) {
		getWindow().removeWindow(windowConfirmDelete);
		deleteMission((Integer) selectedMission.getId());
	}

	/**
	 * Handles user cancel delete the selected mission
	 * 
	 * @param event
	 */
	public void cancelDeleteButtonClick(Button.ClickEvent event) {
		getWindow().removeWindow(windowConfirmDelete);
	}

	/**
	 * Shows the form and fills it with the properties of the mission the user
	 * wants to update
	 * 
	 * @param missionId
	 *            : the id of the mission selected in the table
	 */
	private void fillAddMissionPanelWithMission(Mission mission) {

		enableAddMissionPanel();
		this.missionForm.fillMissionFormWithMission(mission);

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
	 * 
	 * @return the addMissionPanel
	 */
	public AddMissionPanel getAddMissionPanel() {
		return addMissionPanel;
	}

	/**
	 * Set the addMissionPanel value
	 * 
	 * @param addMissionPanel
	 *            the addMissionPanel to set
	 */
	public void setAddMissionPanel(AddMissionPanel addMissionPanel) {
		this.addMissionPanel = addMissionPanel;
	}

	/**
	 * Get the listPanel value
	 * 
	 * @return the listPanel
	 */
	public Panel getListPanel() {
		return listPanel;
	}

	/**
	 * Get the addMissionButton value
	 * 
	 * @return the addMissionButton
	 */
	public Button getAddMissionButton() {
		return btnAddMission;
	}

}
