package com.novedia.talentmap.web.ui.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.Message;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * This class populate 3 lists (tool, concept, category)
 * 
 * @author moumbe
 * 
 */
public class ManageSkillContent extends VerticalLayout implements
		ClickListener, ItemClickListener {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Vaadin Components
	 */
	private Form formSkill;
	private Tree treeSkill;
	private Label title;
	private HorizontalLayout body;

	/**
	 * Vaadin Buttons
	 */
	private Button save;
	private Button edit;
	private Button delete;
	private Button cancel;

	/**
	 * TalentMap Service
	 */
	private IAdminService adminService;

	/**
	 * TalentMap Object
	 */
	VSkill currentVSkill;

	/**
	 * JAVA Object
	 */
	private List<Tool> listTool;
	private List<Concept> listConcept;
	private List<Category> listCategory;
	private Vector<Object> fieldOrderSkill;

	// Constant
	public static final Object[] NAME_FIELD_SKILL = new Object[] { "Category",
			"Concept", "Tool" };

	public static final Object[] FIELD_ORDER_SKILL = new Object[] {
			"categoryName", "conceptName", "toolName" };

	// Constant
	public static final int TYPE_CATEGORY = 1;
	public static final int TYPE_CONCEPT = 2;
	public static final int TYPE_TOOL = 3;
	public static final String MESSAGE_FIELD_EMPTY = "You must fill all fields.";

	/**
	 * Current ID Skill
	 */
	private Integer currentCategoryId;
	private Integer currentConceptId;
	private Integer currentToolId;

	/**
	 * Default constructor
	 */
	public ManageSkillContent() {
		super();
	}

	public ManageSkillContent buildViewManageSkillContent() {
		removeAllComponents();
		mainBuild();
		return this;
	}

	/**
	 * The main build components
	 * 
	 * @class AddSkillContent.java
	 */
	public void mainBuild() {

		setMargin(true);
		setSpacing(true);

		this.title.setCaption(ConstantsEnglish.LIST_TOOL_TITLE);
		this.title.setStyleName(Reindeer.LABEL_H2);
		// Deprecated
		// this.title.setStyle(Reindeer.LABEL_H2);

		addComponent(this.title);
		addComponent(this.body);

		buildFormSkill();

		buildTreeSkill();
	}

	/**
	 * Builder of the form skill
	 * 
	 * @class AddSkillContent.java
	 */
	public void buildFormSkill() {

		BeanItem<VSkill> skillBean = new BeanItem<VSkill>(new VSkill());

		CUtils.setOrderForm(this.fieldOrderSkill, FIELD_ORDER_SKILL);

		// Form Factory
		this.formSkill.setFormFieldFactory(new FormFieldFactory() {

			@Override
			public Field createField(Item item, Object propertyId,
					Component uiContext) {

				for (int i = 0; i < FIELD_ORDER_SKILL.length; i++) {

					if (propertyId.equals(FIELD_ORDER_SKILL[i])) {

						TextField textField = new TextField(NAME_FIELD_SKILL[i]
								+ " : ");
						textField.setNullRepresentation("");
						textField
								.setMaxLength(ConstantsEnglish.CATEGORY_NAME_MAX_LENGTH);
						return textField;
					}
				}

				return null;
			}
		});

		this.formSkill.setItemDataSource(skillBean, this.fieldOrderSkill);
		this.formSkill.setVisible(false);

		buildButton();

		this.body.addComponent(this.formSkill);
	}

	public void buildButton() {

		HorizontalLayout hLayoutButton = new HorizontalLayout();
		hLayoutButton.setMargin(true);
		hLayoutButton.setSpacing(true);

		this.save.setCaption("Save");
		this.save.addListener(this);

		this.cancel.setCaption("Cancel");
		this.cancel.addListener(this);

		this.edit.setCaption("Modify");
		this.edit.addListener(this);

		this.delete.setCaption("Delete");
		this.delete.addListener(this);

		hLayoutButton.addComponent(this.save);
		hLayoutButton.addComponent(this.edit);
		hLayoutButton.addComponent(this.delete);
		hLayoutButton.addComponent(this.cancel);

		setManageButtonVisible(true);

		this.formSkill.setFooter(hLayoutButton);
	}

	/**
	 * Builder of the tree skill
	 * 
	 * @class AddSkillContent.java
	 */
	public void buildTreeSkill() {

		this.treeSkill.removeAllItems();
		/**
		 * We have to select all tools, all concepts and all categories
		 */
		// Build the list tools, concepts and categories
		buildAllList();

		// Build the tree
		this.treeSkill.setSelectable(true);
		this.treeSkill.setNullSelectionAllowed(false);
		this.treeSkill.addListener(this);

		for (Category ca : this.listCategory) {

			this.treeSkill.addItem(ca.getId() + ":" + TYPE_CATEGORY + ":"
					+ ca.getName());
			this.treeSkill.setItemCaption(ca.getId() + ":" + TYPE_CATEGORY
					+ ":" + ca.getName(), ca.getName().toUpperCase());

			for (Concept co : this.listConcept) {

				if (ca.getId().equals(co.getCategory().getId())) {

					this.treeSkill.addItem(co.getId() + ":" + TYPE_CONCEPT
							+ ":" + co.getName());
					this.treeSkill.setItemCaption(co.getId() + ":"
							+ TYPE_CONCEPT + ":" + co.getName(), co.getName()
							.toUpperCase());

					this.treeSkill.setParent(co.getId() + ":" + TYPE_CONCEPT
							+ ":" + co.getName(), ca.getId() + ":"
							+ TYPE_CATEGORY + ":" + ca.getName());

					for (Tool t : this.listTool) {

						if (co.getId().equals(t.getConcept().getId())) {

							this.treeSkill.addItem(t.getId() + ":" + TYPE_TOOL
									+ ":" + t.getName());
							this.treeSkill.setItemCaption(t.getId() + ":"
									+ TYPE_TOOL + ":" + t.getName(), t
									.getName().toUpperCase());

							this.treeSkill.setParent(t.getId() + ":"
									+ TYPE_TOOL + ":" + t.getName(), co.getId()
									+ ":" + TYPE_CONCEPT + ":" + co.getName());

							this.treeSkill.setChildrenAllowed(t.getId() + ":"
									+ TYPE_TOOL + ":" + t.getName(), false);

						}

					}

				}

			}

		}

		this.body.addComponent(this.treeSkill);
	}

	/**
	 * Populate listTool listConcept and listCategory
	 */
	private void buildAllList() {

		this.listTool = this.adminService.getAllTools();
		this.listConcept = this.adminService.getAllConcepts();
		this.listCategory = this.adminService.getAllCategories();
	}

	/**
	 * Show the add view
	 * 
	 * @class ManageSkillContent.java
	 */
	public void addView() {

		this.formSkill.setVisible(true);
		this.treeSkill.setVisible(false);

		// We initiate the form
		VSkill newVskill = new VSkill();
		this.formSkill.setItemDataSource(new BeanItem(newVskill));
		this.formSkill.setReadOnly(false);

		setManageButtonVisible(false);
	}

	/**
	 * Update One Skikll (category, concept, tool)
	 * 
	 * @throws Exception
	 * 
	 * @class ManageSkillContent.java
	 */
	private void updateOneSkill() {

		// Get the skill name
		getSkillNameByForm();

		// Test if an enabled field is empty
		if (skillFieldAreEmpty()) {

			CUtils.showMessage(MESSAGE_FIELD_EMPTY, Message.WARNING,
					getWindow());
		} else {

			if (this.currentCategoryId != null && this.currentConceptId == null
					&& this.currentToolId == null) {

				updateSkillCategory(this.currentVSkill.getCategoryName());
			}

			if (this.currentCategoryId != null && this.currentConceptId != null
					&& this.currentToolId == null) {

				updateSkillConcept(this.currentVSkill.getCategoryName(),
						this.currentVSkill.getConceptName());
			}

			if (this.currentCategoryId != null && this.currentConceptId != null
					&& this.currentToolId != null) {

				updateSkillTool(this.currentVSkill.getCategoryName(),
						this.currentVSkill.getConceptName(),
						this.currentVSkill.getToolName());
			}

			// Re build the tree skill
			buildTreeSkill();
		}
	}

	/**
	 * Update a category according to the form in the database
	 * 
	 * @class ManageSkillContent.java
	 * @param categoryName
	 */
	private void updateSkillCategory(String categoryName) {

		Category category = Category.builder().id(this.currentCategoryId)
				.name(categoryName.toUpperCase()).build();

		try {

			Map<String, Object> mapNotification = this.adminService
					.updateASkill(category, null, null);
			CUtils.showMessage(mapNotification, getWindow());
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Update a concept according to the form in the database
	 * 
	 * @class ManageSkillContent.java
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
			CUtils.showMessage(mapNotification, getWindow());
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Update a tool according to the form in the database
	 * 
	 * @class ManageSkillContent.java
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
			CUtils.showMessage(mapNotification, getWindow());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Show the update view
	 * 
	 * @class ManageSkillContent.java
	 */
	public void updateView() {

		this.formSkill.setVisible(false);
		this.treeSkill.setVisible(true);

		setManageButtonVisible(true);
	}

	/**
	 * Put the manage button (edit and delete) to visible
	 * 
	 * @class ManageSkillContent.java
	 * @param isVisible
	 */
	public void setManageButtonVisible(boolean isVisible) {

		this.edit.setVisible(isVisible);
		this.delete.setVisible(isVisible);

		this.save.setVisible(!isVisible);
		this.cancel.setVisible(!isVisible);
	}

	/**
	 * Delete one skill in the database
	 * 
	 * @throws Exception
	 * @class ManageSkillContent.java
	 */
	private void deleteOneSkill() throws Exception {

		Field category = this.formSkill.getField(FIELD_ORDER_SKILL[0]);
		Field concept = this.formSkill.getField(FIELD_ORDER_SKILL[1]);
		Field tool = this.formSkill.getField(FIELD_ORDER_SKILL[2]);

		Map<String, Object> mapNotification = null;

		// Get the skill name
		getSkillNameByForm();

		// Test if an enabled field is empty
		if (skillFieldAreEmpty()) {

			CUtils.showMessage(MESSAGE_FIELD_EMPTY, Message.WARNING,
					getWindow());
		} else {

			if (category.isEnabled() && !concept.isEnabled()
					&& !tool.isEnabled()) {

				mapNotification = this.adminService
						.deleteCategory(this.currentCategoryId);
			}

			if (category.isEnabled() && concept.isEnabled()
					&& !tool.isEnabled()) {

				mapNotification = this.adminService
						.deleteConcept(this.currentConceptId);
			}

			if (category.isEnabled() && concept.isEnabled() && tool.isEnabled()) {

				mapNotification = this.adminService
						.deleteTool(this.currentToolId);
			}

			CUtils.showMessage(mapNotification, getWindow());

			// Re build the tree skill
			buildTreeSkill();
		}
	}

	/**
	 * Fill the field categoryName, conceptName and toolName according to the
	 * form
	 * 
	 * @class ManageSkillContent.java
	 */
	public void getSkillNameByForm() {

		this.currentVSkill = new VSkill();
		String categoryName = null, conceptName = null, toolName = null;

		if (this.formSkill.getField(FIELD_ORDER_SKILL[0]).isEnabled()) {
			categoryName = (String) this.formSkill.getField(
					FIELD_ORDER_SKILL[0]).getValue();
			categoryName = categoryName.trim();
			this.currentVSkill.setCategoryName(categoryName);
		}

		if (this.formSkill.getField(FIELD_ORDER_SKILL[1]).isEnabled()) {
			conceptName = (String) this.formSkill
					.getField(FIELD_ORDER_SKILL[1]).getValue();
			conceptName = conceptName.trim();
			this.currentVSkill.setConceptName(conceptName);
		}

		if (this.formSkill.getField(FIELD_ORDER_SKILL[2]).isEnabled()) {
			toolName = (String) this.formSkill.getField(FIELD_ORDER_SKILL[2])
					.getValue();
			toolName = toolName.trim();
			this.currentVSkill.setToolName(toolName);
		}
	}

	/**
	 * Check if the fields which fill by the user are empty
	 * 
	 * @class ManageSkillContent.java
	 * @return
	 */
	public boolean skillFieldAreEmpty() {

		if ((this.formSkill.getField(FIELD_ORDER_SKILL[0]).isEnabled() && this.currentVSkill
				.getCategoryName().length() == 0)
				|| (this.formSkill.getField(FIELD_ORDER_SKILL[1]).isEnabled() && this.currentVSkill
						.getConceptName().length() == 0)
				|| (this.formSkill.getField(FIELD_ORDER_SKILL[2]).isEnabled() && this.currentVSkill
						.getToolName().length() == 0)) {

			return true;
		} else {

			return false;
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {

		Button button = event.getButton();

		if (button == this.save) {

			this.updateOneSkill();

			// Init the current Id to null
			this.currentCategoryId = null;
			this.currentConceptId = null;
			this.currentToolId = null;

			updateView();
		}

		if (button == this.edit) {

			this.formSkill.setReadOnly(false);

			setManageButtonVisible(false);
		}

		if (button == this.cancel) {
			this.formSkill.setReadOnly(true);

			setManageButtonVisible(true);

		}

		if (button == this.delete) {

			try {

				this.deleteOneSkill();
			} catch (Exception e) {

				e.printStackTrace();
			}

			updateView();
		}

	}

	@Override
	public void itemClick(ItemClickEvent event) {

		String itemId = (String) event.getItemId();
		String[] itemSplit = itemId.split(":");

		Map<String, Object> item = splitItemTree(itemId);

		this.formSkill.setVisible(true);
		this.formSkill.setReadOnly(false);

		setManageButtonVisible(true);

		// Test if the item clicked is a CATEGORY TYPE
		if ((Integer) item.get("type") == TYPE_CATEGORY) {

			updateItemCategory(item);
		}

		// Test if the item clicked is a CONCEPT TYPE
		if ((Integer) item.get("type") == TYPE_CONCEPT) {

			updateItemConcept(item, itemId);
		}

		// Test if the item clicked is a TOOL TYPE
		if ((Integer) item.get("type") == TYPE_TOOL) {

			updateItemTool(item, itemId);
		}

		this.formSkill.setReadOnly(true);
	}

	/**
	 * 
	 * @class ManageSkillContent.java
	 * @param item
	 */
	private void updateItemCategory(Map<String, Object> item) {

		this.formSkill.getField(FIELD_ORDER_SKILL[0])
				.setValue(item.get("name"));

		this.formSkill.getField(FIELD_ORDER_SKILL[1]).setValue(null);
		this.formSkill.getField(FIELD_ORDER_SKILL[1]).setEnabled(false);

		this.formSkill.getField(FIELD_ORDER_SKILL[2]).setValue(null);
		this.formSkill.getField(FIELD_ORDER_SKILL[2]).setEnabled(false);

		this.currentCategoryId = (Integer) item.get("id");
		this.currentConceptId = null;
		this.currentToolId = null;
	}

	/**
	 * 
	 * @class ManageSkillContent.java
	 * @param item
	 * @param itemId
	 */
	private void updateItemConcept(Map<String, Object> item, String itemId) {

		String categoryItemId = (String) this.treeSkill.getParent(itemId);
		Map<String, Object> categoryItem = splitItemTree(categoryItemId);

		this.formSkill.getField(FIELD_ORDER_SKILL[0]).setValue(
				categoryItem.get("name"));

		this.formSkill.getField(FIELD_ORDER_SKILL[1])
				.setValue(item.get("name"));
		this.formSkill.getField(FIELD_ORDER_SKILL[1]).setEnabled(true);

		this.formSkill.getField(FIELD_ORDER_SKILL[2]).setValue(null);
		this.formSkill.getField(FIELD_ORDER_SKILL[2]).setEnabled(false);

		this.currentCategoryId = (Integer) categoryItem.get("id");
		this.currentConceptId = (Integer) item.get("id");
		this.currentToolId = null;
	}

	/**
	 * 
	 * @class ManageSkillContent.java
	 * @param item
	 */
	private void updateItemTool(Map<String, Object> item, String itemId) {

		String conceptItemId = (String) this.treeSkill.getParent(itemId);
		Map<String, Object> conceptItem = splitItemTree(conceptItemId);

		String categoryItemId = (String) this.treeSkill
				.getParent(conceptItemId);
		Map<String, Object> categoryItem = splitItemTree(categoryItemId);

		this.formSkill.getField(FIELD_ORDER_SKILL[0]).setValue(
				categoryItem.get("name"));
		this.formSkill.getField(FIELD_ORDER_SKILL[0]).setEnabled(true);

		this.formSkill.getField(FIELD_ORDER_SKILL[1]).setValue(
				conceptItem.get("name"));
		this.formSkill.getField(FIELD_ORDER_SKILL[1]).setEnabled(true);

		this.formSkill.getField(FIELD_ORDER_SKILL[2])
				.setValue(item.get("name"));
		this.formSkill.getField(FIELD_ORDER_SKILL[2]).setEnabled(true);

		this.currentCategoryId = (Integer) categoryItem.get("id");
		this.currentConceptId = (Integer) conceptItem.get("id");
		this.currentToolId = (Integer) item.get("id");
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
	 * Get the formSkill value
	 * 
	 * @return the formSkill
	 */
	public Form getFormSkill() {
		return formSkill;
	}

	/**
	 * Set the formSkill value
	 * 
	 * @param formSkill
	 *            the formSkill to set
	 */
	public void setFormSkill(Form formSkill) {
		this.formSkill = formSkill;
	}

	/**
	 * Get the treeSkill value
	 * 
	 * @return the treeSkill
	 */
	public Tree getTreeSkill() {
		return treeSkill;
	}

	/**
	 * Set the treeSkill value
	 * 
	 * @param treeSkill
	 *            the treeSkill to set
	 */
	public void setTreeSkill(Tree treeSkill) {
		this.treeSkill = treeSkill;
	}

	/**
	 * Get the save value
	 * 
	 * @return the save
	 */
	public Button getSave() {
		return save;
	}

	/**
	 * Set the save value
	 * 
	 * @param save
	 *            the save to set
	 */
	public void setSave(Button save) {
		this.save = save;
	}

	/**
	 * Get the listTool value
	 * 
	 * @return the listTool
	 */
	public List<Tool> getListTool() {
		return listTool;
	}

	/**
	 * Set the listTool value
	 * 
	 * @param listTool
	 *            the listTool to set
	 */
	public void setListTool(List<Tool> listTool) {
		this.listTool = listTool;
	}

	/**
	 * Get the listConcept value
	 * 
	 * @return the listConcept
	 */
	public List<Concept> getListConcept() {
		return listConcept;
	}

	/**
	 * Set the listConcept value
	 * 
	 * @param listConcept
	 *            the listConcept to set
	 */
	public void setListConcept(List<Concept> listConcept) {
		this.listConcept = listConcept;
	}

	/**
	 * Get the listCategory value
	 * 
	 * @return the listCategory
	 */
	public List<Category> getListCategory() {
		return listCategory;
	}

	/**
	 * Set the listCategory value
	 * 
	 * @param listCategory
	 *            the listCategory to set
	 */
	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}

	/**
	 * Set the adminService value
	 * 
	 * @param adminService
	 *            the adminService to set
	 */
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * Set the fieldOrderSkill value
	 * 
	 * @param fieldOrderSkill
	 *            the fieldOrderSkill to set
	 */
	public void setFieldOrderSkill(Vector<Object> fieldOrderSkill) {
		this.fieldOrderSkill = fieldOrderSkill;
	}

	/**
	 * Set the edit value
	 * 
	 * @param edit
	 *            the edit to set
	 */
	public void setEdit(Button edit) {
		this.edit = edit;
	}

	/**
	 * Set the delete value
	 * 
	 * @param delete
	 *            the delete to set
	 */
	public void setDelete(Button delete) {
		this.delete = delete;
	}

	/**
	 * Get the title value
	 * 
	 * @return the title
	 */
	public Label getTitle() {
		return title;
	}

	/**
	 * Set the title value
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(Label title) {
		this.title = title;
	}

	/**
	 * Set the body value
	 * 
	 * @param body
	 *            the body to set
	 */
	public void setBody(HorizontalLayout body) {
		this.body = body;
	}

	/**
	 * Set the cancel value
	 * 
	 * @param cancel
	 *            the cancel to set
	 */
	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}
}
