package com.novedia.talentmap.web.utils;

import java.util.Hashtable;

import com.novedia.talentmap.web.ui.colleague.eae.EAEConsultationMode;

/**
 * Classe utilitaire permettant de gérer les différents états d'affichage
 * (caché, read only, modification) des éléments du formulaire des objectifs,
 * parce que les cas imbriqués sont complexes. Cette classe permet d'éviter les
 * if imbriqués dans la classe gérant l'affichage (EAEObjectiveForm).
 * 
 * @author v.guillemain
 * 
 */
public class ObjUtils {

	public static final String READONLY = "READONLY";
	public static final String HIDDEN = "HIDDEN";
	public static final String UPDATE = "UPDATE";
	public static final String GOAL = ComponentsId.OBJECTIVE_GOAL_ID;
	public static final String DATE = ComponentsId.OBJECTIVE_TARGET_DATE_ID;
	public static final String INDIC = ComponentsId.OBJECTIVE_INDICATORS_ID;
	public static final String MEANS = ComponentsId.OBJECTIVE_MEANS_ID;
	public static final String COL_SCORE = ComponentsId.OBJECTIVE_COLL_SCORE_ID;
	public static final String MAN_SCORE = ComponentsId.OBJECTIVE_MAN_SCORE_ID;

	public static final String O_M= EAEConsultationMode.OPEN_MANAGER.getValue();
	public static final String V_M= EAEConsultationMode.VALIDATED_MANAGER.getValue();
	public static final String O_C= EAEConsultationMode.OPEN_COLLAB.getValue();
	public static final String V_C= EAEConsultationMode.VALIDATED_COLLAB.getValue();
	public static final String CL = EAEConsultationMode.CLOSED.getValue();

	public static final String TAB_RES = EAETabEnum.RESULTS_TAB.getValue();
	public static final String TAB_OBJ = EAETabEnum.OBJECTIVE_TAB.getValue();

	private static Hashtable<String, String> ht = new Hashtable<String, String>();
	
	private ObjUtils() {
		fillHashModes();
	}
	
	/** Holder */
	private static class SingletonHolder {		
		/** Instance unique non préinitialisée */
		private final static ObjUtils instance = new ObjUtils();
	}
 
	/** Point d'accès pour l'instance unique du singleton */
	public static ObjUtils getInstance() {
		return SingletonHolder.instance;
	}

	public static void fillHashModes() {
		// GOAL, DATE, INDIC and MEANS have the same behavior
//		ht.put(GOAL+DATE+INDIC+MEANS + TAB_RES + O_M, READONLY);
//		ht.put(GOAL+DATE+INDIC+MEANS + TAB_RES + V_M, READONLY);
//		ht.put(GOAL+DATE+INDIC+MEANS + TAB_RES + CL, READONLY);
//		ht.put(GOAL+DATE+INDIC+MEANS + TAB_RES + O_C, READONLY);
//		ht.put(GOAL+DATE+INDIC+MEANS + TAB_RES + V_C, READONLY);
//		ht.put(GOAL+DATE+INDIC+MEANS + TAB_OBJ + O_M, HIDDEN);
//		ht.put(GOAL+DATE+INDIC+MEANS + TAB_OBJ + V_M, UPDATE);
//		ht.put(GOAL+DATE+INDIC+MEANS + TAB_OBJ + CL, READONLY);

		ht.put(GOAL + TAB_RES + O_M, READONLY);
		ht.put(GOAL + TAB_RES + V_M, READONLY);
		ht.put(GOAL + TAB_RES + CL, READONLY);
		ht.put(GOAL + TAB_RES + O_C, READONLY);
		ht.put(GOAL + TAB_RES + V_C, READONLY);
		ht.put(GOAL + TAB_OBJ + O_M, HIDDEN);
		ht.put(GOAL + TAB_OBJ + V_M, UPDATE);
		ht.put(GOAL + TAB_OBJ + CL, READONLY);

		ht.put(DATE + TAB_RES + O_M, READONLY);
		ht.put(DATE + TAB_RES + V_M, READONLY);
		ht.put(DATE + TAB_RES + CL, READONLY);
		ht.put(DATE + TAB_RES + O_C, READONLY);
		ht.put(DATE + TAB_RES + V_C, READONLY);
		ht.put(DATE + TAB_OBJ + O_M, HIDDEN);
		ht.put(DATE + TAB_OBJ + V_M, UPDATE);
		ht.put(DATE + TAB_OBJ + CL, READONLY);

		ht.put(INDIC + TAB_RES + O_M, READONLY);
		ht.put(INDIC + TAB_RES + V_M, READONLY);
		ht.put(INDIC + TAB_RES + CL, READONLY);
		ht.put(INDIC + TAB_RES + O_C, READONLY);
		ht.put(INDIC + TAB_RES + V_C, READONLY);
		ht.put(INDIC + TAB_OBJ + O_M, HIDDEN);
		ht.put(INDIC + TAB_OBJ + V_M, UPDATE);
		ht.put(INDIC + TAB_OBJ + CL, READONLY);

		ht.put(MEANS + TAB_RES + O_M, READONLY);
		ht.put(MEANS + TAB_RES + V_M, READONLY);
		ht.put(MEANS + TAB_RES + CL, READONLY);
		ht.put(MEANS + TAB_RES + O_C, READONLY);
		ht.put(MEANS + TAB_RES + V_C, READONLY);
		ht.put(MEANS + TAB_OBJ + O_M, HIDDEN);
		ht.put(MEANS + TAB_OBJ + V_M, UPDATE);
		ht.put(MEANS + TAB_OBJ + CL, READONLY);

		ht.put(COL_SCORE + TAB_RES + O_M, HIDDEN);
		ht.put(COL_SCORE + TAB_RES + V_M, READONLY);
		ht.put(COL_SCORE + TAB_RES + CL, READONLY);
		ht.put(COL_SCORE + TAB_RES + O_C, UPDATE);
		ht.put(COL_SCORE + TAB_RES + V_C, READONLY);
		ht.put(COL_SCORE + TAB_OBJ + V_M, HIDDEN);
		ht.put(COL_SCORE + TAB_OBJ + CL, READONLY);

		ht.put(MAN_SCORE + TAB_RES + O_M, HIDDEN);
		ht.put(MAN_SCORE + TAB_RES + V_M, UPDATE);
		ht.put(MAN_SCORE + TAB_RES + CL, READONLY);
		ht.put(MAN_SCORE + TAB_RES + O_C, HIDDEN);
		ht.put(MAN_SCORE + TAB_RES + V_C, HIDDEN);
		ht.put(MAN_SCORE + TAB_OBJ + V_M, HIDDEN);
		ht.put(MAN_SCORE + TAB_OBJ + CL, READONLY);

	}

	public String getMode(String objetAffiché, EAETabEnum tab,
			EAEConsultationMode modeConsultation) {
		String key = objetAffiché+tab.getValue()+modeConsultation.getValue();
		return ht.get(key);
	}

}
