package com.novedia.talentmap.web.ui.formFactory;

import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class EAEGeneralityFormFieldFactory implements FormFieldFactory {


    public EAEGeneralityFormFieldFactory() {
	super();
    }


    @Override
    public Field createField(Item item, Object propertyId, Component uiContext) {

	for (int i = 0; i < ConstantsEnglish.FIELD_ORDER_EAE_GENERALITY.length; i++) {

	    if (propertyId
		    .equals(ConstantsEnglish.FIELD_ORDER_EAE_GENERALITY[i])) {

		if (propertyId.equals(ConstantsEnglish.FIELD_SALARY)) {

		    TextField field = new TextField(
			    (String) ConstantsEnglish.NAME_FIELD_EAE_GENERALITY[i]
				    + " : ");
		    field.setNullRepresentation(ConstantsEnglish.FIELD_NULL_REPRESENTATION);

		    field.setRequired(true);
		    field.setRequiredError(ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART1
			    + ConstantsEnglish.NAME_FIELD_EAE_GENERALITY[i]
			    + ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART2);
		    field.setValidationVisible(true);

		    field.setMaxLength(ConstantsEnglish.EAE_GENERALITY_SALARY_MAX_LENGTH);
		    field.setStyleName("first-name");
		    return field;
		} else {
		    TextField field = new TextField(
			    (String) ConstantsEnglish.NAME_FIELD_EAE_GENERALITY[i]
				    + " : ");
		    field.setNullRepresentation(ConstantsEnglish.FIELD_NULL_REPRESENTATION);
		    field.setStyleName("first-name");
		    field.setReadOnly(true);
		    return field;
		}
	    }
	}
	return null;
    }

}
