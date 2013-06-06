package com.novedia.talentmap.web.ui.collab;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.jensjansson.pagedtable.PagedTable;
import com.novedia.talentmap.model.dto.EAEForSynthesis;
import com.novedia.talentmap.model.entity.Colleague;

@SuppressWarnings("serial")
public class CollabEAEHistoryPageTable extends PagedTable {

    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat(
	    "dd/MM/yyyy");

    private CollabEaeHistoryContainer collabEaeHistoryContainer;
    /**
     * Colonnes
     */
    public static final String DATE_EAE = "date de l'EAE";
    public static final String STATE_EAE = "Etat de l'EAE";
    private static final int PAGE_SIZE = 5;

    /**
     * Vaadin UI
     */

    private Integer colleagueId;

    /**
     * Default constructor
     */
    public CollabEAEHistoryPageTable() {
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
    public CollabEAEHistoryPageTable buildAll(int colleagueId) {
	this.colleagueId = colleagueId;
	removeAllItems();
	buildMain();
	return this;
    }

    public void buildMain() {
	setWidth("900px");
	this.collabEaeHistoryContainer.fillEAEContainer(getColleagueId());
	fillResultsTable();
    }

    /**
     * Gets each item EAEForSynthesis in the container. With each item
     * EAEForSynthesis we fill the actual Table using addItem() method.
     * 
     */
    public void fillResultsTable() {
	Collection<EAEForSynthesis> collectionEAE = this.collabEaeHistoryContainer
		.getItemIds();

	for (EAEForSynthesis eae : collectionEAE) {
	    String date = formatterDate(eae.getDateEae());
	    addItem(new Object[] { date, eae.getEaeStateLabel() }, eae);
	}

    }

    /**
     * Builds Headers of the Table CmListNotification
     */
    public void addColumns() {
	addContainerProperty(DATE_EAE, String.class, null);
	addContainerProperty(STATE_EAE, String.class, null);
    }

    /**
     * Format the date with the template dateFormat "dd/MM/yyyy"
     * 
     * @param date
     * @return
     */
    public static String formatterDate(Date date) {
	if (date != null) {
	    return dateFormat.format(date);
	} else {
	    return "";
	}
    }

    public Integer getColleagueId() {
	return colleagueId;
    }

    public void setColleagueId(Integer colleagueId) {
	this.colleagueId = colleagueId;
    }

    /**
     * @return the collabEaeHistoryContainer
     */
    public CollabEaeHistoryContainer getCollabEaeHistoryContainer() {
	return collabEaeHistoryContainer;
    }

    /**
     * @param collabEaeHistoryContainer
     *            the collabEaeHistoryContainer to set
     */
    public void setCollabEaeHistoryContainer(
	    CollabEaeHistoryContainer collabEaeHistoryContainer) {
	this.collabEaeHistoryContainer = collabEaeHistoryContainer;
    }

}
