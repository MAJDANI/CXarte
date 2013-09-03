package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ModifyToolForm extends VerticalLayout {
	
	/**
	 * Vaadin components
	 */
	
	private TextField categoryField;
	
	private TextField conceptField;
	
	private TextField toolField;
	
	/**
	 * Default constructor
	 */
	public ModifyToolForm() {
		super();
		setSpacing(true);
	}

	public ModifyToolForm buildModifyToolForm() {
		
		categoryField.setCaption(Constants.CATEGORY_CAPTION);
		categoryField.setId(ComponentsId.CATEGORY_ID);
		categoryField.setImmediate(true);
		addComponent(categoryField);
		
		conceptField.setCaption(Constants.CONCEPT_CAPTION);
		conceptField.setId(ComponentsId.CONCEPT_ID);
		conceptField.setImmediate(true);
		addComponent(conceptField);
		
		toolField.setCaption(Constants.TOOL_CAPTION);
		toolField.setId(ComponentsId.TOOL_ID);
		toolField.setImmediate(true);
		addComponent(toolField);
		
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

	public TextField getToolField() {
		return toolField;
	}

	public void setToolField(TextField toolField) {
		this.toolField = toolField;
	}

}
