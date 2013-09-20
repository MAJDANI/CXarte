package com.novedia.talentmap.web.ui.colleague.eae;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.dto.EAEResultsDTO;
import com.novedia.talentmap.model.entity.Objective;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.services.IObjectiveService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.EAEConsultationMode;
import com.novedia.talentmap.web.utils.EAETabEnum;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

public class EAEObjectivesForm extends FormLayout implements EAESaveObjectiveForm, ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2801457655703340958L;
	
	private IEAEService eaeService;
	private IObjectiveService objectiveService;

	private GridLayout eaeObjectivesFormLayout;

	private BeanFieldGroup<EAEResultsDTO> binder;

	private Integer currentEAEId;
	private EAEConsultationMode currentMode;

	private final String WIDTH_APP_GLOBALE = "500px";
	private final String HEIGHT_FORM = "550px";

	private Button newObjectiveButton;
	
	private ResourceBundle resourceBundle;

	private void initResourceBundle() {
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES, locale);
	}

	public EAEObjectivesForm buildEAEObjectivesFormView(Integer currentEAEId,
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
			buildEAEObjectivesForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buildLayout() {
		eaeObjectivesFormLayout.removeAllComponents();
		this.eaeObjectivesFormLayout.setColumns(1);
		this.eaeObjectivesFormLayout.setRows(6);
		this.eaeObjectivesFormLayout.setId(ComponentsId.EAE_OBJECTIVES_FORM_LAYOUT_ID);
		this.eaeObjectivesFormLayout.addStyleName("styleDeTest");

	}

	private void buildEAEObjectivesForm() {
		removeAllComponents();
		 
		List<Objective> listObjectives = objectiveService.getObjectivesByEAEId(currentEAEId);
		if(currentMode != EAEConsultationMode.CLOSED) {
			newObjectiveButton = new Button();
			newObjectiveButton.setCaption(resourceBundle.getString("new.objective.button.caption"));
			newObjectiveButton.setId(ComponentsId.NEW_OBJECTIVE_BUTTON_ID);
			newObjectiveButton.addClickListener(this);
		}
		// ---------------------------------
		// OBJECTIFS
		// ---------------------------------

		// Create the Accordion.
		Accordion accordionObjectives = new Accordion();
		accordionObjectives.setSizeFull();
		accordionObjectives.setWidth(WIDTH_APP_GLOBALE);

		if(listObjectives != null) {
			for(Objective o : listObjectives){
				EAEObjectiveForm oF = new EAEObjectiveForm();
				oF = oF.buildEAEObjectiveFormView(o, currentMode, EAETabEnum.OBJECTIVE_TAB, this);
				accordionObjectives.addTab(oF, resourceBundle.getString("objective.title") + o.getTitle(), null);
			}
		}
		accordionObjectives.addStyleName("spacerTop");
		accordionObjectives.setId(ComponentsId.EAE_OBJECTIVES_ACCORDION_OBJ_ID);
		
		

		// --------------------------------------
		// Insertion des éléments dans le Layout
		// --------------------------------------
		if(currentMode != EAEConsultationMode.CLOSED) {
			eaeObjectivesFormLayout.addComponent(newObjectiveButton);
		}
		if(listObjectives != null && !listObjectives.isEmpty()) {
			eaeObjectivesFormLayout.addComponent(accordionObjectives);
		} else {
			eaeObjectivesFormLayout.addComponent(new Label(resourceBundle.getString("no.objectives.label")));
		}
		
		addComponent(eaeObjectivesFormLayout);
		
	}


	public void saveObjective(Objective objective) {
		objectiveService.saveObjective(objective);
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

	public IObjectiveService getObjectiveService() {
		return objectiveService;
	}

	public void setObjectiveService(IObjectiveService objectiveService) {
		this.objectiveService = objectiveService;
	}

	/**
	 * @return the eaeObjectivesFormLayout
	 */
	public GridLayout getEaeObjectivesFormLayout() {
		return eaeObjectivesFormLayout;
	}

	/**
	 * @param eaeObjectivesFormLayout the eaeObjectivesFormLayout to set
	 */
	public void setEaeObjectivesFormLayout(GridLayout eaeObjectivesFormLayout) {
		this.eaeObjectivesFormLayout = eaeObjectivesFormLayout;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}

}
