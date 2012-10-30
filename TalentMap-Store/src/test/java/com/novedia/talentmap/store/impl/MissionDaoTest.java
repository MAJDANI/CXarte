package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MissionDaoTest {
	
	private MissionDao missionDao;
	private SqlMapClient sqlMapClient;
	
	
	@Before
	public void setUp() throws Exception {
		this.missionDao = new MissionDao(sqlMapClient);
	}

	@Test
	public void testGetByCollabId() throws SQLException {
		
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
