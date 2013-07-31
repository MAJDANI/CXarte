package com.novedia.talentmap.web.ui.colleague;

import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ProfilePopIn extends Window implements ClickListener{
	
	

	

	private Button administrativeDataButton;
	
	private Button skillsButton;
	
	private Button missionsHistoryButton;
	
	private HorizontalLayout hLayout;
	
	private Panel menuPanel;
	
	private VerticalLayout menuContent;
	
	private Panel contentPanel;
	
	/**
	 * Default constructor
	 */
     public ProfilePopIn(){
    	 super();
    	 setCaption(Constants.PROFILE_POP_IN_TITLE);
    	 setModal(true);
     }
     
     
     /**
      * Build profilePopIn View
      * @return Window
      */
	public Window buildProfilePopIn(){
		removeAllComponents();
		buildButtons();
		buildMenu();
		buildContent();
		addComponent(hLayout);
		return this;
	}


	private void buildContent() {
		contentPanel.setSizeFull();
		contentPanel.setHeight("100%");
		contentPanel.setWidth(null);
        hLayout.addComponent(contentPanel);
        hLayout.setExpandRatio(contentPanel, 1.0f);
		
	}


	private void buildButtons() {
		
		administrativeDataButton.setCaption(Constants.ADMINISTRATIVE_DATA);
		
		skillsButton.setCaption(Constants.SKILLS);
		
		missionsHistoryButton.setCaption(Constants.MISSIONS_HISTORY);
		
	}


	private void buildMenu() {
	    hLayout.setSizeFull();
	    menuPanel.setCaption(Constants.PERSONAL_DATA);
	    menuPanel.setHeight("100%");
	    menuPanel.setWidth(null);
	    menuContent.addComponent(administrativeDataButton);
	    menuContent.addComponent(skillsButton);
	    menuContent.addComponent(missionsHistoryButton);
	    menuContent.setWidth(null);
	    menuContent.setMargin(true);
	    menuPanel.setContent(menuContent);
	    hLayout.addComponent(menuPanel);
	    
	}


	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}


	public Button getAdministrativeDataButton() {
		return administrativeDataButton;
	}


	public void setAdministrativeDataButton(Button administrativeDataButton) {
		this.administrativeDataButton = administrativeDataButton;
	}


	public Button getSkillsButton() {
		return skillsButton;
	}


	public void setSkillsButton(Button skillsButton) {
		this.skillsButton = skillsButton;
	}


	public Button getMissionsHistoryButton() {
		return missionsHistoryButton;
	}


	public void setMissionsHistoryButton(Button missionsHistoryButton) {
		this.missionsHistoryButton = missionsHistoryButton;
	}


	public Panel getMenuPanel() {
		return menuPanel;
	}


	public void setMenuPanel(Panel menuPanel) {
		this.menuPanel = menuPanel;
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


	public Panel getContentPanel() {
		return contentPanel;
	}


	public void setContentPanel(Panel contentPanel) {
		this.contentPanel = contentPanel;
	}

}
