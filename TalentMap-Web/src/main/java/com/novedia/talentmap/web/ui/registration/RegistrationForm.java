package com.novedia.talentmap.web.ui.registration;

import java.util.Vector;

import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.web.ui.formFactory.RegistrationFormFieldFactory;
import com.novedia.talentmap.web.util.CUtils;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;


public class RegistrationForm extends FormLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4588382289973469498L;

	/**
	 * POJO
	 */
	private Vector<Object> fieldOrderRegistration;
	
	/**
	 * Vaadin components
	 */
	private Form registrationForm;
	private GridLayout registrationFormLayout;
	
	
	/**
	 * TalentMap service
	 */
	private IRegistrationService registrationService;
	private IBusinessEngineerService businessEngineerService;

	
	
	/**
	 * Constants
	 */
	public static final Object[] NAME_FIELD_REGISTRATION = new Object[] { "Nom", "Prénom","Mot de passe","Confirmer le mot de passe",
		"Email", "Téléphone", "Date d'embauche", "Profile", "Années d'expérience", "Ingénieur d'affaire",
		 "Manager" };
	public static final Object[] FIELD_ORDER_REGISTRATION = new Object[] { "lastName", "firstName","password","passwordConfirm","email",
		"phone", "employmentDate", "profileId", "experience", "businessEngineer", "managerId" };

	
	/**
	 * Build the class RegistrationForm.java
	 * 
	 * @param fieldOrderMission
	 * @param missionForm
	 * @param missionFormLayout
	 */
	public RegistrationForm(Form registrationForm,
			GridLayout registrationFormLayout,
			IRegistrationService registrationService) {
		super();
		this.registrationForm = registrationForm;
		this.registrationFormLayout = registrationFormLayout;
		this.registrationService = registrationService;

		buildMain();
	}
	
	public void buildMain() {

		try {
			buildSignInLayout();
			buildRegistrationForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void buildSignInLayout(){
		this.registrationFormLayout.setMargin(true);
		this.registrationFormLayout.setSpacing(true);
		this.registrationFormLayout.setColumns(2);
		this.registrationFormLayout.setRows(8);
	}
	
	public void buildRegistrationForm(){

		this.registrationForm.setLayout(this.registrationFormLayout);
		
		this.fieldOrderRegistration = new Vector<Object>(FIELD_ORDER_REGISTRATION.length);
		CUtils.setOrderForm(this.fieldOrderRegistration, FIELD_ORDER_REGISTRATION);
		
		this.registrationForm.setFormFieldFactory(new RegistrationFormFieldFactory(this.registrationService, this.businessEngineerService));
		
		BeanItem<Item> registrationBean = new BeanItem(new Registration());
		this.registrationForm.setItemDataSource(registrationBean, this.fieldOrderRegistration);

		this.registrationForm.setImmediate(true);
		
		addComponent(this.registrationForm);
	}
	
	
	public Form getRegistrationForm() {
		return registrationForm;
	}

	public void setRegistrationForm(Form registrationForm) {
		this.registrationForm = registrationForm;
	}

	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}

}