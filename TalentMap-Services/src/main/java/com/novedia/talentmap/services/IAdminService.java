package com.novedia.talentmap.services;

import java.util.List;
import java.util.Map;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;

public interface IAdminService {

	/**
	 * Get all tools
	 * @class IAdminService.java
	 * @return
	 * @throws Exception
	 */
	List<Tool> getAllTools() throws Exception; 
	
	/**
	 * Get all concepts
	 * @class IAdminService.java
	 * @return
	 * @throws Exception
	 */
	List<Concept> getAllConcepts() throws Exception; 

	/**
	 * Get all categories
	 * @class IAdminService.java
	 * @return
	 * @throws Exception
	 */
	List<Category> getAllCategories() throws Exception; 
	
	/**
	 * Add one skill
	 * @class IAdminService.java
	 * @throws Exception
	 */
	Map<String, Object> addOneSkill(VSkill skill) throws Exception;
}
