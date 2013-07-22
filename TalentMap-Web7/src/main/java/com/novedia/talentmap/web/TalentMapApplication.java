package com.novedia.talentmap.web;

import com.vaadin.annotations.Theme;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

//@Theme("talentmaptheme")
@SuppressWarnings("serial")
public class TalentMapApplication extends UI {

	private TextField user;

//	private PasswordField password;

	private Button loginButton;
    
	private FieldGroup binder;
    
    
	
	@Override
	protected void init(VaadinRequest request) {
		HorizontalLayout view = new HorizontalLayout();
		

//	    user = new TextField("User:");
//	    user.setInputPrompt("Your username");
//
//	    password = new PasswordField("Password:");
//	    password.setInputPrompt("Your password");


//	    loginButton = new Button("Login");
		
		// Have an item
		PropertysetItem item = new PropertysetItem();
		item.addItemProperty("User", new ObjectProperty<String>(""));
		item.addItemProperty("Password", new ObjectProperty<String>(""));
		
		// Have some layout and create the fields
		GridLayout form = new GridLayout(2,1);
//		 
//		TextField userField = new TextField("User");
//		form.addComponent(userField);
//		 
//		PasswordField password = new PasswordField("Password");
//		form.addComponent(password);
	    
//	    binder = new FieldGroup();
	    FieldGroup binder = new FieldGroup(item);
	    form.addComponent(binder.buildAndBind("User", "User"));
	    form.addComponent(binder.buildAndBind("Password", "Password"));
//	    form.bind(user, );
//	    form.bind(password, applicationData);
	   
	    
//	    form.addComponent(view);
		
//        view.addComponent(user);
//        view.addComponent(password);
//        view.addComponent(loginButton);
//    	
        
        setContent(form);
		
	}

}

