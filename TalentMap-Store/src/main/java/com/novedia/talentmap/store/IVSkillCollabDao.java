package com.novedia.talentmap.store;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.VSkillCollab;
/**
 * @author v.dibi
 *
 */
public interface IVSkillCollabDao {

/**
 * Get all Collaborators' Skill by ID.
 * @class IVSkillCollabDao.java
 * @param collabId a id
 * @return a list VSkillCollab
 */
List<VSkillCollab> getAllSkillCollab(int collabId);

}
