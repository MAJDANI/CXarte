package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Task;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * The {@link TaskDao} handles query for table Task.
 * 
 * @author e.moumbe
 * 
 */
public class TaskDao extends SqlMapClientDaoSupport implements IDao<Task> {

	/**
	 * TaskDao constructor.
	 * 
	 * @param sqlMapClient
	 *            a SqlMapClient
	 */
	public TaskDao(final SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.store.IDao#get(java.lang.Integer)
	 */
	@Override
	public Task get(final Integer id) throws DataAccessException {
		return (Task) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_TASK_BY_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.store.IDao#getAll()
	 */
	@Override
	public List<Task> getAll() throws DataAccessException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.store.IDao#save(java.lang.Object)
	 */
	@Override
	public int save(Task task) throws DataAccessException {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.store.IDao#add(java.lang.Object)
	 */
	@Override
	public int add(final Task task) throws DataAccessException {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.store.IDao#delete(java.lang.Object)
	 */
	@Override
	public int delete(final Task task) throws DataAccessException {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.store.IDao#getByName(java.lang.String)
	 */
	@Override
	public Task getByName(String name) throws DataAccessException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novedia.talentmap.store.IDao#check(java.lang.String)
	 */
	@Override
	public Task check(String name) throws DataAccessException {
		return null;
	}
}
