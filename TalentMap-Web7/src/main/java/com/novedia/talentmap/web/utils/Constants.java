package com.novedia.talentmap.web.utils;
//import nécessaire?
import com.vaadin.server.ThemeResource;

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
	
	public static final String REGISTRATION_PANEL_USER_CREATION_ERROR = "Error while creating user";
	
	
	public static final String MISSION_MSG_DATA_INSERTED_OK = "The mission has been added";
	
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
	public static final Integer POSITION_X_EAE_POP_IN = 70;
	
	public static final Integer POSITION_Y_EAE_POP_IN = 70;

	/**
     * Constant for Personel EAE Pop-In => Current EAE => Results
     */
	public static final String EAE_RESULTS_TITLE_3 = "3 Overall assessment of my work within the organization :";

	/**
     * Constant for CM EAE Pop-In 
     */
	public static final String IMG_NO_PHOTO_BOY = "img/nophotoBoy.png";
	public static final String IMG_NO_PHOTO_GIRL = "img/nophotoGirl.png";
	public static final String IMG_BACK_BLUE = "img/backBlue.png";

	public static final String IMG_CADRE_GREEN = "img/cadreVert.png";
	public static final String IMG_CADRE_ORANGE = "img/cadreOrange.png";
	public static final String IMG_CADRE_RED = "img/cadreRouge.png";
	public static final String IMG_CADRE_GREY = "img/cadreGris.png";

	/**
	 * Constants for MIN and MAX sizes in forms
	 */
    public static final int COLLEAGUE_LAST_NAME_MIN_LENGTH = 2;
    public static final int COLLEAGUE_FIRST_NAME_MIN_LENGTH = 2;
    public static final int COLLEAGUE_PASSWORD_MIN_LENGTH = 4;
    public static final int COLLEAGUE_EXPERIENCE_MAX_YEARS = 40;
    
    /**
     * REGEX Validations
     */
    public static final String REGEX_EMAIL_VALIDATION = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    public static final String REGEX_PHONE_VALIDATION = "^[0-9]{10}$";
    public static final String REGEX_EXPERIENCE_VALIDATION = "^[0-9]{1,2}";
//    public static final String REGEX_DATE_VALIDATION = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{2}";
}
