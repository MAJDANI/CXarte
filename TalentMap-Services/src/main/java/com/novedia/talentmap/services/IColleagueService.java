package com.novedia.talentmap.services;

import java.util.List;
import java.util.Map;

import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Mission;

/**
 * Colleague Service Interface.
 * 
 * @author j.marie-sainte
 */
public interface IColleagueService {
	
/**
 * Get all colleagues.
 * @return List<Collaborator>
 */
List<Colleague> getAllColleagues();

/**
 * Get a colleague.
 * @param id the identifier
 * @return Colleague a collaborator
 */
Colleague getColleague(Integer id);

/**
 * Save a colleague.
 * @param colleague a collaborator
 * @return a int
 */
Integer saveColleague(Colleague colleague);

/**
 * Add a mission.
 * @param mission a mission
 * @return id of the mission inserted
 */
Integer addMission(Mission mission);

/**
 * Update a mission.
 * @param mission a mission
 * @return int
 */
Integer saveMission(Mission mission);

/**
 * Delete a mission.
 * @param mission mission
 * @return int
 */
Integer deleteMission(Mission mission);

/**
 * Get a mission.
 * @param missionId the mission id
 * @return mission a mission
 */
Mission getMission(Integer missionId);

/**
 * Get all Collaborators' Mission By Collab_ID.
 * @class ICollaboratorService.java
 * @param collabId a id
 * @return List<Mission>
 */
List<Mission> getAllMissions(Integer collabId);

/**
 * Get last mission for a Collab_ID.
 * @class ICollaboratorService.java
 * @param collabId a id
 * @return List<Mission>
 */
Mission getLastMission(Integer collabId);

/**
 * Get manager.
 * @class ICollaboratorService.java
 * @param managerId a id
 * @return Manager
 */
Manager getManager(Integer managerId);

/**
 * Get all Colleagues by lastName.
 * @param lastName a lastName
 * @return List<Colleague>
 */
List<Colleague> getAllColleaguesByLastName(String lastName);

/**
 * Get all Colleagues by client name
 * @param clientName
 * @return List<Colleague>
 */
List<Colleague> getAllColleaguesByClient(Client client);

/**
 * Get all Collaborators by toolId.
 * @param toolId a toolId
 * @return List<Colleague>
 */
List<Colleague> getAllCollaboratorsByToolId(Integer toolId);

/**
 * Select all Collaborators by a list of toolId.
 * @param listToolId a listTool
 * @return List<Colleague>
 */
List<Colleague> getAllColleaguesByListToolId(Map toolIdMap);
}