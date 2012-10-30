package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import java.sql.SQLClientInfoException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;

public class CollaboratorDaoTest{
	
	private CollaboratorDao collabDao;
	private SqlMapClient sqlMapClient;
	
	@Before
	public void setUp() throws Exception {
		this.collabDao = new CollaboratorDao(sqlMapClient);
	}

	@Test
	public void testGetById() throws Exception {
		
		assertNotNull(this.collabDao.getById(2));
		
	}

//	@Test
//	public void testSelectAll() {
//		fail("Not yet implemented");
//	}
//
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
