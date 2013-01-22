package com.novedia.talentmap.web.ui.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.web.MyVaadinApplication;
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
public class RegistrationScreen extends VerticalLayout {
	

	
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
	private MyVaadinApplication application;
	
	/**
	 * Vaadin component
	 */
	private Panel registrationPanel;
	private RegistrationForm registrationForm;
	private Button save;
	private Button cancel;
	private GridLayout registrationFormLayout;
	
	public static final String SAVE_BUTTON_NAME = "Enregistrer";
	public static final String CANCEL_BUTTON_NAME = "Annuler";
	public static final String REGISTRATION_PANEL_NAME = "Registration";
	public static final String REGISTRATION_PANEL_WIDTH = "800px";
	public static final String REGISTRATION_PANEL_FOOTER_HEIGHT = "50px";
	public static final String REGISTRATION_PANEL_MISSING_FIELDS = "Error one or many fields are missing";
	public static final String REGISTRATION_PANEL_PASSWORD_ERROR = "Error on your password confirmation";
	public static final String REGISTRATION_PANEL_USER_CREATION_ERROR = "Error when creating user";
	
	
	/**
	 * Default constructor
	 */
	public RegistrationScreen() {
	}
	
	/**
	 * The constructor
	 * @param application
	 */
	public RegistrationScreen(MyVaadinApplication application){
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Call Sign In Screen constructor");
		}

		this.application = application;

		//Panel for Registration
		this.registrationPanel = new Panel(REGISTRATION_PANEL_NAME);
		
		this.registrationPanel.setWidth(REGISTRATION_PANEL_WIDTH);
		
		//components initialisation
		this.registrationFormLayout = new GridLayout();
		this.save = new Button();
		this.cancel = new Button();

		
		 // Create a form and use FormLayout as its layout. 
		this.registrationForm = new RegistrationForm(new Form(),this.registrationFormLayout,
				application.getRegistrationService());  
		MyRegistrationListener myRegistrationListener = new MyRegistrationListener(this.application,this.registrationForm);
		
		buildButton(myRegistrationListener);
		
		this.registrationPanel.addComponent(this.registrationForm);
		this.registrationPanel.addComponent(this.save);
		this.registrationPanel.addComponent(this.cancel);
		
		addComponent(this.registrationPanel);
		setComponentAlignment(this.registrationPanel, Alignment.MIDDLE_CENTER);

		HorizontalLayout footer = new HorizontalLayout();
		footer.setHeight(REGISTRATION_PANEL_FOOTER_HEIGHT);
		addComponent(footer);
	}
	
	/**
	 * Build the buttons of the screen
	 */
	public void buildButton(MyRegistrationListener myRegistrationListener){
		
		this.save.setCaption(SAVE_BUTTON_NAME);
		this.save.addListener(myRegistrationListener);
		
		this.cancel.setCaption(CANCEL_BUTTON_NAME);
		this.cancel.addListener(myRegistrationListener);
		
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setMargin(true);
		hLayout.setSpacing(true);
		hLayout.addComponent(this.save);
		hLayout.addComponent(this.cancel);
		//this.registrationForm.setFooter(hLayout);
	}
	
	
	/**
	 * Inner listener class
	 * @author e.moumbe
	 *
	 */
	private static class MyRegistrationListener implements ClickListener {
		
		/**
		 * UID
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * The application
		 */
		private MyVaadinApplication application;
		
		/**
		 * The registration form
		 */
		private RegistrationForm registrationForm; 
		
		
		/**
		 * 
		 * @param application
		 * @param loginForm
		 */
		public MyRegistrationListener(MyVaadinApplication application, RegistrationForm registrationForm) {
			this.application = application;
			this.registrationForm = registrationForm;
			
		}


		@Override
		public void buttonClick(ClickEvent event) {
			//appel du service d'inscription
			
			BeanItem<Registration> registrationItem = (BeanItem<Registration>) this.registrationForm.getRegistrationForm().getItemDataSource();
			Registration registration = registrationItem.getBean();
			
			String button = event.getButton().getCaption();
			Authentication authenticate = null;
			if (button.equalsIgnoreCase(SAVE_BUTTON_NAME)){
				if(!validateRegistrationForm()){
					application.getMainWindow().showNotification(REGISTRATION_PANEL_MISSING_FIELDS);
				} else if ((!validatePassword())){
					application.getMainWindow().showNotification(REGISTRATION_PANEL_PASSWORD_ERROR);
				} else {

					try {
						authenticate = application.register(registration);
						application.getMainWindow().setContent(new AuthenticatedScreen(application, authenticate));
					} catch (TalentMapSecurityException e) {
						application.getMainWindow().showNotification(REGISTRATION_PANEL_USER_CREATION_ERROR);
					}
				}
			}
			
			else if (button.equalsIgnoreCase(CANCEL_BUTTON_NAME)){
				application.getMainWindow().setContent(new LoginScreen(application));
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
		

	}
}
