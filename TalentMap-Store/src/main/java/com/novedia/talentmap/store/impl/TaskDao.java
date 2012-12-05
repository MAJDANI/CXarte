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
 * @author moumbe
 *
 */
public class TaskDao extends SqlMapClientDaoSupport implements IDao<Task> {

/**
 * Constructor.
 * @param sqlMapClient a SqlMapClient
 */
public TaskDao(final SqlMapClient sqlMapClient) {
setSqlMapClient(sqlMapClient);
}

/**
 * @return id a id.
 * @param id a id
 * @throws DataAccessException
 */
@Override
public Task get(final Integer id) throws DataAccessException {
return (Task) this.getSqlMapClientTemplate()
.queryForObject(DBRequestsConstants.GET_TASK_BY_ID, id);
}

/**
 * @return list of Task.
 * @throws DataAccessException
 */
@Override
public List<Task> getAll() throws DataAccessException {
return null;
}

/**
 * @param element of type task.
 * @throws DataAccessException
 */
@Override
public int save(Task element) throws DataAccessException {
return 0;
}

/**
 * @param element task
 * @throws DataAccessException
 */
@Override
public int add(final Task element) throws DataAccessException {
return 0;
}

/**
 * @param element task.
 * @throws DataAccessException
 */
@Override
public int delete(final Task element) throws DataAccessException {
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
