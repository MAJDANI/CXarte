package com.novedia.talentmap.services.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Frequency;
import com.novedia.talentmap.model.entity.UserNotification;
import com.novedia.talentmap.services.INotificationService;
import com.novedia.talentmap.store.INotificationDao;

/**
 * NotificationService class
 * 
 * @author j.maquin
 * @version TMP 3.0
 */
public class NotificationService implements INotificationService {

    /**
     * authenticationDao
     */
    private INotificationDao notificationDao;

    @Override
    public Integer saveNotification(UserNotification notification)
	    throws DataAccessException {

	return notificationDao.saveNotification(notification);
    }

    @Override
    public List<UserNotification> getAllNotification(Integer collabId)
	    throws DataAccessException {

	return notificationDao.getAllByManagerId(collabId);
    }

    @Override
    public List<Frequency> getAllFrequencyChoices() throws DataAccessException {
	return notificationDao.getAllFrequencyChoices();
    }

    @Override
    public Frequency getCmFrequencyOption(Integer managerId)
	    throws DataAccessException {
	return notificationDao.getCmFrequencyOption(managerId);
    }

    @Override
    public Integer saveFrequencyOption(int frequencyId, int managerId)
	    throws DataAccessException {
	return notificationDao.saveFrequencyOption(frequencyId, managerId);
    }

    @Override
    public Integer addFrequencyOption(int frequencyId, int managerId)
	    throws DataAccessException {
	return notificationDao.addFrequencyOption(frequencyId, managerId);
    }

    public INotificationDao getNotificationDao() {
	return notificationDao;
    }

    public void setNotificationDao(INotificationDao notificationDao) {
	this.notificationDao = notificationDao;
    }

}
