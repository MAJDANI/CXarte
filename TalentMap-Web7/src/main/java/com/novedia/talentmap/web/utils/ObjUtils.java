package com.novedia.talentmap.web.utils;

import java.util.Hashtable;

/**
 * Classe utilitaire permettant de gérer les différents états d'affichage
 * (caché, read only, modification) des éléments du formulaire des objectifs,
 * parce que les cas imbriqués sont complexes. Cette classe permet d'éviter les
 * if imbriqués dans la classe gérant l'affichage (EAEObjectiveForm).
 * 
 * @author v.guillemain
 * 
 *         _______________________________________________________________
 *         _______________________________________________| Etat EAE | OPEN |
 *         VALIDATED | CLOSED | |PROFIL | ONGLET | Elément | | | |
 *         _____________________________________________________________________________________________
 *         |_______________| |Manager | Généralités |Généralités |Consultation
 *         |Consultation |Consultation | | | |Salaire |Absent |Consultation
 *         |Consultation | | | Bilan/Results |Résumé année |Absent |Consultation
 *         |Consultation | | | |Données objectif : | | | | | | |
 *         (But,date,indicateurs,Moyens)|Consultation |Consultation
 *         |Consultation | | | | Résults obj Note collab |Absent |Consultation
 *         |Consultation | | | | Résults obj Note Manager |Absent |Modification
 *         |Consultation | | | | Eléments freins ou moteurs |Absent
 *         |Consultation |Consultation | | | | Commentaires Obj |Absent
 *         |Consultation |Consultation | | | |Points forts collab |Absent
 *         |Consultation |Consultation | | | |Points faibles collab |Absent
 *         |Consultation |Consultation | | | |Moyens à fournir |Absent
 *         |Modification |Consultation | | | Objectifs |Bouton création | |Actif
 *         | | | | |Formulaire de saisie : | | | | | | |
 *         (But,date,indicateurs,Moyens)| |Actif |Consultation | | | | Résults
 *         obj Note collab | |Absent |Consultation | | | | Résults obj Note
 *         Manager | |Absent |Consultation | | | | Eléments freins ou moteurs |
 *         |Absent |Consultation | | | | Commentaires Obj | |Absent
 *         |Consultation | | | Synthèse |Formulaire de saisie | |Actif
 *         |Consultation |
 *         _____________________________________________________________________________________________
 *         |_______________| |Collab | Généralités |Généralités |Consultation
 *         |Consultation |Consultation | | | |Salaire |Modification
 *         |Consultation |Consultation | | | Bilan/Results |Résumé année
 *         |Modification |Consultation |Consultation | | | |Données objectif | |
 *         | | | | | (But,date,indicateurs,Moyens)|Consultation |Consultation
 *         |Consultation | | | | Résults obj Note collab |Modification
 *         |Consultation |Consultation | | | | Résults obj Note Manager |Absent
 *         |Absent |Consultation | | | | Eléments freins ou moteurs
 *         |Modification |Consultation |Consultation | | | | Commentaires Obj
 *         |Modification |Consultation |Consultation | | | |Points forts collab
 *         |Modification |Consultation |Consultation | | | |Points faibles
 *         collab |Modification |Consultation |Consultation | | | |Moyens à
 *         fournir |Absent |Absent |Consultation |
 *         _____________________________________________________________________________________________
 *         |
 * 
 */
public class ObjUtils {
	/**
	 * Pour afficher les objets mais les positionner en readOnly
	 */
	public static final String READONLY = "READONLY";
	/**
	 * Pour ne pas afficher les objets
	 */
	public static final String HIDDEN = "HIDDEN";
	/**
	 * Pour afficher les objets et les rendre modifiaables
	 */
	public static final String UPDATE = "UPDATE";

	/**
	 * Les différents Id des "attributs" d'Objectifs dont les modes d'affichage
	 * sont gérés dans cette classe
	 */
	public static final String TITLE = ComponentsId.OBJECTIVE_TITLE_ID;
	public static final String GOAL = ComponentsId.OBJECTIVE_GOAL_ID;
	public static final String DATE = ComponentsId.OBJECTIVE_TARGET_DATE_ID;
	public static final String INDIC = ComponentsId.OBJECTIVE_INDICATORS_ID;
	public static final String MEANS = ComponentsId.OBJECTIVE_MEANS_ID;
	public static final String COL_SCORE = ComponentsId.OBJECTIVE_COLL_SCORE_ID;
	public static final String MAN_SCORE = ComponentsId.OBJECTIVE_MAN_SCORE_ID;
	public static final String MOT_RESTR = ComponentsId.OBJECTIVE_MOTIVES_OR_RESTRAINTS_ID;
	public static final String COMMENTS = ComponentsId.OBJECTIVE_COMMENT_ID;

	/**
	 * Les différents mode de consulattion régissant les pages des EAE
	 */
	public static final String O_M = EAEConsultationMode.OPEN_MANAGER.getValue();
	public static final String V_M = EAEConsultationMode.VALIDATED_MANAGER.getValue();
	public static final String O_C = EAEConsultationMode.OPEN_COLLAB.getValue();
	public static final String V_C = EAEConsultationMode.VALIDATED_COLLAB.getValue();
	public static final String CL = EAEConsultationMode.CLOSED.getValue();

	/**
	 * représente l'onglet "Results", où les objectifs sont affichés pour y être
	 * notés
	 */
	public static final String TAB_RES = EAETabEnum.RESULTS_TAB.getValue();
	/**
	 * représente l'onglet "Objectives", où les objectifs sont affichés pour y
	 * être définis
	 */
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

		ht.put(GOAL + TAB_RES + O_M, READONLY);
		ht.put(GOAL + TAB_RES + V_M, READONLY);
		ht.put(GOAL + TAB_RES + CL, READONLY);
		ht.put(GOAL + TAB_RES + O_C, READONLY);
		ht.put(GOAL + TAB_RES + V_C, READONLY);
		ht.put(GOAL + TAB_OBJ + O_M, HIDDEN);
		ht.put(GOAL + TAB_OBJ + V_M, UPDATE);
		ht.put(GOAL + TAB_OBJ + CL, READONLY);

		ht.put(TITLE + TAB_RES + O_M, READONLY);
		ht.put(TITLE + TAB_RES + V_M, READONLY);
		ht.put(TITLE + TAB_RES + CL, READONLY);
		ht.put(TITLE + TAB_RES + O_C, READONLY);
		ht.put(TITLE + TAB_RES + V_C, READONLY);
		ht.put(TITLE + TAB_OBJ + O_M, HIDDEN);
		ht.put(TITLE + TAB_OBJ + V_M, UPDATE);
		ht.put(TITLE + TAB_OBJ + CL, READONLY);

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
		ht.put(COL_SCORE + TAB_OBJ + CL, HIDDEN);

		ht.put(MAN_SCORE + TAB_RES + O_M, HIDDEN);
		ht.put(MAN_SCORE + TAB_RES + V_M, UPDATE);
		ht.put(MAN_SCORE + TAB_RES + CL, READONLY);
		ht.put(MAN_SCORE + TAB_RES + O_C, HIDDEN);
		ht.put(MAN_SCORE + TAB_RES + V_C, HIDDEN);
		ht.put(MAN_SCORE + TAB_OBJ + V_M, HIDDEN);
		ht.put(MAN_SCORE + TAB_OBJ + CL, HIDDEN);

		ht.put(MOT_RESTR + TAB_RES + O_M, HIDDEN);
		ht.put(MOT_RESTR + TAB_RES + V_M, READONLY);
		ht.put(MOT_RESTR + TAB_RES + CL, READONLY);
		ht.put(MOT_RESTR + TAB_RES + O_C, UPDATE);
		ht.put(MOT_RESTR + TAB_RES + V_C, READONLY);
		ht.put(MOT_RESTR + TAB_OBJ + V_M, HIDDEN);
		ht.put(MOT_RESTR + TAB_OBJ + CL, HIDDEN);

		ht.put(COMMENTS + TAB_RES + O_M, HIDDEN);
		ht.put(COMMENTS + TAB_RES + V_M, READONLY);
		ht.put(COMMENTS + TAB_RES + CL, READONLY);
		ht.put(COMMENTS + TAB_RES + O_C, UPDATE);
		ht.put(COMMENTS + TAB_RES + V_C, READONLY);
		ht.put(COMMENTS + TAB_OBJ + V_M, HIDDEN);
		ht.put(COMMENTS + TAB_OBJ + CL, HIDDEN);

	}

	/**
	 * Revoie le mode dans lequel l'objet "objetAffiché" doit être affiché, en
	 * fonction des 2 autres paramètres fournis : tab et modeConsultation.
	 * 
	 * @param objetAffiché
	 *            : l'identifiant de l'objet à afficher : identifiant déclré
	 *            dans la classe ComponentsId, permettant d'identifier exctement
	 *            l'objet que l'on souhaite afficher.
	 * @param tab
	 *            : un EAETabEnum permettant de dire si l'object d'objectif à
	 *            affiché est affcihé dans la parti "Results" ou "Objectives" de
	 *            l'EAE
	 * @param modeConsultation
	 *            : un EAEConsultationMode, indiquant si l'EAE est ouvert ,
	 *            validé ou fermé, et si c'est en tant que manager ou
	 *            collaborateur que l'on affiche la donnée.
	 * @return : mode dans lequel doit être affiché l'objet "objetAffiché".
	 *         C'est soit this.READONLY, this.HIDDEN ou this.UPDATE.
	 */
	public String getMode(String objetAffiché, EAETabEnum tab,
			EAEConsultationMode modeConsultation) {
		String key = objetAffiché + tab.getValue()
				+ modeConsultation.getValue();
		return ht.get(key);
	}

}
