package com.novedia.talentmap.web.registration;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.entity.BusinessEngineer;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.helpers.DataValidationHelper;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.ConstantsDB;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class RegistrationForm extends FormLayout implements BlurListener, ValueChangeListener{

	private Registration registration;

	/**
	 * TalentMap service
	 */
	private IRegistrationService registrationService;
	private IColleagueService colleagueService;
	private IBusinessEngineerService businessEngineerService;
	
	/**
	 * Vaadin components
	 */
	private BeanFieldGroup<Registration> binder;
	
	@PropertyId(ComponentsId.TITLE_ID)
	private OptionGroup title;
	
	@PropertyId(ComponentsId.LAST_NAME_ID)
	private TextField nameField;
	
	@PropertyId(ComponentsId.FIRST_NAME_ID)
	private TextField firstNameField;
	
	@PropertyId(ComponentsId.PASSWORD_ID)
	private PasswordField passwordField;
	
	@PropertyId(ComponentsId.PASSWORD_CONFIRM_ID)
	private PasswordField confirmPasswordField;
	
	@PropertyId(ComponentsId.LOGIN_ID)
	private TextField loginField;
	
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
	
	private GridLayout registrationGridLayout;
	private DataValidationHelper dataValidationHelper;
	private ResourceBundle resourceBundle;
	
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
		this.registrationGridLayout.setColumns(2);
		this.registrationGridLayout.setRows(8);
	}

	public void buildRegistrationForm() {
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES, locale);
		//--------------------------------------
		// TITLE
		//--------------------------------------
		title.setCaption(resourceBundle.getString("title.caption"));
		title.addItem(resourceBundle.getString("title.masculin.value"));
		title.setItemCaption(resourceBundle.getString("title.masculin.value"), resourceBundle.getString("title.masculin.caption"));
		title.addItem(resourceBundle.getString("title.feminin.value"));
		title.setItemCaption(resourceBundle.getString("title.feminin.value"), resourceBundle.getString("title.feminin.caption"));
		title.setMultiSelect(false);
		title.setRequired(true);
		title.setRequiredError(resourceBundle.getString("error.missing.title"));
		title.addStyleName("horizontal");
		title.setId(ComponentsId.TITLE_ID);
		registrationGridLayout.addComponent(title);
		
		//--------------------------------------
		// LOGIN
		//--------------------------------------
		loginField.setCaption(resourceBundle.getString("login.field.caption"));
		loginField.setRequired(true);
		loginField.setImmediate(true);
		loginField.setValidationVisible(true);
		loginField.setInputPrompt(resourceBundle.getString("login.field.default.value"));
		loginField.setNullRepresentation("");
		loginField.setId(ComponentsId.LOGIN_FIELD_ID);
		loginField.setMaxLength(ConstantsDB.REGISTRATION_LOGIN_MAX_LENGTH);
		loginField.addValueChangeListener(this);
		registrationGridLayout.addComponent(loginField);
		
		//--------------------------------------
		// NAME
		//--------------------------------------
		nameField.setCaption(resourceBundle.getString("name.field.caption"));
		nameField.setRequired(true);
		nameField.setImmediate(true);
		nameField.setValidationVisible(true);
		nameField.setInputPrompt(resourceBundle.getString("name.field.default.value"));
		nameField.setNullRepresentation("");
		nameField.setId(ComponentsId.LAST_NAME_ID);
		nameField.setMaxLength(ConstantsDB.COLLEAGUE_LAST_NAME_MAX_LENGTH);
		nameField.addValueChangeListener(this);
		registrationGridLayout.addComponent(nameField);

		//--------------------------------------
		// FIRST NAME
		//--------------------------------------
		firstNameField.setCaption(resourceBundle.getString("firstName.field.caption"));
		firstNameField.setRequired(true);
		firstNameField.setImmediate(true);
		firstNameField.setValidationVisible(true);
		firstNameField.setInputPrompt(resourceBundle.getString("firstName.field.default.value"));
		firstNameField.setNullRepresentation("");
		firstNameField.setId(ComponentsId.FIRST_NAME_ID);
		firstNameField.setMaxLength(ConstantsDB.COLLEAGUE_FIRST_NAME_MAX_LENGTH);
		firstNameField.addValueChangeListener(this);
		registrationGridLayout.addComponent(firstNameField);

		//--------------------------------------
		// PASSWORD
		//--------------------------------------
		passwordField.setCaption(resourceBundle.getString("password.field.caption"));
		passwordField.setRequired(true);
		passwordField.setImmediate(true);
		passwordField.setValidationVisible(true);
		passwordField.setInputPrompt(resourceBundle.getString("password.field.default.value"));
		passwordField.setNullRepresentation("");
		passwordField.setId(ComponentsId.PASSWORD_ID);
		passwordField.setMaxLength(ConstantsDB.REGISTRATION_PASSWORD_MAX_LENGTH);
		passwordField.addValueChangeListener(this);
		registrationGridLayout.addComponent(passwordField);

		//--------------------------------------
		// CONFIRM PASSWORD
		//--------------------------------------
		confirmPasswordField.setCaption(resourceBundle.getString("confirm.password.field.caption"));
		confirmPasswordField.setRequired(true);
		confirmPasswordField.setImmediate(true);
		confirmPasswordField.setValidationVisible(true);
		confirmPasswordField.setInputPrompt(resourceBundle.getString("confirm.password.field.caption"));
		confirmPasswordField.setNullRepresentation("");
		confirmPasswordField.setId(ComponentsId.PASSWORD_CONFIRM_ID);
		confirmPasswordField.setMaxLength(ConstantsDB.REGISTRATION_PASSWORD_MAX_LENGTH);
		confirmPasswordField.addValueChangeListener(this);
		registrationGridLayout.addComponent(confirmPasswordField);

		//--------------------------------------
		// EMAIL
		//--------------------------------------
		emailField.setCaption(resourceBundle.getString("email.field.caption"));
		emailField.setRequired(true);
		emailField.setImmediate(true);
		emailField.setValidationVisible(true);
		emailField.setInputPrompt(resourceBundle.getString("email.field.default.value"));
		emailField.setNullRepresentation("");
		emailField.setId(ComponentsId.EMAIL_ID);
		emailField.setMaxLength(ConstantsDB.COLLEAGUE_EMAIL_MAX_LENGTH);
		emailField.addValueChangeListener(this);
		registrationGridLayout.addComponent(emailField);

		//--------------------------------------
		// PHONE
		//--------------------------------------
		phoneField.setCaption(resourceBundle.getString("phone.field.caption"));
		phoneField.setInputPrompt(resourceBundle.getString("phone.field.default.value"));
		phoneField.setImmediate(true);
		phoneField.setNullRepresentation("");
		phoneField.setId(ComponentsId.PHONE_ID);
		phoneField.setMaxLength(ConstantsDB.COLLEAGUE_PHONE_MAX_LENGTH);
		phoneField.addValueChangeListener(this);
		registrationGridLayout.addComponent(phoneField);

		//--------------------------------------
		// DATE
		//--------------------------------------
		dateField.setCaption(resourceBundle.getString("date.entry.caption"));
		dateField.setRequired(true);
		dateField.setImmediate(true);
		dateField.setValidationVisible(true);
		dateField.setInputPrompt(Constants.DATE_FORMAT);
		dateField.setId(ComponentsId.EMPLOYMENT_DATE_ID);
		dateField.addValueChangeListener(this);
		dateField.addBlurListener(this);
		registrationGridLayout.addComponent(dateField);

		//--------------------------------------
		// JOB/PROFILE
		//--------------------------------------
		jobField.setCaption(resourceBundle.getString("job.field.caption"));
		jobField.setRequired(true);
		jobField.setRequiredError(resourceBundle.getString("error.job.field.missing.msg"));
		jobField.setImmediate(true);
		jobField.setValidationVisible(true);
		jobField.setInputPrompt(resourceBundle.getString("job.field.default.value"));
		buildJobList();
		jobField.setId(ComponentsId.PROFILE_ID);
		registrationGridLayout.addComponent(jobField);

		//--------------------------------------
		// EXPERIENCE
		//--------------------------------------
		experienceField.setCaption(resourceBundle.getString("experience.field.caption"));
		experienceField.setRequired(true);
		experienceField.setImmediate(true);
		experienceField.setValidationVisible(true);
		experienceField.setInputPrompt(Constants.EXPERIENCE_FORMAT);
		experienceField.setNullRepresentation("");
		experienceField.setId(ComponentsId.EXPERIENCE_ID);
		experienceField.setMaxLength(ConstantsDB.COLLEAGUE_EXPERIENCE_MAX_LENGTH);
		experienceField.addValueChangeListener(this);
		registrationGridLayout.addComponent(experienceField);

		//--------------------------------------
		// BUSINESS ENGINEER
		//--------------------------------------
		businessEngineerField.setCaption(resourceBundle.getString("businessEngineer.field.caption"));
		businessEngineerField.setInputPrompt(resourceBundle.getString("businessEngineer.field.default.value"));
		buildEngineerList();
		businessEngineerField.setId(ComponentsId.BUSINESS_ENGINEER_ID);
		registrationGridLayout.addComponent(businessEngineerField);

		//--------------------------------------
		// MANAGER
		//--------------------------------------
		managerField.setCaption(resourceBundle.getString("manager.field.caption"));
		managerField.setInputPrompt(resourceBundle.getString("manager.field.value"));
		buildManagerList();
		managerField.setId(ComponentsId.MANAGER_ID);
		registrationGridLayout.addComponent(managerField);

		registration = Registration.Builder.builder().title(resourceBundle.getString("title.masculin.value")).build();

		binder = new BeanFieldGroup<Registration>(
				Registration.class);
		binder.setItemDataSource(registration);
		binder.setBuffered(false);
		binder.bindMemberFields(this);
		
		addComponent(this.registrationGridLayout);

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
		List<BusinessEngineer> allBusinessEngineers = businessEngineerService
				.getAllBusinessEngineer();

		for (BusinessEngineer businessEngineer : allBusinessEngineers) {
			businessEngineerField.addItem(businessEngineer);
			businessEngineerField.setItemCaption(
					businessEngineer,
					businessEngineer.getFirstName() + " "
							+ (businessEngineer.getLastName()));
		}
	}
	
	@Override
	public void blur(BlurEvent event) {
		Component p = event.getComponent();
		if (dateField.equals(p)) {
			dataValidationHelper.validatePastDateField(dateField);
		} 
	}

	
	@Override
	public void valueChange(ValueChangeEvent event) {
		Property p = event.getProperty();
		if(loginField.equals(p)) {
			dataValidationHelper.validateLogin(loginField);
		} else if(nameField.equals(p)) {
			dataValidationHelper.validateLastName(nameField);
		} else if(firstNameField.equals(p)) {
			dataValidationHelper.validateFirstName(firstNameField);
		} else if(phoneField.equals(p)) {
			dataValidationHelper.validatePhone(phoneField);
		} else if(passwordField.equals(p)) {
			dataValidationHelper.validatePassword(passwordField);
		} else if(confirmPasswordField.equals(p)) {
			dataValidationHelper.validateConfirmPassword(confirmPasswordField);
		} else if(emailField.equals(p)) {
			dataValidationHelper.validateEmail(emailField);
		} else if(experienceField.equals(p)) {
			dataValidationHelper.validateExperience(experienceField);
		} else if(dateField.equals(p)) {
			dataValidationHelper.validatePastDateField(dateField);
		}
	}


	public GridLayout getRegistrationGridLayout() {
		return registrationGridLayout;
	}

	public void setRegistrationGridLayout(GridLayout registrationGridLayout) {
		this.registrationGridLayout = registrationGridLayout;
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

	public IBusinessEngineerService getBusinessEngineerService() {
		return businessEngineerService;
	}

	public void setBusinessEngineerService(
			IBusinessEngineerService businessEngineerService) {
		this.businessEngineerService = businessEngineerService;
	}

	/**
	 * @return the dataValidationHelper
	 */
	public DataValidationHelper getDataValidationHelper() {
		return dataValidationHelper;
	}

	/**
	 * @param dataValidationHelper the dataValidationHelper to set
	 */
	public void setDataValidationHelper(DataValidationHelper dataValidationHelper) {
		this.dataValidationHelper = dataValidationHelper;
	}


}

