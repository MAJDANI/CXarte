package com.novedia.talentmap.web.ui.colleague;

import com.novedia.talentmap.web.ui.colleague.missions.MissionColleagueContent;
import com.novedia.talentmap.web.ui.colleague.missions.MissionForm;
import com.novedia.talentmap.web.ui.colleague.skills.SkillColleagueContent;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ProfilePopIn extends Window implements ClickListener{
	
	/**
	 * Vaadin components
	 */
	private Button administrativeDataButton;
	
	private Button skillsButton;
	
	private Button missionsHistoryButton;
	
	private HorizontalLayout hLayout;
	
	private Panel panelLeft;
	
	private VerticalLayout menuContent;
	
	private Panel panelRight;
	
	private MissionColleagueContent missionColleagueContent;
	
	private SkillColleagueContent skillColleagueContent;
	
	private GridLayout formLayout;
	
	private ColleagueDataForm colleagueDataForm;
	
	private MissionForm missionForm; 
	
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
		hLayout.setSpacing(true);
		hLayout.removeAllComponents();
		buildButtons();
		buildMenu();
		buildPanelRightContent();
		hLayout.addComponent(panelRight);
	    hLayout.setExpandRatio(panelRight, 1.0f);
		addComponent(hLayout);
		return this;
	}

	private void buildPanelRightContent() {
		panelRight.removeAllComponents();
		panelRight.addStyleName("panelRight");
		panelRight.setContent(missionColleagueContent.buildViewMissionColleagueContent());
		colleagueDataForm.setColleagueFormLayout(formLayout);
		panelRight.setContent(colleagueDataForm.buildColleagueDataFormView());
//		panelRight.setWidth("800px");
	}

	private void buildButtons() {
		
		administrativeDataButton.setCaption(Constants.PERSONAL_DATA_LABEL);
		administrativeDataButton.addStyleName(Reindeer.BUTTON_LINK);
		administrativeDataButton.addStyleName("focus");
		administrativeDataButton.addClickListener(this);
		
		skillsButton.setCaption(Constants.SKILL_BUTTON_LABEL);
		skillsButton.addStyleName(Reindeer.BUTTON_LINK);
		skillsButton.removeStyleName("focus");
		skillsButton.addClickListener(this);
		
		missionsHistoryButton.setCaption(Constants.MISSION_LABEL);
		missionsHistoryButton.addStyleName(Reindeer.BUTTON_LINK);
		missionsHistoryButton.removeStyleName("focus");
		missionsHistoryButton.addClickListener(this);
		
	}


	private void buildMenu() {
	    panelLeft.setWidth("200px");
	    panelLeft.addStyleName("panelLeft");
	    menuContent.setSpacing(true);
	    menuContent.setMargin(true);
	    menuContent.addComponent(administrativeDataButton);
	    menuContent.addComponent(skillsButton);
	    menuContent.addComponent(missionsHistoryButton);
	    panelLeft.setContent(menuContent);
	    hLayout.addComponent(panelLeft);
	}


	@Override
	public void buttonClick(ClickEvent event) {
		panelRight.removeAllComponents();
		VerticalLayout v = new VerticalLayout();
		v.addComponent(new Label(event.getButton().getCaption()));
		if (event.getButton().equals(administrativeDataButton)) { 
			administrativeDataButton.addStyleName("focus");
			skillsButton.removeStyleName("focus");
			missionsHistoryButton.removeStyleName("focus");
			panelRight.setContent(colleagueDataForm.buildColleagueDataFormView());
		} else if (event.getButton().equals(skillsButton)) {
			administrativeDataButton.removeStyleName("focus");
			skillsButton.addStyleName("focus");
			missionsHistoryButton.removeStyleName("focus");
			panelRight.setContent(skillColleagueContent.buildSkillColleagueContent());
		} else if(event.getButton().equals(missionsHistoryButton)) {
			administrativeDataButton.removeStyleName("focus");
			skillsButton.removeStyleName("focus");
			missionsHistoryButton.addStyleName("focus");
			panelRight.setContent(missionColleagueContent.buildViewMissionColleagueContent());
		}
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


	public HorizontalLayout gethLayout() {
		return hLayout;
	}


	public void sethLayout(HorizontalLayout hLayout) {
		this.hLayout = hLayout;
	}


	public Panel getPanelRight() {
		return panelRight;
	}


	public void setPanelRight(Panel panelRight) {
		this.panelRight = panelRight;
	}

	public MissionColleagueContent getMissionColleagueContent() {
		return missionColleagueContent;
	}


	public void setMissionColleagueContent(
			MissionColleagueContent missionColleagueContent) {
		this.missionColleagueContent = missionColleagueContent;
	}
	

	public GridLayout getFormLayout() {
		return formLayout;
	}


	public void setFormLayout(GridLayout formLayout) {
		this.formLayout = formLayout;
	}


	public ColleagueDataForm getColleagueDataForm() {
		return colleagueDataForm;
	}


	public void setColleagueDataForm(ColleagueDataForm colleagueDataForm) {
		this.colleagueDataForm = colleagueDataForm;
	}


	public MissionForm getMissionForm() {
		return missionForm;
	}


	public void setMissionForm(MissionForm missionForm) {
		this.missionForm = missionForm;
	}


	public SkillColleagueContent getSkillColleagueContent() {
		return skillColleagueContent;
	}


	public void setSkillColleagueContent(SkillColleagueContent skillColleagueContent) {
		this.skillColleagueContent = skillColleagueContent;
	}
	
	

}
