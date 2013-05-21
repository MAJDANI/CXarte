package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.util.CUtils;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class NewConceptWindow extends Window implements ClickListener {

	/**
	 * Form
	 */
	private Form newConceptForm;

	/**
	 * TalentMap Service
	 */
	private IAdminService adminService;

	/**
	 * Vaadin Button
	 */
	private Button save;

	/**
	 * Constructor
	 * 
	 * @param adminService
	 */
	public NewConceptWindow(IAdminService adminService) {
		super();
		this.adminService = adminService;
	}

	/**
	 * Init component method
	 */
	public void init() {
		this.setModal(true);
		this.center();
		this.setWidth(ConstantsEnglish.NEW_CONCEPT_WINDOW_WIDTH);
		this.setCaption(ConstantsEnglish.NEW_CONCEPT_WINDOW_TITLE);

		VerticalLayout verticalLayout = new VerticalLayout();
		buildForm();
		verticalLayout.addComponent(this.newConceptForm);

		buildButton();
		verticalLayout.addComponent(this.save);

		addComponent(verticalLayout);
	}

	/**
	 * Build the form
	 */
	private void buildForm() {
		this.newConceptForm = new Form();

		// Select category creation
		BeanItemContainer<Category> containerCategory = new BeanItemContainer<Category>(
				Category.class);
		for (Category category : adminService.getAllCategories()) {
			containerCategory.addItem(category);
		}
		Select selectCategory = new Select(
				ConstantsEnglish.NEW_CONCEPT_WINDOW_FIELD_CATEGORY,
				containerCategory);
		selectCategory.setNullSelectionAllowed(false);
		selectCategory.setRequired(true);
		selectCategory.setImmediate(true);
		selectCategory.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
		selectCategory
				.setItemCaptionPropertyId(ConstantsEnglish.NEW_CONCEPT_WINDOW_CAPTION_PROPERTY);
		this.newConceptForm.addField(
				ConstantsEnglish.NEW_CONCEPT_WINDOW_CATEGORY, selectCategory);

		// Concept field creation
		TextField conceptField = new TextField(
				ConstantsEnglish.NEW_CONCEPT_WINDOW_FIELD_CONCEPT);
		conceptField.setRequired(true);
		conceptField.setMaxLength(ConstantsEnglish.CONCEPT_NAME_MAX_LENGTH);
		this.newConceptForm.addField(
				ConstantsEnglish.NEW_CONCEPT_WINDOW_CONCEPT, conceptField);
	}

	/**
	 * Build the button save
	 */
	private void buildButton() {
		this.save = new Button(ConstantsEnglish.SAVE_CAPTION);
		this.save.addListener(this);
	}

	@Override
	public void buttonClick(ClickEvent event) {

		try {
			this.newConceptForm.validate();
			String newConceptName = (String) this.newConceptForm.getField(
					ConstantsEnglish.NEW_CONCEPT_WINDOW_CONCEPT).getValue();
			Category categorySelected = (Category) this.newConceptForm
					.getField(ConstantsEnglish.NEW_CONCEPT_WINDOW_CATEGORY)
					.getValue();
			Concept newConcept = Concept.builder().category(categorySelected)
					.name(newConceptName).build();
			if (adminService.checkConcept(newConcept) == null) {
				adminService.addConcept(newConcept);
				CUtils.showMessage(
						ConstantsEnglish.ADMIN_NEW_CONCEPT_CONFIRMATION,
						getWindow());
				this.close();
			} else {
				getWindow().showNotification(
						ConstantsEnglish.ADMIN_NEW_CONCEPT_EXISTING,
						Notification.TYPE_WARNING_MESSAGE);
			}
		} catch (InvalidValueException e) {
			getWindow().showNotification(
					ConstantsEnglish.NEW_CONCEPT_WINDOW_ERROR,
					Notification.TYPE_ERROR_MESSAGE);
		}
	}

	public Form getNewConceptForm() {
		return newConceptForm;
	}

	public void setNewConceptForm(Form newConceptForm) {
		this.newConceptForm = newConceptForm;
	}

	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public Button getSave() {
		return save;
	}

	public void setSave(Button save) {
		this.save = save;
	}

}
