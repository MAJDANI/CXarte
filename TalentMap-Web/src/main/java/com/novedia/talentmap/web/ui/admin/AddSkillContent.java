package com.novedia.talentmap.web.ui.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Tree;

public class AddSkillContent extends HorizontalLayout {
	
	/**
	 * Vaadin Components
	 */
	private Form formSkill;
	private Tree treeSkill;
	private Button save;
	
	/**
	 * JAVA Object 
	 */
	private List<Tool> listTool;
	private List<Concept> listConcept;
	private List<Category> listCategory;

	/**
	 * Constants
	 */
	
	// Dummy data
	public static Object[] TOOLS = new String[]{"Spring, tool1, tool2, tool3, tool4"};
	public static Object[] CONCEPTS = new String[]{"IOC", "concept1", "concept2", "concept3", "concept4"};
	public static Object[] CATEGORIES = new String[]{"JAVA", ".NET"};
	
	/**
	 * Build the class AddSkillContent.java 
	 * @param formSkill
	 * @param treeSkill
	 * @param save
	 */
	public AddSkillContent(Form formSkill, Tree treeSkill, Button save, List<Tool> listTool, List<Concept> listConcept, List<Category> listCategory) {
		super();
		this.formSkill = formSkill;
		this.treeSkill = treeSkill;
		this.save = save;
		this.listTool = listTool;
		this.listConcept = listConcept;
		this.listCategory = listCategory;
		
		mainBuild();
	}
	
	/**
	 * The main build components
	 * @class AddSkillContent.java
	 */
	public void mainBuild(){
		
		this.setMargin(true);
		this.setSpacing(true);
		
		buildFormSkill();
		
		buildTreeSkill();
	}
	
	/**
	 * Builder of the form skill
	 * @class AddSkillContent.java
	 */
	public void buildFormSkill(){
		
		BeanItem<Item> skillBean = new BeanItem(new VSkill());
		this.formSkill.setItemDataSource(skillBean);
		
		addComponent(this.formSkill);
	}
	
	/**
	 * Builder of the tree skill
	 * @class AddSkillContent.java
	 */
	public void buildTreeSkill(){
		
		/**
		 * We have to select all tools, all concepts and all categories
		 */
		//Build the list tools, concepts and categories
		buildAllList();
		
		//Build the tree
		for(Category ca : this.listCategory){
			
			this.treeSkill.addItem(ca.getName());
			
			for(Concept co : this.listConcept){
				
				if(ca.getId().equals(co.getCategory_id())){
					this.treeSkill.addItem(co.getName());
				}
				
				for(Tool t : this.listTool){
					
					if(co.getId().equals(t.getConcept_id())){
						this.treeSkill.addItem(t.getName());
					}
					
				}
			}
		}
		
	}
	
	private void buildAllList(){
		
		//Build all lists
		for(int i = 0; i < 5; i++){
			Tool t = new Tool(String.valueOf(i+1),String.valueOf(i+1),(String) TOOLS[i]);
			this.listTool.add(t);
		}
		
		for(int i = 0; i < 5; i++){
			Random r = new Random();
			int categoryId = r.nextInt(1)+1;
			Concept c = new Concept(String.valueOf(i+1), String.valueOf(categoryId) , (String) CONCEPTS[i], 3.0);
			this.listConcept.add(c);
		}
		
		for(int i=0; i < 2; i++){
			
			Category c = new Category(String.valueOf(i), (String) CATEGORIES[i]);
			this.listCategory.add(c);
		}
	}
	
	/**
	 * Get the formSkill value
	 * @return the formSkill
	 */
	public Form getFormSkill() {
		return formSkill;
	}
	/**
	 * Set the formSkill value
	 * @param formSkill the formSkill to set
	 */
	public void setFormSkill(Form formSkill) {
		this.formSkill = formSkill;
	}
	/**
	 * Get the treeSkill value
	 * @return the treeSkill
	 */
	public Tree getTreeSkill() {
		return treeSkill;
	}
	/**
	 * Set the treeSkill value
	 * @param treeSkill the treeSkill to set
	 */
	public void setTreeSkill(Tree treeSkill) {
		this.treeSkill = treeSkill;
	}
	/**
	 * Get the save value
	 * @return the save
	 */
	public Button getSave() {
		return save;
	}
	/**
	 * Set the save value
	 * @param save the save to set
	 */
	public void setSave(Button save) {
		this.save = save;
	}
	
	/**
	 * Get the listTool value
	 * @return the listTool
	 */
	public List<Tool> getListTool() {
		return listTool;
	}

	/**
	 * Set the listTool value
	 * @param listTool the listTool to set
	 */
	public void setListTool(List<Tool> listTool) {
		this.listTool = listTool;
	}

	/**
	 * Get the listConcept value
	 * @return the listConcept
	 */
	public List<Concept> getListConcept() {
		return listConcept;
	}

	/**
	 * Set the listConcept value
	 * @param listConcept the listConcept to set
	 */
	public void setListConcept(List<Concept> listConcept) {
		this.listConcept = listConcept;
	}

	/**
	 * Get the listCategory value
	 * @return the listCategory
	 */
	public List<Category> getListCategory() {
		return listCategory;
	}

	/**
	 * Set the listCategory value
	 * @param listCategory the listCategory to set
	 */
	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}
}
