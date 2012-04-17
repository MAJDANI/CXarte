package com.novedia.talentmap.model.dao.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.novedia.talentmap.model.dao.ISkillDao;
import com.novedia.talentmap.model.entity.SkillEntity;

/**
 * ISkillDao interface implementation.
 * 
 * @author j.marie-sainte
 */
public class SkillDao implements ISkillDao {
	
	@Override
	public Collection<SkillEntity> getSkills() {
		
		// Mocked implementation for skill DAO
		SkillEntity skillOne = new SkillEntity();
		skillOne.setDomain("Java");
		skillOne.setLevel(5);
		skillOne.setType(2);

		Collection<SkillEntity> skills = new ArrayList<SkillEntity>();
		skills.add(skillOne);
		
		return skills;
	}

}
