package com.novedia.talentmap.web.ui.search;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class SearchPopIn extends Window implements ClickListener,TextChangeListener,ValueChangeListener{
	
	 

	/**
    * TalentMap Services
    */
    private IColleagueService collabService;
	
	/**
	* Vaadin components
	*/
	private Button searchByName;
	
	private Button searchByCustomer;
	
	private Button searchBySkill;
	
	private HorizontalLayout hLayout;
	
	private Panel panelLeft;
	
	private VerticalLayout menuContent;
	
	private Panel panelRight;
	
	private SearchByNameForm searchByNameForm;
	
	private SearchByCustomerForm searchByCustomerForm;
	
	private SearchResults searchResults;
	
	private Panel searchResultsPanel;
	
	private Panel searchResultsPanelNoResult;
	
	private Label searchResultsLabelNoResult;
	
	private ResourceBundle resourceBundle;
	
	
	 /**
     * POJO
     */
    private List<Colleague> listCollab;
	
	/**
	 * Default constructor
	 */
     public SearchPopIn(){
    	 super();
    	 setWidth("1100px");
    	 setModal(true);
     }
     
     
     /**
      * Build profilePopIn View
      * @return Window
      */
	public Window buildSearchPopIn(){
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.SEARCH_POP_IN_PROPERTIES , locale);
		setCaption(resourceBundle.getString("window.search.popin.title"));
		removeAllComponents();
		hLayout.setSpacing(true);
		hLayout.removeAllComponents();
		buildComponents();
		buildMenu();
		buildPanelRightContent();
		initListeners();
		hLayout.addComponent(panelRight);
	    hLayout.setExpandRatio(panelRight, 1.0f);
		addComponent(hLayout);
		return this;
	}

	private void buildPanelRightContent() {
		panelRight.removeAllComponents();
		searchResultsPanel.removeAllComponents();
		searchResultsPanel.addStyleName("searchResultsPanel");
		panelRight.addStyleName("panelRight");
		panelRight.setContent(searchByNameForm.buildSearchByNameFormView());
		searchResultsPanelNoResult.addComponent(searchResultsLabelNoResult);
		buildResultsPanel();
		panelRight.setWidth("800px");
	}
	
	private void buildResultsPanel(){
		panelRight.addComponent(searchResultsPanelNoResult);
		panelRight.addComponent(searchResultsPanel);
		searchResultsPanelNoResult.setVisible(false);
		searchResultsPanel.setVisible(false);
	}

	public void initListeners(){
		searchByNameForm.getNameField().addTextChangeListener(this);
		searchByCustomerForm.getCustomerField().addValueChangeListener(this);
	}
	
	private void buildComponents() {
		searchByCustomer.setCaption(resourceBundle.getString("search.by.customer.caption"));
		searchByCustomer.addStyleName(Reindeer.BUTTON_LINK);
		searchByCustomer.removeStyleName("focus");
		searchByCustomer.addClickListener(this);
		
		searchByName.setCaption(resourceBundle.getString("search.by.name.caption"));
		searchByName.addStyleName(Reindeer.BUTTON_LINK);
		searchByName.addStyleName("focus");
		searchByName.addClickListener(this);
		
		searchBySkill.setCaption(resourceBundle.getString("search.by.skill.caption"));
		searchBySkill.addStyleName(Reindeer.BUTTON_LINK);
		searchBySkill.removeStyleName("focus");
		searchBySkill.addClickListener(this);
		
		searchResultsLabelNoResult.setValue(resourceBundle.getString("search.results.panel.no.result.label"));
		
	}


	private void buildMenu() {
	    panelLeft.setWidth("200px");
	    panelLeft.addStyleName("panelLeft");
	    menuContent.setSpacing(true);
	    menuContent.setMargin(true);
	    menuContent.addComponent(searchByName);
	    menuContent.addComponent(searchByCustomer);
	    menuContent.addComponent(searchBySkill);
	    panelLeft.setContent(menuContent);
	    hLayout.addComponent(panelLeft);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		panelRight.removeAllComponents();
		if (event.getButton().equals(searchByName)) { 
			searchByName.addStyleName("focus");
			searchByCustomer.removeStyleName("focus");
			searchBySkill.removeStyleName("focus");
			panelRight.setContent(searchByNameForm.buildSearchByNameFormView());
			buildResultsPanel();
		} else if (event.getButton().equals(searchByCustomer)) {
			searchByName.removeStyleName("focus");
			searchByCustomer.addStyleName("focus");
			searchBySkill.removeStyleName("focus");
			panelRight.setContent(searchByCustomerForm.buildSearchByCustomerFormView());
			buildResultsPanel();
		} else if(event.getButton().equals(searchBySkill)) {
			searchByName.removeStyleName("focus");
			searchByCustomer.removeStyleName("focus");
			searchBySkill.addStyleName("focus");
//			panelRight.setContent(missionColleagueContent.buildViewMissionColleagueContent());
		}
		
	}
	
	
	@Override
	public void textChange(TextChangeEvent event) {
		Authentication authentication = TalentMapApplication.getCurrent().getAuthentication();
		if(event.getComponent().equals(searchByNameForm.getNameField())){
			String valueField = event.getText();
			valueField = valueField.trim();
			searchResults.setRoleId((authentication.getAuthorization().getRoleId()));
			if(authentication.getAuthorization().getRoleId().equals(Authorization.Role.CM.getId())){
				int managerId = authentication.getColleagueId();
				listCollab = collabService.getCmColleaguesByName(valueField, managerId);
			} else {
				listCollab = collabService.getAllColleaguesByName(valueField);
			}
			
			if(listCollab.isEmpty()){
				searchResultsPanelNoResult.setVisible(true);
				searchResultsPanel.setVisible(false);
			} else {
				searchResultsPanel.removeAllComponents();
				searchResultsPanelNoResult.setVisible(false);
				searchResultsPanel.setVisible(true);
				searchResults = searchResults.buildSearchResultsView(listCollab);
				if(searchResults.size() < Constants.NB_ROWS_DEFAULT){
					searchResults.setPageLength(searchResults.size());
				} else{
					searchResults.setPageLength(Constants.NB_ROWS_DEFAULT);
				}
				searchResultsPanel.addComponent(searchResults);
			}
		}
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getProperty().getValue() == null){
			searchResultsPanelNoResult.setVisible(true);
			searchResultsPanel.setVisible(false);
		}else {
			Client client = (Client)event.getProperty().getValue();
			listCollab = collabService.getAllColleaguesByClient(client);
			if(listCollab.isEmpty()){
				searchResultsPanelNoResult.setVisible(true);
				searchResultsPanel.setVisible(false);
			}else{
				searchResultsPanel.removeAllComponents();
				searchResultsPanelNoResult.setVisible(false);
				searchResultsPanel.setVisible(true);
				searchResults.buildSearchResultsView(listCollab);
				if(searchResults.size() < Constants.NB_ROWS_DEFAULT){
					searchResults.setPageLength(searchResults.size());
				} else{
					searchResults.setPageLength(Constants.NB_ROWS_DEFAULT);
				}
				searchResultsPanel.addComponent(searchResults);
				
			}
		}
	}
	
	
	public Panel getPanelLeft() {
		return panelLeft;
	}


	public void setPanelLeft(Panel panelLeft) {
		this.panelLeft = panelLeft;
	}


	public VerticalLayout getMenuContent() {
		return menuContent;
	}


	public void setMenuContent(VerticalLayout menuContent) {
		this.menuContent = menuContent;
	}


	public HorizontalLayout gethLayout() {
		return hLayout;
	}


	public void sethLayout(HorizontalLayout hLayout) {
		this.hLayout = hLayout;
	}


	public Panel getPanelRight() {
		return panelRight;
	}


	public void setPanelRight(Panel panelRight) {
		this.panelRight = panelRight;
	}


	public Button getSearchByName() {
		return searchByName;
	}


	public void setSearchByName(Button searchByName) {
		this.searchByName = searchByName;
	}


	public Button getSearchByCustomer() {
		return searchByCustomer;
	}


	public void setSearchByCustomer(Button searchByCustomer) {
		this.searchByCustomer = searchByCustomer;
	}


	public Button getSearchBySkill() {
		return searchBySkill;
	}


	public void setSearchBySkill(Button searchBySkill) {
		this.searchBySkill = searchBySkill;
	}

	
	public SearchByNameForm getSearchByNameForm() {
		return searchByNameForm;
	}


	public void setSearchByNameForm(SearchByNameForm searchByNameForm) {
		this.searchByNameForm = searchByNameForm;
	}

	
	public IColleagueService getCollabService() {
		return collabService;
	}

	
	public void setCollabService(IColleagueService collabService) {
		this.collabService = collabService;
	}


	public List<Colleague> getListCollab() {
		return listCollab;
	}

	
	public void setListCollab(List<Colleague> listCollab) {
		this.listCollab = listCollab;
	}


	public SearchResults getSearchResults() {
		return searchResults;
	}


	public void setSearchResults(SearchResults searchResults) {
		this.searchResults = searchResults;
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


	public Panel getSearchResultsPanel() {
		return searchResultsPanel;
	}


	public void setSearchResultsPanel(Panel searchResultsPanel) {
		this.searchResultsPanel = searchResultsPanel;
	}


	public SearchByCustomerForm getSearchByCustomerForm() {
		return searchByCustomerForm;
	}


	public void setSearchByCustomerForm(SearchByCustomerForm searchByCustomerForm) {
		this.searchByCustomerForm = searchByCustomerForm;
	}

}
