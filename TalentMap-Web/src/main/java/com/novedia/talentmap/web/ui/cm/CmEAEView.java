package com.novedia.talentmap.web.ui.cm;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.web.util.IEAELayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmEAEView extends HorizontalSplitPanel {

    /**
     * Vaadin UI
     */
    private CmEAEContent cmEAEContent;
    private CmEAENavigation cmEAENavigation;

    private Authentication authentication;

    /**
     * Constants
     */
    public static final int EAE_SYNTHESIS = 1;
    public static final int EAE_HISTORY = 2;
    public static final int EAE_CREATION = 3;
    public static final int EAE_PERSONAL = 4;

    /**
     * default constructor
     */
    public CmEAEView() {
	super();
    }

    /**
     * Build the profil Layout of user
     * 
     * @return
     */
    public CmEAEView buildViewCmEAEView() {
	removeAllComponents();
	cmEAEContent.setAuthentication(getAuthentication());
	buildObservators();
	mainBuild();
	return this;
    }

    public void buildObservators() {
	// Temporaire
	final VerticalLayout v1 = new VerticalLayout();
	v1.setCaption("Synthesis");
	v1.addComponent(new Label("Synthesis"));
	final VerticalLayout v2 = new VerticalLayout();
	v2.addComponent(new Label("History"));
	final VerticalLayout v3 = new VerticalLayout();
	v3.addComponent(new Label("Creation"));
	final VerticalLayout v4 = new VerticalLayout();
	v4.addComponent(new Label("Personal"));

	this.cmEAENavigation.addObservateur(new IEAELayout() {

	    @Override
	    public void switchPanelTarget(int targetPanel) {
		if (targetPanel == EAE_SYNTHESIS) {
//		    cmEAEContent.buildViewEAEContent();
		    cmEAEContent.buildViewSynthesisContent();
		    CmEAEView.this.setSecondComponent(cmEAEContent);
		    // cmEAESynthesisContent =
		    // cmEAESynthesisContent.buildViewEAESynthesisContent();
		    // CmEAEView.this.setSecondComponent(cmEAESynthesisContent);
		} else if (targetPanel == EAE_HISTORY) {
		    CmEAEView.this.setSecondComponent(v2);
		    // cmEAEHistoryContent =
		    // cmEAEHistoryContent.buildViewEAEHistoryContent();
		    // CmEAEView.this.setSecondComponent(cmEAEHistoryContent);
		} else if (targetPanel == EAE_CREATION) {
		    CmEAEView.this.setSecondComponent(v3);
		    // cmEAECreationContent =
		    // cmEAECreationContent.buildViewEAECreationContent();
		    // CmEAEView.this.setSecondComponent(cmEAECreationContent);
		} else if (targetPanel == EAE_PERSONAL) {
		    CmEAEView.this.setSecondComponent(v4);
		    // cmEAEPersonalContent =
		    // cmEAEPersonalContent.buildViewEAEPersonalContent();
		    // CmEAEView.this.setSecondComponent(cmEAEPersonalContent);
		}
	    }
	}, IEAELayout.class);

    }

    /**
     * The main builder
     * 
     * @class ProfileLayout.java
     */
    public void mainBuild() {

	cmEAENavigation = cmEAENavigation.buildCmEAENavigation();
	cmEAENavigation.setHeight("800px");

	this.setFirstComponent(cmEAENavigation);
//	cmEAEContent = cmEAEContent.buildViewEAEContent();
	this.setSecondComponent(cmEAEContent);
	//La synthèse par défaut
	cmEAENavigation.setTargetPanel(EAE_SYNTHESIS);
	cmEAENavigation.updateObservateur();

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

    public CmEAENavigation getCmEAENavigation() {
	return cmEAENavigation;
    }

    public void setCmEAENavigation(CmEAENavigation cmEAENavigation) {
	this.cmEAENavigation = cmEAENavigation;
    }

    public CmEAEContent getCmEAEContent() {
	return cmEAEContent;
    }

    public void setCmEAEContent(CmEAEContent cmEAEContent) {
	this.cmEAEContent = cmEAEContent;
    }

}
