package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.web.commons.ConstantsForMenuInglish;
import com.novedia.talentmap.web.util.IAdminContentLayout;
import com.novedia.talentmap.web.util.IAdminView;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

public class AdminNavigation extends VerticalLayout implements ClickListener,
		IObservable {

	/**
	 * Util Observateur
	 */
	private IAdminContentLayout obsAdminContentLayout;
	private IAdminView obsAdminView;

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
	private Button logout;

	
	/**
	 * Flag
	 */
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
			Button addSkill, Button logout) {
		super();
		this.visualizeSkill = visualizeSkill;
		this.deleteCollab = deleteCollab;
		this.addSkill = addSkill;
		this.logout = logout;

		mainBuild();

	}

	public void mainBuild() {

		setMargin(true);
		setSpacing(true);

		buildButtons();
	}

	private void buildButtons() {

		this.visualizeSkill.setCaption(ConstantsForMenuInglish.VISUALIZE_SKILL_NAME);
		this.visualizeSkill.addListener(this);
		this.visualizeSkill.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
		this.visualizeSkill.addStyleName(TalentMapCSS.BUTTON_SELECTED);
		addComponent(this.visualizeSkill);

		this.addSkill.setCaption(ConstantsForMenuInglish.ADD_SKILL_NAME);
		this.addSkill.addListener(this);
		this.addSkill.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
		addComponent(this.addSkill);

		this.deleteCollab.setCaption(ConstantsForMenuInglish.DELETE_COLLAB_NAME);
		this.deleteCollab.addListener(this);
		this.deleteCollab.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
		addComponent(this.deleteCollab);
		
		this.logout.setCaption("Se déconnecter");
		this.logout.addListener(this);
		addComponent(this.logout);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		Button button = event.getButton();
		
		if(button == this.visualizeSkill){
			
			//Update the view : visualize view
			this.addNewSkill = false;
			this.updateObservateur();
			
			//Remove style button selected on the other button
			this.addSkill.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.deleteCollab.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			
			//Add the style button selected on the button clicked
			this.visualizeSkill.addStyleName(TalentMapCSS.BUTTON_SELECTED);
		}
		
		if(button == this.addSkill){
			
			//Update the view : add view
			this.addNewSkill = true;
			this.updateObservateur();
			
			//Remove style button selected on the other button
			this.visualizeSkill.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.deleteCollab.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			
			//Add the style button selected on the button clicked
			this.addSkill.addStyleName(TalentMapCSS.BUTTON_SELECTED);
		}
		
		if(button == this.logout){
			
			this.updateAdminViewObersvateur(false);
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
	
	/**
	 * Set the logout value
	 * @param logout the logout to set
	 */
	public void setLogout(Button logout) {
		this.logout = logout;
	}
	
	@Override
	public void addObservateur(Object observateur, Class<?> cl) {
		
		if(cl == IAdminContentLayout.class){
			this.obsAdminContentLayout = (IAdminContentLayout) observateur;
		}
		
		if(cl == IAdminView.class){
			this.obsAdminView = (IAdminView) observateur;
		}
	}

	@Override
	public void updateObservateur() {
		
		this.obsAdminContentLayout.updateManageSkillContent(this.addNewSkill);
	}
	
	public void updateAdminViewObersvateur(boolean isLogged){
		
		this.obsAdminView.updateAdminContent(isLogged);
	}

	@Override
	public void delObservateur() {
		
		this.obsAdminContentLayout = null;
		this.obsAdminView = null;
	}

}
