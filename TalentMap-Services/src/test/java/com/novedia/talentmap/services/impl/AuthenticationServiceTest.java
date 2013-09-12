/**
 * 
 */
package com.novedia.talentmap.services.impl;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.store.impl.AuthenticationDao;

/**
 * Test class for authentication service.
 * 
 * @author v.dibi
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

	/**
	 * Class under test
	 */
	private AuthenticationService service;

	@Mock
	private AuthenticationDao authenticationDaoMock;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new AuthenticationService();
		service.setAuthenticationDao(authenticationDaoMock);
	}

	/**
	 * Test method for
	 * {@link com.novedia.talentmap.services.impl.AuthenticationService#checkUser(com.novedia.talentmap.model.entity.CredentialToken)}
	 */
	@Test
	public void checkAuthenticationWhenUserExist() {

		// Given
		CredentialToken credentialToken = new CredentialToken();
		credentialToken.setLogin("v.dibi");
		credentialToken.setPassword("v.dibi");

		Authorization authorization = new Authorization();
		authorization.setLabel("Consultant");
		authorization.setRoleId(4);

		Authentication userAuthentication = new Authentication();
		userAuthentication.setColleagueId(35);
		userAuthentication.setAuthorization(authorization);
		userAuthentication.setToken(credentialToken);

		// When
		Mockito.when(authenticationDaoMock.check(credentialToken)).thenReturn(
				userAuthentication);
		Authentication returnedAuthentication = service
				.checkUser(credentialToken);

		// Then
		Mockito.verify(authenticationDaoMock, Mockito.times(1)).check(
				credentialToken);
		assertSame(returnedAuthentication, userAuthentication);
	}

	@Test
	public void checkAuthenticationWhenUserNotExist() throws Exception {

		// Given
		CredentialToken credentialToken = new CredentialToken();
		credentialToken.setLogin("unknow_user");
		credentialToken.setPassword("unknow password");

		Authorization authorization = new Authorization();
		authorization.setLabel("Consultant");
		authorization.setRoleId(4);

		Authentication userAuthentication = new Authentication();
		userAuthentication.setColleagueId(35);
		userAuthentication.setAuthorization(authorization);
		userAuthentication.setToken(credentialToken);

		// When
		Mockito.when(authenticationDaoMock.check(credentialToken)).thenReturn(
				null);
		Authentication returnedAuthentication = service
				.checkUser(credentialToken);

		// Then
		Mockito.verify(authenticationDaoMock, Mockito.times(1)).check(
				credentialToken);
		assertNull(returnedAuthentication);
	}
}