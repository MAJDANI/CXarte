package com.novedia.talentmap.store.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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
import com.novedia.talentmap.model.entity.Tool;

/**
 * Test ToolDao
 * 
 * @author e.moumbe
 * @author j.marie-sainte
 * @author b.tiomofofou
 * 
 */
@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet("ToolDaoTest.xml")
public class ToolDaoTest {

    @SpringBeanByName
    private SqlMapClient sqlMapClient;

    @SpringBeanByName
    private ToolDao toolDao;

    @Before
    public void setUp() {
	toolDao.setSqlMapClient(sqlMapClient);
    }

    /**
     * Test get Tool by id
     * 
     * @throws Exception
     */
    @Test
    public void testGetById() throws Exception {
	Tool tool = toolDao.get(1);
	ReflectionAssert.assertPropertyLenientEquals("id", 1, tool);
    }

    /**
     * Test get all tools
     * 
     * @throws Exception
     */
    @Test
    public void testGetAll() throws Exception {

	// Given
	Concept expectedCpt = Concept.builder().id(1).name("ORM").build();

	// When
	List<Tool> toolsList = toolDao.getAll();

	// Then
	ReflectionAssert.assertPropertyLenientEquals("id",
		Arrays.asList(1, 2, 3), toolsList);
	ReflectionAssert
		.assertPropertyLenientEquals("name",
			Arrays.asList("MYBATIS", "HIBERNATE", "SPRING JDBC"),
			toolsList);
	ReflectionAssert
		.assertPropertyLenientEquals("concept",
			Arrays.asList(expectedCpt, expectedCpt, expectedCpt),
			toolsList);
    }

    /**
     * Test update tool
     * 
     * @throws Exception
     */
    @Test
    @ExpectedDataSet("ToolDaoTest.testSave-result.xml")
    public void testSave() throws Exception {

	// Given
	Category category = Category.builder().id(1).name("JAVA").build();
	Concept concept = Concept.builder().id(1).name("ORM")
		.category(category).build();
	Tool tool = Tool.builder().id(2).name("JDO").concept(concept).build();

	// When
	int updateIndex = toolDao.save(tool);

	// Then
	Assert.assertTrue(updateIndex > 0);
    }

    /**
     * Test delete tool
     * 
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {

	// Given
	Category category = Category.builder().id(1).name("JAVA").build();
	Concept concept = Concept.builder().id(1).name("ORM")
		.category(category).build();
	Tool tool = Tool.builder().id(1).name("MYBATIS").concept(concept)
		.build();

	// When
	int deleteIndex = toolDao.delete(tool);

	// Then
	Assert.assertTrue(deleteIndex > 0);
    }

    /**
     * Test add
     * 
     * @throws Exception
     */
    @Test
    public void testAdd() throws Exception {

	// Given
	Category category = Category.builder().id(1).name("JAVA").build();
	Concept concept = Concept.builder().id(1).name("ORM")
		.category(category).build();
	Tool tool = Tool.builder().name("ActiveObjects").concept(concept)
		.build();

	// When
	int addIndex = toolDao.add(tool);

	// Then
	Assert.assertTrue(addIndex > 0);
    }
}
