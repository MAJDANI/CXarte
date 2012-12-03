package com.novedia.talentmap.store.impl;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;

/**
 * Test Collaborator
 * @author moumbe
 *
 */
//@DataSet (loadStrategy = CleanInsertLoadStrategy.class)
@SpringApplicationContext("test-store-spring-context.xml")
public class CollaboratorDaoTest extends UnitilsJUnit4 {

	@SpringBean("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@SpringBean("collaboratorDao")
	private CollaboratorDao collaboratorDao;
	
	@Before
	public void setUp() {
		//Clear all Data before tests
		collaboratorDao.setSqlMapClient(sqlMapClient);
	}
	
	@Test
	@DataSet("CollaboratorDaoTest.xml")
	public void testGet() {
		Collaborator collaborator = collaboratorDao.get(1);
		ReflectionAssert.assertPropertyLenientEquals("id", 1, collaborator);
	}
}
