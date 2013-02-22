package com.novedia.talentmap.web.ui.profile;

import java.util.Vector;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IClientService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IProfileService;
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
	
	/**
	 * Java Object
	 */
	private Vector<Object> fieldOrderCollaborator;
	private Vector<Object> fieldOrderMission;
	

	/**
	 * ConstantsEnglish
	 */
	//TODO remis la valeur 2 pour que l'application puisse tourner en attendant qu'on enlève le bouchon
	public static int COLLAB_ID = 2;

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
	 * 
	 * Build the class CollaboratorForm.java
	 * 
	 * @param collaboratorService
	 * @param profileService
	 * @param fieldOrder
	 * @param gLayoutCollaborator
	 */
	public CollaboratorForm(IColleagueService colleagueService,
			IProfileService profileService, 
			IClientService clientService, 
			IBusinessEngineerService businessEngineerService,
			Vector<Object> fieldOrderCollaborator, 
			Vector<Object> fieldOrderMission,
			Form formCollaborator, Form formMission) {

		this.fieldOrderCollaborator = fieldOrderCollaborator;
		this.fieldOrderMission = fieldOrderMission;
		this.colleagueService = colleagueService;
		this.profileService = profileService;
		this.clientService = clientService;
		this.businessEngineerService = businessEngineerService;
		this.formCollaborator = formCollaborator;
		this.formMission = formMission;

		mainBuild();
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
			buildFormCollaborator();
			buildFormMission();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Build the Collaborator Form
	 * 
	 * @class CollaboratorForm.java
	 * @throws Exception
	 */
	private void buildFormCollaborator() throws Exception {
		
		// Label "Données administratives"
		Label dataAdminLabel = new Label();
		dataAdminLabel.setCaption(ConstantsEnglish.ADMIN_DATA_LABEL);
		dataAdminLabel.setStyle(TalentMapCSS.H2);
		addComponent(dataAdminLabel);
		
		// Layout des données administratives (2 colonnes)
		GridLayout adminDatasLayout = new GridLayout();

		// Création des différents champs du formulaire "Données administratives"
		this.formCollaborator
				.setFormFieldFactory(new CollaboratorFormFieldFactory(
						this.profileService, this.businessEngineerService, this.colleagueService));
		
		Colleague currentColleague = colleagueService.getColleague(COLLAB_ID);
		initFormCollaborator(currentColleague);

		// VGU
		// Set the form to act immediately on user input. This is
		// necessary for the validation of the fields to occur immediately
		// when the input focus changes and not just on commit.	
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
	private void initFormCollaborator(Colleague currentColleague){
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
	private void buildFormMission() throws Exception {

		// Label "Données administratives"
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
		
		// Création des différents champs du formulaire "Dernière mission"
		this.formMission.setFormFieldFactory(new MissionFormFieldFactory(this.clientService));
		
		Mission currentColleaguesLastMission = colleagueService.getLastMission(COLLAB_ID);
		initFormMission(currentColleaguesLastMission);
		// VGU
		// Set the form to act immediately on user input. This is
		// necessary for the validation of the fields to occur immediately
		// when the input focus changes and not just on commit.	
		this.formMission.setImmediate(true);
		this.formMission.setLayout(lastMissionDatasLayout);
		this.formMission.setReadOnly(true);

		addComponent(this.formMission);
	}
	
	/**
	 * Init the value of the mission form with last mission datas
	 * @param lastMission
	 */
	private void initFormMission(Mission lastMission){
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
	public void refreshAllFormsToDefault(){
		Colleague currentColleague = colleagueService.getColleague(COLLAB_ID);
		Mission currentColleaguesLastMission = colleagueService.getLastMission(COLLAB_ID);
		//Workaround to solve a gridLayout bug vaadin with method SetItemDataSource
		this.formMission.getLayout().removeAllComponents();
		this.formCollaborator.getLayout().removeAllComponents();
		
		initFormCollaborator(currentColleague);
		initFormMission(currentColleaguesLastMission);
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
	

	public Form getFormMission() {
		return formMission;
	}

	public void setFormMission(Form formMission) {
		this.formMission = formMission;
	}
	
	public void discard(){
		
	}
}
