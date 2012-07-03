package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.store.ICategoryDao;

/**
 * Category DAO
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class CategoryDao implements ICategoryDao {
	
	private SqlMapClient sqlMapClient;

	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
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
	public Category getById(int id) {
		
		try {
			
			return (Category)sqlMapClient.queryForObject("category.getCategory", id);
//			return buildDummyCategory(id, "JAVA");
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
			System.err.println("Database Down !");
			
			return buildDummyCategory(id, "JAVA");
//			
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
	@Override
	public List<Category> selectAll() {
		
		try {
			
			return sqlMapClient.queryForList("category.getAllCategory");
//			return buildListDummyCategory();
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
			System.err.println("Database Down !");
			
			return buildListDummyCategory();
		} catch (NullPointerException npe){
			
			return buildListDummyCategory();
		}
	}

	@Override
	public int save(Category category) throws Exception {
		
		this.sqlMapClient.startTransaction();
		int category_id = (Integer) this.sqlMapClient.insert("category.insertCategory", category);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return category_id;
	}

	@Override
	public Category checkCategory(String name) throws Exception {
		
		return (Category) this.sqlMapClient.queryForObject("category.checkCategory", name);
	}

	@Override
	public int update(Category category) throws Exception {
		
		this.sqlMapClient.startTransaction();
		int category_id = (Integer) this.sqlMapClient.update("category.updateCategory", category);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return category_id;
	}

	@Override
	public int delete(int category_id) throws Exception {

		this.sqlMapClient.startTransaction();
		int id = (Integer) this.sqlMapClient.delete("category.deleteCategory", category_id);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
		
		return id;
	}
	

}
