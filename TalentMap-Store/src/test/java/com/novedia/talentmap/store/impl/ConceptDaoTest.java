package com.novedia.talentmap.store.impl;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.datasetloadstrategy.impl.CleanInsertLoadStrategy;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Concept;

/**
 * Test conceptDao
 * @author moumbe
 *
 */
@DataSet (loadStrategy = CleanInsertLoadStrategy.class)
@SpringApplicationContext("test-store-spring-context.xml")
public class ConceptDaoTest extends UnitilsJUnit4 {
	
	@SpringBean("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@SpringBean("conceptDao")
	private ConceptDao conceptDao;
	
	@Before
	public void setUp() {
		conceptDao.setSqlMapClient(sqlMapClient);
	}
	
	@Test
	public void testGet() {
		Concept concept = conceptDao.get(1);
		ReflectionAssert.assertPropertyLenientEquals("concept_id", 1 ,concept);
	}
}
