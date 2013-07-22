package com.novedia.talentmap.web;

import com.novedia.talentmap.web.login.LoginScreen;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

//@Theme("talentmaptheme")
@SuppressWarnings("serial")
public class TalentMapApplication extends UI {
	
	private VerticalLayout view;
	
	private LoginScreen loginScreen;
	

	@Override
	protected void init(VaadinRequest request) {
		view.setSizeFull();
		view.addComponent(loginScreen.buildLoginView());
//		view.setComponentAlignment(loginScreen.getLoginPanel(),Alignment.MIDDLE_CENTER);
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
	
	

	
	
	
	
}

