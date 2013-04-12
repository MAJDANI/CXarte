package com.novedia.talentmap.web.ui.formFactory;

import com.novedia.talentmap.model.entity.BusinessEngineer;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.web.commons.Constants;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.ui.registration.RegistrationForm;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;

/**
 * Create field instance for {@link RegistrationForm}
 * @author y.rohr
 * TODO : Must be refactored because of methods complexity
 *
 */
public class RegistrationFormFieldFactory implements FormFieldFactory {

	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = -6912883483308852687L;

	/**
	 * The registration service
	 */
	private IRegistrationService registrationService;
	
	/**
	 * The business Engineer service
	 */
	private IBusinessEngineerService businessEngineerService;
	
	private Object listener;
	
	/**
	 * 
	 * @param registration Service
	 */
	public RegistrationFormFieldFactory(IRegistrationService registrationService, IBusinessEngineerService businessEngineerService, Object listener){
		this.registrationService = registrationService;
		this.businessEngineerService = businessEngineerService;
		this.listener = listener;
	}
	
	
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		
		for (int i = 0; i < ConstantsEnglish.FIELD_ORDER_REGISTRATION.length; i++) {
			
			if(propertyId.equals(ConstantsEnglish.FIELD_ORDER_REGISTRATION[i])){
				
				if(!propertyId.equals(ConstantsEnglish.REGISTRATION_TITLE_FIELD)
						&&!propertyId.equals(ConstantsEnglish.REGISTRATION_EMPLOYMENT_DATE_FIELD) 
						&& !propertyId.equals(ConstantsEnglish.REGISTRATION_BUSINESS_ENGINEER_FIELD) 
						&& !propertyId.equals(ConstantsEnglish.REGISTRATION_PROFILE_FIELD) 
						&& !propertyId.equals(ConstantsEnglish.REGISTRATION_MANAGER_FIELD) 
						&& !propertyId.equals(ConstantsEnglish.REGISTRATION_PASSWORD_FIELD)
						&& !propertyId.equals(ConstantsEnglish.REGISTRATION_PASSWORD_CONFIRM_FIELD) ){
					
					TextField field = new TextField((String) ConstantsEnglish.NAME_FIELD_REGISTRATION[i]+" : ");
					
					field.setRequired(true);
					field.setRequiredError(ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART1 + ConstantsEnglish.NAME_FIELD_REGISTRATION[i] + ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART2);
					field.setValidationVisible(true);
					field.setNullRepresentation(ConstantsEnglish.REGISTRATION_NULL_REPRESENTATION);
					
					
					if(propertyId.equals(ConstantsEnglish.REGISTRATION_FIRST_NAME_FIELD)){
						field.setMaxLength(ConstantsEnglish.COLLEAGUE_FIRST_NAME_MAX_LENGTH);
					}					
					if(propertyId.equals(ConstantsEnglish.REGISTRATION_LAST_NAME_FIELD)){
						field.setMaxLength(ConstantsEnglish.COLLEAGUE_LAST_NAME_MAX_LENGTH);
					}
					if(propertyId.equals(ConstantsEnglish.REGISTRATION_LOGIN_FIELD)){
						field.setMaxLength(ConstantsEnglish.REGISTRATION_LOGIN_MAX_LENGTH);
						field.addListener(ValueChangeEvent.class, listener, "validateLogin");
					}
					if(propertyId.equals(ConstantsEnglish.REGISTRATION_PHONE_FIELD)){
						field.setRequired(false);
						field.addValidator(new RegexpValidator(Constants.REGISTRATION_NUMBER_REGEXP,ConstantsEnglish.REGISTRATION_ERROR_PHONE_NUMBER));
						field.setMaxLength(ConstantsEnglish.COLLEAGUE_PHONE_MAX_LENGTH);
					}
					
					if(propertyId.equals(ConstantsEnglish.REGISTRATION_EXPERIENCE_FIELD)){
						field.addValidator(new RegexpValidator(ConstantsEnglish.REGISTRATION_NUMBER_REGEXP,ConstantsEnglish.REGISTRATION_ERROR_EXPERIENCE));
						field.setMaxLength(ConstantsEnglish.COLLEAGUE_EXPERIENCE_MAX_LENGTH);
					}
					
					if(propertyId.equals(ConstantsEnglish.REGISTRATION_EMAIL_FIELD)){
						field.addValidator(new RegexpValidator(Constants.REGISTRATION_EMAIL_REGEXP,ConstantsEnglish.REGISTRATION_ERROR_EMAIL));
						field.setMaxLength(ConstantsEnglish.COLLEAGUE_EMAIL_MAX_LENGTH);
						field.addListener(ValueChangeEvent.class, listener, "validateEmail");
					}
					return field;
					
				}
				else if(propertyId.equals(ConstantsEnglish.REGISTRATION_TITLE_FIELD)){
					OptionGroup optiongroup = new OptionGroup((String) ConstantsEnglish.NAME_FIELD_REGISTRATION[i]+" : ");
					optiongroup.addItem((String) ConstantsEnglish.REGISTRATION_TITLE_CHOICE1);
					optiongroup.addItem((String) ConstantsEnglish.REGISTRATION_TITLE_CHOICE2);
					optiongroup.setRequired(true);
					return optiongroup;
				}
				else if(propertyId.equals(ConstantsEnglish.REGISTRATION_PASSWORD_FIELD) || propertyId.equals(ConstantsEnglish.REGISTRATION_PASSWORD_CONFIRM_FIELD)){
					PasswordField passwordField = new PasswordField((String) ConstantsEnglish.NAME_FIELD_REGISTRATION[i]+" : ");
					passwordField.setRequired(true);
					passwordField.setRequiredError(ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART1 + ConstantsEnglish.NAME_FIELD_REGISTRATION[i] + ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART2);
					passwordField.setValidationVisible(true);
					passwordField.setNullRepresentation(ConstantsEnglish.REGISTRATION_NULL_REPRESENTATION);
					passwordField.setMaxLength(ConstantsEnglish.REGISTRATION_PASSWORD_MAX_LENGTH);
					return passwordField;
				}
				
				else if(propertyId.equals(ConstantsEnglish.REGISTRATION_EMPLOYMENT_DATE_FIELD)){
					
					PopupDateField datefield = new PopupDateField();
					
					datefield.setDateFormat(ConstantsEnglish.REGISTRATION_DATE_FORMAT);
					datefield.setCaption((String) ConstantsEnglish.NAME_FIELD_REGISTRATION[i]+ " : ");					
					datefield.setRequired(true);
					datefield.setRequiredError(ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART1 + ConstantsEnglish.NAME_FIELD_REGISTRATION[i] + ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART2);
					datefield.setValidationVisible(true);
					
					return datefield;
				}			
				
				else if(propertyId.equals(ConstantsEnglish.REGISTRATION_PROFILE_FIELD)){
					IndexedContainer ic = new IndexedContainer();
			        ic.addContainerProperty(ConstantsEnglish.REGISTRATION_SELECT_VALUE, String.class, null);
					
					Select profilSelect = new Select((String) ConstantsEnglish.NAME_FIELD_REGISTRATION[i]+" : "); 
					profilSelect.setWidth((String) ConstantsEnglish.REGISTRATION_COMPONENT_SIZE);
					try {
						for(Profile p : registrationService.getAllProfile()){
							item = ic.addItem(p.getId());
							item.getItemProperty(Constants.REGISTRATION_SELECT_VALUE).setValue(p.getType());
						}
						
						profilSelect.setContainerDataSource(ic);
						profilSelect.setRequired(true);
						profilSelect.setItemCaptionPropertyId(ConstantsEnglish.REGISTRATION_SELECT_VALUE);
						profilSelect.setNullSelectionAllowed(false);
						profilSelect.setImmediate(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}	
					
					profilSelect.setStyleName(ConstantsEnglish.REGISTRATION_SELECT_STYLE);
					return profilSelect;
				}
				
				else if(propertyId.equals(ConstantsEnglish.REGISTRATION_BUSINESS_ENGINEER_FIELD) || propertyId.equals(ConstantsEnglish.REGISTRATION_MANAGER_FIELD)){
					IndexedContainer ic = new IndexedContainer();
			        ic.addContainerProperty(Constants.REGISTRATION_SELECT_VALUE, String.class, null);
					
					Select colleagueSelect = new Select((String) ConstantsEnglish.NAME_FIELD_REGISTRATION[i]+" : ");
					colleagueSelect.setWidth((String) ConstantsEnglish.REGISTRATION_COMPONENT_SIZE);
					
					try {
						if(propertyId.equals(ConstantsEnglish.REGISTRATION_MANAGER_FIELD)){
							for(Colleague colleague : registrationService.getAllConsultantManager()){
								item = ic.addItem(colleague.getId());
								item.getItemProperty(Constants.REGISTRATION_SELECT_VALUE).setValue(colleague.getFirstName() + " " + colleague.getLastName());
							}
						} else {
							for(BusinessEngineer businessEngineer : businessEngineerService.getAllBusinessEngineer()){
								item = ic.addItem(businessEngineer);
								item.getItemProperty(Constants.REGISTRATION_SELECT_VALUE).setValue(businessEngineer.getFirstName() + " " + (businessEngineer.getLastName()));
							}
						}
						colleagueSelect.setContainerDataSource(ic);
						colleagueSelect.setItemCaptionPropertyId(ConstantsEnglish.REGISTRATION_SELECT_VALUE);
						colleagueSelect.setImmediate(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}	
					
					colleagueSelect.setStyleName(ConstantsEnglish.REGISTRATION_SELECT_STYLE);
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