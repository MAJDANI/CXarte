package com.novedia.talentmap.web.ui.colleague.eae;

import com.novedia.talentmap.model.dto.EAEGeneralityDTO;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.services.impl.EAEService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

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
public class CurrentEAEContent extends VerticalLayout implements ClickListener,
		ValueChangeListener {
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

	private final String PANEL_LEFT_WIDTH = "150px";
	private final String PANEL_RIGHT_WIDTH = "600px";

	private Integer currentEAEId = null;

	/**
	 * Permits to know if the EAE to display is Open or Closed, in order to set
	 * readOnly fields or not
	 */
	private EAEConsultationMode currentMode;

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
		Integer colleagueId = TalentMapApplication.getCurrent().getAuthentication().getColleagueId();
		Integer currentEAEId = eaeService.getOpenEAEIdForColleague(colleagueId);

		if (currentEAEId == null) {
			buildViewCurrentEAEContentDoesntExists();
		} else {
			buildViewCurrentEAEContentExists(currentEAEId,	EAEConsultationMode.OPEN_COLLAB);
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
		hLayoutCurrentEAE.removeAllComponents();
		Label text = new Label("No current EAE");
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
	public void buildViewCurrentEAEContentExists(Integer currentEAEId, EAEConsultationMode mode) {
		removeAllComponents();
		this.currentEAEId = currentEAEId;
		this.currentMode = mode;

		hLayoutCurrentEAE.setSpacing(true);
		hLayoutCurrentEAE.removeAllComponents();
		buildButtons(mode);
		buildMenu(mode);
//		if(mode == EAEConsultationMode.CLOSED) {
//			panelRightCurrentEAE.removeAllComponents();
//		} else {
//			buildPanelRightContentEAEExists();
//		}
		buildPanelRightContentEAEExists();
		hLayoutCurrentEAE.addComponent(panelRightCurrentEAE);
		hLayoutCurrentEAE.setExpandRatio(panelRightCurrentEAE, 1.0f);
		addComponent(hLayoutCurrentEAE);
	}

	private void buildButtons(EAEConsultationMode mode) {

		generalityButton.setCaption(Constants.GENERALITY_EAE_BUTTON_LABEL);
		generalityButton.addStyleName(Reindeer.BUTTON_LINK);
		generalityButton.addStyleName("focus");
		generalityButton.addClickListener(this);

		resultsButton.setCaption(Constants.RESULTS_EAE_BUTTON_LABEL);
		resultsButton.addStyleName(Reindeer.BUTTON_LINK);
		resultsButton.removeStyleName("focus");
		resultsButton.addClickListener(this);

		if (mode == EAEConsultationMode.CLOSED) {
			objectivesButton.setCaption(Constants.OBJECTIVES_EAE_BUTTON_LABEL);
			objectivesButton.addStyleName(Reindeer.BUTTON_LINK);
			objectivesButton.removeStyleName("focus");
			objectivesButton.addClickListener(this);

			synthesisButton.setCaption(Constants.SYNTHESIS_EAE_BUTTON_LABEL);
			synthesisButton.addStyleName(Reindeer.BUTTON_LINK);
			synthesisButton.removeStyleName("focus");
			synthesisButton.addClickListener(this);
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
		if (mode == EAEConsultationMode.CLOSED) {
			menuContentCurrentEAE.addComponent(objectivesButton);
			menuContentCurrentEAE.addComponent(synthesisButton);
		}
		panelLeftCurrentEAE.setContent(menuContentCurrentEAE);
		hLayoutCurrentEAE.addComponent(panelLeftCurrentEAE);
	}

	private void buildPanelRightContentEAEExists() {
		panelRightCurrentEAE.removeAllComponents();

		EAEService eaeServicePlus = (EAEService) eaeService;
		EAEGeneralityDTO eaeGeneralityDTO = eaeServicePlus
				.getEAEGenerality(currentEAEId);

		panelRightCurrentEAE.addStyleName("panelRight");

		panelRightCurrentEAE
				.setCaption(Constants.CURRENT_EAE_TITLE_1
						+ eaeGeneralityDTO.getCollabLastAndFirstName()
						+ Constants.CURRENT_EAE_TITLE_2
						+ eaeGeneralityDTO.getEaeDate());

		panelRightCurrentEAE.setContent(currentEAEDetailedContent
				.buildViewEAEGenerality(this.currentEAEId, this.currentMode));
		panelRightCurrentEAE.setWidth(PANEL_RIGHT_WIDTH);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		panelRightCurrentEAE.removeAllComponents();
		if (event.getButton().equals(generalityButton)) {
			generalityButton.addStyleName("focus");
			resultsButton.removeStyleName("focus");
			if (currentMode == EAEConsultationMode.CLOSED) {
				objectivesButton.removeStyleName("focus");
				synthesisButton.removeStyleName("focus");
			}
			panelRightCurrentEAE
					.setContent(currentEAEDetailedContent
							.buildViewEAEGenerality(this.currentEAEId,
									this.currentMode));

		} else if (event.getButton().equals(resultsButton)) {
			generalityButton.removeStyleName("focus");
			resultsButton.addStyleName("focus");
			if (currentMode == EAEConsultationMode.CLOSED) {
				objectivesButton.removeStyleName("focus");
				synthesisButton.removeStyleName("focus");
			}
			panelRightCurrentEAE.setContent(currentEAEDetailedContent
					.buildViewEAEResults(currentEAEId));

		} else if (event.getButton().equals(objectivesButton)) {
			generalityButton.removeStyleName("focus");
			resultsButton.removeStyleName("focus");
			if (currentMode == EAEConsultationMode.CLOSED) {
				objectivesButton.addStyleName("focus");
				synthesisButton.removeStyleName("focus");
			}
			panelRightCurrentEAE.setContent(null);
		} else if (event.getButton().equals(synthesisButton)) {
			generalityButton.removeStyleName("focus");
			resultsButton.removeStyleName("focus");
			if (currentMode == EAEConsultationMode.CLOSED) {
				objectivesButton.removeStyleName("focus");
				synthesisButton.addStyleName("focus");
			}
			panelRightCurrentEAE.setContent(null);
		}

	}

	@Override
	public void valueChange(ValueChangeEvent event) {
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
