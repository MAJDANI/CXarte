package com.novedia.talentmap.web.ui.colleague.eae;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.Images;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Table;
import com.novedia.talentmap.web.utils.EAEStateEnum;

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
	public static final String STATE_EAE_IMG = "";

	private String colDateCaption ="";
	private String colStateCaption ="";
	
	/**
	 * Default constructor
	 */
	public ListEAEDTO() {
		super();
		setSelectable(true);
		setImmediate(true);
		setNullSelectionAllowed(true);
	}

	/**
	 * Build 
	 * 
	 * @return
	 */
	
    public Table fillAllColleagueEAE(Integer colleagueId){
		addColumns();
    	removeAllItems();
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

			if (eae.getEaeStateId() == EAEStateEnum.OPEN.getId()) {
				addItem(new Object[] { date, eae.getEaeStateLabel(),
						Images.getImgButtonGreen() }, eae);
			} else if (eae.getEaeStateId() == EAEStateEnum.VALIDATED.getId()) {
				addItem(new Object[] { date, eae.getEaeStateLabel(),
						Images.getImgButtonOrange() }, eae);
			} else if (eae.getEaeStateId() == EAEStateEnum.CLOSED.getId()) {
				addItem(new Object[] { date, eae.getEaeStateLabel(),
						Images.getImgButtonRed()}, eae);
			} else {
				addItem(new Object[] { date, eae.getEaeStateLabel(),
						null }, eae);
			}
		}

	}

	/**
	 * Builds Headers of the Table ListEAEDTO
	 */
	public void addColumns() {
		addContainerProperty(colDateCaption, String.class, null);
		addContainerProperty(colStateCaption, String.class, null);
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

	/**
	 * @return the colDateCaption
	 */
	public String getColDateCaption() {
		return colDateCaption;
	}

	/**
	 * @param colDateCaption the colDateCaption to set
	 */
	public void setColDateCaption(String colDateCaption) {
		this.colDateCaption = colDateCaption;
	}

	/**
	 * @return the colStateCaption
	 */
	public String getColStateCaption() {
		return colStateCaption;
	}

	/**
	 * @param colStateCaption the colStateCaption to set
	 */
	public void setColStateCaption(String colStateCaption) {
		this.colStateCaption = colStateCaption;
	}


}
