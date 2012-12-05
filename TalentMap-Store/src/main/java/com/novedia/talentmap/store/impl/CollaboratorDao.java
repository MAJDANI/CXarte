package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * The collaborator DAO.
 * @author j.collet
 */
public class CollaboratorDao extends SqlMapClientDaoSupport implements IDao<Collaborator>{
/**
 * Class builder based on sqlMapClient.
 * @param sqlMapClient a sqlmapclient
 */
public CollaboratorDao(final SqlMapClient sqlMapClient) {
setSqlMapClient(sqlMapClient);
}

/**
 * This method allow to get a collaborator.
 * @param id a id
 * @throws DataAccessException
 */
@Override
public Collaborator get(final Integer id) throws DataAccessException {
return (Collaborator) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_COLLABORATOR, id);
}

/**
 * This method allow to get a list of collaborator.
 *  @return a list of collaborator
 *  @throws DataAccessException
 */
@Override
public List<Collaborator> getAll() throws DataAccessException {
return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLABORATOR);
}

/**
 * This method allow to update a collaborator.
 * @return a integer
 * @param collaborator
 * @throws DataAccessException
 */
@Override
public int save(Collaborator collaborator) throws DataAccessException {
return this.getSqlMapClientTemplate().update(DBRequestsConstants.SAVE_COLLABORATOR, collaborator.getId());
}

/**
 * This method allow to add a collaborator.
 * @param collaborator a collaborator
 * @throws DataAccessException
 */
@Override
public int add(final Collaborator collaborator) throws DataAccessException {
throw new UnsupportedOperationException();
}

/**
 * This method allow to delete a collaborator.
 * @param collaborator a collaborator
 * @throws DataAccessException
 */
@Override
public int delete(final Collaborator collaborator) throws DataAccessException {
throw new UnsupportedOperationException();
}
/**
 * This method allow to get a collaborator by name.
 * @param name , collaborator name
 * @throws DataAccessException
 */
@Override
public Collaborator getByName(final String name) throws DataAccessException {
throw new UnsupportedOperationException();
}

/**
 * This method allow to check a collaborator by name.
 * @param name a collaborator name
 * @throws DataAccessException
 */
@Override
public Collaborator check(final String name) throws DataAccessException {
throw new UnsupportedOperationException();
}

}