package com.novedia.talentmap.web.ui.profile;

import java.util.List;
import java.util.Vector;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.web.ui.formFactory.CollaboratorFormFieldFactory;
import com.novedia.talentmap.web.ui.formFactory.MissionFormFieldFactory;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.data.Item;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * The form of Administrative Information of Collaborator
 * 
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */
public class CollaboratorForm extends FormLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1195179317563179902L;
	
	/**
	 * Java Object
	 */
	private Vector<Object> fieldOrderCollaborator;
	private Vector<Object> fieldOrderMission;
	
	/**
	 * Current Colleague connected
	 */
	private Colleague currentColleague;
	private Manager currentColleaguesManager; 
	private List<Mission> currentColleaguesMissions;
	private Mission currentColleaguesLastMission;

	/**
	 * Constants
	 */
	private int COLLAB_ID = 2;
	public static final Object[] NAME_FIELD_COLLABORATOR = new Object[] { "Nom", "Prénom",
			"Profil", "Email", "Tél", "Date d'entrée Novedia",
			"Années d'expérience", "Ingénieur d'affaire" };
	public static final Object[] FIELD_ORDER_COLLABORATOR = new Object[] { "lastName",
			"firstName", "profileId", "email", "phone", "employmentDate",
			"experience", "businessEngineer" };
	public static final Object[] NAME_FIELD_MISSION = new Object[] {"Client", "Début mission", "Fin mission"};
	public static final Object[] FIELD_ORDER_MISSION = new Object[] {"client", "startDate", "endDate"};

	public static final String MESSAGE_COLLABORATOR_ID_NOT_FOUND = "Collaborator Id Not Found";

	/**
	 * Vaddin Components
	 */
	private Form formCollaborator;
	private Form formMission;
	private TextField managerField;

	/**
	 * Vaadin Layout
	 */
	private GridLayout gLayoutCollaborator;
	private GridLayout gLayoutMission;
	private VerticalLayout vLayoutManager;

	/**
	 * TalentMap service
	 */
	private IColleagueService collaboratorService;
	private IProfileService profileService;

	/**
	 * 
	 * Build the class CollaboratorForm.java
	 * 
	 * @param collaboratorService
	 * @param profileService
	 * @param fieldOrder
	 * @param gLayoutCollaborator
	 */
	public CollaboratorForm(IColleagueService collaboratorService,
			IProfileService profileService, Vector<Object> fieldOrderCollaborator, Vector<Object> fieldOrderMission,
			GridLayout gLayoutCollaborator, GridLayout gLayoutMission, VerticalLayout vLayoutManager,
			Form formCollaborator, Form formMission, TextField managerField) {

		this.fieldOrderCollaborator = fieldOrderCollaborator;
		this.fieldOrderMission = fieldOrderMission;
		this.gLayoutCollaborator = gLayoutCollaborator;
		this.gLayoutMission = gLayoutMission;
		this.vLayoutManager = vLayoutManager;
		this.collaboratorService = collaboratorService;
		this.profileService = profileService;
		this.formCollaborator = formCollaborator;
		this.formMission = formMission;
		this.managerField = managerField;

		mainBuild();
	}
	
	/**
	 * The main builder
	 * @class CollaboratorForm.java
	 */
	public void mainBuild(){
		
		cleanAll();
		setSizeFull();
		
		// Build Layout
		buildLayout();

		try {

			// Set the order for Collaborator Form
			CUtils.setOrderForm(this.fieldOrderCollaborator, FIELD_ORDER_COLLABORATOR);
			
			//Set the order for Mission Form
			CUtils.setOrderForm(this.fieldOrderMission, FIELD_ORDER_MISSION);

			buildCurrentCollaboratorDatas();
			
			buildFormCollaborator();

			buildFormMission();
			
			buildFormManager();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cleanAll(){
		
		removeAllComponents();
		
		this.formCollaborator = new Form();
		
		this.formMission = new Form();
		
		this.gLayoutCollaborator = new GridLayout();
		
		this.gLayoutMission = new GridLayout();
		
	}

	/**
	 * Gets the current collaborator connected, his manager, his missions 
	 * @class CollaboratorForm.java
	 * @throws Exception
	 */
	private void buildCurrentCollaboratorDatas() throws Exception {
		this.currentColleague = this.collaboratorService.getColleague(COLLAB_ID);
		if(currentColleague != null) {
			this.currentColleaguesManager = this.collaboratorService.getManager(currentColleague.getManagerId());
			//TODO : ajouter une méthode getAllMissionsOrderByStartDate
			this.currentColleaguesMissions = this.collaboratorService.getAllMissions(COLLAB_ID);
			//Récupérer la dernière mission en date du collaborateur
			int nbMissions = this.currentColleaguesMissions.size();
			if(nbMissions > 0) {
				this.currentColleaguesLastMission = this.currentColleaguesMissions.get(nbMissions-1);
			}
		}
	}
	
	/**
	 * Build the Collaborator Form
	 * 
	 * @class CollaboratorForm.java
	 * @throws Exception
	 */
	private void buildFormCollaborator() throws Exception {

		// Set the content form
		this.formCollaborator.setLayout(this.gLayoutCollaborator);

		this.formCollaborator
				.setFormFieldFactory(new CollaboratorFormFieldFactory(
						this.profileService));

		
		if (this.currentColleague != null){
			BeanItem<Item> collabBean = new BeanItem(this.currentColleague);
			this.formCollaborator.setItemDataSource(collabBean, this.fieldOrderCollaborator);
	
			// Set the good value for the Select Item
			int profileId = this.currentColleague.getProfileId();
			
			String profileType = this.profileService.getProfile(profileId).getType();
			
		}
		else {
			InvalidValueException invalidVE = new InvalidValueException(MESSAGE_COLLABORATOR_ID_NOT_FOUND);
			this.formCollaborator.setComponentError(invalidVE);
		}
		addComponent(this.formCollaborator);
	}

	/**
	 * Build the Mission Form
	 * 
	 * @class CollaboratorForm.java
	 * @throws Exception
	 */
	private void buildFormMission() throws Exception {

		// Set the content form
		this.formMission.setLayout(this.gLayoutMission);

		this.formMission.setFormFieldFactory(new MissionFormFieldFactory());
		
		if(currentColleaguesLastMission != null){
			@SuppressWarnings("unchecked")
			BeanItem<Item> missionBean = new BeanItem(currentColleaguesLastMission);
			this.formMission.setItemDataSource(missionBean, this.fieldOrderMission);
			addComponent(this.formMission);
		}
	}
	
	/**
	 * Builds the Manager Form
	 * @throws Exception
	 */
	private void buildFormManager() throws Exception{
		
		this.managerField.setCaption("Consultant Manager : ");
		this.managerField.setStyleName("consultant-manager");
		
		if (this.currentColleague != null){
			if (currentColleaguesManager != null) {
				//TODO: J'ai rajouté le test sur le manager à la demande de JM
				//TODO: pour que l'application ne plante pas.
				this.managerField.setValue(currentColleaguesManager.getFirstName()+ " " + currentColleaguesManager.getLastName());
			}
		} else {
			InvalidValueException invalidVE = new InvalidValueException(MESSAGE_COLLABORATOR_ID_NOT_FOUND);
			this.managerField.setComponentError(invalidVE);
		}
		this.managerField.setReadOnly(true);
		
		this.vLayoutManager.addComponent(this.managerField);
		
		addComponent(this.vLayoutManager);
		
	}

	/**
	 * Build the GridLayout
	 * 
	 * @class CollaboratorForm.java
	 */
	private void buildLayout() {

		// Build the Grid Layout for Collaborator Form
		this.gLayoutCollaborator.setMargin(true);
		this.gLayoutCollaborator.setSpacing(true);
		this.gLayoutCollaborator.setColumns(3);
		this.gLayoutCollaborator.setRows(4);

		// Build the Grid Layout for Mission Form
		this.gLayoutMission.setMargin(true);
		this.gLayoutMission.setSpacing(true);
		this.gLayoutMission.setColumns(3);
		this.gLayoutMission.setRows(1);
		
		this.setStyleName(TalentMapCSS.ADMINISTRATIVE_FORM);
		
	}
	
	/**
	 * Set the profileService value
	 * 
	 * @param profileService
	 *            the profileService to set
	 */
	public void setProfileService(IColleagueService profileService) {
		this.collaboratorService = profileService;
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
	 * Set the collaboratorService value
	 * @param collaboratorService the collaboratorService to set
	 */
	public void setCollaboratorService(IColleagueService collaboratorService) {
		this.collaboratorService = collaboratorService;
	}

	/**
	 * Set the gLayoutCollaborator value
	 * 
	 * @param gLayoutCollaborator
	 *            the gLayoutCollaborator to set
	 */
	public void setgLayoutCollaborator(GridLayout gLayoutCollaborator) {
		this.gLayoutCollaborator = gLayoutCollaborator;
	}

	/**
	 * Set the gLayoutMission value
	 * 
	 * @param gLayoutMission
	 *            the gLayoutMission to set
	 */
	public void setgLayoutMission(GridLayout gLayoutMission) {
		this.gLayoutMission = gLayoutMission;
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
	 * Get the formCollaborator value
	 * 
	 * @return the formCollaborator
	 */
	public Form getFormCollaborator() {
		return formCollaborator;
	}

	/**
	 * Get the formMission value
	 * 
	 * @return the formMission
	 */
	public Form getFormMission() {
		return formMission;
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

	/**
	 * Set the formMission value
	 * 
	 * @param formMission
	 *            the formMission to set
	 */
	public void setFormMission(Form formMission) {
		this.formMission = formMission;
	}
	
	/**
	 * Set the managerField value
	 * @param managerField the managerField to set
	 */
	public void setManagerField(TextField managerField) {
		this.managerField = managerField;
	}

	/**
	 * Set the vLayoutManager value
	 * @param vLayoutManager the vLayoutManager to set
	 */
	public void setvLayoutManager(VerticalLayout vLayoutManager) {
		this.vLayoutManager = vLayoutManager;
	}
	
	/**
	 * Get the cOLLAB_ID value
	 * @return the cOLLAB_ID
	 */
	public int getCOLLAB_ID() {
		return COLLAB_ID;
	}

	/**
	 * Set the cOLLAB_ID value
	 * @param cOLLAB_ID the cOLLAB_ID to set
	 */
	public void setCOLLAB_ID(int cOLLAB_ID) {
		COLLAB_ID = cOLLAB_ID;
	}
}
