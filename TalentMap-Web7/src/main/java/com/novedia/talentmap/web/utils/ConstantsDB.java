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
	
}
