package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConceptDaoTest {
	
	private ConceptDao conceptDao;
	
	@Before
	public void setUp() throws Exception {
		this.conceptDao = new ConceptDao();
	}

//	@Test
//	public void testSetSqlMapClient() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetById() {
		
		assertNotNull(this.conceptDao.getById(2));
	}

//	@Test
//	public void testSelectAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSelectAllByCategoryId() {
//		fail("Not yet implemented");
//	}

}
