package com.novedia.talentmap.web.ui.colleague.eae;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.EaeState;
import com.novedia.talentmap.web.utils.Images;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class ListEAEDTO extends Table {

	protected static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy");

	private EAEDTOContainer eaeDTOContainer;
	/**
	 * Colonnes
	 */
	public static final String DATE_EAE = "EAE's Date";
	public static final String STATE_EAE = "EAE's State";
	public static final String STATE_EAE_IMG = "EAE's Icon";

	/**
	 * Default constructor
	 */
	public ListEAEDTO() {
		super();
		addColumns();
		setSelectable(true);
		setImmediate(true);
		setNullSelectionAllowed(true);
	}

	/**
	 * Build 
	 * 
	 * @return
	 */
	
    public Table fillAllColleagueEAE(){
    	removeAllItems();
    	int colleagueId = TalentMapApplication.getCurrent().getAuthentication().getColleagueId();
		this.eaeDTOContainer.fillEAEContainer(colleagueId);
		fillTable();
    	return this;
    }


	/**
	 * Gets each item EAEForSynthesisDTO in the container. With each item
	 * EAEForSynthesisDTO we fill the actual Table using addItem() method.
	 * 
	 */
	public void fillTable() {
		Collection<EAEForSynthesisDTO> collectionEAE = this.eaeDTOContainer
				.getItemIds();
		
		for (EAEForSynthesisDTO eae : collectionEAE) {
			String date = formatterDate(eae.getDateEae());

			if (eae.getEaeStateId() == EaeState.OPEN) {
				addItem(new Object[] { date, eae.getEaeStateLabel(),
						Images.getImgFeuVert() }, eae);
			} else if (eae.getEaeStateId() == EaeState.VALIDATED) {
				addItem(new Object[] { date, eae.getEaeStateLabel(),
						Images.getImgFeuOrange() }, eae);
			} else if (eae.getEaeStateId() == EaeState.CLOSED) {
				addItem(new Object[] { date, eae.getEaeStateLabel(),
						Images.getImgFeuRouge() }, eae);
			}
		}

	}

	/**
	 * Builds Headers of the Table ListEAEDTO
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

	/**
	 * @return the eaeDTOContainer
	 */
	public EAEDTOContainer getEaeDTOContainer() {
		return eaeDTOContainer;
	}

	/**
	 * @param eaeDTOContainer the eaeDTOContainer to set
	 */
	public void setEaeDTOContainer(EAEDTOContainer eaeDTOContainer) {
		this.eaeDTOContainer = eaeDTOContainer;
	}


}
