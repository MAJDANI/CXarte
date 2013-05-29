package com.novedia.talentmap.web.ui.cm;

import com.novedia.talentmap.web.commons.ConstantsForMenuEnglish;
import com.novedia.talentmap.web.util.IEAELayout;
import com.novedia.talentmap.web.util.INotificationLayout;
import com.novedia.talentmap.web.util.IObservable;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmEAENavigation extends VerticalLayout implements
	IObservable, ItemClickListener {

    /**
     * Util Observateur
     */
    private IEAELayout obsCmEAELayout;

    /**
     * POJO
     */
    private int targetPanel;

    /**
	 * 
	 */
    public Tree tree = new Tree();

    /**
     * 
     * Build the class NavigationLink.java
     * 
     */
    public CmEAENavigation() {
	super();
    }

    /**
     * Build the CmNotificationNavigation view
     * 
     * @return
     */
    public CmEAENavigation buildCmEAENavigation() {
	removeAllComponents();
	mainBuild();
	return this;
    }

    public CmEAENavigation mainBuild() {

	setMargin(true);
	setSpacing(true);
	displayTree();
	return this;
    }

    /**
     * build menu tree
     */
    public void displayTree() {
	String firstElement;
	String firstEl;
	Object[][] menuEAE = ConstantsForMenuEnglish.CM_EAE_NAVIGATION;
	int nbItems = menuEAE.length;
	for (int i = 0; i < nbItems; i++) {
	    firstEl = (String) menuEAE[i][0];
	    tree.addItem(firstEl);
	    // au moins 1 élément dans le tableau
	    if (menuEAE[i].length == 1) {
		tree.setChildrenAllowed(menuEAE, false);
	    } else {
		// On remplit le Menu
		for (int j = 1; j < menuEAE[i].length; j++) {
		    firstElement = (String) menuEAE[i][j];
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

	if (event.getSource() == tree) {
	    // get the item in the tree
	    Object itemId = event.getItemId();
	    if (itemId != null) {
		if (itemId
			.equals(ConstantsForMenuEnglish.CM_EAE_SYNTHESIS)) {
		    // allowed to forward the view page
		    this.targetPanel = CmEAEView.EAE_SYNTHESIS;
		    updateObservateur();
		} else if (itemId
			.equals(ConstantsForMenuEnglish.CM_EAE_HISTORY)) {
		    this.targetPanel = CmEAEView.EAE_HISTORY;
		    updateObservateur();
		} else if (itemId
			.equals(ConstantsForMenuEnglish.CM_EAE_CREATION)) {
		    this.targetPanel = CmEAEView.EAE_CREATION;
		    updateObservateur();
		} else if (itemId
			.equals(ConstantsForMenuEnglish.CM_EAE_PERSONAL)) {
		    this.targetPanel = CmEAEView.EAE_PERSONAL;
		    updateObservateur();
		}

	    }
	}

    }

    @Override
    public void addObservateur(Object observateur, Class<?> cl) {
	if (cl == IEAELayout.class) {
	    this.obsCmEAELayout = (IEAELayout) observateur;
	}
    }

    @Override
    public void updateObservateur() {
	this.obsCmEAELayout.switchPanelTarget(this.targetPanel);
    }

    @Override
    public void delObservateur() {
	this.obsCmEAELayout = null;
    }

    /**
     * @return the targetPanel
     */
    public int getTargetPanel() {
        return targetPanel;
    }

    /**
     * @param targetPanel the targetPanel to set
     */
    public void setTargetPanel(int targetPanel) {
        this.targetPanel = targetPanel;
    }

}
