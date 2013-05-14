package com.novedia.talentmap.web.ui.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.services.impl.AuthenticationService;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.exceptions.TalentMapSecurityException;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * Login Screen
 * 
 * @author b.tiomofofou
 * 
 */

public class LoginScreen extends VerticalLayout implements ClickListener {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 9207462728872629924L;

	/**
	 * The logger
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoginScreen.class);

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
	 * Constant for login
	 */
	public static final String USER_LOGIN = "Login";

	/**
	 * Constant for password
	 */
	public static final String USER_PASSWORD = "Password";

	/**
	 * Constant label for button
	 */
	public static final String LABEL_LOGIN = "Log In";

	/**
	 * Vaadin component
	 */
	private Button signIn;
	private Panel loginPanel;

	private Form loginForm;

	private TextField loginField;
	private PasswordField passwordField;

	private Button logInButton;

	/**
	 * the loginView
	 */
	private VerticalLayout loginView;

	private static final String LABEL_BUTTON_SIGN_IN = "Create an Account";

	private static final String ERROR_LOGIN = "The Login or password was incorrect.";

	private Label errorLoginLabel;

	/**
	 * Default constructor
	 */
	public LoginScreen() {
		setSizeFull();
	}

	/**
	 * Build the login view
	 * 
	 * @return VerticalLayout
	 */
	public VerticalLayout buildLoginScreenView() {
		getMyVaadinApplication().getMainWindow()
				.setCaption("Log In Talent Map");
		// Resource res = new ThemeResource("./images/logoNovedia.jpg");
		// final Embedded logo = new Embedded("",res);

		loginView = new VerticalLayout();
		loginView.setSpacing(false);
		loginView.setSizeFull();
		// loginView.addStyleName(Reindeer.LAYOUT_WHITE);

		// VerticalLayout logo = new VerticalLayout();
		// logo.setHeight("80px");
		// logo.setWidth("200px");
		// logo.addStyleName("logo");
		// loginView.addComponent(logo);
		// loginView.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);

		// Panel for login
		loginPanel = new Panel(LABEL_LOGIN);
		loginPanel.setCaption(LABEL_LOGIN);
		loginPanel.setWidth("400px");

		errorLoginLabel = new Label(ERROR_LOGIN);
		errorLoginLabel.addStyleName("errorLoginLabel");
		errorLoginLabel.setVisible(false);
		loginPanel.addComponent(errorLoginLabel);

		buildLoginForm();
		loginPanel.addComponent(loginForm);

		signIn = new Button(LABEL_BUTTON_SIGN_IN);
		signIn.setCaption(LABEL_BUTTON_SIGN_IN);
		signIn.addStyleName(Reindeer.BUTTON_LINK);
		signIn.addStyleName("signIn");
		signIn.addListener(this);
		loginPanel.addComponent(signIn);
		loginView.addComponent(loginPanel);
		loginView.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		HorizontalLayout footer = new HorizontalLayout();
		footer.setHeight("50px");
		loginView.addComponent(footer);

		return loginView;

	}

	/**
	 * Build the Login Form
	 */
	private void buildLoginForm() {
		loginForm = new Form();
		GridLayout loginFormLayout = new GridLayout();
		loginFormLayout.addStyleName("loginForm");
		loginFormLayout.setSpacing(true);
		loginField = new TextField(USER_LOGIN);
		loginField.addStyleName("loginFormField");
		passwordField = new PasswordField(USER_PASSWORD);
		passwordField.addStyleName("loginFormField");
		loginFormLayout.addComponent(loginField);
		loginFormLayout.addComponent(passwordField);
		logInButton = new Button(LABEL_LOGIN);
		logInButton.addListener(this);
		logInButton.addStyleName("logInButton");
		loginFormLayout.addComponent(logInButton);
		loginForm.setLayout(loginFormLayout);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().equals(signIn)) {
			registrationScreen.setMyVaadinApplication(getMyVaadinApplication());
			registrationScreen.setLoginScreen(this);
			getMyVaadinApplication().getMainWindow().setContent(
					registrationScreen.buildRegistrationScreenView());
			return;
		}
		if (event.getButton().equals(logInButton)) {
			String username = (String) loginField.getValue();
			String password = (String) passwordField.getValue();
			try {
				authentication = checkUserAuthentication(username, password);
				if (authentication != null) {
					authenticatedScreen.setAuthentication(authentication);
					authenticatedScreen
							.setMyVaadinApplicationApplication(myVaadinApplication);
					myVaadinApplication.getMainWindow().setContent(
							authenticatedScreen
									.selectedViewAccordingToUserRoles());
				}
			} catch (TalentMapSecurityException tmpex) {
				// myVaadinApplication.getMainWindow().showNotification("Bad user name/password");
				errorLoginLabel.setVisible(true);
			}
			return;
		}

	}

	/**
	 * this method check the authentication user
	 * 
	 * @param login
	 *            login's user
	 * @param password
	 *            password's user
	 * @return an Authentication object
	 * @throws TalentMapSecurityException
	 */
	public Authentication checkUserAuthentication(final String login,
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
				LOGGER.error("Technical Exception : ", ex.getMessage());
			}
		}

		return authentication;
	}

	/**
	 * Get the authenticatedScreen
	 * 
	 * @return authenticatedScreen
	 */
	public AuthenticatedScreen getAuthenticatedScreen() {
		return authenticatedScreen;
	}

	/**
	 * Set the authenticatedScreen
	 * 
	 * @param authenticatedScreen
	 *            to set
	 */
	public void setAuthenticatedScreen(AuthenticatedScreen authenticatedScreen) {
		this.authenticatedScreen = authenticatedScreen;
	}

	/**
	 * Get MyVaadinApplication
	 * 
	 * @return MyVaadinApplication
	 */
	public MyVaadinApplication getMyVaadinApplication() {
		return myVaadinApplication;
	}

	/**
	 * Set MyVaadinApplication
	 * 
	 * @param myVaadinApplication
	 *            MyVaadinApplication to set
	 */
	public void setMyVaadinApplication(MyVaadinApplication myVaadinApplication) {
		this.myVaadinApplication = myVaadinApplication;
	}

	/**
	 * Get the authentication
	 * 
	 * @return authentication
	 */
	public Authentication getAuthentication() {
		return authentication;
	}

	/**
	 * Set the authentication
	 * 
	 * @param authentication
	 *            authentication to set
	 */
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	/**
	 * Get the authenticationService
	 * 
	 * @return authenticationService
	 */
	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	/**
	 * set the authenticationService
	 * 
	 * @param authenticationService
	 *            the authenticationService to set
	 */
	public void setAuthenticationService(
			AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	/**
	 * Get the registrationScreen
	 * 
	 * @return registrationScreen
	 */
	public RegistrationScreen getRegistrationScreen() {
		return registrationScreen;
	}

	/**
	 * Set the registrationScreen
	 * 
	 * @param registrationScreen
	 *            registrationScreen to set
	 */
	public void setRegistrationScreen(RegistrationScreen registrationScreen) {
		this.registrationScreen = registrationScreen;
	}

}
