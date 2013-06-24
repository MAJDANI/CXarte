package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * The profile DAO handles query for table Profile.
 * 
 * @author j.collet.
 * @author j.marie-sainte
 */
public class ProfileDao extends SqlMapClientDaoSupport implements IDao<Profile> {

	/**
	 * Set the sqlMapClient value.
	 * 
	 * @param sqlMapClient
	 *            the sqlMapClient to set
	 */
	public ProfileDao(final SqlMapClient sqlMapClient) {
		this.setSqlMapClient(sqlMapClient);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Profile get(final Integer id) throws DataAccessException {
		return (Profile) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_PROFILE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Profile> getAll() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_PROFILE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int save(final Profile profile) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int add(final Profile profile) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int delete(final Profile profile) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Profile check(final String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Profile getByName(final String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

}