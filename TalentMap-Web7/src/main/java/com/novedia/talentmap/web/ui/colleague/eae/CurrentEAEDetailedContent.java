package com.novedia.talentmap.web.ui.colleague.eae;

import com.novedia.talentmap.web.utils.ComponentsId;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CurrentEAEDetailedContent extends VerticalLayout implements
		ClickListener, ValueChangeListener {

	private EAEGeneralityForm eaeGeneralityForm;
	private EAEResultsForm eaeResultsForm;

	/**
	 * Default constructor
	 */
	public CurrentEAEDetailedContent() {
		super();
		setSpacing(true);
		addStyleName("TODO");
		this.setId(ComponentsId.EAE_CURRENT_EAE_DETAILED_C_ID);
		setWidth("630px");
	}

	/**
	 * Build buildViewEAEGenerality
	 * 
	 * @return VerticalLayout
	 */
	public VerticalLayout buildViewEAEGenerality(Integer currentEAEId, EAEConsultationMode currentMode) {
		removeAllComponents();
		eaeGeneralityForm.addStyleName("TODO");
		eaeGeneralityForm.setId(ComponentsId.EAE_GENERALITY_FORM_ID);
		addComponent(eaeGeneralityForm.buildEAEGeneralityFormView(currentEAEId, currentMode));
		return this;
	}

	/**
	 * Build buildViewEAEResults
	 * 
	 * @return VerticalLayout
	 */
	public VerticalLayout buildViewEAEResults(Integer currentEAEId, EAEConsultationMode currentMode) {
		removeAllComponents();
		eaeResultsForm.addStyleName("TODO");
		eaeResultsForm.setId(ComponentsId.EAE_RESULTS_FORM_ID);
		addComponent(eaeResultsForm.buildEAEResultsFormView(currentEAEId, currentMode));
		return this;
	}


	@Override
	public void buttonClick(ClickEvent event) {
		System.out.println("Button click event.getSource()=" + event.getSource());
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		System.out.println("Value Change event.getProperty()=" + event.getProperty());
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

}
