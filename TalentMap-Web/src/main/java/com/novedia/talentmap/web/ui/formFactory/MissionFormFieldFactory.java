package com.novedia.talentmap.web.ui.formFactory;

import com.novedia.talentmap.web.ui.profile.mission.MissionForm;
import com.novedia.talentmap.web.ui.profile.mission.MissionValidator;
import com.vaadin.data.Item;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.PopupDateField;
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

	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		boolean allowNull = false;
		
		StringLengthValidator stringLengthValidator = new StringLengthValidator(
				FIELD_MUST_BE_FILLED, MIN_LENGTH_STRING,
                MAX_LENGTH_STRING, allowNull);
		
		for (int i = 0; i < MissionForm.FIELD_ORDER_MISSION.length; i++) {
			
			if(propertyId.equals(MissionForm.FIELD_ORDER_MISSION[i])){
				
				if(!propertyId.equals("notes") && !propertyId.equals("startDate") && !propertyId.equals("endDate")){
				
					TextField field = new TextField((String) MissionForm.NAME_FIELD_MISSION[i]+" : ");
					field.setNullRepresentation("");
					
					field.setRequired(true);
					field.setComponentError(null);
					
					if(propertyId.equals("name")){
						//Putting a condition
					}
					
					if(propertyId.equals("client")){
						field.setStyleName("mission-client");
					}
					
					if(propertyId.equals("place")){
						//Putting a condition
					}
					
					return field;
				}
				
				if (propertyId.equals("startDate") || propertyId.equals("endDate")) {

					PopupDateField datefield = new PopupDateField();
					datefield.setDateFormat("dd/MM/yyyy");
					datefield.setCaption((String) MissionForm.NAME_FIELD_MISSION[i]+ " : ");
					
					//TODO remmettre?
					datefield.setRequired(true);
					
					if (propertyId.equals("startDate")){
						
						datefield.setStyleName("mission-start-date");
					}else if (propertyId.equals("endDate")){
						
						datefield.setStyleName("mission-end-date");
					}
					
					return datefield;
				}
				
				if(propertyId.equals("notes")){
					
					TextArea textarea = new TextArea((String) MissionForm.NAME_FIELD_MISSION[i]+" : ");
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
