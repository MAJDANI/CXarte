package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AdminView extends VerticalLayout implements ClickListener {
	
	/**
	* Vaadin components
	*/
	private GridLayout gridLayout;
	
	private Panel collaboratorAndSkillManagePanel;
	
	private Button collaboratorAndSkillManageButton;
	
	private CollaboratorAndSkillManagePopIn collaboratorAndSkillManagePopIn;
	
	/**
	 * Default constructor
	 */
	public AdminView(){
		super();
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
		
		collaboratorAndSkillManagePanel.removeAllComponents();
		collaboratorAndSkillManageButton.setCaption(Constants.COLLABORATOR_AND_SKILL_MANAGE_LABEL);
		collaboratorAndSkillManageButton.setId(ComponentsId.COLLABORATOR_AND_SKILL_MANAGE_ID);
		collaboratorAndSkillManageButton.addClickListener(this);
		collaboratorAndSkillManagePanel.addComponent(collaboratorAndSkillManageButton);
		
		gridLayout.addComponent(collaboratorAndSkillManagePanel);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().equals(collaboratorAndSkillManageButton)){
			getUI().addWindow(collaboratorAndSkillManagePopIn.buildCollaboratorAndSkillManagePopIn());
		}
		
	}


	public GridLayout getGridLayout() {
		return gridLayout;
	}


	public void setGridLayout(GridLayout gridLayout) {
		this.gridLayout = gridLayout;
	}


	public Panel getCollaboratorAndSkillManagePanel() {
		return collaboratorAndSkillManagePanel;
	}


	public void setCollaboratorAndSkillManagePanel(
			Panel collaboratorAndSkillManagePanel) {
		this.collaboratorAndSkillManagePanel = collaboratorAndSkillManagePanel;
	}


	public Button getCollaboratorAndSkillManageButton() {
		return collaboratorAndSkillManageButton;
	}


	public void setCollaboratorAndSkillManageButton(
			Button collaboratorAndSkillManageButton) {
		this.collaboratorAndSkillManageButton = collaboratorAndSkillManageButton;
	}


	public CollaboratorAndSkillManagePopIn getCollaboratorAndSkillManagePopIn() {
		return collaboratorAndSkillManagePopIn;
	}


	public void setCollaboratorAndSkillManagePopIn(
			CollaboratorAndSkillManagePopIn collaboratorAndSkillManagePopIn) {
		this.collaboratorAndSkillManagePopIn = collaboratorAndSkillManagePopIn;
	}

}
