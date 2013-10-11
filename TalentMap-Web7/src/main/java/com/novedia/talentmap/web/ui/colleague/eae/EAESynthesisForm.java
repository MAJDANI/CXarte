package com.novedia.talentmap.web.ui.colleague.eae;

import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.dto.EAESynthesisDTO;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.services.impl.EAEService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.ConstantsDB;
import com.novedia.talentmap.web.utils.EAEConsultationMode;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;

public class EAESynthesisForm extends FormLayout implements BlurListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2801457655703340958L;

	private IEAEService eaeService;

	private GridLayout eaeSynthesisFormLayout;

	private EAESynthesisDTO eaeSynthesisDTO;

	private BeanFieldGroup<EAESynthesisDTO> binder;

	private Integer currentEAEId;
	private EAEConsultationMode currentMode;


	@PropertyId(ComponentsId.EAE_COLLEAGUES_SYNTHESIS_ID)
	private TextArea eaeColSynthesis;

	@PropertyId(ComponentsId.EAE_MANAGERS_SYNTHESIS_ID)
	private TextArea eaeManSynthesis;

	@PropertyId(ComponentsId.EAE_OTHER_ID)
	private TextArea eaeOther;

	private final String HEIGHT = "30px";
	private final String WIDTH = "550px";
	private final String HEIGHT_FORM = "400px";
	private ResourceBundle resourceBundle;

	private void initResourceBundle() {
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(
				PropertiesFile.TALENT_MAP_PROPERTIES, locale);
	}

	public EAESynthesisForm buildEAESynthesisFormView(Integer currentEAEId,
			EAEConsultationMode currentMode) {
		initResourceBundle();
		this.currentEAEId = currentEAEId;
		this.currentMode = currentMode;
		removeAllComponents();
		buildMain();
		setHeight(HEIGHT_FORM);
		return this;
	}

	private void buildMain() {
		try {
			buildLayout();
			buildEAESynthesisForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buildLayout() {
		eaeSynthesisFormLayout.removeAllComponents();
		this.eaeSynthesisFormLayout.setColumns(1);
		this.eaeSynthesisFormLayout.setRows(4);
		this.eaeSynthesisFormLayout
				.setId(ComponentsId.EAE_SYNTHESIS_FORM_LAYOUT_ID);
		this.eaeSynthesisFormLayout.addStyleName("styleDeTest");
	}

	private void buildEAESynthesisForm() {
		removeAllComponents();
		eaeColSynthesis = new TextArea();
		

		// -----------------------------
		// GLOBAL TITLE
		// -----------------------------
		Label titreSynthesis = new Label(
				resourceBundle.getString("eae.synthesis.title"));
		titreSynthesis.setStyleName("mystyleTitleBilanTODO");
		titreSynthesis.addStyleName("spacerTop");

		// -----------------------------
		// COLLEAGUE'S SYNTHESIS
		// -----------------------------
		eaeColSynthesis.setCaption(resourceBundle
				.getString("eae.colleague.synthesis.label"));
		eaeColSynthesis.setNullRepresentation(resourceBundle
				.getString("null.field.representation"));
		eaeColSynthesis.setId(ComponentsId.EAE_COLLEAGUES_SYNTHESIS_ID);
		eaeColSynthesis.setHeight(HEIGHT);
		eaeColSynthesis.setWidth(WIDTH);
		eaeColSynthesis.setMaxLength(ConstantsDB.EAE_COLLEAGUES_SYNTHESIS_MAX_LENGTH);
		eaeColSynthesis.addStyleName("spacerTop");
//		eaeColSynthesis.addStyleName("monStyleBorderNone");
		if (currentMode == EAEConsultationMode.VALIDATED_MANAGER) {
			eaeColSynthesis.setImmediate(true);
			eaeColSynthesis.addBlurListener(this);
		}

		// -----------------------------
		// MANAGER'S SYNTHESIS
		// -----------------------------
		eaeManSynthesis = new TextArea();

		eaeManSynthesis.setCaption(resourceBundle
				.getString("eae.manager.synthesis.label"));
		eaeManSynthesis.setNullRepresentation(resourceBundle
				.getString("null.field.representation"));
		eaeManSynthesis.setId(ComponentsId.EAE_MANAGERS_SYNTHESIS_ID);
		eaeManSynthesis.setHeight(HEIGHT);
		eaeManSynthesis.setWidth(WIDTH);
		eaeManSynthesis.setMaxLength(ConstantsDB.EAE_MANAGERS_SYNTHESIS_MAX_LENGTH);
		eaeManSynthesis.addStyleName("spacerTop");
//		eaeManSynthesis.addStyleName("monStyleBorderNone");
		if (currentMode == EAEConsultationMode.VALIDATED_MANAGER) {
			eaeManSynthesis.setImmediate(true);
			eaeManSynthesis.addBlurListener(this);
		}

		// -----------------------------
		// OTHER
		// -----------------------------
		eaeOther = new TextArea();
		eaeOther.setCaption(resourceBundle
				.getString("eae.synthesis.other.label"));
		eaeOther.setNullRepresentation(resourceBundle
				.getString("null.field.representation"));
		eaeOther.setId(ComponentsId.EAE_OTHER_ID);
		eaeOther.setHeight(HEIGHT);
		eaeOther.setWidth(WIDTH);
		eaeOther.setMaxLength(ConstantsDB.EAE_OTHER_MAX_LENGTH);
		eaeOther.addStyleName("spacerTop");
//		eaeOther.addStyleName("monStyleBorderNone");
		if (currentMode == EAEConsultationMode.VALIDATED_MANAGER) {
			eaeOther.setImmediate(true);
			eaeOther.addBlurListener(this);
		}

		// ---------------------------------
		// BIND DES DONNEES EAEResultsDTO
		// ---------------------------------
		eaeSynthesisDTO = eaeService.getEAESynthesis(currentEAEId);

		binder = new BeanFieldGroup<EAESynthesisDTO>(EAESynthesisDTO.class);
		binder.setItemDataSource(eaeSynthesisDTO);
		binder.setBuffered(false);
		binder.bindMemberFields(this);

		// --------------------------------------
		// Insertion des éléments dans le Layout
		// --------------------------------------
		eaeSynthesisFormLayout.addComponent(titreSynthesis);
		eaeSynthesisFormLayout.addComponent(eaeColSynthesis);
		eaeSynthesisFormLayout.addComponent(eaeManSynthesis);
		eaeSynthesisFormLayout.addComponent(eaeOther);

		addComponent(eaeSynthesisFormLayout);
		
		// ---------------------------------------
		// DESACTIVATION DES CHAMPS
		// ---------------------------------------
		if (currentMode != EAEConsultationMode.VALIDATED_MANAGER) {
			for(int ligne = 0; ligne <4 ; ligne++) {
				Component c = eaeSynthesisFormLayout.getComponent(0, ligne);
				c.setReadOnly(true);
				c.addStyleName("monStyleBorderNone");
			}
		}

	}

	@Override
	public void blur(BlurEvent event) {
		EAEService eaeS = (EAEService)this.eaeService;
		eaeS.saveEAESynthesis(eaeSynthesisDTO);
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
	 * @return the currentEAEId
	 */
	public Integer getCurrentEAEId() {
		return currentEAEId;
	}

	/**
	 * @param currentEAEId
	 *            the currentEAEId to set
	 */
	public void setCurrentEAEId(Integer currentEAEId) {
		this.currentEAEId = currentEAEId;
	}

	/**
	 * @return the eaeSynthesisFormLayout
	 */
	public GridLayout getEaeSynthesisFormLayout() {
		return eaeSynthesisFormLayout;
	}

	/**
	 * @param eaeSynthesisFormLayout
	 *            the eaeSynthesisFormLayout to set
	 */
	public void setEaeSynthesisFormLayout(GridLayout eaeSynthesisFormLayout) {
		this.eaeSynthesisFormLayout = eaeSynthesisFormLayout;
	}

	/**
	 * @return the eaeColSynthesis
	 */
	public TextArea getEaeColSynthesis() {
		return eaeColSynthesis;
	}

	/**
	 * @param eaeColSynthesis
	 *            the eaeColSynthesis to set
	 */
	public void setEaeColSynthesis(TextArea eaeColSynthesis) {
		this.eaeColSynthesis = eaeColSynthesis;
	}

	/**
	 * @return the eaeManSynthesis
	 */
	public TextArea getEaeManSynthesis() {
		return eaeManSynthesis;
	}

	/**
	 * @param eaeManSynthesis
	 *            the eaeManSynthesis to set
	 */
	public void setEaeManSynthesis(TextArea eaeManSynthesis) {
		this.eaeManSynthesis = eaeManSynthesis;
	}

	/**
	 * @return the eaeOther
	 */
	public TextArea getEaeOther() {
		return eaeOther;
	}

	/**
	 * @param eaeOther
	 *            the eaeOther to set
	 */
	public void setEaeOther(TextArea eaeOther) {
		this.eaeOther = eaeOther;
	}

}
