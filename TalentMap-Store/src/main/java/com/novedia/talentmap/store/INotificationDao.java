package com.novedia.talentmap.store;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Frequency;
import com.novedia.talentmap.model.entity.UserNotification;

/**
 * The notification DAO interface.
 * 
 * @author j.maquin
 * 
 */
public interface INotificationDao {

	/**
	 * Save collaborator notification.
	 * 
	 * @class INotificationDao.java
	 * @param notification
	 *            a notification
	 * @return an integer
	 */
	Integer saveNotification(UserNotification notification)
			throws DataAccessException;

	/**
	 * get cm notifications.
	 * 
	 * @class INotificationDao.java
	 * @param collabId
	 *            id of the cm
	 * @return List<UserNotification>
	 */
	List<UserNotification> getAllByManagerId(Integer collabId)
			throws DataAccessException;

	/**
	 * get frequency options.
	 * 
	 * @class INotificationDao.java
	 * @return List<Frequency>
	 */
	List<Frequency> getAllFrequencyChoices() throws DataAccessException;

	/**
	 * get frequency option of this CM.
	 * 
	 * @class INotificationDao.java
	 * @param managerId
	 *            id of the CM
	 * @return Frequency
	 */
	Frequency getCmFrequencyOption(Integer managerId)
			throws DataAccessException;

	/**
	 * save frequency option of this CM.
	 * 
	 * @class INotificationDao.java
	 * @param frequency
	 *            frequency of notifications
	 * @param managerId
	 *            id of the CM
	 * @return Integer
	 */
	Integer saveFrequencyOption(int frequencyId, int managerId)
			throws DataAccessException;

	/**
	 * add frequency option of this CM.
	 * 
	 * @class INotificationDao.java
	 * @param frequency
	 *            frequency of notifications
	 * @param managerId
	 *            id of the CM
	 * @return Integer
	 */
	Integer addFrequencyOption(int frequencyId, int managerId)
			throws DataAccessException;

}
