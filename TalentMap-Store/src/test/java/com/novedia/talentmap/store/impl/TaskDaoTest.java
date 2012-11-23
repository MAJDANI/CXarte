package com.novedia.talentmap.store.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.datasetloadstrategy.impl.CleanInsertLoadStrategy;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Task;

/**
 * Test TaskDao
 * @author moumbe
 *
 */
@DataSet (loadStrategy = CleanInsertLoadStrategy.class)
@SpringApplicationContext("test-store-spring-context.xml")
public class TaskDaoTest extends UnitilsJUnit4 {

	@SpringBeanByName
	private SqlMapClient sqlMapClient;
	
	@SpringBeanByName
	private TaskDao taskDao;
	
	@Before
	public void setUp() {
		taskDao.setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Test de récupération de toutes les notes attribuées par un collaborateur aux différents
	 * outilss
	 */
	@Test
	public void testGet() {
		Task task = taskDao.get(1);
		assertNotNull(task);
	}
}
