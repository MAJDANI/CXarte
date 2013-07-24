package com.novedia.talentmap.web.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.services.impl.AuthenticationService;
import com.novedia.talentmap.web.registration.RegistrationScreen;
import com.novedia.talentmap.web.util.exceptions.TalentMapSecurityException;
import com.novedia.talentmap.web.utils.CUtils;
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
	
	private Authentication authentication;
	
	private RegistrationScreen registrationScreen;
	
	/**
     * The authentication service
     */
    private AuthenticationService authenticationService;
	
	private Panel loginPanel;
	
	private GridLayout loginFormLayout;
	
	private TextField loginField;
	
	private PasswordField passwordField;
	
	private Button logInButton;
	
	private Button sigIn;
	
	private Label errorLogin ;
	
	
	private AuthenticatedScreen authenticatedScreen;
	
	public LoginScreen(){
		super();
	}
	
	
	public HorizontalLayout buildLoginView(){
		loginPanel.removeAllComponents();
		HorizontalLayout header = new HorizontalLayout();
		Label welcomeLabel = new Label();
		welcomeLabel.setCaption(Constants.WELCOMME);
		welcomeLabel.addStyleName("titleStyle");
		header.addComponent(welcomeLabel);
		
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

	
	private void buidlLoginForm(){
		loginFormLayout.removeAllComponents();
		loginFormLayout.setColumns(3);
		loginFormLayout.setRows(1);
		loginFormLayout.setSpacing(true);
		
		loginField.setCaption(Constants.lOGIN_FIELD_LABEL);
		loginField.setId("login");
		passwordField.setCaption(Constants.PASSWORD_FIELD_LABEL);
		passwordField.setId("password");
		
		sigIn.setStyleName(Reindeer.BUTTON_LINK);
		logInButton.setCaption(Constants.lOGIN_BUTTON_LABEL);
		logInButton.setId("logInButton");
		logInButton.addClickListener(this);
		
		sigIn.setCaption(Constants.SIGN_IN_BUTTON_LABEL);
		sigIn.addClickListener(this);
		sigIn.setId("sigIn");
		
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
					getParent().getUI().setContent(authenticatedScreen.buildAuthenticatedScreen());
				}
				
			} catch (TalentMapSecurityException e) {
				errorLogin.setVisible(true);
			}
			
		} else {  //Sign In Button
			registrationScreen.setLoginScreen(this);
			getParent().getUI().setContent(registrationScreen.buildRegistrationView());
		}
		
	}
	
	
	private Authentication checkUserAuthentication(final String login,
		    final String password) throws TalentMapSecurityException {
		try {
		    CredentialToken credential = new CredentialToken();
		    credential.setLogin(login);
		    credential.setPassword(CUtils.encodePassword(password));
		    authentication = authenticationService.checkUser(credential);
		    if (authentication == null
			    || (authentication != null && authentication
				    .getAuthorization() == null)) {
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
	

	public Panel getLoginPanel() {
		return loginPanel;
	}


	public void setLoginPanel(Panel loginPanel) {
		this.loginPanel = loginPanel;
	}


	public PasswordField getPasswordField() {
		return passwordField;
	}


	public void setPasswordField(PasswordField passwordField) {
		this.passwordField = passwordField;
	}


	public TextField getLoginField() {
		return loginField;
	}


	public void setLoginField(TextField loginField) {
		this.loginField = loginField;
	}


	public Button getLogInButton() {
		return logInButton;
	}


	public void setLogInButton(Button logIn) {
		this.logInButton = logIn;
	}


	public Button getSigIn() {
		return sigIn;
	}


	public void setSigIn(Button sigIn) {
		this.sigIn = sigIn;
	}


	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}


	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}


	public Label getErrorLogin() {
		return errorLogin;
	}


	public void setErrorLogin(Label erroLogin) {
		this.errorLogin = erroLogin;
	}


	public GridLayout getLoginFormLayout() {
		return loginFormLayout;
	}


	public void setLoginFormLayout(GridLayout loginFormLayout) {
		this.loginFormLayout = loginFormLayout;
	}


	public AuthenticatedScreen getAuthenticatedScreen() {
		return authenticatedScreen;
	}


	public void setAuthenticatedScreen(AuthenticatedScreen authenticatedScreen) {
		this.authenticatedScreen = authenticatedScreen;
	}


	public RegistrationScreen getRegistrationScreen() {
		return registrationScreen;
	}


	public void setRegistrationScreen(RegistrationScreen registrationScreen) {
		this.registrationScreen = registrationScreen;
	}
	
	
	

	
	
	


}
