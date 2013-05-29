package com.novedia.talentmap.web.ui.collab;

import com.novedia.talentmap.model.entity.Authentication;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CollabEAEContent extends VerticalLayout implements
	ClickListener {

    private Authentication authentication;


    /**
     * Default constructor
     */
    public CollabEAEContent() {
	super();
    }

    /**
     * Build the view of cm's EAE
     * 
     * @return
     */
    public CollabEAEContent buildViewEAEContent() {
	removeAllComponents();
	buildMain();
	return this;
    }

    /**
     * The main builder
     * 
     * @class CmNotificationContent.java
     */
    public void buildMain() {
	setMargin(true);
	setSpacing(true);
    }


    @Override
    public void buttonClick(ClickEvent event) {
	// TODO Auto-generated method stub

    }


    public Authentication getAuthentication() {
	return authentication;
    }

    public void setAuthentication(Authentication authentication) {
	this.authentication = authentication;
    }

}
