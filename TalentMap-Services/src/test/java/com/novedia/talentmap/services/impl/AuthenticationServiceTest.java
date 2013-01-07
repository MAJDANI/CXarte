/**
 * 
 */
package com.novedia.talentmap.services.impl;

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
 * @author v.dibi
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {
	
	private AuthenticationService service;
	
	private CredentialToken credantialActual;

	@Mock
	private AuthenticationDao authenticationDaoMock;
	
	private Authentication authenticationActual;
	
	private Authorization authorization;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new AuthenticationService();
		service.setAuthenticationDao(authenticationDaoMock);
		credantialActual = new CredentialToken();
		authenticationActual = new Authentication();
		authorization = new Authorization(); 
		
	}

	/**
	 * Test method for {@link com.novedia.talentmap.services.impl.AuthenticationService#checkUser(com.novedia.talentmap.model.entity.CredentialToken)}.
	 */
	@Test
	public void checkUserWhenToExist() {	
	//Given
		
		authorization.setLabel("Consultant");
		authorization.setRoleId(4);
		
		credantialActual.setLogin("v.dibi");
		credantialActual.setPassword("v.dibi");
		
		authenticationActual.setId(1);
		authenticationActual.setColleagueId(35);
		authenticationActual.setAuthorization(authorization);
		authenticationActual.setToken(credantialActual);
		
		
					
	//When
		Mockito.when(authenticationDaoMock.check(credantialActual)).thenReturn(authenticationActual);
		Authentication authExpected = service.checkUser(credantialActual);
		
	//Then	
		Mockito.verify(authenticationDaoMock, Mockito.times(1)).check(credantialActual);		
		assertNotNull(credantialActual);
		assertSame(authExpected, authenticationActual);
	}

//	/**
//	 * TODO : car à quel moment doit ton enregistrer un login et password d'un collaborateur???
//	 */
//	@Test
//	public void checkUserWhenNotExist(){
//		
//		//Given
//		authorization.setLabel("Consultant");
//		authorization.setRoleId(4);
//		
//		credantialActual.setLogin("d.julien");
//		credantialActual.setPassword("d.julien");
//		
//		authenticationActual.setId(2);
//		authenticationActual.setColleagueId(24);
//		authenticationActual.setAuthorization(authorization);
//		authenticationActual.setToken(credantialActual);
//		
//		int addAuth = authenticationDaoMock.add(authenticationActual);
//		
//		//When
//		
//		
//		
//		//Then
//	}
}