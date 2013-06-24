package com.novedia.talentmap.web.ui.EAE;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.jensjansson.pagedtable.PagedTable;
import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;
import com.novedia.talentmap.web.commons.Images;
import com.novedia.talentmap.web.data.EaeState;

@SuppressWarnings("serial")
public class CollabEAEHistoryPageTable extends PagedTable {

    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat(
	    "dd/MM/yyyy");

    private CollabEAEHistoryContainer collabEAEHistoryContainer;
    /**
     * Colonnes
     */
    public static final String DATE_EAE = "EAE's Date";
    public static final String STATE_EAE = "EAE's State";
    public static final String STATE_EAE_IMG = "EAE's Icon";
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
	setWidth("1000px");
	this.collabEAEHistoryContainer.fillEAEContainer(getColleagueId());
	fillResultsTable();
    }

    /**
     * Gets each item EAEForSynthesisDTO in the container. With each item
     * EAEForSynthesisDTO we fill the actual Table using addItem() method.
     * 
     */
    public void fillResultsTable() {
	Collection<EAEForSynthesisDTO> collectionEAE = this.collabEAEHistoryContainer
		.getItemIds();

	for (EAEForSynthesisDTO eae : collectionEAE) {
	    String date = formatterDate(eae.getDateEae());

	    if (eae.getEaeStateId() == EaeState.OPEN) {
		addItem(new Object[] { date, eae.getEaeStateLabel(), Images.getImgFeuVert()}, eae);
	    } else if (eae.getEaeStateId() == EaeState.VALIDATED) {
		addItem(new Object[] { date, eae.getEaeStateLabel(), Images.getImgFeuOrange()}, eae);
	    } else if (eae.getEaeStateId() == EaeState.CLOSED) {
		addItem(new Object[] { date, eae.getEaeStateLabel(), Images.getImgFeuRouge() }, eae);
	    }
	}

    }

    /**
     * Builds Headers of the Table CollabEAEHistoryPageTable
     */
    public void addColumns() {
	addContainerProperty(DATE_EAE, String.class, null);
	addContainerProperty(STATE_EAE, String.class, null);
	addContainerProperty(STATE_EAE_IMG, Embedded.class, null);
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
     * @return the collabEAEHistoryContainer
     */
    public CollabEAEHistoryContainer getCollabEAEHistoryContain() {
	return collabEAEHistoryContainer;
    }

    /**
     * @param collabEAEHistoryContainer
     *            the collabEAEHistoryContainer to set
     */
    public void setCollabEAEHistoryContainer(
	    CollabEAEHistoryContainer collabEAEHistoryContainer) {
	this.collabEAEHistoryContainer = collabEAEHistoryContainer;
    }

}
