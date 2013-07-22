package com.novedia.talentmap.store.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;

/**
 * Test MissionDao
 * 
 * @author moumbe
 */

@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet({ "ProfileDaoTest.xml", "ColleagueDaoTest.xml", "ClientDataSet.xml",
	"MissionDaoTest.xml" })
public class MissionDaoTest {

    @SpringBeanByName
    private SqlMapClient sqlMapClient;

    @SpringBeanByName
    private MissionDao missionDao;

    /**
     * Test initialization
     */
    public void setUp() {
	missionDao.setSqlMapClient(sqlMapClient);
    }

    /**
     * Test get a mission by id.
     */
    @Test
    public void testGetById() throws Exception {
	Mission mission = missionDao.get(1);
	ReflectionAssert.assertPropertyReflectionEquals("id", 1, mission);
    }

    /**
     * Test get all missions by colleague id.
     */
    @Test
    public void testGetAllByColleague() throws Exception {
	Integer colleagueId = 1;
	List<Mission> missionList = missionDao.getAllByColleagueId(colleagueId);
	ReflectionAssert.assertPropertyLenientEquals("id", Arrays.asList(1, 2),
		missionList);
    }

    /**
     * Test save modification on a mission tuple.
     */
    @Test
    @DataSet({ "ProfileDaoTest.xml", "ColleagueDaoTest.xml",
	    "ClientDataSet.xml", "MissionDaoTest-Save-result.xml" })
    public void testSaveReturnInt() throws Exception {

	// Given
	Category category = Category.builder().id(2).name(".NET").build();
	Concept concept = Concept.builder().id(3).category(category)
		.name("ORM").build();
	Client c = Client.builder().id(6).name("Total").build();
	List<Tool> tools = new ArrayList<Tool>();
	Tool t1 = Tool.builder().id(9).concept(concept).name("Spring.NET")
		.build();
	tools.add(t1);
	Tool t2 = Tool.builder().id(10).concept(concept).name("Spring").build();
	tools.add(t2);
	Mission mission = Mission.builder().colleagueId(1).id(1).title("test")
		.place("test").client(c).startDate(new Date(2012 - 05 - 26))
		.tools(tools).build();
	Mission mission2 = Mission.builder().colleagueId(1).id(2).title("test")
		.place("test").client(c).startDate(new Date(2012 - 05 - 26))
		.tools(tools).build();
	int expectedResult = 1;
	int expectedResult2 = 0;

	// When
	int currentResult = missionDao.save(mission);
	int currentResult2 = missionDao.save(mission2);

	// Then
	Assert.assertEquals(expectedResult, currentResult);
	Assert.assertEquals(expectedResult2, currentResult2);
    }

    @Test(expected = DataAccessException.class)
    public void testSaveNullPointerException() {
	Mission mission = null;
	missionDao.save(mission);
    }
}
