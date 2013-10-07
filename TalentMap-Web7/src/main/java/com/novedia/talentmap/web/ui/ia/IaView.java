package com.novedia.talentmap.web.ui.ia;

import com.novedia.talentmap.web.ui.search.SearchPopIn;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class IaView extends VerticalLayout implements ClickListener {
	
	private GridLayout gridLayout;
	
	private Button searchButton;
	
	private SearchPopIn searchPopIn;
	
	
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
		
		searchButton.setId(ComponentsId.SEARCH_BUTTON_ID);
		searchButton.addClickListener(this);
		searchButton.addStyleName("labelBtnDashboard searchButton");
		
		gridLayout.addComponent(searchButton);
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
