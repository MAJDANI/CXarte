package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ManagerDaoTest {
	
	private ManagerDao managerDao;
	private SqlMapClient sqlMapClient;
	
	
	@Before
	public void setUp() throws Exception {
		this.managerDao = new ManagerDao(sqlMapClient);
	}

//	@Test
//	public void testSetSqlMapClient() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetById() throws SQLException {
		
		assertNotNull(this.managerDao.getById(2));
	}

}
