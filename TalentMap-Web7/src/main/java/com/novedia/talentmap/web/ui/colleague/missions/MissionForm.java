package com.novedia.talentmap.web.ui.colleague.missions;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.dto.MissionDTO;
import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IClientService;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.helpers.DataValidationHelper;
import com.novedia.talentmap.web.utils.CUtils;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.ConstantsDB;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;

@SuppressWarnings("serial")
public class MissionForm extends HorizontalLayout implements BlurListener {
	
	private IClientService clientService;

	private ISkillService skillService;
	
	/**
	 * Vaadin components
	 */
	private BeanFieldGroup<MissionDTO> binder;

	private GridLayout missionFormLayout;
	
	private GridLayout missionToolLayout;
	
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
	private TextArea commentField;
	
	@PropertyId(ComponentsId.TOOLS_ID)
	private TwinColSelect toolsField;
	
	private DataValidationHelper dataValidationHelper;
	private ResourceBundle resourceBundle;
	
	/**
	 * Default constructor
	 */
	public MissionForm() {
		super();
		setSpacing(true);
	}
	
	public MissionForm buildMissionForm(MissionDTO missionDto) {
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES , locale);
		dataValidationHelper = new DataValidationHelper();
		removeAllComponents();
		missionFormLayout.removeAllComponents();
		missionFormLayout.setSpacing(true);
		missionFormLayout.setColumns(2);
		missionFormLayout.setRows(2);
		
		missionToolLayout.removeAllComponents();
		missionToolLayout.setSpacing(true);
		missionToolLayout.setRows(1);
		missionToolLayout.setColumns(1);
		
		titleField.setCaption(resourceBundle.getString("form.mission.title.caption"));
		titleField.setId(ComponentsId.TITLE_ID);
		titleField.setRequired(true);
		titleField.setImmediate(true);
		titleField.setValidationVisible(true);
		titleField.setInputPrompt(resourceBundle.getString("form.mission.title.default.value"));
		titleField.setNullRepresentation("");
		titleField.setMaxLength(ConstantsDB.MISSION_TITLE_MAX_LENGTH);
		titleField.addBlurListener(this);
		missionFormLayout.addComponent(titleField);
		
		clientField.setCaption(resourceBundle.getString("form.mission.client.caption"));
		clientField.setId(ComponentsId.CLIENT_ID);
		clientField.setRequired(true);
		clientField.setRequiredError(resourceBundle.getString("form.mission.client.error.msg"));
		clientField.addValidator(new BeanValidator(MissionDTO.class, ComponentsId.CLIENT_ID));
		clientField.setImmediate(true);
		clientField.setValidationVisible(true);
		clientField.setInputPrompt(resourceBundle.getString("form.mission.client.default.value"));
		buildClientList();
		missionFormLayout.addComponent(clientField);
		
		placeField.setCaption(resourceBundle.getString("form.mission.lieu.caption"));
		placeField.setId(ComponentsId.PLACE_ID);
		placeField.setRequired(true);
		placeField.setImmediate(true);
		placeField.setValidationVisible(true);
		placeField.setInputPrompt(resourceBundle.getString("form.mission.lieu.default.value"));
		placeField.setNullRepresentation("");
		placeField.setMaxLength(ConstantsDB.MISSION_PLACE_MAX_LENGTH);
		placeField.addBlurListener(this);
		missionFormLayout.addComponent(placeField);
		
		startDateField.setCaption(resourceBundle.getString("form.mission.start.date.caption"));
		startDateField.setId(ComponentsId.START_DATE_ID);
		startDateField.setRequired(true);
		startDateField.setImmediate(true);
		startDateField.setValidationVisible(true);
		startDateField.setInputPrompt(Constants.DATE_FORMAT);
//		startDateField.addValueChangeListener(this);
		startDateField.addBlurListener(this);
		missionFormLayout.addComponent(startDateField);
		
		endDateField.setCaption(resourceBundle.getString("form.mission.end.date.caption"));
		endDateField.setId(ComponentsId.END_DATE_ID);
		endDateField.setInputPrompt(Constants.DATE_FORMAT);
		endDateField.setImmediate(true);
		endDateField.setValidationVisible(true);
		endDateField.setInputPrompt(Constants.DATE_FORMAT);
//		endDateField.addValueChangeListener(this);
		endDateField.addBlurListener(this);
		missionFormLayout.addComponent(endDateField);
		
		commentField.setCaption(resourceBundle.getString("form.mission.comment.caption"));
		commentField.setId(ComponentsId.COMMENT_ID);
		commentField.setInputPrompt(resourceBundle.getString("form.mission.comment.default.value"));
		commentField.setNullRepresentation("");
		commentField.setRequired(true);
		commentField.setMaxLength(ConstantsDB.MISSION_COMMENT_MAX_LENGTH);
		commentField.addBlurListener(this);
		missionFormLayout.addComponent(commentField);
		
		toolsField.setCaption(resourceBundle.getString("form.mission.tool.caption"));
		toolsField.setId(ComponentsId.TOOLS_ID);
		toolsField.setRequired(true);
		toolsField.setRequiredError(resourceBundle.getString("form.mission.tool.error.msg"));
		toolsField.addValidator(new BeanValidator(MissionDTO.class, ComponentsId.TOOLS_ID));
		toolsField.setImmediate(true);
		toolsField.setValidationVisible(true);
		toolsField.setNullSelectionAllowed(false);
		toolsField.setMultiSelect(true);
		toolsField.setLeftColumnCaption(resourceBundle.getString("form.mission.tool.msg.available"));
		toolsField.setRightColumnCaption(resourceBundle.getString("form.mission.tool.msg.selected"));
		buildToolsList();
		missionToolLayout.addComponent(toolsField);
		
		binder = new BeanFieldGroup<MissionDTO>(MissionDTO.class);
		binder.setItemDataSource(missionDto);
		binder.setBuffered(false);
		binder.bindMemberFields(this);
		
		addComponent(missionFormLayout);
		addComponent(missionToolLayout);
		
		return this;
		
	}
	

	private void buildToolsList() {
		BeanItemContainer<Tool> container2 = new BeanItemContainer<Tool>(Tool.class);
		List<Tool> allTools = skillService.getAllTools();
		for (Tool tool : allTools) {
			container2.addItem(tool);
		}
		toolsField.setContainerDataSource(container2);
		toolsField.setItemCaptionPropertyId("name");
	}

	private void buildClientList() {
		BeanItemContainer<Client> container = new BeanItemContainer<Client>(Client.class);
		for (Client client : clientService.getAllClients()) {
			container.addItem(client);
		}
		clientField.setContainerDataSource(container);
		clientField.setItemCaptionPropertyId("name");
	}

	public boolean validateStartDate() {
		return dataValidationHelper.validateFutureDateField(startDateField, true);
	}
	
	@Override
	public void blur(BlurEvent event) {
		Component p = event.getComponent();
		validate(p);
	}


	private void validate(Object p) {
		if (startDateField.equals(p)) {
			dataValidationHelper.validateFutureDateField(startDateField, true);
		} else if (endDateField.equals(p)) {
			dataValidationHelper.validateFutureDateField(endDateField, false);
		}  else if (titleField.equals(p)) {
			dataValidationHelper.validateMissionTitle(titleField);
		} else if (placeField.equals(p)) {
			dataValidationHelper.validateMissionPlace(placeField);
		} else if (commentField.equals(p)) {
			dataValidationHelper.validateMissionComment(commentField);
		} 
		
	}
	
	public BeanFieldGroup<MissionDTO> getBinder() {
		return binder;
	}

	public void setBinder(BeanFieldGroup<MissionDTO> binder) {
		this.binder = binder;
	}

	public GridLayout getMissionFormLayout() {
		return missionFormLayout;
	}

	public void setMissionFormLayout(GridLayout missionFormLayout) {
		this.missionFormLayout = missionFormLayout;
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

	public TextArea getCommentField() {
		return commentField;
	}

	public void setCommentField(TextArea commentField) {
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

	public GridLayout getMissionToolLayout() {
		return missionToolLayout;
	}

	public void setMissionToolLayout(GridLayout missionToolLayout) {
		this.missionToolLayout = missionToolLayout;
	}
	
	

}
