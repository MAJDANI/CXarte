package com.novedia.talentmap.web.ui.search;

import com.novedia.talentmap.web.data.SearchTargetPanel;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.ISearchLayout;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

public class SearchNavigation extends VerticalLayout implements ClickListener, IObservable {

	/**
	 * Util Observator
	 */
	private ISearchLayout obs;
	
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
	 * Constants
	 */
	public static final String BY_CLIENT_BUTTON_NAME = "Par client";
	public static final String BY_NAME_BUTTON_NAME = "Par nom";
	public static final String BY_SKILLS_BUTTON_NAME = "Par compétences";
	
	/**
	 * Build the class SearchNavigation.java 
	 * @param byClient
	 * @param byName
	 * @param bySkills
	 */
	public SearchNavigation(Button byClient, Button byName, Button bySkills) {
		super();
		this.byClient = byClient;
		this.byName = byName;
		this.bySkills = bySkills;
	
		mainBuild();
	}
	
	public void mainBuild(){
		
		setMargin(true);
		setSpacing(true);
		
		buildButton();
	}
	
	public void buildButton(){
		
		this.byClient.setCaption(BY_CLIENT_BUTTON_NAME);
		this.byClient.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
		this.byClient.addListener(this);
		
		this.byName.setCaption(BY_NAME_BUTTON_NAME);
		this.byName.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
		this.byName.addStyleName(TalentMapCSS.BUTTON_SELECTED);
		this.byName.addListener(this);
		
		this.bySkills.setCaption(BY_SKILLS_BUTTON_NAME);
		this.bySkills.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
		this.bySkills.addListener(this);
	
		//Add Component to the Navigation
		addComponent(this.byName);
		addComponent(this.byClient);
		addComponent(this.bySkills);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		Button button = event.getButton();
		
		if(button == this.byClient){
			
			this.searchTargetPanel = SearchTargetPanel.BY_CLIENT;
			
			//We set the style buttons
			this.byClient.addStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.byName.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.bySkills.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
		}
		
		if(button == this.byName){
			
			this.searchTargetPanel = SearchTargetPanel.BY_NAME;
			
			//We set the style buttons
			this.byClient.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.byName.addStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.bySkills.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
		}
		
		if(button == this.bySkills){
			
			this.searchTargetPanel = SearchTargetPanel.BY_SKILLS;
			
			//We set the style buttons
			this.byClient.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.byName.removeStyleName(TalentMapCSS.BUTTON_SELECTED);
			this.bySkills.addStyleName(TalentMapCSS.BUTTON_SELECTED);
		}
		
		updateObservateur();
	}
	
	
	@Override
	public void addObservateur(Object observateur, Class<?> cl) {
		
		if(cl == ISearchLayout.class){
			
			this.obs = (ISearchLayout) observateur;
		}
	}

	@Override
	public void updateObservateur() {
		this.obs.switchPanelTarget(this.searchTargetPanel);
	}

	@Override
	public void delObservateur() {
		
		this.obs = null;
	}

	/**
	 * Get the byClient value
	 * @return the byClient
	 */
	public Button getByClient() {
		return byClient;
	}

	/**
	 * Set the byClient value
	 * @param byClient the byClient to set
	 */
	public void setByClient(Button byClient) {
		this.byClient = byClient;
	}

	/**
	 * Get the byName value
	 * @return the byName
	 */
	public Button getByName() {
		return byName;
	}

	/**
	 * Set the byName value
	 * @param byName the byName to set
	 */
	public void setByName(Button byName) {
		this.byName = byName;
	}

	/**
	 * Get the bySkills value
	 * @return the bySkills
	 */
	public Button getBySkills() {
		return bySkills;
	}

	/**
	 * Set the bySkills value
	 * @param bySkills the bySkills to set
	 */
	public void setBySkills(Button bySkills) {
		this.bySkills = bySkills;
	}

}
