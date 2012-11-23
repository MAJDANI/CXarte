package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Task;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * The {@link TaskDao} handles query for table Task
 * @author moumbe
 *
 */
public class TaskDao extends SqlMapClientDaoSupport implements IDao<Task> {

	/**
	 * Constructor
	 * @param sqlMapClient
	 */
	public TaskDao (final SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Task get(Integer id) throws DataAccessException {
		return (Task) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_TASK_BY_ID, id);
	}

	@Override
	public List<Task> getAll() throws DataAccessException {
		return null;
	}

	@Override
	public int save(Task element) throws DataAccessException {
		return 0;
	}

	@Override
	public int add(Task element) throws DataAccessException {
		return 0;
	}

	@Override
	public int delete(Task element) throws DataAccessException {
		return 0;
	}

	@Override
	public Task getByName(String name) throws DataAccessException {
		return null;
	}

	@Override
	public Task check(String name) throws DataAccessException {
		return null;
	}

}
