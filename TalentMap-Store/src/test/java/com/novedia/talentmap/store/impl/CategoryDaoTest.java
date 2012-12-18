package com.novedia.talentmap.store.impl;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.dbunit.datasetloadstrategy.impl.CleanInsertLoadStrategy;
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
@SpringApplicationContext("test-store-spring-context.xml")
public class CategoryDaoTest extends UnitilsJUnit4 {

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
	@DataSet(loadStrategy=CleanInsertLoadStrategy.class)
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
	@Test
	public void testAdd () {
		Category cat = Category.Builder.builder().name("CATEGORY4").build();
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
	@Test
	public void testDelete () {
		Category category = Category.Builder.builder().id(1).build();
		int deleteIndex = categoryDao.delete(category);
		//If succeed, delete return 1
		Assert.assertEquals(1, deleteIndex);
	}
}
