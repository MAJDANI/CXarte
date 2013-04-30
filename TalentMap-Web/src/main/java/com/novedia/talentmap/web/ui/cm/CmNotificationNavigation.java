package com.novedia.talentmap.web.ui.cm;

import com.novedia.talentmap.web.commons.ConstantsForMenuEnglish;
import com.novedia.talentmap.web.ui.profile.ProfileCollaboratorContent;
import com.novedia.talentmap.web.util.INotificationLayout;
import com.novedia.talentmap.web.util.IObservable;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmNotificationNavigation  extends VerticalLayout implements IObservable,ItemClickListener {

	/**
	 * Util Observateur
	 */
	private INotificationLayout obsCmNotificationContentLayout;

	
	/**
	 * POJO
	 */
	private Class<?> cl = CmNotificationContent.class;
	
	/**
	 * 
	 */
	public Tree tree = new Tree();
	
	/**
	 * 
	 * Build the class NavigationLink.java
	 * 
	 */
	public CmNotificationNavigation() {
		super();
	}
	
	/**
	 * Build the CmNotificationNavigation view
	 * @return
	 */
	public CmNotificationNavigation  buildCmNotificationNavigation(){
		removeAllComponents();
		mainBuild();
		return this;
	}

	public CmNotificationNavigation mainBuild() {

		setMargin(true);
		setSpacing(true);
		displayTree();
		return this;
	}
	
	/**
	 * build menu tree 
	 */
	public void displayTree(){
		String firstElement ;
		String firstEl;
		Object[][] menuNotif = ConstantsForMenuEnglish.CM_NOTIFICATION_NAVIGATION;
		int nbItems = menuNotif.length;
		for (int i = 0; i < nbItems; i++) {		
			firstEl = (String) menuNotif[i][0];
			tree.addItem(firstEl);
			//au moins 1 élément dans le tableau
			if(menuNotif[i].length == 1){
				tree.setChildrenAllowed(menuNotif, false);
			}
			else{
				//On remplit le Menu
				for (int j = 1; j <  menuNotif[i].length; j++) {	
					firstElement = (String) menuNotif[i][j]; 
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
				if(itemId.equals(ConstantsForMenuEnglish.CM_NOTIFICATION_HISTORY)){
					//allowed to forward the view page
					this.cl = CmNotificationContent.class;
					updateObservateur();			
				}
				else if(itemId.equals(ConstantsForMenuEnglish.CM_NOTIFICATION_SETTINGS)){	
					this.cl = CmNotificationOption.class;
					updateObservateur();				
				}

			}				
		}
			
	}

	@Override
	public void addObservateur(Object observateur, Class<?> cl) {
		if(cl == INotificationLayout.class){
			this.obsCmNotificationContentLayout = (INotificationLayout) observateur;
		}
	}

	@Override
	public void updateObservateur() {
		this.obsCmNotificationContentLayout.updateNotificationLayout(this.cl);
	
	}

	@Override
	public void delObservateur() {
		this.obsCmNotificationContentLayout = null;
	}

}
