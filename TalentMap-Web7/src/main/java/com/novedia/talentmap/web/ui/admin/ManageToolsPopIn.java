package com.novedia.talentmap.web.ui.admin;

import java.util.HashMap;
import java.util.Map;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.web.utils.CUtils;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ManageToolsPopIn extends Window implements ItemClickListener,
		ClickListener {

	private static final String DELETE_BUTTON_CAPTION = "Delete";

	private static final String MODIFY_BUTTON_CAPTION = "Modify";

	private static final String ADD_SKILL_BUTTON_CAPTION = "Add skill";

	/**
	 * TalentMap Service
	 */
	private IAdminService adminService;

	/**
	 * Vaadin components
	 */
	private HorizontalLayout manageContentPanel;

	private VerticalLayout contentPanel;

	private Panel addSkillPanel;

	private Panel treeSkillsPanel;

	private Panel modifySkillFormPanel;

	private SearchBySkillTree searchBySkillTree;

	private ModifyToolForm modifyToolForm;

	private ModifyConceptForm modifyConceptForm;

	private ModifyCategoryForm modifyCategoryForm;
	
	private Button addSkillButton;

	private Button modifyButton;

	private Button deleteButton;

	/**
	 * Current ID Skill
	 */
	private Integer currentCategoryId;
	
	private Integer currentConceptId;
	
	private Integer currentToolId;

	/**
	 * Default constructor
	 */
	public ManageToolsPopIn() {
		super();
		setCaption(Constants.MANAGE_TOOLS_VIEW_TITLE);
		setModal(true);
	}

	public Window buildManageToolsView() {
		removeAllComponents();
		buildContent();

		return this;
	}

	public void buildContent() {
		
		buildButtons();
		buildAddSkillPanel();
		buildTreeSkillsPanel();
		buildSkillFormPanel();
		buildManageContentPanel();
		buildContentPanel();

	}

	private void buildAddSkillPanel(){
		addSkillPanel.removeAllComponents();
		
		addSkillPanel.addComponent(addSkillButton);
	}
	
	private void buildTreeSkillsPanel() {
		treeSkillsPanel.removeAllComponents();
		treeSkillsPanel.addComponent(searchBySkillTree.buildManageToolsView());
		searchBySkillTree.addItemClickListener(this);
	}

	private void buildSkillFormPanel() {
		modifySkillFormPanel.removeAllComponents();

		modifySkillFormPanel.addComponent(modifyCategoryForm
				.buildModifyCategoryForm());
		modifySkillFormPanel.addComponent(modifyConceptForm
				.buildModifyConceptForm());
		modifySkillFormPanel.addComponent(modifyToolForm.buildModifyToolForm());
		modifyCategoryForm.setVisible(false);
		modifyConceptForm.setVisible(false);
		modifyToolForm.setVisible(false);

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.addComponent(modifyButton);
		hLayout.addComponent(deleteButton);
		modifySkillFormPanel.addComponent(hLayout);

		modifySkillFormPanel.setVisible(false);
	}

	private void buildManageContentPanel() {
		manageContentPanel.removeAllComponents();

		manageContentPanel.addComponent(treeSkillsPanel);
		manageContentPanel.addComponent(modifySkillFormPanel);
	}

	private void buildContentPanel() {
		contentPanel.removeAllComponents();

		contentPanel.addComponent(addSkillPanel);
		contentPanel.addComponent(manageContentPanel);

		addComponent(contentPanel);
	}

	private void buildButtons() {
		addSkillButton.setCaption(ADD_SKILL_BUTTON_CAPTION);
		modifyButton.setCaption(MODIFY_BUTTON_CAPTION);
		deleteButton.setCaption(DELETE_BUTTON_CAPTION);

		addSkillButton.addClickListener(this);
		modifyButton.addClickListener(this);
		deleteButton.addClickListener(this);
	}

	private Map<String, Object> splitItemTree(String itemId) {

		Map<String, Object> item = new HashMap<String, Object>();
		String[] itemSplit = itemId.split(":");

		item.put("id", Integer.parseInt(itemSplit[0]));
		item.put("type", Integer.parseInt(itemSplit[1]));
		item.put("name", itemSplit[2]);

		return item;
	}

	/**
	 * 
	 * @class ManageToolsPopIn.java
	 * @param item
	 */
	private void updateItemCategory(Map<String, Object> item) {
		modifyCategoryForm.getCategoryField().setValue(item.get("name"));

		this.currentCategoryId = (Integer) item.get("id");
		this.currentConceptId = null;
		this.currentToolId = null;
	}

	/**
	 * 
	 * @class ManageToolsPopIn.java
	 * @param item
	 */
	private void updateItemConcept(Map<String, Object> item, String itemId) {
		String categoryItemId = (String) this.searchBySkillTree
				.getParent(itemId);
		Map<String, Object> categoryItem = splitItemTree(categoryItemId);

		modifyConceptForm.getCategoryField().setValue(categoryItem.get("name"));

		modifyConceptForm.getConceptField().setValue(item.get("name"));

		this.currentCategoryId = (Integer) categoryItem.get("id");
		this.currentConceptId = (Integer) item.get("id");
		this.currentToolId = null;
	}

	/**
	 * 
	 * @class ManageToolsPopIn.java
	 * @param item
	 */
	private void updateItemTool(Map<String, Object> item, String itemId) {
		String conceptItemId = (String) this.searchBySkillTree
				.getParent(itemId);
		Map<String, Object> conceptItem = splitItemTree(conceptItemId);

		String categoryItemId = (String) this.searchBySkillTree
				.getParent(conceptItemId);
		Map<String, Object> categoryItem = splitItemTree(categoryItemId);

		modifyToolForm.getCategoryField().setValue(categoryItem.get("name"));

		modifyToolForm.getConceptField().setValue(conceptItem.get("name"));

		modifyToolForm.getToolField().setValue(item.get("name"));

		this.currentCategoryId = (Integer) categoryItem.get("id");
		this.currentConceptId = (Integer) conceptItem.get("id");
		this.currentToolId = (Integer) item.get("id");
	}

	/**
	 * Update a category according to the form in the database
	 * 
	 * @class ManageToolsPopIn.java
	 * @param categoryName
	 */
	private void updateSkillCategory(String categoryName) {

		Category category = Category.builder().id(this.currentCategoryId)
				.name(categoryName.toUpperCase()).build();

		try {

			Map<String, Object> mapNotification = this.adminService
					.updateASkill(category, null, null);
			CUtils.showMessage(mapNotification);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Update a concept according to the form in the database
	 * 
	 * @class ManageToolsPopIn.java
	 * @param categoryName
	 * @param conceptName
	 */
	private void updateSkillConcept(String categoryName, String conceptName) {

		Category category = Category.builder().id(this.currentCategoryId)
				.name(categoryName.toUpperCase()).build();

		Concept concept = Concept.builder().id(this.currentConceptId)
				.category(category).name(conceptName.toUpperCase()).build();

		try {

			Map<String, Object> mapNotification = this.adminService
					.updateASkill(category, concept, null);
			CUtils.showMessage(mapNotification);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Update a tool according to the form in the database
	 * 
	 * @class ManageToolsPopIn.java
	 * @param categoryName
	 * @param conceptName
	 * @param toolName
	 */
	private void updateSkillTool(String categoryName, String conceptName,
			String toolName) {

		Category category = Category.builder().id(this.currentCategoryId)
				.name(categoryName.toUpperCase()).build();
		Concept concept = Concept.builder().id(this.currentConceptId)
				.category(category).name(conceptName.toUpperCase()).build();
		Tool tool = Tool.builder().id(this.currentToolId).concept(concept)
				.name(toolName).build();

		try {

			Map<String, Object> mapNotification = this.adminService
					.updateASkill(category, concept, tool);
			CUtils.showMessage(mapNotification);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check if the fields which fill by the user are empty
	 * 
	 * @class ManageToolsPopIn.java
	 * @return
	 */
	public boolean skillFieldAreEmpty() {
		if (modifyCategoryForm.isVisible()) {
			if (modifyCategoryForm.getCategoryField().getValue().length() == 0) {
				return true;
			} else {
				return false;
			}
		} else if (modifyConceptForm.isVisible()) {
			if (modifyConceptForm.getCategoryField().getValue().length() == 0
					|| modifyConceptForm.getConceptField().getValue().length() == 0) {
				return true;
			} else {
				return false;
			}
		} else if (modifyToolForm.isVisible()) {
			if (modifyToolForm.getCategoryField().getValue().length() == 0
					|| modifyToolForm.getConceptField().getValue().length() == 0
					|| modifyToolForm.getToolField().getValue().length() == 0) {
				return true;
			} else {
				return false;
			}
		}
		return true;

	}
	
	public void modifyOneSkill(){
		if (modifyCategoryForm.isVisible()) {
			
			if (skillFieldAreEmpty()) {
				Notification.show("All fields are required");
			} else {
				String categoryName = modifyCategoryForm.getCategoryField()
						.getValue();
				updateSkillCategory(categoryName);
				buildManageToolsView();
			}
			
		} else if (modifyConceptForm.isVisible()) {
			
			if (skillFieldAreEmpty()) {
				Notification.show("All fields are required");
			} else {
				String categoryName = modifyConceptForm.getCategoryField()
						.getValue();
				String conceptName = modifyConceptForm.getConceptField()
						.getValue();
				updateSkillConcept(categoryName,conceptName);
				buildManageToolsView();
			}
			
		} else if (modifyToolForm.isVisible()) {
			
			if (skillFieldAreEmpty()) {
				Notification.show("All fields are required");
			} else {
				String categoryName = modifyToolForm.getCategoryField()
						.getValue();
				String conceptName = modifyToolForm.getConceptField()
						.getValue();
				String toolName = modifyToolForm.getToolField()
						.getValue();
				updateSkillTool(categoryName, conceptName, toolName);
				buildManageToolsView();
			}
		}
	}
	
	public void deleteOneSkill(){
		if (modifyCategoryForm.isVisible()) {
			try {
				Map<String, Object> mapNotification = adminService.deleteCategory(this.currentCategoryId);
				CUtils.showMessage(mapNotification);
				buildManageToolsView();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (modifyConceptForm.isVisible()) {
			
			try {
				Map<String, Object> mapNotification = adminService.deleteConcept(this.currentConceptId);
				CUtils.showMessage(mapNotification);
				buildManageToolsView();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (modifyToolForm.isVisible()) {
			try {
				Map<String, Object> mapNotification = adminService.deleteTool(this.currentToolId);
				CUtils.showMessage(mapNotification);
				buildManageToolsView();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	@Override
	public void itemClick(ItemClickEvent event) {
		modifySkillFormPanel.setVisible(true);
		String itemId = (String) event.getItemId();
		Map<String, Object> item = splitItemTree(itemId);

		// Test if the item clicked is a CATEGORY TYPE
		if ((Integer) item.get("type") == Constants.TYPE_CATEGORY) {

			modifyCategoryForm.setVisible(true);
			modifyConceptForm.setVisible(false);
			modifyToolForm.setVisible(false);

			updateItemCategory(item);
		}

		// Test if the item clicked is a CONCEPT TYPE
		if ((Integer) item.get("type") == Constants.TYPE_CONCEPT) {

			modifyCategoryForm.setVisible(false);
			modifyConceptForm.setVisible(true);
			modifyToolForm.setVisible(false);

			updateItemConcept(item, itemId);
		}

		// Test if the item clicked is a TOOL TYPE
		if ((Integer) item.get("type") == Constants.TYPE_TOOL) {

			modifyCategoryForm.setVisible(false);
			modifyConceptForm.setVisible(false);
			modifyToolForm.setVisible(true);

			updateItemTool(item, itemId);
		}

	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().equals(modifyButton)) {
			modifyOneSkill();
			
		} else if (event.getButton().equals(deleteButton)) {
			deleteOneSkill();
			
		}
	}

	public Panel getTreeSkillsPanel() {
		return treeSkillsPanel;
	}

	public void setTreeSkillsPanel(Panel treeSkillsPanel) {
		this.treeSkillsPanel = treeSkillsPanel;
	}

	public Panel getAddSkillPanel() {
		return addSkillPanel;
	}

	public void setAddSkillPanel(Panel addSkillPanel) {
		this.addSkillPanel = addSkillPanel;
	}

	public Panel getModifySkillFormPanel() {
		return modifySkillFormPanel;
	}

	public void setModifySkillFormPanel(Panel modifySkillFormPanel) {
		this.modifySkillFormPanel = modifySkillFormPanel;
	}

	public SearchBySkillTree getSearchBySkillTree() {
		return searchBySkillTree;
	}

	public void setSearchBySkillTree(SearchBySkillTree searchBySkillTree) {
		this.searchBySkillTree = searchBySkillTree;
	}

	public HorizontalLayout getManageContentPanel() {
		return manageContentPanel;
	}

	public void setManageContentPanel(HorizontalLayout manageContentPanel) {
		this.manageContentPanel = manageContentPanel;
	}

	public VerticalLayout getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(VerticalLayout contentPanel) {
		this.contentPanel = contentPanel;
	}

	public ModifyToolForm getModifyToolForm() {
		return modifyToolForm;
	}

	public void setModifyToolForm(ModifyToolForm modifyToolForm) {
		this.modifyToolForm = modifyToolForm;
	}

	public ModifyConceptForm getModifyConceptForm() {
		return modifyConceptForm;
	}

	public void setModifyConceptForm(ModifyConceptForm modifyConceptForm) {
		this.modifyConceptForm = modifyConceptForm;
	}

	public ModifyCategoryForm getModifyCategoryForm() {
		return modifyCategoryForm;
	}

	public void setModifyCategoryForm(ModifyCategoryForm modifyCategoryForm) {
		this.modifyCategoryForm = modifyCategoryForm;
	}

	public Button getModifyButton() {
		return modifyButton;
	}

	public void setModifyButton(Button modifyButton) {
		this.modifyButton = modifyButton;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public Button getAddSkillButton() {
		return addSkillButton;
	}

	public void setAddSkillButton(Button addSkillButton) {
		this.addSkillButton = addSkillButton;
	}

	public Integer getCurrentCategoryId() {
		return currentCategoryId;
	}

	public void setCurrentCategoryId(Integer currentCategoryId) {
		this.currentCategoryId = currentCategoryId;
	}

	public Integer getCurrentConceptId() {
		return currentConceptId;
	}

	public void setCurrentConceptId(Integer currentConceptId) {
		this.currentConceptId = currentConceptId;
	}

	public Integer getCurrentToolId() {
		return currentToolId;
	}

	public void setCurrentToolId(Integer currentToolId) {
		this.currentToolId = currentToolId;
	}

}
