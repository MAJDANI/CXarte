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
	 * @param skill
	 * @return Return an error message callback 
	 * @throws Exception
	 */
	Map<String, Object> addOneSkill(VSkill skill) throws Exception;
	
	/**
	 * Update one skill
	 * @class IAdminService.java
	 * @param skill
	 * @return Return an error message callback
	 * @throws Exception
	 */
	Map<String, Object> updateOneSkill(Category category, Concept concept, Tool tool) throws Exception;
	
	/**
	 * Delete one category (and his concepts and tools)
	 * @class IAdminService.java
	 * @param category_id
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> deleteCategory(int category_id) throws Exception;
	
	/**
	 * Delete one concept (and his tools)
	 * @class IAdminService.java
	 * @param concept_id
	 * @return
	 * @throws Exception
	 */
	Map<String , Object> deleteConcept(int concept_id) throws Exception;
	
	/**
	 * Delete one tool
	 * @class IAdminService.java
	 * @param tool_id
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> deleteTool(int tool_id) throws Exception;
}
