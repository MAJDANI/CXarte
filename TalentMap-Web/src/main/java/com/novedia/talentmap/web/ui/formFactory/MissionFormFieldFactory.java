package com.novedia.talentmap.web.ui.formFactory;

import com.novedia.talentmap.web.ui.profile.mission.MissionForm;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class MissionFormFieldFactory implements FormFieldFactory {

	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		
		for (int i = 0; i < MissionForm.FIELD_ORDER_MISSION.length; i++) {
			
			if(propertyId.equals(MissionForm.FIELD_ORDER_MISSION[i])){
				
				if(!propertyId.equals("notes") && !propertyId.equals("start_date") && !propertyId.equals("end_date")){
				
					TextField field = new TextField((String) MissionForm.NAME_FIELD_MISSION[i]+" : ");
					field.setNullRepresentation("");
				
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
				
				if (propertyId.equals("start_date") || propertyId.equals("end_date")) {

					PopupDateField datefield = new PopupDateField();
					datefield.setDateFormat("dd/MM/yyyy");
					datefield.setCaption((String) MissionForm.NAME_FIELD_MISSION[i]+ " : ");
					
					
					if (propertyId.equals("start_date")){
						
						datefield.setStyleName("mission-start-date");
					}else if (propertyId.equals("end_date")){
						
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
