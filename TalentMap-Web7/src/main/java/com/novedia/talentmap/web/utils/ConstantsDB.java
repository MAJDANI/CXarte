package com.novedia.talentmap.web.utils;

/**
 * Constantes relatives aux données en bases : Tailles maximales de champs, ...
 * 
 * @author v.guillemain
 *
 */
public class ConstantsDB {

	//TAILLE MAX D'UNE COLONNE de type TEXT
	public static final Integer MAX_LENGTH_TYPE_TEXT = 65536;

	// TABLE EAE
	public static final Integer EAE_PROFILE_LABEL_MAX_LENGTH = 60;
	public static final Integer EAE_YEAR_SYNTHESIS_MAX_LENGTH = 65536;
	public static final Integer EAE_COLLEAGUES_STRENGTHS_MAX_LENGTH = 65536;
	public static final Integer EAE_COLLEAGUES_WEAKNESSES_MAX_LENGTH = 65536;
	public static final Integer EAE_COLLEAGUES_SYNTHESIS_MAX_LENGTH = 65536;
	public static final Integer EAE_MANAGERS_SYNTHESIS_MAX_LENGTH = 65536;
	public static final Integer EAE_OTHER_MAX_LENGTH = 65536;
	public static final Integer EAE_MEANS_TO_PROGRESS_MAX_LENGTH = 65536;


	
	// TABLE OBJECTIVE
	public static final Integer OBJECTIVE_TITLE_MAX_LENGTH = 60;
	public static final Integer OBJECTIVE_GOAL_MAX_LENGTH = 200;
	public static final Integer OBJECTIVE_INDICATORS_MAX_LENGTH = 65536;
	public static final Integer OBJECTIVE_MEANS_MAX_LENGTH = 65536;
	public static final Integer OBJECTIVE_MOTIVES_OR_RESTRAINTS_MAX_LENGTH = 65536;
	public static final Integer OBJECTIVE_COMMENTS_MAX_LENGTH = 65536;

	
    // TABLE CATEGORY
    public static final int CATEGORY_NAME_MAX_LENGTH = 20;//OK 2013/10/18
    
    // TABLE CONCEPT
    public static final int CONCEPT_NAME_MAX_LENGTH = 20;//OK 2013/10/18
    
    // TABLE TOOL
    public static final int TOOL_NAME_MAX_LENGTH = 30;//OK 2013/10/18
    
    // TABLE AUTHENTICATION
    public static final int REGISTRATION_LOGIN_MAX_LENGTH = 50;
    public static final int REGISTRATION_PASSWORD_MAX_LENGTH = 50;
    // TABLE COLLEAGUE
    public static final int COLLEAGUE_LAST_NAME_MAX_LENGTH = 30;
    public static final int COLLEAGUE_FIRST_NAME_MAX_LENGTH = 30;
    public static final int COLLEAGUE_EMAIL_MAX_LENGTH = 50;
    public static final int COLLEAGUE_PHONE_MAX_LENGTH = 10;
    public static final int COLLEAGUE_EXPERIENCE_MAX_LENGTH = 2;

}
