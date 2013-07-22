package com.novedia.talentmap.web.login;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class LoginScreen extends HorizontalLayout{ 
	
	private Panel loginPanel;
	
	private TextField loginField;
	
	private PasswordField passwordField;
	
	private Button logIn;
	
	private Button sigIn;
	
	
	private FieldGroup loginForm;
	
	public LoginScreen(){
		super();
	}
	
	
	public HorizontalLayout buildLoginView(){
		
		
		HorizontalLayout header = new HorizontalLayout();
		header.addComponent(new Label("Welcome"));
		
		loginPanel.addComponent(header);
		loginPanel.setWidth(null);
		
		
		HorizontalLayout content = new HorizontalLayout();
		
		
		GridLayout loginFormLayout = new GridLayout(3,1);
		loginFormLayout.setSpacing(true);
		loginField.setCaption("UserName");
		
		passwordField.setCaption("Password");
		
		sigIn.setStyleName(Reindeer.BUTTON_LINK);
		logIn.setCaption("Log In");
		sigIn.setCaption("Sign In");
		
		VerticalLayout v = new VerticalLayout();
		v.addComponent(logIn);
		v.addComponent(sigIn);
		
		loginFormLayout.addComponent(loginField);
		loginFormLayout.addComponent(passwordField);
		loginFormLayout.addComponent(v);
		
		content.addComponent(loginFormLayout);
		
		loginPanel.addComponent(content);
		
		addComponent(loginPanel);
		setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		setSizeFull();
		
		return this;
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


	public FieldGroup getLoginForm() {
		return loginForm;
	}


	public void setLoginForm(FieldGroup loginForm) {
		this.loginForm = loginForm;
	}


	public TextField getLoginField() {
		return loginField;
	}


	public void setLoginField(TextField loginField) {
		this.loginField = loginField;
	}


	public Button getLogIn() {
		return logIn;
	}


	public void setLogIn(Button logIn) {
		this.logIn = logIn;
	}


	public Button getSigIn() {
		return sigIn;
	}


	public void setSigIn(Button sigIn) {
		this.sigIn = sigIn;
	}
	
	
	
	
	

}
