package com.novedia.talentmap.web.ui.cm;

import com.novedia.talentmap.model.entity.Authentication;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmEAEContentSynthesis extends VerticalLayout implements ClickListener {

    private Authentication authentication;
    private CmEAEOngoingPageTable cmEAEOngoingPageTable;
    private CmEAENotOngoingPageTable cmEAENotOngoingPageTable;

    /**
     * Default constructor
     */
    public CmEAEContentSynthesis() {
	super();
    }

    public CmEAEContentSynthesis buildViewSynthesis() {
	removeAllComponents();
	///****************************
	// Ongoing EAE
	///***************************
	
	this.addComponent(new Label("Ongoing EAE"));
	cmEAEOngoingPageTable.buildAll(getAuthentication().getColleagueId());
	this.addComponent(cmEAEOngoingPageTable);
	///***************************
	// Colleagues without Ongoing EAE
	///***************************
	this.addComponent(new Label("Colleagues without Ongoing EAE"));
	cmEAENotOngoingPageTable.buildAll(getAuthentication().getColleagueId());
	this.addComponent(cmEAENotOngoingPageTable);
	setSizeFull();
	setMargin(true);
	setSpacing(true);
	return this;
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
     * @return the cmEAEOngoingPageTable
     */
    public CmEAEOngoingPageTable getCmEAEOngoingPageTable() {
	return cmEAEOngoingPageTable;
    }

    /**
     * @param cmEAEOngoingPageTable
     *            the cmEAEOngoingPageTable to set
     */
    public void setCmEAEOngoingPageTable(
	    CmEAEOngoingPageTable cmEAEOngoingPageTable) {
	this.cmEAEOngoingPageTable = cmEAEOngoingPageTable;
    }

    /**
     * @return the cmEAENotOngoingPageTable
     */
    public CmEAENotOngoingPageTable getCmEAENotOngoingPageTable() {
        return cmEAENotOngoingPageTable;
    }

    /**
     * @param cmEAENotOngoingPageTable the cmEAENotOngoingPageTable to set
     */
    public void setCmEAENotOngoingPageTable(
    	CmEAENotOngoingPageTable cmEAENotOngoingPageTable) {
        this.cmEAENotOngoingPageTable = cmEAENotOngoingPageTable;
    }

}
