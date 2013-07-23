package com.novedia.talentmap.web.registration;

import java.util.List;

import com.novedia.talentmap.model.entity.BusinessEngineer;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IRegistrationService;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Select;

import com.vaadin.ui.TextField;

public class RegistrationFieldGroupFactory implements FieldGroupFieldFactory {
	
	/**
     * Serialization identifier
     */
    private static final long serialVersionUID = -6912883483308852687L;

    /**
     * The registration service
     */
    private IRegistrationService registrationService;

    /**
     * The business Engineer service
     */
    private IBusinessEngineerService businessEngineerService;

    private Object listener;

    /**
     * 
     * @param registration
     *            Service
     */
    public RegistrationFieldGroupFactory(
	    IRegistrationService registrationService,
	    IBusinessEngineerService businessEngineerService, Object listener) {
	this.registrationService = registrationService;
	this.businessEngineerService = businessEngineerService;
	this.listener = listener;
    }
    
    @SuppressWarnings("rawtypes")
    @Override
	public <T extends Field> T createField(Class<?> dataType, Class<T> fieldType) {
		return null;
	}

    

	

    public void setRegistrationService(IRegistrationService registrationService) {
	this.registrationService = registrationService;
    }

	

}
