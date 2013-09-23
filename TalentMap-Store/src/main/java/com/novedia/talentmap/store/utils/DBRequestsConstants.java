package com.novedia.talentmap.store.utils;

/**
 * This class provides static constants ids matching. the sqlmap-[entity].xml
 * requests used by IBATIS
 * 
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
	public static final String GET_ALL_COLLEAGUES_BY_NAME = "colleague.getAllColleaguesByName";

	/**
	 * Constant request to get a colleague by client name.
	 */
	public static final String GET_ALL_COLLEAGUES_BY_CLIENT = "colleague.getAllColleaguesByClient";

	/**
	 * Constant request to get CM colleagues by client name.
	 */
	public static final String GET_CM_COLLEAGUES_BY_CLIENT = "colleague.getCmColleaguesByClient";

	/**
	 * Constant request to get CM colleagues by last name.
	 */
	public static final String GET_CM_COLLEAGUES_BY_NAME = "colleague.getCmColleaguesByName";

	/**
	 * Constant request to get colleague by manager id
	 */
	public static final String GET_ALL_COLLABORATOR_BY_MANAGER_ID = "colleague.getAllCollaboratorsByManagerId";

	/**
	 * Constant request to count the mail already existing in db
	 */
	public static final String COUNT_COLLEAGUE_BY_MAIL = "colleague.countMail";

	/**
	 * Constant request to count mail already existing in db for other
	 * colleagues
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
	 * Constant request to get all business engineers 
	 */
	public static final String GET_ALL_BUSINESS_ENGINEERS = "colleague.getAllBusinessEngineers";

	/**
	 * Constant request to get all Business Engineer
	 */
	public static final String GET_ALL_BUSINESS_ENGINEER = "businessEngineer.getAll";

	/**
	 * Constant request to get all Business Engineer
	 */
	// public static final String GET_BUSINESS_ENGINEER_BY_ID =
	// "businessEngineer.get";

	/**
	 * This constant is used to retrieve a category.
	 */
	public static final String GET_CATEGORY = "category.getCategory";
	
	/**
	 * This constant is used to retrieve all category.
	 */
	public static final String GET_ALL_CATEGORY = "category.getAll";

	/**
	 * Constant request to insert the category.
	 */
	public static final String ADD_CATEGORY = "category.add";

	/**
	 * Constant request to check a category.
	 */
	public static final String CHECK_CATEGORY_OBJECT = "category.checkObject";

	/**
	 * Constant request to update category.
	 */
	public static final String SAVE_CATEGORY = "category.save";

	/**
	 * Constant request to delete category.
	 */
	public static final String DELETE_CATEGORY = "category.delete";

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

	// CLIENT REQUESTS

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
	 * Constant request to get all clients name
	 */
	public static final String GET_ALL_CLIENTS_NAME = "mission.getAllClientsName";

	// MISSION_TOOL REQUESTS

	/**
	 * Constant request to add a tool to a mission
	 */
	public static final String ADD_MISSION_TOOL = "mission_tool.add";

	/**
	 * Constant request to delete tools for a mission
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
	 * This constant is used to retrieve a profile by name.
	 */
	public static final String GET_PROFILE_BY_NAME = "profile.getByName";

	// SKILL REQUEST

	/**
	 * Constant request to get a skill.
	 */
	public static final String GET_SKILL = "skill.get";

	/**
	 * This constant is used to retrieve all collaborator skill.
	 */
	public static final String GET_ALL_COLLABORATOR_SKILL = "skill.getAllCollaboratorSkill";

	/**
	 * This constant is used to retrieve all skills by collaborator.
	 */
	// public static final String GET_ALL_SKILLS_BY_COLLABORATOR =
	// "skill.getAllSkillsByCollaborator";

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
//	public static final String SAVE_SKILL = "skill.save";
	public static final String SAVE_SKILL = "skill.replace";

	/**
	 * Constant request to get all collaborators by a tool.
	 */
	public static final String GET_ALL_COLLEAGUE_ID_BY_TOOL_ID = "skill.getAllColleagueIdByToolId";

	/**
	 * Constant request to get all CM collaborators by a tool.
	 */
	public static final String GET_CM_COLLEAGUE_ID_BY_TOOL_ID = "skill.getCmColleagueIdByToolId";
	
	/**
	 * Constant request to get all collaborators by a concept.
	 */
	public static final String GET_ALL_COLLEAGUE_ID_BY_CONCEPT_ID = "skill.getAllColleagueIdByConceptId";
	
	/**
	 * Constant request to get all CM collaborators by a concept.
	 */
	public static final String GET_CM_COLLEAGUE_ID_BY_CONCEPT_ID = "skill.getCmColleagueIdByConceptId";
	
	/**
	 * Constant request to get all collaborators by a category.
	 */
	public static final String GET_ALL_COLLEAGUE_ID_BY_CATEGORY_ID = "skill.getAllColleagueIdByCategoryId";
	
	/**
	 * Constant request to get all CM collaborators by a category.
	 */
	public static final String GET_CM_COLLEAGUE_ID_BY_CATEGORY_ID = "skill.getCmColleagueIdByCategoryId";

	/**
	 * Constant request to get all collaborators by a list of tool.
	 */
	public static final String GET_ALL_COLLEAGUE_ID_BY_LIST_OF_TOOL_ID = "skill.getAllColleagueIdByListToolId";

	/**
	 * Constant request to get CM collaborators by a list of tool.
	 */
	public static final String GET_CM_COLLEAGUE_ID_BY_LIST_OF_TOOL_ID = "skill.getCmColleagueIdByListToolId";

	/**
	 * This constant is used to retrieve all collaborator by tool.
	 */
	public static final String GET_ALL_COLLABORATORID_BY_TOOL = "skill.getAllCollaboratorIdByToolIdAndCollabList";

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
	 * This constant is used to retrieve a tool.
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
	 * This constant is used to retrieve all VSkill ordered by category and
	 * concept.
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

	public static final String CHECK_AUTHENTIFICATION = "authentication.check";

	public static final String GET_COLLAB_BY_LOGIN = "authentication.getCollabByLogin";

	public static final String ADD_USER_FROM_REGISTRATION = "authentication.addUserFromRegistration";

	public static final String AUTHENTIFICATION_COUNT_LOGIN = "authentication.countLogin";

	// NOTIFICATION REQUESTS

	public static final String SAVE_NOTIFICATION = "user_notification.saveNotification";
	public static final String GET_ALL_NOTIFICATION_BY_MANAGER_ID = "user_notification.getAllNotificationByManagerId";
	public static final String GET_ALL_FREQUENCY_CHOICES = "user_notification.getAllFrequencyChoices";
	public static final String GET_CM_FREQUENCY_OPTION = "user_notification.getCmFrequencyOption";
	public static final String SAVE_CM_FREQUENCY_OPTION = "user_notification.saveFrequencyOption";
	public static final String ADD_CM_FREQUENCY_OPTION = "user_notification.addFrequencyOption";

	// EAE REQUEST
	public static final String GET_EAE = "eae.get";
	public static final String ADD_EAE = "eae.add";
	public static final String SAVE_EAE = "eae.save";
	public static final String DELETE_EAE = "eae.delete";
	public static final String GET_ONGOING_EAE_FOR_CM = "eae.getOngoingEAEForManager";
	public static final String GET_WITHOUT_ONGOING_EAE_FOR_CM = "eae.getCollabWithoutOngoingEAEForManager";
	public static final String GET_HISTORY_EAE_FOR_COLLAB = "eae.getHistoryEAEForCollab";
	public static final String GET_EAE_GENERALITY = "eae.getEAEGenerality";
	public static final String GET_EAE_RESULTS = "eae.getEAEResults";
	public static final String GET_EAE_SYNTHESIS = "eae.getEAESynthesis";
	public static final String GET_OPEN_EAE_ID = "eae.getOpenEAEIdForColleague";
	public static final String SAVE_EAE_SALARY = "eae.saveEAESalary";
	public static final String SAVE_EAE_RESULTS = "eae.saveEAEResults";
	public static final String SAVE_EAE_SYNTHESIS = "eae.saveEAESynthesis";
	public static final String GET_EAE_COLL_RESUME_FOR_CM = "eae.getEAEColleagueResumeForCM";
	
	
	// OBJECTIVE REQUEST
	public static final String GET_OBJECTIVE = "objective.get";
	public static final String ADD_OBJECTIVE = "objective.add";
	public static final String SAVE_OBJECTIVE = "objective.save";
	public static final String DELETE_OBJECTIVE = "objective.delete";
	public static final String GET_OBJECTIVES_FOR_EAE = "objective.getObjectivesByEAEId";
	public static final String GET_PRECEDENT_OBJECTIVES_FOR_EAE = "objective.getPrecedentObjectivesByEAEId";
}
//=======
//package com.novedia.talentmap.store.utils;
//
///**
// * This class provides static constants ids matching. the sqlmap-[entity].xml
// * requests used by IBATIS
// * 
// * @author j.marie-sainte
// */
//public final class DBRequestsConstants {
//
//	// COLLEAGUE REQUESTS
//
//	/**
//	 * Constant request to delete colleague
//	 */
//	public static final String DELETE_COLLEAGUE = "colleague.delete";
//
//	/**
//	 * Constant request to get colleague by a list of tool id
//	 */
//	public static final String GET_ALL_COLLEAGUES_BY_LIST_TOOL_ID = "colleague.getAllColleaguesByListToolId";
//
//	/**
//	 * Constant request id to get a collaborator.
//	 */
//	public static final String GET_COLLEAGUE = "colleague.get";
//
//	/**
//	 * Constant request id to get all the collaborators.
//	 */
//	public static final String GET_ALL_COLLEAGUES = "colleague.getAll";
//
//	/**
//	 * Constant request to delete a colleague.
//	 */
//	public static final String DELETE_colleague = "colleague.delete";
//
//	/**
//	 * Constant request to update a colleague.
//	 */
//	public static final String SAVE_COLLEAGUE = "colleague.save";
//
//	public static final String ERASE_MANAGER_COLLEAGUE = "colleague.eraseManager";
//
//	/**
//	 * Constant request to get a colleague by lastName.
//	 */
//	public static final String GET_ALL_COLLEAGUES_BY_NAME = "colleague.getAllColleaguesByName";
//
//	/**
//	 * Constant request to get a colleague by client name.
//	 */
//	public static final String GET_ALL_COLLEAGUES_BY_CLIENT = "colleague.getAllColleaguesByClient";
//
//	/**
//	 * Constant request to get CM colleagues by client name.
//	 */
//	public static final String GET_CM_COLLEAGUES_BY_CLIENT = "colleague.getCmColleaguesByClient";
//
//	/**
//	 * Constant request to get CM colleagues by last name.
//	 */
//	public static final String GET_CM_COLLEAGUES_BY_NAME = "colleague.getCmColleaguesByName";
//
//	/**
//	 * Constant request to get colleague by manager id
//	 */
//	public static final String GET_ALL_COLLABORATOR_BY_MANAGER_ID = "colleague.getAllCollaboratorsByManagerId";
//
//	/**
//	 * Constant request to count the mail already existing in db
//	 */
//	public static final String COUNT_COLLEAGUE_BY_MAIL = "colleague.countMail";
//
//	/**
//	 * Constant request to count mail already existing in db for other
//	 * colleagues
//	 */
//	public static final String COUNT_COLLEAGUE_BY_MAIL_AND_ID = "colleague.countMailForColleagueId";
//
//	/**
//	 * Constant request to add a colleague from a registration
//	 */
//	public static final String ADD_COLLEAGUE_FROM_REGISTRATION = "colleague.addColleagueFromRegistration";
//	/**
//	 * Constant request to get all colleagues by a list of colleague's id
//	 */
//	public static final String GET_ALL_COLLEAGUE_BY_COLL_ID_LIST = "colleague.getAllColleagueByColleagueIdList";
//	/**
//	 * Constant request to get all consultant manager
//	 */
//	public static final String GET_ALL_CONSULTANT_MANAGER = "colleague.getAllConsultantManager";
//	
//	/**
//	 * Constant request to get all business engineers 
//	 */
//	public static final String GET_ALL_BUSINESS_ENGINEERS = "colleague.getAllBusinessEngineers";
//
//	/**
//	 * Constant request to get all Business Engineer
//	 */
//	public static final String GET_ALL_BUSINESS_ENGINEER = "businessEngineer.getAll";
//
//	/**
//	 * Constant request to get all Business Engineer
//	 */
//	// public static final String GET_BUSINESS_ENGINEER_BY_ID =
//	// "businessEngineer.get";
//
//	/**
//	 * This constant is used to retrieve a category.
//	 */
//	public static final String GET_CATEGORY = "category.getCategory";
//	
//	/**
//	 * This constant is used to retrieve all category.
//	 */
//	public static final String GET_ALL_CATEGORY = "category.getAll";
//
//	/**
//	 * Constant request to insert the category.
//	 */
//	public static final String ADD_CATEGORY = "category.add";
//
//	/**
//	 * Constant request to check a category.
//	 */
//	public static final String CHECK_CATEGORY_OBJECT = "category.checkObject";
//
//	/**
//	 * Constant request to update category.
//	 */
//	public static final String SAVE_CATEGORY = "category.save";
//
//	/**
//	 * Constant request to delete category.
//	 */
//	public static final String DELETE_CATEGORY = "category.delete";
//
//	// CONCEPT REQUEST
//	/**
//	 * This constant is used to retrieve a concept.
//	 */
//	public static final String GET_CONCEPT = "concept.get";
//
//	/**
//	 * This constant is used to retrieve all concept.
//	 */
//	public static final String GET_ALL_CONCEPT = "concept.getAll";
//
//	/**
//	 * This constant is used to retrieve a concept by category id.
//	 */
//	public static final String GET_ALL_CONCEPT_BY_CATEGORYID = "concept.getAllConceptByCategoryId";
//
//	/**
//	 * Constant request id to add a concept.
//	 */
//	public static final String ADD_CONCEPT = "concept.add";
//
//	/**
//	 * Constant request to update a concept.
//	 */
//	public static final String SAVE_CONCEPT = "concept.save";
//
//	/**
//	 * Constant request to delete concept.
//	 */
//	public static final String DELETE_CONCEPT = "concept.delete";
//
//	/**
//	 * Constant request to check a concept.
//	 */
//	public static final String CHECK_CONCEPT_OBJECT = "concept.checkObject";
//
//	/**
//	 * This constant is used to retrieve a concept by name.
//	 */
//	public static final String GET_CONCEPT_BY_NAME = "concept.getByName";
//
//	/**
//	 * This constant is used to retrieve a concept by name.
//	 */
//	public static final String GET_ALL_CONCEPT_BY_CATEGORY = "concept.getByCategory";
//
//	// MANAGER REQUESTS
//	/**
//	 * This constant is used to retrieve a manager by the id.
//	 */
//	public static final String GET_MANAGER = "colleague.getManager";
//
//	/**
//	 * This constant is used to retrieve all managers.
//	 */
//	public static final String GET_ALL_MANAGER = "colleague.getAllManagers";
//
//	/**
//	 * This constant is used to retrieve a colleague by manager id.
//	 */
//	public static final String GET_COLLABORATORS_BY_MANAGER_ID = "colleague.getAllCollaboratorsByManagerId";
//
//	// CLIENT REQUESTS
//
//	public static final String GET_ALL_CLIENTS = "client.getAll";
//
//	// MISSION REQUESTS
//
//	/**
//	 * This constant is used to retrieve all mission for a colleague id.
//	 */
//	public static final String GET_ALL = "mission.getAll";
//
//	/**
//	 * This constant is used to retrieve tools of a mission.
//	 */
//	public static final String GET_MISSION_TOOLS = "mission.getMissionTools";
//
//	/**
//	 * This constant is used to retrieve a mission.
//	 */
//	public static final String GET_MISSION = "mission.get";
//
//	/**
//	 * This constant is used to retrieve all missions for a clolleagueId,
//	 * ordered by START_DATE.
//	 */
//	public static final String GET_ALL_MISSION_BY_COLLEAGUE_ID = "mission.getAllByColleague";
//
//	/**
//	 * Constant request to get the last mission from a colleague id
//	 */
//	public static final String GET_LAST_MISSION_BY_COLLEAGUE_ID = "mission.getLastMissionByColleague";
//
//	/**
//	 * Constant request to add a mission.
//	 */
//	public static final String ADD_MISSION = "mission.add";
//
//	/**
//	 * Constant request to update a colleague.
//	 */
//	public static final String SAVE_MISSION = "mission.save";
//
//	/**
//	 * Constant request to get all clients name
//	 */
//	public static final String GET_ALL_CLIENTS_NAME = "mission.getAllClientsName";
//
//	// MISSION_TOOL REQUESTS
//
//	/**
//	 * Constant request to add a tool to a mission
//	 */
//	public static final String ADD_MISSION_TOOL = "mission_tool.add";
//
//	/**
//	 * Constant request to delete tools for a mission
//	 */
//	public static final String DELETE_MISSION_TOOL = "mission_tool.delete";
//
//	// PROFILE REQUESTS
//
//	/**
//	 * This constant is used to retrieve all profile.
//	 */
//	public static final String GET_ALL_PROFILE = "profile.getAll";
//
//	/**
//	 * This constant is used to retrieve a profile.
//	 */
//	public static final String GET_PROFILE = "profile.get";
//
//	/**
//	 * This constant is used to retrieve a profile by name.
//	 */
//	public static final String GET_PROFILE_BY_NAME = "profile.getByName";
//
//	// SKILL REQUEST
//
//	/**
//	 * Constant request to get a skill.
//	 */
//	public static final String GET_SKILL = "skill.get";
//
//	/**
//	 * This constant is used to retrieve all collaborator skill.
//	 */
//	public static final String GET_ALL_COLLABORATOR_SKILL = "skill.getAllCollaboratorSkill";
//
//	/**
//	 * This constant is used to retrieve all skills by collaborator.
//	 */
//	// public static final String GET_ALL_SKILLS_BY_COLLABORATOR =
//	// "skill.getAllSkillsByCollaborator";
//
//	/**
//	 * This constant is used to retrieve all skill.
//	 */
//	public static final String GET_ALL_SKILL = "skill.getAll";
//
//	/**
//	 * Constant request to add a skill.
//	 */
//	public static final String ADD_SKILL = "skill.add";
//
//	/**
//	 * Constant request to update a skill.
//	 */
////	public static final String SAVE_SKILL = "skill.save";
//	public static final String SAVE_SKILL = "skill.replace";
//
//	/**
//	 * Constant request to get all collaborators by a tool.
//	 */
//	public static final String GET_ALL_COLLEAGUE_ID_BY_TOOL_ID = "skill.getAllColleagueIdByToolId";
//
//	/**
//	 * Constant request to get all CM collaborators by a tool.
//	 */
//	public static final String GET_CM_COLLEAGUE_ID_BY_TOOL_ID = "skill.getCmColleagueIdByToolId";
//	
//	/**
//	 * Constant request to get all collaborators by a concept.
//	 */
//	public static final String GET_ALL_COLLEAGUE_ID_BY_CONCEPT_ID = "skill.getAllColleagueIdByConceptId";
//	
//	/**
//	 * Constant request to get all CM collaborators by a concept.
//	 */
//	public static final String GET_CM_COLLEAGUE_ID_BY_CONCEPT_ID = "skill.getCmColleagueIdByConceptId";
//	
//	/**
//	 * Constant request to get all collaborators by a category.
//	 */
//	public static final String GET_ALL_COLLEAGUE_ID_BY_CATEGORY_ID = "skill.getAllColleagueIdByCategoryId";
//	
//	/**
//	 * Constant request to get all CM collaborators by a category.
//	 */
//	public static final String GET_CM_COLLEAGUE_ID_BY_CATEGORY_ID = "skill.getCmColleagueIdByCategoryId";
//
//	/**
//	 * Constant request to get all collaborators by a list of tool.
//	 */
//	public static final String GET_ALL_COLLEAGUE_ID_BY_LIST_OF_TOOL_ID = "skill.getAllColleagueIdByListToolId";
//
//	/**
//	 * Constant request to get CM collaborators by a list of tool.
//	 */
//	public static final String GET_CM_COLLEAGUE_ID_BY_LIST_OF_TOOL_ID = "skill.getCmColleagueIdByListToolId";
//
//	/**
//	 * This constant is used to retrieve all collaborator by tool.
//	 */
//	public static final String GET_ALL_COLLABORATORID_BY_TOOL = "skill.getAllCollaboratorIdByToolIdAndCollabList";
//
//	// TOOL REQUESTS
//
//	/**
//	 * Constant request to add a tool.
//	 */
//	public static final String ADD_TOOL = "tool.add";
//
//	/**
//	 * Constant request to update a collaborator.
//	 */
//	public static final String SAVE_TOOL = "tool.save";
//
//	/**
//	 * Constant request id to check a tool.
//	 */
//	public static final String CHECK_TOOL_OBJECT = "tool.checkObject";
//
//	/**
//	 * This constant is used to retrieve all tool by concept.
//	 */
//	public static final String GET_ALL_TOOL_BY_CONCEPT = "tool.getAllToolByConcept";
//
//	/**
//	 * This constant is used to retrieve all tool by concept.
//	 */
//	public static final String GET_TOOLS_BY_CONCEPT = "tool.getAllByConcept";
//
//	/**
//	 * This constant is used to retrieve all tool.
//	 */
//	public static final String GET_ALL_TOOL = "tool.getAll";
//
//	/**
//	 * Constant request to delete a tool.
//	 */
//	public static final String DELETE_TOOL = "tool.delete";
//
//	/**
//	 * This constant is used to retrieve a tool by name.
//	 */
//	public static final String GET_TOOL_BY_NAME = "tool.getByName";
//
//	/**
//	 * This constant is used to retrieve a tool.
//	 */
//	public static final String GET_TOOL = "tool.get";
//
//	// VSKILL REQUESTS
//
//	/**
//	 * This constant is used to retrieve all skill by collaborator.
//	 */
//	public static final String GET_ALL_SKILL_COLLAB = "vskillCollab.getAllSkillCollab";
//
//	/**
//	 * This constant is used to retrieve a skill by tool.
//	 */
//	public static final String GET_SKILL_BY_TOOL = "vskill.getSkillByTool";
//
//	/**
//	 * This constant is used to retrieve all VSkill ordered by category and
//	 * concept.
//	 */
//	public static final String GET_ALL_VSKILL_ORDERED = "vskill.getAllVSkillOrdered";
//
//	/**
//	 * This constant is used to retrieve a concept by category.
//	 */
//	public static final String GET_CONCEPT_BY_CATEGORY = "vskill.getConceptByCategory";
//
//	/**
//	 * Constant request to delete a collaborator.
//	 */
//	public static final String DELETE_MISSION = "mission.delete";
//
//	/**
//	 * This constant is used to retrieve a task by id.
//	 */
//	public static final String GET_TASK_BY_ID = "task.get";
//
//	// AUTHENTIFICATION REQUESTS
//	public static final String GET_AUTHENTIFICATION = "authentication.getAuthentification";
//
//	public static final String GET_ALL_AUTHENTIFICATION = "authentication.getAll";
//
//	public static String SAVE_AUTHENTIFICATION = "authentication.save";
//
//	public static final String CHECK_AUTHENTIFICATION = "authentication.check";
//
//	public static final String GET_COLLAB_BY_LOGIN = "authentication.getCollabByLogin";
//
//	public static final String ADD_USER_FROM_REGISTRATION = "authentication.addUserFromRegistration";
//
//	public static final String AUTHENTIFICATION_COUNT_LOGIN = "authentication.countLogin";
//
//	// NOTIFICATION REQUESTS
//
//	public static final String SAVE_NOTIFICATION = "user_notification.saveNotification";
//	public static final String GET_ALL_NOTIFICATION_BY_MANAGER_ID = "user_notification.getAllNotificationByManagerId";
//	public static final String GET_ALL_FREQUENCY_CHOICES = "user_notification.getAllFrequencyChoices";
//	public static final String GET_CM_FREQUENCY_OPTION = "user_notification.getCmFrequencyOption";
//	public static final String SAVE_CM_FREQUENCY_OPTION = "user_notification.saveFrequencyOption";
//	public static final String ADD_CM_FREQUENCY_OPTION = "user_notification.addFrequencyOption";
//
//	// EAE REQUEST
//	public static final String GET_EAE = "eae.get";
//	public static final String ADD_EAE = "eae.add";
//	public static final String SAVE_EAE = "eae.save";
//	public static final String DELETE_EAE = "eae.delete";
//	public static final String GET_ONGOING_EAE_FOR_CM = "eae.getOngoingEAEForManager";
//	public static final String GET_WITHOUT_ONGOING_EAE_FOR_CM = "eae.getCollabWithoutOngoingEAEForManager";
//	public static final String GET_HISTORY_EAE_FOR_COLLAB = "eae.getHistoryEAEForCollab";
//	public static final String GET_EAE_GENERALITY = "eae.getEAEGenerality";
//	public static final String GET_EAE_RESULTS = "eae.getEAEResults";
//	public static final String GET_OPEN_EAE_ID = "eae.getOpenEAEIdForColleague";
//	public static final String SAVE_EAE_SALARY = "eae.saveEAESalary";
//	public static final String SAVE_EAE_RESULTS = "eae.saveEAEResults";
//	
//	
//	// OBJECTIVE REQUEST
//	public static final String GET_OBJECTIVE = "objective.get";
//	public static final String ADD_OBJECTIVE = "objective.add";
//	public static final String SAVE_OBJECTIVE = "objective.save";
//	public static final String DELETE_OBJECTIVE = "objective.delete";
//	public static final String GET_OBJECTIVES_FOR_EAE = "objective.getObjectivesByEAEId";
//	
//>>>>>>> branch 'master' of https://github.com/Jean-Max/NovTalentMap.git
//}
