package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ToolDaoTest {
	
	private ToolDao toolDao;
	
	@Before
	public void setUp() throws Exception {
		this.toolDao = new ToolDao();
	}

//	@Test
//	public void testSetSqlMapClient() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetById() {
		assertNotNull(this.toolDao.getById(2));
	}

	@Test
	public void testGetByName() {
		
		assertNotNull(this.toolDao.getByName("Spring"));
	}
//
//	@Test
//	public void testSelectAll() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testSelectAllByConceptId() {
		
		assertNotNull(this.toolDao.selectAllByConceptId(1));
		assertEquals(false, this.toolDao.selectAllByConceptId(1).size() == 0);
	}

}
