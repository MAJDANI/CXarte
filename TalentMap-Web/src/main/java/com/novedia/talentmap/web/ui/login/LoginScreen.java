package com.novedia.talentmap.web.ui.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.util.LabelConstants;
import com.novedia.talentmap.web.util.exceptions.TalentMapSecurityException;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
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
		
		this.application = application;
//		setSizeFull();

		//Panel for login
		Panel loginPanel = new Panel("Login");
		
		loginPanel.setWidth("400px");
		
		//The form
		LoginForm loginForm = new LoginForm();
		loginForm.setUsernameCaption(LabelConstants.USER_LOGIN);
		loginForm.setPasswordCaption(LabelConstants.USER_PASSWORD);
		loginForm.setLoginButtonCaption(LabelConstants.LOGIN_BUTTON);

		loginForm.setHeight("150px");
		loginForm.addListener(new MyLoginListener(this.application));

		loginPanel.addComponent(loginForm);

		addComponent(loginPanel);
		setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);

		HorizontalLayout footer = new HorizontalLayout();
		footer.setHeight("50px");
		addComponent(footer);
	}
	
	/**
	 * Inner listener class
	 * @author e.moumbe
	 *
	 */
	private static class MyLoginListener implements LoginForm.LoginListener {
		
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
			}catch (TalentMapSecurityException tmpex) {
				application.getMainWindow().showNotification("User Unknown");
			}
			application.getMainWindow().setContent(new AuthenticatedScreen(application, authentication));
		}

	}
}
