package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.store.generiques.IGenericDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * Category DAO
 * @author j.collet
 * @param <E>
 * @param <E>
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 * 
 */
public class CategoryDao extends SqlMapClientDaoSupport implements IGenericDao<Category> {
	
	private SqlMapClient sqlMapClient;
	private SqlMapClientTemplate sqlMapClientTemplate;
	private int category_id;
	private int id;

	/**
	 * Class builder based on sqlMapClient
	 * @param sqlMapClient
	 */
	@Autowired
	public CategoryDao(final SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}
	/**
	 * Builder of a dummy category if the database is down
	 * @class CategoryDao.java
	 * @param id
	 * @param name
	 * @return
	 */
	private Category buildDummyCategory(int id, String name){	
		Category c = new Category();
		c.setId(id);
		c.setName(name);
		
		return c;
	}
	
	/**
	 * Get One Category By Id
	 */
	@Override
	public Category getById(int id) throws DataAccessException {
		
		try {
			
			return (Category) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_CATEGORY_REQUEST);
			
			
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummyCategory(id, "JAVA");
			
		}
	}
	
	/**
	 * Builder of a Category List if the database is down.
	 * @class CategoryDao.java
	 * @return
	 */
	private List<Category> buildListDummyCategory(){
		
		List<Category> lCategory = new ArrayList<Category>();
		
		Category c1 = buildDummyCategory(1, "JAVA");
		Category c2 = buildDummyCategory(2, ".NET");
		
		lCategory.add(c1);
		lCategory.add(c2);
		
		return lCategory;
	}
	
	/**
	 * Select all Categories
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> selectAll() {
		
		try {
			return (List<Category>) getSqlMapClientTemplate().queryForObject("category.getAllCategory", DBRequestsConstants.GET_ALL_CATEGORY_REQUEST);
			
		} catch (NullPointerException npe){
			System.err.println("Database Down !");
			logger.info("npe"+npe.getMessage());
			return buildListDummyCategory();
		}
	}

	@Override
	public int save(Category category) throws DataAccessException {

		
		try {
			this.sqlMapClient.startTransaction();
			category_id  = (Integer) getSqlMapClientTemplate().insert(DBRequestsConstants.INSERT_CATEGORY_REQUEST, category);
			this.sqlMapClient.commitTransaction();
			
			this.sqlMapClient.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return category_id;
	}

	@Override
	public Category checkCategory(String name) throws DataAccessException {
		return (Category) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.CATEGORY_REQUEST_CHECK,name);
	}

	@Override
	public int update(Category category) throws DataAccessException {
		
		try {
			this.sqlMapClient.startTransaction();
			category_id =  (Integer)this.getSqlMapClientTemplate().update(DBRequestsConstants.CATEGORY_REQUEST_UPDATE,category);
			this.sqlMapClient.commitTransaction();		
			this.sqlMapClient.endTransaction();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return category_id;
	}

	@Override
	public int delete(int category_id) throws DataAccessException {

		try {
			this.sqlMapClient.startTransaction();
			id = (Integer) this.getSqlMapClientTemplate().delete(DBRequestsConstants.CATEGORY_REQUEST_DELETE,category_id);			
			this.sqlMapClient.commitTransaction();			
			this.sqlMapClient.endTransaction();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return id;
	}
}