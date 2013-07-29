package com.novedia.talentmap.web.registration;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class RegistrationForm extends FormLayout implements ClickListener {
	
	  /**
     * Vaadin components
     */
	private OptionGroup title;
	private TextField nameField;
	private TextField firstNameField;
	private PasswordField passwordField;
	private PasswordField confirmPasswordField;
	private TextField loginField;
	private TextField emailField;
	private TextField phoneField;
	private PopupDateField dateField;
	private ComboBox jobField;
	private TextField experienceField;
	private ComboBox businessEngineerField;
	private ComboBox managerField;
	private GridLayout registrationFormLayout;
	
	 /**
     * Default constructor
     */
    public RegistrationForm() {
	super();
    }

	public RegistrationForm buildRegistrationFormView(){
		
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
		this.registrationFormLayout.setMargin(true);
		this.registrationFormLayout.setSpacing(true);
		this.registrationFormLayout.setColumns(2);
		this.registrationFormLayout.setRows(8);
    }
    
    public void buildRegistrationForm(){
		title.setCaption("Title");
		title.addItem("Mr.");
		title.addItem("Mrs.");
		title.setMultiSelect(false);
		title.setRequired(true);
		addComponent(title);
		
		nameField.setCaption("Name");
		nameField.setRequired(true);
		addComponent(nameField);
		
		firstNameField.setCaption("First Name");
		firstNameField.setRequired(true);
		addComponent(firstNameField);
		
		passwordField.setCaption("Password");
		passwordField.setRequired(true);
		addComponent(passwordField);
		
		
		confirmPasswordField.setCaption("Confirm password");
		confirmPasswordField.setRequired(true);
		addComponent(confirmPasswordField);
		
		loginField.setCaption("Choose a login");
		loginField.setRequired(true);
		addComponent(loginField);
		
		emailField.setCaption("Email");
		emailField.setRequired(true);
		addComponent(emailField);
		
		phoneField.setCaption("Phone");
		addComponent(phoneField);
		
		dateField.setCaption("Date of hire");
		dateField.setRequired(true);
		addComponent(dateField);
		
		jobField.setCaption("Job title");
		jobField.setRequired(true);
//		buildJobList();
		addComponent(jobField);
		
		experienceField.setCaption("Years of experience");
		experienceField.setRequired(true);
		addComponent(experienceField);
		
		businessEngineerField.setCaption("Business engineer");
//		buildEngineerList();
		addComponent(businessEngineerField);
		
		managerField.setCaption("Manager");
//		buildManagerList();
		addComponent(managerField);
		
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
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
	

}
