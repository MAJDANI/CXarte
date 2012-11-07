package com.novedia.talentmap.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Mission;
/**
 * Collaborator Service Interface.
 * 
 * @author j.collet
 * @project TalentMap-Services
 * @package com.novedia.talentmap.services
 * @created 21 mai 2012
 */
public interface ICollaboratorService {
	
	/**
	 * Get all collaborators
	 * 
	 * @class ICollaboratorService.java
	 * @return List<Collaborator>
	 * @throws Exception
	 */
	List<Collaborator> getAllCollaborator() throws DataAccessException;
	
	/**
	 * Get a Collaborator
	 * 
	 * @class ICollaboratorService.java
	 * @param id
	 * @return Collaborator
	 * @throws Exception
	 */
	Collaborator getCollaborator(Integer id) throws DataAccessException;
	
	/**
	 * Save a collaborator
	 * @class ICollaboratorService.java
	 * @param collaborator
	 * @return int
	 * @throws Exception
	 */
	int saveCollaborator(Collaborator collaborator) throws DataAccessException;
	
	/**
	 * Add a mission
	 * @class ICollaboratorService.java
	 * @param mission
	 * @return id of the mission inserted
	 * @throws Exception
	 */
	int addMission(Mission mission) throws DataAccessException;

	/**
	 * Update one Mission
	 * @class ICollaboratorService.java
	 * @param mission
	 * @return int
	 * @throws Exception
	 */
	int saveMission(Mission mission) throws DataAccessException;
	
	/**
	 * Delete a mission
	 * 
	 * @class ICollaboratorService.java
	 * @param missionId : mission'id to delete
	 * @return int 
	 * @throws Exception
	 */
	int deleteMission(Mission mission) throws DataAccessException;
	
	/**
	 * Get a mission
	 * 
	 * @class ICollaboratorService.java
	 * @param missionId
	 * @return Mission
	 * @throws Exception
	 */
	Mission getMission(int missionId) throws DataAccessException;
	
	/**
	 * Get all Collaborators' Mission By Collab_ID
	 * 
	 * @class ICollaboratorService.java
	 * @param collab_id
	 * @return List<Mission>
	 * @throws Exception
	 */
	List<Mission> getAllMission(int collabId) throws DataAccessException;
	
	/**
	 * Get one Manager by ID
	 * @class ICollaboratorService.java
	 * @param manager_id
	 * @return Manager
	 * @throws Exception
	 */
	Manager getManager(int managerId) throws DataAccessException;
	
	/**
	 * Select all Collaborators by lastName
	 * @author v.guillemain
	 * @class ICollaboratorService.java
	 * @param lastName
	 * @return List<Collaborator>
	 * @throws Exception
	 */
	List<Collaborator> getAllCollaboratorsByLastName(String lastName) throws DataAccessException;

	/**
	 * Select all Collaborators by toolId
	 * @author v.guillemain
	 * @class ICollaboratorService.java
	 * @param toolId
	 * @return List<Collaborator>
	 * @throws Exception
	 */
	List<Collaborator> getAllCollaboratorsByToolId(int toolId) throws DataAccessException;

	/**
	 * Select all Collaborators by a list of toolId
	 * @author v.guillemain
	 * @class ICollaboratorService.java
	 * @param listToolId
	 * @return List<Collaborator>
	 * @throws Exception
	 */
	List<Collaborator> getAllCollaboratorsByListToolId(List<Integer> listToolId) throws DataAccessException;
}
