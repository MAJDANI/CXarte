package com.novedia.talentmap.web.registration;

import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IRegistrationService;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class RegistrationFieldGroupFactory implements FieldGroupFieldFactory {
	
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
//    public RegistrationFieldGroupFactory(
//	    IRegistrationService registrationService,
//	    IBusinessEngineerService businessEngineerService, Object listener) {
//	this.registrationService = registrationService;
//	this.businessEngineerService = businessEngineerService;
//	this.listener = listener;
//    }
    
    public RegistrationFieldGroupFactory(){
    	
    }
    
    @SuppressWarnings("rawtypes")
    @Override
	public <T extends Field> T createField(Class<?> dataType, Class<T> fieldType) {
    	OptionGroup title = new OptionGroup();
    	title.setCaption("Title");
		title.addItem("Mr.");
		title.addItem("Mrs.");
		title.setMultiSelect(false);
		title.setRequired(true);

		TextField nameField = new TextField();
		nameField.setCaption("Name");
		nameField.setRequired(true);
		
		TextField firstNameField = new TextField();
		firstNameField.setCaption("First Name");
		firstNameField.setRequired(true);
	
		PasswordField passwordField = new PasswordField();
		passwordField.setCaption("Password");
		passwordField.setRequired(true);
		
		
		PasswordField confirmPasswordField = new PasswordField();
		confirmPasswordField.setCaption("Confirm password");
		confirmPasswordField.setRequired(true);
		
		TextField loginField = new TextField();
		loginField.setCaption("Choose a login");
		loginField.setRequired(true);
		
		TextField emailField = new TextField();
		emailField.setCaption("Email");
		emailField.setRequired(true);
	
		TextField phoneField = new TextField();
		phoneField.setCaption("Phone");
		
		DateField dateField = new DateField();
		dateField.setCaption("Date of hire");
		dateField.setRequired(true);
	
		ComboBox jobField = new ComboBox();
		jobField.setCaption("Job title");
		jobField.setRequired(true);
//		buildJobList();
		
		TextField experienceField = new TextField();
		experienceField.setCaption("Years of experience");
		experienceField.setRequired(true);
		
		ComboBox businessEngineerField = new ComboBox();
		businessEngineerField.setCaption("Business engineer");
//		buildEngineerList();
		
		ComboBox managerField = new ComboBox();
		managerField.setCaption("Manager");
//		buildManagerList();
		
		return null;
	}

    

	

    public void setRegistrationService(IRegistrationService registrationService) {
	this.registrationService = registrationService;
    }

	

}
