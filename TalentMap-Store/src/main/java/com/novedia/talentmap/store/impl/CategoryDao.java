package com.novedia.talentmap.store.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;


/**
 * The category DAO
 * 
 * @author j.Collet
 *
 */
public class CategoryDao extends SqlMapClientDaoSupport implements IDao<Category> {
	
	/**
	 * The logger
	 */
	private static Log logger = LogFactory.getLog(CategoryDao.class);
	
	/**
	 * Class builder based on sqlMapClient
	 * @param sqlMapClient
	 */
	public CategoryDao(final SqlMapClient sqlMapClient){
		if(logger.isDebugEnabled()) {
			logger.debug("Create CategoryDao");
		}
		setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Category get(Integer id) throws DataAccessException {
		return (Category) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_CATEGORY, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAll() throws DataAccessException {
		return (List<Category>) getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_CATEGORY);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int add(Category category) throws DataAccessException {
		return (Integer)this.getSqlMapClientTemplate().insert(DBRequestsConstants.ADD_CATEGORY, category);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int save(Category category) throws DataAccessException {
		return (Integer)this.getSqlMapClientTemplate().update(DBRequestsConstants.SAVE_CATEGORY, category);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int delete(Category category) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().delete(DBRequestsConstants.DELETE_CATEGORY, category);			
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Category check(String name) throws DataAccessException {
		return (Category)this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.CHECK_CATEGORY, name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Category getByName(String name) throws DataAccessException {
		return (Category) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_CATEGORY_BY_NAME, name);
	}

}