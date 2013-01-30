package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * Manager DAO.
 * @author j.marie-sainte
 *
 */
public class ManagerDao  extends SqlMapClientDaoSupport implements IDao<Manager>{
	
/**
 * Set the sqlMapClient value.
 * @param sqlMapClient the sqlMapClient to set
 */
public ManagerDao(final SqlMapClient sqlMapClient) {
setSqlMapClient(sqlMapClient);
}

/**
 * This method allow to get a manager by id.
 * @param id a id
 * @return manager
 * @throws DataAccessException
 */
@Override
public Manager get(final Integer id) throws DataAccessException {
return (Manager) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_MANAGER, id);
}

/**
 * This method allow to get all manager.
 * @return a list of manager
 * @throws DataAccessException
 */
@SuppressWarnings("unchecked")
@Override
public List<Manager> getAll() throws DataAccessException {
return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_MANAGER);
}

/**
 * This method to update a manager.
 * @param element of type a manager
 * @throws DataAccessException
 */
@Override
public int save(final Manager element) throws DataAccessException {
throw new UnsupportedOperationException();
}
/**
 * This method to add a manager.
 * @param element of type a manager
 * @throws DataAccessException
 */
@Override
public int add(final Manager element) throws DataAccessException {
throw new UnsupportedOperationException();
}

/**
 * This method to delete a manager.
 * @param element of type a manager
 * @throws DataAccessException
 */
@Override
public int delete(Manager element) throws DataAccessException {
throw new UnsupportedOperationException();
}


/**
 * This method to check a manager.
 * @param name of a manager
 * @throws DataAccessException
 */
@Override
public Manager check(final String name) throws DataAccessException {
throw new UnsupportedOperationException();
}


/**
 * Select all Collaborators by managerId.
 * @param managerId : the id of the manager
 * @return all collaborators who depend on the manager managerId
 * @author v.guillemain
 */
@SuppressWarnings("unchecked")
public List<Colleague> getAllCollaborators(Integer managerId) throws DataAccessException {
return (List<Colleague>) this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLABORATOR_BY_MANAGER_ID, managerId);
}

/**
 * This method to add a manager.
 * @param name of manager
 * @throws DataAccessException
 */
@Override
public Manager getByName(final String name) throws DataAccessException {
throw new UnsupportedOperationException();
}
}