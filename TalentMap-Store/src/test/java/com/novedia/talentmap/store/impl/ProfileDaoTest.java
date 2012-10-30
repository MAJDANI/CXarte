package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ProfileDaoTest {
	
	private ProfileDao profileDao;
	private SqlMapClient sqlMapClient;
	
	@Before
	public void setUp() throws Exception {
		this.profileDao = new ProfileDao(sqlMapClient);
	}

	@Test
	public void testSelectAll() throws SQLException {

		assertNotNull(this.profileDao.selectAll());
	}

	@Test
	public void testGetById() throws SQLException {
		
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
