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
	
	@SpringBean("authenticationDao")
	private AuthenticationDao authenticationDao;
		

	@SpringBeanByName
	private Authorization authorization;

	@Before
	public void setUp() throws Exception {
		authenticationDao.setSqlMapClient(sqlMapClient);
	}

	private CredentialToken credential = new CredentialToken();
	private Authentication authentication = new Authentication();
	
	
	/**
	 * Add the authentication
	 */
	@Test
	@DataSet("AuthenticateDaoTest.xml")
	public void testAdd() {
	
		authorization.setLabel("Consultant");
		authorization.setRoleId(4);
		
		credential.setLogin("v.dibi");
		credential.setPassword("v.dibi");
		
		authentication.setColleagueId(1);
		authentication.setAuthorization(authorization);
		authentication.setToken(credential);

		//TODO REvoir si test bien fait
		Object addAuth = authenticationDao.add(authentication);
		Assert.assertNotNull(addAuth);
	}
	
	/**
	 * Update Authenticate
	 */
	@Test
	@DataSet("AuthenticateDaoTest.testSave-result.xml")
	public void testSave() {	
		authorization.setLabel("Consultant");
		authorization.setRoleId(4);
		
		credential.setLogin("o.chauvie");
		credential.setPassword("testUpdate");
		
		authentication.setColleagueId(1);
		authentication.setAuthorization(authorization);
		authentication.setToken(credential);
		
		int updateAuth = authenticationDao.save(authentication);
		Assert.assertEquals(1, updateAuth);	
	}
}