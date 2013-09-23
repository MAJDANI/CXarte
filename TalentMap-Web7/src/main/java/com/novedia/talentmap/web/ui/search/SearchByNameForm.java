package com.novedia.talentmap.web.ui.search;

import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SearchByNameForm extends VerticalLayout {
	
	
	
	/**
	 * Vaadin components
	 */
	
	private TextField nameField;
	
	private ResourceBundle resourceBundle;
	
	
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
    	Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES , locale);
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
		nameField.setCaption(resourceBundle.getString("name.Field.textfield.caption"));
		addComponent(nameField);
		
	}
	
	public TextField getNameField() {
		return nameField;
	}

	public void setNameField(TextField nameField) {
		this.nameField = nameField;
	}

}
