package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Skill;

public interface ISkillDao {
	
	List<Skill> getAllCollaboratorSkill(int collaborator_id) throws Exception;
	
	Skill getOneCollaboratorSkill(int collaborator_id, int tool_id) throws Exception;
}
