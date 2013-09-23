package com.novedia.talentmap.web.ui.colleague.eae;

import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.dto.EAEGeneralityDTO;
import com.novedia.talentmap.services.IEAEService;
import com.novedia.talentmap.services.impl.EAEService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.EAEConsultationMode;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class EAEGeneralityForm extends FormLayout implements BlurListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3860292803505867673L;

	private IEAEService eaeService;

	private GridLayout eaeGeneralityFormLayout;

	private EAEGeneralityDTO eaeGeneralityDTO;
	private BeanFieldGroup<EAEGeneralityDTO> binder;

	private Integer currentEAEId;
	private EAEConsultationMode currentMode;

	@PropertyId(ComponentsId.COLLAB_LAST_NAME_ID)
	private TextField lastNameField;

	@PropertyId(ComponentsId.COLLAB_FIRST_NAME_ID)
	private TextField firstNameField;

	@PropertyId(ComponentsId.MANAGER_LAST_NAME_ID)
	private TextField managerLastNameField;

	@PropertyId(ComponentsId.MANAGER_FIRST_NAME_ID)
	private TextField managerFirstNameField;

	@PropertyId(ComponentsId.EAE_PROFILE_NAME_ID)
	private TextField jobField;

	@PropertyId(ComponentsId.EAE_DATE_ID)
	private TextField eaeDateField;

	@PropertyId(ComponentsId.EAE_PREV_DATE_ID)
	private TextField prevEaeDateField;

	@PropertyId(ComponentsId.EMPLOYMENT_DATE_ID)
	private TextField employmentDateField;

	@PropertyId(ComponentsId.EAE_SALARY_ID)
	private TextField salaryField;

	private ResourceBundle resourceBundle;

	private void initResourceBundle() {
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES, locale);
	}

	public EAEGeneralityForm buildEAEGeneralityFormView(Integer currentEAEId, EAEConsultationMode currentMode) {
		initResourceBundle();
		this.currentEAEId = currentEAEId;
		this.currentMode = currentMode;
		removeAllComponents();
		buildMain();
		return this;
	}

	private void buildMain() {
		try {
			buildLayout();
			buildEAEGeneralityForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buildLayout() {
		eaeGeneralityFormLayout.removeAllComponents();
		this.eaeGeneralityFormLayout.setColumns(3);
		this.eaeGeneralityFormLayout.setRows(3);
		this.eaeGeneralityFormLayout.setSizeFull();

	}

	private void buildEAEGeneralityForm() {
		removeAllComponents();

		// -----------------------------
		// LAST NAME
		// -----------------------------
		lastNameField.setCaption(resourceBundle.getString("name.field.caption"));
		lastNameField.setId(ComponentsId.COLLAB_LAST_NAME_ID);
		lastNameField.setWidth("190px");
		eaeGeneralityFormLayout.addComponent(lastNameField);

		// -----------------------------
		// FIRST NAME
		// -----------------------------
		firstNameField.setCaption(resourceBundle.getString("firstName.field.caption"));
		firstNameField.setStyleName("TODO");
		firstNameField.setId(ComponentsId.COLLAB_FIRST_NAME_ID);
		firstNameField.setWidth("190px");
		firstNameField.addStyleName("spacerInfo");
		eaeGeneralityFormLayout.addComponent(firstNameField);

		// -----------------------------
		// PROFILE
		// -----------------------------
		jobField.setCaption(resourceBundle.getString("job.field.caption"));
		jobField.setStyleName("TODO");
		jobField.setId(ComponentsId.EAE_PROFILE_NAME_ID);
		jobField.addStyleName("spacerInfo");
		eaeGeneralityFormLayout.addComponent(jobField);

		// -----------------------------
		// MANAGER'S LAST NAME
		// -----------------------------
		managerLastNameField.setCaption(resourceBundle.getString("manager.last.name.field.caption"));
		managerLastNameField.setStyleName("TODO");
		managerLastNameField.setId(ComponentsId.MANAGER_LAST_NAME_ID);
		managerLastNameField.setWidth("190px");
		managerLastNameField.addStyleName("spacerTop");
		eaeGeneralityFormLayout.addComponent(managerLastNameField);

		// -----------------------------
		// MANAGER'S FIRST NAME
		// -----------------------------
		managerFirstNameField.setCaption(resourceBundle.getString("manager.first.name.field.caption"));
		managerFirstNameField.setStyleName("TODO");
		managerFirstNameField.setId(ComponentsId.MANAGER_LAST_NAME_ID);
		managerFirstNameField.setWidth("190px");
		managerFirstNameField.addStyleName("spacerInfo spacerTop");
		eaeGeneralityFormLayout.addComponent(managerFirstNameField);

		// -----------------------------
		// EMPLOYMENT'S DATE
		// -----------------------------
		employmentDateField.setCaption(resourceBundle.getString("date.entry.caption"));
		employmentDateField.setId(ComponentsId.EMPLOYMENT_DATE_ID);
		employmentDateField.addStyleName("spacerInfo spacerTop");
		eaeGeneralityFormLayout.addComponent(employmentDateField);

		// -----------------------------
		// EAE'S DATE
		// -----------------------------
		eaeDateField.setCaption(resourceBundle.getString("eae.date.caption"));
		eaeDateField.setId(ComponentsId.EAE_DATE_ID);
		eaeDateField.addStyleName("spacerTop");
		eaeGeneralityFormLayout.addComponent(eaeDateField);

		// -----------------------------
		// PREVIOUS EAE'S DATE
		// -----------------------------
		prevEaeDateField.setCaption(resourceBundle.getString("eae.previous.date.caption"));
		prevEaeDateField.setId(ComponentsId.EAE_PREV_DATE_ID);
		prevEaeDateField.addStyleName("spacerInfo spacerTop");
		eaeGeneralityFormLayout.addComponent(prevEaeDateField);

		// -----------------------------
		// SALARY
		// -----------------------------
		salaryField.setCaption(resourceBundle.getString("eae.salary.caption"));
		salaryField.setNullRepresentation("");
		salaryField.setId(ComponentsId.EAE_SALARY_ID);
		salaryField.addStyleName("spacerInfo spacerTop");
		if(currentMode == EAEConsultationMode.OPEN_COLLAB) {
			salaryField.setRequired(true);
			salaryField.setRequiredError(resourceBundle.getString("eae.salary.error.message"));
			salaryField.addValidator(new BeanValidator(EAEGeneralityDTO.class,
					ComponentsId.EAE_SALARY_ID));
			salaryField.setImmediate(true);
			salaryField.setValidationVisible(true);
			salaryField.addBlurListener(this);
		} else {
			salaryField.setRequired(false);
			salaryField.removeAllValidators();
			salaryField.removeBlurListener(this);
			salaryField.setValidationVisible(false);
			salaryField.setImmediate(false);
		}
		eaeGeneralityFormLayout.addComponent(salaryField);

		eaeGeneralityDTO = eaeService.getEAEGenerality(currentEAEId);

		binder = new BeanFieldGroup<EAEGeneralityDTO>(EAEGeneralityDTO.class);
		binder.setItemDataSource(eaeGeneralityDTO);
		binder.setBuffered(false);
		binder.bindMemberFields(this);

		addComponent(eaeGeneralityFormLayout);
		
		// ---------------------------------------
		// DESACTIVATION DES CHAMPS, SAUF SALARY
		// ---------------------------------------
		for(int ligne = 0; ligne <3 ; ligne++) {
			for(int colonne = 0; colonne < 3; colonne++) {
				Component c = eaeGeneralityFormLayout.getComponent(ligne, colonne);
				c.setReadOnly(true);
			}
		}
		// ---------------------------------------
		// ACTIVATION CHAMP SALARY SI MODE MODIFICATION
		// ---------------------------------------
		if(currentMode == EAEConsultationMode.OPEN_COLLAB) {
			Component c = eaeGeneralityFormLayout.getComponent(2, 2);
			c.setReadOnly(false);
		}
	}

	@Override
	public void blur(BlurEvent event) {
		if (!validateEAEGeneralityForm()) {
			Notification.show(resourceBundle.getString("missing.or.invalid.field.msg"),
					Notification.Type.WARNING_MESSAGE);
		} else {
			EAEService eaeS = (EAEService)this.eaeService;
			eaeS.saveEAESalary(eaeGeneralityDTO);
		}

	}

	/**
	 * Test the EAEGeneralityForm validity
	 * 
	 * @return boolean
	 */
	private boolean validateEAEGeneralityForm() {
		boolean isValidGenerality = true;
		if (!this.binder.isValid()) {
			isValidGenerality = false;
		}
		return isValidGenerality;
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
	 * @return the eaeGeneralityFormLayout
	 */
	public GridLayout getEaeGeneralityFormLayout() {
		return eaeGeneralityFormLayout;
	}

	/**
	 * @param eaeGeneralityFormLayout
	 *            the eaeGeneralityFormLayout to set
	 */
	public void setEaeGeneralityFormLayout(GridLayout eaeGeneralityFormLayout) {
		this.eaeGeneralityFormLayout = eaeGeneralityFormLayout;
	}

	/**
	 * @return the lastNameField
	 */
	public TextField getLastNameField() {
		return lastNameField;
	}

	/**
	 * @param lastNameField
	 *            the lastNameField to set
	 */
	public void setLastNameField(TextField lastNameField) {
		this.lastNameField = lastNameField;
	}

	/**
	 * @return the firstNameField
	 */
	public TextField getFirstNameField() {
		return firstNameField;
	}

	/**
	 * @param firstNameField
	 *            the firstNameField to set
	 */
	public void setFirstNameField(TextField firstNameField) {
		this.firstNameField = firstNameField;
	}

	/**
	 * @return the managerLastNameField
	 */
	public TextField getManagerLastNameField() {
		return managerLastNameField;
	}

	/**
	 * @param managerLastNameField
	 *            the managerLastNameField to set
	 */
	public void setManagerLastNameField(TextField managerLastNameField) {
		this.managerLastNameField = managerLastNameField;
	}

	/**
	 * @return the managerFirstNameField
	 */
	public TextField getManagerFirstNameField() {
		return managerFirstNameField;
	}

	/**
	 * @param managerFirstNameField
	 *            the managerFirstNameField to set
	 */
	public void setManagerFirstNameField(TextField managerFirstNameField) {
		this.managerFirstNameField = managerFirstNameField;
	}

	/**
	 * @return the jobField
	 */
	public TextField getJobField() {
		return jobField;
	}

	/**
	 * @param jobField
	 *            the jobField to set
	 */
	public void setJobField(TextField jobField) {
		this.jobField = jobField;
	}

	/**
	 * @return the eaeDateField
	 */
	public TextField getEaeDateField() {
		return eaeDateField;
	}

	/**
	 * @param eaeDateField
	 *            the eaeDateField to set
	 */
	public void setEaeDateField(TextField eaeDateField) {
		this.eaeDateField = eaeDateField;
	}

	/**
	 * @return the prevEaeDateField
	 */
	public TextField getPrevEaeDateField() {
		return prevEaeDateField;
	}

	/**
	 * @param prevEaeDateField
	 *            the prevEaeDateField to set
	 */
	public void setPrevEaeDateField(TextField prevEaeDateField) {
		this.prevEaeDateField = prevEaeDateField;
	}

	/**
	 * @return the employmentDateField
	 */
	public TextField getEmploymentDateField() {
		return employmentDateField;
	}

	/**
	 * @param employmentDateField
	 *            the employmentDateField to set
	 */
	public void setEmploymentDateField(TextField employmentDateField) {
		this.employmentDateField = employmentDateField;
	}

	/**
	 * @return the salaryField
	 */
	public TextField getSalaryField() {
		return salaryField;
	}

	/**
	 * @param salaryField
	 *            the salaryField to set
	 */
	public void setSalaryField(TextField salaryField) {
		this.salaryField = salaryField;
	}

}
//=======
//package com.novedia.talentmap.web.ui.colleague.eae;
//
//import com.novedia.talentmap.model.dto.EAEGeneralityDTO;
//import com.novedia.talentmap.services.IEAEService;
//import com.novedia.talentmap.services.impl.EAEService;
//import com.novedia.talentmap.web.utils.ComponentsId;
//import com.novedia.talentmap.web.utils.Constants;
//import com.vaadin.data.fieldgroup.BeanFieldGroup;
//import com.vaadin.data.fieldgroup.PropertyId;
//import com.vaadin.data.validator.BeanValidator;
//import com.vaadin.event.FieldEvents.BlurEvent;
//import com.vaadin.event.FieldEvents.BlurListener;
//import com.vaadin.ui.Component;
//import com.vaadin.ui.FormLayout;
//import com.vaadin.ui.GridLayout;
//import com.vaadin.ui.Notification;
//import com.vaadin.ui.TextField;
//
//public class EAEGeneralityForm extends FormLayout implements BlurListener {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 3860292803505867673L;
//
//	private IEAEService eaeService;
//
//	private GridLayout eaeGeneralityFormLayout;
//
//	private EAEGeneralityDTO eaeGeneralityDTO;
//	private BeanFieldGroup<EAEGeneralityDTO> binder;
//
//	private Integer currentEAEId;
//	private EAEConsultationMode currentMode;
//
//	@PropertyId(ComponentsId.COLLAB_LAST_NAME_ID)
//	private TextField lastNameField;
//
//	@PropertyId(ComponentsId.COLLAB_FIRST_NAME_ID)
//	private TextField firstNameField;
//
//	@PropertyId(ComponentsId.MANAGER_LAST_NAME_ID)
//	private TextField managerLastNameField;
//
//	@PropertyId(ComponentsId.MANAGER_FIRST_NAME_ID)
//	private TextField managerFirstNameField;
//
//	@PropertyId(ComponentsId.EAE_PROFILE_NAME_ID)
//	private TextField jobField;
//
//	@PropertyId(ComponentsId.EAE_DATE_ID)
//	private TextField eaeDateField;
//
//	@PropertyId(ComponentsId.EAE_PREV_DATE_ID)
//	private TextField prevEaeDateField;
//
//	@PropertyId(ComponentsId.EMPLOYMENT_DATE_ID)
//	private TextField employmentDateField;
//
//	@PropertyId(ComponentsId.EAE_SALARY_ID)
//	private TextField salaryField;
//
//	public EAEGeneralityForm buildEAEGeneralityFormView(Integer currentEAEId, EAEConsultationMode currentMode) {
//		this.currentEAEId = currentEAEId;
//		this.currentMode = currentMode;
//		removeAllComponents();
//		buildMain();
//		return this;
//	}
//
//	private void buildMain() {
//		try {
//			buildLayout();
//			buildEAEGeneralityForm();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void buildLayout() {
//		eaeGeneralityFormLayout.removeAllComponents();
//		this.eaeGeneralityFormLayout.setColumns(3);
//		this.eaeGeneralityFormLayout.setRows(3);
//		this.eaeGeneralityFormLayout.setSizeFull();
//
//	}
//
//	private void buildEAEGeneralityForm() {
//		removeAllComponents();
//
//		// -----------------------------
//		// LAST NAME
//		// -----------------------------
//		lastNameField.setCaption("Name");
//		lastNameField.setId(ComponentsId.COLLAB_LAST_NAME_ID);
//		lastNameField.setWidth("190px");
//		eaeGeneralityFormLayout.addComponent(lastNameField);
//
//		// -----------------------------
//		// FIRST NAME
//		// -----------------------------
//		firstNameField.setCaption("First Name");
//		firstNameField.setStyleName("TODO");
//		firstNameField.setId(ComponentsId.COLLAB_FIRST_NAME_ID);
//		firstNameField.setWidth("190px");
//		firstNameField.addStyleName("spacerInfo");
//		eaeGeneralityFormLayout.addComponent(firstNameField);
//
//		// -----------------------------
//		// PROFILE
//		// -----------------------------
//		jobField.setCaption("Job title");
//		jobField.setStyleName("TODO");
//		jobField.setId(ComponentsId.EAE_PROFILE_NAME_ID);
//		jobField.addStyleName("spacerInfo");
//		eaeGeneralityFormLayout.addComponent(jobField);
//
//		// -----------------------------
//		// MANAGER'S LAST NAME
//		// -----------------------------
//		managerLastNameField.setCaption(Constants.MANAGER_LAST_NAME);
//		managerLastNameField.setStyleName("TODO");
//		managerLastNameField.setId(ComponentsId.MANAGER_LAST_NAME_ID);
//		managerLastNameField.setWidth("190px");
//		managerLastNameField.addStyleName("spacerTop");
//		eaeGeneralityFormLayout.addComponent(managerLastNameField);
//
//		// -----------------------------
//		// MANAGER'S FIRST NAME
//		// -----------------------------
//		managerFirstNameField.setCaption(Constants.MANAGER_FIRST_NAME);
//		managerFirstNameField.setStyleName("TODO");
//		managerFirstNameField.setId(ComponentsId.MANAGER_LAST_NAME_ID);
//		managerFirstNameField.setWidth("190px");
//		managerFirstNameField.addStyleName("spacerInfo spacerTop");
//		eaeGeneralityFormLayout.addComponent(managerFirstNameField);
//
//		// -----------------------------
//		// EMPLOYMENT'S DATE
//		// -----------------------------
//		employmentDateField.setCaption("Date of hire");
//		employmentDateField.setId(ComponentsId.EMPLOYMENT_DATE_ID);
//		employmentDateField.addStyleName("spacerInfo spacerTop");
//		eaeGeneralityFormLayout.addComponent(employmentDateField);
//
//		// -----------------------------
//		// EAE'S DATE
//		// -----------------------------
//		eaeDateField.setCaption(Constants.DATE_OF_EAE);
//		eaeDateField.setId(ComponentsId.EAE_DATE_ID);
//		eaeDateField.addStyleName("spacerTop");
//		eaeGeneralityFormLayout.addComponent(eaeDateField);
//
//		// -----------------------------
//		// PREVIOUS EAE'S DATE
//		// -----------------------------
//		prevEaeDateField.setCaption(Constants.DATE_OF_PREV_EAE);
//		prevEaeDateField.setId(ComponentsId.EAE_PREV_DATE_ID);
//		prevEaeDateField.addStyleName("spacerInfo spacerTop");
//		eaeGeneralityFormLayout.addComponent(prevEaeDateField);
//
//		// -----------------------------
//		// SALARY
//		// -----------------------------
//		salaryField.setCaption(Constants.EAE_SALARY);
//		salaryField.setNullRepresentation("");
//		salaryField.setId(ComponentsId.EAE_SALARY_ID);
//		salaryField.addStyleName("spacerInfo spacerTop");
//		if(currentMode == EAEConsultationMode.OPEN_COLLAB) {
//			salaryField.setRequired(true);
//			salaryField.setRequiredError(Constants.GIVE_SALARY);
//			salaryField.addValidator(new BeanValidator(EAEGeneralityDTO.class,
//					ComponentsId.EAE_SALARY_ID));
//			salaryField.setImmediate(true);
//			salaryField.setValidationVisible(true);
//			salaryField.addBlurListener(this);
//		} else {
//			salaryField.setRequired(false);
//			salaryField.removeAllValidators();
//			salaryField.removeBlurListener(this);
//			salaryField.setValidationVisible(false);
//			salaryField.setImmediate(false);
//		}
//		eaeGeneralityFormLayout.addComponent(salaryField);
//
//		eaeGeneralityDTO = eaeService.getEAEGenerality(currentEAEId);
//
//		binder = new BeanFieldGroup<EAEGeneralityDTO>(EAEGeneralityDTO.class);
//		binder.setItemDataSource(eaeGeneralityDTO);
//		binder.setBuffered(false);
//		binder.bindMemberFields(this);
//
//		addComponent(eaeGeneralityFormLayout);
//		
//		// ---------------------------------------
//		// DESACTIVATION DES CHAMPS, SAUF SALARY
//		// ---------------------------------------
//		for(int ligne = 0; ligne <3 ; ligne++) {
//			for(int colonne = 0; colonne < 3; colonne++) {
//				Component c = eaeGeneralityFormLayout.getComponent(ligne, colonne);
//				c.setReadOnly(true);
//			}
//		}
//		// ---------------------------------------
//		// ACTIVATION CHAMP SALARY SI MODE MODIFICATION
//		// ---------------------------------------
//		if(currentMode == EAEConsultationMode.OPEN_COLLAB) {
//			Component c = eaeGeneralityFormLayout.getComponent(2, 2);
//			c.setReadOnly(false);
//		}
//	}
//
//	@Override
//	public void blur(BlurEvent event) {
//		if (!validateEAEGeneralityForm()) {
//			Notification.show(Constants.EAE_MSG_ERROR_GENERALITY_FORM,
//					Notification.Type.WARNING_MESSAGE);
//		} else {
//			System.out.println("eaeGeneralityDTO=" + eaeGeneralityDTO);
//			EAEService eaeS = (EAEService)this.eaeService;
//			eaeS.saveEAESalary(eaeGeneralityDTO);
//		}
//
//	}
//
//	/**
//	 * Test the EAEGeneralityForm validity
//	 * 
//	 * @return boolean
//	 */
//	private boolean validateEAEGeneralityForm() {
//		boolean isValidGenerality = true;
//		if (!this.binder.isValid()) {
//			isValidGenerality = false;
//		}
//		return isValidGenerality;
//	}
//
//	/**
//	 * @return the eaeService
//	 */
//	public IEAEService getEaeService() {
//		return eaeService;
//	}
//
//	/**
//	 * @param eaeService
//	 *            the eaeService to set
//	 */
//	public void setEaeService(IEAEService eaeService) {
//		this.eaeService = eaeService;
//	}
//
//	/**
//	 * @return the eaeGeneralityFormLayout
//	 */
//	public GridLayout getEaeGeneralityFormLayout() {
//		return eaeGeneralityFormLayout;
//	}
//
//	/**
//	 * @param eaeGeneralityFormLayout
//	 *            the eaeGeneralityFormLayout to set
//	 */
//	public void setEaeGeneralityFormLayout(GridLayout eaeGeneralityFormLayout) {
//		this.eaeGeneralityFormLayout = eaeGeneralityFormLayout;
//	}
//
//	/**
//	 * @return the lastNameField
//	 */
//	public TextField getLastNameField() {
//		return lastNameField;
//	}
//
//	/**
//	 * @param lastNameField
//	 *            the lastNameField to set
//	 */
//	public void setLastNameField(TextField lastNameField) {
//		this.lastNameField = lastNameField;
//	}
//
//	/**
//	 * @return the firstNameField
//	 */
//	public TextField getFirstNameField() {
//		return firstNameField;
//	}
//
//	/**
//	 * @param firstNameField
//	 *            the firstNameField to set
//	 */
//	public void setFirstNameField(TextField firstNameField) {
//		this.firstNameField = firstNameField;
//	}
//
//	/**
//	 * @return the managerLastNameField
//	 */
//	public TextField getManagerLastNameField() {
//		return managerLastNameField;
//	}
//
//	/**
//	 * @param managerLastNameField
//	 *            the managerLastNameField to set
//	 */
//	public void setManagerLastNameField(TextField managerLastNameField) {
//		this.managerLastNameField = managerLastNameField;
//	}
//
//	/**
//	 * @return the managerFirstNameField
//	 */
//	public TextField getManagerFirstNameField() {
//		return managerFirstNameField;
//	}
//
//	/**
//	 * @param managerFirstNameField
//	 *            the managerFirstNameField to set
//	 */
//	public void setManagerFirstNameField(TextField managerFirstNameField) {
//		this.managerFirstNameField = managerFirstNameField;
//	}
//
//	/**
//	 * @return the jobField
//	 */
//	public TextField getJobField() {
//		return jobField;
//	}
//
//	/**
//	 * @param jobField
//	 *            the jobField to set
//	 */
//	public void setJobField(TextField jobField) {
//		this.jobField = jobField;
//	}
//
//	/**
//	 * @return the eaeDateField
//	 */
//	public TextField getEaeDateField() {
//		return eaeDateField;
//	}
//
//	/**
//	 * @param eaeDateField
//	 *            the eaeDateField to set
//	 */
//	public void setEaeDateField(TextField eaeDateField) {
//		this.eaeDateField = eaeDateField;
//	}
//
//	/**
//	 * @return the prevEaeDateField
//	 */
//	public TextField getPrevEaeDateField() {
//		return prevEaeDateField;
//	}
//
//	/**
//	 * @param prevEaeDateField
//	 *            the prevEaeDateField to set
//	 */
//	public void setPrevEaeDateField(TextField prevEaeDateField) {
//		this.prevEaeDateField = prevEaeDateField;
//	}
//
//	/**
//	 * @return the employmentDateField
//	 */
//	public TextField getEmploymentDateField() {
//		return employmentDateField;
//	}
//
//	/**
//	 * @param employmentDateField
//	 *            the employmentDateField to set
//	 */
//	public void setEmploymentDateField(TextField employmentDateField) {
//		this.employmentDateField = employmentDateField;
//	}
//
//	/**
//	 * @return the salaryField
//	 */
//	public TextField getSalaryField() {
//		return salaryField;
//	}
//
//	/**
//	 * @param salaryField
//	 *            the salaryField to set
//	 */
//	public void setSalaryField(TextField salaryField) {
//		this.salaryField = salaryField;
//	}
//
//}
//>>>>>>> branch 'master' of https://github.com/Jean-Max/NovTalentMap.git
