/**
 * 
 */
package com.novedia.talentmap.services.impl;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.store.IDao;

/**
 * @author v.dibi
 * @author v.guillemain
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthentificationServiceTest {

	private AuthenticationService service;

	@Mock
	private IDao<Authentication> authenticationDaoMock;
	
	@Mock
	private CredentialToken credantialMock;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new AuthenticationService();
		service.setAuthenticationDao(authenticationDaoMock);
	}

	@Ignore
	public void testSaveAuthentification() {

	}

	@Ignore
	public void testSaveAuthentificationWhenAuthentificationIsNotExist() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test(expected = DataAccessException.class)
	public void testSaveAuthentificationThrowsDataAccessException()
			throws DataAccessException {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.novedia.talentmap.services.impl.AuthenticationService#getAuthentification(java.lang.Integer)}
	 * .
	 */
	@Ignore
	public void testGetAuthentification() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.novedia.talentmap.services.impl.AuthenticationService#getAllAuthentification()}
	 * .
	 */
	@Ignore
	public void testGetAllAuthentification() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.novedia.talentmap.services.impl.AuthenticationService#checkAuthentification(java.lang.String, java.lang.String)}
	 * .
	 */
	@Ignore
	public void testCheckAuthentification() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.novedia.talentmap.services.impl.AuthenticationService#getCollaboratorByLogin(java.lang.String)}
	 * .
	 */
	@Ignore
	public void testGetCollaboratorByLogin() {
		fail("Not yet implemented");
	}

}
