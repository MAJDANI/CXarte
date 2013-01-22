package com.novedia.talentmap.web.ui.formFactory;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.web.commons.Constants;
import com.novedia.talentmap.web.ui.registration.RegistrationForm;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
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
	
	
	public static final String EMPLOYMENT_DATE_FIELD = "employmentDate";
	public static final String BUSINESS_ENGINEER_FIELD = "businessEngineer";
	public static final String PROFILE_FIELD = "profileId";
	public static final String MANAGER_FIELD = "managerId";
	public static final String PASSWORD_FIELD = "password";
	public static final String PASSWORD_CONFIRM_FIELD = "passwordConfirm";
	public static final String PHONE_FIELD = "phone";
	
	public static final String NULL_REPRESENTATION = "";
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String SELECT_VALUE = "value";
	public static final String SELECT_STYLE = "type-profile";
	
	
	
	/**
	 * 
	 * @param profileService
	 * @param colleagueService
	 */
	public RegistrationFormFieldFactory(IRegistrationService registrationService){
		this.registrationService = registrationService;
	}
	
	
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		
		for (int i = 0; i < RegistrationForm.FIELD_ORDER_REGISTRATION.length; i++) {
			
			if(propertyId.equals(RegistrationForm.FIELD_ORDER_REGISTRATION[i])){
				
				if(!propertyId.equals(EMPLOYMENT_DATE_FIELD) && !propertyId.equals(BUSINESS_ENGINEER_FIELD) 
						&& !propertyId.equals(PROFILE_FIELD) && !propertyId.equals(MANAGER_FIELD) &&!propertyId.equals(PASSWORD_FIELD) && !propertyId.equals(PASSWORD_CONFIRM_FIELD) ){
					
					TextField field = new TextField((String) RegistrationForm.NAME_FIELD_REGISTRATION[i]+" : ");
					
					field.setRequired(true);
					field.setRequiredError(Constants.PROFILE_MSG_FIELD_REQUIRED_PART1 + RegistrationForm.NAME_FIELD_REGISTRATION[i] + Constants.PROFILE_MSG_FIELD_REQUIRED_PART2);
					field.setValidationVisible(true);
					field.setNullRepresentation(NULL_REPRESENTATION);
					
					if(propertyId.equals(PHONE_FIELD)){
						field.setRequired(false);
					}
					
					return field;
					
				}
				else if(propertyId.equals(PASSWORD_FIELD) || propertyId.equals(PASSWORD_CONFIRM_FIELD)){
					PasswordField passwordField = new PasswordField((String) RegistrationForm.NAME_FIELD_REGISTRATION[i]+" : ");
					passwordField.setRequired(true);
					passwordField.setRequiredError(Constants.PROFILE_MSG_FIELD_REQUIRED_PART1 + RegistrationForm.NAME_FIELD_REGISTRATION[i] + Constants.PROFILE_MSG_FIELD_REQUIRED_PART2);
					passwordField.setValidationVisible(true);
					passwordField.setNullRepresentation(NULL_REPRESENTATION);

					return passwordField;
				}
				
				else if(propertyId.equals(EMPLOYMENT_DATE_FIELD)){
					
					PopupDateField datefield = new PopupDateField();
					
					datefield.setDateFormat(DATE_FORMAT);
					datefield.setCaption((String) RegistrationForm.NAME_FIELD_REGISTRATION[i]+ " : ");					
					datefield.setRequired(true);
					datefield.setRequiredError(Constants.PROFILE_MSG_FIELD_REQUIRED_PART1 + RegistrationForm.NAME_FIELD_REGISTRATION[i] + Constants.PROFILE_MSG_FIELD_REQUIRED_PART2);
					datefield.setValidationVisible(true);
					
					return datefield;
				}			
				
				else if(propertyId.equals(PROFILE_FIELD)){
					IndexedContainer ic = new IndexedContainer();
			        ic.addContainerProperty(SELECT_VALUE, String.class, null);
					
					Select profilSelect = new Select((String) RegistrationForm.NAME_FIELD_REGISTRATION[i]+" : ");
					
					
					try {
						for(Profile p : registrationService.getAllProfile()){
							item = ic.addItem(p.getId());
							item.getItemProperty(SELECT_VALUE).setValue(p.getType());
						}
						
						profilSelect.setContainerDataSource(ic);
						profilSelect.setItemCaptionPropertyId(SELECT_VALUE);
						profilSelect.setNullSelectionAllowed(false);
						profilSelect.setImmediate(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}	
					
					profilSelect.setStyleName(SELECT_STYLE);
					return profilSelect;
				}
				
				else if(propertyId.equals(BUSINESS_ENGINEER_FIELD) || propertyId.equals(MANAGER_FIELD)){
					IndexedContainer ic = new IndexedContainer();
			        ic.addContainerProperty(SELECT_VALUE, String.class, null);
					
					Select colleagueSelect = new Select((String) RegistrationForm.NAME_FIELD_REGISTRATION[i]+" : ");
					
					
					try {
						if(propertyId.equals(MANAGER_FIELD)){
							for(Colleague colleague : registrationService.getAllColleagues()){
								item = ic.addItem(colleague.getId());
								item.getItemProperty(SELECT_VALUE).setValue(colleague.getFirstName() + " " + colleague.getLastName());
							}
						} else {
							for(Colleague colleague : registrationService.getAllColleagues()){
								item = ic.addItem(colleague.getFirstName() + " " + colleague.getLastName());
								item.getItemProperty(SELECT_VALUE).setValue(colleague.getFirstName() + " " + colleague.getLastName());
							}
						}
						colleagueSelect.setContainerDataSource(ic);
						colleagueSelect.setItemCaptionPropertyId(SELECT_VALUE);
						if(propertyId.equals(MANAGER_FIELD)){
							colleagueSelect.setNullSelectionAllowed(false);
							colleagueSelect.setRequired(true);
						}
						colleagueSelect.setImmediate(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}	
					
					colleagueSelect.setStyleName(SELECT_STYLE);
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