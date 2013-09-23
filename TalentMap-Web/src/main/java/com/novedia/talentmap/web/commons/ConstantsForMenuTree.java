package com.novedia.talentmap.web.commons;

public class ConstantsForMenuTree {

    /**
     * For tree in the MonitoringCollabNanigation
     */
    public static final String VISUALIZE_COLLAB_NAME = "Vue synthétique";
    public static final String SETUP_EA_NAME = "Saisir un EA";
    public static final String OBJECTIVE_NAME = "Objectifs d'un EA";

    public static final Object[][] subItems = new Object[][] { new Object[] {
	    "Menu", VISUALIZE_COLLAB_NAME, SETUP_EA_NAME, OBJECTIVE_NAME } };

    /**
     * For tree in the profileNavigation
     */
    public static final String VISUALIZE_SKILLS_NAME = "Compétences";
    public static final String VISUALIZE_MISSIONS_NAME = "Historique des missions";
    public static final String VISUALIZE_EA_NAME = "Historique EA";
    public static final String VISUALIZE_PROFILE_NAME = "Données administratives";
    public static final Object[][] subItemProfilNav = new Object[][] { new Object[] {
	    "Menu", VISUALIZE_PROFILE_NAME, VISUALIZE_SKILLS_NAME,
	    VISUALIZE_MISSIONS_NAME, VISUALIZE_EA_NAME } };

    /**
     * for tree in the SerachNavigation
     */
    public static final String BY_CLIENT_BUTTON_NAME = "Par client";
    public static final String BY_NAME_BUTTON_NAME = "Par nom";
    public static final String BY_SKILLS_BUTTON_NAME = "Par compétences";

    public static final Object[][] subItemsOfSearch = new Object[][] { new Object[] {
	    "Menu", BY_CLIENT_BUTTON_NAME, BY_NAME_BUTTON_NAME,
	    BY_SKILLS_BUTTON_NAME } };

    /**
     * Constants for tag
     */
    public final String TAB_PROFIL_NAME = "Profil";
    public final String TAB_SEARCH_NAME = "Recherche";
    public final String TAB_ADMIN_NAME = "Administration";

    /**
     * Constants for admin
     */
    public static final String ADD_SKILL_NAME = "Ajouter une compétence";
    public static final String VISUALIZE_SKILL_NAME = "Visualiser les compétences";
    public static final String DELETE_COLLAB_NAME = "Supprimer des collaborateurs";

}
