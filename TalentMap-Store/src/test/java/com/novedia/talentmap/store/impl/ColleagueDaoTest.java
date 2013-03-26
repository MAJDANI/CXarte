package com.novedia.talentmap.store.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.BusinessEngineer;
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
@Ignore
public class ColleagueDaoTest {

	@SpringBeanByName
	private SqlMapClient sqlMapClient;

	@SpringBeanByName
	ColleagueDao colleagueDao;

	@Before
	public void setUp() throws Exception {
		colleagueDao.setSqlMapClient(sqlMapClient);
	}

	@Test
	public void testGetByMail() {
		//Given
		//When
		//Then
	}
	
	
	@Test
	public void testAddColleagueFromRegistration() {
		//Given
		//When
		//Then
	}	
	
	
	@Test
	public void testGetAllConsultantManager() {
		//Given
		//When
		//Then
	}	
	
	
	/**
	 * Test get colleague by id
	 */
	@Test
	public void testGetById() {
		Colleague expectedColleague = colleagueDao.get(1);
		Assert.assertNotNull(expectedColleague);
		ReflectionAssert.assertPropertyLenientEquals("id", 1, expectedColleague);
	}

	/**
	 * Test get all colleagues
	 */
	@Test
	public void testGetAll() {
		
		// Given
		BusinessEngineer bizEngineer = BusinessEngineer.builder().id(1).firstName("Julie").lastName("VIGNERON").businessUnit("INDUSTRIE").build();
		
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
		ReflectionAssert.assertPropertyLenientEquals("businessEngineer", Arrays.asList(bizEngineer.getId(), bizEngineer.getId()),listCollabs);
	}

	/**
	 * Test save
	 */
	@Test
	@DataSet("ColleagueDaoTest.testSave-result.xml")
	public void testSave() {
		BusinessEngineer businessEngineer = BusinessEngineer.builder().id(3).firstName("Julie").lastName("Vigneron").build(); 
		Colleague colleague = Colleague.builder().id(1).profileId(1)
				.phone("6020202020").experience(7)
				.firstName("modifiedColleague")
				.lastName("modifiedLastName")
				.businessEngineer(businessEngineer)
				.email("modifiedemail@novediagroup.com")
				.employmentDate(new Date())
				.build();
		
		int updateCounter = colleagueDao.save(colleague);
		Assert.assertEquals(1, updateCounter);
	}
	

}
