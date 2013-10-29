package com.novedia.talentmap.services;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.novedia.talentmap.model.dto.MissionDTO;
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
     * 
     * @return List<Collaborator>
     */
    List<Colleague> getAllColleagues();

    /**
     * Get a colleague.
     * 
     * @param id
     *            the identifier
     * @return Colleague a collaborator
     */
    Colleague getColleague(Integer id);

    /**
     * Save a colleague.
     * 
     * @param colleague
     *            a collaborator
     * @return a int
     */
    Integer saveColleague(Colleague colleague);

    /**
     * Add a mission.
     * 
     * @param mission
     *            a mission
     * @return id of the mission inserted
     */
    @Transactional
    Integer addMission(MissionDTO mission);

    /**
     * Update a mission.
     * 
     * @param mission
     *            a mission
     * @return int
     */
    @Transactional
    Integer saveMission(MissionDTO mission);

    /**
     * Delete a mission.
     * 
     * @param mission
     *            mission
     * @return int
     */
    Integer deleteMission(Mission mission);

    /**
     * Get a mission.
     * 
     * @param missionId
     *            the mission id
     * @return mission a mission
     */
    Mission getMission(Integer missionId);

    /**
     * Get all Collaborators' Mission By Collab_ID.
     * 
     * @class ICollaboratorService.java
     * @param collabId
     *            a id
     * @return List<Mission>
     */
    List<Mission> getAllMissions(Integer collabId);

    /**
     * Get last mission for a Collab_ID.
     * 
     * @class ICollaboratorService.java
     * @param collabId
     *            a id
     * @return List<Mission>
     */
    MissionDTO getLastMission(Integer collabId);

    /**
     * Get manager.
     * 
     * @class ICollaboratorService.java
     * @param managerId
     *            a id
     * @return Manager
     */
    Manager getManager(Integer managerId);

    /**
     * Get all Manager.
     * 
     * @return list of manager
     */
    List<Colleague> getAllConsultantManager();
    
    /**
     * Get all Business Engineers.
     * 
     * @return list of business engineers
     */
    List<Colleague> getAllBusinessEngineers();

    /**
     * Get all Colleagues by lastName.
     * 
     * @param lastName
     *            a lastName
     * @return List<Colleague>
     */
    List<Colleague> getAllColleaguesByName(String name);

    /**
     * Get all Colleagues by client name
     * 
     * @param clientName
     * @return List<Colleague>
     */
    List<Colleague> getAllColleaguesByClient(Client client);

    /**
     * Get Cm Colleagues by client name
     * 
     * @param clientName
     * @return List<Colleague>
     */
    List<Colleague> getCmColleaguesByClient(int clientId, int managerId);

    /**
     * Get Cm Colleagues by last name
     * 
     * @param lastName
     * @return List<Colleague>
     */
    List<Colleague> getCmColleaguesByName(String name, int managerId);

    /**
     * Get all Colleagues by toolId.
     * 
     * @param toolId
     *            a toolId
     * @return List<Colleague>
     */
    List<Colleague> getAllCollaboratorsByToolId(Integer toolId);

    /**
     * Select all Colleagues by a list of toolId.
     * 
     * @param listToolId
     *            a listTool
     * @return List<Colleague>
     */
    List<Colleague> getAllColleaguesByListToolId(Map toolIdMap);

    /**
     * Select all Colleagues by a list of colleagueId.
     * 
     * @param listColleagueId
     *            a list of colleague's Id
     * @return List<Colleague>
     */
    List<Colleague> getAllColleagueByColleagueIdList(
	    List<Integer> listColleagueId);

    /**
     * Counts colleagues in DB that have already the mail, with a different
     * colleagueId. Used to check when a colleague wants to modify his email, if
     * this email is not already use by someone else.
     * 
     * @param colleague
     *            who wants to change his email
     * @return The count found.
     */
    Integer countMailForColleagueId(Colleague colleague);

    /**
     * Counts colleagues in DB that have already the mail, with a different
     * colleague's last and first name. Used to check, during the registration, if
     * his email is not already use by someone else.
     * 
     * @param email : the email to check
     * @param colleagueFirstName : first name of the colleague who wants to register
     * @param colleagueLastName : last name of the colleague who wants to register
     * @return The count found.
     */
    Integer countMailForColleagueNames(String email, String colleagueFirstName, String colleagueLastName );
  
    MissionDTO createMissionDTO(Mission m);
    
    Mission createEntity(MissionDTO mDTO);
    
}