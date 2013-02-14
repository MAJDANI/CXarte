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
	 * Données Profil - Collaborateur :"Nom", "Prénom", "Profil", "Email", "Tél", "Date d'entrée Novedia", "Années d'expérience", "Ingénieur d'affaire"
	 */
	public static final Object[] NAME_FIELD_COLLABORATOR = new Object[] {
		"Nom", "Prénom", "Profil", "Email", "Tél", "Date d'entrée Novedia",
		"Années d'expérience", "Ingénieur d'affaire", "Manager" };

	public static final String FIELD_COLLAB_LAST_NAME = "lastName";
	public static final String FIELD_COLLAB_FIRST_NAME = "firstName";
	public static final String FIELD_COLLAB_PROFILE_ID = "profileId";
	public static final String FIELD_COLLAB_EMAIL = "email";
	public static final String FIELD_COLLAB_PHONE = "phone";
	public static final String FIELD_COLLAB_EMPLOYMENT_DATE = "employmentDate";
	public static final String FIELD_COLLAB_EXPERIENCE = "experience";
	public static final String FIELD_COLLAB_BUISINESS_ENGINEER = "businessEngineer";
	public static final String FIELD_COLLAB_MANAGER = "managerId";

	public static final Object[] FIELD_ORDER_COLLABORATOR = new Object[] {
		FIELD_COLLAB_LAST_NAME, 
		FIELD_COLLAB_FIRST_NAME, 
		FIELD_COLLAB_PROFILE_ID, 
		FIELD_COLLAB_EMAIL, 
		FIELD_COLLAB_PHONE,
		FIELD_COLLAB_EMPLOYMENT_DATE, 
		FIELD_COLLAB_EXPERIENCE, 
		FIELD_COLLAB_BUISINESS_ENGINEER,
		FIELD_COLLAB_MANAGER};

	/**
	 * Données Profil - Mission
	 */
	public static final Object[] NAME_FIELD_MISSION = new Object[] { "Intitulé", "Client",
		"Lieu", "Début mission", "Fin mission", "Commentaire" };

	public static final String FIELD_MISSION_NAME = "title";
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

	public static final String ADMIN_DATA_LABEL = "Données administratives";
	public static final String LAST_MISSION_LABEL = "Dernière mission";
	public static final String ADMIN_DATA_SAVE_BUTTON = "Enregistrer";
	public static final String ADMIN_DATA_CANCEL_BUTTON = "Annuler";
	public static final String ADMIN_DATA_EDIT_BUTTON = "Editer";
	
	
	/**
	 * Données Mission - Messages
	 */
	public static final String MISSION_MSG_DATA_SAVED_OK = "La mission a bien été mise à jour";
	public static final String MISSION_MSG_DATA_SAVED_KO = "Attention, la mission n'a pas été mise à jour";
	public static final String MISSION_MSG_DATA_INSERTED_OK = "La mission a bien été ajoutée";
	public static final String MISSION_MSG_DATA_INSERTED_KO = "Attention, la mission n'a pas été ajoutée";

	public static final String MISSION_MSG_INVALID_PERIOD = "La période est invalide";
	
	/**
	 * Données Registration
	 */
	public static final String REGISTRATION_EMPLOYMENT_DATE_FIELD = "employmentDate";
	public static final String REGISTRATION_BUSINESS_ENGINEER_FIELD = "businessEngineer";
	public static final String REGISTRATION_PROFILE_FIELD = "profileId";
	public static final String REGISTRATION_MANAGER_FIELD = "managerId";
	public static final String REGISTRATION_PASSWORD_FIELD = "password";
	public static final String REGISTRATION_PASSWORD_CONFIRM_FIELD = "passwordConfirm";
	public static final String REGISTRATION_PHONE_FIELD = "phone";
	public static final String REGISTRATION_EXPERIENCE_FIELD = "experience";
	public static final String REGISTRATION_EMAIL_FIELD = "email";
	
	public static final String REGISTRATION_NULL_REPRESENTATION = "";
	public static final String REGISTRATION_DATE_FORMAT = "dd/MM/yyyy";
	public static final String REGISTRATION_SELECT_VALUE = "value";
	public static final String REGISTRATION_SELECT_STYLE = "type-profile";
	public static final String REGISTRATION_NUMBER_REGEXP = "[0-9]+";
	public static final String REGISTRATION_EMAIL_REGEXP = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$";
	public static final String REGISTRATION_ERROR_PHONE_NUMBER = "Numéro de téléphone incorrecte";
	public static final String REGISTRATION_ERROR_EXPERIENCE = "Années d'expérience incorrectes";
	public static final String REGISTRATION_ERROR_EMAIL = "Email non valide";
	
	
}
