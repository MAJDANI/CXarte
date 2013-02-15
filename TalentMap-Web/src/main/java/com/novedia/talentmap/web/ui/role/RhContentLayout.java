package com.novedia.talentmap.web.ui.role;

import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.ui.login.LoginScreen;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class RhContentLayout extends VerticalLayout implements ClickListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Ajout d'un lien de retour
	 */
	Link buttonlink = new Link();
	LoginScreen loginScreen;
	MyVaadinApplication application;
	Button buttonBack = new Button();

	public RhContentLayout(){
		init(); 
	}
	
	public void init() {
		Label label =  new Label(" Page resource humaine en cours de construction ");
		label.setVisible(true);
		label.addStyleName("mystyle");
		addComponent(label);	
		
//		buttonBack= application.getCloseButton();
//		buttonBack.addStyleName(Reindeer.BUTTON_LINK); 
	}	
	
	@Override
	public void buttonClick(ClickEvent event) {
		buttonBack.setCaption("Back");		
	}
}