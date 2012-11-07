package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class ProfileDao  extends SqlMapClientDaoSupport  implements IDao<Profile> {


	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public ProfileDao (final SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Profile get(Integer id) throws DataAccessException {
		return (Profile) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_PROFILE, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Profile> getAll() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_PROFILE);
	}

	@Override
	public int save(Profile profile) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int add(Profile profile) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int delete(Profile profile) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Profile check(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Get One Profile By Type
	 * @class IProfileDao.java
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Profile getByType(String type) throws DataAccessException{
		return (Profile) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_PROFILE_BY_TYPE, type);
	}


	@Override
	public Profile getByName(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

}