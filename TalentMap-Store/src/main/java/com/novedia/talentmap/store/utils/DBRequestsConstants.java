package com.novedia.talentmap.store.utils;
/**
 * This class contains static constants representing the request used to fetch TMP datas.
 * @author j.marie-sainte
 *
 */
public final class DBRequestsConstants {
	
	/**
	* Request used to get a category in TMP data base model.
	*/
	public final static String GET_CATEGORY_REQUEST = "category.getCategory";

	/**
	* Request used to get all category in TMP data base model.
	*/
	public final static String GET_ALL_CATEGORY_REQUEST = "category.getAllCategory";
	
	/**
	 * Request isert the category
	 */
	public final static String  INSERT_CATEGORY_REQUEST = "category.insertCategory";
	/**
	 * check category
	 */
	public final static String CATEGORY_REQUEST_CHECK = "category.checkCategory";
	
	/**
	 * update category
	 */
	public final static String  CATEGORY_REQUEST_UPDATE ="category.updateCategory";
	/**
	 * delete category
	 */
	public final static String CATEGORY_REQUEST_DELETE ="category.deleteCategory";
	/**
	 * 
	 */
	public final static String GET_COLLABORATOR_REQUEST = "collaborator.getCollaborator";
	public final static String GET_ALL_COLLABORATOR_REQUEST = "collaborator.getAllCollaborator";
	public final static String UPDATE_COLLABORATOR_REQUEST = "collaborator.updateCollaborateur";
	public final static String GET_ALL_COLLABORATOR_BYLASTNAME_REQUEST="collaborator.getAllCollaboratorByLastName";
	public final static String GET_ALL_COLLABORATOR_BYLISTID_REQUEST="collaborator.getAllCollaboratorsByListId";
	
	public final static String GET_CONCEPT = "concept.getConcept";
	public final static String GET_ALL_CONCEPT = "concept.getAllConcept";
	public final static String GET_ALL_CONCEPT_BY_CATEGORYID = "concept.getAllConceptByCategoryId";
	public final static String CONCEPT_INSERT_REQUEST = "concept.insertConcept";
	public final static String CONCEPT_UPDATE_REQUEST = "concept.updateConcept";
	public final static String CONCEPT_DELETE_REQUEST = "concept.deleteConcept";
	public final static String CONCEPT_REQUEST_CHECK ="concept.checkConcept";
	
	public final static String GET_MANAGER = "manager.getManager";
	public final static String GET_ALL_COLLABORATOR_MANAGERID="manager.getAllCollaboratorsByManagerId";
	
	public final static String GET_ALL_MISSION = "mission.getAllMission";
	public final static String GET_MISSION ="mission.getMission";
	public final static String INSERT_MISSION_REQUEST = "mission.insertMission";
	public final static String UPDATE_MISSION_REQUEST = "mission.updateMission";
	public final static String ADD_MISSION_REQUEST = "mission.addMission";	
	
	public final static String GET_ALL_PROFIL = "profile.getAllProfile";
	public final static String GET_PROFIL ="profile.getProfile";
	public final static String GET_PROFIL_BYTYPE = "profile.getByType";
	
	public final static String GET_ALL_COLLABORATOR_SKILL ="skill.getAllCollaboratorSkill";
	public final static String GET_ALL_SKILL ="skill.getSkill";
	public final static String INSERT_SKILL_REQUEST = "skill.insertSkill";
	public final static String UPDATE_SKILL_REQUEST = "skill.updateSkill";
	public final static String GET_ALL_COLLABORATORS_BY_TOOLIB_REQUEST = "skill.getAllCollaboratorsIdByToolId";
	public final static String GET_ALL_COLLABORATORID_BY_TOOL_REQUEST ="skill.getAllCollaboratorIdByToolIdAndCollabList";
	
	public final static String TOOL_INSERT_REQUEST = "tool.insertTool";
	public final static String TOOL_UPDATE_REQUEST = "tool.updateTool";
	public final static String TOOL_REQUEST_CHECK = "tool.checkTool";
	public final static String TOOL_DELETE_REQUEST = "tool.deleteTool";
	public final static String GET_ALL_TOOL_BYCONCEPTID_REQUEST = "tool.getAllToolByConceptId";
	public final static String GET_ALL_TOOL_REQUEST ="tool.getAllTool";
	public final static String GET_TOOL_BY_NAME_REQUEST ="tool.getToolByName";
	public final static String GET_TOOL_BY_ID_REQUEST ="tool.getToolById";
	
	public final static String GET_ALL_SKILL_COLLAB = "vskillCollab.getAllSkillCollab";
	public final static String GET_SKILL_BY_TOOL = "vskill.getSkillByTool";
	public final static String GET_CONCEPT_BY_CATEGORY = "vskill.getConceptByCategory";
	public final static String GET_TOOL_BY_CONCEPT = "vskill.getToolByConcept";
}