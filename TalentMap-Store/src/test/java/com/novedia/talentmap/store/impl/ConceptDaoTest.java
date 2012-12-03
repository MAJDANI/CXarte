package com.novedia.talentmap.store.impl;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.dbunit.datasetloadstrategy.impl.CleanInsertLoadStrategy;
import org.unitils.dbunit.datasetloadstrategy.impl.RefreshLoadStrategy;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;

/**
 * Test conceptDao
 * @author moumbe
 *
 */
@SpringApplicationContext("test-store-spring-context.xml")
@DataSet(loadStrategy = CleanInsertLoadStrategy.class)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ConceptDaoTest {
	
	@SpringBean("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@SpringBean("conceptDao")
	private ConceptDao conceptDao;
	
	@Before
	public void setUp() {
		conceptDao.setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Test get concetp by id
	 */
	@Test
	@DataSet(loadStrategy = RefreshLoadStrategy.class)
	public void testGet() {
		Concept concept = conceptDao.get(1);
		ReflectionAssert.assertPropertyLenientEquals("id", 1 ,concept);
	}
	
	/**
	 * Test gel all concepts
	 */
	@Test
	@DataSet(loadStrategy = RefreshLoadStrategy.class)
	public void testGetAll() {
		List<Concept> concepts = conceptDao.getAll();
		assertNotNull(concepts);
	}
	
	/**
	 * Test update row
	 */
	@Test
	@DataSet(loadStrategy = RefreshLoadStrategy.class)
	@ExpectedDataSet("ConceptDaoTest.testSave-result.xml")
	public void testSave () {
		
		Concept concept = Concept.Builder.builder().build();
		concept.setId(2);
		concept.setName("TOTO");
		
		Category category = Category.Builder.builder().id(1).build();
		concept.setCategory(category);
		int savedId = conceptDao.save(concept);
		
//		Assert.assertEquals(concept.getId().intValue(), savedId);
	}
	
	/**
	 * Test add concept
	 */
	@Test
//	@DataSet(loadStrategy = RefreshLoadStrategy.class)
	@DataSet(loadStrategy = CleanInsertLoadStrategy.class)
	public void testAdd() {
		Category category = Category.Builder.builder().id(1).name("CATEGORY1").build();
		Concept concept = Concept.Builder.builder().name("CONCEPT10").category(category).build();
		int actual = conceptDao.add(concept);
		
		Assert.assertEquals(concept.getId().intValue(), actual);
	}
	
	/**
	 * Test delete
	 */
	@Test
	@DataSet ("ConceptDaoTest.deleteConcept.xml")
	public void testDelete () {
		Concept concept = Concept.Builder.builder().id(1).build();
		int index = conceptDao.delete(concept);
		assertNotNull(index);
	}
}
