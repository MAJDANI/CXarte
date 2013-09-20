package com.novedia.talentmap.web.ui.colleague.eae;

import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.EAEConsultationMode;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Classe permettant l'affichage du tableau d l'historique des EAE du
 * collaborateur connecté. Ce tableau contient tous les EAE du collaborateur,
 * avec leur date et leur état (Ouvert, validé, Fermé).
 * 
 * @author v.guillemain
 * 
 */
@SuppressWarnings("serial")
public class HistoryEAEContent extends VerticalLayout implements ClickListener,
		ValueChangeListener {

	private Panel listEAEPanel;

	/**
	 * Liste historique des EAE du collaborateur
	 */
	private ListEAEDTO listEAE;

	/**
	 * Contenu d'EAE affcihé quand on clique sur un des éléments de la liste historique
	 */
	private CurrentEAEContent historicalEAEContent;


	/**
	 * Default constructor
	 */
	public HistoryEAEContent() {
		super();
		setSpacing(true);
		addStyleName("TODO");
	}

	/**
	 * Build colleague's EAE view
	 * 
	 * @return VerticalLayout
	 */
	public VerticalLayout buildViewHistoryEAEContent() {
		removeAllComponents();
		buildListEAEPanel();
		listEAEPanel.addStyleName("listEAEPanel");
		addComponent(listEAEPanel);
		return this;
	}

	/**
	 * Build colleague's EAE view
	 * 
	 * @return VerticalLayout
	 */
	public VerticalLayout buildViewHistoryEAEContentDetail(Integer historicEAEId) {
		removeAllComponents();
		historicalEAEContent.buildViewCurrentEAEContentExists(historicEAEId, EAEConsultationMode.CLOSED);
		addComponent(historicalEAEContent);
		return this;
	}

	/**
	 * Build list EAE panel
	 */
	private void buildListEAEPanel() {

		listEAE.fillAllColleagueEAE();
		listEAE.addValueChangeListener(this);
		listEAE.addStyleName("table");
		listEAEPanel.addStyleName("listEAEPanel");
		if (listEAE.size() > 0) {
			listEAEPanel.removeAllComponents();
			listEAEPanel.addComponent(listEAE);
			listEAEPanel.setVisible(true);
			if (listEAE.size() < Constants.NB_ROWS_DEFAULT) {
				listEAE.setPageLength(listEAE.size());
			} else {
				listEAE.setPageLength(Constants.NB_ROWS_DEFAULT);
			}
		} else {
			listEAEPanel.setVisible(false);
		}

	}

	@Override
	public void buttonClick(ClickEvent event) {
		//
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		EAEForSynthesisDTO selectedEAE = (EAEForSynthesisDTO) listEAE
				.getValue();
		if (selectedEAE != null) {
			buildViewHistoryEAEContentDetail(selectedEAE.getId());
		} 
	}

	/**
	 * @return the listEAEPanel
	 */
	public Panel getListEAEPanel() {
		return listEAEPanel;
	}

	/**
	 * @param listEAEPanel
	 *            the listEAEPanel to set
	 */
	public void setListEAEPanel(Panel listEAEPanel) {
		this.listEAEPanel = listEAEPanel;
	}

	/**
	 * @return the listEAE
	 */
	public ListEAEDTO getListEAE() {
		return listEAE;
	}

	/**
	 * @param listEAE
	 *            the listEAE to set
	 */
	public void setListEAE(ListEAEDTO listEAE) {
		this.listEAE = listEAE;
	}

	/**
	 * @return the historicalEAEContent
	 */
	public CurrentEAEContent getHistoricalEAEContent() {
		return historicalEAEContent;
	}

	/**
	 * @param historicalEAEContent the historicalEAEContent to set
	 */
	public void setHistoricalEAEContent(CurrentEAEContent historicalEAEContent) {
		this.historicalEAEContent = historicalEAEContent;
	}

}
