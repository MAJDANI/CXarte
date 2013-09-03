package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class AdminView extends VerticalLayout implements ClickListener {
	
	/**
	* Vaadin components
	*/
	private GridLayout gridLayout;
	
	private Panel manageColleaguePanel;
	
	private Panel manageToolPanel;
	
	private Button deleteColleagueButton;
	
	private Button manageToolButton;
	
	private ManageColleaguePopIn manageColleaguePopIn;
	
	private ManageToolsPopIn manageToolsPopIn;
	
	/**
	 * Default constructor
	 */
	public AdminView(){
		super();
		addStyleName("centerPanel adminPanel");
	}
	
	
	/**
	 * Build Admin's content view
	 * @return VerticalLayout
	 */
	public VerticalLayout buildAdminContent(){
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
		
		manageColleaguePanel.removeAllComponents();
		deleteColleagueButton.setCaption(Constants.DELETE_COLLEAGUE_LABEL);
		deleteColleagueButton.addStyleName(Reindeer.BUTTON_LINK);
		deleteColleagueButton.addClickListener(this);
		deleteColleagueButton.addStyleName("labelBtnDashboard");
		manageColleaguePanel.addComponent(deleteColleagueButton);
		
		manageToolPanel.removeAllComponents();
		manageToolButton.setCaption(Constants.MANAGE_TOOL_LABEL);
		manageToolButton.addStyleName(Reindeer.BUTTON_LINK);
		manageToolButton.addClickListener(this);
		manageToolButton.addStyleName("labelBtnDashboard");
		manageToolPanel.addComponent(manageToolButton);
		
		gridLayout.addComponent(manageColleaguePanel);
		gridLayout.addComponent(manageToolPanel);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().equals(deleteColleagueButton)){
			
			getUI().addWindow(manageColleaguePopIn.buildManageColleagueView());
			
		}else if (event.getButton().equals(manageToolButton)){
			
			getUI().addWindow(manageToolsPopIn.buildManageToolsView());
			
		}
		
	}


	public GridLayout getGridLayout() {
		return gridLayout;
	}


	public void setGridLayout(GridLayout gridLayout) {
		this.gridLayout = gridLayout;
	}


	public Panel getManageColleaguePanel() {
		return manageColleaguePanel;
	}


	public void setManageColleaguePanel(Panel manageColleaguePanel) {
		this.manageColleaguePanel = manageColleaguePanel;
	}


	public Panel getManageToolPanel() {
		return manageToolPanel;
	}


	public void setManageToolPanel(Panel manageToolPanel) {
		this.manageToolPanel = manageToolPanel;
	}


	public Button getDeleteColleagueButton() {
		return deleteColleagueButton;
	}


	public void setDeleteColleagueButton(Button deleteColleagueButton) {
		this.deleteColleagueButton = deleteColleagueButton;
	}


	public Button getManageToolButton() {
		return manageToolButton;
	}


	public void setManageToolButton(Button manageToolButton) {
		this.manageToolButton = manageToolButton;
	}


	public ManageColleaguePopIn getManageColleaguePopIn() {
		return manageColleaguePopIn;
	}


	public void setManageColleaguePopIn(ManageColleaguePopIn manageColleaguePopIn) {
		this.manageColleaguePopIn = manageColleaguePopIn;
	}


	public ManageToolsPopIn getManageToolsPopIn() {
		return manageToolsPopIn;
	}


	public void setManageToolsPopIn(ManageToolsPopIn manageToolsPopIn) {
		this.manageToolsPopIn = manageToolsPopIn;
	}
	
}
