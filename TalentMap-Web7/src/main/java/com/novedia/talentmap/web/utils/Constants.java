package com.novedia.talentmap.web.utils;

public class Constants {
	
	public static final int CATEGORY_VIEW = -1;

	public static final int CONCEPT_VIEW = -2;

	public static final int TOOL_VIEW = -3;
	
	public static final String FIL_ARIANE_SEPARATOR_LABEL = " > ";
	
	public static final int NB_ROWS_DEFAULT = 10;
	
	public static final String MANAGE_TOOLS_VIEW_TITLE = "Manage tools View";
	
	public static final String DELETE_COLLEAGUE_VIEW_TITLE = "Delete Colleague View";
	
	public static final String TEXT_FIELD_SEARCH_COLLEAGUE_NAME_LABEL = "Colleague Name : ";
	
	public static final String DELETE_COLLEAGUE_MSG = "Are you sure you want to delete these colleagues ?";
	
	// 3 constants to validate fields in MissionForm
    public static final int VALIDATION_FIELD_MISSING = 0;
    
    public static final int VALIDATION_INVALID_PERIOD = 1;
    
    public static final int VALIDATION_INVALID_SELECTION = 2;
    
    public static final int VALIDATION_VALID_FORM = 3;
    
    // 2 constants to identify if "save" is an insert or update
    public static final int SAVE_MODE_UPDATE = 0;
    
    public static final int SAVE_MODE_INSERT = 1;
    

	/**
     * Constant for error messages
     */
	
	public static final String SAVE_BUTTON_NAME = "Save";
	
	public static final String REGISTRATION_PANEL_PASSWORD_ERROR = "Error on your password confirmation";
	
//	public static final String PANEL_MISSING_FIELDS = "Error one or many fields are missing";
	
	public static final String REGISTRATION_PANEL_USER_CREATION_ERROR = "Error while creating user";
	
//	public static final String MISSION_MSG_INVALID_PERIOD = "The period is invalid";
	
//	public static final String MISSION_MSG_INVALID_SELECTION = "Please select at least one tool";
	
	public static final String MISSION_MSG_DATA_INSERTED_OK = "The mission has been added";
	
//    public static final String MISSION_MSG_DATA_INSERTED_KO = "Be careful , the mission has not been added";
    
//    public static final String MISSION_MSG_DATA_INSERTED_ERROR = "Thecnical error, please try again later";
    
    public static final String EAE_MSG_ERROR_GENERALITY_FORM = "Missing or invalid field";
	
	public static final String EXPERIENCE_FORMAT = "ex : 2";

	public static final String DATE_FORMAT = "DD/MM/YY";

	public static final String SEARCH_LABEL = "Search";
	
	/**
     * Constant for Admin View
     */
	
	public static final String DELETE_COLLEAGUE_LABEL = "Delete Collab";
	
	public static final String MANAGE_TOOL_LABEL = "Manage Tool";
	
	public static final String[] VISIBLE_COlUMN = { "First Name", "Last Name","Email", "Phone" };
	
	
	/**
     * Constant for Collaborator and skill manage Pop-In
     */
	
	public static final String COLLABORATOR_AND_SKILL_MANAGE_POP_IN_TITLE = "Administration";
	
	public static final String MODIFY_OR_DELETE_SKILL_ELEMENT_LABEL = "View Category/Concept/Tool";
	
	public static final String ADD_SKILL_ELEMENT_LABEL = "Add Category/Concept/Tool";
	
	public static final String CATEGORY_CAPTION = "Category";
	
	public static final String CONCEPT_CAPTION = "Concept";
	
	public static final String TOOL_CAPTION = "Tool";
	
	
	public static final String PROFILE_VIEW = "profileView";
	
	public static final String SKILL_VIEW = "skillView";
	
	public static final String MISSION_VIEW = "missionView";
	
	public static final String MISSIONS_HISTORY = "Missions history";

	public static final String SKILLS = "Skills";

	public static final String ADMINISTRATIVE_DATA = "Administrative data";
	
	/**
	* Constant for Search by skill tree admin
	*/
	  public static final int TYPE_CATEGORY = 1;
	  
	  public static final int TYPE_CONCEPT = 2;
	  
	  public static final int TYPE_TOOL = 3;
	
	
	/**
     * Constant for Personel EAE Pop-In
     */
	
	public static final String PERSONAL_EAE_POP_IN_TITLE = "Personal EAE";
	
	public static final String CURRENT_EAE_BUTTON_LABEL = "Current EAE";

	public static final String HISTORY_EAE_BUTTON_LABEL = "EAE History";

	public static final String GENERALITY_EAE_BUTTON_LABEL = "Generality";
	
	public static final String RESULTS_EAE_BUTTON_LABEL = "Results";
	
	public static final String OBJECTIVES_EAE_BUTTON_LABEL = "Objectives";
	
	public static final String SYNTHESIS_EAE_BUTTON_LABEL = "Synthesis";

	/**
     * Constant for Personel EAE Pop-In => Current EAE => Generality
     */
	public static final String MANAGER_LAST_NAME = "Manager's last name";
	
	public static final String MANAGER_FIRST_NAME = "Manager's first name";
	
	public static final String DATE_OF_EAE = "EAE's date";
	
	public static final String DATE_OF_PREV_EAE = "Previous EAE's date";
	
	public static final String EAE_SALARY = "Salary";

	public static final String GIVE_SALARY = "Give a salary";
	
	public static final String CURRENT_EAE_TITLE_1 = "EAE for : ";
    
    public static final String CURRENT_EAE_TITLE_2 = " on date : ";

	/**
     * Constant for Personel EAE Pop-In => Current EAE => Results
     */
	public static final String EAE_YEAR_SYNTHESIS = "Summary of the past year :";
	public static final String EAE_COLLAB_STRENGHTS = "My Highlights :";
	public static final String EAE_COLLAB_WEAKNESSES = "My areas of improvement :";
	public static final String EAE_RESULTS_TITLE_3 = "3 Overall assessment of my work within the organization :";

}
