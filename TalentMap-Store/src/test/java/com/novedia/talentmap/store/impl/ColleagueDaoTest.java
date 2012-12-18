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
import org.springframework.test.annotation.ExpectedException;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Colleague;

/**
 * Test Collaborator
 * 
 * @author moumbe
 * 
 */
@Ignore
@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ColleagueDaoTest {

	@SpringBean("sqlMapClient")
	private SqlMapClient sqlMapClient;

	@SpringBean("colleagueDao")
	private ColleagueDao colleagueDao;

	@Before
	public void setUp() {
		// Clear all Data before tests
		colleagueDao.setSqlMapClient(sqlMapClient);
	}

	/**
	 * Test get collab by id
	 */
	@Test
	@DataSet("ColleagueDaoTest.xml")
	public void testGet() {

		Colleague colleague = colleagueDao.get(1);
		ReflectionAssert.assertPropertyLenientEquals("id", 1, colleague);
		ReflectionAssert.assertPropertyLenientEquals("managerId", 1, colleague);
		ReflectionAssert.assertPropertyLenientEquals("profileId", 1, colleague);
		ReflectionAssert.assertPropertyLenientEquals("lastName", "collab1",
				colleague);
		ReflectionAssert.assertPropertyLenientEquals("firstName", "collab1",
				colleague);
		ReflectionAssert.assertPropertyLenientEquals("email",
				"collab1@gmail.com", colleague);
		ReflectionAssert.assertPropertyLenientEquals("phone", "6010101010",
				colleague);
		ReflectionAssert.assertPropertyLenientEquals("employmentDate",
				new DateTime("2011-12-12"), colleague);
		ReflectionAssert
				.assertPropertyLenientEquals("experience", 5, colleague);
		ReflectionAssert.assertPropertyLenientEquals("businessEngineer",
				"Colombe Tramond", colleague);
		ReflectionAssert
				.assertPropertyLenientEquals("mission", null, colleague);
		ReflectionAssert.assertPropertyLenientEquals("tool", null, colleague);
	}

	/**
	 * Test get all
	 */
	@Test
	public void testGetAll() {
		List<Colleague> listCollabs = colleagueDao.getAll();
		ReflectionAssert.assertPropertyLenientEquals("id", Arrays.asList(1, 2),                                                                                                    
				listCollabs);
	}

	/**
	 * Test save
	 */
	@Test
	public void testSave() {
		Colleague colleague = Colleague.Builder.builder().id(1).profileId(1)
				.phone("060000").experience(5).firstName("MODIFCOLLAB2")
				.build();
		int updateId = colleagueDao.save(colleague);
		Assert.assertEquals(1, updateId);
	}

	/**
	 * Test add collab
	 */
	@DataSet("ColleagueDaoTest.testAdd.xml")
	@Test
	public void testAdd() {
		Colleague collaborator = Colleague.Builder.builder().managerId(1)
				.profileId(1).lastName("collab3").firstName("collab3")
				.email("collab3@mail.com").employmentDate(new Date())
				.experience(4).businessEngineer("no").build();
		int addIndex = colleagueDao.add(collaborator);
		Assert.assertEquals(collaborator.getId().intValue(), addIndex);
	}

	/**
	 * Test delete
	 */
	@Test
	@ExpectedException(UnsupportedOperationException.class)
	public void testDelete() {
		// TODO: implements skeletton
	}
}
