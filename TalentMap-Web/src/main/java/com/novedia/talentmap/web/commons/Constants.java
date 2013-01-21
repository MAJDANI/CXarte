package com.novedia.talentmap.web.commons;

public class Constants {

	/**
	 * Général - Messages
	 */
	public static final String MSG_MISSING_FIELDS = "Les champs ne sont pas tous remplis";

	/**
	 * Données Profil - Messages
	 */
	public static final String PROFILE_MSG_DATA_SAVED_OK = "Vos données ont été enregistrées";
	public static final String PROFILE_MSG_DATA_SAVED_KO = "Vos données n'ont pas pu être enregistrées";
	public static final String PROFILE_MSG_FIELD_REQUIRED_PART1 = "Le champ \"";
	public static final String PROFILE_MSG_FIELD_REQUIRED_PART2 = "\" est obligatoire";

	/**
	 * Champ Vide
	 */
	public static final String FIELD_NULL_REPRESENTATION = "Non renseigné";

	/**
	 * Données Profil - Collaborateur
	 */
	public static final Object[] NAME_FIELD_COLLABORATOR = new Object[] {
		"Nom", "Prénom", "Profil", "Email", "Tél", "Date d'entrée Novedia",
		"Années d'expérience", "Ingénieur d'affaire" };

	public static final String FIELD_COLLAB_LAST_NAME = "lastName";
	public static final String FIELD_COLLAB_FIRST_NAME = "firstName";
	public static final String FIELD_COLLAB_PROFILE_ID = "profileId";
	public static final String FIELD_COLLAB_EMAIL = "email";
	public static final String FIELD_COLLAB_PHONE = "phone";
	public static final String FIELD_COLLAB_EMPLOYMENT_DATE = "employmentDate";
	public static final String FIELD_COLLAB_EXPERIENCE = "experience";
	public static final String FIELD_COLLAB_BUISINESS_ENGINEER = "businessEngineer";

	public static final Object[] FIELD_ORDER_COLLABORATOR = new Object[] {
		FIELD_COLLAB_LAST_NAME, 
		FIELD_COLLAB_FIRST_NAME, 
		FIELD_COLLAB_PROFILE_ID, 
		FIELD_COLLAB_EMAIL, 
		FIELD_COLLAB_PHONE,
		FIELD_COLLAB_EMPLOYMENT_DATE, 
		FIELD_COLLAB_EXPERIENCE, 
		FIELD_COLLAB_BUISINESS_ENGINEER };

	/**
	 * Données Profil - Mission
	 */
	public static final Object[] NAME_FIELD_MISSION = new Object[] { "Intitulé", "Client",
		"Lieu", "Début mission", "Fin mission", "Commentaire" };

	public static final String FIELD_MISSION_NAME = "name";
	public static final String FIELD_MISSION_CLIENT = "client";
	public static final String FIELD_MISSION_PLACE = "place";
	public static final String FIELD_MISSION_START_DATE = "startDate";
	public static final String FIELD_MISSION_END_DATE = "endDate";
	public static final String FIELD_MISSION_NOTES = "notes";

	public static final Object[] FIELD_ORDER_MISSION = new Object[] { 
		FIELD_MISSION_NAME, 
		FIELD_MISSION_CLIENT,
		FIELD_MISSION_PLACE, 
		FIELD_MISSION_START_DATE, 
		FIELD_MISSION_END_DATE, 
		FIELD_MISSION_NOTES };

	/**
	 * Données Mission - Messages
	 */
	public static final String MISSION_MSG_DATA_SAVED_OK = "La mission a bien été mise à jour";
	public static final String MISSION_MSG_DATA_SAVED_KO = "Attention, la mission n'a pas été mise à jour";
	public static final String MISSION_MSG_DATA_INSERTED_OK = "La mission a bien été ajoutée";
	public static final String MISSION_MSG_DATA_INSERTED_KO = "Attention, la mission n'a pas été ajoutée";

	public static final String MISSION_MSG_INVALID_PERIOD = "La période est invalide";
}
