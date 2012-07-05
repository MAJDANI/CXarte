package com.novedia.talentmap.web.ui;

import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * The main tab contains the profile tab and the search tab
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */
public class TabMain extends TabSheet {
	
	private TabProfileSheet tabProfileSheet;
	private VerticalLayout tabSearch;
	private final String TAB_PROFIL_NAME = "Profil";
	private final String TAB_SEARCH_NAME = "Recherche";
	
	/**
	 * Build the class TabMain.java 
	 * @param tabProfileSheet
	 * @param tabSearch
	 */
	public TabMain(TabProfileSheet tabProfileSheet, VerticalLayout tabSearch) {
		super();
		this.tabProfileSheet = tabProfileSheet;
		this.tabSearch = tabSearch;
		
		setStyle(TalentMapCSS.TABSHEET);
		setImmediate(true);
		
		tabSearch.addComponent(new Label("Rien pour le moment !!"));
		addTab(tabProfileSheet, TAB_PROFIL_NAME);
		addTab(tabSearch, TAB_SEARCH_NAME);
		
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
	public void setTabSearch(VerticalLayout tabSearch) {
		this.tabSearch = tabSearch;
	}
	
	
}
