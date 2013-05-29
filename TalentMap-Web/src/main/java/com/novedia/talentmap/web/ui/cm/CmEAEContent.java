package com.novedia.talentmap.web.ui.cm;

import com.novedia.talentmap.model.entity.Authentication;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmEAEContent extends VerticalLayout implements
	ClickListener {

    private Authentication authentication;


    /**
     * Default constructor
     */
    public CmEAEContent() {
	super();
    }

    /**
     * Build the view of cm's EAE
     * 
     * @return
     */
    public CmEAEContent buildViewEAEContent() {
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
