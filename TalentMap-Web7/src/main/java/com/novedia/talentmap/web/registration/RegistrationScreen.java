package com.novedia.talentmap.web.registration;

import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.web.login.LoginScreen;
import com.novedia.talentmap.web.util.exceptions.TalentMapSecurityException;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Reindeer;


@SuppressWarnings("serial")
public class RegistrationScreen extends HorizontalLayout implements ClickListener{
	
	private Panel registrationPanel;
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
	private Button save;
	private Button logIn;
	
	 /**
     * TalentMap service
     */
    private IRegistrationService registrationService;
    private IBusinessEngineerService businessEngineerService;
    
    private LoginScreen loginScreen;
	
	
	public RegistrationScreen(){
		super();
	}
	
	public HorizontalLayout buildRegistrationView(){
		registrationPanel.removeAllComponents();
		HorizontalLayout header = new HorizontalLayout();
		//header.addComponent(new Label("Registration"));
		
		Label registrationLabel = new Label();
		registrationLabel.setCaption("Registration");
		registrationLabel.addStyleName("titleStyle");
		header.addComponent(registrationLabel);
		
		GridLayout content = new GridLayout(2,8);
		
		title.setCaption("Title");
		title.addItem("Mr.");
		title.addItem("Mrs.");
		title.setMultiSelect(false);
		title.setRequired(true);
		content.addComponent(title);
		
		nameField.setCaption("Name");
		nameField.setRequired(true);
		content.addComponent(nameField);
		
		firstNameField.setCaption("First Name");
		firstNameField.setRequired(true);
		content.addComponent(firstNameField);
		
		passwordField.setCaption("Password");
		passwordField.setRequired(true);
		content.addComponent(passwordField);
		
		
		confirmPasswordField.setCaption("Confirm password");
		confirmPasswordField.setRequired(true);
		content.addComponent(confirmPasswordField);
		
		loginField.setCaption("Choose a login");
		loginField.setRequired(true);
		content.addComponent(loginField);
		
		emailField.setCaption("Email");
		emailField.setRequired(true);
		content.addComponent(emailField);
		
		phoneField.setCaption("Phone");
		content.addComponent(phoneField);
		
		dateField.setCaption("Date of hire");
		dateField.setRequired(true);
		content.addComponent(dateField);
		
		jobField.setCaption("Job title");
		content.addComponent(jobField);
		
		experienceField.setCaption("Years of experience");
		experienceField.setRequired(true);
		content.addComponent(experienceField);
		
		businessEngineerField.setCaption("Business engineer");
		content.addComponent(businessEngineerField);
		
		managerField.setCaption("Manager");
		content.addComponent(managerField);
		
		save =  new Button("Save");
		save.setId("saveButton");
		content.addComponent(save);
		
		logIn = new Button("Log In");
		logIn.setStyleName(Reindeer.BUTTON_LINK);
		logIn.setCaption("Log In");
		logIn.addClickListener(this);
		logIn.setId("logIn");
		content.addComponent(logIn);
		
		registrationPanel.addComponent(header);
		registrationPanel.addComponent(content);
		registrationPanel.setWidth(null);
		registrationPanel.addStyleName("registrationPanel");
		
		
		addComponent(registrationPanel);
		setComponentAlignment(registrationPanel, Alignment.MIDDLE_CENTER);
		setSizeFull();
		
		return this;
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		

		if (event.getButton().equals(logIn)) {  //Log In Button
					getParent().getUI().setContent(loginScreen.buildLoginView());
		}
		
	}

	public Panel getRegistrationPanel() {
		return registrationPanel;
	}

	public void setRegistrationPanel(Panel registrationPanel) {
		this.registrationPanel = registrationPanel;
	}

	public TextField getNameField() {
		return nameField;
	}

	public void setNameField(TextField nameField) {
		this.nameField = nameField;
	}

	public OptionGroup getTitle() {
		return title;
	}

	public void setTitle(OptionGroup title) {
		this.title = title;
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

	public Button getSave() {
		return save;
	}

	public void setSave(Button save) {
		this.save = save;
	}

	public LoginScreen getLoginScreen() {
		return loginScreen;
	}

	public void setLoginScreen(LoginScreen loginScreen) {
		this.loginScreen = loginScreen;
	}

}
