package com.novedia.talentmap.web.ui.colleague.missions;

import java.util.List;

import com.novedia.talentmap.model.dto.MissionDTO;
import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IClientService;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;

@SuppressWarnings("serial")
public class MissionForm extends FormLayout implements ClickListener{
	
    private MissionDTO missionDTO;
	
	private IClientService clientService;

	private ISkillService skillService;
	
	/**
	 * Vaadin components
	 */
	private BeanFieldGroup<MissionDTO> binder;

	private GridLayout MissionFormLayout;
	
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
		
//		saveButton.setCaption("Save");
//		saveButton.addClickListener(this);
//		
//		cancelButton.setCaption("Cancel");
//		cancelButton.addClickListener(this);
//		
//		buttonLayout.addComponent(saveButton);
//		
//		buttonLayout.addComponent(cancelButton);
//		
//		addComponent(buttonLayout);
		
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
//		if(event.getButton().equals(cancelButton)){
////			this.setVisible(false);
//		}else if(event.getButton().equals(saveButton)){
//
//		    int formValidation = validatedMissionForm(missionDTO);
//		    
//		    switch (formValidation) {
//		    	case VALIDATION_FIELD_MISSING:
//		    		Notification.show(Constants.PANEL_MISSING_FIELDS,Notification.Type.WARNING_MESSAGE);
//		    		break;
//		    	case VALIDATION_INVALID_PERIOD:
//		    		Notification.show(Constants.MISSION_MSG_INVALID_PERIOD,Notification.Type.WARNING_MESSAGE);
//		    		break;
//		    	case VALIDATION_INVALID_SELECTION:
//		    		Notification.show(Constants.MISSION_MSG_INVALID_SELECTION,Notification.Type.WARNING_MESSAGE);
//				break;
//		    	case VALIDATION_VALID_FORM:
//		    		// Form's data are valid
//		    		if (SAVE_MODE_INSERT == getCurrentSaveMode()) {
//		    			Authentication authentication = TalentMapApplication.getCurrent().getAuthentication();
//		    			missionDTO.setColleagueId(authentication.getColleagueId());
//		    			insertMission(missionDTO);
//		    			this.setVisible(false);		    			
//		    		}
//		    		if (SAVE_MODE_UPDATE == getCurrentSaveMode()) {
//		    			updateMission(missionDTO);
//		    		}
//		    	break;
//		    }
//		}
		
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

	public MissionDTO getMissionDTO() {
		return missionDTO;
	}

	public void setMissionDTO(MissionDTO missionDTO) {
		this.missionDTO = missionDTO;
	}

}
