package com.novedia.talentmap.web.ui;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.web.util.IProfileView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.themes.Reindeer;

/**
 * The Profile View contains the administrative part and the skill part of a Collaborator
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
	private Button cancel;
	private Button addSkill;

	/**
	 * Constants
	 */
	private final String SAVE_CAPTION = "Enregistrer";
	private final String CANCEL_CAPTION = "Annnuler";
	private final String DATA_ADMIN_LABEL = "Données Administratives";
	private final String SKILL_LABEL = "Liste des compétences";
	private final String ADD_SKILL_LABEL = "Ajout de compétences";
	
	/**
	 * 
	 * Build the class ProfileView.java 
	 * @param collabService
	 * @param profileService
	 * @param collabForm
	 * @param listSkill
	 * @param addSkillPanel
	 * @param save
	 * @param cancel
	 * @param addSkill
	 */
	public ProfileView(ICollaboratorService collabService, IProfileService profileService, CollaboratorForm collabForm, ListSkill listSkill, 
			Panel skillPanel, Panel listSkillPanel, Label skillLabel, Button save, Button cancel, Button addSkill, AddSkillPanel addSkillPanel,
			VerticalLayout bodyLayout, HorizontalLayout footerLayout){
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
		this.addSkill = addSkill;
		this.addSkillPanel = addSkillPanel;
		this.bodyLayout = bodyLayout;
		this.footerLayout = footerLayout;
		
		setImmediate(true);
		
		buildObersvators();
		
		this.mainBuild();
	}
	
	private void mainBuild(){
		
		buildDataAdminLayout();
		
		buildSkillLayout();
		
		buildButtonLayout();
		
		addComponent(this.bodyLayout);
		addComponent(this.footerLayout);
	}
	
	/**
	 * Build method for the Skill Layout
	 * @class ProfileView.java
	 * @return
	 */
	private void buildSkillLayout(){
		this.skillLabel.setStyle(Reindeer.LABEL_H2);
		
		if(this.listSkill.isVisible()){
			
			buildAddSkillPanel();
			this.skillPanel.setVisible(false);
			this.addSkillPanel.setVisible(false);
			
			buildListSkillPanel();
			
		}else{
			buildAddSkillPanel();
		}
		
	}
	
	private void buildObersvators(){
		
		/*
		 * Observable : AddSkillPanel
		 */
		this.addSkillPanel.addObservateur(new IProfileView() {
			
			@Override
			public void updateListSkill(ListSkill listSkill) {
				
				ProfileView.this.listSkill = listSkill;
				ProfileView.this.listSkill.setVisible(true);
				
				ProfileView.this.addSkillPanel.setVisible(false);
				
				ProfileView.this.listSkillPanel.removeAllComponents();
				
				buildAddSkillPanel();
				buildListSkillPanel();
				
			}

		});
		
	}
	
	/**
	 * Build the List of Skill
	 * @class ProfileView.java
	 * @param skillPanel
	 */
	private void buildListSkillPanel(){
		
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
	 * @class ProfileView.java
	 * @param skillPanel
	 */
	private void buildAddSkillPanel(){
		
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
	 * @class ProfileView.java
	 * @return
	 */
	
	private void buildDataAdminLayout(){
		VerticalLayout dataAdminLayout = new VerticalLayout();
		Label dataAdminLabel = new Label(DATA_ADMIN_LABEL);
		
		dataAdminLabel.setStyle(Reindeer.LABEL_H2);
		
		dataAdminLayout.setMargin(true);
		dataAdminLayout.addComponent(dataAdminLabel);
		dataAdminLayout.addComponent(this.collabForm);
		
		this.bodyLayout.addComponent(dataAdminLayout);
	}
	
	/**
	 * Build method for the Button Layout
	 * @class ProfileView.java
	 * @return
	 */
	
	private void buildButtonLayout(){
		HorizontalLayout hLayout = new HorizontalLayout();
		
		this.save.setCaption(SAVE_CAPTION);
		this.save.addListener(this);
		
		this.cancel.setCaption(CANCEL_CAPTION);
		this.cancel.addListener(this);
		
		this.addSkill.setDisableOnClick(true);
		this.addSkill.addListener(this);
		
		hLayout.setSpacing(true);
		hLayout.addComponent(this.save);
		hLayout.addComponent(this.cancel);
		
		this.footerLayout.setSizeFull();
		this.footerLayout.setMargin(true);
		this.footerLayout.setStyleName("profile-footer");
		
		this.footerLayout.addComponent(hLayout);
		this.footerLayout.setComponentAlignment(hLayout, Alignment.MIDDLE_CENTER);
		
	}
	
	/**
	 * Button Click Listener
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		
		Button button = event.getButton();
		
		//getWindow().showNotification(event.toString(), Notification.TYPE_HUMANIZED_MESSAGE);
		
		if(button == this.save){
			
			BeanItem<Collaborator> collabItem = (BeanItem<Collaborator>) this.collabForm.getItemDataSource();
			Collaborator collab = collabItem.getBean();
			
			try {
				
				//int value = collabService.updateCollaborator(collab);
				
				collab.setProfile_id(this.profileService.getProfile(collab.getProfile_id()).getId());
				this.collabService.updateCollaborator(collab);
				
				getWindow().showNotification("Vos données ont été modifiées", Notification.TYPE_TRAY_NOTIFICATION);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		else if(button == this.cancel){
			
			
			
		}
		else if(button == this.addSkill){
			
			this.skillPanel.setVisible(false);
			this.addSkillPanel.setVisible(true);
			
		}
	}
	
	/**
	 * Set the collabService value
	 * @param collabService the collabService to set
	 */
	public void setCollabService(ICollaboratorService collabService) {
		this.collabService = collabService;
	}
	
	/**
	 * Set the profileService value
	 * @param profileService the profileService to set
	 */
	public void setProfileService(IProfileService profileService) {
		this.profileService = profileService;
	}
	
	/**
	 * Set the collabForm value
	 * @param collabForm the collabForm to set
	 */
	public void setCollabForm(CollaboratorForm collabForm) {
		this.collabForm = collabForm;
	}

	/**
	 * Set the listSkill value
	 * @param listSkill the listSkill to set
	 */
	public void setListSkill(ListSkill listSkill) {
		this.listSkill = listSkill;
	}
	
	/**
	 * Set the save value
	 * @param save the save to set
	 */
	public void setSave(Button save) {
		this.save = save;
	}

	/**
	 * Set the cancel value
	 * @param cancel the cancel to set
	 */
	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}
	
	/**
	 * Set the addSkill value
	 * @param addSkill the addSkill to set
	 */
	public void setAddSkill(Button addSkill) {
		this.addSkill = addSkill;
	}
	
	/**
	 * Set the skillPanel value
	 * @param skillPanel the skillPanel to set
	 */
	public void setSkillPanel(Panel skillPanel) {
		this.skillPanel = skillPanel;
	}
	
	/**
	 * Set the listSkillPanel value
	 * @param listSkillPanel the listSkillPanel to set
	 */
	public void setListSkillPanel(Panel listSkillPanel) {
		this.listSkillPanel = listSkillPanel;
	}
	
	/**
	 * Set the skillLabel value
	 * @param skillLabel the skillLabel to set
	 */
	public void setSkillLabel(Label skillLabel) {
		this.skillLabel = skillLabel;
	}
	
	/**
	 * Set the addSkillPanel value
	 * @param addSkillPanel the addSkillPanel to set
	 */
	public void setAddSkillPanel(AddSkillPanel addSkillPanel) {
		this.addSkillPanel = addSkillPanel;
	}
	
	/**
	 * Set the bodyLayout value
	 * @param bodyLayout the bodyLayout to set
	 */
	public void setBodyLayout(VerticalLayout bodyLayout) {
		this.bodyLayout = bodyLayout;
	}

	/**
	 * Set the footerLayout value
	 * @param footerLayout the footerLayout to set
	 */
	public void setFooterLayout(HorizontalLayout footerLayout) {
		this.footerLayout = footerLayout;
	}
}
