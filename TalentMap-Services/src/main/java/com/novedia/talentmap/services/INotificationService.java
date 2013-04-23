package com.novedia.talentmap.services;

import com.novedia.talentmap.model.entity.UserNotification;


/**
 * Interface Notification service
 * @author j.maquin
 * @version 3.0
 */
public interface INotificationService {

	/**
	 * Save user's notification
	 * @param notification notification of the user
	 * @return Integer
	 */
	Integer saveNotification(UserNotification notification);
	
}
