/**
 * 
 */
package com.novedia.talentmap.services.impl;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Authentification;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.store.IDao;

/**
 * @author v.dibi
 * @author v.guillemain
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthentificationServiceTest {

	private AuthentificationService service;

	@Mock
	private IDao<Authentification> authentificationDaoMock;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new AuthentificationService();
		service.setAuthentificationDao(authentificationDaoMock);
	}

	/**
	 * If authentification exist
	 */
	@Ignore
	public void testAddAuthentificationWhenAuthentificationExist() {
	//Given
		//Authentification auth = Authentification.Builder.builder().login("v.dibi").password("toocool").idCollab(1).id(1).build();
		Authentification auth = new Authentification();
		Integer returnExpected = -1;	
	//when
		Mockito.when(authentificationDaoMock.add(auth)).thenReturn(returnExpected);
		Integer returnActual = service.addAuthentification(auth);				
	//Then
		Assert.assertEquals(returnExpected, returnActual);
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
	 * {@link com.novedia.talentmap.services.impl.AuthentificationService#getAuthentification(java.lang.Integer)}
	 * .
	 */
	@Ignore
	public void testGetAuthentification() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.novedia.talentmap.services.impl.AuthentificationService#getAllAuthentification()}
	 * .
	 */
	@Ignore
	public void testGetAllAuthentification() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.novedia.talentmap.services.impl.AuthentificationService#checkAuthentification(java.lang.String, java.lang.String)}
	 * .
	 */
	@Ignore
	public void testCheckAuthentification() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.novedia.talentmap.services.impl.AuthentificationService#getCollaboratorByLogin(java.lang.String)}
	 * .
	 */
	@Ignore
	public void testGetCollaboratorByLogin() {
		fail("Not yet implemented");
	}

}
