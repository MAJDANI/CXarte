package com.novedia.talentmap.web.ui.EAE;

import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.model.dto.EAEGeneralityDTO;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.web.ui.search.SearchResults;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CollabEAEContentHistory extends VerticalLayout implements ClickListener, ItemClickListener {

    private Authentication authentication;
    private CollabEAEHistoryPageTable collabEAEHistoryPageTable;
    private EAEWindow eaeWindow;

    private static final String FORM_TITLE = "EAE History";

    /**
     * Default constructor
     */
    public CollabEAEContentHistory() {
	super();
    }

    public CollabEAEContentHistory buildViewHistory() {
	removeAllComponents();
	
	this.addComponent(new Label(FORM_TITLE));
	collabEAEHistoryPageTable.buildAll(getAuthentication().getColleagueId());
	collabEAEHistoryPageTable.addListener(this);
	this.addComponent(collabEAEHistoryPageTable);

	setSizeFull();
	setMargin(true);
	setSpacing(true);
	return this;
    }

    @Override
    public void buttonClick(ClickEvent event) {
	// TODO Auto-generated method stub
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        EAEForSynthesisDTO eae = (EAEForSynthesisDTO)event.getItemId();
//	this.eaeWindow.setEaeForSynthesisDTO(eae);
	this.eaeWindow.build(eae);
        getWindow().addWindow(eaeWindow);
        
    }

    public Authentication getAuthentication() {
	return authentication;
    }

    public void setAuthentication(Authentication authentication) {
	this.authentication = authentication;
    }

    /**
     * @return the collabEAEHistoryPageTable
     */
    public CollabEAEHistoryPageTable getCollabEAEHistoryPageTable() {
        return collabEAEHistoryPageTable;
    }

    /**
     * @param collabEAEHistoryPageTable the collabEAEHistoryPageTable to set
     */
    public void setCollabEAEHistoryPageTable(
    	CollabEAEHistoryPageTable collabEAEHistoryPageTable) {
        this.collabEAEHistoryPageTable = collabEAEHistoryPageTable;
    }

    /**
     * @return the eaeWindow
     */
    public EAEWindow getEaeWindow() {
        return eaeWindow;
    }

    /**
     * @param eaeWindow the eaeWindow to set
     */
    public void setEaeWindow(EAEWindow eaeWindow) {
        this.eaeWindow = eaeWindow;
    }

}
