package com.novedia.talentmap.services;

import java.util.Collection;
import java.util.List;

import com.novedia.talentmap.model.dto.CategoryMapDTO;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.model.entity.VSkillCollab;

/**
 * The Skill Service Interface.
 * 
 * @author j.collet
 */
public interface ISkillService {

    /**
     * Select one Skill by Tool_ID and Collaborator_ID.
     * 
     * @class ISkillService.java
     * @param toolId
     *            a id
     * @param collaboratorID
     *            a collaboratorid
     * @return Skill a skill
     */
    Skill getSkillByToolId(int collaboratorID, int toolId);

    /**
     * Select all colleague's id who have skill with the tool id.
     * 
     * @class ISkillService.java
     * @param listToolId
     *            a tool Id
     * @return List<Integer> a list of colleague's id
     */
    List<Integer> getAllColleagueIdByToolId(Integer toolId);

    /**
     * Select CM colleague's id who have skill with the tool id.
     * 
     * @class ISkillService.java
     * @param listToolId
     *            a tool Id , managerId
     * @return List<Integer> a list of colleague's id
     */
    List<Integer> getCmColleagueIdByToolId(Integer toolId, Integer managerId);

    /**
     * Select all colleague's id who have skill with one ore more tool id of the
     * list.
     * 
     * @class ISkillService.java
     * @param listToolId
     *            a list of toolId
     * @return List<Integer> a list of colleague's id
     */
    List<Integer> getAllColleagueIdByListToolId(List<Integer> listToolId);

    /**
     * Select all CM colleague's id who have skill with one ore more tool id of
     * the list.
     * 
     * @class ISkillService.java
     * @param listToolId
     *            a list of toolId
     * @return List<Integer> a list of colleague's id
     */
    List<Integer> getCmColleagueIdByListToolId(
	    List<Integer> listToolIdForCategory, int managerId);

    /**
     * Select all Collaborator Skills By id (new version).
     * 
     * @class ISkillService.java
     * @param collabId
     *            a collabId
     * @return a list of VSkillCollab
     */
    List<VSkillCollab> getAllSkillCollab(int collabId);

    /**
     * Select a skill by allCollaborator.
     * 
     * @param collabId
     *            a id
     * @return a map
     */
    CategoryMapDTO getAllCollaboratorSkill(int collabId);

    /**
     * Select All Tools.
     * 
     * @class ISkillService.java
     * @return a list of Tool
     */
    List<Tool> getAllTools();

    /**
     * Select All Tools by Concept.
     * 
     * @class ISkillService.java
     * @return a list of Tool
     */
    List<Tool> getAllToolsByConcept(int conceptId);

    /**
     * Select all VSkills ordered by category and concept
     * 
     * @class ISkillService.java
     * @return a list of VSkill
     */
    List<VSkill> getAllVSkillOrdered();

    /**
     * Get One VSkill By Tool_Name.
     * 
     * @class ISkillService.java
     * @param toolName
     *            a tool name
     * @return a VSkill
     */
    VSkill getSkillByTool(String toolName);

    /**
     * Add One Skill.
     * 
     * @class ISkillService.java
     * @param skill
     *            a skill
     */
    Integer addSkill(Skill skill);

    /**
     * Update One Skill.
     * 
     * @class ISkillService.java
     * @param skill
     *            a skill
     */
    Integer saveSkill(Skill skill);

    /**
     * Get One Tool By Name.
     * 
     * @param name
     *            a tool name
     * @return a Tool by name
     */
    Tool getToolByName(String name);

}