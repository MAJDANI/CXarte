package com.novedia.talentmap.store.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.model.dto.EAEGeneralityDTO;
import com.novedia.talentmap.model.dto.EntityUtil;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.EAE;

/**
 * Test class for Data Access to table EAE. {@link EAEDao}
 * 
 * @author v.guillemain
 * 
 */
@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet({ "PROFILE_DatasetTest.xml", "EAE_STATE_DatasetTest.xml",
	"COLLEAGUE_DatasetTest.xml", "EAEDaoTest.xml" })
public class EAEDaoTest {

    @SpringBeanByName
    private SqlMapClient sqlMapClient;

    @SpringBeanByName
    EAEDao eaeDao;

    @Before
    public void setUp() throws Exception {
	eaeDao.setSqlMapClient(sqlMapClient);
    }

    /**
     * Test .....
     */
    @Test
    public void testGetOngoingEAEForCM() {

	// Given
	Integer idColleague = 62;

	// When
	List<EAEForSynthesisDTO> listEaes = eaeDao.getOngoingEAEForCM(idColleague);

	// Then
	ReflectionAssert.assertPropertyLenientEquals("id", Arrays.asList(1, 2),
		listEaes);
	ReflectionAssert.assertPropertyLenientEquals("lastName",
		Arrays.asList("GUILLEMAIN", "COLLAB1"), listEaes);
	ReflectionAssert.assertPropertyLenientEquals("firstName",
		Arrays.asList("Vanessa", "Prénom1"), listEaes);
	ReflectionAssert.assertPropertyLenientEquals("eaeStateId",
		Arrays.asList(1, 2), listEaes);

    }

    /**
     * Test .....
     */
    @Ignore
    @Test(expected = DataAccessException.class)
    public void testGetOngoingEAEForCMThrowsException() {
	// TODO
    }

    /**
     * Test .....
     */
    @Test
    public void testGetCollabWithoutOngoingEAEForManager() {

	// Given
	Integer idColleague = 62;

	// When
	List<Colleague> listCollabs = eaeDao
		.getCollabWithoutOngoingEAEForManager(idColleague);

	// Then
	ReflectionAssert.assertPropertyLenientEquals("id",
		Arrays.asList(65, 66, 67), listCollabs);
	ReflectionAssert.assertPropertyLenientEquals("lastName",
		Arrays.asList("COLLAB2", "COLLAB3", "COLLAB4"), listCollabs);
	ReflectionAssert.assertPropertyLenientEquals("firstName",
		Arrays.asList("Prénom2", "Prénom3", "Prénom4"), listCollabs);

    }

    /**
     * Test .....
     */
    @Ignore
    @Test(expected = DataAccessException.class)
    public void testGetCollabWithoutOngoingEAEForManagerThrowsException() {
	// TODO
    }

    @Test
    public void getHistoryEAEForCollab() {
	// Given
	Integer idColleague = 58;
	// When
	List<EAEForSynthesisDTO> listEaes = eaeDao
		.getHistoryEAEForCollab(idColleague);
	// Then
	ReflectionAssert.assertPropertyLenientEquals("id", Arrays.asList(4, 5),
		listEaes);
	ReflectionAssert.assertPropertyLenientEquals("eaeStateId",
		Arrays.asList(1, 2), listEaes);
    }

    @Test
    public void getEAEGenerality() {
	// Given
	Integer idEAE = 1;
	EAEGeneralityDTO eaeExpected = EntityUtil.createEAEGenerality(1, "GUILLEMAIN", "Vanessa", "", "", "", "2013-06-25", "2010-01-25", "", 1);
	// When
	EAEGeneralityDTO eaeActual = eaeDao
		.getEAEGenerality(idEAE);
	// Then
	Assert.assertEquals(eaeExpected.getId(), eaeActual.getId());
	Assert.assertEquals(eaeExpected.getCollabFirstName(), eaeActual.getCollabFirstName());
	Assert.assertEquals(eaeExpected.getCollabLastName(), eaeActual.getCollabLastName());
	
    }

    /**
     * Test get EAE by id
     */
    @Test
    public void testGetById() {
	// Given
	Integer idEAE = 1;
	// When
	EAE eae = eaeDao.get(idEAE);
	// Then
	ReflectionAssert.assertPropertyLenientEquals("id", 1, eae);
    }

    /**
     * Test save
     */
    @Test
    @ExpectedDataSet("EAEDaoTest.testSave-result.xml")
    public void testSave() {
	// Given
	EAE eae = EntityUtil.createEAE(3, 65, 62, "2013-05-25", 1);
	// When
	int updateIndex = eaeDao.save(eae);
	// Then
	Assert.assertTrue(updateIndex > 0);
    }

    /**
     * Test add
     */
    @Test
    @ExpectedDataSet("EAEDaoTest.testAdd-result.xml")
    public void testAdd() {
	// Given
	EAE eae = EntityUtil.createEAE(4, 63, 62, "2013-06-25", 1);
	// When
	int addedIndex = eaeDao.add(eae);
	// Then
	Assert.assertTrue(addedIndex > 0);
    }

    /**
     * Test delete
     */
    @Test
    @ExpectedDataSet("EAEDaoTest.testDelete-result.xml")
    public void testDelete() {
	// Given
	EAE eae = EntityUtil.createEAE(3, 65, 62, "2013-05-25", 3);
	// When
	int deleteIndex = eaeDao.delete(eae);
	// Then
	Assert.assertTrue(deleteIndex > 0);
    }

}
