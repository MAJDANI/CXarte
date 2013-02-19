package com.novedia.talentmap.web.ui.admin;


import java.util.List;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.util.CUtils;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.Window.Notification;

/**
 * Add Tool (tool, concept and category) page in the administrator view
 * @author y.rohr
 *
 */
public class AdminAddSkillContent extends VerticalLayout implements ClickListener, ValueChangeListener, CloseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6263889328996175494L;

	
	/**
	 * Form
	 */
	Form formAddSkill;

	/**
	 * Sub Windows used for concept and category creation
	 */
	private NewCategoryWindow newCategoryWindow;
	private NewConceptWindow newConceptWindow;
	
	/**
	 * Vaadin Buttons
	 */
	private Button save;
	private Button newCategory;
	private Button newConcept;
	
	
	/**
	 * TalentMap Service
	 */
	private IAdminService adminService;

	
	/**
	 * Build the class AddSkillContent.java
	 * 
	 * @param formSkill
	 * @param treeSkill
	 * @param save
	 */
	public AdminAddSkillContent(IAdminService adminService) {
		super();
		this.adminService = adminService;

		mainBuild();
	}

	/**
	 * The main build components
	 * 
	 * @class AddSkillContent.java
	 */
	public void mainBuild() {
		
		setMargin(true);
		setSpacing(true);

		buildFormSkill();
		buildButton();
	}
	
	/**
	 * Builder of the form skill
	 * 
	 * @class AddSkillContent.java
	 */
	public void buildFormSkill() {

		this.formAddSkill = new Form();
		this.formAddSkill.setVisible(true);
		this.formAddSkill.setImmediate(true);
		buildFormSkillFields();
		addComponent(this.formAddSkill);
	}
	
	/**
	 * Build the fields of the form
	 */
	private void buildFormSkillFields(){

		// Select category field
		BeanItemContainer<Category> containerCategory =
		        new BeanItemContainer<Category>(Category.class);
		
		for(Category category : adminService.getAllCategories()){
			containerCategory.addItem(category);
		}
		
		Select categorySelect = new Select(ConstantsEnglish.ADMIN_CATEGORY_SELECT_NAME,containerCategory);
		categorySelect.setNullSelectionAllowed(false);
		categorySelect.setRequired(true);
		categorySelect.setImmediate(true);
		categorySelect.setItemCaptionMode(
	            Select.ITEM_CAPTION_MODE_PROPERTY);
		categorySelect.setItemCaptionPropertyId(ConstantsEnglish.ADMIN_CAPTION_PROPERTY);
		categorySelect.addListener(this);
		
		// Select concept field
		BeanItemContainer<Concept> containerConcept =
		        new BeanItemContainer<Concept>(Concept.class);
		for(Concept concept : adminService.getAllConcepts()){
			containerConcept.addItem(concept);
		}
		
		Select conceptSelect = new Select(ConstantsEnglish.ADMIN_CONCEPT_SELECT_NAME,containerConcept); 
		conceptSelect.setNullSelectionAllowed(false);
		conceptSelect.setImmediate(true);
		conceptSelect.setItemCaptionMode(
	            Select.ITEM_CAPTION_MODE_PROPERTY);
		conceptSelect.setItemCaptionPropertyId(ConstantsEnglish.ADMIN_CAPTION_PROPERTY);
		conceptSelect.setRequired(true);
		conceptSelect.setEnabled(false);
		conceptSelect.addListener(this);
		
		// Tool field
		TextField toolField = new TextField(ConstantsEnglish.ADMIN_TOOL_SELECT_NAME);
		toolField.setNullRepresentation(ConstantsEnglish.ADMIN_NULL_REPRESENTATION);
		toolField.setRequired(true);
		toolField.setEnabled(false);
		
		// on ajouts les fields au form
		this.formAddSkill.addField(ConstantsEnglish.ADMIN_CATEGORY_NAME, categorySelect);
		this.formAddSkill.addField(ConstantsEnglish.ADMIN_CONCEPT_NAME, conceptSelect);
		this.formAddSkill.addField(ConstantsEnglish.ADMIN_TOOL_NAME, toolField);	
	}

	/**
	 * Build Save, New Category and New Concept buttons
	 */
	public void buildButton() {

		HorizontalLayout hLayoutButton = new HorizontalLayout();
		hLayoutButton.setMargin(true);
		hLayoutButton.setSpacing(true);

		this.save = new Button(ConstantsEnglish.SAVE_CAPTION);
		this.save.addListener((ClickListener)this);
		
		this.newCategory = new Button(ConstantsEnglish.ADMIN_NEW_CATEGORY_BUTTON);
		this.newCategory.addListener((ClickListener)this);
		
		this.newConcept = new Button(ConstantsEnglish.ADMIN_NEW_CONCEPT_BUTTON);
		this.newConcept.addListener((ClickListener)this);
		
		hLayoutButton.addComponent(this.save);
		hLayoutButton.addComponent(this.newCategory);
		hLayoutButton.addComponent(this.newConcept);

		addComponent(hLayoutButton);
	}

	/**
	 * Refresh the category select list
	 */
	private void refreshCategoriesAvailable(){
		
		Select categorySelect = (Select) this.formAddSkill.getField(ConstantsEnglish.ADMIN_CATEGORY_NAME);
		BeanItemContainer<Category> containerCategory =
		        new BeanItemContainer<Category>(Category.class);
		for(Category category : adminService.getAllCategories()){
			containerCategory.addItem(category);
		}
		categorySelect.setContainerDataSource(containerCategory);
	}

	/**
	 * Refresh the concept select list
	 */
	private void refreshConceptsAvailable(){
		
		Select conceptSelect = (Select) this.formAddSkill.getField(ConstantsEnglish.ADMIN_CONCEPT_NAME);
		BeanItemContainer<Concept> containerConcept =
		        new BeanItemContainer<Concept>(Concept.class);
		for(Concept concept : adminService.getAllConcepts()){
			containerConcept.addItem(concept);
		}
		conceptSelect.setContainerDataSource(containerConcept);
	}
	
	/**
	 * Events when the user click on a Button
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		Button button = event.getButton();
		
		if (button.getCaption().equals(ConstantsEnglish.SAVE_CAPTION)) {
			try{
				this.formAddSkill.validate();
				
				Concept conceptSelected = (Concept) this.formAddSkill.getField(ConstantsEnglish.ADMIN_CONCEPT_NAME).getValue();	
				String toolName = (String) this.formAddSkill.getField(ConstantsEnglish.ADMIN_TOOL_NAME).getValue();
				Tool newTool = Tool.builder().name(toolName).concept(conceptSelected).build();
				
				//Appelle du service d'ajout de tool
				adminService.addTool(newTool);
				
				CUtils.showMessage(ConstantsEnglish.ADMIN_NEW_TOOL_CONFIRMATION, getWindow());
				this.formAddSkill.getField(ConstantsEnglish.ADMIN_TOOL_NAME).setEnabled(false);
				this.formAddSkill.getField(ConstantsEnglish.ADMIN_CONCEPT_NAME).setEnabled(false);
			} catch (InvalidValueException e){
				getWindow().showNotification(ConstantsEnglish.ADMIN_NEW_TOOL_ERROR, Notification.TYPE_WARNING_MESSAGE);
			}
			
		} else if(button.getCaption().equals(ConstantsEnglish.ADMIN_NEW_CATEGORY_BUTTON)){
			this.newCategoryWindow = new NewCategoryWindow(adminService);
			this.newCategoryWindow.init();
			this.newCategoryWindow.addListener(this);
			getWindow().addWindow(newCategoryWindow);
			
		} else if(button.getCaption().equals(ConstantsEnglish.ADMIN_NEW_CONCEPT_BUTTON)){
			this.newConceptWindow = new NewConceptWindow(adminService);
			this.newConceptWindow.init();
			this.newConceptWindow.addListener(this);
			getWindow().addWindow(newConceptWindow);
		} 
		
	}

	/**
	 * Events when values in Select are modified
	 */
	@Override
	public void valueChange(ValueChangeEvent event) {
			
		Field toolField = this.formAddSkill.getField(ConstantsEnglish.ADMIN_TOOL_NAME);
		Select conceptSelect = (Select) this.formAddSkill.getField(ConstantsEnglish.ADMIN_CONCEPT_NAME);
		
		if(event.getProperty().getValue() instanceof Category){
			// on met à jour la liste des concepts disponibles en fonction de la catégorie sélectionnée
			Category selectedCategory = (Category) event.getProperty().getValue();
			List<Concept> conceptAvailable = adminService.getAllConceptByCategory(selectedCategory);
			
			BeanItemContainer<Concept> containerConcept =
			        new BeanItemContainer<Concept>(Concept.class);
			for(Concept concept : conceptAvailable){
				containerConcept.addItem(concept);
			}
			conceptSelect.setContainerDataSource(containerConcept);
			conceptSelect.setEnabled(true);
			
			if(this.formAddSkill.getField(ConstantsEnglish.ADMIN_TOOL_NAME).isEnabled()){
				toolField.setEnabled(false);
			}
			
		} else {
			toolField.setEnabled(true);
		}
	}
	
	/**
	 * Events when a sub window is closed. 
	 */
	@Override
	public void windowClose(CloseEvent e) {
		if(e.getWindow() instanceof NewCategoryWindow){
			refreshCategoriesAvailable();
		} else {
			refreshConceptsAvailable();
		}
	}
	
	
	public Form getFormAddSkill() {
		return formAddSkill;
	}

	public void setFormAddSkill(Form formSkill) {
		this.formAddSkill = formSkill;
	}

	public Button getSave() {
		return save;
	}

	public void setSave(Button save) {
		this.save = save;
	}

	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public Button getNewCategory() {
		return newCategory;
	}

	public void setNewCategory(Button newCategory) {
		this.newCategory = newCategory;
	}

	public Button getNewConcept() {
		return newConcept;
	}

	public void setNewConcept(Button newConcept) {
		this.newConcept = newConcept;
	}
	
	public NewCategoryWindow getNewCategoryWindow() {
		return newCategoryWindow;
	}

	public void setNewCategoryWindow(NewCategoryWindow newCategoryWindow) {
		this.newCategoryWindow = newCategoryWindow;
	}

	public NewConceptWindow getNewConceptWindow() {
		return newConceptWindow;
	}

	public void setNewConceptWindow(NewConceptWindow newConceptWindow) {
		this.newConceptWindow = newConceptWindow;
	}


}