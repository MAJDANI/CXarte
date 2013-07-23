package com.novedia.talentmap.web;

import com.novedia.talentmap.web.login.LoginScreen;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
//import com.novedia.talentmap.web.registration.RegistrationScreen;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("talentmap")
@SuppressWarnings("serial")
@PreserveOnRefresh
public class TalentMapApplication extends UI {
	
	private VerticalLayout view;
	
	private LoginScreen loginScreen;
	
//	private RegistrationScreen registrationScreen;
	

	@Override
	protected void init(VaadinRequest request) {
		view.setSizeFull();
		getPage().setTitle(Constants.TITLE);
		view.addComponent(loginScreen.buildLoginView());
//		view.addComponent(registrationScreen.buildRegistrationView());
	    setContent(view);
	    
	}

	
	
	public VerticalLayout getView() {
		return view;
	}


	public void setView(VerticalLayout view) {
		this.view = view;
	}


	public LoginScreen getLoginScreen() {
		return loginScreen;
	}


	public void setLoginScreen(LoginScreen loginScreen) {
		this.loginScreen = loginScreen;
	}
	
	
//	public RegistrationScreen getRegistrationScreen() {
//		return registrationScreen;
//	}



//	public void setRegistrationScreen(RegistrationScreen registrationScreen) {
//		this.registrationScreen = registrationScreen;
//	}
	
	
	
	
}

