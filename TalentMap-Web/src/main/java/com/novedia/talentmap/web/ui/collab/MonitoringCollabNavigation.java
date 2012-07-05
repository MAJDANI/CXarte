package com.novedia.talentmap.web.ui.collab;

import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

public class MonitoringCollabNavigation extends VerticalLayout implements ClickListener {

	/**
	 * Vaadin Components
	 */
	private Button visualizeCollab;
	private Button setUpEA;
	private Button objectiveEA;
	
	/**
	 * Constants
	 */
	public static final String VISUALIZE_COLLAB_NAME = "Vue synthétique";
	public static final String SETUP_EA_NAME = "Saisir un EA";
	public static final String OBJECTIVE_NAME = "Objectifs d'un EA";
	
	
	/**
	 * Build the class MonitoringCollabNavigation.java 
	 * @param visualizeCollab
	 * @param setUpEA
	 * @param objectiveEA
	 */
	public MonitoringCollabNavigation(Button visualizeCollab, Button setUpEA,
			Button objectiveEA) {
		super();
		this.visualizeCollab = visualizeCollab;
		this.setUpEA = setUpEA;
		this.objectiveEA = objectiveEA;
		
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
	
	/**
	 * The button builder
	 * @class MonitoringCollabNavigation.java
	 */
	public void buildButton(){
		
		this.visualizeCollab.setCaption(VISUALIZE_COLLAB_NAME);
		this.visualizeCollab.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
		this.visualizeCollab.addStyleName(TalentMapCSS.BUTTON_SELECTED);
		this.visualizeCollab.addListener(this);
		addComponent(this.visualizeCollab);
		
		
		this.setUpEA.setCaption(SETUP_EA_NAME);
		this.setUpEA.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
		this.setUpEA.addListener(this);
		addComponent(this.setUpEA);
		
		this.objectiveEA.setCaption(OBJECTIVE_NAME);
		this.objectiveEA.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
		this.setUpEA.addListener(this);
		addComponent(this.objectiveEA);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		
		
		
	}
	
	/**
	 * Set the visualizeCollab value
	 * @param visualizeCollab the visualizeCollab to set
	 */
	public void setVisualizeCollab(Button visualizeCollab) {
		this.visualizeCollab = visualizeCollab;
	}
	/**
	 * Set the setUpEA value
	 * @param setUpEA the setUpEA to set
	 */
	public void setSetUpEA(Button setUpEA) {
		this.setUpEA = setUpEA;
	}
	/**
	 * Set the objectiveEA value
	 * @param objectiveEA the objectiveEA to set
	 */
	public void setObjectiveEA(Button objectiveEA) {
		this.objectiveEA = objectiveEA;
	}
}
