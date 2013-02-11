package com.novedia.talentmap.store.impl;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

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

/**
 * Test CategoryDao
 * @author moumbe
 *
 */
@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet("CategoryDaoTest.xml")
public class CategoryDaoTest {

	@SpringBeanByName
	private SqlMapClient sqlMapClient;
	
	@SpringBeanByName
	private CategoryDao categoryDao;
	
	@Before
	public void setUp() {
		categoryDao.setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Test get
	 */
	@Test
	public void testGetById () {
		Category category = categoryDao.get(1);
		ReflectionAssert.assertPropertyLenientEquals("id", 1 ,category);
		ReflectionAssert.assertPropertyLenientEquals("name", "CATEGORY1" ,category);
	}
	
	/**
	 * Test get all
	 */
	@Test
	public void testGetAll () {
		List<Category> listCategory = categoryDao.getAll();
		ReflectionAssert.assertPropertyLenientEquals("id", Arrays.asList(1,2,3), listCategory);
		ReflectionAssert.assertPropertyLenientEquals("name", Arrays.asList("CATEGORY1","CATEGORY2","CATEGORY3"), listCategory);
	}
	
	/**
	 * Test save category
	 */
	@Test
	@ExpectedDataSet("CategoryDaoTest.testSave-result.xml")
	public void testSave () {
		Category category = Category.builder().id(3).name("MODIFCATEGORY3").build();
		int updateIndex = categoryDao.save(category);
		Assert.assertTrue(updateIndex > 0);
	}
	
	/**
	 * Test delete category
	 */
	@Test
	public void testDelete () {
		
		// Given
		Category category = Category.builder().id(1).build();
		
		//When
		int deleteIndex = categoryDao.delete(category);
		
		// Then
		Assert.assertTrue(deleteIndex > 0);
	}
	
	/**
	 * Test add category
	 */
	@Test
	@DataSet("CategoryDaoTest.testAdd.xml")
	public void testAdd () {
		Category cat = Category.builder().id(1).name("CATEGORY").build();
		int addIndex = categoryDao.add(cat);
		Assert.assertTrue(addIndex > 0);
	}
	
}
