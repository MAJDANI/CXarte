package com.novedia.talentmap.web.ui.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.CUtils;
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
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

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
	
	private Label searchResultsLabelNoResult;
	
	private ResourceBundle resourceBundle;
	
	
	private Panel skillPanel;
	
	private Button searchButton;
	
	private Tree treeSkills;
	
	private ISkillService skillService;
	
	
	 /**
     * POJO
     */
    private List<Colleague> listCollab;
    
    
    Authentication authentication ;
	
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
		authentication = TalentMapApplication.getCurrent().getAuthentication();
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES , locale);
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
		searchResultsPanel.addStyleName("searchResultsPanel");
		panelRight.addStyleName("panelRight");
		panelRight.setContent(searchByNameForm.buildSearchByNameFormView());
		panelRight.setWidth("800px");
	}
	
	private void buildResultsPanel(){
		searchResultsPanel.removeAllComponents();
		searchResultsPanel.addComponent(searchResultsLabelNoResult);
		searchResultsPanel.addComponent(searchResults);
		panelRight.addComponent(searchResultsPanel);
	}

	public void initListeners(){
		searchByNameForm.getNameField().addTextChangeListener(this);
		searchByCustomerForm.getCustomerField().addValueChangeListener(this);
	}
	
	private void buildComponents() {
		searchByCustomer.setCaption(resourceBundle.getString("search.by.customer.caption"));
		searchByCustomer.addClickListener(this);
		
		searchByName.setCaption(resourceBundle.getString("search.by.name.caption"));
		searchByName.addClickListener(this);
		
		searchBySkill.setCaption(resourceBundle.getString("search.by.skill.caption"));
		searchBySkill.addClickListener(this);
		
		CUtils.decorateButton(searchByName, searchByCustomer, searchBySkill);
		CUtils.decorateButtonAsLink(searchByCustomer,searchByName,searchBySkill);
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
		if (event.getButton().equals(searchByName)) { 
			CUtils.decorateButton(searchByName, searchByCustomer, searchBySkill);
			panelRight.setContent(searchByNameForm.buildSearchByNameFormView());
		} 
		else if (event.getButton().equals(searchByCustomer)) {
			CUtils.decorateButton(searchByCustomer, searchByName, searchBySkill);
			panelRight.setContent(searchByCustomerForm.buildSearchByCustomerFormView());
		} 
		else if(event.getButton().equals(searchBySkill)) {
			CUtils.decorateButton(searchBySkill, searchByCustomer, searchByName);
			skillPanel.removeAllComponents();
			treeSkills.removeAllItems();
			treeSkills.setImmediate(true);
			CUtils.buildTreeSkills(treeSkills, skillService);
			skillPanel.addComponent(treeSkills);
			searchButton.setCaption(resourceBundle.getString("search.button.caption"));
			searchButton.addClickListener(this);
			skillPanel.addComponent(searchButton);
			panelRight.setContent(skillPanel);
		} 
		else if (event.getButton().equals(searchButton)) {
			Set<Integer> skillsIdSetToSearch = buildToolListOfTreeSkills(treeSkills);
			List<Integer> skillsIdListToSearch = new ArrayList<Integer>(skillsIdSetToSearch);
			List<Integer> colleaguesIdList;
			if(authentication.getAuthorization().getRoleId().equals(Authorization.Role.CM.getId())){
				int managerId = authentication.getColleagueId();
				colleaguesIdList = skillService.getCmColleagueIdByListToolId(skillsIdListToSearch, managerId);
			}else {
				colleaguesIdList = skillService.getAllColleagueIdByListToolId(skillsIdListToSearch);
			}
			listCollab = collabService.getAllColleagueByColleagueIdList(colleaguesIdList);
			displayResult(listCollab);
				
		}
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Set<Integer> buildToolListOfTreeSkills(Tree treeSkills){
		
		Set<Integer> toolIdSelectedSet = new HashSet<Integer>();
		Collection<?> itemIds = treeSkills.getItemIds();
		for (Object itemId : itemIds) {
			
			if(itemId instanceof Category){
				if(treeSkills.isSelected(itemId)){
					Collection<Concept> allConceptOfCategory =  (Collection<Concept>) treeSkills.getChildren(itemId);
					for (Concept concept : allConceptOfCategory) {
						List<Integer> allToolIdOfConcept =  getAllItemIdChildrensOfConcept(treeSkills,concept);
						toolIdSelectedSet.addAll(allToolIdOfConcept);
					}
				}
			}
			else if(itemId instanceof Concept){
				if(treeSkills.isSelected(itemId)){
					List<Integer> allToolIdOfConcept =  getAllItemIdChildrensOfConcept(treeSkills,itemId);
					toolIdSelectedSet.addAll(allToolIdOfConcept);
				}
			} else if (itemId instanceof Tool) {
				if(treeSkills.isSelected(itemId)){
					Tool currentTool = (Tool) itemId;
					toolIdSelectedSet.add(currentTool.getId());
				}
			}
		}
		
		return toolIdSelectedSet;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getAllItemIdChildrensOfConcept(Tree treeSkills, Object itemId){
		Collection<Tool> childrens =  (Collection<Tool>) treeSkills.getChildren(itemId);
		List<Integer> toolId = new ArrayList<Integer>();
		for (Tool tool : childrens) {
			toolId.add(tool.getId());
		}
		return toolId;
	}
	
	
	
	public void displayResult(List<Colleague> listCollab){
		buildResultsPanel();
		if(listCollab == null || listCollab.isEmpty()){
			searchResultsLabelNoResult.setVisible(true);
			searchResults.setVisible(false);
		} else {
			searchResultsLabelNoResult.setVisible(false);
			searchResults = searchResults.buildSearchResultsView(listCollab);
			if(searchResults.size() < Constants.NB_ROWS_DEFAULT){
				searchResults.setPageLength(searchResults.size());
			} else{
				searchResults.setPageLength(Constants.NB_ROWS_DEFAULT);
			}
			searchResults.setVisible(true);
		}
	}
	
	
	@Override
	public void textChange(TextChangeEvent event) {
		if(event.getComponent().equals(searchByNameForm.getNameField())){
			String valueField = event.getText();
			valueField = valueField.trim();
			if(authentication.getAuthorization().getRoleId().equals(Authorization.Role.CM.getId())){
				int managerId = authentication.getColleagueId();
				listCollab = collabService.getCmColleaguesByName(valueField, managerId);
			} else {
				listCollab = collabService.getAllColleaguesByName(valueField);
			}
			displayResult(listCollab);
		}
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getProperty().getValue() == null){
			searchResultsLabelNoResult.setVisible(true);
			searchResults.setVisible(false);
		}else {
			Client client = (Client)event.getProperty().getValue();
			if (authentication.getAuthorization().getRoleId().equals(Authorization.Role.CM.getId())) {
				int managerId = authentication.getColleagueId();
				listCollab = collabService.getCmColleaguesByClient(client.getId(), managerId);
			} else {
				listCollab = collabService.getAllColleaguesByClient(client);
			}
			displayResult(listCollab);
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

	public Panel getSkillPanel() {
		return skillPanel;
	}


	public void setSkillPanel(Panel skillPanel) {
		this.skillPanel = skillPanel;
	}


	public Button getSearchButton() {
		return searchButton;
	}


	public void setSearchButton(Button searchButton) {
		this.searchButton = searchButton;
	}


	public Tree getTreeSkills() {
		return treeSkills;
	}


	public void setTreeSkills(Tree treeSkills) {
		this.treeSkills = treeSkills;
	}


	public ISkillService getSkillService() {
		return skillService;
	}


	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
	}


	
}
