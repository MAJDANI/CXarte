package com.novedia.talentmap.web.ui.collab;

import java.util.List;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IManagerService;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author e.moumbe
 *
 */
public class MonitoringCollabContent extends VerticalLayout {

	/** UID */
	private static final long serialVersionUID = 1L;

	/**
	 * Vaadin UI
	 */
	private ProfileCollabWindow profileCollabWindow;

	/**
	 * Vaadin Components
	 */
	private Table collabTable;
	private Label pageTitle;

	/**
	 * TalentMap Service
	 */
	private IManagerService managerService;
	private IProfileService profileService;

	/**
	 * Constants
	 */
	private Integer MANAGER_ID = 1;
	public static final String VISUALIZE_CV_NAME = "CV";
	public static final String VISUALIZE_MISSION_NAME = "Historique Mission";
	public static final String VISUALIZE_EA_NAME = "Historique EA";
	public static final String VISUALIZE_PROFILE_NAME = "Visualiser Profil";
	public static final String PAGE_TITLE = "Liste de vos Collaborateurs";

	/**
	 * Build the class MonitoringCollabContent.java
	 * 
	 * @param collabTable
	 */
	public MonitoringCollabContent(Table collabTable,
			IManagerService managerService, Label pageTitle,
			ProfileCollabWindow profileCollabWindow,
			IProfileService profileService) {
		super();
		this.collabTable = collabTable;
		this.managerService = managerService;
		this.profileService = profileService;
		this.pageTitle = pageTitle;
		this.profileCollabWindow = profileCollabWindow;

		mainBuild();
	}

	public void mainBuild() {

		setMargin(true);

		buildTitle();

		buildCollabTable();

		addComponent(this.collabTable);
	}

	public void buildTitle() {

		this.pageTitle.setCaption(PAGE_TITLE);
		this.pageTitle.setStyleName(TalentMapCSS.H2);
		addComponent(this.pageTitle);

	}

	public void addColumns() {

		this.collabTable.addContainerProperty("Prénom", String.class, null);
		this.collabTable.addContainerProperty("Nom", String.class, null);
		this.collabTable.addContainerProperty("Profil", String.class, null);
		this.collabTable.addContainerProperty("Statut", String.class, null);
		this.collabTable.addContainerProperty("Actions",
				HorizontalLayout.class, null);
	}
	
	public void fillCollabTable(List<Colleague> listCollaborator) throws Exception{
	
		int idTable = 1;
		for (Colleague collab : listCollaborator) {	
			Profile collabProfile = this.profileService.getProfile(collab.getProfileId());
			
			HorizontalLayout hLayoutButton = new HorizontalLayout();
			hLayoutButton.setSpacing(true);
			hLayoutButton.setMargin(true);
			Button visualizeCV = buildButton(new Button(VISUALIZE_CV_NAME));
			Button visualizeMission = buildButton(new Button(
					VISUALIZE_MISSION_NAME));
			Button visualizeEA = buildButton(new Button(VISUALIZE_EA_NAME));
			Button visualizeProfile = buildButton(new Button(
					VISUALIZE_PROFILE_NAME));

			// Set button date to collaborator id
			visualizeCV.setData(collab.getId());
			visualizeMission.setData(collab.getId());
			visualizeEA.setData(collab.getId());
			visualizeProfile.setData(collab.getId());

			hLayoutButton.addComponent(visualizeCV);
			hLayoutButton.addComponent(visualizeMission);
			hLayoutButton.addComponent(visualizeEA);
			hLayoutButton.addComponent(visualizeProfile);

			this.collabTable.addItem(new Object[] { collab.getFirstName(),
					collab.getLastName(),
					collabProfile.getType(), "En mission",
					hLayoutButton }, idTable);

			idTable++;
		}
	}

	/**
	 * Build table that containt list of collab
	 */
	public void buildCollabTable() {

		// We create the columns
		addColumns();

		try {

			List<Colleague> listCollaborator = this.managerService.getAllColleagues(MANAGER_ID);
			fillCollabTable(listCollaborator);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private Button buildButton(Button button) {

		button.setStyleName(TalentMapCSS.BUTTON_NAVIGATION);

		if (button.getCaption().equals(VISUALIZE_CV_NAME)) {

			btnCVEvent(button);
		}

		if (button.getCaption().equals(VISUALIZE_EA_NAME)) {

			btnEAEvent(button);
		}

		if (button.getCaption().equals(VISUALIZE_MISSION_NAME)) {

			btnMissionEvent(button);
		}

		if (button.getCaption().equals(VISUALIZE_PROFILE_NAME)) {

			btnProfileEvent(button);
		}

		return button;
	}

	private void btnCVEvent(Button button) {

		button.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				Button btnListener = event.getButton();
				int idCollab = (Integer) btnListener.getData();

			}
		});
	}

	private void btnEAEvent(Button button) {

		button.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				Button btnListener = event.getButton();
				int idCollab = (Integer) btnListener.getData();

				CUtils.showMessage(String.valueOf(idCollab), getWindow());
			}
		});
	}

	private void btnMissionEvent(Button button) {

		button.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				Button btnListener = event.getButton();
				int idCollab = (Integer) btnListener.getData();

				CUtils.showMessage(String.valueOf(idCollab), getWindow());
			}
		});
	}

	private void btnProfileEvent(Button button) {
		System.out.println("MonitoringCollabContent.btnProfileEvent()");
		button.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				Button btnListener = event.getButton();
				int idCollab = (Integer) btnListener.getData();
				System.out.println("MonitoringCollabContent.btnProfileEvent() idCollab=" + idCollab);

				MonitoringCollabContent.this.profileCollabWindow
						.setCOLLAB_ID(idCollab);

				MonitoringCollabContent.this.profileCollabWindow.mainBuild();

				getWindow().addWindow(
						MonitoringCollabContent.this.profileCollabWindow);
			}
		});
	}

	/**
	 * Set the collabTable value
	 * 
	 * @param collabTable
	 *            the collabTable to set
	 */
	public void setCollabTable(Table collabTable) {
		this.collabTable = collabTable;
	}

	/**
	 * Set the skillService value
	 * 
	 * @param skillService
	 *            the skillService to set
	 */
	public void setCollabService(IManagerService managerService) {
		this.managerService = managerService;
	}

	/**
	 * Set the pageTitle value
	 * 
	 * @param pageTitle
	 *            the pageTitle to set
	 */
	public void setPageTitle(Label pageTitle) {
		this.pageTitle = pageTitle;
	}

	/**
	 * Set the profileCollabWindow value
	 * 
	 * @param profileCollabWindow
	 *            the profileCollabWindow to set
	 */
	public void setProfileCollabWindow(ProfileCollabWindow profileCollabWindow) {
		this.profileCollabWindow = profileCollabWindow;
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
}
