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
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public VSkillCollab get(int id) throws DataAccessException;
	
	/**
	 * Get all Collaborators' Skill by ID 
	 * @class IVSkillCollabDao.java
	 * @param collab_id
	 * @return
	 * @throws Exception
	 */
	List<VSkillCollab> getAllSkillCollab(int collab_id) throws DataAccessException;
	
}
