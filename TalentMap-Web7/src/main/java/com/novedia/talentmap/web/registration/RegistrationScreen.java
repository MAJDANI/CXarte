package com.novedia.talentmap.web.registration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.services.IAuthenticationService;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.store.impl.ColleagueDao;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.login.AuthenticatedScreen;
import com.novedia.talentmap.web.login.LoginScreen;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class RegistrationScreen extends HorizontalLayout implements
		ClickListener {
	/**
	 * The logger
	 */
	private static Log logger = LogFactory.getLog(ColleagueDao.class);
	
	/**
	 * TalentMap service
	 */
	private IRegistrationService registrationService;
	private IAuthenticationService authenticationService;

	/**
	 * Vaadin component
	 */
	private Panel registrationPanel;
	private GridLayout registrationFormLayout;
	private RegistrationForm registrationForm;
	private Button save;
	private Button logIn;
	
	private AuthenticatedScreen authenticatedScreen;

	private LoginScreen loginScreen;

	public RegistrationScreen() {
		super();
	}

	public HorizontalLayout buildRegistrationScreenView() {
		registrationPanel.removeAllComponents();
		HorizontalLayout header = new HorizontalLayout();

		Label registrationLabel = new Label();
		registrationLabel.setCaption("Registration");
		registrationLabel.addStyleName("titleStyle");
		header.addComponent(registrationLabel);

		// components initialisation
		registrationFormLayout = new GridLayout();
		save = new Button("Save");
		logIn = new Button("Log In");

		registrationForm.setRegistrationFormLayout(registrationFormLayout);

		registrationPanel.addComponent(header);
		registrationForm = registrationForm.buildRegistrationFormView();
		registrationPanel.addComponent(registrationForm);
		buildButton();
		registrationPanel.setWidth(null);

		registrationPanel.addStyleName("registrationPanel");
		addComponent(registrationPanel);
		setComponentAlignment(registrationPanel, Alignment.MIDDLE_CENTER);
		setSizeFull();

		return this;
	}

	/**
	 * Build the buttons of the screen
	 */
	public void buildButton() {

		VerticalLayout vLayout = new VerticalLayout();

		save.setId("saveButton");
		save.setCaption(Constants.SAVE_BUTTON_NAME);
		save.addClickListener(this);
		vLayout.addComponent(save);
		vLayout.setComponentAlignment(save, Alignment.BOTTOM_RIGHT);

		logIn.setStyleName(Reindeer.BUTTON_LINK);
		logIn.setCaption("Log In");
		logIn.addClickListener(this);
		logIn.setId("logIn");

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);
		Label label = new Label(Constants.ALREADY_HAVE_AN_ACCOUNT_LABEL);
		label.addStyleName("haveAccountLabel");
		hLayout.addComponent(label);
		hLayout.addComponent(logIn);
		vLayout.addComponent(hLayout);
		vLayout.setComponentAlignment(hLayout, Alignment.BOTTOM_RIGHT);
		registrationPanel.addComponent(vLayout);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		Authentication authentication = null;
		
		backToLoginView(event);
		
		if (event.getButton().equals(save)) {
			if (!validateRegistrationForm()) {
				Notification.show(Constants.PANEL_MISSING_FIELDS,Notification.Type.WARNING_MESSAGE);
			} else if ((!validatePassword())) {
				Notification.show(Constants.REGISTRATION_PANEL_PASSWORD_ERROR,Notification.Type.WARNING_MESSAGE);
			}
			Registration registration = registrationForm.getRegistration();
			authentication = register(registration);
			if (authentication != null) {
				if(authentication != null){
					TalentMapApplication.getCurrent().setAuthentication(authentication);
					getParent().getUI().setContent(authenticatedScreen.selectedViewAccordingToUserRoles());
				}
			} else {
				Notification.show(Constants.REGISTRATION_PANEL_USER_CREATION_ERROR,Notification.Type.WARNING_MESSAGE);
			}
			if(logger.isDebugEnabled()){
				logger.debug(registrationForm.getRegistration().getLastName());
			}

		}
	}

	private void backToLoginView(ClickEvent event) {
		if (event.getButton().equals(logIn)) {
			getParent().getUI().setContent(loginScreen.buildLoginView());
		}
	}
	
	 /**
     * Register method
     * 
     * @param registration
     */
    public Authentication register(Registration registration)
	    throws DataAccessException {
	Authentication authenticate = null;
	try {
	    // Password encoding
	    String encodedPassword = authenticationService.encodePassword(registration
		    .getPassword());
	    registration.setPassword(encodedPassword);
	    // On positionne le Role par défaut CL Consultant :
	    registration.setRole(Authorization.Role.CL);
	    registrationService.addColleagueFromRegistration(registration);

	    CredentialToken credential = new CredentialToken();
	    credential.setLogin(registration.getLogin());
	    credential.setPassword(registration.getPassword());
	    authenticate = authenticationService.checkUser(credential);
	} catch (DataAccessException ex) {
	    if (logger.isErrorEnabled()) {
	    	logger.error("DataAccessException : ", ex);
	    }
	}
	return authenticate;
    }
	
    /**
     * Test the registrationForm validity
     * 
     * @return boolean
     */
    private boolean validateRegistrationForm() {

    	boolean isValidRegistration = true;
	
    	if(!registrationForm.getBinder().isValid()){
    		isValidRegistration = false;
    	}

    	return isValidRegistration;
    }

	/**
	 * Test the password
	 * 
	 * @return boolean
	 */
	private boolean validatePassword() {
		boolean isValidPassword = true;
		Registration registration = this.registrationForm.getRegistration();
		String password = registration.getPassword();
		String confirmPassword = registration.getPasswordConfirm();
		if (!password.equals(confirmPassword)) {
			isValidPassword = false;
		}

		return isValidPassword;
	}

	public Panel getRegistrationPanel() {
		return registrationPanel;
	}

	public void setRegistrationPanel(Panel registrationPanel) {
		this.registrationPanel = registrationPanel;
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

	public GridLayout getRegistrationFormLayout() {
		return registrationFormLayout;
	}

	public void setRegistrationFormLayout(GridLayout registrationFormLayout) {
		this.registrationFormLayout = registrationFormLayout;
	}

	public RegistrationForm getRegistrationForm() {
		return registrationForm;
	}

	public void setRegistrationForm(RegistrationForm registrationForm) {
		this.registrationForm = registrationForm;
	}

	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	public IAuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(
			IAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public AuthenticatedScreen getAuthenticatedScreen() {
		return authenticatedScreen;
	}

	public void setAuthenticatedScreen(AuthenticatedScreen authenticatedScreen) {
		this.authenticatedScreen = authenticatedScreen;
	}

}
