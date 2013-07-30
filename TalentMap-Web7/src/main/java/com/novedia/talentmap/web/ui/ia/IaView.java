package com.novedia.talentmap.web.ui.ia;

import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class IaView extends VerticalLayout {
	
	private GridLayout gridLayout;
	
	private Panel searchPanel;
	
	private Button searchButton;
	
	/**
	 * Default constructor
	 */
	public IaView(){
		super();
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
		
		searchPanel.removeAllComponents();
		searchButton.setCaption(Constants.SEARCH_LABEL);
		searchButton.setId(ComponentsId.SEARCH_BUTTON_ID);
		searchPanel.addComponent(searchButton);
		
		gridLayout.addComponent(searchPanel);
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
	
}
