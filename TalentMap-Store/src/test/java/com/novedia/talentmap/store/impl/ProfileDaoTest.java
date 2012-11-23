package com.novedia.talentmap.store.impl;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.datasetloadstrategy.impl.CleanInsertLoadStrategy;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Profile;

/**
 * Test ProfileDao
 * @author moumbe
 *
 */
@DataSet (loadStrategy = CleanInsertLoadStrategy.class)
@SpringApplicationContext("test-store-spring-context.xml")
public class ProfileDaoTest extends UnitilsJUnit4 {

	@SpringBeanByName
	private SqlMapClient sqlMapClient;
	
	@SpringBeanByName
	private ProfileDao profileDao;
	
	public void setUp() {
		profileDao.setSqlMapClient(sqlMapClient);
	}
	
	@Test
	public void testGet () {
		Profile profile = profileDao.get(1);
		ReflectionAssert.assertPropertyReflectionEquals("id", 1, profile);
		
	}
}
