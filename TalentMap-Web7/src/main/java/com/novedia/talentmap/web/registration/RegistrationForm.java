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
//	private FieldGroup binder;
	private BeanFieldGroup<Registration> binder;
	
	@PropertyId("title")
	private OptionGroup title;
	
	@PropertyId("lastName")
	private TextField nameField;
	
	@PropertyId("firstName")
	private TextField firstNameField;
	
	@PropertyId("password")
	private PasswordField passwordField;
	
	@PropertyId("passwordConfirm")
	private PasswordField confirmPasswordField;
	
	@PropertyId("login")
	private TextField loginField;
	
	@PropertyId("email")
	private TextField emailField;
	
	@PropertyId("phone")
	private TextField phoneField;
	
	@PropertyId("employmentDate")
	private PopupDateField dateField;
	
	@PropertyId("profileId")
	private ComboBox jobField;
	
	@PropertyId("experience")
	private TextField experienceField;
	
	@PropertyId("businessEngineer")
	private ComboBox businessEngineerField;
	
	@PropertyId("managerId")
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
		title.addItem("Mr.");
		title.addItem("Mrs.");
		title.setMultiSelect(false);
		title.setRequired(true);
		title.setRequiredError("Give title");
		title.addStyleName("horizontal");
		registrationFormLayout.addComponent(title);

		loginField.setCaption("Choose a login");
		loginField.setRequired(true);
		loginField.setRequiredError("Give your last name");
		loginField
				.addValidator(new BeanValidator(Registration.class, "login"));
		loginField.setImmediate(true);
		loginField.setValidationVisible(true);
		loginField.setInputPrompt("Type your login");
		loginField.setNullRepresentation("");
		registrationFormLayout.addComponent(loginField);
		
		nameField.setCaption(Constants.NAME);
		nameField.setRequired(true);
		nameField.setRequiredError("Give your last name");
		nameField
				.addValidator(new BeanValidator(Registration.class, "lastName"));
		nameField.setImmediate(true);
		nameField.setValidationVisible(true);
		nameField.setInputPrompt("Type your name");
		nameField.setNullRepresentation("");
		registrationFormLayout.addComponent(nameField);

		firstNameField.setCaption("First Name");
		firstNameField.setRequired(true);
		firstNameField.setRequiredError("Give your first name");
		firstNameField.addValidator(new BeanValidator(Registration.class,
				"firstName"));
		firstNameField.setImmediate(true);
		firstNameField.setValidationVisible(true);
		firstNameField.setInputPrompt("Type your first name");
		firstNameField.setNullRepresentation("");
		registrationFormLayout.addComponent(firstNameField);

		passwordField.setCaption(Constants.PASSWORD);
		passwordField.setRequired(true);
		passwordField.setRequiredError("Give your password");
		passwordField.addValidator(new BeanValidator(Registration.class,
				"password"));
		passwordField.setImmediate(true);
		passwordField.setValidationVisible(true);
		passwordField.setInputPrompt("Type your password");
		passwordField.setNullRepresentation("");
		registrationFormLayout.addComponent(passwordField);

		confirmPasswordField.setCaption(Constants.CONFIRM_PASSWORD);
		confirmPasswordField.setRequired(true);
		confirmPasswordField.setRequiredError("Give your confirmed password");
		confirmPasswordField.addValidator(new BeanValidator(Registration.class,
				"passwordConfirm"));
		confirmPasswordField.setImmediate(true);
		confirmPasswordField.setValidationVisible(true);
		confirmPasswordField.setInputPrompt("Confirm your password");
		confirmPasswordField.setNullRepresentation("");
		registrationFormLayout.addComponent(confirmPasswordField);

		emailField.setCaption(Constants.EMAIL);
		emailField.setRequired(true);
		emailField.setRequiredError("Give your email");
		emailField.addValidator(new BeanValidator(Registration.class, "email"));
		emailField.setImmediate(true);
		emailField.setValidationVisible(true);
		emailField.setInputPrompt("Type your Email");
		emailField.setNullRepresentation("");
		registrationFormLayout.addComponent(emailField);

		phoneField.setCaption(Constants.PHONE);
		phoneField.setInputPrompt("Type your phone number");
		phoneField.setNullRepresentation("");
		registrationFormLayout.addComponent(phoneField);

		dateField.setCaption(Constants.DATE_OF_HIRE);
		dateField.setRequired(true);
		dateField.setRequiredError("Give your date of employement");
		dateField.addValidator(new BeanValidator(Registration.class,
				"employmentDate"));
		dateField.setImmediate(true);
		dateField.setValidationVisible(true);
		dateField.setInputPrompt("DD/MM/YY");
		registrationFormLayout.addComponent(dateField);

		jobField.setCaption(Constants.JOB_TITLE);
		jobField.setRequired(true);
		jobField.setRequiredError("Give your job title");
		jobField.addValidator(new BeanValidator(Registration.class, "profileId"));
		jobField.setImmediate(true);
		jobField.setValidationVisible(true);
		jobField.setInputPrompt("Select your job title");
		buildJobList();
		registrationFormLayout.addComponent(jobField);

		experienceField.setCaption(Constants.YEARS_OF_EXPERIENCE);
		experienceField.setRequired(true);
		experienceField.setRequiredError("Give your years of experience");
		experienceField.addValidator(new BeanValidator(Registration.class,
				"experience"));
		experienceField.setImmediate(true);
		experienceField.setValidationVisible(true);
		experienceField.setInputPrompt("ex : 2");
		experienceField.setNullRepresentation("");
		registrationFormLayout.addComponent(experienceField);

		businessEngineerField.setCaption(Constants.BUSINESS_ENGINEER);
		businessEngineerField.setInputPrompt("Select your business engineer");
		buildEngineerList();
		registrationFormLayout.addComponent(businessEngineerField);

		managerField.setCaption(Constants.MANAGER);
		managerField.setInputPrompt("Select your manager");
		buildManagerList();
		registrationFormLayout.addComponent(managerField);

		// Registration registration = new Registration();
		// BeanItem<Registration> item = new BeanItem<Registration>
		// (registration);
		// FieldGroup binder = new FieldGroup(item);
		// binder.setFieldFactory(new RegistrationFieldGroupFactory());
		// binder.bind(nameField, "name");
		registration = Registration.Builder.builder().title("Mr.").build();
		// BeanItem<Registration> item = new BeanItem<Registration>
		// (registration);
		// binder = new FieldGroup(item);
		// binder.setBuffered(false);
		// binder.bindMemberFields(this);
		// registrationFormLayout.addComponent(binder.buildAndBind("Name",
		// "lastName"));

		binder = new BeanFieldGroup<Registration>(
				Registration.class);
		binder.setItemDataSource(registration);
		// registrationFormLayout.addComponent(binder.buildAndBind("Name",
		// "lastName"));
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
