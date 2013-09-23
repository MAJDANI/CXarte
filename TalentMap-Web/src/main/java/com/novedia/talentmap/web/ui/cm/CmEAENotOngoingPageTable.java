package com.novedia.talentmap.web.ui.cm;

import java.util.Collection;

import com.jensjansson.pagedtable.PagedTable;
import com.novedia.talentmap.model.entity.Colleague;

@SuppressWarnings("serial")
public class CmEAENotOngoingPageTable extends PagedTable {

    private CmEAENotOngoingContainer cmEAENotOngoingContainer;
    /**
     * Colonnes
     */
    public static final String PRENOM = "First Name";
    public static final String NOM = "Last Name";

    private static final int PAGE_SIZE = 5;

    /**
     * Vaadin UI
     */

    private Integer colleagueId;


    /**
     * Default constructor
     */
    public CmEAENotOngoingPageTable() {
	super();
	addColumns();
	setSelectable(true);
	setNullSelectionAllowed(true);
    }

    /**
     * Build cm's notification
     * 
     * @return
     */
    public CmEAENotOngoingPageTable buildAll(int colleagueId) {
	this.colleagueId = colleagueId;
	removeAllItems();
	buildMain();
	return this;
    }

    public void buildMain() {
	setWidth("1000px");
	this.cmEAENotOngoingContainer.fillEAEContainer(getColleagueId());
	fillResultsTable();
    }


    /**
     * Gets each item Colleague in the container. With each item
     * Colleague we fill the actual Table using addItem() method.
     * 
     */
    public void fillResultsTable() {
	Collection<Colleague> collectionEAE = this.cmEAENotOngoingContainer.getItemIds();

	for (Colleague eae : collectionEAE) {
	    addItem(new Object[] {eae.getFirstName(), eae.getLastName()}, eae);
	}

    }

    /**
     * Builds Headers of the Table CmEAENotOngoingPageTable
     */
    public void addColumns() {
	addContainerProperty(PRENOM, String.class, null);
	addContainerProperty(NOM, String.class, null);
    }


    public Integer getColleagueId() {
	return colleagueId;
    }

    public void setColleagueId(Integer colleagueId) {
	this.colleagueId = colleagueId;
    }

    /**
     * @return the cmEAENotOngoingContainer
     */
    public CmEAENotOngoingContainer getCmEAENotOngoingContainer() {
        return cmEAENotOngoingContainer;
    }

    /**
     * @param cmEAENotOngoingContainer the cmEAENotOngoingContainer to set
     */
    public void setCmEAENotOngoingContainer(
    	CmEAENotOngoingContainer cmEAENotOngoingContainer) {
        this.cmEAENotOngoingContainer = cmEAENotOngoingContainer;
    }



}
