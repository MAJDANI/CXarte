package com.novedia.talentmap.web.ui.registration;

import java.util.Vector;

import com.novedia.talentmap.model.entity.BusinessEngineer;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IRegistrationService;
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
	public RegistrationForm() {
		super();
	}

	/**
	 * Build the registration Form View
	 * 
	 * @return RegistrationForm object
	 */
	public RegistrationForm buildRegistrationFormView() {
		registrationForm.removeAllProperties();
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void buildRegistrationForm() {

		this.registrationForm.setLayout(this.registrationFormLayout);

		this.fieldOrderRegistration = new Vector<Object>(
				ConstantsEnglish.FIELD_ORDER_REGISTRATION.length);
		CUtils.setOrderForm(this.fieldOrderRegistration,
				ConstantsEnglish.FIELD_ORDER_REGISTRATION);

		this.registrationForm
				.setFormFieldFactory(new RegistrationFormFieldFactory(
						this.registrationService, this.businessEngineerService,
						this));

		BusinessEngineer defaultBusinessEngineer = BusinessEngineer.builder()
				.id(ConstantsEnglish.DEFAULT_SELECTED_CHOICE).build();
		Registration defaultRegistration = Registration.Builder.builder()
				.profileId(ConstantsEnglish.DEFAULT_SELECTED_CHOICE)
				.managerId(ConstantsEnglish.DEFAULT_SELECTED_CHOICE)
				.businessEngineer(defaultBusinessEngineer).build();

		BeanItem<Item> registrationBean = new BeanItem(defaultRegistration);
		this.registrationForm.setItemDataSource(registrationBean,
				this.fieldOrderRegistration);

		this.registrationForm.setImmediate(true);

		addComponent(this.registrationForm);
	}

	/**
	 * Controle la validité du champ Login : Vérifie que ce champ n'est pas déjà
	 * en base Cette méthode n'est pas "appelée" directement, on indique son
	 * appel dans RegistrationFormFieldFactory, au moment du .addListener()
	 */
	public void validateLogin() {
		Field fieldLogin = this.getRegistrationForm().getField(
				ConstantsEnglish.REGISTRATION_LOGIN_FIELD);
		String login;
		if (fieldLogin != null && fieldLogin.getValue() != "") {
			login = (String) fieldLogin.getValue();
			try {
				int nbFound = registrationService.countLogin(login);
				if (nbFound > 0) {
					String message = ConstantsEnglish.REGISTRATION_ERROR_LOGIN_EXISTS1
							+ login
							+ ConstantsEnglish.REGISTRATION_ERROR_LOGIN_EXISTS2;
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
	 * Controle la validité du champ email : Vérifie que ce champ n'est pas déjà
	 * en base Cette méthode n'est pas "appelée" directement, on indique son
	 * appel dans RegistrationFormFieldFactory, au moment du .addListener()
	 */
	public void validateEmail() {
		Field fieldEMail = this.getRegistrationForm().getField(
				ConstantsEnglish.REGISTRATION_EMAIL_FIELD);
		String eMail;
		if (fieldEMail != null && fieldEMail.getValue() != "") {
			eMail = (String) fieldEMail.getValue();
			Integer nbColleagueFound = 0;
			try {
				nbColleagueFound = registrationService.countMail(eMail);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (nbColleagueFound > 0) {
				String message = ConstantsEnglish.REGISTRATION_ERROR_EMAIL_EXISTS1
						+ eMail
						+ ConstantsEnglish.REGISTRATION_ERROR_EMAIL_EXISTS2;
				getWindow().showNotification(message);
				fieldEMail.focus();
				fieldEMail.setValue("");
			}
		}
	}

	/**
	 * Get the registrationForm
	 * 
	 * @return registrationForm
	 */
	public Form getRegistrationForm() {
		return registrationForm;
	}

	/**
	 * Set the registrationForm
	 * 
	 * @param registrationForm
	 *            registrationForm to set
	 */
	public void setRegistrationForm(Form registrationForm) {
		this.registrationForm = registrationForm;
	}

	/**
	 * Get the registrationService
	 * 
	 * @return registrationService
	 */
	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	/**
	 * Set the registrationService
	 * 
	 * @param registrationService
	 *            registrationService to set
	 */
	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	/**
	 * Get the businessEngineerService
	 * 
	 * @return businessEngineerService
	 */
	public IBusinessEngineerService getBusinessEngineerService() {
		return businessEngineerService;
	}

	/**
	 * Set the businessEngineerService
	 * 
	 * @param businessEngineerService
	 *            businessEngineerService to set
	 */
	public void setBusinessEngineerService(
			IBusinessEngineerService businessEngineerService) {
		this.businessEngineerService = businessEngineerService;
	}

	/**
	 * Get the registrationFormLayout
	 * 
	 * @return registrationFormLayout
	 */
	public GridLayout getRegistrationFormLayout() {
		return registrationFormLayout;
	}

	/**
	 * Set the registrationFormLayout
	 * 
	 * @param registrationFormLayout
	 *            registrationFormLayout to set
	 */
	public void setRegistrationFormLayout(GridLayout registrationFormLayout) {
		this.registrationFormLayout = registrationFormLayout;
	}

}