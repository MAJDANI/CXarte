package com.novedia.talentmap.web.ui.search;

import com.novedia.talentmap.web.commons.ConstantsForMenuEnglish;
import com.novedia.talentmap.web.data.SearchTargetPanel;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.ISearchLayout;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class SearchNavigation extends VerticalLayout implements IObservable,
	ItemClickListener {

    /**
     * Util Observator
     */
    private ISearchLayout obsLayout;

    /**
     * Vaadin Components
     */
    private Button byClient;
    private Button byName;
    private Button bySkills;

    /**
     * POJO
     */
    private int searchTargetPanel = SearchTargetPanel.BY_NAME;
    /**
     * root
     */
    public Tree root = new Tree();

    /**
     * Default constructor
     */
    public SearchNavigation() {
	super();
    }

    /**
     * Build SearchNavigation view
     * 
     * @return
     */
    public SearchNavigation buildSearchNavigationView() {
	removeAllComponents();
	mainBuild();
	return this;

    }

    public void mainBuild() {

	setMargin(true);
	setSpacing(true);

	constructTree();
    }

    /**
     * allowed unfolding the tree
     */
    public void constructTree() {
	String firstElement;
	String firstEl;
	Object[][] subItems = ConstantsForMenuEnglish.subItemsOfSearch;

	for (int i = 0; i < subItems.length; i++) {
	    firstEl = (String) subItems[i][0];
	    root.addItem(firstEl);

	    // au moins 1 élément dans le tableau
	    if (subItems[i].length == 1) {
		root.setChildrenAllowed(subItems, false);
	    } else {
		// On remplit le Menu
		for (int j = 1; j < subItems[i].length; j++) {
		    firstElement = (String) subItems[i][j];
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
	if (event.getSource() == root) {
	    // get the item in the root
	    Object itemId = event.getItemId();

	    if (itemId != null) {
		if (itemId
			.equals(ConstantsForMenuEnglish.BY_CLIENT_BUTTON_NAME)) {
		    this.searchTargetPanel = SearchTargetPanel.BY_CLIENT;
		    updateObservateur();
		} else if (itemId
			.equals(ConstantsForMenuEnglish.BY_NAME_BUTTON_NAME)) {
		    this.searchTargetPanel = SearchTargetPanel.BY_NAME;
		    updateObservateur();
		} else if (itemId
			.equals(ConstantsForMenuEnglish.BY_SKILLS_BUTTON_NAME)) {
		    this.searchTargetPanel = SearchTargetPanel.BY_SKILLS;
		    updateObservateur();
		}
	    }
	}
    }

    @Override
    public void addObservateur(Object observateur, Class<?> cl) {

	if (cl == ISearchLayout.class) {

	    this.obsLayout = (ISearchLayout) observateur;
	}
    }

    @Override
    public void updateObservateur() {

	this.obsLayout.switchPanelTarget(this.searchTargetPanel);
    }

    @Override
    public void delObservateur() {

	this.obsLayout = null;
    }

    /**
     * Get the byClient value
     * 
     * @return the byClient
     */
    public Button getByClient() {
	return byClient;
    }

    /**
     * Set the byClient value
     * 
     * @param byClient
     *            the byClient to set
     */
    public void setByClient(Button byClient) {
	this.byClient = byClient;
    }

    /**
     * Get the byName value
     * 
     * @return the byName
     */
    public Button getByName() {
	return byName;
    }

    /**
     * Set the byName value
     * 
     * @param byName
     *            the byName to set
     */
    public void setByName(Button byName) {
	this.byName = byName;
    }

    /**
     * Get the bySkills value
     * 
     * @return the bySkills
     */
    public Button getBySkills() {
	return bySkills;
    }

    /**
     * Set the bySkills value
     * 
     * @param bySkills
     *            the bySkills to set
     */
    public void setBySkills(Button bySkills) {
	this.bySkills = bySkills;
    }

}
