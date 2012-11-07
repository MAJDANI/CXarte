package com.novedia.talentmap.store;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.VSkillCollab;

public interface IVSkillCollabDao {
	
	/**
	 * Get all Collaborators' Skill by ID 
	 * @class IVSkillCollabDao.java
	 * @param collab_id
	 * @return
	 * @throws Exception
	 */
	List<VSkillCollab> getAllSkillCollab(int collab_id) throws DataAccessException;
	
}
