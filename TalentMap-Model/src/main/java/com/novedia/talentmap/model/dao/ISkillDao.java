package com.novedia.talentmap.model.dao;

import java.util.Collection;

import com.novedia.talentmap.model.entity.SkillEntity;

/**
 * Skill Data Access Object interface.
 * 
 * @author j.marie-sainte
 */
public interface ISkillDao {
    
	/**
	 * Get a collection of skills.
	 * 
	 * @return a collection of Skills
	 */
	Collection <SkillEntity> getSkills();
}
