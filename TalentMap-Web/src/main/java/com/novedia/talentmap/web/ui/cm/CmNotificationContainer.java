package com.novedia.talentmap.web.ui.cm;

import java.util.List;

import com.novedia.talentmap.model.entity.UserNotification;
import com.novedia.talentmap.services.INotificationService;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class CmNotificationContainer extends
	BeanItemContainer<UserNotification> {

    private INotificationService notificationService;

    /**
     * Default constructor
     */
    public CmNotificationContainer() {
	super(UserNotification.class);
    }

    /**
     * Calls the CollaboratorService to retrieve all missions in the Data Base
     * for the collaborator's id given
     * 
     * @param collabId
     * @return
     */
    public CmNotificationContainer fillContainer(int collabId) {

	try {
	    List<UserNotification> CmListNotification = this.notificationService
		    .getAllNotification(collabId);
	    removeAllItems();
	    if (CmListNotification != null && !CmListNotification.isEmpty()) {
		for (UserNotification n : CmListNotification) {
		    addBean(n);

		}

	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return this;
    }

    public INotificationService getNotificationService() {
	return notificationService;
    }

    public void setNotificationService(INotificationService notificationService) {
	this.notificationService = notificationService;
    }
}
