package com.novedia.talentmap.web.ui.profile.mission;

import com.vaadin.data.Validator;

public class MissionValidator implements Validator {

    /**
	 * 
	 */
    private static final long serialVersionUID = -8850542344779077130L;

    /**
     * Upon failure, the validate() method throws an exception with an error
     * message.
     */
    @Override
    public void validate(Object value) throws InvalidValueException {
	if (!isValid(value)) {
	    if (value != null && value.toString().startsWith("0")) {
		throw new InvalidValueException(
			"Postal code must not start with a zero.");
	    } else {
		throw new InvalidValueException(
			"Postal code must be a number 10000-99999.");
	    }
	}
    }

    /**
     * The isValid() method returns simply a boolean value, so it can not return
     * an error message.
     */
    @Override
    public boolean isValid(Object value) {
	if (value == null || !(value instanceof String)) {
	    return false;
	}
	return ((String) value).matches("[1-9][0-9]{4}");
    }

}
