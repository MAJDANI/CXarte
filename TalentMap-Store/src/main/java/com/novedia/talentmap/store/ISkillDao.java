package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;

/**
 * The skill DAO interface.
 * @author j.marie-sainte
 *
 */
public interface ISkillDao {

/**
 * Select all Collaborator Skills.
 * @class ISkillDao.java
 * @param collaboratorId a collaborator id
 * @return a list of skill
 */
List<Skill> getAllCollaboratorSkill(int collaboratorId);

/**
 * Get One Collaborator Skill By Collaborator_ID and Tool_ID.
 * @class ISkillDao.java
 * @param collaboratorId a id
 * @param toolId a id
 * @return a skill
 */
Skill getOneCollaboratorSkill(int collaboratorId, int toolId);

/**
 * Select all Collaborator's id by a toolId.
 * @author v.guillemain
 * @class ISkillDao.java
 * @param toolId : a toolId
 * @return all collaborator's id who has a competence on the tool specified
 */
List<Integer> getAllCollaboratorsIdByToolId(int toolId);

/**
 * Select all collaborator's Id by a toolId and a list of collaboratorId.
 * @author v.guillemain
 * @class ISkillDao.java
 * @param toolId : a toolId
 * @param listCollabId : a list of collaborator's Id
 * @return all collaborator's id who has the competence (toolId)
 * and who is in the list of collaborator's id
 */
List<Integer> getAllCollaboratorIdByToolIdAndCollabList(int toolId, List<Integer> listCollabId);

/**
 * Get One Tool By Name.
 * @class ISkillService.java
 * @param name a name
 * @return a tool
 */
Tool getToolByName(String name);
}
