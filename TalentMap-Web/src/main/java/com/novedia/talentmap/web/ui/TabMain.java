package com.novedia.talentmap.web.ui;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization.Role;
import com.novedia.talentmap.web.commons.ConstantsForMenuEnglish;
import com.novedia.talentmap.web.ui.admin.AdminView;
import com.novedia.talentmap.web.ui.role.CmContentLayout;
import com.novedia.talentmap.web.ui.role.RhContentLayout;
import com.novedia.talentmap.web.ui.search.SearchView;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.TabSheet;

/**
 * The main tab contains the profile tab and the search tab
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
	private TabProfileSheet tabProfileSheet;
	private SearchView searchView;
	private AdminView adminView;
	private RhContentLayout rhContentLayout;
	private CmContentLayout cmContentLayout; 
	
	private Authentication authentication;
	
	/**
	 * Default constructor
	 */
	public TabMain(){
		super();
	}
	
	/**
	 * Build the View according to user's role
	 * @param role user's role
	 * @return
	 */
	public TabSheet buildViewAccordingToUser(Role role){
		
		removeAllComponents();
		setStyleName(TalentMapCSS.TABSHEET);
		setImmediate(true);
		setAuthentication(authentication);
		if(role.equals(Role.AD)){    //Admin
			adminView = adminView.buildAdminView();
			addTab(adminView, ConstantsForMenuEnglish.TAB_ADMIN_NAME);
		} else if (role.equals(Role.CL)) {   //Colleague
			tabProfileSheet.setAuthentication(getAuthentication());
			addTab(tabProfileSheet.buildTabSheetProfile(), ConstantsForMenuEnglish.TAB_PROFIL_NAME);
		} else if(role.equals(Role.IA)){    //IA
			searchView = searchView.buildSearchView();
			addTab(searchView, ConstantsForMenuEnglish.TAB_SEARCH_NAME);
		} else if (role.equals(Role.CM)) {  //CM
			cmContentLayout = cmContentLayout.init();
			addTab(cmContentLayout,"cm");
		} else if (role.equals(Role.RH)) {  //RH
			rhContentLayout = rhContentLayout.init();
			addTab(rhContentLayout,"rh");
		}
		
		return this;
	}
	
	
	/**
	 * Set the tabProfileSheet value
	 * @param tabProfileSheet the tabProfileSheet to set
	 */
	public void setTabProfileSheet(TabProfileSheet tabProfileSheet) {
		this.tabProfileSheet = tabProfileSheet;
	}
	
	/**
	 * Get the authentication
	 * @return
	 */
	public Authentication getAuthentication() {
		return authentication;
	}

	/**
	 * Set the authentication
	 * @param authentication
	 */
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	/**
	 * Set the tabSearch value
	 * @param tabSearch the tabSearch to set
	 */
	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}
	
	/**
	 * Get the adminView value
	 * @return the adminView
	 */
	public AdminView getAdminView() {
		return adminView;
	}

	/**
	 * Set the adminView value
	 * @param adminView the adminView to set
	 */
	public void setAdminView(AdminView adminView) {
		this.adminView = adminView;
	}

	/**
	 * @return the tabProfileSheet
	 */
	public TabProfileSheet getTabProfileSheet() {
		return tabProfileSheet;
	}

	/**
	 * @return the searchView
	 */
	public SearchView getSearchView() {
		return searchView;
	}

	public RhContentLayout getRhContentLayout() {
		return rhContentLayout;
	}

	public void setRhContentLayout(RhContentLayout rhContentLayout) {
		this.rhContentLayout = rhContentLayout;
	}

	public CmContentLayout getCmContentLayout() {
		return cmContentLayout;
	}

	public void setCmContentLayout(CmContentLayout cmContentLayout) {
		this.cmContentLayout = cmContentLayout;
	}
	
	

}