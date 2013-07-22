package com.novedia.talentmap.web.login;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class LoginScreen extends HorizontalLayout { 
	
	private TextField loginField;
	
	private PasswordField passwordField;
	
	private Button logIn;
	
	private Button sigIn;
	
	
	private FieldGroup loginForm;
	
	private GridLayout loginFormLayout;
	
	
	public HorizontalLayout buildLoginView(){
		
		
		return this;
	}

}
