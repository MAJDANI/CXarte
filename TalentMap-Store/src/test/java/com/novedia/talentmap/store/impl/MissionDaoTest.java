package com.novedia.talentmap.store.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Mission;

/**
 * Test MissionDao
 * @author moumbe
 *
 */
@Ignore
@SpringApplicationContext("test-store-spring-context.xml")
public class MissionDaoTest extends UnitilsJUnit4 {

	@SpringBeanByName
	private SqlMapClient sqlMapClient;
	
	@SpringBeanByName
	private MissionDao missionDao;
	
	public void setUp() {
		missionDao.setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Test get Mission by id
	 */
	@Test
	@DataSet("MissionDaoTest.xml")
	public void testGet () {
		Mission mission = missionDao.get(1);
		ReflectionAssert.assertPropertyReflectionEquals("id", 1, mission);
		
	}
}
