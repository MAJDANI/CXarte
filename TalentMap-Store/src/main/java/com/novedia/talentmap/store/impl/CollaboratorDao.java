package com.novedia.talentmap.store.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * The collaborator DAO.
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class CollaboratorDao extends SqlMapClientDaoSupport implements IDao<Collaborator>{
	
	/** For log management */
	private static Log logger = LogFactory.getLog(CollaboratorDao.class);
	
	/**
	 * Class builder based on sqlMapClient
	 * @param sqlMapClient
	 */
	public CollaboratorDao(final SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Collaborator get(Integer id) throws DataAccessException {
		return (Collaborator) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_COLLABORATOR, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Collaborator> getAll() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLABORATOR);
	}

	@Override
	public int save(Collaborator collaborator) throws DataAccessException {
		return this.getSqlMapClientTemplate().update(DBRequestsConstants.SAVE_COLLABORATOR, collaborator);
	}

	@Override
	public int add(Collaborator collaborator) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Add collaborator");
		}
		return (Integer) this.getSqlMapClientTemplate().insert(DBRequestsConstants.ADD_COLLABORATOR, collaborator);
	}

	@Override
	public int delete(Collaborator collaborator) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collaborator getByName(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Collaborator check(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}


}