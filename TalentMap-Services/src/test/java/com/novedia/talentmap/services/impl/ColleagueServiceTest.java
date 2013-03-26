package com.novedia.talentmap.services.impl;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.ColleagueDao;
import com.novedia.talentmap.store.impl.MissionDao;

/**
 * Colleague service test.
 * 
 * @author v.guillemain
 *
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class ColleagueServiceTest {
	
	private static final String COLLEAGUE_LAST_NAME = "toto";

	/**
	 * The service tested
	 */
	private ColleagueService service;
	
	/**
	 * For services concerning Colleagues, ColleagueService calls an IDao<Colleague> in store layer. This object is mocked here. 
	 */
	@Mock
	private IDao<Colleague> colleagueDaoMock;

	/**
	 * For services extended concerning Colleagues, ColleagueService calls a ColleagueDao in store layer. This object is mocked here. 
	 */
	@Mock
	private ColleagueDao colleagueExtendedDaoMock;

	/**
	 * For services concerning Missions, ColleagueService calls an IDao<Mission> in store layer. This object is mocked here. 
	 */
	@Mock
	private IDao<Mission> missionDaoMock;

	/**
	 * For services extended concerning Missions, ColleagueService calls a MissionDao in store layer. This object is mocked here. 
	 */
	@Mock
	private MissionDao missionDaoExtendedMock;
	
	/**
	 * For services concerning Manager, ColleagueService calls an IDao<Manager> in store layer. This object is mocked here. 
	 */
	@Mock
	private IDao<Manager> managerDaoMock;

	
	/**
	 * Initializations
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new ColleagueService();
		service.setColleagueDao(colleagueDaoMock);
		service.setMissionDao(missionDaoMock);
		service.setManagerDao(managerDaoMock);
	}

	/**
	 * Method tested : getAllColleagues(). {@link ColleagueService#getAllColleagues()}
	 * Result tested : the method returns the colleague's list expected
	 */
	@Test
	public void getAllColleaguesReturnAListOfColleagues(){

		//Given
		Integer colleagueForTestId = 1;
		Colleague colleagueForTests = Colleague.builder().id(colleagueForTestId).firstName(COLLEAGUE_LAST_NAME).build();
		List<Colleague> expectedColleagueList = new ArrayList<Colleague>();
		expectedColleagueList.add(colleagueForTests);

		// When
		Mockito.when(colleagueDaoMock.getAll()).thenReturn(expectedColleagueList);
		List<Colleague> actualColleagueList = service.getAllColleagues();

		// Then
		Assert.assertNotNull(actualColleagueList);
		Assert.assertEquals(expectedColleagueList, actualColleagueList);
	}

	/**
	 * Method tested : getColleague(Integer colleagueId). {@link ColleagueService#getColleague(Integer)}
	 * Result tested : the method returns the colleague expected 
	 */
	@Test
	public void getColleagueReturnsAColleague() {

		//Given
		Integer colleagueForTestId = 1;
		Colleague colleagueForTests = Colleague.builder().id(colleagueForTestId).firstName(COLLEAGUE_LAST_NAME).build();

		// When
		Mockito.when(colleagueDaoMock.get(colleagueForTestId)).thenReturn(colleagueForTests);
		Colleague colleagueActual = service.getColleague(colleagueForTestId);

		// Then
		Assert.assertNotNull(colleagueActual);
		Assert.assertEquals(colleagueForTests, colleagueActual);
	}
	
	/**
	 * Method tested : saveColleague(Colleague colleague). {@link ColleagueService#saveColleague(Colleague)}
	 * Result tested : the method save in the Dao is called once  
	 */
	@Test
	public void saveColleagueCallsSaveOneTime() {
		
		//Given
		Integer colleagueForTestId = 1;
		Colleague colleagueForTests;
		colleagueForTests = Colleague.builder().id(colleagueForTestId).firstName(COLLEAGUE_LAST_NAME).build();

		// When
		Mockito.when(colleagueDaoMock.save(Mockito.any(Colleague.class))).thenReturn(1);
		service.saveColleague(colleagueForTests);

		// Then
		Mockito.verify(colleagueDaoMock, Mockito.times(1)).save(colleagueForTests);		
	}
	
	/**
	 * Method tested : deleteMission(Mission mission). {@link ColleagueService#deleteMission(Mission)}
	 * Result tested : the method delete in the Dao is called once  
	 */
	@Test
	public void deleteMissionCallsDeleteOneTime(){

		//Given
		Integer missionForTestsId = 1;
		Mission missionForTests;
		missionForTests = Mission.builder().build();

		//When
		Mockito.when(missionDaoMock.delete(Mockito.any(Mission.class))).thenReturn(missionForTestsId);
		service.deleteMission(missionForTests);
		
		//Then
		Mockito.verify(missionDaoMock, Mockito.times(1)).delete(Mockito.any(Mission.class));		
	}
	
	/**
	 * Method tested : addMission(Mission mission). {@link ColleagueService#addMission(Mission)}
	 * Result tested : 	- the Dao is called once
	 * 					- the missionId returned is correct.  
	 */
	@Test
	public void addMissionReturnsACorrectId(){
//		
//		//Given
//		Integer missionForTestsId = 1;
//		Mission missionForTests = Mission.builder().build();
//		
//		
//		
//		//When
//		Mockito.when(missionDaoMock.add(missionForTests)).thenReturn(missionForTestsId);
//		Integer missionIdactual = service.addMission(missionForTests);
//		
//		//Then
//		Mockito.verify(missionDaoMock, Mockito.times(1)).add(missionForTests);		
//		Assert.assertEquals(missionIdactual, missionForTestsId);
	}
	
	/**
	 * Method tested : saveMission(Mission mission). {@link ColleagueService#saveMission(Mission)}
	 * Result tested : 	- the Dao is called once
	 * 					- the missionId returned is correct.  
	 */
	@Test
	public void saveMissionReturnsACorrectId() {
//
//		//Given
//		Integer missionForTestsId = 1;
//		Mission missionForTests = Mission.builder().build();
//
//		//When
//		Mockito.when(missionDaoMock.save(missionForTests)).thenReturn(missionForTestsId);
//		Integer missionIdactual = service.saveMission(missionForTests);
//		
//		//Then
//		Mockito.verify(missionDaoMock, Mockito.times(1)).save(missionForTests);		
//		Assert.assertEquals(missionIdactual, missionForTestsId);
//		
	}

	/**
	 * Method tested : getMission(Integer missionId).  {@link ColleagueService#getMission(Integer)}
	 * Result tested : 	- the Dao is called once
	 * 					- the mission returned is correct.  
	 */
	@Test
	public void getMissionReturnsACorrectMission() {

		//Given
		Integer missionForTestsId = 1;
		Mission missionForTests = Mission.builder().id(missionForTestsId).build();

		//When
		Mockito.when(missionDaoMock.get(missionForTestsId)).thenReturn(missionForTests);
		Mission missionActual = service.getMission(missionForTestsId);
		
		//Then
		Mockito.verify(missionDaoMock, Mockito.times(1)).get(missionForTestsId);		
		Assert.assertEquals(missionActual, missionForTests);
		
	}
	
	/**
	 * Method tested : getAllMissions(Integer colleagueId). {@link ColleagueService#getAllMissions(Integer)}
	 * Result tested : 	- the list returned is not null
	 * 					- the list of missions returned is correct.  
	 */
	@Test
	public void getAllMissionsReturnsACorrectListOfMissions() {

		//Given
		Mission missionForTests = Mission.builder().build();

		//Given
		List<Mission> expectedListMission = new ArrayList<Mission>();
		expectedListMission.add(missionForTests);
		Integer colleagueForTestId = 1;
		
		//Changement de mock Dao
		service.setMissionDao(missionDaoExtendedMock);

		//When
		MissionDao missionDao = (MissionDao) this.missionDaoExtendedMock;
		Mockito.when(missionDao.getAllByColleagueId(colleagueForTestId)).thenReturn(expectedListMission);
		List<Mission> actualListMission = service.getAllMissions(colleagueForTestId);
		
		//Then
		Assert.assertNotNull(actualListMission);
		Assert.assertEquals(actualListMission, expectedListMission);

		//Changement de mock Dao
		service.setMissionDao(missionDaoMock);
	}
	
	/**
	 * Method tested : getManager(Integer managerId). {@link ColleagueService#getManager(Integer)}
	 * Result tested : 	- the Dao is called once
	 * 					- the manager returned is correct.  
	 */
	@Test
	public void getManagerReturnsACorrectManager() {

		//Given
		Integer managerForTestsId = 1;
		Manager managerForTests = Manager.builder().build();

		//When
		Mockito.when(managerDaoMock.get(managerForTestsId)).thenReturn(managerForTests);
		Manager managerActual = service.getManager(managerForTestsId);
		
		//Then
		Mockito.verify(managerDaoMock, Mockito.times(1)).get(managerForTestsId);		
		Assert.assertEquals(managerActual, managerForTests);
		
	}
	
	/**
	 * Method tested : getAllColleaguesByLastName(String lastName). {@link ColleagueService#getAllColleaguesByLastName(String)}
	 * Result tested : 	- the Dao is called once
	 * 					- the list of colleague returned is correct.  
	 */
	@Test
	public void getAllColleaguesByLastNameReturnsACorrectListOfColleagues() {

		//Given
		Integer colleagueForTestId = 1;
		Colleague colleagueForTests = Colleague.builder().id(colleagueForTestId).firstName(COLLEAGUE_LAST_NAME).build();
		List<Colleague> expectedColleagueList = new ArrayList<Colleague>();
		expectedColleagueList.add(colleagueForTests);
		
		//Changement de mock Dao
		service.setColleagueDao(colleagueExtendedDaoMock);

		//When
		ColleagueDao colleagueDao = (ColleagueDao) this.colleagueExtendedDaoMock;
		Mockito.when(colleagueDao.getAllColleaguesByLastName(COLLEAGUE_LAST_NAME)).thenReturn(expectedColleagueList);
		List<Colleague> actualColleagueList = service.getAllColleaguesByLastName(COLLEAGUE_LAST_NAME);
		
		//Then
		Mockito.verify(colleagueDao, Mockito.times(1)).getAllColleaguesByLastName(COLLEAGUE_LAST_NAME);		
		Assert.assertEquals(actualColleagueList, expectedColleagueList);

		//Changement de mock Dao
		service.setColleagueDao(colleagueDaoMock);
	}
	
	/**
	 * Method tested : getAllColleaguesByClient(Client client). {@link ColleagueService#getAllColleaguesByClient(Client)}
	 * Result tested : 	- the Dao is called once
	 * 					- the list of colleague returned is correct.  
	 */
	@Test
	public void getAllColleaguesByClientReturnsACorrectListOfColleague() {
		
		//Given
		Client client = Client.builder().id(1).name("ClientName").build();
		Integer colleagueForTestId = 1;
		Colleague colleagueForTests = Colleague.builder().id(colleagueForTestId).firstName(COLLEAGUE_LAST_NAME).build();
		List<Colleague> expectedColleagueList = new ArrayList<Colleague>();
		expectedColleagueList.add(colleagueForTests);
		
		//Changement de mock Dao
		service.setColleagueDao(colleagueExtendedDaoMock);

		//When
		ColleagueDao colleagueDao = (ColleagueDao) this.colleagueExtendedDaoMock;
		Mockito.when(colleagueDao.getAllColleaguesByClient(client)).thenReturn(expectedColleagueList);
		List<Colleague> actualColleagueList = service.getAllColleaguesByClient(client);
		
		//Then
		Mockito.verify(colleagueDao, Mockito.times(1)).getAllColleaguesByClient(client);		
		Assert.assertEquals(actualColleagueList, expectedColleagueList);
		
		//Changement de mock Dao
		service.setColleagueDao(colleagueDaoMock);
	
	}
	
	/**
	 * Method tested : getAllColleaguesByToolId(Integer toolId). {@link ColleagueService#getAllCollaboratorsByToolId(Integer)}
	 * Result tested :    
	 */
	@Ignore
	public void getAllColleaguesByToolIdReturnsACorrectListOfColleague() {
		fail("Not yet implemented");
	}
	
	/**
	 * Method tested : getAllColleaguesByListToolId(Map toolIdMap). {@link ColleagueService#getAllColleaguesByListToolId(java.util.Map)}
	 * Result tested : 	- the Dao is called once
	 * 					- the list of colleague returned is correct.  
	 */
	@Test
	public void getAllColleaguesByListToolIdReturnsACorrectListOfColleagues() {
		
		//Given
		List<Integer> listToolId = new ArrayList<Integer>();
		List<Integer> listSize = new ArrayList<Integer>();
		Hashtable<String,List<Integer>> mapToolId = new Hashtable<String,List<Integer>>();
		mapToolId.put("listSize", listSize);
		mapToolId.put("listId", listToolId);
		Integer colleagueForTestId = 1;
		Colleague colleagueForTests = Colleague.builder().id(colleagueForTestId).firstName(COLLEAGUE_LAST_NAME).build();
		List<Colleague> expectedColleagueList = new ArrayList<Colleague>();
		expectedColleagueList.add(colleagueForTests);

		//Changement de mock Dao
		service.setColleagueDao(colleagueExtendedDaoMock);

		//When
		ColleagueDao colleagueDao = (ColleagueDao) this.colleagueExtendedDaoMock;
		Mockito.when(colleagueDao.getAllColleaguesByListToolId(mapToolId)).thenReturn(expectedColleagueList);
		List<Colleague> actualColleagueList = service.getAllColleaguesByListToolId(mapToolId);
		
		//Then
		Mockito.verify(colleagueDao, Mockito.times(1)).getAllColleaguesByListToolId(mapToolId);		
		Assert.assertEquals(actualColleagueList, expectedColleagueList);
		
		//Changement de mock Dao
		service.setColleagueDao(colleagueDaoMock);
	}
		
	/**
	 * Method tested : getAllColleagues(). {@link ColleagueService#getAllColleagues()}
	 * Result tested : a DataAccessException is caught and treated 
	 */
	@Ignore
	public void getAllColleaguesThrowsDataAccessException() throws DataAccessException {
		//TODO when exceptions will be treated
		fail("Not yet implemented");
	}

	/**
	 * Method tested : getColleague(Integer colleagueId). {@link ColleagueService#getColleague(Integer)}
	 * Result tested : a DataAccessException is caught and treated  
	 */
	@Ignore
	public void getColleagueThrowsDataAccessException() throws DataAccessException {
		//TODO when exceptions will be treated
		fail("Not yet implemented");
	}

	/**
	 * Method tested : saveColleague(Colleague colleague). {@link ColleagueService#saveColleague(Colleague)}
	 * Result tested : a DataAccessException is caught and treated
	 */
	@Ignore
	public void saveColleagueThrowsDataAccessException() {
		//TODO when exceptions will be treated
		fail("Not yet implemented");
	}

	/**
	 * Method tested : deleteMission(Mission mission). {@link ColleagueService#deleteMission(Mission)}
	 * Result tested : a DataAccessException is caught and treated   
	 */
	@Ignore
	public void deleteMissionThrowsDataAccessException() throws DataAccessException {
		//TODO when exceptions will be treated
		fail("Not yet implemented");
	}

	/**
	 * Method tested : addMission(Mission mission). {@link ColleagueService#addMission(Mission)}
	 * Result tested : a DataAccessException is caught and treated    
	 */
	@Ignore
	public void addMissionThrowsDataAccessException(){
		//TODO when exceptions will be treated
		fail("Not yet implemented");
	}

	/**
	 * Method tested : saveMission(Mission mission). {@link ColleagueService#saveMission(Mission)}
	 * Result tested : a DataAccessException is caught and treated    
	 */
	@Ignore
	public void saveMissionThrowsDataAccessException() {
		//TODO when exceptions will be treated
		fail("Not yet implemented");
	}

	/**
	 * Method tested : getMission(Integer missionId). {@link ColleagueService#getMission(Integer)}
	 * Result tested : a DataAccessException is caught and treated    
	 */
	@Ignore
	public void getMissionThrowsDataAccessException() {
		//TODO when exceptions will be treated
		fail("Not yet implemented");
	}

	/**
	 * Method tested : getAllMissions(Integer colleagueId). {@link ColleagueService#getAllMissions(Integer)}
	 * Result tested : a DataAccessException is caught and treated    
	 */
	@Ignore
	public void getAllMissionsThrowsDataAccessException() {
		//TODO when exceptions will be treated
		fail("Not yet implemented");
	}

	/**
	 * Method tested : getManager(Integer managerId). {@link ColleagueService#getManager(Integer)}
	 * Result tested : a DataAccessException is caught and treated    
	 */
	@Ignore
	public void getManagerThrowsDataAccessException() {
		//TODO when exceptions will be treated
		fail("Not yet implemented");
	}

	/**
	 * Method tested : getAllColleaguesByLastName(String lastName). {@link ColleagueService#getAllColleaguesByLastName(String)}
	 * Result tested : a DataAccessException is caught and treated    
	 */
	@Ignore
	public void getAllColleaguesByLastNameThrowsDataAccessException() {
		//TODO when exceptions will be treated
		fail("Not yet implemented");
	}

	/**
	 * Method tested : getAllColleaguesByClient(Client client). {@link ColleagueService#getAllColleaguesByClient(Client)}
	 * Result tested : a DataAccessException is caught and treated    
	 */
	@Ignore
	public void getAllColleaguesByClientThrowsDataAccessException() {
		//TODO when exceptions will be treated
		fail("Not yet implemented");
	}

	/**
	 * Method tested : getAllColleaguesByToolId(Integer toolId). {@link ColleagueService#getAllCollaboratorsByToolId(Integer)}
	 * Result tested : a DataAccessException is caught and treated    
	 */
	@Ignore
	public void getAllColleaguesByToolIdThrowsDataAccessException() {
		//TODO when exceptions will be treated
		fail("Not yet implemented");
	}

	/**
	 * Method tested : getAllColleaguesByListToolId(Map toolIdMap). {@link ColleagueService#getAllColleaguesByListToolId(java.util.Map)}
	 * Result tested : a DataAccessException is caught and treated    
	 */
	@Ignore
	public void getAllColleaguesByListToolIdThrowsDataAccessException() {
		//TODO when exceptions will be treated
		fail("Not yet implemented");
	}

}