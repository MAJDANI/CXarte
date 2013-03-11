package com.novedia.talentmap.web.ui.formFactory;

import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.services.IClientService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class MissionFormFieldFactory implements FormFieldFactory {

	public static final String FIELD_MUST_BE_FILLED = "Le champ est obligatoire";
	public static final int MIN_LENGTH_STRING = 1;
	public static final int MAX_LENGTH_STRING = 100;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8213744445391942619L;
	
	private IClientService clientService;
	
	/**
	 * 
	 * @param  client Service
	 */
	public MissionFormFieldFactory(IClientService clientService){
		this.clientService = clientService;
	}

	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		boolean allowNull = false;
		
		for (int i = 0; i < ConstantsEnglish.FIELD_ORDER_MISSION.length; i++) {
			
			if(propertyId.equals(ConstantsEnglish.FIELD_ORDER_MISSION[i])){
				
				if(propertyId.equals(ConstantsEnglish.FIELD_MISSION_NAME) || propertyId.equals(ConstantsEnglish.FIELD_MISSION_PLACE)){
				
					TextField field = new TextField((String) ConstantsEnglish.NAME_FIELD_MISSION[i]+" : ");
					field.setNullRepresentation(ConstantsEnglish.FIELD_NULL_REPRESENTATION);
					
					field.setRequired(true);
					field.setRequiredError(ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART1 + ConstantsEnglish.NAME_FIELD_MISSION[i] + ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART2);
					field.setValidationVisible(true);
					
					if(propertyId.equals(ConstantsEnglish.FIELD_MISSION_NAME)){
						//Putting a condition
						field.setMaxLength(ConstantsEnglish.MISSION_TITLE_MAX_LENGTH);
					}

					if(propertyId.equals(ConstantsEnglish.FIELD_MISSION_PLACE)){
						//Putting a condition
						field.setMaxLength(ConstantsEnglish.MISSION_PLACE_MAX_LENGTH);
					}
					
					return field;
				}
				
				if(propertyId.equals(ConstantsEnglish.FIELD_MISSION_CLIENT)){
					
					BeanItemContainer<Client> container =
					        new BeanItemContainer<Client>(Client.class);
				
					for(Client client : clientService.getAllClients()){
						container.addItem(client);
					}
					
					Select clientSelect = new Select("Client : ",container); 
					clientSelect.setItemCaptionMode(
				            Select.ITEM_CAPTION_MODE_PROPERTY);
					clientSelect.setItemCaptionPropertyId("name");
					clientSelect.setRequired(true);
					clientSelect.setNullSelectionAllowed(false);
					clientSelect.setImmediate(true);

					return clientSelect;
				}
				
				if (propertyId.equals(ConstantsEnglish.FIELD_MISSION_START_DATE) || propertyId.equals(ConstantsEnglish.FIELD_MISSION_END_DATE)) {

					PopupDateField datefield = new PopupDateField();
					datefield.setDateFormat("dd/MM/yyyy");
					datefield.setCaption((String) ConstantsEnglish.NAME_FIELD_MISSION[i]+ " : ");
					
					datefield.setRequiredError(ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART1 + ConstantsEnglish.NAME_FIELD_MISSION[i] + ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART2);
					datefield.setValidationVisible(true);

					if (propertyId.equals(ConstantsEnglish.FIELD_MISSION_START_DATE)){
						datefield.setRequired(true);
						datefield.setStyleName("mission-start-date");
					}else if (propertyId.equals(ConstantsEnglish.FIELD_MISSION_END_DATE)){
						datefield.setStyleName("mission-end-date");
					}
					
					return datefield;
				}
				
				if(propertyId.equals(ConstantsEnglish.FIELD_MISSION_NOTES)){
					
					TextArea textarea = new TextArea((String) ConstantsEnglish.NAME_FIELD_MISSION[i]+" : ");
					textarea.setNullRepresentation("");
					
					textarea.setColumns(25);
					textarea.setRows(5);
					
					return textarea;
				}
				
			}
			
		}
		
		return null;
	}

		 
}
