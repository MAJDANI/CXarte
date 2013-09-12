package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ModifyConceptForm extends VerticalLayout {
	
	/**
	 * Vaadin components
	 */
	
	private TextField categoryField;
	
	private TextField conceptField;
	
	/**
	 * Default constructor
	 */
	public ModifyConceptForm() {
		super();
		setSpacing(true);
	}
	

	public ModifyConceptForm buildModifyConceptForm() {
		
		categoryField.setCaption(Constants.CATEGORY_CAPTION);
		categoryField.setId(ComponentsId.CATEGORY_ID);
		categoryField.setImmediate(true);
		addComponent(categoryField);
		
		conceptField.setCaption(Constants.CONCEPT_CAPTION);
		conceptField.setId(ComponentsId.CONCEPT_ID);
		conceptField.setImmediate(true);
		addComponent(conceptField);
		
		return this;
	}

	public TextField getCategoryField() {
		return categoryField;
	}

	public void setCategoryField(TextField categoryField) {
		this.categoryField = categoryField;
	}

	public TextField getConceptField() {
		return conceptField;
	}

	public void setConceptField(TextField conceptField) {
		this.conceptField = conceptField;
	}

	
	
	
}
