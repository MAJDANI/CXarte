/**
 * 
 */
package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * @author v.dibi
 * @author v.guillemain
 * 
 */
public class AuthenticationDao extends SqlMapClientDaoSupport implements IDao<Authentication> {

	/**
	 * Class builder based on sqlMapClient
	 * 
	 * @param sqlMapClient
	 */
	public AuthenticationDao(final SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	/**
	 * Update password
	 */
	@Override
	public int save(Authentication element) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().update(
				DBRequestsConstants.SAVE_AUTHENTIFICATION, element);
	}

	/**
	 * Add login/password
	 */
	@Override
	public int add(Authentication element) throws DataAccessException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Add login/password
	 */
	public int addUser(CredentialToken element) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().insert(
				DBRequestsConstants.ADD_USER_AUTHENTIFICATION, element);
	}
	

	/**
	 * Delete login/password
	 */
	@Override
	public int delete(Authentication authentification) throws DataAccessException {
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
	
	public Integer addUserFromRegistration(Registration registration) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().insert(
				DBRequestsConstants.ADD_USER_FROM_REGISTRATION, registration);
	}

	/**
	 * Counts the number of login "login" existing in table Authentication.
	 * It's used to assure a user won't choose a login already existing.
	 * @param login : tho login to check in table
	 * @return the number of login found in table
	 */
	public Integer countLogin(String login) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.AUTHENTIFICATION_COUNT_LOGIN, login);
	}
	
	@Override
	public Authentication getByName(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Authentication get(Integer id) throws DataAccessException {
		return (Authentication) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_BY_ID_AUTHENTIFICATION, id);
	}

	@Override
	public List<Authentication> getAll() throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Authentication check(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}
}