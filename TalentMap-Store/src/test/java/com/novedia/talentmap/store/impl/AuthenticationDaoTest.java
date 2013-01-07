package com.novedia.talentmap.store.impl;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.spring.annotation.SpringBeanByName;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.CredentialToken;

@Ignore
@SpringApplicationContext("test-store-spring-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class AuthenticationDaoTest {
	
	@SpringBean("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@SpringBeanByName
	private AuthenticationDao authenticationDao;
		
	private CredentialToken credantial = new CredentialToken();
	
	private Authentication authentication = new Authentication();

	@SpringBeanByName
	private Authorization authorization;

	@Before
	public void setUp() throws Exception {
		authenticationDao.setSqlMapClient(sqlMapClient);
	}

	/**
	 * Add the authentication
	 */
	@Test
	@DataSet("AuthenticateDaoTest.xml")
	public void testAdd() {
	
		authorization.setLabel("Consultant");
		authorization.setRoleId(4);
		
		credantial.setLogin("v.dibi");
		credantial.setPassword("v.dibi");
		
		authentication.setId(1);
		authentication.setColleagueId(1);
		authentication.setAuthorization(authorization);
		authentication.setToken(credantial);
		
		int addAuth = authenticationDao.add(authentication);
		Assert.assertEquals(authentication.getId().intValue(), addAuth);
	}
	
	/**
	 * Update Authenticate
	 */
	@Test
	@DataSet("AuthenticateDaoTest.testSave-result.xml")
	public void testSave() {	
		authorization.setLabel("Consultant");
		authorization.setRoleId(4);
		
		credantial.setLogin("o.chauvie");
		credantial.setPassword("testUpdate");
		
		//authentication.setId(1);
		authentication.setColleagueId(1);
		authentication.setAuthorization(authorization);
		authentication.setToken(credantial);
		
		int updateAuth = authenticationDao.save(authentication);
		//Assert.assertEquals(1, updateAuth);	
	}
	/**
	 * check the authentication exist
	 */
	@Test
	@DataSet("AuthenticateDaoTest.testCheck-result.xml")
	public void testCheckCredentialToken() {
		
		Authentication authExpected = authenticationDao.get(17);
		ReflectionAssert.assertPropertyLenientEquals("id", 17, authExpected);
		ReflectionAssert.assertPropertyLenientEquals("token.login", "v.dibi", authExpected);
		ReflectionAssert.assertPropertyLenientEquals("token.password", "testUpdate1", authExpected);
		
		Authentication authActual = authenticationDao.get(17);
		ReflectionAssert.assertReflectionEquals(authExpected, authActual, ReflectionComparatorMode.IGNORE_DEFAULTS);		
	}
}