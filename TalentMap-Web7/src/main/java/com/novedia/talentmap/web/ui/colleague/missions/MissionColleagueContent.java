package com.novedia.talentmap.web.ui.colleague.missions;

import java.util.Date;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.dto.MissionDTO;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.MissionFieldLabel;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class MissionColleagueContent extends VerticalLayout implements ClickListener, ValueChangeListener {
	
	// 3 constants to validate fields in MissionForm
    public static final int VALIDATION_FIELD_MISSING = 0;
    public static final int VALIDATION_INVALID_PERIOD = 1;
    public static final int VALIDATION_INVALID_SELECTION = 2;
    public static final int VALIDATION_VALID_FORM = 3;
    
    // 2 constants to identify if "save" is an insert or update
    public static final String SAVE_MODE_UPDATE = "UPDATE";
    public static final String SAVE_MODE_INSERT = "INSERT";
    
    private String currentSaveMode = "INSERT";
    
	private IColleagueService colleagueService;
	
	private Button addMissionButton;
	
    private Button editMissionButton;
    
    private Button deleteMissionButton;
    
    private Panel addMissionPanel;
    
    private Panel listMissionPanel;
    
    private ListMission listMission;
    
    private HorizontalLayout footerLayoutMissionButton;
    
    private Window windowConfirm;
    
    private Label confirmDeleteLabel;
    
    private HorizontalLayout confirmButtonContainer;
    
    private Button yesButton;
    
    private Button noButton;
    
    private MissionForm missionForm;
    
    private GridLayout formLayout;
    
	private Button saveButton;
	
	private Button cancelButton;
    
    public MissionColleagueContent(){
    	super();
    	setSpacing(true);
    	setWidth("800px");
    	addStyleName("missionColleagueView");
    }

    
    
    
    public VerticalLayout buildViewMissionColleagueContent() {
    	removeAllComponents();
    	addMissionButton.setCaption(MissionFieldLabel.ADD_MISSION_LABEL);
    	addMissionButton.addClickListener(this);
    	addMissionPanel.removeAllComponents();
    	missionForm.setMissionFormLayout(formLayout);
    	addMissionPanel.addComponent(missionForm.buildMissionFormView());
    	
    	HorizontalLayout buttonLayout = new HorizontalLayout();
		
		saveButton.setCaption("Save");
		saveButton.addClickListener(this);
		
		cancelButton.setCaption("Cancel");
		cancelButton.addClickListener(this);
		
		buttonLayout.addComponent(saveButton);
		
		buttonLayout.addComponent(cancelButton);
		
		addMissionPanel.addComponent(buttonLayout);
		
    	addComponent(addMissionButton);
    	addComponent(addMissionPanel);
    	buildListMissionPanel();
    	addComponent(listMissionPanel);
    	buildAddMissionPanel();
    	return this;
    }
    
    
    private void buildAddMissionPanel(){
    	if(listMission.size() > 0){
    		addMissionPanel.setVisible(false);
    		addMissionButton.setEnabled(true);
    	}
//    	else {
//    		addMissionPanel.setVisible(true);
//    		addMissionButton.setEnabled(false);
//		}
    }
    
	private void buildListMissionPanel(){
		listMission.fillAllColleagueMission();
    	listMission.addValueChangeListener(this);
    	listMission.addStyleName("table");
    	listMissionPanel.addStyleName("listMissionPanel");
    	if(listMission.size() > 0){
    		listMissionPanel.removeAllComponents();
    		listMissionPanel.addComponent(listMission);
    		buildFooterMissionButton();
    		listMissionPanel.addComponent(footerLayoutMissionButton);
    		listMissionPanel.setVisible(true);
    	} else {
    		listMissionPanel.setVisible(false);
    	}
    	
    }
    
    
    private void buildFooterMissionButton(){
    	footerLayoutMissionButton.removeAllComponents();
    	footerLayoutMissionButton.addStyleName("footerLayoutMissionButton");
    	editMissionButton.setCaption(MissionFieldLabel.EDIT_MISSION_LABEL);
    	editMissionButton.addClickListener(this);
    	deleteMissionButton.setCaption(MissionFieldLabel.DELETE_MISSION_LABEL);
    	deleteMissionButton.addClickListener(this);
    	enableButton(false);
    	footerLayoutMissionButton.setSpacing(true);
    	footerLayoutMissionButton.addComponent(editMissionButton);
    	footerLayoutMissionButton.addComponent(deleteMissionButton);
    }
    
    @Override
	public void buttonClick(ClickEvent event) {

    	if(event.getButton().equals(deleteMissionButton)){
    		buildConfirmWindow();
    		getUI().addWindow(windowConfirm);
    	}else if (event.getButton().equals(editMissionButton)) {
    		addMissionButton.setEnabled(false);
    		enableButton(false);
    		Mission selectedMission = (Mission) listMission.getValue();
    		
		} else if (event.getButton().equals(addMissionButton)) {
			addMissionPanel.setVisible(true);
    		addMissionButton.setEnabled(false);
    		enableButton(false);
		} else if (event.getButton().equals(yesButton)) {
			windowConfirm.close();
			Mission selectedMission = (Mission) listMission.getValue();
			int result = colleagueService.deleteMission(selectedMission);
			if(result != 0){
				buildViewMissionColleagueContent();
			} else {
				Notification.show(MissionFieldLabel.ERROR_DELETE_MISSION_LABEL, Type.ERROR_MESSAGE);
			}
		}else if (event.getButton().equals(noButton)) {
			windowConfirm.close();
		}
		else if(event.getButton().equals(cancelButton)){
				addMissionPanel.setVisible(false);
				addMissionButton.setEnabled(true);
		}else if(event.getButton().equals(saveButton)){
			addMissionButton.setEnabled(true);
			
			int formValidation = validatedMissionForm(missionForm.getMissionDTO());
    
			switch (formValidation) {
				case VALIDATION_FIELD_MISSING:
					Notification.show(Constants.PANEL_MISSING_FIELDS,Notification.Type.WARNING_MESSAGE);
					break;
				case VALIDATION_INVALID_PERIOD:
					Notification.show(Constants.MISSION_MSG_INVALID_PERIOD,Notification.Type.WARNING_MESSAGE);
					break;
				case VALIDATION_INVALID_SELECTION:
					Notification.show(Constants.MISSION_MSG_INVALID_SELECTION,Notification.Type.WARNING_MESSAGE);
					break;
				case VALIDATION_VALID_FORM:
					// Form's data are valid
					if (SAVE_MODE_INSERT == getCurrentSaveMode()) {
						Authentication authentication = TalentMapApplication.getCurrent().getAuthentication();
						missionForm.getMissionDTO().setColleagueId(authentication.getColleagueId());
						insertMission(missionForm.getMissionDTO());
						buildViewMissionColleagueContent();
//						addMissionPanel.setVisible(false);	
					}
					if (SAVE_MODE_UPDATE == getCurrentSaveMode()) {
						updateMission(missionForm.getMissionDTO());
					}
					break;
			}
		}
	}
    
    
    private void buildConfirmWindow(){
    	windowConfirm.removeAllComponents();
    	windowConfirm.setCaption(MissionFieldLabel.WINDOW_CONFIRM_DELETE_TITLE);
    	windowConfirm.center();
    	windowConfirm.setModal(true);
    	windowConfirm.setReadOnly(true);
    	confirmDeleteLabel.setCaption(MissionFieldLabel.CONFIRM_DELETE_MESSAGE_MISSION);
    	confirmButtonContainer.setSpacing(true);
    	yesButton.setCaption(MissionFieldLabel.CONFIRM_DELETE_MISSION_LABEL);
    	yesButton.addClickListener(this);
    	noButton.setCaption(MissionFieldLabel.CANCEL_DELETE_MISSION_LABEL);
    	noButton.addClickListener(this);
    	confirmButtonContainer.addComponent(yesButton);
    	confirmButtonContainer.addComponent(noButton);
    	windowConfirm.addComponent(confirmDeleteLabel);
    	windowConfirm.addComponent(confirmButtonContainer);
    }
    
    
	@Override
	public void valueChange(ValueChangeEvent event) {
    	Mission selectedMission = (Mission) listMission.getValue();
    	if(selectedMission != null){
    		enableButton(true);
    	}else{
    		enableButton(false);
    	}
	}
    
    
    private void enableButton(boolean state){
    	editMissionButton.setEnabled(state);
		deleteMissionButton.setEnabled(state);
    }
    
    /**
     * Checks all mandatory mission's fields are not null and period is valid
     * (begin date before end date)
     * 
     * @param mission
     * @return int : VALIDATION_FIELD_MISSING or VALIDATION_INVALID_PERIOD or
     *         VALIDATION_VALID_FORM
     */
    private int validatedMissionForm(MissionDTO mission) {

    	if (!isNotEmpty(mission.getClient()) || !isNotEmpty(mission.getTitle())
    			|| !isNotEmpty(mission.getPlace())
    			|| !isNotEmpty(mission.getClient())
    			|| !isNotEmpty(mission.getStartDate()))
    		return VALIDATION_FIELD_MISSING;
    	if (!isAValidPeriod(mission.getStartDate(), mission.getEndDate()))
    		return VALIDATION_INVALID_PERIOD;
    	if (!isAValidSelection(mission.getTools()))
    		return VALIDATION_INVALID_SELECTION;

    	return VALIDATION_VALID_FORM;
    }
    
    /**
     * Check null values
     * 
     * @param value
     * @return false if the parameter value is null
     */
    private boolean isNotEmpty(Object value) {
    	if (value == null || value.toString() == "") {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    /**
     * If end Date is specified, checks if startDate is before endDate. Without
     * endDate the method considers the period valid.
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    private boolean isAValidPeriod(Date startDate, Date endDate) {
    	if ((endDate == null)
    			|| ((endDate != null) && (endDate.after(startDate))))
    		return true;
    	return false;
    }
    
    /**
     * Check if selected tools are between 1 and 3
     * 
     * @param tools
     * @return false if the parameter value is not between 1 and 3
     */
    private boolean isAValidSelection(Set<Tool> tools) {
    	if (tools != null) {
    		if (tools.size() <= 3)
    			return true;
    	}

    	return false;
    }
    
    /**
     * Calls the CollaboratorService to insert the mission in Data Base. After
     * the insert the list of missions in the table is updated with fresh data.
     * 
     * @param missionToInsert
     */
    private void insertMission(MissionDTO missionToInsert) {
    	try {
    		int result = this.colleagueService.addMission(missionToInsert);
    		if (result != 0) {
//    			Notification.show(Constants.MISSION_MSG_DATA_INSERTED_OK,Notification.Type.WARNING_MESSAGE);
    		} else {
    			Notification.show(Constants.MISSION_MSG_DATA_INSERTED_KO,Notification.Type.ERROR_MESSAGE);
    		}

    	} catch (DataAccessException e) {
    		Notification.show(Constants.MISSION_MSG_DATA_INSERTED_ERROR,Notification.Type.WARNING_MESSAGE);
    	}

    }
    
    /**
     * Calls the CollaboratorService to update the mission in Data Base. After
     * the insert the list of missions in the table is updated with fresh data.
     * 
     * @param missionToUpdate
     */
    private void updateMission(MissionDTO missionToUpdate) {
	

    }
    
	public IColleagueService getColleagueService() {
		return colleagueService;
	}

	public void setColleagueService(IColleagueService colleagueService) {
		this.colleagueService = colleagueService;
	}

	public Button getAddMissionButton() {
		return addMissionButton;
	}

	public void setAddMissionButton(Button addMissionButton) {
		this.addMissionButton = addMissionButton;
	}

	public Button getEditMissionButton() {
		return editMissionButton;
	}

	public void setEditMissionButton(Button editMissionButton) {
		this.editMissionButton = editMissionButton;
	}

	public Button getDeleteMissionButton() {
		return deleteMissionButton;
	}

	public void setDeleteMissionButton(Button deleteMissionButton) {
		this.deleteMissionButton = deleteMissionButton;
	}

	public Panel getListMissionPanel() {
		return listMissionPanel;
	}

	public void setListMissionPanel(Panel listMissionPanel) {
		this.listMissionPanel = listMissionPanel;
	}

	public ListMission getListMission() {
		return listMission;
	}

	public void setListMission(ListMission listMission) {
		this.listMission = listMission;
	}

	public Panel getAddMissionPanel() {
		return addMissionPanel;
	}

	public void setAddMissionPanel(Panel addMissionPanel) {
		this.addMissionPanel = addMissionPanel;
	}

	public HorizontalLayout getFooterLayoutMissionButton() {
		return footerLayoutMissionButton;
	}

	public void setFooterLayoutMissionButton(
			HorizontalLayout footerLayoutMissionButton) {
		this.footerLayoutMissionButton = footerLayoutMissionButton;
	}

	public Window getWindowConfirm() {
		return windowConfirm;
	}

	public void setWindowConfirm(Window windowConfirm) {
		this.windowConfirm = windowConfirm;
	}

	public Label getConfirmDeleteLabel() {
		return confirmDeleteLabel;
	}

	public void setConfirmDeleteLabel(Label confirmDeleteLabel) {
		this.confirmDeleteLabel = confirmDeleteLabel;
	}

	public HorizontalLayout getConfirmButtonContainer() {
		return confirmButtonContainer;
	}

	public void setConfirmButtonContainer(HorizontalLayout confirmButtonContainer) {
		this.confirmButtonContainer = confirmButtonContainer;
	}

	public Button getYesButton() {
		return yesButton;
	}

	public void setYesButton(Button yesButton) {
		this.yesButton = yesButton;
	}

	public Button getNoButton() {
		return noButton;
	}

	public void setNoButton(Button noButton) {
		this.noButton = noButton;
	}

	public MissionForm getMissionForm() {
		return missionForm;
	}

	public void setMissionForm(MissionForm missionForm) {
		this.missionForm = missionForm;
	}

	public GridLayout getFormLayout() {
		return formLayout;
	}

	public void setFormLayout(GridLayout formLayout) {
		this.formLayout = formLayout;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(Button cancelButton) {
		this.cancelButton = cancelButton;
	}

	public String getCurrentSaveMode() {
		return currentSaveMode;
	}

	public void setCurrentSaveMode(String currentSaveMode) {
		this.currentSaveMode = currentSaveMode;
	}

}
