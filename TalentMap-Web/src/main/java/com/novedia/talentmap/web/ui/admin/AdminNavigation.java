package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.web.util.IAdminView;
import com.novedia.talentmap.web.util.IObservable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

public class AdminNavigation extends VerticalLayout implements ClickListener,
		IObservable {

	/**
	 * Util Observateur
	 */
	private IAdminView obs;

	/**
	 * Vaadin UI
	 */
//	private ManageSkillContent manageSkillContent;

	/**
	 * Vaddin Components
	 */
	private Button addSkill;
	private Button visualizeSkill;
	private Button deleteCollab;

	/**
	 * Constants
	 */
	public static final String ADD_SKILL_NAME = "Ajouter une compétence";
	public static final String VISUALIZE_SKILL_NAME = "Visualiser les compétences";
	public static final String DELETE_COLLAB_NAME = "Supprimer des collaborateurs";
	public static final String STYLE_BUTTON_NAVIGATION = "admin-button";

	/**
	 * Flag
	 */
	public static final String STYLE_BUTTON_SELECTED = "admin-button-selected";
	public static boolean addNewSkill = false;

	/**
	 * 
	 * Build the class NavigationLink.java
	 * 
	 * @param visualizeSkillLink
	 * @param deleteCollabLink
	 * @param addSkillLink
	 */
	public AdminNavigation(Button visualizeSkill, Button deleteCollab,
			Button addSkill) {
		super();
		this.visualizeSkill = visualizeSkill;
		this.deleteCollab = deleteCollab;
		this.addSkill = addSkill;

		mainBuild();

	}

	public void mainBuild() {

		setMargin(true);
		setSpacing(true);

		buildLinks();
	}

	private void buildLinks() {

		this.visualizeSkill.setCaption(VISUALIZE_SKILL_NAME);
		this.visualizeSkill.addListener(this);
		this.visualizeSkill.addStyleName(STYLE_BUTTON_NAVIGATION);
		this.visualizeSkill.addStyleName(STYLE_BUTTON_SELECTED);
		addComponent(this.visualizeSkill);

		this.addSkill.setCaption(ADD_SKILL_NAME);
		this.addSkill.addListener(this);
		this.addSkill.addStyleName(STYLE_BUTTON_NAVIGATION);
		addComponent(this.addSkill);

		this.deleteCollab.setCaption(DELETE_COLLAB_NAME);
		this.deleteCollab.addListener(this);
		this.deleteCollab.addStyleName(STYLE_BUTTON_NAVIGATION);
		addComponent(this.deleteCollab);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		Button button = event.getButton();
		
		if(button == this.visualizeSkill){
			
			//Update the view : visualize view
			this.addNewSkill = false;
			this.updateObservateur();
			
			//Remove style button selected on the other button
			this.addSkill.removeStyleName(STYLE_BUTTON_SELECTED);
			this.deleteCollab.removeStyleName(STYLE_BUTTON_SELECTED);
			
			//Add the style button selected on the button clicked
			this.visualizeSkill.addStyleName(STYLE_BUTTON_SELECTED);
		}
		
		if(button == this.addSkill){
			
			//Update the view : add view
			this.addNewSkill = true;
			this.updateObservateur();
			
			//Remove style button selected on the other button
			this.visualizeSkill.removeStyleName(STYLE_BUTTON_SELECTED);
			this.deleteCollab.removeStyleName(STYLE_BUTTON_SELECTED);
			
			//Add the style button selected on the button clicked
			this.addSkill.addStyleName(STYLE_BUTTON_SELECTED);
		}
		
	}

	/**
	 * Set the addSkill value
	 * 
	 * @param addSkill
	 *            the addSkill to set
	 */
	public void setVisualizeSkill(Button visualizeSkill) {
		this.visualizeSkill = visualizeSkill;
	}

	/**
	 * Set the deleteCollab value
	 * 
	 * @param deleteCollab
	 *            the deleteCollab to set
	 */
	public void setDeleteCollabLink(Button deleteCollab) {
		this.deleteCollab = deleteCollab;
	}

	/**
	 * Get the addSkill value
	 * 
	 * @return the addSkill
	 */
	public Button getVisualizeSkill() {
		return visualizeSkill;
	}

	/**
	 * Get the deleteCollab value
	 * 
	 * @return the deleteCollab
	 */
	public Button getDeleteCollab() {
		return deleteCollab;
	}

	/**
	 * Get the addSkill value
	 * 
	 * @return the addSkill
	 */
	public Button getAddSkill() {
		return addSkill;
	}

	/**
	 * Set the addSkill value
	 * 
	 * @param addSkill
	 *            the addSkill to set
	 */
	public void setAddSkill(Button addSkill) {
		this.addSkill = addSkill;
	}
	
	@Override
	public void addObservateur(Object observateur) {

		this.obs = (IAdminView) observateur;
	}

	@Override
	public void updateObservateur() {
		
		this.obs.updateManageSkillContent(this.addNewSkill);
	}

	@Override
	public void delObservateur() {
		
		this.obs = null;
	}

}
