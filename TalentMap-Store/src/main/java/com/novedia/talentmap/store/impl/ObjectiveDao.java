package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.novedia.talentmap.model.entity.Objective;
import com.novedia.talentmap.store.IDao;

/**
 * The {@link ObjectiveDao} handles query for table Objective.
 * 
 * @author moumbe
 * 
 */
public class ObjectiveDao extends SqlMapClientDaoSupport implements
		IDao<Objective> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Objective get(final Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Objective> getAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int save(final Objective element) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int add(final Objective element) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int delete(final Objective element) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Objective getByName(final String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Objective check(final String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}