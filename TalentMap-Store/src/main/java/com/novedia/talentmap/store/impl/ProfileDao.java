package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * @author j.collet.
 */
public class ProfileDao  extends SqlMapClientDaoSupport  implements IDao<Profile> {

/**
 * Set the sqlMapClient value.
 * @param sqlMapClient the sqlMapClient to set
 */
public ProfileDao(final SqlMapClient sqlMapClient) {
setSqlMapClient(sqlMapClient);
}

/**
 * This method allow to get a profile.
 * @param id a id
 * @throws DataAccessException
 */
@Override
public Profile get(final Integer id) throws DataAccessException {
return (Profile) this.getSqlMapClientTemplate()
.queryForObject(DBRequestsConstants.GET_PROFILE, id);
}

/**
 * This method allow to get all profile.
 * @return a list of profile
 * @throws DataAccessException
 */
@SuppressWarnings("unchecked")
@Override
public List<Profile> getAll() throws DataAccessException {
return this.getSqlMapClientTemplate()
.queryForList(DBRequestsConstants.GET_ALL_PROFILE);
}

/**
 * This method allow to save a profile.
 * @param profile a profile
 * @throws DataAccessException
 */
@Override
public int save(final Profile profile) throws DataAccessException {
throw new UnsupportedOperationException();
}

/**
 * This method allow to add a profile.
 * @param profile a profile
 * @throws DataAccessException
 */
@Override
public int add(final Profile profile) throws DataAccessException {
throw new UnsupportedOperationException();
}

/**
 * This method allow to delete a profile.
 * @param profile a profile
 * @throws DataAccessException
 */
@Override
public int delete(final Profile profile) throws DataAccessException {
throw new UnsupportedOperationException();
}

/**
 * This method allow to check a profile.
 * @param name a name
 * @throws DataAccessException
 */
@Override
public Profile check(final String name) throws DataAccessException {
throw new UnsupportedOperationException();
}

/**
 * Get One Profile By Type.
 * @class IProfileDao.java
 * @param type
 * @return profile
 * @throws DataAccessException
 */
Profile getByType(final String type) throws DataAccessException{
return (Profile) this.getSqlMapClientTemplate()
.queryForObject(DBRequestsConstants.GET_PROFILE_BY_TYPE, type);
}

/**
 * This method allow to add a profile.
 * @param name a name
 * @throws DataAccessException
 */
@Override
public Profile getByName(final String name) throws DataAccessException {
throw new UnsupportedOperationException();
}

}