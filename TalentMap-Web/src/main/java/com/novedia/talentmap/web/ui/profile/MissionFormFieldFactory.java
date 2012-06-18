package com.novedia.talentmap.web.ui.profile;

import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

public class MissionFormFieldFactory implements FormFieldFactory {

	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		
		for (int i = 0; i < CollaboratorForm.FIELD_ORDER_MISSION.length; i++) {
			
			if(propertyId.equals(CollaboratorForm.FIELD_ORDER_MISSION[i])){
				
				if (!propertyId.equals("client")) {

					PopupDateField datefield = new PopupDateField();
					datefield.setDateFormat("dd/MM/yyyy");
					datefield.setCaption((String) CollaboratorForm.NAME_FIELD_MISSION[i]+ " : ");
					
					
					if (propertyId.equals("start_date")){
						
						datefield.setStyleName("mission-start-date");
					}else if (propertyId.equals("end_date")){
						
						datefield.setStyleName("mission-end-date");
					}
					
					return datefield;
				}else{
					TextField field = new TextField((String) CollaboratorForm.NAME_FIELD_MISSION[i]+" : ");

					field.setStyleName("mission-client");
					
					return field;
				}
				
				
			}
			
		}
		
		return null;
	}

}
