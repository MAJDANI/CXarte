package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Category;

public class CategoryDaoTest {
	
	private CategoryDao categoryDao;
	private SqlMapClient sqlMapClient;
	
	@Before
	public void setUp() throws Exception {
		this.categoryDao = new CategoryDao(sqlMapClient);
	}

//	@Test
//	public void testSetSqlMapClient() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetById() throws SQLException {
		assertNotNull(this.categoryDao.getById(1));
	}

	@Test
	public void testSelectAll() {
		assertNotNull(this.categoryDao.selectAll());
	}
	
//	@Test
//	public void testSaveOne() throws Exception{
//		Category category = new Category();
//		
//		this.categoryDao.saveOne(category);
//	}

}
