package com.novedia.talentmap.store.impl;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.store.ICategoryDao;

public class CategoryDao implements ICategoryDao {
	
	private SqlMapClient sqlMapClient;

	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	@Override
	public Category getById(int id) throws Exception {
		
		return (Category)sqlMapClient.queryForObject("category.getCategory", id);
	}

	@Override
	public List<Category> selectAll() throws Exception {
		
		return sqlMapClient.queryForList("category.getAllCategory");
	}
	

}
