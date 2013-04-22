package com.novedia.talentmap.web.ui.collab;

import com.novedia.talentmap.web.commons.ConstantsForMenuEnglish;
import com.novedia.talentmap.web.ui.profile.CollaboratorSkillContent;
import com.novedia.talentmap.web.ui.profile.HistoryMissionColab;
import com.novedia.talentmap.web.ui.profile.ObjectiveEa;
import com.novedia.talentmap.web.util.ICollabLayout;
import com.novedia.talentmap.web.util.IObservable;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class MonitoringCollabNavigation extends VerticalLayout implements ItemClickListener,
IObservable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Vaadin Components
	 */
	private Button visualizeCollab;
	private Button setUpEA;
	private Button objectiveEA;
	private Class<?> cl = CollaboratorSkillContent.class;
	
	/**
	 * Util Observator
	 */
	private ICollabLayout obsLayout;
	public static Tree root = new Tree();
	private ConstantsForMenuEnglish constantsForMenuInglish;
	
		
	/**
	 * Default constructor
	 */
	public MonitoringCollabNavigation(){
		super();
	}
	
	/**
	 * Constructeur
	 */
	public MonitoringCollabNavigation(Button visualizeCollab, Button setUpEA,Button objectiveEA) {
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
		buildTree();
	}
	
	public void buildTree(){
		String firstItem ;
		String secondItem;
		Object[][] subItems = constantsForMenuInglish.subItems;
		for (int i = 0; i < subItems.length; i++) {			
			secondItem = (String) subItems[i][0];
			root.addItem(secondItem);
			
			//au moins 1 élément dans le tableau
			if(subItems[i].length == 1){
				root.setChildrenAllowed(subItems, false);
			}
			else{
				//On remplit le Menu
				for (int j = 1; j < subItems[i].length; j++) {	
					firstItem = (String)subItems[i][j];						
					root.addItem(firstItem);
					root.setParent(firstItem, secondItem);
					root.setChildrenAllowed(firstItem, false);
				}
				root.expandItemsRecursively(secondItem);	
			}
		}
		root.addListener((ItemClickListener) this);
		addComponent(this.root);	
	}
	@Override
	public void itemClick(ItemClickEvent event) {
		if(event.getSource() == root){
			Object itemId = event.getItemId();					
			if(itemId != null){
				if(itemId.equals(constantsForMenuInglish.VISUALIZE_COLLAB_NAME)){		
					this.cl = MonitoringCollabContent.class;
					updateObservateur();
				}
				else if(itemId.equals(constantsForMenuInglish.SETUP_EA_NAME)){
					this.cl = HistoryMissionColab.class;
					updateObservateur();	
				}
				else if(itemId.equals(constantsForMenuInglish.OBJECTIVE_NAME)){
					this.cl = ObjectiveEa.class;
					updateObservateur();	
				}
			}				
		}		
	}
		
	
	/**
	 * @param cl the cl to set
	 */
	public void setCl(Class<?> cl) {
		this.cl = cl;
	}
	 
	/**
	 * @param obsLayout the obsLayout to set
	 */
	public void setObsLayout(ICollabLayout obsLayout) {
		this.obsLayout = obsLayout;
	}

	@Override
	public void addObservateur(Object observateur, Class<?> cl) {
		if(cl == ICollabLayout.class){
			this.obsLayout = (ICollabLayout) observateur;
		}
	}

	@Override
	public void updateObservateur() {
		this.obsLayout.updateCollabLayout(this.cl);		
	}

	@Override
	public void delObservateur() {
		this.obsLayout = null;		
	}

	/**
	 * @return the visualizeCollab
	 */
	public Button getVisualizeCollab() {
		return visualizeCollab;
	}

	/**
	 * @param visualizeCollab the visualizeCollab to set
	 */
	public void setVisualizeCollab(Button visualizeCollab) {
		this.visualizeCollab = visualizeCollab;
	}

	/**
	 * @return the setUpEA
	 */
	public Button getSetUpEA() {
		return setUpEA;
	}

	/**
	 * @param setUpEA the setUpEA to set
	 */
	public void setSetUpEA(Button setUpEA) {
		this.setUpEA = setUpEA;
	}

	/**
	 * @return the objectiveEA
	 */
	public Button getObjectiveEA() {
		return objectiveEA;
	}

	/**
	 * @param objectiveEA the objectiveEA to set
	 */
	public void setObjectiveEA(Button objectiveEA) {
		this.objectiveEA = objectiveEA;
	}	
	
	
}