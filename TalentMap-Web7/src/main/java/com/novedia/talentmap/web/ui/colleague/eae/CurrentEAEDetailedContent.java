package com.novedia.talentmap.web.ui.colleague.eae;

import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.EAEConsultationMode;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CurrentEAEDetailedContent extends VerticalLayout {

	private EAEGeneralityForm eaeGeneralityForm;
	private EAEResultsForm eaeResultsForm;
	private EAEObjectivesForm eaeObjectivesForm;
	private EAESynthesisForm eaeSynthesisForm;

	/**
	 * Default constructor
	 */
	public CurrentEAEDetailedContent() {
		super();
		setSpacing(true);
		addStyleName("TODO");
		this.setId(ComponentsId.EAE_CURRENT_EAE_DETAILED_C_ID);
//		setWidth("700px");//OK
		setWidth("100%");
	}

	/**
	 * Build buildViewEAEGenerality
	 * 
	 * @return VerticalLayout
	 */
	public VerticalLayout buildViewEAEGenerality(Integer currentEAEId, EAEConsultationMode currentMode, CurrentEAEContent currentEAEContent) {
		removeAllComponents();
		eaeGeneralityForm.addStyleName("TODO");
		eaeGeneralityForm.setId(ComponentsId.EAE_GENERALITY_FORM_ID);
		addComponent(eaeGeneralityForm.buildEAEGeneralityFormView(currentEAEId, currentMode, currentEAEContent));
		return this;
	}

	/**
	 * Build buildViewEAEResults
	 * 
	 * @return VerticalLayout
	 */
	public VerticalLayout buildViewEAEResults(Integer currentEAEId, EAEConsultationMode currentMode, CurrentEAEContent currentEAEContent) {
		removeAllComponents();
		eaeResultsForm.addStyleName("TODO");
		eaeResultsForm.setId(ComponentsId.EAE_RESULTS_FORM_ID);
		addComponent(eaeResultsForm.buildEAEResultsFormView(currentEAEId, currentMode, currentEAEContent));
		return this;
	}

	/**
	 * Build buildViewEAEObjectives
	 * 
	 * @return VerticalLayout
	 */
	public VerticalLayout buildViewEAEObjectives(Integer currentEAEId, EAEConsultationMode currentMode) {
		removeAllComponents();
		eaeObjectivesForm.addStyleName("TODO");
		eaeObjectivesForm.setId(ComponentsId.EAE_OBJECTIVES_FORM_ID);
		addComponent(eaeObjectivesForm.buildEAEObjectivesFormView(currentEAEId, currentMode));
		return this;
	}

	/**
	 * Build buildViewEAESynthesis
	 * 
	 * @return VerticalLayout
	 */
	public VerticalLayout buildViewEAESynthesis(Integer currentEAEId, EAEConsultationMode currentMode) {
		removeAllComponents();
		eaeSynthesisForm.addStyleName("TODO");
		eaeSynthesisForm.setId(ComponentsId.EAE_SYNTHESIS_FORM_ID);
		addComponent(eaeSynthesisForm.buildEAESynthesisFormView(currentEAEId, currentMode));
		return this;
	}


	/**
	 * @return the eaeGeneralityForm
	 */
	public EAEGeneralityForm getEaeGeneralityForm() {
		return eaeGeneralityForm;
	}

	/**
	 * @param eaeGeneralityForm
	 *            the eaeGeneralityForm to set
	 */
	public void setEaeGeneralityForm(EAEGeneralityForm eaeGeneralityForm) {
		this.eaeGeneralityForm = eaeGeneralityForm;
	}

	/**
	 * @return the eaeResultsForm
	 */
	public EAEResultsForm getEaeResultsForm() {
		return eaeResultsForm;
	}

	/**
	 * @param eaeResultsForm the eaeResultsForm to set
	 */
	public void setEaeResultsForm(EAEResultsForm eaeResultsForm) {
		this.eaeResultsForm = eaeResultsForm;
	}

	/**
	 * @return the eaeObjectivesForm
	 */
	public EAEObjectivesForm getEaeObjectivesForm() {
		return eaeObjectivesForm;
	}

	/**
	 * @param eaeObjectivesForm the eaeObjectivesForm to set
	 */
	public void setEaeObjectivesForm(EAEObjectivesForm eaeObjectivesForm) {
		this.eaeObjectivesForm = eaeObjectivesForm;
	}

	/**
	 * @return the eaeSynthesisForm
	 */
	public EAESynthesisForm getEaeSynthesisForm() {
		return eaeSynthesisForm;
	}

	/**
	 * @param eaeSynthesisForm the eaeSynthesisForm to set
	 */
	public void setEaeSynthesisForm(EAESynthesisForm eaeSynthesisForm) {
		this.eaeSynthesisForm = eaeSynthesisForm;
	}

}
