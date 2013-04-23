package com.novedia.talentmap.store.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
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
	public Integer saveNotification(UserNotification notification) {
		return (Integer) this.getSqlMapClientTemplate().insert(
				DBRequestsConstants.SAVE_NOTIFICATION, notification);
	}

}
