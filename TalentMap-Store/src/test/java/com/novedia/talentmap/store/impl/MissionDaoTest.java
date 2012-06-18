package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MissionDaoTest {
	
	private MissionDao missionDao;
	
	@Before
	public void setUp() throws Exception {
		this.missionDao = new MissionDao();
	}

	@Test
	public void testGetByCollabId() {
		
		assertNotNull(this.missionDao.getByCollabId(2));
	}

//	@Test
//	public void testUpdate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetSqlMapClient() {
//		fail("Not yet implemented");
//	}

}
