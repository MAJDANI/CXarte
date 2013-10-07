package com.novedia.talentmap.web.utils;

import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;

/**
 * Cette classe permet de lister les différents modes de consultation d'un EAE.
 * (les modes de consultation ne sont pas les états de l'EAE, mais une
 * combinaison de l'état de l'EAE et du profil qui consulte l'EAE)
 * 
 * 
 * OPEN_COLLAB : quand le collaborateur consulte un EAE qui est dans l'état
 * OPEN.
 * 
 * OPEN_MANAGER ; quand le manager consulte un EAE d'un de ses collaborateurs
 * qui est dans l'état OPEN.
 * 
 * 
 * VALIDATED_COLLAB : quand le collaborateur consulte un EAE qui est dans l'état
 * VALIDATED.
 * 
 * VALIDATED_MANAGER ; quand le manager consulte un EAE d'un de ses
 * collaborateurs qui est dans l'état VALIDATED.
 * 
 * CLOSED : quand le collaborateur ou le manager consulte un EAE CLOSED : aucun
 * champ n'est disponible à la saisie.
 * 
 * MANAGER : quand le manager consulte les EAE de ses collaborateurs
 * 
 * 
 * _______________________________________________________________
 * _______________________________________________| Etat EAE | OPEN | VALIDATED
 * | CLOSED | |PROFIL | ONGLET | Elément | | | |
 * _____________________________________________________________________________________________
 * |_______________| |Manager | Généralités |Généralités |Consultation
 * |Consultation |Consultation | | | |Salaire |Absent |Consultation
 * |Consultation | | | Bilan/Results |Résumé année |Absent |Consultation
 * |Consultation | | | |Données objectif : | | | | | | |
 * (But,date,indicateurs,Moyens)|Consultation |Consultation |Consultation | | |
 * | Résults obj Note collab |Absent |Consultation |Consultation | | | | Résults
 * obj Note Manager |Absent |Modification |Consultation | | | | Eléments freins
 * ou moteurs |Absent |Consultation |Consultation | | | | Commentaires Obj
 * |Absent |Consultation |Consultation | | | |Points forts collab |Absent
 * |Consultation |Consultation | | | |Points faibles collab |Absent
 * |Consultation |Consultation | | | |Moyens à fournir |Absent |Modification
 * |Consultation | | | Objectifs |Bouton création | |Actif | | | | |Formulaire
 * de saisie : | | | | | | | (But,date,indicateurs,Moyens)| |Actif |Consultation
 * | | | | Résults obj Note collab | |Absent |Consultation | | | | Résults obj
 * Note Manager | |Absent |Consultation | | | | Eléments freins ou moteurs |
 * |Absent |Consultation | | | | Commentaires Obj | |Absent |Consultation | | |
 * Synthèse |Formulaire de saisie | |Actif |Consultation |
 * _____________________________________________________________________________________________
 * |_______________| |Collab | Généralités |Généralités |Consultation
 * |Consultation |Consultation | | | |Salaire |Modification |Consultation
 * |Consultation | | | Bilan/Results |Résumé année |Modification |Consultation
 * |Consultation | | | |Données objectif | | | | | | |
 * (But,date,indicateurs,Moyens)|Consultation |Consultation |Consultation | | |
 * | Résults obj Note collab |Modification |Consultation |Consultation | | | |
 * Résults obj Note Manager |Absent |Absent |Consultation | | | | Eléments
 * freins ou moteurs |Modification |Consultation |Consultation | | | |
 * Commentaires Obj |Modification |Consultation |Consultation | | | |Points
 * forts collab |Modification |Consultation |Consultation | | | |Points faibles
 * collab |Modification |Consultation |Consultation | | | |Moyens à fournir
 * |Absent |Absent |Consultation | | | Objectifs |Bouton création | | | | | |
 * |Formulaire de saisie : | | | | | | | (But,date,indicateurs,Moyens)| |
 * |Consultation | | | | Résults obj Note collab | | |Consultation | | | |
 * Résults obj Note Manager | | |Consultation | | | | Eléments freins ou moteurs
 * | | |Consultation | | | | Commentaires Obj | | |Consultation | | | Synthèse
 * |Formulaire de saisie | | |Consultation |
 * ______________________________________________________________________________________________________________
 * |
 * 
 * 
 * @author v.guillemain
 * 
 */
public enum EAEConsultationMode {

	OPEN_COLLAB(1, "OPEN_COLLAB"), OPEN_MANAGER(2, "OPEN_MANAGER"), VALIDATED_COLLAB(
			3, "VALIDATED_COLLAB"), VALIDATED_MANAGER(3, "VALIDATED_MANAGER"), CLOSED(
			4, "CLOSED"), MANAGER(5, "MANAGER"), COLLEAGUE(6, "COLLEAGUE");

	private int id;
	private String value;

	private EAEConsultationMode(int id, String value) {
		this.id = id;
		this.value = value;
	}

	public boolean isOPEN() {
		if (this == OPEN_COLLAB || this == OPEN_MANAGER)
			return true;
		else
			return false;
	}

	public boolean isVALIDATED() {
		if (this == VALIDATED_COLLAB || this == VALIDATED_MANAGER)
			return true;
		else
			return false;
	}

	/**
	 * Renvoie le mode de consultation en fonction du profil connecté (entre
	 * MANAGER et COLLEAGUE) et de l'état de l'EAE (état OUVERT, VALIDATED ou
	 * CLOSED)
	 * 
	 * @param selectedEAE
	 *            : l'EAEForSynthesisDTO dont on demande l'affichage
	 * @param profilConnected
	 *            : le profil concerné par l'affichage. Si un MANAGER est
	 *            conecté mais qu'il veut consulter ses propres EAE, ici
	 *            profilConnecté devra être COLLEAGUE
	 * @return le mode de consltation
	 */
	public static EAEConsultationMode computeEAEConsultationMode(
			EAEForSynthesisDTO selectedEAE, ProfilConnectedEnum profilConnected) {
		Integer state = selectedEAE.getEaeStateId();
		EAEConsultationMode mode;
		if (state.equals(EAEStateEnum.CLOSED.getId())) {
			mode = EAEConsultationMode.CLOSED;
		} else {
			if (state.equals(EAEStateEnum.VALIDATED.getId())) {
				if (profilConnected == ProfilConnectedEnum.COLLEAGUE) {
					mode = EAEConsultationMode.VALIDATED_COLLAB;
				} else {
					mode = EAEConsultationMode.VALIDATED_MANAGER;
				}
			} else {
				if (profilConnected == ProfilConnectedEnum.COLLEAGUE) {
					mode = EAEConsultationMode.OPEN_COLLAB;
				} else {
					mode = EAEConsultationMode.OPEN_MANAGER;
				}
			}
		}
		return mode;
	}

	/**
	 * Get the id value
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the id value
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the value value
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Set the value value
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
