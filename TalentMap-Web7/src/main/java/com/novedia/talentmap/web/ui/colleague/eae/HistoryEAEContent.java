package com.novedia.talentmap.web.ui.colleague.eae;

import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.dto.EAEForSynthesisDTO;
import com.novedia.talentmap.model.dto.NewEAEDTO;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Registration;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.ui.cm.eae.CMEAEPopIn;
import com.novedia.talentmap.web.utils.CUtils;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.EAEConsultationMode;
import com.novedia.talentmap.web.utils.EAEStateEnum;
import com.novedia.talentmap.web.utils.ProfilConnectedEnum;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Classe permettant l'affichage du tableau d l'historique des EAE du
 * collaborateur connecté. Ce tableau contient tous les EAE du collaborateur,
 * avec leur date et leur état (Ouvert, validé, Fermé).
 * 
 * @author v.guillemain
 * 
 */
@SuppressWarnings("serial")
public class HistoryEAEContent extends VerticalLayout implements
		ValueChangeListener, MouseEvents.ClickListener, ClickListener {

	private Panel listEAEPanel;

	/**
	 * Liste historique des EAE du collaborateur
	 */
	private ListEAEDTO listEAE;

	private ProfilConnectedEnum profilConnected;

	/**
	 * Contenu d'EAE affcihé quand on clique sur un des éléments de la liste
	 * historique
	 */
	private CurrentEAEContent historicalEAEContent;

	private CMEAEPopIn windowParent;
	ThemeResource resourceBack = new ThemeResource(Constants.IMG_BACK_BLUE);
	private Image imageBack;
	private Button newEAEButton;
	private Button createEAEButton;
	private Integer colleagueIdHistory;
	private IColleagueService colleagueService;
	private IEAEService eaeService;
	Window subWindowNewEAE;

	private BeanFieldGroup<NewEAEDTO> binder;

	@PropertyId(ComponentsId.EAE_DATE_ID)
	private PopupDateField eaeDateField;
	
	@PropertyId(ComponentsId.EAE_PREV_EAE_ID)
	private ComboBox previousEaeCombo;

	/**
	 * DTO that contains the only few values necessaries at the creation of an EAE
	 */
	private NewEAEDTO newEAEDTO;

	private final String WIDTH_EAE_WINDOW = "900px";

	private final String HEIGHT_NEW_EAE_WINDOW = "350px";
	private final String WIDTH_NEW_EAE_WINDOW = "1000px";//"400px";

	private Window subWindowEAEContent = new Window();
	private Integer currentColleagueId;
	
	private ResourceBundle resourceBundle;

	
	private void initResourceBundle() {
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(
				PropertiesFile.TALENT_MAP_PROPERTIES, locale);
	}

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
	public VerticalLayout buildViewHistoryEAEContent(Integer colleagueId,
			ProfilConnectedEnum profilConnected, CMEAEPopIn parent) {
		removeAllComponents();
		initResourceBundle();
		this.currentColleagueId = colleagueId;
		imageBack = new Image("", resourceBack);
		buildListEAEPanel(colleagueId);
		this.profilConnected = profilConnected;
		this.colleagueIdHistory = colleagueId;
		this.windowParent = parent;
		imageBack.addClickListener(this);

		if (profilConnected == ProfilConnectedEnum.MANAGER) {
			addComponent(imageBack);
		}

		listEAEPanel.addStyleName("listEAEPanel");
		addComponent(listEAEPanel);
		if (profilConnected == ProfilConnectedEnum.MANAGER) {
			newEAEButton = new Button(
					resourceBundle
							.getString("history.eae.button.create.new.caption"));
			newEAEButton.addStyleName("styleButton");
			newEAEButton.addClickListener(this);
			addComponent(newEAEButton);
			if (existsOpenEAE()) {
				newEAEButton.setEnabled(false);
			}

		}
		return this;
	}

	public void refreshViewHistoryEAEContent() {
		removeAllComponents();
		imageBack = new Image("", resourceBack);
		buildListEAEPanel(colleagueIdHistory);
		imageBack.addClickListener(this);
		addComponent(imageBack);
		listEAEPanel.addStyleName("listEAEPanel");
		addComponent(listEAEPanel);
		newEAEButton = new Button(
				resourceBundle
						.getString("history.eae.button.create.new.caption"));
		newEAEButton.addStyleName("styleButton");
		newEAEButton.addClickListener(this);
		addComponent(newEAEButton);
		if (existsOpenEAE()) {
			newEAEButton.setEnabled(false);
		}
		
	}
	/**
	 * Builds a new Window to create a new EAE
	 * 
	 * @return
	 */
	public void buildViewNewEAE() {
		// --------------------------
		// NAME OF THE COLLEAGUE
		// --------------------------
		Colleague currentColleague = colleagueService
				.getColleague(colleagueIdHistory);
		Label nomCollegue = new Label(
				resourceBundle.getString("cm.eae.new.eae.title") + " "
						+ currentColleague.getFirstName() + " "
						+ currentColleague.getLastName());
		nomCollegue.addStyleName("spacerInfo spacerTop spacerBottom");

		// --------------------------
		// DATE OF THE EAE
		// --------------------------
		eaeDateField = new PopupDateField();
		eaeDateField
				.setCaption(resourceBundle.getString("cm.eae.date.new.eae"));
		eaeDateField.setId(ComponentsId.EAE_DATE_ID);
		eaeDateField.setRequired(true);
		eaeDateField.setRequiredError(resourceBundle
				.getString("cm.eae.date.new.eae.error.msg"));
		eaeDateField.addValidator(new BeanValidator(NewEAEDTO.class,
				ComponentsId.EAE_DATE_ID));
		eaeDateField.setImmediate(true);
		eaeDateField.setValidationVisible(true);
		eaeDateField.setInputPrompt(Constants.DATE_FORMAT);
		eaeDateField.addStyleName("spacerInfo spacerTop spacerBottom");
		eaeDateField.setDateFormat(CUtils.STRING_DATE_FORMAT);
		
		// -----------------------------
		// LIST OF PREVIOUS EAE
		// -----------------------------
		previousEaeCombo = new ComboBox();
		Integer nbEAE = buildPreviousEAEList();
		if (nbEAE > 0) {
			previousEaeCombo.setCaption(resourceBundle.getString("history.eae.combo.date.caption"));
			previousEaeCombo.addStyleName("spacerInfo spacerTop spacerBottom");
			previousEaeCombo.setRequired(true);
			previousEaeCombo.setRequiredError(resourceBundle.getString("cm.eae.date.prev.eae.error.msg"));
			previousEaeCombo.addValidator(new BeanValidator(NewEAEDTO.class,
					 ComponentsId.EAE_PREV_EAE_ID));

			previousEaeCombo.setImmediate(true);
			previousEaeCombo.setValidationVisible(true);
			previousEaeCombo.setInputPrompt(resourceBundle.getString("history.previous.eae.choice"));
			previousEaeCombo.setId(ComponentsId.EAE_PREV_EAE_ID);
		}
		// -------------------------------------------
		// FILL EAEDTO WITH COLLEAGUE AND MANAGER ID
		// -------------------------------------------
		Integer managerId = TalentMapApplication.getCurrent()
				.getAuthentication().getColleagueId();
		newEAEDTO.setId(null);
		newEAEDTO.setColleagueId(currentColleague.getId());
		newEAEDTO.setManagerId(managerId);

		// --------------------------
		// BINDER
		// --------------------------
		binder = new BeanFieldGroup<NewEAEDTO>(NewEAEDTO.class);
		binder.setItemDataSource(newEAEDTO);
		binder.setBuffered(false);
		binder.bindMemberFields(this);

		// --------------------------
		// BUTTON CREATE
		// --------------------------
		createEAEButton = new Button(
				resourceBundle.getString("history.eae.button.valid.caption"));
		createEAEButton.addStyleName("styleButton");
		createEAEButton.addClickListener(this);
		createEAEButton.addStyleName("spacerInfo spacerTop spacerBottom");

		// --------------------------
		// BUILD THE WINDOW
		// --------------------------
		subWindowNewEAE = new Window(
				resourceBundle.getString("cm.eae.new.eae.window.title"));
		VerticalLayout subContentNewEAE = new VerticalLayout();
		subWindowNewEAE.setContent(subContentNewEAE);
		subContentNewEAE.setSpacing(true);
		subContentNewEAE.setMargin(true);
		subWindowNewEAE.setModal(true);
		subWindowNewEAE.setWidth(WIDTH_NEW_EAE_WINDOW);
		subWindowNewEAE.setHeight(HEIGHT_NEW_EAE_WINDOW);
		// --------------------------
		// Open it in the UI
		// --------------------------
		this.getUI().addWindow(subWindowNewEAE);
		subContentNewEAE.addComponent(nomCollegue);
		subContentNewEAE.addComponent(eaeDateField);
		if (nbEAE > 0) {
			subContentNewEAE.addComponent(previousEaeCombo);
		}
		subContentNewEAE.addComponent(createEAEButton);

	}

	/**
	 * Build colleague's EAE view
	 * 
	 * @return VerticalLayout
	 */
	public VerticalLayout buildViewHistoryEAEContentDetail(
			EAEForSynthesisDTO selectedEAE) {
		EAEConsultationMode mode = EAEConsultationMode
				.computeEAEConsultationMode(selectedEAE, profilConnected);
		//The History is for a Consultant Manager viewing the EAE of one of his colleague
		if (null != windowParent
				&& ProfilConnectedEnum.MANAGER == profilConnected) {
			subWindowEAEContent.setCaption(resourceBundle.getString("eae.content.title"));
			VerticalLayout subEAEContent = new VerticalLayout();
			subWindowEAEContent.setContent(subEAEContent);
			subEAEContent.setMargin(true);
			subWindowEAEContent.setModal(true);
			subWindowEAEContent.setPositionX(Constants.POSITION_X_EAE_POP_IN);
			subWindowEAEContent.setPositionY(Constants.POSITION_Y_EAE_POP_IN);
			subWindowEAEContent.setWidth(WIDTH_EAE_WINDOW);
			
			// Open it in the UI
			this.getUI().addWindow(subWindowEAEContent);
			historicalEAEContent.buildViewCurrentEAEContentExists(
					selectedEAE.getId(), mode, profilConnected, this);
			subEAEContent.addComponent(historicalEAEContent);
			return this;

		} 
		//The History is for a colleague
		else {
			removeAllComponents();
			historicalEAEContent.buildViewCurrentEAEContentExists(
					selectedEAE.getId(), mode, profilConnected, null);
			addComponent(historicalEAEContent);
			return this;
		}
	}

	/**
	 * Close the detailed EAE window opened from the History
	 */
	public void closeWindowDetailEAE() {
		subWindowEAEContent.close();
	}
	
	
	public void refreshListHistoEAE() {
		buildListEAEPanel(currentColleagueId);
	}
	
	/**
	 * Build list EAE panel
	 */
	private void buildListEAEPanel(Integer colleagueId) {

		listEAE.fillAllColleagueEAE(colleagueId);
		listEAE.addValueChangeListener(this);
		listEAE.addStyleName("table");
		listEAEPanel.addStyleName("listEAEPanel");
		listEAEPanel.removeAllComponents();
		if (listEAE.size() > 0) {
			listEAEPanel.addComponent(listEAE);
			listEAEPanel.setVisible(true);
			if (listEAE.size() < Constants.NB_ROWS_DEFAULT) {
				listEAE.setPageLength(listEAE.size());
			} else {
				listEAE.setPageLength(Constants.NB_ROWS_DEFAULT);
			}
		} else {
			listEAEPanel.addComponent(new Label(resourceBundle
					.getString("history.eae.no.history.msg")));
		}
	}

	/**
	 * Builds the COmbo whith the list of precedent EAE's dates 
	 * @return the number of previous EAE in the collection
	 */
	private Integer buildPreviousEAEList() {
		Collection<EAEForSynthesisDTO> collectionEAE = listEAE
				.getEaeDTOContainer().getItemIds();

		for (EAEForSynthesisDTO eae : collectionEAE) {
			Date dateEAE = eae.getDateEae();
			if (dateEAE != null) {
				previousEaeCombo.addItem(eae.getId());
				previousEaeCombo
						.setItemCaption(eae.getId(), CUtils.DATE_FORMAT.format(dateEAE));
			}
		}
		return collectionEAE.size();
	}

	private Boolean existsOpenEAE() {
		Collection<EAEForSynthesisDTO> collectionEAE = listEAE.getEaeDTOContainer()
				.getItemIds();
		for (EAEForSynthesisDTO eae : collectionEAE) {
			if(EAEStateEnum.OPEN.getId() == eae.getEaeStateId() || EAEStateEnum.VALIDATED.getId()== eae.getEaeStateId() )
				return true;
		}
		return false;
			
	}
	@Override
	public void valueChange(ValueChangeEvent event) {
		EAEForSynthesisDTO selectedEAE = (EAEForSynthesisDTO) listEAE
				.getValue();
		if (selectedEAE != null) {
			buildViewHistoryEAEContentDetail(selectedEAE);
		}
	}

	@Override
	public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
		this.windowParent.reloadColleaguesButtons();
	}

	@Override
	public void buttonClick(ClickEvent event) {

		if (event.getSource() == createEAEButton) {
			if (!validateNewEAE()) {
				Notification.show(resourceBundle.getString("missing.or.invalid.field.msg"),
						Notification.Type.WARNING_MESSAGE);
			} else {
//				if(newEAEDTO.getPreviousEaeId() == null) {
//					newEAEDTO.setEaeStateId(EAEStateEnum.VALIDATED.getId());
//				} else {
					newEAEDTO.setEaeStateId(EAEStateEnum.OPEN.getId());
//				}
				eaeService.addNewEAEDTO(newEAEDTO);
				subWindowNewEAE.close();
				refreshViewHistoryEAEContent();
			}

		} else if (event.getSource() == newEAEButton) {
			buildViewNewEAE();
		}
	}

	/**
	 * Test the new EAE validity
	 * 
	 * @return boolean
	 */
	private boolean validateNewEAE() {
		boolean isValidNewEAE = true;
		if (!this.binder.isValid()) {
			isValidNewEAE = false;
		}
		return isValidNewEAE;
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
	 * @param historicalEAEContent
	 *            the historicalEAEContent to set
	 */
	public void setHistoricalEAEContent(CurrentEAEContent historicalEAEContent) {
		this.historicalEAEContent = historicalEAEContent;
	}

	/**
	 * @return the colleagueService
	 */
	public IColleagueService getColleagueService() {
		return colleagueService;
	}

	/**
	 * @param colleagueService
	 *            the colleagueService to set
	 */
	public void setColleagueService(IColleagueService colleagueService) {
		this.colleagueService = colleagueService;
	}

	/**
	 * @return the newEAEDTO
	 */
	public NewEAEDTO getNewEAEDTO() {
		return newEAEDTO;
	}

	/**
	 * @param newEAEDTO
	 *            the newEAEDTO to set
	 */
	public void setNewEAEDTO(NewEAEDTO newEAEDTO) {
		this.newEAEDTO = newEAEDTO;
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

}
