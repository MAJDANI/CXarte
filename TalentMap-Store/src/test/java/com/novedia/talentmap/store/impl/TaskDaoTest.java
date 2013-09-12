package com.novedia.talentmap.store.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Task;

/**
 * Test TaskDao
 * 
 * @author moumbe
 * 
 */
@Ignore
@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class TaskDaoTest {

    @SpringBeanByName
    private SqlMapClient sqlMapClient;

    @SpringBeanByName
    private TaskDao taskDao;

    @Before
    public void setUp() {
	taskDao.setSqlMapClient(sqlMapClient);
    }

    /**
     * Test de récupération de toutes les notes attribuées par un collaborateur
     * aux différents outils
     */
    @Test
    @DataSet("TaskDaoTest.xml")
    public void testGet() {
	Task task = taskDao.get(1);
	assertNotNull(task);
    }
}
