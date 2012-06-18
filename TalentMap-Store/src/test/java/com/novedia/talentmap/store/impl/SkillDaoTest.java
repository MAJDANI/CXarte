package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SkillDaoTest {

	private SkillDao skillDao;
	
	@Before
	public void setUp() throws Exception {
		this.skillDao = new SkillDao();
	}

//	@Test
//	public void testSetSqlMapClient() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetAllCollaboratorSkill() {
	 
		assertNotNull(this.skillDao.getAllCollaboratorSkill(2));
	}

//	@Test
//	public void testGetOneCollaboratorSkill() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddOneSkill() {
//		fail("Not yet implemented");
//	}

}
