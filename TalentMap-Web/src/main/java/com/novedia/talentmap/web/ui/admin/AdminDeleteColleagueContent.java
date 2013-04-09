package com.novedia.talentmap.web.ui.admin;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jensjansson.pagedtable.PagedTable;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.AbstractSelect.MultiSelectMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class AdminDeleteColleagueContent extends VerticalLayout implements ClickListener ,ValueChangeListener, TextChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5865934349280936496L;
	
	/**
	 * 
	 */
	private IAdminService adminService;
	
	private Authentication authentication; 
	
	private IColleagueService colleagueService;
	
	private static final String[] VisibleColumn = {"First Name","Last Name","Email","Phone"};
	
	private static final String LABEL_BUTTON_DELETE = "Delete";
	private static final String LABEL_BUTTON_CANCEL = "Cancel";
	private static final String LABEL_BUTTON_SEARCH = "Search";
	private static final String LABEL_BUTTON_YES = "Yes";
	private static final String LABEL_BUTTON_NO = "No";
	private static final String CONFIRM_DELETE = "Are you sure you want to delete these colleagues ?";
	private static final String NO_RESULT_TEXT = "No colleague found";
	
	private static final int PAGE_SIZE = 5;
	
	/**
	 * vaadin componnents
	 */
	private HorizontalLayout containerButon;
	private Button delete ;
	private Button cancel ;
	private Button searchButton;
	private PagedTable colleagueList ;
	private HorizontalLayout containerSearch;
	private Panel searchPanel;
	private Panel resultPanel;
	private TextField searchField ;
	private Window popup;
	
	private BeanItemContainer<Colleague> colleagueContainer;
	
	/**
	 * Default constructor
	 */
	public AdminDeleteColleagueContent(){
		super();
	}
	
	
	/**
	 * Build the view that allow to delete a colleague
	 */
	public AdminDeleteColleagueContent mainBuild(){
		removeAllComponents();
		setMargin(true);
		setSpacing(true);
		intSearchPannel();
		initResultPanel();
		addComponent(searchPanel);
		resultPanel.setVisible(false);
		addComponent(resultPanel);
		return this;
	}
	
	
	/**
	 * init search pannel
	 */
	public void intSearchPannel(){
		searchPanel = new Panel();
		searchField = new TextField("Colleague Name :");
		searchField.addListener((TextChangeListener) this);
		searchButton = new Button(LABEL_BUTTON_SEARCH);
		searchButton.addListener((ClickListener) this);
		containerSearch = new HorizontalLayout();
		containerSearch.setSpacing(true);
		containerSearch.addComponent(searchField);
		containerSearch.addComponent(searchButton);
		searchPanel.addComponent(containerSearch); 
		
	}
	
	/**
	 * init table's colleague
	 */
	private void initColleagueTable(){
		colleagueList = new PagedTable();
		for (String property : VisibleColumn) {
			colleagueList.addContainerProperty(property, String.class,"");
		}
		colleagueList.setSelectable(true);
		colleagueList.setImmediate(true);
		colleagueList.setMultiSelect(true);
		colleagueList.setMultiSelectMode(MultiSelectMode.SIMPLE);
		colleagueList.setWidth("100%");
		colleagueList.setNullSelectionAllowed(true);
		colleagueList.addListener((Property.ValueChangeListener) this);
	}
	
	/**
	 * init result pannel
	 */
	private void initResultPanel(){
		resultPanel = new Panel();
		colleagueContainer = new BeanItemContainer<Colleague>(Colleague.class);
		initColleagueTable();
		buildContainerButton();
	}
	
	
	
	@Override
	public void textChange(final TextChangeEvent event) {
		String value = event.getText();
		value = value.trim();
		if(value.length() != 0){
			buildResultPanel(value);
		}
		
	}
	
	/**
	 * Build the reult pannel that will contain colleague list
	 * @param value the name of colleague
	 */
	private void buildResultPanel(String value){
		fillColleagueContainer(value);
		fillColleagueList();
		refreshResultPanel();
	}
	
	/**
	 * Fill the container of table  
	 * @param name
	 */
	private void fillColleagueContainer(String name){
		List<Colleague> colleagueResult = colleagueService.getAllColleaguesByLastName(name);
		colleagueContainer.removeAllItems();
		if(colleagueResult != null && !colleagueResult.isEmpty()){
			for (Colleague colleague : colleagueResult) {
					colleagueContainer.addBean(colleague);
			}
		}
		
	}
	
	
	
	/**
	 * fill the table to display
	 */
	private void fillColleagueList(){
		colleagueList.removeAllItems();
		Collection<Colleague> colleagueResult = colleagueContainer.getItemIds();
		for (Colleague colleague : colleagueResult) {
			colleagueList.addItem(new Object[]{colleague.getFirstName(),colleague.getLastName(),
					colleague.getEmail(),colleague.getPhone()},colleague);
		}
	}
	
	/**
	 * Refresh the view of result pannel
	 */
	private void refreshResultPanel(){
		resultPanel.removeAllComponents();
		resultPanel.setVisible(true);
		if(colleagueContainer.size() > 0){
			Label resultTitle = new Label("Colleague List");
			resultTitle.addStyleName(TalentMapCSS.H2);
			resultPanel.addComponent(resultTitle);
			colleagueList.setCurrentPage(1);
			resultPanel.addComponent(colleagueList);
			if(colleagueContainer.size() > PAGE_SIZE){
				resultPanel.addComponent(colleagueList.createControls());
				colleagueList.setPageLength(PAGE_SIZE);
			}
			resultPanel.addComponent(containerButon);
			
		} else {
			Label noResultText = new Label(NO_RESULT_TEXT);
			noResultText.addStyleName(TalentMapCSS.H2);
			resultPanel.addComponent(noResultText);
		}
	}
	
	
	/**
	 * Build container of button
	 */
	private void buildContainerButton(){
		containerButon = new HorizontalLayout();
		containerButon.setSpacing(true);
		containerButon.setMargin(true);
		containerButon.addStyleName("footerButton");
		delete = new Button(LABEL_BUTTON_DELETE);
		cancel = new Button(LABEL_BUTTON_CANCEL);
		
		delete.addListener((ClickListener) this);
		cancel.addListener((ClickListener) this);
		
		delete.setEnabled(false);
		cancel.setEnabled(false);
		containerButon.addComponent(delete);
		containerButon.addComponent(cancel);
		
	}
	

	/**
	 * build pop up confirmation
	 */
	public void buildPopup(){
	    popup = new Window("Confirm Delete");
		popup.setReadOnly(true);
		popup.setWidth("30%");
		HorizontalLayout confirmButtonContainer = new HorizontalLayout();
		Button yesButton = new Button(LABEL_BUTTON_YES);
		Button noButton = new Button(LABEL_BUTTON_NO);
		yesButton.addListener((ClickListener) this);
		noButton.addListener((ClickListener) this);
		confirmButtonContainer.setSpacing(true);
		confirmButtonContainer.setMargin(true);
		confirmButtonContainer.addStyleName("footerButton");
		confirmButtonContainer.addComponent(yesButton);
		confirmButtonContainer.addComponent(noButton);
		popup.addComponent(new Label(CONFIRM_DELETE));
		popup.addComponent(confirmButtonContainer);
		popup.setModal(true);
		getWindow().addWindow(popup);
	}
	


	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().getCaption().equals(LABEL_BUTTON_DELETE) ) {  //delete button
			buildPopup();
			
		} else if(event.getButton().getCaption().equals(LABEL_BUTTON_CANCEL)){  //cancel button
			colleagueList.setValue(null);
			
		} else if (event.getButton().getCaption().equals(LABEL_BUTTON_NO)) {  //no button
			getWindow().removeWindow(popup);
		} else if (event.getButton().getCaption().equals(LABEL_BUTTON_YES)) {  // yes button
			getWindow().removeWindow(popup);
			Set<Colleague> colleagueSelected = (Set<Colleague>) colleagueList.getValue();
			Map<String, Object> mapNotification = null;
			for (Colleague colleague : colleagueSelected) {
				 mapNotification = adminService.deleteColleague(colleague.getId());
			}
			
			if(mapNotification != null){
				CUtils.showMessage(mapNotification, getWindow());
			}
			buildResultPanel((String) searchField.getValue());
			
		} else if (event.getButton().getCaption().equals(LABEL_BUTTON_SEARCH)) {
			buildResultPanel((String) searchField.getValue());
		}
		
	}


	@Override
	public void valueChange(ValueChangeEvent event) {
		Set<Colleague> setItemSelected = (Set<Colleague>) colleagueList.getValue();
		  if (setItemSelected != null && setItemSelected.size() != 0){
			  delete.setEnabled(true);
			  cancel.setEnabled(true);
		  } else{
			  delete.setEnabled(false);
			  cancel.setEnabled(false);
		  }
	}
	
	/**
	 * Get the adminService
	 * @return adminService
	 */
	public IAdminService getAdminService() {
		return adminService;
	}
	
	/**
	 * Set the adminService
	 * @param adminService
	 */
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * Get the colleagueService
	 * @return colleagueService
	 */
	public IColleagueService getColleagueService() {
		return colleagueService;
	}

	/**
	 * Set the colleagueService
	 * @param colleagueService
	 */
	public void setColleagueService(IColleagueService colleagueService) {
		this.colleagueService = colleagueService;
	}


	public Authentication getAuthentication() {
		return authentication;
	}


	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
	
	
	
	
}