package com.novedia.talentmap.services.impl;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.ColleagueDao;
import com.novedia.talentmap.store.impl.AuthenticationDao;
import com.novedia.talentmap.store.impl.ProfileDao;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {
	
	private RegistrationService service;
	
	@Mock
	private ColleagueDao colleagueDaoMock;
	
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
	
	@Test
	public void checkRegistrationReturnAColleague(){
		// Given
		Colleague collaborator = new Colleague();
		collaborator.setFirstName("toto");
		collaborator.setId(1);
		Registration registration  = new Registration();
		

		// When
		Mockito.when(colleagueDaoMock.check(registration)).thenReturn(collaborator);
		Colleague result = service.check(registration);

		// Then
		Assert.assertNotNull(result);
		assertSame(result, collaborator);
	}
	
	
	@Test
	public void getAllConsultantManagerReturnAListOfColleagues(){
		// Given
		Colleague collaborator = new Colleague();
		collaborator.setFirstName("toto");
		collaborator.setId(1);
		
		List<Colleague> expectedCollaboratorsList = new ArrayList<Colleague>();
		expectedCollaboratorsList.add(collaborator);

		// When
		Mockito.when(colleagueDaoMock.getAllConsultantManager()).thenReturn(expectedCollaboratorsList);
		List<Colleague> collaborators = service.getAllConsultantManager();

		// Then
		Assert.assertNotNull(collaborators);
		assertSame(expectedCollaboratorsList, collaborators);
	}
	
	@Test
	public void getAllBusinessEngineerReturnAListOfColleagues(){
		// Given
		Colleague collaborator = new Colleague();
		collaborator.setFirstName("toto");
		collaborator.setId(1);
		
		List<Colleague> expectedCollaboratorsList = new ArrayList<Colleague>();
		expectedCollaboratorsList.add(collaborator);

		// When
		Mockito.when(colleagueDaoMock.getAllBusinessEngineer()).thenReturn(expectedCollaboratorsList);
		List<Colleague> collaborators = service.getAllBusinessEngineer();

		// Then
		Assert.assertNotNull(collaborators);
		assertSame(expectedCollaboratorsList, collaborators);
	}
	
	@Test
	public void getAllProfileReturnAListOfProfile(){
		// Given
		Profile profile = new Profile();
		profile.setType("Développeur");
		profile.setId(1);
		
		List<Profile> expectedProfilesList = new ArrayList<Profile>();
		expectedProfilesList.add(profile);

		// When
		Mockito.when(profileDaoMock.getAll()).thenReturn(expectedProfilesList);
		List<Profile> profiles = service.getAllProfile();

		// Then
		Assert.assertNotNull(profiles);
		assertSame(expectedProfilesList, profiles);
	}
	
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
	
}