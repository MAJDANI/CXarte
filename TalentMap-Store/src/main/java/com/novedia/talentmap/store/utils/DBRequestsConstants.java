package com.novedia.talentmap.store.utils;
/**
 * This class provides static constants ids matching.
 * the sqlmap-[entity].xml requests used by IBATIS
 * @author j.marie-sainte
 */
public final class DBRequestsConstants {

	// COLLEAGUE REQUESTS

	/**
	 * Constant request to delete colleague
	 */
	public static final String DELETE_COLLEAGUE = "colleague.delete";
	
/**
 * Constant request to get colleague by a list of tool id
 */
public static final String GET_ALL_COLLEAGUES_BY_LIST_TOOL_ID = "colleague.getAllColleaguesByListToolId";


/**
 * Constant request id to get a collaborator.
 */
public static final String GET_COLLEAGUE = "colleague.get";

/**
 * Constant request id to get all the collaborators.
 */
public static final String GET_ALL_COLLEAGUES = "colleague.getAll";

/**
 * Constant request to delete a colleague.
 */
public static final String DELETE_colleague = "colleague.delete";

/**
 * Constant request to update a colleague.
 */
public static final String SAVE_COLLEAGUE = "colleague.save";

public static final String ERASE_MANAGER_COLLEAGUE = "colleague.eraseManager";

/**
 * Constant request to get a colleague by lastName.
 */
public static final String GET_ALL_COLLEAGUES_BY_LASTNAME="colleague.getAllColleaguesByLastName";

/**
 * Constant request to get a colleague by client name.
 */
public static final String GET_ALL_COLLEAGUES_BY_CLIENT="colleague.getAllColleaguesByClient";

/**
 * Constant request to get colleague by manager id
 */
public static final String GET_ALL_COLLABORATOR_BY_MANAGER_ID = "colleague.getAllCollaboratorsByManagerId";

/**
 * Constant request to count the mail already existing in db
 */
public static final String COUNT_COLLEAGUE_BY_MAIL = "colleague.countMail";

/**
 * Constant request to count mail already existing in db for other colleagues
 */
public static final String COUNT_COLLEAGUE_BY_MAIL_AND_ID = "colleague.countMailForColleagueId";

/**
 * Constant request to add a colleague from a registration
 */
public static final String ADD_COLLEAGUE_FROM_REGISTRATION = "colleague.addColleagueFromRegistration";
/**
 * Constant request to get all colleagues by a list of colleague's id
 */
public static final String GET_ALL_COLLEAGUE_BY_COLL_ID_LIST = "colleague.getAllColleagueByColleagueIdList";
/**
 * Constant request to get all consultant manager
 */
public static final String GET_ALL_CONSULTANT_MANAGER = "colleague.getAllConsultantManager";

/**
 * Constant request to get all Business Engineer
 */
public static final String GET_ALL_BUSINESS_ENGINEER = "businessEngineer.getAll";

/**
 * Constant request to get all Business Engineer
 */
public static final String GET_BUSINESS_ENGINEER_BY_ID = "businessEngineer.get";

/**
 * Constant request to get a category.
 */
public static final String GET_CATEGORY = "category.get";

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
public static final String CHECK_CATEGORY_OBJECT = "category.checkObject";

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
public static final String CHECK_CONCEPT_OBJECT = "concept.checkObject";

/**
 * This constant is used to retrieve a concept by name.
 */
public static final String GET_CONCEPT_BY_NAME = "concept.getByName";

/**
 * This constant is used to retrieve a concept by name.
 */
public static final String GET_ALL_CONCEPT_BY_CATEGORY = "concept.getByCategory";

// MANAGER REQUESTS
/**
 * This constant is used to retrieve a manager by the id.
 */
public static final String GET_MANAGER = "colleague.getManager";

/**
 * This constant is used to retrieve all managers.
 */
public static final String GET_ALL_MANAGER = "colleague.getAllManagers";

/**
 * This constant is used to retrieve a colleague by manager id.
 */
public static final String GET_COLLABORATORS_BY_MANAGER_ID = "colleague.getAllCollaboratorsByManagerId";

//CLIENT REQUESTS

public static final String GET_ALL_CLIENTS = "client.getAll";


// MISSION REQUESTS

/**
 * This constant is used to retrieve all mission for a colleague id.
 */
public static final String GET_ALL = "mission.getAll";

/**
 * This constant is used to retrieve tools of a mission.
 */
public static final String GET_MISSION_TOOLS = "mission.getMissionTools";

/**
 * This constant is used to retrieve a mission.
 */
public static final String GET_MISSION = "mission.get";

/**
 * This constant is used to retrieve all missions for a clolleagueId, 
 * ordered by START_DATE.
 */
public static final String GET_ALL_MISSION_BY_COLLEAGUE_ID = "mission.getAllByColleague";

/**
 * Constant request to get the last mission from a colleague id
 */
public static final String GET_LAST_MISSION_BY_COLLEAGUE_ID = "mission.getLastMissionByColleague";

/**
 * Constant request to add a mission.
 */
public static final String ADD_MISSION = "mission.add";

/**
 * Constant request to update a colleague.
 */
public static final String SAVE_MISSION = "mission.save";

/**
 *  Constant request to get all clients name
 */
public static final String GET_ALL_CLIENTS_NAME = "mission.getAllClientsName";

//MISSION_TOOL REQUESTS

/**
 *  Constant request to add a tool to a mission
 */
public static final String ADD_MISSION_TOOL = "mission_tool.add";


/**
 *  Constant request to delete tools for a mission
 */
public static final String DELETE_MISSION_TOOL = "mission_tool.delete";

// PROFILE REQUESTS

/**
 * This constant is used to retrieve all profile.
 */
public static final String GET_ALL_PROFILE = "profile.getAll";

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
 * This constant is used to retrieve a profile by type.
 */
public static final String GET_PROFILE_BY_TYPE = "profile.getByType";


// SKILL REQUEST

/**
 * Constant request to get a skill.
 */
public static final String GET_SKILL = "skill.get";

/**
 * This constant is used to retrieve all collaborator skill.
 */
public static final String GET_ALL_COLLABORATOR_SKILL ="skill.getAllCollaboratorSkill";

/**
 * This constant is used to retrieve all skills by collaborator.
 */
//public static final String GET_ALL_SKILLS_BY_COLLABORATOR = "skill.getAllSkillsByCollaborator";

/**
 * This constant is used to retrieve all skill.
 */
public static final String GET_ALL_SKILL = "skill.getAll";

/**
 * Constant request to add a skill.
 */
public static final String ADD_SKILL = "skill.add";

/**
 * Constant request to update a skill.
 */
public static final String SAVE_SKILL = "skill.save";

/**
 * Constant request to get all collaborators by a list of tool.
 */
public static final String GET_ALL_COLLEAGUE_ID_BY_TOOL_ID = "skill.getAllColleagueIdByToolId";

/**
 * Constant request to get all collaborators by a list of tool.
 */
public static final String GET_ALL_COLLEAGUE_ID_BY_LIST_OF_TOOL_ID = "skill.getAllColleagueIdByListToolId";

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
public static final String CHECK_TOOL_OBJECT = "tool.checkObject";

/**
 * This constant is used to retrieve all tool by concept.
 */
public static final String GET_ALL_TOOL_BY_CONCEPT = "tool.getAllToolByConcept";


/**
 * This constant is used to retrieve all tool by concept.
 */
public static final String GET_TOOLS_BY_CONCEPT = "tool.getAllByConcept";


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
public static final String GET_TOOL_BY_NAME = "tool.getByName";

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
public static final String GET_SKILL_BY_TOOL = "vskill.getSkillByTool";

/**
 * This constant is used to retrieve all VSkill ordered by category and concept.
 */
public static final String GET_ALL_VSKILL_ORDERED = "vskill.getAllVSkillOrdered";

/**
 * This constant is used to retrieve a concept by category.
 */
public static final String GET_CONCEPT_BY_CATEGORY = "vskill.getConceptByCategory";

/**
 * Constant request to delete a collaborator.
 */
public static final String DELETE_MISSION = "mission.delete";

/**
 * This constant is used to retrieve a task by id.
 */
public static final String GET_TASK_BY_ID = "task.get";

// AUTHENTIFICATION REQUESTS
public static final String GET_AUTHENTIFICATION = "authentication.getAuthentification";

public static final String GET_ALL_AUTHENTIFICATION = "authentication.getAll";

public static String SAVE_AUTHENTIFICATION = "authentication.save";

public static final String ADD_USER_AUTHENTIFICATION = "authentication.addUser";

public static final String CHECK_AUTHENTIFICATION = "authentication.check";

public static final String GET_COLLAB_BY_LOGIN = "authentication.getCollabByLogin";

public static final String GET_BY_ID_AUTHENTIFICATION = "authentication.get";

public static final String ADD_USER_FROM_REGISTRATION = "authentication.addUserFromRegistration";

public static final String AUTHENTIFICATION_COUNT_LOGIN= "authentication.countLogin";





}