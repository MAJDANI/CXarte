package com.novedia.talentmap.web.utils;

import java.util.Hashtable;

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
 * VALIDATED_MANAGER ; quand le manager consulte un EAE d'un de ses collaborateurs
 * qui est dans l'état VALIDATED. 

 * CLOSED : quand le collaborateur ou le manager consulte un EAE CLOSED : aucun
 * champ n'est disponible à la saisie.
 * 
 * 
 *                                                _______________________________________________________________
 * _______________________________________________|	Etat EAE	|	OPEN		|	VALIDATED	|	CLOSED		|
 * |PROFIL		| ONGLET		| Elément						|				|				|				|
 * _____________________________________________________________________________________________|_______________|
 * |Manager		| Généralités	|Généralités					|Consultation	|Consultation	|Consultation	|
 * |			|				|Salaire						|Absent			|Consultation	|Consultation	|
 * |			| Bilan/Results	|Résumé année					|Absent			|Consultation	|Consultation	|
 * |			|				|Données objectif :				|				|				|				|
 * |			|				|  (But,date,indicateurs,Moyens)|Consultation	|Consultation	|Consultation	|	
 * |			|				|   Résults obj Note collab		|Absent			|Consultation	|Consultation	|	
 * |			|				|   Résults obj Note Manager	|Absent			|Modification	|Consultation	|	
 * |			|				|   Eléments freins ou moteurs	|Absent			|Consultation	|Consultation	|	
 * |			|				|   Commentaires Obj			|Absent			|Consultation	|Consultation	|	
 * |			|				|Points forts collab			|Absent			|Consultation	|Consultation	|	
 * |			|				|Points faibles collab			|Absent			|Consultation	|Consultation	|
 * |			|				|Moyens à fournir				|Absent			|Modification	|Consultation	|		
 * |			| Objectifs		|Bouton création				|				|Actif			|				|
 * |			|				|Formulaire de saisie :			|				|				|				|
 * |			|				|  (But,date,indicateurs,Moyens)|				|Actif			|Consultation	|
 * |			|				|   Résults obj Note collab		|				|Absent			|Consultation	|	
 * |			|				|   Résults obj Note Manager	|				|Absent			|Consultation	|	
 * |			|				|   Eléments freins ou moteurs	|				|Absent			|Consultation	|	
 * |			|				|   Commentaires Obj			|				|Absent			|Consultation	|	
 * |			| Synthèse		|Formulaire de saisie			|				|Actif			|Consultation	|	
 * _____________________________________________________________________________________________|_______________|
 * |Collab		| Généralités	|Généralités					|Consultation	|Consultation	|Consultation	|
 * |			|				|Salaire						|Modification	|Consultation	|Consultation	|
 * |			| Bilan/Results	|Résumé année					|Modification	|Consultation	|Consultation	|
 * |			|				|Données objectif				|				|				|				|
 * |			|				|  (But,date,indicateurs,Moyens)|Consultation	|Consultation	|Consultation	|	
 * |			|				|   Résults obj Note collab		|Modification	|Consultation	|Consultation	|	
 * |			|				|   Résults obj Note Manager	|Absent			|Absent			|Consultation	|	
 * |			|				|   Eléments freins ou moteurs	|Modification	|Consultation	|Consultation	|	
 * |			|				|   Commentaires Obj			|Modification	|Consultation	|Consultation	|	
 * |			|				|Points forts collab			|Modification	|Consultation	|Consultation	|	
 * |			|				|Points faibles collab			|Modification	|Consultation	|Consultation	|
 * |			|				|Moyens à fournir				|Absent			|Absent			|Consultation	|		
  * |			| Objectifs		|Bouton création				|				|				|				|
 * |			|				|Formulaire de saisie :			|				|				|				|
 * |			|				|  (But,date,indicateurs,Moyens)|				|				|Consultation	|
 * |			|				|   Résults obj Note collab		|				|				|Consultation	|	
 * |			|				|   Résults obj Note Manager	|				|				|Consultation	|	
 * |			|				|   Eléments freins ou moteurs	|				|				|Consultation	|	
 * |			|				|   Commentaires Obj			|				|				|Consultation	|	
 * |			| Synthèse		|Formulaire de saisie			|				|				|Consultation	|	
* ______________________________________________________________________________________________________________|
 * 
 * 
 * @author v.guillemain
 * 
 */
public enum EAEConsultationMode {

	OPEN_COLLAB(1, "OPEN_COLLAB"), OPEN_MANAGER(2, "OPEN_MANAGER"), VALIDATED_COLLAB(
			3, "VALIDATED_COLLAB"), VALIDATED_MANAGER(3, "VALIDATED_MANAGER"), CLOSED(
			4, "CLOSED");

	private int id;
	private String value;

	
	private EAEConsultationMode(int id, String value) {
		this.id = id;
		this.value = value;
	}

	public boolean isOPEN() {
		if (this == OPEN_COLLAB || this == OPEN_MANAGER) return true;
		else return false;
	}

	public boolean isVALIDATED() {
		if (this == VALIDATED_COLLAB || this == VALIDATED_MANAGER) return true;
		else return false;
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
