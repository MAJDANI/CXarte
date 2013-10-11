package com.novedia.talentmap.web.ui.colleague.eae;

import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.dto.EAEGeneralityDTO;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.services.impl.EAEService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.ui.colleague.PersonalEAEPopIn;
import com.novedia.talentmap.web.utils.CUtils;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.EAEConsultationMode;
import com.novedia.talentmap.web.utils.ProfilConnectedEnum;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Partie d'écran dédiée à l'affichage des informations d'un EAE. Soit on
 * affiche "Pas d'EAE ouvert en cours", soit on affiche toutes les informations
 * relatives à l'EAE en cours : à ce moment le panel se redécoupe en 2 parties :
 * un menu à gauche pour chacune des 4 parties de l'EAE (Généralités, bilan,
 * objectifs et synthese), et la partie de droite les informations détaillées d
 * la partie demandée.
 * 
 * @author v.guillemain
 * 
 */
@SuppressWarnings("serial")
public class CurrentEAEContent extends VerticalLayout implements ClickListener {
	/**
	 * TalentMap service
	 */
	private IEAEService eaeService;

	/**
	 * Layout Principal de l'objet visuel courant. Ce Layout sera soit enrichi
	 * d'un Text mentionnant l'absence d'EAE en cours, soit enrichi de 2 panels,
	 * un de gauche pour accuillir un sous-menu(panelLeftCurrentEAE) et un de
	 * droite pour afficher les informations sélectionnées dans le sous-menu
	 * précédemment cité (panelRightCurrentEAE).
	 */
	private HorizontalLayout hLayoutCurrentEAE;

	/**
	 * Panel de gauche contruit pour l'afichage de l'EAE en cours. Ce panel
	 * contiendra un menu (menuContentCurrentEAE)
	 */
	private Panel panelLeftCurrentEAE;

	/**
	 * Menu du panel de Gauche. Contient 4 boutons-liens : generalityButton,
	 * resultsButton, objectivesButton, synthesisButton
	 */
	private VerticalLayout menuContentCurrentEAE;

	/**
	 * Bouton lien permettant d'afficher dans le panel de droite la partie
	 * Généralités de l'EAE en cours.
	 */
	private Button generalityButton;

	/**
	 * Bouton lien permettant d'afficher dans le panel de droite la partie Bilan
	 * de l'EAE en cours.
	 */
	private Button resultsButton;

	/**
	 * Bouton lien permettant d'afficher dans le panel de droite la partie
	 * Objectifs de l'EAE en cours.
	 */
	private Button objectivesButton;

	/**
	 * Bouton lien permettant d'afficher dans le panel de droite la partie
	 * Sunthese de l'EAE en cours.
	 */
	private Button synthesisButton;

	/**
	 * Panel de droite contruit pour l'afichage des informations détaillées de
	 * l'EAE en cours. Le contenu variera en fonction de la sélection faite par
	 * l'utilisateur dans le menu de gauche.
	 */
	private Panel panelRightCurrentEAE;

	/**
	 * Objet contenu dans le Panel de droite, qui gèrera les différents contenus
	 * des 4 parties de l'EAE en cours
	 */
	private CurrentEAEDetailedContent currentEAEDetailedContent;

	private final String PANEL_LEFT_WIDTH = "120px";
	private final String PANEL_RIGHT_WIDTH = "680px";

	private Integer currentEAEId = null;

	/**
	 * Permits to know if the EAE to display is Open or Closed, in order to set
	 * readOnly fields or not
	 */
	private EAEConsultationMode currentMode;

	/**
	 * Permits to know which part of the EAE is actually displayed : Generality,
	 * Results, Objectives or Synthesis
	 */
	private Button currentButtonActivated = new Button();

	private Integer nbEmptyFields = Integer.MAX_VALUE;
	private Integer previousEaeId;

	/**
	 * These elements are not injected by Spring
	 */
	private Button validateEAEButton = new Button();
	private Button closeEAEButton = new Button();
	private Button buttonYesValidate = new Button();
	private Button buttonNoValidate = new Button();
	private Button buttonYesClose = new Button();
	private Button buttonNoClose = new Button();
	private Window windowConfirmValidate = new Window();
	private Window windowConfirmClose = new Window();

	private HistoryEAEContent histoEAEContentCalling = new HistoryEAEContent();
	
	private ResourceBundle resourceBundle;

	private void initResourceBundle() {
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(
				PropertiesFile.TALENT_MAP_PROPERTIES, locale);
	}

	/**
	 * Default constructor
	 */
	public CurrentEAEContent() {
		super();
		setSpacing(true);
		addStyleName("TODO");
	}

	/**
	 * Build colleague's current EAE content view
	 * 
	 * @return VerticalLayout
	 */
	public VerticalLayout buildViewCurrentEAEContent() {
		removeAllComponents();
		// Check if the colleague has a current EAE Open
		// initResourceBundle();
		Integer colleagueId = TalentMapApplication.getCurrent()
				.getAuthentication().getColleagueId();
		Integer currentEAEId = eaeService.getOpenEAEIdForColleague(colleagueId);
		hLayoutCurrentEAE.setId(ComponentsId.EAE_HLAYOUT_CURRENT_EAE_ID);
		if (currentEAEId == null) {
			buildViewCurrentEAEContentDoesntExists();
		} else {
			buildViewCurrentEAEContentExists(currentEAEId,
					EAEConsultationMode.OPEN_COLLAB,
					ProfilConnectedEnum.COLLEAGUE, null);
		}
		return this;
	}

	/**
	 * Builds an empty view in case there is no current EAE
	 * 
	 * @return VerticalLayout
	 */
	public void buildViewCurrentEAEContentDoesntExists() {
		removeAllComponents();
		initResourceBundle();
		hLayoutCurrentEAE.removeAllComponents();
		Label text = new Label(
				resourceBundle.getString("eae.no.current.eae.message"));
		hLayoutCurrentEAE.addComponent(text);
		hLayoutCurrentEAE.setExpandRatio(text, 1.0f);
		addComponent(hLayoutCurrentEAE);
	}

	/**
	 * Build HLayout and its components (panel left and right) that displays
	 * entire EAE informations
	 * 
	 * @return Window
	 */
	public void buildViewCurrentEAEContentExists(Integer currentEAEId,
			EAEConsultationMode mode, ProfilConnectedEnum profilConnected, HistoryEAEContent histoEAEContentCalling) {
		removeAllComponents();
		initResourceBundle();
		this.histoEAEContentCalling = histoEAEContentCalling;
		this.currentEAEId = currentEAEId;
		this.currentMode = mode;
		if (mode == EAEConsultationMode.OPEN_COLLAB) {
			previousEaeId= eaeService.getPreviousEAEID(currentEAEId);
			if (previousEaeId != null) {
				nbEmptyFields = eaeService.getNbEmptyFieldsByEAE(currentEAEId);
			} else {
				Integer salary = eaeService.getSalaryByEAEID(currentEAEId);
				if (salary == null) nbEmptyFields = 1;
				else nbEmptyFields = 0;
			}
		}
		hLayoutCurrentEAE.setSpacing(true);
		hLayoutCurrentEAE.removeAllComponents();
		buildButtons(mode);
		buildMenu(mode);

		buildPanelRightContentEAEExists(profilConnected);
		hLayoutCurrentEAE.addComponent(panelRightCurrentEAE);
		hLayoutCurrentEAE.setExpandRatio(panelRightCurrentEAE, 1.0f);
		addComponent(hLayoutCurrentEAE);
	}

	private void buildButtons(EAEConsultationMode mode) {

		generalityButton.setCaption(resourceBundle
				.getString("eae.generality.button.label"));
		generalityButton.addClickListener(this);

		resultsButton.setCaption(resourceBundle
				.getString("eae.results.button.label"));
		resultsButton.addClickListener(this);

		validateEAEButton.setCaption(resourceBundle
				.getString("current.eae.button.validate.caption"));
		validateEAEButton.addStyleName("styleButton");
		validateEAEButton.addClickListener(this);

		closeEAEButton.setCaption(resourceBundle
				.getString("current.eae.button.close.caption"));
		closeEAEButton.addStyleName("styleButton");
		closeEAEButton.addClickListener(this);

		if (mode == EAEConsultationMode.CLOSED
				|| mode == EAEConsultationMode.VALIDATED_MANAGER) {
			objectivesButton.setCaption(resourceBundle
					.getString("eae.objectives.button.label"));
			objectivesButton.addClickListener(this);

			synthesisButton.setCaption(resourceBundle
					.getString("eae.synthesis.button.label"));
			synthesisButton.addClickListener(this);
			CUtils.decorateButton(generalityButton, resultsButton,
					objectivesButton, synthesisButton);
			CUtils.decorateButtonAsLink(generalityButton, resultsButton,
					objectivesButton, synthesisButton);
		} else {
			CUtils.decorateButton(generalityButton, resultsButton);
			CUtils.decorateButtonAsLink(generalityButton, resultsButton);
		}
	}

	private void buildMenu(EAEConsultationMode mode) {
		panelLeftCurrentEAE.removeAllComponents();
		panelLeftCurrentEAE.setWidth(PANEL_LEFT_WIDTH);
		panelLeftCurrentEAE.addStyleName("panelLeft");
		menuContentCurrentEAE.setSpacing(true);
		menuContentCurrentEAE.setMargin(true);
		menuContentCurrentEAE.addComponent(generalityButton);
		menuContentCurrentEAE.addComponent(resultsButton);
		menuContentCurrentEAE.addComponent(validateEAEButton);

		if (mode == EAEConsultationMode.CLOSED
				|| mode == EAEConsultationMode.VALIDATED_MANAGER) {
			menuContentCurrentEAE.addComponent(objectivesButton);
			menuContentCurrentEAE.addComponent(synthesisButton);
		}
		menuContentCurrentEAE.addComponent(closeEAEButton);
		if (mode == EAEConsultationMode.OPEN_COLLAB && nbEmptyFields == 0) {
			validateEAEButton.setVisible(true);
		} else {
			validateEAEButton.setVisible(false);
		}
		if (mode == EAEConsultationMode.VALIDATED_MANAGER) {
			closeEAEButton.setVisible(true);
		} else {
			closeEAEButton.setVisible(false);
		}
		panelLeftCurrentEAE.setContent(menuContentCurrentEAE);
		hLayoutCurrentEAE.addComponent(panelLeftCurrentEAE);
	}

	private void buildPanelRightContentEAEExists(
			ProfilConnectedEnum profilConnected) {
		panelRightCurrentEAE.removeAllComponents();

		panelRightCurrentEAE.setId(ComponentsId.EAE_PANEL_RIGHT_CURRENT_EAE_ID);

		EAEService eaeServicePlus = (EAEService) eaeService;
		EAEGeneralityDTO eaeGeneralityDTO = eaeServicePlus
				.getEAEGenerality(currentEAEId);

		panelRightCurrentEAE.addStyleName("panelRight");

		String caption;
		if (profilConnected == ProfilConnectedEnum.COLLEAGUE) {
			caption = resourceBundle.getString("current.eae.title.part0")
					+ eaeGeneralityDTO.getEaeDate();
		} else {
			caption = resourceBundle.getString("current.eae.title.part1")
					+ eaeGeneralityDTO.getCollabLastAndFirstName() + " "
					+ resourceBundle.getString("current.eae.title.part2")
					+ eaeGeneralityDTO.getEaeDate();
		}

		panelRightCurrentEAE.setCaption(caption);

		panelRightCurrentEAE.setContent(currentEAEDetailedContent
				.buildViewEAEGenerality(this.currentEAEId, this.currentMode,
						this));
		panelRightCurrentEAE.setWidth(PANEL_RIGHT_WIDTH);
		panelRightCurrentEAE.setSizeFull();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		panelRightCurrentEAE.removeAllComponents();
		if (event.getButton().equals(generalityButton)) {
			currentButtonActivated = generalityButton;
			if (currentMode == EAEConsultationMode.CLOSED
					|| currentMode == EAEConsultationMode.VALIDATED_MANAGER) {
				CUtils.decorateButton(generalityButton, resultsButton,
						objectivesButton, synthesisButton);
			} else {
				CUtils.decorateButton(generalityButton, resultsButton);
			}
			panelRightCurrentEAE.setContent(currentEAEDetailedContent
					.buildViewEAEGenerality(this.currentEAEId,
							this.currentMode, this));

		} else if (event.getButton().equals(resultsButton)) {
			currentButtonActivated = resultsButton;
			if (currentMode == EAEConsultationMode.CLOSED
					|| currentMode == EAEConsultationMode.VALIDATED_MANAGER) {
				CUtils.decorateButton(resultsButton, generalityButton,
						objectivesButton, synthesisButton);
			} else {
				CUtils.decorateButton(resultsButton, generalityButton);
			}
			panelRightCurrentEAE.setContent(currentEAEDetailedContent
					.buildViewEAEResults(currentEAEId, this.currentMode, this));

		} else if (event.getButton().equals(objectivesButton)) {
			currentButtonActivated = objectivesButton;
			CUtils.decorateButton(objectivesButton, resultsButton,
					generalityButton, synthesisButton);
			panelRightCurrentEAE.setContent(currentEAEDetailedContent
					.buildViewEAEObjectives(currentEAEId, this.currentMode));
		} else if (event.getButton().equals(synthesisButton)) {
			currentButtonActivated = synthesisButton;
			CUtils.decorateButton(synthesisButton, objectivesButton,
					resultsButton, generalityButton);
			panelRightCurrentEAE.setContent(currentEAEDetailedContent
					.buildViewEAESynthesis(currentEAEId, this.currentMode));
		}

		else if (event.getButton().equals(validateEAEButton)) {
			buildConfirmValidateWindow();
			this.getUI().addWindow(windowConfirmValidate);
			refreshCurrentTab();
		} else if (event.getButton().equals(closeEAEButton)) {
			buildConfirmCloseWindow();
			this.getUI().addWindow(windowConfirmClose);
			refreshCurrentTab();
		}

		else if (event.getButton().equals(buttonNoValidate)) {
			windowConfirmValidate.close();
			refreshCurrentTab();
		} else if (event.getButton().equals(buttonYesValidate)) {
			eaeService.validateEAEById(currentEAEId);
			refreshCurrentTab();
			Notification.show("EAE validé");
			windowConfirmValidate.close();
			buildViewCurrentEAEContentDoesntExists();
		} else if (event.getButton().equals(buttonNoClose)) {
			windowConfirmClose.close();
			refreshCurrentTab();
		} else if (event.getButton().equals(buttonYesClose)) {
			eaeService.closeEAEById(currentEAEId);
			Notification.show(resourceBundle.getString("notification.eae.closed"));
			windowConfirmClose.close();
			histoEAEContentCalling.closeWindowDetailEAE();
			histoEAEContentCalling.refreshListHistoEAE();
		}
	}
	
	/**
	 * When opening the confirmation window, the content of panel Right
	 * vanishes => whe recall the diplay here
	 */
	private void refreshCurrentTab() {
		if (resultsButton == currentButtonActivated) {
			panelRightCurrentEAE.setContent(currentEAEDetailedContent
					.buildViewEAEResults(currentEAEId, this.currentMode, this));
		} else  if (objectivesButton == currentButtonActivated) {
			panelRightCurrentEAE.setContent(currentEAEDetailedContent
					.buildViewEAEObjectives(currentEAEId, this.currentMode));
		} else if (synthesisButton == currentButtonActivated) {
			panelRightCurrentEAE.setContent(currentEAEDetailedContent
					.buildViewEAESynthesis(currentEAEId, this.currentMode));
		} else {
			panelRightCurrentEAE.setContent(currentEAEDetailedContent
					.buildViewEAEGenerality(this.currentEAEId,
							this.currentMode, this));
		}
		
	}

	public void refreshObjectives() {
		CUtils.decorateButton(objectivesButton, resultsButton,
				generalityButton, synthesisButton);
		panelRightCurrentEAE.setContent(currentEAEDetailedContent
				.buildViewEAEObjectives(currentEAEId, this.currentMode));
	}

	private void buildConfirmValidateWindow() {
		windowConfirmValidate.removeAllComponents();
		windowConfirmValidate.setCaption(resourceBundle
				.getString("window.confirm.validate.title"));
		windowConfirmValidate.center();
		windowConfirmValidate.setModal(true);
		windowConfirmValidate.setReadOnly(true);
		Label confirmValidateLabel = new Label(
				resourceBundle.getString("msg.confirm.validate.eae"));
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);
		hLayout.addStyleName("containerButton");
		buttonYesValidate.setCaption(resourceBundle
				.getString("yes.confirm.button.caption"));
		buttonYesValidate.addStyleName("styleButton");
		buttonYesValidate.addClickListener(this);
		buttonNoValidate.setCaption(resourceBundle
				.getString("no.confirm.button.caption"));
		buttonNoValidate.addStyleName("styleButton");
		buttonNoValidate.addClickListener(this);
		hLayout.addComponent(buttonYesValidate);
		hLayout.addComponent(buttonNoValidate);
		windowConfirmValidate.addComponent(confirmValidateLabel);
		windowConfirmValidate.addComponent(hLayout);
	}

	private void buildConfirmCloseWindow() {
		windowConfirmClose.removeAllComponents();
		windowConfirmClose.setCaption(resourceBundle
				.getString("window.confirm.close.title"));
		windowConfirmClose.center();
		windowConfirmClose.setModal(true);
		windowConfirmClose.setReadOnly(true);
		Label confirmCloseLabel = new Label(
				resourceBundle.getString("msg.confirm.close.eae"));
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);
		hLayout.addStyleName("containerButton");
		buttonYesClose.setCaption(resourceBundle
				.getString("yes.confirm.button.caption"));
		buttonYesClose.addStyleName("styleButton");
		buttonYesClose.addClickListener(this);
		buttonNoClose.setCaption(resourceBundle
				.getString("no.confirm.button.caption"));
		buttonNoClose.addStyleName("styleButton");
		buttonNoClose.addClickListener(this);
		hLayout.addComponent(buttonYesClose);
		hLayout.addComponent(buttonNoClose);
		windowConfirmClose.addComponent(confirmCloseLabel);
		windowConfirmClose.addComponent(hLayout);
	}

	public void refreshValidateButton() {
		if (currentMode == EAEConsultationMode.OPEN_COLLAB) {
			
			previousEaeId= eaeService.getPreviousEAEID(currentEAEId);
			if (previousEaeId != null) {
				nbEmptyFields = eaeService.getNbEmptyFieldsByEAE(currentEAEId);
			} else {
				Integer salary = eaeService.getSalaryByEAEID(currentEAEId);
				if (salary == null) nbEmptyFields = 1;
				else nbEmptyFields = 0;
			}
			if (nbEmptyFields == 0) {
				validateEAEButton.setVisible(true);
			} else {
				validateEAEButton.setVisible(false);
			}
		}
	}

	/**
	 * @return the generalityButton
	 */
	public Button getGeneralityButton() {
		return generalityButton;
	}

	/**
	 * @param generalityButton
	 *            the generalityButton to set
	 */
	public void setGeneralityButton(Button generalityButton) {
		this.generalityButton = generalityButton;
	}

	/**
	 * @return the objectivesButton
	 */
	public Button getObjectivesButton() {
		return objectivesButton;
	}

	/**
	 * @param objectivesButton
	 *            the objectivesButton to set
	 */
	public void setObjectivesButton(Button objectivesButton) {
		this.objectivesButton = objectivesButton;
	}

	/**
	 * @return the resultsButton
	 */
	public Button getResultsButton() {
		return resultsButton;
	}

	/**
	 * @param resultsButton
	 *            the resultsButton to set
	 */
	public void setResultsButton(Button resultsButton) {
		this.resultsButton = resultsButton;
	}

	/**
	 * @return the synthesisButton
	 */
	public Button getSynthesisButton() {
		return synthesisButton;
	}

	/**
	 * @param synthesisButton
	 *            the synthesisButton to set
	 */
	public void setSynthesisButton(Button synthesisButton) {
		this.synthesisButton = synthesisButton;
	}

	/**
	 * @return the hLayoutCurrentEAE
	 */
	public HorizontalLayout gethLayoutCurrentEAE() {
		return hLayoutCurrentEAE;
	}

	/**
	 * @param hLayoutCurrentEAE
	 *            the hLayoutCurrentEAE to set
	 */
	public void sethLayoutCurrentEAE(HorizontalLayout hLayoutCurrentEAE) {
		this.hLayoutCurrentEAE = hLayoutCurrentEAE;
	}

	/**
	 * @return the panelRightCurrentEAE
	 */
	public Panel getPanelRightCurrentEAE() {
		return panelRightCurrentEAE;
	}

	/**
	 * @param panelRightCurrentEAE
	 *            the panelRightCurrentEAE to set
	 */
	public void setPanelRightCurrentEAE(Panel panelRightCurrentEAE) {
		this.panelRightCurrentEAE = panelRightCurrentEAE;
	}

	/**
	 * @return the panelLeftCurrentEAE
	 */
	public Panel getPanelLeftCurrentEAE() {
		return panelLeftCurrentEAE;
	}

	/**
	 * @param panelLeftCurrentEAE
	 *            the panelLeftCurrentEAE to set
	 */
	public void setPanelLeftCurrentEAE(Panel panelLeftCurrentEAE) {
		this.panelLeftCurrentEAE = panelLeftCurrentEAE;
	}

	/**
	 * @return the menuContentCurrentEAE
	 */
	public VerticalLayout getMenuContentCurrentEAE() {
		return menuContentCurrentEAE;
	}

	/**
	 * @param menuContentCurrentEAE
	 *            the menuContentCurrentEAE to set
	 */
	public void setMenuContentCurrentEAE(VerticalLayout menuContentCurrentEAE) {
		this.menuContentCurrentEAE = menuContentCurrentEAE;
	}

	/**
	 * @return the eaeService
	 */
	public IEAEService getEaeService() {
		return eaeService;
	}

	/**
	 * @param eaeService
	 *            the eaeService to set
	 */
	public void setEaeService(IEAEService eaeService) {
		this.eaeService = eaeService;
	}

	/**
	 * @return the currentEAEDetailedContent
	 */
	public CurrentEAEDetailedContent getCurrentEAEDetailedContent() {
		return currentEAEDetailedContent;
	}

	/**
	 * @param currentEAEDetailedContent
	 *            the currentEAEDetailedContent to set
	 */
	public void setCurrentEAEDetailedContent(
			CurrentEAEDetailedContent currentEAEDetailedContent) {
		this.currentEAEDetailedContent = currentEAEDetailedContent;
	}

}
