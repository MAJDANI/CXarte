package com.novedia.talentmap.store;

import com.novedia.talentmap.model.entity.UserNotification;

/**
 * The notification DAO interface.
 * @author j.maquin
 *
 */
public interface INotificationDao {

	/**
	 * Save collaborator notification.
	 * @class INotificationDao.java
	 * @param notification a notification
	 * @return an integer
	 */
	Integer saveNotification(UserNotification notification);
	
}
