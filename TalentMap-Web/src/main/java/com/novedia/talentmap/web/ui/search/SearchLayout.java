package com.novedia.talentmap.web.ui.search;

import com.novedia.talentmap.web.util.ISearchLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

public class SearchLayout extends HorizontalLayout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9108933129376360461L;
	
	/**
	 * Vaadin UI
	 */
	private SearchContent searchContent;
	private HorizontalSplitPanel hSplitPanel;
	private SearchNavigation searchNavigation;

	/**
	 * Build the class SearchLayout.java 
	 * @param searchContent
	 * @param hSplitpanel
	 * @param searchNavigation
	 */
	public SearchLayout(SearchContent searchContent,
			HorizontalSplitPanel hSplitPanel, SearchNavigation searchNavigation) {
		super();
		this.searchContent = searchContent;
		this.hSplitPanel = hSplitPanel;
		this.searchNavigation = searchNavigation;
		
		buildObservators();
		
		mainBuild();
	}
	
	public void buildObservators(){
		
		this.searchNavigation.addObservateur(new ISearchLayout() {
			
			@Override
			public void switchPanelTarget(int searchTargetPanel) {
				
				SearchLayout.this.searchContent.switchPanel(searchTargetPanel);
			}
		}, ISearchLayout.class);
	}
	
	public void mainBuild(){
		
		setMargin(true);
		setSpacing(true);
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.addComponent(this.searchNavigation);
		vLayout.setHeight(800);
		
		this.hSplitPanel.setFirstComponent(vLayout);
		this.hSplitPanel.setSecondComponent(this.searchContent);
		this.hSplitPanel.setSplitPosition(20);
		this.hSplitPanel.setLocked(true);
		
		addComponent(this.hSplitPanel);
		setSizeFull();
		setExpandRatio(this.hSplitPanel, 1);
	}
	
	/**
	 * Get the searchContent value
	 * @return the searchContent
	 */
	public SearchContent getSearchContent() {
		return searchContent;
	}

	/**
	 * Set the searchContent value
	 * @param searchContent the searchContent to set
	 */
	public void setSearchContent(SearchContent searchContent) {
		this.searchContent = searchContent;
	}

	/**
	 * Get the hSplitpanel value
	 * @return the hSplitpanel
	 */
	public HorizontalSplitPanel gethSplitpanel() {
		return hSplitPanel;
	}

	/**
	 * Set the hSplitpanel value
	 * @param hSplitpanel the hSplitpanel to set
	 */
	public void sethSplitpanel(HorizontalSplitPanel hSplitPanel) {
		this.hSplitPanel = hSplitPanel;
	}

	/**
	 * Get the searchNavigation value
	 * @return the searchNavigation
	 */
	public SearchNavigation getSearchNavigation() {
		return searchNavigation;
	}

	/**
	 * Set the searchNavigation value
	 * @param searchNavigation the searchNavigation to set
	 */
	public void setSearchNavigation(SearchNavigation searchNavigation) {
		this.searchNavigation = searchNavigation;
	}

	
}
