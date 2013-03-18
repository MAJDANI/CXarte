package com.novedia.talentmap.web.ui.profile;

import java.util.Vector;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IClientService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.web.ColleagueData;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.ui.formFactory.CollaboratorFormFieldFactory;
import com.novedia.talentmap.web.ui.formFactory.MissionFormFieldFactory;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.data.Item;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * The form of Administrative Information of Collaborator
 * 
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */
public class CollaboratorForm extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1195179317563179902L;
	
	
	private Authentication authentication;
	 
	/**
	 * Java Object
	 */
	private Vector<Object> fieldOrderCollaborator;
	private Vector<Object> fieldOrderMission;
	


	/**
	 * Vaddin Components
	 */
	private Form formCollaborator;
	private Form formMission;


	/**
	 * TalentMap service
	 */
	private IColleagueService colleagueService;
	private IProfileService profileService;
	private IClientService clientService;
	private IBusinessEngineerService businessEngineerService;
	
	
	
	/**
	 * Default constructor
	 */
	public CollaboratorForm(){
		super();
	}
	
	/**
	 * Build the colleague data
	 * @return
	 */
	public VerticalLayout buildColleagueData(){
		removeAllComponents();
		mainBuild();
		
		return this;
	}
		
	
	
	/**
	 * The main builder
	 * @class CollaboratorForm.java
	 */
	public void mainBuild(){
		
		setSizeFull();

		try {
			// Set the order for Collaborator Form
			CUtils.setOrderForm(this.fieldOrderCollaborator, ConstantsEnglish.FIELD_ORDER_COLLABORATOR);
			//Set the order for Mission Form
			CUtils.setOrderForm(this.fieldOrderMission, ConstantsEnglish.FIELD_ORDER_MISSION);
			buildFormColleagueData();
			buildFormColleagueMission();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Label dataAdminLabel;
	/**
	 * Build the Collaborator Form
	 * 
	 * @class CollaboratorForm.java
	 * @throws Exception
	 */
	private void buildFormColleagueData() throws Exception {
		
		// Label "Données administratives"
		dataAdminLabel = new Label();
		dataAdminLabel.setCaption(ConstantsEnglish.ADMIN_DATA_LABEL);
		dataAdminLabel.setStyle(TalentMapCSS.H2);
		addComponent(dataAdminLabel);
		
		// Layout des données administratives (2 colonnes)
		GridLayout adminDatasLayout = new GridLayout();

		// Création des différents champs du formulaire "Données administratives"
		this.formCollaborator.setFormFieldFactory(new CollaboratorFormFieldFactory(
						this.profileService, this.businessEngineerService, this.colleagueService));
		
		//Colleague currentColleague = colleagueService.getColleague(COLLAB_ID);
		Colleague currentColleague = colleagueService.getColleague(authentication.getColleagueId());
		initFormColleagueData(currentColleague);

		this.formCollaborator.setImmediate(true);
		
		adminDatasLayout.setMargin(true);
		adminDatasLayout.setSpacing(true);
		adminDatasLayout.setColumns(3);
		this.formCollaborator.setLayout(adminDatasLayout);
		
		addComponent(this.formCollaborator);
	}

	
	/**
	 * Init the value of the collaborator form with current colleague datas
	 * @param currentColleague
	 */
	private void initFormColleagueData(Colleague currentColleague){
		if(currentColleague != null){
			BeanItem<Item> collaboratorBean = new BeanItem(currentColleague);
			this.formCollaborator.setItemDataSource(collaboratorBean, this.fieldOrderCollaborator);
		} else {
			InvalidValueException invalidVE = new InvalidValueException(ConstantsEnglish.MESSAGE_COLLABORATOR_ID_NOT_FOUND);
			this.formCollaborator.setComponentError(invalidVE);
		}
	}
	
	/**
	 * Build the Mission Form
	 * 
	 * @class CollaboratorForm.java
	 * @throws Exception
	 */
	private void buildFormColleagueMission() throws Exception {
		
		Mission currentColleaguesLastMission = colleagueService.getLastMission(authentication.getColleagueId());
		if(currentColleaguesLastMission != null){
			// Label "Last Mission"
			Label lastMissionLabel = new Label();
			lastMissionLabel.setCaption(ConstantsEnglish.LAST_MISSION_LABEL);
			lastMissionLabel.setStyle(TalentMapCSS.H2);
			addComponent(lastMissionLabel);
			
			// Layout de la dernière mission
			GridLayout lastMissionDatasLayout = new GridLayout();
			lastMissionDatasLayout.setMargin(true);
			lastMissionDatasLayout.setSpacing(true);
			lastMissionDatasLayout.setColumns(3);
			lastMissionDatasLayout.setRows(1);
			
			//Mission currentColleaguesLastMission = colleagueService.getLastMission(COLLAB_ID);
			
				// Création des différents champs du formulaire "Dernière mission"
			this.formMission.setFormFieldFactory(new MissionFormFieldFactory(this.clientService));
			initFormColleagueMission(currentColleaguesLastMission);
			this.formMission.setImmediate(true);
			this.formMission.setLayout(lastMissionDatasLayout);
			this.formMission.setReadOnly(true);
			addComponent(this.formMission);
			
		}
	}
	
	/**
	 * Init the value of the mission form with last mission datas
	 * @param lastMission
	 */
	private void initFormColleagueMission(Mission lastMission){
		if(lastMission != null){
			BeanItem<Item> lastMissionBean = new BeanItem(lastMission);
			this.formMission.setItemDataSource(lastMissionBean, this.fieldOrderMission);
		} else {
			InvalidValueException invalidVE = new InvalidValueException(ConstantsEnglish.MESSAGE_COLLABORATOR_ID_NOT_FOUND);
			this.formCollaborator.setComponentError(invalidVE);
		}
	}	
	
	/**
	 * Refresh all the forms with default values (Mission and AdminData)
	 */
	public void refreshAllFormsToDefault() {
		Colleague currentColleague = colleagueService.getColleague(authentication.getColleagueId());
		Mission currentColleaguesLastMission = colleagueService.getLastMission(authentication.getColleagueId());
		//Workaround to solve a gridLayout bug vaadin with method SetItemDataSource
		this.formMission.getLayout().removeAllComponents();
		this.formCollaborator.getLayout().removeAllComponents();
		
		initFormColleagueData(currentColleague);
		if(currentColleaguesLastMission != null){
			initFormColleagueMission(currentColleaguesLastMission);
		}
		
	}
	
	
	/**
	 * Set the profileService value
	 * 
	 * @param profileService
	 *            the profileService to set
	 */
	public void setProfileService(IProfileService profileService) {
		this.profileService = profileService;
	}

	/**
	 * Set the fieldOrderCollaborator value
	 * @param fieldOrderCollaborator the fieldOrderCollaborator to set
	 */
	public void setFieldOrderCollaborator(Vector<Object> fieldOrderCollaborator) {
		this.fieldOrderCollaborator = fieldOrderCollaborator;
	}

	/**
	 * Set the fieldOrderMission value
	 * @param fieldOrderMission the fieldOrderMission to set
	 */
	public void setFieldOrderMission(Vector<Object> fieldOrderMission) {
		this.fieldOrderMission = fieldOrderMission;
	}

	/**
	 * Set the profileService value
	 * 
	 * @param profileService
	 *            the profileService to set
	 */
	public void setColleagueService(IColleagueService colleagueService) {
		this.colleagueService = colleagueService;
	}

	/**
	 * Get the formCollaborator value
	 * 
	 * @return the formCollaborator
	 */
	public Form getFormCollaborator() {
		return formCollaborator;
	}

	/**
	 * Set the formCollaborator value
	 * 
	 * @param formCollaborator
	 *            the formCollaborator to set
	 */
	public void setFormCollaborator(Form formCollaborator) {
		this.formCollaborator = formCollaborator;
	}
	

	public Form getFormMission() {
		return formMission;
	}

	public void setFormMission(Form formMission) {
		this.formMission = formMission;
	}
	
	/**
	 * Get authentication value.
	 * @return authentication
	 */
	public Authentication getAuthentication() {
		return authentication;
	}

	/**
	 * Set authentication value.
	 * @param authentication
	 */
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
	
	
	
	public IClientService getClientService() {
		return clientService;
	}

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	
	
	public IBusinessEngineerService getBusinessEngineerService() {
		return businessEngineerService;
	}

	public void setBusinessEngineerService(
			IBusinessEngineerService businessEngineerService) {
		this.businessEngineerService = businessEngineerService;
	}

	public IColleagueService getColleagueService() {
		return colleagueService;
	}

	public IProfileService getProfileService() {
		return profileService;
	}

}
