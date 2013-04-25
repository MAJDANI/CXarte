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
	public static final String FIELD_NULL_REPRESENTATION = "";

	/**
	 * Données Profil - Collaborateur :"Nom", "Prénom", "Profil", "Email", "Tél", "Date d'entrée Novedia", "Années d'expérience", "Ingénieur d'affaire"
	 */
	public static final Object[] NAME_FIELD_COLLABORATOR = new Object[] {
		"Title", "Name", "First name", "Job title", "Email", "Tel", "Date of entry Novedia",
		"Years of Experience", "Business engineer", "Manager" };
	
	public static final String FIELD_COLLAB_TITLE = "title";
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
		FIELD_COLLAB_TITLE, 
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
		"Place", "Start mission", "End mission", "Comment","tools" };

	public static final String FIELD_MISSION_NAME = "title";
	public static final String FIELD_MISSION_CLIENT = "client";
	public static final String FIELD_MISSION_PLACE = "place";
	public static final String FIELD_MISSION_START_DATE = "startDate";
	public static final String FIELD_MISSION_END_DATE = "endDate";
	public static final String FIELD_MISSION_NOTES = "notes";
	public static final String FIELD_MISSION_TOOLS = "tools";

	public static final Object[] FIELD_ORDER_MISSION = new Object[] { 
		FIELD_MISSION_NAME, 
		FIELD_MISSION_CLIENT,
		FIELD_MISSION_PLACE, 
		FIELD_MISSION_START_DATE, 
		FIELD_MISSION_END_DATE, 
		FIELD_MISSION_NOTES,
		FIELD_MISSION_TOOLS};

	public static final String ADMIN_DATA_LABEL = "Administrative data";
	public static final String LAST_MISSION_LABEL = "Last mission";
	public static final String LABEL_SAVE_BUTTON = "Save";
	public static final String LABEL_CANCEL_BUTTON = "Cancel";
	public static final String ADMIN_DATA_EDIT_BUTTON = "Edit";
	
	
	/**
	 * Données Mission - Messages
	 */
	public static final String MISSION_MSG_DATA_SAVED_OK = "The mission has been updated";
	public static final String MISSION_MSG_DATA_SAVED_KO = "Be careful , the mission has not been updated";
	public static final String MISSION_MSG_DATA_INSERTED_OK = "The mission has been added";
	public static final String MISSION_MSG_DATA_INSERTED_KO = "Be careful , the mission has not been added";

	public static final String MISSION_MSG_INVALID_PERIOD = "The period is invalid";
	public static final String MISSION_MSG_INVALID_SELECTION = "The tools selection is invalid";
	
	/**
	 * Données Registration
	 */
	public static final String REGISTRATION_TITLE_FIELD = "title";
	public static final String REGISTRATION_LAST_NAME_FIELD = "lastName";
	public static final String REGISTRATION_FIRST_NAME_FIELD = "firstName";
	public static final String REGISTRATION_EMPLOYMENT_DATE_FIELD = "employmentDate";
	public static final String REGISTRATION_BUSINESS_ENGINEER_FIELD = "businessEngineer";
	public static final String REGISTRATION_PROFILE_FIELD = "profileId";
	public static final String REGISTRATION_MANAGER_FIELD = "managerId";
	public static final String REGISTRATION_PASSWORD_FIELD = "password";
	public static final String REGISTRATION_PASSWORD_CONFIRM_FIELD = "passwordConfirm";
	public static final String REGISTRATION_LOGIN_FIELD = "login";
	public static final String REGISTRATION_PHONE_FIELD = "phone";
	public static final String REGISTRATION_EXPERIENCE_FIELD = "experience";
	public static final String REGISTRATION_EMAIL_FIELD = "email";
	public static final String REGISTRATION_COMPONENT_SIZE = "208px";
	public static final String REGISTRATION_TITLE_CHOICE1 = "Mr";
	public static final String REGISTRATION_TITLE_CHOICE2 = "M";
	
	
	public static final String REGISTRATION_NULL_REPRESENTATION = "";
	public static final String REGISTRATION_DATE_FORMAT = "dd/MM/yyyy";
	public static final String REGISTRATION_SELECT_VALUE = "value";
	public static final String REGISTRATION_SELECT_STYLE = "type-profile";
	public static final String REGISTRATION_NUMBER_REGEXP = "[0-9]+";
	public static final String REGISTRATION_EMAIL_REGEXP = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$";
	public static final String REGISTRATION_ERROR_PHONE_NUMBER = "Incorrect phone number";
	public static final String REGISTRATION_ERROR_EXPERIENCE = "Years of Experience incorrect";
	public static final String REGISTRATION_ERROR_EMAIL = "Email is invalid";
	public static final String REGISTRATION_ERROR_EMAIL_EXISTS1 = "This email is already in use (";
	public static final String REGISTRATION_ERROR_EMAIL_EXISTS2 = ").Please choose another one.";
	public static final String REGISTRATION_ERROR_LOGIN_EXISTS1 = "This login is already in use (";
	public static final String REGISTRATION_ERROR_LOGIN_EXISTS2 = ").Please choose another one.";
	
	/**
	 * For CollaboratorForm
	 */
	public static final Object[] NAME_FIELD_MISSIONS = new Object[] {"Customer", "Start Mission", "End mission"};
	public static final Object[] FIELD_ORDER_MISSIONS = new Object[] {"Customer", "startDate", "endDate"};
	public static final String MESSAGE_COLLABORATOR_ID_NOT_FOUND = "Collaborator Id Not Found";

	
	/**
	 *For Registration
	 */
	public static final Object[] NAME_FIELD_REGISTRATION = new Object[] { "Title","Name","First name","Password","Confirm password",
		"Choose a login", "Email", "Téléphone", "Date of hire", "Job title", "Years of Experience", "Business engineer",
		 "Manager" };
	public static final Object[] FIELD_ORDER_REGISTRATION = new Object[] {"title","lastName", "firstName","password","passwordConfirm",
		"login", "email", "phone", "employmentDate", "profileId", "experience", "businessEngineer", "managerId" };
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
	public static final String LABEL_BUTTON_ADD_MISSION = "Add Mission";
	public static final String LABEL_BUTTON_MODIFY_MISSION = "Edit Mission";
	public static final String LABEL_BUTTON_DELETE_MISSION = "Delete Mission";
	public static final String TITLE_FORM_ADD_MISSION ="Add Mission";
	public static final String TITLE_FORM_EDIT_MISSION ="Edit Mission";
	
	
	/**
	 * Labels form confirmation delete window (Classe : MissionCollaboratorContent)  
	 */
	public static final String LABEL_WINDOW_CONFIRM_DELETE = "Confirm Delete";
	public static final String LABEL_BUTTON_CONFIRM_DELETE_MISSION = "Yes";
	public static final String LABEL_BUTTON_CANCEL_DELETE_MISSION = "No";
	public static final String CONFIRM_DELETE_MESSAGE_MISSION = "Are you sure you want to delete these mission ?";

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
	public static final String SEARCH_SKILLS_MSG_PLEASE_SELECT = "Please select at least one element";
	public static final String NO_CUSTUMER_SELECT_MSG = "Please select a custumer";
	public static final String SEARCH_SKILLS_MSG_NO_COLLEAGUE_FOUND = "No colleague found";
	
	/**
	 * Constants for Missions list Window
	 */
	public static final String LIST_MISSION_WINDOW_TITLE = "Missions' History for ";
	public static final String LIST_MISSION_WINDOW_NO_MISSIONS = "No records yet.";
	
	/**
	 * DATABASE MAX_SIZE
	 */
	//TABLE MISSION
	public static final int MISSION_TITLE_MAX_LENGTH = 60;
	public static final int MISSION_PLACE_MAX_LENGTH = 60;
	//TABLE CATEGORY
	public static final int CATEGORY_NAME_MAX_LENGTH = 20;
	//TABLE CONCEPT
	public static final int CONCEPT_NAME_MAX_LENGTH = 20;
	//TABLE TOOL
	public static final int TOOL_NAME_MAX_LENGTH = 30;
	// TABLE AUTHENTICATION
	public static final int REGISTRATION_LOGIN_MAX_LENGTH = 50;
	public static final int REGISTRATION_PASSWORD_MAX_LENGTH = 50;
	//TABLE COLLEAGUE
	public static final int COLLEAGUE_LAST_NAME_MAX_LENGTH = 30;
	public static final int COLLEAGUE_FIRST_NAME_MAX_LENGTH = 30;
	public static final int COLLEAGUE_EMAIL_MAX_LENGTH = 50;
	public static final int COLLEAGUE_PHONE_MAX_LENGTH = 10;
	public static final int COLLEAGUE_EXPERIENCE_MAX_LENGTH = 2;
	
	
	public static final Integer DEFAULT_SELECTED_CHOICE = 0; 
	public static final String DEFAULT_CAPTION_SELECTED_JOB = "Select your Profile";
	public static final String DEFAULT_CAPTION_SELECTED_BUSINESSENGINEER = "Select your businessEnginer";
	public static final String DEFAULT_CAPTION_SELECTED_MAMANGER = "Select your Manager";
	public static final String DEFAULT_CAPTION_SELECTED_JOB_MSG = "Please select your job title";

	
}
