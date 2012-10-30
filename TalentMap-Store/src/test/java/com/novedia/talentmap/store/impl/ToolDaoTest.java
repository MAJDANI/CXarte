package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;

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
	public void testGetById() throws SQLException {
		assertNotNull(this.toolDao.getById(2));
	}

	@Test
	public void testGetByName() throws SQLException {
		
		assertNotNull(this.toolDao.getByName("Spring"));
	}
//
//	@Test
//	public void testSelectAll() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testSelectAllByConceptId() throws SQLException {
		
		assertNotNull(this.toolDao.selectAllByConceptId(1));
		assertEquals(false, this.toolDao.selectAllByConceptId(1).size() == 0);
	}

}
