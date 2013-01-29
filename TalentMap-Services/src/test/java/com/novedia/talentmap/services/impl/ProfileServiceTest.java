/**
 * 
 */
package com.novedia.talentmap.services.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;

import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.store.IDao;

/**
 * Profile service test.
 * 
 * @author v.guillemain
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceTest {

	private ProfileService service;
	
	@Mock
	private IDao<Profile> profileDaoMock;

	private final String DATA_ACCESS_ERROR_MESSAGE = "Data Access Failure";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new ProfileService();
		service.setProfileDao(profileDaoMock);
	}
	

	@Test
	public void getAllProfileReturnsAListOfProfiles() {
		//Given
		Profile profileExpected = new Profile();
		List<Profile> listProfileExpected = new ArrayList<Profile>();
		listProfileExpected.add(profileExpected);
		
		//When
		Mockito.when(profileDaoMock.getAll()).thenReturn(listProfileExpected);
		List<Profile> listProfileActual = service.getAllProfile();
		
		//Then
		Mockito.verify(profileDaoMock, Mockito.times(1)).getAll();
		Assert.assertNotNull(listProfileActual);
		Assert.assertSame(listProfileExpected, listProfileActual);
		
	}

	@Test(expected=DataAccessException.class)
	public void getAllProfileThrowsDataAccessException() throws DataAccessException {
		//When
		Mockito.when(profileDaoMock.getAll()).thenThrow(new DataAccessResourceFailureException(DATA_ACCESS_ERROR_MESSAGE));
		service.getAllProfile();
		
	}
	
	@Test
	public void getProfileByIdReturnsProfileWithRightId() {
		
		//Given
		Integer profileId = 1;
		Profile profileExpected = Profile.builder().id(profileId).build();
		
		//When
		Mockito.when(profileDaoMock.get(profileId)).thenReturn(profileExpected);
		Profile profileActual = service.getProfile(profileId);
		
		//Then
		Mockito.verify(profileDaoMock, Mockito.times(1)).get(profileId);
		Assert.assertNotNull(profileActual);
		Assert.assertSame(profileExpected, profileActual);
	}
	
	@Test(expected=DataAccessException.class)
	public void getProfileThrowsDataAccessException() throws DataAccessException{
		//Given
		Integer profileId = 1;
		
		//When
		Mockito.when(profileDaoMock.get(profileId)).thenThrow(new DataAccessResourceFailureException(DATA_ACCESS_ERROR_MESSAGE));
		service.getProfile(profileId);
	}
}
