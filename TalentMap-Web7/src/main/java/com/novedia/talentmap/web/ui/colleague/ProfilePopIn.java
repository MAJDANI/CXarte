package com.novedia.talentmap.web.ui.colleague;

import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.ui.colleague.missions.MissionColleagueContent;
import com.novedia.talentmap.web.ui.colleague.missions.MissionForm;
import com.novedia.talentmap.web.ui.colleague.skills.SkillColleagueContent;
import com.novedia.talentmap.web.utils.CUtils;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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
	
	private ResourceBundle resourceBundle;
	
	/**
	 * Default constructor
	 */
     public ProfilePopIn(){
    	 super();
    	 setModal(true);
     }
     
     
     /**
      * Build profilePopIn View
      * @return Window
      */
	public Window buildProfilePopIn(){
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES , locale);
		removeAllComponents();
		setCaption(resourceBundle.getString("window.profile.title"));
//		hLayout.setSpacing(true);
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
		colleagueDataForm.setColleagueFormLayout(formLayout);
		panelRight.setContent(colleagueDataForm.buildColleagueDataFormView());
		panelRight.setWidth("800px");
		panelRight.setHeight("300px");
	}

	private void buildButtons() {
		
		administrativeDataButton.setCaption(resourceBundle.getString("administrative.data.button.caption"));
		administrativeDataButton.addClickListener(this);
		
		skillsButton.setCaption(resourceBundle.getString("skills.button.caption"));
		skillsButton.addClickListener(this);
		
		missionsHistoryButton.setCaption(resourceBundle.getString("missions.history.button.caption"));
		missionsHistoryButton.addClickListener(this);
		
		CUtils.decorateButton(administrativeDataButton, skillsButton,missionsHistoryButton);
		CUtils.decorateButtonAsLink(administrativeDataButton, skillsButton,missionsHistoryButton);
		
	}


	private void buildMenu() {
	    panelLeft.setWidth("220px");
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
			CUtils.decorateButton(administrativeDataButton, skillsButton,missionsHistoryButton);
			panelRight.setContent(colleagueDataForm.buildColleagueDataFormView());
		} else if (event.getButton().equals(skillsButton)) {
			CUtils.decorateButton(skillsButton,administrativeDataButton,missionsHistoryButton);
			panelRight.setContent(skillColleagueContent.buildSkillColleagueContent());
		} else if(event.getButton().equals(missionsHistoryButton)) {
			CUtils.decorateButton(missionsHistoryButton,administrativeDataButton, skillsButton);
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
