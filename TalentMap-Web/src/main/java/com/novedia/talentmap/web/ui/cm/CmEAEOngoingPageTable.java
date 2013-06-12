package com.novedia.talentmap.web.ui.cm;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.jensjansson.pagedtable.PagedTable;
import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.web.commons.Images;
import com.novedia.talentmap.web.data.EaeState;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;

@SuppressWarnings("serial")
public class CmEAEOngoingPageTable extends PagedTable {

    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat(
	    "dd/MM/yyyy");

    private CmEAEOngoingContainer cmEAEOngoingContainer;
    /**
     * Colonnes
     */
    public static final String PRENOM = "First Name";
    public static final String NOM = "Last Name";
    public static final String DATE_EAE_EN_COURS = "Ongoig EAE's Date";
    public static final String STATE_EAE_EN_COURS = "Ongoig EAE's State";
    public static final String STATE_EAE_EN_COURS_IMG = "Ongoig EAE's Icon";

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
	setWidth("1000px");
	this.cmEAEOngoingContainer.fillEAEContainer(getColleagueId());
	fillResultsTable();
    }

    /**
     * Gets each item EAEForSynthesisDTO in the container. With each item
     * EAEForSynthesisDTO we fill the actual Table using addItem() method.
     * 
     */
    public void fillResultsTable() {
	Collection<EAEForSynthesisDTO> collectionEAE = this.cmEAEOngoingContainer
		.getItemIds();

	for (EAEForSynthesisDTO eae : collectionEAE) {
	    String date = formatterDate(eae.getDateEae());

	    if (eae.getEaeStateId() == EaeState.OPEN) {
		addItem(new Object[] { eae.getFirstName(), eae.getLastName(),
			date, eae.getEaeStateLabel(), Images.getImgFeuVert() }, eae);
	    } else if (eae.getEaeStateId() == EaeState.VALIDATED) {
		addItem(new Object[] { eae.getFirstName(), eae.getLastName(),
			date, eae.getEaeStateLabel(), Images.getImgFeuOrange()  }, eae);
	    } else if (eae.getEaeStateId() == EaeState.CLOSED) {
		addItem(new Object[] { eae.getFirstName(), eae.getLastName(),
			date, eae.getEaeStateLabel(), Images.getImgFeuRouge()  }, eae);
	    }

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
	addContainerProperty(STATE_EAE_EN_COURS_IMG, Embedded.class, null);
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
    public CmEAEOngoingContainer getCmEAEOngoingContainer() {
	return cmEAEOngoingContainer;
    }

    /**
     * @param cmEAEOngoingContainer
     *            the cmEAEOngoingContainer to set
     */
    public void setCmEAEOngoingContainer(
	    CmEAEOngoingContainer cmEAEOngoingContainer) {
	this.cmEAEOngoingContainer = cmEAEOngoingContainer;
    }

}
