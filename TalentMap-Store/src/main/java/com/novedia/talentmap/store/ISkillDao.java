package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Skill;

public interface ISkillDao {
	
	/**
	 * Select all Collaborator Skills
	 * @class ISkillDao.java
	 * @param collaborator_id
	 * @return
	 * @throws Exception
	 */
	List<Skill> getAllCollaboratorSkill(int collaborator_id) throws Exception;
	
	/**
	 * Get One Collaborator Skill By Collaborator_ID and Tool_ID
	 * @class ISkillDao.java
	 * @param collaborator_id
	 * @param tool_id
	 * @return
	 * @throws Exception
	 */
	Skill getOneCollaboratorSkill(int collaborator_id, int tool_id) throws Exception;
	
	/**
	 * Add One Skill
	 * @class ISkillDao.java
	 * @param skill
	 * @throws Exception
	 */
	void addOneSkill(Skill skill) throws Exception;

	/**
	 * Select all Collaborator's id by a toolId
	 * @author v.guillemain
	 * @class ISkillDao.java
	 * @param toolId : a toolId
	 * @return all collaborator's id who has a competence on the tool specified
	 * @throws Exception
	 */
	List<Integer> getAllCollaboratorsIdByToolId(int toolId) throws Exception;

	/**
	 * Select all collaborator's Id by a toolId and a list of collaboratorId.
	 * @author v.guillemain
	 * @class ISkillDao.java
	 * @param toolId : a toolId
	 * @param listCollabId : a list of collaborator's Id
	 * @return all collaborator's id who has the competence (toolId) and who is in the list of collaborator's id
	 * @throws Exception
	 */
	List<Integer> getAllCollaboratorIdByToolIdAndCollabList(int toolId, List<Integer> listCollabId) throws Exception;

	/***
	 * Update One Skill
	 * @class ISkillDao.java
	 * @throws Exception
	 */
	void updateOneSkill(Skill skill) throws Exception;
}
