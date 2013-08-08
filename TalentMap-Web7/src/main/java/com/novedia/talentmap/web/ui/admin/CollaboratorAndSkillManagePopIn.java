package com.novedia.talentmap.web.ui.admin;

import java.util.List;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.ui.search.SearchByNameForm;
import com.novedia.talentmap.web.ui.search.SearchResults;
import com.novedia.talentmap.web.utils.Constants;
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
public class CollaboratorAndSkillManagePopIn extends Window implements ClickListener,TextChangeListener {
	
	/**
	* TalentMap Services
	*/
	    private IColleagueService collabService;
	
	/**
	* Vaadin components
	*/
	private Button modifyAndDeleteSkillElement;
	
	private Button addSkillElement;
	
	private Button deleteCollaborator;
	
	private HorizontalLayout hLayout;
	
	private Panel panelLeft;
	
	private VerticalLayout menuContent;
	
	private Panel panelRight;
	
	private SearchResults searchResults;
	
	private Panel searchResultsPanel;
	
	private Panel searchResultsPanelNoResult;
	
	private Label searchResultsLabelNoResult;
	
	private SearchByNameForm searchByNameForm;
	
	/**
     * POJO
     */
    private List<Colleague> listCollab;
	
	/**
	 * Default constructor
	 */
     public CollaboratorAndSkillManagePopIn(){
    	 super();
    	 setWidth("1100px");
    	 setCaption(Constants.COLLABORATOR_AND_SKILL_MANAGE_POP_IN_TITLE);
    	 setModal(true);
     }
     
     /**
      * Build collaboratorAndSkillManagePopIn View
      * @return Window
      */
	public Window buildCollaboratorAndSkillManagePopIn(){
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

	private void buildComponents() {
		modifyAndDeleteSkillElement.setCaption(Constants.MODIFY_OR_DELETE_SKILL_ELEMENT_LABEL);
		modifyAndDeleteSkillElement.addStyleName(Reindeer.BUTTON_LINK);
		modifyAndDeleteSkillElement.addClickListener(this);
		
		addSkillElement.setCaption(Constants.ADD_SKILL_ELEMENT_LABEL);
		addSkillElement.addStyleName(Reindeer.BUTTON_LINK);
		addSkillElement.addClickListener(this);
		
		deleteCollaborator.setCaption(Constants.DELETE_COLLABORATOR_LABEL);
		deleteCollaborator.addStyleName(Reindeer.BUTTON_LINK);
		deleteCollaborator.addClickListener(this);
		
		searchResultsLabelNoResult.setValue(Constants.NO_COLLEAGUE_FOUND);
		
	}

	private void buildMenu() {
		 panelLeft.setWidth("200px");
		 panelLeft.addStyleName("panelLeft");
		 menuContent.setSpacing(true);
		 menuContent.setMargin(true);
		 menuContent.addComponent(modifyAndDeleteSkillElement);
		 menuContent.addComponent(addSkillElement);
		 menuContent.addComponent(deleteCollaborator);
		 panelLeft.setContent(menuContent);
		 hLayout.addComponent(panelLeft);
		
	}

	private void buildPanelRightContent() {
		panelRight.removeAllComponents();
		searchResultsPanel.removeAllComponents();
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

	private void initListeners() {
		searchByNameForm.getNameField().addTextChangeListener(this);
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void textChange(TextChangeEvent event) {
			Authentication authentication = TalentMapApplication.getCurrent().getAuthentication();
		
			if(event.getComponent().equals(searchByNameForm.getNameField())){
				String valueField = event.getText();
				valueField = valueField.trim();
				searchResults.setRoleId((authentication.getAuthorization().getRoleId()));
					
				listCollab = collabService.getAllColleaguesByName(valueField);
					
				if(listCollab.isEmpty()){
					searchResultsPanelNoResult.setVisible(true);
					searchResultsPanel.setVisible(false);
					
				} else {
					searchResultsPanel.removeAllComponents();
					searchResultsPanelNoResult.setVisible(false);
					searchResultsPanel.setVisible(true);
//					searchResults.buildSearchResultsView();
//					searchResults.buildResultsTable(listCollab);
//					searchResultsPanel.addComponent(searchResults);
					
				}
			}
		
	}

	public Button getModifyAndDeleteSkillElement() {
		return modifyAndDeleteSkillElement;
	}

	public void setModifyAndDeleteSkillElement(Button modifyAndDeleteSkillElement) {
		this.modifyAndDeleteSkillElement = modifyAndDeleteSkillElement;
	}

	public Button getAddSkillElement() {
		return addSkillElement;
	}

	public void setAddSkillElement(Button addSkillElement) {
		this.addSkillElement = addSkillElement;
	}

	public Button getDeleteCollaborator() {
		return deleteCollaborator;
	}

	public void setDeleteCollaborator(Button deleteCollaborator) {
		this.deleteCollaborator = deleteCollaborator;
	}

	public HorizontalLayout gethLayout() {
		return hLayout;
	}

	public void sethLayout(HorizontalLayout hLayout) {
		this.hLayout = hLayout;
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

	public Panel getPanelRight() {
		return panelRight;
	}

	public void setPanelRight(Panel panelRight) {
		this.panelRight = panelRight;
	}

	public SearchResults getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(SearchResults searchResults) {
		this.searchResults = searchResults;
	}

	public Panel getSearchResultsPanel() {
		return searchResultsPanel;
	}

	public void setSearchResultsPanel(Panel searchResultsPanel) {
		this.searchResultsPanel = searchResultsPanel;
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

	public SearchByNameForm getSearchByNameForm() {
		return searchByNameForm;
	}

	public void setSearchByNameForm(SearchByNameForm searchByNameForm) {
		this.searchByNameForm = searchByNameForm;
	}

	public List<Colleague> getListCollab() {
		return listCollab;
	}

	public void setListCollab(List<Colleague> listCollab) {
		this.listCollab = listCollab;
	}

	public IColleagueService getCollabService() {
		return collabService;
	}

	public void setCollabService(IColleagueService collabService) {
		this.collabService = collabService;
	}

}
