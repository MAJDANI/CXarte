package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.novedia.talentmap.model.entity.Category;

public class CategoryDaoTest {
	
	private CategoryDao categoryDao;
	
	@Before
	public void setUp() throws Exception {
		this.categoryDao = new CategoryDao();
	}

//	@Test
//	public void testSetSqlMapClient() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetById() {
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
