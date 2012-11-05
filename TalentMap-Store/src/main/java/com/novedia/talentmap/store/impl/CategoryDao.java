package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;


/**
 * Category DAO
 * 
 * @author j.collet
 * @param <E>
 * @param <E>
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class CategoryDao extends SqlMapClientDaoSupport implements IDao<Category> {
	
	/**
	 * Class builder based on sqlMapClient
	 * @param sqlMapClient
	 */
	@Autowired
	public CategoryDao(final SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Category get(Category category) throws DataAccessException {
		return (Category) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_CATEGORY, category.getId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAll() {
		return (List<Category>) getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_CATEGORY);
	}
	
	@Override
	public int add(Category category) throws DataAccessException {
		return (Integer)this.getSqlMapClientTemplate().insert(DBRequestsConstants.INSERT_CATEGORY, category);
	}

	@Override
	public int save(Category category) throws DataAccessException {
		return (Integer)this.getSqlMapClientTemplate().update(DBRequestsConstants.SAVE_CATEGORY, category);
	}

	@Override
	public int delete(Category category) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().delete(DBRequestsConstants.DELETE_CATEGORY, category);			
	}


	@Override
	public Category check(String name) throws DataAccessException {
		return (Category)this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.CHECK_CATEGORY, name);
	}

}