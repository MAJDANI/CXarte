package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * Tool DAO.
 * @author j.collet
 */
public class ToolDao extends SqlMapClientDaoSupport implements IDao<Tool> {

/**
 * Set the sqlMapClient value.
 * @param sqlMapClient the sqlMapClient to set
 */
public ToolDao(final SqlMapClient sqlMapClient) {
this.setSqlMapClient(sqlMapClient);
}

/**
 * Get Tool by id.
 * @param id a id
 * @return tool
 * @throws DataAccessException
 */
@Override
public Tool get(final Integer id) throws DataAccessException{
return (Tool) this.getSqlMapClientTemplate()
.queryForObject(DBRequestsConstants.GET_TOOL, id);
}

/**
 * Get all tools.
 * @return a list of tool
 * @throws DataAccessException
 */
@SuppressWarnings("unchecked")
@Override
public List<Tool> getAll() throws DataAccessException {
return this.getSqlMapClientTemplate()
.queryForList(DBRequestsConstants.GET_ALL_TOOL);
}

/**
 * @param tool of type tool
 * @throws DataAccessException
 */
@Override
public int save(final Tool tool) throws DataAccessException {
return (Integer) this.getSqlMapClientTemplate()
.insert(DBRequestsConstants.SAVE_TOOL, tool);
}

/**
 * @param tool of.
 * @throws DataAccessException
 */
@Override
public int delete(final Tool tool) throws DataAccessException {
return (Integer) this.getSqlMapClientTemplate()
.delete(DBRequestsConstants.DELETE_TOOL, tool.getId());
}

/**
 * Check the tool by name.
 * @return tool
 * @param name a name
 * @throws DataAccessException
 */
@Override
public Tool check(final String name) throws DataAccessException {
return (Tool) this.getSqlMapClientTemplate()
.queryForObject(DBRequestsConstants.CHECK_TOOL, name);
}

/**
 * Add a tool.
 * @param tool a tool
 * @throws DataAccessException
 */
@Override
public int add(final Tool tool) throws DataAccessException {
return (Integer) this.getSqlMapClientTemplate()
.queryForObject(DBRequestsConstants.ADD_TOOL, tool.getId());
}

/**
 * Get a tool name.
 * @return tool
 * @param name of the tool
 * @throws DataAccessException
 */
@Override
public Tool getByName(final String name) throws DataAccessException {
return (Tool) this.getSqlMapClientTemplate()
.queryForObject(DBRequestsConstants.GET_TOOL_BY_NAME, name);
}
}