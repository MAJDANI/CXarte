package com.novedia.talentmap.web.ui.admin;

import java.util.List;
import java.util.Set;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ManageColleaguePopIn extends Window implements TextChangeListener, ClickListener {
	
	private IAdminService adminService;
	
	private VerticalLayout content;
	
	private Panel searchPanel;
	
	private Panel searchResultPanel;
	
	private TextField colleagueNameField;
	
	private ColleagueContainer colleagueContainer;
	
	private Table colleagueTableList;
	
	private HorizontalLayout containerButton;
	
	private Button deleteColleagueButton;
	
	private Window windowConfirm;
	
	private Label confirmDeleteLabel;
    
    private HorizontalLayout confirmButtonContainer;
    
    private Button yesButton;
    
    private Button noButton;
	
	
	
	public ManageColleaguePopIn(){
		super();
		setCaption(Constants.DELETE_COLLEAGUE_VIEW_TITLE);
		setModal(true);
	}
	
	public Window buildManageColleagueView(){
		initColleagueListProperty();
		content.removeAllComponents();
		content.setSpacing(true);
		buildSearchPanel();
		content.addComponent(searchPanel);
		buildResultPanel();
		buildContainerButton();
		content.addComponent(searchResultPanel);
		searchResultPanel.addComponent(containerButton);
		addComponent(content);
		searchResultPanel.setVisible(false);
		return this;
	}
	
	
	private void buildSearchPanel(){
		searchPanel.removeAllComponents();
		colleagueNameField.setCaption(Constants.TEXT_FIELD_SEARCH_COLLEAGUE_NAME_LABEL);
		colleagueNameField.addTextChangeListener(this);
		searchPanel.addComponent(colleagueNameField);
	}
	
	private void buildResultPanel(){
		searchResultPanel.removeAllComponents();
		searchResultPanel.addComponent(colleagueTableList);
	}

	@Override
	public void textChange(TextChangeEvent event) {
		String value = event.getText();
		value = value.trim();
		refreshResultPanel(value);
	}
	
	private void refreshResultPanel(String value){
		fillResultPanel(value);
		enableOrDisableSearchResultPanel();
	}
	
	private void enableOrDisableSearchResultPanel(){
		if(colleagueTableList.size() > 0){
			searchResultPanel.setVisible(true);
		}else {
			searchResultPanel.setVisible(false);
		}
	}
	
	private void initColleagueListProperty(){
		for (String property : Constants.VISIBLE_COlUMN) {
		    colleagueTableList.addContainerProperty(property, String.class, "");
		}
		colleagueTableList.addStyleName("table");
		colleagueTableList.setSelectable(true);
		colleagueTableList.setImmediate(true);
		colleagueTableList.setNullSelectionAllowed(true);
		colleagueTableList.setMultiSelect(true);
	}
	
	private void fillResultPanel(String value){
		colleagueTableList.removeAllItems();
		colleagueContainer.fillContainerByName(value);
		List<Colleague> colleagueResult = colleagueContainer.getItemIds();
		if(colleagueResult != null && !colleagueResult.isEmpty()){
			for (Colleague colleague : colleagueResult) {
				colleagueTableList.addItem(
					    new Object[] { colleague.getFirstName(),
						    colleague.getLastName(), colleague.getEmail(),
						    colleague.getPhone() }, colleague);
			}
		}
		if (colleagueTableList.size() < Constants.NB_ROWS_DEFAULT) {
			colleagueTableList.setPageLength(colleagueTableList.size());
		} else {
			colleagueTableList.setPageLength(Constants.NB_ROWS_DEFAULT);
		}
	}
	
	
	private void buildContainerButton(){
		containerButton.removeAllComponents();
		containerButton.setSpacing(true);
		containerButton.addStyleName("containerButton");
		deleteColleagueButton.setCaption(Constants.DELETE_BUTTON_LABEL);
		deleteColleagueButton.addClickListener(this);
		containerButton.addComponent(deleteColleagueButton);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().equals(deleteColleagueButton)){
			buildConfirmWindow();
			getUI().addWindow(windowConfirm);
		} else if (event.getButton().equals(yesButton)) {
			windowConfirm.close();
			delecteColleague();
		} else if (event.getButton().equals(noButton)) {
			windowConfirm.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void delecteColleague(){
		Set<Colleague> colleagueSelected = (Set<Colleague>) colleagueTableList.getValue();
		adminService.deleteColleague(colleagueSelected);
		refreshResultPanel(colleagueNameField.getValue());
	}
	
	private void buildConfirmWindow(){
		windowConfirm.removeAllComponents();
		windowConfirm.setCaption(Constants.WINDOW_CONFIRM_DELETE_TITLE);
		windowConfirm.center();
		windowConfirm.setModal(true);
		windowConfirm.setReadOnly(true);
		confirmDeleteLabel.setCaption(Constants.DELETE_COLLEAGUE_MSG);
		confirmButtonContainer.setSpacing(true);
		confirmButtonContainer.addStyleName("containerButton");
		yesButton.setCaption(Constants.CONFIRM_DELETE_MISSION_LABEL);
		yesButton.addStyleName("styleButton");
		yesButton.addClickListener(this);
		noButton.setCaption(Constants.CANCEL_DELETE_MISSION_LABEL);
		noButton.addStyleName("styleButton");
		noButton.addClickListener(this);
		confirmButtonContainer.addComponent(yesButton);
		confirmButtonContainer.addComponent(noButton);
		windowConfirm.addComponent(confirmDeleteLabel);
		windowConfirm.addComponent(confirmButtonContainer);
	 }
	
	

	public VerticalLayout getContent() {
		return content;
	}

	public void setContent(VerticalLayout content) {
		this.content = content;
	}

	public Panel getSearchPanel() {
		return searchPanel;
	}

	public void setSearchPanel(Panel searchPanel) {
		this.searchPanel = searchPanel;
	}

	public Panel getSearchResultPanel() {
		return searchResultPanel;
	}

	public void setSearchResultPanel(Panel searchResultPanel) {
		this.searchResultPanel = searchResultPanel;
	}

	public TextField getColleagueNameField() {
		return colleagueNameField;
	}

	public void setColleagueNameField(TextField colleagueNameField) {
		this.colleagueNameField = colleagueNameField;
	}

	public ColleagueContainer getColleagueContainer() {
		return colleagueContainer;
	}

	public void setColleagueContainer(ColleagueContainer colleagueContainer) {
		this.colleagueContainer = colleagueContainer;
	}

	public Table getColleagueTableList() {
		return colleagueTableList;
	}

	public void setColleagueTableList(Table colleagueList) {
		this.colleagueTableList = colleagueList;
	}

	public HorizontalLayout getContainerButton() {
		return containerButton;
	}

	public void setContainerButton(HorizontalLayout containerButton) {
		this.containerButton = containerButton;
	}

	public Button getDeleteColleagueButton() {
		return deleteColleagueButton;
	}

	public void setDeleteColleagueButton(Button deleteColleagueButton) {
		this.deleteColleagueButton = deleteColleagueButton;
	}

	public Window getWindowConfirm() {
		return windowConfirm;
	}

	public void setWindowConfirm(Window windowConfirm) {
		this.windowConfirm = windowConfirm;
	}

	public Label getConfirmDeleteLabel() {
		return confirmDeleteLabel;
	}

	public void setConfirmDeleteLabel(Label confirmDeleteLabel) {
		this.confirmDeleteLabel = confirmDeleteLabel;
	}

	public HorizontalLayout getConfirmButtonContainer() {
		return confirmButtonContainer;
	}

	public void setConfirmButtonContainer(HorizontalLayout confirmButtonContainer) {
		this.confirmButtonContainer = confirmButtonContainer;
	}

	public Button getYesButton() {
		return yesButton;
	}

	public void setYesButton(Button yesButton) {
		this.yesButton = yesButton;
	}

	public Button getNoButton() {
		return noButton;
	}

	public void setNoButton(Button noButton) {
		this.noButton = noButton;
	}

	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

}
