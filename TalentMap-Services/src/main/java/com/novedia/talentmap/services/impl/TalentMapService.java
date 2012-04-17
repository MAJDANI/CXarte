package com.novedia.talentmap.services.impl;

import java.util.Collection;

import com.novedia.talentmap.model.dao.ISkillDao;
import com.novedia.talentmap.model.entity.SkillEntity;
import com.novedia.talentmap.services.ITalentMapService;

/**
 * ITalentMapService interface implementation.
 * 
 * @author j.marie-sainte
 */
public class TalentMapService implements ITalentMapService {
	
	/**
	 * The skill DAO bean
	 */
	private ISkillDao skillDao;
	
	@Override
	public String displaySkills() {
		
		StringBuilder sb = new StringBuilder();
		
		Collection<SkillEntity> skills= skillDao.getSkills();
//		SkillEntity skillOne = new SkillEntity();
//		skillOne.setDomain("Java");
//		skillOne.setLevel(5);
//		skillOne.setType(2);
		
//		Collection<SkillEntity> skills = new ArrayList<SkillEntity>();
//		skills.add(skillOne);
		
		for (SkillEntity skill : skills) {
			sb.append(skill.getDomain()).append(" / ").append(skill.getLevel()).append(" / ").append(skill.getType());
		}
		
		String message = "Talent Map user skill are : [ ".concat(sb.toString()).concat(" ]");
		
		return message;
	}

	// Setters
	
	/**
	 * Set the skill DAO value
	 * @param skillDao
	 */
	public void setSkillDao(ISkillDao skillDao) {
		this.skillDao = skillDao;
	}	
	
}
