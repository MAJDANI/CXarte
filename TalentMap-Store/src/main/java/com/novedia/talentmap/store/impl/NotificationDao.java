package com.novedia.talentmap.store.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Frequency;
import com.novedia.talentmap.model.entity.UserNotification;
import com.novedia.talentmap.store.INotificationDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

public class NotificationDao extends SqlMapClientDaoSupport implements
		INotificationDao {

	/**
	 * Class builder based on sqlMapClient
	 * 
	 * @param sqlMapClient
	 */
	public NotificationDao(final SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	/**
	 * Save Notification
	 */
	@Override
	public Integer saveNotification(UserNotification notification) throws DataAccessException{
		return (Integer) this.getSqlMapClientTemplate().insert(DBRequestsConstants.SAVE_NOTIFICATION, notification);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserNotification> getAllByManagerId(Integer collabId) throws DataAccessException{
		return (List<UserNotification>) this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_NOTIFICATION_BY_MANAGER_ID,collabId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Frequency> getAllFrequencyChoices() throws DataAccessException{
		return (List<Frequency>) this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_FREQUENCY_CHOICES);
	}
	
	@Override
	public Frequency getCmFrequencyOption(Integer managerId) throws DataAccessException{
		return (Frequency) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_CM_FREQUENCY_OPTION,managerId);
	}


	@Override
	public Integer saveFrequencyOption(int frequencyId, int managerId) throws DataAccessException {
		HashMap parameters = new HashMap();
		parameters.put("frequencyId", frequencyId);
		parameters.put("managerId", managerId);
		return (Integer) this.getSqlMapClientTemplate().update(DBRequestsConstants.SAVE_CM_FREQUENCY_OPTION, parameters);
	}
	
	@Override
	public Integer addFrequencyOption(int frequencyId, int managerId) throws DataAccessException {
		HashMap parameters = new HashMap();
		parameters.put("frequencyId", frequencyId);
		parameters.put("managerId", managerId);
		return (Integer) this.getSqlMapClientTemplate().insert(DBRequestsConstants.ADD_CM_FREQUENCY_OPTION, parameters);
	}

}
