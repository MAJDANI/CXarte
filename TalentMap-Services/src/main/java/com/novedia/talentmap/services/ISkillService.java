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
 * Interface Skill Services.
 * @author j.collet
 */
public interface ISkillService {

/**
 * Select one Skill by Tool_ID and Collaborator_ID.
 * @class ISkillService.java
 * @param toolId a id
 * @param collaboratorID a collaboratorid
 * @return Skill a skill
 */
Skill getSkillByToolId(int collaboratorID, int toolId);

/**
 * Select all Collaborator Skills By id (new version).
 * @class ISkillService.java
 * @param collabId a collabId
 * @return a list of VSkillCollab
 */
List<VSkillCollab> getAllSkillCollab(int collabId);

/**
 * Select a skill by allCollaborator.
 * @param collabId a id
 * @return a map
 */
Map<Category, Map> getAllCollaboratorSkill(int collabId);

/**
 * Select All Tools.
 * @class ISkillService.java
 * @return a list of Tool
 */
List<Tool> getAllTools();

/**
 * Select all VSkill By Category_Name and Concept_Name.
 * @class ISkillService.java
 * @param categoryName a categoryName
 * @param conceptName a concept name
 * @return a list of VSkill
 */
List<VSkill> getToolByConcept(String categoryName, String conceptName);

/**
 * Get One VSkill By Tool_Name.
 * @class ISkillService.java
 * @param toolName a tool name
 * @return a VSkill
 */
VSkill getSkillByTool(String toolName);

/**
 * Select all VSkill By Category_Name.
 * @class ISkillService.java
 * @param categoryName a category name
 * @return a list of VSkill
 * @throws DataAccessException
 */
List<VSkill> getConceptByCategory(String categoryName);

/**
 * Add One Skill.
 * @class ISkillService.java
 * @param skill a skill
 */
void addSkill(Skill skill);

/**
 * Update One Skill.
 * @class ISkillService.java
 * @param skill a skill
 */
void saveSkill(Skill skill);

/**
 * Get One Tool By Name.
 * @param name a tool name
 * @return a Tool by name
 */
 Tool getToolByName(String name);	
}