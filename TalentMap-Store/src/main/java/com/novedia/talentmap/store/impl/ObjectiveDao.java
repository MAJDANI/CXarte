package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.novedia.talentmap.model.entity.Objective;
import com.novedia.talentmap.store.IDao;

/**
 * The {@link ObjectiveDao} handles query for table Objective 
 * @author moumbe
 *
 */
public class ObjectiveDao extends SqlMapClientDaoSupport implements IDao<Objective>{

	@Override
	public Objective get(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Objective> getAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Objective element) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(Objective element) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Objective element) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Objective getByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Objective check(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
