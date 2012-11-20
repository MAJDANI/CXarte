//package com.novedia.talentmap.store.impl;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.unitils.UnitilsJUnit4;
//import org.unitils.dbunit.annotation.DataSet;
//import org.unitils.dbunit.datasetloadstrategy.impl.CleanInsertLoadStrategy;
//import org.unitils.spring.annotation.SpringApplicationContext;
//import org.unitils.spring.annotation.SpringBean;
//
//import com.ibatis.sqlmap.client.SqlMapClient;
//import com.novedia.talentmap.model.entity.Skill;
//
///**
// * Test SkillDao
// * @author moumbe
// *
// */
//@DataSet (loadStrategy = CleanInsertLoadStrategy.class)
//@SpringApplicationContext("test-store-spring-context.xml")
//public class SkillDaoTest extends UnitilsJUnit4 {
//
//	@SpringBean("sqlMapClient")
//	private SqlMapClient sqlMapClient;
//	
//	@SpringBean("skillDao")
//	private SkillDao skillDao;
//	
//	@Before
//	public void setUp() {
//		skillDao.setSqlMapClient(sqlMapClient);
//	}
//	
//	@Test
//	public void testGet() {
//		try {
//			Skill skill = skillDao.get(1);
//		}catch (Exception e) {
//			//HAndle Exception
//		}
//		
//	}
//}
