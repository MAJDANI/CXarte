package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Mission;

/**
 * Test MissionDao
 * 
 * @author moumbe
 */
@Ignore
@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet("MissionDaoTest.xml")
public class MissionDaoTest {

	@SpringBeanByName
	private SqlMapClient sqlMapClient;
	
	@SpringBeanByName
	private MissionDao missionDao;

	
	/**
	 * Test initialization
	 */
	public void setUp() {
		missionDao.setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Test get a mission by id.
	 */
	@Test
	public void testGetById () throws Exception {
		Mission mission = missionDao.get(1);
		ReflectionAssert.assertPropertyReflectionEquals("id", 1, mission);
	}
	
	/**
	 * Test get all missions by colleague id.
	 */
	@Test
	@DataSet("MissionDaoTest.xml")
	public void testGetAllByColleague() throws Exception {
		Integer colleagueId = 1;
		List<Mission> missionList = missionDao.getAllByColleagueId(colleagueId);
		ReflectionAssert.assertPropertyLenientEquals("id", Arrays.asList(1, 2), missionList);
	}
	
	/**
	 * Test save modification on a mission tuple.
	 */
	@Test
	@DataSet("MissionDaoTest-Save-result.xml")
	public void testSave() throws Exception {
		Mission mission = Mission.builder().build();
		missionDao.save(mission);
	}
}
