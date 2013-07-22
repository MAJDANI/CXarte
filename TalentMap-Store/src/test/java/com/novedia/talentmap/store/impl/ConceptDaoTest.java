package com.novedia.talentmap.store.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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

/**
 * Test conceptDao
 * 
 * @author j.marie-sainte
 */
@Ignore
@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet("ConceptDaoTest.xml")
public class ConceptDaoTest {

    @SpringBeanByName
    private SqlMapClient sqlMapClient;

    @SpringBeanByName
    private ConceptDao conceptDao;

    @Before
    public void setUp() {
	conceptDao.setSqlMapClient(sqlMapClient);
    }

    /**
     * Test get concept by id
     */
    @Test
    public void testGetById() {
	Concept concept = conceptDao.get(1);
	ReflectionAssert.assertPropertyLenientEquals("id", 1, concept);
    }

    /**
     * Test gel all concepts
     */
    @Test
    public void testGetAll() {

	// Given
	Category expectedCat = Category.builder().id(1).name("CATEGORY1")
		.build();

	// When
	List<Concept> concepts = conceptDao.getAll();

	// Then
	ReflectionAssert.assertPropertyLenientEquals("id",
		Arrays.asList(1, 2, 3, 4), concepts);
	ReflectionAssert.assertPropertyLenientEquals("category", Arrays.asList(
		expectedCat, expectedCat, expectedCat, expectedCat), concepts);
	ReflectionAssert.assertPropertyLenientEquals("name",
		Arrays.asList("CONCEPT1", "CONCEPT2", "CONCEPT3", "CONCEPT4"),
		concepts);
    }

    /**
     * Test update concept
     */
    @Test
    @ExpectedDataSet("ConceptDaoTest.testSave-result.xml")
    public void testSave() {

	// Given
	Category category = Category.builder().id(1).name("CATEGORY1").build();
	Concept concept = Concept.builder().id(2).name("MODIFIED")
		.category(category).build();

	// When
	int updateIndex = conceptDao.save(concept);

	// Then
	Assert.assertTrue(updateIndex > 0);
    }

    /**
     * Test delete concept
     */
    @Test
    public void testDelete() {

	// Given
	Category category = Category.builder().id(1).name("CATEGORY1").build();
	Concept concept = Concept.builder().id(4).name("CONCEPT4")
		.category(category).build();

	// When
	int deleteIndex = conceptDao.delete(concept);

	// Then
	Assert.assertTrue(deleteIndex > 0);
    }

    /**
     * Test add concept
     */
    @Test
    @DataSet("ConceptDaoTest.testAdd.xml")
    public void testAdd() {

	// Given
	Category category = Category.builder().id(1).name("CATEGORY1").build();
	Concept concept = Concept.builder().name("CONCEPT_ADDED")
		.category(category).build();

	// When
	int addIndex = conceptDao.add(concept);

	// Then
	Assert.assertTrue(addIndex > 0);
    }
}
