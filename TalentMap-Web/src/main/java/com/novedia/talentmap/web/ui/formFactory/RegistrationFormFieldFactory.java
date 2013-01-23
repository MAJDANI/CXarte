package com.novedia.talentmap.web.ui.formFactory;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.web.commons.Constants;
import com.novedia.talentmap.web.ui.registration.RegistrationForm;
import com.novedia.talentmap.web.commons.Constants;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;

public class RegistrationFormFieldFactory implements FormFieldFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6912883483308852687L;

	
	private IRegistrationService registrationService;
	
	
	
	/**
	 * 
	 * @param registration Service
	 */
	public RegistrationFormFieldFactory(IRegistrationService registrationService){
		this.registrationService = registrationService;
	}
	
	
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		
		for (int i = 0; i < RegistrationForm.FIELD_ORDER_REGISTRATION.length; i++) {
			
			if(propertyId.equals(RegistrationForm.FIELD_ORDER_REGISTRATION[i])){
				
				if(!propertyId.equals(Constants.REGISTRATION_EMPLOYMENT_DATE_FIELD) 
						&& !propertyId.equals(Constants.REGISTRATION_BUSINESS_ENGINEER_FIELD) 
						&& !propertyId.equals(Constants.REGISTRATION_PROFILE_FIELD) 
						&& !propertyId.equals(Constants.REGISTRATION_MANAGER_FIELD) 
						&&!propertyId.equals(Constants.REGISTRATION_PASSWORD_FIELD)
						&& !propertyId.equals(Constants.REGISTRATION_PASSWORD_CONFIRM_FIELD) ){
					
					TextField field = new TextField((String) RegistrationForm.NAME_FIELD_REGISTRATION[i]+" : ");
					
					field.setRequired(true);
					field.setRequiredError(Constants.PROFILE_MSG_FIELD_REQUIRED_PART1 + RegistrationForm.NAME_FIELD_REGISTRATION[i] + Constants.PROFILE_MSG_FIELD_REQUIRED_PART2);
					field.setValidationVisible(true);
					field.setNullRepresentation(Constants.REGISTRATION_NULL_REPRESENTATION);
					
					
					if(propertyId.equals(Constants.REGISTRATION_PHONE_FIELD)){
						field.setRequired(false);
						field.addValidator(new RegexpValidator(Constants.REGISTRATION_NUMBER_REGEXP,Constants.REGISTRATION_ERROR_PHONE_NUMBER));
					}
					
					if(propertyId.equals(Constants.REGISTRATION_EXPERIENCE_FIELD)){
						field.addValidator(new RegexpValidator(Constants.REGISTRATION_NUMBER_REGEXP,Constants.REGISTRATION_ERROR_EXPERIENCE));
					}
					
					if(propertyId.equals(Constants.REGISTRATION_EMAIL_FIELD)){
						field.addValidator(new RegexpValidator(Constants.REGISTRATION_EMAIL_REGEXP,Constants.REGISTRATION_ERROR_EMAIL));
					}
					return field;
					
				}
				else if(propertyId.equals(Constants.REGISTRATION_PASSWORD_FIELD) || propertyId.equals(Constants.REGISTRATION_PASSWORD_CONFIRM_FIELD)){
					PasswordField passwordField = new PasswordField((String) RegistrationForm.NAME_FIELD_REGISTRATION[i]+" : ");
					passwordField.setRequired(true);
					passwordField.setRequiredError(Constants.PROFILE_MSG_FIELD_REQUIRED_PART1 + RegistrationForm.NAME_FIELD_REGISTRATION[i] + Constants.PROFILE_MSG_FIELD_REQUIRED_PART2);
					passwordField.setValidationVisible(true);
					passwordField.setNullRepresentation(Constants.REGISTRATION_NULL_REPRESENTATION);

					return passwordField;
				}
				
				else if(propertyId.equals(Constants.REGISTRATION_EMPLOYMENT_DATE_FIELD)){
					
					PopupDateField datefield = new PopupDateField();
					
					datefield.setDateFormat(Constants.REGISTRATION_DATE_FORMAT);
					datefield.setCaption((String) RegistrationForm.NAME_FIELD_REGISTRATION[i]+ " : ");					
					datefield.setRequired(true);
					datefield.setRequiredError(Constants.PROFILE_MSG_FIELD_REQUIRED_PART1 + RegistrationForm.NAME_FIELD_REGISTRATION[i] + Constants.PROFILE_MSG_FIELD_REQUIRED_PART2);
					datefield.setValidationVisible(true);
					
					return datefield;
				}			
				
				else if(propertyId.equals(Constants.REGISTRATION_PROFILE_FIELD)){
					IndexedContainer ic = new IndexedContainer();
			        ic.addContainerProperty(Constants.REGISTRATION_SELECT_VALUE, String.class, null);
					
					Select profilSelect = new Select((String) RegistrationForm.NAME_FIELD_REGISTRATION[i]+" : "); 
					
					try {
						for(Profile p : registrationService.getAllProfile()){
							item = ic.addItem(p.getId());
							item.getItemProperty(Constants.REGISTRATION_SELECT_VALUE).setValue(p.getType());
						}
						
						profilSelect.setContainerDataSource(ic);
						profilSelect.setRequired(true);
						profilSelect.setItemCaptionPropertyId(Constants.REGISTRATION_SELECT_VALUE);
						profilSelect.setNullSelectionAllowed(false);
						profilSelect.setImmediate(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}	
					
					profilSelect.setStyleName(Constants.REGISTRATION_SELECT_STYLE);
					return profilSelect;
				}
				
				else if(propertyId.equals(Constants.REGISTRATION_BUSINESS_ENGINEER_FIELD) || propertyId.equals(Constants.REGISTRATION_MANAGER_FIELD)){
					IndexedContainer ic = new IndexedContainer();
			        ic.addContainerProperty(Constants.REGISTRATION_SELECT_VALUE, String.class, null);
					
					Select colleagueSelect = new Select((String) RegistrationForm.NAME_FIELD_REGISTRATION[i]+" : ");
					
					
					try {
						if(propertyId.equals(Constants.REGISTRATION_MANAGER_FIELD)){
							for(Colleague colleague : registrationService.getAllConsultantManager()){
								item = ic.addItem(colleague.getId());
								item.getItemProperty(Constants.REGISTRATION_SELECT_VALUE).setValue(colleague.getFirstName() + " " + colleague.getLastName());
							}
						} else {
							for(Colleague colleague : registrationService.getAllBusinessEngineer()){
								item = ic.addItem(colleague.getFirstName() + " " + colleague.getLastName());
								item.getItemProperty(Constants.REGISTRATION_SELECT_VALUE).setValue(colleague.getFirstName() + " " + colleague.getLastName());
							}
						}
						colleagueSelect.setContainerDataSource(ic);
						colleagueSelect.setItemCaptionPropertyId(Constants.REGISTRATION_SELECT_VALUE);
						if(propertyId.equals(Constants.REGISTRATION_MANAGER_FIELD)){
							colleagueSelect.setNullSelectionAllowed(false);
							colleagueSelect.setRequired(true);
						}
						colleagueSelect.setImmediate(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}	
					
					colleagueSelect.setStyleName(Constants.REGISTRATION_SELECT_STYLE);
					return colleagueSelect;
				}
		
			}
			
			
			
		}
		
		return null;
	}


	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	
	
}