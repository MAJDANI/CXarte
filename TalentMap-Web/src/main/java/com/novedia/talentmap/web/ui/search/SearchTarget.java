package com.novedia.talentmap.web.ui.search;

import java.util.ArrayList;
import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.ISearchContent;
import com.vaadin.data.Item.PropertySetChangeEvent;
import com.vaadin.data.Item.PropertySetChangeListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

public class SearchTarget extends VerticalLayout implements ClickListener,TextChangeListener,
		IObservable {

	/**
	 * Utils Observator
	 */
	private ISearchContent obs;

	/**
	 * TalentMap Services
	 */
	private ICollaboratorService collabService;
	private ISkillService skillService;

	/**
	 * Vaadin Components
	 */
	private Panel searchByClientPanel;
	private Panel searchByNamePanel;
	private Panel searchBySkillsPanel;

	private TextField fieldClient;
	private TextField fieldName;
	// Il manque les Skills !!! ??

	private Button search;

	/**
	 * POJO
	 */
	private List<Collaborator> listCollab;
	private List<CheckBox> listCheckBoxSkills;

	/**
	 * 
	 * Build the class SearchTarget.java 
	 * @param searchByClientPanel
	 * @param searchByNamePanel
	 * @param searchBySkillsPanel
	 * @param fieldClient
	 * @param fieldName
	 * @param search
	 * @param listCollab
	 * @param collabService
	 * @param skillService
	 */
	public SearchTarget(Panel searchByClientPanel, Panel searchByNamePanel,
			Panel searchBySkillsPanel, TextField fieldClient,
			TextField fieldName, Button search, List<Collaborator> listCollab,
			ICollaboratorService collabService, ISkillService skillService, List<CheckBox> listCheckBoxSkills) {
		super();
		this.searchByClientPanel = searchByClientPanel;
		this.searchByNamePanel = searchByNamePanel;
		this.searchBySkillsPanel = searchBySkillsPanel;
		this.fieldClient = fieldClient;
		this.fieldName = fieldName;
		this.search = search;
		this.listCollab = listCollab;
		this.collabService = collabService;
		this.skillService = skillService;
		this.listCheckBoxSkills = listCheckBoxSkills;

		buildMain();
	}

	/**
	 * The main builder
	 * 
	 * @class SearchTarget.java
	 */
	public void buildMain() {

		setMargin(true);
		setSpacing(true);

		try {
		
			buildField();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		buildButton();
	}

	/**
	 * The field builder
	 * @throws Exception 
	 * 
	 * @class SearchTarget.java
	 */
	public void buildField() throws Exception {

		this.fieldClient.setCaption("Nom du client : ");
		this.fieldClient.addListener(this);
		this.searchByClientPanel.addComponent(this.fieldClient);

		this.fieldName.setCaption("Nom du collaborateur : ");
		this.fieldName.addListener(this);
		this.searchByNamePanel.addComponent(this.fieldName);
		
		//Build the Skills Panel
		List<Tool> listTools = this.skillService.getAllTools();
		
		for(Tool tool : listTools){
			
			CheckBox checkBox = new CheckBox(tool.getName());
			checkBox.setData(tool.getId());
			
			this.listCheckBoxSkills.add(checkBox);
			this.searchBySkillsPanel.addComponent(checkBox);
		}
		
		addComponent(this.searchByClientPanel);
		addComponent(this.searchByNamePanel);
		addComponent(this.searchBySkillsPanel);

		initField();
	}

	/**
	 * Default init for the fields
	 * 
	 * @class SearchTarget.java
	 */
	public void initField() {

		switchByNamePanel();
	}

	/**
	 * 
	 * @class SearchTarget.java
	 */
	public void switchByNamePanel() {

		this.searchByNamePanel.setVisible(true);
		this.searchByClientPanel.setVisible(false);
		this.searchBySkillsPanel.setVisible(false);
	}

	/**
	 * 
	 * @class SearchTarget.java
	 */
	public void switchByClientPanel() {

		this.searchByNamePanel.setVisible(false);
		this.searchByClientPanel.setVisible(true);
		this.searchBySkillsPanel.setVisible(false);
	}

	/**
	 * 
	 * @class SearchTarget.java
	 */
	public void switchBySkillsPanel() {

		this.searchByNamePanel.setVisible(false);
		this.searchByClientPanel.setVisible(false);
		this.searchBySkillsPanel.setVisible(true);
	}

	/**
	 * The button builder
	 * 
	 * @class SearchTarget.java
	 */
	public void buildButton() {

		this.search.setCaption("Rechercher");
		this.search.addListener(this);

		addComponent(this.search);
	}

	@Override
	public void buttonClick(ClickEvent event) {

		Button button = event.getButton();

		if (button == this.search) {

			if (this.searchByClientPanel.isVisible()) {

				String clientName = (String) this.fieldClient.getValue();
				
				
			}

			if (this.searchByNamePanel.isVisible()) {

				String collabName = (String) this.fieldName.getValue();
				
				try {
					
					this.listCollab = this.collabService.getAllCollaboratorsByLastName(collabName);
				
					updateObservateur();
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
			
			//Cas ou le Panel "Rechercher par Compétences" est visible
			if (this.searchBySkillsPanel.isVisible()) {

				//On vérifie si l'utilisateur a renseigné au moins une compétence
				if(!this.listCheckBoxSkills.isEmpty()){
					List<Integer> listToolId = new ArrayList<Integer>();

//					Echantillon de test
//					listToolId.add(new Integer(3));
//					listToolId.add(new Integer(4));
//					=> (1,7) = OK
//					listToolId.add(new Integer(4));
//					listToolId.add(new Integer(22));
//					=> (1,7,8) OK
//					listToolId.add(new Integer(4));
//					listToolId.add(new Integer(22));
//					listToolId.add(new Integer(23));
//					=> (1,8) OK

					listToolId = getListTooIdChecked();
					
					try {
						//On récupère tous les collaborateurs correspondant aux outils indiqués par l'utilisateur
						this.listCollab =  this.collabService.getAllCollaboratorsByListToolId(listToolId);
						updateObservateur();
						
					} catch (Exception e) {
					
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	public void textChange(TextChangeEvent event) {
		
		String valueField = event.getText();
		
		if(valueField.length() >= 3){
			
			if(this.searchByClientPanel.isVisible()){
				
				
			}
			
			if(this.searchByNamePanel.isVisible()){
				
				try {
					
					this.listCollab = this.collabService.getAllCollaboratorsByLastName(valueField);
					
					updateObservateur();
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				
			}
			
		}
	}
	
	@Override
	public void addObservateur(Object observateur, Class<?> cl) {

		if (cl == ISearchContent.class) {

			this.obs = (ISearchContent) observateur;
		}
	}

	@Override
	public void updateObservateur() {

		this.obs.changeSearchResults(this.listCollab);
	}

	@Override
	public void delObservateur() {
		this.obs = null;
	}
	
	
	/**
	 * Renvoie la liste des identifiants des outils cochés dans le Panel de checherche par Compétence
	 * @return
	 */
	private List<Integer> getListTooIdChecked() {
		List<Integer> listToolIdChecked = new ArrayList<Integer>();

		if(!this.listCheckBoxSkills.isEmpty()){
			for (CheckBox checkBox : listCheckBoxSkills) {
				boolean checked = checkBox.booleanValue();
				if(checked) listToolIdChecked.add((Integer)checkBox.getData());
			}
		}
		return listToolIdChecked;
	}
	/**
	 * Get the searchByClientPanel value
	 * 
	 * @return the searchByClientPanel
	 */
	public Panel getSearchByClientPanel() {
		return searchByClientPanel;
	}

	/**
	 * Set the searchByClientPanel value
	 * 
	 * @param searchByClientPanel
	 *            the searchByClientPanel to set
	 */
	public void setSearchByClientPanel(Panel searchByClientPanel) {
		this.searchByClientPanel = searchByClientPanel;
	}

	/**
	 * Get the searchByNamePanel value
	 * 
	 * @return the searchByNamePanel
	 */
	public Panel getSearchByNamePanel() {
		return searchByNamePanel;
	}

	/**
	 * Set the searchByNamePanel value
	 * 
	 * @param searchByNamePanel
	 *            the searchByNamePanel to set
	 */
	public void setSearchByNamePanel(Panel searchByNamePanel) {
		this.searchByNamePanel = searchByNamePanel;
	}

	/**
	 * Get the searchBySkillsPanel value
	 * 
	 * @return the searchBySkillsPanel
	 */
	public Panel getSearchBySkillsPanel() {
		return searchBySkillsPanel;
	}

	/**
	 * Set the searchBySkillsPanel value
	 * 
	 * @param searchBySkillsPanel
	 *            the searchBySkillsPanel to set
	 */
	public void setSearchBySkillsPanel(Panel searchBySkillsPanel) {
		this.searchBySkillsPanel = searchBySkillsPanel;
	}

	/**
	 * Get the fieldClient value
	 * 
	 * @return the fieldClient
	 */
	public TextField getFieldClient() {
		return fieldClient;
	}

	/**
	 * Set the fieldClient value
	 * 
	 * @param fieldClient
	 *            the fieldClient to set
	 */
	public void setFieldClient(TextField fieldClient) {
		this.fieldClient = fieldClient;
	}

	/**
	 * Get the fieldName value
	 * 
	 * @return the fieldName
	 */
	public TextField getFieldName() {
		return fieldName;
	}

	/**
	 * Set the fieldName value
	 * 
	 * @param fieldName
	 *            the fieldName to set
	 */
	public void setFieldName(TextField fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Get the search value
	 * 
	 * @return the search
	 */
	public Button getSearch() {
		return search;
	}

	/**
	 * Set the search value
	 * 
	 * @param search
	 *            the search to set
	 */
	public void setSearch(Button search) {
		this.search = search;
	}
	
	/**
	 * Get the collabService value
	 * @return the collabService
	 */
	public ICollaboratorService getCollabService() {
		return collabService;
	}

	/**
	 * Set the collabService value
	 * @param collabService the collabService to set
	 */
	public void setCollabService(ICollaboratorService collabService) {
		this.collabService = collabService;
	}

	/**
	 * Get the skillService value
	 * @return the skillService
	 */
	public ISkillService getSkillService() {
		return skillService;
	}

	/**
	 * Set the skillService value
	 * @param skillService the skillService to set
	 */
	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
	}

	/**
	 * Get the listCollab value
	 * @return the listCollab
	 */
	public List<Collaborator> getListCollab() {
		return listCollab;
	}

	/**
	 * Set the listCollab value
	 * @param listCollab the listCollab to set
	 */
	public void setListCollab(List<Collaborator> listCollab) {
		this.listCollab = listCollab;
	}
	
	/**
	 * Get the listCheckBoxSkills value
	 * @return the listCheckBoxSkills
	 */
	public List<CheckBox> getListCheckBoxSkills() {
		return listCheckBoxSkills;
	}

	/**
	 * Set the listCheckBoxSkills value
	 * @param listCheckBoxSkills the listCheckBoxSkills to set
	 */
	public void setListCheckBoxSkills(List<CheckBox> listCheckBoxSkills) {
		this.listCheckBoxSkills = listCheckBoxSkills;
	}
}
