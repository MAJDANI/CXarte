package com.novedia.talentmap.store.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Colleague;

/**
 * Test class for Data Access to table COLLEAGUE.
 * {@link ColleagueDao}
 * @author j.marie-sainte
 * 
 */
@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet("ColleagueDaoTest.xml")
public class ColleagueDaoTest {

	@SpringBeanByName
	private SqlMapClient sqlMapClient;

	@SpringBeanByName
	ColleagueDao colleagueDao;

	@Before
	public void setUp() throws Exception {
		colleagueDao.setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Test get colleague by id
	 */
	@Test
	public void testGetById() {
		Integer id = 1;
		Colleague expectedColleague = colleagueDao.get(id);
		Assert.assertNotNull(expectedColleague);
		ReflectionAssert.assertPropertyLenientEquals("id", id, expectedColleague);
	}

	/**
	 * Test get all colleagues
	 */
	@Test
	public void testGetAll() {
		
		// When
		List<Colleague> listCollabs = colleagueDao.getAll();
		
		// Then
		ReflectionAssert.assertPropertyLenientEquals("id", Arrays.asList(1, 2),	listCollabs);
		ReflectionAssert.assertPropertyLenientEquals("lastName", Arrays.asList("collab1", "collab2"),listCollabs);
		ReflectionAssert.assertPropertyLenientEquals("firstName", Arrays.asList("collab1", "collab2"),listCollabs);
		ReflectionAssert.assertPropertyLenientEquals("email", Arrays.asList("collab1@gmail.com", "collab2@yahoo.fr"),listCollabs);
		ReflectionAssert.assertPropertyLenientEquals("employmentDate", Arrays.asList(new DateTime("2011-12-12").toDate(), new DateTime("2011-12-12").toDate()),listCollabs);
		ReflectionAssert.assertPropertyLenientEquals("phone", Arrays.asList("6010101010", "6010101010"),listCollabs);
		ReflectionAssert.assertPropertyLenientEquals("experience", Arrays.asList(5, 5),listCollabs);
		ReflectionAssert.assertPropertyLenientEquals("businessEngineer", Arrays.asList("Colombe Tramond", "Colombe Tramond"),listCollabs);
	}

	/**
	 * Test save
	 */
	@Test
	@DataSet("ColleagueDaoTest.testSave-result.xml")
	public void testSave() {
		Colleague colleague = Colleague.builder().id(1).profileId(1)
				.phone("6020202020").experience(7)
				.firstName("modifiedColleague")
				.lastName("modifiedLastName")
				.businessEngineer("Julie Vigneron")
				.email("modifiedemail@novediagroup.com")
				.employmentDate(new Date())
				.build();
		
		int updateCounter = colleagueDao.save(colleague);
		Assert.assertEquals(1, updateCounter);
	}
	
	/**
	 * Test Add
	 * @throws Exception
	 */
	@Test
	@DataSet("ColleagueDaoTest.testAdd.xml")
	public void testAdd() throws Exception {

		// Given
		Colleague colleague = Colleague.builder().managerId(1).profileId(1)
				.phone("6020202020").experience(7).firstName("Johan")
				.lastName("RONDOUDOUX").businessEngineer("Renaud Migne")
				.email(".rondoudoux@novediagroup.com").employmentDate(new Date())
				.build();
		
		// When
		int addIndex = colleagueDao.add(colleague);
		
		// Then
		Assert.assertTrue(addIndex > 0);
	}

}
