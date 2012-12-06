package com.novedia.talentmap.web.ui.profile;

import com.novedia.talentmap.web.ui.profile.mission.MissionCollaboratorContent;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.IProfileLayout;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.VerticalLayout;

public class ProfileNavigation extends VerticalLayout implements ClickListener, IObservable {

	/**
	 * Utils Observateur
	 */
	private IProfileLayout obs;
	
	/**
	 * Vaadin Components
	 */
	private Button visualizeSkills;
	private Button visualizeMissions;
	private Button visualizeEA;
	
	/**
	 * POJO
	 */
	private Class<?> cl = ProfileCollaboratorContent.class;

	/**
	 * Constants
	 */
	public static final String VISUALIZE_SKILLS_NAME = "Compétences";
	public static final String VISUALIZE_MISSIONS_NAME = "Historique des missions";
	public static final String VISUALIZE_EA_NAME = "Historique EA";
	
	/**
	 * Build the class CollaboratorNavigation.java 
	 * @param visualizeSkills
	 * @param visualizeMissions
	 * @param visualizeEA
	 */
	public ProfileNavigation(Button visualizeSkills,
			Button visualizeMissions, Button visualizeEA) {
		super();
		this.visualizeSkills = visualizeSkills;
		this.visualizeMissions = visualizeMissions;
		this.visualizeEA = visualizeEA;
	
		mainBuild();
	}
	
	/**
	 * The main builder
	 * @class MonitoringCollabNavigation.java
	 */
	public void mainBuild(){
		
		setMargin(true);
		setSpacing(true);
		
		buildButton();
	}
	
	public void buildButton(){
		
		this.visualizeSkills.setCaption(VISUALIZE_SKILLS_NAME);
		this.visualizeSkills.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
		this.visualizeSkills.addStyleName(TalentMapCSS.BUTTON_SELECTED);
		this.visualizeSkills.addListener(this);
		addComponent(this.visualizeSkills);
		
		
		this.visualizeMissions.setCaption(VISUALIZE_MISSIONS_NAME);
		this.visualizeMissions.addStyleName(Reindeer.BUTTON_DEFAULT);
		this.visualizeMissions.addListener(this);
		addComponent(this.visualizeMissions);
		
		this.visualizeEA.setCaption(VISUALIZE_EA_NAME);
		this.visualizeEA.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
		this.visualizeEA.addListener(this);
		addComponent(this.visualizeEA);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		
		Button button = event.getButton();
		
		if(button == this.visualizeSkills){
			
			this.cl = ProfileCollaboratorContent.class;
			
			updateObservateur();
			
			//We set the style buttons
			this.visualizeSkills.addStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.visualizeMissions.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.visualizeEA.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
		}
		
		if(button == this.visualizeMissions){
			
			this.cl = MissionCollaboratorContent.class;
			
			updateObservateur();
			
			//We set the style buttons
			this.visualizeSkills.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.visualizeMissions.addStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.visualizeEA.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
		}
		
		if(button == this.visualizeEA){
			
			//We set the style buttons
			this.visualizeSkills.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.visualizeMissions.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.visualizeEA.addStyleName(TalentMapCSS.BUTTON_SELECTED);
		}
	}
	
	/**
	 * Get the visualizeSkills value
	 * @return the visualizeSkills
	 */
	public Button getVisualizeSkills() {
		return visualizeSkills;
	}

	/**
	 * Set the visualizeSkills value
	 * @param visualizeSkills the visualizeSkills to set
	 */
	public void setVisualizeSkills(Button visualizeSkills) {
		this.visualizeSkills = visualizeSkills;
	}

	/**
	 * Get the visualizeMissions value
	 * @return the visualizeMissions
	 */
	public Button getVisualizeMissions() {
		return visualizeMissions;
	}

	/**
	 * Set the visualizeMissions value
	 * @param visualizeMissions the visualizeMissions to set
	 */
	public void setVisualizeMissions(Button visualizeMissions) {
		this.visualizeMissions = visualizeMissions;
	}

	/**
	 * Get the visualizeEA value
	 * @return the visualizeEA
	 */
	public Button getVisualizeEA() {
		return visualizeEA;
	}

	/**
	 * Set the visualizeEA value
	 * @param visualizeEA the visualizeEA to set
	 */
	public void setVisualizeEA(Button visualizeEA) {
		this.visualizeEA = visualizeEA;
	}

	@Override
	public void addObservateur(Object observateur, Class<?> cl) {
		if(cl == IProfileLayout.class){
			this.obs = (IProfileLayout) observateur;
		}
	}

	@Override
	public void updateObservateur() {
		this.obs.updateProfileLayout(this.cl);
	}

	@Override
	public void delObservateur() {
		this.obs = null;
	}
}
