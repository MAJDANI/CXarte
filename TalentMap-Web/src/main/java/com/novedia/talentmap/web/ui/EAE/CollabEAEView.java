package com.novedia.talentmap.web.ui.EAE;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.web.ui.cm.CmEAEView;
import com.novedia.talentmap.web.util.IEAELayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CollabEAEView extends HorizontalSplitPanel {

    /**
     * Vaadin UI
     */
    private CollabEAEContent collabEAEContent;
    private CollabEAENavigation collabEAENavigation;

    private Authentication authentication;

    /**
     * Constants
     */
    public static final int EAE_HISTORY = 1;

    /**
     * default constructor
     */
    public CollabEAEView() {
	super();
    }

    /**
     * Build the profil Layout of user
     * 
     * @return
     */
    public CollabEAEView buildViewCollabEAEView() {
	removeAllComponents();
	collabEAEContent.setAuthentication(getAuthentication());
	buildObservators();
	mainBuild();
	return this;
    }

    public void buildObservators() {

	this.collabEAENavigation.addObservateur(new IEAELayout() {

	    @Override
	    public void switchPanelTarget(int targetPanel) {
		if (targetPanel == EAE_HISTORY) {
		    collabEAEContent.buildViewEAEContent();
		    CollabEAEView.this.setSecondComponent(collabEAEContent);
		} 
	    }
	}, IEAELayout.class);

    }

    /**
     * The main builder
     * 
     * @class CollabEAEView.java
     */
    public void mainBuild() {

	collabEAENavigation = collabEAENavigation.buildCollabEAENavigation();
	collabEAENavigation.setHeight("800px");

	this.setFirstComponent(collabEAENavigation);
	collabEAEContent = collabEAEContent.buildViewEAEContent();
	this.setSecondComponent(collabEAEContent);
	//L'historique par défaut
	collabEAENavigation.setTargetPanel(EAE_HISTORY);
	collabEAENavigation.updateObservateur();

	this.setSplitPosition(20);
	this.setLocked(true);

	setSizeFull();
    }

    public Authentication getAuthentication() {
	return authentication;
    }

    public void setAuthentication(Authentication authentication) {
	this.authentication = authentication;
    }

    public CollabEAENavigation getCollabEAENavigation() {
	return collabEAENavigation;
    }

    public void setCollabEAENavigation(CollabEAENavigation collabEAENavigation) {
	this.collabEAENavigation = collabEAENavigation;
    }

    public CollabEAEContent getCollabEAEContent() {
	return collabEAEContent;
    }

    public void setCollabEAEContent(CollabEAEContent collabEAEContent) {
	this.collabEAEContent = collabEAEContent;
    }

}
