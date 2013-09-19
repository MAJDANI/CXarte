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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ManageToolsPopIn extends Window implements ItemClickListener  {


	private IAdminService adminService;

	
	private ResourceBundle resourceBundle;
	
	private Panel addCategoryPanel; 
	
//	private Panel treeSkillsPanel;
	
	private HorizontalLayout buttonContainer = new HorizontalLayout();
	
	private Tree treeSkills;
	
	private ISkillService skillService;
	
	private Button editButton = new Button();
	
	private Button deleteButton = new Button();
	
	private Button addButton = new Button();
	

	
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
		CUtils.buildTreeSkills(treeSkills, skillService);
		treeSkills.addItemClickListener(this);
		treeSkills.setImmediate(true);
		addComponent(buttonContainer);
		addComponent(treeSkills);
		return this;
	}
	
	
	private void buildAddCategoryPanel(){
		
		
		
	}
	
	
	@Override
	public void itemClick(ItemClickEvent event) {
		Object item =  event.getItemId();
		builButtonContainer(item);
		
	}

	public void builButtonContainer(Object item){
		buttonContainer.removeAllComponents();
		buttonContainer.setSpacing(true);
		editButton.setCaption(resourceBundle.getString("edit.button.caption"));
		deleteButton.setCaption(resourceBundle.getString("delete.button.caption"));
		
		buttonContainer.addComponent(editButton);
		buttonContainer.addComponent(deleteButton);
		
		if (item instanceof Category) {
			addButton.setCaption(resourceBundle.getString("add.concept.button.caption"));
			buttonContainer.addComponent(addButton);
		} else if (item instanceof Concept) {
			addButton.setCaption(resourceBundle.getString("add.tool.button.caption"));
			buttonContainer.addComponent(addButton);
		} else if (item instanceof Tool) {
			
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
