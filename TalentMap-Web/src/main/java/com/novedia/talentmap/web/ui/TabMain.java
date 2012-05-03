package com.novedia.talentmap.web.ui;

import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class TabMain extends TabSheet {
	
	private TabProfileSheet tabProfileSheet;
	private VerticalLayout tabSearch;
	private final String TAB_FIRST_NAME = "Profil";
	private final String TAB_SECOND_NAME = "Recherche";
	
	/**
	 * Build the class TabMain.java 
	 * @param tabProfileSheet
	 * @param tabSearch
	 */
	public TabMain(TabProfileSheet tabProfileSheet, VerticalLayout tabSearch) {
		super();
		this.tabProfileSheet = tabProfileSheet;
		this.tabSearch = tabSearch;
		
		setStyle(Reindeer.TABSHEET_MINIMAL);
		
		tabSearch.addComponent(new Label("Rien pour le moment !!"));
		addTab(tabProfileSheet, TAB_FIRST_NAME);
		addTab(tabSearch, TAB_SECOND_NAME);
		
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
