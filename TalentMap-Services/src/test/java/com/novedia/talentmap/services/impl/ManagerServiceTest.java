/**
 * 
 */
package com.novedia.talentmap.services.impl;

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
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.ManagerDao;

/**
 * @author v.guillemain
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ManagerServiceTest {

	private ManagerService service;

	@Mock
	private ManagerDao managerDaoMock;
	
	@Mock
	private  IDao<Collaborator> collaboratorDaoMock;
	
	private final String DATA_ACCESS_ERROR_MESSAGE = "Data Access Failure";
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new ManagerService();
		service.setManagerDao(managerDaoMock);
		service.setCollaboratorDao(collaboratorDaoMock);
	}

	@Test
	public void getAllCollaboratorsByManagerIdReturnsAListOfCollaborators() {
		//Given
		Integer managerId = 1;
		Collaborator collaborator = new Collaborator();
		collaborator.setManagerId(managerId);
		
		List<Collaborator> listCollaboratorRequested = new ArrayList<Collaborator>();
		listCollaboratorRequested.add(collaborator);
		
		//When
		Mockito.when(managerDaoMock.getAllCollaboratorsByManagerId(managerId)).thenReturn(listCollaboratorRequested);
		List<Collaborator> listCollaboratorActual = service.getAllCollaboratorsByManagerId(managerId);
		
		//Then
		Mockito.verify(managerDaoMock, Mockito.times(1)).getAllCollaboratorsByManagerId(managerId);
		Assert.assertNotNull(listCollaboratorActual);
		Assert.assertSame(listCollaboratorRequested, listCollaboratorActual);
		
	}
	
	@Test (expected=DataAccessException.class)
	public void getAllCollaboratorsByManagerIdThrowsDataAccessException() throws DataAccessException {
		//Given
		Integer managerId = 1;
		
		//When
		Mockito.when(managerDaoMock.getAllCollaboratorsByManagerId(managerId)).thenThrow(new DataAccessResourceFailureException(DATA_ACCESS_ERROR_MESSAGE));
		service.getAllCollaboratorsByManagerId(managerId);
		
	}
	
	 @Test
	public void getCollaboratorReturnsACollaborator() {
		 //Given
		 Integer collaboratorId = 1;
		 Collaborator collaboratorExpected = new Collaborator();
		 collaboratorExpected.setId(collaboratorId);
		 
		 //When
		 Mockito.when(collaboratorDaoMock.get(collaboratorId)).thenReturn(collaboratorExpected);
		 Collaborator collaboratorActual = service.getCollaborator(collaboratorId);
		
		 //Test
		 Mockito.verify(collaboratorDaoMock, Mockito.times(1)).get(collaboratorId);
		 Assert.assertNotNull(collaboratorActual);
		 Assert.assertSame(collaboratorExpected, collaboratorActual);
		 
	}
	 
	 @Test(expected=DataAccessException.class)
	public void getCollaboratorThrowsDataAccessException () throws DataAccessException {
		//Given
		 Integer collaboratorId = 1;

		 //When
		 Mockito.when(collaboratorDaoMock.get(collaboratorId)).thenThrow(new DataAccessResourceFailureException(DATA_ACCESS_ERROR_MESSAGE));
		 service.getCollaborator(collaboratorId);
		 
	} 
}
