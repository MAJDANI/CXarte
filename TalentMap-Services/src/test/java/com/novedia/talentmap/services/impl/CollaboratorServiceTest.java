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

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.store.IDao;

@RunWith(MockitoJUnitRunner.class)
public class CollaboratorServiceTest {
	
	private CollaboratorService service;
	
	@Mock
	private IDao<Collaborator> collaboratorDaoMock;
	
	@Mock
	private IDao<Mission> missionDaoMock;
	
	@Before
	public void setUp() throws Exception {
		service = new CollaboratorService();
		service.setCollaboratorDao(collaboratorDaoMock);
		service.setMissionDao(missionDaoMock);
	}

	@Test
	public void getAllCollaboratorReturnAListOfCollaborators(){
		// Given
		Collaborator collaborator = new Collaborator();
		collaborator.setFirst_name("toto");
		collaborator.setId(1);
		
		List<Collaborator> expectedCollaboratorsList = new ArrayList<Collaborator>();
		expectedCollaboratorsList.add(collaborator);

		// When
		Mockito.when(collaboratorDaoMock.getAll()).thenReturn(expectedCollaboratorsList);
		List<Collaborator> collaborators = service.getAllCollaborator();

		// Then
		Assert.assertNotNull(collaborators);
		assertSame(expectedCollaboratorsList, collaborators);
	}

	@Test(expected = DataAccessException.class)
	public void getAllCollaboratorsThrowsDataAccessException() throws DataAccessException {

		// When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(collaboratorDaoMock).getAll();
		service.getAllCollaborator();

	}
	
	@Test
	public void getCollaboratorReturnsACollaborator() {
		// Given
		Integer collaboratorId = 1;
		Collaborator collaboratorExpected = new Collaborator();
		collaboratorExpected.setFirst_name("toto");
		collaboratorExpected.setId(collaboratorId);

		// When
		Mockito.when(collaboratorDaoMock.get(collaboratorId)).thenReturn(collaboratorExpected);
		Collaborator collaboratorActual = service.getCollaborator(collaboratorId);

		// Then
		Assert.assertNotNull(collaboratorActual);
		assertSame(collaboratorExpected, collaboratorActual);
	}
	
	@Test(expected = DataAccessException.class)
	public void getCollaboratorThrowsDataAccessException() throws DataAccessException {
		
		// When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(collaboratorDaoMock).get(Mockito.anyInt());
		service.getCollaborator(1);

	}
	
	@Test
	public void saveCollaboratorCallsSaveOneTime() {
		
		// Given
		Collaborator collaborator  = new Collaborator();		
		// When
		Mockito.when(collaboratorDaoMock.save(Mockito.any(Collaborator.class))).thenReturn(1);
		service.saveCollaborator(collaborator);

		// Then
		Mockito.verify(collaboratorDaoMock, Mockito.times(1)).save(collaborator);		
	}
	
	@Test(expected = DataAccessException.class)
	public void saveCollaboratorThrowsDataAccessException() throws DataAccessException {
		// Given
				Collaborator collaborator  = new Collaborator();	
		// When
		Mockito.doThrow(new DataAccessResourceFailureException("Resource failure")).when(collaboratorDaoMock).save(Mockito.any(Collaborator.class));
		service.saveCollaborator(collaborator);
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