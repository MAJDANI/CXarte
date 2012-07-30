package com.novedia.talentmap.web.ui;

import com.novedia.talentmap.web.ui.admin.AdminView;
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
public class TabMain extends TabSheet {
	
	/**
	 * All view
	 */
	private TabProfileSheet tabProfileSheet;
	private SearchView searchView;
	private AdminView adminView;
	
	/**
	 * Constants
	 */
	private final String TAB_PROFIL_NAME = "Profil";
	private final String TAB_SEARCH_NAME = "Recherche";
	private final String TAB_ADMIN_NAME = "Administration";
	
	/**
	 * Build the class TabMain.java 
	 * @param tabProfileSheet
	 * @param tabSearch
	 */
	public TabMain(TabProfileSheet tabProfileSheet, SearchView searchView, AdminView adminView) {
		super();
		this.tabProfileSheet = tabProfileSheet;
		this.searchView = searchView;
		this.adminView = adminView;
		
		setStyle(TalentMapCSS.TABSHEET);
		setImmediate(true);
		
		addTab(this.tabProfileSheet, TAB_PROFIL_NAME);
		addTab(this.searchView, TAB_SEARCH_NAME);
		addTab(this.adminView, TAB_ADMIN_NAME);
	}
	
	/**
	 * Set the tabProfileSheet value
	 * @param tabProfileSheet the tabProfileSheet to set
	 */
	public void setTabProfileSheet(TabProfileSheet tabProfileSheet) {
		this.tabProfileSheet = tabProfileSheet;
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
}
