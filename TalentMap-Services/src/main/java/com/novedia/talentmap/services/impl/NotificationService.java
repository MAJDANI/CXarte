package com.novedia.talentmap.services.impl;

import com.novedia.talentmap.model.entity.UserNotification;
import com.novedia.talentmap.services.INotificationService;
import com.novedia.talentmap.store.INotificationDao;

/**
 * NotificationService class
 * @author j.maquin
 * @version TMP 3.0
 */
public class NotificationService implements INotificationService{

	/**
	 * authenticationDao
	 */
	private INotificationDao notificationDao;

	@Override
	public Integer saveNotification(UserNotification notification) {
		
		return notificationDao.saveNotification(notification);
	}
	
	public INotificationDao getNotificationDao() {
		return notificationDao;
	}

	public void setNotificationDao(INotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}

}
