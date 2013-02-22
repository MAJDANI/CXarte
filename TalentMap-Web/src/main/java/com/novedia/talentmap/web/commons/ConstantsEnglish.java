package com.novedia.talentmap.web.commons;

public class ConstantsEnglish {

	/**
	 * Général - Messages
	 */
	public static final String MSG_MISSING_FIELDS = "Fields are not all filled";

	/**
	 * Données Profil - Messages
	 */
	public static final String PROFILE_MSG_DATA_SAVED_OK = "Your data were recorded";
	public static final String PROFILE_MSG_DATA_SAVED_KO = "Your data could not be recorded";
	public static final String PROFILE_MSG_FIELD_REQUIRED_PART1 = "the field \"";
	public static final String PROFILE_MSG_FIELD_REQUIRED_PART2 = "\" is required";

	/**
	 * Champ Vide
	 */
	public static final String FIELD_NULL_REPRESENTATION = "not save";

	/**
	 * Données Profil - Collaborateur :"Nom", "Prénom", "Profil", "Email", "Tél", "Date d'entrée Novedia", "Années d'expérience", "Ingénieur d'affaire"
	 */
	public static final Object[] NAME_FIELD_COLLABORATOR = new Object[] {
		"Name", "first name", "Profile", "Email", "Tél", "Date of entry Novedia",
		"Years of Experience", "Business engineer", "Manager" };

	public static final String FIELD_COLLAB_LAST_NAME = "lastName";
	public static final String FIELD_COLLAB_FIRST_NAME = "firstName";
	public static final String FIELD_COLLAB_PROFILE_ID = "profileId";
	public static final String FIELD_COLLAB_EMAIL = "email";
	public static final String FIELD_COLLAB_PHONE = "phone";
	public static final String FIELD_COLLAB_EMPLOYMENT_DATE = "employmentDate";
	public static final String FIELD_COLLAB_EXPERIENCE = "experience";
	public static final String FIELD_COLLAB_BUISINESS_ENGINEER = "businessEngineer";
	public static final String FIELD_COLLAB_MANAGER = "managerId";

	public static final Object[] FIELD_ORDER_COLLABORATOR = new Object[] {
		FIELD_COLLAB_LAST_NAME, 
		FIELD_COLLAB_FIRST_NAME, 
		FIELD_COLLAB_PROFILE_ID, 
		FIELD_COLLAB_EMAIL, 
		FIELD_COLLAB_PHONE,
		FIELD_COLLAB_EMPLOYMENT_DATE, 
		FIELD_COLLAB_EXPERIENCE, 
		FIELD_COLLAB_BUISINESS_ENGINEER,
		FIELD_COLLAB_MANAGER};

	/**
	 * Données Profil - Mission
	 */
	public static final Object[] NAME_FIELD_MISSION = new Object[] { "Title", "Customer",
		"Place", "Start mission", "End mission", "Comment" };

	public static final String FIELD_MISSION_NAME = "title";
	public static final String FIELD_MISSION_CLIENT = "client";
	public static final String FIELD_MISSION_PLACE = "place";
	public static final String FIELD_MISSION_START_DATE = "startDate";
	public static final String FIELD_MISSION_END_DATE = "endDate";
	public static final String FIELD_MISSION_NOTES = "notes";

	public static final Object[] FIELD_ORDER_MISSION = new Object[] { 
		FIELD_MISSION_NAME, 
		FIELD_MISSION_CLIENT,
		FIELD_MISSION_PLACE, 
		FIELD_MISSION_START_DATE, 
		FIELD_MISSION_END_DATE, 
		FIELD_MISSION_NOTES };

	public static final String ADMIN_DATA_LABEL = "Administrative data";
	public static final String LAST_MISSION_LABEL = "Last mission";
	public static final String ADMIN_DATA_SAVE_BUTTON = "Save";
	public static final String ADMIN_DATA_CANCEL_BUTTON = "Cancel";
	public static final String ADMIN_DATA_EDIT_BUTTON = "Edit";
	
	
	/**
	 * Données Mission - Messages
	 */
	public static final String MISSION_MSG_DATA_SAVED_OK = "The mission has been updated";
	public static final String MISSION_MSG_DATA_SAVED_KO = "Be careful , the mission has not been updated";
	public static final String MISSION_MSG_DATA_INSERTED_OK = "The mission has been added";
	public static final String MISSION_MSG_DATA_INSERTED_KO = "Be careful , the mission has not been added";

	public static final String MISSION_MSG_INVALID_PERIOD = "The period is invalid";
	
	/**
	 * Données Registration
	 */
	public static final String REGISTRATION_EMPLOYMENT_DATE_FIELD = "employmentDate";
	public static final String REGISTRATION_BUSINESS_ENGINEER_FIELD = "businessEngineer";
	public static final String REGISTRATION_PROFILE_FIELD = "profileId";
	public static final String REGISTRATION_MANAGER_FIELD = "managerId";
	public static final String REGISTRATION_PASSWORD_FIELD = "password";
	public static final String REGISTRATION_PASSWORD_CONFIRM_FIELD = "passwordConfirm";
	public static final String REGISTRATION_PHONE_FIELD = "phone";
	public static final String REGISTRATION_EXPERIENCE_FIELD = "experience";
	public static final String REGISTRATION_EMAIL_FIELD = "email";
	
	public static final String REGISTRATION_NULL_REPRESENTATION = "";
	public static final String REGISTRATION_DATE_FORMAT = "dd/MM/yyyy";
	public static final String REGISTRATION_SELECT_VALUE = "value";
	public static final String REGISTRATION_SELECT_STYLE = "type-profile";
	public static final String REGISTRATION_NUMBER_REGEXP = "[0-9]+";
	public static final String REGISTRATION_EMAIL_REGEXP = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$";
	public static final String REGISTRATION_ERROR_PHONE_NUMBER = "Incorrect phone number";
	public static final String REGISTRATION_ERROR_EXPERIENCE = "Years of Experience incorrect";
	public static final String REGISTRATION_ERROR_EMAIL = "Email is invalid";
	
	/**
	 * For CollaboratorForm
	 */
	public static final Object[] NAME_FIELD_MISSIONS = new Object[] {"Customer", "Start Mission", "End mission"};
	public static final Object[] FIELD_ORDER_MISSIONS = new Object[] {"Customer", "startDate", "endDate"};
	public static final String MESSAGE_COLLABORATOR_ID_NOT_FOUND = "Collaborator Id Not Found";

	
	/**
	 *For Registration
	 */
	public static final Object[] NAME_FIELD_REGISTRATION = new Object[] { "Name", "First name","Password","Confirm password",
		"Email", "Téléphone", "Date of hire", "Profile", "Years of Experience", "Business engineer",
		 "Manager" };
	public static final Object[] FIELD_ORDER_REGISTRATION = new Object[] { "lastName", "firstName","password","passwordConfirm","email",
		"phone", "employmentDate", "profileId", "experience", "businessEngineer", "managerId" };
	/**
	 * For SkillCollaboratorContent
	 */
	public static final String EDIT_CAPTION =  "Change";
	public static final String SAVE_CAPTION = "Save";
	public static final String CANCEL_CAPTION = "Cancel";
	public static final String SKILL_LABEL = "List of skills";
	public static final String ADD_SKILL_LABEL = "Add skills";

	
	/**
	 * Constants for MissionCollaboratorContent
	 */
	public static final String PAGE_TITLE = "List of missions";
	public static final String LABEL_BUTTON_ADD_MISSION = "Add mission";
	public static final String LABEL_BUTTON_MODIFY_MISSION = "Change mission";
	public static final String LABEL_BUTTON_DELETE_MISSION = "Remove mission";
	
	/**
	 * Labels form confirmation delete window (Classe : MissionCollaboratorContent)  
	 */
	public static final String LABEL_WINDOW_CONFIRM_DELETE = "Confirmation of deletion mission";
	public static final String LABEL_BUTTON_CONFIRM_DELETE_MISSION = "Remove mission";
	public static final String LABEL_BUTTON_CANCEL_DELETE_MISSION = "DO NOT delete the mission";

	/**
	 * Constants for AddSkillPanel
	 */
	public static final String CATEGORY_LABEL = "category :";
	public static final String CONCEPT_LABEL = "Concept :";
	public static final String TOOL_LABEL = "Tool :";
	public static final String FREQUENCY_USE = "Use of the tool :";
	public static final String NO_TIME_USING = "Duration of no use of the tool :";
	public static final String[] OPTIONS = new String[] { "Beginner",
			"middle", "professional", "Master", "Expert" };
	
	/**
	 * Constant for RegistrationScreen
	 */
	public static final String SAVE_BUTTON_NAME = "Save";
	public static final String CANCEL_BUTTON_NAME = "Cancel";
	public static final String REGISTRATION_PANEL_NAME = "Registration";
	public static final String REGISTRATION_PANEL_WIDTH = "800px";
	public static final String REGISTRATION_PANEL_FOOTER_HEIGHT = "50px";
	public static final String REGISTRATION_PANEL_MISSING_FIELDS = "Error one or many fields are missing";
	public static final String REGISTRATION_PANEL_PASSWORD_ERROR = "Error on your password confirmation";
	public static final String REGISTRATION_PANEL_USER_CREATION_ERROR = "Error when creating user";
	
	
	/**
	 * Constants for MonitoringCollabContent
	 */
	public static final String VISUALIZE_CV_NAME = "CV";
	public static final String VISUALIZE_MISSION_NAME = "History Mission";
	public static final String VISUALIZE_EA_NAME = "History EA";
	public static final String VISUALIZE_PROFILE_NAME = "Display profile";
	public static final String PAGE_TITLE_COLAB = "List of your collaborator";
	
	/**
	 * Constants for AdminContentLayout
	 */
	public static final String ADD_TOOL_TITLE = "Add a tool";
	public static final String UPDATE_TOOL_TITLE = "Display tools";
	public static final String LIST_TOOL_TITLE = "List of tools";
	
	
	/**
	 * Constants for TabProfileSheet
	 */
	//private final String TAB_PROFILE_NAME = "Fiche Profil";
	public static final String TAB_PROFILE_NAME = "Profile Data";
	public static final String TAB_COLLAB_NAME = "Monitoring";

	
	/**
	 * Constants for AdminAddSkillContent
	 */
	public static final String ADMIN_CATEGORY_SELECT_NAME = "Category :";
	public static final String ADMIN_CONCEPT_SELECT_NAME = "Concept :";
	public static final String ADMIN_TOOL_SELECT_NAME = "Tool :";
	public static final String ADMIN_CAPTION_PROPERTY = "name";
	public static final String ADMIN_NULL_REPRESENTATION = "";
	public static final String ADMIN_CATEGORY_NAME = "category";
	public static final String ADMIN_CONCEPT_NAME = "concept";
	public static final String ADMIN_TOOL_NAME = "tool";
	public static final String ADMIN_NEW_CATEGORY_BUTTON = "New Category";
	public static final String ADMIN_NEW_CONCEPT_BUTTON = "New Concept";
	public static final String ADMIN_NEW_TOOL_CONFIRMATION = "New Tool created";
	public static final String ADMIN_NEW_TOOL_EXISTING = "New Tool already existing";
	public static final String ADMIN_NEW_CONCEPT_CONFIRMATION = "New Concept created";
	public static final String ADMIN_NEW_CONCEPT_EXISTING = "New Concept already existing";
	public static final String ADMIN_NEW_CATEGORY_CONFIRMATION = "New Category created";
	public static final String ADMIN_NEW_CATEGORY_EXISTING = "New Category already existing";
	public static final String ADMIN_NEW_TOOL_ERROR = "Please fill all the fields";
	
	/**
	 * Constants for NewCategoryWindow
	 */
	public static final String NEW_CATEGORY_WINDOW_WIDTH = "500";
	public static final String NEW_CATEGORY_WINDOW_TITLE = "New Category";
	public static final String NEW_CATEGORY_WINDOW_FIELD_LABEL = "Enter Category name:";
	public static final String NEW_CATEGORY_WINDOW_CATEGORY = "category";
	public static final String NEW_CATEGORY_WINDOW_ERROR = "Please enter a name for the new category";
	
	/**
	 * Constants for NewConceptWindow
	 */
	public static final String NEW_CONCEPT_WINDOW_WIDTH = "500";
	public static final String NEW_CONCEPT_WINDOW_TITLE = "New Concept";
	public static final String NEW_CONCEPT_WINDOW_FIELD_CONCEPT = "Enter Concept name:";
	public static final String NEW_CONCEPT_WINDOW_FIELD_CATEGORY = "Category :";
	public static final String NEW_CONCEPT_WINDOW_CATEGORY = "category";
	public static final String NEW_CONCEPT_WINDOW_CONCEPT = "concept";
	public static final String NEW_CONCEPT_WINDOW_ERROR = "Please enter a name and a category for the new concept";
	public static final String NEW_CONCEPT_WINDOW_CAPTION_PROPERTY = "name";
	

	/**
	 * Constants for Search by Skills
	 */
	public static final String SEARCH_SKILLS_MSG_NO_COLLEAGUE_FOUND = "No colleague found";
}
