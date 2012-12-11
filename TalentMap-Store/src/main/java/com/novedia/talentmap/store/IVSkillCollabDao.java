package com.novedia.talentmap.store;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.VSkillCollab;

/**
 * The interface {@link IVSkillCollabDao}
 * @author moumbe
 *
 */
public interface IVSkillCollabDao {
	
	/**
	 * Get Skill by Id
	 * @param id a id
	 * @return VSkillCollab
	 * @throws DataAccessException
	 */
	VSkillCollab get(int id) throws DataAccessException;
	
	/**
	 * Get all Collaborators' Skill by ID 
	 * @class IVSkillCollabDao.java
	 * @param collabId a {@link Collaborator}
	 * @return a list VSkillCollab
	 * @throws DataAccessException
	 */
	List<VSkillCollab> getAllSkillCollab(int collabId) throws DataAccessException;
}