package com.novedia.talentmap.web.ui;


import java.util.Map;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.IProfileView;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * List of Collaborator Skills
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
	 * Constants
	 */
	private int COLLAB_ID = 2;

	/**
	 * 
	 * Build the class ListSkill.java 
	 * @param skillService
	 * @throws Exception
	 */
	public ListSkill(ISkillService skillService) throws Exception{
		this.skillService = skillService;
		
		setImmediate(true);
		setMargin(true);
		
		this.mapSkill = skillService.getAllCollaboratorSkill(COLLAB_ID);
		
		//Test if the Collaborator have one skill
		if(!mapSkill.isEmpty() && mapSkill!=null){
			
			addComponent(buildListSkill(this.mapSkill));

		}else{
			setVisible(false);
		}
	}
	
	public ListSkill(Map<Category, Map> mapSkill){
		this.mapSkill = mapSkill;
		
		//Test if the Collaborator have one skill
		if(!mapSkill.isEmpty() && mapSkill!=null){
			
			addComponent(buildListSkill(this.mapSkill));

		}else{
			setVisible(false);
		}
	}
	
	
	
	/**
	 * Build the list of the Skills
	 * @class ListSkill.java
	 */
	public Accordion buildListSkill(Map<Category, Map> mapSkill){
		
		//We organize the skills in tabs and tables: Categories tabs, Concepts tabs and Tools tables
		
		Accordion accCategory = new Accordion();
		
		for(Map.Entry<Category, Map> eCategory : mapSkill.entrySet()){
			VerticalLayout vLayout = new VerticalLayout();
			vLayout.setMargin(true);
			
			Map<Concept, Map> mapConcept = eCategory.getValue();
			
			Accordion accConcept = new Accordion();
			
			for(Map.Entry<Concept, Map> eConcept : mapConcept.entrySet()){
				
				VerticalLayout vLayout2 = new VerticalLayout();
				vLayout2.setMargin(true);
				
				Map<Tool, Integer> mapTool = eConcept.getValue();
				
				int idTable = 1;
				Table tableTool = new Table();
				tableTool.addContainerProperty("Nom de l'outil", String.class,  null);
				tableTool.addContainerProperty("Note", Integer.class, null);
				
				
				for(Map.Entry<Tool, Integer> eTool : mapTool.entrySet()){
					VerticalLayout vLayout3 = new VerticalLayout();
					
					tableTool.addItem(new Object[] {eTool.getKey().getName(),eTool.getValue()}, new Integer(idTable));
					idTable++;
					
					vLayout3.addComponent(tableTool);
					vLayout2.addComponent(vLayout3);
				}
				
				
				//Set Tool tables Style
				tableTool.setStyleName("tool-table");
				
				//Set Cocnept tabs Style
				accConcept.setStyleName("concept-tab");
				
				accConcept.addTab(vLayout2,eConcept.getKey().getName());
				vLayout.addComponent(accConcept);
			}
			
			//Set Categories tabs Style
			setStyleName("category-tab");
			
			accCategory.addTab(vLayout, eCategory.getKey().getName());
		}
		
		return accCategory;
	}
	/**
	 * Set the skillService value
	 * @param skillService the skillService to set
	 */
	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
	}
	
	/**
	 * Get the mapSkill value
	 * @return the mapSkill
	 */
	public Map<Category, Map> getMapSkill() {
		return mapSkill;
	}

	/**
	 * Set the mapSkill value
	 * @param mapSkill the mapSkill to set
	 */
	public void setMapSkill(Map<Category, Map> mapSkill) {
		this.mapSkill = mapSkill;
	}

}
