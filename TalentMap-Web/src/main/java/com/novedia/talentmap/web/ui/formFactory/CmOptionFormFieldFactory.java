package com.novedia.talentmap.web.ui.formFactory;

import java.util.List;

import com.novedia.talentmap.model.entity.Frequency;
import com.novedia.talentmap.services.INotificationService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Select;

/**
 * Create field instance for CmOptionForm
 * 
 * @author j.maquin
 * 
 */
public class CmOptionFormFieldFactory implements FormFieldFactory {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3853034798754733877L;

    private INotificationService notificationService;

    /**
     * 
     * @param Notification
     *            Service
     */
    public CmOptionFormFieldFactory(INotificationService notificationService) {
	this.notificationService = notificationService;
    }

    @Override
    public Field createField(Item item, Object propertyId, Component uiContext) {

	for (int i = 0; i < ConstantsEnglish.FIELD_ORDER_CM_OPTIONS.length; i++) {

	    if (propertyId.equals(ConstantsEnglish.FIELD_ORDER_CM_OPTIONS[i])) {

		Select emailFrequency = new Select(
			ConstantsEnglish.NAME_FIELD_CM_OPTIONS[i] + " : ");
		try {
		    List<Frequency> allFrequencies = notificationService
			    .getAllFrequencyChoices();

		    for (Frequency f : allFrequencies) {
			emailFrequency.addItem(f);
			emailFrequency.setItemCaption(f, f.getName());
		    }

		    emailFrequency.setNullSelectionAllowed(false);
		    emailFrequency.setImmediate(true);

		} catch (Exception e) {
		    e.printStackTrace();
		}
		return emailFrequency;

	    }
	}
	return null;
    }

    public INotificationService getNotificationService() {
	return notificationService;
    }

    public void setNotificationService(INotificationService notificationService) {
	this.notificationService = notificationService;
    }
}
