package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.web.commons.ConstantsForMenuEnglish;
import com.novedia.talentmap.web.util.IAdminContentLayout;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

public class AdminNavigation extends VerticalLayout implements ClickListener,
		IObservable,ItemClickListener {

	/**
	 * Util Observateur
	 */
	private IAdminContentLayout obsAdminContentLayout;

	/**
	 * Vaddin Components
	 */
	private Button addSkill;
	private Button visualizeSkill;
	private Button deleteCollab;

	
	/**
	 * POJO
	 */
	private Class<?> cl = ManageSkillContent.class;
	
	/**
	 * 
	 */
	public Tree root = new Tree();

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
		//displayTree();
		buildButtons();
	}

	
	public void displayTree(){
		String firstElement ;
		String firstEl;
		Object[][] menuAdmin = ConstantsForMenuEnglish.ADMIN_MENU_NAVIGATION;
		int nbItems = menuAdmin.length;
		for (int i = 0; i < nbItems; i++) {		
			firstEl = (String) menuAdmin[i][0];
			root.addItem(firstEl);
			//au moins 1 élément dans le tableau
			if(menuAdmin[i].length == 1){
				root.setChildrenAllowed(menuAdmin, false);
			}
			else{
				//On remplit le Menu
				for (int j = 1; j < nbItems; j++) {	
					firstElement = (String) menuAdmin[i][j]; 
					root.addItem(firstElement);
					root.setParent(firstElement, firstEl);
					root.setChildrenAllowed(firstElement, false);
				}
				root.expandItemsRecursively(firstEl);	
			}
		}
		root.addListener((ItemClickListener) this);
		addComponent(this.root);	
	}
	
	
	
	@Override
	public void itemClick(ItemClickEvent event) {
		
		if(event.getSource() == root){
			//get the item in the root
			Object itemId = event.getItemId();
			if(itemId != null){
				if(itemId.equals(ConstantsForMenuEnglish.ADMIN_ADD_SKILL_NAME)){
					//allowed to forward the view page
					updateObservateur();				
				}
				else if(itemId.equals(ConstantsForMenuEnglish.ADMIN_VIEW_SKILL_NAME)){					
					updateObservateur();				
				}
				else if(itemId.equals(ConstantsForMenuEnglish.ADMIN_DELETE_COLLAB_NAME)){
					updateObservateur();
				}
			}				
		}
			
	}
	
	
	
	private void buildButtons() {

		this.visualizeSkill.setCaption(ConstantsForMenuEnglish.ADMIN_VIEW_SKILL_NAME);
		this.visualizeSkill.addListener(this);
		this.visualizeSkill.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
		this.visualizeSkill.addStyleName(TalentMapCSS.BUTTON_SELECTED);
		addComponent(this.visualizeSkill);

		this.addSkill.setCaption(ConstantsForMenuEnglish.ADMIN_ADD_SKILL_NAME);
		this.addSkill.addListener(this);
		this.addSkill.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
		addComponent(this.addSkill);

		this.deleteCollab.setCaption(ConstantsForMenuEnglish.ADMIN_DELETE_COLLAB_NAME);
		this.deleteCollab.addListener(this);
		this.deleteCollab.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
		addComponent(this.deleteCollab);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		Button button = event.getButton();
		
		if(button == this.visualizeSkill){
			
			//Update the view : visualize view
			this.cl = ManageSkillContent.class;
			//Add the style button selected on the button clicked
			this.visualizeSkill.addStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.addSkill.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.deleteCollab.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			
			this.updateObservateur();				
		}
		
		else if(button == this.addSkill){
			
			this.cl = AdminAddSkillContent.class;
			//Remove style button selected on the other button
			this.visualizeSkill.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.deleteCollab.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			
			//Add the style button selected on the button clicked
			this.addSkill.addStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.updateObservateur();
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
	public void addObservateur(Object observateur, Class<?> cl) {
		if(cl == IAdminContentLayout.class){
			this.obsAdminContentLayout = (IAdminContentLayout) observateur;
		}
	}

	@Override
	public void updateObservateur() {
		this.obsAdminContentLayout.updateAdminContentLayout(this.cl);
	}

	@Override
	public void delObservateur() {
		this.obsAdminContentLayout = null;
	}

}
