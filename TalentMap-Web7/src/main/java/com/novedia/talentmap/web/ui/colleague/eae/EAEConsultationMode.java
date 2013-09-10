package com.novedia.talentmap.web.ui.colleague.eae;

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
 *                                                _______________________________________________
 * _______________________________________________|	Etat EAE	|	OPEN		|	VALIDATED	|
 * |PROFIL		| ONGLET		| Elément						|				|				|
 * _____________________________________________________________________________________________|
 * |Manager		| Généralités	|Généralités					|Consultation	|Consultation	|
 * |			|				|Salaire						|Absent			|Consultation	|
 * |			| Bilan			|Résumé année					|Absent			|Consultation	|
 * |			|				|Données objectif				|Consultation	|Consultation	|
 * |			|				|Résults objectifs Note collab	|Absent			|Consultation	|	
 * |			|				|Résults objectifs Note Manager	|Absent			|Modification	|	
 * |			|				|Points forts collab			|Absent			|Consultation	|	
 * |			|				|Points faibles collab			|Absent			|Consultation	|
 * |			|				|Moyens à fournir				|Absent			|Modification	|		
 * |			| Objectfs		|Bouton création				|Onglet Absent	|Actif			|
 * |			|				|Formulaire de saisie			|Onglet Absent	|Actif			|	
 * |			| Synthèse		|Formulaire de saisie			|Onglet Absent	|Actif			|	
 * _____________________________________________________________________________________________|
 * |Collab		| Généralités	|Généralités					|Consultation	|Consultation	|
 * |			|				|Salaire						|Modification	|Consultation	|
 * |			| Bilan			|Résumé année					|Modification	|Consultation	|
 * |			|				|Données objectif				|Consultation	|Consultation	|
 * |			|				|Résults objectifs Note collab	|Modification	|Consultation	|	
 * |			|				|Résults objectifs Note Manager	|Absent			|Absent			|	
 * |			|				|Points forts collab			|Modification	|Consultation	|	
 * |			|				|Points faibles collab			|Modification	|Consultation	|
 * |			|				|Moyens à fournir				|Absent			|Absent			|		
 * |			| Objectfs		|Bouton création				|Onglet Absent	|Onglet Absent	|
 * |			|				|Formulaire de saisie			|Onglet Absent	|Onglet Absent	|	
 * |			| Synthèse		|Formulaire de saisie			|Onglet Absent	|Onglet Absent	|	
 * _____________________________________________________________________________________________|
 * 
 * 
 * @author v.guillemain
 * 
 */
public enum EAEConsultationMode {

	OPEN_COLLAB(1, "OPEN_COLLAB"), OPEN_MANAGER(2, "OPEN MANAGER"), VALIDATED_COLLAB(
			3, "VALIDATED_COLLAB"), VALIDATED_MANAGER(3, "VALIDATED_MANAGER"), CLOSED(
			4, "CLOSED");

	private int id;
	private String value;

	private EAEConsultationMode(int id, String value) {
		this.id = id;
		this.value = value;
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
