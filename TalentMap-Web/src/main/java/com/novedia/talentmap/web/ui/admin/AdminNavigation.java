package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.web.commons.ConstantsForMenuEnglish;
import com.novedia.talentmap.web.util.IAdminContentLayout;
import com.novedia.talentmap.web.util.IObservable;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class AdminNavigation extends VerticalLayout implements IObservable , ItemClickListener {

	/**
	 * Util Observateur
	 */
	private IAdminContentLayout obsAdminContentLayout;

	
	/**
	 * POJO
	 */
	private Class<?> cl = ManageSkillContent.class;
	
	/**
	 * 
	 */
	public Tree tree = new Tree();

	/**
	 * 
	 * Build the class NavigationLink.java
	 * 
	 */
	public AdminNavigation() {
		super();
//		mainBuild();

	}
	
	/**
	 * Build the AdminNavigation view
	 * @return
	 */
	public AdminNavigation  buildAdminNavigation(){
		removeAllComponents();
		mainBuild();
		return this;
	}

	public void mainBuild() {

		setMargin(true);
		setSpacing(true);
		displayTree();
	}

	/**
	 * build menu tree 
	 */
	public void displayTree(){
		String firstElement ;
		String firstEl;
		Object[][] menuAdmin = ConstantsForMenuEnglish.ADMIN_MENU_NAVIGATION;
		int nbItems = menuAdmin.length;
		for (int i = 0; i < nbItems; i++) {		
			firstEl = (String) menuAdmin[i][0];
			tree.addItem(firstEl);
			//au moins 1 élément dans le tableau
			if(menuAdmin[i].length == 1){
				tree.setChildrenAllowed(menuAdmin, false);
			}
			else{
				//On remplit le Menu
				for (int j = 1; j <  menuAdmin[i].length; j++) {	
					firstElement = (String) menuAdmin[i][j]; 
					tree.addItem(firstElement);
					tree.setParent(firstElement, firstEl);
					tree.setChildrenAllowed(firstElement, false);
				}
				tree.expandItemsRecursively(firstEl);	
			}
		}
		tree.addListener((ItemClickListener) this);
		addComponent(this.tree);	
	}
	
	
	
	@Override
	public void itemClick(ItemClickEvent event) {
		
		if(event.getSource() == tree){
			//get the item in the root
			Object itemId = event.getItemId();
			if(itemId != null){
				if(itemId.equals(ConstantsForMenuEnglish.ADMIN_ADD_SKILL_NAME)){
					//allowed to forward the view page
					this.cl = AdminAddSkillContent.class;
					this.updateObservateur();				
				}
				else if(itemId.equals(ConstantsForMenuEnglish.ADMIN_VIEW_SKILL_NAME)){	
					this.cl = ManageSkillContent.class;
					this.updateObservateur();				
				}
				else if(itemId.equals(ConstantsForMenuEnglish.ADMIN_DELETE_COLLAB_NAME)){
					this.cl = AdminDeleteColleagueContent.class;
					this.updateObservateur();
				}
			}				
		}
			
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
