package com.novedia.talentmap.web.ui.admin;


import com.novedia.talentmap.services.impl.AuthentificationService;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author v.dibi
 * 
 */
@SuppressWarnings("serial")
public class LogForm extends Window {

	private AuthentificationService service;
	private AdminNavigation adminNavigation;
	private MyVaadinApplication application;
	
	 private final VerticalLayout layout = new VerticalLayout();
	 private final Button btnLogin = new Button("Login");
	 private final TextField txtUsername = new TextField("Username");
	 private final PasswordField txtPassword = new PasswordField("Password");
	
	public LogForm() {
		 super("LoginWindow Login");
         setName("login");
         setContent(layout);
         initUI();
         buildMainLayout();
	}
	
	 private void initUI() {
         txtUsername.setRequired(true);
         txtPassword.setRequired(true);
      
         final Panel loginPanel = new Panel("Authentification");
         layout.addComponent(loginPanel);
         loginPanel.setWidth("400px");
         loginPanel.setHeight("200px");
        
         final FormLayout loginForm = new FormLayout();
         loginForm.setMargin(true);
         loginForm.setStyleName("loginForm");
         loginForm.addComponent(txtUsername);
         loginForm.addComponent(txtPassword);
         loginForm.addComponent(btnLogin);
        
         loginPanel.setContent(loginForm);
         layout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
         layout.setSizeFull();
     }
	
	/**
	 * 
	 */
	private void buildMainLayout() {

		//évènement sur le bouton
		btnLogin.addListener ( new Button.ClickListener()
	     {
	         public void buttonClick ( Button.ClickEvent event )
	         {
	        	// getWindow().showNotification("test boutton");
	        	 authenticate(txtUsername.getValue().toString(), txtPassword.getValue().toString());	        	 
	         }
	     });
	}

	/**
	 * 
	 * @param login
	 * @param password
	 */
	public void authenticate(String login, String password){
		
		//Appelle du service
   	 	service.checkAuthentification(login, password);
   	 	if("user".equals ( login ) && "password".equals( password )){
	   	 	newWindow ();
	   	 	return;
   	 	}
	   	 else{
	 		getWindow().showNotification(" Username And password are invalid");	
	 	}		
	}
	
	@SuppressWarnings("unused")
	private void newWindow ()
    {
		//vers une page
 		Window window = new Window();
 		window.setContent(adminNavigation);     
    }
	
	/**
	 * @return the service
	 */
	public AuthentificationService getService() {
		return service;
	}

	/**
	 * @return the adminNavigation
	 */
	public AdminNavigation getAdminNavigation() {
		return adminNavigation;
	}
}