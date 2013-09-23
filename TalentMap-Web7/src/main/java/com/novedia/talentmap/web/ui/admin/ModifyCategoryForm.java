package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ModifyCategoryForm extends VerticalLayout{
	
	/**
	 * Vaadin components
	 */
	
	private TextField categoryField;


	/**
	 * Default constructor
	 */
	public ModifyCategoryForm() {
		super();
		setSpacing(true);
	}
	
	public ModifyCategoryForm buildModifyCategoryForm() {
		
		categoryField.setCaption(Constants.CATEGORY_CAPTION);
		categoryField.setId(ComponentsId.CATEGORY_ID);
		categoryField.setImmediate(true);
		addComponent(categoryField);
		
		return this;
	}
	
	public TextField getCategoryField() {
		return categoryField;
	}

	public void setCategoryField(TextField categoryField) {
		this.categoryField = categoryField;
	}

	
	
	
	
}
