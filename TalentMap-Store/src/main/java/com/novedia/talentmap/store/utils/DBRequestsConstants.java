package com.novedia.talentmap.store.utils;
/**
 * This class provides static constants ids matching.
 * the sqlmap-[entity].xml requests used by IBATIS
 * @author j.marie-sainte
 */
public final class DBRequestsConstants {
// COLLABORATOR REQUESTS

/**
 * Constant request id to get a collaborator.
 */
public static final String GET_COLLABORATOR = "collaborator.get";

/**
 * Constant request id to get all the collaborators.
 */
public static final String GET_ALL_COLLABORATOR = "collaborator.getAll";

/**
 * Constant request to add a collaborator.
 */
public static final String ADD_COLLABORATOR = "collaborator.add";

/**
 * Constant request to delete a collaborator.
 */
public static final String DELETE_COLLABORATOR = "collaborator.delete";

/**
 * Constant request to update a collaborator.
 */
public static final String SAVE_COLLABORATOR = "collaborator.save";

/**
 * Constant request to get a collaborator by lastName.
 */
public static final String GET_ALL_COLLABORATOR_BY_LASTNAME="collaborator.getAllByLastName";

// CATEGORY REQUESTS
/**
 * Constant request to get a category.
 */
public static final String GET_CATEGORY = "category.getCategory";

/**
 * This constant is used to retrieve all category.
 */
public static final String GET_ALL_CATEGORY = "category.getAll";

/**
 * Constant request to insert the category.
 */
public static final String  ADD_CATEGORY = "category.add";
/**
 * Constant request to check a category.
 */
public static final String CHECK_CATEGORY = "category.check";

/**
 * Constant request to update category.
 */
public static final String  SAVE_CATEGORY = "category.save";

/**
 * Constant request to delete category.
 */
public static final String DELETE_CATEGORY = "category.delete";

/**
 * This constant is used to retrieve a category.
 */
public static final String GET_CATEGORY_BY_NAME = "category.getByName";


// CONCEPT REQUEST
/**
 * This constant is used to retrieve a concept.
 */
public static final String GET_CONCEPT = "concept.get";

/**
 * This constant is used to retrieve all concept.
 */
public static final String GET_ALL_CONCEPT = "concept.getAll";

/**
 * This constant is used to retrieve a concept by category id.
 */
public static final String GET_ALL_CONCEPT_BY_CATEGORYID = "concept.getAllConceptByCategoryId";

/**
 * Constant request id to add a concept.
 */
public static final String ADD_CONCEPT = "concept.add";

/**
 * Constant request to update a concept.
 */
public static final String SAVE_CONCEPT = "concept.save";

/**
 * Constant request to delete concept.
 */
public static final String DELETE_CONCEPT = "concept.delete";

/**
 * Constant request to check a concept.
 */
public static final String CHECK_CONCEPT = "concept.check";

/**
 * This constant is used to retrieve a concept by name.
 */
public static final String GET_CONCEPT_BY_NAME = "concept.getByName";

// MANAGER REQUESTS
/**
 * This constant is used to retrieve a manager.
 */
public static final String GET_MANAGER = "manager.get";

/**
 * This constant is used to retrieve a collaborator by manager id.
 */
public static final String GET_COLLABORATORS_BY_MANAGER_ID = "manager.getAllCollaboratorsByManagerId";


// MISSION REQUESTS

/**
 * This constant is used to retrieve a mission.
 */
public static final String GET_MISSION = "mission.get";

/**
 * Constant request id to get all missions.
 * This constant is used to retrieve all mission.
 */
public static final String GET_ALL_MISSION = "mission.getAll";

/**
 * Constant request to add a mission.
 */
public static final String ADD_MISSION = "mission.add";

/**
 * Constant request to update a collaborator.
 */
public static final String SAVE_MISSION = "mission.save";


// PROFILE REQUESTS

/**
 * This constant is used to retrieve all profile.
 */
public static final String GET_ALL_PROFILE = "profile.getAll";

/**
 * Constant request to check a profile.
 */
public static final String CHECK_PROFILE = "profile.check";

/**
 * This constant is used to retrieve a profile.
 */
public static final String GET_PROFILE = "profile.get";

/**
 * Constant request to delete a profile.
 */
public static final String DELETE_PROFILE = "profile.delete";

/**
 * Constant request to update a profile.
 */
public static final  String SAVE_PROFILE = "profile.save";

/**
 * Constant request to add a profile.
 */
public static final String ADD_PROFILE = "profile.add";

/**
 * This constant is used to retrieve a profile by name.
 */
public static final String GET_PROFILE_BY_NAME = "profile.getByName";

/**
 * This constant is used to retrieve a collaborator by type.
 */
public static final String GET_PROFILE_BY_TYPE = "profile.getByType";

/**
 * This constant is used to retrieve all collaborator skill.
 */
public static final String GET_ALL_COLLABORATOR_SKILL ="skill.getAllCollaboratorSkill";

/**
 * This constant is used to retrieve all skills by collaborator.
 */
public static final String GET_ALL_SKILLS_BY_COLLABORATOR = "skill.getAllSkillsByCollaborator";

/**
 * This constant is used to retrieve all skill.
 */
public static final String GET_ALL_SKILL = "skill.getAll";

/**
 * Constant request to add a skill.
 */
public static final String ADD_SKILL = "skill.add";

/**
 * Constant request to update a collaborator.
 */
public static final String SAVE_SKILL = "skill.save";

/**
 * Constant request to get all collaborators by tool.
 */
public static final String GET_ALL_COLLABORATORS_BY_TOOL = "skill.getAllCollaboratorsIdByTool";

/**
 * This constant is used to retrieve all collaborator by tool.
 */
public static final String GET_ALL_COLLABORATORID_BY_TOOL ="skill.getAllCollaboratorIdByToolIdAndCollabList";


// TOOL REQUESTS

/**
 * Constant request to add a tool.
 */
public static final String ADD_TOOL = "tool.add";

/**
 * Constant request to update a collaborator.
 */
public static final String SAVE_TOOL = "tool.save";

/**
 * Constant request id to check a tool.
 */
public static final String CHECK_TOOL = "tool.check";
/**
 * This constant is used to retrieve all tool by concept.
 */
public static final String GET_ALL_TOOL_BY_CONCEPT = "tool.getAllToolByConcept";

/**
 * This constant is used to retrieve all tool.
 */
public static final String GET_ALL_TOOL = "tool.getAll";

/**
 * Constant request to delete a tool.
 */
public static final String DELETE_TOOL = "tool.delete";

/**
 * This constant is used to retrieve a tool by name.
 */
public static final String GET_TOOL_BY_NAME = "tool.getToolByName";

/**
 *This constant is used to retrieve a tool.
 */
public static final String GET_TOOL = "tool.get";


// VSKILL REQUESTS

/**
 * This constant is used to retrieve all skill by collaborator.
 */
public static final String GET_ALL_SKILL_COLLAB = "vskillCollab.getAllSkillCollab";

/**
 * This constant is used to retrieve a skill by tool.
 */
public static final String GET_SKILL_BY_TOOL = "vskill.getByTool";

/**
 * This constant is used to retrieve a concept by category.
 */
public static final String GET_CONCEPT_BY_CATEGORY = "vskill.getConceptByCategory";

/**
 * This constant is used to retrieve a tool by concept.
 */
public static final String GET_TOOL_BY_CONCEPT = "vskill.getToolByConcept";	

/**
 * Constant request to delete a collaborator.
 */
public static final String DELETE_MISSION = "mission.delete";

/**
 * This constant is used to retrieve a task by id.
 */
public static final String GET_TASK_BY_ID = "task.get";
}