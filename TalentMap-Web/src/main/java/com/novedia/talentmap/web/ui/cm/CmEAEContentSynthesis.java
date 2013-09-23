package com.novedia.talentmap.web.ui.cm;

import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.model.dto.EAEGeneralityDTO;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.services.impl.EAEService;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmEAEContentSynthesis extends VerticalLayout implements ClickListener, ItemClickListener {

    private Authentication authentication;
    private CmEAEOngoingPageTable cmEAEOngoingPageTable;
    private CmEAENotOngoingPageTable cmEAENotOngoingPageTable;
    private static final String TAB_ONGOING_TITLE = "Ongoing EAE";
    private static final String TAB_NO_ONGOING_TITLE = "Colleagues without Ongoing EAE";
    private IEAEService eaeService;

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
	
	this.addComponent(new Label(TAB_ONGOING_TITLE));
	cmEAEOngoingPageTable.buildAll(getAuthentication().getColleagueId());
	cmEAEOngoingPageTable.addListener(this);
	
	this.addComponent(cmEAEOngoingPageTable);
	///***************************
	// Colleagues without Ongoing EAE
	///***************************
	this.addComponent(new Label(TAB_NO_ONGOING_TITLE));
	cmEAENotOngoingPageTable.buildAll(getAuthentication().getColleagueId());
	this.addComponent(cmEAENotOngoingPageTable);
	setSizeFull();
	setMargin(true);
	setSpacing(true);
	return this;
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        EAEForSynthesisDTO eae = (EAEForSynthesisDTO)event.getItemId();
        EAEGeneralityDTO eG = this.eaeService.getEAEGenerality(eae.getId());
        //TODO temporaire
        System.out.println(eG);
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

    /**
     * @return the eaeService
     */
    public IEAEService getEaeService() {
        return eaeService;
    }

    /**
     * @param eaeService the eaeService to set
     */
    public void setEaeService(IEAEService eaeService) {
        this.eaeService = eaeService;
    }

}
