package com.novedia.talentmap.web.commons;

public class ConstantsForMenuEnglish {

	/**
	 * For tree in the MonitoringCollabNanigation
	 */
	public static final String VISUALIZE_COLLAB_NAME = "synthetic view";
	public static final String SETUP_EA_NAME = "Enter data of EA";
	public static final String OBJECTIVE_NAME = "Goals of EA";

	public static final Object[][] subItems = new Object[][] { new Object[] {
			"Menu", VISUALIZE_COLLAB_NAME, SETUP_EA_NAME, OBJECTIVE_NAME } };

	/**
	 * For tree in the profileNavigation
	 */
	public static final String VISUALIZE_SKILLS_NAME = "skills";
	public static final String VISUALIZE_MISSIONS_NAME = "Historic missions";
	public static final String VISUALIZE_EA_NAME = "Historic EA";
	public static final String VISUALIZE_ADMINISTRATIVE_DATA = "administrative data";
	public static final Object[][] subItemProfilNav = new Object[][] { new Object[] {
			"Personal data", VISUALIZE_ADMINISTRATIVE_DATA, VISUALIZE_SKILLS_NAME,
			VISUALIZE_MISSIONS_NAME /*, VISUALIZE_EA_NAME */} };

	/**
	 * for tree in the SerachNavigation
	 */
	public static final String BY_CLIENT_BUTTON_NAME = "by customer";
	public static final String BY_NAME_BUTTON_NAME = "by name";
	public static final String BY_SKILLS_BUTTON_NAME = "by skills";

	public static final Object[][] subItemsOfSearch = new Object[][] { new Object[] {
			"search", BY_CLIENT_BUTTON_NAME, BY_NAME_BUTTON_NAME,
			BY_SKILLS_BUTTON_NAME } };

	public static final String TAB_SEARCH_BY_CUSTOMER = "by customer";
	public static final String TAB_SEARCH_BY_NAME = "by name";
	public static final String TAB_SEARCH_BY_SKILLS = "by skills";
	/**
	 * Constants for TabMain
	 */
	public static final String TAB_PROFIL_NAME = "Profile";
	public static final String TAB_SEARCH_NAME = "search";
	public static final String TAB_ADMIN_NAME = "Administration";
	
	/**
	 * Tree for admin navigation
	 */
	public static final String ADMIN_ADD_SKILL_NAME = "Add Category/Concept/Tool";
	public static final String ADMIN_VIEW_SKILL_NAME = "View Category/Concept/Tool";
	public static final String ADMIN_DELETE_COLLAB_NAME = "delete collaborator";
	public static final Object[][] ADMIN_MENU_NAVIGATION = new Object[][] { new Object[] {
			"Menu", ADMIN_VIEW_SKILL_NAME, ADMIN_ADD_SKILL_NAME,
			ADMIN_DELETE_COLLAB_NAME} };
	
	

//	public static final String ADD_SKILL_TITLE = "Ajouter une compétence à la liste";
//	public static final String UPDATE_SKILL_TITLE = "Visualisation des compétences";

}
