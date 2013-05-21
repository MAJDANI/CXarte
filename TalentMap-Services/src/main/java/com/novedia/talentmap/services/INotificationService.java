package com.novedia.talentmap.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Frequency;
import com.novedia.talentmap.model.entity.UserNotification;

/**
 * Interface Notification service
 * 
 * @author j.maquin
 * @version 3.0
 */
public interface INotificationService {

    /**
     * Save user's notification
     * 
     * @param notification
     *            notification of the user
     * @return Integer
     */
    Integer saveNotification(UserNotification notification)
	    throws DataAccessException;

    /**
     * Get user's notifications
     * 
     * @param collabId
     *            id of the CM
     * @return List<UserNotification>
     */
    List<UserNotification> getAllNotification(Integer collabId)
	    throws DataAccessException;

    /**
     * Get frequency options
     * 
     * @return List<Frequency>
     */
    List<Frequency> getAllFrequencyChoices() throws DataAccessException;

    /**
     * Get frequency option of this CM
     * 
     * @param managerId
     *            id of the CM
     * @return Frequency
     */
    Frequency getCmFrequencyOption(Integer managerId)
	    throws DataAccessException;

    /**
     * Save user's notification frequency option
     * 
     * @param frequency
     *            frequency of notifications
     * @param managerId
     *            id of the manager
     * @return Integer
     */
    Integer saveFrequencyOption(int frequencyId, int managerId)
	    throws DataAccessException;

    /**
     * Add user's notification frequency option
     * 
     * @param frequency
     *            frequency of notifications
     * @param managerId
     *            id of the manager
     * @return Integer
     */
    Integer addFrequencyOption(int frequencyId, int managerId)
	    throws DataAccessException;
}
