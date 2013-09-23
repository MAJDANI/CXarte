package com.novedia.talentmap.web.ui.cm.eae;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.dto.EAEColleagueResumeForCMDTO;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.ui.colleague.eae.CurrentEAEContent;
import com.novedia.talentmap.web.ui.colleague.eae.HistoryEAEContent;
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;

/**
 * La PopIn qui permet la gestion des EAE des collaborateurs rattachés au CM.
 * 
 * 
 * @author v.guillemain
 * 
 */
@SuppressWarnings("serial")
public class CMEAEPopIn extends Window implements MouseEvents.ClickListener {

	private IEAEService eaeService;

	/**
	 * Layout Principal de la PopIn, contenant un panel de gauche
	 * (panelLeftPersoEAE) et un panel de droite (panelRightPersoEAE)
	 */
	private HorizontalLayout hLayoutCMEAE;

	private HistoryEAEContent historyEAEContent;

	/**
	 * Contenu affiché dans le Panel de droite : EAE courant
	 */
	private CurrentEAEContent currentEAEContent;

	private ResourceBundle resourceBundle;

	/**
	 * Default constructor
	 */
	public CMEAEPopIn() {
		super();
		setModal(true);
	}

	private void initResourceBundle() {
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(
				PropertiesFile.TALENT_MAP_PROPERTIES, locale);
	}

	/**
	 * Build PersonalEAEPopIn View
	 * 
	 * @return Window
	 */
	public Window buildCMEAEPopIn() {
		initResourceBundle();
		setCaption(resourceBundle.getString("cm.eae.pop.in.title"));

		removeAllComponents();
		hLayoutCMEAE.setSpacing(true);
		hLayoutCMEAE.removeAllComponents();
		buildColleaguesButtons();
		addComponent(hLayoutCMEAE);
		return this;
	}

	private void buildPanelRightContent() {
		
	}

	private void buildColleaguesButtons() {
		/**
		 * Id du manager connecté
		 */
    	int colleagueId = TalentMapApplication.getCurrent().getAuthentication().getColleagueId();
		List<EAEColleagueResumeForCMDTO> list = eaeService.getEAEColleagueResumeForCM(colleagueId);
		
		ThemeResource resourceBoy = new ThemeResource(Constants.IMG_NO_PHOTO_BOY);
		ThemeResource resourceGirl = new ThemeResource(Constants.IMG_NO_PHOTO_GIRL);

		for (EAEColleagueResumeForCMDTO eaDTO : list) {
			String name = eaDTO.getCollabFirstName() + " " + eaDTO.getCollabLastName();
			Image imageTest ;
			if("M".equals(eaDTO.getTitle().toUpperCase())) {
				imageTest = new Image( name, resourceBoy);
			} else {
				imageTest = new Image( name, resourceGirl);
			}
			imageTest.addClickListener(this);
			imageTest.setId(eaDTO.getIdColleague().toString());
			String date = resourceBundle.getString("cm.eae.date.caption") + eaDTO.getEaeDate();
			imageTest.setAlternateText(date);
			imageTest.setDescription(date);
			
			Integer state = eaDTO.getEaeStateId();
			if(3 == state) {
				imageTest.addStyleName("borderRed");
			}
			if(2 == state) {
				imageTest.addStyleName("borderOrange");
			}
			if(1 == state) {
				imageTest.addStyleName("borderGreen");
			}
			hLayoutCMEAE.addComponent(imageTest);
		}
		
		
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
	 * @return the hLayoutCMEAE
	 */
	public HorizontalLayout gethLayoutCMEAE() {
		return hLayoutCMEAE;
	}

	/**
	 * @param hLayoutCMEAE
	 *            the hLayoutCMEAE to set
	 */
	public void sethLayoutCMEAE(HorizontalLayout hLayoutCMEAE) {
		this.hLayoutCMEAE = hLayoutCMEAE;
	}

	/**
	 * @return the eaeService
	 */
	public IEAEService getEaeService() {
		return eaeService;
	}

	/**
	 * @param eaeService the eaeService to set
	 */
	public void setEaeService(IEAEService eaeService) {
		this.eaeService = eaeService;
	}

	/**
	 * @return the historyEAEContent
	 */
	public HistoryEAEContent getHistoryEAEContent() {
		return historyEAEContent;
	}

	/**
	 * @param historyEAEContent the historyEAEContent to set
	 */
	public void setHistoryEAEContent(HistoryEAEContent historyEAEContent) {
		this.historyEAEContent = historyEAEContent;
	}

	@Override
	public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
		String name = event.getComponent().getCaption();
    	Integer colleagueId = new Integer(event.getComponent().getId());
		
    	hLayoutCMEAE.removeAllComponents();
		hLayoutCMEAE.addComponent(historyEAEContent
				.buildViewHistoryEAEContent(colleagueId));

		hLayoutCMEAE.setCaption("Les EAE de " + name);
		
	}

}
