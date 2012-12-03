package com.novedia.talentmap.store.impl;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
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
//@DataSet (loadStrategy = CleanInsertLoadStrategy.class)
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
	
	@Test
	public void testGet () {
		Category category = categoryDao.get(1);
		ReflectionAssert.assertPropertyLenientEquals("id", 1 ,category);
	}
}
