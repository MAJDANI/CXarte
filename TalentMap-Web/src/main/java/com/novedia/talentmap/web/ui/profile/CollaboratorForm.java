package com.novedia.talentmap.web.ui.profile;

import java.util.Vector;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.services.IProfileService;
import com.vaadin.data.Item;
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
	 * Constants
	 */
	private int COLLAB_ID = 2;
	private Vector<Object> fieldOrderCollaborator;
	private Vector<Object> fieldOrderMission;
	public static final Object[] NAME_FIELD_COLLABORATOR = new Object[] { "Nom", "Prénom",
			"Profil", "Email", "Tél", "Date d'entrée Novedia",
			"Années d'expérience", "Ingénieur d'affaire" };
	public static final Object[] FIELD_ORDER_COLLABORATOR = new Object[] { "last_name",
			"first_name", "profile_id", "email", "phone", "employment_date",
			"experience", "business_engineer" };
	public static final Object[] NAME_FIELD_MISSION = new Object[] {"Client", "Début mission", "Fin mission"};
	public static final Object[] FIELD_ORDER_MISSION = new Object[] {"client", "start_date", "end_date"};

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
	private ICollaboratorService collaboratorService;
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
	public CollaboratorForm(ICollaboratorService collaboratorService,
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

		// Build Layout
		buildGridLayout();

		try {

			// Set the order for Collaborator Form
			setOrderForm(this.fieldOrderCollaborator, FIELD_ORDER_COLLABORATOR);
			
			//Set the order for Mission Form
			setOrderForm(this.fieldOrderMission, FIELD_ORDER_MISSION);

			buildFormCollaborator();

			buildFormMission();
			
			buildFormManager();

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

		// Set the content form
		this.formCollaborator.setLayout(this.gLayoutCollaborator);

		this.formCollaborator
				.setFormFieldFactory(new CollaboratorFormFieldFactory(
						this.profileService));

		@SuppressWarnings("unchecked")
		BeanItem<Item> collabBean = new BeanItem(
				this.collaboratorService.getCollaborator(COLLAB_ID));

		this.formCollaborator.setItemDataSource(collabBean, this.fieldOrderCollaborator);

		// Set the good value for the Select Item
		int profileId = Integer.parseInt(this.collaboratorService
				.getCollaborator(COLLAB_ID).getProfile_id());
		this.formCollaborator.getField("profile_id").setValue(
				this.profileService.getProfile(profileId).getType());
		
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
		
		@SuppressWarnings("unchecked")
		BeanItem<Item> missionBean = new BeanItem(
				this.collaboratorService.getMission(COLLAB_ID));

		this.formMission.setItemDataSource(missionBean, this.fieldOrderMission);
		
		addComponent(this.formMission);
	}
	
	private void buildFormManager() throws Exception{
		
		this.managerField.setCaption("Consultant Manager : ");
		this.managerField.setStyleName("consultant-manager");
		
		Collaborator collab = this.collaboratorService.getCollaborator(COLLAB_ID);
		Manager manager = this.collaboratorService.getManager(Integer.parseInt(collab.getManager_id()));
		
		this.managerField.setValue(manager.getFirst_name()+ " " + manager.getLast_name());
		this.managerField.setReadOnly(true);
		
		this.vLayoutManager.addComponent(this.managerField);
		
		addComponent(this.vLayoutManager);
		
	}

	/**
	 * Build the GridLayout
	 * 
	 * @class CollaboratorForm.java
	 */
	private void buildGridLayout() {

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
		
		this.setStyleName("form-data-administrative");
		
	}

	/**
	 * Set the inputs order of the form
	 * 
	 * @class CollaboratorForm.java
	 * @param fieldOrder
	 */
	private void setOrderForm(Vector<Object> fieldOrder, Object[] order) {

		for (Object field : order) {
			fieldOrder.add(field);
		}
	}

	/**
	 * Set the profileService value
	 * 
	 * @param profileService
	 *            the profileService to set
	 */
	public void setProfileService(ICollaboratorService profileService) {
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
	public void setCollaboratorService(ICollaboratorService collaboratorService) {
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
}
