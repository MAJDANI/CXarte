package com.novedia.talentmap.store.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.ExpectedException;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;

/**
 * Test Collaborator
 * 
 * @author moumbe
 * 
 */
@SpringApplicationContext("test-store-spring-context.xml")
public class CollaboratorDaoTest extends UnitilsJUnit4 {

	@SpringBean("sqlMapClient")
	private SqlMapClient sqlMapClient;

	@SpringBean("collaboratorDao")
	private CollaboratorDao collaboratorDao;

	@Before
	public void setUp() {
		// Clear all Data before tests
		collaboratorDao.setSqlMapClient(sqlMapClient);
	}

	/**
	 * Test get collab by id
	 */
	@Test
	@DataSet("CollaboratorDaoTest.xml")
	public void testGet() {
		Collaborator collaborator = collaboratorDao.get(1);
		ReflectionAssert.assertPropertyLenientEquals("id", 1, collaborator);
	}

	/**
	 * Test get all
	 */
	@Test
	public void testGetAll() {
		List<Collaborator> listCollabs = collaboratorDao.getAll();
//		ReflectionAssert.assertPropertyLenientEquals("id", Arrays.asList(1, 2),
//				listCollabs);
		Assert.assertNotNull(listCollabs);
	}

	/**
	 * Test save
	 */
	@Test
	public void testSave() {
		Collaborator collaborator = Collaborator.Builder.builder().id(1)
				.profileId(1).phone("060000").experience(5)
				.firstName("MODIFCOLLAB2").build();
		int updateId = collaboratorDao.save(collaborator);
		Assert.assertEquals(1, updateId);
	}

	/**
	 * Test add collab
	 */
	@Test
	public void testAdd() {
		Collaborator collaborator = Collaborator.Builder.builder()
				.managerId(1).profileId(1).lastName("collab3")
				.firstName("collab3").email("collab3@mail.com").employmentDate(new Date()).experience(4)
				.businessEngineer("no").build();
		int addIndex = collaboratorDao.add(collaborator);
		Assert.assertEquals(collaborator.getId().intValue(), addIndex);
	}
	
	/**
	 * Test delete
	 */
	@Test
	@ExpectedException(UnsupportedOperationException.class)
	public void testDelete () {
		//TODO: implements skeletton
	}
}
