package com.novedia.talentmap.services.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import javax.naming.LinkRef;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.store.impl.AuthenticationDao;
import com.novedia.talentmap.store.impl.BusinessEngineerDao;
import com.novedia.talentmap.store.impl.ColleagueDao;
import com.novedia.talentmap.store.impl.ProfileDao;

/**
 * 
 * @author y.rohr
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {
	
	private RegistrationService service;
	
	@Mock
	private ColleagueDao colleagueDaoMock;

	@Mock
	private BusinessEngineerDao businessEngineerDaoMock;

	@Mock
	private ProfileDao profileDaoMock;
	
	@Mock
	private AuthenticationDao authenticationDaoMock;
	
	@Before
	public void setUp() throws Exception {
		service = new RegistrationService();
		service.setColleagueDao(colleagueDaoMock);
		service.setProfileDao(profileDaoMock);
		service.setAuthenticationDao(authenticationDaoMock);
	}
	
	/**
	 * 
	 */
	@Test
	public void getByMailReturnAColleague(){
		
		// Given
		Colleague collaborator = Colleague.builder().id(1).firstName("toto").build();
		
		// TODO : rendre les instances de registration immutables
		Registration registration  = new Registration();
		registration.setEmail("mail@gmail.com");
				
		// When
		Mockito.when(colleagueDaoMock.getByMail(registration.getEmail())).thenReturn(collaborator);
		Colleague result = service.getByMail(registration.getEmail());

		// Then
		Assert.assertNotNull(result);
		assertSame(result, collaborator);
	}
	
	/**
	 * 
	 */
	@Test
	public void addColleagueFromRegistrationCallsOneTime() {
		
		// Given
		Registration registration  = new Registration();		
		// When
		Mockito.when(colleagueDaoMock.addColleagueFromRegistration(Mockito.any(Registration.class))).thenReturn(1);
		service.addColleagueFromRegistration(registration);
	
		// Then
		Mockito.verify(colleagueDaoMock, Mockito.times(1)).addColleagueFromRegistration(registration);		
	}

	/**
	 * Test the add user registration
	 */
	@Test
	public void addUserFromRegistrationCallsOneTime() {
		
		// Given
		Registration registration  = new Registration();		
		// When
		Mockito.when(authenticationDaoMock.addUserFromRegistration(Mockito.any(Registration.class))).thenReturn(1);
		service.addUserFromRegistration(registration);
	
		// Then
		Mockito.verify(authenticationDaoMock, Mockito.times(1)).addUserFromRegistration(registration);		
	}

	/**
	 * 
	 */
	@Test
	public void getAllProfileReturnAListOfProfile(){
		
		// Given
		Profile profile = Profile.builder().id(1).type("Développeur").build();
		
		List<Profile> expectedProfilesList = new ArrayList<Profile>();
		expectedProfilesList.add(profile);
	
		// When
		Mockito.when(profileDaoMock.getAll()).thenReturn(expectedProfilesList);
		List<Profile> profiles = service.getAllProfile();
	
		// Then
		Assert.assertNotNull(profiles);
		assertSame(expectedProfilesList, profiles);
	}

	/**
	 * 
	 */
	@Test
	public void getAllConsultantManagerReturnAListOfColleagues(){
		
		// Given
		Colleague collaborator = Colleague.builder().id(1).firstName("toto").build();
		
		List<Colleague> expectedCollaboratorsList = new ArrayList<Colleague>();
		expectedCollaboratorsList.add(collaborator);

		// When
		Mockito.when(colleagueDaoMock.getAllConsultantManager()).thenReturn(expectedCollaboratorsList);
		List<Colleague> collaborators = service.getAllConsultantManager();

		// Then
		Assert.assertNotNull(collaborators);
		assertSame(expectedCollaboratorsList, collaborators);
	}
	
	/**
	 * Test de countLogin(String login)
	 * {@link com.novedia.talentmap.services.impl.RegistrationService#countLogin(String)}
	 */
	public void countLoginReturnsInteger() {
		//Given
		String login ="login";
		Integer expected = 1;
		
		//When
		Mockito.when(authenticationDaoMock.countLogin(login)).thenReturn(1);
		Integer result = service.countLogin(login);
		
		//Then
		assertSame(expected, result);
	}
}