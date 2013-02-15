package com.novedia.talentmap.web.commons;

public class ConstantsForMenuInglish {

	/**
	 * For tree in the MonitoringCollabNanigation
	 */
	public static final String VISUALIZE_COLLAB_NAME = "synthetic view";
	public static final String SETUP_EA_NAME = "Enter data of IA";
	public static final String OBJECTIVE_NAME = "Goals of IA";

	public static final Object[][] subItems = new Object[][] { new Object[] {
			"Menu", VISUALIZE_COLLAB_NAME, SETUP_EA_NAME, OBJECTIVE_NAME } };

	/**
	 * For tree in the profileNavigation
	 */
	public static final String VISUALIZE_SKILLS_NAME = "skills";
	public static final String VISUALIZE_MISSIONS_NAME = "Historic missions";
	public static final String VISUALIZE_EA_NAME = "Historic IA";
	public static final String VISUALIZE_PROFILE_NAME = "administrative data";
	public static final Object[][] subItemProfilNav = new Object[][] { new Object[] {
			"Menu", VISUALIZE_PROFILE_NAME, VISUALIZE_SKILLS_NAME,
			VISUALIZE_MISSIONS_NAME, VISUALIZE_EA_NAME } };

	/**
	 * for tree in the SerachNavigation
	 */
	public static final String BY_CLIENT_BUTTON_NAME = "by customer";
	public static final String BY_NAME_BUTTON_NAME = "by name";
	public static final String BY_SKILLS_BUTTON_NAME = "by skills";

	public static final Object[][] subItemsOfSearch = new Object[][] { new Object[] {
			"Menu", BY_CLIENT_BUTTON_NAME, BY_NAME_BUTTON_NAME,
			BY_SKILLS_BUTTON_NAME } };

	/**
	 * Constants for tag
	 */
	public static final String TAB_PROFIL_NAME = "Profile";
	public static final String TAB_SEARCH_NAME = "search";
	public static final String TAB_ADMIN_NAME = "Administration";
	
	/**
	 * Constants for admin
	 */
	public static final String ADD_SKILL_NAME = "Add skill";
	public static final String VISUALIZE_SKILL_NAME = "View skills";
	public static final String DELETE_COLLAB_NAME = "delete collaborator";


}
