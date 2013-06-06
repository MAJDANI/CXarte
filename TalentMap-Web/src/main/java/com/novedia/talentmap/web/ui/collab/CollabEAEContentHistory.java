package com.novedia.talentmap.web.ui.collab;

import com.jensjansson.pagedtable.PagedTable;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.web.ui.cm.CmEAENotOngoingPageTable;
import com.novedia.talentmap.web.ui.cm.CmEAEOngoingPageTable;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CollabEAEContentHistory extends VerticalLayout implements ClickListener {

    private Authentication authentication;
    private CollabEAEHistoryPageTable collabEAEHistoryPageTable;

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

}
