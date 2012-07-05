package com.novedia.talentmap.web.ui.profile;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.web.util.IProfileView;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Select;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.themes.Reindeer;

/**
 * The Profile View contains the administrative part and the skill part of a
 * Collaborator
 * 
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */
public class ProfileView extends VerticalLayout implements ClickListener {

	/**
	 * TalentMap Services
	 */
	private ICollaboratorService collabService;
	private IProfileService profileService;

	/**
	 * All views
	 */
	private CollaboratorForm collabForm;
	private ListSkill listSkill;
	private Panel listSkillPanel;
	private Panel skillPanel;
	private AddSkillPanel addSkillPanel;
	private VerticalLayout bodyLayout;
	private HorizontalLayout footerLayout;

	/**
	 * Vaadin Components
	 */
	private Label skillLabel;

	/**
	 * Button Vaadin
	 */
	private Button save;
	private Button edit;
	private Button cancel;
	private Button addSkill;

	/**
	 * Constants
	 */
	private final int COLLAB_ID = 2;
	private final String EDIT_CAPTION =  "Modifier";
	private final String SAVE_CAPTION = "Enregistrer";
	private final String CANCEL_CAPTION = "Annnuler";
	private final String DATA_ADMIN_LABEL = "Données Administratives";
	private final String SKILL_LABEL = "Liste des compétences";
	private final String ADD_SKILL_LABEL = "Ajout de compétences";

	/**
	 * 
	 * Build the class ProfileView.java
	 * 
	 * @param collabService
	 * @param profileService
	 * @param collabForm
	 * @param listSkill
	 * @param addSkillPanel
	 * @param save
	 * @param cancel
	 * @param addSkill
	 */
	public ProfileView(ICollaboratorService collabService,
			IProfileService profileService, CollaboratorForm collabForm,
			ListSkill listSkill, Panel skillPanel, Panel listSkillPanel,
			Label skillLabel, Button save, Button cancel, Button addSkill,
			AddSkillPanel addSkillPanel, VerticalLayout bodyLayout,
			HorizontalLayout footerLayout, Button edit) {
		super();
		this.collabService = collabService;
		this.profileService = profileService;
		this.collabForm = collabForm;
		this.listSkill = listSkill;
		this.skillPanel = skillPanel;
		this.listSkillPanel = listSkillPanel;
		this.skillLabel = skillLabel;
		this.cancel = cancel;
		this.save = save;
		this.edit = edit;
		this.addSkill = addSkill;
		this.addSkillPanel = addSkillPanel;
		this.bodyLayout = bodyLayout;
		this.footerLayout = footerLayout;

		setImmediate(true);

		buildObersvators();

		this.mainBuild();
	}

	private void mainBuild() {

		buildDataAdminLayout();

		buildSkillLayout();

		buildButtonLayout();

		addComponent(this.bodyLayout);
		addComponent(this.footerLayout);
	}

	/**
	 * Build method for the Skill Layout
	 * 
	 * @class ProfileView.java
	 * @return
	 */
	private void buildSkillLayout() {
		this.skillLabel.setStyle(TalentMapCSS.H2);

		if (this.listSkill.isVisible()) {

			buildAddSkillPanel();
			this.skillPanel.setVisible(false);
			this.addSkillPanel.setVisible(false);

			buildListSkillPanel();

		} else {
			this.edit.setVisible(false);
			buildAddSkillPanel();
		}

	}

	private void buildObersvators() {

		/*
		 * Observable : AddSkillPanel
		 */
		this.addSkillPanel.addObservateur(new IProfileView() {

			@Override
			public void updateListSkill(ListSkill listSkill) {

				ProfileView.this.listSkill = listSkill;
				ProfileView.this.listSkill.setVisible(true);
				ProfileView.this.edit.setVisible(true);
				ProfileView.this.listSkill.setMargin(true);

				ProfileView.this.addSkillPanel.setVisible(false);
				ProfileView.this.edit.setEnabled(true);

				ProfileView.this.listSkillPanel.removeAllComponents();

				buildAddSkillPanel();
				buildListSkillPanel();

			}

		}, IProfileView.class);

	}

	/**
	 * Build the List of Skill
	 * 
	 * @class ProfileView.java
	 * @param skillPanel
	 */
	private void buildListSkillPanel() {

		this.addSkill.setCaption("Ajouter une compétence");
		this.addSkill.setEnabled(true);

		this.listSkillPanel.addComponent(this.skillLabel);
		this.listSkillPanel.addComponent(this.addSkill);
		this.listSkillPanel.addComponent(this.listSkill);
		skillLabel.setCaption(SKILL_LABEL);

		this.bodyLayout.addComponent(this.listSkillPanel);
	}

	/**
	 * Build method for the SkillPanel Layout
	 * 
	 * @class ProfileView.java
	 * @param skillPanel
	 */
	private void buildAddSkillPanel() {

		VerticalLayout vLayout = new VerticalLayout();
		HorizontalLayout hLayout = new HorizontalLayout();
		HorizontalLayout hLayout2 = new HorizontalLayout();

		this.skillPanel.addComponent(skillLabel);
		skillLabel.setCaption(ADD_SKILL_LABEL);

		Label question = new Label("Voulez-vous choisir vos compétences ?");

		this.addSkill.setCaption("Oui");

		vLayout.setSpacing(true);
		vLayout.addComponent(question);
		vLayout.addComponent(this.addSkill);
		vLayout.setComponentAlignment(this.addSkill, Alignment.MIDDLE_CENTER);

		hLayout.setMargin(true);
		hLayout.addComponent(vLayout);

		hLayout2.addComponent(hLayout);
		hLayout2.setSizeFull();
		hLayout2.setComponentAlignment(hLayout, Alignment.MIDDLE_CENTER);

		this.skillPanel.addComponent(hLayout2);
		this.bodyLayout.addComponent(this.skillPanel);

		this.addSkillPanel.setVisible(false);
		this.bodyLayout.addComponent(this.addSkillPanel);
	}

	/**
	 * Build method for the Data Admin Layout
	 * 
	 * @class ProfileView.java
	 * @return
	 */

	private void buildDataAdminLayout() {
		VerticalLayout dataAdminLayout = new VerticalLayout();
		Label dataAdminLabel = new Label(DATA_ADMIN_LABEL);

		dataAdminLabel.setStyle(TalentMapCSS.H2);

		dataAdminLayout.setMargin(true);
		dataAdminLayout.addComponent(dataAdminLabel);
		dataAdminLayout.addComponent(this.collabForm);

		this.bodyLayout.addComponent(dataAdminLayout);
	}

	/**
	 * Build method for the Button Layout
	 * 
	 * @class ProfileView.java
	 * @return
	 */

	private void buildButtonLayout() {
		HorizontalLayout hLayout = new HorizontalLayout();

		this.save.setCaption(SAVE_CAPTION);
		this.save.addListener(this);
		
		this.edit.setCaption(EDIT_CAPTION);
		this.edit.setDisableOnClick(true);
		this.edit.addListener(this);

		this.cancel.setCaption(CANCEL_CAPTION);
		this.cancel.addListener(this);

		this.addSkill.setDisableOnClick(true);
		this.addSkill.addListener(this);

		hLayout.setSpacing(true);
		hLayout.addComponent(this.save);
		hLayout.addComponent(this.edit);
		hLayout.addComponent(this.cancel);

		this.footerLayout.setSizeFull();
		this.footerLayout.setMargin(true);
		this.footerLayout.setStyleName(TalentMapCSS.FOOTER_PROFILE);

		this.footerLayout.addComponent(hLayout);
		this.footerLayout.setComponentAlignment(hLayout,
				Alignment.MIDDLE_CENTER);

	}
	
	/**
	 * Save the Collaborator Data
	 * @class ProfileView.java
	 */
	private int saveDataCollaborator(){
		
		BeanItem<Collaborator> collabItem = (BeanItem<Collaborator>) this.collabForm.getFormCollaborator()
				.getItemDataSource();
		
		Collaborator collab = collabItem.getBean();

		try {
			
			if(collab.getProfile_id() > 0){
				
				collab.setProfile_id(this.profileService.getProfile(
						collab.getProfile_id()).getId());
			}else{
				
				collab.setProfile_id(this.profileService.getProfile(
						collab.getProfile_id()).getId());
			}
			
			
			
			
			return this.collabService.updateCollaborator(collab);

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * Save the Mission Data
	 * @class ProfileView.java
	 */
	private int saveDataMission(){
		
		BeanItem<Mission> missionItem = (BeanItem<Mission>) this.collabForm.getFormMission()
				.getItemDataSource();
		
		Mission mission = missionItem.getBean();

		try {

			return this.collabService.updateMission(mission);

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * Button Click Listener
	 */
	@Override
	public void buttonClick(ClickEvent event) {

		Button button = event.getButton();
		
		//Save Button
		if (button == this.save) {
		
			if(saveDataCollaborator() == 1 && saveDataMission() == 1){
				
				getWindow().showNotification("Vos données ont été modifiées", Notification.TYPE_TRAY_NOTIFICATION);
			}
			
		//Edit Button
		}else if( button == this.edit){
			
			if(this.setAddSkillPanelWithTool()){
				
				this.addSkillPanel.setNewSkill(false);
				this.addSkillPanel.setVisible(true);
				this.addSkill.setEnabled(false);
			}else{
				this.edit.setEnabled(true);
			}
			
		//Cancel Button
		} else if (button == this.cancel) {
			
			this.addSkillPanel.setVisible(false);
			this.addSkill.setEnabled(true);
			this.edit.setEnabled(true);

		//Add Skill Panel button
		} else if (button == this.addSkill) {
			
			this.edit.setEnabled(false);
			this.skillPanel.setVisible(false);
			
			this.addSkillPanel.setNewSkill(true);
			this.addSkillPanel.setVisible(true);
			this.addSkillPanel.getToolSelect().setReadOnly(false);
		}
	}
	
	public boolean setAddSkillPanelWithTool(){
		
		Object rowId = this.listSkill.getTableTools().getValue(); // get the selected rows id
		
		if(rowId!=null){
			
			String toolName = (String)this.listSkill.getTableTools().getContainerProperty(rowId,"Nom de l'outil").getValue();
			
			this.addSkillPanel.getToolSelect().setReadOnly(false);
			this.addSkillPanel.getToolSelect().setValue(toolName);
			this.addSkillPanel.getToolSelect().setNullSelectionAllowed(false);
			this.addSkillPanel.getToolSelect().setReadOnly(true);
			
			return true;
		}else{
			
			getWindow().showNotification("Veuillez sélectionner un outil dans le tableau de compétences", Notification.TYPE_WARNING_MESSAGE);
			
			return false;
		}
		

	}

	/**
	 * Set the collabService value
	 * 
	 * @param collabService
	 *            the collabService to set
	 */
	public void setCollabService(ICollaboratorService collabService) {
		this.collabService = collabService;
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
	 * Set the collabForm value
	 * 
	 * @param collabForm
	 *            the collabForm to set
	 */
	public void setCollabForm(CollaboratorForm collabForm) {
		this.collabForm = collabForm;
	}

	/**
	 * Set the listSkill value
	 * 
	 * @param listSkill
	 *            the listSkill to set
	 */
	public void setListSkill(ListSkill listSkill) {
		this.listSkill = listSkill;
	}

	/**
	 * Set the save value
	 * 
	 * @param save
	 *            the save to set
	 */
	public void setSave(Button save) {
		this.save = save;
	}

	/**
	 * Set the cancel value
	 * 
	 * @param cancel
	 *            the cancel to set
	 */
	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}

	/**
	 * Set the addSkill value
	 * 
	 * @param addSkill
	 *            the addSkill to set
	 */
	public void setAddSkill(Button addSkill) {
		this.addSkill = addSkill;
	}

	/**
	 * Set the skillPanel value
	 * 
	 * @param skillPanel
	 *            the skillPanel to set
	 */
	public void setSkillPanel(Panel skillPanel) {
		this.skillPanel = skillPanel;
	}

	/**
	 * Set the listSkillPanel value
	 * 
	 * @param listSkillPanel
	 *            the listSkillPanel to set
	 */
	public void setListSkillPanel(Panel listSkillPanel) {
		this.listSkillPanel = listSkillPanel;
	}

	/**
	 * Set the skillLabel value
	 * 
	 * @param skillLabel
	 *            the skillLabel to set
	 */
	public void setSkillLabel(Label skillLabel) {
		this.skillLabel = skillLabel;
	}

	/**
	 * Set the addSkillPanel value
	 * 
	 * @param addSkillPanel
	 *            the addSkillPanel to set
	 */
	public void setAddSkillPanel(AddSkillPanel addSkillPanel) {
		this.addSkillPanel = addSkillPanel;
	}

	/**
	 * Set the bodyLayout value
	 * 
	 * @param bodyLayout
	 *            the bodyLayout to set
	 */
	public void setBodyLayout(VerticalLayout bodyLayout) {
		this.bodyLayout = bodyLayout;
	}

	/**
	 * Set the footerLayout value
	 * 
	 * @param footerLayout
	 *            the footerLayout to set
	 */
	public void setFooterLayout(HorizontalLayout footerLayout) {
		this.footerLayout = footerLayout;
	}
	
	/**
	 * Set the edit value
	 * @param edit the edit to set
	 */
	public void setEdit(Button edit) {
		this.edit = edit;
	}
}
