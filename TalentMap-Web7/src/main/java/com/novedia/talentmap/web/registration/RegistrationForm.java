package com.novedia.talentmap.web.registration;

import java.util.List;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class RegistrationForm extends FormLayout {

	private Registration registration;

	/**
	 * TalentMap service
	 */
	private IRegistrationService registrationService;
	private IColleagueService colleagueService;
	
	/**
	 * Vaadin components
	 */
	private BeanFieldGroup<Registration> binder;
	
	@PropertyId(Constants.TITLE_ID)
	private OptionGroup title;
	
	@PropertyId(Constants.LAST_NAME_ID)
	private TextField nameField;
	
	@PropertyId(Constants.FIRST_NAME_ID)
	private TextField firstNameField;
	
	@PropertyId(Constants.PASSWORD_ID)
	private PasswordField passwordField;
	
	@PropertyId(Constants.PASSWORD_CONFIRM_ID)
	private PasswordField confirmPasswordField;
	
	@PropertyId(Constants.LOGIN_ID)
	private TextField loginField;
	
	@PropertyId(Constants.EMAIL_ID)
	private TextField emailField;
	
	@PropertyId(Constants.PHONE_ID)
	private TextField phoneField;
	
	@PropertyId(Constants.EMPLOYMENT_DATE_ID)
	private PopupDateField dateField;
	
	@PropertyId(Constants.PROFILE_ID)
	private ComboBox jobField;
	
	@PropertyId(Constants.EXPERIENCE_ID)
	private TextField experienceField;
	
	@PropertyId(Constants.BUSINESS_ENGINEER_ID)
	private ComboBox businessEngineerField;
	
	@PropertyId(Constants.MANAGER_ID)
	private ComboBox managerField;
	
	private GridLayout registrationFormLayout;

	/**
	 * Default constructor
	 */
	public RegistrationForm() {
		super();
	}

	public RegistrationForm buildRegistrationFormView() {

		removeAllComponents();
		buildMain();
		return this;
	}

	public void buildMain() {

		try {
			buildSignInLayout();
			buildRegistrationForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Build the registrationForm Layout
	 */
	public void buildSignInLayout() {
		this.registrationFormLayout.setColumns(2);
		this.registrationFormLayout.setRows(8);
	}

	public void buildRegistrationForm() {
		title.setCaption(Constants.TITLE_REGISTRATION);
		title.addItem(Constants.TITLE_MR);
		title.addItem(Constants.TITLE_MRS);
		title.setMultiSelect(false);
		title.setRequired(true);
		title.setRequiredError(Constants.GIVE_TITLE);
		title.addStyleName("horizontal");
		registrationFormLayout.addComponent(title);

		loginField.setCaption(Constants.CHOOSE_LOGIN);
		loginField.setRequired(true);
		loginField.setRequiredError(Constants.GIVE_LOGIN);
		loginField.addValidator(new BeanValidator(Registration.class, Constants.LOGIN_ID));
		loginField.setImmediate(true);
		loginField.setValidationVisible(true);
		loginField.setInputPrompt(Constants.TYPE_LOGIN);
		loginField.setNullRepresentation("");
		registrationFormLayout.addComponent(loginField);
		
		nameField.setCaption(Constants.NAME);
		nameField.setRequired(true);
		nameField.setRequiredError(Constants.GIVE_LAST_NAME);
		nameField.addValidator(new BeanValidator(Registration.class, Constants.LAST_NAME_ID));
		nameField.setImmediate(true);
		nameField.setValidationVisible(true);
		nameField.setInputPrompt(Constants.TYPE_NAME);
		nameField.setNullRepresentation("");
		registrationFormLayout.addComponent(nameField);

		firstNameField.setCaption(Constants.FIRST_NAME);
		firstNameField.setRequired(true);
		firstNameField.setRequiredError(Constants.GIVE_FIRST_NAME);
		firstNameField.addValidator(new BeanValidator(Registration.class,
				Constants.FIRST_NAME_ID));
		firstNameField.setImmediate(true);
		firstNameField.setValidationVisible(true);
		firstNameField.setInputPrompt(Constants.TYPE_FIRST_NAME);
		firstNameField.setNullRepresentation("");
		registrationFormLayout.addComponent(firstNameField);

		passwordField.setCaption(Constants.PASSWORD);
		passwordField.setRequired(true);
		passwordField.setRequiredError(Constants.GIVE_PASSWORD);
		passwordField.addValidator(new BeanValidator(Registration.class,
				Constants.PASSWORD_ID));
		passwordField.setImmediate(true);
		passwordField.setValidationVisible(true);
		passwordField.setInputPrompt(Constants.TYPE_PASSWORD);
		passwordField.setNullRepresentation("");
		registrationFormLayout.addComponent(passwordField);

		confirmPasswordField.setCaption(Constants.CONFIRM_PASSWORD);
		confirmPasswordField.setRequired(true);
		confirmPasswordField.setRequiredError(Constants.GIVE_CONFIRMED_PASSWORD);
		confirmPasswordField.addValidator(new BeanValidator(Registration.class,
				Constants.PASSWORD_CONFIRM_ID));
		confirmPasswordField.setImmediate(true);
		confirmPasswordField.setValidationVisible(true);
		confirmPasswordField.setInputPrompt(Constants.CONFIRM_PASSWORD);
		confirmPasswordField.setNullRepresentation("");
		registrationFormLayout.addComponent(confirmPasswordField);

		emailField.setCaption(Constants.EMAIL);
		emailField.setRequired(true);
		emailField.setRequiredError(Constants.GIVE_EMAIL);
		emailField.addValidator(new BeanValidator(Registration.class, Constants.EMAIL_ID));
		emailField.setImmediate(true);
		emailField.setValidationVisible(true);
		emailField.setInputPrompt(Constants.TYPE_EMAIL);
		emailField.setNullRepresentation("");
		registrationFormLayout.addComponent(emailField);

		phoneField.setCaption(Constants.PHONE);
		phoneField.setInputPrompt(Constants.TYPE_PHONE_NUMBER);
		phoneField.setNullRepresentation("");
		registrationFormLayout.addComponent(phoneField);

		dateField.setCaption(Constants.DATE_OF_HIRE);
		dateField.setRequired(true);
		dateField.setRequiredError(Constants.GIVE_EMPLOYEMENT_DATE);
		dateField.addValidator(new BeanValidator(Registration.class,
				Constants.EMPLOYMENT_DATE_ID));
		dateField.setImmediate(true);
		dateField.setValidationVisible(true);
		dateField.setInputPrompt(Constants.DATE_FORMAT);
		registrationFormLayout.addComponent(dateField);

		jobField.setCaption(Constants.JOB_TITLE);
		jobField.setRequired(true);
		jobField.setRequiredError(Constants.GIVE_JOB_TITLE);
		jobField.addValidator(new BeanValidator(Registration.class, Constants.PROFILE_ID));
		jobField.setImmediate(true);
		jobField.setValidationVisible(true);
		jobField.setInputPrompt(Constants.SELECT_JOB_TITLE);
		buildJobList();
		registrationFormLayout.addComponent(jobField);

		experienceField.setCaption(Constants.YEARS_OF_EXPERIENCE);
		experienceField.setRequired(true);
		experienceField.setRequiredError(Constants.GIVE_EXPERIENCE);
		experienceField.addValidator(new BeanValidator(Registration.class,
				Constants.EXPERIENCE_ID));
		experienceField.setImmediate(true);
		experienceField.setValidationVisible(true);
		experienceField.setInputPrompt(Constants.EXPERIENCE_FORMAT);
		experienceField.setNullRepresentation("");
		registrationFormLayout.addComponent(experienceField);

		businessEngineerField.setCaption(Constants.BUSINESS_ENGINEER);
		businessEngineerField.setInputPrompt(Constants.SELECT_BUSINESS_ENGINEER);
		buildEngineerList();
		registrationFormLayout.addComponent(businessEngineerField);

		managerField.setCaption(Constants.MANAGER);
		managerField.setInputPrompt(Constants.SELECT_MANAGER);
		buildManagerList();
		registrationFormLayout.addComponent(managerField);

		registration = Registration.Builder.builder().title(Constants.TITLE_MR).build();

		binder = new BeanFieldGroup<Registration>(
				Registration.class);
		binder.setItemDataSource(registration);
		binder.setBuffered(false);
		binder.bindMemberFields(this);
		
		addComponent(this.registrationFormLayout);

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
	
	

	public GridLayout getRegistrationFormLayout() {
		return registrationFormLayout;
	}

	public void setRegistrationFormLayout(GridLayout registrationFormLayout) {
		this.registrationFormLayout = registrationFormLayout;
	}

	public OptionGroup getTitle() {
		return title;
	}

	public void setTitle(OptionGroup title) {
		this.title = title;
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

	public PasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(PasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public PasswordField getConfirmPasswordField() {
		return confirmPasswordField;
	}

	public void setConfirmPasswordField(PasswordField confirmPasswordField) {
		this.confirmPasswordField = confirmPasswordField;
	}

	public TextField getLoginField() {
		return loginField;
	}

	public void setLoginField(TextField loginField) {
		this.loginField = loginField;
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

	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	public IColleagueService getColleagueService() {
		return colleagueService;
	}

	public void setColleagueService(IColleagueService colleagueService) {
		this.colleagueService = colleagueService;
	}

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	public BeanFieldGroup<Registration> getBinder() {
		return binder;
	}

	public void setBinder(BeanFieldGroup<Registration> binder) {
		this.binder = binder;
	}

}
