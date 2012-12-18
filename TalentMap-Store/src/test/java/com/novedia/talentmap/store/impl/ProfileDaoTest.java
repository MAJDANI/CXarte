package com.novedia.talentmap.store.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
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
@Ignore
@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ProfileDaoTest {

	@SpringBeanByName
	private SqlMapClient sqlMapClient;
	
	@SpringBeanByName
	private ProfileDao profileDao;
	
	public void setUp() {
		profileDao.setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Test get Profile by id
	 */
	@Test
	@DataSet("ProfileDaoTest.xml")
	public void testGet () {
		Profile profile = profileDao.get(1);
		ReflectionAssert.assertPropertyReflectionEquals("id", 1, profile);
		ReflectionAssert.assertPropertyReflectionEquals("type", "1", profile);	
	}
}
