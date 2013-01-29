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

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.ManagerDao;

/**
 * Manager service test
 * {@link ManagerService}
 * 
 * @author v.guillemain
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ManagerServiceTest {
	
	private ManagerService service;

	@Mock
	private ManagerDao managerDaoMock;
	
	@Mock
	private  IDao<Colleague> colleagueDaoMock;
	
	private final String DATA_ACCESS_ERROR_MESSAGE = "Data Access Failure";
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new ManagerService();
		service.setManagerDao(managerDaoMock);
		service.setColleagueDao(colleagueDaoMock);
	}

	@Test
	public void getAllCollaboratorsByManagerIdReturnsAListOfCollaborators() {
		
		//Given
		Integer managerId = 1;
		Colleague collaborator = Colleague.builder().managerId(managerId).build();
		
		List<Colleague> listCollaboratorRequested = new ArrayList<Colleague>();
		listCollaboratorRequested.add(collaborator);
		
		//When
		Mockito.when(managerDaoMock.getAllCollaborators(managerId)).thenReturn(listCollaboratorRequested);
		List<Colleague> listCollaboratorActual = service.getAllColleagues(managerId);
		
		//Then
		Mockito.verify(managerDaoMock, Mockito.times(1)).getAllCollaborators(managerId);
		Assert.assertNotNull(listCollaboratorActual);
		Assert.assertSame(listCollaboratorRequested, listCollaboratorActual);
	}
	
	@Test (expected=DataAccessException.class)
	public void getAllCollaboratorsByManagerIdThrowsDataAccessException() throws DataAccessException {
		//Given
		Integer managerId = 1;
		
		//When
		Mockito.when(managerDaoMock.getAllCollaborators(managerId)).thenThrow(new DataAccessResourceFailureException(DATA_ACCESS_ERROR_MESSAGE));
		service.getAllColleagues(managerId);
		
	}
	
	 @Test
	public void getCollaboratorReturnsACollaborator() {
		 
		 //Given
		 Integer colleague = 1;
		 Colleague collaboratorExpected = Colleague.builder().id(colleague).build();
		 
		 //When
		 Mockito.when(colleagueDaoMock.get(colleague)).thenReturn(collaboratorExpected);
		 Colleague collaboratorActual = service.getCollaborator(colleague);
		
		 //Test
		 Mockito.verify(colleagueDaoMock, Mockito.times(1)).get(colleague);
		 Assert.assertNotNull(collaboratorActual);
		 Assert.assertSame(collaboratorExpected, collaboratorActual);
		 
	}
	 
	 @Test(expected=DataAccessException.class)
	public void getCollaboratorThrowsDataAccessException () throws DataAccessException {
		
		 //Given
		 Integer collaboratorId = 1;

		 //When
		 Mockito.when(colleagueDaoMock.get(collaboratorId)).thenThrow(new DataAccessResourceFailureException(DATA_ACCESS_ERROR_MESSAGE));
		 service.getCollaborator(collaboratorId);
		 
	} 
}
