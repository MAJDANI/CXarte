package com.novedia.talentmap.web.ui.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.services.impl.AuthenticationService;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.LabelConstants;
import com.novedia.talentmap.web.util.exceptions.TalentMapSecurityException;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.LoginForm.LoginListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;


/**
 * Login Screen
 * @author b.tiomofofou
 * 
 */

public class LoginScreen extends VerticalLayout implements LoginListener, ClickListener {
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 9207462728872629924L;
	
	/**
	 * The logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginScreen.class);
	
	/**
	 * Application
	 */
	private MyVaadinApplication myVaadinApplication;
	
	private RegistrationScreen registrationScreen;
	
	private Authentication authentication;
	
	/**
	 * The authentication service
	 */
	private AuthenticationService authenticationService;
	
	/**
	 * the authenticatedScreen
	 */
	private AuthenticatedScreen authenticatedScreen;
	
	
	
	/**
	 * Vaadin component
	 */
	private Button signIn;
	private LoginForm loginForm;
	private Panel loginPanel;
	
	/**
	 * the loginView
	 */
	private VerticalLayout loginView;
	
	private String LABEL_BUTTON_SIGN_IN = "Sign in";
	
	/**
	 * Default constructor
	 */
	public LoginScreen() {
		setSizeFull();
		setMargin(true);
		setStyleName(Reindeer.LAYOUT_WHITE);
	}
	
	
	
	public VerticalLayout buildLoginScreenView(){
		getMyVaadinApplication().getMainWindow().setCaption("Log In Talent Map");
		loginView = new VerticalLayout();
		loginView.setSizeFull();
		loginView.setMargin(true);
		loginView.setStyleName(Reindeer.LAYOUT_WHITE);
		//Panel for login
		loginPanel = new Panel("Log In");
		loginPanel.setWidth("400px");
		
		//The form
		loginForm = new LoginForm();
		loginForm.setUsernameCaption(LabelConstants.USER_LOGIN);
		loginForm.setPasswordCaption(LabelConstants.USER_PASSWORD);
		loginForm.setLoginButtonCaption(LabelConstants.LOGIN_BUTTON);
		loginForm.setHeight("150px");
		loginForm.addListener(this);
		loginPanel.addComponent(loginForm);
		
		signIn = new Button(LABEL_BUTTON_SIGN_IN);
		signIn.addListener(this);
		loginPanel.addComponent(signIn);
		loginView.addComponent(loginPanel);
		loginView.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		HorizontalLayout footer = new HorizontalLayout();
		footer.setHeight("50px");
		loginView.addComponent(footer);
		
		return loginView;
		
	}
	
	
	@Override
	public void buttonClick(ClickEvent event) {
		String button = event.getButton().getCaption();		
		if (button.equalsIgnoreCase(LABEL_BUTTON_SIGN_IN)){
			registrationScreen.setMyVaadinApplication(getMyVaadinApplication());
			registrationScreen.setLoginScreen(this);
			getMyVaadinApplication().getMainWindow().setContent(registrationScreen.buildRegistrationScreenView());
		}
		
	}



	@Override
	public void onLogin(LoginEvent event) {
		//Credentials infos
		String username = event.getLoginParameter("username");
		String password = event.getLoginParameter("password");
		try {
			authentication = checkUserAuthentication(username, password);
			if (authentication != null) {
				authenticatedScreen.setAuthentication(authentication);
				authenticatedScreen.setMyVaadinApplicationApplication(myVaadinApplication);
				myVaadinApplication.getMainWindow().setContent(authenticatedScreen.selectedViewAccordingToUserRoles());
			}
		} catch (TalentMapSecurityException tmpex) {
			myVaadinApplication.getMainWindow().showNotification("Bad user name/password");
		}
		
	}
	
	
	/**
	 *  this method check the authentication user
	 * @param login login's user
	 * @param password password's user
	 * @return an Authentication object
	 * @throws TalentMapSecurityException
	 */
	public Authentication checkUserAuthentication(final String login,final String password)
			throws TalentMapSecurityException {
		try {
			CredentialToken credential = new CredentialToken();
			credential.setLogin(login);
			credential.setPassword(CUtils.encodePassword(password));
			authentication = authenticationService.checkUser(credential);
			if (authentication == null
					|| (authentication != null && authentication.getAuthorization() == null)) {
				throw new TalentMapSecurityException("User unknown");
			}
		} catch (DataAccessException ex) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("Technical Exception : ", ex.getMessage());
			}
		}

		return authentication;
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
	  * @param authenticatedScreen to set
	  */
	 public void setAuthenticatedScreen(AuthenticatedScreen authenticatedScreen) {
		 this.authenticatedScreen = authenticatedScreen;
	 }

	 /**
	  * Get MyVaadinApplication 
	  * @return MyVaadinApplication
	  */
	public MyVaadinApplication getMyVaadinApplication() {
		return myVaadinApplication;
	}

	/**
	 * Set MyVaadinApplication
	 * @param myVaadinApplication MyVaadinApplication to set
	 */
	public void setMyVaadinApplication(MyVaadinApplication myVaadinApplication) {
		this.myVaadinApplication = myVaadinApplication;
	}

	/**
	 * Get the authentication
	 * @return authentication
	 */
	public Authentication getAuthentication() {
		return authentication;
	}

	/**
	 * Set the authentication
	 * @param authentication authentication to set
	 */
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
	
	/**
	 * Get the authenticationService
	 * @return authenticationService
	 */
	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}
	
	/**
	 * set the authenticationService
	 * @param authenticationService the authenticationService to set
	 */
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	/**
	 * Get the registrationScreen
	 * @return registrationScreen
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
