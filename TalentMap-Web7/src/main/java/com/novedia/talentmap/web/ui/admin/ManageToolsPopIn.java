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
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ManageToolsPopIn extends Window implements  ClickListener, ValueChangeListener, BlurListener, TextChangeListener {

	private final int addCategoryView = 1;
	
	private final  int addConceptView = 2;
	
	private final int addToolView = 3;
	
	private int currentView ;

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
	
	private Button addConceptButton = new Button();
	
	private Button addToolButton = new Button();
	
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
		
		deleteButton.addClickListener(this);
		deleteButton.addStyleName("delBtn");
		addConceptButton.addClickListener(this);
		addConceptButton.addStyleName("styleButton");
		addToolButton.addClickListener(this);
		addToolButton.addStyleName("styleButton");
		
		buttonContainer.removeAllComponents();
		buttonContainer.setSpacing(true);
		addCategoryButton.setCaption(resourceBundle.getString("add.category.button.caption"));
		addCategoryButton.addClickListener(this);
		addCategoryButton.addStyleName("styleButton");
		buttonContainer.addComponent(addCategoryButton);
		treeSkills.addValueChangeListener(this);
		treeSkills.removeAllItems();
		CUtils.buildTreeSkills(treeSkills, skillService);
		treeSkills.setImmediate(true);
		skillsContainer.removeAllComponents();
		skillsContainer.addComponent(treeSkills);
		editSkillLayout.removeAllComponents();
		skillsContainer.addComponent(editSkillLayout);
		saveButton.setCaption(resourceBundle.getString("save.button.caption"));
		saveButton.addStyleName("styleButton");
		saveButton.addClickListener(this);
		addComponent(buttonContainer);
		addComponent(skillsContainer);
		return this;
	}
	
	
	

	public void builLayout(Object item){
		buttonContainer.removeAllComponents();
		
		editSkillLayout.removeAllComponents();
		editSkillLayout.setSpacing(true);
		
		buttonContainer.setSpacing(true);
		buttonContainer.addComponent(addCategoryButton);
		
		if (item != null) {
			
			if (item instanceof Category) {
				addConceptButton.setCaption(resourceBundle.getString("add.concept.button.caption"));
				buttonContainer.addComponent(addConceptButton);
				
				textField.setCaption(resourceBundle.getString("add.category.textfield.caption"));
				Category categ = (Category) item;
				textField.setValue(categ.getName());
				
			} else if (item instanceof Concept) {
				addToolButton.setCaption(resourceBundle.getString("add.tool.button.caption"));
				buttonContainer.addComponent(addToolButton);
				
				textField.setCaption(resourceBundle.getString("add.concept.textfield.caption"));
				Concept concept = (Concept) item;
				textField.setValue(concept.getName());
				
			} else if (item instanceof Tool) {
				textField.setCaption(resourceBundle.getString("add.tool.textfield.caption"));
				Tool tool = (Tool) item;
				textField.setValue(tool.getName());
			}
			
			textField.addBlurListener(this);
			textField.removeTextChangeListener(this);
			editSkillLayout.addComponent(textField);
			
			deleteButton.setCaption(resourceBundle.getString("delete.button.caption"));
			buttonContainer.addComponent(deleteButton);
		}
		
	}
	
	
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		Object itemSelected = treeSkills.getValue();
		builLayout(itemSelected);
	}
	
	
	@Override
	public void textChange(TextChangeEvent event) {
		String value = event.getText();
		value = value.trim();
		if(value.length() != 0){
			saveButton.setEnabled(true);
		}else {
			saveButton.setEnabled(false);
		}
		
	}
	
	
	@Override
	public void blur(BlurEvent event) {
		
		if(textField.getValue().length()  !=  0){
			Object itemSelected = treeSkills.getValue();
			String newValue = textField.getValue();
			newValue = newValue.trim();
			if (itemSelected instanceof Category) {
				Category categ = (Category) itemSelected;
				categ.setName(newValue.toUpperCase());
				adminService.updateASkill(categ, null, null);
				treeSkills.setItemCaption(itemSelected, newValue.toUpperCase());
			} else if (itemSelected instanceof Concept) {
				Concept concept = (Concept) itemSelected;
				Category categParent =  (Category) treeSkills.getParent(itemSelected);
				concept.setName(newValue.toUpperCase());
				concept.setCategory(categParent);
				adminService.saveConcept(concept);
				treeSkills.setItemCaption(itemSelected, newValue.toUpperCase());
				
			} else if (itemSelected instanceof Tool) {
				Concept conceptParent =  (Concept) treeSkills.getParent(itemSelected);
				Tool tool = (Tool) itemSelected;
				tool.setName(newValue);
				tool.setConcept(conceptParent);
				adminService.updateTool(tool);
				treeSkills.setItemCaption(itemSelected, newValue);
			} 
		}
		
	}
	
	
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().equals(addCategoryButton) || event.getButton().equals(addConceptButton) || event.getButton().equals(addToolButton)) {
			editSkillLayout.removeAllComponents();
			editSkillLayout.setSpacing(true);
			editSkillLayout.addComponent(textField);
			editSkillLayout.addComponent(saveButton);
			saveButton.setEnabled(false);
			textField.setValue("");
			textField.removeBlurListener(this);
			textField.addTextChangeListener(this);
			if (event.getButton().equals(addCategoryButton)) {
				textField.setCaption(resourceBundle.getString("add.category.textfield.caption"));
				currentView = addCategoryView;
			} else if (event.getButton().equals(addConceptButton)) {
				textField.setCaption(resourceBundle.getString("add.concept.textfield.caption"));
				currentView = addConceptView;
			} else {
				textField.setCaption(resourceBundle.getString("add.tool.textfield.caption"));
				currentView = addToolView;
			}
			
		} 
		else if (event.getButton().equals(deleteButton)) {
			Object itemSelected = treeSkills.getValue();
			//TODO fenetre de confirmation
			if (itemSelected instanceof Category) {
				Category category = (Category) itemSelected;
				try {
					adminService.deleteCategory(category.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (itemSelected instanceof Concept) {
				Concept concept = (Concept) itemSelected;
				adminService.deleteConcept(concept.getId());
				
			} else if (itemSelected instanceof Tool) {
				Tool tool = (Tool) itemSelected;
				adminService.deleteTool(tool.getId());
			}
			
			buildManageToolsView();
			
		} else if (event.getButton().equals(saveButton)) {
			Object itemSelected = treeSkills.getValue();
			String newSkill = textField.getValue();
			newSkill = newSkill.trim();
			switch (currentView) {
			
				case addCategoryView :
				{
					Category newCategory = Category.builder().name(newSkill.toUpperCase()).build();
					adminService.addCategory(newCategory);
					break;
				}
				case addConceptView :
				{
					Category category = (Category) itemSelected;
					Concept newConcept = Concept.builder().name(newSkill.toUpperCase()).category(category).build();
					adminService.addConcept(newConcept);
					break;
				}
				case addToolView :
				{
					Concept concept = (Concept) itemSelected;
					Tool newTool = Tool.builder().name(newSkill).concept(concept).build();
					adminService.addTool(newTool);
					break;
				}
	
			}
			buildManageToolsView();
			
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
