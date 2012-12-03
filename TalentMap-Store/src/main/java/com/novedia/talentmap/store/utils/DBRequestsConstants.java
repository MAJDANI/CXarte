package com.novedia.talentmap.store.utils;
/**
 * This class provides static constants ids matching.
 * the sqlmap-[entity].xml requests used by IBATIS
 * @author j.marie-sainte
 */
public final class DBRequestsConstants {
	
// COLLABORATOR REQUESTS

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_COLLABORATOR = "collaborator.get";

/**
 * Constant request id to get all the collaborators 
 */
public final static String GET_ALL_COLLABORATOR = "collaborator.getAll";

/**
 * Constant request id to get a collaborator 
 */
public final static String ADD_COLLABORATOR = "collaborator.add";

/**
 * Constant request id to delete a collaborator 
 */
public final static String DELETE_COLLABORATOR = "collaborator.delete";

/**
 * Constant request id to update a collaborator 
 */
public final static String SAVE_COLLABORATOR = "collaborator.save";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_ALL_COLLABORATOR_BY_LASTNAME="collaborator.getAllByLastName";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_ALL_COLLABORATOR_BY_LIST_ID="collaborator.getAllByListId";


// CATEGORY REQUESTS
/**
 * Constant request id to get a category.
 */
public final static String GET_CATEGORY = "category.getCategory";

/**
 * Constant request id to get all category.
 */
public final static String GET_ALL_CATEGORY = "category.getAll";

/**
 * Constant request id to insert the category
 */
public final static String  ADD_CATEGORY = "category.add";
/**
 * Constant request id to check a category
 */
public final static String CHECK_CATEGORY = "category.check";

/**
 * Constant request id to update category
 */
public final static String  SAVE_CATEGORY ="category.save";

/**
 * Constant request id to delete category
 */
public final static String DELETE_CATEGORY ="category.delete";

/**
 * Constant request id to get a collaborator 
 */
public static final String GET_CATEGORY_BY_NAME = "category.getByName";


// CONCEPT REQUEST
/**
 * Constant request id to get a collaborator 
 */
public final static String GET_CONCEPT = "concept.get";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_ALL_CONCEPT = "concept.getAll";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_ALL_CONCEPT_BY_CATEGORYID = "concept.getAllConceptByCategoryId";

/**
 * Constant request id to get a collaborator 
 */
public final static String ADD_CONCEPT = "concept.add";

/**
 * Constant request id to get a collaborator 
 */
public final static String SAVE_CONCEPT = "concept.save";

/**
 * Constant request id to get a collaborator 
 */
public final static String DELETE_CONCEPT = "concept.delete";

/**
 * Constant request id to get a collaborator 
 */
public final static String CHECK_CONCEPT ="concept.check";

/**
 * Constant request id to get a collaborator 
 */
public static final String GET_CONCEPT_BY_NAME = "concept.getByName";

// MANAGER REQUESTS
/**
 * Constant request id to get a collaborator 
 */
public final static String GET_MANAGER = "manager.get";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_COLLABORATORS_BY_MANAGER_ID="manager.getAllCollaboratorsByManagerId";


// MISSION REQUESTS

/**
 * Constant request id to get mission 
 */
public final static String GET_MISSION ="mission.get";

/**
 * Constant request id to get all missions 
 */
public final static String GET_ALL_MISSION = "mission.getAll";

/**
 * Constant request id to insert mission 
 */
public final static String ADD_MISSION = "mission.add";

/**
 * Constant request id to get a collaborator 
 */
public final static String SAVE_MISSION = "mission.save";


// PROFILE REQUESTS

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_ALL_PROFILE = "profile.getAll";

/**
 * Constant request 
 */
public static final String CHECK_PROFILE = "profile.check";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_PROFILE ="profile.get";

/**
 * Constant request id to get a collaborator 
 */

public static final String DELETE_PROFILE = "profile.delete";

/**
 * Constant request id to get a collaborator 
 */
public final static  String SAVE_PROFILE = "profile.save";

/**
 * Constant request id to get a collaborator 
 */
public  final static String ADD_PROFILE = "profile.add";

/**
 * Constant request id to get a profile by name
 */
public static final String GET_PROFILE_BY_NAME = "profile.getByName";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_PROFILE_BY_TYPE = "profile.getByType";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_ALL_COLLABORATOR_SKILL ="skill.getAllCollaboratorSkill";

/**
 * Constant key for query get all skills by collaborator
 */
public final static String GET_ALL_SKILLS_BY_COLLABORATOR = "skill.getAllSkillsByCollaborator";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_ALL_SKILL ="skill.getAll";

/**
 * Constant request id to get a collaborator 
 */
public final static String ADD_SKILL = "skill.add";

/**
 * Constant request id to get a collaborator 
 */
public final static String SAVE_SKILL = "skill.save";

/**
 * Constant request id to get all collaborators by tool 
 */
public final static String GET_ALL_COLLABORATORS_BY_TOOL = "skill.getAllCollaboratorsIdByTool";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_ALL_COLLABORATORID_BY_TOOL ="skill.getAllCollaboratorIdByToolIdAndCollabList";


// TOOL REQUESTS

/**
 * Constant request id to get a collaborator 
 */
public final static String ADD_TOOL = "tool.add";

/**
 * Constant request id to get a collaborator 
 */
public final static String SAVE_TOOL = "tool.save";

/**
 * Constant request id to get a collaborator 
 */
public final static String CHECK_TOOL = "tool.check";
	
/**
 * Constant request id to get a collaborator 
 */
public final static String GET_ALL_TOOL_BY_CONCEPT = "tool.getAllToolByConcept";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_ALL_TOOL = "tool.getAll";

/**
 * Constant request id to get a collaborator 
 */
public static final String DELETE_TOOL = "tool.delete";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_TOOL_BY_NAME ="tool.getToolByName";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_TOOL ="tool.get";


// VSKILL REQUESTS

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_ALL_SKILL_COLLAB = "vskillCollab.getAllSkillCollab";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_SKILL_BY_TOOL = "vskill.getByTool";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_CONCEPT_BY_CATEGORY = "vskill.getConceptByCategory";

/**
 * Constant request id to get a collaborator 
 */
public final static String GET_TOOL_BY_CONCEPT = "vskill.getToolByConcept";	

/**
 * Constant request id to get a collaborator 
 */
public final static String DELETE_MISSION = "mission.delete";

// TASK

public final static String GET_TASK_BY_ID = "task.get";
}