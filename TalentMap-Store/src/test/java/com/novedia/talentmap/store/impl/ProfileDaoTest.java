package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProfileDaoTest {
	
	private ProfileDao profileDao;
	
	@Before
	public void setUp() throws Exception {
		this.profileDao = new ProfileDao();
	}

	@Test
	public void testSelectAll() {

		assertNotNull(this.profileDao.selectAll());
	}

	@Test
	public void testGetById() {
		
		assertNotNull(this.profileDao.getById(1));
	}

//	@Test
//	public void testGetByType() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetSqlMapClient() {
//		fail("Not yet implemented");
//	}

}
