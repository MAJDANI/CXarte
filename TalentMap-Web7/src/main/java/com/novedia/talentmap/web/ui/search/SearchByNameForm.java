package com.novedia.talentmap.web.ui.search;

import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SearchByNameForm extends VerticalLayout {
	
	
	
	/**
	 * Vaadin components
	 */
	
	private TextField nameField;
	
	
	 /**
     * Constructeur par défaut
     */
    public SearchByNameForm() {
    	super();
    }
    
    /**
     * Build SearchByNameForm view
     * 
     * @return
     */
    public SearchByNameForm buildSearchByNameFormView() {
    	removeAllComponents();
    	buildMain();
    	return this;
    }

	private void buildMain() {
		setMargin(true);
		setSpacing(true);
		buildField();
	}

	private void buildField() {
		nameField.setCaption(Constants.COLLABORATOR_NAME_LABEL);
		addComponent(nameField);
		
	}
	
	public TextField getNameField() {
		return nameField;
	}

	public void setNameField(TextField nameField) {
		this.nameField = nameField;
	}

}
