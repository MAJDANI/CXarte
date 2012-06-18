package com.novedia.talentmap.web.ui.profile;

import java.util.Map;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.ISkillService;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * List of Collaborator Skills
 * 
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */
public class ListSkill extends VerticalLayout {

	private static final long serialVersionUID = 1557665108285765557L;

	/**
	 * TalentMap Services
	 */
	private ISkillService skillService;

	/**
	 * JAVA Objects
	 */
	private Map<Category, Map> mapSkill;
	
	/**
	 * Vaadin Components
	 */
	private Table tableTools;
	private Accordion accCategory;
	private Accordion accConcept;

	/**
	 * Constants
	 */
	private int COLLAB_ID = 2;
	private String toolSelected;

	/**
	 * 
	 * Build the class ListSkill.java
	 * 
	 * @param skillService
	 * @throws Exception
	 */
	public ListSkill(ISkillService skillService, Table tableTools, Accordion accCategory, Accordion accConcept) throws Exception {
		this.skillService = skillService;
		this.tableTools = tableTools;
		this.accCategory =  accCategory;
		this.accConcept = accConcept;

		setImmediate(true);
		setMargin(true);

		this.mapSkill = this.skillService.getAllCollaboratorSkill(COLLAB_ID);

		// Test if the Collaborator have one skill
		if (!mapSkill.isEmpty() && mapSkill != null) {

			addComponent(buildListSkill(this.mapSkill));

		} else {
			setVisible(false);
		}
	}

	public ListSkill(Map<Category, Map> mapSkill) {
		this.mapSkill = mapSkill;

		// Test if the Collaborator have one skill
		if (!mapSkill.isEmpty() && mapSkill != null) {

			addComponent(buildListSkill(this.mapSkill));

		} else {
			setVisible(false);
		}
	}

	/**
	 * Build the list of the Skills
	 * 
	 * @class ListSkill.java
	 */
	public Accordion buildListSkill(Map<Category, Map> mapSkill) {

		// We organize the skills in tabs and tables: Categories tabs, Concepts
		// tabs and Tools tables

		for (Map.Entry<Category, Map> eCategory : mapSkill.entrySet()) {
			VerticalLayout vLayoutCategory = new VerticalLayout();
			vLayoutCategory.setMargin(true);

			Map<Concept, Map> mapConcept = eCategory.getValue();

			for (Map.Entry<Concept, Map> eConcept : mapConcept.entrySet()) {

				VerticalLayout vLayoutConcept = new VerticalLayout();
				vLayoutConcept.setMargin(true);

				Map<Tool, Integer> mapTool = eConcept.getValue();

				int idTable = 1;
				VerticalLayout vLayoutTool = new VerticalLayout();
				
				this.tableTools.addContainerProperty("Nom de l'outil", String.class,
						null);
				this.tableTools.addContainerProperty("Note", Integer.class, null);

				for (Map.Entry<Tool, Integer> eTool : mapTool.entrySet()) {
					
					this.tableTools.addItem(new Object[] { eTool.getKey().getName(),
							eTool.getValue() }, new Integer(idTable));
					idTable++;
				}
				
				vLayoutTool.addComponent(this.tableTools);
				vLayoutConcept.addComponent(vLayoutTool);

				// Set Tool tables
				setTableTools(this.tableTools);

				// Set Concept tabs Style
				this.accConcept.setStyleName("concept-tab");

				this.accConcept.addTab(vLayoutConcept, eConcept.getKey().getName()+" : "+ eConcept.getKey().getScore())
				.setCaption(eConcept.getKey().getName()+" : "+ eConcept.getKey().getScore());
				vLayoutCategory.addComponent(this.accConcept);
			}

			// Set Categories tabs Style
			setStyleName("category-tab");

			this.accCategory.addTab(vLayoutCategory, eCategory.getKey().getName())
			.setCaption(eCategory.getKey().getName());
		}
		
		return accCategory;
	}
	
	/**
	 * Get the tableTools value
	 * @return the tableTools
	 */
	public Table getTableTools() {
		return tableTools;
	}
	
	/**
	 * 
	 * @class ListSkill.java
	 * @param tableTools
	 */
	private void setTableTools(Table tableTools){
		this.tableTools = tableTools;
		
		//Set Style
		this.tableTools.setStyleName("tool-table");
	
		this.tableTools.setSelectable(true);
		
	}
	
	/**
	 * Set the skillService value
	 * 
	 * @param skillService
	 *            the skillService to set
	 */
	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
	}

	/**
	 * Get the accCategory value
	 * @return the accCategory
	 */
	public Accordion getAccCategory() {
		return accCategory;
	}

	/**
	 * Set the accCategory value
	 * @param accCategory the accCategory to set
	 */
	public void setAccCategory(Accordion accCategory) {
		this.accCategory = accCategory;
	}

	/**
	 * Get the accConcept value
	 * @return the accConcept
	 */
	public Accordion getAccConcept() {
		return accConcept;
	}

	/**
	 * Set the accConcept value
	 * @param accConcept the accConcept to set
	 */
	public void setAccConcept(Accordion accConcept) {
		this.accConcept = accConcept;
	}
	

}
