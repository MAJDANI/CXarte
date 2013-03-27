package com.novedia.talentmap.web.ui.profile.mission;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.novedia.talentmap.model.dto.MissionDto;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.IMissionCollaboratorContent;
import com.novedia.talentmap.web.util.Message;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class MissionCollaboratorContent extends VerticalLayout implements
		ClickListener {


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

	private HorizontalLayout hLayListMissionButtons;
	private Button btnAddMission;
	private Button btnModifyMission;
	private Button btnDeleteMission;
	
	/**
	 * SubWindow to confirm or cancel a delete mission action
	 */
	private Window windowConfirmDelete;

	/**
	 * The mission selected by the user, in order to modify or delete it.
	 */
	private Mission selectedMission;
	
	private Authentication authentication;
	
	
	/**
	 * Default constructor
	 */
	public MissionCollaboratorContent(){
		super();
	}
	
	/**
	 * Build the view of mission's colleague
	 * @return
	 */
	public MissionCollaboratorContent buildViewMissionColleagueContent(){
		removeAllComponents();
		listMission.setAuthentication(getAuthentication());
		missionForm.setAuthentication(getAuthentication());
		
		addMissionPanel = addMissionPanel.buildAddMissionPanel();
		buildMain();
		buildObersvators();
		return this;
	}
	

	/**
	 * Build the class MissionCollaboratorContent.java
	 * 
	 * @param missionForm
	 */

//	public MissionCollaboratorContent(IColleagueService collaboratorService,
//			ListMission listMission, MissionForm missionForm,
//			Button btnAddMission, AddMissionPanel addMissionPanel,
//			Panel listPanel, Label pageTitle, Button btnModifyMission,
//			Button btnDeleteMission) {
//		super();
//		this.collaboratorService = collaboratorService;
//		this.listMission = listMission;
//		this.missionForm = missionForm;
//		this.btnAddMission = btnAddMission;
//		this.addMissionPanel = addMissionPanel;
//		this.listPanel = listPanel;
//		this.pageTitle = pageTitle;
//		this.btnModifyMission = btnModifyMission;
//		this.btnDeleteMission = btnDeleteMission;
//
//		buildMain();
//		buildObersvators();
//	}

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
		this.missionForm.setImmediate(true);

	}

	/**
	 * The title builder
	 * 
	 * @class MissionCollaboratorContent.java
	 */
	public void buildTitle() {
		
		Label pageTitle = new Label();
		pageTitle.setCaption(ConstantsEnglish.PAGE_TITLE);
		pageTitle.addStyleName(TalentMapCSS.H2);
		addComponent(pageTitle);
	}

	/**
	 * The button builder
	 * 
	 * @class MissionCollaboratorContent.java
	 */
	public void buildButton() {
		this.btnAddMission.setCaption(ConstantsEnglish.LABEL_BUTTON_ADD_MISSION);
		this.btnAddMission.addListener(this);

		this.btnModifyMission.setCaption(ConstantsEnglish.LABEL_BUTTON_MODIFY_MISSION);
		this.btnModifyMission.addListener(this);

		this.btnDeleteMission.setCaption(ConstantsEnglish.LABEL_BUTTON_DELETE_MISSION);
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
		enableButton(true);
		
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
		if(listMission.size() > 0){
			listPanel.removeAllComponents();
			this.listPanel.addComponent(this.listMission.buildAllColleagueMission());
			
			this.hLayListMissionButtons = new HorizontalLayout();
			this.hLayListMissionButtons.addComponent(btnModifyMission);
			this.hLayListMissionButtons.addComponent(btnDeleteMission);
			this.hLayListMissionButtons.setSpacing(true);
			hLayListMissionButtons.setMargin(true);
			hLayListMissionButtons.addStyleName("footerButton");
			
			this.listPanel.addComponent(this.hLayListMissionButtons);
			addComponent(this.listPanel);
			listPanel.setVisible(true);
		} else{
			listPanel.setVisible(false);
		}
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
				enableButton(true);
			}

			@Override
			public void cancelAddMission() {
				MissionCollaboratorContent.this.missionForm.emptyMissionForm();
				disableAddMissionPanel();
				enableButton(true);
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

	
	private void enableButton(boolean enable){
		btnAddMission.setEnabled(enable);
		btnModifyMission.setEnabled(enable);
		btnDeleteMission.setEnabled(enable);
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
		//System.out.println("MissionCollaboratorContent.buttonClick()");
		Button button = event.getButton();
		// ADD NEW MISSION
		if (button == this.btnAddMission) {
			//On vide le contenu de MissionForm
			MissionCollaboratorContent.this.missionForm.emptyMissionForm();
			//On affiche le panel de saisie d'une nouvelle mission
			enableAddMissionPanel();
			enableButton(false);
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
						"Please select a mission",
						Notification.TYPE_WARNING_MESSAGE);
			} else {
				MissionDto selectedMissionDTO = new MissionDto();
				
				// Recopie des attributs "simples"
				Set<Tool> toolsSet = new HashSet<Tool>();
				List<Tool> tools = selectedMission.getTools();
				
				if(tools.size()>0)
				{
					for(Tool t : tools)
					{
						toolsSet.add(t);
					}
					
					selectedMissionDTO.setTools(toolsSet);
				}

				selectedMissionDTO.setId(selectedMission.getId());
				selectedMissionDTO.setClient(selectedMission.getClient());
				selectedMissionDTO.setStartDate(selectedMission.getStartDate());
				selectedMissionDTO.setEndDate(selectedMission.getEndDate());
				selectedMissionDTO.setTitle(selectedMission.getTitle());
				selectedMissionDTO.setPlace(selectedMission.getPlace());
				selectedMissionDTO.setNotes(selectedMission.getNotes());
				selectedMissionDTO.setColleagueId(selectedMission.getColleagueId());
				selectedMissionDTO.setTools(toolsSet);

				// MODIFY MISSION
				if (button == this.btnModifyMission) {
					//missionForm = missionForm.buildMissionFormColleague();
					fillAddMissionPanelWithMission(selectedMissionDTO);
					enableButton(false);
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
				CUtils.showMessage("The mission has been deleted", Message.INFO, getWindow());
				
				// creates a new list
				missionForm.refreshListMission();

			} else {
				// TODO : what to do?
				CUtils.showMessage("The mission was not deleted", Message.INFO, getWindow());
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
		windowConfirmDelete = new Window(ConstantsEnglish.LABEL_WINDOW_CONFIRM_DELETE);
		windowConfirmDelete.center();

		Button yesButton = new Button(ConstantsEnglish.LABEL_BUTTON_CONFIRM_DELETE_MISSION);
		Button noButton = new Button(ConstantsEnglish.LABEL_BUTTON_CANCEL_DELETE_MISSION);
		yesButton.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 8938851452280879463L;
			public void buttonClick(ClickEvent event) {
				confirmDeleteButtonClick(event);
			}
		});
		
		noButton.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 8312230721392985816L;
			public void buttonClick(ClickEvent event) {
				cancelDeleteButtonClick(event);
			}
		});
		
		windowConfirmDelete.addComponent(new Label(ConstantsEnglish.CONFIRM_DELETE_MESSAGE_MISSION));

		HorizontalLayout containerButton = new HorizontalLayout();
		containerButton.setSpacing(true);
		containerButton.setMargin(true);
		containerButton.addStyleName("footerButton");
		containerButton.addComponent(yesButton);
		containerButton.addComponent(noButton);
		windowConfirmDelete.addComponent(containerButton);
		
		windowConfirmDelete.setWidth("30%");
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
	private void fillAddMissionPanelWithMission(MissionDto missionDto) {

		enableAddMissionPanel();
		this.missionForm.fillMissionFormWithMission(missionDto);

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

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public IColleagueService getCollaboratorService() {
		return collaboratorService;
	}

	public void setCollaboratorService(IColleagueService collaboratorService) {
		this.collaboratorService = collaboratorService;
	}

	public MissionForm getMissionForm() {
		return missionForm;
	}

	public void setMissionForm(MissionForm missionForm) {
		this.missionForm = missionForm;
	}

	public HorizontalLayout gethLayListMissionButtons() {
		return hLayListMissionButtons;
	}

	public void sethLayListMissionButtons(HorizontalLayout hLayListMissionButtons) {
		this.hLayListMissionButtons = hLayListMissionButtons;
	}

	public Button getBtnAddMission() {
		return btnAddMission;
	}

	public void setBtnAddMission(Button btnAddMission) {
		this.btnAddMission = btnAddMission;
	}

	public Button getBtnModifyMission() {
		return btnModifyMission;
	}

	public void setBtnModifyMission(Button btnModifyMission) {
		this.btnModifyMission = btnModifyMission;
	}

	public Button getBtnDeleteMission() {
		return btnDeleteMission;
	}

	public void setBtnDeleteMission(Button btnDeleteMission) {
		this.btnDeleteMission = btnDeleteMission;
	}

	public Window getWindowConfirmDelete() {
		return windowConfirmDelete;
	}

	public void setWindowConfirmDelete(Window windowConfirmDelete) {
		this.windowConfirmDelete = windowConfirmDelete;
	}

	public Mission getSelectedMission() {
		return selectedMission;
	}

	public void setSelectedMission(Mission selectedMission) {
		this.selectedMission = selectedMission;
	}

	
	
	
}
