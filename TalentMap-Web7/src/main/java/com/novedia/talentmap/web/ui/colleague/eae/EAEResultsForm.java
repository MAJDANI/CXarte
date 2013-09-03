package com.novedia.talentmap.web.ui.colleague.eae;

import com.novedia.talentmap.model.dto.EAEResultsDTO;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.services.impl.EAEService;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.ConstantsDB;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextArea;

public class EAEResultsForm extends FormLayout implements BlurListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = -165042621036325247L;

	private IEAEService eaeService;

	private GridLayout eaeResultsFormLayout;

	private EAEResultsDTO eaeResultsDTO;
	private BeanFieldGroup<EAEResultsDTO> binder;

	private Integer currentEAEId;
	
	@PropertyId(ComponentsId.EAE_YEAR_SYNTHESES_ID)
	private TextArea yearSynthesis;

	@PropertyId(ComponentsId.EAE_COLLAB_STRENGTHS_ID)
	private TextArea collabStrenghts;

	@PropertyId(ComponentsId.EAE_COLLAB_WEAKNESSES_ID)
	private TextArea collabWeaknesses;

	@PropertyId(ComponentsId.EAE_MEANS_TO_PROGRESS_ID)
	private TextArea meansToProgress;


	public EAEResultsForm buildEAEResultsFormView(Integer currentEAEId) {
		this.currentEAEId = currentEAEId;
		removeAllComponents();
		buildMain();
		return this;
	}

	private void buildMain() {
		try {
			buildLayout();
			buildEAEResultsForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buildLayout() {
		eaeResultsFormLayout.removeAllComponents();
		this.eaeResultsFormLayout.setColumns(1);
		this.eaeResultsFormLayout.setRows(3);

	}

	private void buildEAEResultsForm() {
		removeAllComponents();

		// -----------------------------
		// YEAR SYNTHESIS
		// -----------------------------
		yearSynthesis.setCaption(Constants.EAE_YEAR_SYNTHESIS);
		yearSynthesis.setStyleName("TODO");
		yearSynthesis.setNullRepresentation("");
		yearSynthesis.setId(ComponentsId.EAE_YEAR_SYNTHESES_ID);
		yearSynthesis.setHeight("50px");
		yearSynthesis.setWidth("500px");
		yearSynthesis.setMaxLength(ConstantsDB.EAE_YEAR_SYNTHESIS_MAX_L);
		yearSynthesis.setImmediate(true);
		yearSynthesis.addBlurListener(this);
		eaeResultsFormLayout.addComponent(yearSynthesis);

		// -----------------------------
		// COLLAB STRENGHTS
		// -----------------------------
		collabStrenghts.setCaption(Constants.EAE_COLLAB_STRENGHTS);
		collabStrenghts.setStyleName("TODO");
		collabStrenghts.setNullRepresentation("");
		collabStrenghts.setId(ComponentsId.EAE_COLLAB_STRENGTHS_ID);
		collabStrenghts.setHeight("50px");
		collabStrenghts.setWidth("500px");
		collabStrenghts.setMaxLength(ConstantsDB.EAE_COLLAB_STRENGHTS_MAX_L);
		collabStrenghts.addStyleName("spacerTop");
		collabStrenghts.setImmediate(true);
		collabStrenghts.addBlurListener(this);
		eaeResultsFormLayout.addComponent(collabStrenghts);


	
		// -----------------------------
		// COLLAB WEAKNESSES
		// -----------------------------
		collabWeaknesses.setCaption(Constants.EAE_COLLAB_WEAKNESSES);
		collabWeaknesses.setStyleName("TODO");
		collabWeaknesses.setNullRepresentation("");
		collabWeaknesses.setId(ComponentsId.EAE_COLLAB_WEAKNESSES_ID);
		collabWeaknesses.setHeight("50px");
		collabWeaknesses.setWidth("500px");
		collabWeaknesses.setMaxLength(ConstantsDB.EAE_COLLAB_WEAKNESSES_MAX_L);
		collabWeaknesses.addStyleName("spacerTop");
		collabWeaknesses.setImmediate(true);
		collabWeaknesses.addBlurListener(this);
		eaeResultsFormLayout.addComponent(collabWeaknesses);

		// -----------------------------
		// MEANS TO PROGRESS
		// -----------------------------
		// TODO

		eaeResultsDTO = eaeService.getEAEResults(currentEAEId);

		binder = new BeanFieldGroup<EAEResultsDTO>(EAEResultsDTO.class);
		binder.setItemDataSource(eaeResultsDTO);
		binder.setBuffered(false);
		binder.bindMemberFields(this);

		addComponent(eaeResultsFormLayout);
		
	}

	@Override
	public void blur(BlurEvent event) {
		System.out.println("eaeResultsDTO=" + eaeResultsDTO);
		EAEService eaeS = (EAEService)this.eaeService;
		eaeS.saveEAEResults(eaeResultsDTO);
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
	 * @return the eaeResultsFormLayout
	 */
	public GridLayout getEaeResultsFormLayout() {
		return eaeResultsFormLayout;
	}

	/**
	 * @param eaeResultsFormLayout the eaeResultsFormLayout to set
	 */
	public void setEaeResultsFormLayout(GridLayout eaeResultsFormLayout) {
		this.eaeResultsFormLayout = eaeResultsFormLayout;
	}

	/**
	 * @return the eaeResultsDTO
	 */
	public EAEResultsDTO getEaeResultsDTO() {
		return eaeResultsDTO;
	}

	/**
	 * @param eaeResultsDTO the eaeResultsDTO to set
	 */
	public void setEaeResultsDTO(EAEResultsDTO eaeResultsDTO) {
		this.eaeResultsDTO = eaeResultsDTO;
	}

	/**
	 * @return the currentEAEId
	 */
	public Integer getCurrentEAEId() {
		return currentEAEId;
	}

	/**
	 * @param currentEAEId the currentEAEId to set
	 */
	public void setCurrentEAEId(Integer currentEAEId) {
		this.currentEAEId = currentEAEId;
	}

	/**
	 * @return the yearSynthesis
	 */
	public TextArea getYearSynthesis() {
		return yearSynthesis;
	}

	/**
	 * @param yearSynthesis the yearSynthesis to set
	 */
	public void setYearSynthesis(TextArea yearSynthesis) {
		this.yearSynthesis = yearSynthesis;
	}

	/**
	 * @return the collabStrenghts
	 */
	public TextArea getCollabStrenghts() {
		return collabStrenghts;
	}

	/**
	 * @param collabStrenghts the collabStrenghts to set
	 */
	public void setCollabStrenghts(TextArea collabStrenghts) {
		this.collabStrenghts = collabStrenghts;
	}

	/**
	 * @return the collabWeaknesses
	 */
	public TextArea getCollabWeaknesses() {
		return collabWeaknesses;
	}

	/**
	 * @param collabWeaknesses the collabWeaknesses to set
	 */
	public void setCollabWeaknesses(TextArea collabWeaknesses) {
		this.collabWeaknesses = collabWeaknesses;
	}

	/**
	 * @return the meansToProgress
	 */
	public TextArea getMeansToProgress() {
		return meansToProgress;
	}

	/**
	 * @param meansToProgress the meansToProgress to set
	 */
	public void setMeansToProgress(TextArea meansToProgress) {
		this.meansToProgress = meansToProgress;
	}

}
