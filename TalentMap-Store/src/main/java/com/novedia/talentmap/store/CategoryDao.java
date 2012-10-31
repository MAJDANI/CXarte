package com.novedia.talentmap.store;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.store.generiques.IGenericDao;

/**
 * Interface Category DAO
 * @author j.collet
 * @project TalentMap-Store
 * @created 21 mai 2012
 */
public class CategoryDao implements IGenericDao {

	@Override
	public Object getById(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Object category) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object checkCategory(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Object category) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int category_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}