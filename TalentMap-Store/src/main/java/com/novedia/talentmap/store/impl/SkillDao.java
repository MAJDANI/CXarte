package com.novedia.talentmap.store.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.novedia.talentmap.model.entity.SkillEntity;
import com.novedia.talentmap.store.ISkillDao;

/**
 * ISkillDao interface implementation.
 * 
 * @author j.marie-sainte
 */
public class SkillDao implements ISkillDao {
	
	private SkillEntity skillOne;
	
	/**
	 * Set the skillOne value
	 * @param skillOne the skillOne to set
	 */
	public void setSkillOne(SkillEntity skillOne) {
		this.skillOne = skillOne;
	}

	@Override
	public Collection<SkillEntity> getSkills() {
		
		// Mocked implementation for skill DAO
		skillOne.setDomain("Java");
		skillOne.setLevel(5);
		skillOne.setType(2);

		Collection<SkillEntity> skills = new ArrayList<SkillEntity>();
		skills.add(skillOne);
		
		return skills;
	}

}
