package com.novedia.talentmap.web.ui.search;

import java.util.List;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.web.data.SearchTargetPanel;
import com.novedia.talentmap.web.util.ISearchContent;
import com.novedia.talentmap.web.util.TalentMapCSS;
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
	private Label searchTargetLabel;
	private Label searchResultsLabel;

	/**
	 * Constants
	 */
	public static final String PANEL_TARGET_NAME = "Find an employee";
	public static final String PANEL_RESULTS_NAME = "List of collaborators of the research";

	/**
	 * Build the class SearchContent.java
	 * 
	 * @param searchTarget
	 * @param searchResults
	 * @param searchTargetPanel
	 * @param searchResultsPanel
	 */
	public SearchContent(SearchTarget searchTarget,
			SearchResults searchResults, Panel searchTargetPanel,
			Panel searchResultsPanel, Label searchTargetLabel,
			Label searchResultsLabel) {
		super();
		this.searchTarget = searchTarget;
		this.searchResults = searchResults;
		this.searchTargetPanel = searchTargetPanel;
		this.searchResultsPanel = searchResultsPanel;
		this.searchTargetLabel = searchTargetLabel;
		this.searchResultsLabel = searchResultsLabel;
		
		buildObservators();
		
		mainBuild();
	}
	
	public void buildObservators(){
		this.searchTarget.addObservateur(new ISearchContent() {
			
			@Override
			public void changeSearchResults(List<Colleague> listCollab, boolean clearState) {
				
				if(!clearState){
					
					SearchContent.this.searchResults.removeAllItems();
					SearchContent.this.searchResults.buildResultsTable(listCollab);
					SearchContent.this.searchResultsPanel.setVisible(true);
				}
			}
		}, ISearchContent.class);
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

		// Add the UI to the panels
		this.searchTargetPanel.addComponent(this.searchTarget);
		
		this.searchResultsPanel.addComponent(this.searchResults);
	
		addComponent(this.searchTargetPanel);
		addComponent(this.searchResultsPanel);
		
		this.searchResultsPanel.setVisible(false);
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

	// /**
	// *
	// */
	// private static final long serialVersionUID = 6174726310018981436L;
	//
	// private HorizontalLayout rech;
	// private Button button_ok;
	// private ComboBox liste_noms;
	// private Label nom;
	//
	//
	// public SearchContent(){
	// this.setImmediate(false);
	// this.setDescription("Nom");
	// this.setWidth("347px");
	// this.setHeight("557px");
	// this.setMargin(false);
	// this.addComponent(partieRecherche());
	// }
	//
	// private HorizontalLayout partieRecherche(){
	//
	// // nom
	// nom = new Label();
	// nom.setImmediate(false);
	// nom.setWidth("-1px");
	// nom.setHeight("-1px");
	// nom.setValue("Nom");
	// rech.addComponent(nom);
	// rech.setComponentAlignment(nom, new Alignment(48));
	//
	// // liste_noms
	// liste_noms = new ComboBox();
	// liste_noms.setImmediate(false);
	// liste_noms.setWidth("-1px");
	// liste_noms.setHeight("-1px");
	// rech.addComponent(liste_noms);
	// rech.setComponentAlignment(liste_noms, new Alignment(33));
	//
	// // button_ok
	// button_ok = new Button();
	// button_ok.setCaption("OK");
	// button_ok.setImmediate(true);
	// button_ok.setWidth("-1px");
	// button_ok.setHeight("-1px");
	// rech.addComponent(button_ok);
	// rech.setComponentAlignment(button_ok, new Alignment(33));
	//
	// return rech;
	//
	// }
	//
	// // rech
	// rech = buildrech();
	// verticalSplitPanel_1.addComponent(rech);
	//
	// // table_1
	// table_1 = new Table();
	// table_1.setImmediate(false);
	// table_1.setWidth("-1px");
	// table_1.setHeight("-1px");
	// verticalSplitPanel_1.addComponent(table_1);
	//
	// return verticalSplitPanel_1;
	//

}
