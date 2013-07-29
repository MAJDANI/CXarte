package com.novedia.talentmap.web.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.services.IAuthenticationService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.registration.RegistrationScreen;
import com.novedia.talentmap.web.util.exceptions.TalentMapSecurityException;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class LoginScreen extends HorizontalLayout implements ClickListener{ 
	
	/**
     * The logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginScreen.class);
    
    /**
     * the authentication
     */
	private Authentication authentication;
	
	/**
	 * the registrationScreen
	 */
	private RegistrationScreen registrationScreen;
	
	/**
     * The authentication service
     */
    private IAuthenticationService authenticationService;
	
    /**
	 * the loginPanel
	 */
	private Panel loginPanel;
	
	/**
	 * the loginFormLayout
	 */
	private GridLayout loginFormLayout;
	
	/**
	 * the loginField
	 */
	private TextField loginField;
	
	/**
	 * the passwordField
	 */
	private PasswordField passwordField;
	
	/**
	 * the logInButton
	 */
	private Button logInButton;
	
	/**
	 * the sigIn
	 */
	private Button sigIn;
	
	/**
	 * the errorLogin
	 */
	private Label errorLogin ;
	
	/**
	 * the authenticatedScreen
	 */
	private AuthenticatedScreen authenticatedScreen;
	
	
	/**
	 * Default constructor
	 */
	public LoginScreen(){
		super();
	}
	
	/**
	 * Build home page
	 * @return an HorizontalLayout
	 */
	public HorizontalLayout buildLoginView(){
		removeAllComponents();
		
		loginPanel.removeAllComponents();
		HorizontalLayout header = new HorizontalLayout();
		Label welcomeLabel = new Label();
		welcomeLabel.setCaption(Constants.WELCOME);
		welcomeLabel.addStyleName("titleStyle");
		header.addComponent(welcomeLabel);
		
		loginPanel.removeAllComponents();
		loginPanel.addComponent(header);
		loginPanel.setWidth(null);
		
		loginPanel.addStyleName("loginPanel");
		
		VerticalLayout content = new VerticalLayout();
		
		content.addComponent(errorLogin);
		errorLogin.setCaption(Constants.ERROR_LOGIN_MSG);
		errorLogin.setVisible(false);
		errorLogin.addStyleName("errorStyle");
		
		buidlLoginForm();
		content.addComponent(loginFormLayout);
		loginPanel.addComponent(content);
		
		addComponent(loginPanel);
		setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		setSizeFull();
		
		return this;
	}

	
	/**
	 * Build the login form view
	 */
	private void buidlLoginForm(){
		loginFormLayout.removeAllComponents();
		loginFormLayout.setColumns(3);
		loginFormLayout.setRows(1);
		loginFormLayout.setSpacing(true);
		
		loginField.setCaption(Constants.lOGIN_FIELD_LABEL);
		loginField.setId(ComponentsId.LOGIN_FIELD_ID);
		passwordField.setCaption(Constants.PASSWORD_FIELD_LABEL);
		passwordField.setId(ComponentsId.PASSWORD_ID);
		
		sigIn.setStyleName(Reindeer.BUTTON_LINK);
		logInButton.setCaption(Constants.lOGIN_BUTTON_LABEL);
		logInButton.setId(ComponentsId.LOGIN_BUTTON_ID);
		logInButton.addClickListener(this);
		
		sigIn.setCaption(Constants.SIGN_IN_BUTTON_LABEL);
		sigIn.addClickListener(this);
		sigIn.setId(ComponentsId.SIG_IN_BUTTON_ID);
		
		VerticalLayout buttonBloc = new VerticalLayout();
		buttonBloc.addComponent(logInButton);
		buttonBloc.addComponent(sigIn);
		
		loginFormLayout.addComponent(loginField);
		loginFormLayout.addComponent(passwordField);
		loginFormLayout.addComponent(buttonBloc);
		
	}
	
	
	@Override
	public void buttonClick(ClickEvent event) {
		
		if (event.getButton().equals(logInButton)) {  //Log In Button
			String login = loginField.getValue();
			String password = passwordField.getValue();
			try 
			{
				authentication = checkUserAuthentication(login, password);
				if(authentication != null){
					TalentMapApplication.getCurrent().setAuthentication(authentication);
					getParent().getUI().setContent(authenticatedScreen.selectedViewAccordingToUserRoles());
				}
				
			} catch (TalentMapSecurityException e) {
				errorLogin.setVisible(true);
			}
			
		} else {  //Sign In Button
			registrationScreen.setLoginScreen(this);
			getParent().getUI().setContent(registrationScreen.buildRegistrationScreenView());
		}
		
	}
	
	/**
	 * check user authentication
	 * @param login user's login
	 * @param password user's password
	 * @return an Authentication object
	 * @throws TalentMapSecurityException
	 */
	private Authentication checkUserAuthentication(final String login,
		    final String password) throws TalentMapSecurityException {
		try {
		    CredentialToken credential = new CredentialToken();
		    credential.setLogin(login);
		    credential.setPassword(authenticationService.encodePassword(password));
		    authentication = authenticationService.checkUser(credential);
		    if (authentication == null|| (authentication != null && authentication.getAuthorization() == null)) {
		    	throw new TalentMapSecurityException("User unknown");
		    }
		} catch (DataAccessException ex) {
		    if (LOGGER.isErrorEnabled()) {
			ex.printStackTrace();
			LOGGER.error("Technical Exception : ", ex.getMessage());
		    }
		}

		return authentication;
	    }
	

	
	/**
	 * Get the loginPanel
	 * @return loginPanel 
	 */
	public Panel getLoginPanel() {
		return loginPanel;
	}


	/**
	 * Set the loginPanel
	 * @param loginPanel  loginPanel to set
	 */
	public void setLoginPanel(Panel loginPanel) {
		this.loginPanel = loginPanel;
	}


	/**
	 * Get the passwordField
	 * @return a passwordField
	 */
	public PasswordField getPasswordField() {
		return passwordField;
	}


	/**
	 * Set the passwordField
	 * @param passwordField passwordField to set 
	 */
	public void setPasswordField(PasswordField passwordField) {
		this.passwordField = passwordField;
	}

	/**
	 * Get the loginField
	 * @return loginField
	 */
	public TextField getLoginField() {
		return loginField;
	}

	/**
	 * Set the loginField
	 * @param loginField loginField to set
	 */
	public void setLoginField(TextField loginField) {
		this.loginField = loginField;
	}

	/**
	 * Get logInButton
	 * @return logInButton
	 */
	public Button getLogInButton() {
		return logInButton;
	}


	/**
	 * Set the logInButton
	 * @param logIn logInButton to set 
	 */
	public void setLogInButton(Button logIn) {
		this.logInButton = logIn;
	}

	/**
	 * Get the sigIn Button
	 * @return a button
	 */
	public Button getSigIn() {
		return sigIn;
	}


	/**
	 * Set the sigIn Button
	 * @param sigIn sigIn to set
	 */
	public void setSigIn(Button sigIn) {
		this.sigIn = sigIn;
	}

	/**
	 * Get the authenticationService
	 * @return an authenticationService
	 */
	public IAuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	/**
	 * Set the authenticationService
	 * @param authenticationService authenticationService to set 
	 */
	public void setAuthenticationService(IAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	/**
	 * Get the errorLogin label
	 * @return errorLogin label
	 */
	public Label getErrorLogin() {
		return errorLogin;
	}

	/**
	 * Set the erroLogin 
	 * @param erroLogin erroLogin to set 
	 */
	public void setErrorLogin(Label erroLogin) {
		this.errorLogin = erroLogin;
	}

	/**
	 * Get the loginFormLayout
	 * @return a loginFormLayout
	 */
	public GridLayout getLoginFormLayout() {
		return loginFormLayout;
	}

	/**
	 * Set the loginFormLayout
	 * @param loginFormLayout loginFormLayout to set 
	 */
	public void setLoginFormLayout(GridLayout loginFormLayout) {
		this.loginFormLayout = loginFormLayout;
	}

	/**
	 * Get the authenticatedScreen
	 * @return authenticatedScreen 
	 */
	public AuthenticatedScreen getAuthenticatedScreen() {
		return authenticatedScreen;
	}

	/**
	 * Set the authenticatedScreen
	 * @param authenticatedScreen authenticatedScreen to set 
	 */
	public void setAuthenticatedScreen(AuthenticatedScreen authenticatedScreen) {
		this.authenticatedScreen = authenticatedScreen;
	}

	/**
	 * Get the registrationScreen
	 * @return a registrationScreen
	 */
	
	public RegistrationScreen getRegistrationScreen() {
		return registrationScreen;
	}

	/**
	 * Set the registrationScreen
	 * @param registrationScreen registrationScreen to set
	 */

	public void setRegistrationScreen(RegistrationScreen registrationScreen) {
		this.registrationScreen = registrationScreen;
	}


}
