package com.novedia.talentmap.web.ui.colleague.missions;

import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.dto.MissionDTO;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.helpers.DataValidationHelper;
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class MissionColleagueContent extends VerticalLayout implements ClickListener, ValueChangeListener {
	
	private  int currentSaveMode = Constants.SAVE_MODE_INSERT;
    
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
    
	private Button saveButton;
	
	private Button cancelButton;
	
	private HorizontalLayout missionFormButtonLayout;
	
	private MissionDTO missionDTO;
	private DataValidationHelper dataValidationHelper;

	private ResourceBundle resourceBundle;

	/**
	 * Default constructor
	 */
	public MissionColleagueContent(){
    	super();
    	dataValidationHelper = new DataValidationHelper();
    	setSpacing(true);
    	addStyleName("missionColleagueView");
    	missionDTO = MissionDTO.builder().build();	
    }
    
	/**
	 * Build colleague's mission view
	 * @return VerticalLayout
	 */
    public VerticalLayout buildViewMissionColleagueContent() {
    	Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES , locale);
    	removeAllComponents();
    	listMissionPanel.addStyleName("contentPanel");
    	addMissionPanel.addStyleName("contentPanel");
    	addMissionButton.setCaption(resourceBundle.getString("button.add.mission.caption"));
    	addMissionButton.addClickListener(this);
    	addMissionButton.addStyleName("styleButton");
    	buildAddMissionPanel();
    	addComponent(addMissionButton);
    	addComponent(addMissionPanel);
    	buildListMissionPanel();
    	listMissionPanel.addStyleName("listMissionPanel");
    	addComponent(listMissionPanel);
    	disableddMissionPanel();
    	return this;
    }
    
    /**
     * Build layout Button of mission form
     */
    private void builMissionFormButton(){
    	missionFormButtonLayout.removeAllComponents();
    	missionFormButtonLayout.setSpacing(true);
    	missionFormButtonLayout.addStyleName("containerButton");
    	saveButton.setCaption(resourceBundle.getString("save.button.caption"));
		saveButton.addClickListener(this);
		saveButton.addStyleName("styleButton");
		
		cancelButton.setCaption(resourceBundle.getString("cancel.button.caption"));
		cancelButton.addClickListener(this);
		cancelButton.addStyleName("styleButton");
		
		missionFormButtonLayout.addComponent(saveButton);
		missionFormButtonLayout.addComponent(cancelButton);
    }
    
    /**
     * Disable Add Mission Panel
     */
    private void disableddMissionPanel(){
    	if(listMission.size() > 0){
    		addMissionPanel.setVisible(false);
    		addMissionButton.setEnabled(true);
    	}
    }
    
    private void buildAddMissionPanel(){
    	addMissionPanel.removeAllComponents();
		addMissionPanel.addComponent(missionForm.buildMissionForm(missionDTO));
		builMissionFormButton();
		addMissionPanel.addComponent(missionFormButtonLayout);
    }
    
    /**
     * Build list mission panel
     */
	private void buildListMissionPanel(){
		listMission.fillAllColleagueMission();
    	listMission.addValueChangeListener(this);
    	listMission.addStyleName("table");
    	listMissionPanel.addStyleName("listMissionPanel");
    	if(listMission.size() > 0){
    		listMissionPanel.removeAllComponents();
    		listMissionPanel.addComponent(listMission);
    		buildFooterListMission();
    		listMissionPanel.addComponent(footerLayoutMissionButton);
    		listMissionPanel.setVisible(true);
    		if(listMission.size() < Constants.NB_ROWS_DEFAULT){
    			listMission.setPageLength(listMission.size());
        	} else {
        		listMission.setPageLength(Constants.NB_ROWS_DEFAULT);
        	}
    	} else {
    		listMissionPanel.setVisible(false);
    	}
    	
    }
    
    private void buildFooterListMission(){
    	footerLayoutMissionButton.removeAllComponents();
    	footerLayoutMissionButton.addStyleName("footerLayoutMissionButton containerButton");
    	editMissionButton.setCaption(resourceBundle.getString("edit.button.caption"));
    	editMissionButton.addClickListener(this);
    	editMissionButton.addStyleName("styleButton");
    	deleteMissionButton.setCaption(resourceBundle.getString("delete.button.caption"));
    	deleteMissionButton.addClickListener(this);
    	deleteMissionButton.addStyleName("delBtn");
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
    	}
    	else if (event.getButton().equals(editMissionButton)) {
    		showEditMissionPanel();
		}
    	else if (event.getButton().equals(addMissionButton)) {
			showAddMissionPanel();
		} 
    	else if (event.getButton().equals(yesButton)) {
			windowConfirm.close();
			Mission selectedMission = (Mission) listMission.getValue();
			int result = colleagueService.deleteMission(selectedMission);
			if(result != 0){
				buildViewMissionColleagueContent();
			} else {
				Notification.show(resourceBundle.getString("mission.delete.error.msg"), Type.ERROR_MESSAGE);
			}
		} 
    	else if (event.getButton().equals(noButton)) {
			windowConfirm.close();
		} 
    	else if(event.getButton().equals(cancelButton)){
			addMissionButton.setEnabled(true);
			addMissionPanel.setVisible(false);
		} 
    	else if(event.getButton().equals(saveButton)){
			saveMission();
		}
	}
    
    private void showEditMissionPanel(){
    	currentSaveMode = Constants.SAVE_MODE_UPDATE;
		addMissionButton.setEnabled(false);
		addMissionPanel.setVisible(true);
		enableButton(false);
		Mission selectedMission = (Mission) listMission.getValue();
		missionDTO = colleagueService.createMissionDTO(selectedMission);
		buildAddMissionPanel();
    }
    
    private void showAddMissionPanel(){
    	currentSaveMode = Constants.SAVE_MODE_INSERT;
		addMissionButton.setEnabled(false);
		missionDTO = MissionDTO.builder().build();
		buildAddMissionPanel();
		addMissionPanel.setVisible(true);
		enableButton(false);
    }
    
    private void saveMission(){
    	int formValidation = checkMissionForm(missionDTO);
        
		switch (formValidation) {
			case Constants.VALIDATION_FIELD_MISSING: {
				Notification.show(resourceBundle.getString("missing.fields.msg"),Notification.Type.WARNING_MESSAGE);
				break;
			}
			case Constants.VALIDATION_INVALID_PERIOD:{
				Notification.show(resourceBundle.getString("date.period.invalid.msg"),Notification.Type.WARNING_MESSAGE);
				break;
			}
			case Constants.VALIDATION_INVALID_SELECTION:{
				Notification.show(resourceBundle.getString("tool.select.error.msg"),Notification.Type.WARNING_MESSAGE);
				break;
			}
			case Constants.VALIDATION_VALID_FORM:{
				Authentication authentication = TalentMapApplication.getCurrent().getAuthentication();
				missionDTO.setColleagueId(authentication.getColleagueId());
				if (currentSaveMode == Constants.SAVE_MODE_INSERT) {
					insertMission(missionDTO);
				} else {
					updateMission(missionDTO);
				}
				refreshListMission();
				break;
			}
			case Constants.VALIDATION_INVALID_START_DATE:{
				break;
			}
		}
    }
    
    /**
     * Refresh list mission
     */
    private void refreshListMission(){
    	addMissionButton.setEnabled(true);
		addMissionPanel.setVisible(false);
		listMissionPanel.removeAllComponents();
		buildListMissionPanel();
		listMissionPanel.addComponent(listMission);
		buildFooterListMission();
		listMissionPanel.addComponent(footerLayoutMissionButton);
		listMissionPanel.setVisible(true);
    }
    
    /**
     * Insert mission 
     * @param missionToInsert mission to insert
     */
    private void insertMission(MissionDTO missionToInsert) {
    	try {
    		int result = this.colleagueService.addMission(missionToInsert);
    		if (result == 0) {
    			Notification.show(resourceBundle.getString("error.create.mission.msg"),Notification.Type.ERROR_MESSAGE);
    		}
    	} catch (DataAccessException e) {
    		Notification.show(resourceBundle.getString("technical.exception"),Notification.Type.WARNING_MESSAGE);
    	}
    }
    
    /**
     * Update mission
     * @param missionToUpdate mission to update
     */
    private void updateMission(MissionDTO missionToUpdate) {
    	colleagueService.saveMission(missionToUpdate);
    }
    
    
    
    private void buildConfirmWindow(){
    	windowConfirm.removeAllComponents();
    	windowConfirm.setCaption(resourceBundle.getString("window.confirm.delete.title"));
    	windowConfirm.center();
    	windowConfirm.setModal(true);
    	windowConfirm.setReadOnly(true);
    	confirmDeleteLabel.setCaption(resourceBundle.getString("msg.confirm.delete.mission"));
    	confirmButtonContainer.setSpacing(true);
    	confirmButtonContainer.addStyleName("containerButton");
    	yesButton.setCaption(resourceBundle.getString("yes.confirm.button.caption"));
    	yesButton.addStyleName("styleButton");
    	yesButton.addClickListener(this);
    	noButton.setCaption(resourceBundle.getString("no.confirm.button.caption"));
    	noButton.addStyleName("styleButton");
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
    private int checkMissionForm(MissionDTO mission) {
    	if (!isNotEmpty(mission.getClient()) || !isNotEmpty(mission.getTitle())
    			|| !isNotEmpty(mission.getPlace())
    			|| !isNotEmpty(mission.getClient())
    			|| !isNotEmpty(mission.getTools())
    			|| !isNotEmpty(mission.getNotes())){
    		return Constants.VALIDATION_FIELD_MISSING;
    		
    	}
    	if(!missionForm.validateStartDate()) {
    		return Constants.VALIDATION_INVALID_START_DATE;
    	}
    	if (!isAValidPeriod(mission.getStartDate(), mission.getEndDate())){
    		return Constants.VALIDATION_INVALID_PERIOD;
    	}
    	
    	if (!isAValidSelection(mission.getTools())){
    		return Constants.VALIDATION_INVALID_SELECTION;
    	}
    	
    	return Constants.VALIDATION_VALID_FORM;
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

	public HorizontalLayout getMissionFormButtonLayout() {
		return missionFormButtonLayout;
	}

	public void setMissionFormButtonLayout(HorizontalLayout missionFormButtonLayout) {
		this.missionFormButtonLayout = missionFormButtonLayout;
	}

	
}
