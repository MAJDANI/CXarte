package com.novedia.talentmap.store.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * The Colleague DAO.
 * 
 * @author j.collet
 */
public class ColleagueDao extends SqlMapClientDaoSupport implements IDao<Colleague>{
	
	/**
	 * The logger
	 */
	private static Log logger = LogFactory.getLog(ColleagueDao.class);
	
	/**
	 * Class builder based on sqlMapClient
	 * @param sqlMapClient
	 */
	public ColleagueDao(final SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Colleague get(Integer id) throws DataAccessException {
		return (Colleague) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_COLLEAGUE, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Colleague> getAll() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLEAGUES);
	}

	@Override
	public int save(Colleague colleague) throws DataAccessException {
		return this.getSqlMapClientTemplate().update(DBRequestsConstants.SAVE_COLLEAGUE, colleague);
	}

	@Override
	public int add(Colleague colleague) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Add colleague");
		}
		return (Integer) this.getSqlMapClientTemplate().insert(DBRequestsConstants.ADD_COLLEAGUE, colleague);
	}

	@Override
	public int delete(Colleague collaborator) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Colleague getByName(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Colleague check(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}
	
	public Colleague check(Registration registration) throws DataAccessException{
		return (Colleague) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.CHECK_REGISTRATION, registration);
	}
	
	public Integer addColleagueFromRegistration(Registration registration) throws DataAccessException{
		return (Integer) this.getSqlMapClientTemplate().insert(DBRequestsConstants.ADD_COLLEAGUE_FROM_REGISTRATION, registration);
	}
	
	public List<Colleague> getAllColleaguesByLastName(String lastName) throws DataAccessException {
		return (List<Colleague>) this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLEAGUES_BY_LASTNAME, lastName);
	}
	
	public List<Colleague> getAllColleaguesByClientName(String clientName) throws DataAccessException {
		return (List<Colleague>) this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLEAGUES_BY_CLIENTNAME, clientName);
	}
	
	public List<Colleague> getAllColleaguesByListToolId(Map ToolIdMap) throws DataAccessException {
		return (List<Colleague>) this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLEAGUES_BY_LIST_TOOL_ID, ToolIdMap);
	}

}