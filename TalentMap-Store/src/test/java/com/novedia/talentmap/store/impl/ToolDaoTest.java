//package com.novedia.talentmap.store.impl;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.unitils.UnitilsJUnit4;
//import org.unitils.dbunit.annotation.DataSet;
//import org.unitils.dbunit.datasetloadstrategy.impl.CleanInsertLoadStrategy;
//import org.unitils.reflectionassert.ReflectionAssert;
//import org.unitils.spring.annotation.SpringApplicationContext;
//import org.unitils.spring.annotation.SpringBean;
//
//import com.ibatis.sqlmap.client.SqlMapClient;
//import com.novedia.talentmap.model.entity.Tool;
//
///**
// * Test ToolDao
// * @author moumbe
// *
// */
//@DataSet (loadStrategy = CleanInsertLoadStrategy.class)
//@SpringApplicationContext("test-store-spring-context.xml")
//public class ToolDaoTest extends UnitilsJUnit4 {
//
//	@SpringBean("sqlMapClient")
//	private SqlMapClient sqlMapClient;
//	
//	@SpringBean("toolDao")
//	private ToolDao toolDao;
//	
//	@Before
//	public void setUp() {
//		toolDao.setSqlMapClient(sqlMapClient);
//	}
//	
//	@Test
//	public void testGet() {
//		Tool tool = toolDao.get(1);
//		ReflectionAssert.assertPropertiesNotNull("object is not null", tool);
//	}
//}
