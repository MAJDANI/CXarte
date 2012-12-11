/**
 * 
 */
package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Authentification;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * @author v.dibi
 * @author v.guillemain
 *
 */
public class AuthentificationDao extends SqlMapClientDaoSupport implements IDao<Authentification>{
	

	/**
	 * Class builder based on sqlMapClient
	 * @param sqlMapClient
	 */
	public AuthentificationDao(final SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public Authentification get(Integer id) throws DataAccessException {
		return (Authentification) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_AUTHENTIFICATION, id);
	}

	@Override
	public List<Authentification> getAll() throws DataAccessException {
		return (List<Authentification>) getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_AUTHENTIFICATION);
	}

	@Override
	public int save(Authentification element) throws DataAccessException {
		return (Integer)this.getSqlMapClientTemplate().update(DBRequestsConstants.SAVE_AUTHENTIFICATION, element);
	}

	@Override
	public int add(Authentification element) throws DataAccessException {
		return (Integer)this.getSqlMapClientTemplate().insert(DBRequestsConstants.ADD_AUTHENTIFICATION, element);
	}

	@Override
	public int delete(Authentification authentification) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().delete(DBRequestsConstants.DELETE_CATEGORY, authentification.getId());		
	}

	@Override
	public Authentification getByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Authentification check(String password) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Authentification check(String login, String password) throws DataAccessException {
		return (Authentification)this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.CHECK_AUTHENTIFICATION, login, password);
	}

	public Collaborator getCollaboratorByLogin(String login) throws DataAccessException {
		return (Collaborator)this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_COLLAB_BY_LOGIN, login);
	}

}
