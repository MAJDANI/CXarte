package com.novedia.talentmap.web.ui.admin;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.util.CUtils;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.AbstractSelect.MultiSelectMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class AdminDeleteColleagueContent extends VerticalLayout implements ClickListener, ValueChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5865934349280936496L;
	
	/**
	 * 
	 */
	private IAdminService adminService;
	
	private IColleagueService colleagueService;
	
	private static final String[] colleagueProperties = new String[]{"ID","ManagerID","ProfileID","First Name",
		"Last Name","Email","Phone"};
	
	private static final String[] VisibleColumn = {colleagueProperties[3],colleagueProperties[4],colleagueProperties[5],
		colleagueProperties[6]};
	
	private static final int PAGE_LENGHT = 20; 
	
	private static final String LABEL_BUTTON_DELETE = "Delete";
	private static final String LABEL_BUTTON_CANCEL = "Cancel";
	private static final String LABEL_BUTON_SEARCH = "Search";
	private static final String LABEL_BUTON_YES = "Yes";
	private static final String LABEL_BUTON_NO = "No";
	private static final String CONFIRM_DELETE = "Are you sure you want to delete these colleagues ?";
	
	private IndexedContainer ColleagueContainer ;
	
	private HorizontalLayout containerButon;
	private Button delete ;
	private Button cancel ;
	private Table colleagueList ;
	private HorizontalLayout containerSearch;
	private TextField searchField ;
	private Window popup;
	
	public AdminDeleteColleagueContent(IColleagueService colleagueService,IAdminService adminService){
		super();
		this.adminService = adminService;
		this.colleagueService = colleagueService;
		//mainBuild();
	}
	
	
	public void mainBuild(){
		removeAllComponents();
		setMargin(true);
		setSpacing(true);
		builColleagueContainer();
		initColleagueList();
		intSearch();
		addComponent(containerSearch);
		addComponent(colleagueList);
		buildButton();
		addComponent(containerButon);
	}
	
	
	public void intSearch(){
		
		searchField = new TextField();
		searchField.addListener(new TextChangeListener() {
             public void textChange(final TextChangeEvent event) {
            	 ColleagueContainer.removeAllContainerFilters();
            	 ColleagueContainer.addContainerFilter(new ContactFilter(event.getText()));
             }
     });
		
		containerSearch = new HorizontalLayout();
		containerSearch.setSpacing(true);
		containerSearch.addComponent(searchField);
		
	}
	
	
	public void buildButton(){
		containerButon = new HorizontalLayout();
		containerButon.setSpacing(true);
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
	 * table to display
	 */
	public void initColleagueList(){
		colleagueList = new Table();
		colleagueList.setContainerDataSource(ColleagueContainer);
		colleagueList.setVisibleColumns(VisibleColumn);
		colleagueList.setSelectable(true);
		colleagueList.setImmediate(true);
		colleagueList.setMultiSelect(true);
		colleagueList.setMultiSelectMode(MultiSelectMode.SIMPLE);
		colleagueList.setWidth("100%");
		colleagueList.setNullSelectionAllowed(true);
		colleagueList.addListener((Property.ValueChangeListener) this);
	}
	
	/**
	 * fill all colleague in the container
	 */
	public void builColleagueContainer(){
		List<Colleague> allColleague = colleagueService.getAllColleagues();
		if (allColleague != null) {
			ColleagueContainer = new IndexedContainer();
			for (String property : colleagueProperties) {
				ColleagueContainer.addContainerProperty(property, String.class,"");
			}
			
			for (Colleague colleague : allColleague) {
				Object id = ColleagueContainer.addItem();
				ColleagueContainer.getContainerProperty(id, colleagueProperties[0]).setValue(colleague.getId());
				ColleagueContainer.getContainerProperty(id, colleagueProperties[1]).setValue(colleague.getManagerId());
				ColleagueContainer.getContainerProperty(id, colleagueProperties[2]).setValue(colleague.getProfileId());
				ColleagueContainer.getContainerProperty(id, colleagueProperties[3]).setValue(colleague.getFirstName());
				ColleagueContainer.getContainerProperty(id, colleagueProperties[4]).setValue(colleague.getLastName());
				ColleagueContainer.getContainerProperty(id, colleagueProperties[5]).setValue(colleague.getEmail());
				ColleagueContainer.getContainerProperty(id, colleagueProperties[6]).setValue(colleague.getPhone());
			}
		} 		
		
	}

	/**
	 * build pop up confirmation
	 */
	public void buildPopup(){
	    popup = new Window("Confirm Delete");
		popup.setReadOnly(true);
		popup.setWidth("30%");
		HorizontalLayout confirmationButtonContainer = new HorizontalLayout();
		Button yesButton = new Button(LABEL_BUTON_YES);
		Button noButton = new Button(LABEL_BUTON_NO);
		yesButton.addListener((ClickListener) this);
		noButton.addListener((ClickListener) this);
		confirmationButtonContainer.setSpacing(true);
		confirmationButtonContainer.setMargin(true);
		confirmationButtonContainer.addComponent(yesButton);
		confirmationButtonContainer.addComponent(noButton);
		popup.addComponent(new Label(CONFIRM_DELETE));
		popup.addComponent(confirmationButtonContainer);
		popup.setModal(true);
		
		getWindow().addWindow(popup);
	}
	


	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().getCaption().equals(LABEL_BUTTON_DELETE) ) {  //delete button
			buildPopup();
			
		} else if(event.getButton().getCaption().equals(LABEL_BUTTON_CANCEL)){  //cancel button
			colleagueList.setValue(null);
			
		} else if (event.getButton().getCaption().equals(LABEL_BUTON_NO)) {  //no button
			getWindow().removeWindow(popup);
		} else if (event.getButton().getCaption().equals(LABEL_BUTON_YES)) {  // yes button
			getWindow().removeWindow(popup);
			Set<Object> setItemSelected = (Set<Object>) colleagueList.getValue();
			Map<String, Object> mapNotification = null;
			for (Object itemId : setItemSelected) {
				int colleagueId =  Integer.parseInt((String) colleagueList.getItem(itemId).getItemProperty(colleagueProperties[0]).getValue());
				 mapNotification = adminService.deleteColleague(colleagueId);
				int typeError =(Integer) mapNotification.get("typeError");
				if( typeError == 1){
					colleagueList.removeItem(itemId);
				}
			}
			
			if(mapNotification != null){
				CUtils.showMessage(mapNotification, getWindow());
			}
			
		}
		
	}


	@Override
	public void valueChange(ValueChangeEvent event) {
		Set<Object> setItemSelected = (Set<Object>) colleagueList.getValue();
		  if (setItemSelected != null && setItemSelected.size() != 0){
			  delete.setEnabled(true);
			  cancel.setEnabled(true);
		  } else{
			  delete.setEnabled(false);
			  cancel.setEnabled(false);
		  }
	}
	
	
	
	
	 private class ContactFilter implements Filter {
         private String neededValue;

         public ContactFilter(String neededValue) {
                 this.neededValue = neededValue.toLowerCase();
         }
         
         @Override
         public boolean passesFilter(Object itemId, Item item) {
                 String haystack = ("" + item.getItemProperty(colleagueProperties[3]).getValue()
                                 + item.getItemProperty(colleagueProperties[4]).getValue()).toLowerCase();
                 return haystack.contains(neededValue);
         }

         public boolean appliesToProperty(Object id) {
                 return true;
         }

		
 }
	
	
	
	
}