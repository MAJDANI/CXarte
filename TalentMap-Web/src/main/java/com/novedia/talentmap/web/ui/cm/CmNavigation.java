package com.novedia.talentmap.web.ui.cm;

import com.novedia.talentmap.web.commons.ConstantsForMenuEnglish;
import com.novedia.talentmap.web.data.SearchTargetPanel;
import com.novedia.talentmap.web.ui.admin.AdminAddSkillContent;
import com.novedia.talentmap.web.ui.admin.AdminDeleteColleagueContent;
import com.novedia.talentmap.web.ui.admin.AdminNavigation;
import com.novedia.talentmap.web.ui.admin.ManageSkillContent;
import com.novedia.talentmap.web.util.IAdminContentLayout;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.ISearchLayout;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class CmNavigation extends VerticalLayout implements IObservable , ItemClickListener {
	
	
	
	/**
	 * Util Observateur
	 */
	private ISearchLayout obsCmContentLayout;

	
	/**
	 * POJO
	 */
	private int searchTargetPanel = SearchTargetPanel.BY_NAME;
	
	/**
	 * 
	 */
	public Tree tree = new Tree();

	/**
	 * 
	 * Build the class NavigationLink.java
	 * 
	 */
	public CmNavigation() {
		super();
	}
	
	/**
	 * Build the CmNavigation view
	 * @return
	 */
	public CmNavigation  buildCmNavigation(){
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
		Object[][] menuCm = ConstantsForMenuEnglish.CM_MENU_NAVIGATION;
		int nbItems = menuCm.length;
		for (int i = 0; i < nbItems; i++) {		
			firstEl = (String) menuCm[i][0];
			tree.addItem(firstEl);
			//au moins 1 élément dans le tableau
			if(menuCm[i].length == 1){
				tree.setChildrenAllowed(menuCm, false);
			}
			else{
				//On remplit le Menu
				for (int j = 1; j <  menuCm[i].length; j++) {	
					firstElement = (String) menuCm[i][j]; 
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
			//get the item in the tree
			Object itemId = event.getItemId();
			if(itemId != null){
				if(itemId.equals(ConstantsForMenuEnglish.CM_SEARCH_BY_CLIENT)){
					//allowed to forward the view page
					this.searchTargetPanel = SearchTargetPanel.BY_CLIENT;
					updateObservateur();			
				}
				else if(itemId.equals(ConstantsForMenuEnglish.CM_SEARCH_BY_SKILL)){	
					this.searchTargetPanel = SearchTargetPanel.BY_SKILLS;
					updateObservateur();				
				}
				else if(itemId.equals(ConstantsForMenuEnglish.CM_SEARCH_BY_NAME)){
					this.searchTargetPanel = SearchTargetPanel.BY_NAME;
					updateObservateur();
				}
			}				
		}
			
	}
	

	@Override
	public void addObservateur(Object observateur, Class<?> cl) {
		if(cl == ISearchLayout.class){
			this.obsCmContentLayout = (ISearchLayout) observateur;
		}
	}

	@Override
	public void updateObservateur() {
		this.obsCmContentLayout.switchPanelTarget(this.searchTargetPanel);
	}

	@Override
	public void delObservateur() {
		this.obsCmContentLayout = null;
	}

}
