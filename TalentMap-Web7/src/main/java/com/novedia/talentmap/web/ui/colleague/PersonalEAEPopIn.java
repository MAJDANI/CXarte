package com.novedia.talentmap.web.ui.colleague;

import com.novedia.talentmap.web.ui.colleague.eae.CurrentEAEContent;
import com.novedia.talentmap.web.ui.colleague.eae.HistoryEAEContent;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

/**
 * La PopIn qui permet la gestion des EAE personnels. Cette PopIn contient un
 * menu à gauche avec 2 liens et un panel de droite qui contient les
 * informations à afficher. Dans le menu de gauche, le premier lien permet
 * d'afficher les données de l'EAE en cours et l'autre permet d'afficher le
 * tableau contenant l'historique des EAE.
 * 
 * @author v.guillemain
 * 
 */
@SuppressWarnings("serial")
public class PersonalEAEPopIn extends Window implements ClickListener {

	/**
	 * Layout Principal de la PopIn, contenant un panel de gauche
	 * (panelLeftPersoEAE) et un panel de droite (panelRightPersoEAE)
	 */
	private HorizontalLayout hLayoutPersoEAE;

	/**
	 * Panel de Gauche contenant le menu (menuContentPersoEAE)
	 */
	private Panel panelLeftPersoEAE;

	/**
	 * Menu de Gauche contenant deux boutons-liens, un vers l'EAE en cours
	 * (ouvert par défaut), l'autre vers l'historique des EAE
	 */
	private VerticalLayout menuContentPersoEAE;

	/**
	 * Premier bouton-lien du menu de gauche permettant d'afficher l'EAE en
	 * cours du collaborateur
	 */
	private Button currentEAEButton;

	/**
	 * Second bouton-lien du menu de gauche permettant d'afficher l'historique
	 * des EAE du collaborateur
	 */
	private Button historyEAEButton;

	/**
	 * Panel de droite contenant les données à afficher : soit l'EAE en cours,
	 * soit l'historique des EAE
	 */
	private Panel panelRightPersoEAE;

	/**
	 * Contenu affiché dans le Panel de droite : EAE courant
	 */
	private CurrentEAEContent currentEAEContent;

	/**
	 * Contenu affiché dans le Panel de droite : historique des EAE
	 */
	private HistoryEAEContent historyEAEContent;

	/**
	 * Taille en largeur du panel de gauche
	 */
	private final String PANEL_LEFT_WIDTH = "200px";
	/**
	 * Taille en largeur du panel de droite
	 */
	private final String PANEL_RIGHT_WIDTH = "800px";

	/**
	 * Default constructor
	 */
	public PersonalEAEPopIn() {
		super();
		setCaption(Constants.PERSONAL_EAE_POP_IN_TITLE);
		setModal(true);
	}

	/**
	 * Build PersonalEAEPopIn View
	 * 
	 * @return Window
	 */
	public Window buildPersonalEAEPopIn() {
		removeAllComponents();
		hLayoutPersoEAE.setSpacing(true);
		hLayoutPersoEAE.removeAllComponents();
		buildButtons();
		buildMenu();
		buildPanelRightContent();
		hLayoutPersoEAE.addComponent(panelRightPersoEAE);
		hLayoutPersoEAE.setExpandRatio(panelRightPersoEAE, 1.0f);
		addComponent(hLayoutPersoEAE);
		return this;
	}

	private void buildPanelRightContent() {
		panelRightPersoEAE.removeAllComponents(); // TODO : Vérifier si c'est utile
		panelRightPersoEAE.addStyleName("panelRight");
		panelRightPersoEAE.setContent(currentEAEContent.buildViewCurrentEAEContent());
		panelRightPersoEAE.setCaption("Current EAE Content");
		panelRightPersoEAE.setWidth(PANEL_RIGHT_WIDTH);
	}

	private void buildButtons() {

		currentEAEButton.setCaption(Constants.CURRENT_EAE_BUTTON_LABEL);
		currentEAEButton.addStyleName(Reindeer.BUTTON_LINK);
		currentEAEButton.addStyleName("focus");
		currentEAEButton.addClickListener(this);

		historyEAEButton.setCaption(Constants.HISTORY_EAE_BUTTON_LABEL);
		historyEAEButton.addStyleName(Reindeer.BUTTON_LINK);
		historyEAEButton.addClickListener(this);

	}

	private void buildMenu() {
		panelLeftPersoEAE.setWidth(PANEL_LEFT_WIDTH);
		panelLeftPersoEAE.addStyleName("panelLeft");
		menuContentPersoEAE.setSpacing(true);
		menuContentPersoEAE.setMargin(true);
		menuContentPersoEAE.addComponent(currentEAEButton);
		menuContentPersoEAE.addComponent(historyEAEButton);
		panelLeftPersoEAE.setContent(menuContentPersoEAE);
		hLayoutPersoEAE.addComponent(panelLeftPersoEAE);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		panelRightPersoEAE.removeAllComponents();
		if (event.getButton().equals(currentEAEButton)) {
			currentEAEButton.addStyleName("focus");
			historyEAEButton.removeStyleName("focus");
			panelRightPersoEAE.setContent(currentEAEContent
					.buildViewCurrentEAEContent());
			panelRightPersoEAE.setCaption("Current EAE Content");

		} else if (event.getButton().equals(historyEAEButton)) {
			currentEAEButton.removeStyleName("focus");
			historyEAEButton.addStyleName("focus");
			panelRightPersoEAE.setContent(historyEAEContent
					.buildViewHistoryEAEContent());
			panelRightPersoEAE.setCaption("History EAE Content");
		}
	}


	/**
	 * @return the currentEAEButton
	 */
	public Button getCurrentEAEButton() {
		return currentEAEButton;
	}

	/**
	 * @param currentEAEButton
	 *            the currentEAEButton to set
	 */
	public void setCurrentEAEButton(Button currentEAEButton) {
		this.currentEAEButton = currentEAEButton;
	}

	/**
	 * @return the historyEAEButton
	 */
	public Button getHistoryEAEButton() {
		return historyEAEButton;
	}

	/**
	 * @param historyEAEButton
	 *            the historyEAEButton to set
	 */
	public void setHistoryEAEButton(Button historyEAEButton) {
		this.historyEAEButton = historyEAEButton;
	}

	/**
	 * @return the currentEAEContent
	 */
	public CurrentEAEContent getCurrentEAEContent() {
		return currentEAEContent;
	}

	/**
	 * @param currentEAEContent
	 *            the currentEAEContent to set
	 */
	public void setCurrentEAEContent(CurrentEAEContent currentEAEContent) {
		this.currentEAEContent = currentEAEContent;
	}

	/**
	 * @return the hLayoutPersoEAE
	 */
	public HorizontalLayout gethLayoutPersoEAE() {
		return hLayoutPersoEAE;
	}

	/**
	 * @param hLayoutPersoEAE
	 *            the hLayoutPersoEAE to set
	 */
	public void sethLayoutPersoEAE(HorizontalLayout hLayoutPersoEAE) {
		this.hLayoutPersoEAE = hLayoutPersoEAE;
	}

	/**
	 * @return the panelLeftPersoEAE
	 */
	public Panel getPanelLeftPersoEAE() {
		return panelLeftPersoEAE;
	}

	/**
	 * @param panelLeftPersoEAE
	 *            the panelLeftPersoEAE to set
	 */
	public void setPanelLeftPersoEAE(Panel panelLeftPersoEAE) {
		this.panelLeftPersoEAE = panelLeftPersoEAE;
	}

	/**
	 * @return the menuContentPersoEAE
	 */
	public VerticalLayout getMenuContentPersoEAE() {
		return menuContentPersoEAE;
	}

	/**
	 * @param menuContentPersoEAE
	 *            the menuContentPersoEAE to set
	 */
	public void setMenuContentPersoEAE(VerticalLayout menuContentPersoEAE) {
		this.menuContentPersoEAE = menuContentPersoEAE;
	}

	/**
	 * @return the panelRightPersoEAE
	 */
	public Panel getPanelRightPersoEAE() {
		return panelRightPersoEAE;
	}

	/**
	 * @param panelRightPersoEAE
	 *            the panelRightPersoEAE to set
	 */
	public void setPanelRightPersoEAE(Panel panelRightPersoEAE) {
		this.panelRightPersoEAE = panelRightPersoEAE;
	}

	/**
	 * @return the historyEAEContent
	 */
	public HistoryEAEContent getHistoryEAEContent() {
		return historyEAEContent;
	}

	/**
	 * @param historyEAEContent
	 *            the historyEAEContent to set
	 */
	public void setHistoryEAEContent(HistoryEAEContent historyEAEContent) {
		this.historyEAEContent = historyEAEContent;
	}

}
