package com.novedia.talentmap.web.ui.registration;

import java.util.Vector;

import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.services.impl.RegistrationService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.ui.formFactory.RegistrationFormFieldFactory;
import com.novedia.talentmap.web.util.CUtils;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;


public class RegistrationForm extends FormLayout {

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
	 * Default constructor
	 */
	public RegistrationForm(){
		super();
	}
	
	public RegistrationForm buildRegistrationFormView(){
		removeAllComponents();
		buildMain();
		return this;
	}
	
	

	/**
	 * Build the class RegistrationForm.java
	 * 
	 * @param fieldOrderMission
	 * @param missionForm
	 * @param missionFormLayout
	 */
//	public RegistrationForm(Form registrationForm,
//			GridLayout registrationFormLayout,
//			IRegistrationService registrationService,
//			IBusinessEngineerService businessEngineerService) {
//		super();
//		this.registrationForm = registrationForm;
//		this.registrationFormLayout = registrationFormLayout;
//		this.registrationService = registrationService;
//		this.businessEngineerService = businessEngineerService;
//
//		buildMain();
//	}
	
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
		
		this.fieldOrderRegistration = new Vector<Object>(ConstantsEnglish.FIELD_ORDER_REGISTRATION.length);
		CUtils.setOrderForm(this.fieldOrderRegistration, ConstantsEnglish.FIELD_ORDER_REGISTRATION);
		
		this.registrationForm.setFormFieldFactory(new RegistrationFormFieldFactory(this.registrationService, this.businessEngineerService, this));
		
		BeanItem<Item> registrationBean = new BeanItem(new Registration());
		this.registrationForm.setItemDataSource(registrationBean, this.fieldOrderRegistration);

		this.registrationForm.setImmediate(true);
		
		addComponent(this.registrationForm);
	}
	
	/**
	 * Controle la validité du champ Login : Vérifie que ce champ n'est pas déjà en base
	 */
	public void validateLogin() {
		Field fieldLogin = this.getRegistrationForm().getField(ConstantsEnglish.REGISTRATION_LOGIN_FIELD);
		String login;
		if (fieldLogin != null && fieldLogin.getValue() != "") {
			login = (String) fieldLogin.getValue();
			try {
				RegistrationService service = (RegistrationService)this.registrationService;
				int nbFound = service.countLogin(login);
				if(nbFound>0) {
					String message = ConstantsEnglish.REGISTRATION_ERROR_LOGIN_EXISTS1 + login + ConstantsEnglish.REGISTRATION_ERROR_LOGIN_EXISTS2;
					getWindow().showNotification(message);
					fieldLogin.focus();
					fieldLogin.setValue("");
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Controle la validité du champ email : Vérifie que ce champ n'est pas déjà en base
	 */
	public void validateEmail() {
		Field fieldEMail = this.getRegistrationForm().getField(ConstantsEnglish.REGISTRATION_EMAIL_FIELD);
		String eMail;
		if (fieldEMail != null && fieldEMail.getValue() != "") {
			eMail = (String) fieldEMail.getValue();
			RegistrationService service = (RegistrationService)this.registrationService;
			Integer nbColleagueFound = 0;
			try {
				nbColleagueFound = service.countMail(eMail);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(nbColleagueFound > 0) {
				String message = ConstantsEnglish.REGISTRATION_ERROR_EMAIL_EXISTS1 + eMail + ConstantsEnglish.REGISTRATION_ERROR_EMAIL_EXISTS2;
				getWindow().showNotification(message);
				fieldEMail.focus();
				fieldEMail.setValue("");
			}
		}
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

	public IBusinessEngineerService getBusinessEngineerService() {
		return businessEngineerService;
	}

	public void setBusinessEngineerService(
			IBusinessEngineerService businessEngineerService) {
		this.businessEngineerService = businessEngineerService;
	}

	public GridLayout getRegistrationFormLayout() {
		return registrationFormLayout;
	}

	public void setRegistrationFormLayout(GridLayout registrationFormLayout) {
		this.registrationFormLayout = registrationFormLayout;
	}
	
	

}