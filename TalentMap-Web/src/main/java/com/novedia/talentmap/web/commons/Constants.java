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
	 * Données Profil - Collaborateur :"Nom", "Prénom", "Profil", "Email",
	 * "Tél", "Date d'entrée Novedia", "Années d'expérience",
	 * "Ingénieur d'affaire"
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
			FIELD_COLLAB_LAST_NAME, FIELD_COLLAB_FIRST_NAME,
			FIELD_COLLAB_PROFILE_ID, FIELD_COLLAB_EMAIL, FIELD_COLLAB_PHONE,
			FIELD_COLLAB_EMPLOYMENT_DATE, FIELD_COLLAB_EXPERIENCE,
			FIELD_COLLAB_BUISINESS_ENGINEER, FIELD_COLLAB_MANAGER };

	/**
	 * Données Profil - Mission
	 */
	public static final Object[] NAME_FIELD_MISSION = new Object[] {
			"Intitulé", "Client", "Lieu", "Début mission", "Fin mission",
			"Commentaire" };

	public static final String FIELD_MISSION_NAME = "title";
	public static final String FIELD_MISSION_CLIENT = "client";
	public static final String FIELD_MISSION_PLACE = "place";
	public static final String FIELD_MISSION_START_DATE = "startDate";
	public static final String FIELD_MISSION_END_DATE = "endDate";
	public static final String FIELD_MISSION_NOTES = "notes";

	public static final Object[] FIELD_ORDER_MISSION = new Object[] {
			FIELD_MISSION_NAME, FIELD_MISSION_CLIENT, FIELD_MISSION_PLACE,
			FIELD_MISSION_START_DATE, FIELD_MISSION_END_DATE,
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
	 * Données Skill management
	 */
	public static final Object[] FIELD_ORDER_SKILL = new Object[] {
			"categoryName", "conceptName", "toolName" };

	public static final String SKILL_MANAGEMENT_CATEGORY = "categoryName";
	public static final String SKILL_MANAGEMENT_CONCEPT = "conceptName";
	public static final String SKILL_MANAGEMENT_TOOL = "toolName";

	public static final Object[] NAME_FIELD_SKILL = new Object[] { "Catégorie",
			"Concept", "Outil" };
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

	/**
	 * For Registration
	 */
	public static final Object[] NAME_FIELD_REGISTRATION = new Object[] {
			"Nom", "Prénom", "Mot de passe", "Confirmer le mot de passe",
			"Email", "Téléphone", "Date d'embauche", "Profile",
			"Années d'expérience", "Ingénieur d'affaire", "Manager" };
	public static final Object[] FIELD_ORDER_REGISTRATION = new Object[] {
			"lastName", "firstName", "password", "passwordConfirm", "email",
			"phone", "employmentDate", "profileId", "experience",
			"businessEngineer", "managerId" };

	/**
	 * Constants for MissionCollaboratorContent
	 */
	public static final String PAGE_TITLE = "Liste des missions";
	public static final String LABEL_BUTTON_ADD_MISSION = "Ajouter une mission";
	public static final String LABEL_BUTTON_MODIFY_MISSION = "Modifier la mission";
	public static final String LABEL_BUTTON_DELETE_MISSION = "Supprimer la mission";

	/**
	 * Labels form confirmation delete window (Classe :
	 * MissionCollaboratorContent)
	 */
	public static final String LABEL_WINDOW_CONFIRM_DELETE = "Confirmation de Suppression de mission";
	public static final String LABEL_BUTTON_CONFIRM_DELETE_MISSION = "Supprimer la mission";
	public static final String LABEL_BUTTON_CANCEL_DELETE_MISSION = "NE PAS Supprimer la mission";

	/**
	 * Constants for AddSkillPanel
	 */
	public static final String ADD_SKILL_LABEL = "Ajouter une compétence";
	public static final String CATEGORY_LABEL = "Catégorie :";
	public static final String CONCEPT_LABEL = "Concept :";
	public static final String TOOL_LABEL = "Outil :";
	public static final String FREQUENCY_USE = "Usage de l'outil :";
	public static final String NO_TIME_USING = "Durée de non utilisation de l'outil :";
	public static final String[] OPTIONS = new String[] { "Débutant",
			"Intermédiare", "Professionnel", "Maitrise", "Expert" };

	/**
	 * Constant for RegistrationScreen
	 */
	public static final String SAVE_BUTTON_NAME = "Enregistrer";
	public static final String CANCEL_BUTTON_NAME = "Annuler";
	public static final String REGISTRATION_PANEL_NAME = "Registration";
	public static final String REGISTRATION_PANEL_WIDTH = "800px";
	public static final String REGISTRATION_PANEL_FOOTER_HEIGHT = "50px";
	public static final String REGISTRATION_PANEL_MISSING_FIELDS = "Error one or many fields are missing";
	public static final String REGISTRATION_PANEL_PASSWORD_ERROR = "Error on your password confirmation";
	public static final String REGISTRATION_PANEL_USER_CREATION_ERROR = "Error when creating user";

	/**
	 * Constants for MonitoringCollabContent
	 */
	public static final String VISUALIZE_CV_NAME = "CV";
	public static final String VISUALIZE_MISSION_NAME = "Historique Mission";
	public static final String VISUALIZE_EA_NAME = "Historique EA";
	public static final String VISUALIZE_PROFILE_NAME = "Visualiser Profil";
	public static final String PAGE_TITLE_COLAB = "Liste de vos Collaborateurs";

	/**
	 * Constants for AdminContentLayout
	 */
	public static final String ADD_SKILL_TITLE = "Ajouter une compétence à la liste";
	public static final String UPDATE_SKILL_TITLE = "Visualisation des compétences";

	/**
	 * Constants for TabProfileSheet
	 */
	public static final String TAB_PROFILE_NAME = "Fiche Profil";
	public static final String TAB_COLLAB_NAME = "Suivi des Collaborateur";

}
