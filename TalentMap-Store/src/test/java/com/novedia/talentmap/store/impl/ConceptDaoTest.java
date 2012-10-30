package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ConceptDaoTest {
	
	private ConceptDao conceptDao;
	private SqlMapClient sqlMapClient;
	
	@Before
	public void setUp() throws Exception {
		this.conceptDao = new ConceptDao(sqlMapClient);
	}

//	@Test
//	public void testSetSqlMapClient() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetById() throws SQLException {
		
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
