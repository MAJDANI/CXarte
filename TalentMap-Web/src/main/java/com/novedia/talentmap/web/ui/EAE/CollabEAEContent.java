package com.novedia.talentmap.web.ui.EAE;

import com.novedia.talentmap.model.entity.Authentication;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CollabEAEContent extends VerticalLayout implements
	ClickListener {

    private Authentication authentication;
    private CollabEAEContentHistory collabEAEContentHistory;


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
	collabEAEContentHistory.setAuthentication(getAuthentication());
	collabEAEContentHistory = collabEAEContentHistory.buildViewHistory();
	this.addComponent(collabEAEContentHistory);
	setMargin(true);
	setSpacing(true);
	return this;
    }

    /**
     * The main builder
     * 
     * @class CollabEAEContent.java
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

    /**
     * @return the collabEAEContentHistory
     */
    public CollabEAEContentHistory getCollabEAEContentHistory() {
        return collabEAEContentHistory;
    }

    /**
     * @param collabEAEContentHistory the collabEAEContentHistory to set
     */
    public void setCollabEAEContentHistory(
    	CollabEAEContentHistory collabEAEContentHistory) {
        this.collabEAEContentHistory = collabEAEContentHistory;
    }

}
