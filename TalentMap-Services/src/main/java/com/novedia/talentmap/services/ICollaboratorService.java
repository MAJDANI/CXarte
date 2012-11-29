package com.novedia.talentmap.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Mission;

/**
 * Collaborator Service Interface.
 * @author j.collet
 */
public interface ICollaboratorService {	
/**
 * Get all collaborators.
 * @class ICollaboratorService.java
 * @return List<Collaborator>
 */
List<Collaborator> getAllCollaborator();

/**
 * Get a Collaborator.
 * @class ICollaboratorService.java
 * @param id a id
 * @return Collaborator a collaborator
 */
Collaborator getCollaborator(Integer id);

/**
 * Save a collaborator.
 * @class ICollaboratorService.java
 * @param collaborator a collaborator
 * @return a int
 */
int saveCollaborator(Collaborator collaborator);

/**
 * Add a mission.
 * @class ICollaboratorService.java
 * @param mission a mission
 * @return id of the mission inserted
 */
int addMission(Mission mission);

/**
 * Update one Mission.
 * @class ICollaboratorService.java
 * @param mission a mission
 * @return int
 */
int saveMission(Mission mission);

/**
 * A delete a mission.
 * @param mission a mission
 * @return int
 */
int deleteMission(Mission mission);

/**
 * Get a mission.
 * @param missionId a id
 * @return mission a mission
 */
Mission getMission(int missionId);

/**
 * Get all Collaborators' Mission By Collab_ID.
 * @class ICollaboratorService.java
 * @param collabId a id
 * @return List<Mission>
 */
List<Mission> getAllMission(int collabId);

/**
 * Get one Manager by ID.
 * @class ICollaboratorService.java
 * @param managerId a id
 * @return Manager
 */
Manager getManager(int managerId);

/**
 * Select all Collaborators by lastName.
 * @author v.guillemain
 * @class ICollaboratorService.java
 * @param lastName a lastName
 * @return List<Collaborator>
 */
List<Collaborator> getAllCollaboratorsByLastName(String lastName);

/**
 * Select all Collaborators by toolId.
 * @author v.guillemain
 * @class ICollaboratorService.java
 * @param toolId a toolId
 * @return List<Collaborator>
 */
List<Collaborator> getAllCollaboratorsByToolId(int toolId);

/**
 * Select all Collaborators by a list of toolId.
 * @author v.guillemain
 * @class ICollaboratorService.java
 * @param listToolId a listTool
 * @return List<Collaborator>
 */
List<Collaborator> getAllCollaboratorsByListToolId(List<Integer> listToolId);
}