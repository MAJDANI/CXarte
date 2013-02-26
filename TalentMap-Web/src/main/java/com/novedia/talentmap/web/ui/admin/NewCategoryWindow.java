package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.util.CUtils;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.Form;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;


public class NewCategoryWindow extends Window implements ClickListener{
	
	/**
	 * Form
	 */
	private Form newCategoryForm;
	
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
	 * @param adminService
	 */
	public NewCategoryWindow(IAdminService adminService){
		super();
		this.adminService = adminService;
	}
	
	/**
	 * Init component method
	 */
	public void init(){
		
		this.setModal(true);
		this.center();
		this.setWidth(ConstantsEnglish.NEW_CATEGORY_WINDOW_WIDTH);
		this.setCaption(ConstantsEnglish.NEW_CATEGORY_WINDOW_TITLE);
		
		VerticalLayout verticalLayout = new VerticalLayout();
		
		buildForm();
		verticalLayout.addComponent(this.newCategoryForm);
		
		buildButton();
		verticalLayout.addComponent(this.save);
		
		addComponent(verticalLayout);
	}
	
	/**
	 * Build the form
	 */
	private void buildForm(){
		this.newCategoryForm = new Form();
		
		TextField categoryField = new TextField(ConstantsEnglish.NEW_CATEGORY_WINDOW_FIELD_LABEL);
		categoryField.setRequired(true);
		categoryField.setMaxLength(ConstantsEnglish.CATEGORY_NAME_MAX_LENGTH);
		
		this.newCategoryForm.addField(ConstantsEnglish.NEW_CATEGORY_WINDOW_CATEGORY, categoryField);
	}

	/**
	 * Build the button save
	 */
	private void buildButton(){
		this.save = new Button(ConstantsEnglish.SAVE_CAPTION);
		this.save.addListener(this);
	}
	
	/**
	 * Event on save validation 
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		try{
			this.newCategoryForm.validate();
			
			String newCategoryName =(String) this.newCategoryForm.getField(ConstantsEnglish.NEW_CATEGORY_WINDOW_CATEGORY).getValue();
			Category newCategory = Category.builder().name(newCategoryName).build();
			
			if(adminService.checkCategory(newCategory) == null){
				// on appelle le service d'ajout d'une catégorie
				adminService.addCategory(newCategory);
				CUtils.showMessage(ConstantsEnglish.ADMIN_NEW_CATEGORY_CONFIRMATION, getWindow());
				this.close();
			} else {
				getWindow().showNotification(ConstantsEnglish.ADMIN_NEW_CATEGORY_EXISTING, Notification.TYPE_WARNING_MESSAGE);
			}
		} catch (InvalidValueException e){
			getWindow().showNotification(ConstantsEnglish.NEW_CATEGORY_WINDOW_ERROR, Notification.TYPE_ERROR_MESSAGE);
		}
	}
	
	public Form getNewCategoryForm() {
		return newCategoryForm;
	}

	public void setNewCategoryForm(Form newCategoryForm) {
		this.newCategoryForm = newCategoryForm;
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