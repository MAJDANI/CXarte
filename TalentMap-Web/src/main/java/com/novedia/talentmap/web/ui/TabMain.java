package com.novedia.talentmap.web.ui;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization.Role;
import com.novedia.talentmap.web.commons.ConstantsForMenuEnglish;
import com.novedia.talentmap.web.ui.EAE.CollabEAEView;
import com.novedia.talentmap.web.ui.admin.AdminView;
import com.novedia.talentmap.web.ui.cm.CmEAEView;
import com.novedia.talentmap.web.ui.cm.CmNotificationView;
import com.novedia.talentmap.web.ui.cm.CmView;
import com.novedia.talentmap.web.ui.profile.ProfileView;
import com.novedia.talentmap.web.ui.search.SearchView;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.TabSheet;

/**
 * The main tab contains the profile tab and the search tab
 * 
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */

@SuppressWarnings("serial")
public class TabMain extends TabSheet {

    /**
     * All view
     */
    private ProfileView profileView;
    private SearchView searchView;
    private AdminView adminView;
    private CmView cmView;
    private CmNotificationView cmNotificationView;
    private CmEAEView cmEAEView;
    private CollabEAEView collabEAEView;

    private Authentication authentication;

    /**
     * Default constructor
     */
    public TabMain() {
	super();
    }

    /**
     * Build the View according to user's role
     * 
     * @param role
     *            user's role
     * @return
     */
    public TabSheet buildViewAccordingToUser(Role role) {

	removeAllComponents();
	setStyleName(TalentMapCSS.TABSHEET);
	setImmediate(true);
	setAuthentication(authentication);
	if (role.equals(Role.AD)) { // Admin
	    adminView.setAuthentication(getAuthentication());
	    adminView = adminView.buildAdminView();
	    addTab(adminView, ConstantsForMenuEnglish.TAB_ADMIN_NAME);
	} else if (role.equals(Role.CL)) { // Colleague
	    // ONGLET PROFILE
	    profileView.setAuthentication(getAuthentication());
	    addTab(profileView.buildTabSheetProfilData(),
		    ConstantsForMenuEnglish.TAB_PROFIL_NAME);
	    // ONGLET EAE
	    collabEAEView.setAuthentication(getAuthentication());
	    collabEAEView = collabEAEView.buildViewCollabEAEView();
	    addTab(collabEAEView,
		    ConstantsForMenuEnglish.TAB_COLLEAGUE_EAE);
	} else if (role.equals(Role.IA)) { // IA
	    searchView.setAuthentication(getAuthentication());
	    searchView = searchView.buildSearchView();
	    addTab(searchView, ConstantsForMenuEnglish.TAB_SEARCH_NAME);
	} else if (role.equals(Role.CM)) { // CM
	    // ONGLET PROFILE
	    profileView.setAuthentication(getAuthentication());
	    addTab(profileView.buildTabSheetProfilData(),
		    ConstantsForMenuEnglish.TAB_PROFIL_NAME);
	    // ONGLET 
	    cmView.setAuthentication(getAuthentication());
	    cmView = cmView.buildCmView();
	    addTab(cmView, ConstantsForMenuEnglish.TAB_CM_NAME);
	    // ONGLET NOTIFICATIONS
	    cmNotificationView.setAuthentication(getAuthentication());
	    cmNotificationView = cmNotificationView.buildCmNotificationView();
	    addTab(cmNotificationView,
		    ConstantsForMenuEnglish.TAB_CM_NOTIFICATION);
	    // ONGLET EAE
	    cmEAEView.setAuthentication(getAuthentication());
	    cmEAEView = cmEAEView.buildViewCmEAEView();
	    addTab(cmEAEView,
		    ConstantsForMenuEnglish.TAB_CM_EAE);
	    
	} else if (role.equals(Role.RH)) { // RH
	    searchView.setAuthentication(getAuthentication());
	    addTab(searchView.buildSearchView(),
		    ConstantsForMenuEnglish.TAB_SEARCH_NAME);
	}

	return this;
    }

    /**
     * Get the authentication
     * 
     * @return
     */
    public Authentication getAuthentication() {
	return authentication;
    }

    /**
     * Set the authentication
     * 
     * @param authentication
     */
    public void setAuthentication(Authentication authentication) {
	this.authentication = authentication;
    }

    /**
     * Set the tabSearch value
     * 
     * @param tabSearch
     *            the tabSearch to set
     */
    public void setSearchView(SearchView searchView) {
	this.searchView = searchView;
    }

    /**
     * Get the adminView value
     * 
     * @return the adminView
     */
    public AdminView getAdminView() {
	return adminView;
    }

    /**
     * Set the adminView value
     * 
     * @param adminView
     *            the adminView to set
     */
    public void setAdminView(AdminView adminView) {
	this.adminView = adminView;
    }

    /**
     * @return the searchView
     */
    public SearchView getSearchView() {
	return searchView;
    }

    public ProfileView getProfileView() {
	return profileView;
    }

    public void setProfileView(ProfileView profileView) {
	this.profileView = profileView;
    }

    public CmView getCmView() {
	return cmView;
    }

    public void setCmView(CmView cmView) {
	this.cmView = cmView;
    }

    public CmNotificationView getCmNotificationView() {
	return cmNotificationView;
    }

    public void setCmNotificationView(CmNotificationView cmNotificationView) {
	this.cmNotificationView = cmNotificationView;
    }

    /**
     * @return the cmEAEView
     */
    public CmEAEView getCmEAEView() {
        return cmEAEView;
    }

    /**
     * @param cmEAEView the cmEAEView to set
     */
    public void setCmEAEView(CmEAEView cmEAEView) {
        this.cmEAEView = cmEAEView;
    }

    /**
     * @return the collabEAEView
     */
    public CollabEAEView getCollabEAEView() {
        return collabEAEView;
    }

    /**
     * @param collabEAEView the collabEAEView to set
     */
    public void setCollabEAEView(CollabEAEView collabEAEView) {
        this.collabEAEView = collabEAEView;
    }

}