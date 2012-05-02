package com.novedia.talentmap.web.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;

public class CollaboratorFormFieldFactory implements FormFieldFactory {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IProfileService profileService;
	private static final Logger LOGGER = LoggerFactory.getLogger(CollaboratorFormFieldFactory.class);

	public CollaboratorFormFieldFactory(IProfileService profileService){
		this.profileService = profileService;
	}
	
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		
		
		for(int i=0; i<CollaboratorForm.FIELD_ORDER.length;i++){
			
			if(propertyId.equals(CollaboratorForm.FIELD_ORDER[i])){
				
				if(!propertyId.equals("employment_date")){
					
					if(propertyId.equals("profile_id")){
					
						Select profilSelect = new Select((String) CollaboratorForm.NAME_FIELD[i]+" : ");
						
						try {
							for(Profile p : profileService.getAllProfile()){
								profilSelect.addItem(p.getProfileType());
							}
							
							profilSelect.setNullSelectionAllowed(false);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						
						
						return profilSelect;
						
					}else{
						
						TextField field = new TextField((String) CollaboratorForm.NAME_FIELD[i]+" : ");
						
						if(!propertyId.equals("phone") && !propertyId.equals("experience")){
							field.setReadOnly(true);
						}
						
						return field;		
					}
			
				}else{
					
					PopupDateField datefield = new PopupDateField();
					datefield.setDateFormat("dd/MM/yyyy");
					datefield.setCaption((String) CollaboratorForm.NAME_FIELD[i]);
					datefield.setReadOnly(true);
					return datefield;
				}
					
				
			}
		}
		
		return null;
	}
	
	/**
	 * Set the profileService value
	 * @param profileService the profileService to set
	 */
	public void setProfileService(IProfileService profileService) {
		this.profileService = profileService;
	}

}
