package com.novedia.talentmap.web.ui.admin;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AdminView extends VerticalLayout {
	
	
	public AdminView(){
		super();
	}
	
	
	public VerticalLayout buildAdminContent(){
		
		addComponent(new Label("Adminnnnnnnnnnnnnn"));
		return this;
	}

}
