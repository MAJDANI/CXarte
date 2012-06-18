package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ManagerDaoTest {
	
	private ManagerDao managerDao;
	
	@Before
	public void setUp() throws Exception {
		this.managerDao = new ManagerDao();
	}

//	@Test
//	public void testSetSqlMapClient() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetById() {
		
		assertNotNull(this.managerDao.getById(2));
	}

}
