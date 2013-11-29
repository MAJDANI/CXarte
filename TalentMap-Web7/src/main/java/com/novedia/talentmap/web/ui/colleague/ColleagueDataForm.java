package com.novedia.talentmap.web.ui.colleague;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.BusinessEngineer;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.helpers.DataValidationHelper;
import com.novedia.talentmap.web.utils.ComponentsClass;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.ConstantsDB;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class ColleagueDataForm extends FormLayout implements BlurListener,  ValueChangeListener {

	private Colleague colleagueTmp;

	private Colleague colleague;

	/**
	 * TalentMap service
	 */
	private IRegistrationService registrationService;
	private IColleagueService colleagueService;
	private IBusinessEngineerService businessEngineerService;

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

	private ResourceBundle resourceBundle;
	private DataValidationHelper dataValidationHelper;
	
	private Panel personalDataContentPanel = new Panel();

	
	/**
	 * Default constructor
	 */
	public ColleagueDataForm() {
		super();
	}

	public ColleagueDataForm buildColleagueDataFormView() {
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES , locale);
		removeAllComponents();
		buildMain();
		return this;
	}

	private void buildMain() {
		try {
			buildLayout();
			buildColleagueDataForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buildLayout() {
		personalDataContentPanel.removeAllComponents();
		personalDataContentPanel.addStyleName("contentPanel");	
		colleagueFormLayout.removeAllComponents();
		colleagueFormLayout.setSpacing(true);
		this.colleagueFormLayout.setColumns(3);
		this.colleagueFormLayout.setRows(3);
		this.personalDataContentPanel.setCaption(resourceBundle.getString("personal.data.content.panel.caption"));

	}

	private void buildColleagueDataForm() {
		removeAllComponents();
		nameField.setCaption(resourceBundle.getString("name.field.caption"));
		nameField.setRequired(true);
		nameField.setImmediate(true);
		nameField.setValidationVisible(true);
		nameField.setStyleName(ComponentsClass.TEXTFIELD_COLLEAGUE_DATA_FORM_CLASS);
		nameField.setId(ComponentsId.LAST_NAME_ID);
		nameField.setWidth("190px");
		nameField.setMaxLength(ConstantsDB.COLLEAGUE_LAST_NAME_MAX_LENGTH);
		nameField.addValueChangeListener(this);
		colleagueFormLayout.addComponent(nameField);

		firstNameField.setCaption(resourceBundle.getString("firstName.field.caption"));
		firstNameField.setRequired(true);
		firstNameField.setImmediate(true);
		firstNameField.setValidationVisible(true);
		firstNameField.setStyleName(ComponentsClass.TEXTFIELD_COLLEAGUE_DATA_FORM_CLASS);
		firstNameField.setId(ComponentsId.FIRST_NAME_ID);
		firstNameField.setWidth("190px");
		firstNameField.setMaxLength(ConstantsDB.COLLEAGUE_FIRST_NAME_MAX_LENGTH);
		firstNameField.addStyleName("spacerInfo");
		firstNameField.addValueChangeListener(this);
		colleagueFormLayout.addComponent(firstNameField);

		jobField.setCaption(resourceBundle.getString("job.field.caption"));
		buildJobList();
		jobField.setRequired(true);
		jobField.setRequiredError(resourceBundle.getString("job.field.error.caption"));
		jobField.addValidator(new BeanValidator(Colleague.class,ComponentsId.PROFILE_ID));
		jobField.setImmediate(true);
		jobField.setValidationVisible(true);
		jobField.addBlurListener(this);
		jobField.addStyleName(ComponentsClass.SELECT_COLLEAGUE_DATA_FORM_CLASS);
		jobField.setId(ComponentsId.PROFILE_ID);
		jobField.addStyleName("spacerInfo");
		colleagueFormLayout.addComponent(jobField);

		emailField.setCaption(resourceBundle.getString("email.field.caption"));
		emailField.setRequired(true);
		emailField.setImmediate(true);
		emailField.setValidationVisible(true);
		emailField.setStyleName(ComponentsClass.TEXTFIELD_COLLEAGUE_DATA_FORM_CLASS);
		emailField.setId(ComponentsId.EMAIL_ID);
		emailField.setWidth("190px");
		emailField.setMaxLength(ConstantsDB.COLLEAGUE_EMAIL_MAX_LENGTH);
		emailField.addStyleName("spacerTop");
		emailField.addValueChangeListener(this);
		colleagueFormLayout.addComponent(emailField);

		phoneField.setCaption(resourceBundle.getString("phone.field.caption"));
		phoneField.setInputPrompt(resourceBundle.getString("phone.field.default"));
		phoneField.setNullRepresentation("");
		phoneField.setImmediate(true);
		phoneField.setStyleName(ComponentsClass.TEXTFIELD_COLLEAGUE_DATA_FORM_CLASS);
		phoneField.setId(ComponentsId.PHONE_ID);
		phoneField.setWidth("190px");
		phoneField.addStyleName("spacerInfo spacerTop");
		phoneField.setMaxLength(ConstantsDB.COLLEAGUE_PHONE_MAX_LENGTH);
		phoneField.addValueChangeListener(this);
		colleagueFormLayout.addComponent(phoneField);

		dateField.setCaption(resourceBundle.getString("date.field.caption"));
		dateField.setRequired(true);
		dateField.setImmediate(true);
		dateField.setValidationVisible(true);
		dateField.addBlurListener(this);
		dateField.setId(ComponentsId.EMPLOYMENT_DATE_ID);
		dateField.addStyleName("spacerInfo spacerTop");
		dateField.addValueChangeListener(this);
		colleagueFormLayout.addComponent(dateField);

		experienceField.setCaption(resourceBundle.getString("experience.field.caption"));
		experienceField.setRequired(true);
		experienceField.setImmediate(true);
		experienceField.setValidationVisible(true);
		experienceField.setInputPrompt(Constants.EXPERIENCE_FORMAT);
		experienceField.setStyleName(ComponentsClass.TEXTFIELD_COLLEAGUE_DATA_FORM_CLASS);
		experienceField.setId(ComponentsId.EXPERIENCE_ID);
		experienceField.setWidth("50px");
		experienceField.setMaxLength(ConstantsDB.COLLEAGUE_EXPERIENCE_MAX_LENGTH);
		experienceField.addStyleName("spacerTop");
		experienceField.addValueChangeListener(this);
		colleagueFormLayout.addComponent(experienceField);

		businessEngineerField.setCaption(resourceBundle.getString("businessEngineer.field.caption"));
		buildEngineerList();
		businessEngineerField.addBlurListener(this);
		businessEngineerField.addStyleName(ComponentsClass.SELECT_COLLEAGUE_DATA_FORM_CLASS);
		businessEngineerField.setId(ComponentsId.BUSINESS_ENGINEER_ID);
		businessEngineerField.addStyleName("spacerInfo spacerTop");
		colleagueFormLayout.addComponent(businessEngineerField);

		managerField.setCaption(resourceBundle.getString("manager.field.caption"));
		buildManagerList();
		managerField.addBlurListener(this);
		managerField.addStyleName(ComponentsClass.SELECT_COLLEAGUE_DATA_FORM_CLASS);
		managerField.setId(ComponentsId.MANAGER_ID);
		managerField.addStyleName("spacerInfo spacerTop");
		colleagueFormLayout.addComponent(managerField);
		Authentication authentication = TalentMapApplication.getCurrent().getAuthentication();
		colleagueTmp = colleagueService.getColleague(authentication.getColleagueId());
		colleague = colleagueService.getColleague(authentication.getColleagueId());
		binder = new BeanFieldGroup<Colleague>(Colleague.class);
		binder.setItemDataSource(colleagueTmp);
		binder.setBuffered(false);
		binder.bindMemberFields(this);
		//ICI
		personalDataContentPanel.addComponent(colleagueFormLayout);
		personalDataContentPanel.setWidth("100%");
		colleagueFormLayout.setWidth("100%");
		addComponent(personalDataContentPanel);
		setComponentAlignment(personalDataContentPanel, Alignment.MIDDLE_CENTER);
	}
	
	@Override
	public void blur(BlurEvent event) {
		Component p = event.getComponent();
		if (dateField.equals(p)) {
			dataValidationHelper.validatePastDateField(dateField);
		} else {
			if (!validateColleagueDataForm()) {
				Notification.show(resourceBundle.getString("missing.fields.msg"),Notification.Type.WARNING_MESSAGE);
			} else {
				colleagueTmp.setTitle(colleague.getTitle());
				colleagueTmp.setId(colleague.getId());
				this.colleagueService.saveColleague(colleagueTmp);
			}
		}
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		Property p = event.getProperty();
		boolean isValid = false;
		if(nameField.equals(p)) {
			isValid = dataValidationHelper.validateLastName(nameField);
		} else if(firstNameField.equals(p)) {
			isValid = dataValidationHelper.validateFirstName(firstNameField);
		} else if(phoneField.equals(p)) {
			isValid = dataValidationHelper.validatePhone(phoneField);
		} else if(emailField.equals(p)) {
			isValid = dataValidationHelper.validateEmailForOtherCO(emailField, colleague);
		} else if(experienceField.equals(p)) {
			isValid = dataValidationHelper.validateExperience(experienceField);
		} else if(dateField.equals(p)) {
			isValid = dataValidationHelper.validatePastDateField(dateField);
		}
		if(isValid) {
			this.colleagueService.saveColleague(colleagueTmp);
		}
	}

	/**
	 * Test the colleagueDataForm validity
	 * 
	 * @return boolean
	 */
	private boolean validateColleagueDataForm() {

		boolean isValidRegistration = true;

		if (!this.binder.isValid()) {
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
