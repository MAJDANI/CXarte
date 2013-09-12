package com.novedia.talentmap.web.ui.ia;

import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.ui.search.SearchPopIn;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class IaView extends VerticalLayout implements ClickListener {
	
	private GridLayout gridLayout;
	
	private Panel searchPanel;
	
	private Button searchButton;
	
	private SearchPopIn searchPopIn;
	
	private ResourceBundle resourceBundle;
	
	/**
	 * Default constructor
	 */
	public IaView(){
		super();
		addStyleName("centerPanel iaPanel");
	}
	
	/**
	 * Build IA's content view
	 * @return VerticalLayout
	 */
	public VerticalLayout buildIaContent(){
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.IA_VIEW_PROPERTIES , locale);
		removeAllComponents();
		buildContent();
		addComponent(gridLayout);
		return this;
	}


	private void buildContent() {
		
		gridLayout.removeAllComponents();
		gridLayout.setSpacing(true);
		gridLayout.setRows(2);
		gridLayout.setColumns(2);
		
		searchPanel.removeAllComponents();
		searchButton.setCaption(resourceBundle.getString("search.button.caption"));
		searchButton.setId(ComponentsId.SEARCH_BUTTON_ID);
		searchButton.addStyleName(Reindeer.BUTTON_LINK);
		searchButton.addClickListener(this);
		searchButton.addStyleName("labelBtnDashboard");
		searchPanel.addComponent(searchButton);
		
		gridLayout.addComponent(searchPanel);
	}
	

	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().equals(searchButton)){
			getUI().addWindow(searchPopIn.buildSearchPopIn());
		}
		
	}
	

	public GridLayout getGridLayout() {
		return gridLayout;
	}

	public void setGridLayout(GridLayout gridLayout) {
		this.gridLayout = gridLayout;
	}

	public Panel getSearchPanel() {
		return searchPanel;
	}

	public void setSearchPanel(Panel searchPanel) {
		this.searchPanel = searchPanel;
	}

	public Button getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(Button searchButton) {
		this.searchButton = searchButton;
	}

	public SearchPopIn getSearchPopIn() {
		return searchPopIn;
	}

	public void setSearchPopIn(SearchPopIn searchPopIn) {
		this.searchPopIn = searchPopIn;
	}

}
