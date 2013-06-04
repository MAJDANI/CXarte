package com.novedia.talentmap.web.ui.cm;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.jensjansson.pagedtable.PagedTable;
import com.novedia.talentmap.model.dto.EAEForSynthesis;
import com.novedia.talentmap.model.entity.Colleague;

@SuppressWarnings("serial")
public class CmEAEOngoingPageTable extends PagedTable {

    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat(
	    "dd/MM/yyyy");

    private CmEaeOngoingContainer cmEAEOngoingContainer;
    /**
     * Colonnes
     */
    public static final String PRENOM = "Prénom";
    public static final String NOM = "Nom";
    public static final String DATE_EAE_EN_COURS = "date de l'EAE en cours";
    public static final String STATE_EAE_EN_COURS = "Etat de l'EAE en cours";

    private static final int PAGE_SIZE = 5;

    /**
     * Vaadin UI
     */

    private Integer colleagueId;

    /**
     * Default constructor
     */
    public CmEAEOngoingPageTable() {
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
    public CmEAEOngoingPageTable buildAll(int colleagueId) {
	this.colleagueId = colleagueId;
	removeAllItems();
	buildMain();
	return this;
    }

    public void buildMain() {
	setWidth("900px");
	this.cmEAEOngoingContainer.fillEAEContainer(getColleagueId());
	fillResultsTable();
    }

    /**
     * Gets each item EAEForSynthesis in the container. With each item
     * EAEForSynthesis we fill the actual Table using addItem() method.
     * 
     */
    public void fillResultsTable() {
	Collection<EAEForSynthesis> collectionEAE = this.cmEAEOngoingContainer
		.getItemIds();

	for (EAEForSynthesis eae : collectionEAE) {
	    String date = formatterDate(eae.getDateEae());
	    addItem(new Object[] { eae.getFirstName(), eae.getLastName(), date,
		    eae.getEaeStateLabel() }, eae);
	}

    }

    /**
     * Builds Headers of the Table CmListNotification
     */
    public void addColumns() {
	addContainerProperty(PRENOM, String.class, null);
	addContainerProperty(NOM, String.class, null);
	addContainerProperty(DATE_EAE_EN_COURS, String.class, null);
	addContainerProperty(STATE_EAE_EN_COURS, String.class, null);
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
     * @return the cmEAEOngoingContainer
     */
    public CmEaeOngoingContainer getCmEAEOngoingContainer() {
	return cmEAEOngoingContainer;
    }

    /**
     * @param cmEAEOngoingContainer
     *            the cmEAEOngoingContainer to set
     */
    public void setCmEAEOngoingContainer(
	    CmEaeOngoingContainer cmEAEOngoingContainer) {
	this.cmEAEOngoingContainer = cmEAEOngoingContainer;
    }

}
