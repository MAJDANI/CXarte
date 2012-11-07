package com.novedia.talentmap.services;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.model.entity.VSkillCollab;

/**
 * Interface Skill Services
 * @author j.collet
 * @project TalentMap-Services
 * @package com.novedia.talentmap.services
 * @created 21 mai 2012
 */
public interface ISkillService {
	
	/**
	 * Select one Skill by Tool_ID and Collaborator_ID
	 * @class ISkillService.java
	 * @param toolId
	 * @return
	 * @throws Exception
	 */
	Skill getSkillByToolId(int collaboratorID, int toolId) throws DataAccessException;
	
	/**
	 * Select all Collaborator Skills By id (new version)
	 * @class ISkillService.java
	 * @param collab_id
	 * @return
	 * @throws Exception
	 */
	List<VSkillCollab> getAllSkillCollab(int collab_id) throws DataAccessException;
	
	/**
	 * Select all Collaborator Skills By Id(old version)
	 * @class ISkillService.java
	 * @param collab_id
	 * @return
	 * @throws Exception
	 */
	Map<Category, Map> getAllCollaboratorSkill(int collabId) throws DataAccessException;
	
	/**
	 * Select All Tools
	 * @class ISkillService.java
	 * @return
	 * @throws Exception
	 */
	List<Tool> getAllTools() throws Exception;
	
	/**
	 * Select all VSkill By Category_Name and Concept_Name
	 * @class ISkillService.java
	 * @param categoryName
	 * @param conceptName
	 * @return
	 * @throws Exception
	 */
	List<VSkill> getToolByConcept(String categoryName, String conceptName) throws DataAccessException;
	
	/**
	 * Get One VSkill By Tool_Name
	 * @class ISkillService.java
	 * @param toolName
	 * @return
	 * @throws Exception
	 */
	VSkill getSkillByTool(String toolName) throws DataAccessException;
	
	/**
	 * Select all VSkill By Category_Name
	 * @class ISkillService.java
	 * @param categoryName
	 * @return
	 * @throws Exception
	 */
	List<VSkill> getConceptByCategory(String categoryName) throws DataAccessException;
	
	/**
	 * Add One Skill
	 * @class ISkillService.java
	 * @param skill
	 * @throws Exception
	 */
	void addSkill(Skill skill) throws DataAccessException;
	
	/**
	 * Update One Skill
	 * @class ISkillService.java
	 * @param skill
	 * @throws Exception
	 */
	void saveSkill(Skill skill) throws DataAccessException;
	
	/**
	 * Get One Tool By Name
	 * @class ISkillService.java
	 * @param name
	 * @return
	 * @throws Exception
	 */
	Tool getToolByName(String name) throws DataAccessException;
	
}
