package com.novedia.talentmap.web.ui.admin;

import java.util.List;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.Tree;

@SuppressWarnings("serial")
public class SearchBySkillTree extends Tree{
	
	/**
    * TalentMap Services
    */
    private IAdminService adminService;
	
	 /**
     * JAVA Object
     */
    private List<Tool> listTool;
    private List<Concept> listConcept;
    private List<Category> listCategory;
	
	/**
	* Default constructor
	*/
	public SearchBySkillTree(){
		super();
	}
	
	public Tree buildManageToolsView(){
		buildTreeSkills();
		return this;
	}

	private SearchBySkillTree buildTreeSkills() {
		/**
		 * We have to select all tools, all concepts and all categories
		 */
		removeAllItems();
		// Build the list tools, concepts and categories
		buildAllList();

		// Build the tree
		setImmediate(true);
		setSelectable(true);
		setNullSelectionAllowed(false);

		for (Category ca : this.listCategory) {

		    addItem(ca.getId() + ":" + Constants.TYPE_CATEGORY + ":"
			    + ca.getName());
		    setItemCaption(ca.getId() + ":" + Constants.TYPE_CATEGORY
			    + ":" + ca.getName(), ca.getName().toUpperCase());

		    for (Concept co : this.listConcept) {

				if (ca.getId().equals(co.getCategory().getId())) {
	
				    addItem(co.getId() + ":" + Constants.TYPE_CONCEPT
					    + ":" + co.getName());
				    setItemCaption(co.getId() + ":"
					    + Constants.TYPE_CONCEPT + ":" + co.getName(), co.getName()
					    .toUpperCase());
	
				    setParent(co.getId() + ":" + Constants.TYPE_CONCEPT
					    + ":" + co.getName(), ca.getId() + ":"
					    + Constants.TYPE_CATEGORY + ":" + ca.getName());
	
				    for (Tool t : this.listTool) {
	
						if (co.getId().equals(t.getConcept().getId())) {
		
						    addItem(t.getId() + ":" + Constants.TYPE_TOOL
							    + ":" + t.getName());
						    setItemCaption(t.getId() + ":"
							    + Constants.TYPE_TOOL + ":" + t.getName(), t
							    .getName().toUpperCase());
		
						    setParent(t.getId() + ":"
							    + Constants.TYPE_TOOL + ":" + t.getName(), co.getId()
							    + ":" + Constants.TYPE_CONCEPT + ":" + co.getName());
		
						    setChildrenAllowed(t.getId() + ":"
							    + Constants.TYPE_TOOL + ":" + t.getName(), false);
		
						}
	
				    }
	
				}

		    }

		}

		
		return this;
		
	}
	
	 /**
     * Populate listTool listConcept and listCategory
     */
    private void buildAllList() {
		this.listTool = this.adminService.getAllTools();
		this.listConcept = this.adminService.getAllConcepts();
		this.listCategory = this.adminService.getAllCategories();
    }

	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public List<Tool> getListTool() {
		return listTool;
	}

	public void setListTool(List<Tool> listTool) {
		this.listTool = listTool;
	}

	public List<Concept> getListConcept() {
		return listConcept;
	}

	public void setListConcept(List<Concept> listConcept) {
		this.listConcept = listConcept;
	}

	public List<Category> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}

}
