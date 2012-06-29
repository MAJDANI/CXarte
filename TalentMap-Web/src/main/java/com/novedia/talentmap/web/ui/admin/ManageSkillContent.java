package com.novedia.talentmap.web.ui.admin;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.services.IAdminService;
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
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.themes.Reindeer;

public class ManageSkillContent extends VerticalLayout implements
		ClickListener, ItemClickListener {

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
	 * JAVA Object
	 */
	private List<Tool> listTool;
	private List<Concept> listConcept;
	private List<Category> listCategory;
	private Vector<Object> fieldOrderSkill;
	public static String MAIN_TITLE = "Visualisation des compétences";

	/**
	 * Constants
	 */

	public static final Object[] NAME_FIELD_SKILL = new Object[] { "Catégorie",
			"Concept", "Outil" };
	public static final Object[] FIELD_ORDER_SKILL = new Object[] {
			"category_name", "concept_name", "tool_name" };
	public static final int TYPE_CATEGORY = 1;
	public static final int TYPE_CONCEPT = 2;
	public static final int TYPE_TOOL = 3;

	/**
	 * Flag
	 */
	public static boolean isNewSkill = false;

	// Dummy data
	public static Object[] TOOLS = new String[] { "Spring", "tool1", "tool2",
			"tool3", "tool4" };
	public static Object[] CONCEPTS = new String[] { "IOC", "concept1",
			"concept2", "concept3", "concept4" };
	public static Object[] CATEGORIES = new String[] { "JAVA", ".NET" };

	/**
	 * Build the class AddSkillContent.java
	 * 
	 * @param formSkill
	 * @param treeSkill
	 * @param save
	 */
	public ManageSkillContent(Form formSkill, Tree treeSkill, Button save,
			List<Tool> listTool, List<Concept> listConcept,
			List<Category> listCategory, IAdminService adminService,
			Vector<Object> fieldOrderSkill, Button edit, Button delete,
			Label title, HorizontalLayout body, Button cancel) {
		super();
		this.formSkill = formSkill;
		this.treeSkill = treeSkill;
		this.save = save;
		this.listTool = listTool;
		this.listConcept = listConcept;
		this.listCategory = listCategory;
		this.adminService = adminService;
		this.fieldOrderSkill = fieldOrderSkill;
		this.edit = edit;
		this.delete = delete;
		this.title = title;
		this.body = body;
		this.cancel = cancel;

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

		this.title.setCaption(MAIN_TITLE);
		this.title.setStyle(Reindeer.LABEL_H2);

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

		BeanItem<Item> skillBean = new BeanItem(new VSkill());

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

		this.save.setCaption("Enrergistrer");
		this.save.addListener(this);
		
		this.cancel.setCaption("Annuler");
		this.cancel.addListener(this);
		
		this.edit.setCaption("Modifier");
		this.edit.addListener(this);

		this.delete.setCaption("Supprimer");
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
					+ ":" + ca.getName(), ca.getName());

			for (Concept co : this.listConcept) {

				if (ca.getId().equals(co.getCategory_id())) {

					for (Tool t : this.listTool) {

						if (co.getId().equals(t.getConcept_id())) {

							this.treeSkill.addItem(co.getId() + ":"
									+ TYPE_CONCEPT + ":" + co.getName());
							this.treeSkill.setItemCaption(co.getId() + ":"
									+ TYPE_CONCEPT + ":" + co.getName(),
									co.getName());

							this.treeSkill.setParent(
									co.getId() + ":" + TYPE_CONCEPT + ":"
											+ co.getName(),
									ca.getId() + ":" + TYPE_CATEGORY + ":"
											+ ca.getName());

							this.treeSkill.addItem(t.getId() + ":" + TYPE_TOOL
									+ ":" + t.getName());
							this.treeSkill.setItemCaption(t.getId() + ":"
									+ TYPE_TOOL + ":" + t.getName(),
									t.getName());

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

	private void buildAllList() {

		try {

			this.listTool = this.adminService.getAllTools();
			this.listConcept = this.adminService.getAllConcepts();
			this.listCategory = this.adminService.getAllCategories();

		} catch (Exception e) {

			// Build all lists, dummy data if database is down !

			for (int i = 0; i < 5; i++) {
				Tool t = new Tool(i + 1, i + 1, (String) TOOLS[i]);
				this.listTool.add(t);
			}

			for (int i = 0; i < 5; i++) {
				Random r = new Random();
				int categoryId = r.nextInt(2);
				Concept c = new Concept(i + 1, categoryId,
						(String) CONCEPTS[i], 3.0);
				this.listConcept.add(c);
			}

			for (int i = 0; i < 2; i++) {

				Category c = new Category(i, (String) CATEGORIES[i]);
				this.listCategory.add(c);
			}
		}
	}

	/**
	 * Add one skill(category, concept, tool)
	 * 
	 * @class ManageSkillContent.java
	 */
	private void addOneSkill() {

		// Get all input value (category, concept, tool)
		String categoryName = (String) this.formSkill.getField(
				FIELD_ORDER_SKILL[0]).getValue();
		String conceptName = (String) this.formSkill.getField(
				FIELD_ORDER_SKILL[1]).getValue();
		String toolName = (String) this.formSkill
				.getField(FIELD_ORDER_SKILL[2]).getValue();

		// Test if the field is null
		if (categoryName != null && conceptName != null && toolName != null) {

			VSkill vSkill = new VSkill();
			vSkill.setCategory_name(categoryName.trim());
			vSkill.setConcept_name(conceptName.trim());
			vSkill.setTool_name(toolName.trim());

			// BeanItem<VSkill> vSkillItem = (BeanItem<VSkill>) this.formSkill
			// .getItemDataSource();
			// VSkill vSkill = vSkillItem.getBean();

			try {

				// Add the skill in database
				Map<String, Object> mapNotification = this.adminService
						.addOneSkill(vSkill);
				CUtils.showMessage(mapNotification, getWindow());

				// Re build the tree skill
				buildTreeSkill();

			} catch (Exception e) {

				e.printStackTrace();
			}

		} else {

			CUtils.showMessage("Vous devez remplir tous les champs.",
					Message.WARNING, getWindow());
		}
	}
	
	/**
	 * Show the add view
	 * @class ManageSkillContent.java
	 */
	public void addView(){
	
		this.isNewSkill = true;
		
		this.formSkill.setVisible(true);
		this.treeSkill.setVisible(false);
		
		//We initiate the form
		VSkill newVskill = new VSkill();
		this.formSkill.setItemDataSource(new BeanItem(newVskill));
		this.formSkill.setReadOnly(false);
		
		setManageButtonVisible(false);
	}
	
	/**
	 * Update One Skikll (category, concept, tool)
	 * 
	 * @class ManageSkillContent.java
	 */
	private void updateOneSkill() {
		
		
	}
	
	/**
	 * Show the update view
	 * @class ManageSkillContent.java
	 */
	public void updateView(){
		
		this.isNewSkill = false;
		
		this.formSkill.setVisible(false);
		this.treeSkill.setVisible(true);
		
		setManageButtonVisible(true);
	}
	
	public void setManageButtonVisible(boolean isVisible){
		
		this.edit.setVisible(isVisible);
		this.delete.setVisible(isVisible);
		
		this.save.setVisible(!isVisible);
		this.cancel.setVisible(!isVisible);
	}

	@Override
	public void buttonClick(ClickEvent event) {

		Button button = event.getButton();

		if (button == this.save) {

			if (isNewSkill) {

				this.addOneSkill();
			} else {

				this.updateOneSkill();
			}
		}

		if (button == this.edit) {

			this.formSkill.setReadOnly(false);
			
			setManageButtonVisible(false);
		}
		
		if(button == this.cancel){
			
			this.formSkill.setReadOnly(true);
			
			setManageButtonVisible(true);
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

			this.formSkill.getField(FIELD_ORDER_SKILL[0]).setValue(
					item.get("name"));

			this.formSkill.getField(FIELD_ORDER_SKILL[1]).setValue(null);
			this.formSkill.getField(FIELD_ORDER_SKILL[1]).setEnabled(false);

			this.formSkill.getField(FIELD_ORDER_SKILL[2]).setValue(null);
			this.formSkill.getField(FIELD_ORDER_SKILL[2]).setEnabled(false);
		}

		// Test if the item clicked is a CONCEPT TYPE
		if ((Integer) item.get("type") == TYPE_CONCEPT) {

			String categoryItemId = (String) this.treeSkill.getParent(itemId);
			Map<String, Object> categoryItem = splitItemTree(categoryItemId);

			this.formSkill.getField(FIELD_ORDER_SKILL[0]).setValue(
					categoryItem.get("name"));

			this.formSkill.getField(FIELD_ORDER_SKILL[1]).setValue(
					item.get("name"));
			this.formSkill.getField(FIELD_ORDER_SKILL[1]).setEnabled(true);

			this.formSkill.getField(FIELD_ORDER_SKILL[2]).setValue(null);
			this.formSkill.getField(FIELD_ORDER_SKILL[2]).setEnabled(false);
		}

		// Test if the item clicked is a TOOL TYPE
		if ((Integer) item.get("type") == TYPE_TOOL) {

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

			this.formSkill.getField(FIELD_ORDER_SKILL[2]).setValue(
					item.get("name"));
			this.formSkill.getField(FIELD_ORDER_SKILL[2]).setEnabled(true);

		}

		this.formSkill.setReadOnly(true);
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
	 * @param cancel the cancel to set
	 */
	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}
	
	/**
	 * Get the mAIN_TITLE value
	 * @return the mAIN_TITLE
	 */
	public static String getMAIN_TITLE() {
		return MAIN_TITLE;
	}

	/**
	 * Set the mAIN_TITLE value
	 * @param mAIN_TITLE the mAIN_TITLE to set
	 */
	public static void setMAIN_TITLE(String mAIN_TITLE) {
		MAIN_TITLE = mAIN_TITLE;
	}
}
