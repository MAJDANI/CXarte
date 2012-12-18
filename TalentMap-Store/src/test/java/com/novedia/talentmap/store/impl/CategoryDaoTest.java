package com.novedia.talentmap.store.impl;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Category;

/**
 * Test CategoryDao
 * @author moumbe
 *
 */
@Ignore
@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class CategoryDaoTest {

	@SpringBean("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@SpringBean("categoryDao")
	private CategoryDao categoryDao;
	
	@Before
	public void setUp() {
		categoryDao.setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Test get
	 */
	@Test
	@DataSet("CategoryDaoTest.xml")
	public void testGet () {
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
	 * Test add category
	 */
	@Ignore
	@Test
	@DataSet("CategoryDaoTest.testAdd.xml")
	public void testAdd () {
		Category cat = Category.Builder.builder().id(41).name("CATEGORY").build();
		int addIndex = categoryDao.add(cat);
		Assert.assertEquals(cat.getId().intValue(), addIndex);
	}
	
	/**
	 * Test save category (update)
	 */
	@Test
	@ExpectedDataSet("CategoryDaoTest.testSave-result.xml")
	@Ignore
	public void testSave () {
		Category cat = Category.Builder.builder().id(3).name("MODIFCATEGORY3").build();
		int updateIndex = categoryDao.save(cat);
		
		// TODO : définir les assertions
	}
	
	/**
	 * Test delete category
	 */
	@Ignore
	@Test
	public void testDelete () {
		Category category = Category.Builder.builder().id(1).build();
		int deleteIndex = categoryDao.delete(category);
		//If succeed, delete return 1
		Assert.assertEquals(1, deleteIndex);
	}
}
