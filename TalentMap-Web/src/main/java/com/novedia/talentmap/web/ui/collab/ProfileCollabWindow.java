package com.novedia.talentmap.web.ui.collab;

import java.util.Vector;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IManagerService;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.commons.Constants;
import com.novedia.talentmap.web.ui.formFactory.CollaboratorFormFieldFactory;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Form;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * The {@link ProfileCollabWindow} is the main window for render 
 *  profile of collabs.
 *  
 * @author moumbe
 *
 */
public class ProfileCollabWindow extends Window {

	/**
	 * Vaadin Components
	 */
	private Form dataCollab;
	private Accordion skillCollab;
	
	/**
	 * Talent Map Services
	 */
	private ISkillService skillService;
	private IManagerService managerService;
	private IProfileService profileService;
	private IBusinessEngineerService businessEngineerService;

	/**
	 * Java Object
	 */
	private Vector<Object> fieldOrderCollaborator;

	/**
	 * Constants
	 */
	private int COLLAB_ID = 2;


	/**
	 * 
	 * Build the class ProfileCollabWindow.java 
	 * @param dataCollab
	 * @param skillCollab
	 * @param skillService
	 * @param managerService
	 * @param fieldOrderCollaborator
	 * @param profileService
	 */
	public ProfileCollabWindow(Form dataCollab, Accordion skillCollab,
			ISkillService skillService, IManagerService managerService,
			Vector<Object> fieldOrderCollaborator,
			IProfileService profileService, IBusinessEngineerService businessEngineerService) {
		super();
		this.dataCollab = dataCollab;
		this.skillCollab = skillCollab;
		this.skillService = skillService;
		this.managerService = managerService;
		this.fieldOrderCollaborator = fieldOrderCollaborator;
		this.profileService = profileService;
		this.businessEngineerService = businessEngineerService;
	}
	
	/**
	 * The main builder
	 * @class ProfileCollabWindow.java
	 */
	public void mainBuild() {
		setModal(true);
		center();
		this.setWidth("1135");

		removeAllComponents();
		buildDataCollaborator();
		buildSkillCollaborator();
	}
	
	/**
	 * The Data Collaborator Form Builder
	 * @class ProfileCollabWindow.java
	 */
	public void buildDataCollaborator() {
		try {

			this.dataCollab.setFormFieldFactory(new CollaboratorFormFieldFactory(this.profileService, this.businessEngineerService));

			CUtils.setOrderForm(this.fieldOrderCollaborator,
					Constants.FIELD_ORDER_COLLABORATOR);
			
			Colleague collab = this.managerService.getCollaborator(COLLAB_ID);
			
			//We create a bean with the POJO Collaborator
			BeanItem<Item> collaboratorBean = new BeanItem(collab);
			
			//We give a name for the window
			setCaption("Fiche profil de : "+ collab.getLastName() +" "+ collab.getFirstName());
			
			//Data Binding
			this.dataCollab.setItemDataSource(collaboratorBean,
					this.fieldOrderCollaborator);

			this.dataCollab.setReadOnly(true);

			addComponent(this.dataCollab);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * The Skill Collaborator Accordion Builder
	 * @class ProfileCollabWindow.java
	 */
	public void buildSkillCollaborator() {
		System.out.println("ProfileCollabWindow.buildSkillCollaborator()");

		try {

			this.skillCollab = CUtils.MapSkillToAccordionSkill(
					this.skillService.getAllCollaboratorSkill(COLLAB_ID), null);

			addComponent(this.skillCollab);

		} catch (Exception e) {
			
			VerticalLayout vLayout = new VerticalLayout();
			Label noSkillLabel = new Label("Pas de compétences enregistrées !");
			noSkillLabel.setStyleName(TalentMapCSS.H2);
			
			vLayout.addComponent(noSkillLabel);
			vLayout.setMargin(true);

			addComponent(vLayout);

			e.printStackTrace();
		}
	}

	/**
	 * Get the cOLLAB_ID value
	 * 
	 * @return the cOLLAB_ID
	 */
	public int getCOLLAB_ID() {
		return COLLAB_ID;
	}

	/**
	 * Set the cOLLAB_ID value
	 * 
	 * @param cOLLAB_ID
	 *            the cOLLAB_ID to set
	 */
	public void setCOLLAB_ID(int cOLLAB_ID) {
		COLLAB_ID = cOLLAB_ID;
	}

	/**
	 * Get the dataCollab value
	 * 
	 * @return the dataCollab
	 */
	public Form getDataCollab() {
		return dataCollab;
	}

	/**
	 * Set the dataCollab value
	 * 
	 * @param dataCollab
	 *            the dataCollab to set
	 */
	public void setDataCollab(Form dataCollab) {
		this.dataCollab = dataCollab;
	}

	/**
	 * Get the skillCollab value
	 * 
	 * @return the skillCollab
	 */
	public Accordion getSkillCollab() {
		return skillCollab;
	}

	/**
	 * Set the skillCollab value
	 * 
	 * @param skillCollab
	 *            the skillCollab to set
	 */
	public void setSkillCollab(Accordion skillCollab) {
		this.skillCollab = skillCollab;
	}

	/**
	 * Set the skillService value
	 * 
	 * @param skillService
	 *            the skillService to set
	 */
	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
	}

	/**
	 * Set the managerService value
	 * 
	 * @param managerService
	 *            the managerService to set
	 */
	public void setManagerService(IManagerService managerService) {
		this.managerService = managerService;
	}

	/**
	 * Set the fieldOrderCollaborator value
	 * 
	 * @param fieldOrderCollaborator
	 *            the fieldOrderCollaborator to set
	 */
	public void setFieldOrderCollaborator(Vector<Object> fieldOrderCollaborator) {
		this.fieldOrderCollaborator = fieldOrderCollaborator;
	}
	
	/**
	 * Set the profileService value
	 * @param profileService the profileService to set
	 */
	public void setProfileService(IProfileService profileService) {
		this.profileService = profileService;
	}
}