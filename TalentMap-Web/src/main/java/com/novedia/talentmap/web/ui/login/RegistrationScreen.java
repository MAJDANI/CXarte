package com.novedia.talentmap.web.ui.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.ui.registration.RegistrationForm;
import com.novedia.talentmap.web.util.exceptions.TalentMapSecurityException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Registration Screen
 * @author y.rohr
 */
public class RegistrationScreen extends VerticalLayout implements ClickListener{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8033938929343369593L;

	/**
	 * The logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationScreen.class);
	
	/**
	 * Application
	 */
	private MyVaadinApplication myVaadinApplication;
	
	/**
	 * Vaadin component
	 */
	private Panel registrationPanel;
	private RegistrationForm registrationForm;
	private Button save;
	private Button cancel;
	private GridLayout registrationFormLayout;
	
	private AuthenticatedScreen authenticatedScreen;
	
	
	/**
	 * Default constructor
	 * @author b.tiomofofou
	 */
	public RegistrationScreen() {
		super();
	}
	
	
	/**
	 * Build the registration screen view
	 * @return view of registration
	 * @author b.tiomofofou
	 */
	public RegistrationScreen buildRegistrationScreenView(){
		removeAllComponents();
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Call Sign In Screen constructor");
		}
		//Panel for Registration
		registrationPanel = new Panel(ConstantsEnglish.REGISTRATION_PANEL_NAME);
		
		registrationPanel.setWidth(ConstantsEnglish.REGISTRATION_PANEL_WIDTH);
		
		//components initialisation
		registrationFormLayout = new GridLayout();
		save = new Button();
		cancel = new Button();
		registrationForm.setRegistrationFormLayout(registrationFormLayout);
		registrationForm = registrationForm.buildRegistrationFormView();
		
		
		registrationPanel.addComponent(registrationForm);
		buildButton();
		
		addComponent(registrationPanel);
		setComponentAlignment(registrationPanel, Alignment.MIDDLE_CENTER);

		HorizontalLayout footer = new HorizontalLayout();
		footer.setHeight(ConstantsEnglish.REGISTRATION_PANEL_FOOTER_HEIGHT);
		addComponent(footer);
		
		return this;
	}
	
	
	/**
	 * Build the buttons of the screen
	 */
	public void buildButton(){
		
		this.save.setCaption(ConstantsEnglish.SAVE_BUTTON_NAME);
		this.save.addListener(this);
		
		this.cancel.setCaption(ConstantsEnglish.CANCEL_BUTTON_NAME);
		this.cancel.addListener(this);
		
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setMargin(true);
		hLayout.setSpacing(true);
		hLayout.addComponent(save);
		hLayout.addComponent(cancel);
		registrationPanel.addComponent(hLayout);
		//this.registrationForm.setFooter(hLayout);
	}
	
	
	
	@Override
	public void buttonClick(ClickEvent event) {
		String button = event.getButton().getCaption();
		
		if (button.equalsIgnoreCase(ConstantsEnglish.SAVE_BUTTON_NAME)){
			//appel du service d'inscription
			BeanItem<Registration> registrationItem = (BeanItem<Registration>) this.registrationForm.getRegistrationForm().getItemDataSource();
			Registration registration = registrationItem.getBean();
			Authentication authentication = null;
			//On ne vérifie pas la validité du Login et de l'email ici parce que leur gestion se passe dans le
			//formulaire this.registrationForm.getRegistrationForm() : si l'un de ces champ n'est pas correct
			//on vide le champ, jusqu'à ce que la saisie soit correcte.
			if(!validateRegistrationForm()){
				myVaadinApplication.getMainWindow().showNotification(ConstantsEnglish.REGISTRATION_PANEL_MISSING_FIELDS);
			} else if ((!validatePassword())){
				myVaadinApplication.getMainWindow().showNotification(ConstantsEnglish.REGISTRATION_PANEL_PASSWORD_ERROR);
			} else {
				try {
					authentication = myVaadinApplication.register(registration);
					authenticatedScreen.setAuthentication(authentication);
					authenticatedScreen.setApplication(myVaadinApplication);
					myVaadinApplication.getMainWindow().setContent(authenticatedScreen.selectedViewAccordingToUserRoles());
		} catch (TalentMapSecurityException e) {
					myVaadinApplication.getMainWindow().showNotification(ConstantsEnglish.REGISTRATION_PANEL_USER_CREATION_ERROR);
				}
			}
		}
		
		else if (button.equalsIgnoreCase(ConstantsEnglish.CANCEL_BUTTON_NAME)){
			myVaadinApplication.getMainWindow().setContent(myVaadinApplication.buildLoginView());
		}
	
	}
	
	/**
	 * Test the registrationForm validity
	 * @return
	 */
	private boolean validateRegistrationForm(){
		
		boolean isValidRegistration = true;
		Form regisForm = this.registrationForm.getRegistrationForm();
		
		try {
			regisForm.validate();
		} catch (InvalidValueException e){
			isValidRegistration = false;
		}
		
		return isValidRegistration;
	}
	
	/**
	 * Test the password
	 * @return
	 */
	private boolean validatePassword(){
		
		boolean isValidPassword = true;
		Form regisForm = this.registrationForm.getRegistrationForm();
		String password = (String)regisForm.getField("password").getValue();
		String confirmPassword = (String)regisForm.getField("passwordConfirm").getValue();
		
		if (!password.equals(confirmPassword)){
			isValidPassword = false;
		}
		
		return isValidPassword;
	}	
	
	

	/**
	 * Get the registrationForm
	 * @return registrationForm
	 */
	public RegistrationForm getRegistrationForm() {
		return registrationForm;
	}

	/**
	 * Set the registrationForm
	 * @param registrationForm
	 */
	public void setRegistrationForm(RegistrationForm registrationForm) {
		this.registrationForm = registrationForm;
	}


	
	/**
	 * Get the main application
	 * @return
	 */
	public MyVaadinApplication getMyVaadinApplication() {
		return myVaadinApplication;
	}


	/**
	 * Set the main application
	 * @param myVaadinApplication
	 */
	public void setMyVaadinApplication(MyVaadinApplication myVaadinApplication) {
		this.myVaadinApplication = myVaadinApplication;
	}


	/**
	 * Get the authenticatedScreen
	 * @return
	 */
	public AuthenticatedScreen getAuthenticatedScreen() {
		return authenticatedScreen;
	}


	/**
	 * Set the authenticatedScreen
	 * @param authenticatedScreen
	 */
	public void setAuthenticatedScreen(AuthenticatedScreen authenticatedScreen) {
		this.authenticatedScreen = authenticatedScreen;
	}



}