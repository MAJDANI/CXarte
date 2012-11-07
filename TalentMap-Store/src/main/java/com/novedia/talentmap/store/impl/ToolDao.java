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
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class ToolDao extends SqlMapClientDaoSupport implements IDao<Tool> {
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public ToolDao(final SqlMapClient sqlMapClient) {
		this.setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Get Tool by id
	 */
	@Override
	public Tool get(Integer id) throws DataAccessException{
		return (Tool) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_TOOL, id);
	}
	
	/**
	 * Get all tools 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Tool> getAll() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_TOOL);
	}
	
	@Override
	public int save(Tool tool) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().insert(DBRequestsConstants.SAVE_TOOL, tool);
	}

	@Override
	public int delete(Tool tool) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().delete(DBRequestsConstants.DELETE_TOOL, tool.getId());
	}

	@Override
	public Tool check(String name) throws DataAccessException {
		return (Tool) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.CHECK_TOOL, name);
	}

	@Override
	public int add(Tool tool) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.ADD_TOOL, tool.getId());
	}

	@Override
	public Tool getByName(String name) throws DataAccessException {
		return (Tool) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_TOOL_BY_NAME, name);
	}
}