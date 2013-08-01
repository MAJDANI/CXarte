package com.novedia.talentmap.web.ui.colleague;

import java.util.List;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.ComponentsClass;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class ColleagueDataForm extends FormLayout implements FocusListener{
	
	/**
	 * TalentMap service
	 */
	private IRegistrationService registrationService;
	private IColleagueService colleagueService;
	
	/**
	 * Vaadin components
	 */
	private BeanFieldGroup<Colleague> binder;
	
	private GridLayout colleagueFormLayout;
	
	@PropertyId(ComponentsId.LAST_NAME_ID)
	private TextField nameField;
	
	@PropertyId(ComponentsId.FIRST_NAME_ID)
	private TextField firstNameField;
	
	@PropertyId(ComponentsId.EMAIL_ID)
	private TextField emailField;
	
	@PropertyId(ComponentsId.PHONE_ID)
	private TextField phoneField;
	
	@PropertyId(ComponentsId.EMPLOYMENT_DATE_ID)
	private PopupDateField dateField;
	
	@PropertyId(ComponentsId.PROFILE_ID)
	private ComboBox jobField;
	
	@PropertyId(ComponentsId.EXPERIENCE_ID)
	private TextField experienceField;
	
	@PropertyId(ComponentsId.BUSINESS_ENGINEER_ID)
	private ComboBox businessEngineerField;
	
	@PropertyId(ComponentsId.MANAGER_ID)
	private ComboBox managerField;
	
	/**
	 * Default constructor
	 */
	public ColleagueDataForm() {
		super();
	}
	
	public ColleagueDataForm buildColleagueDataFormView() {

		removeAllComponents();
		buildMain();
		return this;
	}

	private void buildMain() {
		try {
			buildSignInLayout();
			buildRegistrationForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void buildSignInLayout() {
		this.colleagueFormLayout.setColumns(3);
		this.colleagueFormLayout.setRows(3);
		
	}

	private void buildRegistrationForm() {
		
		nameField.setCaption(Constants.NAME);
		nameField.setRequired(true);
		nameField.setRequiredError(Constants.GIVE_LAST_NAME);
		nameField.addValidator(new BeanValidator(Colleague.class, ComponentsId.LAST_NAME_ID));
		nameField.setImmediate(true);
		nameField.setValidationVisible(true);
		nameField.addFocusListener(this);
		nameField.setStyleName(ComponentsClass.TEXTFIELD_COLLEAGUE_DATA_FORM_CLASS);
		nameField.setId(ComponentsId.LAST_NAME_ID);
		colleagueFormLayout.addComponent(nameField);
		
		firstNameField.setCaption(Constants.FIRST_NAME);
		firstNameField.setRequired(true);
		firstNameField.setRequiredError(Constants.GIVE_FIRST_NAME);
		firstNameField.addValidator(new BeanValidator(Colleague.class,ComponentsId.FIRST_NAME_ID));
		firstNameField.setImmediate(true);
		firstNameField.setValidationVisible(true);
		firstNameField.addFocusListener(this);
		firstNameField.setStyleName(ComponentsClass.TEXTFIELD_COLLEAGUE_DATA_FORM_CLASS);
		firstNameField.setId(ComponentsId.FIRST_NAME_ID);
		colleagueFormLayout.addComponent(firstNameField);
		
		jobField.setCaption(Constants.JOB_TITLE);
		buildJobList();
		jobField.setRequired(true);
		jobField.setRequiredError(Constants.GIVE_JOB_TITLE);
		jobField.addValidator(new BeanValidator(Colleague.class, ComponentsId.PROFILE_ID));
		jobField.setImmediate(true);
		jobField.setValidationVisible(true);
		jobField.addFocusListener(this);
		jobField.addStyleName(ComponentsClass.SELECT_COLLEAGUE_DATA_FORM_CLASS);
		jobField.setId(ComponentsId.PROFILE_ID);
		colleagueFormLayout.addComponent(jobField);
		
		emailField.setCaption(Constants.EMAIL);
		emailField.setRequired(true);
		emailField.setRequiredError(Constants.GIVE_EMAIL);
		emailField.addValidator(new BeanValidator(Colleague.class, ComponentsId.EMAIL_ID));
		emailField.setImmediate(true);
		emailField.setValidationVisible(true);
		emailField.addFocusListener(this);
		emailField.setStyleName(ComponentsClass.TEXTFIELD_COLLEAGUE_DATA_FORM_CLASS);
		emailField.setId(ComponentsId.EMAIL_ID);
		colleagueFormLayout.addComponent(emailField);
		
		phoneField.setCaption(Constants.PHONE);
		phoneField.setInputPrompt(Constants.TYPE_PHONE_NUMBER);
		phoneField.setNullRepresentation("");
		phoneField.addFocusListener(this);
		phoneField.setStyleName(ComponentsClass.TEXTFIELD_COLLEAGUE_DATA_FORM_CLASS);
		phoneField.setId(ComponentsId.PHONE_ID);
		colleagueFormLayout.addComponent(phoneField);
		
		dateField.setCaption(Constants.DATE_OF_HIRE);
		dateField.setRequired(true);
		dateField.setRequiredError(Constants.GIVE_EMPLOYEMENT_DATE);
		dateField.addValidator(new BeanValidator(Colleague.class,ComponentsId.EMPLOYMENT_DATE_ID));
		dateField.setImmediate(true);
		dateField.setValidationVisible(true);
		dateField.addFocusListener(this);
		dateField.setId(ComponentsId.EMPLOYMENT_DATE_ID);
		colleagueFormLayout.addComponent(dateField);
		
		experienceField.setCaption(Constants.YEARS_OF_EXPERIENCE);
		experienceField.setRequired(true);
		experienceField.setRequiredError(Constants.GIVE_EXPERIENCE);
		experienceField.addValidator(new BeanValidator(Colleague.class,ComponentsId.EXPERIENCE_ID));
		experienceField.setImmediate(true);
		experienceField.setValidationVisible(true);
		experienceField.setInputPrompt(Constants.EXPERIENCE_FORMAT);
		experienceField.addFocusListener(this);
		experienceField.setStyleName(ComponentsClass.TEXTFIELD_COLLEAGUE_DATA_FORM_CLASS);
		experienceField.setId(ComponentsId.EXPERIENCE_ID);
		colleagueFormLayout.addComponent(experienceField);
		
		businessEngineerField.setCaption(Constants.BUSINESS_ENGINEER);
		buildEngineerList();
		businessEngineerField.addFocusListener(this);
		businessEngineerField.addStyleName(ComponentsClass.SELECT_COLLEAGUE_DATA_FORM_CLASS);
		businessEngineerField.setId(ComponentsId.BUSINESS_ENGINEER_ID);
		colleagueFormLayout.addComponent(businessEngineerField);
		
		managerField.setCaption(Constants.MANAGER);
		buildManagerList();
		managerField.addFocusListener(this);
		managerField.addStyleName(ComponentsClass.SELECT_COLLEAGUE_DATA_FORM_CLASS);
		managerField.setId(ComponentsId.MANAGER_ID);
		colleagueFormLayout.addComponent(managerField);
		Authentication authentication = TalentMapApplication.getCurrent().getAuthentication();
		Colleague colleague = colleagueService.getColleague(authentication.getColleagueId());

		binder = new BeanFieldGroup<Colleague>(Colleague.class);
		binder.setItemDataSource(colleague);
		binder.setBuffered(false);
		binder.bindMemberFields(this);
		
		addComponent(colleagueFormLayout);
	}
	

	@Override
	public void focus(FocusEvent event) {
		if((event.getSource().equals(nameField))){
			if(!nameField.getValue().equals("")){
				if (!validateColleagueDataForm()) {
				Notification.show(Constants.REGISTRATION_PANEL_MISSING_FIELDS,Notification.Type.WARNING_MESSAGE);
				}
			}
		}else if ((event.getSource().equals(firstNameField))){
			if (!validateColleagueDataForm()) {
				Notification.show(Constants.REGISTRATION_PANEL_MISSING_FIELDS,Notification.Type.WARNING_MESSAGE);
			}
		}
	}
	
	/**
     * Test the colleagueDataForm validity
     * 
     * @return boolean
     */
    private boolean validateColleagueDataForm() {

    	boolean isValidRegistration = true;
	
    	if(!this.binder.isValid()){
    		isValidRegistration = false;
    	}

    	return isValidRegistration;
    }
	
	private void buildJobList() {
		List<Profile> allProfile = registrationService.getAllProfile();
		for (Profile p : allProfile) {
			jobField.addItem(p.getId());
			jobField.setItemCaption(p.getId(), p.getType());
		}
	}

	private void buildManagerList() {
		List<Colleague> allConsultantManager = registrationService
				.getAllConsultantManager();
		for (Colleague colleague : allConsultantManager) {
			managerField.addItem(colleague.getId());
			managerField.setItemCaption(colleague.getId(),
					colleague.getFirstName() + " " + colleague.getLastName());
		}
	}

	private void buildEngineerList() {
		List<Colleague> allBusinessEngineers = colleagueService
				.getAllBusinessEngineers();
		for (Colleague colleague : allBusinessEngineers) {
			businessEngineerField.addItem(colleague.getId());
			businessEngineerField.setItemCaption(colleague.getId(),
					colleague.getFirstName() + " " + colleague.getLastName());
		}
	}

	public GridLayout getColleagueFormLayout() {
		return colleagueFormLayout;
	}

	public void setColleagueFormLayout(GridLayout colleagueFormLayout) {
		this.colleagueFormLayout = colleagueFormLayout;
	}

	public TextField getNameField() {
		return nameField;
	}

	public void setNameField(TextField nameField) {
		this.nameField = nameField;
	}

	public TextField getFirstNameField() {
		return firstNameField;
	}

	public void setFirstNameField(TextField firstNameField) {
		this.firstNameField = firstNameField;
	}

	public TextField getEmailField() {
		return emailField;
	}

	public void setEmailField(TextField emailField) {
		this.emailField = emailField;
	}

	public TextField getPhoneField() {
		return phoneField;
	}

	public void setPhoneField(TextField phoneField) {
		this.phoneField = phoneField;
	}

	public PopupDateField getDateField() {
		return dateField;
	}

	public void setDateField(PopupDateField dateField) {
		this.dateField = dateField;
	}

	public ComboBox getJobField() {
		return jobField;
	}

	public void setJobField(ComboBox jobField) {
		this.jobField = jobField;
	}

	public TextField getExperienceField() {
		return experienceField;
	}

	public void setExperienceField(TextField experienceField) {
		this.experienceField = experienceField;
	}

	public ComboBox getBusinessEngineerField() {
		return businessEngineerField;
	}

	public void setBusinessEngineerField(ComboBox businessEngineerField) {
		this.businessEngineerField = businessEngineerField;
	}

	public ComboBox getManagerField() {
		return managerField;
	}

	public void setManagerField(ComboBox managerField) {
		this.managerField = managerField;
	}

	public IColleagueService getColleagueService() {
		return colleagueService;
	}

	public void setColleagueService(IColleagueService collegueService) {
		this.colleagueService = collegueService;
	}

	public BeanFieldGroup<Colleague> getBinder() {
		return binder;
	}

	public void setBinder(BeanFieldGroup<Colleague> binder) {
		this.binder = binder;
	}

	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}

}
