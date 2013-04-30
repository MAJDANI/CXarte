package com.novedia.talentmap.web.ui.profile;

import java.util.Date;

import com.novedia.talentmap.model.dto.MissionDto;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.model.entity.UserNotification;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.INotificationService;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.services.impl.ColleagueService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Field;
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

@SuppressWarnings("serial")
public class ProfileCollaboratorContent extends VerticalLayout implements
		ClickListener {

	/**
	 * TalentMap Services
	 */
	private IColleagueService colleagueService;
	private IProfileService profileService;
	private INotificationService notificationService;
	/**
	 * All views
	 */
	private CollaboratorForm collabForm;

	private Authentication authentication;
	/**
	 * Buttons
	 */
	private Button save;
	private Button cancel;
	private Button edit;

	/**
	 * default constructor
	 */
	public ProfileCollaboratorContent() {
		super();
	}

	/**
	 * Build the ProfileCollaboratorContent
	 * 
	 * @return
	 */
	public VerticalLayout buildProfileColleagueContent() {
		removeAllComponents();
		collabForm.setAuthentication(getAuthentication());
		mainBuild();
		return this;
	}

	/**
	 * The main builder
	 * 
	 * @class ProfileView.java
	 */
	public void mainBuild() {

		// On ajoute le formulaire
		this.addComponent(this.collabForm.buildColleagueData());
		this.collabForm.setImmediate(true);
		this.collabForm.getFormCollaborator().setReadOnly(true);
		this.collabForm.getFormMission().setReadOnly(true);

		this.setMargin(true);
		this.setSizeFull();
		setImmediate(true);

		// Construction des boutons
		buildButtonLayout();

	}

	/**
	 * Build method for the Button Layout
	 * 
	 * @class ProfileView.java
	 * @return
	 */

	private void buildButtonLayout() {

		// layout pour les boutons
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);

		// Bouton "Editer"
		edit = new Button();
		edit.setCaption(ConstantsEnglish.ADMIN_DATA_EDIT_BUTTON);
		edit.addListener(this);
		buttonLayout.addComponent(edit);

		// Bouton "Enregistrer"
		save = new Button();
		save.setCaption(ConstantsEnglish.LABEL_SAVE_BUTTON);
		save.addListener(this);
		save.setEnabled(false);
		buttonLayout.addComponent(save);

		// Bouton "Annuler"
		cancel = new Button();
		cancel.setCaption(ConstantsEnglish.LABEL_CANCEL_BUTTON);
		cancel.addListener(this);
		buttonLayout.addComponent(cancel);

		// Footer layout
		HorizontalLayout footerLayout = new HorizontalLayout();

		footerLayout.setMargin(true);
		footerLayout.setSizeFull();
		footerLayout.setStyleName(TalentMapCSS.FOOTER_PROFILE);
		footerLayout.addComponent(buttonLayout);
		footerLayout.setComponentAlignment(buttonLayout,
				Alignment.MIDDLE_CENTER);

		addComponent(footerLayout);
	}

	/**
	 * Save the Collaborator Data : Les données administratives concernant le
	 * collaborateur dans la fiche Profil (pas les données relatives à la
	 * dernière mission en date)
	 * 
	 * @class ProfileColaboratorContent.java
	 * @return : int : code permettant de savoir si l'opération s'est bien
	 *         passée (int=1) ou pas (int=0)
	 */
	private int saveDataCollaborator() {
		try {
			this.collabForm.getFormCollaborator().commit();
			BeanItem<Colleague> collabItem = (BeanItem<Colleague>) this.collabForm
					.getFormCollaborator().getItemDataSource();
			Colleague collab = collabItem.getBean();
			CmNotification(collab);
			return this.colleagueService.saveColleague(collab);
		} catch (InvalidValueException invalidVE) {
			return 0;
		}
	}

	/**
	 * Save the Mission Data : Les données administratives concernant la
	 * dernière mission du collaborateur (pas les données "personnelles" du
	 * collaborateur)
	 * 
	 * @class ProfileColaboratorContent.java
	 * @return : int : code permettant de savoir si l'opération s'est bien
	 *         passée (int=1) ou pas (int=0)
	 */
	private int saveDataMission() {
		try {
			this.collabForm.getFormMission().commit();
			BeanItem<MissionDto> missionItem = (BeanItem<MissionDto>) this.collabForm
					.getFormMission().getItemDataSource();
			MissionDto mission = missionItem.getBean();
			return this.colleagueService.saveMission(mission);
		} catch (InvalidValueException invalidVE) {
			return 0;
		}

	}

	public String validateForm() {
		String message = null;
		try {
			this.collabForm.getFormCollaborator().validate();
		} catch (InvalidValueException e) {
			message = e.getMessage();
		}
		return message;
	}

	/**
	 * Controle la validité du champ email : Vérifie que ce champ n'est pas déjà
	 * en base pour un autre collaborateur
	 * 
	 * @return : true si le mail est valide (il peut être attribué), false sinon
	 */
	public boolean validateEmail() {
		Field fieldEMail = this.collabForm.getFormCollaborator().getField(
				ConstantsEnglish.REGISTRATION_EMAIL_FIELD);
		if (fieldEMail != null && fieldEMail.getValue() != "") {
			BeanItem<Colleague> collabItem = (BeanItem<Colleague>) this.collabForm
					.getFormCollaborator().getItemDataSource();
			Colleague colleague = collabItem.getBean();
			ColleagueService service = (ColleagueService) this.colleagueService;

			Integer nbColleagueFound = 0;
			try {
				nbColleagueFound = service.countMailForColleagueId(colleague);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (nbColleagueFound > 0) {
				String eMail = colleague.getEmail();
				String message = ConstantsEnglish.REGISTRATION_ERROR_EMAIL_EXISTS1
						+ eMail
						+ ConstantsEnglish.REGISTRATION_ERROR_EMAIL_EXISTS2;
				getWindow().showNotification(message);
				fieldEMail.focus();
				fieldEMail.setValue("");
				return false;
			}
		}
		return true;
	}

	/**
	 * Button Click Listener
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		Button button = event.getButton();

		// Save Button
		if (button.getCaption().equals(ConstantsEnglish.LABEL_SAVE_BUTTON)) {

			String message = validateForm();
			if (message == null) {
				if (validateEmail()) {
					saveDataCollaborator();
					// saveDataMission();
					this.collabForm.getFormCollaborator().setReadOnly(true);
					this.collabForm.getFormMission().setReadOnly(true);
					this.save.setEnabled(false);
					this.edit.setEnabled(true);
					this.cancel.setEnabled(false);
				}
			} else {
				getWindow().showNotification(message);
			}
		} else if (button.getCaption().equals(
				ConstantsEnglish.ADMIN_DATA_EDIT_BUTTON)) {
			this.collabForm.getFormCollaborator().setReadOnly(false);
			this.collabForm.getFormMission().setReadOnly(true);
			this.save.setEnabled(true);
			this.edit.setEnabled(false);
			this.cancel.setEnabled(true);

		} else if (button.getCaption().equals(
				ConstantsEnglish.LABEL_CANCEL_BUTTON)) {
			this.collabForm.refreshAllFormsToDefault();
			this.collabForm.getFormCollaborator().setReadOnly(true);
			this.collabForm.getFormMission().setReadOnly(true);
			this.save.setEnabled(false);
			this.edit.setEnabled(true);
			this.cancel.setEnabled(false);
		}
	}

	public void CmNotification(Colleague colleague) {

	
			Profile profile = profileService.getProfile(colleague.getProfileId());

			String comment = colleague.getFirstName() + " " + colleague.getLastName()
					+ " modified his job title to " + profile.getType() ;
			Date date = new Date();
			UserNotification notification = UserNotification.builder()
					.colleagueId(colleague.getId()).notes(comment)
					.date(date).build();
			this.notificationService.saveNotification(notification);
		
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
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
	 * Set the profileService value
	 * 
	 * @param profileService
	 *            the profileService to set
	 */
	public void setProfileService(IProfileService profileService) {
		this.profileService = profileService;
	}

	/**
	 * Set the notificationService value
	 * 
	 * @param notificationService
	 *            the notificatinService to set
	 */
	public void setNotificationService(INotificationService notificationService) {
		this.notificationService = notificationService;
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
	 * 
	 * @return
	 */
	public CollaboratorForm getCollabForm() {
		return collabForm;
	}

}
