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
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.store.IDao;

@RunWith(MockitoJUnitRunner.class)
public class ColleagueServiceTest {
	
	private ColleagueService service;
	
	@Mock
	private IDao<Colleague> colleagueDaoMock;
	
	@Mock
	private IDao<Mission> missionDaoMock;
	
	@Before
	public void setUp() throws Exception {
		service = new ColleagueService();
		service.setColleagueDao(colleagueDaoMock);
		service.setMissionDao(missionDaoMock);
	}

	@Test
	public void getAllCollaboratorReturnAListOfCollaborators(){
		// Given
		Colleague collaborator = Colleague.builder().id(1).firstName("toto").build();
		
		List<Colleague> expectedCollaboratorsList = new ArrayList<Colleague>();
		expectedCollaboratorsList.add(collaborator);

		// When
		Mockito.when(colleagueDaoMock.getAll()).thenReturn(expectedCollaboratorsList);
		List<Colleague> collaborators = service.getAllColleagues();

		// Then
		Assert.assertNotNull(collaborators);
		assertSame(expectedCollaboratorsList, collaborators);
	}

	@Test(expected = DataAccessException.class)
	public void getAllCollaboratorsThrowsDataAccessException() throws DataAccessException {

		// When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(colleagueDaoMock).getAll();
		service.getAllColleagues();

	}
	
	@Test
	public void getCollaboratorReturnsACollaborator() {
		// Given
		Integer colleagueId = 1;
		Colleague collaboratorExpected = Colleague.builder().id(colleagueId).firstName("toto").build();

		// When
		Mockito.when(colleagueDaoMock.get(colleagueId)).thenReturn(collaboratorExpected);
		Colleague collaboratorActual = service.getColleague(colleagueId);

		// Then
		Assert.assertNotNull(collaboratorActual);
		assertSame(collaboratorExpected, collaboratorActual);
	}
	
	@Test(expected = DataAccessException.class)
	public void getCollaboratorThrowsDataAccessException() throws DataAccessException {
		
		// When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(colleagueDaoMock).get(Mockito.anyInt());
		service.getColleague(1);
	}
	
	@Test
	public void saveCollaboratorCallsSaveOneTime() {
		
		// Given
		Colleague collaborator  = new Colleague();		
		// When
		Mockito.when(colleagueDaoMock.save(Mockito.any(Colleague.class))).thenReturn(1);
		service.saveColleague(collaborator);

		// Then
		Mockito.verify(colleagueDaoMock, Mockito.times(1)).save(collaborator);		
	}
	
	@Test(expected = DataAccessException.class)
	public void saveCollaboratorThrowsDataAccessException() throws DataAccessException {
		// Given
				Colleague collaborator  = new Colleague();	
		// When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(colleagueDaoMock).save(Mockito.any(Colleague.class));
		service.saveColleague(collaborator);
	}

	@Test
	public void deleteMissionCallsDeleteOneTime(){
		
		//When
		Mockito.when(missionDaoMock.delete(Mockito.any(Mission.class))).thenReturn(1);
		service.deleteMission(new Mission());
		
		//Then
		Mockito.verify(missionDaoMock, Mockito.times(1)).delete(Mockito.any(Mission.class));		
	}
	
	@Test(expected = DataAccessException.class)
	public void deleteMissionThrowsDataAccessException() throws DataAccessException {
		// When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(missionDaoMock).delete(Mockito.any(Mission.class));
		service.deleteMission(new Mission());
	}
}