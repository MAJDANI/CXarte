package com.novedia.talentmap.web.ui.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.util.LabelConstants;
import com.novedia.talentmap.web.util.exceptions.TalentMapSecurityException;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Login Screen
 * @author e.moumbe
 */
public class LoginScreen extends VerticalLayout {
	
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
	private MyVaadinApplication application;
	
	/**
	 * Vaadin component
	 */
	private Button signIn;
	private LoginForm loginForm;
	private Panel loginPanel;
	
	
	/**
	 * Default constructor
	 */
	public LoginScreen() {
	}
	
	/**
	 * The constructor
	 * @param application
	 */
	public LoginScreen(MyVaadinApplication application){
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Call Login Screen constructor");
		}
		
		setSizeFull();
		setMargin(true);
		setStyleName(Reindeer.LAYOUT_WHITE);
		
		this.application = application;

		//Panel for login
		this.loginPanel = new Panel("Login");
		
		this.loginPanel.setWidth("400px");
		
		//The form
		this.loginForm = new LoginForm();
		this.loginForm.setUsernameCaption(LabelConstants.USER_LOGIN);
		this.loginForm.setPasswordCaption(LabelConstants.USER_PASSWORD);
		this.loginForm.setLoginButtonCaption(LabelConstants.LOGIN_BUTTON);

		this.loginForm.setHeight("150px");
		MyLoginListener myLoginListener = new MyLoginListener(this.application);
		this.loginForm.addListener(myLoginListener);

		this.loginPanel.addComponent(this.loginForm);
		
		this.signIn = new Button("Sign in");
		this.signIn.addListener(myLoginListener);
		this.loginPanel.addComponent(this.signIn);
		addComponent(this.loginPanel);
		setComponentAlignment(this.loginPanel, Alignment.MIDDLE_CENTER);

		HorizontalLayout footer = new HorizontalLayout();
		footer.setHeight("50px");
		addComponent(footer);
	}
	
	/**
	 * Inner Login Listener.
	 * Handle event from login form
	 * @author e.moumbe
	 *
	 */
	private static class MyLoginListener implements LoginForm.LoginListener, ClickListener {
		
		/**
		 * UID
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * The application
		 */
		private MyVaadinApplication application;
		
		/**
		 * 
		 * @param application
		 * @param loginForm
		 */
		public MyLoginListener(MyVaadinApplication application) {
			this.application = application;
		}

		/**
		 * This method is handler of login
		 */
		@Override
		public void onLogin(LoginEvent event) {
			
			//Credentials infos
			String username = event.getLoginParameter("username");
			String password = event.getLoginParameter("password");
			
			Authentication authentication = null;
			try {
				authentication = application.login(username, password);
				
				//See javado: Application has attribute User (See how we can integrate this capability
				//in yours conception
				if (authentication != null) {
					this.application.setUser(authentication);
					application.getMainWindow().setContent(new AuthenticatedScreen(application, authentication));
				}
			} catch (TalentMapSecurityException tmpex) {
				application.getMainWindow().showNotification("Bad user name/password");
			}
		}

		@Override
		public void buttonClick(ClickEvent event) {
			
			String button = event.getButton().getCaption();		
			if (button.equalsIgnoreCase("Sign In")){
				application.getMainWindow().setContent(new RegistrationScreen(application));
			}
		}

	}
}
