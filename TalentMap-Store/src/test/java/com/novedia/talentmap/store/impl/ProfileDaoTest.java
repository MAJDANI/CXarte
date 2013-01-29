package com.novedia.talentmap.store.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Profile;

/**
 * Test ProfileDao
 * 
 * @author moumbe
 *
 */
@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet("ProfileDaoTest.xml")
public class ProfileDaoTest {

	@SpringBeanByName
	private SqlMapClient sqlMapClient;
	
	@SpringBeanByName
	private ProfileDao profileDao;
	
	/**
	 * Test initilaization
	 */
	public void setUp() {
		profileDao.setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Test get profile by id
	 */
	@Test
	public void testGet() throws Exception {
		Profile profile = profileDao.get(1);
		ReflectionAssert.assertPropertyReflectionEquals("id", 1, profile);
		ReflectionAssert.assertPropertyReflectionEquals("type", "Business Analyst", profile);	
	}
	
	/**
	 * Test get all profiles
	 * @throws Exception
	 */
	@Test
	public void testGetAll() throws Exception {
		List<Profile> profiles = profileDao.getAll();
		ReflectionAssert.assertPropertyLenientEquals("id", Arrays.asList(1,2,3,4,5), profiles);
		ReflectionAssert.assertPropertyLenientEquals("type", Arrays.asList("Business Analyst","Developer","Software Architect","Tester","Project Manager"), profiles);
	}
	
	/**
	 * Test update profile
	 */
	@Test
	@ExpectedDataSet("ProfileDaoTest.testSave-result.xml")
	public void testSave () throws Exception{
		
		// Given
		Profile profile = Profile.builder().id(3).type("MODIFIED").build();
		
		// When
		int updateIndex = profileDao.save(profile);
		
		// Then
		Assert.assertTrue(updateIndex > 0);
	}
	
	/**
	 * Test delete profile
	 */
	@Test
	public void testDelete() throws Exception {
		
		// Given
		Profile profile = Profile.builder().id(2).type("Developer").build();
		
		// When
		int deleteIndex = profileDao.delete(profile);
		
		// Then
		Assert.assertTrue(deleteIndex > 0);
	}
	
	/**
	 * Test add profile
	 */
	@Test
	@DataSet("ProfileDaoTest.testAdd.xml")
	public void testAdd() throws Exception {
		
		// Given
		Profile profile = Profile.builder().type("Build Manager").build();
		
		// When
		int addIndex = profileDao.add(profile);
		
		// Then
		Assert.assertTrue(addIndex > 0);
	}
}
