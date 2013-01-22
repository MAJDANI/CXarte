package com.novedia.talentmap.web.ui.profile;

import com.novedia.talentmap.web.ui.profile.mission.MissionCollaboratorContent;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.IProfileLayout;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class ProfileNavigation extends VerticalLayout implements
IObservable,ItemClickListener {

	/**
	 * Utils Observateur
	 */
	private IProfileLayout obs;
	
	/**
	 * Vaadin Components
	 */
	private Button visualizeSkills;
	private Button visualizeMissions;
	private Button visualizeEA;
	
	/**
	 * POJO
	 */
	private Class<?> cl = ProfileCollaboratorContent.class;

	/**
	 * Constants
	 */
	public static final String VISUALIZE_SKILLS_NAME = "Compétences";
	public static final String VISUALIZE_MISSIONS_NAME = "Historique des missions";
	public static final String VISUALIZE_EA_NAME = "Historique EA";
	
	public Tree root = new Tree();
	public static final Object [][] subItems = new Object[][]{
			new Object[]{"Menu",VISUALIZE_SKILLS_NAME,VISUALIZE_MISSIONS_NAME,VISUALIZE_EA_NAME}
		};
	
	
	String firstElement ;
	String firstEl;
	
	/**
	 * Build the class CollaboratorNavigation.java 
	 * @param visualizeSkills
	 * @param visualizeMissions
	 * @param visualizeEA
	 */
	public ProfileNavigation(Button visualizeSkills,
			Button visualizeMissions, Button visualizeEA) {
		super();
		this.visualizeSkills = visualizeSkills;
		this.visualizeMissions = visualizeMissions;
		this.visualizeEA = visualizeEA;
	
		mainBuild();
	}
	
	/**
	 * The main builder
	 * @class MonitoringCollabNavigation.java
	 */
	public void mainBuild(){
		
		setMargin(true);
		setSpacing(true);
		playTree();

	}
	
	
	/**
	 * allowed unfolding the tree
	 */
	public void playTree(){
		for (int i = 0; i < subItems.length; i++) {			
			firstEl = (String) subItems[i][0];
			System.out.println("firstEl = " + firstEl);
			root.addItem(firstEl);
			
			//au moins 1 élément dans le tableau
			if(subItems[i].length == 1){
				root.setChildrenAllowed(subItems, false);
			}
			else{
				//On remplit le Menu
				for (int j = 1; j < subItems[i].length; j++) {	
					firstElement = (String)subItems[i][j];						
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

	/**
	 * This method allowed to do event, when the item selected
	 */
	
	@Override
	public void itemClick(ItemClickEvent event) {
		
		root.addListener((ItemClickListener) this);
		addComponent(this.root);		
		
		if(event.getSource() == root){
			//get the item in the root
			Object itemId = event.getItemId();
			if(itemId != null){
				if(itemId.equals(VISUALIZE_SKILLS_NAME)){
					//allowed to forward the view page
					this.cl = ProfileCollaboratorContent.class;
					updateObservateur();				
				}
				else if(itemId.equals(VISUALIZE_MISSIONS_NAME)){
					
					this.cl = MissionCollaboratorContent.class;					
					updateObservateur();				
				}
				else if(itemId.equals(VISUALIZE_EA_NAME)){
					//TODO: vue à implémenter
					//We set the style buttons
//					this.visualizeSkills.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
//					this.visualizeMissions.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
//					this.visualizeEA.addStyleName(TalentMapCSS.BUTTON_SELECTED);
				}
			}				
		}
	}
	/**
	 * Get the visualizeSkills value
	 * @return the visualizeSkills
	 */
	public Button getVisualizeSkills() {
		return visualizeSkills;
	}

	/**
	 * Set the visualizeSkills value
	 * @param visualizeSkills the visualizeSkills to set
	 */
	public void setVisualizeSkills(Button visualizeSkills) {
		this.visualizeSkills = visualizeSkills;
	}

	/**
	 * Get the visualizeMissions value
	 * @return the visualizeMissions
	 */
	public Button getVisualizeMissions() {
		return visualizeMissions;
	}

	/**
	 * Set the visualizeMissions value
	 * @param visualizeMissions the visualizeMissions to set
	 */
	public void setVisualizeMissions(Button visualizeMissions) {
		this.visualizeMissions = visualizeMissions;
	}

	/**
	 * Get the visualizeEA value
	 * @return the visualizeEA
	 */
	public Button getVisualizeEA() {
		return visualizeEA;
	}

	/**
	 * Set the visualizeEA value
	 * @param visualizeEA the visualizeEA to set
	 */
	public void setVisualizeEA(Button visualizeEA) {
		this.visualizeEA = visualizeEA;
	}

	@Override
	public void addObservateur(Object observateur, Class<?> cl) {
		if(cl == IProfileLayout.class){
			this.obs = (IProfileLayout) observateur;
		}
	}

	@Override
	public void updateObservateur() {
		this.obs.updateProfileLayout(this.cl);
	}

	@Override
	public void delObservateur() {
		this.obs = null;
	}
}