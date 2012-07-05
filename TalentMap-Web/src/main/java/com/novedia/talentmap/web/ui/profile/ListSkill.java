package com.novedia.talentmap.web.ui.profile;

import java.util.Map;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

/**
 * List of Collaborator Skills
 * 
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */
public class ListSkill extends VerticalLayout implements ItemClickListener{

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
	
	/**
	 * 
	 * Build the class ListSkill.java 
	 * @param mapSkill
	 */
	public ListSkill(Map<Category, Map> mapSkill) {
		this.mapSkill = mapSkill;
		
		//Init the components

		// Test if the Collaborator have one skill
		if (!mapSkill.isEmpty() && mapSkill != null) {
			
			initComponents();
			addComponent(buildListSkill(this.mapSkill));

		} else {
			setVisible(false);
		}
	}
	
	private void initComponents(){
		this.tableTools = new Table();
		this.accCategory = new Accordion();
		this.accConcept = new Accordion();
	}

	/**
	 * Build the list of the Skills
	 * 
	 * @class ListSkill.java
	 */
	public Accordion buildListSkill(Map<Category, Map> mapSkill) {

		// We organize the skills in tabs and tables: Categories tabs, Concepts
		// tabs and Tools tables
		
		//We build a new accordion Category
		Accordion accCategory = new Accordion();
		
		for (Map.Entry<Category, Map> eCategory : mapSkill.entrySet()) {
			VerticalLayout vLayoutCategory = new VerticalLayout();
			vLayoutCategory.setMargin(true);
			
			//We build a new accordion Concept
			Accordion accConcept = new Accordion();
			
			Map<Concept, Map> mapConcept = eCategory.getValue();

			for (Map.Entry<Concept, Map> eConcept : mapConcept.entrySet()) {
				VerticalLayout vLayoutConcept = new VerticalLayout();
				vLayoutConcept.setMargin(true);
				
				//We build a new table Tool
				Table tableTools = new Table();
				tableTools.addListener(this);
				tableTools.setSelectable(true);
				tableTools.setNullSelectionAllowed(true);
				tableTools.setStyleName(TalentMapCSS.TABLE_TOOL);
			
				Map<Tool, Integer> mapTool = eConcept.getValue();

				int idTable = 1;
				VerticalLayout vLayoutTool = new VerticalLayout();
				
				tableTools.addContainerProperty("Nom de l'outil", String.class,
						null);
				tableTools.addContainerProperty("Note", Integer.class, null);

				for (Map.Entry<Tool, Integer> eTool : mapTool.entrySet()) {
					
					tableTools.addItem(new Object[] { eTool.getKey().getName(),
							eTool.getValue() }, new Integer(idTable));
					idTable++;
				}
				
				vLayoutTool.addComponent(tableTools);
				vLayoutConcept.addComponent(vLayoutTool);
			
				// Set Tool tables
				//setTableTools(tableTools);

				// Set Concept tabs Style
				accConcept.setStyleName(TalentMapCSS.TABLE_CONCEPT);

				accConcept.addTab(vLayoutConcept, eConcept.getKey().getName()+" : "+ eConcept.getKey().getScore())
				.setCaption(eConcept.getKey().getName()+" : "+ eConcept.getKey().getScore());
	
				vLayoutCategory.addComponent(accConcept);
			}
			
			// Set Categories tabs Style
			setStyleName(TalentMapCSS.TABLE_CATEGORY);

			accCategory.addTab(vLayoutCategory, eCategory.getKey().getName())
			.setCaption(eCategory.getKey().getName());
		}
		
		return accCategory;
	}
	
	@Override
	public void itemClick(ItemClickEvent event) {
		
		this.tableTools.setValue(null);
		setTableTools((Table) event.getSource());
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
