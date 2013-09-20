package com.novedia.talentmap.web.ui.admin;

import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.CUtils;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ManageToolsPopIn extends Window implements ItemClickListener, ClickListener  {


	private IAdminService adminService;

	private ResourceBundle resourceBundle;
	
	private HorizontalLayout buttonContainer = new HorizontalLayout();
	
	private HorizontalLayout skillsContainer = new HorizontalLayout();
	
	private VerticalLayout editSkillLayout = new VerticalLayout();
	
	private TextField textField = new TextField();
	
	private Button saveButton = new Button();
	
	private Tree treeSkills;
	
	private ISkillService skillService;
	
	private Button deleteButton = new Button();
	
	private Button addButton = new Button();
	
	private Button addCategoryButton = new Button();
	

	
	/**
	 * Default constructor
	 */
	public ManageToolsPopIn() {
		super();
		setModal(true);
		setWidth("500px");
	}

	
	public Window buildManageToolsView() {
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES , locale);
		setCaption(resourceBundle.getString("manage.tool.view.title"));
		removeAllComponents();
		buttonContainer.removeAllComponents();
		addCategoryButton.setCaption(resourceBundle.getString("add.category.button.caption"));
		addCategoryButton.addClickListener(this);
		buttonContainer.addComponent(addCategoryButton);
		CUtils.buildTreeSkills(treeSkills, skillService);
		treeSkills.setImmediate(true);
		treeSkills.addItemClickListener(this);
		skillsContainer.removeAllComponents();
		skillsContainer.addComponent(treeSkills);
		skillsContainer.addComponent(editSkillLayout);
		saveButton.setCaption(resourceBundle.getString("save.button.caption"));
		addComponent(buttonContainer);
		addComponent(skillsContainer);
		return this;
	}
	
	
	
	
	
	@Override
	public void itemClick(ItemClickEvent event) {
		Object item =  event.getItemId();
		editSkillLayout.removeAllComponents();
		editSkillLayout.setSpacing(true);
		editSkillLayout.addComponent(textField);
//		editSkillLayout.addComponent(saveButton);
		if (item instanceof Category) {
			textField.setCaption(resourceBundle.getString("add.category.textfield.caption"));
			Category categ = (Category) item;
			textField.setValue(categ.getName());
			
		} else if (item instanceof Concept) {
			textField.setCaption(resourceBundle.getString("add.concept.textfield.caption"));
			Concept concept = (Concept) item;
			textField.setValue(concept.getName());
			
		} else if (item instanceof Tool) {
			Tool tool = (Tool) item;
			textField.setCaption(resourceBundle.getString("add.tool.textfield.caption"));
			textField.setValue(tool.getName());
		}
		
		builButtonContainer(item);
		
	}

	public void builButtonContainer(Object item){
		buttonContainer.removeAllComponents();
		buttonContainer.setSpacing(true);
		buttonContainer.addComponent(addCategoryButton);
		
		
		if (item instanceof Category) {
			addButton.setCaption(resourceBundle.getString("add.concept.button.caption"));
			buttonContainer.addComponent(addButton);
		} else if (item instanceof Concept) {
			addButton.setCaption(resourceBundle.getString("add.tool.button.caption"));
			buttonContainer.addComponent(addButton);
		}
		
		deleteButton.setCaption(resourceBundle.getString("delete.button.caption"));
		deleteButton.addClickListener(this);
		addButton.addClickListener(this);
		buttonContainer.addComponent(deleteButton);
			
		
	}
	
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().equals(addCategoryButton)) {
			editSkillLayout.removeAllComponents();
			editSkillLayout.setSpacing(true);
			editSkillLayout.addComponent(textField);
			editSkillLayout.addComponent(saveButton);
			textField.setValue("");
			textField.setCaption(resourceBundle.getString("add.category.textfield.caption"));
		} 
		else if (event.getButton().equals(deleteButton)) {
			Object item = treeSkills.getValue();
			if (item instanceof Category) {
				textField.setCaption(resourceBundle.getString("add.category.textfield.caption"));
				Category categ = (Category) item;
				textField.setValue(categ.getName());
				
			} else if (item instanceof Concept) {
				textField.setCaption(resourceBundle.getString("add.concept.textfield.caption"));
				Concept concept = (Concept) item;
				textField.setValue(concept.getName());
				
			} else if (item instanceof Tool) {
				Tool tool = (Tool) item;
				textField.setCaption(resourceBundle.getString("add.tool.textfield.caption"));
				textField.setValue(tool.getName());
			}
		}
		
		
	}

	
	
	
	
	
	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}


	public Tree getTreeSkills() {
		return treeSkills;
	}


	public void setTreeSkills(Tree treeSkills) {
		this.treeSkills = treeSkills;
	}


	public ISkillService getSkillService() {
		return skillService;
	}


	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
	}


	


}
