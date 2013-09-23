package com.novedia.talentmap.web.ui.formFactory;

import java.util.List;

import com.novedia.talentmap.model.entity.BusinessEngineer;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.web.commons.Constants;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.ui.registration.RegistrationForm;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
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
 * 
 * @author y.rohr TODO : Must be refactored because of methods complexity
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
     * @param registration
     *            Service
     */
    public RegistrationFormFieldFactory(
	    IRegistrationService registrationService,
	    IBusinessEngineerService businessEngineerService, Object listener) {
	this.registrationService = registrationService;
	this.businessEngineerService = businessEngineerService;
	this.listener = listener;
    }

    @Override
    public Field createField(Item item, Object propertyId, Component uiContext) {

	for (int i = 0; i < ConstantsEnglish.FIELD_ORDER_REGISTRATION.length; i++) {

	    if (propertyId.equals(ConstantsEnglish.FIELD_ORDER_REGISTRATION[i])) {

		if (!propertyId
			.equals(ConstantsEnglish.REGISTRATION_TITLE_FIELD)
			&& !propertyId
				.equals(ConstantsEnglish.REGISTRATION_EMPLOYMENT_DATE_FIELD)
			&& !propertyId
				.equals(ConstantsEnglish.REGISTRATION_BUSINESS_ENGINEER_FIELD)
			&& !propertyId
				.equals(ConstantsEnglish.REGISTRATION_PROFILE_FIELD)
			&& !propertyId
				.equals(ConstantsEnglish.REGISTRATION_MANAGER_FIELD)
			&& !propertyId
				.equals(ConstantsEnglish.REGISTRATION_PASSWORD_FIELD)
			&& !propertyId
				.equals(ConstantsEnglish.REGISTRATION_PASSWORD_CONFIRM_FIELD)) {

		    TextField field = new TextField(
			    (String) ConstantsEnglish.NAME_FIELD_REGISTRATION[i]
				    + " : ");

		    field.setRequired(true);
		    field.setRequiredError(ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART1
			    + ConstantsEnglish.NAME_FIELD_REGISTRATION[i]
			    + ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART2);
		    field.setValidationVisible(true);
		    field.setNullRepresentation(ConstantsEnglish.REGISTRATION_NULL_REPRESENTATION);

		    if (propertyId
			    .equals(ConstantsEnglish.REGISTRATION_FIRST_NAME_FIELD)) {
			field.setMaxLength(ConstantsEnglish.COLLEAGUE_FIRST_NAME_MAX_LENGTH);
		    }
		    if (propertyId
			    .equals(ConstantsEnglish.REGISTRATION_LAST_NAME_FIELD)) {
			field.setMaxLength(ConstantsEnglish.COLLEAGUE_LAST_NAME_MAX_LENGTH);
		    }
		    if (propertyId
			    .equals(ConstantsEnglish.REGISTRATION_LOGIN_FIELD)) {
			field.setMaxLength(ConstantsEnglish.REGISTRATION_LOGIN_MAX_LENGTH);
			field.addListener(ValueChangeEvent.class, listener,
				"validateLogin");
		    }
		    if (propertyId
			    .equals(ConstantsEnglish.REGISTRATION_PHONE_FIELD)) {
			field.setRequired(false);
			field.addValidator(new RegexpValidator(
				Constants.REGISTRATION_NUMBER_REGEXP,
				ConstantsEnglish.REGISTRATION_ERROR_PHONE_NUMBER));
			field.setMaxLength(ConstantsEnglish.COLLEAGUE_PHONE_MAX_LENGTH);
		    }

		    if (propertyId
			    .equals(ConstantsEnglish.REGISTRATION_EXPERIENCE_FIELD)) {
			field.addValidator(new RegexpValidator(
				ConstantsEnglish.REGISTRATION_NUMBER_REGEXP,
				ConstantsEnglish.REGISTRATION_ERROR_EXPERIENCE));
			field.setMaxLength(ConstantsEnglish.COLLEAGUE_EXPERIENCE_MAX_LENGTH);
		    }

		    if (propertyId
			    .equals(ConstantsEnglish.REGISTRATION_EMAIL_FIELD)) {
			field.addValidator(new RegexpValidator(
				Constants.REGISTRATION_EMAIL_REGEXP,
				ConstantsEnglish.REGISTRATION_ERROR_EMAIL));
			field.setMaxLength(ConstantsEnglish.COLLEAGUE_EMAIL_MAX_LENGTH);
			field.addListener(ValueChangeEvent.class, listener,
				"validateEmail");
		    }
		    return field;

		} else if (propertyId
			.equals(ConstantsEnglish.REGISTRATION_TITLE_FIELD)) {
		    OptionGroup optiongroup = new OptionGroup(
			    (String) ConstantsEnglish.NAME_FIELD_REGISTRATION[i]
				    + " : ");
		    optiongroup
			    .addItem((String) ConstantsEnglish.REGISTRATION_TITLE_CHOICE1);
		    optiongroup
			    .addItem((String) ConstantsEnglish.REGISTRATION_TITLE_CHOICE2);
		    optiongroup.setRequired(true);
		    return optiongroup;
		} else if (propertyId
			.equals(ConstantsEnglish.REGISTRATION_PASSWORD_FIELD)
			|| propertyId
				.equals(ConstantsEnglish.REGISTRATION_PASSWORD_CONFIRM_FIELD)) {
		    PasswordField passwordField = new PasswordField(
			    (String) ConstantsEnglish.NAME_FIELD_REGISTRATION[i]
				    + " : ");
		    passwordField.setRequired(true);
		    passwordField
			    .setRequiredError(ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART1
				    + ConstantsEnglish.NAME_FIELD_REGISTRATION[i]
				    + ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART2);
		    passwordField.setValidationVisible(true);
		    passwordField
			    .setNullRepresentation(ConstantsEnglish.REGISTRATION_NULL_REPRESENTATION);
		    passwordField
			    .setMaxLength(ConstantsEnglish.REGISTRATION_PASSWORD_MAX_LENGTH);
		    return passwordField;
		}

		else if (propertyId
			.equals(ConstantsEnglish.REGISTRATION_EMPLOYMENT_DATE_FIELD)) {

		    PopupDateField datefield = new PopupDateField();

		    datefield
			    .setDateFormat(ConstantsEnglish.REGISTRATION_DATE_FORMAT);
		    datefield
			    .setCaption((String) ConstantsEnglish.NAME_FIELD_REGISTRATION[i]
				    + " : ");
		    datefield.setRequired(true);
		    datefield
			    .setRequiredError(ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART1
				    + ConstantsEnglish.NAME_FIELD_REGISTRATION[i]
				    + ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART2);
		    datefield.setValidationVisible(true);

		    return datefield;
		}

		else if (propertyId
			.equals(ConstantsEnglish.REGISTRATION_PROFILE_FIELD)) {
		    Select profilSelect = new Select(
			    (String) ConstantsEnglish.NAME_FIELD_REGISTRATION[i]
				    + " : ");
		    profilSelect
			    .setWidth((String) ConstantsEnglish.REGISTRATION_COMPONENT_SIZE);
		    try {
			List<Profile> allProfile = registrationService
				.getAllProfile();
			profilSelect
				.addItem(ConstantsEnglish.DEFAULT_SELECTED_CHOICE);
			profilSelect.setItemCaption(
				ConstantsEnglish.DEFAULT_SELECTED_CHOICE,
				ConstantsEnglish.DEFAULT_CAPTION_SELECTED_JOB);

			for (Profile p : allProfile) {
			    profilSelect.addItem(p.getId());
			    profilSelect.setItemCaption(p.getId(), p.getType());
			}
			profilSelect.setRequired(true);
			profilSelect.setNullSelectionAllowed(false);
			profilSelect.setImmediate(true);

		    } catch (Exception e) {
			e.printStackTrace();
		    }

		    profilSelect
			    .setStyleName(ConstantsEnglish.REGISTRATION_SELECT_STYLE);
		    return profilSelect;
		}

		else if (propertyId
			.equals(ConstantsEnglish.REGISTRATION_BUSINESS_ENGINEER_FIELD)
			|| propertyId
				.equals(ConstantsEnglish.REGISTRATION_MANAGER_FIELD)) {
		    Select colleagueSelect = new Select(
			    (String) ConstantsEnglish.NAME_FIELD_REGISTRATION[i]
				    + " : ");
		    colleagueSelect
			    .setWidth((String) ConstantsEnglish.REGISTRATION_COMPONENT_SIZE);

		    try {
			if (propertyId
				.equals(ConstantsEnglish.REGISTRATION_MANAGER_FIELD)) {
			    List<Colleague> allConsultantManager = registrationService
				    .getAllConsultantManager();
			    colleagueSelect
				    .addItem(ConstantsEnglish.DEFAULT_SELECTED_CHOICE);
			    colleagueSelect
				    .setItemCaption(
					    ConstantsEnglish.DEFAULT_SELECTED_CHOICE,
					    ConstantsEnglish.DEFAULT_CAPTION_SELECTED_MAMANGER);

			    for (Colleague colleague : allConsultantManager) {
				colleagueSelect.addItem(colleague.getId());
				colleagueSelect.setItemCaption(
					colleague.getId(),
					colleague.getFirstName() + " "
						+ colleague.getLastName());
			    }
			} else {
			    List<BusinessEngineer> allBusinessEngineers = businessEngineerService
				    .getAllBusinessEngineer();

			    BusinessEngineer defaultBusinessEngineer = BusinessEngineer
				    .builder()
				    .id(ConstantsEnglish.DEFAULT_SELECTED_CHOICE)
				    .build();
			    colleagueSelect.addItem(defaultBusinessEngineer);
			    colleagueSelect
				    .setItemCaption(
					    defaultBusinessEngineer,
					    ConstantsEnglish.DEFAULT_CAPTION_SELECTED_BUSINESSENGINEER);
			    for (BusinessEngineer businessEngineer : allBusinessEngineers) {
				colleagueSelect.addItem(businessEngineer);
				colleagueSelect.setItemCaption(
					businessEngineer,
					businessEngineer.getFirstName()
						+ " "
						+ (businessEngineer
							.getLastName()));
			    }
			}
			colleagueSelect.setImmediate(true);
			colleagueSelect.setNullSelectionAllowed(false);

		    } catch (Exception e) {
			e.printStackTrace();
		    }

		    colleagueSelect
			    .setStyleName(ConstantsEnglish.REGISTRATION_SELECT_STYLE);
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