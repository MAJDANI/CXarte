package com.novedia.talentmap.web.ui.cm.eae;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.dto.EAEColleagueResumeForCMDTO;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.ui.colleague.eae.CurrentEAEContent;
import com.novedia.talentmap.web.ui.colleague.eae.EAEDTOContainer;
import com.novedia.talentmap.web.ui.colleague.eae.HistoryEAEContent;
import com.novedia.talentmap.web.utils.CUtils;
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.EAEStateEnum;
import com.novedia.talentmap.web.utils.ProfilConnectedEnum;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
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
	private GridLayout hLayoutCMEAE;
	private VerticalLayout mainLayout = new VerticalLayout();
	private HorizontalLayout hLayoutLegendes = new HorizontalLayout();
	private HorizontalLayout hLayoutHisto = new HorizontalLayout();
	private HistoryEAEContent historyEAEContent;

	/**
	 * Contenu affiché dans le Panel de droite : EAE courant
	 */
	private CurrentEAEContent currentEAEContent;

	private List<EAEColleagueResumeForCMDTO> listEAEColleagueResumeForCMDTO;

	private ResourceBundle resourceBundle;

	private Integer currentColleagueId;
	
	/**
	 * Default constructor
	 */
	public CMEAEPopIn() {
		super();
		setModal(true);
		setWidth("750px"); //OK
		setHeight("475px");
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
		
		buildLayouts();
		buildLegendes();
		buildColleaguesButtons();
		mainLayout.addComponent(hLayoutLegendes);
		mainLayout.addComponent(hLayoutCMEAE);
		addComponent(mainLayout);
		return this;
	}

	private void buildLayouts() {
		removeAllComponents();
		hLayoutLegendes.setSpacing(true);
		hLayoutCMEAE.setSpacing(true);
		hLayoutCMEAE.setVisible(true);
		hLayoutCMEAE.removeAllComponents();
		hLayoutCMEAE.setColumns(5);
		hLayoutCMEAE.setRows(2);
		hLayoutCMEAE.setSizeFull();
		
	}
	private void buildLegendes() {
		hLayoutLegendes.removeAllComponents();
		TextField legendeVert = new TextField();
		legendeVert.setIcon(new ThemeResource(Constants.IMG_CADRE_GREEN));
		legendeVert.setCaption(resourceBundle.getString("eae.legende.open.caption"));
		legendeVert.setReadOnly(true);

		TextField legendeGris = new TextField();
		legendeGris.setIcon(new ThemeResource(Constants.IMG_CADRE_GREY));
		legendeGris.setCaption(resourceBundle.getString("eae.legende.no.eae.caption"));
		legendeGris.setReadOnly(true);
		
		
		TextField legendeRouge = new TextField();
		legendeRouge.setIcon(new ThemeResource(Constants.IMG_CADRE_RED));
		legendeRouge.setCaption(resourceBundle.getString("eae.legende.closed.caption"));
		legendeRouge.setReadOnly(true);
		
		TextField legendeOrange = new TextField();
		legendeOrange.setIcon(new ThemeResource(Constants.IMG_CADRE_ORANGE));
		legendeOrange.setCaption(resourceBundle.getString("eae.legende.validated.caption"));
		legendeOrange.setReadOnly(true);
		
		hLayoutLegendes.addComponent(legendeVert);
		hLayoutLegendes.addComponent(legendeGris);
		hLayoutLegendes.addComponent(legendeRouge);
		hLayoutLegendes.addComponent(legendeOrange);
		hLayoutLegendes.setVisible(true);
		
	}
	
	private void buildColleaguesButtons() {
		/**
		 * Id du manager connecté
		 */
    	int colleagueId = TalentMapApplication.getCurrent().getAuthentication().getColleagueId();
    	listEAEColleagueResumeForCMDTO = eaeService.getEAEColleagueResumeForCM(colleagueId);
		ThemeResource resourceBoy = new ThemeResource(Constants.IMG_NO_PHOTO_BOY);
		ThemeResource resourceGirl = new ThemeResource(Constants.IMG_NO_PHOTO_GIRL);

		for (EAEColleagueResumeForCMDTO eaDTO : listEAEColleagueResumeForCMDTO) {
			// -----------------------------
			// NOM ET IMAGE COLLABORATEUR
			// -----------------------------
			String firstName = eaDTO.getCollabFirstName() ;
			String lastName = eaDTO.getCollabLastName() ;
			
			Image photoColleague ;
			String typeMasculin = resourceBundle.getString("title.masculin.value");
			if(typeMasculin != null && typeMasculin.equals(eaDTO.getTitle().toUpperCase())) {
				photoColleague = new Image( firstName, resourceBoy);
			} else {
				photoColleague = new Image( firstName, resourceGirl);
			}
			photoColleague.addClickListener(this);
			photoColleague.setId(eaDTO.getIdColleague().toString());
			photoColleague.addStyleName("image");
			
			// -----------------------------
			// DATE EAE
			// -----------------------------
			String captionDate;
			Date dateEAE = eaDTO.getEaeDate();
			if(dateEAE == null) {
				captionDate = resourceBundle.getString("cm.eae.date.null.caption");
			} else {
				String date = CUtils.DATE_FORMAT.format(dateEAE);
				captionDate = resourceBundle.getString("cm.eae.date.caption") + date;
			}
			
			
			String nameDesc = "<h3>"+ firstName + "&nbsp;" + lastName +
				    "</h3>"+captionDate;

			photoColleague.setAlternateText(nameDesc);
			photoColleague.setDescription(nameDesc);
			
			// -----------------------------
			// On affiche le code couleur de l'état du dernier EAE
			// -----------------------------
			Integer state = eaDTO.getEaeStateId();
			if(null == state) {
				photoColleague.addStyleName("borderGrey");
			} else if(EAEStateEnum.CLOSED.getId() == state) {
				photoColleague.addStyleName("borderRed");
			} else if(EAEStateEnum.VALIDATED.getId() == state) {
				photoColleague.addStyleName("borderOrange");
			} else if(EAEStateEnum.OPEN.getId() == state) {
				photoColleague.addStyleName("borderGreen");
			}
			hLayoutCMEAE.addComponent(photoColleague);
			
		}
		
		
	}



	@Override
	public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
		String name = event.getComponent().getCaption();
		currentColleagueId = new Integer(event.getComponent().getId());
		hLayoutLegendes.setVisible(false);
		hLayoutCMEAE.setVisible(false);
		hLayoutHisto.setVisible(true);
//		System.out.println("currentColleagueId=" + currentColleagueId);
//
//		for (EAEColleagueResumeForCMDTO eaeDTO : listEAEColleagueResumeForCMDTO) {
//			Integer idC = eaeDTO.getIdColleague();
//			System.out.println("idC=" + idC);
//			if(idC.equals(currentColleagueId)) {
//				name = name + " " + eaeDTO.getCollabLastName();
//				System.out.println("name=" + name);
//				
//			}
//		}		
//		System.out.println("currentColleagueId=" + currentColleagueId);
		
	    hLayoutHisto.addComponent(historyEAEContent
				.buildViewHistoryEAEContent(currentColleagueId, ProfilConnectedEnum.MANAGER, this));
	    hLayoutHisto.setCaption(resourceBundle.getString("cm.eae.pop.in.collab.eae.title") + name);
	    
	    addComponent(hLayoutHisto);
		
	}

	public void refreshHistoContent() {
		//rafraichir l'historique
	    hLayoutHisto.addComponent(historyEAEContent
				.buildViewHistoryEAEContent(currentColleagueId, ProfilConnectedEnum.MANAGER, this));
	}

	public void reloadColleaguesButtons() {
		hLayoutHisto.setVisible(false);
		buildCMEAEPopIn();
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
	public GridLayout gethLayoutCMEAE() {
		return hLayoutCMEAE;
	}

	/**
	 * @param hLayoutCMEAE
	 *            the hLayoutCMEAE to set
	 */
	public void sethLayoutCMEAE(GridLayout hLayoutCMEAE) {
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

}
