package com.novedia.talentmap.web.ui.colleague.eae;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.dto.EAEResultsDTO;
import com.novedia.talentmap.model.entity.Objective;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.services.IObjectiveService;
import com.novedia.talentmap.services.impl.EAEService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.ConstantsDB;
import com.novedia.talentmap.web.utils.EAEConsultationMode;
import com.novedia.talentmap.web.utils.EAETabEnum;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextArea;

public class EAEResultsForm extends FormLayout implements BlurListener, EAESaveObjectiveForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -165042621036325247L;

	private IEAEService eaeService;
	private IObjectiveService objectiveService;

	private GridLayout eaeResultsFormLayout;

	private EAEResultsDTO eaeResultsDTO;
	private BeanFieldGroup<EAEResultsDTO> binder;

	private Integer currentEAEId;
	private EAEConsultationMode currentMode;

	@PropertyId(ComponentsId.EAE_YEAR_SYNTHESES_ID)
	private TextArea yearSynthesis;

	@PropertyId(ComponentsId.EAE_COLLAB_STRENGTHS_ID)
	private TextArea collabStrenghts;

	@PropertyId(ComponentsId.EAE_COLLAB_WEAKNESSES_ID)
	private TextArea collabWeaknesses;

	@PropertyId(ComponentsId.EAE_MEANS_TO_PROGRESS_ID)
	private TextArea meansToProgress;

	private final String HEIGHT = "30px";
	private final String WIDTH = "640px";//OK
	private final String HEIGHT_APPRECIATION = "100px";
	private final String WIDTH_ACCORDION_OBECTIVES = "640px";
	private final String WIDTH_ACCORDION_APPRECIATION = "640px";
	private final String WIDTH_APPRECIATION_GLOBALE = "580px";
	private final String HEIGHT_FORM = "450px";

	private ResourceBundle resourceBundle;
	private CurrentEAEContent currentEAEContent;

	private void initResourceBundle() {
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES, locale);
	}

	public EAEResultsForm buildEAEResultsFormView(Integer currentEAEId,
			EAEConsultationMode currentMode, CurrentEAEContent currentEAEContent) {
		initResourceBundle();
		this.currentEAEId = currentEAEId;
		this.currentMode = currentMode;
		this.currentEAEContent = currentEAEContent;
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
		this.eaeResultsFormLayout.setRows(8);
		this.eaeResultsFormLayout.setId(ComponentsId.EAE_RESULTS_FORM_LAYOUT_ID);
		this.eaeResultsFormLayout.addStyleName("styleDeTest");
		this.eaeResultsFormLayout.setMargin(true);
		eaeResultsFormLayout.setWidth("90%");
	}

	private void buildEAEResultsForm() {
		removeAllComponents();
		Integer row = 0; 
		
		Integer previousEaeId= eaeService.getPreviousEAEID(currentEAEId);
		
		if(null == previousEaeId) {
			Label noBilan = new Label(resourceBundle.getString("results.no.previous.eae.message"));
			eaeResultsFormLayout.addComponent(noBilan);
		} else {
			setHeight(HEIGHT_FORM);
			// -----------------------------
			// YEAR SYNTHESIS
			// -----------------------------
			Label titreYearSynthesis = new Label(resourceBundle.getString("year.synthesis.label"));
			titreYearSynthesis.setStyleName("mystyleTitleBilanTODO");
			// Cette donnée n'est pas affichée dans le mode OPEN_MANAGER
			if(currentMode != EAEConsultationMode.OPEN_MANAGER) {
				yearSynthesis.setCaption(resourceBundle.getString("results.eae.year.synthesis"));
				yearSynthesis.setStyleName("TODO");
				yearSynthesis.setNullRepresentation(resourceBundle.getString("null.field.representation"));
				yearSynthesis.setId(ComponentsId.EAE_YEAR_SYNTHESES_ID);
				yearSynthesis.setHeight(HEIGHT);
				yearSynthesis.setWidth(WIDTH);
				yearSynthesis.setMaxLength(ConstantsDB.EAE_YEAR_SYNTHESIS_MAX_LENGTH);
				yearSynthesis.addStyleName("spacerTop");
				if(currentMode == EAEConsultationMode.OPEN_COLLAB) {
					yearSynthesis.setImmediate(true);
					yearSynthesis.addBlurListener(this);
				} 
				eaeResultsFormLayout.addComponent(titreYearSynthesis);
				eaeResultsFormLayout.addComponent(yearSynthesis);
				row +=2;
			}

			// ---------------------------------
			// OBJECTIFS
			// ---------------------------------
			eaeResultsDTO = eaeService.getEAEResults(currentEAEId);
			List<Objective> listObjectives = objectiveService.getPrecedentObjectivesByEAEId(currentEAEId);
			eaeResultsDTO.setListOjectives(listObjectives);
	
			Label titreObjectifs = new Label(resourceBundle.getString("objectives.label"));
			titreObjectifs.setStyleName("mystyleTitleBilanTODO");
			titreObjectifs.addStyleName("spacerTop");
			eaeResultsFormLayout.addComponent(titreObjectifs);
			row +=1;

			// Create the Accordion.
			Accordion accordionObjectives = new Accordion();
			accordionObjectives.setWidth(WIDTH_ACCORDION_OBECTIVES);
	
			if(listObjectives != null) {
				for(Objective o : listObjectives){
					EAEObjectiveForm oF = new EAEObjectiveForm();
					oF = oF.buildEAEObjectiveFormView(o, currentMode, EAETabEnum.RESULTS_TAB, this);
					accordionObjectives.addTab(oF, resourceBundle.getString("objective.title") + o.getTitle(), null);
				}
			}
			accordionObjectives.addStyleName("spacerTop");
			accordionObjectives.setId(ComponentsId.EAE_RESULTS_ACCORDION_OBJ_ID);
			
			if(listObjectives != null && !listObjectives.isEmpty()) {
				eaeResultsFormLayout.addComponent(accordionObjectives);
				row +=1;
	
			} else {
				Label noObjFound = new Label(resourceBundle.getString("no.objectives.label"));
				noObjFound.setWidth("300px");
				eaeResultsFormLayout.addComponent(noObjFound);
				row +=1;
			}

			// ---------------------------------
			// BIND DES DONNEES EAEResultsDTO
			// ---------------------------------
			binder = new BeanFieldGroup<EAEResultsDTO>(EAEResultsDTO.class);
			binder.setItemDataSource(eaeResultsDTO);
			binder.setBuffered(false);
			binder.bindMemberFields(this);

			// ---------------------------------
			// Appréciation Globale
			// ---------------------------------
			// -----------------------------
			// COLLAB STRENGHTS
			// -----------------------------
			// Cette donnée n'est pas affichée dans le mode OPEN_MANAGER
			if(currentMode != EAEConsultationMode.OPEN_MANAGER) {
				collabStrenghts.setCaption(resourceBundle.getString("results.eae.collab.strenghts"));
				collabStrenghts.setStyleName("TODO");
				collabStrenghts.setNullRepresentation(resourceBundle.getString("null.field.representation"));
				collabStrenghts.setId(ComponentsId.EAE_COLLAB_STRENGTHS_ID);
				collabStrenghts.setHeight(HEIGHT);
				collabStrenghts.setWidth(WIDTH_APPRECIATION_GLOBALE);
				collabStrenghts.setMaxLength(ConstantsDB.EAE_COLLEAGUES_STRENGTHS_MAX_LENGTH);
				collabStrenghts.addStyleName("spacerTop styleMargin");
				if(currentMode == EAEConsultationMode.OPEN_COLLAB) {
					collabStrenghts.setImmediate(true);
					collabStrenghts.addBlurListener(this);
				} 
			}	
			// -----------------------------
			// COLLAB WEAKNESSES
			// -----------------------------
			// Cette donnée n'est pas affichée dans le mode OPEN_MANAGER
			if(currentMode != EAEConsultationMode.OPEN_MANAGER) {
				collabWeaknesses.setCaption(resourceBundle.getString("results.eae.collab.weaknesses"));
				collabWeaknesses.setStyleName("TODO");
				collabWeaknesses.setNullRepresentation(resourceBundle.getString("null.field.representation"));
				collabWeaknesses.setId(ComponentsId.EAE_COLLAB_WEAKNESSES_ID);
				collabWeaknesses.setHeight(HEIGHT);
				collabWeaknesses.setWidth(WIDTH_APPRECIATION_GLOBALE);
				collabWeaknesses.setMaxLength(ConstantsDB.EAE_COLLEAGUES_WEAKNESSES_MAX_LENGTH);
				collabWeaknesses.addStyleName("spacerTop styleMargin");
				if(currentMode == EAEConsultationMode.OPEN_COLLAB) {
					collabWeaknesses.setImmediate(true);
					collabWeaknesses.addBlurListener(this);
				} 
			}

			// Cette donnée n'est pas affichée dans le mode OPEN_MANAGER
			if(currentMode != EAEConsultationMode.OPEN_MANAGER) {
				Label titreAppreciations = new Label(resourceBundle.getString("appreciations.label"));
				titreAppreciations.setStyleName("mystyleTitleBilanTODO");
				titreAppreciations.addStyleName("spacerTop");
				eaeResultsFormLayout.addComponent(titreAppreciations);
				row +=1;
	
				Accordion accordionAppreciation = new Accordion();
				accordionAppreciation.setSizeFull();
				accordionAppreciation.setHeight(HEIGHT_APPRECIATION);
				accordionAppreciation.setWidth(WIDTH_ACCORDION_APPRECIATION);
				accordionAppreciation.addTab(collabStrenghts, resourceBundle.getString("results.eae.collab.strenghts"), null);
				accordionAppreciation.addTab(collabWeaknesses, resourceBundle.getString("results.eae.collab.weaknesses"), null);
				accordionAppreciation.addStyleName("spacerTop");
				accordionAppreciation.setId(ComponentsId.EAE_RESULTS_ACCORDION_APPREC_ID);
			
				// --------------------------------------
				// Désactivations
				// --------------------------------------
				if(currentMode != EAEConsultationMode.OPEN_COLLAB) {
					yearSynthesis.setReadOnly(true);
					Tab myTabCollabStrenghts = accordionAppreciation.getTab(collabStrenghts);
					myTabCollabStrenghts.getComponent().setReadOnly(true);
					Tab myTabCollabWeaknesses = accordionAppreciation.getTab(collabWeaknesses);
					myTabCollabWeaknesses.getComponent().setReadOnly(true);
				}
				eaeResultsFormLayout.addComponent(accordionAppreciation);
				row +=1;
	
			}
		
			// ---------------------------------
			// MEANS TO PROGRESS // MANAGER PART
			// ---------------------------------
			// Cette donnée n'est pas affichée dans le mode OPEN_COLLAB
			if(currentMode != EAEConsultationMode.OPEN_COLLAB && currentMode != EAEConsultationMode.OPEN_MANAGER && currentMode != EAEConsultationMode.VALIDATED_COLLAB) {
				meansToProgress.setCaption(resourceBundle.getString("means.to.progress.label"));
				meansToProgress.setStyleName("TODO");
				meansToProgress.setNullRepresentation(resourceBundle.getString("null.field.representation"));
				meansToProgress.setId(ComponentsId.EAE_MEANS_TO_PROGRESS_ID);
				meansToProgress.setHeight(HEIGHT);
				meansToProgress.setWidth(WIDTH);
				meansToProgress.setMaxLength(ConstantsDB.EAE_MEANS_TO_PROGRESS_MAX_LENGTH);
				meansToProgress.addStyleName("spacerTop");
				if(currentMode == EAEConsultationMode.VALIDATED_MANAGER) {
					meansToProgress.setImmediate(true);
					meansToProgress.addBlurListener(this);
				} 
				eaeResultsFormLayout.addComponent(meansToProgress);
				row +=1;
				//MODE READONLY si nécessaire
				if(currentMode != EAEConsultationMode.VALIDATED_MANAGER) {
					Component c = eaeResultsFormLayout.getComponent(0, row-1);
					c.setReadOnly(true);
				}
			}
		}
		
		addComponent(eaeResultsFormLayout);
		
	}

	@Override
	public void blur(BlurEvent event) {
		EAEService eaeS = (EAEService) this.eaeService;
		eaeS.saveEAEResults(eaeResultsDTO);
		currentEAEContent.refreshValidateButton();
	}


	public void saveObjective(Objective objective) {
		objectiveService.saveObjective(objective);
		currentEAEContent.refreshValidateButton();
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
	 * @param eaeResultsFormLayout
	 *            the eaeResultsFormLayout to set
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
	 * @param eaeResultsDTO
	 *            the eaeResultsDTO to set
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
	 * @param currentEAEId
	 *            the currentEAEId to set
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
	 * @param yearSynthesis
	 *            the yearSynthesis to set
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
	 * @param collabStrenghts
	 *            the collabStrenghts to set
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
	 * @param collabWeaknesses
	 *            the collabWeaknesses to set
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
	 * @param meansToProgress
	 *            the meansToProgress to set
	 */
	public void setMeansToProgress(TextArea meansToProgress) {
		this.meansToProgress = meansToProgress;
	}

	public IObjectiveService getObjectiveService() {
		return objectiveService;
	}

	public void setObjectiveService(IObjectiveService objectiveService) {
		this.objectiveService = objectiveService;
	}

}
