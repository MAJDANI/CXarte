package com.novedia.talentmap.web.ui.colleague.missions;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.dto.MissionDTO;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IClientService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;

@SuppressWarnings("serial")
public class MissionForm extends FormLayout implements ClickListener{
	
	// 3 constants to validate fields in MissionForm
    public static final int VALIDATION_FIELD_MISSING = 0;
    public static final int VALIDATION_INVALID_PERIOD = 1;
    public static final int VALIDATION_INVALID_SELECTION = 2;
    public static final int VALIDATION_VALID_FORM = 3;
    
 // 2 constants to identify if "save" is an insert or update
    public static final String SAVE_MODE_UPDATE = "UPDATE";
    public static final String SAVE_MODE_INSERT = "INSERT";
    
    private MissionDTO missionDTO;
	
	private IClientService clientService;

	private ISkillService skillService;
	
	private IColleagueService collabService;
	
	private String currentSaveMode = "INSERT";
	
	/**
	 * Vaadin components
	 */
	private BeanFieldGroup<MissionDTO> binder;

	private GridLayout MissionFormLayout;
	
	private Button saveButton;
	
	private Button cancelButton;
	
	@PropertyId(ComponentsId.TITLE_ID)
	private TextField titleField;
	
	@PropertyId(ComponentsId.CLIENT_ID)
	private ComboBox clientField;
	
	@PropertyId(ComponentsId.PLACE_ID)
	private TextField placeField;
	
	@PropertyId(ComponentsId.START_DATE_ID)
	private PopupDateField startDateField;
	
	@PropertyId(ComponentsId.END_DATE_ID)
	private PopupDateField endDateField;
	
	@PropertyId(ComponentsId.COMMENT_ID)
	private TextField commentField;
	
	@PropertyId(ComponentsId.TOOLS_ID)
	private TwinColSelect toolsField;
	
	/**
	 * Default constructor
	 */
	public MissionForm() {
		super();
	}
	
	public MissionForm buildMissionFormView() {

		removeAllComponents();
		buildMain();
		return this;
	}

	private void buildMain() {
		try {
			buildLayout();
			buildMissionForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buildLayout() {
		MissionFormLayout.removeAllComponents();
		this.MissionFormLayout.setColumns(3);
		this.MissionFormLayout.setRows(3);
		
	}
	
	private void buildMissionForm() {
		removeAllComponents();
		
		titleField.setCaption(Constants.TITLE_CAPTION);
		titleField.setId(ComponentsId.TITLE_ID);
		titleField.setRequired(true);
		titleField.setRequiredError(Constants.GIVE_TITLE);
		titleField.addValidator(new BeanValidator(MissionDTO.class,
				ComponentsId.TITLE_ID));
		titleField.setImmediate(true);
		titleField.setValidationVisible(true);
		titleField.setInputPrompt(Constants.TYPE_TITLE);
		titleField.setNullRepresentation("");
		MissionFormLayout.addComponent(titleField);
		
		clientField.setCaption(Constants.CLIENT);
		clientField.setId(ComponentsId.CLIENT_ID);
		clientField.setRequired(true);
		clientField.setRequiredError(Constants.CHOOSE_CLIENT);
		clientField.addValidator(new BeanValidator(MissionDTO.class,
				ComponentsId.CLIENT_ID));
		clientField.setImmediate(true);
		clientField.setValidationVisible(true);
		clientField.setInputPrompt(Constants.SELECT_CLIENT);
		buildClientList();
		MissionFormLayout.addComponent(clientField);
		
		placeField.setCaption(Constants.PLACE);
		placeField.setId(ComponentsId.PLACE_ID);
		placeField.setRequired(true);
		placeField.setRequiredError(Constants.GIVE_PLACE);
		placeField.addValidator(new BeanValidator(MissionDTO.class,
				ComponentsId.PLACE_ID));
		placeField.setImmediate(true);
		placeField.setValidationVisible(true);
		placeField.setInputPrompt(Constants.TYPE_PLACE);
		placeField.setNullRepresentation("");
		MissionFormLayout.addComponent(placeField);
		
		startDateField.setCaption(Constants.START_DATE);
		startDateField.setId(ComponentsId.START_DATE_ID);
		startDateField.setRequired(true);
		startDateField.setRequiredError(Constants.GIVE_START_DATE);
		startDateField.addValidator(new BeanValidator(MissionDTO.class,
				ComponentsId.START_DATE_ID));
		startDateField.setImmediate(true);
		startDateField.setValidationVisible(true);
		startDateField.setInputPrompt(Constants.DATE_FORMAT);
		MissionFormLayout.addComponent(startDateField);
		
		endDateField.setCaption(Constants.END_DATE);
		endDateField.setId(ComponentsId.END_DATE_ID);
		endDateField.setInputPrompt(Constants.DATE_FORMAT);
		MissionFormLayout.addComponent(endDateField);
		
		commentField.setCaption(Constants.COMMENT);
		commentField.setId(ComponentsId.COMMENT_ID);
		commentField.setInputPrompt(Constants.TYPE_COMMENT);
		commentField.setNullRepresentation("");
		MissionFormLayout.addComponent(commentField);
		
		toolsField.setCaption(Constants.TOOLS);
		toolsField.setId(ComponentsId.TOOLS_ID);
		toolsField.setRequired(true);
		toolsField.setRequiredError(Constants.CHOOSE_TOOLS);
		toolsField.addValidator(new BeanValidator(MissionDTO.class,
				ComponentsId.TOOLS_ID));
		toolsField.setImmediate(true);
		toolsField.setValidationVisible(true);
		toolsField.setNullSelectionAllowed(false);
		toolsField.setMultiSelect(true);
		toolsField.setLeftColumnCaption("Available tools");
		toolsField.setRightColumnCaption("Selected tools");
		buildToolsList();
		MissionFormLayout.addComponent(toolsField);
		
		missionDTO = MissionDTO.builder().build();		
		binder = new BeanFieldGroup<MissionDTO>(
				MissionDTO.class);
		binder.setItemDataSource(missionDTO);
		binder.setBuffered(false);
		binder.bindMemberFields(this);
		
		addComponent(MissionFormLayout);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		
		saveButton.setCaption("Save");
		saveButton.addClickListener(this);
		
		cancelButton.setCaption("Cancel");
		cancelButton.addClickListener(this);
		
		buttonLayout.addComponent(saveButton);
		
		buttonLayout.addComponent(cancelButton);
		
		addComponent(buttonLayout);
		
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().equals(cancelButton)){
			this.setVisible(false);
		}else if(event.getButton().equals(saveButton)){

		    int formValidation = validatedMissionForm(missionDTO);
		    
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
		    			missionDTO.setColleagueId(authentication.getColleagueId());
		    			insertMission(missionDTO);
		    			this.setVisible(false);		    			
		    		}
		    		if (SAVE_MODE_UPDATE == getCurrentSaveMode()) {
		    			updateMission(missionDTO);
		    		}
		    	break;
		    }
		}
		
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
    		int result = this.collabService.addMission(missionToInsert);
    		if (result != 0) {
    			Notification.show(Constants.MISSION_MSG_DATA_INSERTED_OK,Notification.Type.WARNING_MESSAGE);
//    			refreshListMission();
    		} else {
    			Notification.show(Constants.MISSION_MSG_DATA_INSERTED_KO,Notification.Type.WARNING_MESSAGE);
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

	private void buildToolsList() {
		BeanItemContainer<Tool> container2 = new BeanItemContainer<Tool>(
			    Tool.class);

		List<Tool> allTools = skillService.getAllTools();

		for (Tool tool : allTools) {
			container2.addItem(tool);
		}
		
		toolsField.setContainerDataSource(container2);
		toolsField.setItemCaptionPropertyId("name");
		
	}

	private void buildClientList() {
		
		BeanItemContainer<Client> container = new BeanItemContainer<Client>(
			    Client.class);

		for (Client client : clientService.getAllClients()) {
			container.addItem(client);
		}
		clientField.setContainerDataSource(container);
		clientField.setItemCaptionPropertyId("name");
		
	}

	public BeanFieldGroup<MissionDTO> getBinder() {
		return binder;
	}

	public void setBinder(BeanFieldGroup<MissionDTO> binder) {
		this.binder = binder;
	}

	public GridLayout getMissionFormLayout() {
		return MissionFormLayout;
	}

	public void setMissionFormLayout(GridLayout missionFormLayout) {
		MissionFormLayout = missionFormLayout;
	}

	public TextField getTitleField() {
		return titleField;
	}

	public void setTitleField(TextField titleField) {
		this.titleField = titleField;
	}

	public ComboBox getClientField() {
		return clientField;
	}

	public void setClientField(ComboBox clientField) {
		this.clientField = clientField;
	}

	public TextField getPlaceField() {
		return placeField;
	}

	public void setPlaceField(TextField placeField) {
		this.placeField = placeField;
	}

	public PopupDateField getStartDateField() {
		return startDateField;
	}

	public void setStartDateField(PopupDateField startDateField) {
		this.startDateField = startDateField;
	}

	public PopupDateField getEndDateField() {
		return endDateField;
	}

	public void setEndDateField(PopupDateField endDateField) {
		this.endDateField = endDateField;
	}

	public TextField getCommentField() {
		return commentField;
	}

	public void setCommentField(TextField commentField) {
		this.commentField = commentField;
	}

	public TwinColSelect getToolsField() {
		return toolsField;
	}

	public void setToolsField(TwinColSelect toolsField) {
		this.toolsField = toolsField;
	}

	public IClientService getClientService() {
		return clientService;
	}

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	public ISkillService getSkillService() {
		return skillService;
	}

	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
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

	public IColleagueService getCollabService() {
		return collabService;
	}

	public void setCollabService(IColleagueService collabService) {
		this.collabService = collabService;
	}

}
