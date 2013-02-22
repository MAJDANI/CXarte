package com.novedia.talentmap.web.ui.profile;

import com.novedia.talentmap.web.commons.ConstantsForMenuEnglish;
import com.novedia.talentmap.web.ui.profile.mission.MissionCollaboratorContent;
import com.novedia.talentmap.web.ui.profile.skill.SkillCollaboratorContent;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.IProfileLayout;
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
	private Button vizualiseProfile;
	
	/**
	 * POJO
	 */
	private Class<?> cl = ProfileCollaboratorContent.class;

	/**
	 * Constants
	 */
	private ConstantsForMenuEnglish constantsForMenuInglish;
	
	public Tree root = new Tree();
	
	/**
	 * Build the class CollaboratorNavigation.java 
	 * @param visualizeSkills
	 * @param visualizeMissions
	 * @param visualizeEA
	 */
	public ProfileNavigation(Button visualizeSkills,
			Button visualizeMissions, Button visualizeEA, Button visualizeProfile) {
		super();
		this.vizualiseProfile = visualizeProfile;
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
		String firstElement ;
		String firstEl;
		Object[][] subItems = constantsForMenuInglish.subItemProfilNav;
		for (int i = 0; i < subItems.length; i++) {			
			firstEl = (String) subItems[i][0];
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
		if(event.getSource() == root){
			//get the item in the root
			Object itemId = event.getItemId();
			if(itemId != null){
				if(itemId.equals(constantsForMenuInglish.VISUALIZE_ADMINISTRATIVE_DATA)){
					//allowed to forward the view page
					this.cl = ProfileCollaboratorContent.class;
					updateObservateur();				
				}
				else if(itemId.equals(constantsForMenuInglish.VISUALIZE_MISSIONS_NAME)){					
					this.cl = MissionCollaboratorContent.class;					
					updateObservateur();				
				}				
				else if(itemId.equals(constantsForMenuInglish.VISUALIZE_SKILLS_NAME)){
					this.cl = SkillCollaboratorContent.class;
					updateObservateur();
				}
//				else if(itemId.equals(constantsForMenuInglish.VISUALIZE_EA_NAME)){
//					this.cl = EaContentHistorique.class;
//					updateObservateur();
//				}
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

	public Button getVizualiseProfile() {
		return vizualiseProfile;
	}

	public void setVizualiseProfile(Button vizualiseProfile) {
		this.vizualiseProfile = vizualiseProfile;
	}
}