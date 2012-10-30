package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.ibatis.sqlmap.client.SqlMapClient;

public class VSkillDaoTest {
	
	private VSkillDao vSkillDao;
	private SqlMapClient sqlMapClient;
	
	@Before
	public void setUp() throws Exception {
		this.vSkillDao = new VSkillDao(sqlMapClient);
	}

	@Test
	public void testGetToolByConcept() throws SQLException {
		
		assertNotNull(this.vSkillDao.getToolByConcept("JAVA", "IOC"));
		assertFalse(this.vSkillDao.getToolByConcept("JAVA", "IOC").size() == 0);
	}

	@Test
	public void testGetSkillByTool() throws Exception {
		
		assertNotNull(this.vSkillDao.getSkillByTool("Spring"));
	}

	@Test
	public void testGetConceptByCategory() throws SQLException {
		
		assertNotNull(this.vSkillDao.getConceptByCategory("JAVA"));
		assertFalse(this.vSkillDao.getConceptByCategory("JAVA").size() == 0);
	}

}
