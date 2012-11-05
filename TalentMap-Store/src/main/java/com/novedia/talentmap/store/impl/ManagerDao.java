package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * 
 * @author j.marie-sainte
 *
 */
public class ManagerDao  extends SqlMapClientDaoSupport implements IDao<Manager>{
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public ManagerDao(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public Manager get(Manager manager) throws DataAccessException {
		return (Manager) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_PROFILE, manager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manager> getAll() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_MISSION);
	}

	@Override
	public int save(Manager element) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int add(Manager element) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int delete(Manager element) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Manager check(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}
	
	
	/**
	 * Select all Collaborators by managerId
	 * @param managerId : the id of the manager
	 * @return all collaborators who depend on the manager managerId
	 * @author v.guillemain
	 */
	@SuppressWarnings("unchecked")
	public List<Collaborator> getAllCollaboratorsByManagerId(Integer managerId) throws DataAccessException {
		return (List<Collaborator>) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_ALL_COLLABORATOR, managerId);
				
	}

	
	
}