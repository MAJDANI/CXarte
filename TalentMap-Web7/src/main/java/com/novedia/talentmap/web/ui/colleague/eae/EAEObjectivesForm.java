package com.novedia.talentmap.web.ui.colleague.eae;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.dto.EAEResultsDTO;
import com.novedia.talentmap.model.entity.EAE;
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
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

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

	private final String WIDTH_ACCORDION_OBECTIVES = "590px";
	private final String HEIGHT_FORM = "450px";

	private Button newObjectiveButton;
	private Window windowNewObjective;
	private Button saveNewObjective;
	private Objective newObjective;
	
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
		this.eaeObjectivesFormLayout.setMargin(true);
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
		accordionObjectives.setWidth(WIDTH_ACCORDION_OBECTIVES);

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
			Label noObjFound = new Label(resourceBundle.getString("no.objectives.label"));
			noObjFound.setWidth("300px");
			eaeObjectivesFormLayout.addComponent(noObjFound);
		}
		
		addComponent(eaeObjectivesFormLayout);
		
	}


	@Override
	public void buttonClick(ClickEvent event) {
		if(newObjectiveButton == event.getSource()) {
			buildWindowNewObjective();
		}
		if(saveNewObjective == event.getSource()) {
			EAE currentEAE = eaeService.getEAEById(currentEAEId);
			Integer collId = currentEAE.getColleague().getId();
			Integer manId = currentEAE.getManager().getId();
			
			newObjective.setColleagueId(collId);
			newObjective.setManagerId(manId);
		
			objectiveService.addObjective(newObjective);
			windowNewObjective.close();
			
			CurrentEAEContent currentEAEContent = (CurrentEAEContent)this.getParent().getParent().getParent().getParent();
			currentEAEContent.refreshObjectives();
		}
		
	}

	private void buildWindowNewObjective() {
		windowNewObjective = new Window(resourceBundle.getString("new.obective.window.title"));
		VerticalLayout vLayout = new VerticalLayout();
		
		EAEObjectiveForm objectiveForm = new EAEObjectiveForm();
		newObjective = new Objective();
		newObjective.setEaeId(currentEAEId);
		objectiveForm = objectiveForm.buildEAEObjectiveFormView(newObjective, currentMode, EAETabEnum.OBJECTIVE_TAB, this);
		vLayout.addComponent(objectiveForm);
		
		saveNewObjective = new Button(resourceBundle.getString("new.obective.button.caption"));
		saveNewObjective.addClickListener(this);
		vLayout.addComponent(saveNewObjective);
		
		windowNewObjective.addComponent(vLayout);
		this.getUI().addWindow(windowNewObjective);
		windowNewObjective.setModal(true);
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

}
