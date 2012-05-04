package com.novedia.talentmap.services;

import java.util.Map;

import com.novedia.talentmap.model.entity.Category;

public interface ISkillService {
	
	Map<Category, Map> getAllSkill(int collab_id) throws Exception;
	
}
