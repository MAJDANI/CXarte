package com.novedia.talentmap.store;

import java.util.List;

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
	
	/***
	 * Update One Skill
	 * @class ISkillDao.java
	 * @throws Exception
	 */
	void updateOneSkill(Skill skill) throws Exception;
}
