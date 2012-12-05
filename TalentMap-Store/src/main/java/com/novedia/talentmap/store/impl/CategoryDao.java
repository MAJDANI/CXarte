package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * Category DAO.
 * @author j.collet
 */
public class CategoryDao extends SqlMapClientDaoSupport implements
IDao<Category> {
/**
 * Class builder based on sqlMapClient.
 * @param sqlMapClient
 * a sqlmapclient
 */
public CategoryDao(final SqlMapClient sqlMapClient) {
setSqlMapClient(sqlMapClient);
}

/**
 * This method allow to get a category.
 * @param id a id
 * @throws DataAccessException
 */

@Override
public Category get(final Integer id) throws DataAccessException {
return (Category) this.getSqlMapClientTemplate().queryForObject(
DBRequestsConstants.GET_CATEGORY, id);
}

/**
 * This method allow to get a list of category.
 */
@SuppressWarnings("unchecked")
@Override
public List<Category> getAll() {
return (List<Category>) getSqlMapClientTemplate().queryForList(
DBRequestsConstants.GET_ALL_CATEGORY);
}
/**
 * This method allow to add a category.
 * @param category a category
 * @throws DataAccessException
 */
@Override
public int add(final Category category) throws DataAccessException {
return (Integer) this.getSqlMapClientTemplate().insert(
DBRequestsConstants.ADD_CATEGORY, category);
}

/**
 *This method allow to update a category.
 *@param category a category
 *@throws DataAccessException
 */
@Override
public int save(final Category category) throws DataAccessException {
return (Integer) this.getSqlMapClientTemplate().update(
DBRequestsConstants.SAVE_CATEGORY, category);
}

/**
 * This method allow to delete a category.
 * @param category a category
 * @throws DataAccessException
 */
@Override
public int delete(final Category category) throws DataAccessException {
return (Integer) this.getSqlMapClientTemplate().delete(
DBRequestsConstants.DELETE_CATEGORY, category);
}

/**
 * This method allow to check a category.
 * @return {@link Category}
 * @param name a category name
 * @throws DataAccessException
 */
@Override
public Category check(final String name) throws DataAccessException {
return (Category) this.getSqlMapClientTemplate().queryForObject(
DBRequestsConstants.CHECK_CATEGORY, name);
}

/**
 * @return {@link Category}
 * @param name a name
 * @throws DataAccessException
 */
@Override
public Category getByName(final String name) throws DataAccessException {
return (Category) this.getSqlMapClientTemplate().queryForObject(
DBRequestsConstants.GET_CATEGORY_BY_NAME, name);
}

}