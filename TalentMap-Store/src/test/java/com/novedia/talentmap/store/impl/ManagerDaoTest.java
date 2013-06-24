package com.novedia.talentmap.store.impl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Manager;

/**
 * Test ManagerDao
 * 
 * @author moumbe
 * 
 */
@Ignore
@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ManagerDaoTest {

    @SpringBeanByName
    private SqlMapClient sqlMapClient;

    @SpringBeanByName
    private ManagerDao managerDao;

    @Before
    public void setUp() {
	managerDao.setSqlMapClient(sqlMapClient);
    }

    /**
     * Test get Manager by id
     */
    @Test
    @DataSet("ManagerDaoTest.xml")
    public void testGet() {
	Manager manager = managerDao.get(1);
	ReflectionAssert.assertPropertyReflectionEquals("id", 1, manager);
    }
}
