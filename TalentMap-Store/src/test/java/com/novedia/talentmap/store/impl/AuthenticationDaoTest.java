package com.novedia.talentmap.store.impl;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.model.entity.Registration;

@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class AuthenticationDaoTest {

    @SpringBean("sqlMapClient")
    private SqlMapClient sqlMapClient;

    @SpringBean("authenticationDao")
    private AuthenticationDao authenticationDao;

    @Before
    public void setUp() throws Exception {
	authenticationDao.setSqlMapClient(sqlMapClient);
    }

    /**
     * update user's password
     */
    @Test
    @DataSet({ "UserRoleDataSet.xml", "ProfileDaoTest.xml",
	    "AuthenticateDaoTest.testSave-result.xml" })
    public void testSaveReturnInteger() {
	// Given
	CredentialToken credential1 = new CredentialToken();
	Authentication authentication1 = new Authentication();

	CredentialToken credential2 = new CredentialToken();
	Authentication authentication2 = new Authentication();

	credential1.setLogin("o.chauvie");
	credential1.setPassword("newPassword");
	authentication1.setToken(credential1);

	credential2.setLogin("user2");
	credential2.setPassword("passworduser2");
	authentication2.setToken(credential2);
	int expectedResultSave1 = 1;
	int expectedResultSave2 = 0;

	// When
	int currentResultSave1 = authenticationDao.save(authentication1);
	int currentResultSave2 = authenticationDao.save(authentication2);

	// Then
	Assert.assertEquals(expectedResultSave1, currentResultSave1);
	Assert.assertEquals(expectedResultSave2, currentResultSave2);
    }

    @Test(expected = DataAccessException.class)
    public void testSaveThrowDataAccessException() {
	// Given
	Authentication authentication = null;

	// When
	authenticationDao.save(authentication);
    }

    /**
     * Test countLogin method
     */
    @Test
    @DataSet({ "UserRoleDataSet.xml", "ProfileDaoTest.xml",
	    "AuthenticateDaoTest.testCheck-result.xml" })
    public void testCountLoginReturnInteger() {
	// Given
	String existLogin = "o.chauvie";
	String notExistLogin = "o.chauvie  ";
	Integer expectedResultExistLogin = 1;
	Integer expectedResultNotExistLogin = 0;

	// When
	Integer currentResultExistLogin = authenticationDao
		.countLogin(existLogin);
	Integer currentResultNotExistLogin = authenticationDao
		.countLogin(notExistLogin);

	// Then
	Assert.assertEquals(expectedResultExistLogin, currentResultExistLogin);
	Assert.assertEquals(expectedResultNotExistLogin,
		currentResultNotExistLogin);

    }

    @Test
    @DataSet({ "UserRoleDataSet.xml", "ProfileDaoTest.xml",
	    "AuthenticateDaoTest.testSave-result.xml" })
    public void testAddUserFromRegistration() {
	// Given
	Registration registration = Registration.Builder.builder()
		.login("t.test").colleagueId(4).password("test").build();
	registration.setRole(Authorization.Role.CL);
	Integer expectedResult = null;

	// When
	Integer currentResult = authenticationDao
		.addUserFromRegistration(registration);

	// Then
	Assert.assertEquals(expectedResult, currentResult);

    }

}