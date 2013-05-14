package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;

/**
 * The skill DAO interface.
 * 
 * @author j.marie-sainte
 * 
 */
public interface ISkillDao {

    /**
     * Select all Collaborator Skills.
     * 
     * @class ISkillDao.java
     * @param collaboratorId
     *            a collaborator id
     * @return a list of skill
     */
    List<Skill> getAllCollaboratorSkill(int collaboratorId);

    /**
     * Get One Collaborator Skill By Collaborator_ID and Tool_ID.
     * 
     * @class ISkillDao.java
     * @param collaboratorId
     *            a id
     * @param toolId
     *            a id
     * @return a skill
     */
    Skill getCollaboratorSkillByTool(int collaboratorId, int toolId);

    /**
     * Get One Tool By Name.
     * 
     * @class ISkillService.java
     * @param name
     *            a name
     * @return a tool
     */
    Tool getToolByName(String name);

    List<Tool> getAllByConcept(int conceptId);
}
