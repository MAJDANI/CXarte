package com.novedia.talentmap.web.login;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AuthenticatedScreen extends VerticalLayout {
	
	public AuthenticatedScreen(){
		super();
	}
	
	
	public VerticalLayout buildAuthenticatedScreen(){
		this.addComponent(new Label("ok"));
		
		return this;
	}

}
