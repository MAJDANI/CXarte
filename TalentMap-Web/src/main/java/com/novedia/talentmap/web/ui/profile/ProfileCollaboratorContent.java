package com.novedia.talentmap.web.ui.profile;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.commons.Constants;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * The Profile View contains the administrative part and the skill part of a
 * Collaborator
 * 
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */
public class ProfileCollaboratorContent extends VerticalLayout implements ClickListener {

	/**
	 * TalentMap Services
	 */
	private IColleagueService colleagueService;

	/**
	 * All views
	 */
	private CollaboratorForm collabForm;

	/**
	 * Buttons
	 */
	private Button save;
	private Button cancel;
	private Button edit;
	
	
	/**
	 * 
	 * Build the class ProfileCollaboratorContent.java 
	 * @param collabService
	 * @param profileService
	 * @param collabForm
	 * @param save
	 * @param cancel
	 * @param bodyLayout
	 * @param footerLayout
	 * @param edit
	 */
	public ProfileCollaboratorContent(IColleagueService colleagueService,
			CollaboratorForm collabForm) {
		super();
		this.colleagueService = colleagueService;
		this.collabForm = collabForm;

		this.mainBuild();
	}

	/**
	 * The main builder
	 * @class ProfileView.java
	 */
	public void mainBuild() {

		//On ajoute le formulaire  
		this.collabForm.setImmediate(true);	
		this.collabForm.getFormCollaborator().setReadOnly(true);
		this.collabForm.getFormMission().setReadOnly(true);
		this.addComponent(this.collabForm);
		
		this.setMargin(true);
		this.setSizeFull();
		setImmediate(true);

		//Construction des boutons
		buildButtonLayout();
		
	}



	/**
	 * Build method for the Button Layout
	 * 
	 * @class ProfileView.java
	 * @return
	 */

	private void buildButtonLayout() {
		
		//layout pour les boutons
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		
		//Bouton "Editer"
		edit = new Button();
		edit.setCaption(Constants.ADMIN_DATA_EDIT_BUTTON);
		edit.addListener(this);
		buttonLayout.addComponent(edit);
		
		//Bouton "Enregistrer"
		save = new Button();
		save.setCaption(Constants.ADMIN_DATA_SAVE_BUTTON);
		save.addListener(this);
		save.setEnabled(false);
		buttonLayout.addComponent(save);

		//Bouton "Annuler"
		cancel = new Button();
		cancel.setCaption(Constants.ADMIN_DATA_CANCEL_BUTTON);
		cancel.addListener(this);
		buttonLayout.addComponent(cancel);
		
		// Footer layout
		HorizontalLayout footerLayout = new HorizontalLayout();
		
		footerLayout.setMargin(true);
		footerLayout.setSizeFull();
		footerLayout.setStyleName(TalentMapCSS.FOOTER_PROFILE);
		footerLayout.addComponent(buttonLayout);
		footerLayout.setComponentAlignment(buttonLayout, Alignment.MIDDLE_CENTER);

		addComponent(footerLayout);
	}

	/**
	 * Save the Collaborator Data : Les données administratives concernant le collaborateur
	 * dans la fiche Profil (pas les données relatives à la dernière mission en date)
	 * @class ProfileColaboratorContent.java
	 * @return : int : code permettant de savoir si l'opération s'est bien passée (int=1) ou pas (int=0) 
	 */
	private int saveDataCollaborator(){
		try {
			this.collabForm.getFormCollaborator().commit();
			BeanItem<Colleague> collabItem = (BeanItem<Colleague>) this.collabForm.getFormCollaborator()
				.getItemDataSource();
			Colleague collab = collabItem.getBean();
			return this.colleagueService.saveColleague(collab);
		} catch (InvalidValueException invalidVE) {
			return 0;
		}
	}
	
	/**
	 * Save the Mission Data : Les données administratives concernant la dernière mission
	 * du collaborateur (pas les données "personnelles" du collaborateur)
	 * @class ProfileColaboratorContent.java
	 * @return : int : code permettant de savoir si l'opération s'est bien passée (int=1) ou pas (int=0) 
	 */
	private int saveDataMission(){
		try {
			this.collabForm.getFormMission().commit();
				BeanItem<Mission> missionItem = (BeanItem<Mission>) this.collabForm.getFormMission()
						.getItemDataSource();
				Mission mission = missionItem.getBean();
				return this.colleagueService.saveMission(mission);
		} catch (InvalidValueException invalidVE) {
			return 0;
		}
		
	}

	/**
	 * Button Click Listener
	 */
	@Override
	public void buttonClick(ClickEvent event) {
			Button button = event.getButton();
		
		//Save Button
		if (button.getCaption().equals(Constants.ADMIN_DATA_SAVE_BUTTON)) {
			saveDataCollaborator();
			saveDataMission();
			this.collabForm.getFormCollaborator().setReadOnly(true);
			this.collabForm.getFormMission().setReadOnly(true);
			this.save.setEnabled(false);
			this.edit.setEnabled(true);
			this.cancel.setEnabled(false);
			
		} else if (button.getCaption().equals(Constants.ADMIN_DATA_EDIT_BUTTON)){
			this.collabForm.getFormCollaborator().setReadOnly(false);
			this.collabForm.getFormMission().setReadOnly(false);
			this.save.setEnabled(true);
			this.edit.setEnabled(false);
			this.cancel.setEnabled(true);
			
		} else if (button.getCaption().equals(Constants.ADMIN_DATA_CANCEL_BUTTON)){
			this.collabForm.refreshAllFormsToDefault();
			this.collabForm.getFormCollaborator().setReadOnly(true);
			this.collabForm.getFormMission().setReadOnly(true);
			this.save.setEnabled(false);
			this.edit.setEnabled(true);
			this.cancel.setEnabled(false);
		} 
	}


	/**
	 * Set the collabService value
	 * 
	 * @param collabService
	 *            the collabService to set
	 */
	public void setColleagueService(IColleagueService colleagueService) {
		this.colleagueService = colleagueService;
	}

	/**
	 * Set the collabForm value
	 * 
	 * @param collabForm
	 *            the collabForm to set
	 */
	public void setCollabForm(CollaboratorForm collabForm) {
		this.collabForm = collabForm;
	}
	
	/**
	 * Get the collabForm
	 * @return
	 */
	public CollaboratorForm getCollabForm() {
		return collabForm;
	}

}
