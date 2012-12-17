/**
 * 
 */
package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * @author v.dibi
 * @author v.guillemain
 * 
 */
public class AuthenticationDao extends SqlMapClientDaoSupport implements
		IDao<Authentication> {

	/**
	 * Class builder based on sqlMapClient
	 * 
	 * @param sqlMapClient
	 */
	public AuthenticationDao(final SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public Authentication get(Integer id) throws DataAccessException {
		return (Authentication) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_AUTHENTIFICATION, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Authentication> getAll() throws DataAccessException {
		return (List<Authentication>) getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_AUTHENTIFICATION);
	}

	@Override
	public int save(Authentication element) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().update(
				DBRequestsConstants.SAVE_AUTHENTIFICATION, element);
	}

	@Override
	public int add(Authentication element) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().insert(
				DBRequestsConstants.ADD_AUTHENTIFICATION, element);
	}

	@Override
	public int delete(Authentication authentification)
			throws DataAccessException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Authentication getByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Authentication check(String password) throws DataAccessException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	/**
	 * This method check if credentials info exists in table
	 * @param token
	 * @return authentication
	 * @throws DataAccessException
	 */
	public Authentication check(CredentialToken token) throws DataAccessException {
		return (Authentication) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.CHECK_AUTHENTIFICATION, token);
	}

	/**
	 * This method return a collaborator by login
	 * @param login
	 * @return
	 * @throws DataAccessException
	 */
	public Collaborator getCollaboratorByLogin(String login)
			throws DataAccessException {
		return (Collaborator) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_COLLAB_BY_LOGIN, login);
	}

}
