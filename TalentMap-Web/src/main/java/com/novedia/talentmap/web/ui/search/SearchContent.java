package com.novedia.talentmap.web.ui.search;

import java.util.List;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.web.data.SearchTargetPanel;
import com.novedia.talentmap.web.util.ISearchContent;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class SearchContent extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3434654428791584784L;
	/**
	 * Vaadin UI
	 */
	private SearchTarget searchTarget;
	private SearchResults searchResults;

	/**
	 * Vaadin Components
	 */
	private Panel searchTargetPanel;
	private Panel searchResultsPanel;
	//Le panel à afficher quand les recherches ne retournent aucun résultat
	private Panel searchResultsPanelNoResult;
	private Label searchTargetLabel;
	private Label searchResultsLabel;
	private Label searchResultsLabelNoResult;

	/**
	 * Constants
	 */
	public static final String PANEL_TARGET_NAME = "Find an employee";
	public static final String PANEL_RESULTS_NAME = "List of colleagues of the research";
	public static final String PANEL_NO_RESULTS_NAME = "No colleague found";
	
	private static final int PAGE_SIZE = 5;
	
	/**
	 * Default constructor
	 */
	public SearchContent(){
		super();
	}
	
	
	public SearchContent buildSearchContentView(){
		removeAllComponents();
		searchTarget = searchTarget.buildSearchTargetView();
		searchResults = searchResults.buildSearchResultsView();
		buildObservators();
		mainBuild();
		return this;
	}
		

	
	public void buildObservators(){
		this.searchTarget.addObservateur(new ISearchContent() {
			
			@Override
			public void changeSearchResults(List<Colleague> listCollab, boolean clearState) {
				
				if(!clearState){
					if(listCollab.isEmpty()) {
						SearchContent.this.searchResultsPanel.setVisible(false);
						SearchContent.this.searchResultsPanelNoResult.setVisible(true);
					} else { 
						SearchContent.this.searchResults.removeAllItems();
						SearchContent.this.searchResults.buildResultsTable(listCollab);
						SearchContent.this.searchResults.setCurrentPage(1);
						refeshSearchResultsPanel();
						if(listCollab.size() > PAGE_SIZE){
							HorizontalLayout control = SearchContent.this.searchResults.createControls();
							 SearchContent.this.searchResultsPanel.addComponent(control);
							 SearchContent.this.searchResults.setPageLength(PAGE_SIZE);
						}
						SearchContent.this.searchResultsPanel.setVisible(true);
						SearchContent.this.searchResultsPanelNoResult.setVisible(false);
					}
				}
			}
		}, ISearchContent.class);
	}

	
	private void refeshSearchResultsPanel(){
		this.searchResultsPanel.removeAllComponents();
		this.searchResultsPanel.addComponent(this.searchResultsLabel);
		this.searchResultsPanel.addComponent(this.searchResults);
	}
	
	/**
	 * The main builder
	 * 
	 * @class SearchContent.java
	 */
	public void mainBuild() {
		
		buildSearchResults();
		
		buildLabel();

		buildPanel();
	}
	
	/**
	 * The Search Results builder
	 * @class SearchContent.java
	 */
	public void buildSearchResults(){
		
		this.searchResults.setColumnWidth("Email", 400);
		this.searchResults.setSizeFull();
	}

	/**
	 * Builder of the labels of panels
	 * 
	 * @class SearchContent.java
	 */
	public void buildLabel() {

		this.searchTargetLabel.setCaption(PANEL_TARGET_NAME);
		this.searchTargetLabel.addStyleName(TalentMapCSS.H2);
		
		this.searchResultsLabel.setCaption(PANEL_RESULTS_NAME);
		this.searchResultsLabel.addStyleName(TalentMapCSS.H2);

		this.searchResultsLabelNoResult.setCaption(PANEL_NO_RESULTS_NAME);
		this.searchResultsLabelNoResult.addStyleName(TalentMapCSS.H2);
}

	/**
	 * Builder of the panels
	 * 
	 * @class SearchContent.java
	 */
	public void buildPanel() {

		// Add the labels to the panels
		this.searchTargetPanel.addComponent(this.searchTargetLabel);
		this.searchResultsPanel.addComponent(this.searchResultsLabel);
		this.searchResultsPanelNoResult.addComponent(this.searchResultsLabelNoResult);

		// Add the UI to the panels
		this.searchTargetPanel.addComponent(this.searchTarget);
		
		this.searchResultsPanel.addComponent(this.searchResults);
	
		addComponent(this.searchTargetPanel);
		addComponent(this.searchResultsPanel);
		addComponent(this.searchResultsPanelNoResult);
		
		this.searchResultsPanel.setVisible(false);
		this.searchResultsPanelNoResult.setVisible(false);
	}
	
	/**
	 * 
	 * @class SearchContent.java
	 * @param searchTargetPanel
	 */
	public void switchPanel(int searchTargetPanel){
		
		if(searchTargetPanel == SearchTargetPanel.BY_NAME){
			
			this.searchTarget.switchByNamePanel();
		}
		
		if(searchTargetPanel == SearchTargetPanel.BY_CLIENT){
			
			this.searchTarget.switchByClientPanel();
		}
		
		if(searchTargetPanel == SearchTargetPanel.BY_SKILLS){
			
			this.searchTarget.switchBySkillsPanel();
		}
		
		clearSearchResults();
	}
	
	public void clearSearchResults(){
		
		this.searchResults.removeAllItems();
		this.searchResultsPanel.setVisible(false);
	}
	/**
	 * Get the searchTarget value
	 * 
	 * @return the searchTarget
	 */
	public SearchTarget getSearchTarget() {
		return searchTarget;
	}

	/**
	 * Set the searchTarget value
	 * 
	 * @param searchTarget
	 *            the searchTarget to set
	 */
	public void setSearchTarget(SearchTarget searchTarget) {
		this.searchTarget = searchTarget;
	}

	/**
	 * Get the searchResults value
	 * 
	 * @return the searchResults
	 */
	public SearchResults getSearchResults() {
		return searchResults;
	}

	/**
	 * Set the searchResults value
	 * 
	 * @param searchResults
	 *            the searchResults to set
	 */
	public void setSearchResults(SearchResults searchResults) {
		this.searchResults = searchResults;
	}

	/**
	 * Get the searchTargetPanel value
	 * 
	 * @return the searchTargetPanel
	 */
	public Panel getSearchTargetPanel() {
		return searchTargetPanel;
	}

	/**
	 * Set the searchTargetPanel value
	 * 
	 * @param searchTargetPanel
	 *            the searchTargetPanel to set
	 */
	public void setSearchTargetPanel(Panel searchTargetPanel) {
		this.searchTargetPanel = searchTargetPanel;
	}

	/**
	 * Get the searchResultsPanel value
	 * 
	 * @return the searchResultsPanel
	 */
	public Panel getSearchResultsPanel() {
		return searchResultsPanel;
	}

	/**
	 * Set the searchResultsPanel value
	 * 
	 * @param searchResultsPanel
	 *            the searchResultsPanel to set
	 */
	public void setSearchResultsPanel(Panel searchResultsPanel) {
		this.searchResultsPanel = searchResultsPanel;
	}
	
	/**
	 * Get the searchTargetLabel value
	 * @return the searchTargetLabel
	 */
	public Label getSearchTargetLabel() {
		return searchTargetLabel;
	}

	/**
	 * Set the searchTargetLabel value
	 * @param searchTargetLabel the searchTargetLabel to set
	 */
	public void setSearchTargetLabel(Label searchTargetLabel) {
		this.searchTargetLabel = searchTargetLabel;
	}

	/**
	 * Get the searchResultsLabel value
	 * @return the searchResultsLabel
	 */
	public Label getSearchResultsLabel() {
		return searchResultsLabel;
	}

	/**
	 * Set the searchResultsLabel value
	 * @param searchResultsLabel the searchResultsLabel to set
	 */
	public void setSearchResultsLabel(Label searchResultsLabel) {
		this.searchResultsLabel = searchResultsLabel;
	}


	public Panel getSearchResultsPanelNoResult() {
		return searchResultsPanelNoResult;
	}


	public void setSearchResultsPanelNoResult(Panel searchResultsPanelNoResult) {
		this.searchResultsPanelNoResult = searchResultsPanelNoResult;
	}


	public Label getSearchResultsLabelNoResult() {
		return searchResultsLabelNoResult;
	}


	public void setSearchResultsLabelNoResult(Label searchResultsLabelNoResult) {
		this.searchResultsLabelNoResult = searchResultsLabelNoResult;
	}
	
	

}
