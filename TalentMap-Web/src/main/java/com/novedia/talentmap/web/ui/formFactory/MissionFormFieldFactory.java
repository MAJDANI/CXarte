package com.novedia.talentmap.web.ui.formFactory;

import java.util.List;
import java.util.Set;

import com.novedia.talentmap.model.dto.MissionDTO;
import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IClientService;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Select;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;

public class MissionFormFieldFactory implements FormFieldFactory {

    public static final String FIELD_MUST_BE_FILLED = "Le champ est obligatoire";
    public static final int MIN_LENGTH_STRING = 1;
    public static final int MAX_LENGTH_STRING = 100;

    /**
	 * 
	 */
    private static final long serialVersionUID = -8213744445391942619L;

    private IClientService clientService;
    private ISkillService skillService;
    private boolean isLastMission;
    private MissionDTO missionDTO;

    /**
     * 
     * @param clientService
     *            skillService mission isLastMission
     */
    public MissionFormFieldFactory(IClientService clientService,
	    ISkillService skillService, MissionDTO missionDTO,
	    boolean isLastMission) {
	this.clientService = clientService;
	this.skillService = skillService;
	this.missionDTO = missionDTO;
	this.isLastMission = isLastMission;

    }

    /**
     * 
     * @param client
     *            Service skillService isLastMission
     */
    public MissionFormFieldFactory(IClientService clientService,
	    ISkillService skillService, boolean isLastMission) {
	this.clientService = clientService;
	this.skillService = skillService;
	this.isLastMission = isLastMission;

    }

    /**
     * 
     * @param client
     *            Service
     */
    public MissionFormFieldFactory(IClientService clientService) {
	this.clientService = clientService;
    }

    @Override
    public Field createField(Item item, Object propertyId, Component uiContext) {

	for (int i = 0; i < ConstantsEnglish.FIELD_ORDER_MISSION.length; i++) {

	    if (propertyId.equals(ConstantsEnglish.FIELD_ORDER_MISSION[i])) {

		if (propertyId.equals(ConstantsEnglish.FIELD_MISSION_NAME)
			|| propertyId
				.equals(ConstantsEnglish.FIELD_MISSION_PLACE)) {

		    TextField field = new TextField(
			    (String) ConstantsEnglish.NAME_FIELD_MISSION[i]
				    + " : ");
		    field.setNullRepresentation(ConstantsEnglish.FIELD_NULL_REPRESENTATION);

		    field.setRequired(true);
		    field.setRequiredError(ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART1
			    + ConstantsEnglish.NAME_FIELD_MISSION[i]
			    + ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART2);
		    field.setValidationVisible(true);

		    if (propertyId.equals(ConstantsEnglish.FIELD_MISSION_NAME)) {
			// Putting a condition
			field.setMaxLength(ConstantsEnglish.MISSION_TITLE_MAX_LENGTH);
		    }

		    if (propertyId.equals(ConstantsEnglish.FIELD_MISSION_PLACE)) {
			// Putting a condition
			field.setMaxLength(ConstantsEnglish.MISSION_PLACE_MAX_LENGTH);
		    }

		    return field;
		}

		if (propertyId.equals(ConstantsEnglish.FIELD_MISSION_CLIENT)) {

		    BeanItemContainer<Client> container = new BeanItemContainer<Client>(
			    Client.class);

		    for (Client client : clientService.getAllClients()) {
			container.addItem(client);
		    }

		    Select clientSelect = new Select("Client : ", container);
		    clientSelect
			    .setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
		    clientSelect.setItemCaptionPropertyId("name");
		    clientSelect.setRequired(true);
		    clientSelect.setNullSelectionAllowed(false);
		    clientSelect.setImmediate(true);

		    return clientSelect;
		}

		if (propertyId
			.equals(ConstantsEnglish.FIELD_MISSION_START_DATE)
			|| propertyId
				.equals(ConstantsEnglish.FIELD_MISSION_END_DATE)) {

		    PopupDateField datefield = new PopupDateField();
		    datefield.setDateFormat("dd/MM/yyyy");
		    datefield
			    .setCaption((String) ConstantsEnglish.NAME_FIELD_MISSION[i]
				    + " : ");

		    datefield
			    .setRequiredError(ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART1
				    + ConstantsEnglish.NAME_FIELD_MISSION[i]
				    + ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART2);
		    datefield.setValidationVisible(true);

		    if (propertyId
			    .equals(ConstantsEnglish.FIELD_MISSION_START_DATE)) {
			datefield.setRequired(true);
			datefield.setStyleName("mission-start-date");
		    } else if (propertyId
			    .equals(ConstantsEnglish.FIELD_MISSION_END_DATE)) {
			datefield.setStyleName("mission-end-date");
		    }

		    return datefield;

		}

		if (propertyId.equals(ConstantsEnglish.FIELD_MISSION_NOTES)) {

		    TextArea textarea = new TextArea(
			    (String) ConstantsEnglish.NAME_FIELD_MISSION[i]
				    + " : ");
		    textarea.setNullRepresentation("");
		    textarea.setColumns(20);
		    textarea.setRows(5);

		    return textarea;

		}
		if (propertyId.equals(ConstantsEnglish.FIELD_MISSION_TOOLS)
			&& !isLastMission) {

		    BeanItemContainer<Tool> container2 = new BeanItemContainer<Tool>(
			    Tool.class);

		    List<Tool> allTools = skillService.getAllTools();

		    for (Tool tool : allTools) {
			container2.addItem(tool);
		    }
		    TwinColSelect l = new TwinColSelect(
			    "Tools (choose 1 to 3 tools) : ", container2);
		    l.setDescription("Choose 1 to 3 tools");
		    l.setRows(3);
		    l.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
		    l.setItemCaptionPropertyId("name");

		    l.setRequired(true);
		    l.setNullSelectionAllowed(false);
		    l.setImmediate(true);
		    l.setMultiSelect(true);

		    l.setLeftColumnCaption("Available tools");
		    l.setRightColumnCaption("Selected tools");
		    l.setWidth("350px");

		    return l;

		}
		if (propertyId.equals(ConstantsEnglish.FIELD_MISSION_TOOLS)
			&& isLastMission) {

		    Set<Tool> allTools = missionDTO.getTools();

		    int j = 0;

		    Table table = new Table("Tools :");
		    table.addContainerProperty("Tool N°", Integer.class, null);
		    table.addContainerProperty("Tool Name", String.class, null);
		    table.setSizeFull();
		    table.setSelectable(true);
		    table.setMultiSelect(true);
		    table.setImmediate(true);

		    if (allTools != null) {
			for (Tool tool : allTools) {
			    table.addItem(
				    new Object[] { j + 1, tool.getName() },
				    new Integer(j));
			    j++;
			}
		    }

		    return table;

		}

	    }

	}

	return null;
    }

    public MissionDTO getMission() {
	return missionDTO;
    }

    public void setMission(MissionDTO missionDTO) {
	this.missionDTO = missionDTO;
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

    public void setSkillService(ISkillService skillservice) {
	this.skillService = skillservice;
    }

    public boolean isLastMission() {
	return isLastMission;
    }

    public void setLastMission(boolean isLastMission) {
	this.isLastMission = isLastMission;
    }

}
